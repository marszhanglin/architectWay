package com.mybatis.dao;


import java.util.List;

import com.mybatis.model.User;

public interface UserDao {

	public User selectUserByID(int id);
	
	public User selectUserByName(String name);
	
	public List<User> selectListByName(String name);
	
}
