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

public class Update {
    public Update(){
    }
    ObservableList<String> updateOption = FXCollections.observableArrayList("Name", "Age", "Team", "Car", "Current Points");

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


    @FXML
    private void initialize(){
        updateElem.setItems(updateOption);
    }
    public void updateSave() throws IOException{
        updateDriverDetails();
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

    public void updateDriverDetails() throws IOException {
        String updateE = (String) updateElem.getValue();

        if (DriverList.allDrivers.isEmpty()) {
            message.setText("");
            messageError.setText("There are no driver details in the system.");
        } else {

            String name = updateName.getText().toUpperCase();
            if (updateE.equals("Name")) {
                String newName = updateRecord.getText().toUpperCase();
                for (int i = 0; i < DriverList.allDrivers.size(); i++) {
                    if (DriverList.allDrivers.get(i).contains(name)) {
                        DriverList.allDrivers.get(i).set(0, newName);
                        System.out.println(DriverList.allDrivers);
                        messageError.setText("");
                        message.setText(name + "-driver's details updated. Click back to go to main menu.");
                    } else {
                        message.setText("");
                        messageError.setText("This driver does not exist in the system. Click back to go to main menu.");
                    }
                }
            } else if (updateE.equals("Team")) {
                String newTeam = updateRecord.getText().toUpperCase();
                for (int i = 0; i < DriverList.allDrivers.size(); i++) {
                    if (DriverList.allDrivers.get(i).contains(name)) {
                        DriverList.allDrivers.get(i).set(2, newTeam);
                        System.out.println(DriverList.allDrivers);
                        message.setText(name + "-driver's details updated. Click back to go to main menu.");
                        messageError.setText("");
                    } else {
                        message.setText("");
                        messageError.setText("This driver does not exist in the system. Click back to go to main menu.");
                    }
                }
            } else if (updateE.equals("Car")) {
                String newCar = updateRecord.getText().toUpperCase();
                for (int i = 0; i < DriverList.allDrivers.size(); i++) {
                    if (DriverList.allDrivers.get(i).contains(name)) {
                        DriverList.allDrivers.get(i).set(3, newCar);
                        System.out.println(DriverList.allDrivers);
                        messageError.setText("");
                        message.setText(name + "-driver's details updated. Click back to go to main menu.");
                    } else {
                        message.setText("");
                        messageError.setText("This name does not exist in the system. Click back to go to main menu.");
                    }
                }
            } else if (updateE.equals("Age")) {
                int newAge = Integer.parseInt(updateRecord.getText());
                for (int i = 0; i < DriverList.allDrivers.size(); i++) {
                    if (DriverList.allDrivers.get(i).contains(name)) {
                        DriverList.allDrivers.get(i).set(1, newAge);
                        System.out.println(DriverList.allDrivers);
                        messageError.setText("");
                        message.setText(name + "-driver's details updated. Click back to go to main menu.");
                    } else {
                        message.setText("");
                        messageError.setText("This driver does not exist in the system. Click back to go to main menu.");
                    }
                }
            } else if (updateE.equals("Current Points")) {
                int newPoints = Integer.parseInt(updateRecord.getText());
                for (int i = 0; i < DriverList.allDrivers.size(); i++) {
                    if (DriverList.allDrivers.get(i).contains(name)) {
                        DriverList.allDrivers.get(i).set(4, newPoints);
                        System.out.println(DriverList.allDrivers);
                        messageError.setText("");
                        message.setText(name + "-driver's details updated. Click back to go to main menu.");
                    } else {
                        message.setText("");
                        messageError.setText("This driver does not exist in the system. Click back to go to main menu.");
                    }
                }
            }



        }
    }
}
