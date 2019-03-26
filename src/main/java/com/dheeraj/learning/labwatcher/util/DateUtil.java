package com.dheeraj.learning.labwatcher.util;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DateUtil {
    public static void main(String[] args) {
        getDates("2019-11-11", 10);
    }

    /**
     * Returns list of dates
     *
     * @param startDate
     * @param numberOfPastDays
     * @return
     */
    public static List<String> getDates(String startDate, Integer numberOfPastDays) {
        List<String> list = new ArrayList<>();
        LocalDate date = LocalDate.parse(startDate);

        for(int i=numberOfPastDays;i>0;i--) {
            list.add(date.minusDays(i).toString());
        }
        list.add(date.toString());

        return list;
    }
}
