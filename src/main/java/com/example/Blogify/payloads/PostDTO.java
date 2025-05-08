package com.example.Blogify.payloads;

import java.util.Date;

public class PostDTO {
	private Integer post_id;
private String postTitle;
private String content;
private String imageName;
private Date addedDate;
private CategoryDTO category;
private UserDTO user;
public PostDTO() {
	super();
	
}
public PostDTO(String postTitle, String content, String imageName, Date addedDate, CategoryDTO category,
		UserDTO user) {
	super();
	this.postTitle = postTitle;
	this.content = content;
	this.imageName = imageName;
	this.addedDate = addedDate;
	this.category = category;
	this.user = user;
}
public String getPostTitle() {
	return postTitle;
}
public void setPostTitle(String postTitle) {
	this.postTitle = postTitle;
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
