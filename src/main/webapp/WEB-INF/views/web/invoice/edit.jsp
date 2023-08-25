<%@ page import="java.util.List" %>
<%@ page import="com.laptrinhjavaweb.util.SecurityUtils" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/common/taglib.jsp"%>
<c:url var="invoiceURL" value="/hoa-don/danh-sach"/>
<c:url var="editInvoiceURL" value="/hoa-don/chinh-sua"/>
<c:url var="searchFlower" value="/tim-kiem-hoa"/>

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

                        <br/>
                        <br/>
                        <select class="js-example-basic" id="flowerSelect" style="width: 300px;">
                        </select>

                        <button type="button" id="addFlowerButton" class="btn btn-white btn-bold">Thêm hoa</button>
                        <div class="form-group">
                            <span id="error" class="text-danger"></span>
                        </div>
                        <div class="form-group">
                            <span id="errorQty" class="text-danger"></span>
                        </div>
                        <div class="form-group">
                            <span id="errorFlowersNull" class="text-danger"></span>
                        </div>
                        <br/>
                        <br/>
                        <div class="table-responsive">
                            <table class="table table-bordered">
                                <thead>
                                <tr>
                                    <th>Tên hoa</th>
                                    <th>Giá mua</th>
                                    <th>Số lượng tồn kho</th>
                                    <th>Số lượng mua</th>
                                    <th>Tổng tiền</th>
                                    <th></th>
                                </tr>
                                </thead>
                                <tbody id="flowerTableBody">
                                <c:forEach var="item" items="${detailModel.getListResult()}">
                                    <tr id="${item.getFlower().id}">
                                        <td>${item.getFlower().name}</td>
                                        <td>${item.getFlower().price}</td>
                                        <td>${item.getFlower().quantity}</td>
                                        <td>
                                            <input class="quantity-input"
                                                   data-flower-code="${item.getFlower().code}"
                                                   value="${item.quantity}"/>
                                        </td>
                                        <td>${item.totalPrice}</td>
                                        <td>
                                            <button class="removeFlowerButton"
                                                    data-flower-id="${item.getFlower().id}">Xóa
                                            </button>
                                        </td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right">Tổng tiền:</label>
                            <div class="col-sm-6">
                                <div class="input-group">
                                    <span class="input-group-addon">$</span>
                                    <form:input path="totalCost" id="totalCost" cssClass="form-control" readonly="true" />
                                </div>
                            </div>
                        </div>
                        <form:hidden path="partnerId" id="partnerId"/>
                        <form:hidden path="id" id="invoiceId"/>
                        <div class="clearfix form-actions">
                            <div class="col-md-offset-3 col-md-9">
                                <c:if test="${not empty model.id}">
                                    <button class="btn btn-info" type="button" id="btnAddOrUpdateFlower">
                                        <i class="ace-icon fa fa-check bigger-110"></i>
                                        Cập nhật hoá đơn
                                    </button>
                                </c:if>
                                <c:if test="${empty model.id}">
                                    <button class="btn btn-info" type="button" id="btnAddOrUpdateFlower">
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

    let flowers = [];
    $(document).ready(function (){
        $('.js-example-basic').select2();

        $('#flowerSelect').select2({
            placeholder: 'Tìm kiếm hoa...',
            allowClear: true,
            minimumInputLength: 1,
            ajax: {
                url: '${searchFlower}',
                dataType: 'json',
                delay: 250,
                data: function (params) {
                    let query = {
                        keyword: params.term
                    }
                    return query;
                },
                processResults: function (data) {
                    let options = data.map(function (item) {
                        return {
                            text: item.name,
                            id: item.id,
                            code: item.code,
                            price: item.price,
                            quantity: item.quantity
                        };
                    });
                    return {
                        results: options
                    };
                },
                cache: true
            }
        });

        let totalCost = ${model.totalCost}

        $('#flowerTableBody').on('input', '.quantity-input', function () {
            let input = $(this);
            let oldQuantity = 0;
            let newQuantity = parseInt(input.val());
            let code = input.data('flower-code').toString();
            flowers.forEach(function (flower) {
                if (flower.code === code){
                    oldQuantity = flower.quantity;
                    if(!$.isNumeric(newQuantity) || newQuantity <= 0 || input.val().trim() === ''){
                        input.css('border', '1px solid red');

                        flower.quantity = 0;
                        flower.totalPrice = 0;
                        flower.flowerQty += oldQuantity
                    }else{
                        if((flower.quantity + flower.flowerQty) < newQuantity){
                            input.css('border', '1px solid red');
                            $("#errorQty").text("Số lượng tồn kho không đủ");
                            flower.quantity = 0;
                            flower.totalPrice = 0;
                            flower.flowerQty += oldQuantity
                        }else{
                            $("#errorQty").text("");
                            input.css('border', '1px solid black');
                            flower.quantity = newQuantity;
                            flower.totalPrice = newQuantity * flower.price;
                            flower.flowerQty -= (newQuantity - oldQuantity);
                            totalCost += flower.totalPrice
                        }
                    }

                    let flowerQtyCell = $('<td></td>');
                    flowerQtyCell.text(flower.flowerQty);
                    $('#' + flower.id + ' td:nth-child(3)').replaceWith(flowerQtyCell);

                    let totalPriceCell = $('<td></td>');
                    totalPriceCell.text(flower.totalPrice);
                    $('#' + flower.id + ' td:nth-child(5)').replaceWith(totalPriceCell);

                    return false;
                }
            })

            $('#totalCost').val(totalCost)
        })


        // Lấy dữ liệu các loại hoa đã được thêm vào trong hoá đơn
        $('#flowerTableBody tr').each(function () {
            let id = $(this).attr("id");
            id = parseInt(id);
            let name = $(this).find('td:eq(0)').text();
            let price = $(this).find('td:eq(1)').text();
            let flowerQty = $(this).find('td:eq(2)').text();
            flowerQty = parseInt(flowerQty);

            let quantity = $(this).find('.quantity-input').val();
            quantity = parseInt(quantity);

            let code = $(this).find('.quantity-input').data("flower-code");
            code = code + ""

            let totalPrice = $(this).find('td:eq(4)').text();
            totalPrice = parseInt(totalPrice);

            addFlowerToArray(flowers, id, name, code, flowerQty, price, quantity, totalPrice);
        });


        // Xử lý khi nhấn nút thêm hoa vào bảng
        $('#addFlowerButton').click(function () {
            let selectedOption = $('#flowerSelect').select2('data')[0];
            let id = selectedOption.id;
            let code = selectedOption.code;
            let name = selectedOption.text;
            let price = selectedOption.price;
            let flowerQty = selectedOption.quantity;
            let quantity = 0;
            let totalPrice = 0;

            $('#quantityError').text('');
            const existingFlower = flowers.find(function (flower) {
                return flower.code === code;
            });

            if (existingFlower) {
                $("#error").text("Loại hoa này đã tồn tại trong hoá đơn!");
            } else {
                $("#error").text("");
                addFlowerToInvoice(flowers, id, name, code, flowerQty, price, quantity, totalPrice);
                addFlowerToArray(flowers, id, name, code, flowerQty, price, quantity, totalPrice);
            }
        });

        // Xử lý khi nhấn nút xoá một loại hoa ra khỏi bảng
        $('#flowerTableBody').on('click', '.removeFlowerButton', function (e) {
            e.preventDefault();
            let id = $(this).data('flower-id');
            removeFlowerFromInvoice(flowers, id);
        });


    })


    function addFlowerToArray(flowers, id, name, code, flowerQty, price, quantity, totalPrice) {
        flowers.push({
            id: id,
            name: name,
            code: code,
            flowerQty: flowerQty,
            quantity: quantity,
            price: price,
            totalPrice: totalPrice
        });
    }

    function addFlowerToInvoice(flowers, id, name, code, flowerQty, price, quantity, totalPrice) {
        let flowerRow =
            '<tr id="' + id + '">' +
            '<td>' + name + '</td>' +
            '<td>' + price + '</td>' +
            '<td>' + flowerQty + '</td>' +
            '<td><input class="quantity-input" data-flower-code="' + code + '" value="' + quantity + '"/></td>' +
            '<td>' + totalPrice + '</td>' +
            '<td><button class="removeFlowerButton" data-flower-id="' + id + '" >Xóa</button></td>' +
            '</tr>';
        $('#flowerTableBody').append(flowerRow);
    }

    function removeFlowerFromInvoice(flowers, id) {
        flowers.forEach(function (flower, index) {
            if (parseInt(flower.id) === id) {
                flowers.splice(index, 1);
                return false;
            }
        });

        $('#' + id).remove();
    }

    $('#btnAddOrUpdateFlower').click(function (e) {
        e.preventDefault();
        let data = {};
        let formData = $('#formSubmit').serializeArray();
        $.each(formData, function (i, v) {
            data[""+v.name+""] = v.value;
        });


        let mappedFlowers = flowers.map(item => {
            return {
                flowerId: item.id,
                quantity: item.quantity,
                totalPrice: item.totalPrice
            };
        });

        data.invoiceDetails = mappedFlowers
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
                window.location.href = "${editInvoiceURL}?id="+result.id+"&message=update_success&partnerId=" +partnerId;
            },
            error: function (error) {
                console.log(error)
                <%--window.location.href = "${editInvoiceURL}?id="+error.id+"&message=error_system&partnerId=" +partnerId;--%>
            }
        });
    }
</script>
</body>
</html>