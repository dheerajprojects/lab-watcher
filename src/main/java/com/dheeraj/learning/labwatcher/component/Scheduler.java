package com.dheeraj.learning.labwatcher.component;

import com.dheeraj.learning.labwatcher.service.SchedulerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * The below class is created based on : https://www.callicoder.com/spring-boot-task-scheduling-with-scheduled-annotation/
 *
 *
 *
 */
@Component
public class Scheduler {
    private static final Logger logger = LoggerFactory.getLogger(Scheduler.class);


    @Autowired
    SchedulerService schedulerService;

    @Scheduled(initialDelay=0, fixedRate=24*60*60*1000)
    public void scheduleTaskWithFixedRate() {
        schedulerService.scheduleDailyRuns();
    }

    //@Scheduled(fixedDelay = 2000)
    public void scheduleTaskWithFixedDelay() {
        schedulerService.scheduleDailyRuns();
    }

    public void scheduleTaskWithInitialDelay() {}

    /**
     * Can refer this link for cron tasks : http://www.nncron.ru/help/EN/working/cron-format.htm
     */
    //@Scheduled(cron = "45 17 * * * *")
    public void scheduleTaskWithCronExpression() {
        schedulerService.scheduleDailyRuns();
    }
}
