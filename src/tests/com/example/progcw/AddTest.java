/*package com.example.progcw;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.junit.BeforeClass;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddTest {


    @Test
    public void testAddDriverDetails() {
        Add add = new Add();
        TextField addName = new TextField("Test Driver");
        TextField addAge = new TextField("25");
        TextField addTeam = new TextField("Test Team");
        TextField addCar = new TextField("Test Car");
        TextField addPoints = new TextField("10");
        Label errorMessage = new Label();
        Label successMessage = new Label();
        add.addName = addName;
        add.addAge = addAge;
        add.addTeam = addTeam;
        add.addCar = addCar;
        add.addPoints = addPoints;
        add.errorMessage = errorMessage;
        add.successMessage = successMessage;

        add.addDriverDetails();
        assertEquals(1, DriverList.allDrivers.size());
        assertEquals("Test Driver", DriverList.allDrivers.get(0).get(0));
        assertEquals(25, DriverList.allDrivers.get(0).get(1));
        assertEquals("Test Team", DriverList.allDrivers.get(0).get(2));
        assertEquals("Test Car", DriverList.allDrivers.get(0).get(3));
        assertEquals(10, DriverList.allDrivers.get(0).get(4));
    }
    @BeforeClass
    public static void setUpClass() {
        Toolkit.getDefaultToolkit();
    }


}*/
