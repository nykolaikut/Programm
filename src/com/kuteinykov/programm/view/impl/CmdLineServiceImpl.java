package com.kuteinykov.programm.view.impl;

import com.kuteinykov.programm.services.ContactService;
import com.kuteinykov.programm.view.CmdLineService;

public class CmdLineServiceImpl implements CmdLineService {

    private ContactService contactService;

    public CmdLineServiceImpl(ContactService contactService) {
        this.contactService = contactService;
    }

    @Override
    public void showMenu() {
        System.out.println("Menu");
    }
}
