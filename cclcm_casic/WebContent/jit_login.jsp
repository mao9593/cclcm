<%@ page language="java" pageEncoding="utf-8"%>
<%@ page import="javax.xml.rpc.ParameterMode"%>
<%@ page import="org.apache.axis.client.Call"%>
<%@ page import="org.apache.axis.client.Service"%>
<%@ page import="org.apache.axis.encoding.XMLType"%>
<%@ page import="com.sp.DAO.loginDAO"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.sp.entity.SysUser"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<%
response.setHeader("cache-control","no-store");
response.setHeader("pragma","no-cache");
response.setDateHeader("expires",0);
 %>
<head>
	<base href="<%=basePath%>">	
		<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache, must-revalidate">
	<meta http-equiv="expires" content="0">      
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body oncontextmenu="self.event.returnValue=false">
  <table>
<tr>
<td>
 <form method="post" action="" name="fr"  >
<%
if(request.getHeader("dnname")!=null){
String DN = new String(request.getHeader("dnname").getBytes("ISO8859-1"),"GB2312");
if(DN!=null&&!DN.equals("")){
	String accout="";
	int dc=DN.indexOf("DC=");
	String username="";
	if(dc!=-1){
		String username2end = DN.substring(dc+3);
		int dot = username2end.indexOf(',');
		if(dot!=-1){
			username = username2end.substring(0,dot);
		}
		else{
			username = username2end;
		}
	}
	if(username.isEmpty()){
		int m=DN.indexOf("T=");
		String identity= "";
		if(m>=0){
			String use1=DN.substring(m+2);
			int k1=use1.indexOf(",");
			if(k1>=0){
				identity = use1.substring(0,k1);
			}
			else{
				identity = use1;
			}
			System.out.println("+++++"+identity);
			
		}
		List users = new ArrayList();
		if(!identity.isEmpty()){
			loginDAO logindao=new loginDAO();
			users=logindao.getUserinfo(identity);
		}
		if(users.size()>0){
			accout=((SysUser) users.get(0)).getUser_accout();
			System.out.println("+++++"+accout);
		}else{
	%>
			<script>
			alert("用户信息不正确，请与管理员联系");
			</script>
	<%
		}
	}
	else{
		accout = username;
	}
				 %>
				   <input type="hidden" name="username" id="username" value="<%=accout %>">
				 </form>
</td>
</tr>
</table>
<script>
		try {
				  xmlHttp = new ActiveXObject("Msxml2.XMLHTTP");
				} catch (e) {
				  try {
				    xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
				  } catch (e2) {
				    xmlHttp = false;
				  }
				}
				
				if (!xmlHttp && typeof XMLHttpRequest != 'undefined') {
				  xmlHttp = new XMLHttpRequest();
				}
				 var url = "user.do?o=oa_login&username="+document.getElementById("username").value;
					 xmlHttp.onreadystatechange = updatePage;
					
					 xmlHttp.open("GET", url, false);
				
				  	xmlHttp.send(null);
 
function updatePage() {
  if (xmlHttp.readyState == 4) {
    var response = xmlHttp.responseText;
       var response = xmlHttp.responseText;
   // var func = new function("return" + response);
   var ss="mess";
   
    if(response.substr(2,4) == ss)
    { 
    	var json = eval('('+ response+')');
		if(json.mess == "1"){
	        alert("短时间内登录频繁，请稍候(30分钟后)登录！");
	        return false; 
	        document.form1.user_accout.value="";
	        document.form1.user_password.value="";
	        document.getElementById("msg").innerText = "";//清空文本框
	        //document.form1.user_accout.focus();
	              
	    }
	    else if(json.mess == "3"){
	    	alert("用户IP与设定的Ip不符，无权登录！");
	    	return false;  
	        document.form1.user_accout.value="";
	        document.form1.user_password.value="";
	        document.getElementById("msg").innerText = "";//清空文本框
	             
	    }
	      else if(json.mess == "4"){
	    	alert("用户不存在请与管理员联系！");
	    	return false;     
	        document.form1.user_accout.value="";
	        document.form1.user_password.value="";
	        document.getElementById("msg").innerText = "";//清空文本框
	          
	    }
	      else if(json.mess== "6"){
	    	alert("用户类型未知请与管理员联系！");
	    	   return false;     
	        document.form1.user_accout.value="";
	        document.form1.user_password.value="";
	        document.getElementById("msg").innerText = "";//清空文本框
	       
	    }
	      else if(json.mess == "5"){
	    	alert("用户已注销请与管理员联系！");
	    	  return false; 
	        document.form1.user_accout.value="";
	        document.form1.user_password.value="";
	        document.getElementById("msg").innerText = "";//清空文本框
	            
	    }
	    else if(json.mess == "7"){
    		window.location.href="user.do?o=ca_fremea";
	            
	    }
	    else{
	           return false;
	    }
    }
//    xmlHttp.responseText = "";
  }
}
</script>
<%
}else{
%>
	<script>
	alert("用户证书主题信息为空！");
	</script>
<%
	
}
}else{
%>
	<script>
	alert("系统参数为空！");
	</script>
<%
	
}
%>


  </body>
</html>
