package com.finance.domain.dto;

public class PersonalDataDto {
    private Long userId;
    private String firstName;
    private String lastName;
    private String voivodeship;
    private String city;
    private String postalCode;
    private String street;
    private String homeNumber;
    private String phoneNumber;

    public PersonalDataDto() {
    }

    public PersonalDataDto(String firstName, String lastName, String voivodeship, String city, String postalCode, String street, String homeNumber, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.voivodeship = voivodeship;
        this.city = city;
        this.postalCode = postalCode;
        this.street = street;
        this.homeNumber = homeNumber;
        this.phoneNumber = phoneNumber;
    }

    public PersonalDataDto(Long userId, String firstName, String lastName, String voivodeship, String city, String postalCode, String street, String homeNumber, String phoneNumber) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.voivodeship = voivodeship;
        this.city = city;
        this.postalCode = postalCode;
        this.street = street;
        this.homeNumber = homeNumber;
        this.phoneNumber = phoneNumber;
    }

    public Long getUserId() {
        return userId;
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
}