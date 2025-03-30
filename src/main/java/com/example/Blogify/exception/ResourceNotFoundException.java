package com.example.Blogify.exception;

public class ResourceNotFoundException extends RuntimeException {
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
String resourceName;
String resourceField;
Integer fieldName;
public ResourceNotFoundException(String resourceName, String resourceField, Integer fieldName ) {
	super(String.format("%s not found with %s : '%s'", resourceName, resourceField, fieldName));
	this.resourceName = resourceName;
	this.resourceField = resourceField;
	this.fieldName = fieldName;
}
public String getResourceName() {
	return resourceName;
}
public void setResourceName(String resourceName) {
	this.resourceName = resourceName;
}
public String getResourceField() {
	return resourceField;
}
public void setResourceField(String resourceField) {
	this.resourceField = resourceField;
}
public Integer getFieldName() {
	return fieldName;
}
public void setFieldName(Integer fieldName) {
	this.fieldName = fieldName;
}

}
