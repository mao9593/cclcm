<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<link rel="stylesheet" type="text/css" href="${ctx}/_css/displaytag.css" media="screen"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/_css/css200603.css"  media="screen"/>
	<script language="JavaScript" src="${ctx}/_script/function.js"></script>
	<script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
 	<script language="javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>	
 	<script language="javascript" src="${ctx}/_script/casic304_ajax.js"></script>
 	<script language="JavaScript" src="${ctx}/_script/common.js"></script>
 	<script language="javascript" src="${ctx}/_script/casic304_common_bm.js"></script>
<script>
$(document).ready(function(){
	onHover();
	disableEnterSubmit();
	setSeclv("seclv_code");
	selectNextApprover();
});
	function selectSeclv(seclv){
		if(seclv == ""){
			alert("请选择作业密级,以确认审批流程");
			$("#seclv_code").focus();
			return false;
		}else if($("#usage_code").val() != ""){
			selectNextApprover();
		}
	}
	function selectUsage(usage){
		if(usage == ""){
			alert("请选择用途,以确认审批流程");
			$("#usage_code").focus();
			return false;
		}else if($("#seclv_code").val() != ""){
			selectNextApprover();
		}
	}
	function selectNextApprover(){
		del_all_True('next_approver_sel');
		var url = "${ctx}/basic/getnextapprover.action";
		callServerPostForm(url,document.forms[0]);
	}
	function getAjaxResult(){
		if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
			if(xmlHttp.responseText.indexOf("<SELECT") != -1){
				$("#tr_approver").show();
				document.getElementById("allApprovers").innerHTML=xmlHttp.responseText;
				$("#submit_btn").attr("disabled",false);
			}else{
				$("#tr_approver").hide();
				alert(xmlHttp.responseText);
				$("#submit_btn").attr("disabled",true);
			}
			if($("#next_approver_all").size() > 0 && $("#next_approver_all").children().size() == 0){
				$("#tr_approver").hide();
			}
		}
	}
	function chk()
	{	
		if($("#contact_num").val().trim() == ""){
			alert("请输入联系电话");
			$("#contact_num").focus();
			return false;
		}
		if($("#next_approver_all option").size() > 0 && $("#next_approver_sel option").size() == 0){
			alert("请选择审批人员");
			$("#next_approver_all").focus();
			return false;
		}		
			//审批人信息
		var next_approver = "";
		$("#next_approver_sel option").each(function(){
			next_approver += this.value+",";
		});
		var len = next_approver.length;
		if (next_approver.lastIndexOf(",") == len - 1){
			next_approver = next_approver.substring(0, len - 1);
		}
		$("#next_approver").val(next_approver);
	    return true;
	}
</script>
</head>
<body oncontextmenu="self.event.returnValue=false">
<table width="70%" border="1" cellspacing="0" cellpadding="0" align="center" class="table_box">	
	<form action="${ctx}/computer/destroyinfodevice.action" method="POST">
		<input type="hidden" id="event_code" name="event_code" value="${event_code}"/>
		<input type="hidden" id="dept_id" name="dept_id" value="${curUser.dept_id}"/>
		<input type="hidden" id="jobType" name="jobType" value="${jobType}"/>
		<input type="hidden" id="next_approver" name="next_approver" />
		<input type="hidden" name="usage_code" id="usage_code" value="default"/>
		<input type="hidden" name="seclv_code" id="seclv_code"/>
		<input type="hidden" id="device_barcode" name="device_barcode" value="${device_barcode}"/>
	<tr>
	    <td colspan="4" class="title_box">信息设备报废审批表</td>
	</tr>
	<tr> 
    	<td width="15%" align="center">申请用户 </td>
    	<td width="18%" align="center"><font color="blue"><b>&nbsp;${curUser.user_name}</b></font></td>
    	<td width="15%" align="center">用户部门 </td>
    	<td width="18%" align="center"><font color="blue"><b>&nbsp;${curUser.dept_name}</b></font></td>
    </tr>  
	<tr>
		<td align="center">责任人</td>
		<td align="center"><font color="blue"><b>&nbsp;${eventList.duty_user_name}</b></font></td>
		<td align="center">责任人部门</td>
		<td align="center"><font color="blue"><b>&nbsp;${eventList.duty_dept_name}</b></font></td>
    </tr>
    <tr>
    	<td align="center">设备类型</td>
		<td align="center"><font color="blue"><b>&nbsp;${eventList.device_type_name}</b></font></td>
		<td align="center">子类型</td>
		<td align="center"><font color="blue"><b>&nbsp;${eventList.info_type}</b></font></td>
	</tr>  
 	<tr>
    	<td align="center">保密编号</td>
		<td align="center"><font color="blue"><b>&nbsp;${eventList.conf_code}</b></font></td>
		<td align="center">设备编号</td>
		<td align="center"><font color="blue"><b>&nbsp;${eventList.device_series}</b></font></td>
	</tr>  
 	<tr>
		<td align="center">品牌类型</td>
		<td align="center"><font color="blue"><b>&nbsp;${eventList.brand_type}</b></font></td>
		<td align="center">型号</td>
		<td align="center"><font color="blue"><b>&nbsp;${eventList.device_version}</b></font></td>
    </tr> 
 	<tr>
    	<td align="center">设备密级</td>
		<td align="center"><font color="blue"><b>&nbsp;${eventList.seclv_name}</b></font></td>
		<td align="center">设备用途</td>
		<td align="center"><font color="blue"><b>&nbsp;${eventList.device_usage}</b></font></td>
    </tr>  
    <tr>
    	<td align="center">单位</td>
		<td align="center"><font color="blue"><b>&nbsp;${eventList.company}</b></font></td>
		<td align="center"><font color="red">*</font>联系电话</td>
		<td align="center"><input type="text" name="contact_num" id="contact_num" size="25"/></td>
	</tr>
	<tr>
		<td align="center">备注</td>
	   	<td colspan="3"><textarea name="summ" rows="3" cols="70" id="summ"></textarea></td>
	</tr>
  	<tr id="tr_approver" style="display: none">
  		<td align="center" id="selApprover1">选择审批人</td>
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
						<br/>
						<INPUT class="button_2003" onclick="add_all_True('next_approver_all','next_approver_sel');" type="button" value="全部增加" name="btnAddItem" /><br/>
						<br/>
						<INPUT class="button_2003" onclick="del_all_True('next_approver_sel');" type="button" value="全部删除" name="btnDelItem"><br/>
					</td>
					<td>
						<SELECT size="8" multiple="true" style="WIDTH: 100px" ondblclick="del_True('next_approver_sel');" id="next_approver_sel">
						</SELECT>
					</td>
				</tr>
			</table>
		</td>
  	</tr>
	</form>
	 	
  	<tr>
    <td colspan="4" align="center"> 
    	<input type="button" class="button_2003" value="提交" onclick="if(chk()) forms[0].submit();" id="submit_btn"/>
		<input type="button" value="返回" class="button_2003" onclick="history.go(-1);">
    </td>
  </tr>
</table>
</body>
</html>
