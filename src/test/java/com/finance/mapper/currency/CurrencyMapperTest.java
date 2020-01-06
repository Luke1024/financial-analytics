package com.finance.mapper.currency;

import com.finance.mapper.CurrencyMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class CurrencyMapperTest {

    @Autowired
    private CurrencyMapper currencyMapper;

    @Test
    void mapToStringKeys() {

    }
}