package com.kuteinykov.programm.dao;

import com.kuteinykov.programm.model.Contact;

import java.util.List;

public interface ContactDao {
    /**
     * Интерфейс описывающий основное поведение работы с различными вариантами долгострочного хранения данных.
     * (файловая система, БД и т.д.)
     */

    void saveContact(Contact contact);
    void deleteContact(long id);
    List<Contact> selectAllContact();
    List<Contact> findContact(String name);
    void editContact(Contact contact);
}