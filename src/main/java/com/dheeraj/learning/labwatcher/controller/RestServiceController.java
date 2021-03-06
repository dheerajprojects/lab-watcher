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

    @GetMapping("analysescenario/{scenarioName}/{prpcverison}/{testBuild}/{param}")
    public ScenarioDataDTO analyseAParticularBuildParticularParam(@PathVariable("scenarioName") String scenarioName, @PathVariable("prpcverison") String prpcversion,
                                                   @PathVariable("testBuild") String testBuild, @PathVariable("param") String param){
        ScenarioDataDTO scenarioDataDTO = schedulerService.analyseAScenarioLatestBuild(scenarioName, testBuild, prpcversion, param);
        return scenarioDataDTO;
    }

    @GetMapping("analysescenario/stringresponse/{scenarioName}/{prpcverison}/{testBuild}/{param}")
    public String analyseAParticularBuildAndReturnString(@PathVariable("scenarioName") String scenarioName, @PathVariable("prpcverison") String prpcversion,
                                                @PathVariable("testBuild") String testBuild, @PathVariable("param") String param){
        String jsonString = schedulerService.analyseAParticularBuildReturnString(scenarioName, testBuild, prpcversion, param);
        return jsonString;
    }

    @GetMapping("analysescenario/{scenarioName}/{prpcverison}/{testBuild}")
    public ScenarioDataDTO analyseAParticularBuild(@PathVariable("scenarioName") String scenarioName, @PathVariable("prpcverison") String prpcversion,
                                                   @PathVariable("testBuild") String testBuild){
        ScenarioDataDTO scenarioDataDTO = schedulerService.analyseAScenarioLatestBuild(scenarioName, prpcversion, testBuild);
        return scenarioDataDTO;
    }

    @GetMapping("allscenarios/{currentDate}/{noOfPreviousDays}")
    public void analyseAParticularBuild(@PathVariable("currentDate") String currentDate, @PathVariable("noOfPreviousDays") String noOfPreviousDays){
        logger.info("Running performance metric analysis for all scenarios from last "+noOfPreviousDays+" days till "+currentDate);
        logger.info("Current Date : "+currentDate);
        logger.info("Number of previous days : "+noOfPreviousDays);

        schedulerService.scheduleDailyRuns(null,currentDate ,Integer.parseInt(noOfPreviousDays));
    }

    @GetMapping("completedBuild/analyzeResult/{prpcversion}/{buildlabel}")
    public void analyzeCompletedBuildResults(@PathVariable("prpcversion") String prpcversion, @PathVariable("buildlabel") String buildlabel){
        logger.info("This rest service got triggered from jenkins job");
        logger.info("Running performance metric analysis on build : "+buildlabel+", prpcversion : "+prpcversion);
        logger.info("Yet to implement the current service...");
    }
}
