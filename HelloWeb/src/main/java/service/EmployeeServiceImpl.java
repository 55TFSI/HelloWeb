package service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.junit.Test;

import dao.EmployeeDaoImpl;
import entity.Employee;
import utils.BaseDao;

public class EmployeeServiceImpl implements EmployeeService {

	private EmployeeDaoImpl empolyeeDao;

	public EmployeeServiceImpl() {
		empolyeeDao = new EmployeeDaoImpl();
	}

	public int getEmployeeNumber(String empName, String empTitle) {

		Connection connection = null;

		int employeeNum = 0;

		try {
			connection = BaseDao.getConnection();
			employeeNum = empolyeeDao.getEmployeeNumber(connection, empName, empTitle);

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			BaseDao.closeResource(connection, null, null);
		}

		return employeeNum;
	}

	public List<Employee> getEmployeeList(String empName, String empTitle, int currentPageNo, int pageSize) {

		List<Employee> employeeList = null;

		// prepare parameters for call Dao
		Connection connection = null;

		try {
			connection = BaseDao.getConnection();

			employeeList = empolyeeDao.getEmployeeList(connection, empName, empTitle, currentPageNo, pageSize);

		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		} finally {

			BaseDao.closeResource(connection, null, null);

		}

		return employeeList;
	}

	// add Employee
	public boolean addEmployee(Employee employee) {
		boolean flag = false;
		Connection connection = null;

		try {
			connection = BaseDao.getConnection();

			// JDBC transaction
			connection.setAutoCommit(false);

			if (empolyeeDao.addEmployee(connection, employee) > 0) {

				flag = true;
				connection.commit();

			}

		} catch (Exception e) {
			e.printStackTrace();

			try {
				System.out.println("rolling back.......");

				connection.rollback();

			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		} finally {
			BaseDao.closeResource(connection, null, null);

		}

		return flag;

	}

	// delete Employee by emp_id
	@Override
	public boolean deleteEmployeeById(int empId) throws SQLException {
		Connection connection = null;
		boolean flag = false;

		try {
			connection = BaseDao.getConnection();
			connection.setAutoCommit(false);

			if (empolyeeDao.deleteEmployeeById(connection, empId) > 0) {
				flag = true;
				connection.commit();
			}

		} catch (Exception e) {
			e.printStackTrace();
			try {

				System.out.print("Rolling back....");
				// delete failed
				connection.rollback();

				e.printStackTrace();
			} finally {
				// close connection 
				BaseDao.closeResource(connection, null, null);
			}
		}
		return flag;
	}
	
	public boolean modify(Employee employee) throws Exception {
		
		boolean flag = false;
		
		Connection connection = null;
		
		try {
			connection = BaseDao.getConnection();
			
			if(empolyeeDao.modifyEmployee(connection, employee) > 0) {
				
				flag = true;
				
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		}finally {
			BaseDao.closeResource(connection, null, null);
			
		}
		
		
		
		return flag;
	}

	@Test
	public void test() throws Exception {
		boolean flag = false;
		
		int empId = 6;
		
		flag = deleteEmployeeById(empId);
		System.out.println(flag);
	}
}
