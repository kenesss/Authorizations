module com.example.kengesmukhamedjan {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.kengesmukhamedjan to javafx.fxml;
    exports com.example.kengesmukhamedjan;
}