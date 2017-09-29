<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>注册</title>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery-3.2.1.min.js"></script>
<script type="text/javascript">
	
function refresh() { 
    document.getElementById("img").src='<%=request.getContextPath()%>'+ "/user/checkImg.do?hehe="+Math.random();
}


/**
 *  获取xmlHttpRequest对象
 */
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


/**
 *  后台检查验证码是否正确
 */
function checkVerifyCode(){
	var checkCode = document.getElementById("checkCode").value
	
	if( checkCode == null || checkCode == ''){
		return ;
	}
	
	xmlHttpRequest = getXmlHttpRequest();
	var url = '<%=request.getContextPath()%>'+"/user/checkCode.do?checkCode="+checkCode+"&mathRadom="+Math.random();
	xmlHttpRequest.open("get",url,true);
  	//回调处理函数指定为 calback();
    xmlHttpRequest.onreadystatechange = function(){
    	if(xmlHttpRequest.readyState==4 && xmlHttpRequest.status==200){
            /* 1，返回格式是 文本格式 (responseText) 的处理方式：*/
            var checkResult= xmlHttpRequest.responseText;
            
            if(checkResult == "\"ok\""){
            	$("#checkCodeCheckFlg").val("ok");
            	$("#checkCodeFont").html("");
            }else{
            	document.getElementById("checkCodeFont").innerHTML=checkResult;
            	$("#checkCodeCheckFlg").val("no");
                refresh();
            }
            
        }
    };
    xmlHttpRequest.send(null);
}

/**
 * 
 *   后台检查用户名是否规范
 */
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
            
            if(checkResult == "\"ok\""){
            	$("#userNameCheckFlg").val("ok");
            	$("#checkResult").html("");
           	}else{
           		document.getElementById("checkResult").innerHTML=checkResult;
           		$("#userNameCheckFlg").val("no");
           	}
         }
    }; 
    xmlHttpRequest.send(null);
}


/**
 *  后台检查密码是否合规
 */
function checkPassword(){
	var password = $("#password").val();
	var xmlHttpRequest = getXmlHttpRequest();
    var url = '<%=request.getContextPath()%>'+"/user/checkPassword.do?password="+password+"&mathRadom="+Math.random();
    xmlHttpRequest.open("get",url,true);
  	//回调处理函数指定为 calback();
    xmlHttpRequest.onreadystatechange=function(){
    	if(xmlHttpRequest.readyState==4 && xmlHttpRequest.status==200){
            /* 1，返回格式是 文本格式 (responseText) 的处理方式：*/
            var checkResult= xmlHttpRequest.responseText;
            
            if(checkResult == "\"ok\""){
            	$("#passwordCheckFlg").val("ok");
            	$("#checkResult2").html("");
            }else{
            	document.getElementById("checkResult2").innerHTML=checkResult;
            	$("#passwordCheckFlg").val("no");
            }
            }
    }; 
    xmlHttpRequest.send(null);
}

/**
 *  后台检查两次密码是否正确
 */
function DoubleCheckPassword(){
	var password = $("#password").val();
	var passwordCheck = $("#passwordCheck").val();
	
	var xmlHttpRequest = getXmlHttpRequest();
    var url = '<%=request.getContextPath()%>'+"/user/doubleCheckPassword.do?password="+password+"&passwordCheck="+passwordCheck+"&mathRadom="+Math.random();
    xmlHttpRequest.open("get",url,true);
  	//回调处理函数指定为 calback();
    xmlHttpRequest.onreadystatechange=function(){
    	if(xmlHttpRequest.readyState==4 && xmlHttpRequest.status==200){
            /* 1，返回格式是 文本格式 (responseText) 的处理方式：*/
            var checkResult= xmlHttpRequest.responseText;
            
            if(checkResult == "\"ok\""){
            	$("#passwordCheckFlg2").val("ok");
            	$("#checkResult3").html("");
            }else{
            	document.getElementById("checkResult3").innerHTML=checkResult;
            	$("#passwordCheckFlg2").val("no");
            }
         }
    }; 
    xmlHttpRequest.send(null);
}

/**
 *  检查表单,全部OK则返回
 */
function CheckForm(){
	
	if( $("#userNameCheckFlg").val() != "ok"){
		alert("用户名格式有误,请重新输入！");
		return false;
	}else if($("#passwordCheckFlg").val() != "ok"){
		alert("用户密码格式有误,请重新输入！");
		return false;
	}else if($("#passwordCheckFlg2").val() != "ok"){
		alert("两次输入密码不相同!");
		return false;
	}else if($("#checkCodeCheckFlg").val() != "ok"){
		alert("验证码输入有误,请重新输入！");
		return false;
	}
	return true;
}

</script>
</head>
<body>
<form action="${pageContext.request.contextPath}/user/register.do" method="get" onsubmit="return CheckForm()">

	用户名：<input type="text" id="userName" name="userName" maxlength="20" onblur="checkUserName()">
		  <input type="hidden" id="userNameCheckFlg">	
		  <font id="checkResult" color="red"></font><br/>
		  
	登陆密码：<input type="password" id="password" name="password" maxlength="50" onblur="checkPassword()">
			<input type="hidden" id="passwordCheckFlg">
		   <font id="checkResult2" color="red"></font><br/>
		   
	确认密码：<input type="password" id="passwordCheck" name="passwordCheck" maxlength="50" onblur="DoubleCheckPassword()"><br/>
			<input type="hidden" id="passwordCheckFlg2">
			<font id="checkResult3" color="red"></font><br/>
	验证码：<input type="text" id="checkCode" name="checkCode" maxlength="4" onblur="checkVerifyCode()">
		  <input type="hidden" id="checkCodeCheckFlg">
		  <font id="checkCodeFont" color="red"></font>	 
	
	<img id="img" src="${pageContext.request.contextPath}/user/checkImg.do" onclick="refresh()"><br/>
	
	<input type="submit" value="注 册"><br/><br/><br/>
	
	<a href="${pageContext.request.contextPath}/user/goLogin.do">已有账户，我要登陆</a>
</form>

</body>
</html>
