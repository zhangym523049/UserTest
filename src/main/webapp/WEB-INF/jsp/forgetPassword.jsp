<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>忘记密码</title>
</head>
<body>
<input type="radio" name="selectFunc" value="使用手机号">
<input type="radio" name="selectFunc" value="使用邮箱">
<form action="">
使用手机号码：<input type="text" id="phoneNum" name="phoneNum"><br/>
使用邮箱：<input type="text" id="emailId" name="emailId"><br/>
验证码：<input type="text" id="checkCode" name="checkCode"><input type="button" value="发送验证码">
		<br/><br/><br/>
		<input type="submit" value="下一步">

</form>
</body>
</html>