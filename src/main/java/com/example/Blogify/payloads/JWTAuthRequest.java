package com.example.Blogify.payloads;

public class JWTAuthRequest {
private String email;
private String password;
public JWTAuthRequest() {
	super();
	// TODO Auto-generated constructor stub
}
public JWTAuthRequest(String email, String password) {
	super();
	this.email = email;
	this.password = password;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}

}
