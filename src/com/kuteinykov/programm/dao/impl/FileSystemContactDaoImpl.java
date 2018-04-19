package com.kuteinykov.programm.dao.impl;

import com.kuteinykov.programm.dao.ContactDao;
import com.kuteinykov.programm.model.Contact;

import java.io.*;
import java.util.HashMap;

public class FileSystemContactDaoImpl implements ContactDao{

    /**
     * Сервис работы с файловой системой. Преобразует модели в/из данные хранимые на жестком диске.
     */

    private static final File FILE = new File("data.txt");
    private HashMap<Long, Contact> searchContactList = new HashMap<>();
    private long idContact = 0;

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
    public void saveContact(String name, String phoneNumber, int age, String address) {

        idContact++;
        Contact contact = new Contact(idContact, name, phoneNumber, age, address);

        try(PrintStream printStream = new PrintStream(new FileOutputStream(FILE, true), true)) {
            printStream.println(contact);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void displayContact() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE))){
            String line;
            while ((line = reader.readLine()) != null) System.out.println(line);
            System.out.println();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteContact(long id) {

        if (!searchContactList.isEmpty()) searchContactList.clear();

        readFile();
        searchContactList.remove(id);

        if (FILE.delete()) {
            writeCollectionToFile();
        }
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
            for (Contact contact: searchContactList.values()) {
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

        searchContactList.put(id, new Contact(id, name, phoneNumber, age,address));
    }

    public void editContact(Contact newContact){
        if (!searchContactList.isEmpty()) searchContactList.clear();

        readFile();

        if (searchContactList.containsKey(newContact.getId()))
            searchContactList.put(newContact.getId(), newContact);

        if (FILE.delete()) {
            writeCollectionToFile();
        }
    }

    public void findContact(String name){

        if (!searchContactList.isEmpty()) searchContactList.clear();

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE))){
            String line;
            while ((line = reader.readLine()) != null) searchContact(name, line);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (!searchContactList.isEmpty()) {
            System.out.printf("%20s","Search results");
            displayListContact(searchContactList);
        }
    }

    private void searchContact(String nameSearch, String line){

        int lengthSearch = nameSearch.length();
        String[] str = line.split(":");

        long id = Long.valueOf(str[0]);
        String name = str[1];
        String phoneNumber = str[2];
        int age = Integer.valueOf(str[3]);
        String address = str[4];

        if (name.length() >= lengthSearch){
            if (name.substring(0,lengthSearch).compareToIgnoreCase(nameSearch) == 0 ) {
                searchContactList.put(id, new Contact(id, name, phoneNumber, age,address));
            }
        }
   }

   private void displayListContact(HashMap<Long, Contact> list) {
       System.out.println();
       for (Contact contact : list.values()) {
           System.out.println(contact);
       }
       System.out.println();
   }

   private void initContact() {
       idContact++;
       searchContactList.put(idContact, new Contact(idContact,
               "Olga", "1234567", 25, "Address Olga Kyiv"));
       idContact++;
       searchContactList.put(idContact, new Contact(idContact,
               "Tanya", "45-12-36-7", 22, "Address Tanya"));
       idContact++;
       searchContactList.put(idContact, new Contact(idContact,
               "Natasha", "12 34 567", 32, "Address Natasha"));
       idContact++;
       searchContactList.put(idContact, new Contact(idContact,
               "Olga", "56-88-908", 33, "Address Olga Dnepr"));
       idContact++;
       searchContactList.put(idContact, new Contact(idContact,
               "Oleg", "+38-093-123-45-67", 88, "Address Oleg"));
       idContact++;
       searchContactList.put(idContact, new Contact(idContact,
               "Yana", "067 369 98 908", 46, "Address Yana"));
       idContact++;
       searchContactList.put(idContact, new Contact(idContact,
               "Anna", "0911234567", 43, "Address Anna"));
       idContact++;
       searchContactList.put(idContact, new Contact(idContact,
               "Sveta", "756-88-90", 63, "Address Sveta"));
   }
}