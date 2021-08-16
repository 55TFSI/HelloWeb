package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entity.User;
import utils.BaseDao;

public class UserDaoImpl implements UserDao{
	
	public User getLoginUser(Connection connection, String username, String password) {
		
		// prepared parameters for call BaseDao.excute()
		
		String sql = "SELECT * FROM user WHERE username = ? and password = ?;";
		Object[] object = {username,password};
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		
		// Initialize a user instance to match rs and set to session
		User searchedUser = new User(); 
		
		
		if (connection != null) {
			
			try {
				// call BaseDao.execute()
				rs = BaseDao.excute(connection, sql, object, preparedStatement, rs);
				
				
				if (rs.next()) {
					// if we found that user
					
					String searchedUsername = rs.getString("username");
					String searchedPassword = rs.getString("password");
					int searchedUserId = rs.getInt("user_id");
					
					System.out.println(searchedUserId);
					
					searchedUser.setUsername(searchedUsername);
					searchedUser.setPassword(searchedPassword);
					searchedUser.setUser_id(searchedUserId);
					
					// close JDBC resources
					
					Boolean flag = BaseDao.closeResource(null, rs, preparedStatement);
					// maybe  void for closeResource better
					if(!flag) {
						System.out.print("failed to close one of the resource");
					} 
				}else {
					
					System.out.println("UserDao : no such user");
					
					return null;
				
					
					
					
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} else {
			
			System.out.print("Can not get coonection");
		}
 		
		
		
		return searchedUser;
	}
	
	
	
}
