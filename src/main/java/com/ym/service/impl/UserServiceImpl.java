package com.ym.service.impl;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ym.bean.User;
import com.ym.dao.UserDao;
import com.ym.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserDao userDao;
	
	@Override
	public boolean userRegister(User user) throws Exception {
		return userDao.addUser(user) != 0 ? true : false;
	}

	@Override
	public boolean checkUserExist(String userName) throws Exception {
		return userDao.checkUserExist(userName) == 0 ? true : false;
	}

	@Override
	public boolean checkPasswordByUserName(String userName,String password) throws Exception {
		return userDao.selectPasswordByUserName(userName).equals(DigestUtils.md5Hex(password));
	}

}
