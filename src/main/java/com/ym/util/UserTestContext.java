package com.ym.util;


/**
 * 
 * 通用信息
 * @author Administrator
 *
 */
public class UserTestContext {
	
	//用户名规则
	public static final String USERNAME_ROLE="用户名只能包含英文字母、数字及“_”，必须以英文字母开头,最少不得小于五位，最大不能超过20";
	//用户名正则表达式
	public static final String USERNAME_REGULAR_EXPRESSION = "^[a-zA-Z][a-zA-Z0-9_]{4,19}$";
	//密码规则
	public static final String PASSWORD_ROLE="密码只能包含英文字母及数字，必须以大写的英文字母开头,最少不得小于五位";
	//密码正则表达式
	public static final String PASSWORD_REGULAR_EXPRESSION = "^[A-Z][a-zA-Z0-9]{4,49}$";
	
	public final static String USER_EXIST = "*该用户已存在,请重新输入用户名";
	
	public final static String USER_NOTEXIST = "用户不存在，请确认用户名是否输入正确";
	
	public final static String VERIFYCODE = "verifyCode";
	
	public final static String VERIFYCODE_ERROR = "验证码输入错误,请重新输入！";
	
	public final static String PASSWORD_CHECK_ERROR = "两次输入密码不一致，请重新输入！";
	
	public final static String CHECK_OK = "ok";
	
	public final static String USER_NAME = "userName";
	
	public final static String ENPTY_CODE ="";
	
	public final static String PASSWORD_ERROR = "密码错误，请重新输入！";
	
	public final static String TYPE_PHONENUM = "phoneNum";
	
	public final static String TYPE_EMAIL = "email";
	
	
	
	
	
	
}
