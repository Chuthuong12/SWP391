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
                    <h1>Question List</h1>

                    <!-- Button trigger modal -->
                    <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal">
                        Add Question
                    </button>
                    
                    <!-- Modal -->
                    <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <form action="InsertQuestion">
                                    <div class="modal-header">
                                        <h5 class="modal-title" id="exampleModalLabel">Insert Question</h5>
                                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                    </div>
                                    <div class="modal-body">
                                        <p>Insert Content</p>
                                        <input type="hidden" name="subId" value="${requestScope.subId}" />
                                        <input type="hidden" name="quizId" value="${requestScope.quizId}">
                                        <input type="hidden" name="lessonId" value="${requestScope.lessonId}" />
                                        <input type="text" name="content" value="" class="form-control">
                                        <label class="form-check-label" for="flexCheckChecked">Is Multiple</label>
                                        <input class="form-check-input" name="isMultiple" type="checkbox" value="" id="flexCheckChecked">
                                        <br>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                                        <input type="submit" value="insert" name="action" class="btn btn-primary"/>
                                    </div>
                                </form>

                            </div>
                        </div>
                    </div>
                    <div class ="row" style="margin-top: 80px">

                        <div class="mb-3 mx-auto d-block shadow p-3 mb-5 bg-white rounded" style="padding: 10px 0px 14px 24px !important; border-radius: 8px; width: 32%; margin-left: 10px; width: 100% !important">
                            <table class="table table-bordered table-hover table-striped row">
                                <thead>
                                    <tr class="row text-center">
                                        <th class="col-1">ID</th>
                                        <th class="col-3">content</th>
                                        <th class="col-1">subjectId</th>
                                        <th class="col-2">level</th>
                                        <th class="col-1">status</th>
                                        <th class="col-1">is Multiple</th>
                                        <th class="col-3">Action</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach items="${requestScope.QUESTIONS}" var="Q">
                                        <tr class="row">
                                            <th class="col-1 text-center">${Q.questionId}</th>
                                            <th class="col-3 text-center">${Q.content}</th>
                                            <th class="col-1 text-center">${Q.subjectId}</th>
                                            <th class="col-2 text-center">${Q.level}</th>
                                            <th class="col-1 text-center">${Q.status}</th>
                                            <th class="col-1 text-center">${Q.isIsMultipleChoice()}</th>
                                            <th class="col-3 text-center">
                                                <c:if test="${Q.status == 'true'}">
                                                    <a href="hide-question?questionId=${Q.questionId}" class="btn btn-danger ms-3"/>Inactive</a>
                                                </c:if>
                                                <c:if test="${Q.status == 'false'}">
                                                    <a href="show-question?questionId=${Q.questionId}" class="btn btn-success ms-3"/>Active</a>
                                                </c:if>
                                                <a href="question-detail?questionId=${Q.questionId}&action=edit-quiz" class="btn btn-primary"/>Detail</a>
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



    </body>
</html>
