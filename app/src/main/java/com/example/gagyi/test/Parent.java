package com.example.gagyi.test;

import java.util.List;

/**
 * Created by gagyi on 2017-10-30.
 */

public class Parent {

    public String lastName;
    public String firstName;
    public String[] children;

    public Parent(String lastName, String firstName, String[] children) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.children = children;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String[] getChildren() {
        return children;
    }

    public void setChildren(String[]  children) {
        this.children = children;
    }
}
