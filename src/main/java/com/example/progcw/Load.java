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
        ArrayList<ArrayList<String>> driverDetails = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            /*while ((line = br.readLine()) != null) {
                String[] record = line.replaceAll("[\\[\\]]","").split(",\\s*");
                String name = record[0];
                int age = Integer.parseInt(record[1].replaceAll("[^0-9]",""));
                String team = record[2];
                String car = record[3];
                int points = Integer.parseInt(record[4].replaceAll("[^0-9]",""));
                DriverDetails driverDetail = new DriverDetails(name, age, team, car, points);
                driverDetails.add(driverDetail);

            }
            DriverList.allDrivers.add(driverDetails);
            System.out.println(DriverList.allDrivers);*/
            while((line = br.readLine())!=null){
                String[] driver = line.split(",\\s*");
                ArrayList<String> driverRecord = new ArrayList<>(Arrays.asList(driver));
                driverRecord.set(0, driverRecord.get(0).replaceAll("^\\[|\\]$",""));
                driverRecord.set(4, driverRecord.get(4).replaceAll("^\\[|\\]$",""));
                driverRecord.set(1, String.valueOf(Integer.parseInt(driverRecord.get(1))));
                driverRecord.set(4, String.valueOf(Integer.parseInt(driverRecord.get(4))));
                DriverList.allDrivers.add(driverRecord);


            }
            System.out.println(DriverList.allDrivers);

        }
    }

    /*public class DriverDetails {
        private String name;
        private int age;
        private String team;
        private String car;
        private int points;

        public DriverDetails(String name, int age, String team, String car, int points) {
            this.name = name;
            this.age = age;
            this.team = team;
            this.car = car;
            this.points = points;
        }
        public String toString(){
            return name +","+ age+"," + team+"," + car+"," + points;
        }

    }*/
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
}
