package com.example.utils;

public class StringUtils {
	public static boolean isNullOrEmpty(String value) {
		if(value != null && value.trim() != "")
			return false;
		return true;
	}
	
}
