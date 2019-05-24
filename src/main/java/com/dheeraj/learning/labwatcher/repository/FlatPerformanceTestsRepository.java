package com.dheeraj.learning.labwatcher.repository;

import com.dheeraj.learning.labwatcher.entity.FlatPerformanceTestsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlatPerformanceTestsRepository extends JpaRepository<FlatPerformanceTestsEntity, Integer> {
}
