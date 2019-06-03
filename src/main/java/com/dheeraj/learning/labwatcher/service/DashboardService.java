package com.dheeraj.learning.labwatcher.service;

import com.dheeraj.learning.labwatcher.dao.ParamDataDAO;
import com.dheeraj.learning.labwatcher.dao.PerfStatDAO;
import com.dheeraj.learning.labwatcher.dto.ParamDataDTO;
import com.dheeraj.learning.labwatcher.entity.ParamData;
import com.dheeraj.learning.labwatcher.repository.ParamDataRepository;
import com.dheeraj.learning.labwatcher.repository.ScenarioDataRepository;
import com.dheeraj.learning.labwatcher.util.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DashboardService {
    @Autowired
    ParamDataRepository paramDataRepository;

    @Autowired
    ScenarioDataRepository scenarioDataRepository;

    @Autowired
    PerfStatDAO perfStatDAO;

    @Autowired
    ParamDataDAO paramDataDAO;

    public List<ParamDataDTO> getAll() {
        List<ParamData> paramDatas = paramDataDAO.getAll();
        List<ParamDataDTO> paramDataDTOs = Mapper.convertResultsToDTO(paramDatas);
        return paramDataDTOs;
    }

    public List<ParamDataDTO> getDataOnGivenScenario(String scenarioName) {
        List<ParamData> paramDatas = paramDataDAO.getDataOnGivenScenario(scenarioName);
        List<ParamDataDTO> paramDataDTOs = Mapper.convertResultsToDTO(paramDatas);
        return paramDataDTOs;
    }

    public List<ParamDataDTO> getDataOnGivenBuild(String buildInfo) {
        List<ParamData> paramDatas = paramDataDAO.getDataOnGivenBuild(buildInfo);
        List<ParamDataDTO> paramDataDTOs = Mapper.convertResultsToDTO(paramDatas);
        return paramDataDTOs;
    }

    public List<ParamDataDTO> getDataOnGivenPerfMetric(String perfMetric) {
        List<ParamData> paramDatas = paramDataDAO.getDataOnGivenPerfMetric(perfMetric);
        List<ParamDataDTO> paramDataDTOs = Mapper.convertResultsToDTO(paramDatas);
        return paramDataDTOs;
    }

    public List<ParamDataDTO> getDataOnGivenPerfMetric(String perfMetric, Boolean isDegraded) {
        List<ParamData> paramDatas = paramDataDAO.getDataOnGivenPerfMetric(perfMetric, isDegraded);
        List<ParamDataDTO> paramDataDTOs = Mapper.convertResultsToDTO(paramDatas);
        return paramDataDTOs;
    }

}
