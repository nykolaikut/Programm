package com.kuteinykov.programm.services;

import com.kuteinykov.programm.model.Contact;
import javafx.collections.ObservableList;

public interface CollectionService {
    ObservableList<Contact> getCollection();
}
