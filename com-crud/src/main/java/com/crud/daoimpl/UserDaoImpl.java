package com.crud.daoimpl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.crud.daoapi.UserDao;
import com.crud.entities.LoginBean;
import com.crud.entities.UserRole;
import com.crud.entities.UsersBean;

@Repository
@Transactional
public class UserDaoImpl implements UserDao{

	@Autowired
	private SessionFactory sessionFactory;
	
	@PersistenceContext
	EntityManager em;
	
	private static final Log LOG = LogFactory.getLog(UserDaoImpl.class);
	
	public boolean createOrUpdate(UsersBean uBean) {
		try {
		sessionFactory.getCurrentSession().persist(uBean);
		sessionFactory.getCurrentSession().persist((new ArrayList<UserRole>(uBean.getUserRoles())).get(0));
		return true;
		}
		catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
public boolean updateUser(UsersBean uBean) {
	try {
		Query query = sessionFactory.getCurrentSession().createQuery("update UsersBean set name= :name, email= :email,"
				+ "mobile= :mob, city= :city where id= :id");
		query.setString("name",uBean.getName());
		query.setString("email", uBean.getEmail());
		query.setString("mob", uBean.getMobile());
		query.setString("city", uBean.getCity());
		query.setLong("id", uBean.getId());
		query.executeUpdate();
		return true;
	} catch (Exception e) {
		e.printStackTrace();
		return false;
	}
}

	public boolean deleteUser(LoginBean uBean) {

		try {
			//first load the bean and then pass it in delete fun
			//UsersBean bean = (UsersBean) sessionFactory.getCurrentSession().load(UsersBean.class, 61);
			//sessionFactory.getCurrentSession().delete(bean);
			Query query = sessionFactory.getCurrentSession().createQuery("delete from UsersBean where email= :email");
			query.setString("email", uBean.getEmail());
			query.executeUpdate();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean checkDuplicateEmail(String email) {

		Query query = sessionFactory.getCurrentSession()
				.createQuery("SELECT COUNT(*) FROM UsersBean bean WHERE bean.email=:email");
		query.setString("email", email);
		int count = (int) (long) (Long) query.uniqueResult();
		if (count == 0)
			return true;

		return false;
	}

	public List<UsersBean> getAllUsers() {
		
		Query query = sessionFactory.getCurrentSession().createQuery("from UsersBean");
		//query.setCacheable(true);
		return query.list();
	}

	public boolean toggleUserStatus(UsersBean bean) {

		try {
			Query query = sessionFactory.getCurrentSession()
					.createQuery("update UsersBean set status =:status where id =:id");
			
			query.setParameter("status", true);
			if (bean.isStatus())
				query.setParameter("status", false);
			query.setParameter("id", bean.getId());
			query.executeUpdate();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	public UsersBean getUserByEmail(String email) {
		
		List<UsersBean> list = new ArrayList<UsersBean>();
		list = sessionFactory.getCurrentSession().createQuery("from UsersBean where email=:email")
				.setParameter("email", email).list();
		
		if(list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	public boolean savePhotoSign(UsersBean bean) {

		try {
			Query query = sessionFactory.getCurrentSession()
					.createQuery("update UsersBean set photo=:photo, sign=:sign " + "where id=:id");
			query.setBinary("photo", bean.getPhoto());
			query.setBinary("sign", bean.getSign());
			query.setParameter("id", bean.getId());
			query.executeUpdate();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
