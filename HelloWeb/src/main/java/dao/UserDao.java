package dao;

import java.sql.Connection;

import entity.User;

/**
 * 
 * @author chengyuan.li
 * 
 */
public interface UserDao {
	
	/**
	 * 
	 */
	public User getLoginUser(Connection connection, String username, String password);
}
