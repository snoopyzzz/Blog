package com.blog.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.blog.entity.User;
import com.blog.service.UserService;





@Controller
public class UserController {
	@Resource	
	private UserService userService;
	
	@RequestMapping(value = "/test")
	public String Test() {
		return "admin/test";
	}
	
	
	@RequestMapping(value = "/register")
	public String Register() {
		return "blog/register";
	}
	
	@RequestMapping(value = "/doRegister", method = RequestMethod.POST)
	public ModelAndView doRegister(User user, HttpServletRequest req) {
		ModelAndView view = new ModelAndView();		
		if(userService.findUserByName(user.getName()) == null){
			userService.saveUser(user);
			System.out.println("博客注册成功！");
			view.setViewName("redirect:login");
		} else {
			view.addObject("errorMsg","用户名已经存在，请输入其它的名字！");
			view.setViewName("forward:register");
		}
		return view;
	}
	
	@RequestMapping(value = "/login")
	public String Login() {
		return "admin/login";
	}
}
