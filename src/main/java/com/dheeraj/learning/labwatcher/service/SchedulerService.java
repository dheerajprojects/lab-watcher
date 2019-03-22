package com.dheeraj.learning.labwatcher.service;

import com.dheeraj.learning.labwatcher.dto.ScenarioDataDTO;
import com.dheeraj.learning.labwatcher.util.DataUtil;
import com.dheeraj.learning.labwatcher.util.FormatUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * This just mimics invocation of PerfStatService from a cron job.
 *
 * Methods plan
 * 1.analyseAScenarioLatestBuild
 * 2.analyseAScenarioMultipleBuilds
 *
 *
 * Later
 * 1.analyseMultipleScenarios
 */
@Service
public class SchedulerService {

    Logger logger = LoggerFactory.getLogger(SchedulerService.class);

    @Autowired
    private PerfStatService perfStatService;

    /**
     * This method is analyses the latest build of a scenario with the last n builds
     * and identifies if the latest build is degraded or not.
     */
    public void analyseAScenarioLatestBuild() {
        String scenarioName = "CCCASE";
        List<String> paramList = DataUtil.populateGivenParamsList("totalreqtime");
        String prpcVersion = "8.2.0";
        String currentBuildLabel = "PRPC-HEAD-6577";

        perfStatService.callAScenario(scenarioName, paramList, prpcVersion, currentBuildLabel, true);
    }

    /**
     * This method analyses degradations occurred in a scenario between the given dates.
     *
     * Sample builds
     * List<String> validBuildLabels = DataUtil.buildArrayList("PRPC-HEAD-6570,PRPC-HEAD-6573,PRPC-HEAD-6575,PRPC-HEAD-6577,PRPC-HEAD-6578,PRPC-HEAD-6580,PRPC-HEAD-6583,PRPC-HEAD-6585," +
     *                 "PRPC-HEAD-6586,PRPC-HEAD-6587,PRPC-HEAD-6588,PRPC-HEAD-6590,PRPC-HEAD-6591,PRPC-HEAD-6592,PRPC-HEAD-6593,PRPC-HEAD-6594,PRPC-HEAD-6595,PRPC-HEAD-6596,PRPC-HEAD-6598," +
     *                 "PRPC-HEAD-6601,PRPC-HEAD-6602,PRPC-HEAD-6604,PRPC-HEAD-6605,PRPC-HEAD-6609,PRPC-HEAD-6610,PRPC-HEAD-6612,PRPC-HEAD-6613,PRPC-HEAD-6615,PRPC-HEAD-6616,PRPC-HEAD-6619," +
     *                 "PRPC-HEAD-6621,PRPC-HEAD-6622,PRPC-HEAD-6626,PRPC-HEAD-6630,PRPC-HEAD-6631,PRPC-HEAD-6633,PRPC-HEAD-6649,PRPC-HEAD-6651,PRPC-HEAD-6653,PRPC-HEAD-6654,PRPC-HEAD-6655," +
     *                 "PRPC-HEAD-6656,PRPC-HEAD-6658,PRPC-HEAD-6659,PRPC-HEAD-6661,PRPC-HEAD-6662,PRPC-HEAD-6663,PRPC-HEAD-6666,PRPC-HEAD-6667,PRPC-HEAD-6669,PRPC-HEAD-6672,PRPC-HEAD-6673," +
     *                 "PRPC-HEAD-6675,PRPC-HEAD-6676,PRPC-HEAD-6677,PRPC-HEAD-6679,PRPC-HEAD-6681,PRPC-HEAD-6683,PRPC-HEAD-6684,PRPC-HEAD-6685,PRPC-HEAD-6688,PRPC-HEAD-6689,PRPC-HEAD-6690," +
     *                 "PRPC-HEAD-6691,PRPC-HEAD-6692,PRPC-HEAD-6693,PRPC-HEAD-6696,PRPC-HEAD-6697,PRPC-HEAD-6701");
     */
    public void analyseAScenarioMultipleBuilds() {
        String scenarioName = "CCCASE";
        List<String> paramList = DataUtil.populateGivenParamsList("totalreqtime","rdbiocount");
        String prpcVersion = "8.2.0";
        String startDate = "2018-08-25";
        String endDate = "2018-12-11";
        List<String> validBuildLabels = perfStatService.getValidBuildLabelsBetweenGivenDates(scenarioName, prpcVersion, startDate, endDate);


        for (String buildLabel : validBuildLabels) {
            ScenarioDataDTO scenarioDataDTO = perfStatService.callAScenario(scenarioName, paramList, prpcVersion, buildLabel, true);
            logger.debug("BuildLabel : " + buildLabel + ", isDegraded : " + scenarioDataDTO.getMap().get("totalreqtime").isDegraded()+", isImproved : "+scenarioDataDTO.getMap().get("totalreqtime").isImproved());
        }
    }

    /**
     * This method analyses degradations occurred in a scenario between the given dates.
     */
    public void analyseAScenarioMultipleBuildsHardCoded() {
        String scenarioName = "CCCASE";
        List<String> paramList = DataUtil.populateGivenParamsList("totalreqtime");
        String prpcVersion = "8.2.0";
        List<String> validBuildLabels = DataUtil.buildArrayList("PRPC-HEAD-6575,PRPC-HEAD-6577,PRPC-HEAD-6578,PRPC-HEAD-6580,PRPC-HEAD-6583,PRPC-HEAD-6585,PRPC-HEAD-6586,PRPC-HEAD-6587");

        for (String buildLabel : validBuildLabels) {
            ScenarioDataDTO scenarioDataDTO = perfStatService.callAScenario(scenarioName, paramList, prpcVersion, buildLabel, true);
            logger.debug("BuildLabel : " + buildLabel + ", isDegraded : " + scenarioDataDTO.getMap().get("totalreqtime").isDegraded());
        }
    }

    /**
     * This method is meant for invoking as a rest service from browser.
     * @param scenarioName
     * @param currentBuildLabel
     * @param prpcVersion
     * @param param
     * @return
     */
    public ScenarioDataDTO analyseAScenarioLatestBuild(String scenarioName, String currentBuildLabel, String prpcVersion, String param) {
        List<String> paramList = DataUtil.populateGivenParamsList(param);

        ScenarioDataDTO scenarioDataDTO = perfStatService.callAScenario(scenarioName, paramList, prpcVersion, currentBuildLabel, true);
        logger.debug(scenarioDataDTO.toString());

        return scenarioDataDTO;
    }

    /**
     * This is meant for printing the analysis logic in json format.
     * @param scenarioName
     * @param testBuildLabel
     * @param prpcVersion
     * @param param
     * @return
     */
    public String analyseAParticularBuildReturnString(String scenarioName, String testBuildLabel, String prpcVersion, String param) {
        ScenarioDataDTO scenarioDataDTO = analyseAScenarioLatestBuild(scenarioName, testBuildLabel, prpcVersion, param);

        String jsonString = FormatUtil.convertToJSON(scenarioDataDTO);
        logger.debug(jsonString);

        return jsonString;
    }




}
