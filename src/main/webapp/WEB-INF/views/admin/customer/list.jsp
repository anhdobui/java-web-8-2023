<%--
  Created by IntelliJ IDEA.
  User: ACER
  Date: 11/14/2023
  Time: 7:48 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp"%>
<c:url var="customerListURL" value="/admin/customer-list" />
<c:url var="baseAPI" value="/api/customer" />
<c:url var="loadStaffAPI" value="/api/user" />
<html>
<head>
    <title>Danh sách khách hàng</title>
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
            <!-- /.ace-settings-container -->

            <div class="page-header">
                <h1>
                    Dashboard
                    <small>
                        <i class="ace-icon fa fa-angle-double-right"></i>
                        overview &amp; stats
                    </small>
                </h1>
            </div>
            <!-- /.page-header -->
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
            <div class="row">
                <div class="col-xs-12">
                    <div class="widget-box">
                        <div class="widget-header">
                            <h4 class="widget-title">Tìm kiếm</h4>
                            <div class="widget-toolbar">
                                <a href="#" data-action="collapse">
                                    <i class="ace-icon fa fa-chevron-up"></i>
                                </a>
                            </div>
                        </div>
                        <div class="widget-body">
                            <div class="widget-main">
                                <form:form modelAttribute ="modelSearch" action="${customerListURL}" id="formSearchCustomer" method="GET">
                                    <input id="page" type="hidden" name="page" value="${page}"/>
                                    <input id="pageSize" type="hidden" name="pageSize" value="${pageSize}"/>
                                    <div class="row">
                                        <div class="col-sm-4">
                                            <label for="fullName">Tên khách hàng</label>
                                                <%--<input type="text" id="name" placeholder="" name="name" value="${modelSearch.name}" class="form-control" />--%>
                                            <form:input path="fullName" cssClass="form-control" />
                                        </div>
                                        <div class="col-sm-4">
                                            <label for="phone">Di động</label>
                                            <form:input path="phone" type="text" cssClass="form-control" />
                                        </div>
                                        <div class="col-sm-4">
                                            <label for="email">Email</label>
                                            <form:input path="email" type="email" cssClass="form-control" />
                                        </div>
                                    </div>
                                    <security:authorize access="hasRole('MANAGER')">
                                        <div class="row">
                                            <div class="col-sm-3">
                                                <label for="staffId">Chọn nhân viên phụ trách</label>
                                                <form:select path="staffId" cssClass="form-control" >
                                                    <form:option value="-1" label="---Chọn nhân viên---" />
                                                    <form:options items="${staffmaps}" />
                                                </form:select>
                                            </div>
                                        </div>
                                    </security:authorize>

                                    <br/>
                                    <div class="row">
                                        <div class="col-sm-12">
                                            <button type="button" class="btn btn-primary" id="btnSearch">Tìm kiếm</button>
                                        </div>
                                    </div>
                                </form:form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- /.row -->
            <div class="row">
                <div class="col-xs-12">
                    <div class="pull-right">
                        <a href="/admin/customer-edit" class="btn btn-white btn-info btn-bold" data-toggle="tooltip" title="Thêm khách hàng">
                            <i class="fa fa-plus-circle"></i>
                        </a>
                        <button
                                class="btn btn-white btn-warning btn-bold"
                                data-toggle="tooltip"
                                title="Xóa khách hàng"
                                id="btnDeleteCustomer"
                        >
                            <i class="fa fa-trash" aria-hidden="true"></i>
                        </button>
                    </div>
                </div>
            </div>
            <br />
            <!-- /.row -->
            <div class="row">
                <div class="col-xs-12">
                    <display:table name="customers" id="customerList" class="table table-striped table-bordered table-hover">
                        <display:column
                                title="<fieldset ><input type='checkbox' id='cb_customerid_all' /></fieldset>">
                            <fieldset>
                                <input type="checkbox" value="${customerList.id}" class="cb-customer-js-handle" id="cb_customerid_${customerList.id}" />
                            </fieldset>
                        </display:column>
                        <display:column headerClass="text-left" property="fullName" title="Tên khách hàng"/>
                        <display:column headerClass="text-left" property="staffName" title="Nhân viên quản lý"/>
                        <display:column headerClass="text-left" property="phone" title="Số điện thoại"/>
                        <display:column headerClass="text-left" property="email" title="Email"/>
                        <display:column headerClass="text-left" property="desire" title="Nhu cầu"/>
                        <display:column headerClass="text-left" property="modifiedBy" title="Người nhập"/>
                        <display:column headerClass="text-left" property="modifiedDate" title="Ngày nhập"/>
                        <display:column headerClass="text-left" property="status" title="Tình trạng"/>
                        <display:column headerClass="text-left"  title="Thao tác" >
                            <security:authorize access="hasRole('MANAGER')">
                                <button
                                        class="btn btn-xs btn-info"
                                        data-toggle="tooltip"
                                        title="Giao tòa nhà"
                                        onclick="assignmentCustomer(${customerList.id})"
                                >
                                    <i class="fa fa-bars" aria-hidden="true"></i>
                                </button>
                            </security:authorize>
                            <a
                                    class="btn btn-xs btn-info"
                                    data-toggle="tooltip"
                                    title="Chỉnh sửa tòa nhà"
                                    href="/admin/customer-edit?id=${customerList.id}"
                            >
                                <i class="fa fa-pencil-square-o" aria-hidden="true"></i>
                            </a>
                            <button
                                    class="btn btn-xs ${customerList.status.equals('Đang xử lý') ? 'btn-info':'btn-danger'}"
                                    data-toggle="tooltip"
                                    title="Cập nhật tình trạng"
                                    onclick="deleteSoftCustomer(${customerList.id},${customerList.status.equals('Đang xử lý') ? true:false})"
                            >
                                <c:choose>
                                    <c:when test="${customerList.status.equals('Đang xử lý')}">
                                        <i class="fa fa-eye" aria-hidden="true"></i>
                                    </c:when>
                                    <c:otherwise>
                                        <i class="fa fa-eye-slash" aria-hidden="true"></i>
                                    </c:otherwise>
                                </c:choose>
                            </button>
                        </display:column>
                    </display:table>

                </div>
            </div>

            <br />
            <c:if test="${objects.size() == 0}">
                <span>No objectsfound!</span>
            </c:if>

            <div class="d-flex mt-5 justify-content-center">
                <nav aria-label="...">
                    <!--pagination-->
                    <ul class="pagination" id="pagination"></ul>
                </nav>
            </div>

        </div>
        <!-- /.page-content -->
    </div>
