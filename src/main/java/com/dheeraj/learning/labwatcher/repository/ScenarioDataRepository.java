package com.dheeraj.learning.labwatcher.repository;

import com.dheeraj.learning.labwatcher.entity.ScenarioData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScenarioDataRepository extends JpaRepository<ScenarioData, Integer> {
}
