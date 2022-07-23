<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="layoutSidenav_nav">
    <nav class="sb-sidenav accordion sb-sidenav-light" id="sidenavAccordion" style="background-color: #f1d692 !important; margin-top: 70px">
        <div class="sb-sidenav-menu">
            <div class="nav">
                <div class="sb-sidenav-menu-heading">Quiz Question</div>
                <a class="nav-link" href="">
                    <div class="sb-nav-link-icon"><i class="bi bi-question-circle-fill"></i></div>
                    Quiz Question
                </a>
                <div class="sb-sidenav-menu-heading">Subject</div>

                <a class="nav-link collapsed" href="#" data-bs-toggle="collapse" data-bs-target="#collapseSub" aria-expanded="false" aria-controls="collapsePages">
                    <div class="sb-nav-link-icon"><i class="fas fa-book-open"></i></div>
                    Subject
                    <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                </a>
                <c:forEach items="${sessionScope.listSubjects}" var="S">
                    <div class="collapse" id="collapseSub" aria-labelledby="headingOne" data-bs-parent="#sidenavAccordion">

                        <nav class="sb-sidenav-menu-nested nav accordion" id="sidenavAccordionPages">

                            <a class="nav-link collapsed" href="#" data-bs-toggle="collapse" data-bs-target="#listLessonId${S.subjectId}" aria-expanded="false" aria-controls="pagesCollapseAuth">
                                ${S.subjectName}
                                <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                            </a>
                            <c:forEach items="${sessionScope.listLessons}" var="L">
                                <c:choose>
                                    <c:when test="${S.subjectId == L.subId}">
                                        <div class="collapse" id="listLessonId${L.subId}" aria-labelledby="headingOne" data-bs-parent="#sidenavAccordionPages">
                                            <nav class="sb-sidenav-menu-nested nav">
                                                <a class="nav-link" href="lesson-quiz?lessonId=${L.lessonId}&subId=${L.subId}&action=post">${L.lessonName}: ${L.content}</a>
                                            </nav>
                                        </div>
                                    </c:when>
                                </c:choose>

                            </c:forEach>
                        </nav>
                    </div>
                </c:forEach>

<!--                <div class="sb-sidenav-menu-heading">Marketing</div>
                <a class="nav-link collapsed" href="slider-list">
                    <div class="sb-nav-link-icon"><i class="bi bi-file-earmark-ppt-fill"></i></div>
                    Slider
                    <div class="sb-sidenav-collapse-arrow"></div>
                </a>-->
                
                <div class="sb-sidenav-menu-heading">Course Content</div>
                <a class="nav-link collapsed" href="subject-lesson">
                    <div class="sb-nav-link-icon"><i class="bi bi-file-earmark-ppt-fill"></i></div>
                    Subject Lesson
                    <div class="sb-sidenav-collapse-arrow"></div>
                </a>
                
                <div class="sb-sidenav-menu-heading">Customer</div>
                <a class="nav-link collapsed" href="practice-list">
                    <div class="sb-nav-link-icon"><i class="bi bi-file-earmark-ppt-fill"></i></div>
                    Practice List
                    <div class="sb-sidenav-collapse-arrow"></div>
                </a>
                <a class="nav-link collapsed" href="MyRegistrationServlet">
                    <div class="sb-nav-link-icon"><i class="bi bi-file-earmark-ppt-fill"></i></div>
                    My Registration
                    <div class="sb-sidenav-collapse-arrow"></div>
                </a>
                
            </div>
        </div>
    </nav>
</div>
