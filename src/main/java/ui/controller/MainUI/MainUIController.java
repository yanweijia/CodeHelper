package ui.controller.MainUI;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import util.FXHelper;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class MainUIController implements Initializable {

    public static String fxmlFile = "/fxml/MainUI.fxml";
    public static String title = "mainUI";

    @FXML
    private Button btnTest;

    @FXML
    private void login() throws IOException {

        FXHelper.newStage(getClass(), "/fxml/MainUI.fxml", "个人信息维护!");
        FXHelper.getFirstStage().close();    //关闭系统第一个窗口
    }


    /**
     * 退出程序
     */
    @FXML
    private void quit() {
        Optional<ButtonType> buttonType = new Alert(Alert.AlertType.CONFIRMATION, "确认退出?", ButtonType.YES, ButtonType.CANCEL).showAndWait();
        if (buttonType.get().getButtonData().equals(ButtonBar.ButtonData.YES)) {
            FXHelper.getFirstStage().close();
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

}