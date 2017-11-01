<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
<title>配置用户</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="${ctx}/_css/css200603.css" type="text/css" media="screen" />
	<link rel="stylesheet" type="text/css" href="${ctx}/_css/displaytag.css" media="screen"/>
	<link href="${ctx}/_script/calendar2/calendar-blue.css" type="text/css" rel="stylesheet"/>
	<script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
	<script language="javascript" src="${ctx}/_script/calendar2/calendar.js"></script>
	<script language="javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>
	<script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
	<script language="javascript" src="${ctx}/_script/casic304_ajax.js"></script>
	<script  language="JavaScript" >
	<!--
	$(document).ready(function(){
		onHover();
	});
	function chk()
	{
		if($("#proxy_user_iidd").val().trim() == ""){
			alert("请输入用户");
			$("#proxy_user_name").val("");
			$("#proxy_user_name").focus();
			return false;
		}
		
	    return true;
	}
	function selectRecvUser(word){
		$("#proxy_user_iidd").val(" ");
		var url = "${ctx}/basic/getfuzzyuser.action";
		if(word != ""){
			callServer1(url,"fuzzy="+word);
		}else{
			document.getElementById("allOptions").innerHTML="";
		}
	}

	function updateResult(){
		if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
				if(xmlHttp.responseText.toString().length > 154){
					document.getElementById("allOptions").innerHTML=xmlHttp.responseText;
				}else{
					document.getElementById("allOptions").innerHTML="";
				}
			}else{
				document.getElementById("allOptions").innerHTML="";
			}
	}
	function add_True(){
		var user_id=$("#allOption").val();
		var user_name=$("#allOption option[value='"+user_id+"']").text();
		if(user_id != ""){
			$("#proxy_user_iidd").val(user_id);
			$("#proxy_user_name").val(user_name);
		}
			document.getElementById("allOptions").innerHTML="";
	}
	
	function onOK(){
		if($("#proxy_user_iidd").val().trim() == ""){
			alert("请输入用户名");
			$("#proxy_user_name").val("");
			$("#proxy_user_name").focus();
			return false;
		}
		var result = new Object();
		result.role_id="${role_id }";
		result.user_iidd=$("#proxy_user_iidd").val();
		window.returnValue=result;	
		window.close();  
	}
	

	</script>
</head>
<body oncontextmenu="self.event.returnValue=false">
<center>
<form method="post" action="${ctx }/basic/configuserforrole.action">
<input type="hidden" value="Y" name="update" />
<input type="hidden" value="${role_id }" name="role_id" />
	<table class="table_box" cellspacing=0 cellpadding=0 border=1 width="60%">
	 <tr>
	 	<td colspan="2" class="title_box">配置用户</td>
	 </tr>
    <tr>
    	<td align="center"><font color="red">*</font>用户名：</td>
		<td>
				
    		<input type="text" id="proxy_user_name" name="proxy_user_name" onkeyup="selectRecvUser(this.value);" size="60"/>
    		<input type="hidden" id="proxy_user_iidd" name="proxy_user_iidd" onkeyup="selectRecvUser(this.value);" size="20"/>
    		 <br>
    		 	<div id="allOptions" class="containDiv" style="position:absolute;border:0px solid black;padding:0px">
    		 </div>
		</td>
    </tr>
    <tr>
        <td colspan="2" align="center" class="bottom_box">
            <input type="button" value="添加" class="button_2003" onclick="return onOK();">&nbsp;
            <input type="reset" value="重置" class="button_2003">
            <input type="button" value="关闭" class="button_2003" onclick="window.close();">
        </td>
    </tr>
  	</table>
</form>
</center>
</body>
</html>