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

                    <div class ="row mt-5">
                        <h1>Edit Lesson</h1>
                        <c:choose>
                            <c:when test="${requestScope.table_lesson == 'lesson'}">
                                <form action="filter-lesson" method="POST" class="d-flex" style="width: 600px; float: right !important">
                                    <div class="input-group">
                                        <!--                                    <select name="status" class="form-control ms-2 mb-2">
                                                                                <option value="2">All lesson groups</option>
                                                                                <option value="1">Active</option>
                                                                                <option value="0">Inactive</option>
                                                                            </select>-->
                                        <select name="status" class="form-control ms-2 mb-2">
                                            <option value="2" ${requestScope.status == 2?"selected":""}>All status</option>
                                            <option value="1" ${requestScope.status == 1?"selected":""}>Active</option>
                                            <option value="0" ${requestScope.status == 0?"selected":""}>Inactive</option>
                                        </select>
                                            <input type="search" name="keyword" value="${sessionScope.keyword}" id="form1" class="form-control ms-2 mb-2 py-3" style="height: 38px !important;" placeholder="Search"/>
                                        <!--<label class="form-label" for="form1">Search</label>-->
                                        <button type="submit" class="btn btn-primary mb-2">
                                            <i class="fas fa-search"></i>
                                        </button>
                                    </div>
                                </form>
                            </c:when>
                        </c:choose>

                        <div class="mb-3 mx-auto d-block shadow p-3 mb-5 bg-white rounded ps-5 pt-5" style="padding: 10px 0px 10px 10px; border-radius: 8px; width: 32%; margin-left: 10px; width: 100% !important">
                            <table class="table table-bordered table-hover table-striped row">


                                <c:choose>
                                    <c:when test="${requestScope.table_lesson == 'subject'}">

                                        <thead>
                                            <tr class="row text-center">
                                                <th class="col-1">ID</th>
                                                <th class="col-6">Subject</th>
                                                <th class="col-3">Status</th>
                                                <th class="col-2">Action</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach items="${listSubjects}" var="S">
                                                <tr class="row">
                                                    <td class="col-1 text-center">${S.subjectId}</td>
                                                    <td class="col-6">${S.subjectName}</td>
                                                    <td class="col-3 text-center">${S.status == 'true'?"Active":"Inactive"}</td>
                                                    <td class="col-2 text-center"><a href="subject-lesson-detail?subId=${S.subjectId}" class="btn btn-primary"/>Lesson</a></td>
                                                </tr>
                                            </c:forEach>
                                        </tbody>

                                    </c:when>
                                    <c:when test="${requestScope.table_lesson == 'lesson'}">
                                        <a class="badge bg-secondary text-decoration-none link-light me-4 mb-3" style="float: right" href="ViewAddLessonServlet">Add New</a>
                                        <thead>
                                            <tr class="row text-center">
                                                <th class="col-1">ID</th>
                                                <th class="col-4">Lesson</th>
                                                <th class="col-2">Type Name</th>
                                                <th class="col-2">Status</th>
                                                <th class="col-3">Action</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach items="${sessionScope.listLessonBySubId}" var="L">
                                                <tr class="row">
                                                    <th class="col-1 text-center">${L.lessonId}</th>
                                                    <th class="col-4">${L.lessonName}</th>
                                                    <th class="col-2 text-center">${L.typeName}</th>
                                                    <th class="col-2 text-center">${L.status == 'true'?"Active":"Inactive"}</th>
                                                    <th class="col-3 text-center">
                                                        <a href="ViewLessonDetailServlet?txtLessonId=${L.lessonId}" class="btn btn-primary"/>Edit</a>
                                                        <c:if test="${L.status == 'true'}">
                                                            <a href="update_status_lesson?inactiveLessonId=${L.lessonId}" class="btn btn-danger ms-3"/>Inactive</a>
                                                        </c:if>
                                                        <c:if test="${L.status == 'false'}">
                                                            <a href="update_status_lesson?activeLessonId=${L.lessonId}" class="btn btn-primary ms-3"/>Active</a>
                                                        </c:if>

                                                    </th>
                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                    </c:when> 
                                </c:choose>
                            </table>
                        </div><br>
                    </div>
                </div>
            </div>
        </div>

        
    </body>
</html>
