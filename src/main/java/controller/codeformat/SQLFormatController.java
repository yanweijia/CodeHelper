package controller.codeformat;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import util.FXHelper;
import util.SQLUtil;

public class SQLFormatController {
    public static final String fxmlFile = "/fxml/codeformat/SQLFormat.fxml";

    @FXML
    private TextArea txtOrigin, txtPretty;


    @FXML
    private void format() {
        String originSQL = txtOrigin.getText();

        if (originSQL == null || "".equals(originSQL.trim())) {
            FXHelper.showInfoDialog("please input sth.");
            txtOrigin.requestFocus();
            return;
        }

        String prettySQL = null;
        try {
            prettySQL = SQLUtil.format(txtOrigin.getText());
        } catch (Exception e) {
            FXHelper.showErrorDialog("fail to format text.\n msg:" + e.getMessage());
        }
        txtPretty.setText(prettySQL);
    }
}
