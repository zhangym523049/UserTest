package com.ym.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ym.enums.ReturnMessageEnums;
import com.ym.enums.ReturnTypeEnums;
import com.ym.bean.User;
import com.ym.service.UserService;
import com.ym.util.UserTestContext;
import com.ym.util.VerifyCodeUtils;

@Controller
@RequestMapping("/user")
public class UserController {
	
	public static Logger logger = Logger.getLogger(UserController.class);
	
	public final static String LOGIN_SUCCESS = "loginSuccess";
	
	@Autowired
	private UserService userService;
	
	/**
	 * 
	 * 注册
	 * @param userName
	 * @param password
	 * @param model
	 * @return
	 */
	@RequestMapping("/register.do")
	public String userRegister(@RequestParam(value="userName") String userName
			,@RequestParam(value="password") String password,Model model) {
		try {
			//double check
			if(!doubleCheck(userName,password)) {
				setModel(model,ReturnTypeEnums.ERROR.getType(),ReturnMessageEnums.NULL_ERROR.geteMessage());
				return ReturnMessageEnums.ERROR.geteMessage();
			}
			userService.userRegister(new User(userName,password));
		} catch (Exception e) {
			logger.error(e.getMessage());
			setModel(model,ReturnTypeEnums.ERROR.getType(),ReturnMessageEnums.INTERNET_ERROR.geteMessage());
			return ReturnMessageEnums.ERROR.geteMessage();
		}
		return LOGIN_SUCCESS;
	}
	
	
	/**
	 * 
	 * 检查用户名是否存在，用户名是否规范
	 * @param userName
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/checkUserName.do",method = RequestMethod.GET)
	public @ResponseBody String checkUserExist(@RequestParam(value="userName") String userName) {
		
	try {
		logger.info("接收到的userName为"+userName);
		//double check
		if(StringUtils.isEmpty(userName)){
			return ReturnMessageEnums.NULL_ERROR.geteMessage();
		}
			//用户不存在返回用户名是否符合规则，存在返回错误信息
			System.out.println(userService.checkUserExist(userName) ? checkUserNameFormat(userName) : UserTestContext.USER_EXIST);
			return userService.checkUserExist(userName) ? checkUserNameFormat(userName) : UserTestContext.USER_EXIST;
		} catch (Exception e) {
			logger.error(e.getMessage());
			return ReturnMessageEnums.ERROR.geteMessage();
		}
		
	}
	
	
	
	/**
	 * 
	 * 检查用户密码是否规范
	 * @param userName
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/checkPassword.do",method = RequestMethod.GET)
	public @ResponseBody String checkPassword(@RequestParam(value="password") String password) {
		
	try {
		
		logger.info("接收到的password为"+password);
		//double check
		if(StringUtils.isEmpty(password)){
			return ReturnMessageEnums.NULL_ERROR.geteMessage();
		}
			//返回结果
			System.out.println(checkPasswordFormat(password));
			return checkPasswordFormat(password);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return ReturnMessageEnums.INTERNET_ERROR.geteMessage();
		}
		
	}
	
	
	@RequestMapping("/goLogin.do")
	public String goLogin() {
		return "login";
	}
	
	@RequestMapping("/goRegister.do")
	public String goRegister() {
		return "register";
	}
	
	@RequestMapping(value="/userInfo.do",method = RequestMethod.GET)
	@ResponseBody
	public String getUserInfo(HttpSession session,@RequestParam(value="userName")String username,@RequestParam(value="password")String password){
		session.setAttribute("username", username);  
		return "userInfo";
	}
	
	/**
	 * 
	 * 生成验证码
	 * @param request
	 * @param response
	 */
	@RequestMapping("/checkImg.do")  
    public void createCode(HttpServletRequest request, HttpServletResponse response) {  
        try {
        	noCache(response);
        	ServletOutputStream outputStream = response.getOutputStream();
			String outputImage = VerifyCodeUtils.outputImage(100,40,outputStream, VerifyCodeUtils.generateVerifyCode(4));
			logger.info("当前用户生成的图片验证码的值为"+outputImage);
			setCookie(response,outputImage);
			outputStream.close();
			outputStream.flush();
			
        } catch (IOException e) {
        	logger.error(e.getMessage());
		}
        
    }
	
