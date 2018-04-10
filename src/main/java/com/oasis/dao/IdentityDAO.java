package com.oasis.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Component;

import com.oasis.model.User;

@Component
public class IdentityDAO {

	@Autowired
	private JdbcTemplateSupport jdbcTemplate;
	
	private static final String QUERY_USER = "SELECT * FROM users WHERE username=?";
	
	private static final String INSERT_USER = "INSERT INTO users (username, password, authority) VALUES (?,?,?)";
	
	public User queryUser(String username) {
		List<User> users = jdbcTemplate.getJdbcTemplate().query(QUERY_USER, new Object[]{username}, new BeanPropertyRowMapper<User>(User.class));
		if (users == null) return null;
		if (users.size() == 0) return null;
		return users.get(0);
	}
	
	public void insertUser(String username, String password, String authority) {
		jdbcTemplate.getJdbcTemplate().update(INSERT_USER, username, password, authority);
	}
}
