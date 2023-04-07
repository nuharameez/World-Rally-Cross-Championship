package com.example.progcw;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class RaceTable {

    public RaceTable() throws IOException {

    }

    @FXML
    private TableColumn<RaceDetails, String> date;
    @FXML
    private TableColumn<RaceDetails, String> raceLocation;
    @FXML
    private TableColumn<RaceDetails, String> drivers;
    @FXML
    private TableView<RaceDetails> raceTable;

    public void raceTableShow(){
        displayRaceTable();
    }

    public class RaceDetails {
        private String date;
        private String location;
        private String drivers;


        public RaceDetails(String date, String location, String drivers) {
            this.date = date;
            this.location = location;
            this.drivers = drivers;
        }

        public String getDate() {
            return date;
        }

        public String getLocation() {
            return location;
        }

        public String getDrivers() {
            return drivers;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public void setDrivers(String drivers) {
            this.drivers = drivers;
        }
    }

    public void displayRaceTable(){
        String filePath = "raceDrivers.txt";
        List<List<String>> allRaces = new ArrayList<>();
        List<String> eachRace = new ArrayList<>();

        try {
            File file = new File(filePath);
            Scanner readFile = new Scanner(file);
            while (readFile.hasNextLine()) {
                String line = readFile.nextLine();
                String[] details = line.split(",");

                if (details.length>0 && details[details.length - 1].matches("\\d{2}-\\d{2}-\\d{4}")) {
                    List<String> formattedEachRace = new ArrayList<>();
                    for (int i = 0; i < eachRace.size(); i += 2) {
                        if (i + 1 < eachRace.size()) {
                            formattedEachRace.add(eachRace.get(i) + "-" + eachRace.get(i + 1));
                        }
                        else{
                            formattedEachRace.add(eachRace.get(i));
                        }
                    }
                    if(!formattedEachRace.isEmpty()){
                        formattedEachRace.add(details[details.length-1]);
                        allRaces.add(formattedEachRace);
                    }

                    eachRace = new ArrayList<>();
                } else {
                    for (String element : details) {
                        eachRace.add(element);
                    }
                }
            }

            List<String> formattedEachRace = new ArrayList<>();
            for (int i = 0; i < eachRace.size(); i += 2) {
                if (i + 1 < eachRace.size()) {
                    formattedEachRace.add(eachRace.get(i) + "-" + eachRace.get(i + 1));
                }
                else{
                    formattedEachRace.add(eachRace.get(i));
                }
            }
            if(!formattedEachRace.isEmpty()){
                allRaces.add(formattedEachRace);
            }


            readFile.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");

        }



        List<List<String>> modifiedList = new ArrayList<>();
        for(List<String> innerList : allRaces){
            String location = innerList.get(innerList.size() - 2);
            String date = innerList.get(innerList.size() - 1);
            List<String> modifiedInnerList = new ArrayList<>();
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i< innerList.size() - 2; i++){
                sb.append(innerList.get(i)).append(" ");
            }
            modifiedInnerList.add(sb.toString().trim());
            modifiedInnerList.add(String.valueOf(location));

            try{
                SimpleDateFormat originalFormat = new SimpleDateFormat("dd-MM-yyyy");
                Date convertDate = originalFormat.parse(date);
                modifiedInnerList.add(originalFormat.format(convertDate));
            }
            catch (ParseException e){
                System.out.println("Date could not be converted to date format");
            }
            modifiedList.add(modifiedInnerList);
        }

        /*for(int k = 1; k< modifiedList.size();k++){
            List<String> current = modifiedList.get(k);
            String currentDate = current.get(current.size()-1);
            int j = k - 1;
            while(j >=0 && currentDate.compareTo(modifiedList.get(k).get(modifiedList.get(j).size()-1))<0){
                modifiedList.set(j+1, modifiedList.get(j));
                j--;

        }
        System.out.println("Sorted" + modifiedList);*/

        boolean sorted = false;
        while(!sorted){
            sorted = true;
            //loop to go trhough all elements in the nested list
            for(int i = 0; i< modifiedList.size()-1;i++){
                //loop to iterate through all the elements in the inner list
                //for(int j =0; j< modifiedList.get(i).size() -1; j++){
                //checks for third element to consider it date
                //if (j + 2 < modifiedList.get(i).size()  && i + 1 < modifiedList.size() ) {
                //getting current date and next date
                String currentDate = (String) modifiedList.get(i).get(2);
                String nextDate = (String) modifiedList.get(i+1).get(2);
                //sepearting the day, month and year to compare all three.
                String[] currentDateElements = currentDate.split("-");
                String[] nextDateElements = nextDate.split("-");
                int currentDay = Integer.parseInt(currentDateElements[0]);
                int nextDay = Integer.parseInt(nextDateElements[0]);
                int currentMonth = Integer.parseInt(currentDateElements[1]);
                int nextMonth = Integer.parseInt(nextDateElements[1]);
                int currentYear = Integer.parseInt(currentDateElements[2]);
                int nextYear = Integer.parseInt(nextDateElements[2]);
                if(currentYear>nextYear || (currentYear == nextYear && currentMonth > nextMonth) || (currentYear == nextYear && currentMonth == nextMonth && currentDay > nextDay)){
                    Collections.swap(modifiedList, i, i+1);
                    sorted = false;
                }
            }


        }
        System.out.println(modifiedList);
        if(!modifiedList.isEmpty()){
            ObservableList<RaceDetails> data = FXCollections.observableArrayList();
            for(List<String> details : modifiedList){
                String date =  details.get(2);
                String location =  details.get(1);
                String driverElem = details.get(0);
                String[] elem = driverElem.split("\\s+");
                if(elem.length >= 3) {
                    elem[0] = "First: " + elem[0];
                    elem[1] = "Second: " + elem[1];
                    elem[2] = "Third: " + elem[2];
                } else if (elem.length == 2) {
                    elem[0] = "First: " + elem[0];
                    elem[1] = "Second: " + elem[1];
                } else if (elem.length == 1) {
                    elem[0] = "First: " + elem[0];
                }
                String newDriverElem = String.join("\n", elem);
                details.set(0, newDriverElem);
                String drivers =  details.get(0);
                RaceDetails raceDetails = new RaceDetails(date, location, drivers);
                data.add(raceDetails);
            }
            date.setCellValueFactory(new PropertyValueFactory<RaceDetails, String>("date"));
            raceLocation.setCellValueFactory(new PropertyValueFactory<RaceDetails, String>("location"));
            drivers.setCellValueFactory(new PropertyValueFactory<RaceDetails, String>("drivers"));
            raceTable.setItems(data);
        }
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
}





