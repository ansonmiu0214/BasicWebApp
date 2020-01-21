package com.develogical;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class QueryProcessor {

    public String process(String query) {
        if (query.toLowerCase().contains("team name")) {
            return "Morning";
        }


        String patternString = "what is ([0-9]+) plus ([0-9]+)";
        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(query);
        if (matcher.matches()) {
            Integer first = Integer.parseInt(matcher.group(1));
            Integer second = Integer.parseInt(matcher.group(2));
            return Integer.toString(first + second);
        }


        if (query.toLowerCase().contains("shakespeare")) {
            return "William Shakespeare (26 April 1564 - 23 April 1616) was an " +
                    "English poet, playwright, and actor, widely regarded as the greatest " +
                    "writer in the English language and the world's pre-eminent dramatist.";
        }
        return "";
    }
}
