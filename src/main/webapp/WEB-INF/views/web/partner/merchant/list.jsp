<%@ page import="java.util.List" %>
<%@ page import="com.laptrinhjavaweb.util.SecurityUtils" %>
<%@include file="/common/taglib.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<c:url var="merchantURL" value="/merchant/danh-sach"/>
<c:url var="editMerchantURL" value="/merchant/chinh-sua"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Danh sách hoa</title>
</head>

<body>
<div class="main-content">
    <form action="<c:url value='/merchant/danh-sach'/>" id="formSubmit" method="get">

        <div class="main-content-inner">
            <div class="breadcrumbs ace-save-state" id="breadcrumbs">

                <%
                    List<String> roles = SecurityUtils.getAuthorities();
                    String url = "";

                    if (roles.contains("ADMIN")) {
                        url = "/quan-tri/trang-chu";
                    } else if (roles.contains("USER")) {
                        url = "/trang-chu";
                    }
                %>

                <ul class="breadcrumb">
                    <li>
                        <i class="ace-icon fa fa-home home-icon"></i>
                        <a href="<c:url value='<%= url %>'/>">Trang chủ</a>
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
                                        <c:url var="createMerchantURL" value="/merchant/chinh-sua"/>
                                        <a flag="info"
                                           class="dt-button buttons-colvis btn btn-white btn-primary btn-bold" data-toggle="tooltip"
                                           title='Thêm mới merchant' href='${createMerchantURL}'>
															<span>
																<i class="fa fa-plus-circle bigger-110 purple"></i>
															</span>
                                        </a>
                                        <button id="btnDelete" type="button" onclick="warningBeforeDelete()"
                                                class="dt-button buttons-html5 btn btn-white btn-primary btn-bold" data-toggle="tooltip" title='Xóa'>
																<span>
																	<i class="fa fa-trash-o bigger-110 pink"></i>
																</span>
                                        </button>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-xs-12">
                                <div class="table-responsive">
                                    <table class="table table-bordered">
                                        <thead>
                                        <tr>
                                            <th><input type="checkbox" id="checkAll"></th>
                                            <th>Username</th>
                                            <th>Số điện thoại</th>
                                            <th>Thao tác</th>
                                            <th></th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach var="item" items="${model.listResult}">
                                            <tr>
                                                <td><input type="checkbox" id="checkbox_${item.id}" value="${item.id}"></td>
                                                <td>${item.username}</td>
                                                <td>${item.phone}</td>
                                                <td>
                                                    <c:url var="updateMerchantURL" value="/merchant/chinh-sua">
                                                        <c:param name="id" value="${item.id}"/>
                                                    </c:url>
                                                    <a class="btn btn-sm btn-primary btn-edit" data-toggle="tooltip"
                                                       title="Cập nhật merchant" href='${updateMerchantURL}'><i class="fa fa-pencil-square-o" aria-hidden="true"></i>
                                                    </a>
                                                </td>
                                                <td>
                                                    <c:url var="listInvoiceURL" value="/hoa-don/danh-sach?page=1&limit=5">
                                                        <c:param name="partnerId" value="${item.id}"/>
                                                    </c:url>
                                                    <a href="${listInvoiceURL}">
                                                        <i class="menu-icon fa fa-caret-right"></i>
                                                        Danh sách hoá đơn
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
<!-- /.main-content -->
<script>
    let totalPages = ${model.totalPage};
    let currentPage = ${model.page};
    $(function () {
        window.pagObj = $('#pagination').twbsPagination({
            totalPages: totalPages,
            visiblePages: 10,
            startPage: currentPage,
            onPageClick: function (event, page) {
                if (currentPage != page) {
                    $('#limit').val(5);
                    $('#page').val(page);
                    $('#formSubmit').submit();
                }
            }
        });
    });

    function warningBeforeDelete() {
        swal({
            title: "Xác nhận xóa",
            text: "Bạn có chắc chắn muốn xóa hay không",
            type: "warning",
            showCancelButton: true,
            confirmButtonClass: "btn-success",
            cancelButtonClass: "btn-danger",
            confirmButtonText: "Xác nhận",
            cancelButtonText: "Hủy bỏ",
        }).then(function(isConfirm) {
            if (isConfirm) {
                let ids = $('tbody input[type=checkbox]:checked').map(function () {
                    return $(this).val();
                }).get();
                deleteNew(ids);
            }
        });
    }
    function deleteNew(data) {
        $.ajax({
            url: '${editMerchantURL}',
            type: 'DELETE',
            contentType: 'application/json',
            data: JSON.stringify(data),
            success: function (result) {
                window.location.href = "${merchantURL}?page=1&limit=5&message=delete_success";
            },
            error: function (error) {
                window.location.href = "${merchantURL}?page=1&limit=5&message=error_system";
            }
        });
    }
</script>
</body>
</html>