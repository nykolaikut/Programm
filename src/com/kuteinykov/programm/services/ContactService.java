package com.kuteinykov.programm.services;

import com.kuteinykov.programm.model.Contact;
import javafx.collections.ObservableList;

import java.util.List;

public interface ContactService {
    void createContact(Contact contact);
    void deleteContact(long id);
    List<Contact> displayAllContact();
    void editContact(Contact contact);
    void findContact(String name);
}