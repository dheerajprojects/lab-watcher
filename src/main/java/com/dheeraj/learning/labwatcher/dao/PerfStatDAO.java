package com.dheeraj.learning.labwatcher.dao;

import com.dheeraj.learning.labwatcher.entity.ParamData;
import com.dheeraj.learning.labwatcher.entity.PerfStat;
import com.dheeraj.learning.labwatcher.entity.ScenarioData;
import com.dheeraj.learning.labwatcher.repository.ParamDataRepository;
import com.dheeraj.learning.labwatcher.repository.ScenarioDataRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.AbstractQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class PerfStatDAO {

    Logger logger = LoggerFactory.getLogger(PerfStatDAO.class);

    @PersistenceContext
    EntityManager em;

    @Autowired
    ParamDataRepository paramDataRepository;

    @Autowired
    ScenarioDataRepository scenarioDataRepository;

    public PerfStat getPerfStatForAGivenBuild(String scenarioName, String buildInfo) {
        String sql = "FROM PerfStat " +
                "where trialtype='Performance' " +
                "and runlevel='optimized' " +
                "and testname='" + scenarioName + "' " +
                "and isvalidrun='true'" +
                "and buildinfo = '" + buildInfo + "' " +
                "order by teststart desc";

        Query query = em.createQuery(sql);

        List<PerfStat> list = query.getResultList();
        return list.get(0);
    }

    /**
     * This retrieves the all scenario results for a given build with given prpcversion.
     * When the same scenario for a specific build is run multiple times, this will retrieve the latest result.
     * @param prpcversion
     * @param buildinfo
     * @return
     */
    public List<PerfStat> getLatestPerfStatsForAGivenBuild(String prpcversion, String buildinfo) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        AbstractQuery<PerfStat> aq = cb.createQuery(PerfStat.class);
        Root<PerfStat> root = aq.from(PerfStat.class);

        CriteriaQuery<PerfStat> select = ((CriteriaQuery<PerfStat>)aq).multiselect(cb.max(root.get("teststart")),
                root.get("trialtype"),
                root.get("runlevel"),
                root.get("testname"),
                root.get("isvalidrun"),
                root.get("prpcversion"),
                root.get("buildinfo")
        );

        //Where clause
        aq.where(cb.equal(root.get("trialtype"), "Performance"),
                cb.equal(root.get("runlevel"),"optimized"),
                cb.equal(root.get("prpcversion"),prpcversion),
                cb.equal(root.get("buildinfo"), buildinfo));


        select.groupBy(root.get("trialtype"),
                root.get("runlevel"),
                root.get("testname"),
                root.get("isvalidrun"),
                root.get("prpcversion"),
                root.get("buildinfo")
                );

        select.orderBy(cb.asc(root.get("testname")));

        TypedQuery<PerfStat> q = em.createQuery(select);

        List<PerfStat> list = q.getResultList();

        return list;
    }

    /**
     * Retrieves builddate for given build. Check if prpc version is also needed.
     *
     * @param buildinfo
     * @return
     */
    public Timestamp getBuildDate(String buildinfo) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        AbstractQuery<Timestamp> aq = cb.createQuery(Timestamp.class);
        Root<PerfStat> root = aq.from(PerfStat.class);

        //Select clause
        CriteriaQuery<Timestamp> select = ((CriteriaQuery<Timestamp>) aq).select(root.get("builddate")).distinct(true);

        //Where clause
        aq.where(cb.equal(root.get("isvalidrun"), "true"),
                cb.equal(root.get("buildinfo"), buildinfo));

        TypedQuery<Timestamp> q = em.createQuery(select);

        List<Timestamp> list = q.getResultList();
        if(list.size() > 1)
            logger.warn("There are multiple build dates for the same build : "+buildinfo);

        Timestamp timestamp = list.get(0);

        return timestamp;
    }

    /**
     *
     * TODO
     * //Selected columns only to increase the performance. Add other parameters if required.
     *         /*CriteriaQuery<PerfStat> select = ((CriteriaQuery<PerfStat>)aq).multiselect(root.get("buildinfo"),
     *                 root.get("totalreqtime"),
     *                 root.get("totalreqcpu"),
     *                 root.get("rdbiocount"),
     *                 root.get("commitcount"),
     *                 root.get("activitycount"),
     *                 root.get("alertcount"),
     *                 root.get("commitcount"),
     *                 root.get("errorcount"),
     *                 root.get("testelapsedminutes")
     *         );
     *
     * @param scenarioName
     * @param prpcVersion
     * @param buildInfo
     * @param maxResults     *
     * @return
     */
    public List<PerfStat> getPerfStatsForLastNBuilds(String scenarioName, String prpcVersion, String buildInfo, int maxResults) {
        Timestamp buildDate = getBuildDate(buildInfo);

        CriteriaBuilder cb = em.getCriteriaBuilder();
        AbstractQuery<PerfStat> aq = cb.createQuery(PerfStat.class);
        Root<PerfStat> root = aq.from(PerfStat.class);

        //Select all columns
        CriteriaQuery<PerfStat> select = ((CriteriaQuery<PerfStat>) aq).select(root);

        //Where clause
        aq.where(cb.equal(root.get("trialtype"), "Performance"),
                cb.equal(root.get("runlevel"),"optimized"),
                cb.equal(root.get("isvalidrun"),"true"),
                cb.equal(root.get("runlevel"),"optimized"),
                cb.equal(root.get("prpcversion"),prpcVersion),
                cb.equal(root.get("testname"), scenarioName),
                cb.lessThan(root.get("builddate"), buildDate));

        select.orderBy(cb.desc(root.get("teststart")));

        TypedQuery<PerfStat> q = em.createQuery(select);
        q.setMaxResults(maxResults);

        List<PerfStat> list = q.getResultList();

        return list;
    }

    public List<String> getValidBuildsBetweenGivenDates(String scenarioName, String prpcVersion, String startDate, String endDate) {
        String sql = "select max(teststart) as maxteststart, buildinfo, trialtype, runlevel, testname, isvalidrun, prpcversion from PerfStat " +
                "where trialtype='Performance' " +
                "and runlevel='optimized' " +
                "and testname='" + scenarioName + "' " +
                "and prpcversion='" + prpcVersion + "' " +
                "and isvalidrun='true' " +
                "and buildinfo like '%HEAD%' " +
                "and teststart > '" + startDate + "' " +
                "and teststart < '" + endDate + "' " +
                "group by trialtype, runlevel, testname, isvalidrun, prpcversion, buildinfo " +
                "order by buildinfo asc";

        Query query = em.createQuery(sql);
        List<Object[]> rows = query.getResultList();
        List<String> buildInfos = new ArrayList<>();
        for (Object[] row :
                rows) {
            buildInfos.add(row[1] + "");
        }

        return buildInfos;
    }

    /**
     * SQL Query
     * SELECT Max(teststart) AS maxteststart,
     *        buildinfo,
     *        trialtype,
     *        runlevel,
     *        testname,
     *        isvalidrun,
     *        prpcversion,
     *        builddate
     * FROM   data.pr_data_performancestats
     * WHERE  trialtype = 'Performance'
     *        AND runlevel = 'optimized'
     *        AND testname = 'CCCASE'
     *        AND prpcversion = '8.3.0'
     *        AND isvalidrun = 'true'
     *        AND buildinfo LIKE '%HEAD%'
     * GROUP  BY trialtype,
     *           runlevel,
     *           testname,
     *           isvalidrun,
     *           prpcversion,
     *           buildinfo,
     *           builddate
     * ORDER  BY builddate ASC;
     *
     * @param scenarioName
     * @param prpcVersion
     * @return
     */
    public List<String> getValidBuildsForGivenRelease(String scenarioName, String prpcVersion) {
        String sql = "select max(teststart) as maxteststart, buildinfo, trialtype, runlevel, testname, isvalidrun, prpcversion, builddate from PerfStat " +
                "where trialtype='Performance' " +
                "and runlevel='optimized' " +
                "and testname='" + scenarioName + "' " +
                "and prpcversion='" + prpcVersion + "' " +
                "and isvalidrun='true' " +
                "and buildinfo like '%HEAD%' " +
                "group by trialtype, runlevel, testname, isvalidrun, prpcversion, buildinfo, builddate " +
                "order by builddate asc";

        Query query = em.createQuery(sql);
        List<Object[]> rows = query.getResultList();
        List<String> buildInfos = new ArrayList<>();
        for (Object[] row :
                rows) {
            buildInfos.add(row[1] + "");
        }

        return buildInfos;
    }

    /**
     * SQL Query
     * SELECT Max(teststart) AS maxteststart,
     *        buildinfo,
     *        trialtype,
     *        runlevel,
     *        isvalidrun,
     *        prpcversion,
     *        builddate
     * FROM   data.pr_data_performancestats
     * WHERE  trialtype = 'Performance'
     *        AND runlevel = 'optimized'
     *        AND prpcversion = '8.3.0'
     *        AND isvalidrun = 'true'
     *        AND buildinfo LIKE '%HEAD%'
     * GROUP  BY trialtype,
     *           runlevel,
     *           isvalidrun,
     *           prpcversion,
     *           buildinfo,
     *           builddate
     * ORDER  BY builddate ASC;
     *
     * @param prpcVersion
     * @return
     */
    public List<String> getValidBuildsForGivenRelease(String prpcVersion) {
        String sql = "select max(teststart) as maxteststart, buildinfo, trialtype, runlevel, isvalidrun, prpcversion, builddate from PerfStat " +
                "where trialtype='Performance' " +
                "and runlevel='optimized' " +
                "and prpcversion='" + prpcVersion + "' " +
                "and isvalidrun='true' " +
                "and buildinfo like '%HEAD%' " +
                "group by trialtype, runlevel, isvalidrun, prpcversion, buildinfo, builddate " +
                "order by builddate asc";

        Query query = em.createQuery(sql);
        List<Object[]> rows = query.getResultList();
        List<String> buildInfos = new ArrayList<>();
        for (Object[] row :
                rows) {
            buildInfos.add(row[1] + "");
        }

        return buildInfos;
    }

    /**
     * Find an efficent way for this later.
     * ========================
     *
     * @param scenarioName
     * @param performanceMetricName
     * @param currentBuildInfo
     */
    public ParamData getBaselineBuild(String scenarioName, String performanceMetricName, String currentBuildInfo, String prpcVersion) {
        //TODO : Add prpcVersion to ParamData too.
        String sql = "FROM ParamData pd " +
                "where pd.scenarioData.testname='" + scenarioName + "' " +
                "and pd.paramName = '" + performanceMetricName + "' " +
                "and pd.scenarioData.buildInfo < '" + currentBuildInfo + "' " +
                "and pd.accuracy >= 50 " +
                "and ( pd.isDegraded = true or pd.isImproved = true ) " +
                "order by pd.scenarioData.buildInfo desc";
        Query query = em.createQuery(sql);
        List<ParamData> list = query.getResultList();
        if (list.size() == 0)
            return null;
        else {
            ParamData paramData = list.get(0);
            String sql2 = "from PerfStat ps1 " +
                    "where trialtype='Performance' " +
                    "and runlevel='optimized' " +
                    "and testname='" + scenarioName + "' " +
                    "and isvalidrun='true' " +
                    "and prpcversion='" + prpcVersion + "' " +
                    "and teststart = ( select max(ps2.teststart) from PerfStat ps2 " +
                    "where ps1.trialtype=ps2.trialtype " +
                    "and ps1.runlevel=ps2.runlevel " +
                    "and ps1.testname=ps2.testname " +
                    "and ps1.isvalidrun=ps2.isvalidrun " +
                    "and ps1.prpcversion=ps2.prpcversion " +
                    "and ps1.buildinfo=ps2.buildinfo " +
                    "and ps1.buildlabel=ps2.buildlabel ) ";

            sql2 += "and buildinfo >= '" + paramData.getScenarioData().getBuildInfo() + "' " +
                    "and buildinfo < '" + currentBuildInfo + "' " +
                    "order by buildinfo desc";


            Query query2 = em.createQuery(sql2);
            List<PerfStat> list2 = query2.getResultList();
            paramData.setVariedBuildRank(list2.size());

            return paramData;
        }
    }

    //TODO: PRPCVERSION HAS TO BE INCLUDED AND IT IS BETTER TO HAVE A BUILDDATE AS WELL AS SOMETIMES BUILD LABELS ARE GETTING MODIFIED.
    public ParamData getParamDataForAGivenBuild(String scenarioName, String paramName, String buildInfo) {
        String sql = "FROM ParamData pd " +
                "where pd.scenarioData.testname='" + scenarioName + "' " +
                "and pd.paramName = '" + paramName + "' " +
                "and pd.scenarioData.buildInfo = '" + buildInfo + "' " +
                "order by pd.scenarioData.buildInfo desc";
        Query query = em.createQuery(sql);
        List<ParamData> list = query.getResultList();
        return list.get(0);
    }

    public void findAndUpdate(ParamData paramData, Double newAccuracy) {
        ParamData attachedParamData = em.find(ParamData.class, paramData.getParamId());
        attachedParamData.setAccuracy(newAccuracy);
    }

    public void findAndRemoveVariation(ParamData paramData) {
        ParamData attachedParamData = em.find(ParamData.class, paramData.getParamId());
        attachedParamData.setDegraded(false);
        attachedParamData.setImproved(false);
    }

    public ScenarioData getScenarioData(String scenarioName, String buildInfo) {
        //TODO : Add prpcversion later if required.
        String sql = "from ScenarioData sd " +
                "where sd.testname = '" + scenarioName + "' " +
                "and sd.buildInfo = '" + buildInfo + "' ";

        Query query = em.createQuery(sql);
        List<ScenarioData> list = query.getResultList();
        if (list == null || list.size() == 0)
            return null;

        if (list.size() > 1) {
            logger.debug("There are duplicates in scenario data. Scenario name : " + scenarioName + ", buildInfo : " + buildInfo + ", List size : " + list.size());
        }

        return list.get(0);
    }

    /**
     * Retrieves builds on the given date.
     *
     * @param date
     * @return
     */
    public List<PerfStat> getBuilds(String date) {
        String sql = "FROM PerfStat " +
                "where trialtype='Performance' " +
                "and runlevel='optimized' " +
                "and isvalidrun='true' " +
                "and buildinfo like '%HEAD%' " +
                "and cast(teststart as date) = '" + date + "' " +
                "order by buildinfo asc";

        Query query = em.createQuery(sql);
        List<PerfStat> list = query.getResultList();
        return list;
    }

    /**
     * Retrieves builds on the given date.
     *
     * @param date
     * @return
     */
    public List<PerfStat> getBuilds(String date, String scenarioName) {
        String sql = "FROM PerfStat " +
                "where trialtype='Performance' " +
                "and runlevel='optimized' " +
                "and isvalidrun='true' " +
                "and buildinfo like '%HEAD%' " +
                "and cast(teststart as date) = '" + date + "' " +
                "and testname = '" + scenarioName + "' " +
                "and prpcversion <> null " +
                "order by buildinfo asc";

        Query query = em.createQuery(sql);
        List<PerfStat> list = query.getResultList();
        return list;
    }

    /**
     * Retrieves builds on the given date.
     *
     * @param startDate This excludes the start date
     * @return
     */
    public List<PerfStat> getBuildsBetweenDates(String startDate, String endDate) {
        String sql = "FROM PerfStat " +
                "where trialtype='Performance' " +
                "and runlevel='optimized' " +
                "and isvalidrun='true' " +
                "and buildinfo like '%HEAD%' " +
                "and cast(teststart as date) > '" + startDate + "' " +
                "and cast(teststart as date) <= '" + endDate + "' " +
                "and prpcversion <> null " +
                "order by buildinfo asc";

        Query query = em.createQuery(sql);
        List<PerfStat> list = query.getResultList();
        return list;
    }

    public List<PerfStat> getPerfStatsBetweenBuilds(String scenarioName, String prpcVersion, String startBuildInfo, String endBuildInfo, int maxResults) {
        String sql = "FROM PerfStat " +
                "where trialtype='Performance' " +
                "and runlevel='optimized' " +
                "and testname='" + scenarioName + "' " +
                "and prpcversion='" + prpcVersion + "' " +
                "and isvalidrun='true'" +
                "and buildinfo >= '" + startBuildInfo + "' " +
                "and buildinfo <= '" + endBuildInfo + "' " +
                "order by teststart desc";

        Query query = em.createQuery(sql);
        // TODO : Verify here if the results size is  <= rank.
        // query.setMaxResults(maxResults);
        List<PerfStat> list = query.getResultList();
        return list;
    }
}
