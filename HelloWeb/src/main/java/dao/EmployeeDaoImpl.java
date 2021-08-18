package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.StringUtils;

import entity.Employee;
import utils.BaseDao;

public class EmployeeDaoImpl implements EmployeeDao {

	public int getEmployeeNumber(Connection connection, String empName, String empTitle) throws SQLException {

		int count = 0;
		if (connection != null) {
			// prepare parameters for calling baseDao
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT COUNT(1) AS COUNT FROM employee WHERE 1=1 ");

			// prepare parameters for sql
			ArrayList<Object> list = new ArrayList<Object>();

			// Fuzzy query emp_name
			if (!StringUtils.isNullOrEmpty(empName)) {

				sql.append("AND emp_name LIKE ? ");
				list.add("%" + empName + "%");
			}
			// Fuzzy query emp_title
			if (!StringUtils.isNullOrEmpty(empTitle)) {

				sql.append("AND emp_title LIKE ? ");
				list.add("%" + empTitle + "%");
			}

			// prepare parameters for call BaseDao
			PreparedStatement preparedStatement = null;
			ResultSet rs = null;
			Object[] params = list.toArray();

			// for debug
			System.out.println("EmploueeDao: " + sql.toString());

			// call BaseDao
			rs = BaseDao.excute(connection, sql.toString(), params, preparedStatement, rs);

			// standard operation
			if (rs.next()) {
				count = rs.getInt("COUNT");
			}

			// close resources
			BaseDao.closeResource(null, rs, preparedStatement);

		} else {
			System.out.print("Database coonection failed");
		}

		return count;

	}

	public List<Employee> getEmployeeList(Connection connection, String empName, String empTitle, int currentPageNum,
			int pageSize) throws SQLException {

		PreparedStatement preparedStatement = null;

		ResultSet rs = null;

		List<Employee> employeeList = new ArrayList<>();

		if (connection != null) {

			StringBuffer sql = new StringBuffer();
			sql.append("SELECT * FROM employee WHERE 1=1 ");

			List<Object> paramList = new ArrayList<>();

			// Fuzzy query emp_name
			if (!StringUtils.isNullOrEmpty(empName)) {

				sql.append("AND emp_name LIKE ? ");

				paramList.add("%" + empName + "%");

			}

			if (!StringUtils.isNullOrEmpty(empTitle)) {

				sql.append("AND emp_title LIKE ? ");

				paramList.add("%" + empTitle + "%");

			}


			// page control part:
			sql.append(" ORDER BY emp_id DESC LIMIT ?, ? ;");
			currentPageNum = (currentPageNum - 1) * pageSize;

			// prepare param
			paramList.add(currentPageNum);
			paramList.add(pageSize);

			Object[] params = paramList.toArray();

			// for debug
			System.out.println("EmployeeDaoImpl :" + sql.toString());

			// get ResultSet
			rs = BaseDao.excute(connection, sql.toString(), params, preparedStatement, rs);

			// standard operation
			while (rs.next()) {
				
				Employee emp = new Employee();
				emp.setEmp_id(rs.getInt("emp_id"));
				emp.setEmp_name(rs.getString("emp_name"));
				emp.setEmp_salary(rs.getInt("emp_salary"));
				emp.setEmp_title(rs.getString("emp_title"));
				emp.setGroup(rs.getString("group"));
				emp.setEntry_date(rs.getString("entry_date"));

				employeeList.add(emp);

			}

			BaseDao.closeResource(null, rs, preparedStatement);

		} else {
			System.out.println("Database Connection failed");
		}

		return employeeList;
	}

	public int addEmployee(Connection connection, Employee employee) {

		// prepare return value 
		int updatedRows = 0;
		
		// prepare parameter for call BaseDao
		PreparedStatement preparedStatement = null;
	
		String sql = "INSERT INTO employee(`emp_name`,`emp_title`,`emp_salary`,`entry_date`,"
				+ "`group`) VALUES(?,?,?,?,?)"; 
		
		Object[] params = {employee.getEmp_name(), employee.getEmp_title(),
				employee.getEmp_salary(), employee.getEntry_date(),employee.getGroup()};
		
		try {
			updatedRows = BaseDao.execute(connection, preparedStatement, sql, params);
			
			
		
		} catch (Exception e) {
			System.out.println("EmployeeDaoImpl : Something bad happend.......");
			e.printStackTrace();
		}
		
		
		

		return updatedRows;
	}
	
	public int deleteEmployeeById(Connection connection, int empId) throws Exception {
		
		int deleteRows = 0;
		
		String sql = "DELETE FROM employee WHERE emp_id = ?";
		
		PreparedStatement preparedStatement = null;
		
		Object[] param = {empId};
		
		if(connection != null) {
			
			deleteRows = BaseDao.execute(connection, preparedStatement, sql, param);		
		}
		
		return deleteRows;
	}
	
	public int modifyEmployee(Connection connection, Employee employee)throws Exception{
		
		int flag = 0;
		
		PreparedStatement preparedStatement = null;
		
		if(connection != null) {
			
			String sql = " UPDATE employee SET emp_name = ?, emp_title = ?, emp_salary = ?, entry_date = ? WHERE emp_id = ?";
			
			Object[] params = {employee.getEmp_name(),employee.getEmp_title(),employee.getEmp_salary(),employee.getEntry_date(),employee.getEmp_id()};
			
			flag = BaseDao.execute(connection, preparedStatement, sql, params);
			
			BaseDao.closeResource(null, null, preparedStatement);
		}
		
		if(flag != 0) {
			System.out.println("Update successd");
		}else {
			System.out.println("Update failed");
		}
		
		return flag;
		
	}

}
