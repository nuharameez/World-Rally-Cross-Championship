/*package com.example.progcw;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AddTest {

    @Test
    void addSave() {
        Add add = new Add();

        TextField name1 = new TextField("Anna");
        TextField age1 = new TextField("20");
        TextField team1 = new TextField("Legends");
        TextField car1 = new TextField("Honda");
        TextField points1 = new TextField("50");
        Label eMessage = new Label("");
        Label sMessage = new Label("");

        add.addName = name1;
        add.addAge = age1;
        add.addTeam = team1;
        add.addCar = car1;
        add.addPoints = points1;
        add.errorMessage = eMessage;
        add.successMessage = sMessage;

        add.addDriverDetails();

        assertEquals("Driver details added successfully!", sMessage.getText());
    }
}*/