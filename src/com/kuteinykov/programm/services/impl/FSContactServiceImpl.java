package com.kuteinykov.programm.services.impl;

import com.kuteinykov.programm.dao.ContactDao;
import com.kuteinykov.programm.model.Contact;
import com.kuteinykov.programm.services.ContactService;

import java.util.HashMap;

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
        contactDao.displayContact();
    }

    public void editContact(long id, String name, String phoneNumber, int age, String address){
        contactDao.editContact(new Contact(id, name, phoneNumber, age, address));
    }

    public void findContact(String name){
        contactDao.findContact(name);
    }

    private void displayListContact(HashMap<Long, Contact> list) {
        System.out.println();
        for (Contact contact : list.values()) {
            System.out.println(contact);
        }
        System.out.println();
    }
 }