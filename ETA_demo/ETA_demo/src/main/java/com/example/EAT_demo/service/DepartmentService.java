package com.example.EAT_demo.service;

import com.example.EAT_demo.dto.DepartmentDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DepartmentService {

    long addAndUpdateNewDepartmentInfo(DepartmentDTO departmentDTO);

    List<DepartmentDTO> getAllDepartmentInfo();

    long deleteDepartmentInfo(Long departmentId);
}
