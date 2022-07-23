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
                    <h1>Question Detail</h1>
                    <a href="QuestionList?" class="btn btn-primary">Back</a>
                    <div class ="row" style="margin-top: 80px">
                        <div class="mb-3 mx-auto d-block shadow p-3 mb-5 bg-white rounded" style="padding: 10px 24px 14px 24px !important; border-radius: 8px; width: 32%; margin-left: 10px; width: 100% !important">
                            <div class="row mt-3">
                                <p>Question: </p>
                                <form action="updateQuestion" class="row">
                                    <input type="hidden" name="questionId" value="${requestScope.QUESTION.questionId}">
                                    
                                    <div class="col-11">
                                        <input class="form-control" type="text" name="questionContent" value="${requestScope.QUESTION.content}">
                                    </div>
                                    <div class="col-1">
                                        <button type="submit" name="action" value="Update" class="btn btn-success">Update</button>
                                    </div>

                                </form>
                            </div>
                            <div class="row">
                                <br>
                            </div>
                            <ol type="A">
                                <c:forEach var="a" items="${requestScope.ANSWER}">
                                    <li>
                                        <form action="updateAnswer" class="row">
                                            <input type="hidden" name="questionId" value="${requestScope.QUESTION.questionId}">
                                            <input type="hidden" name="answerId" value="${a.answerId}">
                                            <div class="col-6">
                                                <input class="form-control col-6" type="text" name="answerContent" value="${a.content}">
                                            </div>
                                            <div class="col-1">
                                                <select name="isCorrect" id="true" class="form-control">
                                                    <option value="${a.correct}" selected="">${a.correct}</option>
                                                    <option value="true">True</option>
                                                    <option value="false">False</option>
                                                </select>
                                            </div>
                                            <div class="col-3">
                                                <button type="submit" name="action" value="Update" class="btn btn-success">Update</button>
                                                <button type="submit" name="action" value="Delete" class="btn btn-danger">Delete</button>
                                            </div>
                                        </form>
                                    </li>
                                    <br>
                                </c:forEach>
                                <form action="updateAnswer">
                                    <li>
                                        <div class="row">
                                            <input type="hidden" name="questionId" value="${requestScope.QUESTION.questionId}">
                                            <div class="col-6">
                                                <input class="form-control col-6" type="text" name="answerContent" value="">
                                            </div>
                                            <div class="col-1">
                                                <select name="isCorrect" id="true" class="form-control">
                                                    <option value="true" selected="">True</option>
                                                    <option value="false">False</option>
                                                </select>
                                            </div>
                                            <div class="col-3">
                                                <button type="submit" name="action" value="Add" class="btn btn-primary">Add New</button>
                                            </div>
                                        </div>
                                    </li>
                                </form>

                            </ol>

                        </div><br>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
