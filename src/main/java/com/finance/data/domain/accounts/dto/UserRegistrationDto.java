package com.finance.data.domain.accounts.dto;

public class UserRegistrationDto {
    private PersonalDataDto personalDataDto;
    private String password;
    private String email;

    public UserRegistrationDto() {
    }

    public UserRegistrationDto(PersonalDataDto personalDataDto, String password, String email) {
        this.personalDataDto = personalDataDto;
        this.password = password;
        this.email = email;
    }

    public PersonalDataDto getPersonalDataDto() {
        return personalDataDto;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }
}
