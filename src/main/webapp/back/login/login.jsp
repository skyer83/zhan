<%--
参考：http://layuicms.gitee.io/page/login/login.html
--%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="com.black.zhan.back.login.common.LoginConstants"%>
<%@ include file="/resources/jsp/common/common.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<title>zhan系统</title>
<link rel="stylesheet" href="<c:url value="/resources/back/css/login/login.css"/>">
</head>
<body style="background-color: #F2F2F2;">
<div class="login">
	<h1>zhan管理登录</h1>
	<form id="loginForm" class="layui-form">
		<input name="loginType" value="1" style="display: none;">
		<div class="layui-form-item">
			<input class="layui-input" name="userName" placeholder="用户名" lay-verify="required" type="text" autocomplete="off" value="admin">
		</div>
		<div class="layui-form-item">
			<input class="layui-input" name="password" placeholder="密码" lay-verify="required" type="password" autocomplete="off" value="123456">
		</div>
		<div class="layui-form-item form_code">
			<input class="layui-input" name="captcha" placeholder="验证码" lay-verify="required" type="text" autocomplete="off" value="8888">
			<div class="code">
				<img id="img_captcha" width="<%=LoginConstants.CAPTCHA_WIDTH%>" height="<%=LoginConstants.CAPTCHA_HEIGHT%>">
			</div>
		</div>
		<button class="layui-btn login_btn" lay-submit="" lay-filter="login" type="submit">登录</button>
	</form>
</div>
<%@ include file="/resources/jsp/common/script.jsp" %>
<script>
layui.use(['form', 'layer', 'common'], function(){
	var $ = layui.$
	, form = layui.form
	, layer = layui.layer
	, common = layui.common;

	form.on("submit(login)", function(data) {
		var url = BASE_PATH + "/back/login/login/doLogin";
		$.ajax({
			global:false, cache: false, async:false, type:'POST', dataType:"json", url:url, data: data.field,
			success : function (response) {
				var failMsg = response[FAIL_MSG_KEY];
				if (failMsg) {
					layer.msg(failMsg);
					return false;
				}
				window.location.href = BASE_PATH + "/back/index/index";
			},
			error : function (XMLHttpRequest, textStatus, errorThrown) {
				common.consoleError(errorThrown, url);
			}
		});
		return false;
	});
		
	
	var $imgCaptcha = $("#img_captcha");
	$imgCaptcha.on("click", function(event){
		$(event.target).attr("src", BASE_PATH + "/back/login/login/generateCaptcha?date = " + new Date() + Math.floor(Math.random()*24));
	});
	$imgCaptcha.click();
});
</script>
<script type="text/javascript" src="https://res.wx.qq.com/open/js/jweixin-1.3.2.js"></script>
<script type="text/javascript">
	alert(wx);
</script>
</body>
</html>