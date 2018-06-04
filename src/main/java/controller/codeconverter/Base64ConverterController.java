package controller.codeconverter;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import util.FXHelper;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class Base64ConverterController {
    public static final String fxmlFile = "/fxml/codeconverter/Base64Converter.fxml";

    @FXML
    private TextArea txtOrigin, txtEncoded;


    @FXML
    private void encodeBase64() {
        String originText = txtOrigin.getText();

        if (originText == null || "".equals(originText.trim())) {
            FXHelper.showInfoDialog("please input sth.");
            txtOrigin.requestFocus();
            return;
        }

        String encodedText = null;
        try {
            encodedText = Base64.getEncoder().encodeToString(originText.getBytes());
        } catch (Exception e) {
            FXHelper.showErrorDialog("fail to converter text.\n msg:" + e.getMessage());
        }
        txtEncoded.setText(encodedText);
    }

    @FXML
    private void decodeBase64() {
        String originText = txtEncoded.getText();

        if (originText == null || "".equals(originText.trim())) {
            FXHelper.showInfoDialog("please input sth.");
            txtEncoded.requestFocus();
            return;
        }

        String decodeText = null;
        try {
            decodeText = new String(Base64.getDecoder().decode(originText.getBytes()), StandardCharsets.UTF_8);
        } catch (Exception e) {
            FXHelper.showErrorDialog("fail to converter text.\n msg:" + e.getMessage());
        }
        txtOrigin.setText(decodeText);
    }
}
