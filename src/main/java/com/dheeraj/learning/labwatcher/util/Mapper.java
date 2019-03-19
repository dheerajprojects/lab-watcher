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

    public static List<ParamData> copyResultsToDTO(Collection<ParamDataDTO> results) {
        List<ParamData> paramsData = new ArrayList<>();

        for (Object dto : results) {
            ParamData paramData = new ParamData();
            BeanUtils.copyProperties(dto, paramData);
            paramsData.add(paramData);
        }

        return paramsData;
    }

    public static ScenarioData convert(ScenarioDataDTO scenarioDataDTO) {
        ScenarioData scenarioData = new ScenarioData();
        scenarioData.setTestname(scenarioDataDTO.getTestname());
        scenarioData.setLatestbuild(scenarioDataDTO.getLatestbuild());

        Set<ParamData> set = new HashSet<>();
        set.addAll(copyResultsToDTO(scenarioDataDTO.getMap().values()));

        scenarioData.setSet(set);

        return scenarioData;
    }


}
