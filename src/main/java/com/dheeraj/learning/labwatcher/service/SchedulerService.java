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

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * This just mimics invocation of PerfStatService from a cron job.
 * <p>
 * Methods plan
 * 1.analyseAScenarioLatestBuild
 * 2.analyseAScenarioMultipleBuilds
 * <p>
 * <p>
 * Later
 * 1.analyseMultipleScenarios
 */
@Service
public class SchedulerService {

    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
    Logger logger = LoggerFactory.getLogger(SchedulerService.class);
    @Autowired
    PerfStatDAO perfStatDAO;
    @Autowired
    ConfigurationService configurationService;
    @Autowired
    private PerfStatService perfStatService;

    @Autowired
    private BuildThreadService buildThreadService;

    /**
     * This method is analyses the latest build of a scenario with the last n builds
     * and identifies if the latest build is degraded or not.
     */
    public void analyseAScenarioLatestBuild() {
        String scenarioName = "CCCASE";
        List<String> paramList = DataUtil.populateGivenParamsList("totalreqtime");
        String prpcVersion = "8.3.0";
        String currentBuildInfo = "HEAD-6813";

        buildThreadService.doDegradationAnalysis(scenarioName, paramList, prpcVersion, currentBuildInfo, true);
    }

    /**
     * This method analyses degradations occurred in a scenario between the given dates.
     */
    public void analyseAScenarioMultipleBuilds(String scenarioName) {
        List<String> paramList = DataUtil.populateGivenParamsList("totalreqtime", "rdbiocount");
        String prpcVersion = "8.3.0";
        String startDate = "2019-01-04";
        String endDate = "2019-01-19";
        List<String> validBuilds = perfStatService.getValidBuildsBetweenGivenDates(scenarioName, prpcVersion, startDate, endDate);


        for (String buildName : validBuilds) {
            ScenarioDataDTO scenarioDataDTO = perfStatService.doDegradationAnalysis(scenarioName, paramList, prpcVersion, buildName, true);
            logger.trace("Build : " + buildName + ", isDegraded : " + scenarioDataDTO.getMap().get("totalreqtime").isDegraded() + ", isImproved : " + scenarioDataDTO.getMap().get("totalreqtime").isImproved());
        }
    }

    public void analyseMultipleScenariosMultipleBuilds() {
        List<String> scenarioNames = DataUtil.populateScenariosList();

        for (String scenarioName : scenarioNames) {
            logger.trace("==============================================================================================");
            logger.trace("Started analysing scenario : " + scenarioName);
            try {
                analyseAScenarioMultipleBuilds(scenarioName);
            } catch (Exception e) {
                e.printStackTrace();
            }
            logger.trace("Completed analysing scenario : " + scenarioName);
            logger.trace("==============================================================================================");
        }
    }

    /**
     * This method analyses degradations occurred in a scenario between the given dates.
     */
    public void analyseAScenarioMultipleBuildsHardCoded() {
        String scenarioName = "CCCASE";
        List<String> paramList = DataUtil.populateGivenParamsList("totalreqtime");
        String prpcVersion = "8.2.0";
        List<String> validBuilds = DataUtil.buildArrayList("PRPC-HEAD-6575,PRPC-HEAD-6577,PRPC-HEAD-6578,PRPC-HEAD-6580,PRPC-HEAD-6583,PRPC-HEAD-6585,PRPC-HEAD-6586,PRPC-HEAD-6587");

        for (String buildName : validBuilds) {
            ScenarioDataDTO scenarioDataDTO = perfStatService.doDegradationAnalysis(scenarioName, paramList, prpcVersion, buildName, true);
            logger.debug("Build : " + buildName + ", isDegraded : " + scenarioDataDTO.getMap().get("totalreqtime").isDegraded());
        }
    }

    /**
     * This method is meant for invoking as a rest service from browser.
     *
     * @param scenarioName
     * @param currentBuildInfo
     * @param prpcVersion
     * @param param
     * @return
     */
    public ScenarioDataDTO analyseAScenarioLatestBuildGivenParam(String scenarioName, String currentBuildInfo, String prpcVersion, String param) {
        List<String> paramList = DataUtil.populateGivenParamsList(param);

        ScenarioDataDTO scenarioDataDTO = perfStatService.doDegradationAnalysis(scenarioName, paramList, prpcVersion, currentBuildInfo, true);
        logger.debug(scenarioDataDTO.toString());

        return scenarioDataDTO;
    }

