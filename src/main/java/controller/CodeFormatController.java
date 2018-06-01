package controller;

import controller.CodeFormat.HTMLFormatController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CodeFormatController implements Initializable {
    public static final String fxmlFile = "/fxml/CodeFormat.fxml";


    @FXML
    private Pane container;
    @FXML
    private ScrollPane scrollPane;

    @FXML
    private void HTMLFormatFunction() {
        Node pane = null;
        try {
            pane = FXMLLoader.load(getClass().getResource(HTMLFormatController.fxmlFile));
        } catch (IOException e) {
            System.out.println("switch ui failed");
        }
        container.getChildren().clear();
        container.getChildren().addAll(pane);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        HTMLFormatFunction();
        //scrollPane's content auto fit parent size
        scrollPane.setFitToHeight(true);
        scrollPane.setFitToWidth(true);
    }

}
