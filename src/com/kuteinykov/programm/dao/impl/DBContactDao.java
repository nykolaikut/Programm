package com.kuteinykov.programm.dao.impl;

import com.kuteinykov.programm.dao.ContactDao;
import com.kuteinykov.programm.model.Contact;

import java.sql.*;
import java.util.ArrayList;

public class DBContactDao implements ContactDao {

    private static final String DB_DRIVE = "org.h2.Driver";
    private static final String DB_URL = "jdbc:h2:tcp://localhost/~/Programm";
    private static final String TABLE_NAME = "CLIENT";
    private static final String USER_NAME = "Test";
    private static final String PASSWORD = "";
    private ArrayList<Contact> contactList = new ArrayList<Contact>();

    public DBContactDao(){
        try {
            Class.forName(DB_DRIVE);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try (Connection connection = DriverManager
                .getConnection(DB_URL, USER_NAME, PASSWORD);
             Statement st = connection.createStatement())
        {
             st.execute("CREATE TABLE IF NOT EXISTS " + TABLE_NAME +
                  "(id BIGINT NOT NULL AUTO_INCREMENT," +
                  " NAME VARCHAR(60) NOT NULL, phoneNumber VARCHAR(20) NOT NULL," +
                  " AGE INT(3) NOT NULL," +
                  " address VARCHAR(60) default '', PRIMARY KEY (id));");
        } catch (SQLException e) {
             e.printStackTrace();
        }
    }

    public void saveContact(String name, String phoneNumber, int age, String address) {
        try (Connection connection = DriverManager
                .getConnection(DB_URL, USER_NAME, PASSWORD);
             PreparedStatement st =
                connection.prepareStatement("INSERT INTO " + TABLE_NAME + " VALUES( default, ?, ?, ?, ?);")){

            st.setString(1, name);
            st.setString(2, phoneNumber);
            st.setInt(3, age);
            st.setString(4, address);

            st.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteContact(long id){
        try (Connection connection = DriverManager
                .getConnection(DB_URL, USER_NAME, PASSWORD);
             PreparedStatement st =
                connection.prepareStatement("DELETE FROM " + TABLE_NAME + " WHERE ID = ?;")){

            st.setLong(1, id);

            st.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Contact> displayContact(){
        if (!contactList.isEmpty()) contactList.clear();

        String query = "SELECT * FROM " + TABLE_NAME + ";";
        selectContact(query);
        return contactList;
    }

    public ArrayList<Contact> findContact(String searchName){
        if (!contactList.isEmpty()) contactList.clear();

        int lengthSearch = searchName.length();
        String query = "SELECT * FROM " + TABLE_NAME +
                " WHERE UPPER(SUBSTR(NAME,1," + lengthSearch + ")) =  '" + searchName.toUpperCase() + "';";

        selectContact(query);
        return contactList;
    }

    private void selectContact(String query){
        try (Connection connection = DriverManager
                .getConnection(DB_URL, USER_NAME, PASSWORD);
             PreparedStatement st =
                     connection.prepareStatement(query)){

            ResultSet res = st.executeQuery();
            while (res.next()){
                Long id = res.getLong("id");
                String name = res.getString("name");
                String phoneNumber = res.getString("phonenumber");
                int age = res.getInt("age");
                String address = res.getString("address");

                contactList.add(new Contact(id, name, phoneNumber, age, address));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void editContact(Contact contact){
        try (Connection connection = DriverManager
                .getConnection(DB_URL, USER_NAME, PASSWORD);
             PreparedStatement st =
                connection.prepareStatement(
                   "UPDATE " + TABLE_NAME +" SET NAME = ?, PHONENUMBER = ?, AGE = ?, ADDRESS = ? WHERE ID = ?;")){

            st.setString(1, contact.getName());
            st.setString(2, contact.getPhoneNumber());
            st.setInt(3, contact.getAge());
            st.setString(4, contact.getAddress());
            st.setLong(5, contact.getId());

            st.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}