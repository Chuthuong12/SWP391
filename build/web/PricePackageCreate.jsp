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
                    <h1>Create Price Package</h1>
                    <div class="mb-3 mx-auto d-block shadow p-3 mb-5 bg-white rounded mt-5" style="padding: 10px 0px 10px 10px; border-radius: 8px; width: 32%; margin-left: 10px; width: 100% !important">
                        <!-- Heading Row-->
                        <!--                <form class="row g-3" action="create-pricePackage" method="POST">
                                            Package <input type="text" name="name" value="" /> </br>
                                            Duration <input type="text" name="acessDuration" value="" /> </br>
                                            Price <input type="text" name="price" value="" /> </br>
                                            SalePrice <input type="text" name="salePrice" value="" /> </br>
                                            Status <input type="text" name="status" value="" /> </br>
                                            Description <input type="text" name="description" value="" /> </br>
                                            <button class="btn btn-primary" type="submit">Create</button>
                                        </form>-->
                        <form class="row g-3" action="create-pricePackage" method="POST">
                            <div class="col-md-6">
                                <label for="inputZip" class="form-label">Package Name</label>
                                <input type="text" name="name" value="" class="form-control" id="inputZip">
                            </div>
                            <div class="col-md-4">
                                <label for="inputState" class="form-label">Duration</label>
                                <select id="inputState" name="acessDuration" value="" class="form-select">
                                    <option selected>Choose time by month</option>
                                    <option >1 </option>
                                    <option >3 </option>
                                    <option >6 </option>
                                    <option >12 </option>
                                    <option >24</option>
                                </select>
                            </div>
                            <div class="col-md-4">
                                <label for="inputZipp" class="form-label">Price</label>
                                <input type="text" name="price" value="" class="form-control" id="inputZipp">
                            </div>
                            <div class="col-md-4">
                                <label for="inputEmail4" class="form-label">Sale Price</label>
                                <input type="text" type="text" name="salePrice" value="" class="form-control" id="inputEmail4">
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
</html>

