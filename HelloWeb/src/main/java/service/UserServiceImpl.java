package service;

import java.sql.Connection;
import java.sql.SQLException;
import dao.UserDao;
import dao.UserDaoImpl;
import entity.User;
import utils.BaseDao;

public class UserServiceImpl implements UserService{
	
	private UserDao userDao;
	
	public UserServiceImpl() {
		userDao = new UserDaoImpl();
	}

	public User userLogin(String username, String password) {
		
		Connection connection = null;
		User searchedUser = null;
		
		try {
			
			// prepare parameters for calling dao
			
			connection = BaseDao.getConnection();
			
			// make query
			searchedUser = userDao.getLoginUser(connection, username, password);
			

			
		} catch (ClassNotFoundException e) {
			System.out.print("User service :no connection class");
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			BaseDao.closeResource(connection, null, null);
		}
		
		
		
		 
		return searchedUser;
	}
	

}
