<%--
  Created by IntelliJ IDEA.
  User: ACER
  Date: 11/14/2023
  Time: 10:03 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp"%>
<c:url var="customerApi" value="/api/customer" />
<html>
<head>
    <title>${mode.equals('update') ? "Cập nhật":"Thêm mới"} khách hàng</title>
</head>
<body>
<div class="main-content">
    <div class="main-content-inner">
        <div class="breadcrumbs" id="breadcrumbs">
            <script type="text/javascript">
                try {
                    ace.settings.check("breadcrumbs", "fixed");
                } catch (e) {}
            </script>

            <ul class="breadcrumb">
                <li>
                    <i class="ace-icon fa fa-home home-icon"></i>
                    <a href="#">Home</a>
                </li>
                <li class="active">Dashboard</li>
            </ul>
            <!-- /.breadcrumb -->

            <!-- /.nav-search -->
        </div>

        <div class="page-content">


            <div class="row">
                <div class="col-xs-12">
                    <form:form modelAttribute ="customerEdit" cssClass="form-horizontal" id="formEdit" method="GET">
                        <div class="form-group">
                            <label for="fullName" class="col-sm-2 control-label no-padding-right">Tên khách hàng</label>
                            <div class="col-sm-9">
                                <form:input path="fullName" type="text" cssClass="form-control" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="phone" class="col-sm-2 control-label no-padding-right">Số điện thoại</label>
                            <div class="col-sm-9">
                                <form:input path="phone" type="text" cssClass="form-control" />

                            </div>
                        </div>
                        <div class="form-group">
                            <label for="email" class="col-sm-2 control-label no-padding-right">Email</label>
                            <div class="col-sm-9">
                                <form:input path="email" type="text" cssClass="form-control" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="company" class="col-sm-2 control-label no-padding-right">Tên công ty</label>
                            <div class="col-sm-9">
                                <form:input path="company" type="text" cssClass="form-control" />

                            </div>
                        </div>
                         <div class="form-group">
                            <label for="desire" class="col-sm-2 control-label no-padding-right">Nhu cầu</label>
                            <div class="col-sm-9">
                                <form:input path="desire" type="text" cssClass="form-control" />

                            </div>
                        </div>
                         <div class="form-group">
                            <label for="note" class="col-sm-2 control-label no-padding-right">Ghi chú</label>
                            <div class="col-sm-9">
                                <form:input path="note" type="text" cssClass="form-control" />

                            </div>
                        </div>



                        <div class="form-group">
                            <div class="col-sm-2"></div>
                            <div class="col-sm-9">
                                <c:choose>
                                    <c:when test="${mode.equals('update')}">
                                        <button type="button" class="btn btn-primary btn-bold" id="btnSendCustomer">Cập nhật khách hàng</button>
                                    </c:when>
                                    <c:otherwise>
                                        <button type="button" class="btn btn-primary btn-bold" id="btnSendCustomer">Thêm mới khách hàng</button>
                                    </c:otherwise>
                                </c:choose>

                                <button type="button" class="btn btn-warning btn-bold">Hủy</button>
                            </div>
                        </div>
                    </form:form>

                </div>
            </div>
        </div>
        <!-- /.page-content -->
    </div>
</div>
<script>
    $("#btnSendCustomer").click(function(e) {
        e.preventDefault()
        var data = {}
        var formData = $("#formEdit").serializeArray();
        $.each(formData,function (index,v) {
            data[""+v.name+""] = !data[""+v.name+""] ? v.value:data[""+v.name+""]+","+ v.value;
        })
        <c:if test="${mode.equals('update') && customerEdit.id != null}">
        if(${mode.equals("update")}){
            data.id = ${customerEdit.id}
        }
        </c:if>
        console.log(data)

        $.ajax({
            type: "${mode.equals("update") ? 'PUT':'POST' }",
            url:'${customerApi}',
            data:JSON.stringify(data),
            contentType:"application/json",
            success:function (response) {
                localStorage.setItem("messageSuccess", "${mode.equals('update') ? "Cập nhật":"Thêm mới"} khách hàng thành công ");
                window.location.href = "/admin/customer-list";
            },
            error:function (response) {
                localStorage.setItem("messageDanger", "Đã có lỗi xảy ra");
                window.location.href = "/admin/customer-list";
            }
        })
    })
</script>
</body>
</html>
