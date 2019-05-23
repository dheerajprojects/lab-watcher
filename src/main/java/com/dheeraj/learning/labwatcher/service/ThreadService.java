package com.dheeraj.learning.labwatcher.service;

import com.dheeraj.learning.labwatcher.dao.PerfStatDAO;
import com.dheeraj.learning.labwatcher.dto.ParamDataDTO;
import com.dheeraj.learning.labwatcher.dto.PerfStatDTO;
import com.dheeraj.learning.labwatcher.dto.ScenarioDataDTO;
import com.dheeraj.learning.labwatcher.entity.ParamData;
import com.dheeraj.learning.labwatcher.entity.PerfStat;
import com.dheeraj.learning.labwatcher.entity.ScenarioData;
import com.dheeraj.learning.labwatcher.util.DataUtil;
import com.dheeraj.learning.labwatcher.util.DegradationIdentificationUtil;
import com.dheeraj.learning.labwatcher.util.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Service
public class ThreadService {
    private static final Logger logger = LoggerFactory.getLogger(ThreadService.class);

    private static Integer MAX_DATA_SIZE=50;
    private static Integer DECENT_DATA_SIZE=20;
    private static Integer MIN_DATA_SIZE=5;
    private static Double MAX_DATA_ACCURACY=70.0;
    private static Double DECENT_DATA_ACCURACY=60.0;
    private static Double MIN_DATA_ACCURACY=60.0;

    @Autowired
    private PerfStatDAO perfStatDAO;

    @Autowired
    private PerfStatService perfStatService;

    @Async
    public CompletableFuture<ParamDataDTO> analysePerfMetric(String scenarioName, String prpcVersion, String currentBuildLabel,
                                                       boolean isHead, Map<String, ParamDataDTO> baselineBuildMap,
                                                       Map<String, ParamDataDTO> currentBuildParamMap, String currentPerfMetric) {
        logger.info("Started new thread for performance metric : "+currentPerfMetric);
        ParamDataDTO baselineBuildParamDataDTO = baselineBuildMap.get(currentPerfMetric);
        //The second condition occurs below only when the first condition is false
        if( baselineBuildParamDataDTO  == null) {
            analyseWhenResultsAreStableForNBuilds(scenarioName, prpcVersion, currentBuildLabel, currentBuildParamMap, currentPerfMetric, MAX_DATA_SIZE, MAX_DATA_ACCURACY);
        } else if (baselineBuildParamDataDTO.getBaselineBuildPosition() > DECENT_DATA_SIZE) {
            analyseWhenResultsAreStableForNBuilds(scenarioName, prpcVersion, currentBuildLabel, currentBuildParamMap, currentPerfMetric, baselineBuildParamDataDTO.getBaselineBuildPosition(), DECENT_DATA_ACCURACY);
        } else if (baselineBuildParamDataDTO.getBaselineBuildPosition() >= MIN_DATA_SIZE && baselineBuildParamDataDTO.getBaselineBuildPosition() <= DECENT_DATA_SIZE){
            analyseWhenResultsAreStableForNBuilds(scenarioName, prpcVersion, currentBuildLabel, currentBuildParamMap, currentPerfMetric, baselineBuildParamDataDTO.getBaselineBuildPosition(), MIN_DATA_ACCURACY);
        } else {
            int rank = baselineBuildParamDataDTO.getBaselineBuildPosition();
            Double accuracy = 0.0;
            if (baselineBuildParamDataDTO.isDegraded()) {
                accuracy = analyseWhenRecentBuildsHaveVariation(false, scenarioName, prpcVersion, currentBuildLabel, baselineBuildMap.get(currentPerfMetric).getBuildLabel(), currentPerfMetric, rank, true);
            } else if (baselineBuildParamDataDTO.isImproved()) {
                accuracy = analyseWhenRecentBuildsHaveVariation(false, scenarioName, prpcVersion, currentBuildLabel, baselineBuildMap.get(currentPerfMetric).getBuildLabel(), currentPerfMetric, rank, false);
            } else {
                logger.debug("Though this currentPerfMetric is neither improved nor degraded somehow this data got into database incorrectly.");
            }

            decideAndSendEmail(baselineBuildParamDataDTO, accuracy);
        }
        logger.info("Completed new thread for performance metric : "+currentPerfMetric);
        return CompletableFuture.completedFuture(currentBuildParamMap.get(currentPerfMetric));
    }

