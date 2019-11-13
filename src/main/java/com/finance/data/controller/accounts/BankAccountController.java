package com.finance.data.controller.accounts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@CrossOrigin("*")
@RestController
@RequestMapping("/bank")
public class BankAccountController {
    @Autowired
    private BankAccountService bankAccountService;

    @Autowired
    private BankAccountMapper bankAccountMapper;

    @GetMapping(value = "/userBankAccount/{userId}")
    public List<BankAccountDto> getUserBankAccount(@PathVariable Long userId){
        return bankAccountMapper.mapToBankAccountDtoList(bankAccountService.getUserBankAccounts(userId));
    }

    @PostMapping(value = "/userBankAccount", consumes = APPLICATION_JSON_VALUE)
    public void createUserBankAccount(@RequestBody BankAccountCreationDto bankAccountCreationDto) {
        bankAccountService.createBankAccount(bankAccountCreationDto);
    }

    @PutMapping(value = "/userBankAccount", consumes = APPLICATION_JSON_VALUE)
    public void updateUserBankAccount(@RequestBody BankAccountUpdaterDto bankAccountUpdaterDto) {
        bankAccountService.updateBankAccount(bankAccountUpdaterDto);
    }
}
