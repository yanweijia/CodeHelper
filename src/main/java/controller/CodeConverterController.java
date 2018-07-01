package controller;

import controller.codeconverter.Base64ConverterController;
import controller.codeconverter.Base64ImageConverterController;
import controller.codeconverter.HashConverterController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import util.FXHelper;

import java.net.URL;
import java.util.ResourceBundle;

public class CodeConverterController implements Initializable {
    public static final String fxmlFile = "/fxml/CodeConverter.fxml";


    @FXML
    private Pane container;
    @FXML
    private ScrollPane scrollPane;

    @FXML
    private void Base64ConverterFunction() {
        FXHelper.loadWindow(getClass(), Base64ConverterController.fxmlFile, container);
    }

    @FXML
    private void HashConverterFunction() {
        FXHelper.loadWindow(getClass(), HashConverterController.fxmlFile, container);
    }

    @FXML
    private void Base64ImageConverterFunction() {
        FXHelper.loadWindow(getClass(), Base64ImageConverterController.fxmlFile, container);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Base64ConverterFunction();
        //scrollPane's content auto fit parent size
        scrollPane.setFitToHeight(true);
        scrollPane.setFitToWidth(true);
    }

}
