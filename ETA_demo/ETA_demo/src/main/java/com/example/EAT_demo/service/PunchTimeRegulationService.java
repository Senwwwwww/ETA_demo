package com.example.EAT_demo.service;

import com.example.EAT_demo.pojo.PunchTimeRegulations;
import org.springframework.stereotype.Service;

@SuppressWarnings("all")
@Service
public interface PunchTimeRegulationService {
    PunchTimeRegulations addAndUpdatePunchTimeRegulations(PunchTimeRegulations punchTimeRegulations);

    PunchTimeRegulations getPunchTimeRegulations();
}
