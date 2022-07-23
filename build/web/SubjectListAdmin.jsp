<%-- 
    Document   : subject
    Created on : May 25, 2022, 9:42:14 PM
    Author     : 84969
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
                <div class="container-fluid px-4 px-lg-5 mb-5 row" style="margin-top: 91px">
                    <h1>Subject List</h1>
                    <div class="col-md-12">
                        <!--                    <div>
                                                <article style="float: right">
                                                    <h6>
                                                        <a href="DispatchServlet?btAction=CreateForm">Add new subject </a>
                                                    </h6>
                        
                                                </article>
                                            </div>
                        
                                            <article  style="float: right">
                                                <header>
                                                    <h6>Filter by status</h6>
                                                </header>
                                                <div>
                                                    <form action="DispatchServlet" method="post">
                                                        <input type="radio" name="statusFilter" value="1" checked="checked" /> Active
                                                        <input type="radio" name="statusFilter" value="0" checked="checked" /> Un-Active
                                                        <button name="btAction" value="Search" type="submit">Filter</button>
                                                    </form>
                                                </div>
                                            </article>-->

                        <form action="DispatchServlet" method="POST" class="d-flex" style="width: 600px; float: right !important">
                            <div class="input-group">
                                <input type="radio" name="statusFilter" value="1" class="mx-2 my-1"/>Active
                                <input type="radio" name="statusFilter" value="0" class="mx-2 my-1"/>Inactive
                                <button name="btAction" value="Filter" type="submit" class="btn btn-primary ms-4" style="border-radius: 5px !important">Filter</button>
                                <a href="DispatchServlet?btAction=CreateForm" class="btn btn-primary ms-4" style="border-radius: 5px"/>Add New Subject</a>
                            </div>
                        </form>
                    </div>
                    <c:if test="${message !=null}">
                        <p>${message}</p>
                    </c:if>

                    <div class ="row mt-5">
                        <c:forEach var="s" items="${listSubjects}">
                            <div class="col-md-4 mb-3 mx-auto d-block shadow p-3 mb-5 bg-white rounded" style="padding: 10px 0px 10px 10px; border-radius: 8px; width: 32%; margin-left: 10px">
                                <div>
                                    <h4>${s.getSubjectId()}</h4>
                                    <img class="mx-auto d-block rounded-circle img-thumbnail" src="${s.status == 'false'?"photo/":""}${s.getThumbnail()}"/>
                                    <h2 class="text-center"> ${s.getSubjectName()} </h2>
                                    <!--                                    <input id="file-upload" type="file" name="photo"/>-->
                                </div>
                                <div class="card-body bg-white mt-0"  style="margin-left: 8%">
                                    <b class="mb-1">Status: </b> ${s.status == 'true'?"Active":"Inactive"}<br>
                                    <b class="mb-1">Title: </b>${s.getTitle()}<br>
                                    <b class="mb-1">Tag Line: </b>${s.getTagLine()}<br>
                                    <b class="mb-1">Description: </b>${s.getDescription()}<br>
                                </div>

                                <div class="text-center">
                                    <a href="DispatchServlet?btAction=EditSubject&id=${s.getSubjectId()}">Edit</a>

                                </div>
                            </div>
                        </c:forEach>

                    </div>

                </div>
            </div>
        </div>
        <c:choose>
            <c:when test="${listSubjects==null || listSubjects.size()==0}">
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


    </body>
</html>
