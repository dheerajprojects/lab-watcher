package com.dheeraj.learning.labwatcher.controller;

import com.dheeraj.learning.labwatcher.dto.ScenarioData;
import com.dheeraj.learning.labwatcher.entity.PerfStat;
import com.dheeraj.learning.labwatcher.repository.PerfStatsRepository;
import com.dheeraj.learning.labwatcher.service.SchedulerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
public class LabWatcherController {

    @Autowired
    private SchedulerService schedulerService;

    @GetMapping("analysescenario/{scenarioName}/{prpcverison}/{testBuild}/{param}")
    public ScenarioData analyseAParticularBuild(@PathVariable("scenarioName") String scenarioName, @PathVariable("prpcverison") String prpcversion,
                                          @PathVariable("testBuild") String testBuild, @PathVariable("param") String param){
        ScenarioData scenarioData = schedulerService.analyseAParticularBuild(scenarioName, testBuild, prpcversion, param);
        return scenarioData;
    }

    @GetMapping("analysescenario/stringresponse/{scenarioName}/{prpcverison}/{testBuild}/{param}")
    public String analyseAParticularBuildAndReturnString(@PathVariable("scenarioName") String scenarioName, @PathVariable("prpcverison") String prpcversion,
                                                @PathVariable("testBuild") String testBuild, @PathVariable("param") String param){
        String jsonString = schedulerService.analyseAParticularBuildReturnString(scenarioName, testBuild, prpcversion, param);
        return jsonString;
    }
}
