package com.example.hoteloverlookjavafx.Controllers;

import com.example.hoteloverlookjavafx.Models.BookingList;
import com.example.hoteloverlookjavafx.Models.GuestList;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.File;

public class CrudBookingController {
    @FXML TextArea extraInfoField;
    @FXML TextField guestsField;
    @FXML DatePicker arrivalField;
    @FXML DatePicker departureField;
    @FXML TextArea extrasField;
    @FXML TextField priceField;
    @FXML ChoiceBox stateBox;
    @FXML ChoiceBox stateRoom;
    @FXML CheckBox extrasCheck;
    private BookingList bookings;

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

    public void switchBack(ActionEvent event) {
    }

    public void addGuest(ActionEvent actionEvent) {
        
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
}
