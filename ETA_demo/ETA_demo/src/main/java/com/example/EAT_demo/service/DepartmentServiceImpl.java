package com.example.EAT_demo.service;

import com.example.EAT_demo.converter.DepartmentConverter;
import com.example.EAT_demo.dao.DepartmentRepository;
import com.example.EAT_demo.dto.DepartmentDTO;
import com.example.EAT_demo.pojo.Department;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@SuppressWarnings("all")
@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Resource
    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public long addAndUpdateNewDepartmentInfo(DepartmentDTO departmentDTO) {
        Department departmentINDB = departmentRepository.findByDepartmentId(departmentDTO.getDepartmentId());
        // 如果已存在则更新，不存在则添加
        if (departmentINDB != null) {
            departmentINDB.setDepartmentName(departmentDTO.getDepartmentName());
            departmentINDB.setDepartmentContent(departmentDTO.getDepartmentContent());
            departmentRepository.save(departmentINDB);
            return departmentINDB.getDepartmentId();
        } else {
            Department department = DepartmentConverter.convertDepartment(departmentDTO);
            departmentRepository.save(department);
            return department.getDepartmentId();
        }
    }

    @Override
    public List<DepartmentDTO> getAllDepartmentInfo() {
        List<Department> departments = departmentRepository.findAll(); // 从数据库获取所有部门
        return departments.stream()
                .map(DepartmentConverter::convertDepartment) // 假设你有一个转换方法将 Department 转换为 DepartmentDTO
                .collect(Collectors.toList());
    }

    @Override
    public long deleteDepartmentInfo(Long departmentId) {
        Department departmentINDB = departmentRepository.findByDepartmentId(departmentId);
        // 如果存在则删除
        if (departmentINDB != null) {
            departmentRepository.delete(departmentINDB);
        }
        return departmentINDB.getDepartmentId();
    }
}
