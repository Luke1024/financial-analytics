package com.finance.data.controller.currency;

import com.finance.data.mapper.currency.CurrencyMapper;
import com.finance.data.service.currency.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/finance")
public class CurrencyController {
    @Autowired
    private CurrencyService currencyService;

    @GetMapping(value = "/currency/")
    public List<String> getAllAvailableCurrencies(){
        return currencyService.getCurrencies();
    }
}
