<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<div class="container mt-5">
    <h1 class="text-center text-info mt-2 mb-4">Danh sách Báo Thiếu Theo Khoa</h1>
    <div class="row justify-content-center mb-5">
        <div class="col-md-6">
            <div class="card">
                <div class="card-body">
                    <form id="xemBaoThieuKhoa" method="get">
                        <div class="mb-3">
                            <label for="idKhoa" class="form-label">Chọn Khoa</label>
                            <select class="form-control" id="idKhoa" name="idKhoa" required>
                                <c:forEach var="khoa" items="${khoaList}">
                                    <option value="${khoa.id}">${khoa.tenkhoa}</option>
                                </c:forEach>
                            </select>
                        </div>                       
                        <button type="submit" class="btn btn-success" id="submitBtn">Xem Báo Thiếu</button>
                        <a class="btn btn-info mt-2 mb-2" href="<c:url value='/'/>">Trở về</a>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <div>
        <h2 class="text-info">Kết Quả</h2>
    </div>
    <table class="table table-striped mt-3"  >
        <thead>
            <tr>
                <th scope="col">Hoạt Động</th>
                <th scope="col">Sinh Viên Báo Thiếu</th>
                <th scope="col">Mã Sinh Viên </th>
                <th scope="col">Minh Chứng</th>
                <th scope="col">Ngày Báo</th>
                <th scope="col"></th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="baothieu" items="${baoThieuList}">
                <tr>
                    <td>${baothieu.idHoatDong.tenhoatdong}</td>
                    <td>${baothieu.idUser.ten}</td>
                    <td>${baothieu.idUser.masinhvien}</td>
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
<!--        </div>-->
</div>
<script src="<c:url value='/js/script.js'/>"></script>
<script>
    document.getElementById("xemBaoThieuKhoa").onsubmit = function (e) {
        e.preventDefault();
        var idKhoa = document.getElementById("idKhoa").value;
        window.location.href = "<c:url value='/tlsv/baothieukhoa'/>/" + idKhoa;
    };
</script>
