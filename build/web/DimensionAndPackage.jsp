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

        <div id="layoutSidenav" class="mb-4">
            <%@include file="components/catgoryComponent_1.jsp" %>
            <div id="layoutSidenav_content">
                <div class="container-fluid px-4 px-lg-5 mb-5" style="margin-top: 91px">
                    <h1>Subject Register</h1>
                    <div class="tab list-group list-group-horizontal" style="width: 30%; margin-left: 75px">
                        <button class="tablinks list-group-item list-group-item-action active" onclick="openCity(event, 'dimension')">Dimension</button>
                        <button class="tablinks list-group-item list-group-item-action" onclick="openCity(event, 'price-package')">Price Package</button>
                    </div>

                    <div id="dimension" class="tabcontent active">
                        <h3>Dimension</h3>
                        <table class="table" >
                            <thead>
                                <tr>
                                    <th scope="col" class="text-center">DimensionId</th>
                                    <th scope="col" class="text-center">Type</th>
                                    <th scope="col" class="text-center">Dimension</th>
                                    <th scope="col" class="text-center">Description</th>
                                    <th scope="col" class="text-center">Action</th>
                                    <th scope="col" class="text-center"><a href="create-dimension">Add new</a></th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${listAllDimension}" var="D">
                                    <tr class="table-active">
                                        <td class="text-center">${D.dimId}</td>
                                        <td class="text-center">${D.typeId}</td>
                                        <td class="text-center">${D.name}</td>
                                        <td class="text-center">${D.description}</td>
                                        <td class="text-center">
<!--                                            <a href="update-dimension?dimId=${D.dimId}">Update</a>-->
                                            <button type="button" class="btn btn-warning" data-toggle="modal" data-target="#A${D.dimId}">
                                                Update
                                            </button>
                                            <div id="A${D.dimId}"  class="modal fade" tabindex="-1" role="dialog">
                                                <div class="modal-dialog" role="document">
                                                    <div class="modal-content">
                                                        <div class="modal-header">
                                                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                                            <h4 class="modal-title">Do you want update...</h4>
                                                        </div>
                                                        <div class="modal-body">
                                                            <p>I love you <3
                                                        </div>
                                                        <div class="modal-footer">
                                                            <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                                            <button type="button" class="btn btn-danger">

                                                                <a href="update-dimension?dimId=${D.dimId}">Update</a>
                                                            </button>
                                                        </div>
                                                    </div> 
                                                </div> 
                                            </div>  
                                            <button type="submit" class="btn btn-warning" data-toggle="modal" data-target="#B${D.dimId}">
                                                Delete
                                            </button>
                                            <div id="B${D.dimId}"  class="modal fade" tabindex="-1" role="dialog">
                                                <div class="modal-dialog" role="document">
                                                    <div class="modal-content">
                                                        <div class="modal-header">
                                                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                                            <h4 class="modal-title">Do you delete .....</h4>
                                                        </div>
                                                        <div class="modal-body">
                                                            <p>Em va Trinh <3
                                                        </div>
                                                        <div class="modal-footer">
                                                            <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                                            <button type="sbumit" class="btn btn-danger">

                                                                <a href="delete-dimension?dimId=${D.dimId}">Delete</a>
                                                            </button>
                                                        </div>
                                                    </div><!-- /.modal-content -->
                                                </div><!-- /.modal-dialog -->
                                            </div><!-- /.modal -->
