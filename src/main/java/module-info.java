module org.example.graafikaga {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens org.example.graafikaga to javafx.fxml;
    exports org.example.graafikaga;
}