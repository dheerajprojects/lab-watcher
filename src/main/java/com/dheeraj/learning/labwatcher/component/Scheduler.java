package com.dheeraj.learning.labwatcher.component;

import com.dheeraj.learning.labwatcher.service.SchedulerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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

    @Autowired
    SchedulerService schedulerService;

    //@Scheduled(initialDelay=0, fixedRate=24*60*60*1000)
    public void scheduleTaskWithFixedRate() {
        schedulerService.scheduleDailyRuns("DataEngineJUnit","2019-03-27" ,3);
        //schedulerService.analyseAScenarioMultipleBuildsHardCoded();
    }

    //@Scheduled(fixedDelay = 2000)
    public void scheduleTaskWithFixedDelay() {
        //schedulerService.scheduleDailyRuns(null);
    }

    public void scheduleTaskWithInitialDelay() {}

    /**
     * https://www.baeldung.com/cron-expressions
     * <second> <minute> <hour> <day-of-month> <month> <day-of-week> <year>
     *
     */
    @Scheduled(cron = "0 0 9 ? * *")
    public void scheduleTaskWithCronExpression() {
        schedulerService.scheduleDailyRuns();
    }
}
