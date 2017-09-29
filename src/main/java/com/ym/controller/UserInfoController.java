package com.ym.controller;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ym.util.UserTestContext;

@Controller
@RequestMapping("/userInfo")
public class UserInfoController {
	
	public static Logger logger = Logger.getLogger(UserInfoController.class);
	
	@RequestMapping("/showInfo")
	public String test(HttpSession session) {
		String attribute = (String)session.getAttribute(UserTestContext.USER_NAME);
		
		
		
		
		
		
		return "userInfo";
	}
	
	
}
