package com.kuteinykov.programm.dao.impl;

import com.kuteinykov.programm.dao.ContactDao;
import com.kuteinykov.programm.model.Contact;

import java.io.*;

public class FileSystemContactDaoImpl implements ContactDao{

    /**
     * Сервис работы с файловой системой. Преобразует модели в/из данные хранимые на жестком диске.
     */

    private static final File FILE = new File("data");

    public FileSystemContactDaoImpl() {
    }

    //TODO исправить логику так, что бы файл не пересоздавался а дополнялся.
    public void saveContact(Contact contact) {

        try(PrintStream printStream = new PrintStream(new FileOutputStream(FILE, true), true)) {
            printStream.println(contact);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

//    @Override
//    public void removeContact() {
//
//    }

    @Override
    public void displayContact() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE))){
            String line;
            while ((line = reader.readLine()) != null) System.out.println(line);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void findContact(String name){
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE))){
            String line;
            while ((line = reader.readLine()) != null) printLine(line);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void printLine(String line){
        System.out.println(line);
    }
}