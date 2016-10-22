package com.subbu.aegean.dao;

import java.util.List;

import com.subbu.aegean.model.User;

public interface UserDAO {
	public List<User> list();
	public User get(String id);
	public void saveOrUpdate(User user);
	public void delete(String id);
	public boolean isValidUser(String username, String password);

}
