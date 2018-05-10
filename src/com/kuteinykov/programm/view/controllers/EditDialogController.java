package com.kuteinykov.programm.view.controllers;

import com.kuteinykov.programm.model.Contact;
import com.kuteinykov.programm.utils.DialogManager;
import com.kuteinykov.programm.utils.ValidationUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EditDialogController {

    private static final String DIGITS = "0987654321";
    private static final String SYMBOLS_PHONE_NUMBER = "+- 0987654321";
    private static final int MIN_NUMBER_OF_DIGITS = 7;
    private static final int MAX_NUMBER_OF_DIGITS = 12;
    private static final int MAX_AGE = 100;
    private static final int MIN_AGE = 1;

    @FXML
    private Label labelName;
    @FXML
    private Label labelPhone;
    @FXML
    private Label labelAge;
    @FXML
    private Label labelAddress;
    @FXML
    private TextField txtName;
    @FXML
    private TextField txtPhone;
    @FXML
    private TextField txtAge;
    @FXML
    private TextField txtAddress;
    @FXML
    private Button btnOk;
    @FXML
    private Button btnCancel;

    private Contact contact;
    private Object eventSource;
    private String errorText;

    public void setContact(Contact contact){
        if (contact == null) return;

        this.contact = contact;

        txtName.setText(contact.getName());
        txtPhone.setText(contact.getPhoneNumber());
        if (contact.getAge() != 0) {
            txtAge.setText(String.valueOf(contact.getAge()));
        } else {
            txtAge.setText("");
        }
        txtAddress.setText(contact.getAddress());
    }

    public Contact getContact() {
        return contact;
    }

    public void actionClose(ActionEvent actionEvent) {
        Node sourse = (Node) actionEvent.getSource();
        Stage stage = (Stage) sourse.getScene().getWindow();
        stage.hide();
    }

    public void actionSave(ActionEvent actionEvent){
        if (!checkValues()) return;

        contact.setName(txtName.getText().trim());
        contact.setPhoneNumber(txtPhone.getText().trim());
        contact.setAge(Integer.valueOf(txtAge.getText().trim()));
        contact.setAddress(txtAddress.getText().trim());
        actionClose(actionEvent);
    }

    private boolean checkValues(){
        errorText = "";
        boolean checkField = true;

        while(true) {

            // check field Name
            if (!checkName(txtName.getText().trim())) {
                checkField = false;
                break;
            }

            // check field Phone
            if (!checkPhone(txtPhone.getText().trim())) {
                checkField = false;
                break;
            }

            // check field Age
            if (!checkAge(txtAge.getText().trim())) {
                checkField = false;
                break;
            }

            break;
        }

        if (!checkField) {
            DialogManager.showErrorDialog("Error", errorText);
            return false;
        }
        return true;
    }

    private boolean checkName(String s){
        if (s.length() == 0) {
            errorText = "Field <Name> is empty.";
            return false;
        }
        return true;
    }

    private boolean checkPhone(String s){
        if (s.length() == 0) {
            errorText = "Field <Phone> is empty.";
            return false;
        }

        if (!ValidationUtil.checkInput( s, SYMBOLS_PHONE_NUMBER))
        {
            errorText = "Use only symbols " + SYMBOLS_PHONE_NUMBER + " in field <Phone>.";
            return false;
        }

        if (ValidationUtil.countDigits( s, DIGITS) < MIN_NUMBER_OF_DIGITS
            || ValidationUtil.countDigits( s, DIGITS) > MAX_NUMBER_OF_DIGITS )
        {
            errorText = "Count of digits from " + MIN_NUMBER_OF_DIGITS +
                        " to " + MAX_NUMBER_OF_DIGITS + " in field <Phone>.";
            return false;
        }

        return true;
    }

    private boolean checkAge(String s){
        if (s.length() == 0) {
            errorText = "Field <Age> is empty.";
            return false;
        }

        if (!ValidationUtil.checkInput( s, DIGITS))
        {
            errorText =  "Use only symbols " + DIGITS + " in field <Age>.";
            return false;
        }

        if(Integer.valueOf(s) < MIN_AGE || Integer.valueOf(s) > MAX_AGE)
        {
            errorText = "The value of field <Age> must be in the range "
                        + MIN_AGE + " - " + MAX_AGE + ".";
            return false;
        }

        return true;
    }
}