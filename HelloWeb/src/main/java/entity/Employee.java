package entity;

public class Employee {
	
	private String emp_name;
	private String emp_title;
	private int emp_salary;
	private String entry_date;
	private String group;
	private int emp_id;
	
	
	public String getGroup() {
		return group;
	}
	public void setGroup(String group) {
		this.group = group;
	}
	public String getEntry_date() {
		return entry_date;
	}
	public void setEntry_date(String entry_date) {
		this.entry_date = entry_date;
	}
	public int getEmp_salary() {
		return emp_salary;
	}
	public void setEmp_salary(int emp_salary) {
		this.emp_salary = emp_salary;
	}
	public String getEmp_title() {
		return emp_title;
	}
	public void setEmp_title(String emp_title) {
		this.emp_title = emp_title;
	}
	public String getEmp_name() {
		return emp_name;
	}
	public void setEmp_name(String emp_name) {
		this.emp_name = emp_name;
	}
	public int getEmp_id() {
		return emp_id;
	}
	public void setEmp_id(int i) {
		this.emp_id = i;
	}
}
