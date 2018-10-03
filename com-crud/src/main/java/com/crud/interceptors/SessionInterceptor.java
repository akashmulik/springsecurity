package com.crud.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class SessionInterceptor extends HandlerInterceptorAdapter {

	private final Log log = LogFactory.getLog(this.getClass());

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
			HttpSession session = request.getSession(false);
			log.info(request.getRequestURI());
			log.info(request.getAttribute("aa"));
			
			if(session == null) {
				log.info("SESSION IS NULL");
				response.sendRedirect("login?invalid");
				return false;
			}
			if(session.getAttribute("userID") == null) {
				log.info("SESSION ID NULL");
				session.invalidate();
				response.sendRedirect("login?invalid");
				return false;
			} else {
				log.info("USER ID --->>> "+session.getAttribute("userID"));
				return true;
			}
	}
}
