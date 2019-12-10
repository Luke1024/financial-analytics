package com.finance.data.controller.currency;

import com.finance.data.domain.currency.CurrencyPair;
import com.finance.data.mapper.currency.CurrencyMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(CurrencyController.class)
class CurrencyPairControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CurrencyService currencyService;

    @MockBean
    private CurrencyMapper currencyMapper;

    @Test
    void getAllAvailableCurrencies() throws Exception {
        when(currencyService.getCurrencies()).thenReturn(new ArrayList<>());
        when(currencyMapper.mapToStringKeys(ArgumentMatchers.anyListOf(CurrencyPair.class))).thenReturn(new ArrayList<>());

        mockMvc.perform(get("/finance/currency/")
        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}