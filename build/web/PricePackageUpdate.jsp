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

        <div id="layoutSidenav">
            <%@include file="components/catgoryComponent_1.jsp" %>
            <div id="layoutSidenav_content">
                <div class="container px-4 px-lg-5" style="margin-top: 91px">
                    <div class="tab list-group list-group-horizontal" style="width: 30%; margin-left: 75px">
                        <button class="tablinks list-group-item list-group-item-action active" onclick="openCity(event, 'price-package')">Price Package</button>
                    </div>

                    <div id="price-package" class="tabcontent active">
                        <h3>Price Package</h3>
                        <div class="row">
                            <div class="col-12">
                                <form action="update-pricePackage" method="POST">
                                    <table class="table" >
                                        <thead>
                                            <tr class="row">
                                                <th class="col-1 text-center">PackageId</th>
                                                <th class="col-2 text-center">Duration </th>
                                                <th class="col-2 text-center">ListPrice</th>
                                                <th class="col-2 text-center">SalePrice</th>
                                                <th class="col-2 text-center">Status</th>
                                                <th class="col-3 text-center">Description</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <tr class="table-active row">
                                                <td class="col-1 text-center"><input readonly style="width: 25px" type="text" name="priceId" value="${pricePack.priceId}" /></td>
                                                <td class="col-2 text-center"><input type="text" name="acessDuration" value="${pricePack.acessDuration}" /></td>
                                                <td class="col-2 text-center"><input type="text" name="price" value="${pricePack.price}" /></td>
                                                <td class="col-2 text-center"><input type="text" name="salePrice" value="${pricePack.salePrice}" /></td>
                                                <td class="col-2 text-center"><input type="text" name="status" value="${pricePack.status}" /></td>
                                                <td class="col-3 text-center"><input type="text" name="description" value="${pricePack.description}" /></td>


                                            </tr>
                                        </tbody>

                                    </table>
                                                <div class="text-center">
                                        <button type="submit" class="btn btn-primary">Save</button>
                                    </div>

                                </form>
                            </div>
                        </div>
                    </div>

                </div>
            </div>
        </div>

    </body>
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
    </script>
</html>

