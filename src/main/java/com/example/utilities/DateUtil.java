package com.example.utilities;

import java.util.HashMap;
import java.util.Map;

/**
 * This class is created because I have not found a correct solution for
 * how to transform dutch short months to date object. This is a workaround
 */
public class DateUtil {

    private DateUtil() {}

    public static String transformDate(String dateString) {
        String[] shortDutchMonthList = {"jan","feb", "maart", "apr", "mei", "juni", "juli", "aug", "sept", "okt", "nov", "dec"};
        Map<String, Integer> dateMap = new HashMap();
        String newDateString = "";

        for (int i = 0; i < shortDutchMonthList.length; i++) {
            dateMap.put(shortDutchMonthList[i], i + 1);
        }

        for (Map.Entry<String, Integer> entry: dateMap.entrySet()) {
            if (dateString.toLowerCase().contains(entry.getKey())) {
                newDateString = dateString.toLowerCase().replace(entry.getKey(), entry.getValue().toString());
            }
        }

        return newDateString;
    }
}
