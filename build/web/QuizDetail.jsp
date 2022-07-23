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

    <style>
        body {font-family: Arial;}

        /* Style the tab */
        .tab {
            overflow: hidden;
            border: 1px solid #ccc;
            background-color: #f1f1f1;
        }

        /* Style the buttons inside the tab */
        .tab button {
            background-color: inherit;
            float: left;
            border: none;
            outline: none;
            cursor: pointer;
            padding: 14px 16px;
            transition: 0.3s;
            font-size: 17px;
        }

        /* Change background color of buttons on hover */
        .tab button:hover {
            background-color: #ddd;
        }

        /* Create an active/current tablink class */
        .tab button.active {
            background-color: #ccc;
        }

        /* Style the tab content */
        .tabcontent {
            display: none;
            padding: 6px 12px;
            border: 1px solid #ccc;
            border-top: none;
        }
    </style>

    <body class="sb-sidenav-toggled">
        <%@include file="components/navBarComponent.jsp" %>

        <div id="layoutSidenav" class="mb-4">
            <%@include file="components/catgoryComponent_1.jsp" %>
            <div id="layoutSidenav_content">
                <div class="container-fluid px-4 px-lg-5 mb-5" style="margin-top: 91px">
                    <h1>Quiz Detail</h1>
                    <div class ="row mt-5">
                        <div class="mb-3 mx-auto d-block shadow p-3 bg-white rounded" style="padding: 10px 0px 10px 10px; border-radius: 8px; width: 32%; margin-left: 10px; width: 100% !important">
                            <div class="tab list-group list-group-horizontal" style="width: 30%; margin-left: 75px">
                                <button class="tablinks list-group-item list-group-item-action active" onclick="openCity(event, 'Overview')">Overview</button>
                                <button class="tablinks list-group-item list-group-item-action" onclick="openCity(event, 'Setting')">Setting</button>
                            </div>
                            <form action="${sessionScope.action}" method="post">
                                <div id="Overview" class="tabcontent" style="border: none; display: block;">
                                    
                                    <div class ="row mt-3 mx-5">
                                        <c:if test="${sessionScope.CheckEditQuiz == 'true'}">
                                            <p class="text-success">${sessionScope.messageQuiz}</p>
                                        </c:if>
                                        <c:if test="${sessionScope.CheckEditQuiz == 'false'}">
                                            <p class="text-danger">${sessionScope.messageQuiz}</p>
                                        </c:if>
                                        <div class="my-3 col-12">
                                            <c:if test="${sessionScope.action == 'edit-quiz'}">
                                                <b>Name <input type="text" class="form-control" id="title" name="title" placeholder="Quiz Name" value="${requestScope.quizByQuizId.title}"></b>
                                                </c:if>
                                                <c:if test="${sessionScope.action == 'add-quiz'}">
                                                <b>Name <input type="text" class="form-control" id="title" name="title" placeholder="Quiz Name"></b>
                                                </c:if>

                                        </div>
                                        <div class="my-3 col-12">
                                            <b>Subject </b> 
                                            <select name="subId" class="form-control" onchange="SubjectToLessonAsync(this.value)">
                                                <c:forEach items="${listSubjects}" var="S">
                                                    <option value="${S.subjectId}" ${S.subjectId == requestScope.quizByQuizId.subId?"selected":""}>${S.subjectName}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                        <div class="my-3 col-12" id="update_lesson">
                                            <b>Lesson </b> 
                                            <select name="lessonId" class="form-control">
                                                <c:forEach items="${listLessons}" var="L">
                                                    <option value="${L.lessonId}" ${L.lessonId == requestScope.quizByQuizId.lessonId?"selected":""}>${L.lessonName}: ${L.content}</option>
                                                </c:forEach>
                                            </select>
                                        </div>

                                        <div class="my-3 col-12">  
                                            <c:if test="${sessionScope.action == 'edit-quiz'}">
                                                <b>Description: <br><textarea name="description" rows="3" style="width: 100%; padding: 10px" placeholder="Quiz Description">${requestScope.quizByQuizId.description}</textarea></b>
                                                </c:if>
                                                <c:if test="${sessionScope.action == 'add-quiz'}">
                                                <b>Description: <br><textarea name="description" rows="3" style="width: 100%; padding: 10px" placeholder="Quiz Description"></textarea></b>
                                                </c:if>

                                        </div>
                                        <div class="text-center mt-5">
                                            <a class="btn btn-primary" onclick="openCity(event, 'Setting')"/>Next Setting</a>
                                        </div>
                                    </div>
                                </div>

                                <div id="Setting" class="tabcontent" style="border: none; display: none;">
                                    <div class ="row mt-5 mx-5">
                                        <div class="my-3 col-7">
                                             <c:if test="${sessionScope.action == 'edit-quiz'}">
                                                 <b class="col-2 pt-1">Total Question <input type="text" class="form-control" id="totalQues" name="totalQues" value="${requestScope.quizByQuizId.totalQues}" placeholder="Total Question"></b>
                                                </c:if>
                                                <c:if test="${sessionScope.action == 'add-quiz'}">
                                                    <b class="col-2 pt-1">Total Question <input type="text" class="form-control" id="totalQues" name="totalQues" placeholder="Total Question"></b>
                                                </c:if>
                                            
                                        </div>
                                        <div class="my-3 col-5">
                                             <c:if test="${sessionScope.action == 'edit-quiz'}">
                                                 <b class="col-2 pt-1">Attempt <input type="text" class="form-control" id="attempt" name="attempt" value="${requestScope.quizByQuizId.attempt}" placeholder="Total Question"></b>
                                                </c:if>
                                                <c:if test="${sessionScope.action == 'add-quiz'}">
                                                    <b class="col-2 pt-1">Attempt <input type="text" class="form-control" id="attempt" name="attempt" placeholder="Attempt"></b>
                                                </c:if>
                                            
                                        </div>
                                        <div class="my-3 col-6">
                                            <b>Exam Level </b> 
                                            <select name="level" class="form-control">
                                                <c:if test="${sessionScope.action == 'edit-quiz'}">
                                                    <option value="Easy" ${requestScope.quizByQuizId.level == "Easy"?"selected":""}>Easy</option>
                                                    <option value="Medium" ${requestScope.quizByQuizId.level == "Medium"?"selected":""}>Medium</option>
                                                    <option value="Hard" ${requestScope.quizByQuizId.level == "Hard"?"selected":""}>Hard</option>
                                                </c:if>
                                                <c:if test="${sessionScope.action == 'add-quiz'}">
                                                    <option value="Easy">Easy</option>
                                                    <option value="Medium">Medium</option>
                                                    <option value="Hard">Hard</option>
                                                </c:if>

                                            </select>
                                        </div>
                                        <div class="my-3 col-6">
                                            <c:if test="${sessionScope.action == 'edit-quiz'}">
                                                <b>Duration(mins) <input type="text" class="form-control" id="duration" name="duration" value="${requestScope.quizByQuizId.duration}" placeholder="Quiz Duration"></b>
                                                </c:if>
                                                <c:if test="${sessionScope.action == 'add-quiz'}">
                                                <b>Duration(mins) <input type="text" class="form-control" id="duration" name="duration" placeholder="Quiz Duration"></b>
                                                </c:if>

                                        </div>
                                        <div class="my-3 col-6">
                                            <c:if test="${sessionScope.action == 'edit-quiz'}">
                                                <b>Pass Rate(%) <input type="text" class="form-control" id="rate" name="rate" value="${requestScope.quizByQuizId.rate}" placeholder="Pass Rate"></b>
                                                </c:if>
                                                <c:if test="${sessionScope.action == 'add-quiz'}">
                                                <b>Pass Rate(%) <input type="text" class="form-control" id="rate" name="rate" placeholder="Pass Rate"></b>
                                                </c:if>

                                        </div>
                                        <div class="my-3 col-6">
                                            <b>Quiz Type </b> 
                                            <select name="typeId" class="form-control">
                                                <c:forEach items="${listTypeQuizes}" var="TQ">
                                                    <option value="${TQ.typeId}" ${requestScope.quizByQuizId.typeId == TQ.typeId?"selected":""}>${TQ.typeName}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                        <div class="text-center mt-5">
                                            <c:if test="${sessionScope.action == 'edit-quiz'}">
                                                <button class="btn btn-success"/>Save</button>
                                            </c:if>
                                            <c:if test="${sessionScope.action == 'add-quiz'}">
                                                <button class="btn btn-primary"/>Add</button>
                                            </c:if>

                                            <a class="btn btn-danger" onclick="openCity(event, 'Overview')"/>Back</a>
                                        </div>
                                    </div>
                                </div>
                            </form>

                        </div>
                        <div class="form-row">
                            <div class="form-group col-md-12">
                                <a href="quiz-list" class="btn btn-danger" style="float: right !important">Back</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </body>

    <script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
    <script>
                                                function openCity(evt, cityName) {
                                                    var i, tabcontent, tablinks;
                                                    tabcontent = document.getElementsByClassName("tabcontent");
                                                    for (i = 0; i < tabcontent.length; i++) {
                                                        tabcontent[i].style.display = "none";
                                                    }
                                                    tablinks = document.getElementsByClassName("tablinks");
                                                    for (i = 0; i < tablinks.length; i++) {
                                                        tablinks[i].className = tablinks[i].className.replace(" active", "");
                                                    }
                                                    document.getElementById(cityName).style.display = "block";
                                                    evt.currentTarget.className += " active";
                                                }

                                                function SubjectToLessonAsync(subId) {
                                                    axios.get('edit-quiz-async', {
                                                        params: {
                                                            subId: subId
                                                        }
                                                    }).then((response) => {
                                                        document.getElementById("update_lesson").innerHTML = response.data;
                                                    })
                                                }
    </script>
</html>
