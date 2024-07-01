<%-- 
    Document   : add-user
    Created on : May 24, 2024, 1:01:50 AM
    Author     : PC
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<h1 class="text-center text-info mt-1">
    <c:choose>
        <c:when test="${user.id > 0}">CẬP NHẬT THÔNG TIN NGƯỜI DÙNG</c:when>
        <c:otherwise>THÊM TÀI KHOẢN TRỢ LÝ SINH VIÊN</c:otherwise>
    </c:choose>
</h1>

<c:url value="/users/add-user" var="action" />
<form:form method="post" action="${action}" modelAttribute="user" enctype="multipart/form-data">
    <form:errors path="*" element="div" cssClass="alert alert-danger" />
    <div class="form-floating mb-3 mt-3">
        <form:input class="form-control" id="username" placeholder="Tên đăng nhập" path="username" />
        <label for="username">Tên đăng nhập</label>
    </div>

    <div class="form-floating mb-3 mt-3">
        <form:input class="form-control" id="password" placeholder="Mật khẩu" path="password" />
        <label for="password">Mật khẩu</label>
    </div>

    <div class="form-floating mb-3 mt-3">
        <form:input class="form-control" id="name" placeholder="Họ tên" path="ten" />
        <label for="name">Họ tên</label>
    </div>

    <div class="form-floating mb-3 mt-3">
        <form:input class="form-control" id="email" placeholder="Email trường" path="email" />
        <label for="email">Email trường</label>
    </div>

    <div class="form-floating mb-3 mt-3">
        <form:select class="form-select" id="khoa" name="khoa" path="idKhoa">
            <c:forEach items="${khoa}" var="khoa">
                <c:choose>
                    <c:when test="${khoa.id==user.idKhoa.id}">
                        <option value="${khoa.id}" selected>${khoa.tenkhoa}</option>
                    </c:when>
                    <c:otherwise>
                        <option value="${khoa.id}">${khoa.tenkhoa}</option>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </form:select>
        <label for="khoa">Chọn khoa:</label>
    </div>

    <div class="form-floating mb-3 mt-3">
        <form:input class="form-control" id="birth" placeholder="Ngày sinh" path="ngaysinh" type="date" />
        <label for="birth">Ngày sinh</label>
    </div>

    <div class="form-floating mb-3 mt-3">
        <form:input class="form-control" id="sex" placeholder="Giới tính" path="gioitinh" />
        <label for="sex">Giới tính</label>
    </div>

    <div class="form-floating mb-3 mt-3">
        <form:input type="file" class="form-control" id="avatar" path="file"/>
        <label for="avatar">Avatar</label>

        <c:if test="${user.id > 0}">
            <img src="${user.avatar}" width="100" class="img-fluid"/>
        </c:if>
    </div>

    <form:hidden path="ngaytao" />
    <div class="form-floating">
        <button class="btn btn-info mt-1" type="submit">
            <c:choose>
                <c:when test="${user.id > 0 }">Cập nhật</c:when>
                <c:otherwise>Thêm</c:otherwise>
            </c:choose>
        </button>
        <form:hidden path="id"></form:hidden>
        </div>
</form:form>