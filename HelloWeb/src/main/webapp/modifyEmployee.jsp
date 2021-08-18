<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false" %>
<%@ include file="/header.jsp"%>

<% String empId=request.getParameter("empId"); %>
<% String empName=request.getParameter("empName"); %>
<% String empTitle=request.getParameter("empTitle"); %>
<% String empSalary=request.getParameter("empSalary"); %>
<% String entryDate=request.getParameter("entryDate"); %>
<% String group=request.getParameter("group"); %>
<div class="right">

    <div class= "providerAdd" >
        <form id="providerAdd" name="providerAdd" method="post"
            action="${pageContext.request.contextPath}/employee.do">
            
            <input type="hidden" name="method" value="modify">
            <div>
                <label for="empId">Employee Id</label> 
                <input  type="text" readonly="readonly"
                    name="empId" id="empId" value= <%= empId %> >
                <!-- 放置提示信息 -->
                <font color="red"></font>
            </div>
            <div>
                <label for="empName">Employee Name</label> 
                <input required type="text"
                    name="empName" id="empName" value= <%= empName %>>
                <!-- 放置提示信息 -->
                <font color="red"></font>
            </div>
            <div>
                <label for="empTitle">Employee Title</label>
                <input required type="text"
                    name="empTitle" id="emptitle" value= <%= empTitle %>> <font color="red"></font>
            </div>
            <div>
                <label for="empSalary">Salary</label> 
                <input type="number"
                    name="empSalary" id="empSalary" value= <%= empSalary %> > <font
                    color="red"></font>
            </div>
            
            <div>
                <label for="entryDate">Entry Date: </label> 
                <input required type="date"
                    name="entryDate" id="entryDate" value= <%= entryDate %> > <font
                    color="red"></font>
            </div>
            <div>
                <label for ="group">Group</label> 
                <input type="text" required
                    name="group" id="group" value= <%= group %>> <font
                    color="red"> </font>
                
            </div>
            <div class="employeeAddBtn">
                <input type="submit" name="add" id="modify" value="modify"> 
                
                <input type="reset" id="reset" name="reset" value="Reset">
            </div>
        </form>
    </div>
</div>

