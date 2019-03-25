package com.dheeraj.learning.labwatcher.component;

import com.dheeraj.learning.labwatcher.dao.PerfStatDAO;
import com.dheeraj.learning.labwatcher.entity.PerfStat;
import com.dheeraj.learning.labwatcher.service.SchedulerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * The below class is created based on : https://www.callicoder.com/spring-boot-task-scheduling-with-scheduled-annotation/
 *
 *
 *
 */
@Component
public class Scheduler {
    private static final Logger logger = LoggerFactory.getLogger(Scheduler.class);
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
    private static Integer NUMBER_OF_RECORDS=284;

    @Autowired
    PerfStatDAO perfStatDAO;

    @Autowired
    SchedulerService schedulerService;

    //@Scheduled(initialDelay=0, fixedRate=24*60*60*1000)
    public void scheduleTaskWithFixedRate() {
        runIt();
    }

    //@Scheduled(fixedDelay = 2000)
    public void scheduleTaskWithFixedDelay() {
        runIt();
    }

    public void scheduleTaskWithInitialDelay() {}

    /**
     * Can refer this link for cron tasks : http://www.nncron.ru/help/EN/working/cron-format.htm
     */
    //@Scheduled(cron = "45 17 * * * *")
    public void scheduleTaskWithCronExpression() {
        runIt();
    }

    public void runIt() {
        logger.info("Cron Task :: Execution Time - {}", dateTimeFormatter.format(LocalDateTime.now()));
        List<PerfStat> perfStats = perfStatDAO.getLatestBuilds();
        logger.info("Number of scenarios for analysis : "+perfStats.size());
        for (PerfStat perfstat : perfStats) {
            try {
                logger.info("Started processing scenario : "+perfstat.getTestname()+", buildlabel : "+perfstat.getBuildlabel());

                schedulerService.analyseMultipleScenariosLatestBuild(perfstat.getTestname(), perfstat.getPrpcversion(), perfstat.getBuildlabel());

                logger.info("Completed processing scenario : "+perfstat.getTestname()+", buildlabel : "+perfstat.getBuildlabel());
            } catch (Exception e) {
                logger.info(e.toString());
            }
        }
    }
}
