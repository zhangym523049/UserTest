package com.ym.controller;

import java.io.IOException;
import java.util.regex.Pattern;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ym.enums.ReturnMessageEnums;
import com.ym.enums.ReturnTypeEnums;
import com.ym.bean.Account;
import com.ym.bean.User;
import com.ym.service.UserInfoService;
import com.ym.service.UserService;
import com.ym.util.SendEmail;
import com.ym.util.SendMessage;
import com.ym.util.UserTestContext;
import com.ym.util.VerifyCodeUtils;

@Controller
@RequestMapping("/user")
@Transactional(value="transactionManager",rollbackFor=java.lang.Exception.class)
public class UserController {
	
	public static Logger logger = Logger.getLogger(UserController.class);
	
	//登陆成功
	public final static String LOGIN_SUCCESS = "loginSuccess";
	
	public final static String LOGIN = "login";
	
	//登陆失败
	public final static String LOGIN_FAIL = "loginFail";
	
	@Autowired
	private UserInfoService userInfoService;
	
	@Autowired
	private UserService userService;
	
	
	/**
	 * 
	 * 登陆
	 * @param userName
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/login.do",method = RequestMethod.GET)
	public String login(@RequestParam(value="userName")String userName,
			@RequestParam(value="password")String password,Model model,HttpSession session) {
	try {	
			session.setAttribute(UserTestContext.USER_NAME, userName);
			return userService.checkPasswordByUserName(userName, password) ? "redirect:/userInfo/showInfo" : LOGIN;
		} catch (Exception e) {
			logger.error(e.getMessage());
			setModel(model, ReturnTypeEnums.ERROR.getType(), UserTestContext.PASSWORD_ERROR);
			return LOGIN;
		}
	}
	
	
	
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
			,@RequestParam(value="password") String password,Model model,HttpSession session) {
		try {
			//double check
			if(!doubleCheck(userName,password)) {
				setModel(model,ReturnTypeEnums.ERROR.getType(),ReturnMessageEnums.NULL_ERROR.geteMessage());
				return LOGIN_FAIL;
			}
			
			userInfoService.addDefaultAccount(new Account(userName));
			userService.userRegister(new User(userName,DigestUtils.md5Hex(password),userInfoService.findIdByCreateUser(userName)));
			session.setAttribute(UserTestContext.USER_NAME, userName);
		} catch (Exception e) {
			logger.error(e.getMessage());
			setModel(model,ReturnTypeEnums.ERROR.getType(),ReturnMessageEnums.INTERNET_ERROR.geteMessage());
			return LOGIN_FAIL;
		}
		return "redirect:/userInfo/showInfo";
	}
	
	
	/**
	 * 
	 * 检查用户名是否存在
	 * @param userName
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/checkLoginUserName.do",method = RequestMethod.GET)
	public @ResponseBody String checkLoginUserName(@RequestParam(value="userName") String userName) {
	try {	
		
			logger.info("当前用户登录的用户名为<"+userName+">");
			return userService.checkUserExist(userName) ? UserTestContext.USER_NOTEXIST : UserTestContext.CHECK_OK;
		} catch (Exception e) {
			logger.error(e.getMessage());
			return UserTestContext.USER_NOTEXIST;
		}
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
		if(StringUtils.isEmpty(userName)) {
			return ReturnMessageEnums.NULL_ERROR.geteMessage();
		}
			return checkUserNameFormat(userName) ? (userService.checkUserExist(userName) ? UserTestContext.CHECK_OK : UserTestContext.USER_EXIST) 
					: UserTestContext.USERNAME_ROLE;
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
			return checkPasswordFormat(password);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return ReturnMessageEnums.INTERNET_ERROR.geteMessage();
		}
		
	}
	
	
	/**
	 * 
	 * 检查两次输入密码是否正确
	 * @param userName
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/doubleCheckPassword.do",method = RequestMethod.GET)
	public @ResponseBody String doubleCheckPassword(@RequestParam(value="password") String password,
			@RequestParam(value="passwordCheck") String passwordCheck) {
		
	try {
			if(StringUtils.isEmpty(password) || StringUtils.isEmpty(passwordCheck)) {
				return UserTestContext.PASSWORD_CHECK_ERROR;
			}
			return password.equals(passwordCheck) ? UserTestContext.CHECK_OK : UserTestContext.PASSWORD_CHECK_ERROR;
		
		} catch (Exception e) {
			logger.error(e.getMessage());
			return ReturnMessageEnums.INTERNET_ERROR.geteMessage();
		}
	}
	
	@RequestMapping("/goLogin.do")
	public String goLogin() {
		//return "login";
		return "forgetPassword";
	}
	
	@RequestMapping("/goRegister.do")
	public String goRegister() {
		return "register";
	}
	
	/**
	 * 
	 * 生成验证码
	 * @param request
	 * @param response
	 */
	@RequestMapping("/checkImg.do")  
    public void createCode(HttpSession session, HttpServletResponse response) {  
        try {
        	noCache(response);
        	ServletOutputStream outputStream = response.getOutputStream();
        	String generateVerifyCode = VerifyCodeUtils.generateVerifyCode(4);
        	session.setAttribute(UserTestContext.VERIFYCODE, generateVerifyCode);
			VerifyCodeUtils.outputImage(100,40,outputStream,generateVerifyCode);
			logger.info("当前用户生成的图片验证码的值为<"+generateVerifyCode+">");
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
    public @ResponseBody String checkCode(@RequestParam(value="checkCode")String checkCode,HttpSession session) { 
		try {
			
			logger.info("从Session中取得的验证码为<"+(String)session.getAttribute(UserTestContext.VERIFYCODE)+">");
			
			//  验证码是否为空 --> 是  -->返回空错误
			//            |
			//            -->不为空 --> 判断输入的验证码是否相同 --> 是 --> 返回null
			//                                         |
			//                                         --> 不相同  --> 返回验证码输入错误
			return StringUtils.isEmpty(checkCode) ? ReturnMessageEnums.NULL_ERROR.geteMessage() : 
				checkCode.equalsIgnoreCase((String)session.getAttribute(UserTestContext.VERIFYCODE)) ? UserTestContext.CHECK_OK : ReturnMessageEnums.VERIFYCODE_ERROR.geteMessage();
			
		} catch (Exception e) {
			logger.error(e.getMessage());
			return ReturnMessageEnums.UNKNOWN_ERROR.geteMessage();
		}
		
	}
	
	/**
	 * 
	 * 验证 用户填写验证码是否正确
	 * @param request
	 * @param response
	 */
	@RequestMapping("/forgetPassword.do")  
    public String forgetPassword(@RequestParam(value="phoneNum")String phoneNum,
    		@RequestParam(value="email")String email,
    		@RequestParam(value="checkCode")String checkCode,
    		@RequestParam(value="type")String type) { 
		try {
			
			if(StringUtils.isEmpty(type)) {
				return ReturnMessageEnums.UNKNOWN_ERROR.geteMessage(); 
			}
			
			if(type.equals(UserTestContext.TYPE_PHONENUM)) {
				SendMessage.send(phoneNum);
			}
			
			if(type.equals(UserTestContext.TYPE_EMAIL)){
				
				SendEmail.send(email);
			}
			
			
			
			
			
			return "forgetPassword";
		} catch (Exception e) {
			logger.error(e.getMessage());
			return ReturnMessageEnums.UNKNOWN_ERROR.geteMessage();
		}
		
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
	private Boolean checkUserNameFormat(String userName) {
			 return match(UserTestContext.USERNAME_REGULAR_EXPRESSION,userName);
	}
	
	
	/**
	 * 密码正则验证
	 * @param password
	 * @return
	 */
	private String checkPasswordFormat(String password) {
		 return match(UserTestContext.PASSWORD_REGULAR_EXPRESSION,password) 
				 ? UserTestContext.CHECK_OK:UserTestContext.PASSWORD_ROLE;
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
