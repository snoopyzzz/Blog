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
	
	@RequestMapping(value = "/login")
	public String Login() {
		return "admin/login";
	}
	
	/**
	 * 登录验证
	 * @param user
	 * @param req
	 * @return
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
	 * 修改个人联系信息
	 * @return
	 */
	@RequestMapping(value = "/admin/doModify")
	public String modify(HttpServletRequest req, ModelMap map) {
		User loginUser = (User) req.getSession().getAttribute("existUser");

		map.put("user", loginUser);
		return "admin/dataModify";
	}

	/**
	 * 修改普通资料
	 * @param user
	 * @return
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
	 * 查询密码
	 * @return
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
	 * 修改账号密码
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/admin/updateUserPassword")
	public String updateUserP(User user) {
		userService.updateUserPassword(user);;
		System.out.print("密码更新成功");
		return "admin/userModify";
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
