package com.example.startup;

import java.io.Serializable;

public class Employee implements Serializable {
    private int id;
    private String name;
    private float salary;
    private String phone;
    private String email;
    private String address;

    public Employee() {
    }

    public Employee(int id, String name, float salary, String phone, String email, String address) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.phone = phone;
        this.email = email;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
