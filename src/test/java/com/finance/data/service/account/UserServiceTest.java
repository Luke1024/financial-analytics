package com.finance.data.service.account;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.finance.data.domain.accounts.User;
import com.finance.data.domain.accounts.UserStatus;
import com.finance.data.domain.accounts.dto.LoginDto;
import com.finance.data.repository.accounts.UserRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
//@Transactional
class UserServiceTest {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Test
    void saveUserWithNonRepeatedEmail() {
        User user = new User("password", "email", null, false, null);
        userService.saveUser(user);

        Assert.assertTrue(userRepository.findById(user.getId()).isPresent());

        userRepository.deleteById(user.getId());
    }

    @Test
    void saveUserWithRepeatedEmail() {
        User user = new User("password", "email", null, false, null);
        userService.saveUser(user);

        Assert.assertFalse(userService.saveUser(user));

        if(user.getId() != null) {
            userRepository.deleteById(user.getId());
        }
    }

    @Test
    void loginUser() {
        LoginDto loginDto = new LoginDto("email26", "password23");

        User user = new User("password23", "email26", null, false, null);

        userRepository.save(user);
        Assert.assertTrue(userService.loginUser(loginDto));
        Assert.assertEquals(true, userRepository.findById(user.getId()).get().getUserLoggedIn());

        userRepository.deleteById(user.getId());

    }

    @Test
    void logOutUser() {
        LoginDto loginDto = new LoginDto("email26", "password23");

        User user = new User("password23", "email26", null, false, null);

        userRepository.save(user);
        Assert.assertTrue(userService.loginUser(loginDto));
        Assert.assertEquals(true, userRepository.findById(user.getId()).get().getUserLoggedIn());

        userService.logOutUser(user.getId());

        Assert.assertFalse(userRepository.findById(user.getId()).get().getUserLoggedIn());

        userRepository.deleteById(user.getId());
    }

    @Test
    void changeUserPassword() {
        User user = new User("password4", "email3", null, false, null);
        userRepository.save(user);

        Assert.assertTrue(userRepository.findById(user.getId()).isPresent());

        Assert.assertTrue(userService.changeUserPassword(new LoginDto("email3", "password2")));

        Assert.assertEquals("password2", userRepository.findById(user.getId()).get().getPassword());

        userRepository.deleteById(user.getId());
    }
}