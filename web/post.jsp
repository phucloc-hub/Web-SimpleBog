<%-- 
    Document   : post
    Created on : Jan 10, 2020, 11:01:43 AM
    Author     : Loc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="showPage/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

        <!-- Custom styles for this template -->
        <link href="showPage/css/blog-home.css" rel="stylesheet">
    </head>
    <body>

        <c:set var="fullname" value="${sessionScope.FULLNAME}"/>

        <c:if test="${empty fullname}">
            <c:redirect url="login.html">

            </c:redirect>


        </c:if>
        <c:if test="${not empty fullname}">
            <div style="background-color: aliceblue;color: red;width: 100%;float: left;height: 43px">
                Hello ${fullname} |  <form action="ServletDispatcher"> <input type="submit" value="Logout" name="btAction" style="border: 5px;border-bottom-color: black;border-radius: 5px" />
                </form>


            </div>



        </c:if>


        <div style="margin-top:3% " class="btn btn-outline-info">
            <a href="blogs.jsp">Home</a>

        </div>
        <div class="container" style="background-color: #0069d9">
            <div>

                <c:set var="ERROR" value="${requestScope.ERROR}"/>
                <form action="ServletDispatcher" method="POST">

                    <table border="1">
                        <tr>
                            <td> Title </td>
                            <td> <input type="text" name="txtTitle" value="${param.txtTitle}" class="form-control" placeholder="Title..."> </td>
                                <c:if test="${not empty ERROR.titleLength}">
                                <td>
                                    <font color="red">
                                    ${ERROR.titleLength}
                                    </font>

                                </td>

                            </c:if>
                        </tr>
                        <tr>
                            <td> Description </td>
                            <td><input type="text" name="txtDescription" value="${param.txtDescription}" class="form-control" placeholder="Description..."></td>
                                <c:if test="${not empty ERROR.descriptionLength}">
                                <td>
                                    <font color="red">
                                    ${ERROR.descriptionLength}
                                    </font>

                                </td>

                            </c:if>
                        </tr>
                        <tr>
                            <td> Contents </td>
                            <td> <textarea type="text" name="txtContents" value="${param.txtContents}" class="form-control" placeholder="Content..."></textarea></td>

                            <c:if test="${not empty ERROR.contentsLength}">
                                <td>
                                    <font color="red">
                                    ${ERROR.contentsLength}
                                    </font>

                                </td>

                            </c:if>
                        </tr>    

                        <!--<a href="#" class="btn btn-primary">Post &rarr;</a>-->
                        <c:set var="USERKIND" value="${sessionScope.USERKIND}"/>
                        <c:if test="${USERKIND ne 'Admin'}">
                            <input type="submit" class="btn btn-dark" value="Post" name="btAction" />  
                        </c:if>


                    </table>
                </form>


            </div>


        </div>
    </body>
</html>
