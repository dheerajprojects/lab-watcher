package com.dheeraj.learning.labwatcher.dao;

import com.dheeraj.learning.labwatcher.entity.ParamData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.concurrent.RecursiveTask;

/**
 *  * /analyzedresults/scenarios/              --all scenario
 *  * /analyzedresults/scenarios/scenarioname  --given scenario
 *  * /analyzedresults/builds/buildlabel       --all scenarios for the given build
 */
@Repository
@Transactional
public class ParamDataDAO {
    Logger logger = LoggerFactory.getLogger(ParamDataDAO.class);

    @PersistenceContext
    EntityManager em;


    public List<ParamData> getAll(Double accuracy, String scenarioName, String buildLabel, String perfMetric, Boolean isDegraded) {
        String sql = "FROM ParamData pd " +
                "where pd.scenarioData.testname is not NULL ";
        if(accuracy != null)
            sql+="and pd.accuracy >= "+accuracy+" ";
        if(scenarioName != null)
            sql+="and pd.scenarioData.testname='" + scenarioName + "' ";
        if(buildLabel != null)
            sql+="and pd.scenarioData.buildLabel = '" + buildLabel + "' ";
        if(perfMetric != null)
            sql+="and pd.paramName = '" + perfMetric + "' ";
        if(isDegraded != null) {
            if(isDegraded)
                sql+="and pd.isDegraded = true ";
            else
                sql+="and pd.isImproved = true ";
        }


        sql+="order by pd.scenarioData.buildLabel desc";

        Query query = em.createQuery(sql);
        List<ParamData> list = query.getResultList();
        return list;
    }

    public List<ParamData> getAll() {
        return getAll(100.0, null, null, null,null);
    }

    public List<ParamData> getDataOnGivenScenario(String scenarioName){
        return getAll(100.0, scenarioName, null, null, null);
    }

    public List<ParamData> getDataOnGivenBuild(String buildLabel){
        return getAll(100.0, null, buildLabel, null, null);
    }

    public List<ParamData> getDataOnGivenPerfMetric(String perfMetric){
        return getAll(100.0, null, null, perfMetric, null);
    }

    public List<ParamData> getDataOnGivenPerfMetric(String perfMetric, Boolean isDegraded){
        return getAll(100.0, null, null, perfMetric, isDegraded);
    }
}
