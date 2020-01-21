package com.develogical;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.BiFunction;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class QueryProcessor {

    public String process(String query) {
        if (query.toLowerCase().contains("team name")) {
            return "Morning";
        }

        String patternString = ".*what is ([0-9]+) (plus|minus|multiplied by) ([0-9]+)$";
        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(query);
        if (matcher.matches()) {
            Integer first = Integer.parseInt(matcher.group(1));
            String op = matcher.group(2);
            Integer second = Integer.parseInt(matcher.group(3));

            BiFunction<Integer, Integer, Integer> func = op.equals("plus")
                    ? (x, y) -> x + y
                    : op.equals("minus")
                        ? (x, y) -> x - y
                        : (x, y) -> x * y;
            return Integer.toString(func.apply(first, second));
        }

        patternString = ".*what is ([0-9]+) (plus|minus|multiplied by) ([0-9]+) (plus|minus|multiplied by) ([0-9]+)$";
        pattern = Pattern.compile(patternString);
        matcher = pattern.matcher(query);
        if (matcher.matches()) {
            Integer first = Integer.parseInt(matcher.group(1));
            String op1 = matcher.group(2);
            Integer second = Integer.parseInt(matcher.group(3));
            String op2 = matcher.group(4);
            Integer third = Integer.parseInt(matcher.group(5));

            BiFunction<Integer, Integer, Integer> func1 = op1.equals("plus")
                    ? (x, y) -> x + y
                    : op1.equals("minus")
                    ? (x, y) -> x - y
                    : (x, y) -> x * y;
            BiFunction<Integer, Integer, Integer> func2 = op2.equals("plus")
                    ? (x, y) -> x + y
                    : op2.equals("minus")
                    ? (x, y) -> x - y
                    : (x, y) -> x * y;

            return Integer.toString(func2.apply(func1.apply(first, second), third));
        }

        if (query.contains("cube") && query.contains("square")) {
            String numbers = query.substring(query.lastIndexOf(":") + 2);
            List<Integer> nums = new ArrayList<>();
            for (String s : numbers.split(", ")) {
                nums.add(Integer.parseInt(s));
            }
            StringBuilder sb = new StringBuilder();
            for (int num : nums) {
                if (isSquare(num) && isCube(num)) {
                    sb.append(num).append(", ");
                }
            }
            String res = sb.toString();
            return res.endsWith(", ") ? res.substring(0, res.lastIndexOf(",")) : res;
        }

        if (query.toLowerCase().contains("shakespeare")) {
            return "William Shakespeare (26 April 1564 - 23 April 1616) was an " +
                    "English poet, playwright, and actor, widely regarded as the greatest " +
                    "writer in the English language and the world's pre-eminent dramatist.";
        }

        if (query.toLowerCase().contains("which of the following numbers is the largest:")) {
            String numbers = query.substring(query.lastIndexOf(":") + 2);
            List<Integer> numberList = new ArrayList<>();
            for (String s : numbers.split(", ")) {
                try {
                    numberList.add(Integer.parseInt(s));
                } catch (Exception e) {
                    return "";
                }
            }
            return Integer.toString(Collections.max(numberList));
        }

        if (query.toLowerCase().contains("which of the following numbers are primes:")) {
            String numbers = query.substring(query.lastIndexOf(":") + 2);
            return Arrays.stream(numbers.split(", "))
                    .map(Integer::parseInt)
                    .filter(QueryProcessor::isPrime)
                    .map(x -> Integer.toString(x))
                    .collect(Collectors.joining(", "));
        }


        if (query.contains("who played James Bond in the film Dr No")) {
            return "Sean Connery";
        }

        if (query.toLowerCase().contains("what colour is a banana")) {
            return "Yellow";
        }

        if (query.contains("Fibonacci sequence")) {
            String subString = query.substring(query.indexOf('e') + 1);
            String index = subString.substring(1, subString.indexOf("th"));
            return Integer.toString(fib(Integer.parseInt(index)));
        }

        if (query.toLowerCase().contains("theresa may")) {
            return "2016";
        }

        if (query.toLowerCase().contains("eiffel")) {
            return "Paris";
        }

        if (query.toLowerCase().contains("olympics")) {
            return "2020";
        }

        return "";
    }

    public static int fib(int n) {
        if ((n == 0) || (n == 1))
            return n;
        else
            return fib(n - 1) + fib(n - 2);

    }

    private static boolean isPrime(int num) {
        if (num == 0 || num == 1) {
            return false;
        }
        if (num == 2 || num == 3) {
            return true;
        }
        if ((num * num - 1) % 24 == 0) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isSquare(int n) {
        int x = (int) Math.sqrt(n);
        return Math.pow(x,2) == n;
    }

    public static boolean isCube(int n) {
        int x = (int) Math.round(Math.pow(n, 1.0 / 3.0));
        return Math.pow(x, 3) == n;
    }
}
