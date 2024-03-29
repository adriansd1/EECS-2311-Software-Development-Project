module com.example.eecs2311termproject {
    requires javafx.controls;
    requires javafx.fxml;

    requires java.sql;

    opens com.example.eecs2311termproject to javafx.fxml;
    exports com.example.eecs2311termproject;
}