<%-- 
    Document   : PracticeListAsync
    Created on : Jul 13, 2022, 11:26:48 PM
    Author     : KDIchigo
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<c:forEach items="${listPractice}" var="P">
    <tr class="row">
        <th class="col-5 text-center">${P.subName}<br>${P.title}</th>
        <th class="col-2 text-center">${P.taken_date}<br>Date taken</th>
        <th class="col-2 text-center">${P.numQuesTrue} Correct<br>${P.totalQues} Questions</th>
        <th class="col-1 text-center">${P.pointPercent}%<br>Correct</th>
        <th class="col-2 text-center">
            <a href="" class="btn btn-primary"/>Detail</a>
        </th>
    </tr>
    <tr class="row">
        <th class="col-12 text-center"></th>
    </tr>
</c:forEach>
