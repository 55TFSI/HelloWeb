package web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.StringUtils;

import entity.Employee;
import service.EmployeeService;
import service.EmployeeServiceImpl;

/**
 * Servlet implementation class Employee
 */
public class EmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmployeeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("get");
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("post");
		String method = request.getParameter("method");
		System.out.println("method :" + method);
		
		if(method != null && method.equals("add") ) {
			this.add(request, response);
			
		}else if(method != null && method.equals("query")) {
			System.out.println("going query");
			this.query(request, response);
			
		}else if (method != null && method.equals("delete")) {
			System.out.println("deleting");
			try {
				this.delete(request, response);
				
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}else if (method != null && method.equals("modify")) {
			try {
				this.modify(request, response);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private void add(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		boolean flag = false;
		String empName = request.getParameter("empName");
		String empTitle = request.getParameter("empTitle");
		
		int empSalary = Integer.parseInt(request.getParameter("empSalary"));
		//System.out.println(empSalary);
		
		String entryDate = request.getParameter("entryDate");
		System.out.println(entryDate);
		
		String group = request.getParameter("group");
		
		Employee emp = new Employee();
		
		
		emp.setEmp_name(empName);
		emp.setEmp_title(empTitle);
		emp.setEmp_salary(empSalary);
		emp.setEntry_date(entryDate);
		emp.setGroup(group);
		
		EmployeeService empSer = new EmployeeServiceImpl();
		
		flag = empSer.addEmployee(emp);
		
		if (flag == true) {
			System.out.println("Add succeed ");
			response.sendRedirect(request.getContextPath()+ "/employee.do?method=query");
		}else {
			System.out.println("Add failed ");
			request.getRequestDispatcher("addEmployee.jsp").forward(request, response);
		}
		
		
	}
	
	private void query(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("Start Servlet");
		
		String empName = request.getParameter("empName");
		
		String empTitle = request.getParameter("empTitle");
		
		if(StringUtils.isNullOrEmpty(empName)){
			empName = "";
		}
		if(StringUtils.isNullOrEmpty(empTitle)){
			empTitle = "";
		}
		
		List<Employee> empList = new ArrayList<Employee>();
		
		EmployeeService empServ = new EmployeeServiceImpl();
		
		empList = empServ.getEmployeeList(empName, empTitle, 1, 20);
		
		request.setAttribute("employeelist", empList);
		request.setAttribute("employeeTitle", empTitle);
		request.setAttribute("employeeName", empName);
		
		System.out.println("list:" + empList.toString());
		
		request.getRequestDispatcher("ShowEmployee.jsp").forward(request, response);
		
	}
	private void delete(HttpServletRequest request, HttpServletResponse response) throws NumberFormatException, Exception {
		
		boolean flag = false;
		
		String empId = request.getParameter("empId");
		System.out.println(empId);
		
		if(!StringUtils.isNullOrEmpty(empId.toString())) {
			
			EmployeeService empServ = new EmployeeServiceImpl();
			
			// Todo get result
			flag = empServ.deleteEmployeeById(Integer.parseInt(empId));
			
			if (flag == false) {
				System.out.println("delete failed");
			}else {
				System.out.println("delete successed");
				response.sendRedirect(request.getContextPath()+ "/employee.do?method=query");
			}
		}
	}
	private void modify(HttpServletRequest request, HttpServletResponse response) throws Exception {
		boolean flag = false;
		int empId = Integer.parseInt(request.getParameter("empId"));
		String empName = request.getParameter("empName");
		String empTitle = request.getParameter("empTitle");
		
		int empSalary = Integer.parseInt(request.getParameter("empSalary"));
		//System.out.println(empSalary);
		
		String entryDate = request.getParameter("entryDate");
		System.out.println(entryDate);
		
		String group = request.getParameter("group");
		Employee emp = new Employee();
		
		emp.setEmp_name(empName);
		emp.setEmp_title(empTitle);
		emp.setEmp_salary(empSalary);
		emp.setEntry_date(entryDate);
		emp.setGroup(group);
		emp.setEmp_id(empId);
		
		EmployeeService empServ = new EmployeeServiceImpl();
		
		flag = empServ.modify(emp);
		if(flag){
			response.sendRedirect(request.getContextPath()+"/employee.do?method=query");
		}else{
			request.getRequestDispatcher("modifyEmployee.jsp").forward(request, response);
		}
		
	}
	
	

}
