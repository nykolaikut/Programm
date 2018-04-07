package com.kuteinykov.programm.view.impl;

import com.kuteinykov.programm.services.ContactService;
import com.kuteinykov.programm.view.CmdLineService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class CmdLineServiceImpl implements CmdLineService {

    private static final String DIGITS = "0987654321";
    private static final String SYMBOLS_PHONE_NUMBER = "+- 0987654321";
    private static final int MIN_NUMBER_OF_DIGITS = 7;

    private ContactService contactService;
    private BufferedReader br =
            new BufferedReader(new InputStreamReader(System.in));

    public CmdLineServiceImpl(ContactService contactService) {
        this.contactService = contactService;
    }

    @Override
    public void runMenu() throws IOException {
        boolean isRunning = true;
        initContact();
        while (isRunning) {
            showMenu();
            String line = br.readLine();
            switch (line) {
                case "1": {
                    createContact();
                    break;
                }
                case "2": {
                    deleteContact();
                    break;
                }
                case "3": {
                    displayContact();
                    break;
                }
                case "4": {
                    editContact();
                    break;
                }
                case "5": {
                    findContact();
                    break;
                }
                case "0": {
                    isRunning = false;
                    break;
                }
                default:
                    System.out.println("Repeat");
            }
        }
    }

    private static void showMenu() {
        System.out.println("1. Create Contact");
        System.out.println("2. Delete Contact");
        System.out.println("3. View Contact");
        System.out.println("4. Edit Contact");
        System.out.println("5. Find Contact");
        System.out.println("0. Exit");
    }

    private void createContact() throws IOException {
        String name = readName();

        System.out.println("Enter phoneNumber");
        String phoneNumber = br.readLine();
        phoneNumber = phoneNumber.trim();
        if(!checkInput(phoneNumber,SYMBOLS_PHONE_NUMBER)) {
            System.out.println("Wrong input! " + SYMBOLS_PHONE_NUMBER);
            return;
        }
        if(countDigits(phoneNumber) < MIN_NUMBER_OF_DIGITS){
            System.out.println("Wrong input!");
            return;
        }

        System.out.println("Enter age");
        String number = br.readLine();
        number = number.trim();
        if(!checkInput(number,DIGITS)) {
            System.out.println("Wrong input!");
            return;
        }
        int age = Integer.parseInt(number);

        System.out.println("Enter address");
        String address = br.readLine();
        address = address.trim();

        if (!name.isEmpty() && !phoneNumber.isEmpty() ) {
            this.contactService.createContact(name, phoneNumber, age, address);
        } else {
            System.out.println("A name and phone number must be provided" );
        }
    }

    private void deleteContact() throws IOException {
        System.out.println("Enter ID");
        String number = br.readLine();
        if(checkInput(number,DIGITS)) {
            int id = Integer.parseInt(number);
            this.contactService.deleteContact(id);
        } else System.out.println("Wrong input!");
    }

    private void displayContact(){
       this.contactService.displayContact();

    }
    private void editContact() throws IOException {

        System.out.println("Enter ID");
        String number = br.readLine();
        if(checkInput(number,DIGITS)) {
            int id = Integer.parseInt(number);

            String name = readName();

            if (!name.isEmpty())
                this.contactService.editContact(id, name);
            else System.out.println("Wrong input!");

        } else System.out.println("Wrong input!");
    }

    private void initContact(){
        this.contactService.initContact();
    }

    private void  findContact() throws IOException {
        String name = readName();
        if (!name.isEmpty())
            this.contactService.findContact(name);
        else System.out.println("Wrong input!");
    }

    private String readName() throws IOException {
        System.out.println("Enter name");
        String name = br.readLine();
        return name.trim();
    }

    private boolean checkInput(String number,String digits ){

        if(number.isEmpty()) return false;

        for (int i = 0; i < number.length(); i++){
            if (!digits.contains(number.substring(i,(i+1))))
                return false;
        }
        return true;
    }

    private int countDigits(String number){

        if(number.isEmpty()) return 0;

        String digitsPhonNumber = "";

        for (int i = 0; i < number.length(); i++){
            if (DIGITS.contains(number.substring(i,(i+1))))
                digitsPhonNumber += number.substring(i,(i+1));
        }
        return Integer.toString(Integer.parseInt(digitsPhonNumber)).length();
    }
}