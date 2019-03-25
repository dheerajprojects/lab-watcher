package com.dheeraj.learning.labwatcher.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Table(name = "pr_data_performancestats")
public class PerfStat {
    private String testname;
    private String trialtype;
    private String runlevel;
    private Timestamp builddate;
    private String buildlabel;
    private String prpcversion;
    private Timestamp teststart;
    private Timestamp testend;
    private BigDecimal activitycount;
    private BigDecimal alertcount;
    private String application;
    private BigDecimal commitcount;
    private BigDecimal commitrowcount;
    private BigDecimal connectcount;
    private BigDecimal connectelapsed;
    private BigDecimal dbopexceedingthresholdcount;
    private BigDecimal declarativerulereadcount;
    private BigDecimal declarativeruleslookupcount;
    private BigDecimal flowcount;
    private BigDecimal infergeneratedjavacount;
    private BigDecimal infergeneratedjavaelapsed;
    private BigDecimal infergeneratedjavahlelapsed;
    private BigDecimal javaassemblecount;
    private BigDecimal javacompilecount;
    private BigDecimal javageneratecount;
    private BigDecimal javasyntaxcpu;
    private BigDecimal otheriocount;
    private BigDecimal otheriocpu;
    private BigDecimal otherioelapsed;
    private BigDecimal parserulecount;
    private BigDecimal processcpu;
    private BigDecimal rdbiocount;
    private BigDecimal rdbioelapsed;
    private BigDecimal rulecount;
    private BigDecimal rulefromcachecount;
    private BigDecimal ruleioelapsed;
    private BigDecimal rulesexecuted;
    private BigDecimal ruleused;
    private BigDecimal runotherrulecount;
    private BigDecimal runwhencount;
    private String systemname;
    private String systemnode;
    private String systemnodeid;
    private Timestamp systemstart;
    private BigDecimal totalreqcpu;
    private BigDecimal totalreqtime;
    private String pxobjclass;
    private String pzinskey;
    private String pxinsname;
    private BigDecimal javaassemblehlelapsed;
    private String buildinfo;
    private String prpcrelease;
    private BigDecimal buildnumber;
    private BigDecimal wallseconds;
    private BigDecimal testelapsedminutes;
    private BigDecimal errorcount;
    private Integer linecountdata;
    private Integer infocount;
    private String dbinputbytes;
    private String outputbytes;
    private String inputbytes;
    private String dboutputbytes;
    private String isga;
    private String isvalidrun;
    private String ispristinerun;
    private String teamemail;
    private String pristinebuildnumber;
    private String validationjobId;
    private BigDecimal gctotalevents;
    private BigDecimal gctotalmajor;
    private BigDecimal gctotalminor;
    private BigDecimal gctotalother;
    private BigDecimal gctotalduration;
    private BigDecimal gctotalgarbage;
    private BigDecimal gctotalprecollection;
    private BigDecimal gcmaxduration;
    private BigDecimal gcmaxgarbage;
    private BigDecimal gctotalpostcollection;
    private BigDecimal gcmaxpostcollection;
    private BigDecimal gcmaxprecollection;
    private String teamname;
    private BigDecimal loadedclasscount;
    private BigDecimal activationdatatimeelapsed;
    private BigDecimal commitelapsed;
    private BigDecimal declarativerulesinvokedcount;
    private BigDecimal declarativerulesinvokedcpu;
    private BigDecimal declarativeruleslookupcpu;
    private BigDecimal declexprctxfreeusecount;
    private BigDecimal declntwksbuildconstcpu;
    private BigDecimal declntwksbuildconstelapsed;
    private BigDecimal declntwksbuildhlcpu;
    private BigDecimal declntwksbuildhlelap;
    private BigDecimal declrulesinvokedelapsed;
    private BigDecimal declruleslookupelapsed;
    private BigDecimal indexcount;
    private BigDecimal infergeneratedjavacountcpu;
    private BigDecimal interactions;
    private BigDecimal javaassemblecpu;
    private BigDecimal javaassembleelapsed;
    private BigDecimal javacompilecpu;
    private BigDecimal javacompileelapsed;
    private BigDecimal javageneratecpu;
    private BigDecimal javagenerateelapsed;
    private BigDecimal legacyruleapiusedcount;
    private BigDecimal listrowwithunfilteredstrmcnt;
    private BigDecimal listwithunfilteredstrmcnt;
    private BigDecimal lookuplistdbfetches;
    private BigDecimal otherbrowsecpu;
    private BigDecimal otherbrowseelapsed;
    private BigDecimal othercount;
    private BigDecimal otherfromcachecount;
    private BigDecimal proceduralrulereadcount;
    private BigDecimal rdbrowwithoutstreamcount;
    private BigDecimal rdbwithoutstreamcount;
    private BigDecimal rulebrowsecpu;
    private BigDecimal rulebrowseelapsed;
    private BigDecimal rulecpu;
    private BigDecimal runmodelcount;
    private BigDecimal runstreamcount;
    private BigDecimal peakthreadcount;
    private BigDecimal threadcount;
    private BigDecimal totalloadedclasscount;
    private BigDecimal unloadedclasscount;
    private BigDecimal totalstartedthreadcount;
    private BigDecimal infergeneratedjavacpu;
    private String testmode;
    private String commitid;
    private String mprid;
    private String testrunstatus;

    @Basic
    @Column(name = "testname", nullable = false, length = 32)
    public String getTestname() {
        return testname;
    }

    public void setTestname(String testname) {
        this.testname = testname;
    }

    @Basic
    @Column(name = "trialtype", nullable = false, length = 32)
    public String getTrialtype() {
        return trialtype;
    }

    public void setTrialtype(String trialtype) {
        this.trialtype = trialtype;
    }

    @Basic
    @Column(name = "runlevel", nullable = false, length = 32)
    public String getRunlevel() {
        return runlevel;
    }

    public void setRunlevel(String runlevel) {
        this.runlevel = runlevel;
    }

    @Basic
    @Column(name = "builddate", nullable = false)
    public Timestamp getBuilddate() {
        return builddate;
    }

    public void setBuilddate(Timestamp builddate) {
        this.builddate = builddate;
    }

    @Basic
    @Column(name = "buildlabel", nullable = false, length = 128)
    public String getBuildlabel() {
        return buildlabel;
    }

    public void setBuildlabel(String buildlabel) {
        this.buildlabel = buildlabel;
    }

    @Basic
    @Column(name = "prpcversion", nullable = false, length = 128)
    public String getPrpcversion() {
        return prpcversion;
    }

    public void setPrpcversion(String prpcversion) {
        this.prpcversion = prpcversion;
    }

    @Basic
    @Column(name = "teststart", nullable = false)
    public Timestamp getTeststart() {
        return teststart;
    }

    public void setTeststart(Timestamp teststart) {
        this.teststart = teststart;
    }

    @Basic
    @Column(name = "testend", nullable = false)
    public Timestamp getTestend() {
        return testend;
    }

    public void setTestend(Timestamp testend) {
        this.testend = testend;
    }

    @Basic
    @Column(name = "activitycount", nullable = true, precision = 2)
    public BigDecimal getActivitycount() {
        return activitycount;
    }

    public void setActivitycount(BigDecimal activitycount) {
        this.activitycount = activitycount;
    }

    @Basic
    @Column(name = "alertcount", nullable = true, precision = 2)
    public BigDecimal getAlertcount() {
        return alertcount;
    }

    public void setAlertcount(BigDecimal alertcount) {
        this.alertcount = alertcount;
    }

    @Basic
    @Column(name = "application", nullable = false, length = 128)
    public String getApplication() {
        return application;
    }

    public void setApplication(String application) {
        this.application = application;
    }

    @Basic
    @Column(name = "commitcount", nullable = true, precision = 2)
    public BigDecimal getCommitcount() {
        return commitcount;
    }

    public void setCommitcount(BigDecimal commitcount) {
        this.commitcount = commitcount;
    }

    @Basic
    @Column(name = "commitrowcount", nullable = true, precision = 2)
    public BigDecimal getCommitrowcount() {
        return commitrowcount;
    }

    public void setCommitrowcount(BigDecimal commitrowcount) {
        this.commitrowcount = commitrowcount;
    }

    @Basic
    @Column(name = "connectcount", nullable = true, precision = 2)
    public BigDecimal getConnectcount() {
        return connectcount;
    }

    public void setConnectcount(BigDecimal connectcount) {
        this.connectcount = connectcount;
    }

    @Basic
    @Column(name = "connectelapsed", nullable = true, precision = 2)
    public BigDecimal getConnectelapsed() {
        return connectelapsed;
    }

    public void setConnectelapsed(BigDecimal connectelapsed) {
        this.connectelapsed = connectelapsed;
    }

    @Basic
    @Column(name = "dbopexceedingthresholdcount", nullable = true, precision = 2)
    public BigDecimal getDbopexceedingthresholdcount() {
        return dbopexceedingthresholdcount;
    }

    public void setDbopexceedingthresholdcount(BigDecimal dbopexceedingthresholdcount) {
        this.dbopexceedingthresholdcount = dbopexceedingthresholdcount;
    }

