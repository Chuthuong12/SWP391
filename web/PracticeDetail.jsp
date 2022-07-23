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

        <div id="layoutSidenav">
            <%@include file="components/catgoryComponent_1.jsp" %>
            <div id="layoutSidenav_content">
                <div class="container px-4 px-lg-5 mb-5" style="margin-top: 91px">
                    <h3>Practice Details</h3>
                    <div class ="row" style="margin-top: 80px">

                        <div class="mb-3 mx-auto d-block shadow p-3 mb-5 bg-white rounded" style="padding: 10px 24px 14px 24px !important; border-radius: 8px; width: 32%; margin-left: 10px; width: 100% !important">
                            <form class="row d-flex" action="practiceQuiz" method="post">
                                <div class="col-6 mt-3">
                                    Subject: <select name="subject" class="form-control" onchange="PracticeDetailBySubAsync(this.value)">
                                        <c:forEach var="s" items="${listSubjects}">
                                            <c:if test="${sessionScope.action=='add'}">
                                                <option value="${s.subjectId}">${s.subjectName}</option>
                                            </c:if>
                                            <c:if test="${sessionScope.action=='detail'}">
                                                <option value="${s.subjectId}"  ${practice.subId==s.subjectId?"selected":""} >${s.subjectName}</option>
                                            </c:if>
                                        </c:forEach>     
                                    </select> 
                                </div>
                                <div class="col-6 mt-3">
                                    Quiz: <select id="practice_quiz" name="quiz" class="form-control">
                                        <c:forEach var="q" items="${listQuizs}">
                                            <c:if test="${sessionScope.action=='add'}">
                                                <option value="${q.quizId}">${q.title}</option>
                                            </c:if>
                                            <c:if test="${sessionScope.action=='detail'}">
                                                <option value="${q.quizId}"  ${practice.quizzId==q.quizId?"selected":""} >${q.title}</option>
                                            </c:if>
                                        </c:forEach>   
                                    </select>
                                </div>
                                <div class="col-6 mt-3">
                                    Title: <input type="text" class="form-control" name="title" value="${sessionScope.action == 'add'?"":practice.title}" placeholder="Title"/></br>
                                </div>
                                <div class="col-6 mt-3">
                                    Number of practicing question: <input type="text" class="form-control" name="numOfQues" value="${sessionScope.action == 'add'?"":practice.numOfQues}" placeholder="Number of Question"/>   </br>
                                </div>
                                <c:if test="${sessionScope.action=='add'}">
                                    <div class="col-11 mt-3 text-center">
                                        <button type="submit" class="btn btn-primary">Practice</button>
                                    </div>
                                    <div class="col-1 mt-3">
                                        <a href="practice-list" class="btn btn-success" style="float: right">Back</a>
                                    </div>
                                </c:if>
                                <c:if test="${sessionScope.action=='detail'}">
                                    <div class="col-12 mt-3">
                                        <a href="practice-list" class="btn btn-success" style="float: right">Back</a>
                                    </div>
                                </c:if>
                            </form>
                            <!-- Call to Action-->
                        </div>
                    </div>

                </div>
            </div>
        </div>
    </body>
    <script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
    <script>
                                        function PracticeDetailBySubAsync(subId) {
                                            axios.get('practice-detail-async', {
                                                params: {
                                                    subId: subId
                                                }
                                            }).then((response) => {
                                                document.getElementById("practice_quiz").innerHTML = response.data;
                                            })
                                        }
    </script>
</html>
