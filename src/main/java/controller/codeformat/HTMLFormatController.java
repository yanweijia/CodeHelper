package controller.codeformat;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import util.HtmlUtil;

import java.net.URL;
import java.util.ResourceBundle;

public class HTMLFormatController implements Initializable {
    public static final String fxmlFile = "/fxml/codeformat/HTMLFormat.fxml";

    @FXML
    private TextArea txtOriginHtml, txtFormatedHtml;
    @FXML
    private ChoiceBox cbType;


    @FXML
    private void formatHTML() {
        if (cbType.getValue().equals(HtmlType.html4)) {
            txtFormatedHtml.setText(HtmlUtil.formatHtml4(txtOriginHtml.getText()));
        } else {
            txtFormatedHtml.setText(HtmlUtil.formatHtml5(txtOriginHtml.getText()));
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cbType.getItems().setAll(HtmlType.values());
        cbType.setValue(HtmlType.html5);
    }

    public enum HtmlType {
        html4("html4"),
        html5("html5");

        private String label;

        HtmlType(String label) {
            this.label = label;
        }
    }
}
