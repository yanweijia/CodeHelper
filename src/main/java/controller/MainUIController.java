package controller;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.StackPane;
import util.FXHelper;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class MainUIController implements Initializable {

    public static final String fxmlFile = "/fxml/MainUI.fxml";
    public static final String title = "Code Helper";


    @FXML
    private StackPane containerPane;



    @FXML
    private void codeFormatFunction() {
        Node pane = null;
        try {
            pane = FXMLLoader.load(getClass().getResource(CodeFormatController.fxmlFile));
        } catch (IOException e) {
            System.out.println("switch ui failed");
        }
        containerPane.getChildren().clear();
        containerPane.getChildren().addAll(pane);
    }


    @FXML
    private void quit() {
        Optional<ButtonType> buttonType = new Alert(Alert.AlertType.CONFIRMATION, "确认退出?", ButtonType.YES, ButtonType.CANCEL).showAndWait();
        if (buttonType.get().getButtonData().equals(ButtonBar.ButtonData.YES)) {
            FXHelper.getFirstStage().close();
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        codeFormatFunction();
    }

}