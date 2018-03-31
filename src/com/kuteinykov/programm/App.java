package com.kuteinykov.programm;

import com.kuteinykov.programm.services.impl.ContactServiceImpl;
import com.kuteinykov.programm.view.CmdLineService;
import com.kuteinykov.programm.view.impl.CmdLineServiceImpl;

import java.io.IOException;

public class App {
    public static void main(String[] args) throws IOException {

        CmdLineService cmd = new CmdLineServiceImpl(new ContactServiceImpl());
        
        cmd.runMenu();

    }
}

