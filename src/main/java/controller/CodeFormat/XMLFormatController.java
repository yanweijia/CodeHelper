package controller.CodeFormat;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import util.FXHelper;
import util.XMLUtil;

public class XMLFormatController {
    public static final String fxmlFile = "/fxml/CodeFormat/XMLFormat.fxml";

    @FXML
    private TextArea txtOriginXML, txtPrettyXML;


    @FXML
    private void formatXML() {
        String originXML = txtOriginXML.getText();

        if (originXML == null || "".equals(originXML.trim())) {
            FXHelper.showInfoDialog("please input sth.");
            txtOriginXML.requestFocus();
            return;
        }

        String prettyXML = null;
        try {
            prettyXML = XMLUtil.format(txtOriginXML.getText());
        } catch (Exception e) {
            FXHelper.showErrorDialog("fail to format text.\n msg:" + e.getMessage());
        }
        txtPrettyXML.setText(prettyXML);
    }
}
