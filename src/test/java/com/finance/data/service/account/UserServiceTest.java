package com.finance.data.service.account;

import com.finance.data.domain.accounts.User;
import com.finance.data.domain.accounts.dto.LoginDto;
import com.finance.data.domain.accounts.dto.PasswordChangerDto;
import com.finance.data.repository.accounts.UserRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static com.shazam.shazamcrest.matcher.Matchers.sameBeanAs;

@RunWith(SpringRunner.class)
@SpringBootTest
//@Transactional
class UserServiceTest {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Test
    void getUserById() {
        User user = new User("password", "email", null, false, null);

        userService.saveUser(user);
        if(user.getId() == null) {
            user = userService.retrieveUserByEmail(user.getEmail());
        }

        assertThat(user, sameBeanAs(userService.getUserById(user.getId()));

        if(user.getId() != null) {
            userRepository.deleteById(user.getId());
        }
    }


    @Test
    void saveUserWithNonRepeatedEmail() {
        User user = new User("password", "email", null, false, null);

        userService.saveUser(user);
        if(user.getId() == null) {
            user = userService.retrieveUserByEmail(user.getEmail());
        }

        Assert.assertTrue(userRepository.findById(user.getId()).isPresent());


        if(user.getId() != null) {
            userRepository.deleteById(user.getId());
        }
    }

    @Test
    void saveUserWithRepeatedEmail() {
        User user = new User("password", "email", null, false, null);
        userService.saveUser(user);
        if(user.getId() == null) {
            user = userService.retrieveUserByEmail(user.getEmail());
        }

        Assert.assertFalse(userService.saveUser(user));

        if(user.getId() != null) {
            userRepository.deleteById(user.getId());
        }
    }

    @Test
    void loginUser() {
        LoginDto loginDto = new LoginDto("email26", "password23");

        User user = new User("password23", "email26", null, false, null);

        userService.saveUser(user);
        if(user.getId() == null) {
            user = userService.retrieveUserByEmail(user.getEmail());
        }

        Assert.assertTrue(userService.loginUser(loginDto));
        Assert.assertEquals(true, userRepository.findById(user.getId()).get().getUserLoggedIn());

        userRepository.deleteById(user.getId());

    }

    @Test
    void logOutUser() {
        LoginDto loginDto = new LoginDto("email26", "password23");

        User user = new User("password23", "email26", null, false, null);

        userService.saveUser(user);
        if(user.getId() == null) {
            user = userService.retrieveUserByEmail(user.getEmail());
        }

        Assert.assertTrue(userService.loginUser(loginDto));
        Assert.assertEquals(true, userRepository.findById(user.getId()).get().getUserLoggedIn());

        userService.logOutUser(user.getId());

        Assert.assertFalse(userRepository.findById(user.getId()).get().getUserLoggedIn());

        userRepository.deleteById(user.getId());
    }

    @Test
    void changeUserPassword() {
        User user = new User("password4", "email3",
                null, false, null);
        userService.saveUser(user);
        if(user.getId() == null) {
            user = userService.retrieveUserByEmail(user.getEmail());
        }

        Assert.assertTrue(userRepository.findById(user.getId()).isPresent());

        PasswordChangerDto passwordChangerDto =
                new PasswordChangerDto("email3", "password4", "password2");

        Assert.assertTrue(userService.changeUserPassword(passwordChangerDto));

        Assert.assertEquals("password2", userRepository.findById(user.getId()).get().getPassword());

        userRepository.deleteById(user.getId());
    }
}