package com.example.Blogify.payloads;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class CategoryDTO {
private Integer category_id;
@NotEmpty
@Size(min=4 ,message="minimum size of title is 4")
private String title;
@NotEmpty
@Size(min=10,message="minimum size of description is 10" )
private String description;
public CategoryDTO() {
	super();
	// TODO Auto-generated constructor stub
}
public CategoryDTO( String title, String description) {
	super();
	
	this.title = title;
	this.description = description;
}
public Integer getCategory_id() {
	return category_id;
}
public void setCategory_id(Integer category_id) {
	this.category_id = category_id;
}
public String getTitle() {
	return title;
}
public void setTitle(String title) {
	this.title = title;
}
public String getDescription() {
	return description;
}
public void setDescription(String description) {
	this.description = description;
}

}
