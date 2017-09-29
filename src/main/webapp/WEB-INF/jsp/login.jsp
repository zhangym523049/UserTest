<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登陆</title>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery-3.2.1.min.js"></script>
<script type="text/javascript">
function getXmlHttpRequest(){
    var xmlHttpRequest= "";
    if(window.XMLHttpRequest){ 
        xmlHttpRequest = new XMLHttpRequest();
    }
    else{ // IE
        xmlHttpRequest = new ActiveXObject("Microsoft.XMLHTTP");
    }
    return xmlHttpRequest;
}

function checkUserName(){
		
	var userName = $("#userName").val();
	xmlHttpRequest = getXmlHttpRequest();
	var url = '<%=request.getContextPath()%>'+"/user/checkLoginUserName.do?userName="+userName+"&mathRadom="+Math.random();
	xmlHttpRequest.open("get",url,true);
  	//回调处理函数指定为 calback();
    xmlHttpRequest.onreadystatechange = function(){
    	if(xmlHttpRequest.readyState==4 && xmlHttpRequest.status==200){
            /* 1，返回格式是 文本格式 (responseText) 的处理方式：*/
            var checkResult= xmlHttpRequest.responseText;
            
            if(checkResult == "\"ok\""){
            	$("#userNameCheckResult").val("ok");
            	$("#checkUserName").html("");
            }else{
            	$("#userName").val("");
            	$("#checkUserName").html(checkResult);
            	$("#checkCodeCheckFlg").val("no");
            }
        }
    };
    xmlHttpRequest.send(null);
		
}
	
</script>

</head>
<body>
<p>${returnError}</p>
<%-- ${pageContext.request.contextPath}/user/userInfo --%>
<form action="${pageContext.request.contextPath}/user/login.do">
用户名：<input type="text" id="userName" name="userName" maxlength="20" onblur="checkUserName()">
	  <input type="hidden" id="userNameCheckResult">
	  <font id="checkUserName" color="red"></font><br/><br/>

密码：<input type="text" id="password" name="password" maxlength="20">
	 <input type="hidden" id="passwordCheckResult">
	 <font id="checkPassword" color="red"></font><br/><br/>
	<input type="submit" value="登陆">
</form>

<input type="button" value="注册" onclick="window.location.href='${pageContext.request.contextPath}/user/goRegister.do';"/> &nbsp;&nbsp;&nbsp;&nbsp;
<input type="button" value="忘记密码" onclick="window.location.href='${pageContext.request.contextPath}/user/fogetPassword.do';"/>

</body>
</html>