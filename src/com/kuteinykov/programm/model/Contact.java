package com.kuteinykov.programm.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;

public class Contact {

//    private long id;
//    private String name;
//    private String phoneNumber;
//    private int age;
//    private String address;

      private SimpleLongProperty id = new SimpleLongProperty(0);
      private SimpleStringProperty name = new SimpleStringProperty("");
      private SimpleStringProperty phoneNumber = new SimpleStringProperty("");
      private SimpleIntegerProperty age = new SimpleIntegerProperty(0);
      private SimpleStringProperty address = new SimpleStringProperty("");


    public Contact(){
    }

    public Contact(long id, String name, String phoneNumber, int age, String address) {
        this.id = new SimpleLongProperty(id);
        this.name = new SimpleStringProperty(name);
        this.phoneNumber = new SimpleStringProperty(phoneNumber);
        this.age = new SimpleIntegerProperty(age);
        this.address = new SimpleStringProperty(address);
    }

    public String getName() {
        return name.get();
    }

    public Integer getAge() {
        return age.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public void setAge(int age) {
        this.age.set(age);
    }

    public String getPhoneNumber() {
        return phoneNumber.get();
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber.set(phoneNumber);
    }

    public String getAddress() {
        return address.get();
    }

    public void setAddress(String address) {
        this.address.set(address);
    }

    public long getId() {
        return id.get();
    }

    public SimpleLongProperty idProperty() {
        return id;
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public SimpleStringProperty phoneNumberProperty() {
        return phoneNumber;
    }

    public SimpleIntegerProperty ageProperty() {
        return age;
    }

    public SimpleStringProperty addressProperty() {
        return address;
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