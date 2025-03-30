package com.example.Blogify.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.Blogify.payloads.ResponseMsg;

@RestControllerAdvice
public class GlobalExceptionHandler extends RuntimeException {
  
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ExceptionHandler
	public ResponseEntity<ResponseMsg> getResourceNotFoundExceptionHandler(ResourceNotFoundException ex){
		String message= ex.getMessage();
		ResponseMsg res=new ResponseMsg("message", message,false);
		return new ResponseEntity<ResponseMsg>(res,HttpStatus.NOT_FOUND);
	}
}