</div>
<div id="deleteModal" class="modal fade" role="dialog">
    <div class="modal-dialog">
        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Xác nhận xóa khách hàng</h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-warning">
                    Bạn có chắc chắn muốn xóa những khách hàng đã chọn
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-yellow " id="btnComfirmRemoveCustomer" data-dismiss="modal">Xóa</button>
                <button type="button" class="btn btn-light" data-dismiss="modal">Hủy</button>
            </div>
        </div>
    </div>
</div>
<security:authorize access="hasRole('MANAGER')">
    <div id="assignmentCustomerModal" class="modal fade" role="dialog">
        <div class="modal-dialog">
            <!-- Modal content-->
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title">Danh sách nhân viên</h4>
                </div>
                <div class="modal-body">
                    <table class="table table-bordered" id="staffList">
                        <thead>
                        <tr>
                            <th>Chọn nhân viên</th>
                            <th>Tên nhân viên</th>
                        </tr>
                        </thead>
                        <tbody>

                        </tbody>
                    </table>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" id="btn_send_assignmentCustomer" data-dismiss="modal">Gửi</button>
                    <button type="button" class="btn btn-default" data-dismiss="modal">Đóng</button>
                </div>
            </div>
        </div>
    </div>
</security:authorize>

<script src="https://cdnjs.cloudflare.com/ajax/libs/twbs-pagination/1.4.2/jquery.twbsPagination.min.js"
        integrity="sha512-frFP3ZxLshB4CErXkPVEXnd5ingvYYtYhE5qllGdZmcOlRKNEPbufyupfdSTNmoF5ICaQNO6SenXzOZvoGkiIA=="
        crossorigin="anonymous" referrerpolicy="no-referrer">
</script>
<script>
    function deleteSoftCustomer(customerid,status){
        $.ajax({
            type:'PUT',
            url:'${baseAPI}/'+customerid,
            data:JSON.stringify(status),
            contentType: 'application/json',
            success:function (response) {
                localStorage.setItem("messageSuccess", "Cập nhật trạng thái của khách hàng thành công");
                location.reload();
            },
            error:function (response) {
                 localStorage.setItem("messageDanger", "Đã có lỗi xảy ra");
                location.reload();
            }
        })
    }
