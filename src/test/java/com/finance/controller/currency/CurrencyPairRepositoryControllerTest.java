package com.finance.controller.currency;

import com.finance.controller.CurrencyPairController;
import com.finance.domain.CurrencyPairHistoryPoint;
import com.finance.domain.dto.PairHistoryRequestDto;
import com.finance.mapper.CurrencyPairMapper;
import com.finance.service.CurrencyPairService;
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
@WebMvcTest(CurrencyPairController.class)
class CurrencyPairRepositoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CurrencyPairService currencyPairService;

    @MockBean
    private CurrencyPairMapper currencyPairMapper;
/*
    @Test
    void currencyPairHistory() throws Exception  {
        when(currencyPairService.getCurrencyPairHistory(ArgumentMatchers.any(PairHistoryRequestDto.class)))
                .thenReturn(new ArrayList<>());
        when(currencyPairMapper.mapToDTOList(ArgumentMatchers.anyListOf(CurrencyPairHistoryPoint.class)))
                .thenReturn(new ArrayList<>());

        mockMvc.perform(get("/finance/currency/pair/history")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
    */
}