package net.atos.lepus.DAOs;

import java.util.Collection;

import net.atos.lepus.models.User;

public interface UserDAO {
	public int insert(User user);
	public boolean delete(int id);
	public User find(int id);
	public boolean update(User user);
	public Collection<User> findAll();	
	
	public User findByName(String name);
	public User findByWibble(String wibble);
}
