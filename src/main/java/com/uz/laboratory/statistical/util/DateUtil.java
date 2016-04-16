package com.uz.laboratory.statistical.util;


import org.apache.commons.lang3.StringUtils;
import org.eclipse.collections.impl.list.Interval;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class DateUtil {
    private static StringBuilder stringBuilder = new StringBuilder();

    public static Integer getMonthNumberByTitle(String title) throws ParseException {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new SimpleDateFormat("MMMM").parse(title));
        return calendar.get(Calendar.MONTH);
    }

    public static List<Integer> getDayListByMonthYear(int year, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, 1);
        return Interval.oneTo(calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
    }

    public static String dateBuilder(Integer year, String month, Integer day) throws ParseException {
        if (day != null) {
            if (day.toString().length() < 2) {
                stringBuilder
                        .append("0")
                        .append(day)
                        .append("-");
            } else {
                stringBuilder
                        .append(day)
                        .append("-");
            }
        }
        if (StringUtils.isNotBlank(month)) {
            if (Integer.toString(getMonthNumberByTitle(month).intValue() + 1).length() < 2) {
                stringBuilder
                        .append("0")
                        .append(getMonthNumberByTitle(month) + 1)
                        .append("-");
            } else {
                stringBuilder
                        .append(getMonthNumberByTitle(month) + 1)
                        .append("-");
            }
        }
        if (year != null) {
            stringBuilder.append(year);
        }
        String buildedDate = stringBuilder.toString();
        stringBuilder.setLength(0);
        return buildedDate;
    }
}
