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
                <div class="container px-4 px-lg-5" style="margin-top: 91px">
                    <h1>Create Dimension</h1>
                    <div class="mb-3 mx-auto d-block shadow p-3 mb-5 bg-white rounded mt-5" style="padding: 10px 0px 10px 10px; border-radius: 8px; width: 32%; margin-left: 10px; width: 100% !important">

                        <!-- Heading Row-->
                        <!--                <form class="row g-3" action="create-dimension" method="POST">
                                            TypeId <input type="text" name="typeId" value="" /> </br>
                                            Name <input type="text" name="name" value="" /> </br>
                                            Description <input type="text" name="description" value=""  /> </br>
                                            <button class="btn btn-primary" type="submit">Create</button>
                                        </form>-->

                        <form class="row g-3" action="create-dimension" method="POST">
                            <div class="col-md-4">
                                <label for="inputState" class="form-label">TypeId</label>
                                <select id="inputState" name="typeId" value="" class="form-select" onchange="ChooseTypeIdToTypeName(this.value)">
                                    <c:forEach items="${listType}" var="T">
                                        <option>${T.typeId}</option>
                                    </c:forEach>

                                </select>
                            </div>
                            <div class="col-md-4" id="update_dimension">
                                <label for="inputZip" class="form-label">TypeName</label>
                                <input type="text" class="form-control" value="${requestScope.typeName}" id="inputZip">
                            </div></br>
                            <div class="col-md-6">
                                <label for="inputEmail4" class="form-label">Dimension Name</label>
                                <input type="text" name="name" value="" class="form-control" id="inputEmail4">
                            </div></br>
                            <div class="col-md-8">
                                <label for="inputPassword4" class="form-label">Description</label>
                                <input type="text" name="description" value="" class="form-control" id="inputPassword4">
                            </div>                
                            <div class="col-12">
                                <button type="submit" class="btn btn-primary">Create</button>
                                <a href="subject-list" class="btn btn-primary"/>Back</a>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </body>
    <script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
    <script>
                                                function ChooseTypeIdToTypeName(typeId) {
                                                    axios.get('update_dimension-async', {
                                                        params: {
                                                            typeId: typeId
                                                        }
                                                    }).then((response) => {
                                                        document.getElementById("update_dimension").innerHTML = response.data;
                                                    })
                                                }
    </script>
</html>

