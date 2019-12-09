package com.finance.data.mapper.accounts;

import com.finance.data.domain.accounts.UserData;
import com.finance.data.domain.accounts.User;
import com.finance.data.domain.accounts.dto.PersonalDataDto;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.hamcrest.MatcherAssert.assertThat;
import static com.shazam.shazamcrest.matcher.Matchers.sameBeanAs;

@RunWith(SpringRunner.class)
@SpringBootTest
class UserDataMapperTest {

    @Autowired
    private PersonalDataMapper personalDataMapper;

    @Test
    void mapToPersonalDataDto() {
        UserData userData = new UserData("firstName", "lastName",
                "voivodeship", "city", "postalCode", "street",
                "homeNumber", "phoneNumber", new User(1L));

        PersonalDataDto personalDataDto = new PersonalDataDto("firstName", "lastName",
                "voivodeship", "city", "postalCode", "street",
                "homeNumber", "phoneNumber", 1L);

        assertThat(personalDataDto, sameBeanAs(personalDataMapper.mapToPersonalDataDto(userData)));
    }

    @Test
    void mapToPersonalData() {
        User user = new User(1L);
        UserData userData = new UserData("firstName", "lastName",
                "voivodeship", "city", "postalCode", "street",
                "homeNumber", "phoneNumber", user);

        PersonalDataDto personalDataDto = new PersonalDataDto("firstName", "lastName",
                "voivodeship", "city", "postalCode", "street",
                "homeNumber", "phoneNumber", 1L);

        assertThat(userData, sameBeanAs(personalDataMapper.mapToPersonalDataWithUser(personalDataDto, user)));
    }
}