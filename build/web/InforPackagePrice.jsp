<%-- 
    Document   : InforPackagePrice
    Created on : Jun 28, 2022, 11:45:02 PM
    Author     : 84969
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<table class="table" >
    <thead>
        <tr>
            <th scope="col">PackageId</th>
            <th scope="col">Package </th>
            <th scope="col">Duration </th>
            <th scope="col">ListPrice</th>
            <th scope="col">SalePrice</th>
            <th scope="col">Status</th>
            <th scope="col">Description</th>
        </tr>
    </thead>
    <tbody>
        <tr class="table-active">

            <td>${requestScope.pricePack.priceId}</td>
            <td>${requestScope.pricePack.name}</td>
            <td>${requestScope.pricePack.acessDuration}</td>
            <td>${requestScope.pricePack.price}</td>
            <td>${requestScope.pricePack.salePrice}</td>
            <td>${requestScope.pricePack.status}</td>
            <td>${requestScope.pricePack.description}</td>
        </tr>
    </tbody>
</table>