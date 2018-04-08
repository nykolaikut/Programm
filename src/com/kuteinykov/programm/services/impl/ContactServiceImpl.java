package com.kuteinykov.programm.services.impl;

import com.kuteinykov.programm.model.Contact;
import com.kuteinykov.programm.services.ContactService;

import java.util.ArrayList;
import java.util.List;

public class ContactServiceImpl implements ContactService {

    private List<Contact> contactList = new ArrayList<>();
    private List<Contact> searchContactList = new ArrayList<>();
    private long idContact = 0;

    @Override
    public void createContact(String name, String phoneNumber, int age, String address) {
        this.contactList.add(new Contact(++idContact, name, phoneNumber, age, address));
    }

    @Override
    public void deleteContact(int id) {
        if (contactList.removeIf(x -> x.getId() == id)) {
            System.out.println("Contact is deleted " + id);
        }
    }

    @Override
    public void displayContact() {
        displayListContact(contactList);
    }

    private <T> void displayListContact(List<T> list){
        System.out.println();
        for (T contact: list ) {
            System.out.print(contact);
            System.out.println();
        }
    }

    @Override
    public void editContact(int id, String name, String phoneNumber, int age, String address){
        for (Contact contact : this.contactList) {
            if (contact.getId() == id){
                contact.setName(name);
                contact.setPhoneNumber(phoneNumber);
                contact.setAge(age);
                contact.setAddress(address);
                return;
            }
        }
    }

    @Override
    public void findContact(String name){

        if (!searchContactList.isEmpty()) searchContactList.clear();

        int lengthSearch = name.length();

        for (Contact contact : this.contactList) {
            if (contact.getName().trim().length() >= lengthSearch){
                if (contact.getName().trim().toUpperCase().substring(0,lengthSearch).equals(name.toUpperCase())) {
                    searchContactList.add(new Contact(contact.getId(), contact.getName(),
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

    @Override
    public void initContact() {
        contactList.add(new Contact(++idContact, "Olga", "1234567", 25, "Address Olga Kyiv"));
        contactList.add(new Contact(++idContact, "Tanya", "45-12-36-7", 22, "Address Tanya"));
        contactList.add(new Contact(++idContact, "Natasha", "12 34 567", 32, "Address Natasha"));
        contactList.add(new Contact(++idContact, "Olga", "56-88-908", 33, "Address Olga Dnepr"));
        contactList.add(new Contact(++idContact, "Oleg", "+38-093-123-45-67", 88, "Address Oleg"));
        contactList.add(new Contact(++idContact, "Yana", "067 369 98 908", 46, "Address Yana"));
        contactList.add(new Contact(++idContact, "Anna", "0911234567", 43, "Address Anna"));
        contactList.add(new Contact(++idContact, "Sveta", "756-88-90", 63, "Address Sveta"));
    }
}