    @Basic
    @Column(name = "declarativerulereadcount", nullable = true, precision = 2)
    public BigDecimal getDeclarativerulereadcount() {
        return declarativerulereadcount;
    }

    public void setDeclarativerulereadcount(BigDecimal declarativerulereadcount) {
        this.declarativerulereadcount = declarativerulereadcount;
    }

    @Basic
    @Column(name = "declarativeruleslookupcount", nullable = true, precision = 1)
    public BigDecimal getDeclarativeruleslookupcount() {
        return declarativeruleslookupcount;
    }

    public void setDeclarativeruleslookupcount(BigDecimal declarativeruleslookupcount) {
        this.declarativeruleslookupcount = declarativeruleslookupcount;
    }

    @Basic
    @Column(name = "flowcount", nullable = true, precision = 1)
    public BigDecimal getFlowcount() {
        return flowcount;
    }

    public void setFlowcount(BigDecimal flowcount) {
        this.flowcount = flowcount;
    }

    @Basic
    @Column(name = "infergeneratedjavacount", nullable = true, precision = 2)
    public BigDecimal getInfergeneratedjavacount() {
        return infergeneratedjavacount;
    }

    public void setInfergeneratedjavacount(BigDecimal infergeneratedjavacount) {
        this.infergeneratedjavacount = infergeneratedjavacount;
    }

    @Basic
    @Column(name = "infergeneratedjavaelapsed", nullable = true, precision = 2)
    public BigDecimal getInfergeneratedjavaelapsed() {
        return infergeneratedjavaelapsed;
    }

    public void setInfergeneratedjavaelapsed(BigDecimal infergeneratedjavaelapsed) {
        this.infergeneratedjavaelapsed = infergeneratedjavaelapsed;
    }

    @Basic
    @Column(name = "infergeneratedjavahlelapsed", nullable = true, precision = 2)
    public BigDecimal getInfergeneratedjavahlelapsed() {
        return infergeneratedjavahlelapsed;
    }

    public void setInfergeneratedjavahlelapsed(BigDecimal infergeneratedjavahlelapsed) {
        this.infergeneratedjavahlelapsed = infergeneratedjavahlelapsed;
    }

    @Basic
    @Column(name = "javaassemblecount", nullable = true, precision = 1)
    public BigDecimal getJavaassemblecount() {
        return javaassemblecount;
    }

    public void setJavaassemblecount(BigDecimal javaassemblecount) {
        this.javaassemblecount = javaassemblecount;
    }

    @Basic
    @Column(name = "javacompilecount", nullable = true, precision = 1)
    public BigDecimal getJavacompilecount() {
        return javacompilecount;
    }

    public void setJavacompilecount(BigDecimal javacompilecount) {
        this.javacompilecount = javacompilecount;
    }

    @Basic
    @Column(name = "javageneratecount", nullable = true, precision = 1)
    public BigDecimal getJavageneratecount() {
        return javageneratecount;
    }

    public void setJavageneratecount(BigDecimal javageneratecount) {
        this.javageneratecount = javageneratecount;
    }

    @Basic
    @Column(name = "javasyntaxcpu", nullable = true, precision = 2)
    public BigDecimal getJavasyntaxcpu() {
        return javasyntaxcpu;
    }

    public void setJavasyntaxcpu(BigDecimal javasyntaxcpu) {
        this.javasyntaxcpu = javasyntaxcpu;
    }

    @Basic
    @Column(name = "otheriocount", nullable = true, precision = 1)
    public BigDecimal getOtheriocount() {
        return otheriocount;
    }

    public void setOtheriocount(BigDecimal otheriocount) {
        this.otheriocount = otheriocount;
    }

    @Basic
    @Column(name = "otheriocpu", nullable = true, precision = 2)
    public BigDecimal getOtheriocpu() {
        return otheriocpu;
    }

    public void setOtheriocpu(BigDecimal otheriocpu) {
        this.otheriocpu = otheriocpu;
    }

    @Basic
    @Column(name = "otherioelapsed", nullable = true, precision = 6)
    public BigDecimal getOtherioelapsed() {
        return otherioelapsed;
    }

    public void setOtherioelapsed(BigDecimal otherioelapsed) {
        this.otherioelapsed = otherioelapsed;
    }

    @Basic
    @Column(name = "parserulecount", nullable = true, precision = 1)
    public BigDecimal getParserulecount() {
        return parserulecount;
    }

    public void setParserulecount(BigDecimal parserulecount) {
        this.parserulecount = parserulecount;
    }

    @Basic
    @Column(name = "processcpu", nullable = true, precision = 2)
    public BigDecimal getProcesscpu() {
        return processcpu;
    }

    public void setProcesscpu(BigDecimal processcpu) {
        this.processcpu = processcpu;
    }

    @Basic
    @Column(name = "rdbiocount", nullable = true, precision = 1)
    public BigDecimal getRdbiocount() {
        return rdbiocount;
    }

    public void setRdbiocount(BigDecimal rdbiocount) {
        this.rdbiocount = rdbiocount;
    }

    @Basic
    @Column(name = "rdbioelapsed", nullable = true, precision = 2)
    public BigDecimal getRdbioelapsed() {
        return rdbioelapsed;
    }

    public void setRdbioelapsed(BigDecimal rdbioelapsed) {
        this.rdbioelapsed = rdbioelapsed;
    }

    @Basic
    @Column(name = "rulecount", nullable = true, precision = 1)
    public BigDecimal getRulecount() {
        return rulecount;
    }

    public void setRulecount(BigDecimal rulecount) {
        this.rulecount = rulecount;
    }

    @Basic
    @Column(name = "rulefromcachecount", nullable = true, precision = 1)
    public BigDecimal getRulefromcachecount() {
        return rulefromcachecount;
    }

    public void setRulefromcachecount(BigDecimal rulefromcachecount) {
        this.rulefromcachecount = rulefromcachecount;
    }

    @Basic
    @Column(name = "ruleioelapsed", nullable = true, precision = 2)
    public BigDecimal getRuleioelapsed() {
        return ruleioelapsed;
    }

    public void setRuleioelapsed(BigDecimal ruleioelapsed) {
        this.ruleioelapsed = ruleioelapsed;
    }

    @Basic
    @Column(name = "rulesexecuted", nullable = true, precision = 1)
    public BigDecimal getRulesexecuted() {
        return rulesexecuted;
    }

    public void setRulesexecuted(BigDecimal rulesexecuted) {
        this.rulesexecuted = rulesexecuted;
    }

    @Basic
    @Column(name = "ruleused", nullable = true, precision = 2)
    public BigDecimal getRuleused() {
        return ruleused;
    }

    public void setRuleused(BigDecimal ruleused) {
        this.ruleused = ruleused;
    }

    @Basic
    @Column(name = "runotherrulecount", nullable = true, precision = 1)
    public BigDecimal getRunotherrulecount() {
        return runotherrulecount;
    }

    public void setRunotherrulecount(BigDecimal runotherrulecount) {
        this.runotherrulecount = runotherrulecount;
    }

    @Basic
    @Column(name = "runwhencount", nullable = true, precision = 1)
    public BigDecimal getRunwhencount() {
        return runwhencount;
    }

    public void setRunwhencount(BigDecimal runwhencount) {
        this.runwhencount = runwhencount;
    }

    @Basic
    @Column(name = "systemname", nullable = false, length = 128)
    public String getSystemname() {
        return systemname;
    }

    public void setSystemname(String systemname) {
        this.systemname = systemname;
    }

    @Basic
    @Column(name = "systemnode", nullable = true, length = 128)
    public String getSystemnode() {
        return systemnode;
    }

    public void setSystemnode(String systemnode) {
        this.systemnode = systemnode;
    }

    @Basic
    @Column(name = "systemnodeid", nullable = true, length = 128)
    public String getSystemnodeid() {
        return systemnodeid;
    }

    public void setSystemnodeid(String systemnodeid) {
        this.systemnodeid = systemnodeid;
    }

    @Basic
    @Column(name = "systemstart", nullable = true)
    public Timestamp getSystemstart() {
        return systemstart;
    }

    public void setSystemstart(Timestamp systemstart) {
        this.systemstart = systemstart;
    }

    @Basic
    @Column(name = "totalreqcpu", nullable = true, precision = 2)
    public BigDecimal getTotalreqcpu() {
        return totalreqcpu;
    }

    public void setTotalreqcpu(BigDecimal totalreqcpu) {
        this.totalreqcpu = totalreqcpu;
    }

    @Basic
    @Column(name = "totalreqtime", nullable = true, precision = 2)
    public BigDecimal getTotalreqtime() {
        return totalreqtime;
    }

    public void setTotalreqtime(BigDecimal totalreqtime) {
        this.totalreqtime = totalreqtime;
    }

    @Basic
    @Column(name = "pxobjclass", nullable = false, length = 96)
    public String getPxobjclass() {
        return pxobjclass;
    }

    public void setPxobjclass(String pxobjclass) {
        this.pxobjclass = pxobjclass;
    }

    @Id
    @Column(name = "pzinskey", nullable = false, length = 255)
    public String getPzinskey() {
        return pzinskey;
    }

