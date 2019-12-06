package com.finance.data.controller.accounts;

import com.finance.data.domain.accounts.User;
import com.finance.data.domain.accounts.dto.LoginDto;
import com.finance.data.domain.accounts.dto.PasswordChangerDto;
import com.finance.data.domain.accounts.dto.UserRegistrationDto;
import com.finance.data.mapper.accounts.UserMapper;
import com.finance.data.service.account.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@CrossOrigin("*")
@RestController
@RequestMapping("/finance")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @PostMapping(value = "/users", consumes = APPLICATION_JSON_VALUE)
    public void registerUser(@RequestBody UserRegistrationDto userRegistrationDto) {
        userService.saveUser(userMapper.mapToUser(userRegistrationDto));
    }

    @PutMapping(value = "/users/login", consumes = APPLICATION_JSON_VALUE)
    public User loginUser(@RequestBody LoginDto loginDto){
        return userService.loginUser(loginDto);
    }

    @PutMapping(value = "/users/logout/{userId}")
    public User logOutUser(@PathVariable Long userId) throws Exception {
        return userService.logOutUser(userId);
    }

    @PutMapping(value = "/users/password", consumes = APPLICATION_JSON_VALUE)
    public User changeUserPassword(@RequestBody PasswordChangerDto passwordChangerDto) {
        return userService.changeUserPassword(passwordChangerDto);
    }
}
