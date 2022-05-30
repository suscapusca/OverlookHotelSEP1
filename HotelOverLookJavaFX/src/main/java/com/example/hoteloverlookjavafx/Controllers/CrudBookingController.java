package com.example.hoteloverlookjavafx.Controllers;

import com.example.hoteloverlookjavafx.Models.*;
import com.example.hoteloverlookjavafx.OverLookApplication;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import com.example.hoteloverlookjavafx.Models.Booking;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class CrudBookingController implements Initializable {
    @FXML TextField nameField1;
    @FXML TextField phoneField1;
    @FXML DatePicker birthdayField1;
    @FXML TextField addressField1;
    @FXML TextField nationalityField1;
    @FXML DatePicker arrivalField1;
    @FXML DatePicker departureField1;
    @FXML TextField guestsField1;
    @FXML TextArea extraInfoField1;
    @FXML ChoiceBox<String> stateBox1;
    @FXML ChoiceBox stateRoom1;
    @FXML CheckBox extrasCheck1;
    @FXML TextArea extrasField1;
    @FXML TextField priceField1;

    @FXML TextField nameField;
    @FXML TextField phoneField;
    @FXML DatePicker birthdayField;
    @FXML TextField addressField;
    @FXML TextField nationalityField;
    @FXML TextArea extraInfoField;
    @FXML TextField guestsField;
    @FXML DatePicker arrivalField;
    @FXML DatePicker departureField;
    @FXML TextArea extrasField;
    @FXML TextField priceField;
    @FXML ChoiceBox<String> stateBox;
    @FXML ChoiceBox stateRoom;
    @FXML CheckBox extrasCheck;
    private BookingList bookings = new BookingList();
    private Stage stage;
    private Scene scene;
    Parent root;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        stateBox.getItems().add("BOOKED");
        stateBox.getItems().add("ARRIVED");
        stateBox.getItems().add("CLOSED");
        stateBox1.getItems().add("BOOKED");
        stateBox1.getItems().add("ARRIVED");
        stateBox1.getItems().add("CLOSED");
    }

    public void initPlaceholders(Booking booking){
        nameField1.setText(booking.getGuest().getName());
        phoneField1.setText(booking.getGuest().getPhone());
        LocalDate localDate = LocalDate.of(booking.getGuest().getBirthday().getYear(), booking.getGuest().getBirthday().getMonth(), booking.getGuest().getBirthday().getDay());
        birthdayField1.setValue(localDate);
        nationalityField1.setText(booking.getGuest().getNationality());
        addressField1.setText(booking.getGuest().getAddress());
        arrivalField1.setValue(LocalDate.of(booking.getDateInterval().getStartDate().getYear(),booking.getDateInterval().getStartDate().getMonth(),booking.getDateInterval().getStartDate().getDay()));
        departureField1.setValue(LocalDate.of(booking.getDateInterval().getEndDate().getYear(),booking.getDateInterval().getEndDate().getMonth(),booking.getDateInterval().getEndDate().getDay()));
        guestsField1.setText(String.valueOf(booking.getGuests()));
        extraInfoField1.setText(booking.getExtraInfo());
        if(booking.getExtra().getExtras()== "" || booking.getExtra().getExtras().equals(""))
            extrasCheck1.setDisable(false);
        priceField1.setText(String.valueOf(booking.getExtra().getPriceExtra()));

    }

    public CrudBookingController(){
        JAXBContext jaxbContext = null;
        try {
            jaxbContext = JAXBContext.newInstance(BookingList.class);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        File file = new File("src\\main\\resources\\bookings.xml");
        Unmarshaller unmarshaller = null;
        try {
            if (jaxbContext != null) {
                unmarshaller = jaxbContext.createUnmarshaller();
            }
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        try {
            if (unmarshaller != null) {
                bookings = (BookingList) unmarshaller.unmarshal(file);
            }
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    public void switchBack(ActionEvent event) throws IOException {
        root = FXMLLoader.load(OverLookApplication.class.getResource("bookings.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void clickExtras(ActionEvent actionEvent) {
        if(extrasCheck.isSelected()){
            extrasField.setDisable(false);
            priceField.setDisable(false);
        }else{
            extrasField.setText("");
            priceField.setText("");
            extrasField.setDisable(true);
            priceField.setDisable(true);
        }
    }


    public void addBooking(ActionEvent event) throws IOException, JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(BookingList.class);
        Marshaller marshaller = jaxbContext.createMarshaller();
        Room room =  new Room(1,100,"ALWDWALDAW",1,2);

        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        File file = new File("src\\main\\resources\\bookings.xml");
        if(extrasCheck.isSelected()){
            bookings.add(new Booking(new Guest(nameField.getText(),phoneField.getText(),new Date(birthdayField.getValue().getDayOfMonth(),birthdayField.getValue().getMonth().getValue(),birthdayField.getValue().getYear())),new DateInterval(new Date(arrivalField.getValue().getDayOfMonth(), arrivalField.getValue().getMonth().getValue(),arrivalField.getValue().getYear()),new Date(departureField.getValue().getDayOfMonth(),departureField.getValue().getMonth().getValue(),departureField.getValue().getYear())),room,Integer.parseInt(guestsField.getText()),extraInfoField.getText(),stateBox.getSelectionModel().getSelectedItem().toString(),new Extra(Float.parseFloat(priceField.getText()), extrasField.getText())));
            marshaller.marshal(bookings, file);
        }else{
            bookings.add(new Booking(new Guest(nameField.getText(),phoneField.getText(),new Date(birthdayField.getValue().getDayOfMonth(),birthdayField.getValue().getMonth().getValue(),birthdayField.getValue().getYear())),new DateInterval(new Date(arrivalField.getValue().getDayOfMonth(), arrivalField.getValue().getMonth().getValue(),arrivalField.getValue().getYear()),new Date(departureField.getValue().getDayOfMonth(),departureField.getValue().getMonth().getValue(),departureField.getValue().getYear())),room,Integer.parseInt(guestsField.getText()),extraInfoField.getText(),stateBox.getSelectionModel().getSelectedItem().toString()));
            marshaller.marshal(bookings, file);
        }
        root = FXMLLoader.load(OverLookApplication.class.getResource("bookings.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void changeBooking(ActionEvent actionEvent) {
    }
}
