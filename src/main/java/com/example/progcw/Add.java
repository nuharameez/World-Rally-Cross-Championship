package com.example.progcw;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Add {

    public Add() {

    }


    @FXML
    private TextField addName;
    @FXML
    private TextField addAge;
    @FXML
    private TextField addTeam;
    @FXML
    private TextField addCar;
    @FXML
    private TextField addPoints;
    @FXML
    private Label errorMessage;
    @FXML
    private Label successMessage;


    public void addSave() {
        addDriverDetails();


    }

    @FXML
    protected void onGoBackButtonClick(ActionEvent actionEvent) throws Exception {
        navigateGoBack(actionEvent);
    }

    public void navigateGoBack(ActionEvent actionEvent) throws Exception {
        Stage newStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("menu.fxml"));
        newStage.setScene(new Scene(root, 600, 400));
        newStage.show();

        Stage previousStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        previousStage.close();
    }

    @FXML
    protected void onClearClick(ActionEvent actionEvent) throws Exception {
        navigateClear(actionEvent);
    }

    public void navigateClear(ActionEvent actionEvent) throws Exception {
        Stage newStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("addDetails.fxml"));
        newStage.setScene(new Scene(root, 600, 400));
        newStage.show();

        Stage previousStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        previousStage.close();
    }


    public void addDriverDetails() {
        String filename = "SavedDriverDetails.txt";
        ArrayList<ArrayList<String>> driverDetails = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] driver = line.split(",\\s*");
                ArrayList<String> driverRecord = new ArrayList<>(Arrays.asList(driver));
                driverRecord.set(0, driverRecord.get(0).replaceAll("^\\[|\\]$", ""));
                driverRecord.set(4, driverRecord.get(4).replaceAll("^\\[|\\]$", ""));
                driverRecord.set(1, String.valueOf(Integer.parseInt(driverRecord.get(1))));
                driverRecord.set(4, String.valueOf(Integer.parseInt(driverRecord.get(4))));
                driverDetails.add(driverRecord);
            }


        } catch (IOException e) {
            System.out.println("No file");
        }
        while (true) {
            ArrayList drivers = new ArrayList();

            String name = addName.getText().toUpperCase();
            drivers.add(name);


            try {
                int age = Integer.parseInt(addAge.getText());
                drivers.add(age);


            } catch (NumberFormatException e) {
                successMessage.setText("");
                errorMessage.setText("Invalid Input! Check age and points.");
                drivers.clear();
                break;
            }


            String team = addTeam.getText().toUpperCase();
            drivers.add(team);
            String car = addCar.getText().toUpperCase();
            drivers.add(car);

            try {
                int points = Integer.parseInt(addPoints.getText());
                drivers.add(points);

            } catch (NumberFormatException e) {
                successMessage.setText("");
                errorMessage.setText("Invalid Input! Check age and points.");
                drivers.clear();
                break;
            }


            for (int i = 0; i < DriverList.allDrivers.size(); i++) {
                if (DriverList.allDrivers.get(i).contains(name)) {
                    //System.out.println("Name exists");
                    successMessage.setText("");
                    errorMessage.setText("This driver already exists in the system");
                    drivers.clear();
                }
            }


            for (int j = 0; j < driverDetails.size(); j++) {
                if (driverDetails.get(j).get(0).equals(name)) {
                    //System.out.println("Name exists");
                    successMessage.setText("");
                    errorMessage.setText("This driver already exists in the system");
                    drivers.clear();

                }
            }


            if (!drivers.isEmpty()) {
                errorMessage.setText("");
                DriverList.allDrivers.add(drivers);
                successMessage.setText("Driver details added successfully!");
            }

            /*System.out.println(AddList.drivers);*/
            System.out.println(DriverList.allDrivers);

        /*Stage newStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("menu.fxml"));
        newStage.setScene(new Scene(root,600,400 ));
        newStage.show();

        Stage previousStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        previousStage.close();
    }*/
            break;
        }
    }
}





