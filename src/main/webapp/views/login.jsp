<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<html>
<head>
    <title>Đăng nhập</title>
</head>
<body>
<div class="container">
    <div class="login-form">
        <div class="main-div">
            <c:if test="${not empty message}">
                <div class="alert alert-${alert}">
                        ${message}
                </div>
            </c:if>
            <form method="post" action='<c:url value="/dang-nhap" />' id="formLogin">
                <div class="form-group">
                    <input type="text" class="form-control" id="inputEmail" name="userName" placeholder="Tên đăng nhập">
                </div>

                <div class="form-group">

                    <input type="password" class="form-control" id="inputPassword" name="password" placeholder="Mật khẩu">

                </div>
                <input type="hidden" name="action" value="login" id="action">
                <button type="submit" class="btn btn-primary">Đăng nhập</button>

            </form>
        </div>
    </div>
</div>
</body>
</html>
