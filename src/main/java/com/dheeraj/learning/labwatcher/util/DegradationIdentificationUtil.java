package com.dheeraj.learning.labwatcher.util;

import com.dheeraj.learning.labwatcher.dto.ParamDataDTO;
import com.dheeraj.learning.labwatcher.dto.PerfStatDTO;
import com.dheeraj.learning.labwatcher.entity.PerfStat;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class DegradationIdentificationUtil {

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
        double degradationPercentage;
        boolean isDegraded;
        boolean isImproved;

        tempMap.get(param).setParamValue(latestValue);
        degradationPercentage = (mean == 0 ? 0 : (((latestValue - mean) / mean) * 100));
        isDegraded = isDegraded(latestValue, mean, standardDeviation, 2);
        isImproved = isImproved(latestValue, mean, standardDeviation, 2);

        if (latestValue > mean + 2 * standardDeviation) {
            isDegraded = true;
        } else if (latestValue < mean - 2 * standardDeviation) {
            isImproved = true;
        }
        tempMap.get(param).setVariedBy(degradationPercentage);
        tempMap.get(param).setDegraded(isDegraded);
        tempMap.get(param).setImproved(isImproved);
        return isDegraded || isImproved;
    }

    public static boolean isVaried(ParamDataDTO paramDataDTO, String param, Double latestValue) {
        double mean = paramDataDTO.getMean();
        double standardDeviation = paramDataDTO.getStandardDeviation();

        double variationPercentage = (mean == 0 ? 0 : (((latestValue - mean) / mean) * 100));
        boolean isDegraded = isDegraded(latestValue, mean, standardDeviation, 2);
        boolean isImproved = isImproved(latestValue, mean, standardDeviation, 2);

        paramDataDTO.setParamValue(latestValue);
        paramDataDTO.setVariedBy(variationPercentage);
        paramDataDTO.setDegraded(isDegraded);
        paramDataDTO.setImproved(isImproved);

        return isDegraded || isImproved;
    }

    public static boolean isDegraded(Double currentValue, Double mean, Double standardDeviation, Integer sigmaCount) {
        if (currentValue > (mean + (sigmaCount * standardDeviation)))
            return true;
        return false;
    }

    public static boolean isImproved(Double currentValue, Double mean, Double standardDeviation, Integer sigmaCount) {
        if (currentValue < (mean - (sigmaCount * standardDeviation)))
            return true;
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
        for (PerfStatDTO perfstatDTO : perfStatDTOs) {
            if (perfstatDTO.getDouble(param) != null) {
                sum += perfstatDTO.getDouble(param);
                noOfRecords++;
            }
        }
        mean = sum / noOfRecords;
        paramDataDTO.setMean(mean);
        paramDataDTO.setNoOfValidRecords(noOfRecords);
        return mean;
    }

    public static Double calcStandardDeviation(List<PerfStatDTO> perfStatDTOs, String param, ParamDataDTO paramDataDTO) {
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
}
