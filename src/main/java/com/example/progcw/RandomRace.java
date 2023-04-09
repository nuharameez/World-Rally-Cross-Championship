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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

import static com.example.progcw.DriverList.*;
import static com.example.progcw.DriverList.allDrivers;

public class RandomRace {


    public RandomRace() {



    }

    @FXML
    private TableColumn<RaceDrivers, String> name;

    @FXML
    private TableColumn<RaceDrivers, String> position;
    @FXML
    private TableColumn<RaceDrivers, String> points;

    @FXML
    private TableView<RaceDrivers> race;
    @FXML
    private TextArea locationL;
    @FXML
    private TextArea date;
    @FXML
    private Label raceError;


    public void showRace() {

        displayTable(DriverList.allDrivers);
        setLocation();
        setDate();
    }

    //creating a class in order to use when populating tables / observable lists
    public class RaceDrivers {
        private String name;
        private String points;
        private String position;

        public RaceDrivers(String name, String points, String position) {
            this.name = name;
            this.points = points;
            this.position = position;
        }

        public String getName() {
            return name;
        }

        public String getPoints() {
            return points;
        }

        public String getPosition() {
            return position;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setPoints(String points) {
            this.points = points;
        }

        public void setPosition(String position) {
            this.position = position;
        }
    }


    //method to set a date for the race randomly
    private void setDate() {
        if (!allDrivers.isEmpty()) {
            raceError.setText("");
            LocalDate today = LocalDate.now(); //today's date
            LocalDate nextMonth = today.plusDays(30); //getting dates from today for the nest 30 days

            //toEpoch since dates need to be used for calculation later on
            long minDay = today.toEpochDay();
            long maxDay = nextMonth.toEpochDay();

            List<String> existingDates = new ArrayList<>();

            //checking the dates stored in the text file that has dates of all the races previously generated

            try{
                BufferedReader reader = new BufferedReader(new FileReader("raceDates.txt"));
                String line = reader.readLine();
                while(line != null){
                    existingDates.add(line.trim());
                    line = reader.readLine();
                }
                reader.close();
            }catch(IOException e){
                System.out.println("File does not exist");
            }

            LocalDate randomDate = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            String formattedDate="";
            while(existingDates.contains(randomDate.format(formatter))){
                //randomly selecting a day from minDay(today) to maxDay(30 days from now)
                long randomDay = ThreadLocalRandom.current().nextLong(minDay, maxDay);
                randomDate = LocalDate.ofEpochDay(randomDay);
            }

            //randomly selecting a day from minDay(today) to maxDay(30 days from now)
            //long randomDay = ThreadLocalRandom.current().nextLong(minDay, maxDay);
            //LocalDate randomDate = LocalDate.ofEpochDay(randomDay);

            //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            formattedDate = randomDate.format(formatter);
            date.setText(formattedDate);

            //writing date to teh text file

            try {
                FileWriter writer = new FileWriter("detailsOfRace.txt", true); //true appends the details. does not over write.
                writer.write(formattedDate + "\n");
                writer.close();
            } catch (IOException e) {
                System.out.println("File does not exist");
            }

            //saving only the date of race in a text file to ensure the date is not duplicated
            try {
                FileWriter writer = new FileWriter("raceDates.txt", true); //true appends the details. does not over write.
                writer.write(formattedDate + "\n");
                writer.close();
            } catch (IOException e) {
                System.out.println("File does not exist");
            }

        } else {
            raceError.setText("There are no drivers to start race");
        }
    }


    private void setLocation() {
        if (!allDrivers.isEmpty()) {
            String[] locations = {"Nyirad", "Holjes", "Montalegre", "Barcelona", "Riga", "Norway"};
            Random rand = new Random();
            String randomLocation = locations[rand.nextInt(locations.length)];
            locationL.setText(randomLocation);
            try {
                FileWriter writer = new FileWriter("detailsOfRace.txt", true); //true appends the details. does not over write.
                writer.write(randomLocation + "\n");
                writer.close();
            } catch (IOException e) {
                System.out.println("File does not exist");
            }

        } else {
            raceError.setText("There are no drivers to start race");

        }

    }

    private void displayTable(List<ArrayList> allDrivers) {
        Collections.shuffle(allDrivers);

        for (int i = 0; i < allDrivers.size(); i++) {
            //if details loaded from text file to system, points will be in String, therefore need to convert first.
            Object elem = allDrivers.get(i).get(4);
            if (elem instanceof String) {
                if (i == 0) {
                    int current = Integer.parseInt((String) allDrivers.get(i).get(4));
                    int newVal = current + 10;
                    allDrivers.get(i).set(4, Integer.toString(newVal));
                } else if (i == 1) {
                    int current = Integer.parseInt((String) allDrivers.get(i).get(4));
                    int newVal = current + 7;
                    allDrivers.get(i).set(4, Integer.toString(newVal));
                } else if (i == 2) {
                    int current = Integer.parseInt((String) allDrivers.get(i).get(4));
                    int newVal = current + 5;
                    allDrivers.get(i).set(4, Integer.toString(newVal));
                }
            } else {
                if (i == 0) {
                    int current = (int) allDrivers.get(i).get(4);
                    int newVal = current + 10;
                    allDrivers.get(i).set(4, newVal);
                } else if (i == 1) {
                    int current = (int) allDrivers.get(i).get(4);
                    int newVal = current + 7;
                    allDrivers.get(i).set(4, newVal);
                } else if (i == 2) {
                    int current = (int) allDrivers.get(i).get(4);
                    int newVal = current + 5;
                    allDrivers.get(i).set(4, newVal);
                }

            }
        }

        //creating a new list to hold the name and points only
        List<String> raceData = new ArrayList<>();
        for (int i = 0; i < DriverList.allDrivers.size(); i++) {
            raceData.add(String.valueOf(DriverList.allDrivers.get(i).get(0)));
            raceData.add(String.valueOf(DriverList.allDrivers.get(i).get(4)));
            //writing the name and points of the drivers in to a text file
            try {
                FileWriter writer = new FileWriter("detailsOfRace.txt", true); //true appends the details. does not over write.

                for (String line : raceData) {
                    writer.write(String.join(",", line,"\n"));
                }
                raceData.clear();
                writer.close();
            } catch (IOException e) {
                System.out.println("File does not exist");
            }
        }
        //populatig the observable list and then the table

        if(!DriverList.allDrivers.isEmpty()) {
            ObservableList<RaceDrivers> data = FXCollections.observableArrayList();
            int rank = 1;
            for (ArrayList driver : allDrivers) {
                String name = (String) driver.get(0);
                String points = String.valueOf(driver.get(4));
                String position = String.valueOf((rank));
                rank += 1;
                RaceDrivers raceDriver = new RaceDrivers(name, points, position);
                data.add(raceDriver);
            }
            position.setCellValueFactory(new PropertyValueFactory<RaceDrivers, String>("position"));
            name.setCellValueFactory(new PropertyValueFactory<RaceDrivers, String>("name"));
            points.setCellValueFactory(new PropertyValueFactory<RaceDrivers, String>("points"));
            race.setItems(data);

        }


        else{
        raceError.setText("There are no drivers to start race");
    }

}
    //navigating user back to the main menu when back button clicked
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
