package controller.tools;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import org.apache.commons.lang3.math.NumberUtils;
import util.DateUtil;
import util.FXHelper;

import java.net.URL;
import java.text.ParseException;
import java.util.Date;
import java.util.ResourceBundle;

public class DateTimeToolsController implements Initializable {
    public static final String fxmlFile = "/fxml/tools/DateTimeTools.fxml";

    private static final String DATE_FORMAT_PARTTEN = DateUtil.FORMAT_YYYY_MM_DD_HH_MI_SS_SSS;

    @FXML
    private TextField dateTime, timeStamp;

    @FXML
    private void changeDate() {
        Date date = null;
        try {
            date = DateUtil.getDateByPattern(dateTime.getText(), DATE_FORMAT_PARTTEN);
        } catch (ParseException e) {
            FXHelper.showWarningDialog("read time error!, cause:" + e.getMessage());
            e.printStackTrace();
            return;
        }
        timeStamp.setText(String.valueOf(date.getTime()));
    }

    @FXML
    private void changeTimestamp() {
        Long timestamp = NumberUtils.toLong(timeStamp.getText());
        Date date = new Date(timestamp);
        dateTime.setText(DateUtil.getSpecifyDate(date, DATE_FORMAT_PARTTEN));
    }

    @FXML
    private void refreshDateTime() {
        dateTime.setText(DateUtil.getSpecifyDate(new Date(), DATE_FORMAT_PARTTEN));
    }

    @FXML
    private void refreshTimestamp() {
        timeStamp.setText(String.valueOf(System.currentTimeMillis()));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dateTime.setPromptText(DATE_FORMAT_PARTTEN);
        dateTime.setText(DateUtil.getSpecifyDate(new Date(), DATE_FORMAT_PARTTEN));
        timeStamp.setPromptText("timestamp(ms)");
    }

}
