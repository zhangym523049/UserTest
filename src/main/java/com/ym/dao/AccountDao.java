package com.ym.dao;

import com.ym.bean.Account;

public interface AccountDao {
	
	Account getAccountById(int id);
	
	Account getAccountByInfo(Account account);
	
	int insertDefaultInfo(Account account);
	
	int updateAccountById(Account account,int id);
	
	int cancelAccountById(int id);
	
}
