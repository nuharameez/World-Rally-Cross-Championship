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

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

import static com.example.progcw.DriverList.*;

public class RandomRace {


    public RandomRace() {
        Collections.shuffle(allDrivers);
        ;
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
                    int newVal = current + 7;
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
                    int newVal = current + 7;
                    allDrivers.get(i).set(4, newVal);
                }

            }
        }
        List<String> raceData = new ArrayList<>();
        for (int i = 0; i < DriverList.allDrivers.size(); i++) {
            raceData.add(String.valueOf(DriverList.allDrivers.get(i).get(0)));
            raceData.add(String.valueOf(DriverList.allDrivers.get(i).get(4)));

            try {
                FileWriter writer = new FileWriter("raceDrivers.txt", true); //true appends the details. does not over write.

                for (String line : raceData) {
                    writer.write(String.join(",", line,"\n"));
                }
                raceData.clear();
                //writer.write("\n");
                writer.close();
            } catch (IOException e) {
                System.out.println("File does not exist");
            }
        }
        //setLocation();

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

    public void setDate() {
        if (!allDrivers.isEmpty()) {
            raceError.setText("");
            LocalDate today = LocalDate.now();
            LocalDate nextMonth = today.plusDays(30);
            long minDay = today.toEpochDay();
            long maxDay = nextMonth.toEpochDay();
            long randomDay = ThreadLocalRandom.current().nextLong(minDay, maxDay);
            LocalDate randomDate = LocalDate.ofEpochDay(randomDay);

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            String formattedDate = randomDate.format(formatter);
            date.setText(formattedDate);

            try {
                FileWriter writer = new FileWriter("raceDrivers.txt", true); //true appends the details. does not over write.
                writer.write(formattedDate + "\n");
                writer.close();
            } catch (IOException e) {
                System.out.println("File does not exist");
            }

        } else {
            raceError.setText("There are no drivers to start race");
        }
    }


    public void setLocation() {
        if (!allDrivers.isEmpty()) {
            String[] locations = {"Nyirad", "Holjes", "Montalegre", "Barcelona", "Riga", "Norway"};
            Random rand = new Random();
            String randomLocation = locations[rand.nextInt(locations.length)];
            locationL.setText(randomLocation);
            try {
                FileWriter writer = new FileWriter("raceDrivers.txt", true); //true appends the details. does not over write.
                writer.write(randomLocation + "\n");
                writer.close();
            } catch (IOException e) {
                System.out.println("File does not exist");
            }

        } else {
            raceError.setText("There are no drivers to start race");

        }

    }

    public void displayTable(List<ArrayList> allDrivers) {

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
