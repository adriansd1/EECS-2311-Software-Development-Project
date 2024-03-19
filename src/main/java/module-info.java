module com.example.eecs2311termproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;


    opens com.example.eecs2311termproject to javafx.fxml;
    exports com.example.eecs2311termproject;
}