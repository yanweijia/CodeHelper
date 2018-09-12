package controller.codeconverter;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import org.apache.commons.io.FileUtils;
import util.FXHelper;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.ResourceBundle;

public class Base64ConverterController implements Initializable {
    public static final String fxmlFile = "/fxml/codeconverter/Base64Converter.fxml";


    @FXML
    private TextArea txtOrigin, txtEncoded;

    @FXML
    private ChoiceBox<Source> cbEncodeFrom, cbEncodeTo, cbDecodeFrom, cbDecodeTo;

    //文件选择器
    private final FileChooser fileChooser = new FileChooser();

    @FXML
    private void encodeBase64() throws IOException {
        //读取待加密字串
        byte[] originBytes = readOrigin(cbEncodeFrom, txtOrigin);

        if (originBytes == null || originBytes.length == 0) {
            FXHelper.showInfoDialog("Text to Encode is EMPTY or ReadFile Err");
            return;
        }

        byte[] targetBytes = null;
        try {
            targetBytes = Base64.getEncoder().encode(originBytes);
        } catch (Exception e) {
            FXHelper.showErrorDialog("Fail to converter text.\n msg:" + e.getMessage());
        }
        //转换后
        if (writeToTarget(cbEncodeTo, txtEncoded, targetBytes)) {
            FXHelper.showInfoDialog("convert complete!");
        } else {
            FXHelper.showErrorDialog("Convert Wrong!");
        }

    }

    @FXML
    private void decodeBase64() {
        //读取待解密字串
        byte[] originBytes = readOrigin(cbDecodeFrom, txtEncoded);

        if (originBytes == null || originBytes.length == 0) {
            FXHelper.showInfoDialog("Text to Decode is EMPTY or ReadFile Err");
            return;
        }

        byte[] targetBytes = null;
        try {
            targetBytes = Base64.getDecoder().decode(originBytes);
        } catch (Exception e) {
            FXHelper.showErrorDialog("Fail to converter text.\n msg:" + e.getMessage());
        }

        if (writeToTarget(cbDecodeTo, txtOrigin, targetBytes)) {
            FXHelper.showInfoDialog("convert complete!");
        } else {
            FXHelper.showErrorDialog("Convert Wrong!");
        }
    }

    /**
     * 读取待加密字串,从文件或界面
     *
     * @return
     */
    private byte[] readOrigin(ChoiceBox<Source> cbFrom, TextArea txtField) {
        if (cbFrom.getValue().equals(Source.textField)) {
            return txtField.getText().getBytes();
        } else {
            try {
                //选择 originFlie
                File originFile = fileChooser.showOpenDialog(null);
                if (originFile == null) {
                    FXHelper.showWarningDialog("Please choice one File!");
                    return null;
                }
                return FileUtils.readFileToByteArray(originFile);
            } catch (IOException e) {
                FXHelper.showErrorDialog("failed to read file!\n" + e.getMessage());
                return null;
            }
        }
    }

    /**
     * 将目标信息写入到文本框或文件
     *
     * @return
     */
    private boolean writeToTarget(ChoiceBox<Source> cbTo, TextArea txtField, byte[] bytes) {
        if (cbTo.getValue().equals(Source.textField)) {
            txtField.setText(new String(bytes, StandardCharsets.UTF_8));
            return true;
        } else {
            try {
                //选择保存的 targetFile
                File targetFile = fileChooser.showSaveDialog(null);
                if (targetFile == null) {
                    FXHelper.showWarningDialog("Please choice where to save.");
                    return false;
                }
                FileUtils.writeByteArrayToFile(targetFile, bytes);
                return true;
            } catch (IOException e) {
                FXHelper.showErrorDialog("write to target error." + e.getMessage());
            }
        }
        return false;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initCheckBox();
    }

    private void initCheckBox() {
        cbEncodeFrom.getItems().setAll(Source.values());
        cbDecodeFrom.getItems().setAll(Source.values());
        cbDecodeTo.getItems().setAll(Source.values());
        cbEncodeTo.getItems().setAll(Source.values());

        cbEncodeFrom.setValue(Source.textField);
        cbEncodeTo.setValue(Source.textField);
        cbDecodeTo.setValue(Source.textField);
        cbDecodeFrom.setValue(Source.textField);
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
