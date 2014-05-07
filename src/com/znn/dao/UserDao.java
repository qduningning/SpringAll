package com.znn.dao;

import java.util.List;

import com.znn.vo.User;

public interface UserDao {

	public abstract List<User> query();

	public abstract int save(User user);

	public abstract int update(User user);
	
	public abstract int delete(int id);
	
	public abstract User queryAUser(int id);
}