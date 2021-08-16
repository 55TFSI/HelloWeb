<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>Employee Management</title>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath }/css/style.css" />
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath }/css/public.css" />
</head>
<body>
<!--头部-->
    <header class="publicHeader">
        <h1>Employee Management</h1>
        <div class="publicHeaderR">
            <p><span>Hi! </span><span style="color: #fff21b"> ${userSession.username}</span> </p>
        </div>
    </header>
 <!--主体内容-->
 <section class="publicMian ">
     <input type="hidden" id="path" name="path" value="${pageContext.request.contextPath }"/>
     <input type="hidden" id="referer" name="referer" value="<%=request.getHeader("Referer")%>"/>