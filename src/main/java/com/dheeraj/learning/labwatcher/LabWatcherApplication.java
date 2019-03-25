package com.dheeraj.learning.labwatcher;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class LabWatcherApplication {
    public static void main(String[] args) {
        SpringApplication.run(LabWatcherApplication.class, args);
    }
}
