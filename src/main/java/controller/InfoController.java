package controller;

import controller.info.SystemInfoController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import util.FXHelper;

import java.net.URL;
import java.util.ResourceBundle;

public class InfoController implements Initializable {
    public static final String fxmlFile = "/fxml/Info.fxml";


    @FXML
    private Pane container;
    @FXML
    private ScrollPane scrollPane;

    @FXML
    private void systemInfoFunction() {
        FXHelper.loadWindow(getClass(), SystemInfoController.fxmlFile, container);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        systemInfoFunction();
        //scrollPane's content auto fit parent size
        scrollPane.setFitToHeight(true);
        scrollPane.setFitToWidth(true);
    }

}
