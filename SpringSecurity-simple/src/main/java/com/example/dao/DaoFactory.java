package com.example.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DaoFactory {

	@Autowired
	AccountDaoImpl accountDaoImpl;
	
	public AccountDao getAccountDao() {
		return accountDaoImpl;
	}
}
