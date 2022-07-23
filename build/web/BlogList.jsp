<%-- 
    Document   : test
    Created on : May 25, 2022, 8:15:23 PM
    Author     : ADMIN
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>

        <link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
        <!-- Bootstrap icons-->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet" />
        <link href="css/dashboard.css" rel="stylesheet" />
        <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/js/all.min.js" crossorigin="anonymous"></script>
        <!--        <script src="js/scripts.js"></script>-->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
        <!-- MDB -->
        <link rel="icon" href="img/mdb-favicon.ico" type="image/x-icon" />
        <!-- Font Awesome -->
        <link
            rel="stylesheet"
            href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css"
            />
        <!-- Google Fonts Roboto -->
        <link
            rel="stylesheet"
            href="https://fonts.googleapis.com/css2?family=Roboto:wght@300;400;500;700;900&display=swap"
            />
        <!-- MDB -->
        <link rel="stylesheet" href="css/dashboard.css" />
        <link rel="stylesheet" href="css/mdb.min.css" />
        <!-- MDB -->
        <script type="text/javascript" src="js/navbarCategory.js"></script>
        <script type="text/javascript" src="js/mdb.min.js"></script>
        <!-- Custom scripts -->
        <script type="text/javascript"></script>
    </head>

    <body class="sb-sidenav-toggled">
        <%@include file="components/navBarComponent.jsp" %>

        <div id="layoutSidenav" class="mb-4">
            <%@include file="components/catgoryComponent_1.jsp" %>
            <div id="layoutSidenav_content">
                <div class="container-fluid px-4 px-lg-5 mb-5" style="margin-top: 91px">
                    <h1>Blog List</h1>
                    <div class="container">
                        <div class="row">
                            <!-- Blog entries-->
                            <div class="col-lg-8">
                                <!-- Featured blog post-->
                                <div class="card mb-4">
                                    <a href="#!"><img class="card-img-top" src="uploads/${lastPost.thumbnail}" alt="..." /></a>
                                    <div class="card-body">
                                        <div class="small text-muted">${lastPost.created_date}</div>
                                        <h2 class="card-title">${lastPost.title}</h2>
                                        <p class="card-text">${lastPost.content}</p>
                                        <div class="text-center">
                                            <a href="post-detail?postId=${lastPost.postId}" class="btn btn-primary"/>Detail</a>
                                        </div>
                                    </div>
                                </div>
                                <!-- Blog post-->

                                <div class ="row mt-5">
                                    <c:forEach var="P" items="${listPosts}">
                                        <div class="col-md-4 mb-3 mx-auto d-block shadow p-3 mb-5 bg-white rounded" style="padding: 10px 0px 10px 10px; border-radius: 8px; width: 32%; margin-left: 10px">
                                            <form action="subject-detail" method="POST">
                                                <img class="mx-auto d-block img-fluid" src="uploads/${P.thumbnail}" width="400" height="400" />
                                                <h2 class="text-center"> ${P.title} </h2>
                                                <ul style="margin-left: 8%">
                                                    <li><b>Last edit: ${P.edit_date}</b></li>
                                                    <li><b>Infor: ${P.brifInfor}</b></li>
                                                    <li><b>Status: ${P.status == 'true'?"Active":"Inactive"}</b></li>
                                                </ul>
                                                <div class="text-center">
                                                    <a href="post-detail?postId=${P.postId}" class="btn btn-primary"/>Detail</a>
                                                </div>

                                            </form>
                                        </div>
                                    </c:forEach>
                                </div>
                            </div>
                            <!-- Side widgets-->
                            <div class="col-lg-4">
                                <div class="card mb-4">
                                    <div class="card-header">Blog Categories</div>
                                    <div class="list-group">
                                        <c:forEach items="${listBlogs}" var="B">
                                            <a href="post-list?blogId=${B.blogId}&blogAction=post" class="list-group-item list-group-item-action ${B.blogId == requestScope.blogId?"list-group-item-warning":""}">${B.blogName}</a>
                                        </c:forEach>
                                    </div>
                                </div>
                            </div>

                        </div>
                    </div>
                    <!-- Phan trang -->
                    <c:choose>
                        <c:when test="${listPosts==null || listPosts.size()==0}">
                            Not founds
                        </c:when>
                        <c:when test="${totalPage < 2}">
                            <nav aria-label="Page navigation example" class="d-flex justify-content-center">
                                <ul class="pagination">
                                    <c:forEach begin="1" end="${totalPage}" var="i">
                                        <li class="page-item ${i == page?"active":""}"><a class="page-link" href="${pagination_url}page=${i}">${i}</a></li>
                                        </c:forEach>
                                </ul>
                            </nav>
                        </c:when>
                        <c:when test="${page < 2}">
                            <nav aria-label="Page navigation example" class="d-flex justify-content-center">
                                <ul class="pagination">  
                                    <c:forEach begin="1" end="${totalPage}" var="i">
                                        <li class="page-item ${i == page?"active":""}"><a class="page-link" href="${pagination_url}page=${i}">${i}</a></li>
                                        </c:forEach>
                                    <li class="page-item"><a class="page-link" href="${pagination_url}page=${page+1}">Next</a></li>
                                </ul>
                            </nav>
                        </c:when>
                        <c:when test="${page+1 > totalPage}">
                            <nav aria-label="Page navigation example" class="d-flex justify-content-center">
                                <ul class="pagination">
                                    <li class="page-item"><a class="page-link" href="${pagination_url}page=${page-1}">Previous</a></li>
                                        <c:forEach begin="1" end="${totalPage}" var="i">
                                        <li class="page-item ${i == page?"active":""}"><a class="page-link" href="${pagination_url}page=${i}">${i}</a></li>
                                        </c:forEach>
                                </ul>
                            </nav>
                        </c:when>
                        <c:otherwise>
                            <nav aria-label="Page navigation example" class="d-flex justify-content-center">
                                <ul class="pagination">
                                    <li class="page-item"><a class="page-link" href="${pagination_url}page=${page-1}">Previous</a></li>
                                        <c:forEach begin="1" end="${totalPage}" var="i">
                                        <li class="page-item ${i == page?"active":""}"><a class="page-link" href="${pagination_url}page=${i}">${i}</a></li>
                                        </c:forEach>
                                    <li class="page-item"><a class="page-link" href="${pagination_url}page=${page+1}">Next</a></li>
                                </ul>
                            </nav>
                        </c:otherwise>
                    </c:choose>


                </div>
            </div>
    </body>
</html>

