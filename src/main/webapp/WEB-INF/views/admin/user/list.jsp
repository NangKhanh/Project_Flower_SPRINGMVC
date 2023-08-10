<%@include file="/common/taglib.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Title</title>
</head>
<body>
<div class="main-content">
    <form action="<c:url value='/quan-tri/hoa/danh-sach'/>" id="formSubmit" method="get">

        <div class="main-content-inner">
            <div class="breadcrumbs ace-save-state" id="breadcrumbs">
                <ul class="breadcrumb">
                    <li>
                        <i class="ace-icon fa fa-home home-icon"></i>
                        <a href="#">Trang chủ</a>
                    </li>
                </ul>
                <!-- /.breadcrumb -->
            </div>
            <div class="page-content">
                <div class="row">
                    <div class="col-xs-12">
                        <div class="widget-box table-filter">
                            <div class="table-btn-controls">
                                <div class="pull-right tableTools-container">
                                    <div class="dt-buttons btn-overlap btn-group">
                                        <c:url var="createUserURL" value="/quan-tri/nguoi-dung/chinh-sua"/>
                                        <a flag="info"
                                           class="dt-button buttons-colvis btn btn-white btn-primary btn-bold"
                                           data-toggle="tooltip"
                                           title='Thêm nguời dùng' href='${createUserURL}'>
															<span>
																<i class="fa fa-plus-circle bigger-110 purple"></i>
															</span>
                                        </a>

                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row">

                            <div class="col-xs-12">
                                <c:if test="${not empty message}">
                                    <div class="alert alert-${alert}">
                                            ${message}
                                    </div>
                                </c:if>
                                <div class="table-responsive">
                                    <table class="table table-bordered">
                                        <thead>
                                        <tr>
                                            <th>id</th>
                                            <th>Tên</th>
                                            <th>Trạng thái</th>
                                            <th>Nhóm</th>
                                            <th>Hành động</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach var="item" items="${model.listResult}">
                                            <tr>
                                                <td>${item.id}</td>
                                                <td>${item.fullName}</td>
                                                <c:choose>
                                                    <c:when test="${item.status == 1}">
                                                        <td>Active</td>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <td>Inactive</td>
                                                    </c:otherwise>
                                                </c:choose>
                                                <td>${item.groupName}</td>
                                                <td>
                                                    <c:url var="crearteUserURL" value="/quan-tri/nguoi-dung/chinh-sua">
                                                        <c:param name="id" value="${item.id}"/>
                                                    </c:url>
                                                    <a class="btn btn-sm btn-primary btn-edit" data-toggle="tooltip"
                                                       title="Cập nhật người dùng" href='${crearteUserURL}'><i class="fa fa-pencil-square-o" aria-hidden="true"></i>
                                                    </a>
                                                    <c:url var="crearteUserURL" value="/quan-tri/nguoi-dung/chinh-sua/doi-trang-thai">
                                                        <c:param name="id" value="${item.id}"/>
                                                        <c:param name="status" value="${item.status}"/>
                                                    </c:url>
                                                    <a class="btn btn-sm btn-danger" data-toggle="tooltip"
                                                       title="Thay đổi trạng thái người dùng" href='${crearteUserURL}'><i class="fa fa-rotate-right" aria-hidden="true"></i>
                                                    </a>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                        </tbody>
                                    </table>
                                    <ul class="pagination" id="pagination"></ul>
                                    <input type="hidden" value="" id="page" name="page"/>
                                    <input type="hidden" value="" id="limit" name="limit"/>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </form>
</div>
</body>
</html>
