package com.example.Blogify.entities;

import java.util.HashSet;
import java.util.Set;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class Role {
	@Id
private Integer id;
private String name;
@ManyToMany(mappedBy = "roles")
private Set<UserClass> users=new HashSet<UserClass>();
public Role() {
	super();
	// TODO Auto-generated constructor stub
}
public Role(Integer id, String name, Set<UserClass> users) {
	super();
	this.id = id;
	this.name = name;
	this.users = users;
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
public Set<UserClass> getUsers() {
	return users;
}
public void setUsers(Set<UserClass> users) {
	this.users = users;
}


}
