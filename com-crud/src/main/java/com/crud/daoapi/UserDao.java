package com.crud.daoapi;

import java.util.List;

import com.crud.entities.LoginBean;
import com.crud.entities.UsersBean;

public interface UserDao {

	public boolean createOrUpdate(UsersBean uBean);

	public boolean deleteUser(LoginBean bean);

	public boolean checkDuplicateEmail(String email);

	public List<UsersBean> getAllUsers();

	public boolean updateUser(UsersBean uBean);

	public boolean toggleUserStatus(UsersBean bean);
	
	public UsersBean getUserByEmail(String email);

	public boolean savePhotoSign(UsersBean bean);
}
