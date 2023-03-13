module com.example.progcw {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.progcw to javafx.fxml;
    exports com.example.progcw;
}