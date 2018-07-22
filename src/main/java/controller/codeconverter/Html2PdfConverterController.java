package controller.codeconverter;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.FileUtils;
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
        if(Source.textField.equals(cbSource.getValue())){

        }else{

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
