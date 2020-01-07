package com.finance.controller.accounts;

import com.finance.controller.UserController;
import com.finance.domain.User;
import com.finance.domain.dto.LoginDto;
import com.finance.domain.dto.PasswordChangerDto;
import com.finance.domain.dto.UserRegistrationDto;
import com.finance.mapper.UserMapper;
import com.finance.service.UserService;
import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @MockBean
    private UserMapper userMapper;

    @Test
    void registerUser() throws Exception {
        UserRegistrationDto userRegistrationDto = new UserRegistrationDto();

        Gson gson = new Gson();

        String userRegistrationDtoInJson = gson.toJson(userRegistrationDto);

        User user = new User();

        when(userMapper.mapToUser(ArgumentMatchers.any(UserRegistrationDto.class))).thenReturn(user);
        when(userService.saveUser(ArgumentMatchers.any(User.class))).thenReturn(true);

        mockMvc.perform(post("/finance/users")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(userRegistrationDtoInJson))
                .andExpect(status().isOk());
    }
/*
    @Test
    void loginUser() throws Exception {
        LoginDto loginDto = new LoginDto();

        Gson gson = new Gson();

        String loginDtoInJson = gson.toJson(loginDto);

        when(userService.loginUser(loginDto)).thenReturn(true);

        mockMvc.perform(post("/finance/users/login")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(loginDtoInJson))
                .andExpect(status().isOk());
    }
    */
/*
    @Test
    void logOutUser() throws Exception {
        when(userService.logOutUser(ArgumentMatchers.anyLong())).thenReturn(true);

        mockMvc.perform(post("/finance/users/logout/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

 */

/*
    @Test
    void changeUserPassword() throws Exception {
        PasswordChangerDto passwordChangerDto = new PasswordChangerDto();

        Gson gson = new Gson();
        String passwordChangerDtoInJson = gson.toJson(passwordChangerDto);

        when(userService.changeUserPassword(passwordChangerDto)).thenReturn(true);

        mockMvc.perform(put("/finance/users/password")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(passwordChangerDtoInJson))
                .andExpect(status().isOk());
    }
    */
}