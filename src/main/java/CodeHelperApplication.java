import controller.MainUIController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;
import util.ConfigUtil;

import javax.swing.*;

/**
 * 软件入口
 */
@Slf4j
public class CodeHelperApplication extends Application {



    @Override
    public void start(Stage stage) throws Exception {
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource(MainUIController.fxmlFile)));
        stage.setScene(scene);
        stage.setTitle(MainUIController.title);
        stage.show();

        if (stage != null) {
            stage.setOnCloseRequest(event -> {  //设置退出程序时自动保存窗口位置
                onClose(stage);
                System.exit(0);
            });
        }
        readWindowInfo(stage);
    }

    /**
     * JavaFX 需要 JDK1.8_60 版本以后的才可以
     * @param args
     */
    public static void main(String[] args) {
        String version = System.getProperty("java.version");
        if (Integer.parseInt(version.substring(2, 3)) >= 8 && Integer.parseInt(version.substring(6)) >= 60) {
            log.info("begin to launch...");
            launch(args);
        } else {
            log.error("do not support version below jdk_1.8_60");
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


    private void onClose(Stage stage) {
        saveWindowInfo(stage);
    }

    private void saveWindowInfo(Stage stage) {
        log.info("width:{},height:{},x:{},y:{}", stage.getWidth(), stage.getHeight(), stage.getX(), stage.getY());
        ConfigUtil.saveWindowSize(String.valueOf(stage.getX()), String.valueOf(stage.getY()), String.valueOf(stage.getWidth()), String.valueOf(stage.getHeight()));
        log.info("done!");
    }

    private void readWindowInfo(Stage stage) {
        //读取到的内容有误就使用默认信息
        try {
            double x = Double.parseDouble(ConfigUtil.getProperty(ConfigUtil.CFG_WINDOW_X, null));
            double y = Double.parseDouble(ConfigUtil.getProperty(ConfigUtil.CFG_WINDOW_Y, null));
            double width = Double.parseDouble(ConfigUtil.getProperty(ConfigUtil.CFG_WINDOW_WIDTH, null));
            double height = Double.parseDouble(ConfigUtil.getProperty(ConfigUtil.CFG_WINDOW_HEIGHT, null));
            if (x != 0.0D && y != 0.0D && width != 0.0D && height != 0.0D) {
                stage.setX(x);
                stage.setY(y);
                stage.setWidth(width);
                stage.setHeight(height);
            }
        } catch (Exception e) {
            log.warn("Cannot find window size properties,The config file is not exists or conf is empty.msg:{}", e.getMessage(), e);
        }
        log.info("done!");
    }

}