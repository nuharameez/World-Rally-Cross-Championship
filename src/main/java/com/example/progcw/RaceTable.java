package com.example.progcw;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


public class RaceTable {

    public RaceTable() {

    }

    @FXML
    private TableColumn<RaceDetails, String> date;
    @FXML
    private TableColumn<RaceDetails, String> raceLocation;
    @FXML
    private TableColumn<RaceDetails, String> drivers;
    @FXML
    private TableView<RaceDetails> raceTable;
    @FXML
    private Label message;

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

    private void displayRaceTable(){
        String filePath = "detailsOfRace.txt";
        List<List<String>> allRaces = new ArrayList<>();
        List<String> eachRace = new ArrayList<>();

        //reading the details of the races held from the text file
        try {
            File file = new File(filePath);
            Scanner readFile = new Scanner(file);
            while (readFile.hasNextLine()) {
                String line = readFile.nextLine();
                String[] details = line.split(",");


                //grouping all the elements before the date as details of one race, and the element right after to teh next date as another detail of a race
                //details.length - 1 = date
                if (details.length>0 && details[details.length - 1].matches("\\d{2}-\\d{2}-\\d{4}")) {
                    List<String> formattedEachRace = new ArrayList<>();
                    for (int i = 0; i < eachRace.size(); i += 2) {
                        //checks for all the names and points. stores name and points as name-points
                        //below condition will be false when location is reached
                        if (i + 1 < eachRace.size()) {
                            formattedEachRace.add(eachRace.get(i) + "-" + eachRace.get(i + 1));
                        }
                        else{
                            // location added
                            formattedEachRace.add(eachRace.get(i));
                        }
                    }
                    if(!formattedEachRace.isEmpty()){
                        //date added
                        formattedEachRace.add(details[details.length-1]);
                        allRaces.add(formattedEachRace);
                    }

                    eachRace = new ArrayList<>();
                } else {
                    //getting details of each race from details[]
                    for (String element : details) {
                        eachRace.add(element);
                    }
                }
            }



            readFile.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");

        }


        List<List<String>> modifiedList = new ArrayList<>();
        for(List<String> innerList : allRaces){
            //for each race in all races
            String location = innerList.get(innerList.size() - 2); //one before last element
            String date = innerList.get(innerList.size() - 1); //last element

            List<String> modifiedInnerList = new ArrayList<>();
            //putting all the drivers and their points in to one single string, location seperately
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i< innerList.size() - 2; i++){
                sb.append(innerList.get(i)).append(" ");
            }
            modifiedInnerList.add(sb.toString().trim());
            modifiedInnerList.add(String.valueOf(location));

            //converting the date to the date format
            try{
                SimpleDateFormat originalFormat = new SimpleDateFormat("dd-MM-yyyy");
                Date convertDate = originalFormat.parse(date);
                modifiedInnerList.add(originalFormat.format(convertDate)); //adding the date after location
            }
            catch (ParseException e){
                System.out.println("Date could not be converted to date format");
            }
            modifiedList.add(modifiedInnerList); //all driver names and points as one string, date and location seperately
        }


        boolean sorted = false;
        while(!sorted){
            sorted = true;
            //loop to go trhough all elements in the nested list
            for(int i = 0; i< modifiedList.size()-1;i++){
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
                    //swapping inner lists according to date
                    List temp = modifiedList.get(i);
                    modifiedList.set(i, modifiedList.get(i+1));
                    modifiedList.set(i+1, temp);
                    //Collections.swap(modifiedList, i, i+1);
                    sorted = false;
                }
            }


        }

        //creating and populating observable lists / tables
        if(!modifiedList.isEmpty()){
            message.setText("");
            ObservableList<RaceDetails> data = FXCollections.observableArrayList();
            for(List<String> details : modifiedList){
                String date =  details.get(2);
                String location =  details.get(1);
                String driverElem = details.get(0);
                String[] elem = driverElem.split("(?<=\\d)\\s+(?=[A-Za-z])");
                //(?<=\\d): when there is a white space before a digit
                //(\\s): one or more white spaces
                //(?=[A-Za-z]): white space before a letter
                //this splits the string before a letter after a digit.
                //this is to make sure that when full names are entered they are not split, but the whole name is considered.


                //mentioning positions for first three players.
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
        else{
            message.setText("No races have taken place.");
        }
    }


    //navigating to menu by click of back button
    @FXML
    private void onGoBackButtonClick(ActionEvent actionEvent) throws IOException {
        navigateGoBack(actionEvent);
    }

    private void navigateGoBack(ActionEvent actionEvent) throws IOException {
        Stage newStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("menu.fxml"));
        newStage.setScene(new Scene(root, 600, 400));
        newStage.show();

        Stage previousStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        previousStage.close();
    }
}





