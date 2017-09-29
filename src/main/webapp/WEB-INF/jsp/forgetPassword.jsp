<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>忘记密码</title>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery-3.2.1.min.js"></script>

</head>
<body>
	<div><input type="radio" id="selectFunc1" name="selectFunc" value="phoneNum" checked="checked">使用手机号 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<input type="radio" id="selectFunc2"name="selectFunc" value="email" >使用邮箱</div>
	
<form action="<%=request.getContextPath()%>/user/forgetPassword.do">
		<input type="hidden" id="type" name="type">
		<div id="phoneNum">手机号码：<input type="text" name="phoneNum"></div>
		<div id="email">邮箱：<input type="text" name="email"></div>
		<div>验证码：&nbsp;&nbsp;&nbsp;<input type="text" id="checkCode" name="checkCode"><input type="button" value="发送验证码"></div>
	<input type="submit" value="下一步">
</form>
<script type="text/javascript">

$("#email").hide();
$("#type").val("phoneNum");

$(function(){ 
	$("#selectFunc1").click(function() {
		$("#email").hide();
		$("#phoneNum").show();
		$("#type").val("phoneNum");
		}); 
	}); 

$(function(){ 
	$("#selectFunc2").click(function() {
		$("#phoneNum").hide();
		$("#email").show();
		$("#type").val("email");
		}); 
	});

</script>
</body>
</html>