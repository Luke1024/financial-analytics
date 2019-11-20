package com.finance.data.mapper.accounts;

import com.finance.data.domain.accounts.PersonalData;
import com.finance.data.domain.accounts.User;
import com.finance.data.domain.accounts.dto.PersonalDataDto;
import org.springframework.stereotype.Component;

@Component
public class PersonalDataMapper {

    public PersonalDataDto mapToPersonalDataDto(PersonalData personalData){
        return new PersonalDataDto(personalData.getFirstName(),
                personalData.getLastName(),
                personalData.getVoivodeship(),
                personalData.getCity(),
                personalData.getPostalCode(),
                personalData.getStreet(),
                personalData.getHomeNumber(),
                personalData.getPhoneNumber(),
                personalData.getUser().getId());
    }
    public PersonalData mapToPersonalData(PersonalDataDto personalDataDto, User user) {
        return new PersonalData(personalDataDto.getFirstName(),
                    personalDataDto.getLastName(),
                    personalDataDto.getVoivodeship(),
                    personalDataDto.getCity(),
                    personalDataDto.getPostalCode(),
                    personalDataDto.getStreet(),
                    personalDataDto.getHomeNumber(),
                    personalDataDto.getPhoneNumber(),
                    user);
    }
}
