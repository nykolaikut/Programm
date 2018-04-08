package com.kuteinykov.programm.services;

public interface ContactService {

    void initContact();
    void createContact(String name, String phoneNumber, int age, String address);
    void deleteContact(int id);
    void displayContact();
    void editContact(int id, String name, String phoneNumber,int age, String address);
    void findContact(String name);
}
