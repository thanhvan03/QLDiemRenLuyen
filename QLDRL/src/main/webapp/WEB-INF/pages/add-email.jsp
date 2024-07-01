<%-- 
    Document   : add-email
    Created on : May 25, 2024, 12:43:22 AM
    Author     : PC
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<h1 class="text-center text-info mt-1" >
    <c:choose>
        <c:when test="${email.id > 0}">CẬP NHẬT THÔNG TIN EMAIL</c:when>
        <c:otherwise>THÊM EMAIL TRƯỜNG</c:otherwise>
    </c:choose>
</h1>
<c:url value="/emails/add-email" var="action" />
<form:form method="post" action="${action}" modelAttribute="email">

    <div class="form-floating mb-3 mt-3">
        <form:input class="form-control" id="email" placeholder="Email" path="email" />
        <label for="email">Email</label>
    </div>

    <div class="form-floating mb-3 mt-3">
        <form:input class="form-control" id="name" placeholder="Tên sinh viên" path="tensinhvien" />
        <label for="name">Tên sinh viên</label>
    </div>

    <div class="form-floating mb-3 mt-3">
        <form:input class="form-control" id="idsv" placeholder="Mã số sinh viên" path="masinhvien" />
        <label for="idsv">Mã số sinh viên</label>
    </div>

    <div class="form-floating mb-3 mt-3">
        <form:input class="form-control" id="birth" placeholder="Ngày sinh" path="ngaysinh" type="date" />
        <label for="birth">Ngày sinh</label>
    </div>

    <div class="form-floating mb-3 mt-3">
        <form:input class="form-control" id="birthplace" placeholder="Quê quán" path="quequan" />
        <label for="birthplace">Quê quán</label>
    </div>

    <div class="form-floating mb-3 mt-3">
        <form:input class="form-control" id="sex" placeholder="Giới tính" path="gioitinh" />
        <label for="sex">Giới tính</label>
    </div>

    <div class="form-floating">
        <button class="btn btn-info mt-1" type="submit">
            <c:choose>
                <c:when test="${email.id > 0}">Cập nhật</c:when>
                <c:otherwise>Thêm</c:otherwise>
            </c:choose>
        </button>
        <form:hidden path="id"></form:hidden>
        </div>

</form:form>