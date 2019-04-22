package com.dheeraj.learning.labwatcher.util;

import com.dheeraj.learning.labwatcher.dto.ParamDataDTO;
import com.dheeraj.learning.labwatcher.dto.PerfStatDTO;
import com.dheeraj.learning.labwatcher.dto.ScenarioDataDTO;
import com.dheeraj.learning.labwatcher.entity.ParamData;
import com.dheeraj.learning.labwatcher.entity.PerfStat;
import com.dheeraj.learning.labwatcher.entity.ScenarioData;
import org.springframework.beans.BeanUtils;

import java.util.*;

/**
 * TODO : Try spring provided modelmapper : http://modelmapper.org/getting-started/
 */
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

    public static ScenarioDataDTO convert(ScenarioData scenarioData) {
        ScenarioDataDTO scenarioDataDTO = new ScenarioDataDTO();
        scenarioDataDTO.setTestname(scenarioData.getTestname());
        scenarioDataDTO.setLatestbuild(scenarioData.getBuildLabel());

        Map<String, ParamDataDTO> paramDataDTOMap = new HashMap<>();
        Set<ParamData> paramDataSet = scenarioData.getSet();
        for (ParamData paramData : paramDataSet) {
            ParamDataDTO paramDataDTO = convert(paramData);
            paramDataDTOMap.put(paramDataDTO.getParamName(), paramDataDTO);
        }
        scenarioDataDTO.setMap(paramDataDTOMap);

        return scenarioDataDTO;
    }

    public static ScenarioData map(ScenarioDataDTO scenarioDataDTO, ScenarioData scenarioData, List<String> paramList) {
        scenarioData.setTestname(scenarioDataDTO.getTestname());
        scenarioData.setBuildLabel(scenarioDataDTO.getLatestbuild());

        //Temporary map to easily access paramdata set.
        Map<String, ParamData> temporaryMap = new HashMap<>();
        for (ParamData paramData: scenarioData.getSet()) {
            temporaryMap.put(paramData.getParamName(), paramData);
        }

        for (String paramName: paramList) {
            if(temporaryMap.containsKey(paramName))
                map(scenarioDataDTO.getMap().get(paramName), temporaryMap.get(paramName));
            else {
                ParamData paramData = new ParamData();
                BeanUtils.copyProperties(scenarioDataDTO.getMap().get(paramName), paramData);
                scenarioData.getSet().add(paramData);
            }
        }

        return scenarioData;
    }

    public static ParamData map(ParamDataDTO paramDataDTO, ParamData paramData) {
        BeanUtils.copyProperties(paramDataDTO, paramData);

        return paramData;
    }

    public static ParamDataDTO convert(ParamData paramData) {
        ParamDataDTO paramDataDTO = new ParamDataDTO();

        BeanUtils.copyProperties(paramData, paramDataDTO);

        paramDataDTO.setBaselineBuildPosition(paramData.getVariedBuildRank());
        paramDataDTO.setScenarioName(paramData.getScenarioData().getTestname());
        paramDataDTO.setBuildLabel(paramData.getScenarioData().getBuildLabel());

        return paramDataDTO;
    }

}
