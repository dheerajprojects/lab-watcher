package com.dheeraj.learning.labwatcher.entity;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "SCENARIODATA1")
public class ScenarioData {
    @Id
    @GeneratedValue
    int id;
    String testname;
    String buildInfo;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "scenarioData")
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

    public String getBuildInfo() {
        return buildInfo;
    }

    public void setBuildInfo(String latestbuild) {
        this.buildInfo = latestbuild;
    }

    public void addParam(String paramName) {
        set.add(new ParamData(paramName));
    }

    public void addParam(ParamData paramData) {
        if (set == null)
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
        return "ScenarioData{" +
                "id=" + id +
                ", testname='" + testname + '\'' +
                ", buildInfo='" + buildInfo + '\'' +
                ", set=" + set +
                '}';
    }
}
