package com.example.startup;

import java.io.Serializable;

public class Person implements Serializable {
    private int id;
    private String name;
    private int age;
    private String address;
    private String phone;
    private float salary;

    public Person() {
    }

    public Person(int id, String name, int age, String address, String phone, float salary) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.address = address;
        this.phone = phone;
        this.salary = salary;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return name;
    }

    public String[] toArray(){
        return new String[]{name, String.valueOf(age), phone, address, String.valueOf(salary)};
    }
    public String[] toArrayUpdate(){
        return new String[]{name, String.valueOf(age), phone, address, String.valueOf(salary), String.valueOf(id)};
    }
}
