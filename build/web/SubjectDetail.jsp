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
                    <!-- Heading Row-->
                    <div class="row gx-4 gx-lg-5 align-items-center my-5">
                        <c:forEach items="${listSubjectBySubjectId}" var="SBSI">
                            <div class="col-lg-7"><img class="img-fluid rounded mb-4 mb-lg-0 mx-auto d-block" style="height: 260px" src="${SBSI.thumbnail}" alt="..." /></div>
                            <div class="col-lg-5">
                                <h1 class="font-weight-light mb-4">${SBSI.subjectName}</h1>
                                Title: <b class="mb-2">${SBSI.getTitle()}</b><br>
                                Description: <b class="mb-2">${SBSI.description}</b><br>
                                Tag Line: <b class="mb-2">${SBSI.getTagLine()}</b><br>
                                Price: <b class="mb-4">3200-10000</b><br>
                                <c:if test="${requestScope.checkRegis == 0}">
                                    <button type="submit" class="btn btn-primary mt-4 me-3" data-toggle="modal" data-target="#SBSI${SBSI.getSubjectId()}">Register ${SBSI.subjectName}</button>
                                </c:if>
                                <!--<strong class="text-success">REGISTERED</strong><br>-->
                                <c:if test="${requestScope.checkRegis != 0}">
                                    <button class="btn btn-danger mt-4 me-3">Registered ${SBSI.subjectName}</button>
                                    <a class="btn btn-primary mt-4 me-3" href="lesson-quiz?subId=${SBSI.getSubjectId()}&action=get">List Lesson</a> 
                                </c:if>
                                <div id="SBSI${SBSI.getSubjectId()}"  class="modal fade" tabindex="-1" role="dialog">
                                    <div class="modal-dialog" role="document">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <button type="button" class="close btn btn-danger" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                                <h4 class="modal-title">Choose price package </h4>
                                            </div>
                                            <form action="subject-resgister">
                                                <div class="modal-body">
                                                    <div class="my-3 col-12">
                                                        <b>Price Package:
                                                            <select name="priceId" class="form-control" onchange="PricePackageAsync(this.value)">
                                                                <c:forEach items="${listAllPricePackage}" var="PP">
                                                                    <option value="${PP.priceId}">${PP.name}</option>
                                                                </c:forEach>
                                                            </select></b>
                                                        <div class="mt-3" id="update_price_async">
                                                            <p>SalePrice - Price : ${SBSI.getSalePrice()} - ${SBSI.getPrice()}</p>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="modal-footer">
                                                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                                    <button type="submit" class="btn btn-primary">Register</button>
                                                </div>
                                            </form>

                                        </div><!-- /.modal-content -->
                                    </div><!-- /.modal-dialog -->
                                </div><!-- /.modal -->
                            </div>

                        </c:forEach>
                    </div>
                    <!-- Call to Action-->
                    <div class="card text-white bg-secondary my-5 py-4 text-center">
                        <div class="card-body" style="padding: 0px"><p class="text-white m-0" style="font-size: 30px">First 3 Lessons</p></div>
                    </div>
                    <!-- Content Row-->

                    <div class="row gx-4 gx-lg-5">
                        <c:forEach items="${listLessonsBySubId}" var="l">
                            <div class="col-md-4 mb-5">
                                <div class="card h-100">
                                    <div class="card-body">
                                        <h2 class="card-title">${l.lessonName}</h2>
                                        <p class="card-text">${l.description}</p>
                                    </div>
                                    <div class="card-footer"><a class="btn btn-primary btn-sm" href="#!">More Info</a></div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>

                </div>
            </div>
        </div>
    </body>
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
    <script>
                                                                function PricePackageAsync(priceId) {
                                                                    axios.get('update-price-async', {
                                                                        params: {
                                                                            priceId: priceId
                                                                        }
                                                                    }).then((response) => {
                                                                        document.getElementById("update_price_async").innerHTML = response.data;
                                                                    })
                                                                }
    </script>
</html>
