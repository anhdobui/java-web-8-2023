package com.example.customexception;

public class FieldRequiredException extends RuntimeException{
	public FieldRequiredException(String erorMessage) {
		super(erorMessage);
	}
}
