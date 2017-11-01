<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<link rel="stylesheet" type="text/css" href="${ctx}/_css/displaytag.css" media="screen"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/_css/css200603.css"  media="screen"/>
	<script language="JavaScript" src="${ctx}/_script/function.js"></script>
	<script type="text/javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>
	<script language="javascript" src="${ctx}/_script/casic304_common.js"></script>	
 	<script language="javascript" src="${ctx}/_script/casic304_ajax.js"></script>
 	<script language="JavaScript" src="${ctx}/_script/common.js"></script>
<script>
$(document).ready(function(){
    onHover();
	prepareBizApproval("${process.step_dept}","${process.step_role}","${process.step_dept_name}","${process.step_role_name}","${ctx}");
	if($("#next_approver_all").children().size() == 0){
		$("#selApprover1").hide();
		$("#selApprover2").hide();
	}
	if("${history}" == "Y"){
		viewOpinion("");//判断审批到哪一步
	}else{
		viewOpinion("read");//判断审批到哪一步
	}	
});

function goback(){
 javascript:history.go(-1);
}
function viewOpinion(type){
	var len = $("#listSize").val();
	var opinions = $("#opinion_all").val();
	var step_opinion = new Array();
	step_opinion = opinions.split("|");
	var stepx = "";
	var i = 1;
	for(i=1;i<=len;i++){
		stepx = "showfile" + i.toString();
		if(document.getElementById(stepx)){
			document.getElementById(stepx).style.display = "block";
			var opin = "text" + i.toString();
			if(document.getElementById(opin)){
				document.getElementById(opin).innerHTML = '<font color="blue"><b>'+step_opinion[i]+'</b></font>';
			}
		}else{
			stepx = "step" + i.toString();
			if(document.getElementById(stepx)){
				document.getElementById(stepx).innerHTML = '<font color="blue"><b>'+step_opinion[i]+'</b></font>';
			}
		}
	}
	if(type == "read"){
		stepx = "opinion"+i.toString();
		if(document.getElementById(stepx)){
			document.getElementById(stepx).style.backgroundColor='#ede4cd';
			document.getElementById(stepx).readOnly="";
		}
		
		stepx = "hidden"+i.toString();
		if(document.getElementById(stepx)){
			document.getElementById(stepx).innerHTML ='&nbsp;&nbsp;<input type="radio" name="approved" checked="checked" value="true" id="approved" />同意&nbsp;&nbsp;<input type="radio" name="approved" value="false"/>不同意';
		}
	
		stepx = "addfile"+i.toString();
		if(document.getElementById(stepx)){
			document.getElementById(stepx).style.display = "block";
		}
		stepx = "fileflag"+i.toString();
		if(document.getElementById(stepx)){
			document.getElementById(stepx).innerHTML = '<input type="hidden" name="add_file" value="Y" id="add_file" />';
		}	
	}
}

function subOpinion(){
	var i= Number($("#listSize").val());
	i = i+1;
	var stepx = "opinion"+i.toString();
	if(document.getElementById(stepx)){
		var values = document.getElementById(stepx).value;
		$("#opinion").val(values);
	}else {
		$("#opinion").val("同意");
	}	
}		
function chkapprove(){

}

