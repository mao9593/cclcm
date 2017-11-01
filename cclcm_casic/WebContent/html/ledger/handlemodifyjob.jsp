<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<link rel="stylesheet" type="text/css" href="${ctx}/_css/displaytag.css" media="screen"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/_css/css200603.css"  media="screen"/>
	<script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
	<script language="javascript" src="${ctx}/_script/casic304_ajax.js"></script>
 	<script type="text/javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>
<script>
<!--
$(document).ready(function(){
	onHover();
	$("#tr_send input").attr("disabled",true);
	$("#tr_send").hide();
});
function chkSubmit()
{	
	if($("#seclv_code").val() == ''){
		alert("请选择作业审批密级");
		$("#seclv_code").focus();
		return false;
	}
	//是否选择了审批人员
	if($("#next_approver_all option").size() > 0 && $("#next_approver_sel option").size() == 0){
		alert("请选择审批人员");
		$("#next_approver_all").focus();
		return false;
	}
	//审批人信息
	var next_approver = "";
	$("#next_approver_sel option").each(function(i){
		next_approver += $(this).val()+",";
	});
	var len = next_approver.length;
	if (next_approver.lastIndexOf(",") == len - 1){
		next_approver = next_approver.substring(0, len - 1);
	}
	$("#next_approver").val(next_approver);
	$("#modifyForm").submit();
	//$("#submit_btn").attr("disabled",true);
    return true;
}
function selectNextApprover(){
	del_all_True('next_approver_sel');
	var url = "${ctx}/basic/getnextapprover.action";
	//$("#jobType").val("PRINT_"+$("#cycle_type").val());
	callServerPostForm(url,document.forms[0]);
}
function selectSeclv(seclv){
	if(seclv == ""){
		alert("请选择作业审批密级,以确认审批流程");
		$("#seclv_code").focus();
		return false;
	}else {
		$("select[name='seclv_code']").each(function(){
			index = $(this).val().indexOf(":");
			$(this).val($(this).val().substring(0,index+1)+seclv);
			$("#trg_seclv").val(this.value);
		});
			selectNextApprover();
	}
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
//-->
</script>
</head>
<body oncontextmenu="self.event.returnValue=false">
<table width="95%" border="1" cellspacing="0" cellpadding="0" align="center" class="table_box">
<form action="${ctx}/ledger/handlemodifyjob.action" method="post" id="modifyForm">
	<input type="hidden" name="dept_id" value="${curUser.dept_id}"/>
	<input type="hidden" name="jobType" id="jobType" value="MODIFY_SECLV"/>
	<input type="hidden" name="usage_code" id="usage_code" value="MODIFY_SECLV"/>
	<input type="hidden" name="event_ids" value="${event_ids}"/>
	<input type="hidden" name="flag" value="Y"/> <!-- 错误！输入不能为对象，只能为字符串或数字 -->
    <input type="hidden" name="file_titles" id="file_titles"/>
    <input type="hidden" name="next_approver" id="next_approver"/>
    <input type="hidden" name="trg_seclv" id="trg_seclv"/>
    <input type="hidden" name="entity_type" value="${entity_type}"/>
	<tr>
	    <td colspan="6" class="title_box">生成审批单</td>
	</tr>
	<tr> 
    	<td width="15%" align="center">申请用户： </td>
    	<td width="35%"><font color="blue"><b>${curUser.user_name}</b></font></td>
    	<td width="15%" align="center">用户部门： </td>
    	<td width="35%"><font color="blue"><b>${curUser.dept_name}</b></font></td>
    </tr>
    <tr>
    	<td align="center"><font color="red">*</font>&nbsp;密级变更：</td>
	    <td>
			<select name="seclv_code" id="seclv_code" onchange="selectSeclv(this.value);">
				<option value="">--请选择--</option>
				<s:iterator value="#request.seclvList" var="item">
					<option value="${item.seclv_code}">${item.seclv_name}</option>
				</s:iterator>
			</select>
		</td>
		<td align="center">具体说明： </td>
	   	<td><textarea name="summ" rows="3" cols="60" id="summ"></textarea></td>
	</tr>
</form>
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
		<td colspan="4">
		 <c:if test="${entity_type eq 'Paper'}">
			<display:table uid="item" class="displaytable" name="entityList">
				<display:column title="序号">
					<c:out value="${item_rowNum}"/>
				</display:column>
				<display:column property="file_title" title="文件名"/>
				<display:column property="paper_barcode" title="载体条码"/>
				<display:column property="create_type_name" title="制作方式"/>
				<display:column property="seclv_name" title="文件原密级"/>
				<display:column property="print_time" sortable="true" title="制作时间"/>
			</display:table>
			</c:if>
		<c:if test="${entity_type eq 'CD'}">
			<display:table uid="item" class="displaytable" name="entityCDList">
				<display:column title="序号">
					<c:out value="${item_rowNum}"/>
				</display:column>
				<display:column property="file_list" title="文件列表"/>
				<display:column property="cd_barcode" title="载体条码"/>
				<display:column property="create_type_name" title="制作方式"/>
				<display:column property="seclv_name" title="文件原密级"/>
				<display:column property="create_type_name" sortable="true" title="制作时间"/>
			</display:table>
		</c:if>
		</td>
	</tr>
  	<tr>
	    <td colspan="6" align="center"> 
	      <input type="button" class="button_2003" value="提交" onclick="chkSubmit();" id="submit_btn"/>&nbsp;
	      <input class="button_2003" type="button" value="返回" onclick="javascript:history.go(-1)">&nbsp;
	    </td>
	</tr>
</table>
</body>
</html>
