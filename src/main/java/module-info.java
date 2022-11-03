module com.example.labb3jesperbodin {
    requires javafx.controls;
    requires javafx.fxml;
            
                            
    opens com.example.labb3jesperbodin to javafx.fxml;
    exports com.example.labb3jesperbodin;
}