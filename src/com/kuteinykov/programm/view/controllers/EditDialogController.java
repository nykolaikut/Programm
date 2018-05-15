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
    private static final String PHONE_NUMBER_CHARACTERS = "+- 0987654321";
    private static final int MIN_NUMBER_OF_DIGITS = 7;
    private static final int MAX_NUMBER_OF_DIGITS = 12;
    private static final int MIN_AGE = 1;
    private static final int MAX_AGE = 100;

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

        while(errorText.isEmpty()) {

            // check field Name
            if (!checkName(txtName.getText().trim())) {
                continue;
            }

            // check field Phone
            if (!checkPhone(txtPhone.getText().trim())) {
                continue;
            }

            // check field Age
            if (!checkAge(txtAge.getText().trim())) {
                continue;
            }

            break;
        }

        if (!errorText.isEmpty()) {
            DialogManager.showErrorDialog("Error", errorText);
        }

        return errorText.isEmpty();
    }

    private boolean checkName(String s){
        if (s.length() == 0) {
            errorText = "Field <Name> is empty.";
        }

        return errorText.isEmpty();
    }

    private boolean checkPhone(String s){
        if (s.length() == 0) {
            errorText = "Field <Phone> is empty.";
        }

        if (errorText.isEmpty()) {
            if (!ValidationUtil.checkInput(s, PHONE_NUMBER_CHARACTERS)) {
                errorText = "Use only characters " + PHONE_NUMBER_CHARACTERS + " in the field <Phone>.";
            }
        }

        if (errorText.isEmpty()) {
            if (ValidationUtil.countDigits(s, DIGITS) < MIN_NUMBER_OF_DIGITS
                    || ValidationUtil.countDigits(s, DIGITS) > MAX_NUMBER_OF_DIGITS) {
                errorText = "Number of digits from " + MIN_NUMBER_OF_DIGITS +
                        " to " + MAX_NUMBER_OF_DIGITS + " in the field <Phone>.";
            }
        }

        return errorText.isEmpty();
    }

    private boolean checkAge(String s){
        if (s.length() == 0) {
            errorText = "Field <Age> is empty.";
        }

        if (errorText.isEmpty()) {
            if (!ValidationUtil.checkInput(s, DIGITS))
                errorText = "Use only characters " + DIGITS + " in the field <Age>.";
        }

        if (errorText.isEmpty()) {
            if (Integer.valueOf(s) < MIN_AGE || Integer.valueOf(s) > MAX_AGE)
                errorText = "The value of field <Age> must be in the range "
                        + MIN_AGE + " - " + MAX_AGE + ".";
        }

        return errorText.isEmpty();
    }
}