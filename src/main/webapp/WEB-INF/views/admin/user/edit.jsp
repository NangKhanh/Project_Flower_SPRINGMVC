<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp" %>
<c:url var="userURL" value="/quan-tri/nguoi-dung/danh-sach"/>
<c:url var="editUserURL" value="/quan-tri/nguoi-dung/chinh-sua"/>
<c:url var="newAPI" value="/api/new"/>

<html>
<head>
    <c:if test="${empty model.id}">
        <title>Thêm người dùng vào danh sách</title>
    </c:if>
    <c:if test="${not empty model.id}">
        <title>Chỉnh sửa thông tin người dùng</title>
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

                    <form:form class="form-horizontal" role="form" method="post" action="${editUserURL}" modelAttribute="model">
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="form-field-1">Tên người
                                dùng</label>
                            <div class="col-sm-9">
                                    <%--<input type="text" name="fullName" value="${model.fullName}">--%>
                                <form:input path="fullName"></form:input>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="form-field-1">Nhóm người
                                dùng</label>
                            <div class="col-sm-9">
                                <form:select path="groupId">
                                    <c:forEach var="item" items="${group.listResult}">
                                        <c:choose>
                                            <c:when test="${item.groupName == model.groupName}">
                                                <form:option value="${item.id}"
                                                             selected="true">${item.groupName}</form:option>
                                            </c:when>
                                            <c:otherwise>
                                                <form:option value="${item.id}">${item.groupName}</form:option>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:forEach>
                                </form:select>
                            </div>
                        </div>
                        <c:if test="${empty model.id}">
                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right" for="form-field-1">Tài
                                    khoản</label>
                                <div class="col-sm-9">
                                        <%--<input type="text" name="fullName" value="${model.fullName}">--%>
                                    <form:input path="userName"></form:input>
                                </div>
                            </div>
                            <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="form-field-1">Mật khẩu</label>
                            <div class="col-sm-9">
                                    <%--<input type="text" name="fullName" value="${model.fullName}">--%>
                                <form:input path="password"></form:input>
                            </div>
                        </c:if>
                        </div>
                        <form:hidden path="id"/>
                        <div class="clearfix form-actions">
                            <div class="col-md-offset-3 col-md-9" style="margin-top: 20px">
                                <c:if test="${not empty model.id}">
                                    <button class="btn btn-info" type="submit"
                                    <i class="ace-icon fa fa-check bigger-110"></i>
                                    Cập nhật thông tin
                                    </button>
                                </c:if>
                                <c:if test="${empty model.id}">
                                    <button class="btn btn-info" type="submit"
                                    <i class="ace-icon fa fa-check bigger-110"></i>
                                    Thêm người đùng
                                    </button>
                                </c:if>
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
</script>
</body>
</html>