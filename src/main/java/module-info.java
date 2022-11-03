module com.example.labb3jesperbodin {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.labb3jesperbodin to javafx.fxml;
    exports com.example.labb3jesperbodin;
    exports com.example.labb3jesperbodin.model;
    opens com.example.labb3jesperbodin.model to javafx.fxml;
    exports com.example.labb3jesperbodin.controller;
    opens com.example.labb3jesperbodin.controller to javafx.fxml;
}