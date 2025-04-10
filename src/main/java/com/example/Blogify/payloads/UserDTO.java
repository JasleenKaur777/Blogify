package com.example.Blogify.payloads;



import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDTO {
	
	private Integer id;
	@NotBlank(message = "Name cannot be empty")
	@Size(min = 4, message = "Username must contain a minimum of 4 characters")
	private String name;
	@NotBlank(message = "Password cannot be empty")
	@Size(min = 8, max = 20, message = "Password must be between 8 and 20 characters")
	@Pattern(
	    regexp = "^(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,20}$",
	    message = "Password must be 8-20 characters long, include at least one uppercase letter, one number, and one special character"
	)
	private String password;
	@NotNull(message = "Email cannot be null")
	@Email(message = "Email should be in proper format")
	private String email;
	@NotBlank(message = "About section cannot be empty")
	private String about;
	public UserDTO() {
		super();
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
