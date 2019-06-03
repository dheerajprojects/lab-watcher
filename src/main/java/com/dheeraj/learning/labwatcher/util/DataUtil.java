package com.dheeraj.learning.labwatcher.util;

import com.dheeraj.learning.labwatcher.dto.ParamDataDTO;
import com.dheeraj.learning.labwatcher.dto.ScenarioDataDTO;
import com.dheeraj.learning.labwatcher.entity.ParamData;
import com.dheeraj.learning.labwatcher.entity.ScenarioData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DataUtil {

    static Logger logger = LoggerFactory.getLogger(DataUtil.class);

    /**
     * This creates an object of ScenarioData with two set of param details.
     *
     * @return
     */
    public static ScenarioData getScenarioData() {
        ParamData paramData1 = new ParamData("totalreqtime");
        paramData1.setDegraded(true);
        paramData1.setMean(94.59659999999998);
        paramData1.setStandardDeviation(3.531566570234802);
        paramData1.setParamValue(91.76);
        paramData1.setVariedBy(2.998627857660821);
        paramData1.setNoOfValidRecords(10.0);
        paramData1.setAccuracy(60.0);


        ParamData paramData2 = new ParamData("rdbiocount");
        paramData2.setDegraded(false);
        paramData2.setMean(93.59612499999998);
        paramData2.setStandardDeviation(43.531566570234802);
        paramData2.setParamValue(91.73246);
        paramData2.setVariedBy(29.998627857660821);
        paramData2.setNoOfValidRecords(20.0);
        paramData2.setAccuracy(60.0);

        ScenarioData scenarioData = new ScenarioData();
        scenarioData.setBuildLabel("PRPC-HEAD-5577");
        scenarioData.setTestname("CCCASE");
        scenarioData.addParam(paramData1);
        scenarioData.addParam(paramData2);

        paramData1.setScenarioData(scenarioData);
        paramData2.setScenarioData(scenarioData);

        return scenarioData;
    }

    public static ScenarioDataDTO getScenarioDataDTO() {
        ScenarioData scenarioData = getScenarioData();
        return Mapper.convert(scenarioData);
    }

    /**
     * Improve this to print message properly. Keep separate message for improvement and degradation.
     *
     * @param paramDataDTO
     */
    public static void printVariationMessage(ParamDataDTO paramDataDTO) {
        logger.info(paramDataDTO.getScenarioName() + ", "+ paramDataDTO.getBuildLabel()+", "+paramDataDTO.getParamName() + " - " + paramDataDTO.getVariedBy());

        logger.trace("The scenario : " + paramDataDTO.getScenarioName() + " for build :" + paramDataDTO.getBuildLabel() +
                " is degraded/improved in terms of performance metric : " + paramDataDTO.getParamName() + " with " + paramDataDTO.getVariedBy() + " percentage.");

        logger.debug(paramDataDTO.toString());
    }

    public static List<String> populateScenariosList() {
        List<String> list = new ArrayList<>();
        list.add("RBS");
        list.add("Offline");
        list.add("ChaseMidas");
        list.add("MultiChannel");
        list.add("Mortgage");
        list.add("CRM_731_SA");
        list.add("PerfClip");
        list.add("CustomerService");
        list.add("DevPerfJUnit");
        list.add("SimpleSurvey");
        list.add("CRM_731_CS");
        list.add("DataEngineJUnit");
        list.add("DSMBatchMarketing");
        list.add("ISBANK");
        list.add("CallCenterJUnit");
        list.add("Integration");
        list.add("CCCASE");
        list.add("CRM_81_CS");

        return list;
    }

    public static List<String> populateAllParamsList() {
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
    public static List<String> populateGivenParamsList(String... params) {
        List<String> list = new ArrayList<>();
        for (String param : params
        ) {
            list.add(param);
        }
        return list;
    }

    /**
     * Converts given comma separated string to arraylist.
     *
     * @param str
     * @return
     */
    public static ArrayList<String> buildArrayList(String str) {
        String[] strList = str.split(",");
        ArrayList<String> list = new ArrayList<>();
        int length = strList.length;
        for (int i = 0; i < length; i++) {
            list.add(strList[i]);
        }
        return list;
    }

    /**
     * Returns formatted string value.
     *
     * @param value
     * @return
     */
    public static String getValue(Double value) {
        DecimalFormat decimalFormat = new DecimalFormat("##.##");
        String val = null;
        try {
            val = decimalFormat.format(value);
        } catch (Exception e) {
            logger.error("Converting the value :" + value + " into decimal format has thrown exception.");
            e.printStackTrace();
            //Not throwing exception as this is just throw e;
        }
        return val;
    }

    public static void main(String[] args) {
        Random rand = new Random();
        for (int i = 0; i < 20; i++)
            System.out.println(rand.nextInt(10));
    }

    /**
     * Time attribute for junits is wallseconds. So this method replaces totalreqtime with wallseconds for all junit scenarios.
     * TODO : Make use of enum #JUnitScenarios.
     *
     * @param paramList
     */
    public static void fixTimeAttributeForJUnits(String scenarioName, List<String> paramList) {
        List<String> jUnitScenarios = new ArrayList<>();
        jUnitScenarios.add("PerfClip");
        jUnitScenarios.add("DevPerfJUnit");
        jUnitScenarios.add("DataEngineJUnit");
        jUnitScenarios.add("CallCenterJUnit");

        if (jUnitScenarios.contains(scenarioName)) {
            if (paramList.remove("totalreqtime"))
                paramList.add("wallseconds");
        }
    }
}
