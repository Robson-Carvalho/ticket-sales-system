module com.uefs.system {
    requires javafx.controls;
    requires javafx.graphics;
    requires javafx.fxml;
    requires javafx.swing;
    requires javafx.web;
    requires javafx.media;
    requires com.google.gson;

    opens com.uefs.system.model to com.google.gson;
    opens com.uefs.system.service to com.google.gson;
    opens com.uefs.system.utils to com.google.gson;
    opens com.uefs.system.repository to com.google.gson;
    opens com.uefs.system.emun to com.google.gson;
    opens com.uefs.system.controller to com.google.gson;
    opens com.uefs.system to javafx.graphics;
    exports com.uefs.system;
}
