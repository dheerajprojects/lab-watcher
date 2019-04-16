package com.dheeraj.learning.labwatcher.service;

import com.dheeraj.learning.labwatcher.util.DataUtil;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.reloading.FileChangedReloadingStrategy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

@Service
public class ConfigurationService {

    private Properties properties;
    private String PROPERTIES_FILE_NAME="src/main/resources/scenarioConfiguration.properties";

    private void loadProperties() {
        properties = new Properties();
        try {
            properties.load(new FileReader(PROPERTIES_FILE_NAME));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<String> getPerformanceMetrics() {
        loadProperties();
        String perfMetrics = properties.getProperty("perf.metrics");
        List<String> list = DataUtil.buildArrayList(perfMetrics);
        return list;
    }
}


//The below logic uses Apache properties load to load properties on certain interval everytime.

    /*private PropertiesConfiguration configuration;
    private String PROPERTIES_FILE_NAME="scenarioConfiguration.properties";

    public void loadProperties() {
        try {
            configuration = new PropertiesConfiguration(PROPERTIES_FILE_NAME);

            FileChangedReloadingStrategy fileChangedReloadingStrategy = new FileChangedReloadingStrategy();
            fileChangedReloadingStrategy.setRefreshDelay(1000);
            configuration.setReloadingStrategy(fileChangedReloadingStrategy);
        } catch (ConfigurationException e) {
            e.printStackTrace();
        }
    }

    public List<String> getPerformanceMetrics() {
        loadProperties();
        Object perfMetrics = configuration.getProperty("perf.metrics");
        return (List<String>)perfMetrics;
    }*/
