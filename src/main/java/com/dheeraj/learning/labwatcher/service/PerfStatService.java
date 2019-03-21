package com.dheeraj.learning.labwatcher.service;

import com.dheeraj.learning.labwatcher.dao.PerfStatDAO;
import com.dheeraj.learning.labwatcher.dto.ParamDataDTO;
import com.dheeraj.learning.labwatcher.dto.PerfStatDTO;
import com.dheeraj.learning.labwatcher.entity.ParamData;
import com.dheeraj.learning.labwatcher.entity.PerfStat;
import com.dheeraj.learning.labwatcher.util.DataUtil;
import com.dheeraj.learning.labwatcher.util.DegradationIdentificationUtil;
import com.dheeraj.learning.labwatcher.util.Mapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.tags.Param;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Service
public class PerfStatService {
    @Autowired
    private PerfStatDAO perfStatDAO;

    public List<PerfStatDTO> getPerfStatsBetweenBuilds(String scenarioName, String prpcVersion, String startBuildLabel, String endBuildLabel, int maxResults) {
        List<PerfStat> list = perfStatDAO.getPerfStatsBetweenBuilds(scenarioName, prpcVersion, startBuildLabel, endBuildLabel, maxResults);
        List<PerfStatDTO> dtoList = Mapper.copyResultsToDTO(list);
        return dtoList;
    }

    public PerfStatDTO getPerfStatForAGivenBuild(String scenarioName, String buildLabel) {
        PerfStat perfStat = perfStatDAO.getPerfStatForAGivenBuild(scenarioName, buildLabel);
        PerfStatDTO perfStatDTO = new PerfStatDTO();
        BeanUtils.copyProperties(perfStat, perfStatDTO);
        return  perfStatDTO;
    }


    public List<PerfStatDTO> getPerfStatsForLastNBuilds(String scenarioName, String prpcVersion,  String endBuildLabel, int maxResults, boolean isHead) {
        List<PerfStat> list = perfStatDAO.getPerfStatsForLastNBuilds(scenarioName, prpcVersion, endBuildLabel, maxResults, isHead);

        List<PerfStatDTO> dtoList = Mapper.copyResultsToDTO(list);
        return dtoList;
    }

    public List<String> getValidBuildLabels(String scenarioName, String prpcVersion, String startDate, String endDate) {
        List<String> list = perfStatDAO.getValidBuildLabels(scenarioName, prpcVersion, startDate, endDate);
        return list;
    }

