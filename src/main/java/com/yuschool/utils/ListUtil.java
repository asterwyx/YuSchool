package com.yuschool.utils;

import org.springframework.lang.NonNull;

import java.util.ArrayList;
import java.util.List;

public class ListUtil {
    @NonNull
    public static List<Integer> parseIntList(String list) {
        List<Integer> result = new ArrayList<>();
        String[] integers = list.split(",");
        for (String integer : integers) {
            if (!integer.equals("")) {
                result.add(Integer.parseInt(integer));
            }
        }
        return result;
    }
}
