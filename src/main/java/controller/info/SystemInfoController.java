package controller.info;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import util.SystemInfoUtil;
import vo.SystemInfoVO;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class SystemInfoController implements Initializable {
    public static final String fxmlFile = "/fxml/info/SystemInfo.fxml";

    @FXML
    private TableView<SystemInfoVO> tableView;
    @FXML
    private TableColumn<SystemInfoVO, String> colKey, colValue;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colKey.setCellValueFactory(new PropertyValueFactory<>("key"));
        colValue.setCellValueFactory(new PropertyValueFactory<>("value"));
        showSystemInfo();
    }

    private List<SystemInfoVO> getSystemInfo() {
        List<SystemInfoVO> list = new ArrayList<>();
        list.add(new SystemInfoVO("JavaVersion", SystemInfoUtil.getJavaVersion()));
        list.add(new SystemInfoVO("OS name", SystemInfoUtil.getOSName()));
        list.add(new SystemInfoVO("os version", SystemInfoUtil.getOSVersion()));
        list.add(new SystemInfoVO("OS architecture", SystemInfoUtil.getOSArch()));
        list.add(new SystemInfoVO("OS dataModel",SystemInfoUtil.getDataModel()));
        list.add(new SystemInfoVO("file encoding",SystemInfoUtil.getFileEncoding()));
        list.add(new SystemInfoVO("Jnu encoding",SystemInfoUtil.getJnuEncoding()));
        list.add(new SystemInfoVO("Java Class Version",SystemInfoUtil.getJavaClassVersion()));
        list.add(new SystemInfoVO("Java home",SystemInfoUtil.getJavaHome()));
        list.add(new SystemInfoVO("user country",SystemInfoUtil.getUserCountry()));
        list.add(new SystemInfoVO("user home",SystemInfoUtil.getUserHome()));
        list.add(new SystemInfoVO("user name",SystemInfoUtil.getUserName()));
        list.add(new SystemInfoVO("user language",SystemInfoUtil.getUserLanguage()));
        list.add(new SystemInfoVO("user timezone",SystemInfoUtil.getUserTimezone()));
        list.add(new SystemInfoVO("Work Directory",SystemInfoUtil.getWorkDir()));
        list.add(new SystemInfoVO("Disk Info",SystemInfoUtil.getDiskInfo()));
        return list;
    }

    private void showSystemInfo() {
        tableView.getItems().removeAll();
        tableView.setItems(FXCollections.observableList(getSystemInfo()));
    }
}
