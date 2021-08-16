package dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import entity.Employee;

public interface EmployeeDao {
	
	// 根据用户输入查询 (name or title) 员工的数量
	public int getEmployeeNumber(Connection connection, String empName, String empTitle) throws SQLException;
	
	// 查询员工列表
	public List<Employee> getEmployeeList(Connection connection, String empName, 
			String empTitle,int currentPageNum, int pageSize) throws Exception;
	
	// 增加员工
	public int addEmployee(Connection connection, Employee employee);
	
	// 删除员工
	public int deleteEmployeeById(Connection connection, int empId) throws Exception; 
	
	// modify
	public int modifyEmployee(Connection connection, Employee employee)throws Exception;
}
