package com.finance.data.controller.accounts;

import com.finance.data.domain.accounts.TradingAccount;
import com.finance.data.domain.accounts.dto.TradingAccountCreationDto;
import com.finance.data.domain.accounts.dto.TradingAccountDto;
import com.finance.data.mapper.accounts.TradingAccountMapper;
import com.finance.data.service.account.TradingAccountService;
import com.google.gson.Gson;
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
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(TradingAccountController.class)
class TradingAccountControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TradingAccountService tradingAccountService;

    @MockBean
    private TradingAccountMapper tradingAccountMapper;

    @Test
    void getUserTradingAccounts() throws Exception {
        List<TradingAccountDto> tradingAccountDtos = new ArrayList<>();

        TradingAccountDto tradingAccountDto= new TradingAccountDto();
        tradingAccountDtos.add(tradingAccountDto);

        when(tradingAccountMapper.mapToTradingDtoList(ArgumentMatchers.anyListOf(TradingAccount.class)))
                .thenReturn(tradingAccountDtos);

        mockMvc.perform(get("/finance/tradingAccount/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void createUserTradingAccount() throws Exception {
        TradingAccount tradingAccount = new TradingAccount();

        Gson gson = new Gson();
        String creationDtoInJson = gson.toJson(tradingAccount);

        when(tradingAccountMapper.mapToNewTradingAccount(ArgumentMatchers.any(TradingAccountCreationDto.class)))
                .thenReturn(tradingAccount);

        mockMvc.perform(post("/finance/tradingAccount")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(creationDtoInJson))
                .andExpect(status().isOk());
    }
}