package com.example.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.example.domain.Account;

@Component
public class AccountDaoImpl implements AccountDao{

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	private static RowMapper<Account> rowMapper = new BeanPropertyRowMapper<Account>(Account.class);
	
	@Override
	public Account findOne(String id) {
		var sql = "select * from spring where id = ?";
		return jdbcTemplate.queryForObject(sql, rowMapper, id);
	}

	@Override
	public List<Account> findAll() {
		var sql = "select * from spring";
		return jdbcTemplate.query(sql, rowMapper);
	}

	@Override
	public int create(Account account) {
		var sql = "insert into spring(id, name, password, admin, enabled) values(?, ?, ?, ?, ?);";
		return jdbcTemplate.update(sql, account.getId(), account.getName(), account.getPassword()
				,account.isAdmin(), account.isEnabled());
	}

	@Override
	public int update(Account account) {
		var sql = "update spring set name = ?, password = ?, admin = ?, enabled = ? where id = ?";
		return jdbcTemplate.update(sql, account.getName(), account.getPassword(), 
				account.isAdmin(), account.isEnabled(), account.getId());
	}

	@Override
	public int delete(String id) {
		var sql = "delete from spring where id = ?";
		return jdbcTemplate.update(sql, id);
	}

}
