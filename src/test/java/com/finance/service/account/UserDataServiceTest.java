package com.finance.service.account;

import com.finance.domain.UserData;
import com.finance.domain.User;
import com.finance.domain.dto.PersonalDataDto;
import com.finance.repository.PersonalDataRepository;
import com.finance.service.PersonalDataService;
import com.finance.service.UserService;
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
public class UserDataServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private PersonalDataRepository personalDataRepository;

    @Autowired
    private PersonalDataService personalDataService;

    @Test
    public void getPersonalData() {
        User user = new User();
        UserData userData = new UserData("firstName", "lastName",
                "voivodeship", "city", "postalCode", "street",
                "homeNumber", "phoneNumber", user);

        user.setUserData(userData);

        userService.saveUser(user);

        assertThat(userData, sameBeanAs(userService.getUserById(user.getId()).getUserData()));

        userService.deleteUserById(user.getId());
    }

    @Test
    public void createPersonalData() {

        User user = new User();

        userService.saveUser(user);

        PersonalDataDto personalDataDto = new PersonalDataDto("firstName", "lastName",
                "voivodeship", "city", "postalCode", "street",
                "homeNumber", "phoneNumber", user.getId());

        UserData userData = new UserData("firstName", "lastName",
                "voivodeship", "city", "postalCode", "street",
                "homeNumber", "phoneNumber", user);

        personalDataService.createUpdatePersonalData(personalDataDto);

        UserData retrievedUserData = personalDataService.getPersonalDataByUserId(user.getId());

        assertEquals(userData.getFirstName(), retrievedUserData.getFirstName());
        assertEquals(userData.getLastName(), retrievedUserData.getLastName());
        assertEquals(user.getId(), retrievedUserData.getUser().getId());

        personalDataRepository.deleteById(retrievedUserData.getId());
    }
}