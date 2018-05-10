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

        contact.setName(txtName.getText());
        contact.setPhoneNumber(txtPhone.getText());
        contact.setAge(Integer.valueOf(txtAge.getText()));
        contact.setAddress(txtAddress.getText());
        actionClose(actionEvent);
    }

    private boolean checkValues(){
        boolean errorIs = false;
        String errorText = "";

        // check field Name
        if (!errorIs && txtName.getText().trim().length() == 0) {
            errorText = errorText + "Field <Name> is empty.";
            errorIs = true;
        }

        // check field Phone
        if (!errorIs && txtPhone.getText().trim().length() == 0) {
            errorText = errorText + "Field <Phone> is empty.";
            errorIs = true;
        }
        if (!errorIs && (!ValidationUtil.checkInput( txtPhone.getText().trim(), SYMBOLS_PHONE_NUMBER)))
        {
            errorText = errorText + " Use only symbols " + SYMBOLS_PHONE_NUMBER + " in field <Phone>.";
            errorIs = true;
        } else if (!errorIs &&
                     (ValidationUtil.countDigits( txtPhone.getText().trim(), DIGITS) < MIN_NUMBER_OF_DIGITS
                     || ValidationUtil.countDigits( txtPhone.getText().trim(), DIGITS) > MAX_NUMBER_OF_DIGITS ))
               {
                   errorText = errorText + "Count digits from " + MIN_NUMBER_OF_DIGITS +
                        " to " + MAX_NUMBER_OF_DIGITS + " in field <Phone>.";
                   errorIs = true;
               }

        // check Age
        if (!errorIs && txtAge.getText().trim().length() == 0) {
            errorText = errorText + "Field <Age> is empty.";
            errorIs = true;
        }
        if (!errorIs && (!ValidationUtil.checkInput( txtAge.getText().trim(), DIGITS)))
        {
            errorText = errorText + " Use only symbols " + DIGITS + " in field <Age>.";
            errorIs = true;
        } else if(!errorIs &&
                  (Integer.valueOf(txtAge.getText()) < MIN_AGE
                  || Integer.valueOf(txtAge.getText()) > MAX_AGE))
               {
                  errorText = errorText + "The value of field <Age> must be in the range "
                            + MIN_AGE + " - " + MAX_AGE + ".";
                  errorIs = true;
               }

        if (errorIs) {
            DialogManager.showErrorDialog("Error", errorText);
            return false;
        }
        return true;
    }
}