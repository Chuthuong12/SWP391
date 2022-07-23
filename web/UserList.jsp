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

                    <h1>User List</h1>

                    <div class ="row mt-5">
                        <form action="DispatchServlet" method="POST" class="d-flex" style="width: 600px; float: right !important">
                            <div class="input-group">
                                <select name="roleIdFilter" class="form-control">
                                    <option value="0">All role</option>
                                    <c:forEach var="s" items="${listRole}">
                                        <option value="${s.role_id}" ${s.role_id==requestScope.roleId?"selected":"" }>${s.role_name}</option>
                                    </c:forEach>
                                </select>
                                <select name="genderFilter" class="form-control">
                                    <option value="1"  ${1==requestScope.gender?"selected":"" }> Male</option>
                                    <option value="0" ${0==requestScope.gender?"selected":"" }>Female</option>
                                </select>
                                <select name="statusFilter" class="form-control">
                                    <option value="1"  ${1==requestScope.status?"selected":"" }> Active</option>
                                    <option value="0" ${0==requestScope.status?"selected":"" }>Inactive</option>
                                </select>
                            </div>
                            <button name="btAction" value="FilterUser" type="submit" class="btn btn-primary ms-4" style="border-radius: 5px !important">Filter</button>
                        </form>

                        <div class="my-3">
                            <c:if test="${message!=null}">
                                ${message}
                            </c:if>
                            <c:if test="${message==null}">
                                <c:forEach var="u" items="${listUser}">
                                    <div class="mb-3 mx-auto d-block shadow p-3 mb-5 bg-white rounded" style="padding: 10px 0px 10px 10px; border-radius: 8px; width: 32%; margin-left: 10px; width: 100% !important">
                                        <div class="row">
                                            <b class="col-6">${u.userid}: ${u.fullname} </b>
                                            <b class="col-3"></b>
                                            <a href="UserDetail?uid=${u.userid}" class="col-3 btn btn-warning text-decoration-none" style="color: white; width: 20%">Detail</a>

                                        </div>
                                        <br>
                                        <div class="row d-flex mt-3 pt-2" style="border-top: 1px solid silver">
                                            <div class="col-3">
                                                Gender: ${u.gender == 1?"Male":"Female"}
                                            </div>

                                            <div class="col-3 ">
                                                Role: ${u.role.role_name}
                                            </div>
                                            <div class="col-3 ">
                                                Status ${u.status}
                                            </div>

                                        </div>
                                    </div>
                                </c:forEach>
                            </c:if>


                        </div>
                    </div>
                </div>
            </div>
        </div>
        <c:choose>
            <c:when test="${listUser==null || listUser.size()==0}">
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
</html>







