<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/resources/jsp/common/common.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<title>zhan系统</title>
</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
	<div class="layui-header">
		<a href='<c:url value="/back/login/login/doLogout"/>' style="color: #fff;">退出</a>
	</div>

	
	<div class="layui-body">
		<!-- 内容主体区域 -->
		<div style="padding: 15px;">内容主体区域</div>
	</div>

	<div class="layui-footer footer footer-index" style="left:0;">
		<!-- 底部固定区域 -->
		<div class="layui-main">
			<p>&copy; 2018 <a href="/">black</a> MIT license</p>
		</div>
	</div>
</div>
<%@ include file="/resources/jsp/common/script.jsp" %>
<script>

</script>
</body>
</html>