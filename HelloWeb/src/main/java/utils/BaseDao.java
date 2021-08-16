package utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;


/**
 * This is a Class for database connection and resource closing.
 */

public class BaseDao {

	private static String driver;
    private static String url;
    private static String username;
    private static String password;
    
    
    static{
        // Get properties in db.properties
    	Properties properties = new Properties();
        InputStream is = BaseDao.class.getClassLoader().getResourceAsStream("db.properties");
        
        try {
            properties.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        driver = properties.getProperty("driver");
        url = properties.getProperty("url");
        username = properties.getProperty("username");
        password = properties.getProperty("password");
    }
    public static Connection getConnection() throws ClassNotFoundException, SQLException{
        
        Class.forName(driver);
        Connection con = DriverManager.getConnection(url, username, password);
        
        System.out.println("Got connection with" + url);
        
        return con;
    }
    /**
     * @param Connection connection
     * @param String sql
     * @param Object params 
     * @param ResultSet rs
     * @return: 两个加数的和
     * @throws SQLException 
     */
    public static ResultSet excute(Connection con,String sql,Object[] params,PreparedStatement preparedStatement, 
    		ResultSet rs) throws SQLException {
		// 
    	preparedStatement = con.prepareStatement(sql);
    	
    	// Parameter injection to sql
    	for(int i = 0; i < params.length; i++) {
    		preparedStatement.setObject(i+1, params[i]); 
    	}
    	
    	
    	// Get resultSet by execute query
    	rs = preparedStatement.executeQuery();
    	
    	return rs;
    }
    
    public static boolean closeResource(Connection connection, ResultSet resultset, PreparedStatement preparedStatement) {
		boolean flag = true;
    	
		// close connection
		if(connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				flag = false;
				System.out.print("failed to close connection");
				e.printStackTrace();
			}
			
		// close resultset
			if(resultset != null) {
				try {
					resultset.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					flag = false;
					System.out.println("failed to close resultset");
					e.printStackTrace();
				}
		}
		// close preparedStatemnet
			if(preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					flag = false;
					System.out.print("failed to close preparedStatement");
					e.printStackTrace();
				}
		}
    }
		return flag;
       
    }
    /**
     * Update table(add, delete update)
     * @param connection
     * @param pstm
     * @param sql
     * @param params
     * @return
     * @throws Exception
     */
    public static int execute(Connection connection,PreparedStatement pstm,
			String sql,Object[] params) throws Exception{
		
    	int updateRows = 0;
		pstm = connection.prepareStatement(sql);
		
		for(int i = 0; i < params.length; i++){
			pstm.setObject(i+1, params[i]);
		}
		
		System.out.println(sql);
		
		updateRows = pstm.executeUpdate();
		return updateRows;
	}
}