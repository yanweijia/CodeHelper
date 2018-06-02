package controller.CodeFormat;

import com.alibaba.fastjson.JSON;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import util.FXHelper;

public class JSONFormatController {
    public static final String fxmlFile = "/fxml/CodeFormat/JSONFormat.fxml";

    @FXML
    private TextArea txtOriginJSON, txtFormatedJSON;


    @FXML
    private void formatJSON() {
        String originJSON = txtOriginJSON.getText();

        if (originJSON == null || "".equals(originJSON.trim())) {
            FXHelper.showInfoDialog("please input sth.");
            txtOriginJSON.requestFocus();
            return;
        }

        String prettyJSON = null;
        try {
            prettyJSON = JSON.toJSONString(JSON.parseObject(txtOriginJSON.getText()), true);
        } catch (Exception e) {
            FXHelper.showErrorDialog("fail to format text.\n msg:" + e.getMessage());
        }
        txtFormatedJSON.setText(prettyJSON);
    }
}
