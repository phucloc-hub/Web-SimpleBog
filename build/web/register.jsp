<%-- 
    Document   : register
    Created on : Jan 7, 2020, 3:30:06 PM
    Author     : Loc
--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register Page</title>
    </head>
    <body>
        <c:set var="check" value="true" />
        <form action="ServletDispatcher" method="POST">
            <!--              Email <input type="text" name="txtEmail" value="" />(6 - 12 chars)<br>
                        Name <input type="text" name="txtUsername" value="" />(6 - 12 chars)<br>
                        Password  <input type="password" name="txtPassword" value="" />(6 - 12 chars)<br>
                        Confirm   <input type="password" name="txtConfirm" value="" />(6 - 12 chars)<br>
            -->


            <c:set var="errors" value="${requestScope.ERROR}"/>
            Email <input type="text" name="txtEmail" value="${param.txtEmail}" />(6 - 12 chars)
            <c:if test="${not empty errors.emailLength}">
                <font color="red">
                ${errors.emailLength}
                <c:set var="check" value="false"/>

                </font>

            </c:if>
            <c:if test="${check eq true}">
                <c:if test="${not empty errors.emailFormat}">
                    <font color="red">
                    ${errors.emailFormat}
                    <c:set value="false" var="check"/>
                    </font>

                </c:if>    
                    
            </c:if>
            <c:if test="${check eq true}">
                <c:if test="${not empty errors.emailDup}">
                    <font color="red">
                    ${errors.emailDup}
                    <c:set value="false" var="check"/>
                    </font>

                </c:if>   
            </c:if>

                    <br>
            Name <input type="text" name="txtUsername" value="${param.txtUsername}" />(6 - 12 chars)
            <c:if test="${not empty errors.userNameLength}">
                <font color="red">
                ${errors.userNameLength}
                <c:set var="check" value="false" />
                </font>

            </c:if><br>
           <br>
            Password <input type="password" name="txtPassword" value="" />(6 - 12 chars)
            <c:if test="${not empty errors.passwordLength}">
                <font color="red">
                ${errors.passwordLength}
                <c:set var="check" value="false" />
                </font>

            </c:if><br>
            <br>
            Confirm <input type="password" name="txtConfirm" value="" />(6 - 12 chars)
            <c:if test="${not empty errors.confirmNotMatchPassword}">
                <font color="red">
                ${errors.confirmNotMatchPassword}
                <c:set var="check" value="false" />
                </font>

            </c:if><br>
            <br>
            <br>
            <input type="submit" value="Register" name="btAction" />
            <input type="reset" value="Reset" />

            <font color="blue">
            <a href="login.html"> Back to login? </a>
            </font>

            <c:if test="${check eq true}">
                <font color="blue">

                Registerd Successfully
                </font>
                <!--alert('Xin chào các bạn')-->
                <div onkeyup="alert('Xin chào các bạn')">
                    
                </div>
                
                <c:redirect url="login.html"/>


            </c:if>


        </form>
    </body>
</html>
