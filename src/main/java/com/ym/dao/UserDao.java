package com.ym.dao;

import com.ym.bean.User;

public interface UserDao {
	
	int addUser(User user) throws Exception;
	
	int checkUserExist(String userName) throws Exception;
	
	String selectPasswordByUserName(String userName);
	

}
