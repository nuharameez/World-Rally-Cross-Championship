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

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;



public class Standings {
    ArrayList<ArrayList> allDrivers = DriverList.allDrivers;
    public Standings() {
        this.allDrivers = allDrivers;
    }

    @FXML
    private Label Heading;
    @FXML
    private TableColumn<Driver, String> age;

    @FXML
    private TableColumn<Driver, String> name;
    @FXML
    private TableColumn<Driver, String> car;
    @FXML
    private TableColumn<Driver, String> team;

    @FXML
    private TableColumn<Driver, String> points;

    @FXML
    private TableView<Driver> standingTable;






    public void showStanding() throws IOException {
        displayTable(allDrivers);
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

    public static void bubblesortingList(ArrayList<ArrayList> sortList){
        int n = sortList.size();
        for (int i = 0; i<n; i++){
            for ( int j = 0; j< n - i - 1; j++){
                int current = (int) sortList.get(j).get(4);
                int next= (int) sortList.get(j+1).get(4);
                if(current<next){
                    ArrayList temp = sortList.get(j);
                    sortList.set(j, sortList.get(j+1));
                    sortList.set(j+1, temp);
                }
            }
        }
    }

    public class Driver{
        private String name;
        private String team;
        private String car;

        private String age;
        private String points;

        public Driver(String name, String age, String team, String car, String points) {
            this.name = name;
            this.age = age;
            this.team = team;
            this.car = car;
            this.points = points;
        }

        public String getName(){
            return name;
        }

        public String getTeam() {
            return team;
        }

        public String getCar() {
            return car;
        }

        public String getPoints() {
            return points;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setTeam(String team) {
            this.team = team;
        }

        public void setCar(String car) {
            this.car = car;
        }

        public void setAge(String age) {
            this.age = age;
        }

        public void setPoints(String points) {
            this.points = points;
        }

        public String getAge(){
            return age;
        }
    }
    public void displayTable(List<ArrayList> allDrivers){
        bubblesortingList(DriverList.allDrivers);
        ObservableList<Driver> data = FXCollections.observableArrayList();
        for (ArrayList driverInfo : allDrivers){
            String name = (String) driverInfo.get(0);
            String age = String.valueOf( driverInfo.get(1));
            String team = (String) driverInfo.get(2);
            String car = (String) driverInfo.get(3);
            String points = String.valueOf(driverInfo.get(4));
            Driver driver = new Driver(name, age, team, car, points);
            data.add(driver);
        }
        name.setCellValueFactory(new PropertyValueFactory<Driver, String>("name"));
        age.setCellValueFactory(new PropertyValueFactory<Driver, String>("age"));
        team.setCellValueFactory(new PropertyValueFactory<Driver, String>("team"));
        car.setCellValueFactory(new PropertyValueFactory<Driver, String>("car"));
        points.setCellValueFactory(new PropertyValueFactory<Driver, String>("points"));
        standingTable.setItems(data);
    }


    }
