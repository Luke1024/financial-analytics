package com.finance.controller;

import com.finance.preprocessor.HistoricDataLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/finance")
public class AdminController {

    @Autowired
    private HistoricDataLoader historicDataLoader;

    @GetMapping(value = "/admin/load")
    public void loadHistory(){
        historicDataLoader.loadDataIntoDatabase();
    }
}