    public void setPzinskey(String pzinskey) {
        this.pzinskey = pzinskey;
    }

    @Basic
    @Column(name = "pxinsname", nullable = false, length = 255)
    public String getPxinsname() {
        return pxinsname;
    }

    public void setPxinsname(String pxinsname) {
        this.pxinsname = pxinsname;
    }

    @Basic
    @Column(name = "javaassemblehlelapsed", nullable = true, precision = 2)
    public BigDecimal getJavaassemblehlelapsed() {
        return javaassemblehlelapsed;
    }

    public void setJavaassemblehlelapsed(BigDecimal javaassemblehlelapsed) {
        this.javaassemblehlelapsed = javaassemblehlelapsed;
    }

    @Basic
    @Column(name = "buildinfo", nullable = false, length = 50)
    public String getBuildinfo() {
        return buildinfo;
    }

    public void setBuildinfo(String buildinfo) {
        this.buildinfo = buildinfo;
    }

    @Basic
    @Column(name = "prpcrelease", nullable = true, length = 50)
    public String getPrpcrelease() {
        return prpcrelease;
    }

    public void setPrpcrelease(String prpcrelease) {
        this.prpcrelease = prpcrelease;
    }

    @Basic
    @Column(name = "buildnumber", nullable = true, precision = 2)
    public BigDecimal getBuildnumber() {
        return buildnumber;
    }

    public void setBuildnumber(BigDecimal buildnumber) {
        this.buildnumber = buildnumber;
    }

    @Basic
    @Column(name = "wallseconds", nullable = true, precision = 2)
    public BigDecimal getWallseconds() {
        return wallseconds;
    }

    public void setWallseconds(BigDecimal wallseconds) {
        this.wallseconds = wallseconds;
    }

    @Basic
    @Column(name = "testelapsedminutes", nullable = true, precision = 2)
    public BigDecimal getTestelapsedminutes() {
        return testelapsedminutes;
    }

    public void setTestelapsedminutes(BigDecimal testelapsedminutes) {
        this.testelapsedminutes = testelapsedminutes;
    }

    @Basic
    @Column(name = "errorcount", nullable = true, precision = 1)
    public BigDecimal getErrorcount() {
        return errorcount;
    }

    public void setErrorcount(BigDecimal errorcount) {
        this.errorcount = errorcount;
    }

    @Basic
    @Column(name = "linecountdata", nullable = true, precision = 0)
    public Integer getLinecountdata() {
        return linecountdata;
    }

    public void setLinecountdata(Integer linecountdata) {
        this.linecountdata = linecountdata;
    }

    @Basic
    @Column(name = "infocount", nullable = true, precision = 0)
    public Integer getInfocount() {
        return infocount;
    }

    public void setInfocount(Integer infocount) {
        this.infocount = infocount;
    }

    @Basic
    @Column(name = "dbinputbytes", nullable = true, length = 30)
    public String getDbinputbytes() {
        return dbinputbytes;
    }

    public void setDbinputbytes(String dbinputbytes) {
        this.dbinputbytes = dbinputbytes;
    }

    @Basic
    @Column(name = "outputbytes", nullable = true, length = 30)
    public String getOutputbytes() {
        return outputbytes;
    }

    public void setOutputbytes(String outputbytes) {
        this.outputbytes = outputbytes;
    }

    @Basic
    @Column(name = "inputbytes", nullable = true, length = 30)
    public String getInputbytes() {
        return inputbytes;
    }

    public void setInputbytes(String inputbytes) {
        this.inputbytes = inputbytes;
    }

    @Basic
    @Column(name = "dboutputbytes", nullable = true, length = 30)
    public String getDboutputbytes() {
        return dboutputbytes;
    }

    public void setDboutputbytes(String dboutputbytes) {
        this.dboutputbytes = dboutputbytes;
    }

    @Basic
    @Column(name = "isga", nullable = true, length = 5)
    public String getIsga() {
        return isga;
    }

    public void setIsga(String isga) {
        this.isga = isga;
    }

    @Basic
    @Column(name = "isvalidrun", nullable = true, length = 5)
    public String getIsvalidrun() {
        return isvalidrun;
    }

    public void setIsvalidrun(String isvalidrun) {
        this.isvalidrun = isvalidrun;
    }

    @Basic
    @Column(name = "ispristinerun", nullable = true, length = 5)
    public String getIspristinerun() {
        return ispristinerun;
    }

    public void setIspristinerun(String ispristinerun) {
        this.ispristinerun = ispristinerun;
    }

    @Basic
    @Column(name = "teamemail", nullable = true, length = 50)
    public String getTeamemail() {
        return teamemail;
    }

    public void setTeamemail(String teamemail) {
        this.teamemail = teamemail;
    }

    @Basic
    @Column(name = "pristinebuildnumber", nullable = true, length = 128)
    public String getPristinebuildnumber() {
        return pristinebuildnumber;
    }

    public void setPristinebuildnumber(String pristinebuildnumber) {
        this.pristinebuildnumber = pristinebuildnumber;
    }

    @Basic
    @Column(name = "validationjob_id", nullable = true, length = 50)
    public String getValidationjobId() {
        return validationjobId;
    }

    public void setValidationjobId(String validationjobId) {
        this.validationjobId = validationjobId;
    }

    @Basic
    @Column(name = "gctotalevents", nullable = true, precision = 2)
    public BigDecimal getGctotalevents() {
        return gctotalevents;
    }

    public void setGctotalevents(BigDecimal gctotalevents) {
        this.gctotalevents = gctotalevents;
    }

    @Basic
    @Column(name = "gctotalmajor", nullable = true, precision = 2)
    public BigDecimal getGctotalmajor() {
        return gctotalmajor;
    }

    public void setGctotalmajor(BigDecimal gctotalmajor) {
        this.gctotalmajor = gctotalmajor;
    }

    @Basic
    @Column(name = "gctotalminor", nullable = true, precision = 2)
    public BigDecimal getGctotalminor() {
        return gctotalminor;
    }

    public void setGctotalminor(BigDecimal gctotalminor) {
        this.gctotalminor = gctotalminor;
    }

    @Basic
    @Column(name = "gctotalother", nullable = true, precision = 2)
    public BigDecimal getGctotalother() {
        return gctotalother;
    }

    public void setGctotalother(BigDecimal gctotalother) {
        this.gctotalother = gctotalother;
    }

    @Basic
    @Column(name = "gctotalduration", nullable = true, precision = 2)
    public BigDecimal getGctotalduration() {
        return gctotalduration;
    }

    public void setGctotalduration(BigDecimal gctotalduration) {
        this.gctotalduration = gctotalduration;
    }

    @Basic
    @Column(name = "gctotalgarbage", nullable = true, precision = 2)
    public BigDecimal getGctotalgarbage() {
        return gctotalgarbage;
    }

    public void setGctotalgarbage(BigDecimal gctotalgarbage) {
        this.gctotalgarbage = gctotalgarbage;
    }

    @Basic
    @Column(name = "gctotalprecollection", nullable = true, precision = 2)
    public BigDecimal getGctotalprecollection() {
        return gctotalprecollection;
    }

    public void setGctotalprecollection(BigDecimal gctotalprecollection) {
        this.gctotalprecollection = gctotalprecollection;
    }

    @Basic
    @Column(name = "gcmaxduration", nullable = true, precision = 2)
    public BigDecimal getGcmaxduration() {
        return gcmaxduration;
    }

    public void setGcmaxduration(BigDecimal gcmaxduration) {
        this.gcmaxduration = gcmaxduration;
    }

    @Basic
    @Column(name = "gcmaxgarbage", nullable = true, precision = 2)
    public BigDecimal getGcmaxgarbage() {
        return gcmaxgarbage;
    }

    public void setGcmaxgarbage(BigDecimal gcmaxgarbage) {
        this.gcmaxgarbage = gcmaxgarbage;
    }

    @Basic
    @Column(name = "gctotalpostcollection", nullable = true, precision = 2)
    public BigDecimal getGctotalpostcollection() {
        return gctotalpostcollection;
    }

    public void setGctotalpostcollection(BigDecimal gctotalpostcollection) {
        this.gctotalpostcollection = gctotalpostcollection;
    }

    @Basic
    @Column(name = "gcmaxpostcollection", nullable = true, precision = 2)
    public BigDecimal getGcmaxpostcollection() {
        return gcmaxpostcollection;
    }

    public void setGcmaxpostcollection(BigDecimal gcmaxpostcollection) {
        this.gcmaxpostcollection = gcmaxpostcollection;
    }

    @Basic
    @Column(name = "gcmaxprecollection", nullable = true, precision = 2)
    public BigDecimal getGcmaxprecollection() {
        return gcmaxprecollection;
    }

    public void setGcmaxprecollection(BigDecimal gcmaxprecollection) {
        this.gcmaxprecollection = gcmaxprecollection;
    }

    @Basic
    @Column(name = "teamname", nullable = true, length = 50)
    public String getTeamname() {
        return teamname;
    }

    public void setTeamname(String teamname) {
        this.teamname = teamname;
    }

    @Basic
    @Column(name = "loadedclasscount", nullable = true, precision = 2)
    public BigDecimal getLoadedclasscount() {
        return loadedclasscount;
    }

