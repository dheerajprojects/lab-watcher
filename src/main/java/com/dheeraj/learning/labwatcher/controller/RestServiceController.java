package com.dheeraj.learning.labwatcher.controller;

import com.dheeraj.learning.labwatcher.dto.ScenarioDataDTO;
import com.dheeraj.learning.labwatcher.service.SchedulerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestServiceController {

    Logger logger = LoggerFactory.getLogger(RestServiceController.class);

    @Autowired
    private SchedulerService schedulerService;

    @GetMapping("analysescenario/{scenarioName}/{prpcverison}/{buildInfo}/{param}")
    public ScenarioDataDTO analyseAParticularBuildParticularParam(@PathVariable("scenarioName") String scenarioName, @PathVariable("prpcverison") String prpcversion,
                                                                  @PathVariable("buildInfo") String buildInfo, @PathVariable("param") String param) {
        ScenarioDataDTO scenarioDataDTO = schedulerService.analyseAScenarioLatestBuildGivenParam(scenarioName, buildInfo, prpcversion, param);
        return scenarioDataDTO;
    }

    @GetMapping("analysescenario/stringresponse/{scenarioName}/{prpcverison}/{buildInfo}/{param}")
    public String analyseAParticularBuildAndReturnString(@PathVariable("scenarioName") String scenarioName, @PathVariable("prpcverison") String prpcversion,
                                                         @PathVariable("buildInfo") String buildInfo, @PathVariable("param") String param) {
        String jsonString = schedulerService.analyseAParticularBuildReturnString(scenarioName, buildInfo, prpcversion, param);
        return jsonString;
    }

    /**
     * This can be used to test degradation on a single valid build.
     *
     * @param scenarioName The scenario name to be tested.
     * @param prpcversion PRPCVersion of the build label.
     * @param testBuild The scenario ran successfully in this build.
     * @return
     */
    @GetMapping("analysescenario/{scenarioName}/{prpcverison}/{buildInfo}")
    public ScenarioDataDTO analyseAParticularBuild(@PathVariable("scenarioName") String scenarioName, @PathVariable("prpcverison") String prpcversion,
                                                   @PathVariable("buildInfo") String buildInfo){
        ScenarioDataDTO scenarioDataDTO = schedulerService.analyseAScenarioLatestBuild(scenarioName, prpcversion, buildInfo, "true");
        return scenarioDataDTO;
    }

    /**
     * This can be used to analyze all degradations of a scenario in a release.
     *
     * @param scenarioName The scenario name to be tested.
     * @param prpcversion PRPCVersion of the build label.
     * @return
     */
    @GetMapping("analysescenario/{scenarioName}/{prpcverison}/")
    public ScenarioDataDTO analyseAScenarioInARelease(@PathVariable("scenarioName") String scenarioName, @PathVariable("prpcverison") String prpcversion){
        ScenarioDataDTO scenarioDataDTO = schedulerService.analyseAScenarioGivenRelease(scenarioName, prpcversion, "true");
        return scenarioDataDTO;
    }

    /**
     * This can be used to analyze all degradations of a scenario in a release.
     *
     * @param prpcversion PRPCVersion of the build label.
     * @return
     */
    @GetMapping("analyse/{prpcversion}")
    public String analyseARelease(@PathVariable("prpcversion") String prpcversion){
        schedulerService.analyseARelease(prpcversion);
        return "Analysis for entire release is completed";
    }

    @GetMapping("allscenarios/{currentDate}/{noOfPreviousDays}")
    public void analyseAParticularBuild(@PathVariable("currentDate") String currentDate, @PathVariable("noOfPreviousDays") String noOfPreviousDays) {
        logger.info("Running performance metric analysis for all scenarios from last " + noOfPreviousDays + " days till " + currentDate);
        logger.info("Current Date : " + currentDate);
        logger.info("Number of previous days : " + noOfPreviousDays);

        schedulerService.scheduleDailyRuns(null, currentDate, Integer.parseInt(noOfPreviousDays));
    }

    @GetMapping("completedBuild/analyzeResult/{prpcversion}/{buildInfo}")
    public void analyzeCompletedBuildResults(@PathVariable("prpcversion") String prpcversion, @PathVariable("buildInfo") String buildInfo) {
        logger.info("This rest service got triggered from jenkins job");
        logger.info("Running performance metric analysis on build : " + buildInfo + ", prpcversion : " + prpcversion);
        logger.info("Yet to implement the current service...");
    }
}