</script>
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
<script>
    function openModalAssignmentCustomer() {
        $("#assignmentCustomerModal").modal();
    }
    function assignmentCustomer(customerid){
        openModalAssignmentCustomer();
        loadStaff(customerid);
    }

    function loadStaff(buildingid){
        $.ajax({
            type:'GET',
            url:'${loadStaffAPI}?customerid='+buildingid,
            dataType:"json",
            success:function (response) {
                var row = '';
                $.each(response.data,function(index,item){
                    row += '<tr>';
                    row += '<td class="text-center"><input type="checkbox" value="'+item.staffId+'" id="staffCheckbox_'+item.staffId+'" class="check-box-element"'+item.checked+' /></td>'
                    row += '<td class="text-center">'+item.fullName+'</td>'
                    row += '</tr>';
                })
                $('#staffList tbody').html(row);
                $('#staffList').attr("data",buildingid)

            },
            error:function (response) {
                showToast("Đã có lỗi xảy ra","danger")
                console.log(response)
            }
        })
    }
    function getStaffChecked(){
        var listStaffChecked = $("#assignmentCustomerModal tbody .check-box-element:checked")
        var staffIds = []
        listStaffChecked.each(function() {
            var value = $(this).val();
            staffIds.push(value)
        });
        return staffIds
    }
    $("#btn_send_assignmentCustomer").click(function (){
        var customerid = $('#staffList').attr("data")
        var staffIds = getStaffChecked()
        sendApiAssignmentCustomer(customerid,staffIds)
    })

    function sendApiAssignmentCustomer(customerid,staffIds) {
        $.ajax({
            type:'POST',
            url:'${baseAPI}/'+customerid+'/assignment',
            data:JSON.stringify(staffIds),
            contentType: 'application/json',
            success:function (response) {

                localStorage.setItem("messageSuccess", "Giao khách hàng cho nhân viên quản lý thành công");
                location.reload();
            },
            error:function (response) {
                localStorage.setItem("messageDanger", "Đã có lỗi xảy ra");
                location.reload();
            }
        })
    }


</script>
<script >
    $('#btnSearch').click(function (e){

        e.preventDefault()
        $('#page').val(1);
        $('#formSearchCustomer').submit()
    })
    var checkAll = $("#cb_customerid_all")
    var listCheckBuilding = $(".cb-customer-js-handle")
    checkAll.change(function (){
        if(checkAll.is(":checked")){
            listCheckBuilding.prop("checked", true);
        }else{
            listCheckBuilding.prop("checked", false);
        }
    })
    function removeCustomer(ids){
        $.ajax({
            type:'DELETE',
            url:'${baseAPI}',
            data:JSON.stringify(ids),
            contentType: 'application/json',
            success:function (response) {

                localStorage.setItem("messageSuccess", "Đã xóa thành công "+ response +" khách hàng");
                location.reload();
            },
            error:function (response) {
                localStorage.setItem("messageDanger", "Đã có lỗi xảy ra");
                location.reload();
            }
        })
    }
    $("#btnDeleteCustomer").click(function (e){
        var listChecked = $(".cb-customer-js-handle:checked")
        if(listChecked.size()){
            $("#deleteModal").modal()
            var ids_remove = []
            listChecked.each(function() {
                var value = $(this).val();
                ids_remove.push(value)
            });

            $("#btnComfirmRemoveCustomer").click(function (e){
                removeCustomer(ids_remove)
            })

        }
    })
$(function () {
    var currentPage = ${currentPage}
    var totalPages = ${totalPages}
    if(currentPage > totalPages){
        totalPages = currentPage
    }
    window.pagObj = $('#pagination').twbsPagination({
        totalPages: totalPages ,
        visiblePages: totalPages+1,
        startPage: currentPage,
        onPageClick: function (event, page) {
            if (currentPage != page) {
                $('#page').val(page);
                var form = $("#formSearchCustomer")
                var formData = form.serialize();
                var urlSearchParams = new URLSearchParams(formData);
                var queryString = urlSearchParams.toString();
                var action = form.attr('action')
                form.attr('action', action + "?" + queryString);
                form.submit();
            }
        },
        // Text labels
        first: 'Trang đầu',
        prev: 'Trang trước',
        next: 'Tiếp theo',
        last: 'Trang cuối',
    });
});
</script>

</body>

</html>
