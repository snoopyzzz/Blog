package com.blog.controller;



import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.blog.entity.User;
import com.blog.service.UserService;





@Controller
public class UserController {
	@Resource	
	private UserService userService;
	
	/*
	 * 测试用
	 * 
	 */
	@RequestMapping(value = "/test")
	public String Test() {
		return "admin/test";
	}
	
	/*
	 * 进入注册页面
	 */
	@RequestMapping(value = "/register")
	public String Register() {
		return "blog/register";
	}
	
	/*
	 * 执行注册功能，
	 * 并验证用户名是否相同
	 * 有相同用户名，返回注册页面并提示用户名相同
	 */
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
	
	@RequestMapping(value = "/nameCheck", produces = "application/json")   
	@ResponseBody
	public void checkUser(HttpServletRequest request, HttpServletResponse response, HttpSession session)
            throws ServletException, IOException {  
	      
        PrintWriter out = response.getWriter();
        String name = request.getParameter("name");
        if (name.trim().equals("")) {
            out.print(2);// 2是不能为空
        } else {
            User user = userService.findUserByName(name);
            if (user != null) {
                out.print(1);// 1用户名已存在F
            } else {
                out.print(3);// 用户名可以用
            }
        }
	}  
	
	/*
	 * 进入登录页面
	 */
	@RequestMapping(value = "/login")
	public String Login() {
		return "admin/login";
	}
	
	/**
	 * 登录验证
	 * @param user
	 * @param req
	 * @return
	 * 如果用户名密码错误，会返回登录界面并提示
	 */
	@RequestMapping(value = "/doLogin", method = RequestMethod.POST)
	public ModelAndView doLogin(User user, HttpServletRequest req) {
		ModelAndView view = new ModelAndView();
		HttpSession session = req.getSession();
		User existUser = userService.login(user);
		
		if (existUser != null) {
			System.out.println("博客主页登录成功");
			session.setAttribute("existUser", existUser);
			view.setViewName("admin/background");
		}else {
			System.out.println("博客主页登录失败");
			view.addObject("errorMsg","用户名或密码错误！");
			view.setViewName("forward:login");
		}
		return view;
	}
	
	/**
	 * 修改个人信息
	 * @return
	 * 进入修改页面
	 */
	@RequestMapping(value = "/admin/doModify")
	public String modify(HttpServletRequest req, ModelMap map) {
		User loginUser = (User) req.getSession().getAttribute("existUser");

		map.put("user", loginUser);
		return "admin/dataModify";
	}

	/**
	 * 修改个人信息
	 * @param user
	 * @return
	 * 执行修改个人信息
	 */
	@RequestMapping(value = "/admin/updateUser")
	public String updateUserdata(User user) {
		User u = userService.findUser(user.getId());
		u.setEmail(user.getEmail());
		u.setPhone(user.getPhone());
		u.setQq(user.getQq());
		u.setInfo(user.getInfo());
		userService.updateUser(u);
		return "admin/dataModify";
	}
	
	/**
	 * 修改密码
	 * @return
	 * 进入修改密码页面
	 */
	@RequestMapping(value = "/admin/userModify")
	public String doUser(ModelMap map,HttpServletRequest req) {
		User loginUser = (User) req.getSession().getAttribute("existUser");
		User user = userService.findUser(loginUser.getId());
		userService.updateUserPassword(user);
		map.put("user", user);
		System.out.println("user,更新密码成功");
		return "admin/userModify";
	}

	/**
	 * 修改密码
	 * @param user
	 * @return
	 * 执行修改操作
	 * 并验证原密码是否相符
	 */
	@RequestMapping(value = "/admin/updateUserPassword")
	public ModelAndView updateUserPassword(User user, String originalPwd, HttpServletRequest req) {
		ModelAndView view = new ModelAndView();
		System.out.println("原密码：" + originalPwd);
		User loginUser = (User) req.getSession().getAttribute("existUser");
		if(loginUser.getPassword().equals(originalPwd)) {
			userService.updateUserPassword(user);
			System.out.println("密码更新成功");
			view.addObject("Message", "密码修改成功！");
			view.setViewName("admin/userModify");
		}
		else {
			System.out.println("原密码输入错误，密码更新失败");
			view.addObject("Message", "密码修改失败！原密码错误！");
			view.setViewName("admin/userModify");
		}
		return view;
	}
	
	/**
	 * 退出登录
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/SignOut")
	public String SignOut(HttpServletRequest req) {
		HttpSession session = req.getSession(false);
		session.removeAttribute("existUser");
		return "redirect:login";
	}
	
	
}
