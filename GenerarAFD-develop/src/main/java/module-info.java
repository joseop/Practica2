module com.generarafd {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.generarafd to javafx.fxml;
    exports com.generarafd;
}