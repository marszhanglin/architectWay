package com.mybatis.dao;


import java.util.List;

import com.mybatis.model.User;

public interface UserDao {

	public User selectUserByID(int id); 
	
	public List<User> selectListByName1(String anyvalue);
	
	public List<User> selectListByName2(String anyvalue);
	
	public List<User> selectListByName3(String anyvalue);
	
	public List<User> selectListByName4(String anyvalue);
	
	public List<User> selectListByName5(String anyvalue1,int anyvalue2);
	
}
