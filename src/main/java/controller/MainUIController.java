package controller;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import util.FXHelper;

import java.net.URL;
import java.util.ResourceBundle;

public class MainUIController implements Initializable {

    public static final String fxmlFile = "/fxml/MainUI.fxml";
    public static final String title = "Code Helper";

    private Stage stage = null;

    @FXML
    private StackPane containerPane;


    @FXML
    private void codeFormatFunction() {
        FXHelper.loadWindow(getClass(), CodeFormatController.fxmlFile, containerPane);
    }

    @FXML
    private void codeConverterFunction() {
        FXHelper.loadWindow(getClass(), CodeConverterController.fxmlFile, containerPane);
    }

    @FXML
    private void infoFunction() {
        FXHelper.loadWindow(getClass(), InfoController.fxmlFile, containerPane);
    }

    @FXML
    private void toolsFunction() {
        FXHelper.loadWindow(getClass(), ToolsController.fxmlFile, containerPane);
    }




    @Override
    public void initialize(URL location, ResourceBundle resources) {
        codeFormatFunction();
    }

}