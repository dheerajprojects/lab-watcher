package com.dheeraj.learning.labwatcher.service;

import com.dheeraj.learning.labwatcher.dao.PerfStatDAO;
import com.dheeraj.learning.labwatcher.dto.ParamDataDTO;
import com.dheeraj.learning.labwatcher.dto.ScenarioDataDTO;
import com.dheeraj.learning.labwatcher.entity.ScenarioData;
import com.dheeraj.learning.labwatcher.repository.ScenarioDataRepository;
import com.dheeraj.learning.labwatcher.util.DegradationIdentificationUtil;
import com.dheeraj.learning.labwatcher.util.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Service
public class BuildThreadService {

    Logger logger = LoggerFactory.getLogger(BuildThreadService.class);

    @Autowired
    private PerfStatDAO perfStatDAO;

    @Autowired
    private ScenarioDataRepository scenarioDataRepository;

    @Autowired
    private PerfMetricThreadService perfMetricThreadService;

    /**
     * This method analyzes last n (maxResults #50 for now) perfstats(previous to the #testBuild} and calculates mean and
     * standard deviation for the given parameters.
     * Then it calculates if the #testBuild metrics are degraded/improved based on how far they are from standard deviation.
     * <p>
     * validateInputData(scenarioName, paramList, prpcVersion, testBuild);
     * TODO : Validate relevant database data. Whether user requested data exist in the database. Fire perfstat db with scenarioname, prpcversion, testbuild
     * TODO   and see if we get atleast one row. If not then throw error that the requested build doesn't exist.
     * TODO : Later we might give more information to the user whether scenario is invalid, prpcversion is invalid or build label is invalid.
     * TODO : map/create exact parameters from entity to dto.
     *
     * @param scenarioName Scenario to be tested.
     * @param paramList    Parameters to be tested.
     * @param prpcVersion  Prpcversion of the testing builds.
     * @param testBuild    The build for which degradation analysis to be done.
     * @return This an object which contains all the degradation details.
     */
    @Async
    public CompletableFuture<ScenarioDataDTO> doDegradationAnalysis(String scenarioName, List<String> paramList, String prpcVersion, String testBuild, boolean isHead) {
        logger.trace("Analyzing " + scenarioName + ", " + prpcVersion + ", " + testBuild + "...");
        ScenarioDataDTO scenarioDataDTO = new ScenarioDataDTO();
        scenarioDataDTO.setTestname(scenarioName);
        scenarioDataDTO.setLatestbuild(testBuild);

        Map<String, ParamDataDTO> currentBuildParamMap = new HashMap<>();
        List<CompletableFuture<ParamDataDTO>> list = new ArrayList<>();
        for (String perfMetric : paramList) {
            CompletableFuture<ParamDataDTO> futures = perfMetricThreadService.analysePerfMetric(scenarioName, perfMetric, prpcVersion, testBuild, isHead);
            list.add(futures);
        }

        CompletableFuture<Void> allFutures = CompletableFuture.allOf(list.toArray(new CompletableFuture[list.size()]));
        CompletableFuture.allOf(allFutures);

        for (CompletableFuture<ParamDataDTO> dto : list) {
            try {
                currentBuildParamMap.put(dto.get().getParamName(), dto.get());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        scenarioDataDTO.setMap(currentBuildParamMap);

        //Saving only if atleast one parameter is degraded/improved;
        //Ensure that this works even if we rerun the analysis for the same build.
        boolean isVaried = DegradationIdentificationUtil.isAnyParamVaried(currentBuildParamMap);
        if (isVaried) {
            ScenarioData scenarioData = perfStatDAO.getScenarioData(scenarioName, testBuild);
            if (scenarioData != null)
                Mapper.map(scenarioDataDTO, scenarioData, paramList);
            else
                scenarioData = Mapper.convert(scenarioDataDTO);

            scenarioDataRepository.save(scenarioData);
        }

        return CompletableFuture.completedFuture(scenarioDataDTO);
    }
}
