<%--
  Created by IntelliJ IDEA.
  User: ACER
  Date: 10/13/2023
  Time: 4:34 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp"%>
<c:url var="buildingListURL" value="/admin/building-list" />
<c:url var="loadStaffAPI" value="/api/building" />
<c:url var="baseAPI" value="/api/building" />
<html>
<head>

    <title>Danh sách tòa nhà</title>
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
                                <form:form modelAttribute ="modelSearch" action="${buildingListURL}" id="listForm" method="GET">
                                    <input id="page" type="hidden" name="page" value="${page}"/>
                                    <input id="pageSize" type="hidden" name="pageSize" value="${pageSize}"/>
                                    <div class="row">
                                        <div class="col-sm-6">
                                            <label for="name">Tên tòa nhà</label>
                                            <%--<input type="text" id="name" placeholder="" name="name" value="${modelSearch.name}" class="form-control" />--%>
                                            <form:input path="name" cssClass="form-control" />
                                        </div>
                                        <div class="col-sm-6">
                                            <label for="floorArea">Diện tích sàn</label>
                                            <form:input path="floorArea" type="number" cssClass="form-control" />
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-sm-4">
                                            <label for="districtCode">Quận hiện có</label>
                                            <form:select path="districtCode" cssClass="form-control" >
                                                <form:option value="-1" label="---Chọn quận---" />
                                                <form:options items="${districtmaps}" />
                                            </form:select>
                                        </div>
                                        <div class="col-sm-4">
                                            <label for="ward">Phường</label>
                                            <form:input path="ward" cssClass="form-control" />
                                        </div>
                                        <div class="col-sm-4">
                                            <label for="street">Đường</label>
                                            <form:input path="street" cssClass="form-control" />
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-sm-4">
                                            <label for="numberOfBasement">Số tầng hầm</label>
                                            <form:input type="number" path="numberOfBasement" cssClass="form-control" />
                                        </div>
                                        <div class="col-sm-4">
                                            <label for="direction">Hướng</label>
                                            <form:input path="direction" cssClass="form-control" />
                                        </div>
                                        <div class="col-sm-4">
                                            <label for="level">Hạng</label>
                                            <form:input path="level" cssClass="form-control" />
                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="col-sm-3">
                                            <div>
                                                <label for="rentAreaFrom">Diện tích từ</label>
                                                <form:input path="rentAreaFrom" type="number" cssClass="form-control" />
                                            </div>
                                        </div>
                                        <div class="col-sm-3">
                                            <div>
                                                <label for="rentAreaTo">Diện tích đến</label>
                                                <form:input path="rentAreaTo" type="number" cssClass="form-control" />
                                            </div>
                                        </div>
                                        <div class="col-sm-3">
                                            <div>
                                                <label for="costRentFrom">Giá thuê từ</label>
                                                <form:input path="costRentFrom" type="number" cssClass="form-control" />

                                            </div>
                                        </div>
                                        <div class="col-sm-3">
                                            <div>
                                                <label for="costRentTo">Giá thuê đến</label>
                                                <form:input path="costRentTo" type="number" cssClass="form-control" />
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-sm-4">
                                            <label for="managerName">Tên quản lý</label>
                                            <form:input path="managerName" cssClass="form-control" />
                                        </div>
                                        <div class="col-sm-4">
                                            <label for="managerPhone">Điện thoại quản lý</label>
                                            <form:input path="managerPhone" cssClass="form-control" />
                                        </div>
                                        <security:authorize access="hasRole('MANAGER')">
                                            <div class="col-sm-3">
                                                <label for="staffId">Chọn nhân viên phụ trách</label>
                                                <form:select path="staffId" cssClass="form-control" >
                                                    <form:option value="-1" label="---Chọn nhân viên---" />
                                                    <form:options items="${staffmaps}" />
                                                </form:select>
                                            </div>
                                        </security:authorize>
                                    </div>
                                    <div class="row">
                                        <div></div>
                                        <div class="col-sm-11">
                                            <div class="checkbox">
                                                <c:forEach var="type" items="${typemaps}">
                                                    <label for="${type.key}">
                                                        <form:checkbox id="${type.key}" path="types" value="${type.key}" />
                                                        <span class="lbl">${type.value}</span>
                                                    </label>
                                                </c:forEach>
                                            </div>
                                            </div>

                                    </div>
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
                        <a href="/admin/building-edit" class="btn btn-white btn-info btn-bold" data-toggle="tooltip" title="Thêm tòa nhà">
                            <i class="fa fa-plus-circle"></i>
                        </a>
                        <button
                                class="btn btn-white btn-warning btn-bold"
                                data-toggle="tooltip"
                                title="Xóa tòa nhà"
                                id="btnDeleteBuilding"
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
                   <display:table name="buildings" id="buildingList" class="table table-striped table-bordered table-hover">
                       <display:column
                       title="<fieldset ><input type='checkbox' id='cb_buildingid_all' /></fieldset>">
                           <fieldset>
                               <input type="checkbox" value="${buildingList.id}" class="cb-building-js-handle" id="cb_buildingid_${buildingList.id}" />
                           </fieldset>
                       </display:column>
                       <display:column headerClass="text-left" property="name" title="Tên tòa nhà"/>
                       <display:column headerClass="text-left" property="address" title="Địa chỉ"/>
                       <display:column headerClass="text-left" property="managerName" title="Tên quản lý"/>
                       <display:column headerClass="text-left" property="numberOfBasement" title="Số tầng hầm"/>
                       <display:column headerClass="text-left" property="floorArea" title="DT.sàn"/>
                       <display:column headerClass="text-left" property="rentArea" title="DT.thuê"/>
                       <display:column headerClass="text-left" property="rentPrice" title="Giá thuê"/>
                       <display:column headerClass="text-left" property="serviceFee" title="Phí dịch vụ"/>
                       <display:column headerClass="text-left"  title="Thao tác" >
                           <security:authorize access="hasRole('MANAGER')">
                               <button
                                       class="btn btn-xs btn-info"
                                       data-toggle="tooltip"
                                       title="Giao tòa nhà"
                                       onclick="assignmentBuilding(${buildingList.id})"
                               >
                                   <i class="fa fa-bars" aria-hidden="true"></i>
                               </button>
                           </security:authorize>
                           <a
                                   class="btn btn-xs btn-info"
                                   data-toggle="tooltip"
                                   title="Chỉnh sửa tòa nhà"
                                   href="/admin/building-edit?id=${buildingList.id}"
                           >
                               <i class="fa fa-pencil-square-o" aria-hidden="true"></i>
                           </a>
                       </display:column>
                   </display:table>
                    <%--<table id="buildingList" class="table table-striped table-bordered table-hover">--%>
                    <%--<thead>--%>
                    <%----%>
                    <%--<tr>--%>
                        <%--<th><input type="checkbox" id="cb_buildingid_all" /></th>--%>
                        <%--<th>Tên tòa nhà</th>--%>
                        <%--<th>Địa chỉ</th>--%>
                        <%--<th>Tên quản lý</th>--%>
                        <%--<th>Số tầng hầm</th>--%>
                        <%--<th>D.T sàn</th>--%>
                        <%--<th>D.T thuê</th>--%>
                        <%--<th>Giá thuê</th>--%>
                        <%--<th>Phí dịch vụ</th>--%>
                        <%--<th>Thao tác</th>--%>
                    <%--</tr>--%>
                    <%--</thead>--%>
                    <%--<tbody>--%>
                    <%--<c:forEach var="item" items="${buildings}">--%>
                        <%--<tr>--%>
                            <%--<td><input type="checkbox" value="${item.id}" class="cb-building-js-handle" id="cb_buildingid_${item.id}" /></td>--%>
                            <%--<td>${item.name}</td>--%>
                            <%--<td>${item.address}</td>--%>
                            <%--<td>${item.managerName}</td>--%>
                            <%--<td>${item.numberOfBasement}</td>--%>
                            <%--<td>${item.floorArea}</td>--%>
                            <%--<td>${item.rentArea}</td>--%>
                            <%--<td>${item.rentPrice}</td>--%>
                            <%--<td>${item.serviceFee}</td>--%>
                            <%--<td>--%>
                                <%--<button--%>
                                        <%--class="btn btn-xs btn-info"--%>
                                        <%--data-toggle="tooltip"--%>
                                        <%--title="Giao tòa nhà"--%>
                                        <%--onclick="assignmentBuilding(${item.id})"--%>
                                <%-->--%>
                                    <%--<i class="fa fa-bars" aria-hidden="true"></i>--%>
                                <%--</button>--%>
                                <%--<a--%>
                                        <%--class="btn btn-xs btn-info"--%>
                                        <%--data-toggle="tooltip"--%>
                                        <%--title="Chỉnh sửa tòa nhà"--%>
                                        <%--href="/admin/building-edit?id=${item.id}"--%>
                                <%-->--%>
                                    <%--<i class="fa fa-pencil-square-o" aria-hidden="true"></i>--%>
                                <%--</a>--%>
                            <%--</td>--%>
                        <%--</tr>--%>
                    <%--</c:forEach>--%>

                    <%--</tbody>--%>
                <%--</table>--%>


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
<security:authorize access="hasRole('MANAGER')">
    <div id="assignmentBuildingModal" class="modal fade" role="dialog">
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
                    <button type="button" class="btn btn-default" id="btn_send_assignmentBuilding" data-dismiss="modal">Gửi</button>
                    <button type="button" class="btn btn-default" data-dismiss="modal">Đóng</button>
                </div>
            </div>
        </div>
    </div>
