package com.example.Blogify.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException.Unauthorized;

import com.example.Blogify.payloads.ResponseMsg;

@RestControllerAdvice
public class GlobalExceptionHandler extends RuntimeException {
  
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ResponseMsg> getResourceNotFoundExceptionHandler(ResourceNotFoundException ex){
		String message= ex.getMessage();
		ResponseMsg res=new ResponseMsg("message", message,false);
		return new ResponseEntity<ResponseMsg>(res,HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> handleValidationException(MethodArgumentNotValidException ex) {
	    Map<String, String> errors = new HashMap<>();
	    ex.getBindingResult().getFieldErrors().forEach(error -> 
	        errors.put(error.getField(), error.getDefaultMessage())
	    );
	    return ResponseEntity.badRequest().body(errors);
	}
	@ExceptionHandler(Unauthorized.class)
	public ResponseEntity<ResponseMsg> handleUnauthorizedException(Unauthorized ex) {
	    String message = ex.getMessage();
	    ResponseMsg res = new ResponseMsg("Unauthorized", message, false);
	    return new ResponseEntity<>(res, HttpStatus.UNAUTHORIZED);
	}

}
