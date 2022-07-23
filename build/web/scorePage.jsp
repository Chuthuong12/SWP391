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
    <body class="sb-sidenav-toggled">
        <%@include file="components/navBarComponent.jsp" %>
        <div id="layoutSidenav" class="mb-4">
            <%@include file="components/catgoryComponent_1.jsp" %>
            <div id="layoutSidenav_content">
                <div class="container-fluid px-4 px-lg-5 mb-5" style="margin-top: 91px">
                    <div class="container">
                        <h1>Your Score: ${requestScope.SCORE}</h1>
                        <div>
                            <a href="lesson-quiz?lessonId=${sessionScope.lessonIdScore}&subId=${sessionScope.subIdScore}&action=post" class="btn btn-danger" style="float: left"/>Back</a>
                        </div>
                        
                        <c:if test="${requestScope == null}">
                            <a href="DoQuizzServlet?quizzId=${requestScope.quizzId}&subId=${requestScope.subId}&method=get" class="btn btn-danger"/>Do Again</a>
                            <a href="ReviewQuizzServlet?quizzId=${requestScope.quizzId}&attempt=${requestScope.attempt}&subId=${requestScope.subId}" class="btn btn-success"/>Review</a>
                        </c:if>

                    </div>
                </div>
            </div>
        </div>





    </body>
</html>
