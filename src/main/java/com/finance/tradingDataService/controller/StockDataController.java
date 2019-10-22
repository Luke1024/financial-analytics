package com.finance.tradingDataService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/dataSource")
public class StockDataController {
    /*
    @Autowired
    private DataBaseServive service;
    @Autowired
    private StockMapper stockMapper;
    @Autowired
    private MutualFundMapper mutualFundMapper;

    @RequestMapping(method = RequestMethod.GET, value = "/stocks")
    public List<StockDto> getTodayHistoryPoint(){
        return stockMapper.mapToStockDtos(service.getAllStocks());
    }

    public List<StockDto>
    */
}
