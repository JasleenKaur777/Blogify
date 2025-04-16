package com.example.Blogify.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="comments")
public class Comment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
private Integer id;
@Column(nullable = false)
private String content;

@ManyToOne
private User user;
@ManyToOne
private Post posts;
public Comment() {
	super();
	
}
public Comment(String content, User user, Post posts) {
	super();
	this.content = content;
	this.user = user;
	this.posts = posts;
}
public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
}
public String getContent() {
	return content;
}
public void setContent(String content) {
	this.content = content;
}
public User getUser() {
	return user;
}
public void setUser(User user) {
	this.user = user;
}
public Post getPosts() {
	return posts;
}
public void setPosts(Post posts) {
	this.posts = posts;
}

}
