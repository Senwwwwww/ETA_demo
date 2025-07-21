package com.example.EAT_demo.service;

import com.example.EAT_demo.converter.ApplicationConverter;
import com.example.EAT_demo.dao.ApplicationRepository;
import com.example.EAT_demo.dao.EmployeeRepository;
import com.example.EAT_demo.dao.UserRepository;
import com.example.EAT_demo.dto.ApplicationDTO;
import com.example.EAT_demo.exception.ApplicationContextException;
import com.example.EAT_demo.pojo.Application;
import com.example.EAT_demo.pojo.User;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@SuppressWarnings("all")
@Service
public class ApplicationServiceImpl implements ApplicationService {
    @Resource
    @Autowired
    private ApplicationRepository applicationRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public long addNewApplicationInfo(ApplicationDTO applicationDTO, Long userId) {
        User user = userRepository.findByUserId(userId);

        if (user.getEmployee() == null) {
            throw new ApplicationContextException("未绑定员工");
        }
        Application application = ApplicationConverter.convertApplication(applicationDTO);
        application.setUser(user);
        applicationRepository.save(application);
        return application.getApplicationId();
    }

    @Override
    public List<ApplicationDTO> getUserApplicationHistory(Long userId) {
        User user = userRepository.findByUserId(userId);
        List<Application> applicationINDB = applicationRepository.findByUser(user);
        if(applicationINDB.isEmpty())
        {
            throw new ApplicationContextException("没有申请记录");
        }

        List<ApplicationDTO> applicationDTOs = applicationINDB.stream()
                .map(ApplicationConverter::convertApplication)
                .collect(Collectors.toList());
        return applicationDTOs;
    }

    @Override
    public List<ApplicationDTO> getAllApplicationHistory() {
        List<Application> applicationINDB = applicationRepository.findAll();
        if(applicationINDB.isEmpty())
        {
            throw new ApplicationContextException("没有申请记录");
        }

        // 保存修改后的 application 数据
        applicationRepository.saveAll(applicationINDB);

        List<ApplicationDTO> applicationDTOs = applicationINDB.stream()
                .map(ApplicationConverter::convertApplication)
                .collect(Collectors.toList());
        return applicationDTOs;
    }

    @Transactional
    @Override
    public ApplicationDTO updateApplicationStatusById(ApplicationDTO applicationDTO) {
        Application application = applicationRepository.findById(applicationDTO.getApplicationId()).get();
        application.setApplicationStatus(applicationDTO.getApplicationStatus());
        applicationRepository.save(application);
        return ApplicationConverter.convertApplication(application);
    }

}
