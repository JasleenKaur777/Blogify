package com.example.Blogify.entities;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "posts")
public class Post {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer post_id;
	@Column(name = "post_title", length = 100, nullable = false)
	private String post_title;
	@Column(length = 1000)
	private String content;
	private String imageName;
	private Date added_Date;

	@ManyToOne
	@JoinColumn(name="category_id")
	private Category category;
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	public Post() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Post(String post_title, String content, String imageName, Date added_Date, Category category, User user) {
		super();
		this.post_title = post_title;
		this.content = content;
		this.imageName = imageName;
		this.added_Date = added_Date;
		this.category = category;
		this.user = user;
	}
	public Integer getPost_id() {
		return post_id;
	}
	public void setPost_id(Integer post_id) {
		this.post_id = post_id;
	}
	public String getPost_Title() {
		return post_title;
	}
	public void setPost_Title(String post_title) {
		this.post_title = post_title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getImageName() {
		return imageName;
	}
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	public Date getAdded_Date() {
		return added_Date;
	}
	public void setAdded_Date(Date added_Date) {
		this.added_Date = added_Date;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}

}
