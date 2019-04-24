package com.dheeraj.learning.labwatcher.controller;

import com.dheeraj.learning.labwatcher.dto.ParamDataDTO;
import com.dheeraj.learning.labwatcher.dto.ScenarioDataDTO;

import com.dheeraj.learning.labwatcher.service.DashboardService;
import com.dheeraj.learning.labwatcher.util.DataUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

/**
 * Shows dashboards based on the given input.
 * /analyzedresults/                        --all scenario all builds
 * /analyzedresults/scenarios/scenarioname  --all builds for the given scenario
 * /analyzedresults/builds/buildlabel       --all scenarios for the given build
 */
@Controller
public class WebController {

    @Autowired
    DashboardService dashboardService;

    /**
     * Returns complete data
     * @param model
     * @return
     */
    @RequestMapping(value="/analyzedresults/", method = RequestMethod.GET)
    public String getAnalyzedResults(Model model) {
        List<ParamDataDTO> paramDataDTOs = dashboardService.getAll();
        model.addAttribute("analyzedresults",paramDataDTOs);
        return "analyzedresults";
    }

    @RequestMapping(value="/analyzedresults/simple", method = RequestMethod.GET)
    public String getAnalyzedSimpleResults(Model model) {
        List<ParamDataDTO> paramDataDTOs = dashboardService.getAll();
        model.addAttribute("analyzedresults",paramDataDTOs);
        return "simpleResults";
    }

    @RequestMapping(value="/analyzedresults/scenarios/{scenarioname}", method = RequestMethod.GET)
    public String getScenarioResults(@PathVariable("scenarioname") String scenarioName, Model model) {
        List<ParamDataDTO> paramDataDTOs = dashboardService.getDataOnGivenScenario(scenarioName);
        model.addAttribute("analyzedresults",paramDataDTOs);
        return "analyzedresults";
    }

    @RequestMapping(value="/analyzedresults/builds/{buildlabel}", method = RequestMethod.GET)
    public String getBuildResults(@PathVariable("buildlabel") String buildlabel, Model model) {
        List<ParamDataDTO> paramDataDTOs = dashboardService.getDataOnGivenBuild(buildlabel);
        model.addAttribute("analyzedresults",paramDataDTOs);
        return "analyzedresults";
    }

    @RequestMapping(value="/analyzedresults/perfmetrics/{perfmetric}", method = RequestMethod.GET)
    public String getPerfMetricResults(@PathVariable("perfmetric") String perfmetric, Model model) {
        List<ParamDataDTO> paramDataDTOs = dashboardService.getDataOnGivenPerfMetric(perfmetric);
        model.addAttribute("analyzedresults",paramDataDTOs);
        return "analyzedresults";
    }

    @RequestMapping(value="/analyzedresults/degraded/perfmetrics/{perfmetric}", method = RequestMethod.GET)
    public String getDegradedPerfMetricResults(@PathVariable("perfmetric") String perfmetric, Model model) {
        List<ParamDataDTO> paramDataDTOs = dashboardService.getDataOnGivenPerfMetric(perfmetric, true);
        model.addAttribute("analyzedresults",paramDataDTOs);
        return "analyzedresults";
    }

    @RequestMapping(value="/analyzedresults/improved/perfmetrics/{perfmetric}", method = RequestMethod.GET)
    public String getImprovedPerfMetricResults(@PathVariable("perfmetric") String perfmetric, Model model) {
        List<ParamDataDTO> paramDataDTOs = dashboardService.getDataOnGivenPerfMetric(perfmetric, false);
        model.addAttribute("analyzedresults",paramDataDTOs);
        return "analyzedresults";
    }
}