    public void setLoadedclasscount(BigDecimal loadedclasscount) {
        this.loadedclasscount = loadedclasscount;
    }

    @Basic
    @Column(name = "activationdatatimeelapsed", nullable = true, precision = 2)
    public BigDecimal getActivationdatatimeelapsed() {
        return activationdatatimeelapsed;
    }

    public void setActivationdatatimeelapsed(BigDecimal activationdatatimeelapsed) {
        this.activationdatatimeelapsed = activationdatatimeelapsed;
    }

    @Basic
    @Column(name = "commitelapsed", nullable = true, precision = 2)
    public BigDecimal getCommitelapsed() {
        return commitelapsed;
    }

    public void setCommitelapsed(BigDecimal commitelapsed) {
        this.commitelapsed = commitelapsed;
    }

    @Basic
    @Column(name = "declarativerulesinvokedcount", nullable = true, precision = 2)
    public BigDecimal getDeclarativerulesinvokedcount() {
        return declarativerulesinvokedcount;
    }

    public void setDeclarativerulesinvokedcount(BigDecimal declarativerulesinvokedcount) {
        this.declarativerulesinvokedcount = declarativerulesinvokedcount;
    }

    @Basic
    @Column(name = "declarativerulesinvokedcpu", nullable = true, precision = 2)
    public BigDecimal getDeclarativerulesinvokedcpu() {
        return declarativerulesinvokedcpu;
    }

    public void setDeclarativerulesinvokedcpu(BigDecimal declarativerulesinvokedcpu) {
        this.declarativerulesinvokedcpu = declarativerulesinvokedcpu;
    }

    @Basic
    @Column(name = "declarativeruleslookupcpu", nullable = true, precision = 2)
    public BigDecimal getDeclarativeruleslookupcpu() {
        return declarativeruleslookupcpu;
    }

    public void setDeclarativeruleslookupcpu(BigDecimal declarativeruleslookupcpu) {
        this.declarativeruleslookupcpu = declarativeruleslookupcpu;
    }

    @Basic
    @Column(name = "declexprctxfreeusecount", nullable = true, precision = 2)
    public BigDecimal getDeclexprctxfreeusecount() {
        return declexprctxfreeusecount;
    }

    public void setDeclexprctxfreeusecount(BigDecimal declexprctxfreeusecount) {
        this.declexprctxfreeusecount = declexprctxfreeusecount;
    }

    @Basic
    @Column(name = "declntwksbuildconstcpu", nullable = true, precision = 2)
    public BigDecimal getDeclntwksbuildconstcpu() {
        return declntwksbuildconstcpu;
    }

    public void setDeclntwksbuildconstcpu(BigDecimal declntwksbuildconstcpu) {
        this.declntwksbuildconstcpu = declntwksbuildconstcpu;
    }

    @Basic
    @Column(name = "declntwksbuildconstelapsed", nullable = true, precision = 2)
    public BigDecimal getDeclntwksbuildconstelapsed() {
        return declntwksbuildconstelapsed;
    }

    public void setDeclntwksbuildconstelapsed(BigDecimal declntwksbuildconstelapsed) {
        this.declntwksbuildconstelapsed = declntwksbuildconstelapsed;
    }

    @Basic
    @Column(name = "declntwksbuildhlcpu", nullable = true, precision = 2)
    public BigDecimal getDeclntwksbuildhlcpu() {
        return declntwksbuildhlcpu;
    }

    public void setDeclntwksbuildhlcpu(BigDecimal declntwksbuildhlcpu) {
        this.declntwksbuildhlcpu = declntwksbuildhlcpu;
    }

    @Basic
    @Column(name = "declntwksbuildhlelap", nullable = true, precision = 2)
    public BigDecimal getDeclntwksbuildhlelap() {
        return declntwksbuildhlelap;
    }

    public void setDeclntwksbuildhlelap(BigDecimal declntwksbuildhlelap) {
        this.declntwksbuildhlelap = declntwksbuildhlelap;
    }

    @Basic
    @Column(name = "declrulesinvokedelapsed", nullable = true, precision = 2)
    public BigDecimal getDeclrulesinvokedelapsed() {
        return declrulesinvokedelapsed;
    }

    public void setDeclrulesinvokedelapsed(BigDecimal declrulesinvokedelapsed) {
        this.declrulesinvokedelapsed = declrulesinvokedelapsed;
    }

    @Basic
    @Column(name = "declruleslookupelapsed", nullable = true, precision = 2)
    public BigDecimal getDeclruleslookupelapsed() {
        return declruleslookupelapsed;
    }

    public void setDeclruleslookupelapsed(BigDecimal declruleslookupelapsed) {
        this.declruleslookupelapsed = declruleslookupelapsed;
    }

    @Basic
    @Column(name = "indexcount", nullable = true, precision = 2)
    public BigDecimal getIndexcount() {
        return indexcount;
    }

    public void setIndexcount(BigDecimal indexcount) {
        this.indexcount = indexcount;
    }

    @Basic
    @Column(name = "infergeneratedjavacountcpu", nullable = true, precision = 2)
    public BigDecimal getInfergeneratedjavacountcpu() {
        return infergeneratedjavacountcpu;
    }

    public void setInfergeneratedjavacountcpu(BigDecimal infergeneratedjavacountcpu) {
        this.infergeneratedjavacountcpu = infergeneratedjavacountcpu;
    }

    @Basic
    @Column(name = "interactions", nullable = true, precision = 2)
    public BigDecimal getInteractions() {
        return interactions;
    }

    public void setInteractions(BigDecimal interactions) {
        this.interactions = interactions;
    }

    @Basic
    @Column(name = "javaassemblecpu", nullable = true, precision = 2)
    public BigDecimal getJavaassemblecpu() {
        return javaassemblecpu;
    }

    public void setJavaassemblecpu(BigDecimal javaassemblecpu) {
        this.javaassemblecpu = javaassemblecpu;
    }

    @Basic
    @Column(name = "javaassembleelapsed", nullable = true, precision = 2)
    public BigDecimal getJavaassembleelapsed() {
        return javaassembleelapsed;
    }

    public void setJavaassembleelapsed(BigDecimal javaassembleelapsed) {
        this.javaassembleelapsed = javaassembleelapsed;
    }

    @Basic
    @Column(name = "javacompilecpu", nullable = true, precision = 2)
    public BigDecimal getJavacompilecpu() {
        return javacompilecpu;
    }

    public void setJavacompilecpu(BigDecimal javacompilecpu) {
        this.javacompilecpu = javacompilecpu;
    }

    @Basic
    @Column(name = "javacompileelapsed", nullable = true, precision = 2)
    public BigDecimal getJavacompileelapsed() {
        return javacompileelapsed;
    }

    public void setJavacompileelapsed(BigDecimal javacompileelapsed) {
        this.javacompileelapsed = javacompileelapsed;
    }

    @Basic
    @Column(name = "javageneratecpu", nullable = true, precision = 2)
    public BigDecimal getJavageneratecpu() {
        return javageneratecpu;
    }

    public void setJavageneratecpu(BigDecimal javageneratecpu) {
        this.javageneratecpu = javageneratecpu;
    }

    @Basic
    @Column(name = "javagenerateelapsed", nullable = true, precision = 2)
    public BigDecimal getJavagenerateelapsed() {
        return javagenerateelapsed;
    }

    public void setJavagenerateelapsed(BigDecimal javagenerateelapsed) {
        this.javagenerateelapsed = javagenerateelapsed;
    }

    @Basic
    @Column(name = "legacyruleapiusedcount", nullable = true, precision = 2)
    public BigDecimal getLegacyruleapiusedcount() {
        return legacyruleapiusedcount;
    }

    public void setLegacyruleapiusedcount(BigDecimal legacyruleapiusedcount) {
        this.legacyruleapiusedcount = legacyruleapiusedcount;
    }

    @Basic
    @Column(name = "listrowwithunfilteredstrmcnt", nullable = true, precision = 2)
    public BigDecimal getListrowwithunfilteredstrmcnt() {
        return listrowwithunfilteredstrmcnt;
    }

    public void setListrowwithunfilteredstrmcnt(BigDecimal listrowwithunfilteredstrmcnt) {
        this.listrowwithunfilteredstrmcnt = listrowwithunfilteredstrmcnt;
    }

    @Basic
    @Column(name = "listwithunfilteredstrmcnt", nullable = true, precision = 2)
    public BigDecimal getListwithunfilteredstrmcnt() {
        return listwithunfilteredstrmcnt;
    }

    public void setListwithunfilteredstrmcnt(BigDecimal listwithunfilteredstrmcnt) {
        this.listwithunfilteredstrmcnt = listwithunfilteredstrmcnt;
    }

    @Basic
    @Column(name = "lookuplistdbfetches", nullable = true, precision = 2)
    public BigDecimal getLookuplistdbfetches() {
        return lookuplistdbfetches;
    }

    public void setLookuplistdbfetches(BigDecimal lookuplistdbfetches) {
        this.lookuplistdbfetches = lookuplistdbfetches;
    }

