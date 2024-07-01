<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
    <head>
        <title>Thống Kê Báo Cáo</title>
        <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    </head>
    <body>
        <div class="row">
            <h1 class="text-center text-primary mt-2 mb-4">Thống Kê Báo Cáo</h1>
                <div class="col-md-5 col-12" >
                <form id="thongKeForm" method="get" action="<c:url value='/admin/stats-khoa'/>">
                    <div class="form-group row">
                        <div class="col-md-5 col-12">
                            <label for="hocKyId" class="col-form-label">Chọn Học Kỳ</label>
                            <select class="form-control" id="hocKyId" name="hocKyId" required>
                                <c:forEach var="hocKy" items="${hockys}">
                                    <option value="${hocKy.id}" <c:if test="${hocKy.id == selectedHocKyId}">selected</c:if>>${hocKy.hocky} - ${hocKy.namhoc}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="col-md-5 col-12">
                            <label for="khoaId" class="col-form-label">Chọn Khoa</label>
                            <select class="form-control" id="khoaId" name="khoaId" required>
                                <c:forEach var="khoa" items="${khoas}">
                                    <option value="${khoa.id}" <c:if test="${khoa.id == selectedKhoaId}">selected</c:if>>${khoa.tenkhoa}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="col-md-2 col-12 d-flex align-items-end justify-content-center">
                            <button type="submit" class="btn btn-success">Thống Kê</button>
                        </div>
                    </div>
                </form> 
                <div class="mt-5">
                    <h2 class="text-center text-info">Kết Quả Thống Kê</h2>
                    <table class="table table-striped mt-3">
                        <thead>
                            <tr>
                                <th scope="col">Xếp Loại</th>
                                <th scope="col">Số Lượng</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="entry" items="${stats}">
                                <tr>
                                    <td>${entry.key}</td>
                                    <td>${entry.value}</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
            <div class="col-md-7 col-12">
                <h2 class="text-center text-info">Biểu Đồ Thống Kê</h2>
                <canvas id="thongKeChart"  style="margin-left: 30px; height: 235px; width: 50%"></canvas>
            </div>
        </div>                                                                  
        <script>
            window.onload = function () {
                // Lấy dữ liệu từ server
                var labels = [];
                var data = [];
            <c:forEach var="entry" items="${stats}">
                labels.push('${entry.key}');
                data.push(${entry.value});
            </c:forEach>

                // Tạo biểu đồ
                var ctx = document.getElementById('thongKeChart').getContext('2d');
                var thongKeChart = new Chart(ctx, {
                    type: 'bar', // Kiểu biểu đồ: bar (biểu đồ cột)
                    data: {
                        labels: labels,
                        datasets: [{                               
                                data: data,
                                
                                backgroundColor: [
                                    'rgba(255, 99, 132, 0.2)',
                                    'rgba(54, 162, 235, 0.2)',
                                    'rgba(255, 206, 86, 0.2)',
                                    'rgba(75, 192, 192, 0.2)',
                                    'rgba(153, 102, 255, 0.2)',
                                    'rgba(255, 159, 64, 0.2)'
                                ],
                                borderColor: [
                                    'rgba(255, 99, 132, 1)',
                                    'rgba(54, 162, 235, 1)',
                                    'rgba(255, 206, 86, 1)',
                                    'rgba(75, 192, 192, 1)',
                                    'rgba(153, 102, 255, 1)',
                                    'rgba(255, 159, 64, 1)'
                                ],
                                borderWidth: 1,
                                label: 'Số lượng sinh viên'
                            }]
                    },
                    options: {
                        scales: {
                            y: {
                                beginAtZero: true
                            }
                        }
                    }
                });
            };
        </script>
    </body>
</html>
