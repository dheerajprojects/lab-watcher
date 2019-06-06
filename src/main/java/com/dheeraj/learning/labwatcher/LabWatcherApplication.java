package com.dheeraj.learning.labwatcher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@SpringBootApplication
@EnableScheduling
@EnableAsync
public class LabWatcherApplication {
    public static void main(String[] args) {
        SpringApplication.run(LabWatcherApplication.class, args);
    }

    @Autowired
    private Environment env;

    @Bean
    public Executor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(Integer.parseInt(env.getProperty("thread.corepoolsize")));
        executor.setMaxPoolSize(Integer.parseInt(env.getProperty("thread.maxpoolsize")));
        executor.setQueueCapacity(Integer.parseInt(env.getProperty("thread.queuecapacity")));
        executor.setThreadNamePrefix(env.getProperty("thread.corepoolsize"));
        executor.initialize();
        return executor;
    }
}
