package com.finance.data.service.account;

import com.finance.data.domain.accounts.PersonalData;
import com.finance.data.domain.accounts.User;
import com.finance.data.domain.accounts.dto.PersonalDataDto;
import com.finance.data.repository.accounts.PersonalDataRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import static com.shazam.shazamcrest.matcher.Matchers.sameBeanAs;
import static org.junit.Assert.assertEquals;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class PersonalDataServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private PersonalDataRepository personalDataRepository;

    @Autowired
    private PersonalDataService personalDataService;

    @Test
    public void getPersonalData() {
        User user = new User();
        PersonalData personalData = new PersonalData("firstName", "lastName",
                "voivodeship", "city", "postalCode", "street",
                "homeNumber", "phoneNumber", user);

        user.setPersonalData(personalData);

        userService.saveUser(user);

        assertThat(personalData, sameBeanAs(userService.getUserById(user.getId()).getPersonalData()));

        userService.deleteUserById(user.getId());
    }

    @Test
    public void createPersonalData() {

        User user = new User();

        userService.saveUser(user);

        PersonalDataDto personalDataDto = new PersonalDataDto("firstName", "lastName",
                "voivodeship", "city", "postalCode", "street",
                "homeNumber", "phoneNumber", user.getId());

        PersonalData personalData = new PersonalData("firstName", "lastName",
                "voivodeship", "city", "postalCode", "street",
                "homeNumber", "phoneNumber", user);

        personalDataService.createUpdatePersonalData(personalDataDto);

        PersonalData retrievedPersonalData = personalDataService.getPersonalDataByUserId(user.getId());

        assertEquals(personalData.getFirstName(), retrievedPersonalData.getFirstName());
        assertEquals(personalData.getLastName(), retrievedPersonalData.getLastName());
        assertEquals(user.getId(), retrievedPersonalData.getUser().getId());

        personalDataRepository.deleteById(retrievedPersonalData.getId());
    }
}