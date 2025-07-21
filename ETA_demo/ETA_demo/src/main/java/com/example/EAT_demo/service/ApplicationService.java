package com.example.EAT_demo.service;

import com.example.EAT_demo.dto.ApplicationDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SuppressWarnings("all")
@Service
public interface ApplicationService {

    long addNewApplicationInfo(ApplicationDTO applicationDTO, Long userId);

    List<ApplicationDTO> getUserApplicationHistory(Long userId);

    List<ApplicationDTO> getAllApplicationHistory();

    @Transactional
    ApplicationDTO updateApplicationStatusById(ApplicationDTO applicationDTO);
}
