package com.kuteinykov.programm.services.impl;

import com.kuteinykov.programm.model.Contact;
import com.kuteinykov.programm.services.ContactBook;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class CollectionContactBook implements ContactBook {

    private ObservableList<Contact> contactlist = FXCollections.observableArrayList();


    @Override
    public void add(Contact contact) {contactlist.add(contact);}

    @Override
    public void delete(Contact contact) {contactlist.remove(contact);}

    public ObservableList<Contact> getContactlist() {
        return contactlist;
    }

    public void fillData(ArrayList list){
        this.contactlist.addAll(list);
    }
}