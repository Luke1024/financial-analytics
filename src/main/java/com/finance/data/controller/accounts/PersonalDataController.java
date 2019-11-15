package com.finance.data.controller.accounts;

import com.finance.data.domain.accounts.dto.PersonalDataDto;
import com.finance.data.mapper.accounts.PersonalDataMapper;
import com.finance.data.service.account.PersonalDataService;
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
        return personalDataMapper.mapToPersonalDataDto(personalDataService.getPersonalData(userId));
    }

    @PostMapping(value = "/personalData", consumes = APPLICATION_JSON_VALUE)
    public void createPersonalData(PersonalDataDto personalDataDto) {
        personalDataService.createPersonalData(personalDataDto);
    }

    @PutMapping(value = "/personalData", consumes = APPLICATION_JSON_VALUE)
    public void updatePersonalData(PersonalDataDto personalDataDto){
        personalDataService.updatePersonalData(personalDataDto);
    }
}
