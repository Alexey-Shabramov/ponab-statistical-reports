package com.uz.laboratory.statistical.util;


import org.eclipse.collections.impl.list.Interval;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Month;
import java.util.Calendar;
import java.util.List;

public class DateUtil {
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

    private int getMonthNumber(String monthName) {
        return Month.valueOf(monthName.toUpperCase()).getValue();
    }
}
