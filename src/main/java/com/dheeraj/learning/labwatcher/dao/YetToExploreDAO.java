package com.dheeraj.learning.labwatcher.dao;

import com.dheeraj.learning.labwatcher.entity.PerfStat;
import com.dheeraj.learning.labwatcher.repository.PerfStatsRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class YetToExploreDAO {

    private PerfStatsRepository perfStatsRepository;

    public YetToExploreDAO(PerfStatsRepository perfStatsRepository){
        this.perfStatsRepository = perfStatsRepository;
    }

    /**
     * This method uses advanced spring repository concept to get the responses.
     * @return
     */
    public List<PerfStat> getLatestBuild() {
        PageRequest pageRequest = PageRequest.of(0, 10, Sort.by(Sort.Direction.ASC, "Teststart"));
        Page<PerfStat> page = perfStatsRepository.findAll(pageRequest);

        return page.getContent();
    }
}
