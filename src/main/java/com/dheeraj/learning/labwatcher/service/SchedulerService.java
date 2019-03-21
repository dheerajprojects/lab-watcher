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
        String currentBuildLabel = "PRPC-HEAD-6657";

        perfStatService.callAScenario(scenarioName, paramList, prpcVersion, currentBuildLabel);
    }

    /**
     * This method analyses degradations occurred in a scenario between the given dates.
     */
    public void analyseAScenarioMultipleBuilds() {
        String scenarioName = "CCCASE";
        List<String> paramList = DataUtil.populateGivenParamsList("totalreqtime");
        String prpcVersion = "8.2.0";
        String startDate = "2018-11-14";
        String endDate = "2018-11-23";
        List<String> validBuildLabels = perfStatService.getValidBuildLabelsBetweenGivenDates(scenarioName, prpcVersion, startDate, endDate);

        for (String buildLabel : validBuildLabels) {
            ScenarioDataDTO scenarioDataDTO = perfStatService.callAScenario(scenarioName, paramList, prpcVersion, buildLabel);
            logger.debug("BuildLabel : " + buildLabel + ", isDegraded : " + scenarioDataDTO.getMap().get("totalreqtime").isDegraded());
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
            ScenarioDataDTO scenarioDataDTO = perfStatService.callAScenario(scenarioName, paramList, prpcVersion, buildLabel);
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

        ScenarioDataDTO scenarioDataDTO = perfStatService.callAScenario(scenarioName, paramList, prpcVersion, currentBuildLabel);
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
