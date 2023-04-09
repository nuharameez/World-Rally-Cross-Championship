package com.example.progcw;

//importing all necessary packages
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Add {

    public Add() {

    }
    //initializing the textfields user will input data in to
    //initializing labels to show user messages


    @FXML
    protected TextField addName;
    @FXML
    protected TextField addAge;
    @FXML
    protected TextField addTeam;
    @FXML
    protected TextField addCar;
    @FXML
    protected TextField addPoints;
    @FXML
    protected Label errorMessage;
    @FXML
    protected Label successMessage;


    public void addSave() {
        addDriverDetails();


    }


    //this window will have a back button, when pressed it will be navigated to the main menu
    //IOException thrown in case menu.fxml not found

    @FXML
    private void onGoBackButtonClick(ActionEvent actionEvent) throws IOException {
        navigateGoBack(actionEvent);
    }

    private void navigateGoBack(ActionEvent actionEvent) throws IOException {
        Stage newStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("menu.fxml"));
        newStage.setScene(new Scene(root, 600, 400));
        newStage.show();

        Stage previousStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        previousStage.close();
    }

    //when clear button clicked a new add details window will be opened

    @FXML
    private void onClearClick(ActionEvent actionEvent) throws Exception {
        navigateClear(actionEvent);
    }

    private void navigateClear(ActionEvent actionEvent) throws Exception {
        Stage newStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("addDetails.fxml"));
        newStage.setScene(new Scene(root, 600, 400));
        newStage.show();

        Stage previousStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        previousStage.close();
    }


    //method to add driver details
    private void addDriverDetails() {
        //reading driver details stored in text file to avoid duplicate drivers
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

            //checking if age entered ins an integer
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

            //checking if points entered in integer

            try {
                int points = Integer.parseInt(addPoints.getText());
                drivers.add(points);

            } catch (NumberFormatException e) {
                successMessage.setText("");
                errorMessage.setText("Invalid Input! Check age and points.");
                drivers.clear();
                break;
            }

            //checking main list if user input name already exists
            for (int i = 0; i < DriverList.allDrivers.size(); i++) {
                if (DriverList.allDrivers.get(i).contains(name)) {
                    successMessage.setText("");
                    errorMessage.setText("This driver already exists in the system");
                    drivers.clear();
                }
            }

            //checking list from text file if user input name already exists
            for (int j = 0; j < driverDetails.size(); j++) {
                if (driverDetails.get(j).get(0).equals(name)) {
                    //System.out.println("Name exists");
                    successMessage.setText("");
                    errorMessage.setText("This driver already exists in the system");
                    drivers.clear();

                }
            }

            //adding driver details to main list if driver name does not get duplicated.
            if (!drivers.isEmpty()) {
                errorMessage.setText("");
                DriverList.allDrivers.add(drivers);
                successMessage.setText("Driver details added successfully!");
            }

            /*System.out.println(AddList.drivers);*/
            System.out.println(DriverList.allDrivers);


            break;
        }
    }
}