    /**
     * When there are no major deviations in the last #DECENT_DATA_SIZE number of builds, this method is executed to
     * identify if the current build as degraded/improved.
     *
     * @param scenarioName
     * @param prpcVersion
     * @param currentBuildLabel
     * @param currentBuildParamMap
     * @param param
     */
    private void analyseWhenResultsAreStableForNBuilds(String scenarioName, String prpcVersion, String currentBuildLabel,
                                                       Map<String, ParamDataDTO> currentBuildParamMap, String param, Integer rank,
                                                       Double accuracy) {
        //TODO: Yet to write logic to identify if the build is HEAD or not.
        List<PerfStat> perfStats = perfStatDAO.getPerfStatsForLastNBuilds(scenarioName, prpcVersion, currentBuildLabel, rank, true);

        List<PerfStatDTO> perfStatDTOs = Mapper.copyResultsToDTO(perfStats);

        //Calc mean and std
        DegradationIdentificationUtil.calcStandardDeviation(perfStatDTOs, param, currentBuildParamMap.get(param));

        //Get latset bulid values
        PerfStat currentBuildPerfStat = perfStatDAO.getPerfStatForAGivenBuild(scenarioName, currentBuildLabel);
        PerfStatDTO currentBuildPerfStatDTO = new PerfStatDTO();
        BeanUtils.copyProperties(currentBuildPerfStat, currentBuildPerfStatDTO);

        //Compare
        boolean isVaried = DegradationIdentificationUtil.isVaried(currentBuildParamMap.get(param), param, currentBuildPerfStatDTO.getDouble(param));
        if(isVaried) {
            currentBuildParamMap.get(param).setAccuracy(accuracy);
            DataUtil.printVariationMessage(currentBuildParamMap.get(param));
        }
    }

    private Double analyseWhenRecentBuildsHaveVariation(boolean isAverageRecentBuilds, String scenarioName, String prpcVersion, String currentBuildLabel, String degradedBuild, String param, Integer rank, boolean isForDegradationCheck) {
        Double currentParamValue;
        if(isAverageRecentBuilds) {  //This logic takes the average of the last n (<5) builds and compare with the last degraded value.
            List<PerfStat> perfStats = perfStatDAO.getPerfStatsBetweenBuilds(scenarioName, prpcVersion, degradedBuild, currentBuildLabel, rank);

            List<PerfStatDTO> perfStatDTOs = Mapper.copyResultsToDTO(perfStats);
            Double consolidatedMean = DegradationIdentificationUtil.getMean(perfStatDTOs, param);
            currentParamValue = consolidatedMean;
        } else { //This logic just compares the current value with last degraded value.
            PerfStat currentBuildPerfStat = perfStatDAO.getPerfStatForAGivenBuild(scenarioName, currentBuildLabel);
            PerfStatDTO perfStatDTO = new PerfStatDTO();
            BeanUtils.copyProperties(currentBuildPerfStat, perfStatDTO);

            currentParamValue = perfStatDTO.getDouble(param);
        }

        ParamData paramData = perfStatDAO.getParamDataForAGivenBuild(scenarioName, param, degradedBuild);
        ParamDataDTO paramDataDTO = Mapper.convert(paramData);

        double variationPercentage = (paramDataDTO.getMean() == 0 ? 0 : (((currentParamValue - paramDataDTO.getMean()) / paramDataDTO.getMean()) * 100));
        boolean isDegraded = DegradationIdentificationUtil.isDegraded(currentParamValue, paramDataDTO.getMean(), paramDataDTO.getStandardDeviation(), 2, true, variationPercentage);
        boolean isImproved = DegradationIdentificationUtil.isImproved(currentParamValue, paramDataDTO.getMean(), paramDataDTO.getStandardDeviation(), 2, true, variationPercentage);
        logger.debug("ScenarioName : "+scenarioName+", currentBuildLabel : "+currentBuildLabel+" Variation status : isDegraded : "+isDegraded+", isImproved : "+isImproved);

        Double accuracy = 0.0;
        if ((isForDegradationCheck && isDegraded) || (!isForDegradationCheck && isImproved)) {
            paramDataDTO.setAccuracy(paramDataDTO.getAccuracy() + 10.0);
            accuracy = paramData.getAccuracy() + 10.0;
            logger.debug("Accuracy increased for scenario : "+paramDataDTO.getScenarioName()+", build : "+paramDataDTO.getBuildLabel()+", param : "+paramDataDTO.getParamName());
        } else {
            paramDataDTO.setAccuracy(paramDataDTO.getAccuracy() - 10.0);
            accuracy = paramData.getAccuracy() - 10.0;
            logger.debug("Accuracy decreased for scenario : "+paramDataDTO.getScenarioName()+", build : "+paramDataDTO.getBuildLabel()+", param : "+paramDataDTO.getParamName());
        }

        //Send Email
        //Send the last degraded content as email //Also put accuracy in the email.

        //Check if the last degradation/baseline is an outlier. If it is then remove/update it from database that it is not degraded.
        boolean isLastVariationInvalid = isLastVariationInvalid(rank, accuracy);
        if(!isLastVariationInvalid) {
            perfStatDAO.findAndRemoveVariation(paramData);
            //Rerun later builds after this degradation.
            rerunLaterBuildsAfterDegradation(scenarioName, prpcVersion, rank, currentBuildLabel, paramData);
        } else {
            //Can change this save logic later
            perfStatDAO.findAndUpdate(paramData, accuracy);
        }

        return accuracy;
    }

