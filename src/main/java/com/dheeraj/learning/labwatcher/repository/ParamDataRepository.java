package com.dheeraj.learning.labwatcher.repository;

import com.dheeraj.learning.labwatcher.entity.ParamData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ParamDataRepository  extends JpaRepository<ParamData, Integer> {
    List<ParamData> findByAccuracyGreaterThan(Double accuracy);
}
