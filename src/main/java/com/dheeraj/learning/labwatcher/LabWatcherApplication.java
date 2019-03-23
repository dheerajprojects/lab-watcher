package com.dheeraj.learning.labwatcher;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class LabWatcherApplication {
    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(LabWatcherApplication.class, args);
    }
}
