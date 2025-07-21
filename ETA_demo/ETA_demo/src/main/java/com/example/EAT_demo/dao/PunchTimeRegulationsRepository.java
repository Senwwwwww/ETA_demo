package com.example.EAT_demo.dao;

import com.example.EAT_demo.pojo.PunchTimeRegulations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PunchTimeRegulationsRepository extends JpaRepository<PunchTimeRegulations, Long> {

    @Query("SELECT p FROM PunchTimeRegulations p WHERE true ")
    PunchTimeRegulations findRegulations();
}
