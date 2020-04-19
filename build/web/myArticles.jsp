<%-- 
    Document   : myArticles
    Created on : Jan 10, 2020, 11:31:24 AM
    Author     : Loc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>My Article</title>

    <!-- Bootstrap core CSS -->
    <link href="showPage/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="showPage/css/blog-home.css" rel="stylesheet">
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
        <div style="margin-top:3%" class="btn btn-outline-info">
            <a href="blogs.jsp">Home</a>

        </div>
        <div class="container">

            <div class="row">
                <div class="col-md-8">

                    <c:set var="MYARTICLE" value="${requestScope.MYARTICLE}"/>
                    <c:if test="${not empty MYARTICLE}">
                        <c:forEach items="${MYARTICLE}" var="article">
                            <div class="card mb-4">
                                <div class="card-body">
                                    <h2 class="card-title">
                                        <c:if test="${article.status ne 'New'}">
                                            <c:url var="urlRewriting" value="ServletDispatcher">
                                                <c:param name="btAction" value="ViewArticle"/>
                                                <!--do dung filter tu dong chuyen qua servlet xu li-->
                                                <!--tuong ung nen khong can gui btAction ve-->
                                                <c:param name="id" value="${article.articleID}"/>
                                            </c:url>

                                            <a href="${urlRewriting}"> ${article.title} </a> 

                                        </c:if>
                                        <c:if test="${article.status eq 'New'}">
                                            ${article.title}

                                        </c:if>

                                    </h2>
                                    <p class="card-text">${article.description}</p>
                                    <p class="card-text">${article.contents} </p>
                                    <!--<a href="#" class="btn btn-primary">Read More &rarr;</a>-->
                                </div>
                                <div class="card-footer text-muted">
                                    Posted on ${article.datePost} 
                                    Status : 


                                    <c:set var="status" value="${article.status}"/>
                                    <c:if test="${status eq 'New'}">
                                        <font color="blue">
                                        Processing 
                                        </font>

                                    </c:if>
                                    <c:if test="${status eq 'Active'}">
                                        <font color="green">
                                        Active
                                        </font>

                                    </c:if>
                                    <c:if test="${status eq 'Deleted'}">
                                        <font color="red">
                                        Deleted
                                        </font>

                                    </c:if>

                                </div>
                            </div> 

                        </c:forEach>



                    </c:if>


                    <c:if test="${empty MYARTICLE}">

                        <font color="red" class="text-center">
                        No record!
                        </font>



                    </c:if>


                </div>
            </div>
        </div>




    </body>
</html>
