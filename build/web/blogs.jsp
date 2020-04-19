<%-- 
    Document   : blogs
    Created on : Jan 7, 2020, 3:33:26 PM
    Author     : Loc
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <!--<head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <title>JSP Page</title>
        </head>-->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Blog Home</title>

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

            <c:set var="USERKIND" value="${sessionScope.USERKIND}"/>
            <c:if test="${USERKIND ne 'Admin'}">
                
            <div class="btn btn-dark">
                <li>

                    <a href="post.jsp"> click to post</a>

                </li>

            </div>
            <div onclick="" class="btn btn-dark"> 
                <form action="ServletDispatcher" method="POST">
                    <input type="submit" value="ViewOwnArticle" name="btAction" />

                </form>

            </div>
            </c:if>


        
        
        <div>
            <form action="ServletDispatcher">
                <input class="btn btn-outline-info" type="submit" value="Home" name="btAction" />

            </form>   
        </div>



        <!--<h1>Hello World! </h1>-->
        <div class="container">

            <div class="row">
                <div class="col-md-8">

                    <c:set var="LISTARTICLE" value="${sessionScope.LISTARTICLE}" />

                    <c:if test="${not empty LISTARTICLE}">

                        <c:forEach items="${LISTARTICLE}" var="dtoArticle">
                            <div class="card mb-4">
                                <div class="card-body">
                                    <h2 class="card-title">

                                        <c:url var="urlRewriting" value="ServletDispatcher">
                                            <c:param name="btAction" value="ViewArticle"/>
                                            <!--do dung filter tu dong chuyen qua servlet xu li-->
                                            <!--tuong ung nen khong can gui btAction ve-->
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
                                </div>
                            </div> 


                        </c:forEach>
                        <c:set var="TOTALPAGE" value="${sessionScope.TOTALPAGE}"/>
                        
                        <c:if test="${TOTALPAGE gt 1}">
                        <ul class="pagination justify-content-center mb-4">
                            <li class="page-item">
                                
                                <form action="ServletDispatcher">
                                    <input type="hidden" name="contents" value="${param.txtSearch}" />
                                    <input class="page-link" type="submit" name="btAction" value="Older" />
                                    
                                </form>
                                
<!--                                <a class="page-link disabled" href="">&larr; Older</a>-->
                            </li>
                            <li class="page-item ">
                                 <form action="ServletDispatcher">
                                     <input type="hidden" name="contents" value="${param.txtSearch}" />
                                    <input class="page-link" type="submit" name="btAction" value="Newer" />
                                    
                                </form>
                                <!--<a class="page-link" href="#">Newer &rarr;</a>-->
                            </li>
                            
                            <!--ServletDispatcher?btAction=ViewArticle&id=12-->
                        </ul>  
                        </c:if>
                    </c:if>

                    <c:if test="${empty LISTARTICLE}">

                        <font style="font: bolder" color="red">                       
                        No record!!   
                        </font>

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
