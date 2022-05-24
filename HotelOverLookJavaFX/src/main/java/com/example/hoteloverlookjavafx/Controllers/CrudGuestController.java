package com.example.hoteloverlookjavafx.Controllers;

import com.example.hoteloverlookjavafx.Models.Date;
import com.example.hoteloverlookjavafx.Models.Guest;
import com.example.hoteloverlookjavafx.Models.GuestList;
import com.example.hoteloverlookjavafx.OverLookApplication;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class CrudGuestController {
    @FXML
    TextField nameField;
    @FXML TextField phoneField;
    @FXML TextField addressField;
    @FXML TextField nationalityField;
    @FXML DatePicker birthdayField;
    private Stage stage;
    private Scene scene;
    private Parent root;
    private GuestList guests;

    public CrudGuestController() {
        JAXBContext jaxbContext = null;
        try {
            jaxbContext = JAXBContext.newInstance(GuestList.class);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        File file = new File("src\\main\\resources\\student.xml");
        Unmarshaller unmarshaller = null;
        try {
            unmarshaller = jaxbContext.createUnmarshaller();
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        try {
            guests = (GuestList) unmarshaller.unmarshal(file);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    //Change to Guest view
    public void switchToGuests(ActionEvent event) throws IOException {
        root = FXMLLoader.load(OverLookApplication.class.getResource("guests.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    //Change to Home view
    public void switchToHome(ActionEvent event) throws IOException {
        root = FXMLLoader.load(OverLookApplication.class.getResource("home.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void addGuest(ActionEvent event) throws IOException, JAXBException {

        JAXBContext jaxbContext = JAXBContext.newInstance(GuestList.class);
        Marshaller marshaller = jaxbContext.createMarshaller();

        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        File file = new File("src\\main\\resources\\student.xml");
        if(addressField == null && nationalityField == null ){
            guests.add(new Guest(nameField.getText(),phoneField.getText(),new Date(birthdayField.getValue().getDayOfMonth(),birthdayField.getValue().getMonth().getValue(),birthdayField.getValue().getYear())));
            marshaller.marshal(guests, file);
        }else{
            guests.add(new Guest(nameField.getText(),phoneField.getText(),new Date(birthdayField.getValue().getDayOfMonth(),birthdayField.getValue().getMonth().getValue(),birthdayField.getValue().getYear()), addressField.getText(), nationalityField.getText()));
            marshaller.marshal(guests, file);
        }
        root = FXMLLoader.load(OverLookApplication.class.getResource("guests.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