    /**
     * This method is analyses the latest build of a scenario with the last n builds
     * and identifies if the latest build is degraded or not.
     */
    public ScenarioDataDTO analyseAScenarioGivenRelease(String scenarioName, String prpcVersion, String isvalidrun) {
        List<String> paramList = configurationService.getPerformanceMetrics();

        DataUtil.fixTimeAttributeForJUnits(scenarioName, paramList);

        ScenarioDataDTO scenarioDataDTO = null;

        List<String> validBuilds = perfStatService.getValidBuildsForGivenRelease(scenarioName, prpcVersion);

        for (String buildName : validBuilds) {
            if (isvalidrun.equalsIgnoreCase("true")) {
                scenarioDataDTO = perfStatService.doDegradationAnalysis(scenarioName, paramList, prpcVersion, buildName, true);
            } else {
                logger.trace("Test failed.");
                logger.trace("Yet to implement test failure analysis... ");
                //scenarioDataDTO = perfStatService.doFailureAnalysisOnAScenario(scenarioName, prpcVersion, currentBuildLabel);
            }
        }

        logger.debug(scenarioDataDTO != null ? scenarioDataDTO.toString() : "ScenarioDataDTO is null");

        return scenarioDataDTO;
    }

    /**
     * This method is analyses the latest build of a scenario with the last n builds
     * and identifies if the latest build is degraded or not.
     */
    public void analyseARelease(String prpcVersion) {
        perfStatService.analyseARelease(prpcVersion);
    }

    /**
     * This method is analyses the latest build of a scenario with the last n builds
     * and identifies if the latest build is degraded or not.
     */
    public ScenarioDataDTO analyseAScenarioLatestBuild(String scenarioName, String prpcVersion, String currentBuildInfo, String isvalidrun) {
        List<String> paramList = configurationService.getPerformanceMetrics();

        DataUtil.fixTimeAttributeForJUnits(scenarioName, paramList);

        ScenarioDataDTO scenarioDataDTO = null;

        if (isvalidrun.equalsIgnoreCase("true")) {
            logger.trace("Test ran successfully.");
            //TODO : Rename below method in a single commit.
            scenarioDataDTO = perfStatService.doDegradationAnalysis(scenarioName, paramList, prpcVersion, currentBuildInfo, true);
        } else {
            logger.trace("Test failed.");
            logger.trace("Yet to implement test failure analysis... ");
            //scenarioDataDTO = perfStatService.doFailureAnalysisOnAScenario(scenarioName, prpcVersion, currentBuildInfo);
        }

        logger.debug(scenarioDataDTO != null ? scenarioDataDTO.toString() : "ScenarioDataDTO is null");

        return scenarioDataDTO;
    }

    /**
     * This is meant for printing the analysis logic in json format.
     *
     * @param scenarioName
     * @param testBuildInfo
     * @param prpcVersion
     * @param param
     * @return
     */
    public String analyseAParticularBuildReturnString(String scenarioName, String testBuildInfo, String prpcVersion, String param) {
        ScenarioDataDTO scenarioDataDTO = analyseAScenarioLatestBuildGivenParam(scenarioName, testBuildInfo, prpcVersion, param);

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
        logger.info("Daily task - {}", dateTimeFormatter.format(LocalDateTime.now()));

        if (startDate == null)
            startDate = LocalDate.now().toString();

        List<String> dates = DateUtil.getDates(LocalDate.parse(startDate).toString(), numberOfDays);
        for (String date : dates) {
            runAnalysisOnDailyBuilds(date, scenarioName);
        }
    }

    public void scheduleDailyRuns() {
        LocalDateTime endDate = LocalDate.now().atStartOfDay();
        LocalDateTime startDate = endDate.minusDays(1);
        //Gets all available builds from Yesterday 0 hour to today 0 hour.
        List<PerfStat> perfStats = perfStatDAO.getBuildsBetweenDates(startDate.toString(), endDate.toString());

        runAnalysisOnGivenPerfStats(perfStats);
    }

    public void runAnalysisOnDailyBuilds(String date, String scenarioName) {
        logger.trace("Running analysis on performance metrics on " + date + "...");
        List<PerfStat> perfStats;
        if (scenarioName == null) {
            perfStats = perfStatDAO.getBuilds(date);
        } else {
            perfStats = perfStatDAO.getBuilds(date, scenarioName);
        }

        runAnalysisOnGivenPerfStats(perfStats);
    }

    public void runAnalysisOnGivenPerfStats(List<PerfStat> perfStats) {
        logger.trace("Number of builds for analysis : " + perfStats.size());
        for (PerfStat perfstat : perfStats) {
            try {
                logger.trace("Started processing scenario : " + perfstat.getTestname() + ", buildInfo : " + perfstat.getBuildinfo());

                analyseAScenarioLatestBuild(perfstat.getTestname(), perfstat.getPrpcversion(), perfstat.getBuildinfo(), perfstat.getIsvalidrun());

                logger.trace("Completed processing scenario : " + perfstat.getTestname() + ", buildInfo : " + perfstat.getBuildinfo());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


}
