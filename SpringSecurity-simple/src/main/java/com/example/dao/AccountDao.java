package com.example.dao;

import java.util.List;

import com.example.domain.Account;

public interface AccountDao {

	public Account findOne(String id);
	public List<Account> findAll();
	public int create(Account account);
	public int update(Account account);
	public int delete(String id);
}
