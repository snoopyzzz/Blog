package com.blog.dao;

import com.blog.entity.User;



public interface UserDao extends BaseDao<User>{
	User login(User user);
	User findByName(String name);
	void updateUserPassword(User user);
}