    @Basic
    @Column(name = "otherbrowsecpu", nullable = true, precision = 2)
    public BigDecimal getOtherbrowsecpu() {
        return otherbrowsecpu;
    }

    public void setOtherbrowsecpu(BigDecimal otherbrowsecpu) {
        this.otherbrowsecpu = otherbrowsecpu;
    }

    @Basic
    @Column(name = "otherbrowseelapsed", nullable = true, precision = 2)
    public BigDecimal getOtherbrowseelapsed() {
        return otherbrowseelapsed;
    }

    public void setOtherbrowseelapsed(BigDecimal otherbrowseelapsed) {
        this.otherbrowseelapsed = otherbrowseelapsed;
    }

    @Basic
    @Column(name = "othercount", nullable = true, precision = 2)
    public BigDecimal getOthercount() {
        return othercount;
    }

    public void setOthercount(BigDecimal othercount) {
        this.othercount = othercount;
    }

    @Basic
    @Column(name = "otherfromcachecount", nullable = true, precision = 2)
    public BigDecimal getOtherfromcachecount() {
        return otherfromcachecount;
    }

    public void setOtherfromcachecount(BigDecimal otherfromcachecount) {
        this.otherfromcachecount = otherfromcachecount;
    }

    @Basic
    @Column(name = "proceduralrulereadcount", nullable = true, precision = 2)
    public BigDecimal getProceduralrulereadcount() {
        return proceduralrulereadcount;
    }

    public void setProceduralrulereadcount(BigDecimal proceduralrulereadcount) {
        this.proceduralrulereadcount = proceduralrulereadcount;
    }

    @Basic
    @Column(name = "rdbrowwithoutstreamcount", nullable = true, precision = 2)
    public BigDecimal getRdbrowwithoutstreamcount() {
        return rdbrowwithoutstreamcount;
    }

    public void setRdbrowwithoutstreamcount(BigDecimal rdbrowwithoutstreamcount) {
        this.rdbrowwithoutstreamcount = rdbrowwithoutstreamcount;
    }

    @Basic
    @Column(name = "rdbwithoutstreamcount", nullable = true, precision = 2)
    public BigDecimal getRdbwithoutstreamcount() {
        return rdbwithoutstreamcount;
    }

    public void setRdbwithoutstreamcount(BigDecimal rdbwithoutstreamcount) {
        this.rdbwithoutstreamcount = rdbwithoutstreamcount;
    }

    @Basic
    @Column(name = "rulebrowsecpu", nullable = true, precision = 2)
    public BigDecimal getRulebrowsecpu() {
        return rulebrowsecpu;
    }

    public void setRulebrowsecpu(BigDecimal rulebrowsecpu) {
        this.rulebrowsecpu = rulebrowsecpu;
    }

    @Basic
    @Column(name = "rulebrowseelapsed", nullable = true, precision = 2)
    public BigDecimal getRulebrowseelapsed() {
        return rulebrowseelapsed;
    }

    public void setRulebrowseelapsed(BigDecimal rulebrowseelapsed) {
        this.rulebrowseelapsed = rulebrowseelapsed;
    }

    @Basic
    @Column(name = "rulecpu", nullable = true, precision = 2)
    public BigDecimal getRulecpu() {
        return rulecpu;
    }

    public void setRulecpu(BigDecimal rulecpu) {
        this.rulecpu = rulecpu;
    }

    @Basic
    @Column(name = "runmodelcount", nullable = true, precision = 2)
    public BigDecimal getRunmodelcount() {
        return runmodelcount;
    }

    public void setRunmodelcount(BigDecimal runmodelcount) {
        this.runmodelcount = runmodelcount;
    }

    @Basic
    @Column(name = "runstreamcount", nullable = true, precision = 2)
    public BigDecimal getRunstreamcount() {
        return runstreamcount;
    }

    public void setRunstreamcount(BigDecimal runstreamcount) {
        this.runstreamcount = runstreamcount;
    }

    @Basic
    @Column(name = "peakthreadcount", nullable = true, precision = 2)
    public BigDecimal getPeakthreadcount() {
        return peakthreadcount;
    }

    public void setPeakthreadcount(BigDecimal peakthreadcount) {
        this.peakthreadcount = peakthreadcount;
    }

    @Basic
    @Column(name = "threadcount", nullable = true, precision = 2)
    public BigDecimal getThreadcount() {
        return threadcount;
    }

    public void setThreadcount(BigDecimal threadcount) {
        this.threadcount = threadcount;
    }

    @Basic
    @Column(name = "totalloadedclasscount", nullable = true, precision = 2)
    public BigDecimal getTotalloadedclasscount() {
        return totalloadedclasscount;
    }

    public void setTotalloadedclasscount(BigDecimal totalloadedclasscount) {
        this.totalloadedclasscount = totalloadedclasscount;
    }

    @Basic
    @Column(name = "unloadedclasscount", nullable = true, precision = 2)
    public BigDecimal getUnloadedclasscount() {
        return unloadedclasscount;
    }

    public void setUnloadedclasscount(BigDecimal unloadedclasscount) {
        this.unloadedclasscount = unloadedclasscount;
    }

    @Basic
    @Column(name = "totalstartedthreadcount", nullable = true, precision = 2)
    public BigDecimal getTotalstartedthreadcount() {
        return totalstartedthreadcount;
    }

    public void setTotalstartedthreadcount(BigDecimal totalstartedthreadcount) {
        this.totalstartedthreadcount = totalstartedthreadcount;
    }

    @Basic
    @Column(name = "infergeneratedjavacpu", nullable = true, precision = 2)
    public BigDecimal getInfergeneratedjavacpu() {
        return infergeneratedjavacpu;
    }

    public void setInfergeneratedjavacpu(BigDecimal infergeneratedjavacpu) {
        this.infergeneratedjavacpu = infergeneratedjavacpu;
    }

    @Basic
    @Column(name = "testmode", nullable = true, length = 30)
    public String getTestmode() {
        return testmode;
    }

    public void setTestmode(String testmode) {
        this.testmode = testmode;
    }

    @Basic
    @Column(name = "commitid", nullable = true, length = 100)
    public String getCommitid() {
        return commitid;
    }

    public void setCommitid(String commitid) {
        this.commitid = commitid;
    }

    @Basic
    @Column(name = "mprid", nullable = true, length = 100)
    public String getMprid() {
        return mprid;
    }

    public void setMprid(String mprid) {
        this.mprid = mprid;
    }

    @Basic
    @Column(name = "testrunstatus", nullable = true, length = 30)
    public String getTestrunstatus() {
        return testrunstatus;
    }

