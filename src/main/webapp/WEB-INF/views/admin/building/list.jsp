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
                                    <div class="row">
                                        <div class="col-sm-6">
                                            <label for="name">Tên tòa nhà</label>
                                            <%--<input type="text" id="name" placeholder="" name="name" value="${modelSearch.name}" class="form-control" />--%>
                                            <form:input path="name" cssClass="form-control" />
                                        </div>
                                        <div class="col-sm-6">
                                            <label for="area">Diện tích sàn</label>
                                            <input type="text" id="area" placeholder="" class="form-control" />
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-sm-4">
                                            <label for="district">Quận hiện có</label>
                                            <select class="form-control" id="district">
                                                <option value="">---Chọn quận---</option>
                                                <option value="Q1">Quận 1</option>
                                                <option value="Q2">Quận 2</option>
                                                <option value="Q3">Quận 3</option>
                                                <option value="Q4">Quận 4</option>
                                            </select>
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
                                            <input type="number" id="numberOfBasement" class="form-control" />
                                        </div>
                                        <div class="col-sm-4">
                                            <label for="direction">Hướng</label>
                                            <input type="number" id="direction" class="form-control" />
                                        </div>
                                        <div class="col-sm-4">
                                            <label for="level">Hạng</label>
                                            <input type="number" id="level" class="form-control" />
                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="col-sm-3">
                                            <div>
                                                <label for="areaRentFrom">Diện tích từ</label>
                                                <input
                                                        type="number"
                                                        id="areaRentFrom"
                                                        placeholder=""
                                                        class="form-control"
                                                        name="areaRentFrom"
                                                />
                                            </div>
                                        </div>
                                        <div class="col-sm-3">
                                            <div>
                                                <label for="areaRentTo">Diện tích đến</label>
                                                <input
                                                        type="number"
                                                        id="areaRentTo"
                                                        placeholder=""
                                                        class="form-control"
                                                        name="areaRentTo"
                                                />
                                            </div>
                                        </div>
                                        <div class="col-sm-3">
                                            <div>
                                                <label for="costRentFrom">Giá thuê từ</label>
                                                <input
                                                        type="number"
                                                        id="costRentFrom"
                                                        placeholder=""
                                                        class="form-control"
                                                        name="costRentFrom"
                                                />
                                            </div>
                                        </div>
                                        <div class="col-sm-3">
                                            <div>
                                                <label for="costRentTo">Giá thuê đến</label>
                                                <input
                                                        type="number"
                                                        id="costRentTo"
                                                        placeholder=""
                                                        class="form-control"
                                                        name="costRentTo"
                                                />
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-sm-4">
                                            <label for="managerName">Tên quản lý</label>
                                            <input type="text" id="managerName" class="form-control" />
                                        </div>
                                        <div class="col-sm-4">
                                            <label for="managerPhone">Điện thoại quản lý</label>
                                            <input type="text" id="managerPhone" class="form-control" />
                                        </div>
                                        <div class="col-sm-3">
                                            <label for="staffId">Chọn nhân viên phụ trách</label>
                                           <form:select path="staffId" cssClass="form-control" >
                                               <form:option value="-1" label="---Chọn nhân viên---" />
                                               <form:options items="${staffmaps}" />
                                           </form:select>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="checkbox">
                                            <label for="">
                                                <input type="checkbox" class="ace" name="form-field-checkbox" />
                                                <span class="lbl">Tầng trệt</span>
                                            </label>
                                            <label for="">
                                                <input type="checkbox" class="ace" name="form-field-checkbox" />
                                                <span class="lbl">Nguyên căn</span>
                                            </label>
                                            <label for="">
                                                <input type="checkbox" class="ace" name="form-field-checkbox" />
                                                <span class="lbl">Nội thất</span>
                                            </label>
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
                        <button class="btn btn-white btn-info btn-bold" data-toggle="tooltip" title="Thêm tòa nhà">
                            <i class="fa fa-plus-circle"></i>
                        </button>
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
                    <table id="buildingList" class="table table-striped table-bordered table-hover">
                        <thead>
                        <tr>
                            <th></th>
                            <th>Tên tòa nhà</th>
                            <th>Số tầng hầm</th>
                            <th>Địa chỉ</th>
                            <th>Tên quản lý</th>
                            <th>Số điện thoại</th>
                            <th>Diện tích sàn</th>
                            <th>Giá thuê</th>
                            <th>Phí dịch vụ</th>
                            <th>Thao tác</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="item" items="${buildings}">
                            <tr>
                                <td><input type="checkbox" value="4" id="checkbox_4" /></td>
                                <td>${item.name}</td>
                                <td>${item.numberOfBasement}</td>
                                <td>$45</td>
                                <td>$45</td>
                                <td>$45</td>
                                <td>$45</td>
                                <td>$45</td>
                                <td>$45</td>
                                <td>
                                    <button
                                            class="btn btn-xs btn-info"
                                            data-toggle="tooltip"
                                            title="Giao tòa nhà"
                                            onclick="assignmentBuilding()"
                                    >
                                        <i class="fa fa-bars" aria-hidden="true"></i>
                                    </button>
                                </td>
                            </tr>
                        </c:forEach>

                        </tbody>
                    </table>
                </div>
            </div>
        </div>
        <!-- /.page-content -->
    </div>
</div>
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
                    <tr>
                        <td><input type="checkbox" value="2" id="checkbox_2" /></td>
                        <td>Nguyễn Văn B</td>
                    </tr>
                    <tr>
                        <td><input type="checkbox" value="3" id="checkbox_3" /></td>
                        <td>Nguyễn Văn B</td>
                    </tr>
                    <tr>
                        <td><input type="checkbox" value="4" id="" /></td>
                        <td>Nguyễn Văn C</td>
                    </tr>
                    </tbody>
                </table>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Gửi</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">Đóng</button>
            </div>
        </div>
    </div>
</div>
<script>
    function assignmentBuilding() {
        openModalAssignmentBuilding();
        loadStaff();
    }
    function loadStaff(){
        $.ajax({
            type:'GET',
            url:'${loadStaffAPI}/1/staffs',
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
            },
            error:function (response) {
                console.log("failed")
                console.log(response)
            }
        })
    }
    function openModalAssignmentBuilding() {
        $("#assignmentBuildingModal").modal();
    }

    $('#btnSearch').click(function (e){
        e.preventDefault()
        $('#listForm').submit()
    })
</script>
</body>
</html>
