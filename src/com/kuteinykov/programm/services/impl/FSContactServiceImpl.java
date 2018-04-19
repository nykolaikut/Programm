package com.kuteinykov.programm.services.impl;

import com.kuteinykov.programm.dao.ContactDao;
import com.kuteinykov.programm.model.Contact;
import com.kuteinykov.programm.services.ContactService;

public class FSContactServiceImpl implements ContactService {

    /**
     * Реализация ContactService которая использует Файловую Систему для хранения данных.
     */

    private final ContactDao contactDao;
    private long idContact = 0;

    public FSContactServiceImpl(ContactDao contactDao) {
        this.contactDao = contactDao;
//        if ( idContact == 0) initContact();
        initIdContact();
    }

    @Override
    public void createContact(String name, String phoneNumber, int age, String address) {
        idContact++;
        contactDao.saveContact(new Contact(idContact, name, phoneNumber, age, address));
    }

    @Override
    public void deleteContact(long id) {
//		this.contactList.remove(name);
    }

    @Override
    public void displayContact() {
        contactDao.displayContact();
    }

    @Override
    public void editContact(long id, String name, String phoneNumber, int age, String address){
//        for (Contact contact : contactList.values()) {
//            if (contact.getId() == id){
//                contact.setName(name);
//                contact.setPhoneNumber(phoneNumber);
//                contact.setAge(age);
//                contact.setAddress(address);
//                return;
//            }
//        }
    }
    @Override
    public void findContact(String name){

//        if (!searchContactList.isEmpty()) searchContactList.clear();
//
//        int lengthSearch = name.length();
//
//        for (Contact contact : contactList.values()) {
//            if (contact.getName().trim().length() >= lengthSearch){
////                if (contact.getName().trim().toUpperCase().substring(0,lengthSearch).equals(name.toUpperCase())) {
//                if (contact.getName().substring(0,lengthSearch).compareToIgnoreCase(name) == 0 ) {
//                    searchContactList.put(contact.getId(), new Contact(contact.getId(), contact.getName(),
//                            contact.getPhoneNumber(), contact.getAge(),
//                            contact.getAddress()));
//                }
//            }
//        }
//        if (!searchContactList.isEmpty()) {
//            System.out.printf("%20s","Search results");
//            displayListContact(searchContactList);
//        }
    }

    private void initContact() {
        idContact++;
        contactDao.saveContact(new Contact(idContact, "Olga", "1234567", 25, "Address Olga Kyiv"));
        idContact++;
        contactDao.saveContact(new Contact(idContact, "Tanya", "45-12-36-7", 22, "Address Tanya"));
        idContact++;
        contactDao.saveContact(new Contact(idContact, "Natasha", "12 34 567", 32, "Address Natasha"));
        idContact++;
        contactDao.saveContact(new Contact(idContact, "Olga", "56-88-908", 33, "Address Olga Dnepr"));
        idContact++;
        contactDao.saveContact(new Contact(idContact, "Oleg", "+38-093-123-45-67", 88, "Address Oleg"));
        idContact++;
        contactDao.saveContact(new Contact(idContact, "Yana", "067 369 98 908", 46, "Address Yana"));
        idContact++;
        contactDao.saveContact(new Contact(idContact, "Anna", "0911234567", 43, "Address Anna"));
        idContact++;
        contactDao.saveContact(new Contact(idContact, "Sveta", "756-88-90", 63, "Address Sveta"));
    }

    private void initIdContact(){


    }

}