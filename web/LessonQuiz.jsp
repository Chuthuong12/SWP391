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
                    <h1>Lesson Quiz</h1>
                    <div class="container">
                        <div class="row">
                            <!-- Blog entries-->
                            <div class="col-lg-8">
                                <!-- Featured blog post-->
                                <div class="card mb-4">
                                    <div class="card-body">
                                        <iframe width="800" height="490" src="https://www.youtube.com/embed/${requestScope.lessonByLessonId.video_url}" title="YouTube video player" frameborder="0" 
                                                allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>


                                    </div>
                                </div>
                                <div class ="row mt-5">
                                    <c:forEach var="q" items="${listQuizByLessonId}">
                                        <div class="col-md-4 mb-3 mx-auto d-block shadow p-3 mb-5 bg-white rounded" style="padding: 10px 0px 10px 10px; border-radius: 8px; width: 32%; margin-left: 10px">
                                            <form action="subject-detail" method="POST">
                                                <img class="mx-auto d-block" src="" />
                                                <h2 class="text-center"> ${q.title} </h2>
                                                <ul style="margin-left: 8%">
                                                    <li><b>Total Question: </b>${q.totalQues}</li>
                                                    <li><b>Duration: </b>${q.duration} (mins)</li>
                                                    <li><b>Level: </b>${q.level}</li>
                                                    <li><b>Description: </b>${q.description}</li>
                                                    <li><b>Attempt:</b> ${q.attempt}</li>
                                                </ul>
                                                <div class="text-center">
                                                    <!--                                                    <a href="" class="btn btn-primary"/>Register</a>
                                                                                                        <a href="" class="btn btn-primary"/>Detail</a>-->
                                                    <c:if test="${q.typeId eq 'Q2'}">

                                                        <a href="DoQuizzServlet?quizzId=${q.quizId}&subId=${requestScope.subId}&method=get" class="btn btn-danger"/>Do Quizz</a>
                                                        <a href="QuizDetailReview?quizId=${q.quizId}&subId=${requestScope.subId}" class="btn btn-primary"/>Detail</a>
                                                        <!--<a href="ReviewQuizzServlet?quizzId=${q.quizId}&attempt=1&subId=${requestScope.subId}" class="btn btn-warning mt-2">Review</a>-->
                                                        <!--<a href="simulation-exam?quizzId=&method=get" class="btn btn-warning mt-2"/>Simulation Exam</a>-->



                                                    </c:if>
                                                    <c:if test="${q.typeId eq 'Q1'}">
                                                        <a href="PracticeQuizzServlet?quizzId=${q.quizId}&subId=${requestScope.subId}&method=get" class="btn btn-primary"/>Practice Quizz</a>
                                                    </c:if>

                                                </div>

                                            </form>
                                        </div>
                                    </c:forEach>
                                    <c:if test="${requestScope.WARNING!= null}">
                                        <p style="color: red">${requestScope.WARNING}</p>
                                    </c:if>
                                </div>
                            </div>
                            <!-- Side widgets-->
                            <div class="col-lg-4">
                                <!-- Search widget-->
                                <!--                                <div class="card mb-4">
                                                                    <div class="card-header">Search</div>
                                                                    <div class="card-body">
                                                                        <div class="input-group">
                                                                            <input class="form-control" type="text" placeholder="Enter search term..." aria-label="Enter search term..." aria-describedby="button-search" />
                                                                            <button class="btn btn-primary" id="button-search" type="button"><i class="fas fa-search"></i></button>
                                
                                                                        </div>
                                                                    </div>
                                                                </div>-->
                                <!-- Categories widget-->
                                <div class="card mb-4">
                                    <div class="card-header">Lesson Categories</div>
                                    <div class="list-group">

                                        <c:forEach items="${sessionScope.listLessonBySubId}" var="L">
                                            <a href="lesson-quiz?lessonId=${L.lessonId}&subId=${L.subId}&action=post" class="list-group-item list-group-item-action ${L.lessonId == requestScope.lesId?"list-group-item-warning":""}">${L.lessonName}: ${L.content}</a>
                                        </c:forEach>
                                    </div>
                                </div>
                            </div>
                        </div>

                    </div>
                </div>
            </div>
        </div>

    </body>
</html>
