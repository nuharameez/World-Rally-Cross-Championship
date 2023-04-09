package com.example.progcw;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class Update {
    public Update(){
    }
    ObservableList<String> updateOption = FXCollections.observableArrayList( "Age", "Team", "Car", "Current Points");

    @FXML
    private TextField updateName;
    @FXML
    private TextField updateRecord;
    @FXML
    private Label message;
    @FXML
    private Label messageError;
    @FXML
    private ChoiceBox updateElem;

    //initializing items to choice box
    @FXML
    private void initialize(){
        updateElem.setItems(updateOption);
    }
    public void updateSave() throws IOException{
        updateDriverDetails();
    }

    //to navigate back to menu when back button clicked
    @FXML
    private void onGoBackButtonClick(ActionEvent actionEvent) throws Exception {
        navigateGoBack(actionEvent);
    }

    private void navigateGoBack(ActionEvent actionEvent) throws Exception {
        Stage newStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("menu.fxml"));
        newStage.setScene(new Scene(root, 600, 400));
        newStage.show();

        Stage previousStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        previousStage.close();
    }

    //method to update driver details
    private void updateDriverDetails() throws IOException {
        //getting the driver to be updated inner list to a seperate list
        ArrayList<ArrayList> toUpdate = new ArrayList();
        String updateE = (String) updateElem.getValue();
        String name = updateName.getText().toUpperCase();
        //checking if name entered is in the sytsem
        for (int j = 0; j<DriverList.allDrivers.size();j++) {
            if (DriverList.allDrivers.isEmpty() || !(DriverList.allDrivers.get(j).contains(name))) {
                message.setText("");
                messageError.setText("Driver does not exist in the system.");

            } else {

                toUpdate.add(DriverList.allDrivers.get(j));
                DriverList.allDrivers.remove(DriverList.allDrivers.get(j));
            }
        }
        System.out.println("to update" + toUpdate);


        if(!toUpdate.isEmpty()) {
            //updating according to user input

            if (updateE.equals("Team")) {
                String newTeam = updateRecord.getText().toUpperCase();
                toUpdate.get(0).set(2, newTeam);
                DriverList.allDrivers.add(toUpdate.get(0));
                messageError.setText("");
                message.setText("Driver's team updated successfully.");


            } else if (updateE.equals("Car")) {
                String newCar = updateRecord.getText().toUpperCase();
                toUpdate.get(0).set(3, newCar);
                DriverList.allDrivers.add(toUpdate.get(0));
                messageError.setText("");
                message.setText("Driver's car updated successfully.");


            } else if (updateE.equals("Age")) {
                try {
                    int newAge = Integer.parseInt(updateRecord.getText());
                    toUpdate.get(0).set(1, newAge);
                    DriverList.allDrivers.add(toUpdate.get(0));
                    messageError.setText("");
                    message.setText("Driver's age updated successfully.");
                } catch (NumberFormatException e) {
                    message.setText("");
                    messageError.setText("Invalid input!");
                    DriverList.allDrivers.add(toUpdate.get(0));
                }


            } else if (updateE.equals("Current Points")) {
                try {
                    int newPoints = Integer.parseInt(updateRecord.getText());
                    toUpdate.get(0).set(4, newPoints);
                    DriverList.allDrivers.add(toUpdate.get(0));
                    messageError.setText("");
                    message.setText("Driver's current points updated successfully.");
                } catch (NumberFormatException e) {
                    message.setText("");
                    messageError.setText("Invalid input!");
                    DriverList.allDrivers.add(toUpdate.get(0));
                }
            }
        }
        else{
            message.setText("");
            messageError.setText("Driver does not exist in the system.");
        }
        System.out.println(DriverList.allDrivers);
    }

}


