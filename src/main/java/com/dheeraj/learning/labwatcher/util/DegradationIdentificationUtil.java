package com.dheeraj.learning.labwatcher.util;

import com.dheeraj.learning.labwatcher.dto.ParamDataDTO;
import com.dheeraj.learning.labwatcher.dto.PerfStatDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class DegradationIdentificationUtil {

    static Logger logger = LoggerFactory.getLogger(DegradationIdentificationUtil.class);

    /**
     * Here mapping is done for all parameters simultaneously
     *
     * @param resultDTOs
     * @param listOfParams
     * @return
     */
    public static Map<String, ParamDataDTO> getStandardDeviation(List<PerfStatDTO> resultDTOs, List<String> listOfParams) {
        Map<String, ParamDataDTO> temp = new HashMap<>();
        for (String param : listOfParams) {
            temp.put(param, new ParamDataDTO(param));
        }

        for (Iterator iterator = resultDTOs.iterator(); iterator.hasNext(); ) {
            PerfStatDTO stats = (PerfStatDTO) iterator.next();
            for (String param : temp.keySet()) {

                if (stats.getDouble(param) != null) {
                    double sum = temp.get(param).getTempsum();
                    double noOfRecords = temp.get(param).getNoOfValidRecords();
                    sum += stats.getDouble(param);
                    noOfRecords++;
                    temp.get(param).setTempsum(sum);
                    temp.get(param).setNoOfValidRecords(noOfRecords);
                }
            }
        }

        for (String param : temp.keySet()) {
            temp.get(param).calcMean();
        }

        for (Iterator iterator = resultDTOs.iterator(); iterator.hasNext(); ) {
            PerfStatDTO stats = (PerfStatDTO) iterator.next();

            for (String param : temp.keySet()) {
                if (stats.getDouble(param) != null) {
                    double sdTemp = temp.get(param).getTempSD();
                    sdTemp += Math.pow(stats.getDouble(param) - temp.get(param).getMean(), 2);
                    temp.get(param).setTempSD(sdTemp);
                }
            }
        }

        for (String param : temp.keySet()) {
            temp.get(param).calcStandardDeviation();
        }

        return temp;
    }

    /**
     * Here mapping will be done for each parameter separately.
     *
     * @param resultDTOs
     * @param listOfParams
     * @return
     */
    public static Map<String, ParamDataDTO> getStandardDeviation2(List<PerfStatDTO> resultDTOs, List<String> listOfParams, Map<String, Integer> paramDegradationRankMap) {
        Map<String, ParamDataDTO> paramDetailsMap = new HashMap<>();
        for (String param : listOfParams) {
            paramDetailsMap.put(param, new ParamDataDTO(param));
        }

        for (String param : paramDetailsMap.keySet()) {
            int rank = paramDegradationRankMap.get(param);
            if (rank < 5) {
                //Average out the current build and previous build and
            } else if (rank >= 5 && rank < 50) {

            } else {

            }
        }

        for (Iterator iterator = resultDTOs.iterator(); iterator.hasNext(); ) {
            PerfStatDTO stats = (PerfStatDTO) iterator.next();
            for (String param : paramDetailsMap.keySet()) {

                if (stats.getDouble(param) != null) {
                    double sum = paramDetailsMap.get(param).getTempsum();
                    double noOfRecords = paramDetailsMap.get(param).getNoOfValidRecords();
                    sum += stats.getDouble(param);
                    noOfRecords++;
                    paramDetailsMap.get(param).setTempsum(sum);
                    paramDetailsMap.get(param).setNoOfValidRecords(noOfRecords);
                }
            }
        }

        for (String param : paramDetailsMap.keySet()) {
            paramDetailsMap.get(param).calcMean();
        }

        for (Iterator iterator = resultDTOs.iterator(); iterator.hasNext(); ) {
            PerfStatDTO stats = (PerfStatDTO) iterator.next();

            for (String param : paramDetailsMap.keySet()) {
                if (stats.getDouble(param) != null) {
                    double sdTemp = paramDetailsMap.get(param).getTempSD();
                    sdTemp += Math.pow(stats.getDouble(param) - paramDetailsMap.get(param).getMean(), 2);
                    paramDetailsMap.get(param).setTempSD(sdTemp);
                }
            }
        }

        for (String param : paramDetailsMap.keySet()) {
            paramDetailsMap.get(param).calcStandardDeviation();
        }

        return paramDetailsMap;
    }


    public static boolean isVariedOld(Map<String, ParamDataDTO> tempMap, String param, Double latestValue) {
        double mean = tempMap.get(param).getMean();
        double standardDeviation = tempMap.get(param).getStandardDeviation();
        double variationPercentage;
        boolean isDegraded;
        boolean isImproved;

        tempMap.get(param).setParamValue(latestValue);
        variationPercentage = (mean == 0 ? 0 : (((latestValue - mean) / mean) * 100));
        isDegraded = isDegraded(latestValue, mean, standardDeviation, 2, true, variationPercentage);
        isImproved = isImproved(latestValue, mean, standardDeviation, 2, true, variationPercentage);

        if (latestValue > mean + 2 * standardDeviation) {
            isDegraded = true;
        } else if (latestValue < mean - 2 * standardDeviation) {
            isImproved = true;
        }
        tempMap.get(param).setVariedBy(variationPercentage);
        tempMap.get(param).setDegraded(isDegraded);
        tempMap.get(param).setImproved(isImproved);
        return isDegraded || isImproved;
    }

    public static boolean isVaried(ParamDataDTO paramDataDTO, String param, Double latestValue) {
        double mean = paramDataDTO.getMean();
        double standardDeviation = paramDataDTO.getStandardDeviation();

        double variationPercentage = (mean == 0 ? 0 : (((latestValue - mean) / mean) * 100));
        boolean isDegraded = isDegraded(latestValue, mean, standardDeviation, 2, true, variationPercentage);
        boolean isImproved = isImproved(latestValue, mean, standardDeviation, 2, true, variationPercentage);

        paramDataDTO.setParamValue(latestValue);
        paramDataDTO.setVariedBy(variationPercentage);
        paramDataDTO.setDegraded(isDegraded);
        paramDataDTO.setImproved(isImproved);

        return isDegraded || isImproved;
    }

    public static boolean isDegraded(Double currentValue, Double mean, Double standardDeviation, Integer sigmaCount, boolean isMinVariationCheck, Double variationPercentage) {
        if (currentValue > (mean + (sigmaCount * standardDeviation))) {
            //Ensure that the variation is atleast X%
            if(isMinVariationCheck && variationPercentage >= 3.0) {
                return true;
            }
        }
        return false;
    }

    public static boolean isImproved(Double currentValue, Double mean, Double standardDeviation, Integer sigmaCount, boolean isMinVariationCheck, Double variationPercentage) {
        if (currentValue < (mean - (sigmaCount * standardDeviation))) {
            //Ensure that the variation is atleast X%
            if(isMinVariationCheck && variationPercentage <= -3.0) {
                return true;
            }
        }
        return false;
    }

    public static Double getMean(List<PerfStatDTO> perfStatDTOs, String param) {
        Double mean;
        Double sum = 0.0;
        Integer noOfRecords = 0;
        for (PerfStatDTO perfstatDTO : perfStatDTOs) {
            if (perfstatDTO.getDouble(param) != null) {
                sum += perfstatDTO.getDouble(param);
                noOfRecords++;
            }
        }
        mean = sum / noOfRecords;

        return mean;
    }

    public static Double getStandardDeviation(List<PerfStatDTO> perfStatDTOs, String param) {
        Double mean = getMean(perfStatDTOs, param);
        Double sdTemp = 0.0;
        Double standardDeviation;
        Integer noOfRecords = 0;
        for (PerfStatDTO perfStatDTO : perfStatDTOs) {
            if (perfStatDTO.getDouble(param) != null) {
                sdTemp += Math.pow(perfStatDTO.getDouble(param) - mean, 2);
                noOfRecords++;
            }
        }

        standardDeviation = Math.sqrt(sdTemp / noOfRecords);

        return standardDeviation;
    }

    public static Double calcMean(List<PerfStatDTO> perfStatDTOs, String param, ParamDataDTO paramDataDTO) {
        Double mean;
        Double sum = 0.0;
        Double noOfRecords = 0.0;
        String listOfBuilds="";
        logger.debug("List of builds considered are : ");
        for (PerfStatDTO perfstatDTO : perfStatDTOs) {
            if (perfstatDTO.getDouble(param) != null) {
                listOfBuilds += perfstatDTO.getBuildlabel()+" : "+perfstatDTO.getDouble(param)+", ";
                sum += perfstatDTO.getDouble(param);
                noOfRecords++;
            }
        }
        logger.debug(listOfBuilds);
        mean = sum / noOfRecords;
        paramDataDTO.setMean(mean);
        paramDataDTO.setNoOfValidRecords(noOfRecords);
        return mean;
    }

    public static Double calcStandardDeviation(List<PerfStatDTO> perfStatDTOs, String param, ParamDataDTO paramDataDTO) {
        //Map list of previous build values to check/confirm manually in a chart

        Double mean = calcMean(perfStatDTOs, param, paramDataDTO);
        Double sdTemp = 0.0;
        Double standardDeviation;

        for (PerfStatDTO perfStatDTO : perfStatDTOs) {
            if (perfStatDTO.getDouble(param) != null) {
                sdTemp += Math.pow(perfStatDTO.getDouble(param) - mean, 2);
            }
        }

        standardDeviation = Math.sqrt(sdTemp / paramDataDTO.getNoOfValidRecords());
        paramDataDTO.setStandardDeviation(standardDeviation);

        return standardDeviation;
    }

    public static boolean isAnyParamVaried(Map<String, ParamDataDTO> paramDataDTOMap) {
        boolean varied=false;
        for (ParamDataDTO paramDataDTO : paramDataDTOMap.values()) {
            if (paramDataDTO.isImproved() || paramDataDTO.isDegraded()) {
                varied = true;
                break;
            }
        }
        return varied;
    }
}
