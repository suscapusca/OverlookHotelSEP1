package com.example.hoteloverlookjavafx.Controllers;

import com.example.hoteloverlookjavafx.Models.Booking;
import com.example.hoteloverlookjavafx.Models.BookingList;
import com.example.hoteloverlookjavafx.OverLookApplication;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class BookingController implements Initializable {
    @FXML TableColumn<Booking, String> guestColumn;
    @FXML TableColumn<Booking, String> roomColumn;
    @FXML TableColumn<Booking, String> arrivalColumn;
    @FXML TableColumn<Booking, String> departureColumn;
    @FXML TableColumn<Booking, String> guestsColumn;
    @FXML TableColumn<Booking, String> extraColumn;
    @FXML TableColumn<Booking, String> stateColumn;
    @FXML TableView<Booking> table;
    private BookingList bookings;
    private Stage stage;
    private Scene scene;
    private Parent root;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

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

        ObservableList<Booking> oL = FXCollections.observableArrayList();
        if(bookings!=null) {
            oL.addAll(bookings.getAllBookings());
            table.getItems().addAll(oL);
        }
        guestColumn.setCellValueFactory(features -> new ReadOnlyStringWrapper(features.getValue().getGuest().getName()));
        roomColumn.setCellValueFactory(features -> new ReadOnlyStringWrapper(String.valueOf(features.getValue().getRoom().getNumber())));
        arrivalColumn.setCellValueFactory(features -> new ReadOnlyStringWrapper(String.valueOf(features.getValue().getDateInterval().getStartDate())));
        departureColumn.setCellValueFactory(features -> new ReadOnlyStringWrapper(String.valueOf(features.getValue().getDateInterval().getEndDate())));
        guestsColumn.setCellValueFactory(features -> new ReadOnlyStringWrapper(String.valueOf(features.getValue().getGuests())));
        extraColumn.setCellValueFactory(features -> new ReadOnlyStringWrapper(features.getValue().getExtraInfo()));
        stateColumn.setCellValueFactory(features-> new ReadOnlyStringWrapper(features.getValue().getState()));

    }

    public BookingController(){
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

    //Change to Home view
    public void switchToHome(ActionEvent event) throws IOException {
        root = FXMLLoader.load(OverLookApplication.class.getResource("home.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    public void switchToAddBook(ActionEvent event) throws IOException {
        root = FXMLLoader.load(OverLookApplication.class.getResource("addBooking.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}