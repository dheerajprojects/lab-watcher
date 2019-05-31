package com.dheeraj.learning.labwatcher;

import com.dheeraj.learning.labwatcher.dao.ParamDataDAO;
import com.dheeraj.learning.labwatcher.dao.PerfStatDAO;
import com.dheeraj.learning.labwatcher.entity.ParamData;
import com.dheeraj.learning.labwatcher.entity.PerfStat;
import com.dheeraj.learning.labwatcher.repository.FlatPerformanceTestsRepository;
import com.dheeraj.learning.labwatcher.repository.ParamDataRepository;
import com.dheeraj.learning.labwatcher.repository.ScenarioDataRepository;
import com.dheeraj.learning.labwatcher.service.ConfigurationService;
import com.dheeraj.learning.labwatcher.service.EmailService;
import com.dheeraj.learning.labwatcher.service.SchedulerService;
import com.dheeraj.learning.labwatcher.util.DataUtil;
import com.dheeraj.learning.labwatcher.util.DateUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LabWatcherApplicationTests {

    @Autowired
    SchedulerService schedulerService;

    @Autowired
    PerfStatDAO perfStatDAO;

    @Autowired
    ConfigurationService configurationService;

    @Autowired
    ScenarioDataRepository scenarioDataRepository;

    @Autowired
    ParamDataRepository paramDataRepository;

    @Autowired
    ParamDataDAO paramDataDAO;

    @Autowired
    FlatPerformanceTestsRepository flatPerformanceTestsRepository;


    public void contextLoads() {
        //TODO : Remove DTOs and use only Entity objects everywhere.
        schedulerService.scheduleDailyRuns(null, "2019-04-17", 0);
    }

    //@Test
    public void testEmail() {
        EmailService.sendEmail(DataUtil.getScenarioDataDTO());
    }

    //@Test
    public void testGetValidBuildLabelsBetweenGivenDates() {
        String scenarioName = "MultiChannel";
        String prpcVersion = "8.2.0";
        String startDate = "2018-10-24";
        String endDate = "2019-03-31";

        List<String> validBuildLabels = perfStatDAO.getValidBuildLabelsBetweenGivenDates(scenarioName, prpcVersion, startDate, endDate);

        System.out.println(validBuildLabels);
    }


    public void testGetScenarioData() {
        System.out.println(perfStatDAO.getScenarioData("CCCASE", "PRPC-HEAD-6577"));
    }


    //@Test
    public void testGetLatestBuilds() {
        List<PerfStat> perfStats = perfStatDAO.getBuilds("2019-03-26");
        System.out.println(perfStats.size());
        for (PerfStat perfStat : perfStats) {
            System.out.println("BuildLabel : " + perfStat.getBuildlabel() + ", ScenarioName : " + perfStat.getTestname());
        }
    }

    //@Test
    public void testDateUtil() {
        System.out.println(DateUtil.getDates("2019-03-26", 1));
    }

    //@Test
    public void testPropertiesLoad() {
        System.out.println(configurationService.getPerformanceMetrics());
        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(configurationService.getPerformanceMetrics());
    }

    //@Test
    public void testScenarioDataRepository() {
        //List<ScenarioData> scenarioDatas = scenarioDataRepository.findAll();
        //List<ParamData> paramDatas = paramDataRepository.findByAccuracyGreaterThan(50.0);
        List<ParamData> paramDatas = paramDataDAO.getAll();
        for (ParamData paramData :
                paramDatas) {
            System.out.println(paramData);
        }
    }

    //@Test
    public void criteriaTest() {
        paramDataDAO.researchOnCriteria();
    }

    @Test
    public void testCollidersDB() {
        System.out.println(flatPerformanceTestsRepository.findById(44957));
    }
}
