<%-- 
    Document   : articleDetails
    Created on : Jan 9, 2020, 8:38:42 AM
    Author     : Loc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>

<html>
    <!--    <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <title>JSP Page</title>
        </head>-->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Blog Details</title>

    <!-- Bootstrap core CSS -->
    <link href="showPage/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="showPage/css/blog-home.css" rel="stylesheet">
    <body>


        <c:set var="fullname" value="${sessionScope.FULLNAME}"/>
        <c:if test="${empty fullname}">
            <div class="loginform" style="background-color: aliceblue;align-content: flex-end; float: left; border: 2px;width: 100%">
                <a href="login.html">Login?</a><br>
                <a href="register.html">Have not an account?</a><br>  
            </div>     



        </c:if>    


        <c:if test="${not empty fullname}">
            <div style="background-color: aliceblue;color: red;width: 100%;float: left;height: 43px">
                Hello ${fullname} |  <form action="ServletDispatcher"> <input type="submit" value="Logout" name="btAction" style="border: 5px;border-bottom-color: black;border-radius: 5px" />
                </form>


            </div>



        </c:if>
        <c:set var="USERKIND" value="${sessionScope.USERKIND}" />
        <c:if test="${USERKIND ne 'Admin'}">
            
            
        <div class="btn btn-dark">
            <li>
                <a href="post.jsp"> click to post</a>

            </li>

        </div>
            
        </c:if>    
            
        <div>
            <form action="ServletDispatcher">

                <c:set var="USERKIND" value="${sessionScope.USERKIND}" />
                <c:if test="${USERKIND eq 'Admin'}">
                    
                    <input class="btn btn-outline-info" type="submit" name="btAction" value="Manage" />  
                </c:if>
                <c:if test="${USERKIND ne 'Admin'}">
                    
                    <input class="btn btn-outline-info" type="submit" name="btAction" value="Home" />
                </c:if>



            </form>


        </div>




        <div class="container">

            <div class="row">
                <div class="col-md-8">

                    <c:set var="ARTDETAILS" value="${sessionScope.ARTDETAILS}" />


                    <c:if test="${not empty ARTDETAILS}">
                        <div class="card-footer text-muted">
                            Posted on ${ARTDETAILS.datePost} by
                            <a href="#">${ARTDETAILS.author}</a>
                        </div>

                        <div class="card mb-4">
                            <div class="card-body">
                                <h2 class="card-title">

                                    ${ARTDETAILS.title}

                                </h2>
                                <p style="color: chartreuse" class="card-text">${ARTDETAILS.description}</p>    

                                <p class="card-text">${ARTDETAILS.contents}</p>
                            </div>


                        </div> 

                        <table style="table-layout: auto">

                            <tbody>
                                <tr>


                                    <!--COMMENT INFO TO COMMENTSERVLET-->
                            <form action="ServletDispatcher" method="POST">
                                <c:set var="USERKIND" value="${sessionScope.USERKIND}"/>
                                <c:if test="${USERKIND ne 'Admin'}">
                                    <input type="text" name="txtComment" value="${param.txtComment}" />
                                    <input type="hidden" name="articleID" value="${ARTDETAILS.articleID}" />
                                    <input type="hidden" name="author" value="${sessionScope.Email}" />  
                                </c:if>





                                <c:if test="${not empty fullname}"> 
                                    <span class="input-group-btn">


                                        <c:if test="${USERKIND ne 'Admin'}">
                                            <button class="btn btn-secondary" type="submit" name="btAction" value="Comment">Post Comment</button>
                                        </c:if>

                                        <!--<input type="submit" value="Login" name="btAction" />-->
                                    </span>

                                </c:if>

                            </form>
                            <c:if test="${empty fullname}">
                                <span class="input-group-btn">

                                    <!--<input type="submit" value="Login" name="btAction" />-->
                                    <!--<a href="login.html"> Post Comment </a>--> 

                                    <!--JAVASCRIP CODE-->
                                    <button onclick="window.location.href = 'login.html'" class="btn btn-secondary">Post Comment</button>

                                </span>
                            </c:if>


                            </tr>


                            <c:set var="listComments" value="${requestScope.LISTCOMM}"/>

                            <c:if test="${not empty listComments}">
                                <thead>
                                    <tr>
                                        <th>Comments</th>
                                    </tr>
                                </thead>
                                <c:forEach items="${listComments}" var="commentdto">


                                    <tr>
                                        <td>${commentdto.author}</td>
                                        <td>${commentdto.contents}</td>
                                        <td>${commentdto.datePost}</td>
                                    </tr>   


                                </c:forEach> 

                            </c:if>






                            </tbody>
                        </table>


                    </c:if>
                    <c:if test="${USERKIND eq 'Admin'}">
                        <form action="ServletDispatcher" method="POST">

                            <input type="hidden" name="ArticleID" value="${ARTDETAILS.articleID}" />
                            <input class="btn btn-dark" type="submit" name="btAction" value="StatusToDeleted" />
                            <input class="btn btn-dark" type="submit" name="btAction" value="StatusToActive" />

                        </form>
                    </c:if>


                    <!--end of show contents-->
                    <c:if test="${empty ARTDETAILS}">

                        This contents have beeen removed!

                    </c:if>


                </div>

                <div class="col-md-4">

                    <!-- Search Widget -->
                    <div class="card my-4">
                        <h5 class="card-header">Search</h5>
                        <div class="card-body">
                            <div class="input-group">

                                <form action="ServletDispatcher" method="POST">


                                    <input type="text" name="txtSearch" value="${param.txtSearch}" class="form-control" placeholder="Search for...">
                                    <span class="input-group-btn">
                                        <!--<input type="submit" value="Login" name="btAction" />-->
                                        <button class="btn btn-secondary" type="submit" name="btAction" value="Search">Go!</button>
                                    </span>
                                </form>


                            </div>
                        </div>
                    </div>
                </div>

            </div>
        </div>

    </body>
</html>

