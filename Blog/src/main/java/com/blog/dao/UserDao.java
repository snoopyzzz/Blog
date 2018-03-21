package com.blog.dao;

import com.blog.entity.User;



public interface UserDao extends BaseDao<User>{
	User login(User user);
	User findUser(int i);
	User findByName(String name);
	void updateUser(User user);
	void updateUserPassword(User user);
}
