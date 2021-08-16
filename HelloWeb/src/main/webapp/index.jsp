<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false" %>
<!DOCTYPE html>

<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>HelloWeb:v1</title>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath }/css/style.css" />
</head>
<body class="login_bg">
    <section class="loginBox">
        <header class="loginHeader">
            <h1>HelloWeb:v1</h1>
        </header>
        <section class="loginCont">
            <form class="loginForm" action="login"  name="actionForm" id="actionForm"  method="post" >
                <div class="info"> ${errormsg} </div>
                <div class="inputbox">
                    <label for="userCode"> Username: </label>
                    <input type="text" class="input-text" id="username" name="username" placeholder="please input your username" required/>
                </div>  
                <div class="inputbox">
                    <label for="password">password:</label>
                    <input type="password" id="password" name=password placeholder="please input your password" required/>
                </div>  
                <div class="subBtn">
                    <input type="submit" value="login"/>
                    <input type="reset" value="reset"/>
                </div>  
            </form>
        </section>
    </section>
</body>
</html>
