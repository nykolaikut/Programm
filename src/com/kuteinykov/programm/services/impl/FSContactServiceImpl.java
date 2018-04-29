package com.kuteinykov.programm.services.impl;

import com.kuteinykov.programm.dao.ContactDao;
import com.kuteinykov.programm.model.Contact;
import com.kuteinykov.programm.services.ContactService;

import java.util.ArrayList;
import java.util.List;

public class FSContactServiceImpl implements ContactService {

    /**
     * Реализация ContactService которая использует Файловую Систему для хранения данных.
     */

    private final ContactDao contactDao;

    public FSContactServiceImpl(ContactDao contactDao) {
        this.contactDao = contactDao;
    }

    public void createContact(String name, String phoneNumber, int age, String address) {
        contactDao.saveContact(name, phoneNumber, age, address);
    }

    public void deleteContact(long id) {
        contactDao.deleteContact(id);
    }

    public void displayContact() {
        displayContactList(contactDao.displayContact(),"Contact list");
    }

    public void editContact(long id, String name, String phoneNumber, int age, String address){
        contactDao.editContact(new Contact(id, name, phoneNumber, age, address));
    }

    public void findContact(String name){
        displayContactList(contactDao.findContact(name), "Search results");
    }

    private void displayContactList(List<Contact> list, String title) {
        System.out.printf("%20s", title);
        System.out.println();
        for (Contact contact : list) {
            System.out.println(contact);
        }
        System.out.println();
    }
}