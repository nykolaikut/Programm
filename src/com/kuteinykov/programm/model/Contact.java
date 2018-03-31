package com.kuteinykov.programm.model;

public class Contact {

    private String name;
    private int age;

    public Contact(String name, int phoneNumber) {
        this.name = name;
        this.age = phoneNumber;
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

    @Override
    public String toString() {
        return "Contact{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
