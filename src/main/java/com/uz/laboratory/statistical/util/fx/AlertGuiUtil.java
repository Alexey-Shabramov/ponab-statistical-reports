package com.uz.laboratory.statistical.util.fx;

import java.util.List;


public class AlertGuiUtil {
    private static StringBuilder stringBuilder = new StringBuilder();

    public static String prepareAlertMessage(List<String> errorList) {
        for (String error : errorList) {
            stringBuilder.append("- ")
                    .append(error)
                    .append(" ")
                    .append("\n");
        }
        String preparedString = stringBuilder.toString();
        stringBuilder.setLength(0);
        errorList.clear();
        return preparedString;
    }
}
