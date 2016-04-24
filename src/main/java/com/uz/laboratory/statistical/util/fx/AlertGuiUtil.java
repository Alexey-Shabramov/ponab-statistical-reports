package com.uz.laboratory.statistical.util.fx;

import com.uz.laboratory.statistical.dict.Constants;
import javafx.scene.control.Alert;

import java.util.List;


public class AlertGuiUtil {
    private static StringBuilder stringBuilder = new StringBuilder();

    public static void prepareAlertMessage(List<String> errorList) {
        for (String error : errorList) {
            stringBuilder.append("- ")
                    .append(error)
                    .append(" ")
                    .append("\n");
        }
        String preparedString = stringBuilder.toString();
        stringBuilder.setLength(0);
        errorList.clear();
        createAlert(preparedString);
    }

    private static void createAlert(String error) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(Constants.ERROR_TITLE);
        alert.setHeaderText(Constants.ERROR_HEADER);
        alert.setContentText(error);
        alert.showAndWait();
        alert = null;
    }
}
