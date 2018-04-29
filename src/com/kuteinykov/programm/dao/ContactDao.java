package com.kuteinykov.programm.dao;

import com.kuteinykov.programm.model.Contact;

import java.util.List;

public interface ContactDao {
    /**
     * Интерфейс описывающий основное поведение работы с различными вариантами долгострочного хранения данных.
     * (файловая система, БД и т.д.)
     */

    void saveContact(String name, String phoneNumber, int age, String address);
    void deleteContact(long id);
    List<Contact> displayContact();
    List<Contact> findContact(String name);
    void editContact(Contact contact);
}