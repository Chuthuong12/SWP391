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

                    <h1>Practice List</h1>
                    <form action="filter-practice" method="POST" style="width: 900px; float: right !important">

                        <div class="input-group">
                            <select name="subId" class="form-control ms-2" style="margin: 2px" onchange="PracticeListBySubAsync(this.value)">
                                <option value="0">All Subject</option>
                                <c:forEach items="${listSubjects}" var="S">
                                    <option value="${S.subjectId}" ${S.subjectId == requestScope.subId?"selected":""}>${S.subjectName}</option>
                                </c:forEach>
                            </select>
                            <a href="PraticeDetail?action=add" class="btn btn-primary ms-4 pt-2" style="border-radius: 5px; height: 39px; padding-top: 10px; padding-top: 14px !important;"/>New Practice</a>
                            <!--<a href="" class="btn btn-primary ms-4 pt-2" style="border-radius: 5px; height: 39px; padding-top: 10px; padding-top: 14px !important;"/>Stimulation Exam</a>-->
                        </div>
                    </form>
                    <div class ="row" style="margin-top: 80px">

                        <div class="mb-3 mx-auto d-block shadow p-3 mb-5 bg-white rounded" style="padding: 10px 24px 14px 24px !important; border-radius: 8px; width: 32%; margin-left: 10px; width: 100% !important">
                            <table class="table table-bordered table-hover table-striped row">
                                <tbody id="filter_practice">
                                    <c:forEach items="${listPractice}" var="P">
                                        <tr class="row">
                                            <th class="col-5 text-center">${P.subName}<br>${P.title}</th>
                                            <th class="col-2 text-center">${P.taken_date}<br>Date taken</th>
                                            <th class="col-2 text-center">${P.numQuesTrue} Correct<br>${P.totalQues} Questions</th>
                                            <th class="col-1 text-center">${P.pointPercent}%<br>Correct</th>
                                            <th class="col-2 text-center">
                                                <a href="PraticeDetail?quizId=${P.quizzId}&attempt=${P.attempt}&action=detail" class="btn btn-primary"/>Detail</a>
                                            </th>
                                        </tr>
                                        <tr class="row">
                                            <th class="col-12 text-center"></th>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div><br>
                    </div>
                </div>
            </div>
        </div>

    </body>
    <script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
    <script>
        function PracticeListBySubAsync(subId) {
            axios.get('filter-practice-async', {
                params: {
                    subId: subId
                }
            }).then((response) => {
                document.getElementById("filter_practice").innerHTML = response.data;
            })
        }
    </script>
</html>
