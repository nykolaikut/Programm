package com.kuteinykov.programm.services;

import com.kuteinykov.programm.model.Contact;

public interface ContactBook {
    void add(Contact contact);
//    void update(Contact contact);
    void delete(Contact contact);
}