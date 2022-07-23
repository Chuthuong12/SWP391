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
                <div class="container px-4 px-lg-5 mx-auto" style="margin-top: 91px">
                    <!-- Heading Row-->
                    <div class="row gx-4 gx-lg-8 align-items-center my-5">
                        <header class="mb-4">
                            <!-- Post title-->
                            <h1 class="fw-bolder mb-1">Welcome to Post Detail!</h1>
                            <!-- Post meta content-->
                            <div class="text-muted fst-italic mb-2">Posted on ${post.created_date} by ${post.brifInfor}</div>
                            <!-- Post categories-->
                            <a class="badge bg-secondary text-decoration-none link-light" href="post-list?blogAction=get">Post List</a>
                            <c:if test="${sessionScope.account.role.getRole_name() eq 'ADMIN' || sessionScope.account.role.getRole_name() eq 'EXPERT'}">
                                <a class="badge bg-secondary text-decoration-none link-light" href="add-post">Add New</a>
                            </c:if>

                        </header>
                        <div class="col-lg-8">
                            <div class="mx-auto d-block"><img class="img-fluid rounded mb-4 mb-lg-0 mx-auto d-block" style="height: 400px" src="uploads/${post.thumbnail}" alt="..." /></div>

                        </div>


                        <!-- Side widgets-->
                        <div class="col-lg-4">

                            <div class="card mb-4">
                                <div class="card-header">Blog Categories</div>
                                <div class="list-group">
                                    <c:forEach items="${listBlogs}" var="B">
                                        <a href="post-list?blogId=${B.blogId}&blogAction=post" class="list-group-item list-group-item-action ${B.blogId == requestScope.blogId?"list-group-item-warning":""}">${B.blogName}</a>
                                    </c:forEach>
                                </div>

                            </div>
                        </div>
                    </div>
                    <div>
                        <h1 class="font-weight-light mb-4 text-center">${post.title}</h1>
                    </div>
                    <p class="mb-2" style="font-size: 24px">${post.content}</p>
                    <c:if test="${sessionScope.account.role eq 'ADMIN' || sessionScope.account.role eq 'EXPERT'}">
                        <div class="text-center">
                            <a class="btn btn-primary mt-4 me-3" style="width: 8%" href="edit-post?postId=${post.postId}">Edit</a> 
                        </div>
                    </c:if>

                </div>
            </div>
        </div>
    </body>
    <style>
        /* The switch - the box around the slider */
        .switch {
            position: relative;
            display: inline-block;
            width: 60px;
            height: 34px;
        }

        /* Hide default HTML checkbox */
        .switch input {
            opacity: 0;
            width: 0;
            height: 0;
        }

        /* The slider */
        .slider {
            position: absolute;
            cursor: pointer;
            top: 0;
            left: 0;
            right: 0;
            bottom: 0;
            background-color: #ccc;
            -webkit-transition: .4s;
            transition: .4s;
        }

        .slider:before {
            position: absolute;
            content: "";
            height: 26px;
            width: 26px;
            left: 4px;
            bottom: 4px;
            background-color: white;
            -webkit-transition: .4s;
            transition: .4s;
        }

        input:checked + .slider {
            background-color: #2196F3;
        }

        input:focus + .slider {
            box-shadow: 0 0 1px #2196F3;
        }

        input:checked + .slider:before {
            -webkit-transform: translateX(26px);
            -ms-transform: translateX(26px);
            transform: translateX(26px);
        }

        /* Rounded sliders */
        .slider.round {
            border-radius: 34px;
        }

        .slider.round:before {
            border-radius: 50%;
        }
    </style>
</html>
