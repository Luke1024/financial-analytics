package com.finance.domain;

import javax.persistence.*;

@Entity
public class UserData {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstName;
    private String lastName;
    private String voivodeship;
    private String city;
    private String postalCode;
    private String street;
    private String homeNumber;
    private String phoneNumber;

    public UserData() {
    }

    public UserData(String firstName, String lastName, String voivodeship, String city, String postalCode,
                    String street, String homeNumber, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.voivodeship = voivodeship;
        this.city = city;
        this.postalCode = postalCode;
        this.street = street;
        this.homeNumber = homeNumber;
        this.phoneNumber = phoneNumber;
    }

    public UserData(Long id, String firstName, String lastName, String voivodeship, String city, String postalCode,
                    String street, String homeNumber, String phoneNumber) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.voivodeship = voivodeship;
        this.city = city;
        this.postalCode = postalCode;
        this.street = street;
        this.homeNumber = homeNumber;
        this.phoneNumber = phoneNumber;
    }

    public Long getId() {
        return id;
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