package com.kuteinykov.programm.model;

public class Contact {

    private long id;
    private String name;
    private String phoneNumber;
    private int age;
    private String address;

    public Contact(long id, String name, String phoneNumber, int age, String address) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.age = age;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public long getId() {
        return id;
    }
    @Override
    public String toString() {
//        return "Contact{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                ", phoneNumber='" + phoneNumber + '\'' +
//                ", age=" + age +
//                ", address='" + address + '\'' +
//                '}';
        return id + ":" + name + ":" + phoneNumber + ":" + age + ":" + address;
     }
}