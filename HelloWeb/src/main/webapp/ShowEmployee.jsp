
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>

<%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div>
<span>
<%@ include file="/header.jsp" %>
</span>
</div>

<div class="search">
	<form method="post" action="${pageContext.request.contextPath}/employee.do">
		<input name="method" value="query" class="input-text" type="hidden">
		
		<span>EmployeeName</span> <input name="empName" class="input-text"
			type="text" value="${ employeeName }"> 
		
		<span>EmployeeTitle</span> <input name="empTitle">
	    
	    <input type="hidden" name="pageIndex" value="1" />
	    
	    <input value="Search" type="submit" id="searchbutton">
	    
	    <a href="${pageContext.request.contextPath}/addEmployee.jsp">Add Employee</a>
	</form>
</div>
<!--用户-->
<table class="providerTable" cellpadding="0" cellspacing="0">
	<tr class="firstTr">
		<th width="10%">Employee id</th>
		<th width="20%">Employee name</th>
		<th width="10%">Employee title</th>
		<th width="10%">Employee salary</th>
		<th width="10%">Entry date</th>
		<th width="10%">group</th>
		<th width="30%">operations</th>
	</tr>
	<c:forEach var="employee" items="${ employeelist }" varStatus="status">
		<tr>
			<td><span>${employee.emp_id }</span></td>
			<td><span>${employee.emp_name }</span></td>
			<td><span> ${employee.emp_title }</span></td>
			    
			<td><span>${employee.emp_salary} </span></td>
			<td><span>${employee.entry_date}</span></td>
			<td><span>${employee.group}</span></td>
			
			<td>
			     <span><a class="modifyEmployee"
					href="${pageContext.request.contextPath}/modifyEmployee.jsp?empId=${employee.emp_id}&empName=${employee.emp_name}&empTitle=${employee.emp_title}&empSalary=${employee.emp_salary}&entryDate=${employee.entry_date}&group=${employee.group}">
					
					<img
						src="${pageContext.request.contextPath }/images/modify.png"
						alt="modify" title="modify" /></a></span> 
				    

				<span><a class="deleteEmployee"
					href="${pageContext.request.contextPath}/employee.do?method=delete&empId=${employee.emp_id}">
					<img src="${pageContext.request.contextPath }/images/delete.png" alt="delete"
						title="delete" /></a>
					</span></td>
		</tr>
	</c:forEach>
</table>

<input type="hidden" id="totalPageCount" value="${totalPageCount}" />






