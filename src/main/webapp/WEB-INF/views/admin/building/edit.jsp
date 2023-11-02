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
                    <form:form modelAttribute ="buildingEdit" cssClass="form-horizontal" id="formEdit" method="GET">
                        <div class="form-group">
                            <label for="name" class="col-sm-3 control-label no-padding-right">Tên tòa nhà</label>
                            <div class="col-sm-9">
                                <form:input path="name" type="text" cssClass="form-control" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="managerName" class="col-sm-3 control-label no-padding-right">Tên quản lý tòa nhà</label>
                            <div class="col-sm-9">
                                <form:input path="managerName" type="text" cssClass="form-control" />

                            </div>
                        </div>
                        <div class="form-group">
                            <label for="managerPhone" class="col-sm-3 control-label no-padding-right">Sdt quản lý tòa nhà</label>
                            <div class="col-sm-9">
                                <form:input path="managerPhone" type="text" cssClass="form-control" />

                            </div>
                        </div>
                        <div class="form-group">
                            <label for="districtCode" class="col-sm-3 control-label no-padding-right">Quận</label>
                            <div class="col-sm-3">
                                <select class="form-control" name="districtCode" id="districtCode">
                                    <option value="">---Chọn quận---</option>
                                    <c:forEach var="entry" items="${districtmaps}">
                                        <option value="${entry.key}" ${buildingEdit.districtCode.equalsIgnoreCase(entry.key) ? "selected":""}>${entry.value}</option>
                                    </c:forEach>

                                </select>

                            </div>
                        </div>
                        <div class="form-group">
                            <label for="ward" class="col-sm-3 control-label no-padding-right">Phường</label>
                            <div class="col-sm-9">
                                <form:input path="ward" type="text" cssClass="form-control" />

                            </div>
                        </div>
                        <div class="form-group">
                            <label for="street" class="col-sm-3 control-label no-padding-right">Đường</label>
                            <div class="col-sm-9">
                                <form:input path="street" type="text" cssClass="form-control" />
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="numberOfBasement" class="col-sm-3 control-label no-padding-right">Số tầng hầm</label>
                            <div class="col-sm-9">
                                <form:input path="numberOfBasement" type="number" cssClass="form-control" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="floorArea" class="col-sm-3 control-label no-padding-right">Diện tích sàn</label>
                            <div class="col-sm-9">
                                <form:input path="floorArea" type="number" cssClass="form-control" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="direction" class="col-sm-3 control-label no-padding-right">Hướng</label>
                            <div class="col-sm-9">
                                <form:input path="direction" type="text" cssClass="form-control" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="level" class="col-sm-3 control-label no-padding-right">Hạng</label>
                            <div class="col-sm-9">
                                <form:input path="level" type="text" cssClass="form-control" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="rentArea" class="col-sm-3 control-label no-padding-right">Diện tích thuê</label>
                            <div class="col-sm-9">
                                <form:input path="rentArea" placeholder="vd:100,200" type="text" cssClass="form-control" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="rentPrice" class="col-sm-3 control-label no-padding-right">Giá thuê</label>
                            <div class="col-sm-9">
                                <form:input path="rentPrice" type="number" cssClass="form-control" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="rentPriceDescription" class="col-sm-3 control-label no-padding-right">Mô tả giá thuê</label>
                            <div class="col-sm-9">
                                <form:textarea path="rentPriceDescription" cssClass="form-control"></form:textarea>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="carFee" class="col-sm-3 control-label no-padding-right">Phí xe hơi</label>
                            <div class="col-sm-9">
                                <form:input path="carFee" type="text" cssClass="form-control" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="motoFee" class="col-sm-3 control-label no-padding-right">Phí xe máy</label>
                            <div class="col-sm-9">
                                <form:input path="motoFee" type="text" cssClass="form-control" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="overtimeFee" class="col-sm-3 control-label no-padding-right">Phí ngoài giờ</label>
                            <div class="col-sm-9">
                                <form:input path="overtimeFee" type="text" cssClass="form-control" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="waterFee" class="col-sm-3 control-label no-padding-right">Giá nước</label>
                            <div class="col-sm-9">
                                <form:input path="waterFee" type="text" cssClass="form-control" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="electricityFee" class="col-sm-3 control-label no-padding-right">Giá điện</label>
                            <div class="col-sm-9">
                                <form:input path="electricityFee" type="text" cssClass="form-control" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="deposit" class="col-sm-3 control-label no-padding-right">Giá cọc</label>
                            <div class="col-sm-9">
                                <form:input path="deposit" type="text" cssClass="form-control" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="payment" class="col-sm-3 control-label no-padding-right">Thanh toán</label>
                            <div class="col-sm-9">
                                <form:input path="payment" type="text" cssClass="form-control" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="renttime" class="col-sm-3 control-label no-padding-right">Thời gian thuê</label>
                            <div class="col-sm-9">
                                <form:input path="renttime" type="text" cssClass="form-control" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="note" class="col-sm-3 control-label no-padding-right">Ghi chú</label>
                            <div class="col-sm-9">
                                <form:input path="note" type="text" cssClass="form-control" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="linkOfBuilding" class="col-sm-3 control-label no-padding-right">Link tòa nhà</label>
                            <div class="col-sm-9">
                                <form:input path="linkOfBuilding" type="text" cssClass="form-control" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="map" class="col-sm-3 control-label no-padding-right">Map</label>
                            <div class="col-sm-9">
                                <form:input path="map" type="text" cssClass="form-control" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right">Hình đại diện</label>
                            <input class="col-sm-3 no-padding-right" type="file" id="uploadImage"/>
                            <div class="col-sm-9">
                                <c:if test="${not empty buildingEdit.image}">
                                    <c:set var="imagePath" value="/repository${buildingEdit.image}"/>
                                    <img src="${imagePath}" id="viewImage" width="300px" height="300px" style="margin-top: 50px">
                                </c:if>
                                <c:if test="${empty buildingEdit.image}">
                                    <img src="/admin/image/default.png" id="viewImage" width="300px" height="300px">
                                </c:if>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="brokeragetee" class="col-sm-3 control-label no-padding-right">Phí môi giới</label>
                            <div class="col-sm-9">
                                <form:input path="brokeragetee" type="number" cssClass="form-control" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="serviceFee" class="col-sm-3 control-label no-padding-right">Phí dịch vụ</label>
                            <div class="col-sm-9">
                                <form:input path="serviceFee" type="text" cssClass="form-control" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right">Loại tòa nhà</label>
                            <div class="col-sm-9">
                                <c:forEach var="type" items="${typemaps}">
                                    <label for="${type.key}" class="checkbox-inline">
                                        <c:set var="isChecked" value="${fn:containsIgnoreCase(buildingEdit.type, type.key)}" />
                                        <input type="checkbox" value="${type.key}" id="${type.key}" ${isChecked ? "checked":""} name="type" />
                                            ${type.value}
                                    </label>
                                </c:forEach>

                            </div>
                        </div>


                        <div class="form-group">
                            <div class="col-sm-2"></div>
                            <div class="col-sm-9">
                                <c:choose>
                                    <c:when test="${mode.equals('update')}">
                                        <button type="button" class="btn btn-primary btn-bold" id="btnSendBuilding">Cập nhật tòa nhà</button>
                                    </c:when>
                                    <c:otherwise>
                                        <button type="button" class="btn btn-primary btn-bold" id="btnSendBuilding">Thêm mới tòa nhà</button>
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
    var imageBase64 = '';
    var imageName = '';
    function assignmentBuilding() {
        openModalAssignmentBuilding();
    }
    function openModalAssignmentBuilding() {
        $("#assignmentBuildingModal").modal();
    }
    $('#btnSendBuilding').click(function (e) {
        e.preventDefault()
        var data = {}
        var buildingTypes=[]
        var formData = $("#formEdit").serializeArray();
        $.each(formData,function (index,v) {
                data[""+v.name+""] = !data[""+v.name+""] ? v.value:data[""+v.name+""]+","+ v.value;
            if ('' !== imageBase64) {
                data['imageBase64'] = imageBase64;
                data['imageName'] = imageName;
            }

        })
        <c:if test="${mode.equals('update') && buildingEdit.id != null}">
            if(${mode.equals("update")}){
                data.id = ${buildingEdit.id}
            }
        </c:if>

        console.log(data)

        $('#loading_image').show();
        $.ajax({
            type: "${mode.equals("update") ? 'PUT':'POST' }",
            url:'${buildingApi}',
            data:JSON.stringify(data),
            contentType:"application/json",
            success:function (response) {
                localStorage.setItem("messageSuccess", "${mode.equals('update') ? "Cập nhật":"Thêm mới"} nhà thành công ");
                window.location.href = "/admin/building-list";
            },
            error:function (response) {
                localStorage.setItem("messageDanger", "Đã có lỗi xảy ra");
                window.location.href = "/admin/building-list";
            }
        })
    })


    $('#uploadImage').change(function (event) {
        var reader = new FileReader();
        var file = $(this)[0].files[0];
        reader.onload = function(e){
            imageBase64 = e.target.result;
            imageName = file.name; // ten hinh khong dau, khoang cach. Dat theo format sau: a-b-c
        };
        reader.readAsDataURL(file);
        openImage(this, "viewImage");
    });


    function openImage(input, imageView) {
        if (input.files && input.files[0]) {
            var reader = new FileReader();
            reader.onload = function (e) {
                $('#' +imageView).attr('src', reader.result);
            }
            reader.readAsDataURL(input.files[0]);
        }
    }
</script>


</body>
</html>
