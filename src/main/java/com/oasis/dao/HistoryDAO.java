package com.oasis.dao;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Component;

import com.oasis.model.History;

@Component
public class HistoryDAO {

	@Autowired
	private JdbcTemplateSupport jdbcTemplate;
	
	private static final String QUERY_HISTORIES = "SELECT * FROM histories WHERE username=? ORDER BY lastTime DESC";
	
	private static final String QUERY_HISTORY = "SELECT * FROM histories WHERE username=? AND bookname=?";
	
	private static final String UPDATE_HISTORY = "UPDATE histories SET lastIndex=?, lastTime=? WHERE username=? AND bookname=?";
	
	private static final String INSERT_HISTORY = "INSERT INTO histories (username, bookname, lastIndex, lastTime) VALUES (?, ?, ?, ?)";
	
	public List<History> queryHistoriesByTime(String username) {
		List<History> histories = jdbcTemplate.getJdbcTemplate().query(QUERY_HISTORIES, new Object[]{username}, new BeanPropertyRowMapper<History>(History.class));
		if (histories == null) return null;
		if (histories.size() == 0) return null;
		return histories;
	}
	
	public History queryHistory(String username, String bookname) {
		List<History> histories = jdbcTemplate.getJdbcTemplate().query(QUERY_HISTORY, new Object[]{username, bookname}, new BeanPropertyRowMapper<History>(History.class));
		if (histories == null) return null;
		if (histories.size() == 0) return null;
		return histories.get(0);
	}
	
	public void update(String username, String bookname, int lastIndex, Timestamp lastTime) {
		jdbcTemplate.getJdbcTemplate().update(UPDATE_HISTORY, lastIndex, lastTime, username, bookname);
	}
	
	public void insert(String username, String bookname, int lastIndex, Timestamp lastTime) {
		jdbcTemplate.getJdbcTemplate().update(INSERT_HISTORY, username, bookname, lastIndex, lastTime);
	}
}
