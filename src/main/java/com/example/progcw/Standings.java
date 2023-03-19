package com.example.progcw;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;


public class Standings {
    public Standings(){
    }
    @FXML
    private Label Heading;
    @FXML
    private TextArea record;



    public void showStanding() throws IOException {
        viewStandingTable();
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

    public static void bubblesortingList(ArrayList<ArrayList> sortList){
        int n = sortList.size();
        for (int i = 0; i<n; i++){
            for ( int j = 0; j< n - i - 1; j++){
                int current = (int) sortList.get(j).get(4);
                int next= (int) sortList.get(j+1).get(4);
                if(current>next){
                    ArrayList temp = sortList.get(j);
                    sortList.set(j, sortList.get(j+1));
                    sortList.set(j+1, temp);
                }
                }
            }
        }
    public void viewStandingTable(){
        if (DriverList.allDrivers.size() != 0) {
            StringBuilder toShow = new StringBuilder();
            bubblesortingList(DriverList.allDrivers);
            System.out.println(DriverList.allDrivers);


            for (int i = 0; i < DriverList.allDrivers.size(); i++) {
                ArrayList innerList = DriverList.allDrivers.get(i);
                for (int j = 0; j < innerList.size(); j++) {
                    toShow.append(String.format("%-20s", innerList.get(j)));
                    toShow.append("          ");
                }
                toShow.append("\n");


            }
            record.setText(toShow.toString());
        }
        else{
            record.setText("There are no drivers in the system.");
        }

    }











}
