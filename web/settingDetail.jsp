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
            <div id="">
                <div class="container-fluid px-4 px-lg-5 mb-5" style="margin-top: 91px">

                    <h1>Setting Detail</h1>
                    <div class="container">
                        <form action="Setting-Detail" method="POST">
                            <div class="row">
                                <span for="">Id:</span>
                                <input type="number" name="id" value="${SETTING.settingId}"readonly=""  class="form-control"/>
                            </div>
                            <div class="row">
                                <span for="">Name:</span>
                                <input type="text" name="name" value="${SETTING.name}" class="form-control"/>
                            </div>

                            <div class="row">
                                <span for="description">Description</span>
                                <input type="text" name="description" value="${SETTING.description}" id="description" class="form-control"/>
                            </div>

                            <div class="row">
                                <span for="value">Value: </span>
                                <input type="number" name="value" value="${SETTING.value}" id="value" class="form-control"/>
                            </div>

                            <div class="row">
                                <select name="type"class="form-control">
                                    <option value="${SETTING.typeId}"selected="">${typeName}</option>
                                    <c:forEach items="${requestScope.type}" var="t">
                                        <option value="${t.typeId}">${t.typeName}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="row">
                                <input class="form-control btn btn-success" type="submit" value="Save" name="btnAction" />
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>


</body>
</html>