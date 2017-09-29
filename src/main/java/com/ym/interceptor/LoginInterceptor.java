package com.ym.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.ym.util.UserTestContext;

/**
 * login 
 */
public class LoginInterceptor implements HandlerInterceptor {
	
	public static Logger logger = Logger.getLogger(LoginInterceptor.class);
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		/*HttpSession session = request.getSession();
		String hasLogin = (String) session.getAttribute("userLogin");
		if (StringUtils.isEmpty(hasLogin) || !"hasLogin".equals(hasLogin)) {
			
			String contextPath = request.getContextPath();
			System.out.println(contextPath);
			response.sendRedirect("login.jsp");
		}*/
        String url = request.getRequestURI();  
        logger.info(url);
        
        //URL:login.jsp是公开的;这个demo是除了login.jsp是可以公开访问的，其它的URL都进行拦截控制  
        if(url.indexOf("/user/")>=0){  
            return true;
        }  
        //获取Session
        HttpSession session = request.getSession();  
        String username = (String)session.getAttribute(UserTestContext.USER_NAME);  
          
        if(username != null){  
            return true;  
        }  
        
        logger.info("当前session中的UserName信息为"+username+"即将跳转到登陆页面！");
        //不符合条件的，跳转到登录界面  
        request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);  
          
		return false;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}

}
