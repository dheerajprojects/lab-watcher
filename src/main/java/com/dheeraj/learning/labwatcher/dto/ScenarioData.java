package com.dheeraj.learning.labwatcher.dto;

import java.util.Map;

public class ScenarioData {
    String testname;
    String latestbuild;
    Map<String, ParamData> map;

    public String getTestname() {
        return testname;
    }

    public void setTestname(String testname) {
        this.testname = testname;
    }

    public String getLatestbuild() {
        return latestbuild;
    }

    public void setLatestbuild(String latestbuild) {
        this.latestbuild = latestbuild;
    }

    public void addParam(String paramName){
        map.put(paramName, new ParamData(paramName));
    }

    public Map<String, ParamData> getMap() {
        return map;
    }

    public void setMap(Map<String, ParamData> map) {
        this.map = map;
    }

    @Override
    public String toString() {
        return "ScenarioData{" +
                "\n\ttestname='" + testname + '\'' + "," +
                "\n\tlatestbuild='" + latestbuild + '\'' + "," +
                "\n\tmap=" + map + "," +
                "\n" + '}';
    }
}
