package com.oasis.service;

import java.sql.Timestamp;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.oasis.controller.HomeController;
import com.oasis.dao.HistoryDAO;
import com.oasis.model.History;
import com.oasis.model.User;

@Component
public class HistoryService {

	@Autowired
	HistoryDAO historyDAO;
	
	@Autowired
	UserService userService;
	
	public void updateHistory(HttpServletRequest request, String bookname, int lastIndex) {
		String username = ((User) request.getSession().getAttribute(HomeController.USER_SESSION)).getUsername();
		History history = historyDAO.queryHistory(username, bookname);
		if (history == null)
			historyDAO.insert(username, bookname, lastIndex, new Timestamp(new Date().getTime()));
		else
			historyDAO.update(username, bookname, lastIndex, new Timestamp(new Date().getTime()));
	}
}