	/**
	 * 
	 * 验证 用户填写验证码是否正确
	 * @param request
	 * @param response
	 */
	@RequestMapping("/checkCode.do")  
    public @ResponseBody String checkCode(HttpServletRequest request, HttpServletResponse response) { 
		try {
			return getCookieByName(request, UserTestContext.VERIFYCODE).getValue()
					.equals(request.getParameter("checkCode")) ? null:ReturnMessageEnums.VERIFYCODE_ERROR.geteMessage();
		} catch (Exception e) {
			logger.error(e.getMessage());
			return ReturnMessageEnums.UNKNOWN_ERROR.geteMessage();
		}
		
	}
	/**
	 * 
	 * 设置Cookie
	 * @param response
	 * @param info
	 */
	private void setCookie(HttpServletResponse response, String info) {
		Cookie cookie = new Cookie(UserTestContext.VERIFYCODE,info);
		cookie.setPath("/");
		response.addCookie(cookie);
		
	}
	
	/**
	 * 根据名字获取cookie
	 * @param request
	 * @param name cookie名字
	 * @return
	 */
	public static Cookie getCookieByName(HttpServletRequest request,String name){
	    Map<String,Cookie> cookieMap = ReadCookieMap(request);
	    if(cookieMap.containsKey(name)){
	        Cookie cookie = (Cookie)cookieMap.get(name);
	        return cookie;
	    }else{
	        return null;
	    }   
	}
	
	/**
	 * 将cookie封装到Map里面
	 * @param request
	 * @return
	 */
	private static Map<String,Cookie> ReadCookieMap(HttpServletRequest request){  
	    Map<String,Cookie> cookieMap = new HashMap<String,Cookie>();
	    Cookie[] cookies = request.getCookies();
	    if(null!=cookies){
	        for(Cookie cookie : cookies){
	            cookieMap.put(cookie.getName(), cookie);
	        }
	    }
	    return cookieMap;
	}


	/**
	 *  通知浏览器不要缓存  
	 * @param response
	 */
	private void noCache(HttpServletResponse response) {
        response.setHeader("Expires", "-1");
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Pragma", "-1");
	}  
	
	/**
	 * 用户名正则验证
	 * @param userName
	 * @return
	 */
	private String checkUserNameFormat(String userName) {
			 return match(UserTestContext.USERNAME_REGULAR_EXPRESSION,userName) 
					 ? null:UserTestContext.USERNAME_ROLE;
	}
	
	
	/**
	 * 密码正则验证
	 * @param password
	 * @return
	 */
	private String checkPasswordFormat(String password) {
		 return match(UserTestContext.PASSWORD_REGULAR_EXPRESSION,password) 
				 ? null:UserTestContext.PASSWORD_ROLE;
	}
	
	/**
	 * 正则验证
	 * @param regex
	 * @param str
	 * @return
	 */
	 private static boolean match(String regex, String str) {
		 return Pattern.compile(regex).matcher(str).matches();
	}
	
	
	/**
	 * 二次验证
	 * 
	 * @param userName
	 * @param password
	 * @return
	 */
	private boolean doubleCheck(String userName, String password) {
		return (userName != null && !userName.equals("")) && (password != null && !password.equals(""));
	}
	
	
	/**
	 * 设置返回视图信息
	 * @param model
	 * @param returnMessage
	 * @param returnObject
	 */
	private void setModel(Model model,String returnMessage,Object returnObject) {
		model.addAttribute(returnMessage, returnObject);
	}

}
