<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/common/taglib.jsp"%>
<c:url var="merchantURL" value="/quan-tri/merchant/danh-sach"/>
<c:url var="editMerchantURL" value="/quan-tri/merchant/chinh-sua"/>

<html>
<head>
    <c:if test="${empty model.id}">
        <title>Thêm mới merchant</title>
    </c:if>
    <c:if test="${not empty model.id}">
        <title>Chỉnh sửa merchant</title>
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
                <li><i class="ace-icon fa fa-home home-icon"></i> <a href="#">Home</a>
                </li>

                <li><a href="#">Forms</a></li>
                <li class="active">Form Elements</li>
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
                            <label class="col-sm-3 control-label no-padding-right" for="form-field-1">Họ và tên</label>
                            <div class="col-sm-9">
                                <form:input path="name" cssClass="col-xs-10 col-sm-5"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="form-field-1">Username</label>
                            <div class="col-sm-9">
                                <form:input path="username" cssClass="col-xs-10 col-sm-5"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="type" class="col-sm-3 control-label no-padding-right">Loại khách hàng:</label>
                            <div class="col-sm-9">
                                <form:select path="type" id="type">
                                    <form:option value="" label="-- Chọn loại khách hàng --"/>
                                    <form:option value="merchant" label="Merchant"/>
                                    <form:option value="vendor" label="Vendor"/>
                                </form:select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="form-field-1">Địa chỉ</label>
                            <div class="col-sm-9">
                                <form:input path="address" cssClass="col-xs-10 col-sm-5"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="form-field-1">Số điện thoại</label>
                            <div class="col-sm-9">
                                <form:input path="phone" cssClass="col-xs-10 col-sm-5"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="form-field-1">Chiết khấu</label>
                            <div class="col-sm-9">
                                <form:input path="discountRate" cssClass="col-xs-10 col-sm-5"/>
                            </div>
                        </div>
                        <form:hidden path="id" id="merchantId"/>
                        <div class="clearfix form-actions">
                            <div class="col-md-offset-3 col-md-9">
                                <c:if test="${not empty model.id}">
                                    <button class="btn btn-info" type="button" id="btnAddOrUpdateNew">
                                        <i class="ace-icon fa fa-check bigger-110"></i>
                                        Cập nhật merchant
                                    </button>
                                </c:if>
                                <c:if test="${empty model.id}">
                                    <button class="btn btn-info" type="button" id="btnAddOrUpdateNew">
                                        <i class="ace-icon fa fa-check bigger-110"></i>
                                        Thêm mới merchant
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
        let id = $('#merchantId').val();
        if (id == "") {
            addNew(data);
        } else {
            updateNew(data);
        }
    });

    function setURL(){
        let selectedType = $("#type").val();
        let url;
        if (selectedType === "merchant") {
            url = "${editMerchantURL}";
        }else{
            url = "${editVendorURL}";
        }
        return url;
    }


    function addNew(data) {
        $.ajax({
            url: setURL(),
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(data),
            dataType: 'json',
            success: function (result) {
                console.log(result)
                window.location.href = "${editMerchantURL}?id="+result.id+"&message=insert_success";
            },
            error: function (error) {
                window.location.href = "${merchantURL}?page=1&limit=2&message=error_system";
            }
        });
    }

    function updateNew(data) {
        $.ajax({
            url: setURL(),
            type: 'PUT',
            contentType: 'application/json',
            data: JSON.stringify(data),
            dataType: 'json',
            success: function (result) {
                window.location.href = "${editNewURL}?id="+result.id+"&message=update_success";
            },
            error: function (error) {
                window.location.href = "${editNewURL}?id="+error.id+"&message=error_system";
            }
        });
    }
</script>
</body>
</html>