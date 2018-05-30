package util;

import com.sun.javafx.robot.impl.FXRobotHelper;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by FXBL on 6/20/2017.
 */
public class FXHelper {
    public static Stage getFirstStage() {
        ObservableList<Stage> stage = FXRobotHelper.getStages();
        return stage.get(0);
    }

    /**
     * 新窗口
     *
     * @param className   类名
     * @param fxmlFile    fxml文件位置
     * @param windowTitle 窗口名称
     * @throws IOException
     */
    public static void newStage(Class className, String fxmlFile, String windowTitle) throws IOException {
        Scene scene = new Scene((Parent) FXMLLoader.load(className.getResource(fxmlFile)));
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle(windowTitle);
        stage.show();
    }


    private static void showErrorDialog(String message) {
        showDialog("Error", "Error", message, Alert.AlertType.ERROR);
    }

    private static void showInfoDialog(String message) {
        showDialog("Info", "Info", message, Alert.AlertType.INFORMATION);
    }

    /**
     * 消息框
     *
     * @param title      标题
     * @param headerText
     * @param message    信息
     * @param type       信息框类型
     */
    private static void showDialog(String title, String headerText, String message, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(message);
        alert.initOwner(FXHelper.getFirstStage());
        alert.show();
    }


    /**
     * 弹出警告框
     *
     * @param message 信息
     */
    public static void showWarningDialog(String message) {
        showWarningDialog("Warning", "Warning", message);
    }

    /**
     * 弹出警告框
     *
     * @param title      标题
     * @param headerText
     * @param message    信息
     */
    public static void showWarningDialog(String title, String headerText, String message) {
        showDialog(title, headerText, message, Alert.AlertType.WARNING);
    }

}