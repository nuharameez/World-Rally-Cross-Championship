package com.example.progcw;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static com.example.progcw.DriverList.allDrivers;


public class Menu {
    public Menu(){

    }


    @FXML
    protected void onAddButtonClick(ActionEvent actionEvent) throws Exception {
        navigateAdd(actionEvent);
    }

    public void navigateAdd(ActionEvent actionEvent) throws Exception  {
        Stage newStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("addDetails.fxml"));
        newStage.setScene(new Scene(root,600,400 ));
        newStage.show();

        Stage previousStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        previousStage.close();
    }

    @FXML
    protected void onDeleteButtonClick(ActionEvent actionEvent) throws Exception {
        navigateDelete(actionEvent);
    }

    public void navigateDelete(ActionEvent actionEvent) throws Exception  {
        Stage newStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("deleteDetails.fxml"));
        newStage.setScene(new Scene(root,600,400 ));
        newStage.show();

        Stage previousStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        previousStage.close();
    }


    @FXML
    protected void onUpdateButtonClick(ActionEvent actionEvent) throws Exception {
        navigateUpdate(actionEvent);
    }

    public void navigateUpdate(ActionEvent actionEvent) throws Exception  {
        Stage newStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("updateDetails.fxml"));
        newStage.setScene(new Scene(root,600,400 ));
        newStage.show();

        Stage previousStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        previousStage.close();
    }

    @FXML
    protected void onGoBackButtonClick(ActionEvent actionEvent) throws Exception {
        navigateGoBack(actionEvent);
    }

    public void navigateGoBack(ActionEvent actionEvent) throws Exception  {
        Stage newStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("menu.fxml"));
        newStage.setScene(new Scene(root,600,400 ));
        newStage.show();

        Stage previousStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        previousStage.close();
    }

    @FXML
    protected void onStandingTableButtonClick(ActionEvent actionEvent) throws Exception {
        navigateStandingTable(actionEvent);

    }

    public void navigateStandingTable(ActionEvent actionEvent) throws Exception  {
        Stage newStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("standingTable.fxml"));
        newStage.setScene(new Scene(root,600,400 ));
        newStage.show();


        Stage previousStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        previousStage.close();
    }

    @FXML
    protected void onRandomRaceButtonClick(ActionEvent actionEvent) throws Exception {
        navigateRandomRace(actionEvent);
    }

    public void navigateRandomRace(ActionEvent actionEvent) throws Exception  {
        Stage newStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("randomRace.fxml"));
        newStage.setScene(new Scene(root,600,400 ));
        newStage.show();

        Stage previousStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        previousStage.close();
    }

    @FXML
    protected void onRaceTableButtonClick(ActionEvent actionEvent) throws Exception {
        navigateRaceTable(actionEvent);
    }

    public void navigateRaceTable(ActionEvent actionEvent) throws Exception  {
        Stage newStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("raceTable.fxml"));
        newStage.setScene(new Scene(root,600,400 ));
        newStage.show();

        Stage previousStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        previousStage.close();
    }








    @FXML
    protected void onSaveButtonClick(ActionEvent actionEvent) throws Exception {
        //navigateSaveData(actionEvent);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Did you save or load data in to the system?");
        alert.setContentText("If yes selected data will be overwritten");

        ButtonType buttonTypeYes = new ButtonType("Yes");
        ButtonType buttonTypeNo = new ButtonType("No");
        alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);
        Optional<ButtonType> result = alert.showAndWait();
        List<List<String>> data = new ArrayList<>();
        if(result.get() == buttonTypeYes) {

            for (int i = 0; i < DriverList.allDrivers.size(); i++) {
                data.add(Arrays.asList(String.valueOf(DriverList.allDrivers.get(i))));
                try {
                    FileWriter writer = new FileWriter("SavedDriverDetails.txt"); //ask user if they called stf or rff in the program, if yes get rid of the true.
                    for (List<String> line : data) {
                        writer.write(String.join(",", line) + "\n");
                    }
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println("Current working directory: " + System.getProperty("user.dir"));

            }
        }
        else{
            for (int i = 0; i < DriverList.allDrivers.size(); i++) {
                data.add(Arrays.asList(String.valueOf(DriverList.allDrivers.get(i))));
                try {
                    FileWriter writer = new FileWriter("SavedDriverDetails", true); //ask user if they called stf or rff in the program, if yes get rid of the true.
                    for (List<String> line : data) {
                        writer.write(String.join(",", line) + "\n");
                    }
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println("Current working directory: " + System.getProperty("user.dir"));

            }
        }
    }

    /*public void navigateSaveData(ActionEvent actionEvent) throws Exception  {
        Stage newStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("saveData.fxml"));
        newStage.setScene(new Scene(root,600,400 ));
        newStage.show();

        Stage previousStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        previousStage.close();
    }*/

    @FXML
    protected void onLoadButtonClick(ActionEvent actionEvent) throws Exception {
        navigateLoadData(actionEvent);
    }

    public void navigateLoadData(ActionEvent actionEvent) throws Exception  {
        Stage newStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("loadData.fxml"));
        newStage.setScene(new Scene(root,600,400 ));
        newStage.show();

        Stage previousStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        previousStage.close();
    }
}
