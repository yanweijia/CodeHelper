package controller;

import controller.codeformat.HTMLFormatController;
import controller.codeformat.JSONFormatController;
import controller.codeformat.SQLFormatController;
import controller.codeformat.XMLFormatController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import util.FXHelper;

import java.net.URL;
import java.util.ResourceBundle;

public class CodeFormatController implements Initializable {
    public static final String fxmlFile = "/fxml/CodeFormat.fxml";


    @FXML
    private Pane container;
    @FXML
    private ScrollPane scrollPane;

    @FXML
    private void htmlFormatFunction() {
        FXHelper.loadWindow(getClass(), HTMLFormatController.fxmlFile, container);
    }

    @FXML
    private void jsonFormatFunction() {
        FXHelper.loadWindow(getClass(), JSONFormatController.fxmlFile, container);
    }

    @FXML
    private void xmlFormatFunction() {
        FXHelper.loadWindow(getClass(), XMLFormatController.fxmlFile, container);
    }

    @FXML
    private void sqlFormatFunction() {
        FXHelper.loadWindow(getClass(), SQLFormatController.fxmlFile, container);

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        htmlFormatFunction();
        //scrollPane's content auto fit parent size
        scrollPane.setFitToHeight(true);
        scrollPane.setFitToWidth(true);
    }

}
