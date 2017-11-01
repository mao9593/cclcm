<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<link rel="stylesheet" type="text/css" href="${ctx}/_css/displaytag.css" media="screen"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/_css/css200603.css"  media="screen"/>
     <link rel="stylesheet" type="text/css" href="${ctx}/_css/print.css"  media="print"/>
 	<script language="javascript" src="${ctx}/_script/casic304_common.js"></script> 
	<script language="javascript" src="${ctx}/_script/casic304_ajax.js"></script>
 	<script type="text/javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>
 	<script language="javascript" src="${ctx}/_script/casic304_common_bm.js"></script>
 	<script language="JavaScript" src="${ctx}/_script/function.js"></script>
 	<script language="javascript" src="${ctx}/_script/jquery.jqprint-0.3.js"></script>
<script>
$(document).ready(function(){
	prepareBizApproval("${process.step_dept}","${process.step_role}","${process.step_dept_name}","${process.step_role_name}","${ctx}");
	if($("#next_approver_all").children().size() == 0){
		$("#selApprover1").hide();
		$("#selApprover2").hide();
	}
	$("#submit_btn").attr("disabled",false);
	if("${history}" == "Y"){
		viewOpinion("");//判断审批到哪一步
	}else{
		viewOpinion("read");//判断审批到哪一步
	}
	
	$("#page_print").click(function(){
		$(".printContent").jqprint();
	})		
});

function changeApproved(value){
	if(value == "true"){
		$("#opinion").val("同意");
	}else if(value == "false"){
		$("#opinion").val("不同意");
	}
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

</script>
</head>
<div class="printContent">
<body oncontextmenu="self.event.returnValue=false">
<form action="${ctx}/carriermanage/approvepersonalfilejob.action" method="post" >
<input type="hidden" name="next_approver" id="next_approver"/>
<input type="hidden" name="job_code" value="${job.job_code}"/>
<input type="hidden" id="opinion_all" name="opinion_all" value="${opinion_all}"/> 
<input type="hidden" id="opinion" name="opinion"/> 
<input type="hidden" id="types" name="types" value="${types}"/>
<input type="hidden" name="event_code" id="event_code" value="${event.event_code}"/>
<input type="hidden" id="listSize" name="listSize" value="${listSize}"/> 

<table width="95%" border="1" cellspacing="0" cellpadding="0" align="center" class="table_box">
	<tr>
	    <td colspan="6" class="title_box">新增涉密人员个人涉密资料台帐审批表</td>
	</tr>
	<tr > 
    	<td width="14%" align="center">责任人 </td>
    	<td width="20%"><font color="blue"><b>&nbsp;${event.user_name}</b></font></td>
    	<td width="13%" align="center">责任部门 </td>
    	<td width="20%"><font color="blue"><b>&nbsp;${event.dept_name}</b></font></td>
    	<td align="center"><font color="red">*</font>责任人单位</td>
		<td><font color="blue"><b>&nbsp;${event.duty_entp_id}</b></font></td>
    </tr>
    <tr>
    	<td align="center">年份</td>
		<td><font color="blue"><b>&nbsp;${event.file_year}</b></font></td>
    	<td align="center"><font color="red">*</font>季度</td>
		<td colspan="3"><font color="blue"><b>&nbsp;${event.file_quarter}</b></font></td>
	</tr>	
	<tr>
		<td align="center">涉密资料信息</td>
		<td colspan="5">
			<font color="blue"><b>&nbsp;
	    		<table border rules=all width="90%" border="0" cellspacing="0" cellpadding="0" align="left"  class="table_box_bottom_border">
	    		<tr>
					<td align="center" width="10%"><font color="blue"><b>&nbsp;涉密资料名称</b></font></td>
					<td align="center" width="10%"><font color="blue"><b>&nbsp;资料密级</b></font></td>
					<td align="center" width="10%"><font color="blue"><b>&nbsp;资料类型</b></font></td>
					<td align="center" width="10%"><font color="blue"><b>&nbsp;资料格式</b></font></td>
					<td align="center" width="15%"><font color="blue"><b>&nbsp;备注(其他类型请填写)</b></font></td>
					<td align="center" width="15%"><font color="blue"><b>&nbsp;数量</b></font></td>		
				</tr>	
	  			<s:iterator value="#request.pfileinfo" var="it">
	  				<tr>
	  					<td align="center"><font color="blue"><b>&nbsp;${it.file_name}</b></font></td>
	  		 		  	<td align="center"><font color="blue"><b>&nbsp;${it.file_seclv_name}</b></font></td> 
	  					<td align="center"><font color="blue"><b>&nbsp;${it.file_type}</b></font></td>
	  					<td align="center"><font color="blue"><b>&nbsp;${it.file_category}</b></font></td> 
	  					<td align="center"><font color="blue"><b>&nbsp;${it.other_type}</b></font></td>
	  					<td align="center"><font color="blue"><b>&nbsp;${it.file_quantity}</b></font></td>   
	  				</tr>
				</s:iterator>
	  			</table>
			</b></font>
		</td>
	</tr>
  
   <tr>
		<td align="center">部门负责人意见</td>
			<td colspan="5" id="step1">
				<table width="99%" border="0" cellspacing="0" cellpadding="0" align="left">
					<tr><td><textarea id="opinion1" rows="4" cols="100" readonly="readonly"></textarea></td></tr>
					<tr><td id="hidden1"></td></tr>
				</table>
			</td>
	</tr>
	<tr>
		<td align="center">部门保密员备案</td>
			<td colspan="5" id="step2">
				<table width="99%" border="0" cellspacing="0" cellpadding="0" align="left">
					<tr><td><textarea id="opinion2" rows="4" cols="100" readonly="readonly"></textarea></td></tr>
					<tr><td id="hidden2"></td></tr>
				</table>
			</td>
	</tr>

	<tr>
  		<td align="center" id="selApprover1">选择审批人</td>
  		<td id="selApprover2" colspan="4">
  			<table width="300" border="0" cellspacing="0" cellpadding="0" align="left" class="table_box_border_empty">
				<tr>
					<td id="allApprovers">
						<SELECT ondblclick="add_True('next_approver_all','next_approver_sel');" style="WIDTH: 100px" multiple="true" size="8" id="next_approver_all">
							<c:forEach var="item" items="${userList}" varStatus="status">
								<option value="${item.user_iidd}">${item.user_name}</option>
							</c:forEach>
						</SELECT>
					</td>
					<td align="center" valign="middle">
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
  	<tr class="Noprint">
	    <td colspan="6" align="center">
            <c:if test="${history != 'Y'}">
	     	<input class="button_2003" type="submit" value="提交" onclick="return chk();" id="submit_btn" disabled="disabled">&nbsp;
            </c:if>
			<input class="button_2003" type="button" value="返回" onClick="javascript:history.go(-1);">&nbsp;
			<c:if test="${history == 'Y'}">
			<input type="button" class="button_2003" value="打印" id="page_print" />
			</c:if>
	    </td>
  	</tr>
</table>
</form>
</body>
</div>
</html>
    
    