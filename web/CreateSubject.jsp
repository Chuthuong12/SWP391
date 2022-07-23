<%-- 
    Document   : CreateSubject
    Created on : Jun 9, 2022, 9:24:15 PM
    Author     : 84969
--%>
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
            <div id="layoutSidenav">
                <div class="container-fluid px-4 px-lg-5 mb-5 row" style="margin-top: 91px">
                    <div class ="row mt-5">
                        <div class="mb-3 mx-auto d-block shadow p-3 mb-5 bg-white rounded" style="padding: 10px 0px 10px 10px; border-radius: 8px; width: 32%; margin-left: 10px; width: 100% !important">
                            <form action="CreateController" method="post" class="container row mx-auto" style="width: 80%" enctype="multipart/form-data">
                                <h1>Create A New Subject</h1>
                                <div class="my-3 col-2">
                                    <input type="text" class="form-control" placeholder="Subject Id (example: 1)" name="id"/>
                                    <c:if test="${not empty message or not empty ErrCreateMsg[1]}" >
                                        <div class="alert alert-danger" role="alert">
                                            ${message}          
                                            ${ErrCreateMsg[1]}
                                        </div>
                                    </c:if>
                                </div>
                                <div class="my-3 col-4">
                                    <input type="text" class="form-control" placeholder="Subject Name" name="name"/>
                                    <c:if test="${not empty message or not empty ErrCreateMsg[2]}" >
                                        <div class="alert alert-danger" role="alert">
                                            ${message}                 
                                            ${ErrCreateMsg[2]}                  
                                        </div>
                                    </c:if>
                                </div>
                                <div class="my-3 col-6">
                                    <input class="form-control" type="file" name="photo"/>
                                    <c:if test="${not empty ErrCreateMsg[0]}" >
                                        <div class="alert alert-danger" role="alert">
                                            ${ErrCreateMsg[0]}
                                        </div>
                                    </c:if>
                                </div>
                                <div class="mb-3 col-12">
                                    <textarea placeholder="Description" class="form-control" name="description"></textarea>
                                    <c:if test="${not empty message or not empty ErrCreateMsg[3]}" >
                                        <div class="alert alert-danger" role="alert">
                                            ${message}                
                                            ${ErrCreateMsg[3]}                  
                                        </div>
                                    </c:if>
                                </div>
                                <div class="mb-3 col-12">
                                    <input type="number" name="tagLine" class="form-control" placeholder="Tag Line"/>
                                    <c:if test="${not empty message or not empty ErrCreateMsg[4]}" >
                                        <div class="alert alert-danger" role="alert">
                                            ${message}                 
                                            ${ErrCreateMsg[4]}                  
                                        </div>
                                    </c:if>
                                </div>
                                <div class="mb-3 col-12">
                                    <input type="number" name="status" class="form-control" placeholder="Status"/>
                                    <c:if test="${not empty message or not empty ErrCreateMsg[5]}" >
                                        <div class="alert alert-danger" role="alert">
                                            ${message}
                                            ${ErrCreateMsg[5]}                   
                                        </div>
                                    </c:if>
                                </div>
                                <div class="mb-3 col-12">
                                    <input type="text" name="title" class="form-control" placeholder="Title"/>
                                    <c:if test="${not empty message or not empty ErrCreateMsg[6]}" >
                                        <div class="alert alert-danger" role="alert">
                                            ${message}                   
                                            ${ErrCreateMsg[6]}                  
                                        </div>
                                    </c:if>
                                </div>
                                <div class="mb-3 col-12">
                                    <input type="text" name="price" class="form-control" placeholder="Price"/>
                                    <c:if test="${not empty message or not empty ErrCreateMsg[7]}" >
                                        <div class="alert alert-danger" role="alert">
                                            ${message}
                                            ${ErrCreateMsg[7]}                   
                                        </div>
                                    </c:if>
                                </div>
                                <div class="mb-3 col-12">
                                    <input type="text" name="salePrice" class="form-control" placeholder="salePrice"/>
                                    <c:if test="${not empty message or not empty ErrCreateMsg[7]}" >
                                        <div class="alert alert-danger" role="alert">
                                            ${message}
                                            ${ErrCreateMsg[7]}                   
                                        </div>
                                    </c:if>
                                </div>
                                <div class="mb-3 col-12">
                                    <select name="category" class="form-control">
                                        <c:forEach items="${listCategories}" var="c">
                                            <option value="${c.categoryId}">
                                                ${c.categoryName}
                                            </option>
                                        </c:forEach>   
                                    </select>
                                </div>
                                <div class="text-center mt-5">
                                    <button class="btn btn-primary" value="Create" type="submit">Create</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
