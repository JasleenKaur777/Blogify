package com.example.Blogify.entities;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "user_name", nullable = false, length = 100)
	private String name;
	private String password;
	private String email;
	private String about;
	@OneToMany(mappedBy = "user")
	List<Post> posts;
	
	@OneToMany(mappedBy = "user")
	Set<Comment> comments=new HashSet<Comment>();
	
	public Set<Comment> getComments() {
		return comments;
	}
	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}
	public User(String name, String password, String email, String about, List<Post> posts, Set<Comment> comments) {
		super();
		this.name = name;
		this.password = password;
		this.email = email;
		this.about = about;
		this.posts = posts;
		this.comments = comments;
	}
	public User() {
		super();
	}
	public User(Integer id, String name, String password, String email, String about) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.email = email;
		this.about = about;
	}
	
	public User(String name, String password, String email, String about, List<Post> posts) {
		super();
		this.name = name;
		this.password = password;
		this.email = email;
		this.about = about;
		this.posts = posts;
	}
	
	public List<Post> getPosts() {
		return posts;
	}
	public void setPosts(List<Post> posts) {
		this.posts = posts;
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
