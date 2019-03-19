package com.dheeraj.learning.labwatcher.repository;

import com.dheeraj.learning.labwatcher.entity.PerfStat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PerfStatsRepository extends JpaRepository<PerfStat, String> {
}
