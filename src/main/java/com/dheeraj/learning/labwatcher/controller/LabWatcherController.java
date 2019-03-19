package com.dheeraj.learning.labwatcher.controller;

import com.dheeraj.learning.labwatcher.dto.ScenarioDataDTO;
import com.dheeraj.learning.labwatcher.service.SchedulerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class LabWatcherController {

    @Autowired
    private SchedulerService schedulerService;

    @GetMapping("analysescenario/{scenarioName}/{prpcverison}/{testBuild}/{param}")
    public ScenarioDataDTO analyseAParticularBuild(@PathVariable("scenarioName") String scenarioName, @PathVariable("prpcverison") String prpcversion,
                                                   @PathVariable("testBuild") String testBuild, @PathVariable("param") String param){
        ScenarioDataDTO scenarioDataDTO = schedulerService.analyseAParticularBuild(scenarioName, testBuild, prpcversion, param);
        return scenarioDataDTO;
    }

    @GetMapping("analysescenario/stringresponse/{scenarioName}/{prpcverison}/{testBuild}/{param}")
    public String analyseAParticularBuildAndReturnString(@PathVariable("scenarioName") String scenarioName, @PathVariable("prpcverison") String prpcversion,
                                                @PathVariable("testBuild") String testBuild, @PathVariable("param") String param){
        String jsonString = schedulerService.analyseAParticularBuildReturnString(scenarioName, testBuild, prpcversion, param);
        return jsonString;
    }
}
