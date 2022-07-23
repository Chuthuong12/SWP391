<%-- 
    Document   : UserDetail
    Created on : Jul 8, 2022, 3:28:46 PM
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
            <div id="layoutSidenav_content">
                <div class="container-fluid px-4 px-lg-5 mb-5" style="margin-top: 91px">
                    <h1>User Detail of ${acc.fullname} </h1>
                    <div class="mb-3 mx-auto d-block shadow p-3 bg-white rounded" style="padding: 10px 0px 10px 10px; border-radius: 8px; width: 32%; margin-left: 10px; width: 100% !important">
                        
                        <table>                   

                            <tr><td colspan="2" id="error-fname"></td></tr>

                            <tr>
                                <td>Avatar</td>
                                <td><img src="${acc.avatar}"/></td>
                            </tr>
                            <tr>
                                <td>Full name: </td>
                                <td>${acc.fullname}</td>
                            </tr>
                            <tr><td colspan="2" id="error-lname" ></td></tr>

                            <tr>
                                <td>Phone: </td>
                                <td>${acc.phone}"</td>
                            </tr>
                            <tr><td colspan="2" ></td></tr>

                            <tr>
                                <td>Email: </td>
                                <td>${acc.email}</td>
                            </tr>
                            <tr><td colspan="2"></td></tr>

                            <tr>
                                <td>Password: </td>
                                <td>*******</td>
                            </tr>
                            <tr><td></td></tr>

                            <tr>
                                <td>Address: </td>
                                <td>${acc.address}</td>
                            </tr>
                            <tr><td colspan="2"></td></tr>


                        </table>
                    </div>
                </div>  
            </div>
        </div>

    </form>
</body>
</html>
