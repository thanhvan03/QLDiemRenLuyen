<%-- 
    Document   : danhsachdangky
    Created on : Jun 5, 2024, 2:00:20 AM
    Author     : DELL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<div class="container mt-5">
    <h1 class="text-center text-info mt-2 mb-4">Danh sách Đăng Ký</h1>
    <div>
        <div class="form-floating mt-3 mb-3">
            <!-- Form upload file CSV -->
            <form id="uploadForm" action="<c:url value='/api/tlsv/diemrenluyen/nhapdiem-csv/${hoatdong.id}'/>" method="post" enctype="multipart/form-data" class="border p-4 rounded bg-light">
                <div class="mb-3">
                    <label for="file" class="form-label">Nhập điểm từ file CSV:</label>
                    <input type="file" class="form-control" id="file" name="file" accept=".csv" required>
                </div>
                <button type="submit" class="btn btn-primary mt-2 mb-2">Upload</button>
                <a class="btn btn-info mt-2 mb-2" href="<c:url value='/'/>">Trở về</a>
            </form>
        </div>
    </div>

    <table class="table table-striped">
        <thead>
            <tr>            
                <th scope="col">Mã Sinh Viên</th>
                <th scope="col">Tên Sinh Viên</th>
                <th scope="col">Ngày Đăng ký</th>
                <th scope="col"></th>
            </tr>
        </thead>
        <tbody>
        <tbody>
            <c:forEach var="dangKy" items="${dsDangKy}">
                <tr>
                    <td>${dangKy.idUser.masinhvien}</td>
                    <td>${dangKy.idUser.ten}</td>                    
                    <td>${fn:substring(dangKy.ngaydangky, 0, 10)}</td>
                    <td>
                        <button class="btn btn-success" onclick="nhapDiem(${dangKy.idHoatDong.id}, ${dangKy.idUser.id})">Nhập điểm</button>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>

<script src="<c:url value='/js/script.js'/>"></script>