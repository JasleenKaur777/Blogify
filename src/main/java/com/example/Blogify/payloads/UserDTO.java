package com.example.Blogify.payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDTO {
	private Integer id;
	private String name;
	private String password;
	private String email;
	private String about;
	public UserDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UserDTO(Integer id, String name, String password, String email, String about) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.email = email;
		this.about = about;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAbout() {
		return about;
	}
	public void setAbout(String about) {
		this.about = about;
	}
	
}
