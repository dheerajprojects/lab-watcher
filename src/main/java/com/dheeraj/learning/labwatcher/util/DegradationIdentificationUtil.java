package com.dheeraj.learning.labwatcher.util;

import com.dheeraj.learning.labwatcher.dto.ParamDataDTO;
import com.dheeraj.learning.labwatcher.dto.PerfStatDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

public class DegradationIdentificationUtil {

    static Logger logger = LoggerFactory.getLogger(DegradationIdentificationUtil.class);

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

    public static boolean isAnyParamVaried(Map<String, ParamDataDTO> paramDataDTOMap) {
        boolean varied = false;
        for (ParamDataDTO paramDataDTO : paramDataDTOMap.values()) {
            if (paramDataDTO.isImproved() || paramDataDTO.isDegraded()) {
                varied = true;
                break;
            }
        }
        return varied;
    }

    public static boolean isDegraded(Double currentValue, Double mean, Double standardDeviation, Integer sigmaCount, boolean isMinVariationCheck, Double variationPercentage) {
        if (currentValue > (mean + (sigmaCount * standardDeviation))) {
            //Ensure that the variation is atleast X%
            if (isMinVariationCheck && variationPercentage >= 3.0) {
                return true;
            }
        }
        return false;
    }

    public static boolean isImproved(Double currentValue, Double mean, Double standardDeviation, Integer sigmaCount, boolean isMinVariationCheck, Double variationPercentage) {
        if (currentValue < (mean - (sigmaCount * standardDeviation))) {
            //Ensure that the variation is atleast X%
            if (isMinVariationCheck && variationPercentage <= -3.0) {
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

    public static Double calcMean(List<PerfStatDTO> perfStatDTOs, String param, ParamDataDTO paramDataDTO) {
        Double mean;
        Double sum = 0.0;
        Double noOfRecords = 0.0;
        String listOfBuilds = "";
        logger.debug("List of builds considered are : ");
        for (PerfStatDTO perfstatDTO : perfStatDTOs) {
            if (perfstatDTO.getDouble(param) != null) {
                listOfBuilds += perfstatDTO.getBuildinfo() + " : " + perfstatDTO.getDouble(param) + ", ";
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


}