function viewEventDetail(event_code){
	go("${ctx}/asset/viewwasteeventdetail.action?event_code="+escape(event_code));
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
</script>
</head>
 <body oncontextmenu="self.event.returnValue=false">
<table width="95%" border="1" cellspacing="0" cellpadding="0" align="center" class="table_box">
	<tr>
	    <td colspan="6" class="title_box">查看任务详情</td>
	</tr>
	<tr> 
    	<td width="15%" align="center">申请用户： </td>
    	<td width="35%"><font color="blue"><b>${job.user_name}</b></font></td>
    	<td width="15%" align="center">用户部门： </td>
    	<td width="34%"><font color="blue"><b>${job.dept_name}</b></font></td>
    	
	</tr>
	<tr>
		<td align="center">任务流水号： </td>
    	<td ><font color="blue"><b>${job.job_code}</b></font></td>
    	<td align="center">操作：</td>
    	<td><font color="blue"><b>审批</b></font></td>
	</tr>
	<tr> 
    	<td align="center">密级： </td>
    	<td><font color="blue"><b>${job.seclv_name}</b></font></td>
    	<td align="center">业务类型： </td>
    	<td><font color="blue"><b>${job.jobType.jobTypeName}</b></font></td>
    	
	</tr>
	 <tr> 	
    	<td align="center">资产类别： </td>
    	<td><font color="blue"><b>&nbsp;${event.property_kind}</b></font></td>     	   	
    	<td align="center">设备名称： </td>
    	<td><font color="blue"><b>&nbsp;${event.property_name}</b></font></td>   	   	
    </tr>
    <tr> 	
    	<td align="center">当前责任人： </td>
    	<td><font color="blue"><b>&nbsp;${event.duty_user_name}</b></font></td>     	   	
    	<td align="center">当前责任部门： </td>
    	<td><font color="blue"><b>&nbsp;${event.duty_dept_name}</b></font></td>   	   	
    </tr>
    <c:if test="${event.event_type eq 'PROPERTYCHANGE'}">
    <tr> 	
    	<td align="center">变更后责任人： </td>
    	<td><font color="blue"><b>&nbsp;${event.user_name_after}</b></font></td>     	   	
    	<td align="center">变更后责任部门： </td>
    	<td><font color="blue"><b>&nbsp;${event.dept_name_after}</b></font></td>   	   	
    </tr>
    <tr> 	  	   	
    	<td align="center">资产存放单位： </td>
    	<td colspan="3"><font color="blue"><b>&nbsp;${event.property_place}</b></font></td>   	   	
    </tr>
	</c:if>
	<c:if test="${event.event_type eq 'WASTE'}">
    <tr> 	
    	<td align="center">折旧年限： </td>
    	<td><font color="blue"><b>&nbsp;${event.depre_life}</b></font></td>     	   	
    	<td align="center">使用年限： </td>
    	<td><font color="blue"><b>&nbsp;${event.userd_life}</b></font></td>   	   	
    </tr>
    <tr> 	  	   	
    	<td align="center">设备仪器现状及报废原因： </td>
    	<td colspan="3"><font color="blue"><b>&nbsp;${event.reason}</b></font></td>   	   	
    </tr>
	</c:if>
    <tr>	 
    	<td align="center">申请时间： </td>
    	<td colspan="3"><font color="blue"><b>&nbsp;${event.apply_time_str}</b></font></td> 		   	
  	</tr>
  	<tr>	   		   	
		<td align="center">备注：</td>
		<td colspan="3"><textarea rows="3" cols="80" readonly="readonly">&nbsp;${event.summ}</textarea></td>
  	</tr>
  	<tr height="50">
  		<td align="center">下级审批人：</td>
  		<td colspan="5"><font color="blue"><b>&nbsp;${job.next_approver_name}</b></font></td>
  	</tr>
  	<tr>
  		<td align="center">流程记录：</td>
  		<td colspan="5">
  			<table width="90%" border="0" cellspacing="0" cellpadding="0" align="left" >
				<tr>
					<td align="center" width="100">操作</td>
					<td align="center" width="100">操作人</td>
					<td align="center" width="100">部门</td>
					<td align="center" width="150">时间</td>
					<td align="center">意见</td>
				</tr>		
	  			<s:iterator value="#request.recordList" var="item">
	  				<tr>
	  					<td align="center">&nbsp;${item.operation}</td>
	  					<td align="center">&nbsp;${item.user_name}</td>
	  					<td align="center">&nbsp;${item.dept_name}</td>
	  					<td align="center">&nbsp;${item.op_time_str}</td>
	  					<td align="center">&nbsp;${item.opinion}</td>
	  				</tr>
				</s:iterator>
  			</table>
  		</td>
  	</tr>
	<form action="${ctx}/asset/handlejob.action" method="post" >
		<input type="hidden" name="next_approver" id="next_approver"/>
		<input type="hidden" name="job_code" value="${job.job_code}"/>
		<input type="hidden" name="listSize" id="listSize" value="${listSize}"/>
		<input type="hidden" name="opinion_all" id="opinion_all" value="${opinion_all}"/>
		<input type="hidden" name="history" id="history" value="${history}"/>
		<input type="hidden" id="opinion" name="opinion"/> 
	<c:choose>
		<c:when test="${job.jobType.jobTypeCode == 'WASTE'}">		
		  	<tr>
				<td align="center">鉴定意见</td>
					<td colspan="5" id="step1">
						<table width="99%" border="0" cellspacing="0" cellpadding="0" align="left">
							<tr><td><textarea id="opinion1" rows="4" cols="100" readonly="readonly"></textarea></td></tr>
							<tr><td id="hidden1"></td></tr>
						</table>
					</td>
			</tr>
			<tr>
				<td align="center">报废处理意见</td>
					<td colspan="5" id="step2">
						<table width="99%" border="0" cellspacing="0" cellpadding="0" align="left">
							<tr><td><textarea id="opinion2" rows="4" cols="100" readonly="readonly"></textarea></td></tr>
							<tr><td id="hidden2"></td></tr>
						</table>
					</td>
			</tr>
			<tr>
				<td align="center">财务部门意见</td>
					<td colspan="5" id="step3">
						<table width="99%" border="0" cellspacing="0" cellpadding="0" align="left">
							<tr><td><textarea id="opinion3" rows="4" cols="100" readonly="readonly"></textarea></td></tr>
							<tr><td id="hidden3"></td></tr>
						</table>
					</td>
			</tr>
			<tr>
				<td align="center">纪检监察部门意见</td>
					<td colspan="5" id="step4">
						<table width="99%" border="0" cellspacing="0" cellpadding="0" align="left">
							<tr><td><textarea id="opinion4" rows="4" cols="100" readonly="readonly"></textarea></td></tr>
							<tr><td id="hidden4"></td></tr>
						</table>
					</td>
			</tr>
			<tr>
				<td align="center">设备管理部门意见</td>
					<td colspan="5" id="step5">
						<table width="99%" border="0" cellspacing="0" cellpadding="0" align="left">
							<tr><td><textarea id="opinion5" rows="4" cols="100" readonly="readonly"></textarea></td></tr>
							<tr><td id="hidden5"></td></tr>
						</table>
					</td>
			</tr>
			<tr>
				<td align="center">主管院领导审批意见</td>
					<td colspan="5" id="step6">
						<table width="99%" border="0" cellspacing="0" cellpadding="0" align="left">
							<tr><td><textarea id="opinion6" rows="4" cols="100" readonly="readonly"></textarea></td></tr>
							<tr><td id="hidden6"></td></tr>
						</table>
					</td>
			</tr>
			<tr>
				<td align="center">总会计师审批意见</td>
					<td colspan="5" id="step7">
						<table width="99%" border="0" cellspacing="0" cellpadding="0" align="left">
							<tr><td><textarea id="opinion7" rows="4" cols="100" readonly="readonly"></textarea></td></tr>
							<tr><td id="hidden7"></td></tr>
						</table>
					</td>
			</tr>							
		</c:when>
		<c:otherwise>
		<c:if test="${history != 'Y'}">
			<tr>
				<td align="center"><font color="red">*</font>&nbsp;审批意见：</td>
		  		<td class="table_box_border_empty" colspan="5">
		  			<table width="90%" border="0" cellspacing="0" cellpadding="0" align="left" >
						<tr>
							<td>
								&nbsp;&nbsp;<input type="radio" name="approved" checked="checked" value="true" id="approved" onclick="changeApproved(this.value);"/>同意
								&nbsp;&nbsp;<input type="radio" name="approved" value="false" onclick="changeApproved(this.value);"/>不同意
							</td>
						</tr>		
			  			<tr>
			  				<td>
			  					<textarea rows="3" cols="50" name="opinion" id="opinion">同意</textarea>
			  				</td>
			  			</tr>	  		
		  			</table>
		  		</td>
		  	</tr>
	  	</c:if>	
  		</c:otherwise>
	</c:choose>	 	  
	<c:if test="${history != 'Y'}">			  
		  	<tr>
		  		<td align="center" id="selApprover1">选择审批人：</td>
		  		<td id="selApprover2" colspan="5">
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
	  	<tr>
		    <td colspan="6" align="center">
		    <c:if test="${history != 'Y'}">
		     	<input class="button_2003" type="submit" value="提交" onclick="return chk();">&nbsp;
			</c:if>
			<input class="button_2003" type="button" value="返回" onClick="goback()">&nbsp;
		    </td>
	  	</tr>	  	
  	</form>
</table>
</br></br>
<table align="center" width="95%">
	<tr>
	    <td class="title_box">待变动资产</td>
	</tr>
	<tr>
		<td>
			<table class="displaytable-outter" cellspacing=0 cellpadding=0 border=0 width="100%">
	 			<tr>
	   				<td>
					<display:table uid="item" class="displaytable" name="eventList" pagesize="15" sort="list">
						<display:column title="序号">
							<c:out value="${item_rowNum}"/>
						</display:column>
						<display:column title="申请人" property="user_name"/>
						<display:column title="部门" property="dept_name"/>	
						<display:column title="作业密级" property="seclv_name"/>
						<display:column title="资产种类" property="property_kind" maxLength="20"/>
						<display:column title="设备名称" property="property_name" maxLength="20"/>
						<display:column title="申请状态" property="job_status_name"/>
						<display:column title="申请时间" property="apply_time_str"/>
						<display:column title="操作" style="width:50px">
							<input type="button" class="button_2003" value="查看" onclick="viewEventDetail('${item.event_code}');"/>
						</display:column>
					</display:table>
					</td>
				</tr>
			</table>
         </td>
	</tr>
</table>
</body>
</html>
