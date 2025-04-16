package com.example.Blogify.payloads;

public class CommentDTO {
	
private Integer id;

private String content;

private UserDTO user;

private PostDTO posts;

public CommentDTO(Integer id, String content, UserDTO user, PostDTO posts) {
	super();
	this.id = id;
	this.content = content;
	this.user = user;
	this.posts = posts;
}

public CommentDTO() {
	super();
	// TODO Auto-generated constructor stub
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

public UserDTO getUser() {
	return user;
}

public void setUser(UserDTO user) {
	this.user = user;
}

public PostDTO getPosts() {
	return posts;
}

public void setPosts(PostDTO posts) {
	this.posts = posts;
}

}
