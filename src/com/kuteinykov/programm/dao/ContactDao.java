package com.kuteinykov.programm.dao;

import com.kuteinykov.programm.model.Contact;

public interface ContactDao {
    /**
     * Интерфейс описывающий основное поведение работы с различными вариантами долгострочного хранения данных.
     * (файловая система, БД и т.д.)
     */

    void saveContact(Contact contact);
//    void removeContact();
    void displayContact();
    void findContact(String name);
}