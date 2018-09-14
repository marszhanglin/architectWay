package com.mybatis.dao;


import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.mybatis.model.User;

public interface UserDao {
	@Insert("insert into users(name,password,email,birthday) values(#{name},#{password},#{email},#{birthday})")
	public boolean save(User user);
	@Delete("delete from users where id=#{id}")  
	public boolean delete(int id);
	@Delete("delete from users where email is null")  
	public boolean deleteEmailIsNull();
	@Update("update users set name=#{name},password=#{password},email=#{email} where id=#{id}") 
	public boolean update(User user);
	//多次参数传参又一解决方案：
	@Select("select * from users where name like concat('%',#{aaa,jdbcType=VARCHAR},'%')  ")
	public List<User> selectListByName1(@Param("aaa")String anyvalue); 
	@Select("select * from users where name = #{0} or id = #{1}")
	public List<User> selectListByName2(String anyvalue1,int anyvalue2);
	@Select("select * from users where name=#{name} or id = #{id} or email = #{email}")
	public List<User> selectListByObj(User anyvalue);
	
	
	
}
