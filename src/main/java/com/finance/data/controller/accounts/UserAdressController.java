package com.finance.data.controller.accounts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@CrossOrigin("*")
@RestController
@RequestMapping("/finance")
public class UserAdressController {
    @Autowired
    private UserAdressService userAdressService;

    @Autowired
    private UserAdressMapper userAdressMapper;

    @GetMapping(value = "/userAdress/{userId}")
    public UserAdressDto getUserAdress(@PathVariable Long userId) {
        return userAdressMapper.mapToUserAdressDto(userAdressService.getUserAdress(userId));
    }

    @PostMapping(value = "/userAdress", consumes = APPLICATION_JSON_VALUE)
    public void createUserAdress(UserAdressCreationDto userAdressCreationDto) {
        userAdressService.createUserAdress(userAdressCreationDto);
    }

    @PutMapping(value = "/userAdress", consumes = APPLICATION_JSON_VALUE)
    public void updateUserAdress(UserAdressCreatorDto userAdressCreatorDto){
        userAdressService.updateUserAdress(userAdressCreatorDto);
    }
}
