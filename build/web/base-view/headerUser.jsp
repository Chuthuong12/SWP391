<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- Navbar Start -->
<c:choose>
    <c:when test="${sessionScope.account == null}">
        <nav class="navbar">
            <a href="home" class="navbar-brand">
                <h2>QuizSystem</h2>
            </a>
            <div class="navbar-collapse" id="navbarCollapse">
                <div class="navbar-nav">
                    <a href="home" class="nav-item nav-link active">Home</a>
                   
                </div>
                <!--<a href="Login" class="btn-primary">Join Now <i class="fa-solid fa-arrow-right"></i></a>-->
            </div>
        </nav>
    </c:when>
    <c:otherwise >
        <nav class="navbar">
            <a href="home" class="navbar-brand">
                <h2>Quiz System</h2>
            </a>
            <div class="navbar-collapse" id="navbarCollapse">
                <div class="navbar-nav">
                    <a href="home" class="nav-item nav-link active">Home</a>
<!--                    <a href="#" class="nav-item nav-link">Courses</a>
                    <a href="#" class="nav-item nav-link">My Courses</a>
                    <a href="blog" class="nav-item nav-link">Blog</a>-->
                </div>
                <div id="top-bar">
                    <ul>
                        <div class="topbar-divider"></div>
                        <!-- Nav Item - User Information -->
                        <li>
                            <a href="#" id="dropdown-toggle" onclick="dropdown()">
                                <div id="name-balance">
                                    <span>${sessionScope.account.fullname} </span>
                                   
                                </div>
                                <img src="uploads/${sessionScope.account.avatar}">
                            </a>
                            <!-- Dropdown - User Information -->
                            <div id="dropdown-menu">
                                <a class="dropdown-item" href="profile">
                                    <i class="fa-solid fa-address-card"></i>
                                    Profile
                                </a>
<!--                                <a class="dropdown-item" href="#">
                                    <i class="fa-solid fa-gear"></i>
                                    Settings
                                </a>
                                <a class="dropdown-item" href="#">
                                    <i class="fa-solid fa-list-check"></i>
                                    Activity Log
                                </a>-->
                                <div class="dropdown-divider"></div>
                                <a class="dropdown-item" href="logout">
                                    <i class="fa-solid fa-right-from-bracket"></i>
                                    Logout
                                </a>
                            </div>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>
        <script src="./js/base.js"></script>
    </c:otherwise>     
</c:choose>