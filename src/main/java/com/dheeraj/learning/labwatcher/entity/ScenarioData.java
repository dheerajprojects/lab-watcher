package com.dheeraj.learning.labwatcher.entity;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "SCENARIODATA")
public class ScenarioData {
    @Id
    @GeneratedValue
    int id;
    String testname;
    String buildLabel;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "scenarioData")
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

    public String getBuildLabel() {
        return buildLabel;
    }

    public void setBuildLabel(String latestbuild) {
        this.buildLabel = latestbuild;
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
                ", buildLabel='" + buildLabel + '\'' +
                ", set=" + set +
                '}';
    }
}
