package com.example.EAT_demo.dao;

import com.example.EAT_demo.pojo.PersonalInformation;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonalInformationRepository extends JpaRepository<PersonalInformation, Long> {
    PersonalInformation findByuserId(Long userId);
}
