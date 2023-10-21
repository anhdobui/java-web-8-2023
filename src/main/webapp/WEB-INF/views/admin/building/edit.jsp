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
                            <label for="staffId" class="col-sm-3 control-label no-padding-right">Người quản lý tòa nhà</label>
                            <div class="col-sm-9">
                                <input type="text" id="staffId" name="staffId" value="" class="form-control" />
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
                                <input type="text" id="numberOfBasement" name="numberOfBasement" value="" class="form-control" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right">Loại tòa nhà</label>
                            <div class="col-sm-9">
                                <label for="" class="checkbox-inline"
                                ><input type="checkbox" value="TANG_TRET" name="buildingTypes" />Tầng trệt</label
                                >
                                <label for="" class="checkbox-inline"
                                ><input type="checkbox" value="NGUYEN_CAN" name="buildingTypes" />Nguyên căn</label
                                >
                                <label for="" class="checkbox-inline"
                                ><input type="checkbox" value="NOI_THAT" name="buildingTypes" />Nội thất</label
                                >
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="areaRent" class="col-sm-3 control-label no-padding-right">Diện tích thuê</label>
                            <div class="col-sm-9">
                                <input type="text" id="areaRent" class="form-control" name="areaRent" />
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
            data[""+v.name+""] = v.value;
        })
        console.log(formData)
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
