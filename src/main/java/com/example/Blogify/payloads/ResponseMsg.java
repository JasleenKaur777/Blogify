package com.example.Blogify.payloads;

public class ResponseMsg {
String message;
String value;
Boolean success;
public ResponseMsg(String message, String value,Boolean success) {
	super();
	this.message = message;
	this.value = value;
	this.success=success;
}
public ResponseMsg() {
	super();
	// TODO Auto-generated constructor stub
}
public Boolean getSuccess() {
	return success;
}
public void setSuccess(Boolean success) {
	this.success = success;
}
public String getMessage() {
	return message;
}
public void setMessage(String message) {
	this.message = message;
}
public String getValue() {
	return value;
}
public void setValue(String value) {
	this.value = value;
}

}
