package ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.swing.*;

/**
 * 软件入口
 */
public class CodeHelperApplication extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/MainUI.fxml"));
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(true);
        primaryStage.setTitle("Title");
        primaryStage.show();
    }

    public static void main(String[] args) {
        String version = System.getProperty("java.version");
        if (Integer.parseInt(version.substring(2, 3)) >= 8 && Integer.parseInt(version.substring(6)) >= 60) {
            launch(args);
        } else {
            JFrame jFrame = new JFrame("版本错误");
            jFrame.setSize(500, 100);
            jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            JPanel jPanel = new JPanel();
            JLabel jLabel = new JLabel("JDK的版本不能低于1.8，请升级至最近的JDK 1.8再运行此软件");
            jPanel.add(jLabel);
            jFrame.add(jPanel);
            jFrame.setLocationRelativeTo(null);
            jFrame.setVisible(true);

        }
    }

}