<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<style>
    .wrap-content-nd{
        white-space: normal;
        word-wrap: break-word;
        max-width: 35ch;
    }
    .wrap-content-ten{
        white-space: normal;
        word-wrap: break-word;
        max-width: 20ch;
    }
</style>

<div class="container mt-5">
    <h1 class="text-center text-info mb-2">Danh sách hoạt động</h1>    
    <a class="btn btn-info mt-2 mb-2" href="<c:url value='tlsv/hoatdongs'/>">Tạo Hoạt Động</a>
    <table class="table table-striped">
        <thead>
            <tr>
                <th scope="col">Tên Hoạt Động</th>
                <th scope="col">Nội Dung</th>
                <th scope="col">Ngày Bắt Đầu</th>
                <th scope="col">Ngày Kết Thúc</th>
                <th scope="col">Tiêu Chí</th>
                <th scope="col">Điểm</th>
                <th scope="col">Học Kì</th>
                <th scope="col"></th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="hoatdong" items="${hd}">
                <tr>
                    <td class="wrap-content-ten">${hoatdong.tenhoatdong}</td>
                    <td class="wrap-content-nd">${hoatdong.noidung}</td>
                    <td>${fn:substring(hoatdong.ngaybatdau, 0, 10)}</td>
                    <td>${fn:substring(hoatdong.ngayketthuc, 0, 10)}</td>
                    <td>${hoatdong.tieuchi}</td>
                    <td>${hoatdong.diem}</td>
                    <td>${hoatdong.idHocKy.hocky} - ${hoatdong.idHocKy.namhoc}</td>
                    <td style="width: 300px">
                        <a class="btn btn-success" href="<c:url value='tlsv/baothieu/${hoatdong.id}' />">Xem báo thiếu</a>
                        <a class="btn btn-info" href="<c:url value='tlsv/hoatdongs/${hoatdong.id}' />">Cập nhật</a>
                        <a class="btn btn-success" href="<c:url value='tlsv/danhsachdangky/${hoatdong.id}' />">Danh Sách Đăng Ký</a>                        
                        <button onclick="confirmDeleteHoatDong(${hoatdong.id})" class="btn btn-danger mt-1">Xóa</button>
                    </td>                   
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>
<script src="<c:url value="/js/script.js"/>"></script>