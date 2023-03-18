package com.example.progcw;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;


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
        navigateSaveData(actionEvent);
    }

    public void navigateSaveData(ActionEvent actionEvent) throws Exception  {
        Stage newStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("saveData.fxml"));
        newStage.setScene(new Scene(root,600,400 ));
        newStage.show();

        Stage previousStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        previousStage.close();
    }

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
