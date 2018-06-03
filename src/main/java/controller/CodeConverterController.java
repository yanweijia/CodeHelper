package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;

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
//        FXHelper.loadWindow(getClass(), Base64ConverterController.fxmlFile, container);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Base64ConverterFunction();
        //scrollPane's content auto fit parent size
        scrollPane.setFitToHeight(true);
        scrollPane.setFitToWidth(true);
    }

}
