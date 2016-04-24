package com.uz.laboratory.statistical.validator;


import com.uz.laboratory.statistical.dict.Constants;
import com.uz.laboratory.statistical.filter.RemarkStatisticsFilter;
import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class StatisticsValidator {
    public static List<String> validateSearchValueByFilter(RemarkStatisticsFilter remarkStatisticsFilter, List<String> errorList) {
        if (remarkStatisticsFilter.getSector() == null
                || StringUtils.isNotEmpty(remarkStatisticsFilter.getSector().getTitle())) {
            errorList.add(Constants.SECTOR_IS_NOT_CHOSEN);
        }

        return null;
    }

    private boolean isThisDateValid(String dateToValidate) {
        if (dateToValidate == null) {
            return false;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(Constants.DATE_FORMAT);
        simpleDateFormat.setLenient(false);
        try {
            Date date = simpleDateFormat.parse(dateToValidate);
            System.out.println(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
