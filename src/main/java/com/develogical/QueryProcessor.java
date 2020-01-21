package com.develogical;

import java.util.ArrayList;
import java.util.List;

public class QueryProcessor {

    public String process(String query) {
        if (query.toLowerCase().contains("team name")) {
            return "Morning";
        }

        if (query.toLowerCase().contains("shakespeare")) {
            return "William Shakespeare (26 April 1564 - 23 April 1616) was an " +
                    "English poet, playwright, and actor, widely regarded as the greatest " +
                    "writer in the English language and the world's pre-eminent dramatist.";
        }

        if (query.toLowerCase().contains("which of the% following numbers is the largest:")) {
            String numbers = query.substring(query.lastIndexOf(":") + 1);
            List<Integer> numberList = new ArrayList<>();
            for (String s : numbers.split(", ")) {
                numberList.add(Integer.parseInt(s));
            }

        }
        return "";
    }
}
