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
                    <div class="position-relative">
                        <div class="position-absolute top-0 end-0">
                            <c:if test="${requestScope.UPDATELESSON_MSG != null}">
                                <div class="alert-success" role="alert">
                                    ${requestScope.UPDATELESSON_MSG}
                                </div>
                            </c:if>
                        </div>
                    </div>
                    <h1>Edit Lesson</h1>
                    <c:if test="${requestScope.LESSON != null}">

                        <form class="" action="UpdateLessonDetailServlet" method="post">
                            <div class="form-row">
                                <div class="form-group col-md-8">
                                    <input type="hidden" name="txtLessonId" value="${requestScope.LESSON.lessonId}" />
                                    <label class="form-label" for="name">name</label>
                                    <input class="form-control" type="text" name="txtName" id="name" value="${requestScope.LESSON.lessonName}">
                                </div>
                                <div class="form-group col-md-4">
                                    <label class="form-label" for="txtType">type</label>
                                    <select class="form-control custom-select" name="txtType" id="txtType">
                                        <c:forEach var="type" items="${requestScope.TYPE}">
                                            <c:if test="${requestScope.LESSON.typeId == type.typeId}">
                                                <option class="" selected value="${requestScope.LESSON.typeId}">${type.typeName}</option> 
                                            </c:if>
                                            <c:if test="${requestScope.LESSON.typeId != type.typeId}">
                                                <option class="" value="${type.typeId}">${type.typeName}</option>
                                            </c:if>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <div class="form-row">
                                <div class="form-group col-md-8">
                                    <label class="form-label" for="txtTopic">Topic</label>
                                    <select class="form-control custom-select" name="txtTopic" id="txtType">
                                        <c:forEach var="topic" items="${requestScope.TOPIC}">
                                            <c:if test="${requestScope.LESSON.topicId == topic.topicId}">
                                                <option class="" selected value="${requestScope.LESSON.topicId}">${topic.topicName}</option> 
                                            </c:if>
                                            <c:if test="${requestScope.LESSON.topicId != topic.topicId}">
                                                <option class="" value="${topic.topicId}">${topic.topicName}</option>
                                            </c:if>
                                        </c:forEach>

                                    </select>
                                </div>
                                <div class="form-group col-md-4">
                                    <div class="form-group">
                                        <label class="form-label" for="Order">Order</label>
                                        <input class="form-control" type="text" name="txtOrder" id="Order" value="${requestScope.LESSON.order}">
                                    </div>
                                </div>
                            </div>

                            <div class="form-row">
                                <div class="form-group col-md-12">
                                    <label class="form-label" for="videoLink">Video Link</label>
                                    <input class="form-control" type="text" name="txtVideoLink" id="videoLink" value="${requestScope.LESSON.video_url}">
                                </div>
                            </div>
                            <div class="form-row">
                                <div class="form-group col-md-12">
                                    <label class="form-label" for="content">HTML Content</label>
                                    <textarea class="form-control" name="txtContent" id="content" cols="30" rows="10">${requestScope.LESSON.content}</textarea>
                                </div>
                            </div>
                            <div class="form-row">
                                <div class="form-group col-md-12">
                                    <input class="btn btn-primary" type="submit" value="Submit">
                                    <a href="subject-lesson-detail?subId=${sessionScope.subIdForLesson}" class="btn btn-danger">back</a>
                                </div>
                            </div>
                        </form>

                    </c:if>
                </div>
            </div>
        </div>



    </body>

</html>
