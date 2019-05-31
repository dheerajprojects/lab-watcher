package com.dheeraj.learning.labwatcher.service;

import com.dheeraj.learning.labwatcher.dao.PerfStatDAO;
import com.dheeraj.learning.labwatcher.dto.ScenarioDataDTO;
import com.dheeraj.learning.labwatcher.entity.PerfStat;
import com.dheeraj.learning.labwatcher.util.DataUtil;
import com.dheeraj.learning.labwatcher.util.DateUtil;
import com.dheeraj.learning.labwatcher.util.FormatUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    @Autowired
    private PerfStatService perfStatService;


    @Autowired
    PerfStatDAO perfStatDAO;

    @Autowired
    ConfigurationService configurationService;

    /**
     * This method is analyses the latest build of a scenario with the last n builds
     * and identifies if the latest build is degraded or not.
     */
    public void analyseAScenarioLatestBuild() {
        String scenarioName = "CCCASE";
        List<String> paramList = DataUtil.populateGivenParamsList("totalreqtime");
        String prpcVersion = "8.3.0";
        String currentBuildLabel = "PRPC-HEAD-6813";

        perfStatService.callAScenario(scenarioName, paramList, prpcVersion, currentBuildLabel, true);
    }

    /**
     * This method analyses degradations occurred in a scenario between the given dates.
     *
     */
    public void analyseAScenarioMultipleBuilds(String scenarioName) {
        List<String> paramList = DataUtil.populateGivenParamsList("totalreqtime","rdbiocount");
        String prpcVersion = "8.3.0";
        String startDate = "2019-01-04";
        String endDate = "2019-01-19";
        List<String> validBuildLabels = perfStatService.getValidBuildLabelsBetweenGivenDates(scenarioName, prpcVersion, startDate, endDate);


        for (String buildLabel : validBuildLabels) {
            ScenarioDataDTO scenarioDataDTO = perfStatService.callAScenario(scenarioName, paramList, prpcVersion, buildLabel, true);
            logger.info("BuildLabel : " + buildLabel + ", isDegraded : " + scenarioDataDTO.getMap().get("totalreqtime").isDegraded()+", isImproved : "+scenarioDataDTO.getMap().get("totalreqtime").isImproved());
        }
    }

    public void analyseMultipleScenariosMultipleBuilds() {
        List<String> scenarioNames = DataUtil.populateScenariosList();

        for (String scenarioName : scenarioNames) {
            logger.info("==============================================================================================");
            logger.info("Started analysing scenario : "+scenarioName);
            try {
                analyseAScenarioMultipleBuilds(scenarioName);
            } catch (Exception e) {
                e.printStackTrace();
            }
            logger.info("Completed analysing scenario : "+scenarioName);
            logger.info("==============================================================================================");
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
    public ScenarioDataDTO analyseAScenarioLatestBuildGivenParam(String scenarioName, String currentBuildLabel, String prpcVersion, String param) {
        List<String> paramList = DataUtil.populateGivenParamsList(param);

        ScenarioDataDTO scenarioDataDTO = perfStatService.callAScenario(scenarioName, paramList, prpcVersion, currentBuildLabel, true);
        logger.debug(scenarioDataDTO.toString());

        return scenarioDataDTO;
    }

    /**
     * This method is analyses the latest build of a scenario with the last n builds
     * and identifies if the latest build is degraded or not.
     */
    public ScenarioDataDTO analyseAScenarioLatestBuild(String scenarioName, String prpcVersion, String currentBuildLabel, String isvalidrun) {
        List<String> paramList = configurationService.getPerformanceMetrics();

        fixTimeAttributeForJUnits(scenarioName, paramList);

        ScenarioDataDTO scenarioDataDTO = null;

        if(isvalidrun.equalsIgnoreCase("true")) {
            System.out.println("Test ran successfully.");
            scenarioDataDTO = perfStatService.callAScenario(scenarioName, paramList, prpcVersion, currentBuildLabel, true);
            //perstatservice.doDegradationAnalysis(scenarioName, paramList, prpcVersion, currentBuildLabel, true);
        } else {
            System.out.println("Test failed.");
            System.out.println("Running test failure analysis.");
            //scenarioDataDTO = perfStatService.doFailureAnalysisOnAScenario(scenarioName, prpcVersion, currentBuildLabel);
        }

        logger.debug(scenarioDataDTO!=null?scenarioDataDTO.toString():"ScenarioDataDTO is null");

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
        ScenarioDataDTO scenarioDataDTO = analyseAScenarioLatestBuildGivenParam(scenarioName, testBuildLabel, prpcVersion, param);

        String jsonString = FormatUtil.convertToJSON(scenarioDataDTO);
        logger.debug(jsonString);

        return jsonString;
    }

    /**
     * This methods triggers analysis for the past n days #numberOfDays builds from current date.
     * When scenarioName is null, it runs on all performance scenarios.
     *
     * @param scenarioName
     * @param startDate
     * @param numberOfDays
     */
    public void scheduleDailyRuns(String scenarioName, String startDate, Integer numberOfDays) {
        logger.info("Cron Task :: Execution Time - {}", dateTimeFormatter.format(LocalDateTime.now()));

        if(startDate == null)
            startDate = LocalDate.now().toString();

        List<String> dates = DateUtil.getDates(LocalDate.parse(startDate).toString(), numberOfDays);
        for (String date : dates) {
            runAnalysisOnDailyBuilds(date, scenarioName);
        }
    }

    public void scheduleDailyRuns() {
        logger.info("Cron Task :: Execution Time - {}", dateTimeFormatter.format(LocalDateTime.now()));

        LocalDateTime endDate = LocalDate.now().atStartOfDay();
        LocalDateTime startDate = endDate.minusDays(1);
        //Gets all available builds from Yesterday 0 hour to today 0 hour.
        List<PerfStat> perfStats = perfStatDAO.getBuildsBetweenBuilds(startDate.toString(), endDate.toString());

        runAnalysisOnGivenPerfStats(perfStats);
    }

    public void runAnalysisOnDailyBuilds(String date, String scenarioName) {
        logger.info("Running analysis on performance metrics on "+date+"...");
        List<PerfStat> perfStats;
        if(scenarioName == null) {
            perfStats = perfStatDAO.getBuilds(date);
        } else {
            perfStats = perfStatDAO.getBuilds(date, scenarioName);
        }


        runAnalysisOnGivenPerfStats(perfStats);
    }

    public void runAnalysisOnGivenPerfStats(List<PerfStat> perfStats) {
        logger.info("Number of builds for analysis : "+perfStats.size());
        for (PerfStat perfstat : perfStats) {
            try {
                logger.info("Started processing scenario : "+perfstat.getTestname()+", buildlabel : "+perfstat.getBuildlabel());

                analyseAScenarioLatestBuild(perfstat.getTestname(), perfstat.getPrpcversion(), perfstat.getBuildlabel(), perfstat.getIsvalidrun());

                logger.info("Completed processing scenario : "+perfstat.getTestname()+", buildlabel : "+perfstat.getBuildlabel());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Time attribute for junits is wallseconds. So this method replaces totalreqtime with wallseconds for all junit scenarios.
     * TODO : Make use of enum #JUnitScenarios.
     * @param paramList
     */
    public void fixTimeAttributeForJUnits(String scenarioName, List<String> paramList) {
        List<String> jUnitScenarios = new ArrayList<>();
        jUnitScenarios.add("PerfClip");
        jUnitScenarios.add("DevPerfJUnit");
        jUnitScenarios.add("DataEngineJUnit");
        jUnitScenarios.add("CallCenterJUnit");

        if(jUnitScenarios.contains(scenarioName)) {
            if(paramList.remove("totalreqtime"))
                paramList.add("wallseconds");
        }
    }
}
