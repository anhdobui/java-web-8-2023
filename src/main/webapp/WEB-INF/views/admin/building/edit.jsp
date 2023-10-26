<%--
  Created by IntelliJ IDEA.
  User: ACER
  Date: 10/13/2023
  Time: 4:34 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp"%>
<c:url var="buildingApi" value="/api/building" />
<html>
<head>
    <title>Cập nhật tòa nhà</title>
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
                    <form class="form-horizontal" role="form" id="formEdit">
                        <div class="form-group">
                            <label for="name" class="col-sm-3 control-label no-padding-right">Tên tòa nhà</label>
                            <div class="col-sm-9">
                                <input type="text" id="name" name="name" value="" class="form-control" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="managerName" class="col-sm-3 control-label no-padding-right">Tên quản lý tòa nhà</label>
                            <div class="col-sm-9">
                                <input type="text" id="managerName" name="managerName" value="" class="form-control" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="managerPhone" class="col-sm-3 control-label no-padding-right">Sdt quản lý tòa nhà</label>
                            <div class="col-sm-9">
                                <input type="text" id="managerPhone" name="managerPhone" value="" class="form-control" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="districtCode" class="col-sm-3 control-label no-padding-right">Quận</label>
                            <div class="col-sm-3">
                                <select class="form-control" name="districtCode" id="districtCode">
                                    <option value="">---Chọn quận---</option>
                                    <c:forEach var="entry" items="${districtmaps}">
                                        <option value="${entry.key}">${entry.value}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="ward" class="col-sm-3 control-label no-padding-right">Phường</label>
                            <div class="col-sm-9">
                                <input type="text" id="ward" name="ward" class="form-control" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="street" class="col-sm-3 control-label no-padding-right">Đường</label>
                            <div class="col-sm-9">
                                <input type="text" id="street" name="street" class="form-control" />
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="numberOfBasement" class="col-sm-3 control-label no-padding-right">Số tầng hầm</label>
                            <div class="col-sm-9">
                                <input type="number" id="numberOfBasement" name="numberOfBasement" value="" class="form-control" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="floorArea" class="col-sm-3 control-label no-padding-right">Diện tích sàn</label>
                            <div class="col-sm-9">
                                <input type="number" id="floorArea" name="floorArea" value="" class="form-control" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="direction" class="col-sm-3 control-label no-padding-right">Hướng</label>
                            <div class="col-sm-9">
                                <input type="text" id="direction" name="direction" value="" class="form-control" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="level" class="col-sm-3 control-label no-padding-right">Hạng</label>
                            <div class="col-sm-9">
                                <input type="text" id="level" name="level" value="" class="form-control" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="rentArea" class="col-sm-3 control-label no-padding-right">Diện tích thuê</label>
                            <div class="col-sm-9">
                                <input type="text" placeholder="vd:100,200" id="rentArea" class="form-control" name="rentArea" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="rentPrice" class="col-sm-3 control-label no-padding-right">Giá thuê</label>
                            <div class="col-sm-9">
                                <input type="number" id="rentPrice" class="form-control" name="rentPrice" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="rentPriceDescription" class="col-sm-3 control-label no-padding-right">Mô tả giá thuê</label>
                            <div class="col-sm-9">
                                <textarea type="text" id="rentPriceDescription" class="form-control" name="rentPriceDescription" ></textarea>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="serviceFee" class="col-sm-3 control-label no-padding-right">Phí dịch vụ</label>
                            <div class="col-sm-9">
                                <input type="text" id="serviceFee" class="form-control" name="serviceFee" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right">Loại tòa nhà</label>
                            <div class="col-sm-9">
                                <c:forEach var="type" items="${typemaps}">
                                    <label for="${type.key}" class="checkbox-inline"
                                    ><input type="checkbox" value="${type.key}" id="${type.key}" name="type" />${type.value}</label
                                    >
                                </c:forEach>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="col-sm-2"></div>
                            <div class="col-sm-9">
                                <button type="button" class="btn btn-primary btn-bold" id="btnAddBuilding">Thêm tòa nhà</button>
                                <button type="button" class="btn btn-warning btn-bold">Hủy</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <!-- /.page-content -->
    </div>
</div>

<script>
    function assignmentBuilding() {
        openModalAssignmentBuilding();
    }
    function openModalAssignmentBuilding() {
        $("#assignmentBuildingModal").modal();
    }
    $('#btnAddBuilding').click(function (e) {
        e.preventDefault()

        var data = {}
        var buildingTypes=[]
        var formData = $("#formEdit").serializeArray();
        $.each(formData,function (index,v) {
                data[""+v.name+""] = !data[""+v.name+""] ? v.value:data[""+v.name+""]+","+ v.value;
        })
        console.log(data)
        $.ajax({
            type:'POST',
            url:'${buildingApi}',
            data:JSON.stringify(data),
            dataType:"json",
            contentType:"application/json",
            success:function (response) {
                console.log("success")
            },
            error:function (response) {
                console.log("failed")
                console.log(response)
            }
        })
    })

</script>
</body>
</html>
