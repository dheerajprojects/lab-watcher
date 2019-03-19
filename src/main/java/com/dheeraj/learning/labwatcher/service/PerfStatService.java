package com.dheeraj.learning.labwatcher.service;

import com.dheeraj.learning.labwatcher.dao.PerfStatDAO;
import com.dheeraj.learning.labwatcher.dto.PerfStatDTO;
import com.dheeraj.learning.labwatcher.entity.PerfStat;
import com.dheeraj.learning.labwatcher.util.Mapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PerfStatService {
    @Autowired
    private PerfStatDAO perfStatDAO;

    public List<PerfStatDTO> getPerfStatsBetweenBuilds(String scenarioName, String prpcVersion, String startBuildLabel, String endBuildLabel, int maxResults) {
        List<PerfStat> list = perfStatDAO.getPerfStatsBetweenBuilds(scenarioName, prpcVersion, startBuildLabel, endBuildLabel, maxResults);
        List<PerfStatDTO> dtoList = Mapper.copyResultsToDTO(list);
        return dtoList;
    }

    public PerfStatDTO getPerfStatForAGivenBuild(String scenarioName, String buildLabel) {
        PerfStat perfStat = perfStatDAO.getPerfStatForAGivenBuild(scenarioName, buildLabel);
        PerfStatDTO perfStatDTO = new PerfStatDTO();
        BeanUtils.copyProperties(perfStat, perfStatDTO);
        return  perfStatDTO;
    }

    public List<PerfStatDTO> getPerfStatsForLastNBuilds(String scenarioName, String prpcVersion,  String endBuildLabel, int maxResults, boolean isHead) {
        List<PerfStat> list = perfStatDAO.getPerfStatsForLastNBuilds(scenarioName, prpcVersion, endBuildLabel, maxResults, isHead);

        List<PerfStatDTO> dtoList = Mapper.copyResultsToDTO(list);
        return dtoList;
    }

    public List<String> getValidBuildLabels(String scenarioName, String prpcVersion, String startDate, String endDate) {
        List<String> list = perfStatDAO.getValidBuildLabels(scenarioName, prpcVersion, startDate, endDate);
        return list;
    }
}
