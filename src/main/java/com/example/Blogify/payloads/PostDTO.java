package com.example.Blogify.payloads;

import java.util.Date;

public class PostDTO {
	private Integer post_id;
private String post_title;
private String content;
private String imageName;
private Date addedDate;
private CategoryDTO category;
private UserDTO user;
public PostDTO() {
	super();
	// TODO Auto-generated constructor stub
}
public PostDTO(String post_title, String content, String imageName, Date addedDate, CategoryDTO category,
		UserDTO user) {
	super();
	this.post_title = post_title;
	this.content = content;
	this.imageName = imageName;
	this.addedDate = addedDate;
	this.category = category;
	this.user = user;
}
public String getPost_title() {
	return post_title;
}
public void setPost_title(String post_title) {
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
public Date getAddedDate() {
	return addedDate;
}
public void setAddedDate(Date addedDate) {
	this.addedDate = addedDate;
}
public CategoryDTO getCategory() {
	return category;
}
public void setCategory(CategoryDTO category) {
	this.category = category;
}
public UserDTO getUser() {
	return user;
}
public void setUser(UserDTO user) {
	this.user = user;
}
public Integer getPost_id() {
	return post_id;
}
public void setPost_id(Integer post_id) {
	this.post_id = post_id;
}


}