</security:authorize>

<div id="deleteModal" class="modal fade" role="dialog">
    <div class="modal-dialog">
        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Xác nhận xóa tòa nhà</h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-warning">
                    Bạn có chắc chắn muốn xóa những tòa nhà đã chọn
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-yellow " id="btnComfirmRemoveBuilding" data-dismiss="modal">Xóa</button>
                <button type="button" class="btn btn-light" data-dismiss="modal">Hủy</button>
            </div>
        </div>
    </div>
</div>
<%--https://www.jqueryscript.net/other/Simple-Customizable-Pagination-Plugin-with-jQuery-Bootstrap-Twbs-Pagination.html--%>
<script src="https://cdnjs.cloudflare.com/ajax/libs/twbs-pagination/1.4.2/jquery.twbsPagination.min.js"
        integrity="sha512-frFP3ZxLshB4CErXkPVEXnd5ingvYYtYhE5qllGdZmcOlRKNEPbufyupfdSTNmoF5ICaQNO6SenXzOZvoGkiIA=="
        crossorigin="anonymous" referrerpolicy="no-referrer"></script>
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
    function assignmentBuilding(buildingid) {
        openModalAssignmentBuilding();
        loadStaff(buildingid);

    }
    $("#btn_send_assignmentBuilding").click(function (){
        var buildingid = $('#staffList').attr("data")
        var staffIds = getStaffChecked()
        sendApiAssignmentBuilding(buildingid,staffIds)
    })

    function getStaffChecked(){
        var listStaffChecked = $("#assignmentBuildingModal tbody .check-box-element:checked")
            var staffIds = []
            listStaffChecked.each(function() {
                var value = $(this).val();
                staffIds.push(value)
            });
        return staffIds
    }
    function sendApiAssignmentBuilding(buildingid,staffIds) {
        $.ajax({
            type:'POST',
            url:'${baseAPI}/'+buildingid+'/assignment',
            data:JSON.stringify(staffIds),
            contentType: 'application/json',
            success:function (response) {
                showToast("Giao tòa nhà cho nhân viên quản lý thành công","success")
            },
            error:function (response) {
                showToast("Đã có lỗi xảy ra","danger")
                console.log(response)
            }
        })
    }
    function loadStaff(buildingid){
        $.ajax({
            type:'GET',
            url:'${loadStaffAPI}/'+buildingid+'/staffs',
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
    function removeBuilding(ids){
        $.ajax({
            type:'DELETE',
            url:'${baseAPI}',
            data:JSON.stringify(ids),
            contentType: 'application/json',
            success:function (response) {
                localStorage.setItem("messageSuccess", "Đã xóa thành công "+ids.length+" tòa nhà");
                location.reload();
            },
            error:function (response) {
                localStorage.setItem("messageDanger", "Đã có lỗi xảy ra");
                location.reload();
            }
        })
    }
    function openModalAssignmentBuilding(buildingid) {
        $("#assignmentBuildingModal").modal();
    }

    $('#btnSearch').click(function (e){
        e.preventDefault()
        $('#page').val(1);
        $('#listForm').submit()
    })
    var checkAll = $("#cb_buildingid_all")
    var listCheckBuilding = $(".cb-building-js-handle")
    checkAll.change(function (){

        if(checkAll.is(":checked")){
            listCheckBuilding.prop("checked", true);
        }else{
            listCheckBuilding.prop("checked", false);
        }
    })
    $("#btnDeleteBuilding").click(function (e){
        var listChecked = $(".cb-building-js-handle:checked")

            if(listChecked.size()){
                $("#deleteModal").modal()
                var ids_remove = []
                listChecked.each(function() {
                    var value = $(this).val();
                    ids_remove.push(value)
                });
                $("#btnComfirmRemoveBuilding").click(function (e){
                     removeBuilding(ids_remove)
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
                    var form = $("#listForm")
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
