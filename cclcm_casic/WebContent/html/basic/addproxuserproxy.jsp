<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
<title>添加代理</title>
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
		preCalendarDay();
		document.getElementById("allOptions").innerHTML="";
	});
	function preCalendarDay(){
		Calendar.setup({inputField: "useful_life_time", button: "useful_life_time_trigger", singleClick: true, showsTime: true, ifFormat: "%Y-%m-%d %H:%M:%S", cache: true, weekNumbers:true, showOthers: true, step: 1});
	}
	function chk()
	{
		if($("#proxy_user_iidd").val().trim() == ""){
			alert("请添加代理人");
			$("#proxy_user_iidd").focus();
			return false;
		}
		if($("#useful_life_time").val() == ""){
			alert("请添加有效时间");
			$("#useful_life_time").focus();
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
	//-->
	</script>
</head>
<body oncontextmenu="self.event.returnValue=false">
<center>
<form method="post" action="${ctx}/basic/adduserproxy.action">
  	<table class="table_box" cellspacing=0 cellpadding=0 border=1 width="60%">
	 <tr>
		 <td align="center">
            	申请人
        </td>
        <td colspan="2">
            <font color="blue"><b>${user_name}</b></font>
        </td>
    </tr>
    <tr>
    	<td align="center"><font color="red">*</font>代理人：</td>
		<td>
				
    		<input type="text" id="proxy_user_name" name="proxy_user_name" onkeyup="selectRecvUser(this.value);" size="20"/>
    		<input type="hidden" id="proxy_user_iidd" name="proxy_user_iidd" onkeyup="selectRecvUser(this.value);" size="20"/>
    		 <br>
    		 	<div id="allOptions" class="containDiv" style="position:absolute;border:0px solid black;padding:0px">
    		 </div>
		</td>
    </tr>
    <tr>
    	<td align="center"><font color="red">*</font>代理类型：</td>
		<td>
            <select name="proxy_type" id="proxy_type">
    			<option value="ALL">全部</option>
    			<c:if test="${isBurnEnable}">
    				<option value="BURN">刻录</option>
    			</c:if>
    			<c:if test="${isPrintEnable}">
    				<option value="PRINT">打印</option>
    			</c:if>
    		</select>
        </td>
    </tr>
    <tr>
    	<td align="center"><font color="red">*</font>有效时间：</td>
		<td>
			<input type="text" id="useful_life_time" name="useful_life_time" readonly="readonly" style="cursor:hand;" value="${useful_life_time}"/>
        	<input type="image" class="button_2003" src="${ctx}/_image/time2.jpg" id="useful_life_time_trigger">
		</td>
    </tr>
    <tr>
        <td colspan="2" align="center" class="bottom_box">
            <input type="submit" value="添加" class="button_2003" onclick="return chk();">&nbsp;
            <input type="reset" value="重置" class="button_2003">
            <input type="button" value="返回" class="button_2003" onclick="javascript:history.go(-1);">
        </td>
    </tr>
  	</table>
</form>
</center>
</body>
</html>