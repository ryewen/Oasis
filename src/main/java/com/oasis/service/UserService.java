package com.oasis.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.oasis.controller.HomeController;
import com.oasis.dao.IdentityDAO;
import com.oasis.model.User;

@Component
public class UserService {

	@Autowired
	private IdentityDAO identityDAO;
	
	public boolean ifLogined(HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute(HomeController.USER_SESSION);
		if (user == null) return false;
		if (user.getUsername() == null) return false;
		return true;
	}
	
	public String getAuthority(String username) {
		User user = identityDAO.queryUser(username);
		if (user == null) return User.NO_EXIST;
		return user.getAuthority();
	}
	
}
