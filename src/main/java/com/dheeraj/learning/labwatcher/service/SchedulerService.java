package com.dheeraj.learning.labwatcher.service;

import com.dheeraj.learning.labwatcher.dto.ParamData;
import com.dheeraj.learning.labwatcher.dto.PerfStatDTO;
import com.dheeraj.learning.labwatcher.dto.ScenarioData;
import com.dheeraj.learning.labwatcher.util.DegradationIdentificationUtil;
import com.dheeraj.learning.labwatcher.util.FormatUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class SchedulerService {

    private static String START_DATE = "2018-11-14";
    private static String END_DATE = "2018-11-23";
    private static String TEST_BUILD = "PRPC-HEAD-6577";
    private static String SCENARIO_NAME = "CCCASE";
    private static String PRPC_VERSION = "8.2.0";

    @Autowired
    private PerfStatService perfStatService;

    public void analyseRangeOfData() {
        List<String> scenariosList = new ArrayList<>();
        populateScenariosList(scenariosList);

        List<String> paramList = populateParamsList();

        List<String> validBuildLabels = perfStatService.getValidBuildLabels(SCENARIO_NAME, PRPC_VERSION, START_DATE, END_DATE);
        System.out.println(validBuildLabels);

        for (String buildLabel :
                validBuildLabels) {
            ScenarioData scenarioData = callAScenario("CCCASE", paramList, PRPC_VERSION, buildLabel);
            System.out.println("BuildLabel : "+ buildLabel + ", isDegraded : " + scenarioData.getMap().get("totalreqtime").isDegraded());
        }
    }

    public void analyseAParticularBuild() {
        String specificBuild = "PRPC-HEAD-6577";
        List<String> paramList = populateParamsList();

        ScenarioData scenarioData = callAScenario("CCCASE", paramList, PRPC_VERSION, specificBuild);
        System.out.println("BuildLabel : "+ specificBuild + ", isDegraded : " + scenarioData.getMap().get("totalreqtime").isDegraded());

        System.out.println(FormatUtil.convertToJSON(scenarioData));
    }

    public ScenarioData analyseAParticularBuild(String scenarioName, String testBuildLabel, String prpcVersion, String params) {
        List<String> paramList = populateParamsList(params);

        ScenarioData scenarioData = callAScenario(scenarioName, paramList, prpcVersion, testBuildLabel);
        System.out.println("BuildLabel : "+ testBuildLabel + ", isDegraded : " + scenarioData.getMap().get("totalreqtime").isDegraded());

        return scenarioData;
    }

    public String analyseAParticularBuildReturnString(String scenarioName, String testBuildLabel, String prpcVersion, String params) {
        List<String> paramList = populateParamsList(params);

        ScenarioData scenarioData = callAScenario(scenarioName, paramList, prpcVersion, testBuildLabel);
        System.out.println("BuildLabel : "+ testBuildLabel + ", isDegraded : " + scenarioData.getMap().get("totalreqtime").isDegraded());

        String jsonString = FormatUtil.convertToJSON(scenarioData);

        return jsonString;
    }

    /**
     * This method analyzes last n (maxResults #50 for now) perfstats(previous to the #testBuild} and calculates mean and standard deviation for the given parameters.
     * Then it calculates if the #testBuild metrics are degraded based on how far they are from standard deviation.
     *
     * @param scenarioName Scenario to be tested.
     * @param paramList Parameters to be tested.
     * @param prpcVersion Prpcversion of the testing builds.
     * @param testBuild The build for which degradation analysis to be done.
     * @return This an object which contains all the degradation details.
     */
    public ScenarioData callAScenario(String scenarioName, List<String> paramList, String prpcVersion, String testBuild) {
        ScenarioData scenarioData = new ScenarioData();
        scenarioData.setTestname(scenarioName);
        scenarioData.setLatestbuild(testBuild);

        //Bullet points
            //If the degradation continues for 5(This can be varied based on the scenario and parameter) builds, we dont need manual intervention to consider it as degraded.
        //Get number of builds after last degradation/improvement
        //Query table2 and get the last build having degradation for the given metric and get its rank (#the position in the last 100 builds).
            //If the rank is less than 5, then do X
            //If the rank is between 5 and n, then do Y
            //If the rank is beyond n, then just do the existing logic.

            //**X = Update the accuracy of the previous build according to the current result.
                //Say last degraded at build A with rank 1, then take mean of build A+Current build and then verify if the value is beyond std. If yes, increase accuracy of the earlier degradation.
                    //If no, mark the deviation at build A as outlier and consider current build as valid build.
                //Say last degraded at build A with rank 2, then take mean of build A + (A+1) and current build and then verify if the value is beyond std. If yes, increase accuracy of the earlier degradation.
                    //If no, mark the deviation at build A as outlier and consider current build as valid build.

        List<PerfStatDTO> resultDTOs = perfStatService.getPerfStatsForLastNBuilds(scenarioName, prpcVersion, testBuild, 50, true);
        Map<String, ParamData> tempMap = DegradationIdentificationUtil.getStandardDeviation(resultDTOs, paramList);

        PerfStatDTO perfStatDTO = perfStatService.getPerfStatForAGivenBuild(scenarioName, testBuild);

        for (String param : paramList) {
            DegradationIdentificationUtil.isDegraded(tempMap, param, perfStatDTO.getDouble(param));
        }

        scenarioData.setMap(tempMap);

        //Map this scenario data into an entity and then save it to database.

        return scenarioData;
    }

    public String getLastDegradedBuild() {
        return "PRPC-HEAD-6814";
    }

    public void populateScenariosList(List<String> list) {
        list.add("SimpleSurvey");

        /*list.add("SimpleSurvey");
        list.add("CCCASE");
        list.add("ChaseMidas");
        list.add("Mortgage");
        list.add("CallCenterJUnit");
        list.add("DataEngineJUnit");
        list.add("DevPerfJUnit");
        list.add("Integration");
        list.add("ISBANK");
        list.add("MultiChannel");
        list.add("Offline");
        list.add("RBS");
        /*list.add("PerfClip");*/
    }

    public List<String> populateParamsList() {
        /*list.add("totalreqtime");
        list.add("rdbiocount");
        list.add("commitcount");*/
        List<String> list = new ArrayList<>();

        list.add("activationdatatimeelapsed");
        list.add("activitycount");
        list.add("alertcount");
        list.add("buildnumber");
        list.add("commitcount");
        list.add("commitelapsed");
        list.add("commitrowcount");
        list.add("connectcount");
        list.add("connectelapsed");
        list.add("dbopexceedingthresholdcount");
        list.add("declarativerulereadcount");
        list.add("declarativerulesinvokedcount");
        list.add("declarativerulesinvokedcpu");
        list.add("declarativeruleslookupcount");
        list.add("declarativeruleslookupcpu");
        list.add("declexprctxfreeusecount");
        list.add("declntwksbuildconstcpu");
        list.add("declntwksbuildconstelapsed");
        list.add("declntwksbuildhlcpu");
        list.add("declntwksbuildhlelap");
        list.add("declrulesinvokedelapsed");
        list.add("declruleslookupelapsed");
        list.add("errorcount");
        list.add("flowcount");
        list.add("gcmaxduration");
        list.add("gcmaxgarbage");
        list.add("gcmaxpostcollection");
        list.add("gcmaxprecollection");
        list.add("gctotalduration");
        list.add("gctotalevents");
        list.add("gctotalgarbage");
        list.add("gctotalmajor");
        list.add("gctotalminor");
        list.add("gctotalother");
        list.add("gctotalpostcollection");
        list.add("gctotalprecollection");
        list.add("indexcount");
        list.add("infergeneratedjavacount");
        list.add("infergeneratedjavacountcpu");
        list.add("infergeneratedjavacpu");
        list.add("infergeneratedjavaelapsed");
        list.add("infergeneratedjavahlelapsed");
        list.add("interactions");
        list.add("javaassemblecount");
        list.add("javaassemblecpu");
        list.add("javaassembleelapsed");
        list.add("javaassemblehlelapsed");
        list.add("javacompilecount");
        list.add("javacompilecpu");
        list.add("javacompileelapsed");
        list.add("javageneratecount");
        list.add("javageneratecpu");
        list.add("javagenerateelapsed");
        list.add("javasyntaxcpu");
        list.add("legacyruleapiusedcount");
        list.add("listrowwithunfilteredstrmcnt");
        list.add("listwithunfilteredstrmcnt");
        list.add("loadedclasscount");
        list.add("lookuplistdbfetches");
        list.add("otherbrowsecpu");
        list.add("otherbrowseelapsed");
        list.add("othercount");
        list.add("otherfromcachecount");
        list.add("otheriocount");
        list.add("otheriocpu");
        list.add("otherioelapsed");
        list.add("parserulecount");
        list.add("peakthreadcount");
        list.add("proceduralrulereadcount");
        list.add("processcpu");
        list.add("rdbiocount");
        list.add("rdbioelapsed");
        list.add("rdbrowwithoutstreamcount");
        list.add("rdbwithoutstreamcount");
        list.add("rulebrowsecpu");
        list.add("rulebrowseelapsed");
        list.add("rulecount");
        list.add("rulecpu");
        list.add("rulefromcachecount");
        list.add("ruleioelapsed");
        list.add("rulesexecuted");
        list.add("ruleused");
        list.add("runmodelcount");
        list.add("runotherrulecount");
        list.add("runstreamcount");
        list.add("runwhencount");
        list.add("testelapsedminutes");
        list.add("threadcount");
        list.add("totalloadedclasscount");
        list.add("totalreqcpu");
        list.add("totalreqtime");
        list.add("totalstartedthreadcount");
        list.add("unloadedclasscount");
        list.add("wallseconds");

        return list;
    }

    /**
     * This is an utility method to construct list of parameters to be tested.
     *
     * @param params
     * @return
     */
    public List<String> populateParamsList(String params) {
        if("ALL".equalsIgnoreCase(params))
            return populateParamsList();
        else {
            List<String> list = new ArrayList<>();
            list.add(params);
            return list;
        }
    }
}
