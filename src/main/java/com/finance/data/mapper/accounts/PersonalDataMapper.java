package com.finance.data.mapper.accounts;

import com.finance.data.domain.accounts.UserData;
import com.finance.data.domain.accounts.User;
import com.finance.data.domain.accounts.dto.PersonalDataDto;
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
                userData.getPhoneNumber(),
                userData.getUser().getId());
    }
    public UserData mapToPersonalDataWithUser(PersonalDataDto personalDataDto, User user) {
        return new UserData(personalDataDto.getFirstName(),
                    personalDataDto.getLastName(),
                    personalDataDto.getVoivodeship(),
                    personalDataDto.getCity(),
                    personalDataDto.getPostalCode(),
                    personalDataDto.getStreet(),
                    personalDataDto.getHomeNumber(),
                    personalDataDto.getPhoneNumber(),
                    user);
    }

    public UserData mapToPersonalData(PersonalDataDto personalDataDto) {
        return new UserData(personalDataDto.getFirstName(),
                personalDataDto.getLastName(),
                personalDataDto.getVoivodeship(),
                personalDataDto.getCity(),
                personalDataDto.getPostalCode(),
                personalDataDto.getStreet(),
                personalDataDto.getHomeNumber(),
                personalDataDto.getPhoneNumber(),
                null);
    }
}
