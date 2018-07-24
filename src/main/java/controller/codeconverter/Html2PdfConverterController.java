package controller.codeconverter;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import util.FXHelper;
import util.ITextUtil;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Html2PdfConverterController implements Initializable {
    public static final String fxmlFile = "/fxml/codeconverter/Html2PdfConverter.fxml";

    @FXML
    private TextArea txtHtmlContent;
    @FXML
    private ChoiceBox cbSource;

    //文件选择器
    private final FileChooser fileChooser = new FileChooser();


    @FXML
    private void convert() {
        //将textArea中的html文档转换成pdf文件
        String htmlContent;
        if(Source.textField.equals(cbSource.getValue())){
            htmlContent = txtHtmlContent.getText();
        }else{
            File htmlFile = fileChooser.showOpenDialog(null);
            if (htmlFile == null) {
                FXHelper.showWarningDialog("Please choose a File!");
                return;
            } else {
                try {
                    htmlContent = FileUtils.readFileToString(htmlFile, "UTF-8");
                } catch (IOException e) {
                    e.printStackTrace();
                    htmlContent = null;
                }
            }
        }
        if (StringUtils.isEmpty(htmlContent)) {
            FXHelper.showWarningDialog("convert to pdf error, html content is empty or cannot open html file!");
            return;
        }
        File pdfFile = fileChooser.showSaveDialog(null);
        if (pdfFile != null) {
            ITextUtil.exportPdf(htmlContent, pdfFile);
            FXHelper.showInfoDialog("convert to pdf complete!");
        } else {
            FXHelper.showWarningDialog("please choose a location to save pdf file.");
        }

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
