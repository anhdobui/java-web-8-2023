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
<c:url var="transactionApi" value="/api/transaction" />
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

            <div class="row">
                <div class="col-xs-12">
                    <div class="alert alert-block text-left hidden" id="toast-message">
                        <button type="button" class="close" data-dismiss="alert">
                            <i class="ace-icon fa fa-times"></i>
                        </button>
                        <span id="text-message"></span>
                    </div>
                </div>
            </div>

            <c:if test="${mode.equals('update') && customerEdit.id != null}">
                <c:forEach var="type" items="${typeTransaction}">
                    <div class="row">
                        <div class="page-header">
                            <h1>
                                    ${type.name}
                                <button class="btn btn-white btn-info btn-bold" onclick="handleAddTransaction('${type.code}','${customerEdit.id}')" ><i class="fa fa-plus-circle" aria-hidden="true"></i></button>

                            </h1>
                        </div>
                        <div class="row">
                            <div class="col-xs-12">
                                <table class="table table-bordered" >
                                    <thead >
                                    <tr class="row">
                                        <th class="col-xs-3">Ngày tạo</th>
                                        <th class="col-xs-9">Ghi chú</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach var="transaction" items="${type.transactions}">
                                        <tr class="row">
                                            <td class="col-xs-3">${transaction.createdDate}</td>
                                            <td class="col-xs-9">${transaction.note}</td>
                                        </tr>
                                    </c:forEach>
                                    <tr class="row">
                                        <td class="col-xs-3"></td>
                                        <td class="col-xs-9 "><input id="newTransaction_${type.code}" type="text" class="form-control"></td>
                                    </tr>
                                    </tbody>
                                </table>
                            </div>

                        </div>
                    </div>
                </c:forEach>
            </c:if>



        </div>
        <!-- /.page-content -->
    </div>
</div>

<script>
    function showToast(message,status){
        var toastElm = $("#toast-message").removeClass("hidden")
        toastElm.addClass("alert-"+status)
        $("#text-message").text(message)
    }
    function loadMessageinLocalSt(name){
        var message = localStorage.getItem(name);
        var satus = name == "messageSuccess" ?  "success":"danger"
        message && showToast(message,satus)
        message && localStorage.removeItem(name)
    }
    loadMessageinLocalSt("messageSuccess")
    loadMessageinLocalSt("messageDanger")
</script>
<c:if test="${mode.equals('update') && customerEdit.id != null}">
    <script>

        function handleAddTransaction(codeTransaction,customerid){
            var note = $("#newTransaction_"+codeTransaction).val()
            if(note.trim()){
                var data = {}
                data['code'] = codeTransaction
                data['note'] = note
                data['customerid'] = Number(customerid)

                $.ajax({
                    type:'POST',
                    url:'${transactionApi}',
                    data:JSON.stringify(data),
                    contentType: 'application/json',
                    success:function (response) {
                        localStorage.setItem("messageSuccess", "Thêm mới giao dịch thành công");
                        location.reload();
                    },
                    error:function (response) {
                        localStorage.setItem("messageDanger", "Đã có lỗi xảy ra");
                        location.reload();
                    }
                })
            }
        }

    </script>
</c:if>
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
