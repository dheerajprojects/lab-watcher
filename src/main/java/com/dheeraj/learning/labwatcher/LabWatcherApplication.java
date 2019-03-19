package com.dheeraj.learning.labwatcher;

import com.dheeraj.learning.labwatcher.dto.PerfStatDTO;
import com.dheeraj.learning.labwatcher.service.PerfStatService;
import com.dheeraj.learning.labwatcher.service.SchedulerService;
import com.dheeraj.learning.labwatcher.util.FormatUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.List;

@SpringBootApplication
public class LabWatcherApplication {

    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(LabWatcherApplication.class, args);
        SchedulerService schedulerService = applicationContext.getBean(SchedulerService.class);
        //schedulerService.analyseRangeOfData();
        schedulerService.analyseAParticularBuild();
    }
}
