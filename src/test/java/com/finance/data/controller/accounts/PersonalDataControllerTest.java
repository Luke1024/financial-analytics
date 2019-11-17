package com.finance.data.controller.accounts;

import com.finance.data.mapper.accounts.PersonalDataMapper;
import com.finance.data.service.account.PersonalDataService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.awt.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(PersonalDataController.class)
public class PersonalDataControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PersonalDataService personalDataService;

    @MockBean
    private PersonalDataMapper personalDataMapper;

    @Test
    public void getUserPersonalData() throws Exception {
        mockMvc.perform(get("/finance/personalData/1")
        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}