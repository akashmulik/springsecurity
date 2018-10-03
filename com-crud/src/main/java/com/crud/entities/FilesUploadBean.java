package com.crud.entities;

import org.springframework.web.multipart.MultipartFile;

public class FilesUploadBean {

	private MultipartFile photo;
	private MultipartFile sign;
	private int id;
	
	public MultipartFile getPhoto() {
		return photo;
	}
	public void setPhoto(MultipartFile photo) {
		this.photo = photo;
	}
	public MultipartFile getSign() {
		return sign;
	}
	public void setSign(MultipartFile sign) {
		this.sign = sign;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
}
