package com.kuteinykov.programm.services.impl;

import com.kuteinykov.programm.model.Contact;
import com.kuteinykov.programm.services.ContactService;

import java.util.HashMap;

public class ContactServiceImpl implements ContactService {

    /**
     * Реализация ContactService которая использует Map для хранения данных.
     */

    private HashMap<Long, Contact> contactList;
    private HashMap<Long, Contact> searchContactList = new HashMap<>();
    private long idContact = 0;


    public ContactServiceImpl() {
        this.contactList = new HashMap<>();
        initContact();
    }

    public void createContact(String name, String phoneNumber, int age, String address) {
        idContact++;
        contactList.put(idContact, new Contact(idContact, name, phoneNumber, age, address));
    }

    public void deleteContact(long id) {
        contactList.remove(id);
    }

    public void displayContact() {
        displayListContact(contactList);
    }

    private void displayListContact(HashMap<Long, Contact> list){
        System.out.println();
        for (Contact contact : list.values()) {
            System.out.println(contact);
        }
    }

    public void editContact(long id, String name, String phoneNumber, int age, String address){
        for (Contact contact : contactList.values()) {
            if (contact.getId() == id){
                contact.setName(name);
                contact.setPhoneNumber(phoneNumber);
                contact.setAge(age);
                contact.setAddress(address);
                return;
            }
        }
    }

    public void findContact(String name){

        if (!searchContactList.isEmpty()) searchContactList.clear();

        int lengthSearch = name.length();

        for (Contact contact : contactList.values()) {
            if (contact.getName().length() >= lengthSearch){
                if (contact.getName().substring(0,lengthSearch).compareToIgnoreCase(name) == 0 ) {
                    searchContactList.put(contact.getId(), new Contact(contact.getId(), contact.getName(),
                            contact.getPhoneNumber(), contact.getAge(),
                            contact.getAddress()));
                }
            }
        }
        if (!searchContactList.isEmpty()) {
            System.out.printf("%20s","Search results");
            displayListContact(searchContactList);
        }
    }

    private void initContact() {
        idContact++;
        contactList.put(idContact, new Contact(idContact, "Olga", "1234567", 25, "Address Olga Kyiv"));
        idContact++;
        contactList.put(idContact, new Contact(idContact, "Tanya", "45-12-36-7", 22, "Address Tanya"));
        idContact++;
        contactList.put(idContact, new Contact(idContact, "Natasha", "12 34 567", 32, "Address Natasha"));
        idContact++;
        contactList.put(idContact, new Contact(idContact, "Olga", "56-88-908", 33, "Address Olga Dnepr"));
        idContact++;
        contactList.put(idContact, new Contact(idContact, "Oleg", "+38-093-123-45-67", 88, "Address Oleg"));
        idContact++;
        contactList.put(idContact, new Contact(idContact, "Yana", "067 369 98 908", 46, "Address Yana"));
        idContact++;
        contactList.put(idContact, new Contact(idContact, "Anna", "0911234567", 43, "Address Anna"));
        idContact++;
        contactList.put(idContact, new Contact(idContact, "Sveta", "756-88-90", 63, "Address Sveta"));
    }
}