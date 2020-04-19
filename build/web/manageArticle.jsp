<%-- 
    Document   : manageArticle
    Created on : Jan 13, 2020, 9:57:17 AM
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


        <div class="container">
            <div class="row">

                <div class="col-md-8">
                    <form action="ServletDispatcher">
                        <select name="statusOption">
                            <option>New</option>
                            <option>Deleted</option>
                            <option>Active</option>

                        </select> 
                        <select name="Bywhat">
                            <option>Contents</option>
                            <option>Title</option>
                        </select>
                        <input type="text" name="txtSearchAdmin" value="${param.txtSearchAdmin}" />
                        
                        <input class="btn btn-secondary" type="submit" name="btAction" value="SearchAdmin" />



                    </form>

                    <c:set var="LISTARTICLE" value="${sessionScope.LISTARTICLE}"/>
                    <c:if test="${not empty LISTARTICLE}">


                        <form action="ServletDispatcher" method="POST">
                            <input class="btn btn-dark" type="submit" value="DeleteMul" name="btAction"/>
                            <c:forEach items="${LISTARTICLE}" var="dtoArticle">
                                <div class="card mb-4">
                                    <div class="card-body">
                                        <h2 class="card-title">

                                            <c:url var="urlRewriting" value="ServletDispatcher">
                                                <c:param name="btAction" value="ViewArticle"/>
                                                
                                                <c:param name="id" value="${dtoArticle.articleID}"/>
                                            </c:url>

                                            <a href="${urlRewriting}"> ${dtoArticle.title} </a>

                                        </h2>
                                        <p class="card-text">${dtoArticle.description}</p>
                                        <!--<a href="#" class="btn btn-primary">Read More &rarr;</a>-->
                                    </div>
                                    <div class="card-footer text-muted">
                                        Posted on ${dtoArticle.datePost} by
                                        <a href="#">${dtoArticle.author}</a>
                                        Status: <font color="green">
                                        ${dtoArticle.status}
                                        </font>

                                    </div>
                                </div> 
                                <div class="btn btn-danger">

                                    <input type="checkbox" name="multipleChk" value="${dtoArticle.articleID}" />
                                </div>



                            </c:forEach>
                        </form>
                        <c:set var="SEARCHEDADMIN" value="${sessionScope.SEARCHEDADMIN}"/>
                        <c:if test="${SEARCHEDADMIN ne 'SEARCHEDADMIN'}">
                            
                        
                        <ul class="pagination justify-content-center mb-4">
                            <li class="page-item">
                                
                                <form action="ServletDispatcher">
                                    <input class="page-link" type="submit" name="btAction" value="Older" />
                                    
                                </form>
                                
<!--                                <a class="page-link disabled" href="">&larr; Older</a>-->
                            </li>
                            <li class="page-item ">
                                 <form action="ServletDispatcher">
                                    <input class="page-link" type="submit" name="btAction" value="Newer" />
                                    
                                </form>
                                <!--<a class="page-link" href="#">Newer &rarr;</a>-->
                            </li>
                            
                            <!--ServletDispatcher?btAction=ViewArticle&id=12-->
                        </ul>  
                        </c:if>

                    </c:if>
                    
                    <c:if test="${empty LISTARTICLE}">
                        
                        <h2 style="color: red"> No record! </h2>
                        
                    </c:if>

                </div>


            </div>


        </div>








    </body>
</html>
