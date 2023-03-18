package com.example.progcw;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Add {

    public Add(){

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


    public void addSave(ActionEvent actionEvent) throws IOException{
        addDriverDetails(actionEvent);



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



    public void addDriverDetails(ActionEvent actionEvent) throws IOException{

        String name = addName.getText().toUpperCase();
        int age = Integer.parseInt(addAge.getText());
        String team = addTeam.getText().toUpperCase();
        String car = addCar.getText().toUpperCase();
        int points = Integer.parseInt(addPoints.getText());


        ArrayList drivers = new ArrayList();
        drivers.add(name);
        drivers.add(age);
        drivers.add(team);
        drivers.add(car);
        drivers.add(points);


        DriverList.allDrivers.add(drivers);

        /*System.out.println(AddList.drivers);*/
        System.out.println( DriverList.allDrivers);

        Stage newStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("menu.fxml"));
        newStage.setScene(new Scene(root,600,400 ));
        newStage.show();

        Stage previousStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        previousStage.close();
    }


}


