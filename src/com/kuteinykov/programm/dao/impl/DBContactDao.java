package com.kuteinykov.programm.dao.impl;

import com.kuteinykov.programm.dao.ContactDao;
import com.kuteinykov.programm.model.Contact;

import java.sql.*;

public class DBContactDao implements ContactDao {

    private static final String DB_URL = "jdbc:h2:tcp://localhost/~/Programm";
    private int counter = 0;

    public DBContactDao(){
        try {
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
//        try (Connection connection = DriverManager
//                .getConnection(DB_URL, "Test", "");
//             Statement st = connection.createStatement()){
//            st.execute("CREATE TABLE CLIENT(id BIGINT NOT NULL AUTO_INCREMENT," +
//                    " NAME VARCHAR(60) NOT NULL, phoneNumber VARCHAR(20) NOT NULL," +
//                    " AGE INT(3) NOT NULL," +
//                    " address VARCHAR(60) default '', PRIMARY KEY (id));");

//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
    }

    public void saveContact(String name, String phoneNumber, int age, String address) {
        try (Connection connection = DriverManager
                .getConnection(DB_URL, "Test", "");
            PreparedStatement st =
                connection.prepareStatement("INSERT INTO CLIENT VALUES( default, ?, ?, ?, ?);")){

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

    }
    public void displayContact(){

    }
    public void findContact(String name){

    }
    public void editContact(Contact contact){

    }
}