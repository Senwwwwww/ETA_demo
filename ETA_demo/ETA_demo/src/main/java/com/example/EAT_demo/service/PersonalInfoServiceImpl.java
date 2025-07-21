package com.example.EAT_demo.service;

import com.example.EAT_demo.converter.PersonalInformationConvert;
import com.example.EAT_demo.dao.PersonalInformationRepository;
import com.example.EAT_demo.dao.UserRepository;
import com.example.EAT_demo.dto.PersonalInformationDTO;
import com.example.EAT_demo.pojo.PersonalInformation;
import com.example.EAT_demo.pojo.User;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@SuppressWarnings("all")
@Service
public class PersonalInfoServiceImpl implements PersonalInfoService{


    @Resource
    @Autowired
    private PersonalInformationRepository personalInformationRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public PersonalInformationDTO addAndUpdatePersonalInfo(PersonalInformationDTO personalInformationDTO, Long userId) {
        PersonalInformation personalInformationINDB = personalInformationRepository.findByuserId(userId);
        if(personalInformationINDB !=null){
            personalInformationINDB.setSex(personalInformationDTO.getSex());
            personalInformationINDB.setBirthDay(personalInformationDTO.getBirthDay());

            // 保存 PersonalInformation
            personalInformationRepository.save(personalInformationINDB);

            personalInformationDTO = PersonalInformationConvert.convertPersonalInformation(personalInformationINDB);
            return personalInformationDTO;
        }
        else {
            PersonalInformation personalInformation = PersonalInformationConvert.convertPersonalInformation(personalInformationDTO);
            personalInformation.setUserId(userId);
            // 保存 PersonalInformation
            personalInformationRepository.save(personalInformation);

            User user = userRepository.findByUserId(userId);
            user.setPersonalInformation(personalInformation);
            userRepository.save(user);
            return PersonalInformationConvert.convertPersonalInformation(personalInformation);
        }

    }



}
