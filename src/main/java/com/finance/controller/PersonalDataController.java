package com.finance.controller;

import com.finance.domain.dto.PersonalDataDto;
import com.finance.mapper.PersonalDataMapper;
import com.finance.service.PersonalDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@CrossOrigin("*")
@RestController
@RequestMapping("/finance")
public class PersonalDataController {
    @Autowired
    private PersonalDataService personalDataService;

    @Autowired
    private PersonalDataMapper personalDataMapper;

    @GetMapping(value = "/personalData/{userId}")
    public PersonalDataDto getUserPersonalData(@PathVariable Long userId) {
        return personalDataMapper.mapToPersonalDataDto(personalDataService.getPersonalDataByUserId(userId));
    }

    @PutMapping(value = "/personalData", consumes = APPLICATION_JSON_VALUE)
    public void createUpdatePersonalData(PersonalDataDto personalDataDto) {
        personalDataService.createUpdatePersonalData(personalDataDto);
    }
}