    public void decideAndSendEmail(ParamDataDTO variedBuildParamDTO, Double accuracy) {
        boolean sendEmail = false;
        Integer rank = variedBuildParamDTO.getBaselineBuildPosition();
        if(rank >= 3) {
            if(rank == 3 && accuracy >= 90) {
                sendEmail = true;
            } else if (rank == 4 && accuracy >= 80) {
                sendEmail = true;
            }
        }
        if(sendEmail) {
            ScenarioData scenarioData = perfStatDAO.getScenarioData(variedBuildParamDTO.getScenarioName(), variedBuildParamDTO.getBuildLabel());
            ScenarioDataDTO scenarioDataDTO = Mapper.convert(scenarioData);
            EmailService.sendEmail(scenarioDataDTO);
        }
    }

    /**
     * TODO Make use of rank attribute from the caller to cross check the data.
     * @param scenarioName
     * @param prpcVersion
     * @param rank
     * @param endBuildLabel
     */
    public void rerunLaterBuildsAfterDegradation(String scenarioName, String prpcVersion, Integer rank, String endBuildLabel, ParamData paramData) {
        List<PerfStat> perfStats = perfStatDAO.getPerfStatsForLastNBuilds(scenarioName, prpcVersion, endBuildLabel, rank-1, true);
        List<String> paramList = new ArrayList<>();
        paramList.add(paramData.getParamName());
        for (PerfStat perfstat : perfStats) {
            //Make sure to invoke this w.r.t only one performance metric or call analysePerfMetric() instead.
            perfStatService.callAScenario(scenarioName, paramList, prpcVersion, perfstat.getBuildlabel(), true);
        }
    }

    /**
     * When there is a degradation/baseline in the last 5 builds and 2 in the last 4 builds doesnt follow the same trend, then
     * it is considered that the last degradation is invalid and it is removed from the database.
     *
     * The below logic is simplified in the method.
     * if(rank == 1 && accuracy >= 50)
     *             return true;
     *         else if(rank == 2 && accuracy >= 60)
     *             return true;
     *         else if (rank == 3 && accuracy >= 70)
     *             return true;
     *         else if (rank == 4 && accuracy >= 80)
     *             return true;
     *         else
     *             return false;
     *
     * @param rank
     * @param accuracy
     * @return
     */
    public boolean isLastVariationInvalid(Integer rank, Double accuracy) {
        if(accuracy >= (40+rank*10))
            return true;
        else
            return false;
    }
}
