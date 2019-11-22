package com.finance.data.domain.accounts.dto;

public class PasswordChangerDto {
    private String email;
    private String password;
    private String newPassword;

    public PasswordChangerDto() {
    }

    public PasswordChangerDto(String email, String password, String newPassword) {
        this.email = email;
        this.password = password;
        this.newPassword = newPassword;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getNewPassword() {
        return newPassword;
    }
}
