package com.example.Blogify.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "posts")
public class Post {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer post_id;
	@Column(name = "postTitle", length = 100)
	private String postTitle;
	@Column(length = 1000)
	private String content;
	private String imageName;
	private Date added_Date;

	@ManyToOne
	@JoinColumn(name="category_id")
	private Category category;
	@ManyToOne
	@JoinColumn(name="user_id")
	private UserClass user;
	
	@OneToMany(mappedBy = "posts")
	Set<Comment> comments=new HashSet<Comment>();
	
	public Post(String postTitle, String content, String imageName, Date added_Date, Category category, UserClass user,
			Set<Comment> comments) {
		super();
		this.postTitle = postTitle;
		this.content = content;
		this.imageName = imageName;
		this.added_Date = added_Date;
		this.category = category;
		this.user = user;
		this.comments = comments;
	}
	
	public String getPostTitle() {
		return postTitle;
	}

	public void setPostTitle(String postTitle) {
		this.postTitle = postTitle;
	}

	public Set<Comment> getComments() {
		return comments;
	}

	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}

	public Post() {
		super();

	}
	public Post(String postTitle, String content, String imageName, Date added_Date, Category category, UserClass user) {
		super();
		this.postTitle = postTitle;
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
	public UserClass getUser() {
		return user;
	}
	public void setUser(UserClass user) {
		this.user = user;
	}

}
