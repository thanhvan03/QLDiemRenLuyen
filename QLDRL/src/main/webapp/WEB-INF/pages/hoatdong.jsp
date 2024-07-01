<%-- 
    Document   : test
    Created on : May 27, 2024, 5:10:34 AM
    Author     : DELL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div class="container mt-5">

    <c:url value="/tlsv/hoatdongs" var="action"/>
    <form:form method="post" modelAttribute="hoatdong" action="${action}">
        <h1 class="text-center text-info mt-2 mb-4">
            <c:choose>
                <c:when test="${hoatdong.id > 0}"> Cập nhât hoạt động </c:when>
                <c:otherwise> Tạo Hoạt Động</c:otherwise>
            </c:choose>
        </h1>

        <form:errors path="*" element="div" cssClass="alert alert-danger" />
        <div class="form-floating mt-3 mb-3">
            <form:input type="text" class="form-control" id="tenhoatdong" placeholder="Tên Hoạt Động" path="tenhoatdong"/>
            <label for="tenhoatdong">Tên Hoạt Động</label>
        </div>
        <div class="form-floating mt-3 mb-3">
            <form:input type="text" class="form-control" id="noidung" placeholder="Nội Dung" path="noidung"/>
            <form:errors path="noidung" cssClass="text-danger" />
            <label for="noidung">Nội Dung</label>
        </div>
        <div class="form-floating mt-3 mb-3">
            <form:select path="tieuchi" class="form-select" id="tieuchi">
                <option value="Dieu 1">Dieu 1</option>
                <option value="Dieu 2">Dieu 2</option>
                <option value="Dieu 3">Dieu 3</option>
            </form:select>
            <form:errors path="tieuchi" cssClass="text-danger" />
            <label for="tieuchi">Tiêu Chí</label>
        </div>
        <div class="form-floating mt-3 mb-3">
            <form:input type="number" class="form-control" id="diem" placeholder="Điểm" path="diem"/>
            <form:errors path="diem" cssClass="text-danger" />
            <label for="diem">Điểm</label>
        </div>
        <div class="form-floating mt-3 mb-3">
            <form:input type="date" class="form-control" id="ngaybatdau" placeholder="Ngày Bắt Đầu" path="ngaybatdau"/>
            <form:errors path="ngaybatdau" cssClass="text-danger" />
            <label for="ngaybatdau">Ngày Bắt Đầu</label>
        </div>
        <div class="form-floating mt-3 mb-3">
            <form:input type="date" class="form-control" id="ngayketthuc" placeholder="Ngày Kết Thúc" path="ngayketthuc"/>
            <form:errors path="ngayketthuc" cssClass="text-danger" />
            <label for="ngayketthuc">Ngày Kết Thúc</label>
        </div>
        <div class="form-floating mt-3 mb-3">
            <form:input type="text" class="form-control" id="diadiem" placeholder="Địa Điểm" path="diadiem"/>
            <form:errors path="diadiem" cssClass="text-danger" />
            <label for="diadiem">Địa Điểm</label>
        </div>
        <div class="form-floating mt-3 mb-3">
            <form:select class="form-select" id="idHocKy" path="idHocKy">
                <c:forEach items="${hockys}" var="hocky">
                    <c:choose>
                        <c:when test="${hocky.id == hoatdong.idHocKy.id}">
                            <option value="${hocky.id}" selected>${hocky.hocky} - ${hocky.namhoc}</option>
                        </c:when>
                        <c:otherwise>
                            <option value="${hocky.id}">${hocky.hocky} - ${hocky.namhoc}</option>
                        </c:otherwise>
                    </c:choose>                            
                </c:forEach>
            </form:select>
            <form:errors path="idHocKy" cssClass="text-danger" />
            <label for="idHocKy" class="form-lable">Học Kỳ</label>
        </div> 
        <button type="submit" class="btn btn-info">
            <c:choose>
                <c:when test="${hoatdong.id > 0}"> Cập nhât </c:when>
                <c:otherwise> Tạo Hoạt Động</c:otherwise>
            </c:choose>
        </button>
        <form:hidden path="id" />  
    </form:form>
</div>


