package com.crud.servicesimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.crud.daoapi.UserDao;
import com.crud.entities.UserRole;
import com.crud.entities.UsersBean;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	UserDao userDao;
	
	protected final static Log log = LogFactory.getLog(CustomUserDetailsService.class);
	
	@Transactional(readOnly=true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		UsersBean user = userDao.getUserByEmail(username);
		if(user == null){
            log.info("***User not found***");
            throw new UsernameNotFoundException("Username not found");
        }
		
		List<GrantedAuthority> authorities = getGrantedAuthorities(user.getUserRoles());
		boolean isEnabled = user.isStatus();
		return new User(user.getEmail(), user.getPswd(), isEnabled,
				true, true, true, authorities);
	}
	

	private List<GrantedAuthority> getGrantedAuthorities(Set<UserRole> roles){
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
         
        for(UserRole userProfile : roles){
            authorities.add(new SimpleGrantedAuthority("ROLE_"+userProfile.getRole()));
        }
        return authorities;
    }
}
