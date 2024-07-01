<%-- 
    Document   : users
    Created on : May 22, 2024, 3:10:37 PM
    Author     : PC
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>


<h1 class="text-center text-info mt-3 mb-3">QUẢN TRỊ NGƯỜI DÙNG</h1>


<div class="float-start mb-3 mt-3">
    <form id="filterForm" action="<c:url value='/users' />" method="get" class="d-flex">
        <div class="me-3">
            <select name="userRole" id="userRole" class="form-select form-select-lg" style="width: 350px;">
                <option value="">Tất cả</option>
                <option value="ROLE_CVCTSV">Chuyên viên công tác sinh viên</option>
                <option value="ROLE_TLSV">Trợ lý sinh viên</option>
                <option value="ROLE_SV">Sinh viên</option>
            </select>
        </div>
        <button type="submit" class="btn btn-primary btn-lg">Lọc</button>
    </form>
</div>

<div class="float-end mb-3">
    <a class="btn btn-success btn-lg mt-3" href="<c:url value='/users/add-user' />">Thêm tài khoản</a>
</div>


<table class="table table-striped mt-1">
    <tr>
        <th>ID</th>
        <th>Avatar</th>
        <th>Username</th>
        <th>Email</th>
        <th>Họ tên</th>
        <th>MSSV</th>
        <th>Khoa</th>
        <th>Ngày sinh</th>
        <th></th>
    </tr>
    <c:forEach items="${users}" var="u">
        <tr>
            <td>${u.id}</td>
            <td> <img class="card-img-top" src="${u.avatar}" alt="${u.username}" style="width:50px;"></td>
            <td>${u.username}</td>
            <td>${u.email}</td>
            <td>${u.ten}</td>
            <td>${u.masinhvien}</td> 
            <td>${u.idKhoa.tenkhoa}</td>
            <td>${u.ngaysinh}</td> 
            <td>
                <a class="btn btn-info" href="<c:url value="/users/${u.id}" />">Cập nhật</a>
            </td>
        </tr>
    </c:forEach>
</table>

<script>
    document.getElementById('filterForm').addEventListener('submit', function (event) {
        var selectedUserRole = document.getElementById('userRole').value;

        // Check if "Tất cả" is selected (value is empty)
        if (selectedUserRole === '') {
            // Prevent default form submission
            event.preventDefault();

            // Submit form to the URL without appending userRole
            window.location.href = '<c:url value="/users" />';
        }
    });
</script>