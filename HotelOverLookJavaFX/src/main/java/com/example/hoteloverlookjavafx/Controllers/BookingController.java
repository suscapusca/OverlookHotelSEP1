package com.example.hoteloverlookjavafx.Controllers;

import com.example.hoteloverlookjavafx.Models.Booking;
import com.example.hoteloverlookjavafx.Models.BookingList;
import com.example.hoteloverlookjavafx.OverLookApplication;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import javafx.beans.property.ReadOnlyIntegerWrapper;
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
import javafx.scene.control.TableRow;
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
    @FXML TableColumn<Booking, String> idColumn;
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
        Marshaller marshaller = null;
        try {
            if (jaxbContext != null) {
                unmarshaller = jaxbContext.createUnmarshaller();
                marshaller = jaxbContext.createMarshaller();
                marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            }
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        try {
            if (unmarshaller != null) {
                bookings = (BookingList) unmarshaller.unmarshal(file);
                if(bookings!=null){
                    for (Booking booking : bookings.getAllBookings()){
                        booking.setId(bookings.getAllBookings().indexOf(booking));
                    }
                    if (marshaller != null) {
                        marshaller.marshal(bookings, file);
                    }
                }
            }
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        ObservableList<Booking> oL = FXCollections.observableArrayList();
        if(bookings!=null) {
            oL.addAll(bookings.getAllBookings());
            table.getItems().addAll(oL);
        }
        idColumn.setCellValueFactory(features-> new ReadOnlyStringWrapper(String.valueOf(features.getValue().getId())));
        guestColumn.setCellValueFactory(features -> new ReadOnlyStringWrapper(features.getValue().getGuest().getName()));
        roomColumn.setCellValueFactory(features -> new ReadOnlyStringWrapper(String.valueOf(features.getValue().getRoom().getNumber())));
        arrivalColumn.setCellValueFactory(features -> new ReadOnlyStringWrapper(String.valueOf(features.getValue().getDateInterval().getStartDate())));
        departureColumn.setCellValueFactory(features -> new ReadOnlyStringWrapper(String.valueOf(features.getValue().getDateInterval().getEndDate())));
        guestsColumn.setCellValueFactory(features -> new ReadOnlyStringWrapper(String.valueOf(features.getValue().getGuests())));
        extraColumn.setCellValueFactory(features -> new ReadOnlyStringWrapper(features.getValue().getExtraInfo()));
        stateColumn.setCellValueFactory(features-> new ReadOnlyStringWrapper(features.getValue().getState()));

        table.setRowFactory(tv->{
            TableRow<Booking> tableRow = new TableRow<>();
            tableRow.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! tableRow.isEmpty()) ) {
                    Booking rowData = tableRow.getItem();
                    System.out.println("Double click on: "+rowData.getId());
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("changeBooking.fxml"));
                        CrudBookingController crudBookingController = new CrudBookingController();
                        crudBookingController.initPlaceholders(bookings.getBooking(rowData.getId()));
                        root = FXMLLoader.load(OverLookApplication.class.getResource("changeBooking.fxml"));
                        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        scene = new Scene(root);
                        stage.setScene(scene);
                        stage.show();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            });
            return tableRow ;
        });


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
        Marshaller marshaller = null;
        try {
            if (jaxbContext != null) {
                unmarshaller = jaxbContext.createUnmarshaller();
                marshaller = jaxbContext.createMarshaller();
                marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            }
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        try {
            if (unmarshaller != null) {
                bookings = (BookingList) unmarshaller.unmarshal(file);
                if(bookings!=null){
                    for (Booking booking : bookings.getAllBookings()){
                        booking.setId(bookings.getAllBookings().indexOf(booking));
                    }
                    if (marshaller != null) {
                        marshaller.marshal(bookings, file);
                    }
                }
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