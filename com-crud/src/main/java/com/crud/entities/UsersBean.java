package com.crud.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.validator.constraints.Length;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "users")
public class UsersBean implements Serializable{

	/**
	 * To ensure that the class who's obj is serialized is not modified after
	 * serialization - java.io.InvalidClassException
	 */
	private static final long serialVersionUID = 4191084969432733904L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@NotBlank
	@Column(name="name")
	private String name;

	@Column(name="email", unique=true, nullable=false)
	@Email
	private String email;
	
	@Column(name="password", nullable=false)
	@Length(min=5, max=8)
	private String pswd;
	
	@Column(name = "mobile_no")
	@Length(min=10, max=12)
	private String mobile;
	
	@Column(name="city")
	@NotNull
	private String city;
	
	@Column(name="status")
	private boolean status;
	
	@OneToMany(fetch=FetchType.EAGER, mappedBy="user", cascade=CascadeType.ALL)
	@JsonManagedReference
	private Set<UserRole> userRoles = new HashSet<UserRole>();
	
	@Lob
	private byte[] photo;
	
	@Lob
	private byte[] sign;

	//getters and setters
	
	public void addToUserRoles(UserRole userRole) { //bidirectional association
		this.userRoles.add(userRole);
		userRole.setUser(this);
	}
	
	public Set<UserRole> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(Set<UserRole> userRoles) {
		this.userRoles = userRoles;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPswd() {
		return pswd;
	}
	public void setPswd(String pswd) {
		this.pswd = pswd;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
	public byte[] getPhoto() {
		return photo;
	}
	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}
	public byte[] getSign() {
		return sign;
	}
	public void setSign(byte[] sign) {
		this.sign = sign;
	}
}
