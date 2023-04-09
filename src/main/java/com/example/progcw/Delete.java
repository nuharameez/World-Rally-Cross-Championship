package com.example.progcw;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class Delete {
    public Delete() {
    }

    @FXML
    private TextField deleteName;
    @FXML
    private Label message;
    @FXML
    private Label messageError;

    public void deleteSave() throws IOException {
        deleteDriverDetails();
    }


    //navigates back to menu when back button clicked
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

    private void deleteDriverDetails() throws IOException {
        //takes driver details to be deleted to a seperate list
        ArrayList<ArrayList> toDelete = new ArrayList<ArrayList>();
        String delName = deleteName.getText().toUpperCase();
        //checks if name entered exists in the system
        for(int i = 0; i<DriverList.allDrivers.size();i++){
            if (DriverList.allDrivers.isEmpty() || !(DriverList.allDrivers.get(i).contains(delName))) {
                message.setText("");
                messageError.setText("There are no drivers loaded in to the system.");
            }
            else{
                toDelete.add(DriverList.allDrivers.get(i));
                DriverList.allDrivers.remove(DriverList.allDrivers.get(i));
            }
        }
        //if existing in the system remove.
        if(!toDelete.isEmpty()){
                if (toDelete.get(0).contains(delName)) {
                    toDelete.clear();
                    System.out.println(DriverList.allDrivers);
                    messageError.setText("");
                    message.setText(delName + "- driver's records has been deleted.");
                }


            }
        else{
            message.setText("");
            messageError.setText(delName + "- driver's records does not exist or has been removed from the system.");
        }






    }
}

