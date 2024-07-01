<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div class="container mt-5">
    <h1 class="text-center text-info mt-2 mb-4">Xem Thành Tích</h1>
    <%-- Kiểm tra xem có thông báo lỗi không --%>
    <c:if test="${not empty errorMessage}">
        <div class="alert alert-danger" role="alert">
            ${errorMessage}
        </div>
    </c:if>
    <div class="row justify-content-center mb-5">
        <div class="col-md-6">
            <div class="card">
                <div class="card-body">
                    <form id="thanhtichForm" method="get">
                        <div class="mb-3">
                            <label for="idUser" class="form-label">Nhập Mã Sinh Viên</label>
                            <input type="text" class="form-control" id="idUser" name="idUser" required>
                        </div>
                        <div class="mb-3">
                            <label for="idHocKy" class="form-label">Học Kỳ</label>
                            <select class="form-control" id="idHocKy" name="idHocKy" required>
                                <c:forEach var="hocky" items="${hockys}">
                                    <option value="${hocky.id}">${hocky.hocky} - ${hocky.namhoc}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <button type="submit" class="btn btn-success">Xem Thành Tích</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <div>
        <h2 class="text-info">Kết Quả</h2>
    </div>
    <table class="table table-striped">
        <thead>
            <tr>
                <th scope="col">Tên Sinh Viên</th>
                <th scope="col">Mã Sinh Viên</th>
                <th scope="col">Điểm Loại 1</th>
                <th scope="col">Điểm Loại 2</th>
                <th scope="col">Điểm Loại 3</th>
                <th scope="col">Tổng điểm</th>
                <th scope="col">Xếp Loại</th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <td>${sinhvien.ten}</td>
                <td>${sinhvien.masinhvien}</td>
                <td>${results.diemDieu1}</td>
                <td>${results.diemDieu2}</td>
                <td>${results.diemDieu3}</td>
                <td>${results.tongDiem}</td>
                <td>${results.xepLoai}</td>
            </tr>
        </tbody>
    </table>
</div>

<script type="text/javascript">
    document.getElementById("thanhtichForm").onsubmit = function (e) {
        e.preventDefault();
        var idUser = document.getElementById("idUser").value;
        var idHocKy = document.getElementById("idHocKy").value;
        window.location.href = "<c:url value='/tlsv/sinhvien/thanhtich'/>/" + idUser + "/" + idHocKy;
    };
</script>
