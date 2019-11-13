package com.finance.data.controller.accounts;

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
    public boolean registerUser(@RequestBody UserDto userDto){
        return userService.saveUser(userMapper.mapToUser(userDto));
    }

    @PostMapping(value = "/users/login", consumes = APPLICATION_JSON_VALUE)
    public boolean loginUser(@RequestBody LoginDto loginDto){
        return userService.loginUser(loginDto);
    }

    @PostMapping(value = "/users/logout/{userId}", consumes = APPLICATION_JSON_VALUE)
    public boolean logOutUser(@PathVariable Long userId) throws Exception {
        return userService.logOutUser(userId);
    }

    @PutMapping(value = "/users/password", consumes = APPLICATION_JSON_VALUE)
    public void changeUserPassword(@RequestBody LoginDto loginDto) {

    }

    @PutMapping(value = "users/block")

    @DeleteMapping(value = "/users", consumes = APPLICATION_JSON_VALUE)
    public void deleteUser(@RequestBody LoginDto loginDto) {

    }
}
