<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false" %>
<%@ include file="/header.jsp"%>


<div class="right">

	<div class ="providerAdd">
		<form id="employeeForm" name="employeeForm" method="post"
			action="${pageContext.request.contextPath}/employee.do">
			
			<input type="hidden" name="method" value="add">
			
			<div>
				<label for="empName">Employee Name</label> 
				<input required type="text"
					name="empName" id="empName" value="">
				<!-- 放置提示信息 -->
				<font color="red"></font>
			</div>
			<div>
				<label for="empTitle">Employee Title</label>
				<input required type="text"
					name="empTitle" id="Emptitle" value=""> <font color="red"></font>
			</div>
			<div>
				<label for="empSalary">Salary</label> 
				<input type="number"
					name="empSalary" id="empSalary" value=""> <font
					color="red"></font>
			</div>
			
			<div>
				<label for="entryDate">Entry Date: </label> 
				<input required type="date"
					name="entryDate" id="entryDate" value=""> <font
					color="red"></font>
			</div>
			<div>
				<label for ="group">Group</label> 
				<input type="text" required
                    name="group" id="group" value=""> <font
                    color="red"> </font>
				
			</div>
			<div class="employeeAddBtn">
				<input type="submit" name="add" id="add" value="Add"> 
				
				<input
					type="Reset" id="reset" name="reset" value="reset">
			</div>
		</form>
	</div>
</div>

