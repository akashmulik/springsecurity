package com.crud.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name="user_roles")
public class UserRole {
	
	@Id
	@Column(name="role_id", nullable=false)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int roleId;
	
	@Column(name="role", nullable=false)
	private String role;

	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="user_id", nullable=false)
	@JsonBackReference
	private UsersBean user;
	
	//constructors
	public UserRole() {}
	
	public UserRole(String role) {
		this.role = role;
	}
	
	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public UsersBean getUser() {
		return user;
	}

	public void setUser(UsersBean user) {
		this.user = user;
	}

}
