package com.dheeraj.learning.labwatcher.dto;


public class ParamDataDTO {
    String scenarioName;
    String paramName;
    Double mean;
    Double standardDeviation;
    String buildLabel;
    Double paramValue;
    //TODO : Change this to Integer
    Double noOfValidRecords = 0.0;
    transient Double tempsum = 0.0;
    transient Double tempSD = 0.0;
    Double variedBy = 0.0;
    int variedBuildRank;
    boolean isDegraded;
    boolean isImproved;
    Double accuracy = 0.0;

    public ParamDataDTO() {
    }

    public ParamDataDTO(String paramName) {
        this.paramName = paramName;
    }

    public ParamDataDTO(String paramName, String scenarioName, String buildLabel) {
        this.paramName = paramName;
        this.scenarioName = scenarioName;
        this.buildLabel = buildLabel;
    }

    public String getBuildLabel() {
        return buildLabel;
    }

    public void setBuildLabel(String buildLabel) {
        this.buildLabel = buildLabel;
    }

    public String getScenarioName() {
        return scenarioName;
    }

    public void setScenarioName(String scenarioName) {
        this.scenarioName = scenarioName;
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

    public Double getTempsum() {
        return tempsum;
    }

    public void setTempsum(Double tempsum) {
        this.tempsum = tempsum;
    }

    public Double getTempSD() {
        return tempSD;
    }

    public void setTempSD(Double tempSD) {
        this.tempSD = tempSD;
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

    public boolean isDegraded() {
        return isDegraded;
    }

    public void setDegraded(boolean degraded) {
        isDegraded = degraded;
    }

    public void calcMean() {
        mean = tempsum / noOfValidRecords;
    }

    public void calcStandardDeviation() {
        standardDeviation = Math.sqrt(tempSD / noOfValidRecords);
    }

    @Override
    public String toString() {
        return "ParamDataDTO{" +
                "scenarioName='" + scenarioName + '\'' +
                ", paramName='" + paramName + '\'' +
                ", mean=" + mean +
                ", standardDeviation=" + standardDeviation +
                ", buildLabel='" + buildLabel + '\'' +
                ", paramValue=" + paramValue +
                ", noOfValidRecords=" + noOfValidRecords +
                ", tempsum=" + tempsum +
                ", tempSD=" + tempSD +
                ", variedBy=" + variedBy +
                ", variedBuildRank=" + variedBuildRank +
                ", isDegraded=" + isDegraded +
                ", isImproved=" + isImproved +
                ", accuracy=" + accuracy +
                '}';
    }
}