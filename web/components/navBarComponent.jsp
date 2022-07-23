<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 

<header class="sb-nav-fixed">
    <nav class="sb-topnav navbar navbar-expand navbar-light py-3" style=" background-image: url(''); background-color: #FFC533; height: 70px">
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


            <c:if test="${sessionScope.account.role.getRole_name() eq 'ADMIN'}">
                <a href="DashboardServlet?year=${sessionScope.currentYear}" class="btn m2-1 me-2 pb-1">
                    <i class="bi bi-speedometer2 text-white me-2"></i><span class="text-white">DashBoard</span>
                </a>
            </c:if>
            <c:choose>
                <c:when test="${sessionScope.account != null}">
                    <a class="btn ms-1 pb-1 account-div">
                        <i class="bi bi-person-circle text-white me-2 py-5"></i><span class="text-white">${sessionScope.account.username}</span>
                        <table class="text-dark cart-table row account-table bg-white" style="border: 1px solid gray;">
                            <tr>
                                <td><a href="profile" 
                                       class="btn btn-outline-secondary ms-lg-2 mt-2" style="padding: 6px 17px 6px 17px !important">
                                        <i class="fas fa-user fa-sm fa-fw mr-2 text-gray-400"></i>Information</a></td>   
                            </tr>
                            <tr>
                                <td><a href="Logout" class="btn btn-outline-danger ms-lg-2 mt-2"
                                       style="padding: 6px 34px 6px 34px !important">
                                        <i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>Logout</a></td>
                            </tr>
                        </table>
                    </a>
                </c:when>
            </c:choose>

        </div>
    </nav>
</header>
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


