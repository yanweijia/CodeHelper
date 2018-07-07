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
        return list;
    }

    private void showSystemInfo() {
        tableView.getItems().removeAll();
        tableView.setItems(FXCollections.observableList(getSystemInfo()));
    }
}
