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

    public void deleteDriverDetails() throws IOException {
        String delName = deleteName.getText().toUpperCase();

        if (DriverList.allDrivers.isEmpty()){
            message.setText("");
            messageError.setText("There are no drivers entered/loaded in to the system. Click on back to go to main menu");


        }
        else {
            for (int i = 0; i < DriverList.allDrivers.size(); i++) {
                if (DriverList.allDrivers.get(i).contains(delName)) {
                    DriverList.allDrivers.remove(i);
                    System.out.println(DriverList.allDrivers);
                    messageError.setText("");
                    message.setText(delName + "- driver's records has been deleted. Click on back to go to main menu");
                }
                else{
                    message.setText("");
                    messageError.setText(delName + "- driver's records do not exist in the system. Click back to go to main menu");
                }

            }


        }



    }
}

