package com.blog.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.blog.entity.User;



public class LoginInterceptor implements HandlerInterceptor {

	@Override
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
		// TODO Auto-generated method stub

	}

	@SuppressWarnings("unused")
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object arg2) throws Exception {
		
		User user = (User) request.getSession().getAttribute("existUser");
		String name = user.getName();
		
		if (user == null) {
			System.out.println("进入springmvc拦截器");
			System.out.println("尚未登录，调到登录页面");
			// 跳转到登录页面
			request.getRequestDispatcher("/WEB-INF/views/admin/login.jsp")
					.forward(request, response);
			return false;
		}
		System.out.println("进入:" + name);
		return true;
	}

}
