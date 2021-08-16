package service;

import java.util.List;

import entity.Employee;

public interface EmployeeService {
	
	// select number of employee
	public int getEmployeeNumber(String empName, String empTitle);
	
	
	// get employee list 
	public List<Employee> getEmployeeList(String empName, String empTitle, int currentPageNo, int pageSize);
	
	// add employee
	public boolean addEmployee(Employee employee);
	
	// delete employee
	public boolean deleteEmployeeById(int empId) throws Exception;
	
	// Modify employee
	public boolean modify(Employee employee) throws Exception;
}
