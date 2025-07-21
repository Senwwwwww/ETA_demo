package com.example.EAT_demo.service;

import com.example.EAT_demo.dao.PunchTimeRegulationsRepository;
import com.example.EAT_demo.exception.UserAuthenticationException;
import com.example.EAT_demo.pojo.PunchTimeRegulations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@SuppressWarnings("all")
@Service
public class PunchTimeRegulationServiceImpl implements PunchTimeRegulationService {

    @Autowired
    private PunchTimeRegulationsRepository punchTimeRegulationsRepository;

    @Override
    public PunchTimeRegulations addAndUpdatePunchTimeRegulations(PunchTimeRegulations punchTimeRegulations) {
        if (punchTimeRegulationsRepository.findById(punchTimeRegulations.getAdminId()).isPresent())
        {
            PunchTimeRegulations punchTimeRegulationsINDB = punchTimeRegulationsRepository.findById(punchTimeRegulations.getAdminId()).get();

            punchTimeRegulationsINDB.setClockInTime(punchTimeRegulations.getClockInTime());
            punchTimeRegulationsINDB.setClockOutTime(punchTimeRegulations.getClockOutTime());
            punchTimeRegulationsINDB.setClockBeLate(punchTimeRegulations.getClockBeLate());

            punchTimeRegulationsINDB.setAbsenteeismPolicy(punchTimeRegulations.getAbsenteeismPolicy());
            punchTimeRegulationsINDB.setOvertimeRules(punchTimeRegulations.getOvertimeRules());
            punchTimeRegulationsINDB.setLeaveProcess(punchTimeRegulations.getLeaveProcess());

            punchTimeRegulationsRepository.save(punchTimeRegulationsINDB);
            return punchTimeRegulationsINDB;
        }
        else {
            return punchTimeRegulationsRepository.save(punchTimeRegulations);
        }
    }

    @Override
    public PunchTimeRegulations getPunchTimeRegulations() {
        return punchTimeRegulationsRepository.findRegulations();
    }


}
