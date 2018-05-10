package com.kuteinykov.programm.services.impl;

import com.kuteinykov.programm.dao.ContactDao;
import com.kuteinykov.programm.model.Contact;
import com.kuteinykov.programm.services.ContactService;

import java.util.List;

public class FSContactServiceImpl implements ContactService {

    /**
     * Реализация ContactService которая использует Файловую Систему для хранения данных.
     */

    private final ContactDao contactDao;

    public FSContactServiceImpl(ContactDao contactDao) {
        this.contactDao = contactDao;
    }

    public void createContact(Contact contact) {
        contactDao.saveContact(contact);
    }

    public void deleteContact(long id) {
        contactDao.deleteContact(id);
    }

    public List<Contact> displayAllContact() {
        return contactDao.selectAllContact();
    }

    public void editContact(Contact contact){
        contactDao.editContact(contact);
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