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

        <div id="layoutSidenav" class="mb-4">
            <%@include file="components/catgoryComponent_1.jsp" %>
            <div id="layoutSidenav_content">
                <div class="container-fluid px-4 px-lg-5 mb-5" style="margin-top: 91px">
                    <h1>Edit Post</h1>
                    <div class="row gx-4 gx-lg-5 align-items-center my-5">
                        <div class="col-lg-7"><img class="img-fluid rounded mb-4 mb-lg-0 mx-auto d-block" style="height: 380px" src="images/${post.thumbnail}" alt="..." /></div>
                        <div class="col-lg-5">
                            <form action="edit-post?postId=${post.postId}" method="POST" class="container-fluid row mx-auto">

                                <div class="my-3 col-3">
                                    <b>Post ID: <input type="text" class="form-control" id="postId" name="postId" placeholder="Post ID"
                                                       value="${post.postId}" readonly></b>
                                </div>
                                <div class="my-3 col-6">
                                    <b>Title: <input type="text" class="form-control" id="title" name="title" placeholder="Post Title"
                                                     value="${post.title}"></b>
                                </div>
                                <div class="mb-3 col-3 ps-3 my-3">  
                                    <b>Status: <br><label class="switch">
                                            <input type="checkbox" name="status" value="true" ${post.status == "true"?"checked":""} >
                                            <span class="slider round"></span>
                                        </label></b>
                                </div>
                                <div class="mb-3">
                                    <b>Post_url: <input type="text" class="form-control" id="slider_url" name="thumbnail" placeholder="Post Img_url"
                                                        value="uploads/${post.thumbnail}"></b>
                                </div>
                                <div class="mb-3">
                                    <b>Post Image: <input type="file" class="form-control" id="slider_url" name="image" placeholder="Post Image"></b>
                                </div>
                                <!--                             <div class="mb-3">
                                                                <b>Update date: <input type="date" class="form-control" id="date" name="date"></b>
                                                            </div>-->
                                <div class="mb-3">
                                    <b>Brief Infor: <input type="text" class="form-control" id="backlink" name="briefInfor" placeholder="Brief Infor"
                                                           value="${post.brifInfor}"></b>
                                </div>

                                <div class="mb-3 col-12">
                                    <b>Description: <br><textarea name="content" rows="6" style="width: 100%" placeholder="Post Description">${post.content}</textarea></b>
                                </div>
                                <div class="text-center mt-5">
                                    <button class="btn btn-success"/>Edit</button>
                                </div>
                            </form>
                        </div>
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
