package com.dheeraj.learning.labwatcher.util;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DateUtil {
    public static void main(String[] args) {
        //getDates("2019-11-11", 10);

        System.out.println(Duration.between(LocalDateTime.parse("2019-06-03T10:10:10"), LocalDate.now().atStartOfDay()));
        System.out.println(LocalDate.now().toString());

        /*LocalDate startDate = LocalDate.now();
        LocalTime nineAM = LocalTime.of(9, 0);
        LocalDateTime todaySpecificTime = LocalDateTime.of(startDate, nineAM);
        System.out.println(todaySpecificTime);
        System.out.println(todaySpecificTime.minusDays(1));*/

        LocalDateTime endDate = LocalDate.now().atStartOfDay();
        LocalDateTime startDate = endDate.minusDays(1);
        System.out.println(endDate);
        System.out.println(startDate);
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

        for (int i = numberOfPastDays; i > 0; i--) {
            list.add(date.minusDays(i).toString());
        }
        list.add(date.toString());

        return list;
    }
}
