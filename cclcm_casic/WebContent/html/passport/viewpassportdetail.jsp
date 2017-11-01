<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
<title>查看护照详情</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="${ctx}/_css/css200603.css" type="text/css" media="screen" />
	<script language="javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>
	<script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
	<script language="javascript" src="${ctx}/_script/casic304_ajax.js"></script>
	<script  language="JavaScript" >
	<!--
	$(document).ready(function(){
		onHover();
		disableEnterSubmit();
		//document.getElementById("allOptions").innerHTML="";
	});
	
	function goback(){
	
	}
	
	function selectRecvUser(word){
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
			$("#duty_user_iidd").val(user_id);
			$("#duty_user_name").val(user_name);
			document.getElementById("allOptions").innerHTML="";
		}
	}
	function chk()
	{
	    return true;
	}
	//-->
	</script>
</head>
<body oncontextmenu="self.event.returnValue=false">
<center>
<form method="post" action="${ctx}/passport/viewpassportdetail.action">
  	<table class="table_box" cellspacing=0 cellpadding=0 border=1 width="90%">
	 <tr>
		 <td colspan="6" class="title_box"> 
            	查看护照详细信息
        </td>
    </tr>
    <input type="hidden" name="user_iidd" id="user_iidd" value="${curUser.user_iidd}"/>
    <tr>
    	<td align="center">证件编号</td>
		<td><font color="blue"><b>&nbsp;${passport.passport_num}</b></font></td>
		<td align="center">责任人</td>
		<td><font color="blue"><b>&nbsp;${passport.duty_user_name}</b></font></td>
		<td align="center">责任部门</td>
		<td><font color="blue"><b>&nbsp;${passport.duty_dept_name}</b></font></td>
    </tr>  
    <tr>
    	<td align="center">证件类型</td>
		<td><font color="blue"><b>
					&nbsp;
					<c:if test="${passport.passport_type=='0'}">普通护照</c:if>
					<c:if test="${passport.passport_type=='1'}">外交护照</c:if>
					<c:if test="${passport.passport_type=='2'}">公务护照</c:if>
					<c:if test="${passport.passport_type=='3'}">港澳通行证</c:if>
					<c:if test="${passport.passport_type=='4'}">大陆居民来往台湾地区通行证</c:if>
				</b></font></td>
		<td align="center">证件状态</td>
		<td><font color="blue"><b>
					&nbsp;
					<c:if test="${passport.passport_status=='0'}">在册</c:if>
					<c:if test="${passport.passport_status=='1'}">借出</c:if>
					<c:if test="${passport.passport_status=='2'}">删除</c:if>
				</b></font></td>
		<td align="center">签发机关</td>
		<td><font color="blue"><b>&nbsp;${passport.issuing_authority}</b></font></td>
    </tr> 
    <tr>
    	<td align="center">签发时间</td>
		<td><font color="blue"><b>&nbsp;${passport.issuing_date}</b></font></td>
    	<td align="center">过期时间</td>
		<td><font color="blue"><b>&nbsp;${passport.ending_date}</b></font></td>
    	<td align="center">备注</td>
		<td><font color="blue"><b>&nbsp;${passport.summ}</b></font></td>
    </tr>
    <tr>
        <td colspan="6" align="center" class="bottom_box">
            <input type="button" value="返回" class="button_2003" onclick="history.go(-1);">
        </td>
    </tr>
  	</table>
</form>
</center>
</body>
</html>