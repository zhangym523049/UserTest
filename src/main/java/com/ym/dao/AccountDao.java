package com.ym.dao;

import com.ym.bean.Account;

public interface AccountDao {
	
	Account getAccountById(int id);
	
	Account getAccountByInfo(Account account);
	
	int getIdByDefaultCreater(String userName);
	
	int insertDefaultInfo(Account account);
	
	int updateAccount(Account account);
	
	int cancelAccountById(int id);
	
}
