<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<c:url var="deleteUrl" value="/api-admin-news"></c:url>
<html>
<head>
    <title>Danh sách bài viết</title>
</head>
<body>
<div class="main-content">
    <div class="main-content-inner">
        <div class="breadcrumbs ace-save-state" id="breadcrumbs">
            <ul class="breadcrumb">
                <li>
                    <i class="ace-icon fa fa-home home-icon"></i>
                    <a href="#">Trang chủ</a>
                </li>
            </ul><!-- /.breadcrumb -->
        </div>
        <form method="GET" action="<c:url value='/admin-news' />" id="formSubmit">
            <div class="page-content">
                <c:if test="${not empty message}">
                    <div class="alert alert-${alert}">
                            ${message}
                    </div>
                </c:if>
                <div class="row" >
                    <div class="col-xs-12">
                        <div class="widget-box table-filter">
                            <div class="widget-body">
                                <div class="widget-main">
                                    <div class="form-horizontal">
                                        <div class="form-group">
                                            <label class="col-sm-3 control-label no-padding-right">Tên bài viết</label>
                                            <div class="col-sm-9">
                                                <input type="text" id="title" name="title" class="form-control" value="${model.title}">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-3 control-label no-padding-right">Loại bài viết</label>
                                            <div class="col-sm-9">
                                                <select class="form-control" id="categoryCode" name="categoryCode">
                                                    <c:if test="${empty model.categoryCode}">
                                                        <option value="">Chọn loại bài viết</option>
                                                        <c:forEach var="item" items="${categories}">
                                                            <option value="${item.code}">${item.name}</option>
                                                        </c:forEach>
                                                    </c:if>
                                                    <c:if test="${not empty model.categoryCode}">
                                                        <option value="">Chọn loại bài viết</option>
                                                        <c:forEach var="item" items="${categories}">
                                                            <option value="${item.code}"
                                                                    <c:if test="${item.code == model.categoryCode}">
                                                                        selected="selected"
                                                                    </c:if>>
                                                                    ${item.name}
                                                            </option>
                                                        </c:forEach>
                                                    </c:if>
                                                </select>
                                            </div>
                                        </div>
                                        <%--<div class="form-group">--%>
                                            <%--<label class="col-sm-3 control-label no-padding-right">Lượt xem</label>--%>
                                            <%--<div class="col-sm-9">--%>
                                                <%--<input type="number" id="view" name="view" class="form-control" value="${model.view}">--%>
                                            <%--</div>--%>
                                        <%--</div>--%>
                                        <div class="form-group">
                                            <label class="col-sm-3 control-label no-padding-right"></label>
                                            <div class="col-sm-9">
                                                <button id="btnSearch" type="button" class="btn btn-sm btn-success">
                                                    Tìm kiếm
                                                    <i class="ace-icon fa fa-arrow-right icon-on-right bigger-110"></i>
                                                </button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <br />
                            <div class="table-btn-controls">
                                <div class="pull-right tableTools-container">
                                    <div class="dt-buttons btn-overlap btn-group">
                                        <a flag="info"
                                           class="dt-button buttons-colvis btn btn-white btn-primary btn-bold" data-toggle="tooltip"
                                           title="Thêm bài viết" href='<c:url value="/admin-news?type=edit" />' >
                                            <span>
                                                <i class="fa fa-plus-circle bigger-110 purple"></i>
                                            </span>
                                        </a>
                                        <button id="btnDelete" type="button" disabled
                                            class="dt-button buttons-html5 btn btn-white btn-primary btn-bold" data-toggle="tooltip" title="Xóa bài viết">
                                            <span>
                                                <i class="fa fa-trash-o bigger-110 pink"></i>
                                            </span>
                                        </button>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-xs-12">
                                <div class="table-responsive">
                                    <table class="table table-bordered">
                                        <thead>
                                            <tr>
                                                <th><input type="checkbox" class="check-box-element" id="checkAll"></th>
                                                <th>Tên bài viết</th>
                                                <th>Mô tả ngắn</th>
                                                <th>Lượt xem</th>
                                                <th>Thao tác</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach items="${model.listResult}" var="item">
                                                <tr>
                                                    <td><input type="checkbox" class="check-box-element" id="checkbox_${item.id}" value="${item.id}"></td>
                                                    <td>${item.title}</td>
                                                    <td>${item.shortDescription}</td>
                                                    <td>${item.view}</td>
                                                    <td>
                                                        <c:url value="/admin-news" var="editURL">
                                                            <c:param name="type" value="edit" />
                                                            <c:param name="id" value="${item.id}" />
                                                        </c:url>
                                                        <a class="btn btn-sm btn-primary btn-edit" data-toggle="tooltip"
                                                        title="Cập nhập bài viết" href="${editURL}">
                                                            <i class="fa fa-pencil-square-o" aria-hidden="true"></i>
                                                        </a>
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                    <ul class="pagination" id="pagination"></ul>
                                    <input type="hidden" value="" name="page" id="page" >
                                    <input type="hidden" value="" name="maxPageItem" id="maxPageItem" >
                                    <input type="hidden" value="" name="sortName" id="sortName" >
                                    <input type="hidden" value="" name="sortBy" id="sortBy" >
                                    <input type="hidden" value="" name="type" id="type" >
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </form>
    </div>
</div><!-- /.main-content -->
    <script type="text/javascript">

        var totalPages = ${model.totalPages};
        var currentPage = ${model.page};
        var limit = 2;
        $(function () {
            window.pagObj = $('#pagination').twbsPagination({
                totalPages: totalPages,
                visiblePages: 10,
                startPage: currentPage,
                onPageClick: function (event, page) {
                    if(currentPage != page) {
                        $('#maxPageItem').val(limit);
                        $('#page').val(page);
                        $('#sortName').val("title");
                        $('#sortBy').val("desc");
                        $('#type').val("list");
                        $('#formSubmit').submit();
                    }
                }
            });
        });

        $('#btnDelete').click(function (e) {
            e.preventDefault();
            var dataArray = $('tbody input[type=checkbox]:checked').map(function () {
                return $(this).val();
            }).get();
            var data = {};
            data["ids"] = dataArray;
            $.ajax({
                url: '${deleteUrl}',
                type: 'DELETE',
                contentType: 'application/json',
                data: JSON.stringify(data),
                dataType: 'json',
                success: function (result) {
                    window.location.href = "<c:url value='/admin-news?type=list&page=1&maxPageItem=2&message=delete_success&alert=success' />"
                },
                error: function (error) {
                    window.location.href = "<c:url value='/admin-news?type=list&page=1&maxPageItem=2&message=error_system&alert=danger' />"
                }
            });
        });
        
        $('#btnSearch').click(function () {
            $('#maxPageItem').val(limit);
            $('#page').val(1);
            $('#type').val("list");
            $('#formSubmit').submit();
        });
</script>
</body>
</html>
