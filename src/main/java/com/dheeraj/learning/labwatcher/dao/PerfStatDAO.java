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

    public List<PerfStat> getPerfStatsBetweenBuilds(String scenarioName, String prpcVersion, String startBuildLabel, String endBuildLabel, int maxResults) {
        String sql2 = "select s " +
                "from Student s " +
                "where s.studentName = :studentName ";

        String sql = "FROM PerfStat " +
                "where trialtype='Performance' " +
                "and runlevel='optimized' " +
                "and testname='" + scenarioName + "' " +
                "and prpcversion='" + prpcVersion + "' " +
                "and isvalidrun='true'" +
                "and buildlabel >= '" + startBuildLabel + "' " +
                "and buildlabel <= '" + endBuildLabel + "' " +
                "order by teststart desc";

        Query query = em.createQuery(sql);
        // TODO : Verify here if the results size is  <= rank.
        // query.setMaxResults(maxResults);
        List<PerfStat> list = query.getResultList();
        return list;
    }

    public PerfStat getPerfStatForAGivenBuild(String scenarioName, String buildLabel) {
        String sql2 = "select s " +
                "from Student s " +
                "where s.studentName = :studentName ";

        String sql = "FROM PerfStat " +
                "where trialtype='Performance' " +
                "and runlevel='optimized' " +
                "and testname='" + scenarioName + "' " +
                "and isvalidrun='true'" +
                "and buildlabel = '" + buildLabel + "' " +
                "order by teststart desc";

        Query query = em.createQuery(sql);

        List<PerfStat> list = query.getResultList();
        return list.get(0);
    }

    public List<PerfStat> getPerfStatsForLastNBuilds(String scenarioName, String prpcVersion, String endBuildLabel, int maxResults, boolean isHead) {
        String sql2 = "select s " +
                "from Student s " +
                "where s.studentName = :studentName ";

        String sql = "FROM PerfStat " +
                "where trialtype='Performance' " +
                "and runlevel='optimized' " +
                "and testname='" + scenarioName + "' " +
                "and prpcversion='" + prpcVersion + "' " +
                "and isvalidrun='true'" +
                "and builddate < ( "+
                                    "select max(builddate) from PerfStat "+
                                    "where buildlabel='"+endBuildLabel+"' "+
                                    "and trialtype='Performance' "+
                                    "and runlevel='optimized' "+
                                    "and isvalidrun='true' ";
        if(isHead)
            sql+="and buildinfo like '%HEAD%' ";

        //This is remaining of the subquery
        sql+="and testname = '"+scenarioName+"' "+
                                ")";

        if (isHead)
            sql += "and buildinfo like '%HEAD%' ";

        //This is remaining of main query
        sql += "order by teststart desc";

        Query query = em.createQuery(sql);
        query.setMaxResults(maxResults);
        List<PerfStat> list = query.getResultList();
        return list;
    }

    public List<String> getValidBuildLabelsBetweenGivenDates(String scenarioName, String prpcVersion, String startDate, String endDate) {
        String sql = "select max(teststart) as maxteststart, buildlabel, trialtype, runlevel, testname, isvalidrun, prpcversion from PerfStat " +
                "where trialtype='Performance' " +
                "and runlevel='optimized' " +
                "and testname='" + scenarioName + "' " +
                "and prpcversion='" + prpcVersion + "' " +
                "and isvalidrun='true' " +
                "and buildlabel like '%HEAD%' "+
                "and teststart > '" + startDate + "' " +
                "and teststart < '" + endDate + "' " +
                "group by trialtype, runlevel, testname, isvalidrun, prpcversion, buildlabel "+
                "order by buildlabel asc";

        Query query = em.createQuery(sql);
        List<Object[]> rows = query.getResultList();
        List<String> buildLabels = new ArrayList<>();
        for (Object[] row :
                rows) {
            buildLabels.add(row[1]+"");
        }

        return buildLabels;
    }

    /**
     * Find an efficent way for this later.
     * ========================
     *
     * @param scenarioName
     * @param param
     * @param currentBuildLabel
     */
    public ParamData getVariedBuildRankDetails(String scenarioName, String param, String currentBuildLabel, String prpcVersion, boolean isHead) {
        //TODO : Add prpcVersion to ParamData too.
        String sql = "FROM ParamData pd " +
                "where pd.scenarioData.testname='" + scenarioName + "' " +
                "and pd.paramName = '" + param + "' " +
                "and pd.scenarioData.buildLabel < '" + currentBuildLabel + "' " +
                "and pd.accuracy >= 50 "+
                "and ( pd.isDegraded = true or pd.isImproved = true ) "+
                "order by pd.scenarioData.buildLabel desc";
        Query query = em.createQuery(sql);
        List<ParamData> list = query.getResultList();
        if (list.size() == 0)
            return null;
        else{
            ParamData paramData = list.get(0);
            String sql2 ="from PerfStat ps1 "+
                            "where trialtype='Performance' "+
                            "and runlevel='optimized' "+
                            "and testname='"+scenarioName+"' "+
                            "and isvalidrun='true' "+
                            "and prpcversion='"+prpcVersion+"' "+
                            "and teststart = ( select max(ps2.teststart) from PerfStat ps2 "+
                                            "where ps1.trialtype=ps2.trialtype "+
                                            "and ps1.runlevel=ps2.runlevel "+
                                            "and ps1.testname=ps2.testname "+
                                            "and ps1.isvalidrun=ps2.isvalidrun "+
                                            "and ps1.prpcversion=ps2.prpcversion "+
                                            "and ps1.buildinfo=ps2.buildinfo "+
                                            "and ps1.buildlabel=ps2.buildlabel ) ";
            if (isHead)
                sql2 += "and buildinfo like '%HEAD%' ";

            sql2 += "and buildLabel >= '" +paramData.getScenarioData().getBuildLabel()+"' "+
                    "and buildLabel < '"+currentBuildLabel+"' "+
                    "order by buildlabel desc";


            Query query2 = em.createQuery(sql2);
            List<PerfStat> list2 = query2.getResultList();
            paramData.setVariedBuildRank(list2.size());

            return paramData;
        }
    }

    //TODO: PRPCVERSION HAS TO BE INCLUDED AND IT IS BETTER TO HAVE A BUILDDATE AS WELL AS SOMETIMES BUILD LABELS ARE GETTING MODIFIED.
    public ParamData getParamDataForAGivenBuild(String scenarioName, String paramName, String currentBuildLabel) {
        String sql = "FROM ParamData pd " +
                "where pd.scenarioData.testname='" + scenarioName + "' " +
                "and pd.paramName = '" + paramName + "' " +
                "and pd.scenarioData.buildLabel = '" + currentBuildLabel + "' " +
                "order by pd.scenarioData.buildLabel desc";
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

    public ScenarioData getScenarioData(String scenarioName, String buildLabel) {
        //TODO : Add prpcversion later if required.
        String sql = "from ScenarioData sd " +
                "where sd.testname = '"+scenarioName+"' " +
                "and sd.buildLabel = '"+buildLabel+"' ";

        Query query = em.createQuery(sql);
        List<ScenarioData> list = query.getResultList();
        if(list == null || list.size() == 0)
            return null;

        if(list.size()>1) {
            logger.debug("There are duplicates in scenario data. Scenario name : "+scenarioName+", buildLabel : "+buildLabel+", List size : " + list.size());
        }

        return list.get(0);
    }

    public Integer getNumberOfRecords(String scenarioName, String prpcVersion, boolean isHead) {
        String sql = "select count(*) FROM PerfStat " +
                "where trialtype='Performance' " +
                "and runlevel='optimized' " +
                "and testname='" + scenarioName + "' " +
                "and prpcversion='" + prpcVersion + "' " +
                "and isvalidrun='true' " +
                "and buildinfo like '%HEAD%' ";

        Query query = em.createQuery(sql);
        List<Object> rows = query.getResultList();
        if(rows.size() > 1)
            logger.info("Something is wrong with the query result. Current row size : "+rows.size());

        return Integer.parseInt(rows.get(0)+"");
    }

    /**
     * Currently this gives today's builds.
     * @return
     */
    public List<PerfStat> getLatestBuilds() {
        String sql = "from PerfStat " +
                "where trialtype='Performance' " +
                "and runlevel='optimized' " +
                "and isvalidrun='true' " +
                "and buildinfo like '%HEAD%' " +
                "and teststart > current_date";

        Query query = em.createQuery(sql);
        List<PerfStat> list = query.getResultList();
        return list;
    }

    /**
     * Retrieves builds on the given date.
     * @param date
     * @return
     */
    public List<PerfStat> getBuilds(String date) {
        String sql = "FROM PerfStat " +
                "where trialtype='Performance' " +
                "and runlevel='optimized' " +
                "and isvalidrun='true' " +
                "and buildinfo like '%HEAD%' " +
                "and cast(teststart as date) = '"+date+"' "+
                "order by buildlabel asc";

        Query query = em.createQuery(sql);
        List<PerfStat> list = query.getResultList();
        return list;
    }

    /**
     * Retrieves builds on the given date.
     * @param date
     * @return
     */
    public List<PerfStat> getBuilds(String date, String scenarioName) {
        String sql = "FROM PerfStat " +
                "where trialtype='Performance' " +
                "and runlevel='optimized' " +
                "and isvalidrun='true' " +
                "and buildinfo like '%HEAD%' " +
                "and cast(teststart as date) = '"+date+"' "+
                "and testname = '"+scenarioName+"' "+
                "and prpcversion <> null "+
                "order by buildlabel asc";

        Query query = em.createQuery(sql);
        List<PerfStat> list = query.getResultList();
        return list;
    }
}
