package com.finance.data.domain.accounts.dto;

public class PersonalDataDto {
    private String firstName;
    private String lastName;
    private String voivodeship;
    private String city;
    private String postalCode;
    private String street;
    private String homeNumber;
    private String phoneNumber;
    private Long userId;

    public PersonalDataDto() {
    }

    public PersonalDataDto(String firstName, String lastName, String voivodeship, String city, String postalCode, String street, String homeNumber, String phoneNumber, Long userId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.voivodeship = voivodeship;
        this.city = city;
        this.postalCode = postalCode;
        this.street = street;
        this.homeNumber = homeNumber;
        this.phoneNumber = phoneNumber;
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getVoivodeship() {
        return voivodeship;
    }

    public String getCity() {
        return city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getStreet() {
        return street;
    }

    public String getHomeNumber() {
        return homeNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Long getUserId() {
        return userId;
    }
}
