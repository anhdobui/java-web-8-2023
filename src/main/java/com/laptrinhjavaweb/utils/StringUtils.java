package com.laptrinhjavaweb.utils;

public class StringUtils {
    public static boolean isNullOrEmpty(String value) {
        if(value != null && !value.trim().equals(""))
            return false;
        return true;
    }

}