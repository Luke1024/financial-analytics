package com.finance.controller.accounts;

import com.finance.controller.TradingAccountHistoryPointsController;
import com.finance.mapper.TradingAccountHistoryPointMapper;
import com.finance.service.TradingAccountHistoryPointService;
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
@WebMvcTest(TradingAccountHistoryPointsController.class)
class TradingAccountHistoryPointsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TradingAccountHistoryPointMapper tradingAccountHistoryPointMapper;

    @MockBean
    private TradingAccountHistoryPointService tradingAccountHistoryPointService;

    @Test
    void getAccountHistoryPoints() throws Exception {

        when(tradingAccountHistoryPointService
                .getTradingAccountHistoryPoints(ArgumentMatchers.anyLong()))
                .thenReturn(new ArrayList<>());
        when(tradingAccountHistoryPointMapper.mapToAccountHistoryPointDtos
                (ArgumentMatchers.anyList())).thenReturn(new ArrayList<>());

        mockMvc.perform(get("/finance/accountHistoryPoints/1")
        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}