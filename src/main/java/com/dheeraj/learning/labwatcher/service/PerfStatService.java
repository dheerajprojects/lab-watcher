package com.dheeraj.learning.labwatcher.service;

import com.dheeraj.learning.labwatcher.InvalidInputCustomException;
import com.dheeraj.learning.labwatcher.dao.PerfStatDAO;
import com.dheeraj.learning.labwatcher.dto.ParamDataDTO;
import com.dheeraj.learning.labwatcher.dto.PerfStatDTO;
import com.dheeraj.learning.labwatcher.dto.ScenarioDataDTO;
import com.dheeraj.learning.labwatcher.entity.ParamData;
import com.dheeraj.learning.labwatcher.entity.PerfStat;
import com.dheeraj.learning.labwatcher.entity.ScenarioData;
import com.dheeraj.learning.labwatcher.repository.ScenarioDataRepository;
import com.dheeraj.learning.labwatcher.util.DataUtil;
import com.dheeraj.learning.labwatcher.util.DegradationIdentificationUtil;
import com.dheeraj.learning.labwatcher.util.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This is the main service method to do business logic on performance stats.
 *
 * Methods plan
 * 1.getValidBuildLabelsBetweenGivenDates
 * 2.callAScenario
 */
@Service
public class PerfStatService {

    Logger logger = LoggerFactory.getLogger(PerfStatService.class);

    public static Integer MAX_DATA_SIZE=50;
    public static Integer DECENT_DATA_SIZE=20;
    public static Integer MIN_DATA_SIZE=5;
    public static Double MAX_DATA_ACCURACY=70.0;
    public static Double DECENT_DATA_ACCURACY=60.0;
    public static Double MIN_DATA_ACCURACY=60.0;

    @Autowired
    private PerfStatDAO perfStatDAO;

    @Autowired
    private ScenarioDataRepository scenarioDataRepository;

    /**
     * This method analyzes last n (maxResults #50 for now) perfstats(previous to the #testBuild} and calculates mean and
     * standard deviation for the given parameters.
     * Then it calculates if the #testBuild metrics are degraded/improved based on how far they are from standard deviation.
     *
     * @param scenarioName Scenario to be tested.
     * @param paramList    Parameters to be tested.
     * @param prpcVersion  Prpcversion of the testing builds.
     * @param testBuild    The build for which degradation analysis to be done.
     * @return This an object which contains all the degradation details.
     */
    public ScenarioDataDTO callAScenario(String scenarioName, List<String> paramList, String prpcVersion, String testBuild, boolean isHead) {
        //validateInputData(scenarioName, paramList, prpcVersion, testBuild);

        //TODO : Validate relevant database data. Whether user requested data exist in the database. Fire perfstat db with scenarioname, prpcversion, testbuild
        // and see if we get atleast one row. If not then throw error that the requested build doesn't exist.
        //TODO : Later we might give more information to the user whether scenario is invalid, prpcversion is invalid or build label is invalid.
        //TODO : map/create exact parameters from entity to dto.
        ScenarioDataDTO scenarioDataDTO = new ScenarioDataDTO();
        scenarioDataDTO.setTestname(scenarioName);
        scenarioDataDTO.setLatestbuild(testBuild);

        Map<String, ParamDataDTO> currentBuildParamMap = new HashMap<>();
        for (String perfMetric : paramList) {
            ParamDataDTO paramDataDTO = analysePerfMetric(scenarioName, perfMetric, prpcVersion, testBuild, isHead);
            currentBuildParamMap.put(paramDataDTO.getParamName(), paramDataDTO);
        }

        scenarioDataDTO.setMap(currentBuildParamMap);

        //Saving only if atleast one parameter is degraded/improved;
        //Ensure that this works even if we rerun the analysis for the same build.
        boolean isVaried = DegradationIdentificationUtil.isAnyParamVaried(currentBuildParamMap);
        if(isVaried) {
            ScenarioData scenarioData = perfStatDAO.getScenarioData(scenarioName, testBuild);
            if(scenarioData!= null)
                Mapper.map(scenarioDataDTO, scenarioData, paramList);
            else
                scenarioData = Mapper.convert(scenarioDataDTO);

            scenarioDataRepository.save(scenarioData);
        }

        return scenarioDataDTO;
    }

