package com.kuteinykov.programm.services.impl;

import com.kuteinykov.programm.model.Contact;
import com.kuteinykov.programm.services.ContactService;

import java.util.ArrayList;
import java.util.List;

public class ContactServiceImpl implements ContactService {

    private List<Contact> contactList = new ArrayList<>();

    @Override
    public void createContact(String name, int age) {
        this.contactList.add(new Contact(name, age));

        for (Contact contact : this.contactList) {
            System.out.println(contact);
        }

    }

    @Override
    public void deleteContact() {

    }

}
