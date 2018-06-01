package controller.CodeFormat;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import util.HtmlFormatUtil;

public class HTMLFormatController {
    public static final String fxmlFile = "/fxml/CodeFormat/HTMLFormat.fxml";

    @FXML
    private TextArea txtOriginHtml, txtFormatedHtml;


    @FXML
    private void formatHTML() {
        txtFormatedHtml.setText(HtmlFormatUtil.formatHtml(txtOriginHtml.getText()));
    }
}
