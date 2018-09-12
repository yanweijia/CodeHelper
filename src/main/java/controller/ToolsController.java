package controller;

import controller.tools.DateTimeToolsController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import util.FXHelper;

import java.net.URL;
import java.util.ResourceBundle;

public class ToolsController implements Initializable {
    public static final String fxmlFile = "/fxml/Tools.fxml";


    @FXML
    private Pane container;
    @FXML
    private ScrollPane scrollPane;

    @FXML
    private void dateTimeFunction() {
        FXHelper.loadWindow(getClass(), DateTimeToolsController.fxmlFile, container);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dateTimeFunction();
        //scrollPane's content auto fit parent size
        scrollPane.setFitToHeight(true);
        scrollPane.setFitToWidth(true);
    }

}
