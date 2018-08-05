package controller.tools;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;

import java.net.URL;
import java.util.ResourceBundle;

public class DateTimeToolsController implements Initializable {
    public static final String fxmlFile = "/fxml/tools/DateTimeTools.fxml";

    @FXML
    private DatePicker datePicker;

    @FXML
    private void changeDate() {
        System.out.println(datePicker.getChronology().dateNow().toString());
        System.out.println(datePicker.getChronology().toString());

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        datePicker.setShowWeekNumbers(true);
    }

}
