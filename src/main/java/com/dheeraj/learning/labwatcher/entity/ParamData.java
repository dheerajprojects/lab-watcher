package com.dheeraj.learning.labwatcher.entity;

import javax.persistence.*;

@Entity
@Table(name = "PARAMDATA")
public class ParamData {
    @Id
    @GeneratedValue
    int paramId;
    String paramName;
    Double mean;
    Double standardDeviation;
    Double paramValue;
    Double noOfValidRecords = 0.0;
    Double variedBy = 0.0;
    int variedBuildRank;
    boolean isDegraded;
    boolean isImproved;
    Double accuracy;
    @ManyToOne(cascade = CascadeType.ALL)
    ScenarioData scenarioData;

    public ParamData() {
    }

    public ParamData(String paramName) {
        this.paramName = paramName;
    }

    public int getParamId() {
        return paramId;
    }

    public void setParamId(int paramId) {
        this.paramId = paramId;
    }

    public String getParamName() {
        return paramName;
    }

    public void setParamName(String paramName) {
        this.paramName = paramName;
    }

    public Double getMean() {
        return mean;
    }

    public void setMean(Double mean) {
        this.mean = mean;
    }

    public Double getStandardDeviation() {
        return standardDeviation;
    }

    public void setStandardDeviation(Double standardDeviation) {
        this.standardDeviation = standardDeviation;
    }

    public Double getParamValue() {
        return paramValue;
    }

    public void setParamValue(Double paramValue) {
        this.paramValue = paramValue;
    }

    public Double getNoOfValidRecords() {
        return noOfValidRecords;
    }

    public void setNoOfValidRecords(Double noOfValidRecords) {
        this.noOfValidRecords = noOfValidRecords;
    }

    public Double getVariedBy() {
        return variedBy;
    }

    public void setVariedBy(Double variedBy) {
        this.variedBy = variedBy;
    }

    public int getVariedBuildRank() {
        return variedBuildRank;
    }

    public void setVariedBuildRank(int variedBuildRank) {
        this.variedBuildRank = variedBuildRank;
    }

    public boolean isDegraded() {
        return isDegraded;
    }

    public void setDegraded(boolean degraded) {
        isDegraded = degraded;
    }

    public boolean isImproved() {
        return isImproved;
    }

    public void setImproved(boolean improved) {
        isImproved = improved;
    }

    public Double getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(Double accuracy) {
        this.accuracy = accuracy;
    }

    public ScenarioData getScenarioData() {
        return scenarioData;
    }

    public void setScenarioData(ScenarioData scenarioData) {
        this.scenarioData = scenarioData;
    }

    @Override
    public String toString() {
        return "ParamData{" +
                "paramId=" + paramId +
                ", paramName='" + paramName + '\'' +
                ", mean=" + mean +
                ", standardDeviation=" + standardDeviation +
                ", paramValue=" + paramValue +
                ", noOfValidRecords=" + noOfValidRecords +
                ", variedBy=" + variedBy +
                ", variedBuildRank=" + variedBuildRank +
                ", isDegraded=" + isDegraded +
                ", isImproved=" + isImproved +
                ", accuracy=" + accuracy +
                ", buildLabel=" + scenarioData.getBuildLabel() +
                ", testname="+ scenarioData.getTestname() +
                '}';
    }
}