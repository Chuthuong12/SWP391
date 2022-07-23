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
        <%@include file="../components/navBarComponent.jsp" %>

        <div id="layoutSidenav" class="mb-4">
            <%@include file="../components/catgoryComponent_1.jsp" %>
            <div id="layoutSidenav_content">
                <div class="container-fluid px-4 px-lg-5 mb-5" style="margin-top: 91px">
                    <h1>Slider Detail</h1>
                    <div class ="row mt-5">
                        <c:forEach var="sl" items="${listSliderById}">
                            <div class="mb-3 mx-auto d-block shadow p-3 mb-5 bg-white rounded" style="padding: 10px 0px 10px 10px; border-radius: 8px; width: 32%; margin-left: 10px; width: 100% !important">
                                <form action="slider-detail" method="POST">
                                    <a href="subject-detail?id=${sl.subId}"><img class="mx-auto d-block img-fluid" src="images/${sl.sliderUrl}" /></a>
                                    <h2 class="text-center mt-4"> ${sl.title} </h2>
                                    <ul style="margin-left: 8%; font-size: 24px">
                                        <li><b>Sub Name: </b>${subject.subjectName}</li>
                                        <li><b>Status: </b>${sl.status == "True"?"Active":"Inactive"}</li>
                                        <li><b>Content: </b>${sl.content}</li>
                                        <li><b>Notes: </b>${sl.notes}</li>
                                    </ul>
                                    <div class="text-center">
                                        <a href="edit-slider?id=${sl.sliderId}" class="btn btn-primary"/>Edit</a>
                                    </div>
                                </form>
                            </div><br>
                        </c:forEach>
                        <div class="form-row">
                            <div class="form-group col-md-12">
                                <a href="slider-list" class="btn btn-danger" style="float: right !important">Back</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </body>
</html>
