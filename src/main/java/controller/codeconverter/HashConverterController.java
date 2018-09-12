package controller.codeconverter;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HashConverterController implements Initializable {
    public static final String fxmlFile = "/fxml/codeconverter/HashConverter.fxml";

    @FXML
    private TextArea txtHash;
    @FXML
    private ChoiceBox cbSource;
    @FXML
    private TextArea txtOrigin;
    //文件选择器
    private final FileChooser fileChooser = new FileChooser();


    @FXML
    private void compute() {
        byte[] originBytes;
        StringBuilder result = new StringBuilder();
        if (cbSource.getValue().equals(Source.textField)) {
            originBytes = txtOrigin.getText().getBytes();
        } else {
            try {
                //选择 originFlie
                File originFile = fileChooser.showOpenDialog(null);
                if (originFile == null) {
                    showResult("Please choice one File!");
                    return;
                }
                originBytes = FileUtils.readFileToByteArray(originFile);
            } catch (IOException e) {
                showResult("failed to read file!");
                return;
            }
        }

        String md5 = DigestUtils.md5Hex(originBytes);
        String sha1 = DigestUtils.sha1Hex(originBytes);
        String sha256 = DigestUtils.sha256Hex(originBytes);
        String sha384 = DigestUtils.sha384Hex(originBytes);
        String sha512 = DigestUtils.sha512Hex(originBytes);
        result.append("MD5: ").append(md5)
                .append("\n").append("SHA1: ").append(sha1)
                .append("\n").append("SHA256: ").append(sha256)
                .append("\n").append("SHA384: ").append(sha384)
                .append("\n").append("SHA512: ").append(sha512);
        showResult(result.toString());
    }

    private void showResult(String result) {
        txtHash.setText(result);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cbSource.getItems().setAll(Source.values());
        cbSource.setValue(Source.textField);
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