    /**
     *
     * @param scenarioName
     * @param paramList
     * @param prpcVersion
     * @param currentBuildLabel
     * @return
     */
    public Map<String, ParamDataDTO> analyseData(String scenarioName, List<String> paramList, String prpcVersion, String currentBuildLabel) {
        Map<String, ParamDataDTO> variedBuildRankMap = new HashMap<>();
        for (String param :
                paramList) {
            ParamData paramData = perfStatDAO.getVariedBuildRankDetails(scenarioName, param, currentBuildLabel);
            ParamDataDTO paramDataDTO = new ParamDataDTO();
            if(paramData == null)
                paramDataDTO = null;
            else
                paramDataDTO = Mapper.convert(paramData);
            variedBuildRankMap.put(param, paramDataDTO);
        }

        Map<String, ParamDataDTO> currentBuildParamMap = new HashMap<>();
        for (String param : paramList) {
            currentBuildParamMap.put(param, new ParamDataDTO(param));
        }

        //For loop over params
        for (String param : currentBuildParamMap.keySet()) {
            ParamDataDTO variedBuildParamDataDTO = variedBuildRankMap.get(param);
            if( variedBuildParamDataDTO  == null ||
                    (variedBuildParamDataDTO  != null && variedBuildParamDataDTO.getVariedBuildRank() > 20)) {

                List<PerfStat> perfStats = perfStatDAO.getPerfStatsForLastNBuilds(scenarioName, prpcVersion, currentBuildLabel, 50, true);
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
                    currentBuildParamMap.get(param).setAccuracy(60.0);
                    DataUtil.printVariationMessage(currentBuildParamMap.get(param));
                    //get this param from DB.
                    //Map the DTO to the DB entity and save.
                }
            } else {
                int rank = variedBuildParamDataDTO.getVariedBuildRank();
                if (variedBuildParamDataDTO.isDegraded()) {
                    if (rank < 5) {
                        //Get the builds
                        String degradedBuild = variedBuildRankMap.get(param).getBuildLabel();
                        List<PerfStat> perfStats = perfStatDAO.getPerfStatsBetweenBuilds(scenarioName, prpcVersion, degradedBuild, currentBuildLabel, 10);
                        List<PerfStatDTO> perfStatDTOs = Mapper.copyResultsToDTO(perfStats);
                        Double consolidatedMean = DegradationIdentificationUtil.getMean(perfStatDTOs, param);
                        ParamData paramData = perfStatDAO.getParamDataForAGivenBuild(scenarioName, param, degradedBuild);
                        ParamDataDTO paramDataDTO = Mapper.convert(paramData);
                        boolean isDegraded = DegradationIdentificationUtil.isDegraded(consolidatedMean, paramDataDTO.getMean(), paramDataDTO.getStandardDeviation(), 2);
                        boolean isImproved = DegradationIdentificationUtil.isImproved(consolidatedMean, paramDataDTO.getMean(), paramDataDTO.getStandardDeviation(), 2);
                        Double accuracy = 0.0;
                        if (isDegraded) {
                            //Increase the accuracy of the build 1.
                            paramDataDTO.setAccuracy(paramDataDTO.getAccuracy() + 10.0);
                            accuracy = paramData.getAccuracy() + 10.0;
                        } else if (isImproved) {
                            paramDataDTO.setAccuracy(paramDataDTO.getAccuracy() - 10.0);
                            accuracy = paramData.getAccuracy() - 10.0;
                        }
                        //Can change this save logic later
                        perfStatDAO.findAndUpdate(paramData, accuracy);
//Compare with std of 1.
                    } else if (rank < 20) {
                        List<PerfStat> perfStats = perfStatDAO.getPerfStatsForLastNBuilds(scenarioName, prpcVersion, currentBuildLabel, 50, true);
                        List<PerfStatDTO> perfStatDTOs = Mapper.copyResultsToDTO(perfStats);
                        //Calc mean and std
                        DegradationIdentificationUtil.calcStandardDeviation(perfStatDTOs, param, currentBuildParamMap.get(param));
                        //Get latset bulid values
                        PerfStat currentBuildPerfStat = perfStatDAO.getPerfStatForAGivenBuild(scenarioName, currentBuildLabel);
                        PerfStatDTO currentBuildPerfStatDTO = new PerfStatDTO();
                        BeanUtils.copyProperties(currentBuildPerfStat, currentBuildPerfStatDTO);
                        //Compare
                        boolean isVaried = DegradationIdentificationUtil.isVaried(currentBuildParamMap.get(param), param, currentBuildPerfStatDTO.getDouble(param));
                        if (isVaried) {
                            currentBuildParamMap.get(param).setAccuracy(50.0);
                            DataUtil.printVariationMessage(currentBuildParamMap.get(param));
                            //get this param from DB.
                            //Map the DTO to the DB entity and save.
                        }
                    }
                } else if (variedBuildParamDataDTO.isImproved()) {
                    if (rank < 5) {
                        //Get the builds
                        String degradedBuild = variedBuildRankMap.get(param).getScenarioName();
                        List<PerfStat> perfStats = perfStatDAO.getPerfStatsBetweenBuilds(scenarioName, prpcVersion, degradedBuild, currentBuildLabel, 10);
                        List<PerfStatDTO> perfStatDTOs = Mapper.copyResultsToDTO(perfStats);
                        Double consolidatedMean = DegradationIdentificationUtil.getMean(perfStatDTOs, param);
                        ParamData paramData = perfStatDAO.getParamDataForAGivenBuild(scenarioName, param, degradedBuild);
                        ParamDataDTO paramDataDTO = Mapper.convert(paramData);
                        boolean isDegraded = DegradationIdentificationUtil.isDegraded(consolidatedMean, paramDataDTO.getMean(), paramDataDTO.getStandardDeviation(), 2);
                        boolean isImproved = DegradationIdentificationUtil.isImproved(consolidatedMean, paramDataDTO.getMean(), paramDataDTO.getStandardDeviation(), 2);
                        Double accuracy = 0.0;
                        if (isImproved) {
                            //Increase the accuracy of the build 1.
                            paramDataDTO.setAccuracy(paramDataDTO.getAccuracy() + 10.0);
                            accuracy = paramData.getAccuracy() + 10.0;
                        } else if (isDegraded) {
                            paramDataDTO.setAccuracy(paramDataDTO.getAccuracy() - 10.0);
                            accuracy = paramData.getAccuracy() - 10.0;
                        }
                        perfStatDAO.findAndUpdate(paramData, accuracy);

                        //Compare with std of 1.
                    } else if (rank < 20) {
                        List<PerfStat> perfStats = perfStatDAO.getPerfStatsForLastNBuilds(scenarioName, prpcVersion, currentBuildLabel, 50, true);
                        List<PerfStatDTO> perfStatDTOs = Mapper.copyResultsToDTO(perfStats);
                        //Calc mean and std
                        DegradationIdentificationUtil.calcStandardDeviation(perfStatDTOs, param, currentBuildParamMap.get(param));
                        //Get latset bulid values
                        PerfStat currentBuildPerfStat = perfStatDAO.getPerfStatForAGivenBuild(scenarioName, currentBuildLabel);
                        PerfStatDTO currentBuildPerfStatDTO = new PerfStatDTO();
                        BeanUtils.copyProperties(currentBuildPerfStat, currentBuildPerfStatDTO);
                        //Compare
                        boolean isVaried = DegradationIdentificationUtil.isVaried(currentBuildParamMap.get(param), param, currentBuildPerfStatDTO.getDouble(param));
                        if (isVaried) {
                            currentBuildParamMap.get(param).setAccuracy(50.0);
                            DataUtil.printVariationMessage(currentBuildParamMap.get(param));
                            //get this param from DB.
                            //Map the DTO to the DB entity and save.
                        }
                    }
                } else {
                    System.out.println("None of the parameters is improved. Check the data properly. ");
                }
            }
        }
        return currentBuildParamMap;
    }
}