    /**
     * This method retrieves different baselines for each parameter and based on the position of the baseline build, it performs different tasks to identify the given build degradation.
     *
     * @param scenarioName      Performance scenario to be tested.
     * @param performanceMetricName         List of performance metrics to be analyzed for degradation or improvement.
     * @param prpcVersion       PrpcVersion for the #currentBuildLabel.
     * @param currentBuildLabel Build to be tested.
     * @return A map of given performance metrics with analysis results.
     */
    public ParamDataDTO analysePerfMetric(String scenarioName, String performanceMetricName, String prpcVersion, String currentBuildLabel, boolean isHead) {
        logger.debug("Processing parameter : " + performanceMetricName);

        ParamDataDTO baselineBuildParamDataDTO = getBaselineBuild(scenarioName, performanceMetricName, currentBuildLabel, prpcVersion, isHead);
        ParamDataDTO currentBuildParamDataDTO = new ParamDataDTO(performanceMetricName, scenarioName, currentBuildLabel);

        //The second condition occurs below only when the first condition is false
        if (baselineBuildParamDataDTO == null) {
            analyseWhenResultsAreStableForNBuilds(scenarioName, prpcVersion, currentBuildLabel, currentBuildParamDataDTO, performanceMetricName, MAX_DATA_SIZE, MAX_DATA_ACCURACY);
        } else if (baselineBuildParamDataDTO.getBaselineBuildPosition() > DECENT_DATA_SIZE) {
            analyseWhenResultsAreStableForNBuilds(scenarioName, prpcVersion, currentBuildLabel, currentBuildParamDataDTO, performanceMetricName, baselineBuildParamDataDTO.getBaselineBuildPosition(), DECENT_DATA_ACCURACY);
        } else if (baselineBuildParamDataDTO.getBaselineBuildPosition() >= MIN_DATA_SIZE && baselineBuildParamDataDTO.getBaselineBuildPosition() <= DECENT_DATA_SIZE) {
            analyseWhenResultsAreStableForNBuilds(scenarioName, prpcVersion, currentBuildLabel, currentBuildParamDataDTO, performanceMetricName, baselineBuildParamDataDTO.getBaselineBuildPosition(), MIN_DATA_ACCURACY);
        } else {
            int rank = baselineBuildParamDataDTO.getBaselineBuildPosition();
            Double accuracy = 0.0;
            if (baselineBuildParamDataDTO.isDegraded()) {
                accuracy = analyseWhenRecentBuildsHaveVariation(scenarioName, prpcVersion, currentBuildLabel, baselineBuildParamDataDTO.getBuildLabel(), performanceMetricName, rank, true);
            } else if (baselineBuildParamDataDTO.isImproved()) {
                accuracy = analyseWhenRecentBuildsHaveVariation(scenarioName, prpcVersion, currentBuildLabel, baselineBuildParamDataDTO.getBuildLabel(), performanceMetricName, rank, false);
            } else {
                logger.debug("Though this param is neither improved nor degraded somehow this data got into database incorrectly.");
            }

            decideAndSendEmail(baselineBuildParamDataDTO, accuracy);

        }
        return currentBuildParamDataDTO;
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

    private Double analyseWhenRecentBuildsHaveVariation(String scenarioName, String prpcVersion, String currentBuildLabel, String baselineBuild, String performanceMetricName, Integer rank, boolean isForDegradationCheck) {
        Double currentParamValue;
        //This logic just compares the current value with last degraded value.
        PerfStat currentBuildPerfStat = perfStatDAO.getPerfStatForAGivenBuild(scenarioName, currentBuildLabel);
        PerfStatDTO perfStatDTO = new PerfStatDTO();
        BeanUtils.copyProperties(currentBuildPerfStat, perfStatDTO);

        currentParamValue = perfStatDTO.getDouble(performanceMetricName);


        ParamData paramData = perfStatDAO.getParamDataForAGivenBuild(scenarioName, performanceMetricName, baselineBuild);
        ParamDataDTO paramDataDTO = Mapper.convert(paramData);

        double variationPercentage = (paramDataDTO.getMean() == 0 ? 0 : (((currentParamValue - paramDataDTO.getMean()) / paramDataDTO.getMean()) * 100));
        boolean isDegraded = DegradationIdentificationUtil.isDegraded(currentParamValue, paramDataDTO.getMean(), paramDataDTO.getStandardDeviation(), 2, true, variationPercentage);
        boolean isImproved = DegradationIdentificationUtil.isImproved(currentParamValue, paramDataDTO.getMean(), paramDataDTO.getStandardDeviation(), 2, true, variationPercentage);
        logger.debug("ScenarioName : " + scenarioName + ", currentBuildLabel : " + currentBuildLabel + " Variation status : isDegraded : " + isDegraded + ", isImproved : " + isImproved);

        Double accuracy = 0.0;
        if ((isForDegradationCheck && isDegraded) || (!isForDegradationCheck && isImproved)) {
            paramDataDTO.setAccuracy(paramDataDTO.getAccuracy() + 10.0);
            accuracy = paramData.getAccuracy() + 10.0;
            logger.debug("Accuracy increased for scenario : " + paramDataDTO.getScenarioName() + ", build : " + paramDataDTO.getBuildLabel() + ", param : " + paramDataDTO.getParamName());
        } else {
            paramDataDTO.setAccuracy(paramDataDTO.getAccuracy() - 10.0);
            accuracy = paramData.getAccuracy() - 10.0;
            logger.debug("Accuracy decreased for scenario : " + paramDataDTO.getScenarioName() + ", build : " + paramDataDTO.getBuildLabel() + ", param : " + paramDataDTO.getParamName());
        }

        //Send Email
        //Send the last degraded content as email //Also put accuracy in the email.

        //Check if the last degradation/baseline is an outlier. If it is then remove/update it from database that it is not degraded.
        boolean isLastVariationInvalid = isLastVariationInvalid(rank, accuracy);
        if (!isLastVariationInvalid) {
            perfStatDAO.findAndRemoveVariation(paramData);
            //Rerun later builds after this degradation. //TODO : Check if this is messing up the accuracy.
            //TODO: Dont have to rerun for all the performance metrics. We can just rerun the specific performance metric.
            rerunLaterBuildsAfterDegradation(scenarioName, prpcVersion, rank, currentBuildLabel, paramData);
        } else {
            //Can change this save logic later
            //Better to update the accuracy based on the number of degraded builds in between than directly increasing the accuracy by 10 every time.
            //Say if there are 2 degraded builds in between, we can mark the accuracy as 90 etc.
            perfStatDAO.findAndUpdate(paramData, accuracy);
        }

        return accuracy;
    }

    /**
     * TODO Make use of rank attribute from the caller to cross check the data.
     * @param scenarioName
     * @param prpcVersion
     * @param rank
     * @param endBuildLabel
     */
    public void rerunLaterBuildsAfterDegradation(String scenarioName, String prpcVersion, Integer rank, String endBuildLabel, ParamData paramData) {
        List<PerfStat> perfStats = perfStatDAO.getPerfStatsForLastNBuilds(scenarioName, prpcVersion, endBuildLabel, rank - 1, true);
        List<String> paramList = new ArrayList<>();
        paramList.add(paramData.getParamName());
        for (PerfStat perfstat : perfStats) {
            callAScenario(scenarioName, paramList, prpcVersion, perfstat.getBuildlabel(), true);
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

    /**
     * When there are no major deviations in the last #DECENT_DATA_SIZE number of builds, this method is executed to
     * identify if the current build as degraded/improved.
     *
     * @param scenarioName
     * @param prpcVersion
     * @param currentBuildLabel
     * @param currentBuildParamDataDTO
     * @param param
     */
    private void analyseWhenResultsAreStableForNBuilds(String scenarioName, String prpcVersion, String currentBuildLabel,
                                                       ParamDataDTO currentBuildParamDataDTO, String param, Integer rank,
                                                       Double accuracy) {
        //TODO: Yet to write logic to identify if the build is HEAD or not.
        List<PerfStat> perfStats = perfStatDAO.getPerfStatsForLastNBuilds(scenarioName, prpcVersion, currentBuildLabel, rank, true);

        List<PerfStatDTO> perfStatDTOs = Mapper.copyResultsToDTO(perfStats);

        //Calc mean and std
        DegradationIdentificationUtil.calcStandardDeviation(perfStatDTOs, param, currentBuildParamDataDTO);

        //Get latset bulid values
        PerfStat currentBuildPerfStat = perfStatDAO.getPerfStatForAGivenBuild(scenarioName, currentBuildLabel);
        PerfStatDTO currentBuildPerfStatDTO = new PerfStatDTO();
        BeanUtils.copyProperties(currentBuildPerfStat, currentBuildPerfStatDTO);

        //Compare
        boolean isVaried = DegradationIdentificationUtil.isVaried(currentBuildParamDataDTO, param, currentBuildPerfStatDTO.getDouble(param));
        if (isVaried) {
            currentBuildParamDataDTO.setAccuracy(accuracy);
            DataUtil.printVariationMessage(currentBuildParamDataDTO);
        }
    }

    /**
     * This method retrieves the builds(#with ocmplete parameter data) which were degraded/improved earlier for the given scenario.
     * In other words this method returns latest baseline build for each parameter. How far is the
     *
     * @param scenarioName
     * @param performanceMetricName
     * @param currentBuildLabel
     * @return
     */
    private ParamDataDTO getBaselineBuild(String scenarioName, String performanceMetricName, String currentBuildLabel, String prpcVersion, boolean isHead) {
        ParamData paramData = perfStatDAO.getBaselineBuild(scenarioName, performanceMetricName, currentBuildLabel, prpcVersion, isHead);
        ParamDataDTO paramDataDTO = null;
        if (paramData != null)
            paramDataDTO = Mapper.convert(paramData);

        return paramDataDTO;
    }

    public List<String> getValidBuildLabelsBetweenGivenDates(String scenarioName, String prpcVersion, String startDate, String endDate) {
        List<String> list = perfStatDAO.getValidBuildLabelsBetweenGivenDates(scenarioName, prpcVersion, startDate, endDate);
        return list;
    }
}

