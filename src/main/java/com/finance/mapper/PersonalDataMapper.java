package com.finance.mapper;

import com.finance.domain.UserData;
import com.finance.domain.User;
import com.finance.domain.dto.PersonalDataDto;
import org.springframework.stereotype.Component;

@Component
public class PersonalDataMapper {
    public PersonalDataDto mapToPersonalDataDto(UserData userData){
        return new PersonalDataDto(userData.getFirstName(),
                userData.getLastName(),
                userData.getVoivodeship(),
                userData.getCity(),
                userData.getPostalCode(),
                userData.getStreet(),
                userData.getHomeNumber(),
                userData.getPhoneNumber());
    }
    public UserData mapToPersonalDataWithUser(PersonalDataDto personalDataDto, User user) {
        return new UserData(personalDataDto.getFirstName(),
                personalDataDto.getLastName(),
                personalDataDto.getVoivodeship(),
                personalDataDto.getCity(),
                personalDataDto.getPostalCode(),
                personalDataDto.getStreet(),
                personalDataDto.getHomeNumber(),
                personalDataDto.getPhoneNumber());
    }

    public UserData mapToPersonalData(PersonalDataDto personalDataDto) {
        return new UserData(personalDataDto.getFirstName(),
                personalDataDto.getLastName(),
                personalDataDto.getVoivodeship(),
                personalDataDto.getCity(),
                personalDataDto.getPostalCode(),
                personalDataDto.getStreet(),
                personalDataDto.getHomeNumber(),
                personalDataDto.getPhoneNumber());
    }
}