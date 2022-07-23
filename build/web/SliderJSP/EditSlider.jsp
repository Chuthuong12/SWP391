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
        <%@include file="../components/navBarComponent.jsp" %>

        <div id="layoutSidenav" class="mb-4">
            <%@include file="../components/catgoryComponent_1.jsp" %>
            <div id="layoutSidenav_content">
                <div class="container-fluid px-4 px-lg-5 mb-5" style="margin-top: 91px">
                    <h1>Edit Slider</h1>
                    <div class ="row mt-5">
                        <div class="mb-3 mx-auto d-block shadow p-3 mb-5 bg-white rounded" style="padding: 10px 0px 10px 10px; border-radius: 8px; width: 32%; margin-left: 10px; width: 100% !important">

                            <form action="edit-slider" enctype="multipart/form-data" method="post" class="container row mx-auto" >
                                <img src="uploads/${requestScope.slider.sliderUrl}" alt="alt" width="200px" height="200px"/>
                                <div class="my-3 col-1">
                                    <b>Slider ID: <input type="text" class="form-control" id="id" name="sliderId" placehutolder="Slider ID"
                                                         value="${requestScope.slider.sliderId}" readonly></b>
                                </div>
                                <div class="my-3 col-1">
                                    <b>Sub ID: <input type="text" class="form-control" id="subId" name="subId" placehutolder="Sub ID"
                                                      value="${requestScope.slider.subId}"></b>
                                </div>
                                <div class="my-3 col-7">
                                    <b>Title: <input type="text" class="form-control" id="title" name="title" placeholder="Slider Title"
                                                     value="${requestScope.slider.title}"></b>
                                </div>
                                <div class="mb-3 col-3 ps-5">                                
                                    <br><br>
                                    <b>Status: <input type="radio" name="status" value="1" class="mx-2 my-1" ${requestScope.slider.status == 'true'?"checked":""}/>Active
                                        <input type="radio" name="status" value="0" class="mx-2 my-1" ${requestScope.slider.status == 'false'?"checked":""}/>Inactive</b>
                                </div>
                                <!--                            <div class="mb-3">
                                                                <b>Slider_url: <input type="text" class="form-control" id="slider_url" name="slider_url" placeholder="Slider Img_url"
                                                                                      value=""></b>
                                                            </div>-->
                                <div class="mb-3">
                                    <b>Slider Image: 
                                        <input type="file" class="form-control" name="image" placeholder="Slider Image"></b>
                                </div>
                                <div class="mb-3 col-6">
                                    <b>Content: <br><textarea name="content" rows="3" style="width: 100%" placeholder="Slider Content">${requestScope.slider.content}</textarea></b>
                                </div>

                                <div class="mb-3 col-6">                                
                                    <b>Notes: <br><textarea name="notes" rows="3" style="width: 100%" placeholder="Slider Notes">${requestScope.slider.notes}</textarea></b>
                                </div>
                                <div class="text-center mt-5">
                                    <input type="submit" name="Edit" class="btn btn-success">
                                </div>
                            </form>
                        </div><br>

                    </div>
                    <div class="form-row">
                        <div class="form-group col-md-12">
                            <a href="${sessionScope.backlink}" class="btn btn-danger" style="float: right !important">Back</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </body>
</html>
