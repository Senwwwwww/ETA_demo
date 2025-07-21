package com.example.EAT_demo.converter;

import com.example.EAT_demo.dto.ApplicationDTO;
import com.example.EAT_demo.dto.EmployeeDTO;
import com.example.EAT_demo.dto.UserDTO;
import com.example.EAT_demo.pojo.Application;

@SuppressWarnings("all")
public class ApplicationConverter {


    public static ApplicationDTO convertApplication(Application application){
        ApplicationDTO applicationDTO = new ApplicationDTO();

        applicationDTO.setApplicationId(application.getApplicationId());
        applicationDTO.setApplicationReason(application.getApplicationReason());
        applicationDTO.setApplicationIsDelete(application.getApplicationIsDelete());
        applicationDTO.setApplicationStatus(application.getApplicationStatus());
        applicationDTO.setApplicationType(application.getApplicationType());
        applicationDTO.setStartTime(application.getStartTime());
        applicationDTO.setEndTime(application.getEndTime());

        // 添加 User 信息到 ApplicationDTO
        if (application.getUser() != null) {
            UserDTO userDTO = new UserDTO();
            userDTO.setUserId(application.getUser().getUserId());
            userDTO.setUsername(application.getUser().getUsername());
            userDTO.setEmail(application.getUser().getEmail());
            userDTO.setPassword(application.getUser().getPassword());
            userDTO.setRole(application.getUser().getRole());
            applicationDTO.setUserDTO(userDTO);
        }

        // 添加 employee 信息到 ApplicationDTO
        if (application.getUser() != null && application.getUser().getEmployee() != null) {
            EmployeeDTO employeeDTO = new EmployeeDTO();
            employeeDTO.setEmployeeId(application.getUser().getEmployee().getEmployeeId());
            employeeDTO.setRealName(application.getUser().getEmployee().getRealName());
            employeeDTO.setEmployeeIsDelete(application.getUser().getEmployee().getEmployeeIsDelete());

            applicationDTO.getUserDTO().setEmployeeDTO(employeeDTO);
        }

        return  applicationDTO;
    }

    public static Application convertApplication(ApplicationDTO applicationDTO){
        Application application = new Application();

        application.setApplicationReason(applicationDTO.getApplicationReason());
        application.setApplicationIsDelete(applicationDTO.getApplicationIsDelete());
        application.setApplicationType(applicationDTO.getApplicationType());
        application.setStartTime(applicationDTO.getStartTime());
        application.setEndTime(applicationDTO.getEndTime());

        return  application;
    }

}