    public void setTestrunstatus(String testrunstatus) {
        this.testrunstatus = testrunstatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PerfStat that = (PerfStat) o;

        if (testname != null ? !testname.equals(that.testname) : that.testname != null) return false;
        if (trialtype != null ? !trialtype.equals(that.trialtype) : that.trialtype != null) return false;
        if (runlevel != null ? !runlevel.equals(that.runlevel) : that.runlevel != null) return false;
        if (builddate != null ? !builddate.equals(that.builddate) : that.builddate != null) return false;
        if (buildlabel != null ? !buildlabel.equals(that.buildlabel) : that.buildlabel != null) return false;
        if (prpcversion != null ? !prpcversion.equals(that.prpcversion) : that.prpcversion != null) return false;
        if (teststart != null ? !teststart.equals(that.teststart) : that.teststart != null) return false;
        if (testend != null ? !testend.equals(that.testend) : that.testend != null) return false;
        if (activitycount != null ? !activitycount.equals(that.activitycount) : that.activitycount != null)
            return false;
        if (alertcount != null ? !alertcount.equals(that.alertcount) : that.alertcount != null) return false;
        if (application != null ? !application.equals(that.application) : that.application != null) return false;
        if (commitcount != null ? !commitcount.equals(that.commitcount) : that.commitcount != null) return false;
        if (commitrowcount != null ? !commitrowcount.equals(that.commitrowcount) : that.commitrowcount != null)
            return false;
        if (connectcount != null ? !connectcount.equals(that.connectcount) : that.connectcount != null) return false;
        if (connectelapsed != null ? !connectelapsed.equals(that.connectelapsed) : that.connectelapsed != null)
            return false;
        if (dbopexceedingthresholdcount != null ? !dbopexceedingthresholdcount.equals(that.dbopexceedingthresholdcount) : that.dbopexceedingthresholdcount != null)
            return false;
        if (declarativerulereadcount != null ? !declarativerulereadcount.equals(that.declarativerulereadcount) : that.declarativerulereadcount != null)
            return false;
        if (declarativeruleslookupcount != null ? !declarativeruleslookupcount.equals(that.declarativeruleslookupcount) : that.declarativeruleslookupcount != null)
            return false;
        if (flowcount != null ? !flowcount.equals(that.flowcount) : that.flowcount != null) return false;
        if (infergeneratedjavacount != null ? !infergeneratedjavacount.equals(that.infergeneratedjavacount) : that.infergeneratedjavacount != null)
            return false;
        if (infergeneratedjavaelapsed != null ? !infergeneratedjavaelapsed.equals(that.infergeneratedjavaelapsed) : that.infergeneratedjavaelapsed != null)
            return false;
        if (infergeneratedjavahlelapsed != null ? !infergeneratedjavahlelapsed.equals(that.infergeneratedjavahlelapsed) : that.infergeneratedjavahlelapsed != null)
            return false;
        if (javaassemblecount != null ? !javaassemblecount.equals(that.javaassemblecount) : that.javaassemblecount != null)
            return false;
        if (javacompilecount != null ? !javacompilecount.equals(that.javacompilecount) : that.javacompilecount != null)
            return false;
        if (javageneratecount != null ? !javageneratecount.equals(that.javageneratecount) : that.javageneratecount != null)
            return false;
        if (javasyntaxcpu != null ? !javasyntaxcpu.equals(that.javasyntaxcpu) : that.javasyntaxcpu != null)
            return false;
        if (otheriocount != null ? !otheriocount.equals(that.otheriocount) : that.otheriocount != null) return false;
        if (otheriocpu != null ? !otheriocpu.equals(that.otheriocpu) : that.otheriocpu != null) return false;
        if (otherioelapsed != null ? !otherioelapsed.equals(that.otherioelapsed) : that.otherioelapsed != null)
            return false;
        if (parserulecount != null ? !parserulecount.equals(that.parserulecount) : that.parserulecount != null)
            return false;
        if (processcpu != null ? !processcpu.equals(that.processcpu) : that.processcpu != null) return false;
        if (rdbiocount != null ? !rdbiocount.equals(that.rdbiocount) : that.rdbiocount != null) return false;
        if (rdbioelapsed != null ? !rdbioelapsed.equals(that.rdbioelapsed) : that.rdbioelapsed != null) return false;
        if (rulecount != null ? !rulecount.equals(that.rulecount) : that.rulecount != null) return false;
        if (rulefromcachecount != null ? !rulefromcachecount.equals(that.rulefromcachecount) : that.rulefromcachecount != null)
            return false;
        if (ruleioelapsed != null ? !ruleioelapsed.equals(that.ruleioelapsed) : that.ruleioelapsed != null)
            return false;
        if (rulesexecuted != null ? !rulesexecuted.equals(that.rulesexecuted) : that.rulesexecuted != null)
            return false;
        if (ruleused != null ? !ruleused.equals(that.ruleused) : that.ruleused != null) return false;
        if (runotherrulecount != null ? !runotherrulecount.equals(that.runotherrulecount) : that.runotherrulecount != null)
            return false;
        if (runwhencount != null ? !runwhencount.equals(that.runwhencount) : that.runwhencount != null) return false;
        if (systemname != null ? !systemname.equals(that.systemname) : that.systemname != null) return false;
        if (systemnode != null ? !systemnode.equals(that.systemnode) : that.systemnode != null) return false;
        if (systemnodeid != null ? !systemnodeid.equals(that.systemnodeid) : that.systemnodeid != null) return false;
        if (systemstart != null ? !systemstart.equals(that.systemstart) : that.systemstart != null) return false;
        if (totalreqcpu != null ? !totalreqcpu.equals(that.totalreqcpu) : that.totalreqcpu != null) return false;
        if (totalreqtime != null ? !totalreqtime.equals(that.totalreqtime) : that.totalreqtime != null) return false;
        if (pxobjclass != null ? !pxobjclass.equals(that.pxobjclass) : that.pxobjclass != null) return false;
        if (pzinskey != null ? !pzinskey.equals(that.pzinskey) : that.pzinskey != null) return false;
        if (pxinsname != null ? !pxinsname.equals(that.pxinsname) : that.pxinsname != null) return false;
        if (javaassemblehlelapsed != null ? !javaassemblehlelapsed.equals(that.javaassemblehlelapsed) : that.javaassemblehlelapsed != null)
            return false;
        if (buildinfo != null ? !buildinfo.equals(that.buildinfo) : that.buildinfo != null) return false;
        if (prpcrelease != null ? !prpcrelease.equals(that.prpcrelease) : that.prpcrelease != null) return false;
        if (buildnumber != null ? !buildnumber.equals(that.buildnumber) : that.buildnumber != null) return false;
        if (wallseconds != null ? !wallseconds.equals(that.wallseconds) : that.wallseconds != null) return false;
        if (testelapsedminutes != null ? !testelapsedminutes.equals(that.testelapsedminutes) : that.testelapsedminutes != null)
            return false;
        if (errorcount != null ? !errorcount.equals(that.errorcount) : that.errorcount != null) return false;
        if (linecountdata != null ? !linecountdata.equals(that.linecountdata) : that.linecountdata != null)
            return false;
        if (infocount != null ? !infocount.equals(that.infocount) : that.infocount != null) return false;
        if (dbinputbytes != null ? !dbinputbytes.equals(that.dbinputbytes) : that.dbinputbytes != null) return false;
        if (outputbytes != null ? !outputbytes.equals(that.outputbytes) : that.outputbytes != null) return false;
        if (inputbytes != null ? !inputbytes.equals(that.inputbytes) : that.inputbytes != null) return false;
        if (dboutputbytes != null ? !dboutputbytes.equals(that.dboutputbytes) : that.dboutputbytes != null)
            return false;
        if (isga != null ? !isga.equals(that.isga) : that.isga != null) return false;
        if (isvalidrun != null ? !isvalidrun.equals(that.isvalidrun) : that.isvalidrun != null) return false;
        if (ispristinerun != null ? !ispristinerun.equals(that.ispristinerun) : that.ispristinerun != null)
            return false;
        if (teamemail != null ? !teamemail.equals(that.teamemail) : that.teamemail != null) return false;
        if (pristinebuildnumber != null ? !pristinebuildnumber.equals(that.pristinebuildnumber) : that.pristinebuildnumber != null)
            return false;
        if (validationjobId != null ? !validationjobId.equals(that.validationjobId) : that.validationjobId != null)
            return false;
        if (gctotalevents != null ? !gctotalevents.equals(that.gctotalevents) : that.gctotalevents != null)
            return false;
        if (gctotalmajor != null ? !gctotalmajor.equals(that.gctotalmajor) : that.gctotalmajor != null) return false;
        if (gctotalminor != null ? !gctotalminor.equals(that.gctotalminor) : that.gctotalminor != null) return false;
        if (gctotalother != null ? !gctotalother.equals(that.gctotalother) : that.gctotalother != null) return false;
        if (gctotalduration != null ? !gctotalduration.equals(that.gctotalduration) : that.gctotalduration != null)
            return false;
        if (gctotalgarbage != null ? !gctotalgarbage.equals(that.gctotalgarbage) : that.gctotalgarbage != null)
            return false;
        if (gctotalprecollection != null ? !gctotalprecollection.equals(that.gctotalprecollection) : that.gctotalprecollection != null)
            return false;
        if (gcmaxduration != null ? !gcmaxduration.equals(that.gcmaxduration) : that.gcmaxduration != null)
            return false;
        if (gcmaxgarbage != null ? !gcmaxgarbage.equals(that.gcmaxgarbage) : that.gcmaxgarbage != null) return false;
        if (gctotalpostcollection != null ? !gctotalpostcollection.equals(that.gctotalpostcollection) : that.gctotalpostcollection != null)
            return false;
        if (gcmaxpostcollection != null ? !gcmaxpostcollection.equals(that.gcmaxpostcollection) : that.gcmaxpostcollection != null)
            return false;
        if (gcmaxprecollection != null ? !gcmaxprecollection.equals(that.gcmaxprecollection) : that.gcmaxprecollection != null)
            return false;
        if (teamname != null ? !teamname.equals(that.teamname) : that.teamname != null) return false;
        if (loadedclasscount != null ? !loadedclasscount.equals(that.loadedclasscount) : that.loadedclasscount != null)
            return false;
        if (activationdatatimeelapsed != null ? !activationdatatimeelapsed.equals(that.activationdatatimeelapsed) : that.activationdatatimeelapsed != null)
            return false;
        if (commitelapsed != null ? !commitelapsed.equals(that.commitelapsed) : that.commitelapsed != null)
            return false;
        if (declarativerulesinvokedcount != null ? !declarativerulesinvokedcount.equals(that.declarativerulesinvokedcount) : that.declarativerulesinvokedcount != null)
            return false;
        if (declarativerulesinvokedcpu != null ? !declarativerulesinvokedcpu.equals(that.declarativerulesinvokedcpu) : that.declarativerulesinvokedcpu != null)
            return false;
        if (declarativeruleslookupcpu != null ? !declarativeruleslookupcpu.equals(that.declarativeruleslookupcpu) : that.declarativeruleslookupcpu != null)
            return false;
        if (declexprctxfreeusecount != null ? !declexprctxfreeusecount.equals(that.declexprctxfreeusecount) : that.declexprctxfreeusecount != null)
            return false;
        if (declntwksbuildconstcpu != null ? !declntwksbuildconstcpu.equals(that.declntwksbuildconstcpu) : that.declntwksbuildconstcpu != null)
            return false;
        if (declntwksbuildconstelapsed != null ? !declntwksbuildconstelapsed.equals(that.declntwksbuildconstelapsed) : that.declntwksbuildconstelapsed != null)
            return false;
        if (declntwksbuildhlcpu != null ? !declntwksbuildhlcpu.equals(that.declntwksbuildhlcpu) : that.declntwksbuildhlcpu != null)
            return false;
        if (declntwksbuildhlelap != null ? !declntwksbuildhlelap.equals(that.declntwksbuildhlelap) : that.declntwksbuildhlelap != null)
            return false;
        if (declrulesinvokedelapsed != null ? !declrulesinvokedelapsed.equals(that.declrulesinvokedelapsed) : that.declrulesinvokedelapsed != null)
            return false;
        if (declruleslookupelapsed != null ? !declruleslookupelapsed.equals(that.declruleslookupelapsed) : that.declruleslookupelapsed != null)
            return false;
        if (indexcount != null ? !indexcount.equals(that.indexcount) : that.indexcount != null) return false;
        if (infergeneratedjavacountcpu != null ? !infergeneratedjavacountcpu.equals(that.infergeneratedjavacountcpu) : that.infergeneratedjavacountcpu != null)
            return false;
        if (interactions != null ? !interactions.equals(that.interactions) : that.interactions != null) return false;
        if (javaassemblecpu != null ? !javaassemblecpu.equals(that.javaassemblecpu) : that.javaassemblecpu != null)
            return false;
        if (javaassembleelapsed != null ? !javaassembleelapsed.equals(that.javaassembleelapsed) : that.javaassembleelapsed != null)
            return false;
        if (javacompilecpu != null ? !javacompilecpu.equals(that.javacompilecpu) : that.javacompilecpu != null)
            return false;
        if (javacompileelapsed != null ? !javacompileelapsed.equals(that.javacompileelapsed) : that.javacompileelapsed != null)
            return false;
        if (javageneratecpu != null ? !javageneratecpu.equals(that.javageneratecpu) : that.javageneratecpu != null)
            return false;
        if (javagenerateelapsed != null ? !javagenerateelapsed.equals(that.javagenerateelapsed) : that.javagenerateelapsed != null)
            return false;
        if (legacyruleapiusedcount != null ? !legacyruleapiusedcount.equals(that.legacyruleapiusedcount) : that.legacyruleapiusedcount != null)
            return false;
        if (listrowwithunfilteredstrmcnt != null ? !listrowwithunfilteredstrmcnt.equals(that.listrowwithunfilteredstrmcnt) : that.listrowwithunfilteredstrmcnt != null)
            return false;
        if (listwithunfilteredstrmcnt != null ? !listwithunfilteredstrmcnt.equals(that.listwithunfilteredstrmcnt) : that.listwithunfilteredstrmcnt != null)
            return false;
        if (lookuplistdbfetches != null ? !lookuplistdbfetches.equals(that.lookuplistdbfetches) : that.lookuplistdbfetches != null)
            return false;
        if (otherbrowsecpu != null ? !otherbrowsecpu.equals(that.otherbrowsecpu) : that.otherbrowsecpu != null)
            return false;
        if (otherbrowseelapsed != null ? !otherbrowseelapsed.equals(that.otherbrowseelapsed) : that.otherbrowseelapsed != null)
            return false;
        if (othercount != null ? !othercount.equals(that.othercount) : that.othercount != null) return false;
        if (otherfromcachecount != null ? !otherfromcachecount.equals(that.otherfromcachecount) : that.otherfromcachecount != null)
            return false;
        if (proceduralrulereadcount != null ? !proceduralrulereadcount.equals(that.proceduralrulereadcount) : that.proceduralrulereadcount != null)
            return false;
        if (rdbrowwithoutstreamcount != null ? !rdbrowwithoutstreamcount.equals(that.rdbrowwithoutstreamcount) : that.rdbrowwithoutstreamcount != null)
            return false;
        if (rdbwithoutstreamcount != null ? !rdbwithoutstreamcount.equals(that.rdbwithoutstreamcount) : that.rdbwithoutstreamcount != null)
            return false;
        if (rulebrowsecpu != null ? !rulebrowsecpu.equals(that.rulebrowsecpu) : that.rulebrowsecpu != null)
            return false;
        if (rulebrowseelapsed != null ? !rulebrowseelapsed.equals(that.rulebrowseelapsed) : that.rulebrowseelapsed != null)
            return false;
        if (rulecpu != null ? !rulecpu.equals(that.rulecpu) : that.rulecpu != null) return false;
        if (runmodelcount != null ? !runmodelcount.equals(that.runmodelcount) : that.runmodelcount != null)
            return false;
        if (runstreamcount != null ? !runstreamcount.equals(that.runstreamcount) : that.runstreamcount != null)
            return false;
        if (peakthreadcount != null ? !peakthreadcount.equals(that.peakthreadcount) : that.peakthreadcount != null)
            return false;
        if (threadcount != null ? !threadcount.equals(that.threadcount) : that.threadcount != null) return false;
        if (totalloadedclasscount != null ? !totalloadedclasscount.equals(that.totalloadedclasscount) : that.totalloadedclasscount != null)
            return false;
        if (unloadedclasscount != null ? !unloadedclasscount.equals(that.unloadedclasscount) : that.unloadedclasscount != null)
            return false;
        if (totalstartedthreadcount != null ? !totalstartedthreadcount.equals(that.totalstartedthreadcount) : that.totalstartedthreadcount != null)
            return false;
        if (infergeneratedjavacpu != null ? !infergeneratedjavacpu.equals(that.infergeneratedjavacpu) : that.infergeneratedjavacpu != null)
            return false;
        if (testmode != null ? !testmode.equals(that.testmode) : that.testmode != null) return false;
        if (commitid != null ? !commitid.equals(that.commitid) : that.commitid != null) return false;
        if (mprid != null ? !mprid.equals(that.mprid) : that.mprid != null) return false;
        if (testrunstatus != null ? !testrunstatus.equals(that.testrunstatus) : that.testrunstatus != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = testname != null ? testname.hashCode() : 0;
        result = 31 * result + (trialtype != null ? trialtype.hashCode() : 0);
        result = 31 * result + (runlevel != null ? runlevel.hashCode() : 0);
        result = 31 * result + (builddate != null ? builddate.hashCode() : 0);
        result = 31 * result + (buildlabel != null ? buildlabel.hashCode() : 0);
        result = 31 * result + (prpcversion != null ? prpcversion.hashCode() : 0);
        result = 31 * result + (teststart != null ? teststart.hashCode() : 0);
        result = 31 * result + (testend != null ? testend.hashCode() : 0);
        result = 31 * result + (activitycount != null ? activitycount.hashCode() : 0);
        result = 31 * result + (alertcount != null ? alertcount.hashCode() : 0);
        result = 31 * result + (application != null ? application.hashCode() : 0);
        result = 31 * result + (commitcount != null ? commitcount.hashCode() : 0);
        result = 31 * result + (commitrowcount != null ? commitrowcount.hashCode() : 0);
        result = 31 * result + (connectcount != null ? connectcount.hashCode() : 0);
        result = 31 * result + (connectelapsed != null ? connectelapsed.hashCode() : 0);
        result = 31 * result + (dbopexceedingthresholdcount != null ? dbopexceedingthresholdcount.hashCode() : 0);
        result = 31 * result + (declarativerulereadcount != null ? declarativerulereadcount.hashCode() : 0);
        result = 31 * result + (declarativeruleslookupcount != null ? declarativeruleslookupcount.hashCode() : 0);
        result = 31 * result + (flowcount != null ? flowcount.hashCode() : 0);
        result = 31 * result + (infergeneratedjavacount != null ? infergeneratedjavacount.hashCode() : 0);
        result = 31 * result + (infergeneratedjavaelapsed != null ? infergeneratedjavaelapsed.hashCode() : 0);
        result = 31 * result + (infergeneratedjavahlelapsed != null ? infergeneratedjavahlelapsed.hashCode() : 0);
        result = 31 * result + (javaassemblecount != null ? javaassemblecount.hashCode() : 0);
        result = 31 * result + (javacompilecount != null ? javacompilecount.hashCode() : 0);
        result = 31 * result + (javageneratecount != null ? javageneratecount.hashCode() : 0);
        result = 31 * result + (javasyntaxcpu != null ? javasyntaxcpu.hashCode() : 0);
        result = 31 * result + (otheriocount != null ? otheriocount.hashCode() : 0);
        result = 31 * result + (otheriocpu != null ? otheriocpu.hashCode() : 0);
        result = 31 * result + (otherioelapsed != null ? otherioelapsed.hashCode() : 0);
        result = 31 * result + (parserulecount != null ? parserulecount.hashCode() : 0);
        result = 31 * result + (processcpu != null ? processcpu.hashCode() : 0);
        result = 31 * result + (rdbiocount != null ? rdbiocount.hashCode() : 0);
        result = 31 * result + (rdbioelapsed != null ? rdbioelapsed.hashCode() : 0);
        result = 31 * result + (rulecount != null ? rulecount.hashCode() : 0);
        result = 31 * result + (rulefromcachecount != null ? rulefromcachecount.hashCode() : 0);
        result = 31 * result + (ruleioelapsed != null ? ruleioelapsed.hashCode() : 0);
        result = 31 * result + (rulesexecuted != null ? rulesexecuted.hashCode() : 0);
        result = 31 * result + (ruleused != null ? ruleused.hashCode() : 0);
        result = 31 * result + (runotherrulecount != null ? runotherrulecount.hashCode() : 0);
        result = 31 * result + (runwhencount != null ? runwhencount.hashCode() : 0);
        result = 31 * result + (systemname != null ? systemname.hashCode() : 0);
        result = 31 * result + (systemnode != null ? systemnode.hashCode() : 0);
        result = 31 * result + (systemnodeid != null ? systemnodeid.hashCode() : 0);
        result = 31 * result + (systemstart != null ? systemstart.hashCode() : 0);
        result = 31 * result + (totalreqcpu != null ? totalreqcpu.hashCode() : 0);
        result = 31 * result + (totalreqtime != null ? totalreqtime.hashCode() : 0);
        result = 31 * result + (pxobjclass != null ? pxobjclass.hashCode() : 0);
        result = 31 * result + (pzinskey != null ? pzinskey.hashCode() : 0);
        result = 31 * result + (pxinsname != null ? pxinsname.hashCode() : 0);
        result = 31 * result + (javaassemblehlelapsed != null ? javaassemblehlelapsed.hashCode() : 0);
        result = 31 * result + (buildinfo != null ? buildinfo.hashCode() : 0);
        result = 31 * result + (prpcrelease != null ? prpcrelease.hashCode() : 0);
        result = 31 * result + (buildnumber != null ? buildnumber.hashCode() : 0);
        result = 31 * result + (wallseconds != null ? wallseconds.hashCode() : 0);
        result = 31 * result + (testelapsedminutes != null ? testelapsedminutes.hashCode() : 0);
        result = 31 * result + (errorcount != null ? errorcount.hashCode() : 0);
        result = 31 * result + (linecountdata != null ? linecountdata.hashCode() : 0);
        result = 31 * result + (infocount != null ? infocount.hashCode() : 0);
        result = 31 * result + (dbinputbytes != null ? dbinputbytes.hashCode() : 0);
        result = 31 * result + (outputbytes != null ? outputbytes.hashCode() : 0);
        result = 31 * result + (inputbytes != null ? inputbytes.hashCode() : 0);
        result = 31 * result + (dboutputbytes != null ? dboutputbytes.hashCode() : 0);
        result = 31 * result + (isga != null ? isga.hashCode() : 0);
        result = 31 * result + (isvalidrun != null ? isvalidrun.hashCode() : 0);
        result = 31 * result + (ispristinerun != null ? ispristinerun.hashCode() : 0);
        result = 31 * result + (teamemail != null ? teamemail.hashCode() : 0);
        result = 31 * result + (pristinebuildnumber != null ? pristinebuildnumber.hashCode() : 0);
        result = 31 * result + (validationjobId != null ? validationjobId.hashCode() : 0);
        result = 31 * result + (gctotalevents != null ? gctotalevents.hashCode() : 0);
        result = 31 * result + (gctotalmajor != null ? gctotalmajor.hashCode() : 0);
        result = 31 * result + (gctotalminor != null ? gctotalminor.hashCode() : 0);
        result = 31 * result + (gctotalother != null ? gctotalother.hashCode() : 0);
        result = 31 * result + (gctotalduration != null ? gctotalduration.hashCode() : 0);
        result = 31 * result + (gctotalgarbage != null ? gctotalgarbage.hashCode() : 0);
        result = 31 * result + (gctotalprecollection != null ? gctotalprecollection.hashCode() : 0);
        result = 31 * result + (gcmaxduration != null ? gcmaxduration.hashCode() : 0);
        result = 31 * result + (gcmaxgarbage != null ? gcmaxgarbage.hashCode() : 0);
        result = 31 * result + (gctotalpostcollection != null ? gctotalpostcollection.hashCode() : 0);
        result = 31 * result + (gcmaxpostcollection != null ? gcmaxpostcollection.hashCode() : 0);
        result = 31 * result + (gcmaxprecollection != null ? gcmaxprecollection.hashCode() : 0);
        result = 31 * result + (teamname != null ? teamname.hashCode() : 0);
        result = 31 * result + (loadedclasscount != null ? loadedclasscount.hashCode() : 0);
        result = 31 * result + (activationdatatimeelapsed != null ? activationdatatimeelapsed.hashCode() : 0);
        result = 31 * result + (commitelapsed != null ? commitelapsed.hashCode() : 0);
        result = 31 * result + (declarativerulesinvokedcount != null ? declarativerulesinvokedcount.hashCode() : 0);
        result = 31 * result + (declarativerulesinvokedcpu != null ? declarativerulesinvokedcpu.hashCode() : 0);
        result = 31 * result + (declarativeruleslookupcpu != null ? declarativeruleslookupcpu.hashCode() : 0);
        result = 31 * result + (declexprctxfreeusecount != null ? declexprctxfreeusecount.hashCode() : 0);
        result = 31 * result + (declntwksbuildconstcpu != null ? declntwksbuildconstcpu.hashCode() : 0);
        result = 31 * result + (declntwksbuildconstelapsed != null ? declntwksbuildconstelapsed.hashCode() : 0);
        result = 31 * result + (declntwksbuildhlcpu != null ? declntwksbuildhlcpu.hashCode() : 0);
        result = 31 * result + (declntwksbuildhlelap != null ? declntwksbuildhlelap.hashCode() : 0);
        result = 31 * result + (declrulesinvokedelapsed != null ? declrulesinvokedelapsed.hashCode() : 0);
        result = 31 * result + (declruleslookupelapsed != null ? declruleslookupelapsed.hashCode() : 0);
        result = 31 * result + (indexcount != null ? indexcount.hashCode() : 0);
        result = 31 * result + (infergeneratedjavacountcpu != null ? infergeneratedjavacountcpu.hashCode() : 0);
        result = 31 * result + (interactions != null ? interactions.hashCode() : 0);
        result = 31 * result + (javaassemblecpu != null ? javaassemblecpu.hashCode() : 0);
        result = 31 * result + (javaassembleelapsed != null ? javaassembleelapsed.hashCode() : 0);
        result = 31 * result + (javacompilecpu != null ? javacompilecpu.hashCode() : 0);
        result = 31 * result + (javacompileelapsed != null ? javacompileelapsed.hashCode() : 0);
        result = 31 * result + (javageneratecpu != null ? javageneratecpu.hashCode() : 0);
        result = 31 * result + (javagenerateelapsed != null ? javagenerateelapsed.hashCode() : 0);
        result = 31 * result + (legacyruleapiusedcount != null ? legacyruleapiusedcount.hashCode() : 0);
        result = 31 * result + (listrowwithunfilteredstrmcnt != null ? listrowwithunfilteredstrmcnt.hashCode() : 0);
        result = 31 * result + (listwithunfilteredstrmcnt != null ? listwithunfilteredstrmcnt.hashCode() : 0);
        result = 31 * result + (lookuplistdbfetches != null ? lookuplistdbfetches.hashCode() : 0);
        result = 31 * result + (otherbrowsecpu != null ? otherbrowsecpu.hashCode() : 0);
        result = 31 * result + (otherbrowseelapsed != null ? otherbrowseelapsed.hashCode() : 0);
        result = 31 * result + (othercount != null ? othercount.hashCode() : 0);
        result = 31 * result + (otherfromcachecount != null ? otherfromcachecount.hashCode() : 0);
        result = 31 * result + (proceduralrulereadcount != null ? proceduralrulereadcount.hashCode() : 0);
        result = 31 * result + (rdbrowwithoutstreamcount != null ? rdbrowwithoutstreamcount.hashCode() : 0);
        result = 31 * result + (rdbwithoutstreamcount != null ? rdbwithoutstreamcount.hashCode() : 0);
        result = 31 * result + (rulebrowsecpu != null ? rulebrowsecpu.hashCode() : 0);
        result = 31 * result + (rulebrowseelapsed != null ? rulebrowseelapsed.hashCode() : 0);
        result = 31 * result + (rulecpu != null ? rulecpu.hashCode() : 0);
        result = 31 * result + (runmodelcount != null ? runmodelcount.hashCode() : 0);
        result = 31 * result + (runstreamcount != null ? runstreamcount.hashCode() : 0);
        result = 31 * result + (peakthreadcount != null ? peakthreadcount.hashCode() : 0);
        result = 31 * result + (threadcount != null ? threadcount.hashCode() : 0);
        result = 31 * result + (totalloadedclasscount != null ? totalloadedclasscount.hashCode() : 0);
        result = 31 * result + (unloadedclasscount != null ? unloadedclasscount.hashCode() : 0);
        result = 31 * result + (totalstartedthreadcount != null ? totalstartedthreadcount.hashCode() : 0);
        result = 31 * result + (infergeneratedjavacpu != null ? infergeneratedjavacpu.hashCode() : 0);
        result = 31 * result + (testmode != null ? testmode.hashCode() : 0);
        result = 31 * result + (commitid != null ? commitid.hashCode() : 0);
        result = 31 * result + (mprid != null ? mprid.hashCode() : 0);
        result = 31 * result + (testrunstatus != null ? testrunstatus.hashCode() : 0);
        return result;
    }
}
