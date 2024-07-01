<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<div class="container mt-5">
    <h1 class="text-center text-info mt-2 mb-4">Danh sách Báo Thiếu</h1>
    <a class="btn btn-info mt-2 mb-2" href="<c:url value='/'/>">Trở về</a>
    <table class="table table-striped">
        <thead>
            <tr>
                <th scope="col">Mã Sinh Viên</th>
                <th scope="col">Sinh Viên Báo Thiếu</th>
                <th scope="col">Minh Chứng</th>
                <th scope="col">Ngày Báo</th>
                <th scope="col"></th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="baothieu" items="${baoThieuList}">
                <tr>                 
                    <td>${baothieu.idUser.masinhvien}</td>
                    <td>${baothieu.idUser.ten}</td>
                    <td>
                        <a href="${baothieu.minhchung}" target="_blank">
                            <img src="${baothieu.minhchung}" style="max-width: 100px; max-height: 100px;">
                        </a>
                    </td>
                    <td>${fn:substring(baothieu.ngaybao, 0, 10)}</td>
                    <td>
                        <button class="btn btn-info" onclick="duyetBaoThieu(${baothieu.id}, ${baothieu.idUser.id})">Duyệt</button>
                        <button class="btn btn-danger" onclick="confirmDeleteBaoThieu(${baothieu.id})">Xóa</button>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>
<script src="<c:url value='/js/script.js'/>"></script>

