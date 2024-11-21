module com.uefs.system {
    requires javafx.controls;
    requires javafx.graphics;
    requires javafx.fxml;
    requires javafx.swing;
    requires javafx.web;
    requires javafx.media;
    requires com.google.gson;
    requires annotations;
    requires java.logging;
    requires com.google.protobuf;

    opens com.uefs.system.model to com.google.gson;
    opens com.uefs.system.service to com.google.gson;
    opens com.uefs.system.utils to com.google.gson;
    opens com.uefs.system.repository to com.google.gson;
    opens com.uefs.system.emun to com.google.gson;
    opens com.uefs.system.controller to com.google.gson;
    opens com.uefs.system to javafx.graphics;
    opens com.uefs.system.view.controllers to javafx.fxml;
    exports com.uefs.system.view.controllers;
    exports com.uefs.system.emun;
    exports com.uefs.system.utils;
    exports com.uefs.system.model;
    exports com.uefs.system.Interface;
    exports com.uefs.system.view;
    exports com.uefs.system;
}
