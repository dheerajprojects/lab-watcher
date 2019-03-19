package com.dheeraj.learning.labwatcher.util;

import com.dheeraj.learning.labwatcher.dto.ParamDataDTO;
import com.dheeraj.learning.labwatcher.dto.PerfStatDTO;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class DegradationIdentificationUtil {

    public static Map<String, ParamDataDTO> getStandardDeviation(List<PerfStatDTO> resultDTOs, List<String> listOfParams) {
        Map<String, ParamDataDTO> temp = new HashMap();
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

    public static boolean isDegraded(Map<String, ParamDataDTO> tempMap, String param, Double latestValue) {
        double mean = tempMap.get(param).getMean();
        double standardDeviation = tempMap.get(param).getStandardDeviation();
        double degradationPercentage;
        boolean temp = false;
        tempMap.get(param).setLatestBuildValue(latestValue);
        degradationPercentage = (mean == 0 ? 0 : (((latestValue - mean) / mean) * 100));
        if (latestValue > mean + 2 * standardDeviation || latestValue < mean - 2 * standardDeviation) {
            temp = true;
        }
        tempMap.get(param).setDegradationPercentage(degradationPercentage);
        tempMap.get(param).setDegraded(temp);
        return temp;
    }
}
