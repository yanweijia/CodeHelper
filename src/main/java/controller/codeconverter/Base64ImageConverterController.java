package controller.codeconverter;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;

import java.net.URL;
import java.util.ResourceBundle;

public class Base64ImageConverterController implements Initializable {
    public static final String fxmlFile = "/fxml/codeconverter/Base64ImageConverter.fxml";

    @FXML
    private TextArea txtBase64;
    @FXML
    private WebView webView;
//    @FXML
//    private Button btnImageToBase64, btnBase64ToImage;

    //文件选择器
    private final FileChooser fileChooser = new FileChooser();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        txtBase64.setText(null);
        webView.getEngine().loadContent("<span>this is image box</span>");
        webView.setOnDragOver(null);
    }

    @FXML
    private void base64ToImage() {
        showImage(txtBase64.getText());
    }

    @FXML
    private void imageToBase64() {

    }

    private void showImage(String base64Str) {
        if (base64Str == null) {
            return;
        }
        String html = "<img src='" + base64Str + "'/>";
        webView.getEngine().loadContent(html);
    }

    public enum Source {
        textField("TextField"),
        file("File");

        private String label;

        Source(String label) {
            this.label = label;
        }
    }
}
