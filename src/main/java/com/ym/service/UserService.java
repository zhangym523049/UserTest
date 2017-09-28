package com.ym.service;

import com.ym.bean.User;

public interface UserService {
	
	boolean userRegister(User user) throws Exception;
	
	boolean checkUserExist(String userName) throws Exception;
	
}
