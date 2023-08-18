<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/common/taglib.jsp"%>
<c:url var="invoiceURL" value="/hoa-don/danh-sach"/>
<c:url var="editInvoiceURL" value="/hoa-don/chinh-sua"/>

<html>
<head>
    <c:if test="${empty model.id}">
        <title>Thêm mới hoá đơn</title>
    </c:if>
    <c:if test="${not empty model.id}">
        <title>Chỉnh sửa hoá </title>
    </c:if>

</head>
<body>
<div class="main-content">
    <div class="main-content-inner">
        <div class="breadcrumbs" id="breadcrumbs">
            <script type="text/javascript">
                try {
                    ace.settings.check('breadcrumbs', 'fixed')
                } catch (e) {
                }
            </script>

            <ul class="breadcrumb">
                <li><i class="ace-icon fa fa-home home-icon"></i> <a href="<c:url value='/hoa-don/danh-sach?page=1&limit=5'/>">Trang chủ</a>
                </li>
            </ul>
            <!-- /.breadcrumb -->
        </div>
        <div class="page-content">
            <div class="row">
                <div class="col-xs-12">
                    <c:if test="${not empty message}">
                        <div class="alert alert-${alert}">
                                ${message}
                        </div>
                    </c:if>
                    <form:form class="form-horizontal" role="form" id="formSubmit" modelAttribute="model">
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="form-field-1">Chiết khấu</label>
                            <div class="col-sm-9">
                                <form:input path="discount" cssClass="col-xs-10 col-sm-5"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="form-field-1">Số lượng</label>
                            <div class="col-sm-9">
                                <form:input path="amount" cssClass="col-xs-10 col-sm-5"/>
                            </div>
                        </div>
<%--                        <div class="form-group">--%>
<%--                            <label for="type" class="col-sm-3 control-label no-padding-right">Loại khách hàng:</label>--%>
<%--                            <div class="col-sm-9">--%>
<%--                                <form:select path="type" id="type">--%>
<%--                                    <form:option value="" label="-- Chọn loại khách hàng --"/>--%>
<%--                                    <form:option value="merchant" label="Merchant"/>--%>
<%--                                    <form:option value="vendor" label="Vendor"/>--%>
<%--                                </form:select>--%>
<%--                            </div>--%>
<%--                        </div>--%>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="form-field-1">Tổng tiền</label>
                            <div class="col-sm-9">
                                <form:input path="totalCost" cssClass="col-xs-10 col-sm-5"/>
                            </div>
                        </div>
                        <form:hidden path="partnerId" id="partnerId"/>
                        <form:hidden path="id" id="invoiceId"/>
                        <div class="clearfix form-actions">
                            <div class="col-md-offset-3 col-md-9">
                                <c:if test="${not empty model.id}">
                                    <button class="btn btn-info" type="button" id="btnAddOrUpdateNew">
                                        <i class="ace-icon fa fa-check bigger-110"></i>
                                        Cập nhật hoá đơn
                                    </button>
                                </c:if>
                                <c:if test="${empty model.id}">
                                    <button class="btn btn-info" type="button" id="btnAddOrUpdateNew">
                                        <i class="ace-icon fa fa-check bigger-110"></i>
                                        Thêm mới hoá đơn
                                    </button>
                                </c:if>

                                &nbsp; &nbsp; &nbsp;
                                <button class="btn" type="reset">
                                    <i class="ace-icon fa fa-undo bigger-110"></i>
                                    Hủy
                                </button>
                            </div>
                        </div>
                    </form:form>
                </div>
            </div>
        </div>
    </div>
</div>

<script>
    $('#btnAddOrUpdateNew').click(function (e) {
        e.preventDefault();
        let data = {};
        let formData = $('#formSubmit').serializeArray();
        $.each(formData, function (i, v) {
            data[""+v.name+""] = v.value;
        });
        let id = $('#invoiceId').val();
        let partnerId = $('#partnerId').val();
        if (id == "") {
            addInvoice(data, partnerId);
        } else {
            updateInvoice(data, partnerId);
        }
    });

    function addInvoice(data, partnerId) {
        $.ajax({
            url: '${editInvoiceURL}',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(data),
            dataType: 'json',
            success: function (result) {
                window.location.href = "${editInvoiceURL}?id="+result.id+"&message=insert_success&partnerId="+partnerId;
            },
            error: function (error) {
                window.location.href = "${invoiceURL}?page=1&limit=5&message=error_system&partnerId=" +partnerId;
            }
        });
    }

    function updateInvoice(data, partnerId) {
        $.ajax({
            url: '${editInvoiceURL}',
            type: 'PUT',
            contentType: 'application/json',
            data: JSON.stringify(data),
            dataType: 'json',
            success: function (result) {
                window.location.href = "${editInvoiceURL}?id="+result.id+"&message=update_success&partnerId" +partnerId;
            },
            error: function (error) {
                window.location.href = "${editInvoiceURL}?id="+error.id+"&message=error_system&partnerId=" +partnerId;
            }
        });
    }
</script>
</body>
</html>