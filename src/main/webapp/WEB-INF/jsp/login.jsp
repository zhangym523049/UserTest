<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登陆</title>
<script type="text/javascript">
function check(){
	   var name = document.getElementById("userName").value;
	   if(name ==  null || name == ''){
	        alert("用户名不能为空");
	        return false;
	   }
	   return true;
	}s


</script>

</head>
<body>

<%-- ${pageContext.request.contextPath}/user/userInfo --%>
<form action="${pageContext.request.contextPath}/user/userInfo.do" onsubmit="return check()">
用户名：<input type="text" id="userName" name="userName" maxlength="20">
	  <font id="checkUserName" color="red"></font><br/><br/>

密码：<input type="text" id="userName" name="userName" maxlength="20">
	 <font id="checkPassword" color="red"></font><br/><br/>
	<input type="submit" value="登陆">
</form>

<input type="button" value="注册" onclick="window.location.href='${pageContext.request.contextPath}/user/goRegister.do';"/> &nbsp;&nbsp;&nbsp;&nbsp;
<input type="button" value="忘记密码" onclick="window.location.href='${pageContext.request.contextPath}/user/fogetPassword.do';"/>

</body>
</html>