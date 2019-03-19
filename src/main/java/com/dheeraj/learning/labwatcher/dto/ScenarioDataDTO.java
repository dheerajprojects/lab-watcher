package com.dheeraj.learning.labwatcher.dto;

import java.util.Map;

public class ScenarioDataDTO {
    String testname;
    String latestbuild;
    Map<String, ParamDataDTO> map;

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
        map.put(paramName, new ParamDataDTO(paramName));
    }

    public Map<String, ParamDataDTO> getMap() {
        return map;
    }

    public void setMap(Map<String, ParamDataDTO> map) {
        this.map = map;
    }

    @Override
    public String toString() {
        return "ScenarioDataDTO{" +
                "\n\ttestname='" + testname + '\'' + "," +
                "\n\tlatestbuild='" + latestbuild + '\'' + "," +
                "\n\tmap=" + map + "," +
                "\n" + '}';
    }
}
