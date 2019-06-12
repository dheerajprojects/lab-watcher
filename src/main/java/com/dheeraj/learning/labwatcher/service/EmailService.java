package com.dheeraj.learning.labwatcher.service;

import com.dheeraj.learning.labwatcher.dto.ScenarioDataDTO;
import org.springframework.stereotype.Service;

@Service
public interface EmailService {
    void sendEmail(ScenarioDataDTO scenarioDataDTO);
}
