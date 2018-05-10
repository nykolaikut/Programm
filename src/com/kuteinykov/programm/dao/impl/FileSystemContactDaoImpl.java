package com.kuteinykov.programm.dao.impl;

import com.kuteinykov.programm.dao.ContactDao;
import com.kuteinykov.programm.model.Contact;

import java.io.*;
import java.util.ArrayList;


public class FileSystemContactDaoImpl implements ContactDao{

    /**
     * Сервис работы с файловой системой. Преобразует модели в/из данные хранимые на жестком диске.
     */

    private static final File FILE = new File("data.txt");
    private ArrayList<Contact> contactList = new ArrayList<Contact>();
    private long idContact = 0;
    private boolean aBoolean;

    public FileSystemContactDaoImpl() {
        if (!FILE.exists()) {
            initContact();
            writeCollectionToFile();
        } else initIdContact();
    }

    private void initIdContact(){
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE))){
            String line;
            while ((line = reader.readLine()) != null) initId(line);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initId(String line){
        String[] str = line.split(":");
        Long id = Long.valueOf(str[0]);
        if (idContact < id) idContact = id;
    }

    //TODO исправить логику так, что бы файл не пересоздавался а дополнялся.
    public void saveContact(Contact newContact) {

        idContact++;
        Contact contact = new Contact(idContact,newContact.getName(), newContact.getPhoneNumber(),
                newContact.getAge(), newContact.getAddress());

        try(PrintStream printStream = new PrintStream(new FileOutputStream(FILE, true), true)) {
            printStream.println(contact);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Contact> selectAllContact() {
        if (!contactList.isEmpty()) contactList.clear();

        readFile();
        return contactList;
    }

    public void deleteContact(long id) {

        if (!contactList.isEmpty()) contactList.clear();

        readFile();

        int index = findIndexOfElement(id);
        if (index > -1 ) contactList.remove(index);

        if (FILE.delete()) {
            writeCollectionToFile();
        }
    }

    private int findIndexOfElement(long id){
        for (int i = 0; i < contactList.size(); i++ ) {
            if (contactList.get(i).getId() == id) {
                return i;
            }
        }
        return -1;
    }

    private void readFile(){
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE))){
            String line;
            while ((line = reader.readLine()) != null) initCollection(line);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeCollectionToFile(){
        try(PrintStream printStream = new PrintStream(new FileOutputStream(FILE, true), true)) {
            for (Contact contact: contactList) {
                printStream.println(contact);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void initCollection(String line){
        String[] str = line.split(":");

        long id = Long.valueOf(str[0]);
        String name = str[1];
        String phoneNumber = str[2];
        int age = Integer.valueOf(str[3]);
        String address = str[4];

        contactList.add( new Contact(id, name, phoneNumber, age,address));
    }

    public void editContact(Contact newContact){
        if (!contactList.isEmpty()) contactList.clear();

        readFile();

        int index = findIndexOfElement(newContact.getId());
        if (index > -1 ) contactList.set(index, newContact);

        if (FILE.delete()) {
            writeCollectionToFile();
        }
    }

    public ArrayList<Contact> findContact(String name){

        if (!contactList.isEmpty()) contactList.clear();

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE))){
            String line;
            while ((line = reader.readLine()) != null) searchContact(name, line);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return contactList;
    }

    private void searchContact(String searchName, String line){

        int lengthSearch = searchName.length();
        String[] str = line.split(":");

        long id = Long.valueOf(str[0]);
        String name = str[1];
        String phoneNumber = str[2];
        int age = Integer.valueOf(str[3]);
        String address = str[4];

        if (name.length() >= lengthSearch){
            if (name.substring(0,lengthSearch).compareToIgnoreCase(searchName) == 0 ) {
                contactList.add( new Contact(id, name, phoneNumber, age,address));
            }
        }
   }

   private void initContact() {
       idContact++;
       contactList.add( new Contact(idContact,
               "Olga", "1234567", 25, "Address Olga Kyiv"));
       idContact++;
       contactList.add( new Contact(idContact,
               "Tanya", "45-12-36-7", 22, "Address Tanya"));
       idContact++;
       contactList.add( new Contact(idContact,
               "Natasha", "12 34 567", 32, "Address Natasha"));
       idContact++;
       contactList.add( new Contact(idContact,
               "Olga", "56-88-908", 33, "Address Olga Dnepr"));
       idContact++;
       contactList.add( new Contact(idContact,
               "Oleg", "+38-093-123-45-67", 88, "Address Oleg"));
       idContact++;
       contactList.add( new Contact(idContact,
               "Yana", "067 369 98 908", 46, "Address Yana"));
       idContact++;
       contactList.add( new Contact(idContact,
               "Anna", "0911234567", 43, "Address Anna"));
       idContact++;
       contactList.add( new Contact(idContact,
               "Sveta", "756-88-90", 63, "Address Sveta"));
   }
}