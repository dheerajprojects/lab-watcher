package com.dheeraj.learning.labwatcher.util;

import com.dheeraj.learning.labwatcher.entity.ParamData;
import com.dheeraj.learning.labwatcher.entity.ScenarioData;

public class DataUtil {

    /**
     * This creates an object of ScenarioData with two set of param details.
     * @return
     */
    public static ScenarioData getScenarioData() {
        ParamData paramData1 = new ParamData("totalreqtime");
        paramData1.setDegraded(true);
        paramData1.setDegradationPercentage(10.0);
        paramData1.setStandardDeviation(4.0);

        ParamData paramData2 = new ParamData("rdbiocount");
        paramData2.setDegraded(true);
        paramData2.setDegradationPercentage(9.0);
        paramData2.setStandardDeviation(7.0);

        ScenarioData scenarioData = new ScenarioData();
        scenarioData.setLatestbuild("PRPC-HEAD-5577");
        scenarioData.setTestname("CCCASE");
        scenarioData.addParam(paramData1);
        scenarioData.addParam(paramData2);

        return scenarioData;
    }
}
