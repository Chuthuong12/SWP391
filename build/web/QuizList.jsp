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
                    <h1>Quiz List</h1>
                    <c:if test="${sessionScope.checkSuccess == 'false'}">
                        <p class="text-danger">${sessionScope.messageStatusQuiz}</p>
                    </c:if>
                    <c:if test="${sessionScope.checkSuccess == 'true'}">
                        <p class="text-success">${sessionScope.messageStatusQuiz}</p>
                    </c:if>
                    <form action="search-quiz" method="POST" style="width: 900px; float: right !important">

                        <div class="input-group">
                            <select name="subId" class="form-control ms-2" style="margin: 2px">
                                <option value="0">All Subject</option>
                                <c:forEach items="${listSubjects}" var="S">
                                    <option value="${S.subjectId}" ${S.subjectId == requestScope.subId?"selected":""}>${S.subjectName}</option>
                                </c:forEach>
                            </select>
                            <select name="typeId" class="form-control ms-2" style="margin: 2px">
                                <option value="Q0">All Type</option>
                                <c:forEach items="${listTypeQuizes}" var="TQ">
                                    <option value="${TQ.typeId}" ${TQ.typeId == requestScope.typeId?"selected":""}>${TQ.typeName}</option>
                                </c:forEach>
                            </select>
                            <input type="search" name="keyword" id="form1" value="${requestScope.keyword}" class="form-control ms-2 mb-2 py-3" style="height: 38px !important;" placeholder="Search"/>
                            <button type="submit" class="btn btn-primary mb-2">
                                <i class="fas fa-search"></i>
                            </button>
                            <a href="quiz-detail?action=add-quiz&message=0" class="btn btn-primary ms-4 pt-2" style="border-radius: 5px; height: 39px; padding-top: 10px; padding-top: 14px !important;"/>Add New</a>
                        </div>
                    </form>
                    <div class ="row" style="margin-top: 80px">

                        <div class="mb-3 mx-auto d-block shadow p-3 mb-5 bg-white rounded" style="padding: 10px 0px 14px 24px !important; border-radius: 8px; width: 32%; margin-left: 10px; width: 100% !important">
                            <table class="table table-bordered table-hover table-striped row">
                                <thead>
                                    <tr class="row text-center">
                                        <th class="col-1">ID</th>
                                        <th class="col-3">Title</th>
                                        <th class="col-1">Level</th>
                                        <th class="col-2">Subject</th>
                                        <th class="col-1">Durantion</th>
                                        <th class="col-1">Pass Rate</th>
                                        <th class="col-3">Action</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach items="${sessionScope.listQuizzesByPagging}" var="Q">
                                        <tr class="row">
                                            <th class="col-1 text-center">${Q.quizId}</th>
                                            <th class="col-3 text-center">${Q.title}</th>
                                            <th class="col-1 text-center">${Q.level}</th>
                                            <th class="col-2 text-center">${Q.subjectName}</th>
                                            <th class="col-1 text-center">${Q.duration}</th>
                                            <th class="col-1 text-center">${Q.rate}</th>
                                            <th class="col-3 text-center">
                                                <c:if test="${Q.status == 'true'}">
                                                    <a href="hide-quiz?quizId=${Q.quizId}" class="btn btn-danger ms-3"/>Inactive</a>
                                                </c:if>
                                                <c:if test="${Q.status == 'false'}">
                                                    <a href="show-quiz?quizId=${Q.quizId}" class="btn btn-success ms-3"/>Active</a>
                                                </c:if>
                                                <a href="quiz-detail?quizId=${Q.quizId}&action=edit-quiz&message=0" class="btn btn-primary"/>Detail</a>
                                                <a href="QuestionListAdminServlet?quizId=${Q.quizId}&subId=${Q.subId}&lessonId=${Q.lessonId}" class="btn btn-warning"/>Ques</a>
                                            </th>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div><br>
                    </div>
                </div>
            </div>
        </div>
        <c:choose>
            <c:when test="${sessionScope.listQuizzesByPagging==null || sessionScope.listQuizzesByPagging.size()==0}">
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
