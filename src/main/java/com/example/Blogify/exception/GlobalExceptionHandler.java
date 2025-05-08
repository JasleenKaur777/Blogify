package com.example.Blogify.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException.Forbidden;
import org.springframework.web.client.HttpClientErrorException.Unauthorized;

import com.example.Blogify.payloads.ResponseMsg;

@RestControllerAdvice
@ControllerAdvice
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
	    return new ResponseEntity<>(errors, HttpStatus.UNAUTHORIZED);
	}

	@ExceptionHandler(AccessDeniedException.class)
	public ResponseEntity<ResponseMsg> handleAccessDeniedException(AccessDeniedException ex) {
	    String message = ex.getMessage();
	    ResponseMsg res = new ResponseMsg("Forbidden", message, false);
	    return new ResponseEntity<>(res, HttpStatus.FORBIDDEN);
	}
	@ExceptionHandler(ApiException.class)
	 public ResponseEntity<ResponseMsg> handleApiException(ApiException ex) {
        return new ResponseEntity<>(new ResponseMsg("message",ex.getMessage(), false), HttpStatus.UNAUTHORIZED);
    }
	@ExceptionHandler(Exception.class)
	 public ResponseEntity<ResponseMsg> handleAllException(Exception ex) {
       return new ResponseEntity<>(new ResponseMsg("message",ex.getMessage(), false), HttpStatus.UNAUTHORIZED);
   }

}
