package com.crud.listeners;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@WebListener
public class SessionListener implements HttpSessionListener {

	private final static Log log = LogFactory.getLog(SessionListener.class);
	
	public void sessionCreated(HttpSessionEvent se) {
		
		log.info("Session created");
	}
	
public void sessionDestroyed(HttpSessionEvent se) {
		
		log.info("Session destroyed");
	}
}
