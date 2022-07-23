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
                    <h1></h1>
                    
                    <div class="container">
                        <div class="row">
                            <div class="mb-3 mx-auto d-block shadow p-3 mb-5 bg-white rounded">
                                <table class="table table-bordered table-hover table-striped row ms-1">
                                    <thead>
                                        <tr class="row text-center">
                                            <th class="col-1">Attempt</th>
                                            <th class="col-2">Title</th>
                                            <th class="col-2">Title</th>
                                            <th class="col-2">Date Taken</th>
                                            <th class="col-2">Point Percent</th>
                                            <th class="col-1">Point</th>
                                            <th class="col-2">Action</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach items="${requestScope.quizByQuizId}" var="Q">
                                            <tr class="row">
                                                <th class="col-1 text-center">${Q.attempt}</th>
                                                <th class="col-2 text-center">${Q.subName}</th>
                                                <th class="col-2 text-center">${Q.title}</th>
                                                <th class="col-2 text-center">${Q.taken_date}</th>
                                                <th class="col-2 text-center">${Q.pointPercent}%</th>
                                                <th class="col-1 text-center">${Q.point}</th>
                                                <th class="col-2 text-center">
                                                    <a href="ReviewQuizzServlet?quizzId=${Q.quizzId}&attempt=${Q.attempt}&subId=${Q.subId}" class="btn btn-warning">Review</a>
                                                </th>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                                
                            </div>
                        </div>

                    </div>
                </div>
            </div>
        </div>

    </body>
</html>
