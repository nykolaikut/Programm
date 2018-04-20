package com.kuteinykov.programm.dao.impl;

import com.kuteinykov.programm.dao.ContactDao;
import com.kuteinykov.programm.model.Contact;

import java.sql.*;

public class DBContactDao implements ContactDao {

    private static final String DB_URL = "jdbc:h2:tcp://localhost/~/Program";
    private int counter = 0;

    public DBContactDao(){
        try {
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Can't connect to DB");
        }
        try (Connection connection = DriverManager
                .getConnection(DB_URL, "Test", "");
             Statement st = connection.createStatement()){
            st.execute("CREATE TABLE CLIENT(ID INT AUTO_INCREMENT PRIMARY KEY, NAME VARCHAR(255), AGE INT);");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveContact(String name, String phoneNumber, int age, String address) {
        try (Connection connection = DriverManager
                .getConnection(DB_URL, "Test", "");
            PreparedStatement st =
                connection.prepareStatement("INSERT INTO CLIENT VALUES(?, ?, ?, ?);")){

            st.setInt(1, counter++);
            st.setString(2, name);
            st.setInt(3, age);
            st.setString(4, address);

            st.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void deleteContact(long id){

    }
    public void displayContact(){

    }
    public void findContact(String name){

    }
    public void editContact(Contact contact){

    }
}