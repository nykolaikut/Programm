package com.kuteinykov.programm.view.controllers;

import com.kuteinykov.programm.dao.impl.DBContactDao;
import com.kuteinykov.programm.model.Contact;
import com.kuteinykov.programm.services.ContactService;
import com.kuteinykov.programm.services.impl.CollectionContactBook;
import com.kuteinykov.programm.services.impl.FSContactServiceImpl;
import com.kuteinykov.programm.utils.DialogManager;
import javafx.beans.property.ObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.controlsfx.control.textfield.CustomTextField;
import org.controlsfx.control.textfield.TextFields;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MainController {

    private ContactService contactService;
    private CollectionContactBook contactBookImpl;
    private Stage mainStage;

    public MainController() {
        this.contactService = new FSContactServiceImpl(new DBContactDao());
        contactBookImpl = new CollectionContactBook();
    }

    @FXML
    private Button btnCreate;

    @FXML
    private Button btnEdit;

    @FXML
    private Button btnDelete;

    @FXML
    private CustomTextField txtSearch;

    @FXML
    private Button btnSearch;

    @FXML
//    private TableView<Contact> tableContact;
    private TableView tableContact;

    @FXML
    private TableColumn<Contact, Long> columnId;

    @FXML
    private TableColumn<Contact, String> columnName;

    @FXML
    private TableColumn<Contact, String> columnPhone;

    @FXML
    private TableColumn<Contact, Integer> columnAge;

    @FXML
    private TableColumn<Contact, String> columnAddress;

    @FXML
    private Label labelCount;

    private Parent fxmlEdit;
    private FXMLLoader fxmlLoader = new FXMLLoader();
    private EditDialogController editDialogController;
    private Stage editDialogStage;
    private ResourceBundle resourceBundle;

    private ObservableList<Contact> backupList;

    @FXML
//    private void initialize(URL location, ResourceBundle resources ) {
//        this.resourceBundle = resources;
    private void initialize() {
        columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnName.setCellValueFactory(new PropertyValueFactory<>("name"));
        columnPhone.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        columnAge.setCellValueFactory(new PropertyValueFactory<>("age"));
        columnAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        setupClearButtonField(txtSearch);
        initListener();

        fillData();

        initLoader();
    }

    private void setupClearButtonField(CustomTextField customTextField){
        try{
            Method m = TextFields.class.getDeclaredMethod("setupClearButtonField", TextField.class, ObjectProperty.class);
            m.setAccessible(true);
            m.invoke(null, customTextField, customTextField.rightProperty());
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void setMainStage(Stage mainStage) {
        this.mainStage = mainStage;
    }

    private void fillData() {
        contactBookImpl.fillData((ArrayList) contactService.displayAllContact());
        backupList = FXCollections.observableArrayList();
        backupList.addAll(contactBookImpl.getContactlist());
        tableContact.setItems(contactBookImpl.getContactlist());
    }

    private void initListener() {

        contactBookImpl.getContactlist().addListener((ListChangeListener<Contact>) c -> updateLabelCount());
//        contactBookImpl.getContactlist().addListener(new ListChangeListener<Contact>() {
//            @Override
//            public void onChanged(Change<? extends Contact> c) {
//                updateLabelCount();
//            }
//        });

        tableContact.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                editDialogController.setContact((Contact) tableContact.getSelectionModel().getSelectedItem());
                showDialog();
                contactService.editContact((Contact) tableContact.getSelectionModel().getSelectedItem());
            }
        });
//        tableContact.setOnMouseClicked(new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent event) {
//                if (event.getClickCount() == 2) {
//                    editDialogController.setContact((Contact) tableContact.getSelectionModel().getSelectedItem());
//                    showDialog();
//                    contactService.editContact((Contact) tableContact.getSelectionModel().getSelectedItem());
//                }
//            }
//        });

    }

    private void initLoader() {
        try {
            fxmlLoader.setLocation(getClass().getResource("editScene.fxml"));
            fxmlEdit = fxmlLoader.load();
            editDialogController = fxmlLoader.getController();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void updateLabelCount() {
        labelCount.setText("Count: " + contactBookImpl.getContactlist().size());
    }

    public void actionButtonPressed(ActionEvent actionEvent) {

        Object sourse = actionEvent.getSource();

        // If the button is not pressed
        if (!(sourse instanceof Button)) return;

        Button clickButton = (Button) sourse;
        Contact selectedContact = (Contact) tableContact.getSelectionModel().getSelectedItem();
        Window parentWindow = ((Node) actionEvent.getSource()).getScene().getWindow();
        editDialogController.setContact(selectedContact);

        switch (clickButton.getId()) {
            case "btnCreate":
                editDialogController.setContact(new Contact());
                showDialog();

                if (editDialogController.getContact().getName() != "") {
                    contactService.createContact(editDialogController.getContact());
                    contactBookImpl.getContactlist().clear();
                    contactBookImpl.fillData((ArrayList) contactService.displayAllContact());
                    backupList.clear();
                    backupList.addAll(contactBookImpl.getContactlist());
//                    contactBookImpl.add(editDialogController.getContact());
                }
                break;

            case "btnEdit":
                if (!contactIsSelected(selectedContact)) return;

                editDialogController.setContact((Contact) tableContact.getSelectionModel().getSelectedItem());
                showDialog();
                contactService.editContact(selectedContact);

                break;

            case "btnDelete":
                if (!contactIsSelected(selectedContact)) return;

                contactService.deleteContact(((Contact) tableContact.getSelectionModel().getSelectedItem()).getId());
                backupList.remove(((Contact) tableContact.getSelectionModel().getSelectedItem()));
                contactBookImpl.delete(((Contact) tableContact.getSelectionModel().getSelectedItem()));
                break;
        }
    }

    private boolean contactIsSelected(Contact selectedContact){
        if (selectedContact == null){
            DialogManager.showInfoDialog("Error", "Select contact.");
            return false;
        }
        return true;
    }

    private void showDialog() {
        if (editDialogStage == null) {
            editDialogStage = new Stage();
            editDialogStage.setTitle("Edit");
            editDialogStage.setMinHeight(210);
            editDialogStage.setMinWidth(400);
            editDialogStage.setResizable(false);
            editDialogStage.setScene(new Scene(fxmlEdit));
            editDialogStage.initModality(Modality.WINDOW_MODAL);
            editDialogStage.initOwner(mainStage);
        }
        editDialogStage.showAndWait();
    }

    public void actionSearch(ActionEvent actionEvent) {
        contactBookImpl.getContactlist().clear();

        int lengthSearch = txtSearch.getText().trim().length();
        for (Contact contact : backupList) {
            if (contact.getName().length() >= lengthSearch) {
                if (contact.getName().substring(0, lengthSearch).compareToIgnoreCase(txtSearch.getText().trim()) == 0) {
                    contactBookImpl.getContactlist().add(contact);
                }
            }
        }
    }
}