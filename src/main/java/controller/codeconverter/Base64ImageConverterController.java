package controller.codeconverter;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.ArrayUtils;
import util.FXHelper;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Base64;
import java.util.ResourceBundle;

public class Base64ImageConverterController implements Initializable {
    public static final String fxmlFile = "/fxml/codeconverter/Base64ImageConverter.fxml";

    //var to save image to File.
    private String base64ImageStr = null;

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
        txtBase64.setText(base64ImageStr);
    }

    @FXML
    private void openImageFile() {
        File imageFile = fileChooser.showOpenDialog(null);
        if (null == imageFile) {
            return;
        }
        try {
            byte[] bytes = FileUtils.readFileToByteArray(imageFile);
            if (null != bytes && ArrayUtils.getLength(bytes) != 0) {
                String imageContent = Base64.getEncoder().encodeToString(bytes);
                showImage(imageContent);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void saveImageToFile() {
        File imageFile = fileChooser.showSaveDialog(null);
        if (null == imageFile) {
            return;
        }
        byte[] bytes = Base64.getDecoder().decode(base64ImageStr);
        try {
            FileUtils.writeByteArrayToFile(imageFile, bytes, false);
        } catch (IOException e) {
            FXHelper.showErrorDialog("save image to file error!");
            System.out.println(e.getMessage());
        }
    }

    private void showImage(String base64Str) {
        if (base64Str == null) {
            return;
        }
        base64ImageStr = base64Str;
        String html = "<img style='max-width:100%; max-height:100%; align:center;' src='data:image;base64," + base64Str + "'/>";
        webView.getEngine().loadContent(html);
    }
}
