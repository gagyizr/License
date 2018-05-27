package com.example.gagyi.test;

public class Customer {

    public String firstName;
    public String lastName;
    public String email;
    public long phone;
    public String privilege;

    public Customer(){

    }

    public Customer(String firstName, String lastName, String email, long phone, String privilege) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.privilege = privilege;
    }
}
