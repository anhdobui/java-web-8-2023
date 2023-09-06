package com.example;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.bean.ErrorResponseBean;
import com.example.customexception.FieldRequiredException;

@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(ArithmeticException.class)
	public ResponseEntity<Object> handleArithmeticException(ArithmeticException ex, WebRequest request){
		ErrorResponseBean result = new ErrorResponseBean();
		result.setError(ex.getMessage());
		List<String> details = new ArrayList<>();
		details.add("khong the chia cho 0");
		result.setDetails(details);
		return new ResponseEntity<>(result,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	@ExceptionHandler(FieldRequiredException.class)
	public ResponseEntity<ErrorResponseBean> handleCityNotFoundException(FieldRequiredException ex,WebRequest request){
		ErrorResponseBean result = new ErrorResponseBean();
		result.setError(ex.getMessage());
		List<String> details = new ArrayList<String>();
		result.setDetails(details);
		return new ResponseEntity<>(result,HttpStatus.BAD_REQUEST);
		
	}
}
