package com.example.controlleradvice;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.customexception.FieldRequiredException;
import com.example.model.ErrorResponseDTO;

@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(FieldRequiredException.class)
	public ResponseEntity<ErrorResponseDTO> handleCityNotFoundException(FieldRequiredException ex,WebRequest request){
		ErrorResponseDTO result = new ErrorResponseDTO();
		result.setError(ex.getMessage());
		List<String> details = new ArrayList<String>();
		result.setDetails(details);
		return new ResponseEntity<>(result,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponseDTO> handleCityNotFoundException(Exception ex,WebRequest request){
		ErrorResponseDTO result = new ErrorResponseDTO();
		result.setError(ex.getMessage());
		List<String> details = new ArrayList<String>();
		result.setDetails(details);
		return new ResponseEntity<>(result,HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
