<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<link rel="stylesheet" type="text/css" href="${ctx}/_css/displaytag.css" media="screen"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/_css/css200603.css"  media="screen"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/_css/print.css"  media="print"/>
    <script language="javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>
	<script language="JavaScript" src="${ctx}/_script/function.js"></script>
	<script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
	<script language="javascript" src="${ctx}/_script/casic304_common_bm.js"></script>
	<script language="javascript" src="${ctx}/_script/jquery.jqprint-0.3.js"></script>
<script>
$(document).ready(function(){
	prepareBizApproval("${process.step_dept}","${process.step_role}","${process.step_dept_name}","${process.step_role_name}","${ctx}");
	if($("#next_approver_all").children().size() == 0){
		$("#selApprover1").hide();
		$("#selApprover2").hide();
	}
	$("#submit_btn").attr("disabled",false);
	var flag = $("#history").val();
	var type = "read";
	if(flag == 'Y'){
		type = "readonly";
	}
	viewOpinion(type);//判断审批到哪一步
	
	$("#page_print").click(function(){
		$(".printContent").jqprint();
	})
});
function viewEventDetail(event_code){
	go("${ctx}/secmanage/viewexchangematerialdetail.action?event_code="+escape(event_code));
}
function chk(){
	subOpinion();//提交时将审批意见复制给opinion
	if($("#opinion").val().trim() == ""){
		alert("审批意见不能为空");
		$("#opinion").focus();
		return false;
	}
	if($("#next_approver_all option").size() > 0 && $("#next_approver_sel option").size() == 0 && $("#approved")[0].checked){
		alert("请选择审批人员");
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
	return true;
}
function changeApproved(value){
	if(value == "true"){
		$("#opinion").val("同意");
	}else if(value == "false"){
		$("#opinion").val("不同意");
	}
}
function goback(){
	javascript:history.go(-1);
}
</script>
</head>
<div class="printContent">
<body oncontextmenu="self.event.returnValue=false">
<form action="${ctx}/secmanage/approveexchangematerialjob.action" method="post" >
	<input type="hidden" name="next_approver" id="next_approver"/>
	<input type="hidden" name="job_code" value="${job.job_code}"/>
	<input type="hidden" id="listSize" name="listSize" value="${listSize}"/> 
    <input type="hidden" id="opinion_all" name="opinion_all" value="${opinion_all}"/> 
    <input type="hidden" id="opinion" name="opinion"/> 
    <input type="hidden" id="types" name="types" value="${types}"/>
    <input type="hidden" id="history" name="history" value="${history}"/> 
<table width="95%" border="1" cellspacing="0" cellpadding="0" align="center" class="table_box">
	<tr>
	    <td colspan="4" class="title_box">对外提供资料保密审查表</td>
	</tr>
	<tr> 
    	<td width="10%" align="center">申请部门 </td>
    	<td width="23%"><font color="blue"><b>${job.dept_name}</b></font></td>
    	<td width="10%" align="center">申请用户 </td>
    	<td width="23%"><font color="blue"><b>${job.user_name}</b></font></td>
	</tr>
	<tr>
		<td align="center">业务类型</td>
    	<td><font color="blue"><b>${job.jobType.jobTypeName}</b></font></td>
		<td align="center">申请状态 </td>
    	<td><font color="blue"><b>${job.job_status_name}</b></font></td> 
	</tr>
	<tr>
		<td align="center">联系电话 </td>
    	<td><font color="blue"><b>&nbsp;${event.contact_num}</b></font></td>
		<td align="center">交付材料类型 </td>
		<td><font color="blue"><b>&nbsp;${event.exc_type_name}</b></font></td>
	</tr>
	<tr>
		<td align="center">交付对象 </td>
    	<td><font color="blue"><b>&nbsp;${event.exc_object}</b></font></td>
    	<td align="center">交付地点 </td>
    	<td><font color="blue"><b>&nbsp;${event.exc_location}</b></font></td>
	</tr>
	<tr>
		<td align="center">交付资料清单</td>
		<td colspan="3" align="center">
			<table border rules=all width="60%" border="0" cellspacing="0" cellpadding="0" align="left"  class="table_box_bottom_border">
				<td width="40%" align="center">资料名称</td>
				<td width="10%" align="center">密 级</td>
				<s:iterator value="#request.material" var="matr">
	  				<tr>
	  					<td align="center"><font color="blue"><b>&nbsp;${matr.file_name}</b></font></td>
	  					<td align="center"><font color="blue"><b>&nbsp;${matr.file_selv}</b></font></td>
	  				</tr>
				</s:iterator>
			</table>
		</td>
	</tr>	
	<c:if test="${history eq 'Y'}">
		<tr>
	  		<td align="center">下一步骤审批人</td>
	  		<td colspan="5"><font color="blue"><b>&nbsp;${job.next_approver_name}</b></font></td>
	  	</tr>
  	</c:if>
  	<tr>
		<td align="center">部门审查意见</td>
		<td colspan="3" id="step1">
			<table width="99%" border="0" cellspacing="0" cellpadding="0" align="left">
				<tr><td>
					<textarea id="opinion1" rows="4" cols="100" readonly="readonly" ></textarea>
				</td></tr>
				<tr><td id="hidden1"></td></tr>
			</table>
		</td>
	  </tr>
<!-- 	  <tr>
			<td align="center">保密审批意见</td>
			<td colspan="3" id="step2">
				<table width="99%" border="0" cellspacing="0" cellpadding="0" align="left">
					<tr><td>
						<textarea id="opinion2" rows="4" cols="100" readonly="readonly" ></textarea>
					</td></tr>
					<tr><td id="hidden2"></td></tr>
			</table>
	  </tr> -->
	  <tr>
			<td align="center">材料交接情况记录</td>
			<td colspan="3" id="step2">
				<table width="99%" border="0" cellspacing="0" cellpadding="0" align="left">
					<tr><td>
						<textarea id="opinion2" rows="4" cols="100" readonly="readonly" ></textarea>
					</td></tr>
					<tr><td id="hidden2"></td></tr>
			</table>
	  </tr>
	<c:if test="${history != 'Y'}">
	<tr>
  		<td align="center" id="selApprover1">选择审批人</td>
  		<td id="selApprover2" colspan="2">
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
						<INPUT class="button_2003" onclick="add_MoreTrue('next_approver_all','next_approver_sel');" type="button" value="增加-->" /><br/>
						<br/>
						<INPUT class="button_2003" onclick="del_MoreTrue('next_approver_sel');" type="button" value="删除<--"><br/>
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
  	</c:if>
  	<tr class="Noprint">
	    <td colspan="6" align="center">
	    	<c:if test="${history != 'Y'}">
	     	<input class="button_2003" type="submit" value="提交" onclick="return chk();" id="submit_btn" disabled="disabled">&nbsp;
	     	</c:if>
			<input class="button_2003" type="button" value="返回" onClick="return goback();">&nbsp;
			<c:if test="${history == 'Y'}">
			<input type="button" class="button_2003" value="打印" id="page_print" />
			</c:if>
	    </td>
  	</tr>
  	</form>
</table>
</br></br>
</body>
</div>
</html>
