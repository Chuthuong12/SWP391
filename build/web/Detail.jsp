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
        body {
            font-family: Arial;
        }

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
            background-color: #1266f1;
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
                    <h1>Subject Detail</h1>
                    <div class ="row mt-5">
                        <div class="mb-3 mx-auto d-block shadow p-3 bg-white rounded" style="padding: 10px 0px 10px 10px; border-radius: 8px; width: 32%; margin-left: 10px; width: 100% !important">
                            <c:if test="${message!=null}">
                                ${message}
                            </c:if>
                            <div class="tab list-group list-group-horizontal" style="width: 40%; margin-left: 75px">
                                <button class="tablinks list-group-item list-group-item-action text-center active" onclick="openCity(event, 'Overview')">Overview</button>
                                <button class="tablinks list-group-item list-group-item-action text-center" onclick="openCity(event, 'Dimension')">Dimension</button>
                                <button class="tablinks list-group-item list-group-item-action text-center" onclick="openCity(event, 'PackagePrice')">Package Price</button>
                            </div>
                            <div id="Overview" class="tabcontent" style="border: none; display: block;">
                                <h3>Overview</h3>
                                <div class="row">
                                    <form action="EditSubjectController?id=${requestScope.id}" class="container row mx-auto d-flex" method="post" enctype="multipart/form-data">
                                        <div class="my-3 col-6">
                                            Subject Name </br>
                                            <input class="form-control" type="text" name="name" value="${sessionScope.SubjectBySubId.subjectName}" />
                                        </div>
                                        <div class="my-3 col-6">
                                            Image </br>
                                            <input id="file-upload" class="form-control" type="file" name="photo"/>
                                        </div>
                                        <div class="my-3 col-6">
                                            Category  </br>
                                            <select class="form-control" name="categoryId">
                                                <c:forEach var="c" items="${listCategories}">
                                                    <option value = "${c.getCategoryId()}" ${sessionScope.SubjectBySubId.categoryId == c.getCategoryId()?"selected":""}>${c.getCategoryName()}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                        <div class="my-3 col-6">
                                            Title  </br>
                                            <input type="text" class="form-control" name="title" value="${sessionScope.SubjectBySubId.title}" /> 
                                        </div>
                                        <div class="my-3 col-6">
                                            Tag Line  </br>
                                            <input type="text" class="form-control" name="tagLine" value="${sessionScope.SubjectBySubId.tagLine}" /> 
                                        </div>
                                        <div class="my-3 col-6">
                                            Status 
                                            <select class="form-control" name="status">
                                                <option value="true" ${sessionScope.SubjectBySubId.status == 'true'?"selected":""}>Active</option>
                                                <option value="false" ${sessionScope.SubjectBySubId.status == 'false'?"selected":""}>Un -Active</option>
                                            </select>
                                        </div>
                                        <div class="my-3 col-12">
                                            Description  </br>
                                            <textarea name="description"class="form-control"  rows="3" placeholder="Subject Description">${sessionScope.SubjectBySubId.description}</textarea>
                                        </div>
                                        <div class="text-center mt-5">
                                            <input type="submit" class="btn btn-primary form-control" style="width: 10%" value="Submit" />
                                        </div>
                                    </form>
                                        <div style="float: right !important"">
                                        <button class="btn btn-danger" style=" width: 10%; float: right !important"><a href="/SWP391/SubjectListAdmin" class="text-decoration-none" style="color: white;">Back</a></button>
                                    </div>


                                </div>
                            </div>

                            <div id="Dimension" class="tabcontent" style="border: none; display: none;">
                                <h3>Dimension</h3>
                                <div>
                                    <a href="DispatchServlet?btAction=CreateDimension&subId=${requestScope.id}" class="btn btn-primary text-decoration-none" style="color: white">Add new Dimension</a>
                                    <table class="table">
                                        <thead>
                                            <tr class="row text-center">
                                                <th class="col-2">#</th>
                                                <th class="col-3">Type</th>
                                                <th class="col-3">Dimension</th>
                                                <th class="col-4">Action</th>

                                            </tr>
                                        </thead>

                                        <tbody>
                                            <c:forEach var="D" items="${requestScope.listDimension}">
                                                <tr class="table-active row text-center">
                                                    <td class="col-2">${D.dimId}</td>
                                                    <td class="col-3">${D.typeId}</td>
                                                    <td class="col-3">${D.name}</td>
                                                    <td class="col-4">
                                                        <a href="DispatchServlet?btAction=EditDimension&id=${D.dimId}&subId=${requestScope.id}" class="btn btn-primary text-decoration-none" style="color: white">Edit</a> 
                                                        <a href="DispatchServlet?btAction=DeleteDimension&id=${D.dimId}&subId=${requestScope.id}" class="btn btn-danger text-decoration-none" style="color: white"
                                                           onclick="return confirm('Are you sure to delete?')">Delete</a>
                                                    </td>
                                                </tr>
                                            </c:forEach>

                                        </tbody>
                                    </table>
                                </div>
                            </div>
                            <div id="PackagePrice" class="tabcontent" style="border: none; display: none;">
                                <h3>Package Price</h3>
                                <div>
                                    <a href="DispatchServlet?btAction=CreatePackagePrice&subId=${requestScope.id}" class="btn btn-primary text-decoration-none" style="color: white">Add new Package Price</a>
                                    <table class="table" >
                                        <thead>
                                            <tr>
                                                <th scope="col">#</th>
                                                <th scope="col">Name</th>
                                                <th scope="col">Duration</th>
                                                <th scope="col">Price</th>
                                                <th scope="col">Sale Price</th>
                                                <th scope="col">Status</th>
                                                <th scope="col">Description</th>
                                                <th scope="col">Action</th>

                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach items="${listPricePackage}" var="p">
                                                <tr class="table-active">
                                                    <td>${p.priceId}</td>
                                                    <td>${p.name}</td>
                                                    <td>${p.acessDuration}</td>
                                                    <td>${p.price}</td>
                                                    <td>${p.salePrice}</td>
                                                    <td>${p.status}</td>
                                                    <td>${p.description}</td>

                                                    <td>
                                                        <a href="PricePackageUpdateInSubject?priceId=${p.priceId}" class="btn btn-primary text-decoration-none" style="color: white">Edit</a> 
                                                        <a href="DispatchServlet?btAction=DeletePackagePrice&pid=${p.priceId}&subId=${requestScope.id}" class="btn btn-danger text-decoration-none" style="color: white"
                                                           onclick="return confirm('Are you sure to delete?')">Delete</a>
                                                    </td>
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
        </div>

    </body>

    <script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
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

                                                               function SubjectToLessonAsync(subId) {
                                                                   axios.get('edit-quiz-async', {
                                                                       params: {
                                                                           subId: subId
                                                                       }
                                                                   }).then((response) => {
                                                                       document.getElementById("update_lesson").innerHTML = response.data;
                                                                   })
                                                               }
    </script>
</html>
