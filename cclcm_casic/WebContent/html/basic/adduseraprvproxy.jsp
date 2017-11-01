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
		init();
	});
	function init(){
		preCalendarDay();
		document.getElementById("allOptions").innerHTML="";
		
	}
	function preCalendarDay(){
		Calendar.setup({inputField: "useful_life_time", button: "useful_life_time_trigger", singleClick: true, showsTime: true, ifFormat: "%Y-%m-%d %H:%M:%S", cache: true, weekNumbers:true, showOthers: true, step: 1});
	}
	function chk()
	{
		if($("#proxy_user_iidd").val().trim() == ""){
			alert("请选择添加代理人");
			$("#proxy_user_name").val("");
			$("#proxy_user_name").focus();
			return false;
		}
		if($("#useful_life_time").val() == ""){
			alert("请添加有效时间");
			$("#useful_life_time").focus();
			return false;
		}
		if($(":checkbox[name='define_value']:checked").size() == 0){
			alert("请选择需要代理的类型。");
			return false;
		}
		
	    return true;
	}
	function selectRecvUser(word){
		$("#proxy_user_iidd").val(" ");
		var url = "${ctx}/basic/getproxyfuzzyuser.action";
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
	function selectAll(sa){
		if(sa.checked){
			$(":checkbox[name='define_value']").attr("checked",true);
		}else{
			$(":checkbox[name='define_value']").attr("checked",false);
		}
	}
	//-->
	</script>
</head>
<body oncontextmenu="self.event.returnValue=false">
<center>
<form method="post" action="${ctx}/basic/adduseraprvproxy.action">
  	<table class="table_box" cellspacing=0 cellpadding=0 border=1 width="80%">
	 <tr>
	 	<td colspan="2" class="title_box">添加代理人</td>
	 </tr>
	 <tr>
		 <td align="center">
            	申请人
        </td>
        <td>
            <font color="blue"><b>${user_name}</b></font>
        </td>
    </tr>
    <tr>
    	<td align="center"><font color="red">*</font>代理人：</td>
		<td>
				
    		<input type="text" id="proxy_user_name" name="proxy_user_name" onkeyup="selectRecvUser(this.value);" size="60"/>
    		<input type="hidden" id="proxy_user_iidd" name="proxy_user_iidd" size="20"/>
    		 <br>
    		 	<div id="allOptions" class="containDiv" style="position:absolute;border:0px solid black;padding:0px">
    		 </div>
		</td>
    </tr>
    <tr>
    	<td align="center"><font color="red">*</font>有效时间：</td>
		<td>
			<input type="text" id="useful_life_time" name="useful_life_time" readonly="readonly" style="cursor:hand;" value="${useful_life_time}"/>
        	<input type="image" class="button_2003" src="${ctx}/_image/time2.jpg" id="useful_life_time_trigger">
		</td>
    </tr>
     <tr >
	    <td align="center"><font color="red">*</font>代理审批类型：</td>
		<td align="left">
			<input type="checkbox" value="all" name="all" onclick="selectAll(this)"/>全选 &nbsp;
			<s:iterator value="#request.jobTypeList" var="job">
				&nbsp;&nbsp;<input type="checkbox" value="${job.jobTypeCode}" name="define_value"/>${job.jobTypeName}
			</s:iterator>
		</td>
	</tr>
    <tr>
        <td colspan="2" align="center" class="bottom_box">
            <input type="submit" value="添加" class="button_2003" onclick="return chk();">&nbsp;
            <input type="reset" value="重置" class="button_2003">
        </td>
    </tr>
  	</table>
</form>
</center>
</body>
</html>