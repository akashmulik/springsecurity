package com.crud.servicesimpl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.crud.daoapi.UserDao;
import com.crud.entities.LoginBean;
import com.crud.entities.UserRole;
import com.crud.entities.UsersBean;
import com.crud.servicesapi.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserDao userDao;
	
	@Autowired
	MailSender mailSender;
	
	static final private Log log = LogFactory.getLog(UserServiceImpl.class);

	//create user
	public boolean createOrUpdate(UsersBean uBean) {

		// using apache commons lang dependency
		// String generatedString = RandomStringUtils.randomAlphabetic(10);

		// generate random password string
		final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		int count = 5;
		StringBuilder builder = new StringBuilder();
		while (count-- != 0) {
			int character = (int) (Math.random() * ALPHA_NUMERIC_STRING.length());
			builder.append(ALPHA_NUMERIC_STRING.charAt(character));
		}
		log.info(builder);
		uBean.setPswd(builder.toString());
		uBean.setStatus(true); //user's status will be active by default when user signs up
		UserRole role = new UserRole("USER");
		uBean.addToUserRoles(role); //setting role USER for new user by default
		
		if (userDao.createOrUpdate(uBean)) {
			SimpleMailMessage mail = constructMailForUserSignup(uBean);
			send(mail);
			return true;
		}
		return false;
	}
	
	@Async
	void send(SimpleMailMessage email) {
		try {
			mailSender.send(email);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private SimpleMailMessage constructMailForUserSignup(UsersBean userBean) {
		//construct simple mail 
		//(to create MIME email, use MimeMessagePreparator, MimeMessageHelper & MimeMailMessage classes)
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(userBean.getEmail());
		mail.setSubject("Spring MVC - Signup successful");
		mail.setText("Dear "+userBean.getName()+", You have signed up to Spring MVC app by Akash Mulik. "
				+ "Your primary password is: "+userBean.getPswd());
		return mail;
	}

	public boolean updateUser(UsersBean uBean) {

		return userDao.updateUser(uBean);
	}

	public boolean checkDuplicateEmail(String email) {

		return userDao.checkDuplicateEmail(email);
	}

	public List<UsersBean> getAllUsers() {

		return userDao.getAllUsers();
	}

	public boolean deleteUser(LoginBean bean) {

		return userDao.deleteUser(bean);
	}

	public boolean toggleUserStatus(UsersBean bean) {
		return userDao.toggleUserStatus(bean);
	}

	public boolean savePhotoSign(UsersBean bean) {
		return userDao.savePhotoSign(bean);
	}

	public UsersBean getUserByEmail(String email) {
		return userDao.getUserByEmail(email);
	}
}
