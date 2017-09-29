package com.ym.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ym.bean.Account;
import com.ym.dao.AccountDao;
import com.ym.service.UserInfoService;

@Service
public class UserInfoServiceImpl implements UserInfoService{
	
	@Autowired
	private AccountDao accountDao;

	@Override
	public boolean addDefaultAccount(Account account) {
		return accountDao.insertDefaultInfo(account) >= 0;
	}

	@Override
	public int findIdByCreateUser(String userName) {
		return accountDao.getIdByDefaultCreater(userName);
	}

	@Override
	public int updateAccountInfo(Account account) {
		return accountDao.updateAccount(account);
	}

}
