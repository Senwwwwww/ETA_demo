package com.example.EAT_demo.converter;

import com.example.EAT_demo.dto.PersonalInformationDTO;
import com.example.EAT_demo.pojo.PersonalInformation;

@SuppressWarnings("all")
public class PersonalInformationConvert {

    public static PersonalInformationDTO convertPersonalInformation(PersonalInformation personalInformation){
        PersonalInformationDTO personalInfoDTO = new PersonalInformationDTO();

        personalInfoDTO.setPersonalInfoId(personalInformation.getPersonalInfoId());
        personalInfoDTO.setSex(personalInformation.getSex());
        personalInfoDTO.setBirthDay(personalInformation.getBirthDay());
        personalInfoDTO.setHeadPicture(personalInformation.getHeadPicture());
        personalInfoDTO.setUserId(personalInformation.getUserId());
        personalInfoDTO.setPersonIsDelete(personalInformation.getPersonIsDelete());
        return personalInfoDTO;
    }

    public static PersonalInformation convertPersonalInformation(PersonalInformationDTO personalInformationDTO){
        PersonalInformation personalInformation = new PersonalInformation();

        personalInformation.setSex(personalInformationDTO.getSex());
        personalInformation.setBirthDay(personalInformationDTO.getBirthDay());
        personalInformation.setHeadPicture(personalInformationDTO.getHeadPicture());
        personalInformation.setUserId(personalInformationDTO.getUserId());
        personalInformation.setPersonIsDelete(personalInformationDTO.getPersonIsDelete());

        return personalInformation;
    }
}
