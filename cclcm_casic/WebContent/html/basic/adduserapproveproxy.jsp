<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
<title>添加代理</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="${ctx}/_css/css200603.css" type="text/css" media="screen" />
	<link rel="stylesheet" type="text/css" href="${ctx}/_css/displaytag.css" media="screen"/>
	<link href="${ctx}/_script/calendar2/calendar-blue.css" type="text/css" rel="stylesheet"/>
	<script language="javascript" src="${ctx}/_script/calendar2/calendar.js"></script>
	<script language="javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>
	<script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
	<script language="javascript" src="${ctx}/_script/casic304_ajax.js"></script>
	<script  language="JavaScript" >
	$(document).ready(function(){
		onHover();
		preCalendarDay();
		prepareBizApproval("${process.step_dept}","${process.step_role}","${process.step_dept_name}","${process.step_role_name}","${ctx}");
		init();
	});
	function init(){
		if($("#tr_approver option").size()>0){
			$("#tr_approver").show();
		}
	}
	function preCalendarDay(){
		Calendar.setup({inputField: "useful_life_time", button: "useful_life_time_trigger", singleClick: true, showsTime: true, ifFormat: "%Y-%m-%d %H:%M:%S", cache: true, weekNumbers:true, showOthers: true, step: 1});
	}
	function chk()
	{
		if($("#next_approver_all option").size() > 0 && $("#next_approver_sel option").size() == 0){
		alert("请选择代理审批人员");
		$("#next_approver_all").focus();
		return false;
	}
	var next_approver = "";
	$("#next_approver_sel option").each(function(){
		next_approver += this.value+",";
	});
	var len = next_approver.length;
	if (next_approver.lastIndexOf(",") == len - 1){
		next_approver = next_approver.substring(0, len - 1);
	}
	$("#next_approver").val(next_approver);
		if($("#useful_life_time").val() == ""){
			alert("请添加有效时间");
			$("#useful_life_time").focus();
			return false;
		}
		
	    return true;
	}
	function clearInput(){
		del_all_True('next_approver_sel');
		$("#useful_life_time").val("");
	}
	</script>
</head>
<body oncontextmenu="self.event.returnValue=false">
<center>
<form method="post" action="${ctx}/basic/adduserapproveproxy.action">
<input type="hidden" name="next_approver" id="next_approver"/>
<input type="hidden" name="update" id="update" value="Y"/>
	<table class="table_box" cellspacing=0 cellpadding=0 border=1 width="60%">
	 <tr>
	 	<td colspan="2" class="title_box">添加代理审批人</td>
	 </tr>
	 <tr>
		 <td align="center">
            	申请人
        </td>
        <td>
            <font color="blue"><b>${user_name}</b></font>
        </td>
    </tr>
    <tr id="tr_approver" style="display: none">
  		<td align="center" id="selApprover1">选择审批人：</td>
  		<td id="selApprover2" colspan="3">
  			<table width="300" border="0" cellspacing="0" cellpadding="0" align="left" class="table_box_border_empty">
				<tr>
					<td id="allApprovers">
						<SELECT ondblclick="add_True('next_approver_all','next_approver_sel');" style="WIDTH: 100px" multiple="true" size="8" id="next_approver_all">
							<c:forEach var="item" items="${userList}" varStatus="status">
								<option value="${item.user_iidd}">${item.user_name}</option>
							</c:forEach>
						</SELECT>
					</td>
					<td aling="center" valign="middle">
						<INPUT class="button_2003" onclick="add_MoreTrue('next_approver_all','next_approver_sel');" type="button" value="增加-->" name="btnAddItem" /><br/>
						<br/>
						<INPUT class="button_2003" onclick="del_MoreTrue('next_approver_sel');" type="button" value="删除<--" name="btnDelItem"><br/>
					</td>
					<td>
						<SELECT size="8" multiple="true" style="WIDTH: 100px" ondblclick="del_True('next_approver_sel');" id="next_approver_sel">
						</SELECT>
					</td>
				</tr>
			</table>
		</td>
  	</tr>
    <tr>
    	<td align="center"><font color="red">*</font>代理类型：</td>
		<td>
            <select name="proxy_type" id="proxy_type">
            	<option value="APPROVE">审批</option>
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
            <input type="button" value="重置" onclick="clearInput();" class="button_2003">
            <input type="button" value="返回" class="button_2003" onclick="javascript:history.go(-1);">
        </td>
    </tr>
  	</table>
</form>
</center>
</body>
</html>