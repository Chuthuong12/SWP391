<%-- 
    Document   : CreateDimension
    Created on : Jun 26, 2022, 10:22:49 AM
    Author     : 84969
--%>

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
            <div id="layoutSidenav">
            <div class="container px-4 px-lg-5" style="margin-top: 91px">
                <h3 style="color:blue;">Add new dimension</h3>
                <form class="row g-3" action="CreateDimension?subId=${subId}" method="POST">
                    TypeId 
                    <select name="typeId">
                        <c:forEach var="t" items="${listType}">
                            <option  value="${t.getTypeId()}">${t.getTypeName()}</option>
                        </c:forEach>
                    </select> </br>
                    Name <input type="text" name="name" value="" /> </br>
                    Description <input type="text" name="description" value="" /> </br>
                    <button class="btn btn-primary" type="submit">Create</button>
                </form>
            </div>
            </div>
        </div>
    </body>
</html>
