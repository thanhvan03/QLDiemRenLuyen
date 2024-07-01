<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
    <div class="container-fluid">
        <a class="navbar-brand" href="#">Forging points</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#collapsibleNavbar">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="collapsibleNavbar">
            <ul class="navbar-nav me-auto">
                <li class="nav-item">
                    <a class="nav-link" href="<c:url value="/" />">Trang chủ</a>
                </li>                          
                <c:choose>
                    <c:when test="${pageContext.request.userPrincipal.name == 'admin'}">
                        <li class="nav-item">
                            <a class="nav-link" href="<c:url value="/users" />">QL Người dùng</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="<c:url value="/emails" />">Email trường</a>
                        </li>
                        <li class="nav-item ms-1 me-1">
                            <a class="nav-link ms-1 me-1" href="<c:url value="/admin/stats-khoa" />">Thống kê theo Khoa</a>
                        </li>  
                        <li class="nav-item ms-1 me-1">
                            <a class="nav-link ms-1 me-1" href="<c:url value="/tlsv/stats-lop" />">Thống kê theo Lớp</a>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li class="nav-item ms-1 me-1">
                            <a class="nav-link ms-1 me-1" href="<c:url value="/tlsv/stats-lop" />">Thống kê theo Lớp</a>
                        </li>
                    </c:otherwise>
                </c:choose>
                                        <li class="nav-item">
                    <a class="nav-link ms-1 me-1" href="<c:url value="/tlsv/sinhvien/thanhtich" />">Thành Tích Sinh Viên</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link ms-1 me-1" href="<c:url value="/tlsv/baothieukhoa" />">Báo Thiếu Khoa</a>
                </li>   
            </ul>
            <div>
                <c:choose>
                    <c:when test="${pageContext.request.userPrincipal.name == null}">
                        <li class="nav-item">
                            <a class="btn btn-primary" href="<c:url value="/login" />">Đăng nhập</a>
                        </li>
                    </c:when>
                    <c:when test="${pageContext.request.userPrincipal.name != null}">
                        <li class="nav-item">
                            <a href="<c:url value="/" />">Xin Chào ${pageContext.request.userPrincipal.name}!</a>
                            <a class="btn btn-primary" href="<c:url value="/logout" />">Đăng xuất</a>
                        </li>
                    </c:when>
                </c:choose>
            </div> 
        </div>
    </div>
</nav>
