package com.blog.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.blog.dao.UserDao;
import com.blog.entity.User;
import com.blog.service.UserService;


@Transactional
@Service
public class UserServiceImpl implements UserService{
	@Resource
	private UserDao userDao;

	@Override
	public User login(User user) {
		return userDao.login(user);
	}
	
	@Override  
	public User findUser(int i) {
		return userDao.findById(i);
	}
	
	@Override
	public void updateUser(User user) {
		userDao.update(user);
	}
	
	@Override
	public void saveUser(User user) {
		userDao.save(user);
	}
	
	@Override
	public void updateUserPassword(User user) {
		userDao.updateUserPassword(user);
	}

	@Override
	public User findUserByName(String userName) {
		return userDao.findByName(userName);
	}
}
