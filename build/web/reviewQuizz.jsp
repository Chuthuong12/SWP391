<%-- 
    Document   : doQuizz
    Created on : Jun 27, 2022, 5:34:11 PM
    Author     : Fangl
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <body class="sb-sidenav-toggled" onload="timerfunction()">
        <%@include file="components/navBarComponent.jsp" %>
        <div id="layoutSidenav" class="mb-4">
            <%@include file="components/catgoryComponent_1.jsp" %>
            <div id="layoutSidenav_content">
                <div class="container-fluid px-4 px-lg-5 mb-5" style="margin-top: 91px">
                    <p class="fs-2 fw-bold">${requestScope.QUIZZ.title}</p>
                    <p class="fs-4">Your Point: ${requestScope.QUIZZ_POINT.point}</p>
                    <div class="container">
                        <div class="row">
                            <div class="col-lg-12">

                                <!-- Blog entries-->
                                <form action="DoQuizzServlet" method="post" id="myForm">
                                    <input type="hidden" name="quizzId" value="${requestScope.quizzId}" />
                                    <div class="row">
                                        <div class="col-12">
                                            <div>
                                                <ol type="1">
                                                    <c:forEach var="questionMap" items="${sessionScope.REVIEW_QUIZZ}">
                                                        <div class="mb-3 mx-auto d-block shadow p-3 mb-5 bg-white rounded" style="padding: 10px 10px 10px 10px; border-radius: 8px; width: 32%; margin-left: 10px; width: 100% !important">
                                                            <li class="ms-3">
                                                                <span>${questionMap.key.content} </span>
                                                                <input type="hidden" name="questionId" value="${questionMap.key.questionId}">
                                                                <ol type="A">
                                                                    <c:if test="${questionMap.key.isIsMultipleChoice() eq 'true'}">  <!-- Là Multiple choice -->
                                                                        <c:forEach var="ans" items="${questionMap.value}">
                                                                            <c:if test="${ans.correct eq 'true'}"> <!-- Là câu đúng -->
                                                                                <c:if test="${ans.userCheck eq 'true'}"> <!-- Được ng dùng chọn -->
                                                                                    <li class="d-flex mb-1">
                                                                                        <input type="checkbox" checked="check" name="answer_${questionMap.key.questionId}" value="${ans.answerId}" class="col-1" style="width: 14px" disabled>
                                                                                        <span class="col-11"><input class="bg-success col-6 form-control ms-2" type="text" name="name" value="${ans.content}" checked="check" readonly="">TRUE </span>
                                                                                    </li>   
                                                                                </c:if>
                                                                                <c:if test="${ans.userCheck ne 'true'}"> <!-- Không được ng dùng chọn -->
                                                                                    <li class="d-flex mb-1">
                                                                                        <input type="checkbox" name="answer_${questionMap.key.questionId}" value="${ans.answerId}" class="col-1" style="width: 14px" disabled>
                                                                                        <span class="col-11"><input class="bg-primary  col-6 form-control ms-2" type="text" name="name" value="${ans.content}" checked="check" readonly="">TRUE</span>
                                                                                    </li>    
                                                                                </c:if>
                                                                            </c:if>
                                                                            <c:if test="${ans.correct ne 'true'}"><!-- Là câu sai -->
                                                                                <c:if test="${ans.userCheck eq 'true'}"><!-- Được ng dùng chọn -->
                                                                                    <li class="d-flex mb-1">
                                                                                        <input type="checkbox" checked="check" name="answer_${questionMap.key.questionId}" value="${ans.answerId}" class="col-1" style="width: 14px" disabled>
                                                                                        <span class="col-11"><input class="bg-danger col-6 form-control ms-2" type="text" name="name" value="${ans.content}" checked="check" readonly="">Fail </span>
                                                                                    </li>   
                                                                                </c:if>
                                                                                <c:if test="${ans.userCheck ne 'true'}"><!-- Không được ng dùng chọn -->
                                                                                    <li class="d-flex mb-1">
                                                                                        <input type="checkbox" name="answer_${questionMap.key.questionId}" value="${ans.answerId}" class="col-1" style="width: 14px" disabled>
                                                                                        <span class="col-11"><input class=" col-6 form-control ms-2" type="text" name="name" value="${ans.content}" checked="check" readonly=""></span>
                                                                                    </li>   
                                                                                </c:if>
                                                                            </c:if>
                                                                        </c:forEach>
                                                                    </c:if>
                                                                    <c:if test="${questionMap.key.isIsMultipleChoice() ne 'true'}"> <!-- Không phải câu Multiple choice -->
                                                                        <c:forEach var="ans" items="${questionMap.value}">
                                                                            <c:if test="${ans.correct eq 'true'}">
                                                                                <c:if test="${ans.userCheck eq 'true'}">
                                                                                    <li class="d-flex mb-1">
                                                                                        <input type="radio" checked="check" name="answer_${questionMap.key.questionId}" value="${ans.answerId}" class="col-1" style="width: 14px" disabled>
                                                                                        <span class="col-11"><input class="bg-success col-6 form-control ms-2" type="text" name="name" value="${ans.content}" checked="check" readonly="">TRUE </span>
                                                                                    </li>   
                                                                                </c:if>
                                                                                <c:if test="${ans.userCheck ne 'true'}">
                                                                                    <li class="d-flex mb-1">
                                                                                        <input type="radio" name="answer_${questionMap.key.questionId}" value="${ans.answerId}" class="col-1" style="width: 14px" disabled>
                                                                                        <span class="col-11"><input class="bg-primary col-6 form-control ms-2" type="text" name="name" value="${ans.content}"  checked="check" readonly="">TRUE</span>
                                                                                    </li>    
                                                                                </c:if>
                                                                            </c:if>
                                                                            <c:if test="${ans.correct ne 'true'}">
                                                                                <c:if test="${ans.userCheck eq 'true'}">
                                                                                    <li class="d-flex mb-1">
                                                                                        <input type="radio" checked="check" name="answer_${questionMap.key.questionId}" value="${ans.answerId}" class="col-1" style="width: 14px" disabled>
                                                                                        <span class="col-11"><input class="bg-danger col-6 form-control ms-2" type="text" name="name" value="${ans.content}" checked="check" readonly="">Fail </span>
                                                                                    </li>   
                                                                                </c:if>
                                                                                <c:if test="${ans.userCheck ne 'true'}">
                                                                                    <li class="d-flex mb-1">
                                                                                        <input type="radio" name="answer_${questionMap.key.questionId}" value="${ans.answerId}" class="col-1" style="width: 14px" disabled>
                                                                                        <span class="col-11"><input class=" col-6 form-control ms-2" type="text" name="name" value="${ans.content}" checked="check" readonly=""></span>
                                                                                    </li>   
                                                                                </c:if>
                                                                            </c:if>
                                                                        </c:forEach>
                                                                    </c:if>
                                                                </ol>
                                                            </li>
                                                        </div>
                                                        <br>
                                                    </c:forEach>
                                                </ol>
                                            </div>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>

                    </div>
                </div>
            </div>
        </div>





    </body>
</html>
