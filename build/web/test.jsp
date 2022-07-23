<%-- 
    Document   : doQuizz
    Created on : Jun 27, 2022, 5:34:11 PM
    Author     : Fangl
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <script>
            function timerfunction() {
            <% HttpSession session1 = request.getSession();%>
                var minute = <%=(int) session1.getAttribute("TIMER")%>;
                minute--;
                var sec = 60;
                setInterval(function () {
                    document.getElementById("timer").innerHTML = minute + " : " + sec;
                    sec--;
                    if (sec == 00) {
                        minute--;
                        sec = 60;
                        if (minute == 0) {
//                            minute = 5;
                        }
                    }
                    if (minute == 0) {
                        document.getElementById("myForm").submit();
                    }
                }, 1000);
            }
        </script>
        <style>
            .account-div{
                position: relative;
            }

            .account-table{
                display: none;
                position: absolute;
                top: 34px;
                right: 0;
                width: 160px;
                border-radius: 5px;
                padding: 10px;
            }

            .account-div:hover .account-table{
                display: block;
            }
        </style>
    </head>
    <body class="sb-sidenav-toggled" onload="timerfunction()">
        <header class="sb-nav-fixed"><nav class="sb-topnav navbar navbar-expand navbar-light py-3" style=" background-image: url(''); background-color: #FFC533; height: 70px">
                <!-- Navbar Brand-->
                <div class="container-fluid px-4 px-lg-5">
                    <!-- Navbar Search-->
                    <img src="images/Logo/Logo_Black.png" style="height: 40px;" alt=""></a>
                    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
                        <span class="navbar-toggler-icon"></span>
                    </button>
                    <button class="btn btn-link btn-sm order-1 order-lg-0 me-5 me-lg-0 mx-4 mt-2" id="sidebarToggle" href="#!"><i class="fas fa-bars" style="font-size: 20px"></i></button>
                    <div class="collapse navbar-collapse" id="navbarNavDropdown">
                        <ul class="navbar-nav">
                            <li></li>
                            <li class="nav-item me-5">
                                <a class="nav-link active mt-2" style="padding-left: 30px" aria-current="page" href="home">HOME</a>
                            </li>
                            <li class="nav-item me-5">
                                <a class="nav-link text-dark mt-2" href="subject-list">SUBJECT</a>
                            </li>
                            <li class="nav-item me-5">
                                <a class="nav-link text-dark mt-2" href="post-list?blogAction=get">BLOG</a>
                            </li>
                        </ul>
                    </div>

                    <div class="collapse navbar-collapse" id="navbarSupportedContent">
                        <form action="SearchUrl" method="POST" class="d-flex">
                            <div class="input-group">
                                <input type="search" name="keyword" id="form1" class="form-control" placeholder="Search"/>
                                <!--<label class="form-label" for="form1">Search</label>-->
                                <button type="submit" class="btn btn-primary">
                                    <i class="fas fa-search"></i>
                                </button>
                            </div>
                        </form>
                    </div>

                    <div class="collapse navbar-collapse" id="navbarSupportedContent">
                        <div class="row p-2">
                            Your Time: <p class="border border-dark p-2" style="background-color: red; color: white" id="timer"><svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" width="24" height="24"><path d="M12.5 7.25a.75.75 0 00-1.5 0v5.5c0 .27.144.518.378.651l3.5 2a.75.75 0 00.744-1.302L12.5 12.315V7.25z"></path><path fill-rule="evenodd" d="M12 1C5.925 1 1 5.925 1 12s4.925 11 11 11 11-4.925 11-11S18.075 1 12 1zM2.5 12a9.5 9.5 0 1119 0 9.5 9.5 0 01-19 0z"></path></svg></p>
                        </div>
                    </div>

                    <c:choose>
                        <c:when test="${sessionScope.account != null}">
                            <a class="btn ms-1 pb-1 account-div">
                                <i class="bi bi-person-circle text-white me-2 py-5"></i><span class="text-white">${sessionScope.account.username}</span>
                                <table class="text-dark cart-table row account-table" style="border: 1px solid gray;">
                                    <tr>
                                        <td><a href="profile" 
                                               class="btn btn-outline-secondary ms-lg-2 mt-2" style="padding: 6px 17px 6px 17px !important">Information</a></td>   
                                    </tr>
                                    <tr>
                                        <td><a href="" class="btn btn-outline-dark ms-lg-2 mt-2"
                                               style="padding: 6px 34px 6px 34px !important">History</a></td>   
                                    </tr>
                                    <tr>
                                        <td><a href="Logout" class="btn btn-outline-danger ms-lg-2 mt-2"
                                               style="padding: 6px 34px 6px 34px !important">Logout</a></td>
                                    </tr>
                                </table>
                            </a>
                        </c:when>
                    </c:choose>

                </div>
            </nav></header>
        <div class="col-lg-4">
            <!-- Side widgets-->

        </div>
        <div id="layoutSidenav" class="mb-4">
            <%@include file="components/catgoryComponent_1.jsp" %>
            <div id="layoutSidenav_content">
                <div class="container-fluid px-4 px-lg-5 mb-5" style="margin-top: 91px">
                    <p class="fs-1 fw-bold">Quiz Name: ${QUIZZ.title}</p>
                    <p class="fs-2 fw-light">Total Question: ${QUIZZ.totalQues}</p>
                    <div class="container">
                        <div class="row">
                            <div class="col-lg-8">

                                <!-- Blog entries-->
                                <form action="DoQuizzServlet" method="post" id="myForm">
                                    <input type="hidden" name="quizzId" value="${requestScope.quizzId}" />
                                    <div class="row">
                                        <div class="col-12">
                                            <div>
                                                <ol type="1">
                                                    <c:forEach var="questionMap" items="${sessionScope.DO_QUIZZ}">
                                                        <li>
                                                            <span>${questionMap.key.content} </span>
                                                            <input type="hidden" name="questionId" value="${questionMap.key.questionId}">
                                                            <ol type="A">
                                                                <c:if test="${questionMap.key.isIsMultipleChoice() eq 'true'}">
                                                                    <c:forEach var="ans" items="${questionMap.value}">
                                                                        <li>
                                                                            <input type="checkbox" name="answer_${questionMap.key.questionId}" value="${ans.answerId}">
                                                                            <span><input type="text" name="name" value="${ans.content}" class="col-6" readonly=""></span>
                                                                        </li>
                                                                    </c:forEach>
                                                                </c:if>
                                                                <c:if test="${questionMap.key.isIsMultipleChoice() ne 'true'}">
                                                                    <c:forEach var="ans" items="${questionMap.value}">
                                                                        <li>
                                                                            <input type="radio" name="answer_${questionMap.key.questionId}" value="${ans.answerId}">
                                                                            <span><input type="text" name="name" value="${ans.content}" readonly="" class="col-6"></span>
                                                                        </li>
                                                                    </c:forEach>
                                                                </c:if>
                                                            </ol>
                                                        </li>
                                                        <br>
                                                    </c:forEach>
                                                </ol>
                                                <div class="col-12">
                                                    <div class="d-flex justify-content-center">
                                                        <!--<button type="submit" class="btn btn-primary px-4 py-2 fw-bold">check</button>-->
                                                        <button type="submit" name="btnAction" value="submitQuizz" class="btn btn-primary px-4 py-2 fw-bold">Submit</button> 
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </form>
                            </div>



                        </div>

                    </div>
                </div>
            </div>
        </div>





    </body>
</html>
