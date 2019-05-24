package com.dheeraj.learning.labwatcher.entity;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "flat_performance_tests", schema = "public", catalog = "postgres")
@TypeDef(
        name = "jsonb",
        typeClass = JsonBinaryType.class
)
public class FlatPerformanceTestsEntity {
    private Integer id;
    private String testname;
    private String stat;
    private String testlabel;
    private String buildurl;
    private String failmessage;
    private String failcause;
    private String testcontroller;
    private String jvmHeapMemory;
    private String tomcatinstancecount;
    private Boolean isga;
    private Boolean isvalidrun;
    private Boolean isofficial;
    private String javaversion;
    private Double users;
    private Double iterations;
    private String buildlabel;
    private Double totallines;
    private Double ignoredcount;
    private Double errorcount;
    private Double infocount;
    private Double debugcount;
    private Double warncount;
    private Double exceptioncount;
    private Double clientfails;
    private String application;
    private Timestamp teststart;
    private Double testelapsedminutes;
    private Double work;
    private Double worklist;
    private Double perfstats;
    private Double pxtotalreqcpu;
    private Double pxtotalreqtime;
    private Double pywallseconds;
    private Double pxactivitycount;
    private Double pxalertcount;
    private Double pxcommitcount;
    private Double pxcommitelapsed;
    private Double pxcommitrowcount;
    private Double pxothercount;
    private Double pxotheriocpu;
    private Double pxotheriocount;
    private Double pxotherioelapsed;
    private Double pxrdbiocount;
    private Double pxrdbioelapsed;
    private Double jmeterResptimeAvg;
    private Double gctotalevents;
    private Double gctotalduration;
    private Double totaljitcompilationtime;
    private String performanceData;

