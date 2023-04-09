package com.example.progcw;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Load {
    public Load() throws IOException {
        String filename = "SavedDriverDetails.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while((line = br.readLine())!=null){
                String[] driver = line.split(",\\s*");
                //splitting by white space
                ArrayList<String> driverRecord = new ArrayList<>(Arrays.asList(driver));
                //removing the string "[" "]" from the lines when reading.
                driverRecord.set(0, driverRecord.get(0).replaceAll("^\\[|\\]$",""));
                driverRecord.set(4, driverRecord.get(4).replaceAll("^\\[|\\]$",""));
                driverRecord.set(1, String.valueOf(Integer.parseInt(driverRecord.get(1))));
                driverRecord.set(4, String.valueOf(Integer.parseInt(driverRecord.get(4))));
                DriverList.allDrivers.add(driverRecord);


            }
            System.out.println(DriverList.allDrivers);

        }
        catch(FileNotFoundException e){
            System.out.println("File not found");
        }
    }


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
}
