package com.finance.data.domain.accounts;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class PersonalData {
    @Id
    @GeneratedValue
    private Long id;
    private String firstName;
    private String lastName;
    private String voivodeship;
    private String city;
    private String postalCode;
    private String street;
    private String homeNumber;
    private String phoneNumber;
    private User user;

    public PersonalData() {
    }

    public PersonalData(String firstName, String lastName, String voivodeship, String city, String postalCode,
                        String street, String homeNumber, String phoneNumber, User user) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.voivodeship = voivodeship;
        this.city = city;
        this.postalCode = postalCode;
        this.street = street;
        this.homeNumber = homeNumber;
        this.phoneNumber = phoneNumber;
        this.user = user;
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

    public User getUser() {
        return user;
    }
}