    @Id
    @Column(name = "id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "testname")
    public String getTestname() {
        return testname;
    }

    public void setTestname(String testname) {
        this.testname = testname;
    }

    @Basic
    @Column(name = "stat")
    public String getStat() {
        return stat;
    }

    public void setStat(String stat) {
        this.stat = stat;
    }

    @Basic
    @Column(name = "testlabel")
    public String getTestlabel() {
        return testlabel;
    }

    public void setTestlabel(String testlabel) {
        this.testlabel = testlabel;
    }

    @Basic
    @Column(name = "buildurl")
    public String getBuildurl() {
        return buildurl;
    }

    public void setBuildurl(String buildurl) {
        this.buildurl = buildurl;
    }

    @Basic
    @Column(name = "failmessage")
    public String getFailmessage() {
        return failmessage;
    }

    public void setFailmessage(String failmessage) {
        this.failmessage = failmessage;
    }

    @Basic
    @Column(name = "failcause")
    public String getFailcause() {
        return failcause;
    }

    public void setFailcause(String failcause) {
        this.failcause = failcause;
    }

    @Basic
    @Column(name = "testcontroller")
    public String getTestcontroller() {
        return testcontroller;
    }

    public void setTestcontroller(String testcontroller) {
        this.testcontroller = testcontroller;
    }

    @Basic
    @Column(name = "jvm_heap_memory")
    public String getJvmHeapMemory() {
        return jvmHeapMemory;
    }

    public void setJvmHeapMemory(String jvmHeapMemory) {
        this.jvmHeapMemory = jvmHeapMemory;
    }

    @Basic
    @Column(name = "tomcatinstancecount")
    public String getTomcatinstancecount() {
        return tomcatinstancecount;
    }

    public void setTomcatinstancecount(String tomcatinstancecount) {
        this.tomcatinstancecount = tomcatinstancecount;
    }

    @Basic
    @Column(name = "isga")
    public Boolean getIsga() {
        return isga;
    }

    public void setIsga(Boolean isga) {
        this.isga = isga;
    }

    @Basic
    @Column(name = "isvalidrun")
    public Boolean getIsvalidrun() {
        return isvalidrun;
    }

    public void setIsvalidrun(Boolean isvalidrun) {
        this.isvalidrun = isvalidrun;
    }

    @Basic
    @Column(name = "isofficial")
    public Boolean getIsofficial() {
        return isofficial;
    }

    public void setIsofficial(Boolean isofficial) {
        this.isofficial = isofficial;
    }

    @Basic
    @Column(name = "javaversion")
    public String getJavaversion() {
        return javaversion;
    }

    public void setJavaversion(String javaversion) {
        this.javaversion = javaversion;
    }

    @Basic
    @Column(name = "users")
    public Double getUsers() {
        return users;
    }

    public void setUsers(Double users) {
        this.users = users;
    }

    @Basic
    @Column(name = "iterations")
    public Double getIterations() {
        return iterations;
    }

    public void setIterations(Double iterations) {
        this.iterations = iterations;
    }

    @Basic
    @Column(name = "buildlabel")
    public String getBuildlabel() {
        return buildlabel;
    }

    public void setBuildlabel(String buildlabel) {
        this.buildlabel = buildlabel;
    }

    @Basic
    @Column(name = "totallines")
    public Double getTotallines() {
        return totallines;
    }

    public void setTotallines(Double totallines) {
        this.totallines = totallines;
    }

    @Basic
    @Column(name = "ignoredcount")
    public Double getIgnoredcount() {
        return ignoredcount;
    }

    public void setIgnoredcount(Double ignoredcount) {
        this.ignoredcount = ignoredcount;
    }

    @Basic
    @Column(name = "errorcount")
    public Double getErrorcount() {
        return errorcount;
    }

    public void setErrorcount(Double errorcount) {
        this.errorcount = errorcount;
    }

    @Basic
    @Column(name = "infocount")
    public Double getInfocount() {
        return infocount;
    }

    public void setInfocount(Double infocount) {
        this.infocount = infocount;
    }

    @Basic
    @Column(name = "debugcount")
    public Double getDebugcount() {
        return debugcount;
    }

    public void setDebugcount(Double debugcount) {
        this.debugcount = debugcount;
    }

    @Basic
    @Column(name = "warncount")
    public Double getWarncount() {
        return warncount;
    }

    public void setWarncount(Double warncount) {
        this.warncount = warncount;
    }

    @Basic
    @Column(name = "exceptioncount")
    public Double getExceptioncount() {
        return exceptioncount;
    }

    public void setExceptioncount(Double exceptioncount) {
        this.exceptioncount = exceptioncount;
    }

    @Basic
    @Column(name = "clientfails")
    public Double getClientfails() {
        return clientfails;
    }

    public void setClientfails(Double clientfails) {
        this.clientfails = clientfails;
    }

    @Basic
    @Column(name = "application")
    public String getApplication() {
        return application;
    }

    public void setApplication(String application) {
        this.application = application;
    }

    @Basic
    @Column(name = "teststart")
    public Timestamp getTeststart() {
        return teststart;
    }

    public void setTeststart(Timestamp teststart) {
        this.teststart = teststart;
    }

    @Basic
    @Column(name = "testelapsedminutes")
    public Double getTestelapsedminutes() {
        return testelapsedminutes;
    }

    public void setTestelapsedminutes(Double testelapsedminutes) {
        this.testelapsedminutes = testelapsedminutes;
    }

    @Basic
    @Column(name = "work")
    public Double getWork() {
        return work;
    }

    public void setWork(Double work) {
        this.work = work;
    }

    @Basic
    @Column(name = "worklist")
    public Double getWorklist() {
        return worklist;
    }

    public void setWorklist(Double worklist) {
        this.worklist = worklist;
    }

    @Basic
    @Column(name = "perfstats")
    public Double getPerfstats() {
        return perfstats;
    }

    public void setPerfstats(Double perfstats) {
        this.perfstats = perfstats;
    }

    @Basic
    @Column(name = "pxtotalreqcpu")
    public Double getPxtotalreqcpu() {
        return pxtotalreqcpu;
    }

    public void setPxtotalreqcpu(Double pxtotalreqcpu) {
        this.pxtotalreqcpu = pxtotalreqcpu;
    }

    @Basic
    @Column(name = "pxtotalreqtime")
    public Double getPxtotalreqtime() {
        return pxtotalreqtime;
    }

    public void setPxtotalreqtime(Double pxtotalreqtime) {
        this.pxtotalreqtime = pxtotalreqtime;
    }

    @Basic
    @Column(name = "pywallseconds")
    public Double getPywallseconds() {
        return pywallseconds;
    }

    public void setPywallseconds(Double pywallseconds) {
        this.pywallseconds = pywallseconds;
    }

    @Basic
    @Column(name = "pxactivitycount")
    public Double getPxactivitycount() {
        return pxactivitycount;
    }

    public void setPxactivitycount(Double pxactivitycount) {
        this.pxactivitycount = pxactivitycount;
    }

    @Basic
    @Column(name = "pxalertcount")
    public Double getPxalertcount() {
        return pxalertcount;
    }

    public void setPxalertcount(Double pxalertcount) {
        this.pxalertcount = pxalertcount;
    }

    @Basic
    @Column(name = "pxcommitcount")
    public Double getPxcommitcount() {
        return pxcommitcount;
    }

    public void setPxcommitcount(Double pxcommitcount) {
        this.pxcommitcount = pxcommitcount;
    }

    @Basic
    @Column(name = "pxcommitelapsed")
    public Double getPxcommitelapsed() {
        return pxcommitelapsed;
    }

    public void setPxcommitelapsed(Double pxcommitelapsed) {
        this.pxcommitelapsed = pxcommitelapsed;
    }

    @Basic
    @Column(name = "pxcommitrowcount")
    public Double getPxcommitrowcount() {
        return pxcommitrowcount;
    }

    public void setPxcommitrowcount(Double pxcommitrowcount) {
        this.pxcommitrowcount = pxcommitrowcount;
    }

    @Basic
    @Column(name = "pxothercount")
    public Double getPxothercount() {
        return pxothercount;
    }

    public void setPxothercount(Double pxothercount) {
        this.pxothercount = pxothercount;
    }

    @Basic
    @Column(name = "pxotheriocpu")
    public Double getPxotheriocpu() {
        return pxotheriocpu;
    }

    public void setPxotheriocpu(Double pxotheriocpu) {
        this.pxotheriocpu = pxotheriocpu;
    }

    @Basic
    @Column(name = "pxotheriocount")
    public Double getPxotheriocount() {
        return pxotheriocount;
    }

    public void setPxotheriocount(Double pxotheriocount) {
        this.pxotheriocount = pxotheriocount;
    }

    @Basic
    @Column(name = "pxotherioelapsed")
    public Double getPxotherioelapsed() {
        return pxotherioelapsed;
    }

    public void setPxotherioelapsed(Double pxotherioelapsed) {
        this.pxotherioelapsed = pxotherioelapsed;
    }

    @Basic
    @Column(name = "pxrdbiocount")
    public Double getPxrdbiocount() {
        return pxrdbiocount;
    }

    public void setPxrdbiocount(Double pxrdbiocount) {
        this.pxrdbiocount = pxrdbiocount;
    }

    @Basic
    @Column(name = "pxrdbioelapsed")
    public Double getPxrdbioelapsed() {
        return pxrdbioelapsed;
    }

    public void setPxrdbioelapsed(Double pxrdbioelapsed) {
        this.pxrdbioelapsed = pxrdbioelapsed;
    }

    @Basic
    @Column(name = "jmeter_resptime_avg")
    public Double getJmeterResptimeAvg() {
        return jmeterResptimeAvg;
    }

    public void setJmeterResptimeAvg(Double jmeterResptimeAvg) {
        this.jmeterResptimeAvg = jmeterResptimeAvg;
    }

    @Basic
    @Column(name = "gctotalevents")
    public Double getGctotalevents() {
        return gctotalevents;
    }

    public void setGctotalevents(Double gctotalevents) {
        this.gctotalevents = gctotalevents;
    }

    @Basic
    @Column(name = "gctotalduration")
    public Double getGctotalduration() {
        return gctotalduration;
    }

    public void setGctotalduration(Double gctotalduration) {
        this.gctotalduration = gctotalduration;
    }

    @Basic
    @Column(name = "totaljitcompilationtime")
    public Double getTotaljitcompilationtime() {
        return totaljitcompilationtime;
    }

    public void setTotaljitcompilationtime(Double totaljitcompilationtime) {
        this.totaljitcompilationtime = totaljitcompilationtime;
    }

    @Type(type = "jsonb")
    @Column(name = "performance_data",  columnDefinition = "jsonb")
    public String getPerformanceData() {
        return performanceData;
    }

    public void setPerformanceData(String performanceData) {
        this.performanceData = performanceData;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FlatPerformanceTestsEntity that = (FlatPerformanceTestsEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(testname, that.testname) &&
                Objects.equals(stat, that.stat) &&
                Objects.equals(testlabel, that.testlabel) &&
                Objects.equals(buildurl, that.buildurl) &&
                Objects.equals(failmessage, that.failmessage) &&
                Objects.equals(failcause, that.failcause) &&
                Objects.equals(testcontroller, that.testcontroller) &&
                Objects.equals(jvmHeapMemory, that.jvmHeapMemory) &&
                Objects.equals(tomcatinstancecount, that.tomcatinstancecount) &&
                Objects.equals(isga, that.isga) &&
                Objects.equals(isvalidrun, that.isvalidrun) &&
                Objects.equals(isofficial, that.isofficial) &&
                Objects.equals(javaversion, that.javaversion) &&
                Objects.equals(users, that.users) &&
                Objects.equals(iterations, that.iterations) &&
                Objects.equals(buildlabel, that.buildlabel) &&
                Objects.equals(totallines, that.totallines) &&
                Objects.equals(ignoredcount, that.ignoredcount) &&
                Objects.equals(errorcount, that.errorcount) &&
                Objects.equals(infocount, that.infocount) &&
                Objects.equals(debugcount, that.debugcount) &&
                Objects.equals(warncount, that.warncount) &&
                Objects.equals(exceptioncount, that.exceptioncount) &&
                Objects.equals(clientfails, that.clientfails) &&
                Objects.equals(application, that.application) &&
                Objects.equals(teststart, that.teststart) &&
                Objects.equals(testelapsedminutes, that.testelapsedminutes) &&
                Objects.equals(work, that.work) &&
                Objects.equals(worklist, that.worklist) &&
                Objects.equals(perfstats, that.perfstats) &&
                Objects.equals(pxtotalreqcpu, that.pxtotalreqcpu) &&
                Objects.equals(pxtotalreqtime, that.pxtotalreqtime) &&
                Objects.equals(pywallseconds, that.pywallseconds) &&
                Objects.equals(pxactivitycount, that.pxactivitycount) &&
                Objects.equals(pxalertcount, that.pxalertcount) &&
                Objects.equals(pxcommitcount, that.pxcommitcount) &&
                Objects.equals(pxcommitelapsed, that.pxcommitelapsed) &&
                Objects.equals(pxcommitrowcount, that.pxcommitrowcount) &&
                Objects.equals(pxothercount, that.pxothercount) &&
                Objects.equals(pxotheriocpu, that.pxotheriocpu) &&
                Objects.equals(pxotheriocount, that.pxotheriocount) &&
                Objects.equals(pxotherioelapsed, that.pxotherioelapsed) &&
                Objects.equals(pxrdbiocount, that.pxrdbiocount) &&
                Objects.equals(pxrdbioelapsed, that.pxrdbioelapsed) &&
                Objects.equals(jmeterResptimeAvg, that.jmeterResptimeAvg) &&
                Objects.equals(gctotalevents, that.gctotalevents) &&
                Objects.equals(gctotalduration, that.gctotalduration) &&
                Objects.equals(totaljitcompilationtime, that.totaljitcompilationtime) &&
                Objects.equals(performanceData, that.performanceData);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, testname, stat, testlabel, buildurl, failmessage, failcause, testcontroller, jvmHeapMemory, tomcatinstancecount, isga, isvalidrun, isofficial, javaversion, users, iterations, buildlabel, totallines, ignoredcount, errorcount, infocount, debugcount, warncount, exceptioncount, clientfails, application, teststart, testelapsedminutes, work, worklist, perfstats, pxtotalreqcpu, pxtotalreqtime, pywallseconds, pxactivitycount, pxalertcount, pxcommitcount, pxcommitelapsed, pxcommitrowcount, pxothercount, pxotheriocpu, pxotheriocount, pxotherioelapsed, pxrdbiocount, pxrdbioelapsed, jmeterResptimeAvg, gctotalevents, gctotalduration, totaljitcompilationtime, performanceData);
    }

    @Override
    public String toString() {
        return "FlatPerformanceTestsEntity{" +
                "id=" + id +
                ", testname='" + testname + '\'' +
                ", stat='" + stat + '\'' +
                ", testlabel='" + testlabel + '\'' +
                ", buildurl='" + buildurl + '\'' +
                ", failmessage='" + failmessage + '\'' +
                ", failcause='" + failcause + '\'' +
                ", testcontroller='" + testcontroller + '\'' +
                ", jvmHeapMemory='" + jvmHeapMemory + '\'' +
                ", tomcatinstancecount='" + tomcatinstancecount + '\'' +
                ", isga=" + isga +
                ", isvalidrun=" + isvalidrun +
                ", isofficial=" + isofficial +
                ", javaversion='" + javaversion + '\'' +
                ", users=" + users +
                ", iterations=" + iterations +
                ", buildlabel='" + buildlabel + '\'' +
                ", totallines=" + totallines +
                ", ignoredcount=" + ignoredcount +
                ", errorcount=" + errorcount +
                ", infocount=" + infocount +
                ", debugcount=" + debugcount +
                ", warncount=" + warncount +
                ", exceptioncount=" + exceptioncount +
                ", clientfails=" + clientfails +
                ", application='" + application + '\'' +
                ", teststart=" + teststart +
                ", testelapsedminutes=" + testelapsedminutes +
                ", work=" + work +
                ", worklist=" + worklist +
                ", perfstats=" + perfstats +
                ", pxtotalreqcpu=" + pxtotalreqcpu +
                ", pxtotalreqtime=" + pxtotalreqtime +
                ", pywallseconds=" + pywallseconds +
                ", pxactivitycount=" + pxactivitycount +
                ", pxalertcount=" + pxalertcount +
                ", pxcommitcount=" + pxcommitcount +
                ", pxcommitelapsed=" + pxcommitelapsed +
                ", pxcommitrowcount=" + pxcommitrowcount +
                ", pxothercount=" + pxothercount +
                ", pxotheriocpu=" + pxotheriocpu +
                ", pxotheriocount=" + pxotheriocount +
                ", pxotherioelapsed=" + pxotherioelapsed +
                ", pxrdbiocount=" + pxrdbiocount +
                ", pxrdbioelapsed=" + pxrdbioelapsed +
                ", jmeterResptimeAvg=" + jmeterResptimeAvg +
                ", gctotalevents=" + gctotalevents +
                ", gctotalduration=" + gctotalduration +
                ", totaljitcompilationtime=" + totaljitcompilationtime +
                ", performanceData='" + performanceData + '\'' +
                '}';
    }
}
