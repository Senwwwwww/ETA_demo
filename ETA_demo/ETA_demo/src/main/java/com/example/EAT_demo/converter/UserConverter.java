package com.example.EAT_demo.converter;

import com.example.EAT_demo.dto.EmployeeDTO;
import com.example.EAT_demo.dto.PersonalInformationDTO;
import com.example.EAT_demo.dto.UserDTO;
import com.example.EAT_demo.pojo.PersonalInformation;
import com.example.EAT_demo.pojo.User;

import static com.example.EAT_demo.converter.EmployeeConverter.convertEmployee;
import static com.example.EAT_demo.converter.PersonalInformationConvert.convertPersonalInformation;

@SuppressWarnings("all")
public class UserConverter {

    public static UserDTO convertUser(User user){
        UserDTO userDTO = new UserDTO();

        userDTO.setUserId(user.getUserId());
        userDTO.setUsername(user.getUsername());
        userDTO.setName(user.getName());
        userDTO.setPassword(user.getPassword());
        userDTO.setEmail(user.getEmail());
        userDTO.setUserIsDelete(user.getUserIsDelete());
        userDTO.setRole(user.getRole());
        userDTO.setBioinformatics(user.getBioinformatics());
//        userDTO.setPersonalInformationDTO(user.getPersonalInformation());

        return  userDTO;
    }

    public static User convertUser(UserDTO userDTO){
        User user = new User();

        user.setUsername(userDTO.getUsername());
        user.setName(userDTO.getName());
        user.setPassword(userDTO.getPassword());
        user.setEmail(userDTO.getEmail());
        user.setUserIsDelete(userDTO.getUserIsDelete());

        return  user;
    }

    //用户信息
    public static UserDTO convertUserandPersonalInfomation(User user){
        UserDTO userDTO = new UserDTO();

        userDTO.setUserId(user.getUserId());
        userDTO.setUsername(user.getUsername());
        userDTO.setName(user.getName());
        userDTO.setPassword(user.getPassword());
        userDTO.setEmail(user.getEmail());
        userDTO.setUserIsDelete(user.getUserIsDelete());
        userDTO.setRole(user.getRole());
        userDTO.setBioinformatics(user.getBioinformatics());

        PersonalInformationDTO personalInfoDTO = convertPersonalInformation(user.getPersonalInformation());
        userDTO.setPersonalInformationDTO(personalInfoDTO);

        return  userDTO;
    }

    public static User convertUserandPersonalInfomation(UserDTO userDTO){
        User user = new User();

        user.setUsername(userDTO.getUsername());
        user.setName(userDTO.getName());
        user.setPassword(userDTO.getPassword());
        user.setEmail(userDTO.getEmail());
        user.setUserIsDelete(userDTO.getUserIsDelete());
        user.setRole(userDTO.getRole());
        user.setBioinformatics(userDTO.getBioinformatics());

        PersonalInformation personalInfo = convertPersonalInformation(userDTO.getPersonalInformationDTO());
        user.setPersonalInformation(personalInfo);

        return  user;
    }

    //所有信息
    public static UserDTO convertUserAllInfo(User user){
        UserDTO userDTO = new UserDTO();

        userDTO.setUserId(user.getUserId());
        userDTO.setUsername(user.getUsername());
        userDTO.setName(user.getName());
        userDTO.setPassword(user.getPassword());
        userDTO.setEmail(user.getEmail());
        userDTO.setUserIsDelete(user.getUserIsDelete());

        userDTO.setRole(user.getRole());
        userDTO.setBioinformatics(user.getBioinformatics());

        // 处理个人信息，若为空则设置为 null
        PersonalInformationDTO personalInfoDTO = (user.getPersonalInformation() != null)
                ? convertPersonalInformation(user.getPersonalInformation())
                : null;
        userDTO.setPersonalInformationDTO(personalInfoDTO);

        // 处理员工信息，若为空则设置为 null
        EmployeeDTO employeeDTO = (user.getEmployee() != null)
                ? convertEmployee(user.getEmployee())
                : null;
        userDTO.setEmployeeDTO(employeeDTO);

        return  userDTO;
    }

    public static User convertUserAllInfo(UserDTO userDTO){
        User user = new User();

        user.setUsername(userDTO.getUsername());
        user.setName(userDTO.getName());
        user.setPassword(userDTO.getPassword());
        user.setEmail(userDTO.getEmail());
        user.setUserIsDelete(userDTO.getUserIsDelete());

        user.setRole(userDTO.getRole());
        user.setBioinformatics(userDTO.getBioinformatics());

        // 处理个人信息，若为空则设置为 null
        PersonalInformationDTO personalInfoDTO = (user.getPersonalInformation() != null)
                ? convertPersonalInformation(user.getPersonalInformation())
                : null;
        userDTO.setPersonalInformationDTO(personalInfoDTO);

        // 处理员工信息，若为空则设置为 null
        EmployeeDTO employeeDTO = (user.getEmployee() != null)
                ? convertEmployee(user.getEmployee())
                : null;
        userDTO.setEmployeeDTO(employeeDTO);

        return  user;
    }
}