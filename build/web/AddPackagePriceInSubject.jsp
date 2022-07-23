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

    <body>
        <%@include file="components/navBarComponent.jsp" %>

        <div id="layoutSidenav">
            <%@include file="components/catgoryComponent_1.jsp" %>
            <div class="container px-4 px-lg-5" style="margin-top: 91px">
                <div id="layoutSidenav_content">
                    <!-- Heading Row-->
                    <form class="row g-3" action="AddPackageInSubject?subId=${requestScope.subId}" method="POST">
                        Package Price: 
                        <select name="packagePrice" onchange="PricePackageAsync(this.value)">
                            <option>All package Price</option>
                            <c:forEach var="pp" items="${listPricePackage}">
                                <option value="${pp.priceId}">${pp.name}</option>
                            </c:forEach>                       
                        </select> </br>
                        <button class="btn btn-primary" type="submit">Add</button>

                        <div id="update_pricePackage" style="width: 100% !important; height: 200px">
                            <table class="table" >
                                <thead>
                                    <tr>
                                        <th scope="col">PackageId</th>
                                        <th scope="col">Package </th>
                                        <th scope="col">Duration </th>
                                        <th scope="col">ListPrice</th>
                                        <th scope="col">SalePrice</th>
                                        <th scope="col">Status</th>
                                        <th scope="col">Description</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr class="table-active">

                                        <td>${pricePack.priceId}</td>
                                        <td>${pricePack.name}</td>
                                        <td>${pricePack.acessDuration}</td>
                                        <td>${pricePack.price}</td>
                                        <td>${pricePack.salePrice}</td>
                                        <td>${pricePack.status}</td>
                                        <td>${pricePack.description}</td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                    </form>

                </div>
            </div>


        </div>

    </body>
    <script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
    <script>

                            function PricePackageAsync(priceId) {
                                axios.get('update_price_async', {
                                    params: {
                                        priceId: priceId
                                    }
                                }).then((response) => {
                                    document.getElementById("update_pricePackage").innerHTML = response.data;
                                })
                            }
    </script>
</html>

