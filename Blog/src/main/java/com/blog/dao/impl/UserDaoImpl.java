package com.blog.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.blog.dao.UserDao;
import com.blog.entity.User;


@Repository
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao{
	public User login(User user) {
		String hql = "from User where name=? and password=?";
		@SuppressWarnings("unchecked")
		List<User> userList =  (List<User>) super.hibernateTemplate.find(hql,user.getName(),user.getPassword());
		if(userList.size()>0){
			// 查到数据返回第一个
			System.out.println(userList.get(0).getName()+"查找成功");
			return userList.get(0);
		}
		return null;
	}




    /**
     * 更新密码，账号
     */
	public void updateUserPassword(User user) {
		User u = super.hibernateTemplate.get(User.class, user.getId());
		u.setPassword(user.getPassword());
		u.setName(user.getName());
		super.update(u);		
	}
	
    /**
     * 根据用户名查重
     */
	public User findByName(String userName) {
		String hql = "from User where name=?";
		@SuppressWarnings("unchecked")
		List<User> userList =  (List<User>) super.hibernateTemplate.find(hql, userName);
		if(userList.size() > 0){
			// 查到数据返回第一个
			System.out.println(userList.get(0).getName()+"查找成功");
			return userList.get(0);
		}
		return null;
	}
}
