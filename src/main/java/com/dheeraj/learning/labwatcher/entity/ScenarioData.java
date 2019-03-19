package com.dheeraj.learning.labwatcher.entity;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Entity
@Table(name = "SCENARIODATA")
public class ScenarioData {
    @Id
    @GeneratedValue
    int id;
    String testname;
    String latestbuild;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "SCENARIO_PARAM", joinColumns = { @JoinColumn(name = "ID") }, inverseJoinColumns = { @JoinColumn(name = "PARAMID") })
    private Set<ParamData> set;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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
        set.add(new ParamData(paramName));
    }

    public void addParam(ParamData paramData){
        if(set == null)
            set = new HashSet<>();
        set.add(paramData);
    }

    public Set<ParamData> getSet() {
        return set;
    }

    public void setSet(Set<ParamData> set) {
        this.set = set;
    }

    @Override
    public String toString() {
        return "ScenarioDataDTO{" +
                "\n\ttestname='" + testname + '\'' + "," +
                "\n\tlatestbuild='" + latestbuild + '\'' + "," +
                "\n\tset=" + set + "," +
                "\n" + '}';
    }
}
