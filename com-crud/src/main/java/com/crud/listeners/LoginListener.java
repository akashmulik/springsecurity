package com.crud.listeners;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.InteractiveAuthenticationSuccessEvent;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
@WebListener
public class LoginListener implements ApplicationListener<InteractiveAuthenticationSuccessEvent> {

    @Autowired 
    HttpSession session;
    
    private static final Log log = LogFactory.getLog(LoginListener.class);

	public void onApplicationEvent(InteractiveAuthenticationSuccessEvent event) {
		// TODO Auto-generated method stub
		UserDetails userDetails = (UserDetails) event.getAuthentication().getPrincipal();
		log.info("Log in successful "+userDetails.getUsername());
		
	}
}
