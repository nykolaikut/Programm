package com.kuteinykov.programm.services;

public interface ContactService {

    void createContact(String name, String phoneNumber, int age, String address);
    void deleteContact(long id);
    void displayContact();
    void editContact(long id, String name, String phoneNumber, int age, String address);
    void findContact(String name);
}
