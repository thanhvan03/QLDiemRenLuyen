<%-- 
    Document   : emails
    Created on : May 25, 2024, 12:26:43 AM
    Author     : PC
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<h1 class="text-center text-info mt-3">QUẢN TRỊ EMAIL TRƯỜNG</h1>
<div>
    <a class="btn btn-success" href="<c:url value="/emails/add-email"/>">Thêm email</a>
</div>
<table class="table table-striped mt-1">
    <tr>
        <th>ID</th>
        <th>Email</th>
        <th>Họ tên SV</th>
        <th>MSSV</th>
        <th>Ngày sinh</th>
        <th>Giới tính</th>
        <th></th>
    </tr>
    <c:forEach items="${emails}" var="e">
        <tr>
            <td>${e.id}</td>
            <td>${e.email}</td>
            <td>${e.tensinhvien}</td>
            <td>${e.masinhvien}</td> 
            <td>${e.ngaysinh}</td>
            <td>${e.gioitinh}</td> 
            <td>
                <c:url value="/emails/${e.id}" var="url" />
                <a class="btn btn-info" href="<c:url value="/emails/${e.id}" />">Cập nhật</a>
                <button onclick="confirmDeleteEmail('${url}', ${e.id})" class="btn btn-danger">Xóa</button>
            </td>
        </tr>
    </c:forEach>
</table>

<script src="<c:url value="/js/script.js"/>"></script>