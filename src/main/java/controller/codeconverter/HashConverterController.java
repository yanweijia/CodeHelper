package controller.codeconverter;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.apache.commons.codec.digest.DigestUtils;

public class HashConverterController {
    public static final String fxmlFile = "/fxml/codeconverter/HashConverter.fxml";

    @FXML
    private TextField txtMD5, txtSHA1;
    @FXML
    private ChoiceBox cbSource;
    @FXML
    private TextArea txtOrigin;


    @FXML
    private void compute() {
        byte[] originBytes = txtOrigin.getText().getBytes();
        String md5 = DigestUtils.md5Hex(originBytes);
        String sha1 = DigestUtils.sha1Hex(originBytes);
        txtMD5.setText(md5);
        txtSHA1.setText(sha1);

    }
}
