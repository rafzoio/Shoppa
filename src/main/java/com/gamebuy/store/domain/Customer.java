package com.gamebuy.store.domain;

public class Customer {

    private int id;
    private String firstName;
    private String secondName;
    private String telephoneNumber;

    public Customer(String firstName, String secondName, String telephoneNumber) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.telephoneNumber = telephoneNumber;
    }

    public Customer(int id, String firstName, String secondName, String telephoneNumber) {
        this.id = id;
        this.firstName = firstName;
        this.secondName = secondName;
        this.telephoneNumber = telephoneNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
