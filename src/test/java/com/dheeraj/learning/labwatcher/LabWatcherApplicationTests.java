package com.dheeraj.learning.labwatcher;

import com.dheeraj.learning.labwatcher.dao.PerfStatDAO;
import com.dheeraj.learning.labwatcher.entity.ParamData;
import com.dheeraj.learning.labwatcher.service.SchedulerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LabWatcherApplicationTests {

    @Autowired
    SchedulerService schedulerService;

    @Autowired
    PerfStatDAO perfStatDAO;

    @Test
    public void contextLoads() {
        //System.out.println(perfStatDAO.getVariedBuildRankDetails("CCCASE", "totalreqtime", "PRPC-HEAD-6578"));
        schedulerService.analyseAScenarioMultipleBuildsHardCoded();
    }
}
