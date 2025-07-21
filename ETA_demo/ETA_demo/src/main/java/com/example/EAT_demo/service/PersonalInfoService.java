package com.example.EAT_demo.service;

import com.example.EAT_demo.dto.PersonalInformationDTO;
import org.springframework.stereotype.Service;

@Service
public interface PersonalInfoService {

    PersonalInformationDTO addAndUpdatePersonalInfo(PersonalInformationDTO personalInformationDTO, Long userId);
}
