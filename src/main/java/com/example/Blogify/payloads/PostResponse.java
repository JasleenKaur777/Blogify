package com.example.Blogify.payloads;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class PostResponse {
private List<PostDTO> content;
private int pageNumber;
private int pageSize;
private int totalElements;
private int totalPages;
private boolean lastPage;
public PostResponse() {
	super();}
public PostResponse(List<PostDTO> content, int pageNumber, int pageSize, int totalElements, int totalPages,
		boolean lastPage) {
	super();
	this.content = content;
	this.pageNumber = pageNumber;
	this.pageSize = pageSize;
	this.totalElements = totalElements;
	this.totalPages = totalPages;
	this.lastPage = lastPage;
}
public List<PostDTO> getContent() {
	return content;
}
public void setContent(List<PostDTO> content) {
	this.content = content;
}
public int getPageNumber() {
	return pageNumber;
}
public void setPageNumber(int pageNumber) {
	this.pageNumber = pageNumber;
}
public int getPageSize() {
	return pageSize;
}
public void setPageSize(int pageSize) {
	this.pageSize = pageSize;
}
public int getTotalElements() {
	return totalElements;
}
public void setTotalElements(int totalElements) {
	this.totalElements = totalElements;
}
public int getTotalPages() {
	return totalPages;
}
public void setTotalPages(int totalPages) {
	this.totalPages = totalPages;
}
public boolean isLastPage() {
	return lastPage;
}
public void setLastPage(boolean lastPage) {
	this.lastPage = lastPage;
}

}
