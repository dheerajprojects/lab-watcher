package com.dheeraj.learning.labwatcher.dto;


public class ParamDataDTO {
    String name;
    Double mean;
    Double standardDeviation;
    Double latestBuildValue;
    Double noOfValidRecords = 0.0;
    transient Double tempsum = 0.0;
    transient Double tempSD = 0.0;
    Double degradationPercentage = 0.0;
    boolean isDegraded;

    public ParamDataDTO(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Double getLatestBuildValue() {
        return latestBuildValue;
    }

    public void setLatestBuildValue(Double latestBuildValue) {
        this.latestBuildValue = latestBuildValue;
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

    public Double getDegradationPercentage() {
        return degradationPercentage;
    }

    public void setDegradationPercentage(Double degradationPercentage) {
        this.degradationPercentage = degradationPercentage;
    }

    public boolean isDegraded() {
        return isDegraded;
    }

    public void setDegraded(boolean degraded) {
        isDegraded = degraded;
    }

    public void calcMean(){
        mean = tempsum / noOfValidRecords;
    }

    public void calcStandardDeviation(){
        standardDeviation = Math.sqrt(tempSD / noOfValidRecords);
    }

    @Override
    public String toString() {
        return "ParamDataDTO{" +
                "\n\t\tname='" + name + '\'' + "," +
                "\n\t\tmean=" + mean + "," +
                "\n\t\tstandardDeviation=" + standardDeviation + "," +
                "\n\t\tlatestBuildValue=" + latestBuildValue + "," +
                "\n\t\tnoOfValidRecords=" + noOfValidRecords + "," +
                "\n\t\tdegradationPercentage=" + degradationPercentage + "," +
                "\n\t" + '}';
    }
    
    
}