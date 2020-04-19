<%-- 
    Document   : Login
    Created on : Jan 7, 2020, 2:49:37 PM
    Author     : Loc
--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
    </head>
    <body>
        <form action="ServletDispatcher" method="POST">
            Username <input type="text" name="txtEmail" value="${sessionScope.Email}" /><br/>
            Password <input type="password" name="txtPassword" value="" /><br/>          
            <input type="submit" value="Login" name="btAction" />
            <input type="reset" value="Reset" />
            <a href="register.html">Have not an account?</a><br>
            <button> <a href="blogs.jsp"> Blogs </a> </button><br>
            <c:if test="${empty sessionScope.FULLNAME}">
                <font color="red"> Cannot found this account!! </font>
                
            </c:if>
            
        </form>
    </body>
</html>
