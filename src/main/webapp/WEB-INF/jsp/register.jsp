<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>注册</title>
<script type="text/javascript">
	
function refresh() { 
    document.getElementById("img").src='<%=request.getContextPath()%>'+ "/user/checkImg.do?hehe="+Math.random();
}

function getXmlHttpRequest(){
    var xmlHttpRequest= "";
    if(window.XMLHttpRequest){ // 火狐
        xmlHttpRequest = new XMLHttpRequest();
    }
    else{ // IE
        xmlHttpRequest = new ActiveXObject("Microsoft.XMLHTTP");
    }
    return xmlHttpRequest;
}

function checkCode(){
	var checkCode = document.getElementById("checkCode").value
	xmlHttpRequest = getXmlHttpRequest();
	var url = '<%=request.getContextPath()%>'+"/user/checkCode.do?checkCode="+checkCode+"&mathRadom="+Math.random();
	xmlHttpRequest.open("get",url,true);
  	//回调处理函数指定为 calback();
    xmlHttpRequest.onreadystatechange=function(){
    	if(xmlHttpRequest.readyState==4 && xmlHttpRequest.status==200){
            /* 1，返回格式是 文本格式 (responseText) 的处理方式：*/
            var checkResult= xmlHttpRequest.responseText;
            if(checkResult != null){
            	document.getElementById("checkCodeFont").innerHTML=checkResult;
            	refresh();
            }
        }
    };
    xmlHttpRequest.send(null);
}

function checkUserName(){
	var userName = document.getElementById("userName").value;
	var xmlHttpRequest = getXmlHttpRequest();
    var url = '<%=request.getContextPath()%>'+"/user/checkUserName.do?userName="+userName+"&mathRadom="+Math.random();
    xmlHttpRequest.open("get",url,true);
  	//回调处理函数指定为 calback();
    xmlHttpRequest.onreadystatechange=function(){
    	
    	if(xmlHttpRequest.readyState==4 && xmlHttpRequest.status==200){
            /* 1，返回格式是 文本格式 (responseText) 的处理方式：*/
            var checkResult= xmlHttpRequest.responseText;
            document.getElementById("checkResult").innerHTML=checkResult;
            }
    }; 
    xmlHttpRequest.send(null);
}
function checkPassword(){
	var password = document.getElementById("password").value;
	var xmlHttpRequest = getXmlHttpRequest();
    var url = '<%=request.getContextPath()%>'+"/user/checkPassword.do?password="+password+"&mathRadom="+Math.random();
    xmlHttpRequest.open("get",url,true);
  	//回调处理函数指定为 calback();
    xmlHttpRequest.onreadystatechange=function(){
    	if(xmlHttpRequest.readyState==4 && xmlHttpRequest.status==200){
            /* 1，返回格式是 文本格式 (responseText) 的处理方式：*/
            var checkResult= xmlHttpRequest.responseText;
            document.getElementById("checkResult2").innerHTML=checkResult;
            }
    }; 
    xmlHttpRequest.send(null);
}
</script>
</head>
<body>
<form action="${pageContext.request.contextPath}/user/register.do" method="get" onsubmit="return CheckForm()">

	用户名：<input type="text" id="userName" name="userName" maxlength="20" onblur="checkUserName()">
		  <font id="checkResult" color="red"></font><br/>
		  
	登陆密码：<input type="password" id="password" name="password" maxlength="50" onblur="checkPassword()">
		   <font id="checkResult2" color="red"></font><br/>
		   
	确认密码：<input type="password" id="passwordCheck" name="passwordCheck" maxlength="50"><br/>
	
	验证码：<input type="text" id="checkCode" name="checkCode" maxlength="4" onblur="checkCode()">
		  <font id="checkCodeFont" color="red"></font>	 
	
	<img id="img" src="${pageContext.request.contextPath}/user/checkImg.do" onclick="refresh()"><br/>
	
	<input type="submit" value="注 册"><br/><br/><br/>
	
	<a href="${pageContext.request.contextPath}/user/goLogin.do">已有账户，我要登陆</a>
</form>

</body>
</html>
