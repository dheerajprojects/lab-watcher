package com.dheeraj.learning.labwatcher.util;

import com.dheeraj.learning.labwatcher.dto.ParamDataDTO;
import com.dheeraj.learning.labwatcher.dto.PerfStatDTO;
import com.dheeraj.learning.labwatcher.dto.ScenarioDataDTO;
import com.dheeraj.learning.labwatcher.entity.ParamData;
import com.dheeraj.learning.labwatcher.entity.PerfStat;
import com.dheeraj.learning.labwatcher.entity.ScenarioData;
import org.springframework.beans.BeanUtils;

import java.util.*;

public class Mapper {
    public static List<PerfStatDTO> copyResultsToDTO(List<PerfStat> results) {
        List resultDTOs = new ArrayList();

        for (Object ent : results) {
            PerfStatDTO dto = new PerfStatDTO();
            BeanUtils.copyProperties(ent, dto);
            resultDTOs.add(dto);
        }

        return resultDTOs;
    }

    public static List<ParamData> copyResultsToEntity(Collection<ParamDataDTO> results, ScenarioData scenarioData) {
        List<ParamData> paramsData = new ArrayList<>();

        for (Object dto : results) {
            ParamData paramData = new ParamData();
            BeanUtils.copyProperties(dto, paramData);
            paramData.setScenarioData(scenarioData);
            paramsData.add(paramData);
        }

        return paramsData;
    }

    public static ScenarioData convert(ScenarioDataDTO scenarioDataDTO) {
        ScenarioData scenarioData = new ScenarioData();
        scenarioData.setTestname(scenarioDataDTO.getTestname());
        scenarioData.setBuildLabel(scenarioDataDTO.getLatestbuild());

        Set<ParamData> set = new HashSet<>();
        set.addAll(copyResultsToEntity(scenarioDataDTO.getMap().values(), scenarioData));

        scenarioData.setSet(set);

        return scenarioData;
    }

    public static ParamDataDTO convert(ParamData paramData) {
        ParamDataDTO paramDataDTO = new ParamDataDTO();

        BeanUtils.copyProperties(paramData, paramDataDTO);

        paramDataDTO.setScenarioName(paramData.getScenarioData().getTestname());
        paramDataDTO.setBuildLabel(paramData.getScenarioData().getBuildLabel());

        return paramDataDTO;
    }

}
