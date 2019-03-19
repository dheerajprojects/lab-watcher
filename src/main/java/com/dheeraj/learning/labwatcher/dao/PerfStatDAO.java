package com.dheeraj.learning.labwatcher.dao;

import com.dheeraj.learning.labwatcher.entity.PerfStat;
import com.dheeraj.learning.labwatcher.repository.PerfStatsRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional(readOnly=true)
public class PerfStatDAO {

    @PersistenceContext
    EntityManager em;

    public List<PerfStat> getPerfStatsBetweenBuilds(String scenarioName, String prpcVersion, String startBuildLabel, String endBuildLabel, int maxResults) {
        String sql2 = "select s " +
                "from Student s " +
                "where s.studentName = :studentName ";

        String sql = "FROM PerfStat " +
        "where trialtype='Performance' " +
                "and runlevel='optimized' " +
                "and testname='" + scenarioName + "' " +
                "and prpcversion='" + prpcVersion+ "' " +
                "and isvalidrun='true'" +
                "and buildlabel >= '" + startBuildLabel + "' " +
                "and buildlabel <= '" + endBuildLabel + "' " +
                "order by teststart desc";

        Query query = em.createQuery(sql);
        query.setMaxResults(maxResults);
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
                "and prpcversion='" + prpcVersion+ "' " +
                "and isvalidrun='true'" +
                "and buildlabel < '" + endBuildLabel + "' ";

        if(isHead)
            sql += "and buildinfo like '%HEAD%' ";

        sql += "order by teststart desc";

        Query query = em.createQuery(sql);
        query.setMaxResults(maxResults);
        List<PerfStat> list = query.getResultList();
        return list;
    }

    public List<String> getValidBuildLabels(String scenarioName, String prpcVersion, String startDate, String endDate) {
        String sql = "FROM PerfStat " +
                "where trialtype='Performance' " +
                "and runlevel='optimized' " +
                "and testname='" + scenarioName + "' " +
                "and prpcversion='" + prpcVersion+ "' " +
                "and isvalidrun='true'" +
                "and teststart > '" + startDate + "' " +
                "and teststart < '" + endDate + "' " +
                "order by buildlabel asc";

        Query query = em.createQuery(sql);
        List<PerfStat> list = query.getResultList();
        List<String> buildLabels = new ArrayList<>();
        for (PerfStat perfStat :
                list) {
            buildLabels.add(perfStat.getBuildlabel());
        }

        return buildLabels;
    }
}
