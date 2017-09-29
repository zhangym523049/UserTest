package com.ym.service;

import com.ym.bean.Account;

public interface UserInfoService {
	
	boolean addDefaultAccount(Account account);
	
	int findIdByCreateUser(String userName);
	
	int updateAccountInfo(Account account);
	

}
