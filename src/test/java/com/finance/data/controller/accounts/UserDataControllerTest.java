package com.finance.data.controller.accounts;

import com.finance.data.domain.accounts.UserData;
import com.finance.data.domain.accounts.User;
import com.finance.data.domain.accounts.dto.PersonalDataDto;
import com.finance.data.mapper.accounts.PersonalDataMapper;
import com.finance.data.service.account.PersonalDataService;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(PersonalDataController.class)
public class UserDataControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PersonalDataService personalDataService;

    @MockBean
    private PersonalDataMapper personalDataMapper;

    @Test
    public void getUserPersonalData() throws Exception {
        PersonalDataDto personalDataDto = new PersonalDataDto("firstName", "lastName",
                "voivodeship", "city", "postalCode", "street",
                "homeNumber", "phoneNumber", 1L);

        UserData userData = new UserData(1L, "firstName", "lastName",
                "voivodeship", "city", "postalCode", "street",
                "homeNumber", "phoneNumber", new User());

        when(personalDataService.getPersonalDataByUserId(ArgumentMatchers.anyLong())).thenReturn(userData);
        when(personalDataMapper.mapToPersonalDataDto(ArgumentMatchers.any(UserData.class))).thenReturn(personalDataDto);

        mockMvc.perform(get("/finance/userData/1")
        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void createPersonalData() throws Exception {
        PersonalDataDto personalDataDto = new PersonalDataDto("firstName", "lastName",
                "voivodeship", "city", "postalCode", "street",
                "homeNumber", "phoneNumber", 1L);

        Gson gson = new Gson();
        String personalDataDtoInJson = gson.toJson(personalDataDto);

        mockMvc.perform(post("/finance/personalData")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(personalDataDtoInJson))
                .andExpect(status().isOk());
    }

    @Test
    public void updatePersonalData() throws Exception {
        PersonalDataDto personalDataDto = new PersonalDataDto("firstName", "lastName",
                "voivodeship", "city", "postalCode", "street",
                "homeNumber", "phoneNumber", 1L);

        Gson gson = new Gson();
        String personalDataDtoInJson = gson.toJson(personalDataDto);

        mockMvc.perform(put("/finance/personalData")
            .contentType(MediaType.APPLICATION_JSON)
            .characterEncoding("UTF-8")
            .content(personalDataDtoInJson))
                .andExpect(status().isOk());
    }
}