<!--                                                <a href="delete-dimension?dimId=${D.dimId}">Delete</a></td>-->
                                        </td>
                                        <td></td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>

                    <div id="price-package" class="tabcontent">
                        <h3>Price Package</h3>
                        <table class="table">
                            <thead>
                                <tr>
                                    <th scope="col" class="text-center">PackageId</th>
                                    <th scope="col" class="text-center">Package</th>
                                    <th scope="col" class="text-center">Duration</th>
                                    <th scope="col" class="text-center">ListPrice</th>
                                    <th scope="col" class="text-center">SalePrice</th>
                                    <th scope="col" class="text-center">Status</th>
                                    <th scope="col" class="text-center">Description</th>
                                    <th scope="col" class="text-center">Action  </th>

                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${listAllPricePackage}" var="P">
                                    <tr class="table-active">
                                        <td class="text-center">${P.priceId}</td>
                                        <td class="text-center">${P.name}</td>
                                        <td class="text-center">${P.acessDuration}</td>
                                        <td class="text-center">${P.price}</td>
                                        <td class="text-center">${P.salePrice}</td>
                                        <td class="text-center">${P.status}</td>
                                        <td class="text-center">${P.description}</td>
                                        <td>
                                            <!--                                                <a href="update-pricePackage">Update</a> | <a href="#">Delete</a>-->
                                            <button type="submit" class="btn btn-warning" data-toggle="modal" data-target="#C${P.priceId}">
                                                Update
                                            </button>
                                            <div id="C${P.priceId}"  class="modal fade" tabindex="-1" role="dialog">
                                                <div class="modal-dialog" role="document">
                                                    <div class="modal-content">
                                                        <div class="modal-header">
                                                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                                            <h4 class="modal-title">Do you update .....</h4>
                                                        </div>
                                                        <div class="modal-body">
                                                            <p>I love you <3
                                                        </div>
                                                        <div class="modal-footer">
                                                            <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                                            <button type="sbumit" class="btn btn-danger">

                                                                <a href="update-pricePackage?priceId=${P.priceId}">Update</a>
                                                            </button>
                                                        </div>
                                                    </div><!-- /.modal-content -->
                                                </div><!-- /.modal-dialog -->
                                            </div><!-- /.modal -->
                                            <button type="submit" class="btn btn-warning" data-toggle="modal" data-target="#D${P.priceId}">
                                                Delete
                                            </button>
                                            <div id="D${P.priceId}"  class="modal fade" tabindex="-1" role="dialog">
                                                <div class="modal-dialog" role="document">
                                                    <div class="modal-content">
                                                        <div class="modal-header">
                                                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                                            <h4 class="modal-title">Do you delete .....</h4>
                                                        </div>
                                                        <div class="modal-body">
                                                            <p>Em va Trinh <3
                                                        </div>
                                                        <div class="modal-footer">
                                                            <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                                            <button type="sbumit" class="btn btn-danger">

                                                                <a href="delete-pricePackage?priceId=${P.priceId}">Delete</a>
                                                            </button>
                                                        </div>
                                                    </div><!-- /.modal-content -->
                                                </div><!-- /.modal-dialog -->
                                            </div><!-- /.modal -->
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                        <button type="submit" class="btn btn-warning">

                            <a href="create-pricePackage">Add new</a>
                        </button>
                    </div>

                </div>
            </div>
        </div>
        <c:choose>
            <c:when test="${listSubjectsByPagging==null || listSubjectsByPagging.size()==0}">
                Not founds
            </c:when>
            <c:when test="${totalPage < 2}">
                <nav aria-label="Page navigation example" class="d-flex justify-content-center">
                    <ul class="pagination">
                        <c:forEach begin="1" end="${totalPage}" var="i">
                            <li class="page-item ${i == page?"active":""}"><a class="page-link" href="${pagination_url}page=${i}">${i}</a></li>
                            </c:forEach>
                    </ul>
                </nav>
            </c:when>
            <c:when test="${page < 2}">
                <nav aria-label="Page navigation example" class="d-flex justify-content-center">
                    <ul class="pagination">                               
                        <c:forEach begin="1" end="${totalPage}" var="i">
                            <li class="page-item ${i == page?"active":""}"><a class="page-link" href="${pagination_url}page=${i}">${i}</a></li>
                            </c:forEach>
                        <li class="page-item"><a class="page-link" href="${pagination_url}page=${page+1}">Next</a></li>
                    </ul>
                </nav>
            </c:when>
            <c:when test="${page+1 > totalPage}">
                <nav aria-label="Page navigation example" class="d-flex justify-content-center">
                    <ul class="pagination">
                        <li class="page-item"><a class="page-link" href="${pagination_url}page=${page-1}">Previous</a></li>
                            <c:forEach begin="1" end="${totalPage}" var="i">
                            <li class="page-item ${i == page?"active":""}"><a class="page-link" href="${pagination_url}page=${i}">${i}</a></li>
                            </c:forEach>
                    </ul>
                </nav>
            </c:when>
            <c:otherwise>
                <nav aria-label="Page navigation example" class="d-flex justify-content-center">
                    <ul class="pagination">
                        <li class="page-item"><a class="page-link" href="${pagination_url}page=${page-1}">Previous</a></li>
                            <c:forEach begin="1" end="${totalPage}" var="i">
                            <li class="page-item ${i == page?"active":""}"><a class="page-link" href="${pagination_url}page=${i}">${i}</a></li>
                            </c:forEach>
                        <li class="page-item"><a class="page-link" href="${pagination_url}page=${page+1}">Next</a></li>
                    </ul>
                </nav>
            </c:otherwise>
        </c:choose>


    </body>
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
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
