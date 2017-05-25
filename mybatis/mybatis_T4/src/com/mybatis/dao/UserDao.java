package com.mybatis.dao;


import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.mybatis.model.User;

public interface UserDao {
	@Insert("insert into users(name,password) values(#{name},#{password})")
	public boolean save(User user);
	@Delete("delete from users where id=#{id}")  
	public boolean delete(int id);
	@Update("update users set name=#{name},password=#{password} where id=#{id}") 
	public boolean update(User user);
	@Select("select * from users where id=#{id}")
	public User findByID(int id); 
	//方法名不能相同  
	@Select("select * from users where name like #{value})")
	public List<User> selectListByName1(String anyvalue); 
	@Select("select * from users where name = #{0} or id = #{1}")
	public List<User> selectListByName2(String anyvalue1,int anyvalue2);
	@Select("select * from users where name=#{name} or id = #{id} or email = #{email}")
	public List<User> selectListByObj(User anyvalue);
	
	
}
