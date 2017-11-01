<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<link rel="stylesheet" type="text/css" href="${ctx}/_css/displaytag.css" media="screen"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/_css/css200603.css"  media="screen"/>
    <script language="javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>
	<script language="JavaScript" src="${ctx}/_script/function.js"></script>
	<script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
	<script language="JavaScript" src="${ctx}/_script/function.js"></script>
<script>
$(document).ready(function(){
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
	if( Number($("#listSize").val()) == 0){
		document.getElementById("property_no").readOnly="";
		document.getElementById("identity_code").readOnly="";
	}
	if( Number($("#listSize").val()) == 1){
		document.getElementById("voucher_no").readOnly="";
	}
	$("#submit_btn").attr("disabled",false);
});
function viewEventDetail(event_code){
	go("${ctx}/asset/viewstorageeventdetail.action?updatFlag=Y&event_code="+escape(event_code));
}
function goback(){
 javascript:history.go(-1);
}
function chk(){
    subOpinion();
	if( Number($("#listSize").val()) == 0){
		if($("#property_no").val().trim() == ""){
		alert("资产编码不能为空");
		$("#property_no").focus();
		return false;
		}
		if($("#identity_code").val().trim() == ""){
		alert("识别码不能为空");
		$("#identity_code").focus();
		return false;
		}
	}
	if( Number($("#listSize").val()) == 1){
		if($("#voucher_no").val().trim() == ""){
		alert("凭证号不能为空");
		$("#voucher_no").focus();
		return false;
		}
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
		<td align="center">申请状态： </td>
    	<td><font color="blue"><b>${job.job_status_name}</b></font></td> 
    	<td align="center">业务密级： </td>
    	<td><font color="blue"><b>${job.seclv_name}</b></font></td>
    	
	</tr>
	<tr>
		<td align="center">业务类型：</td>
    	<td ><font color="blue"><b>${job.jobType.jobTypeName}</b></font></td>
    	<td align="center">具体说明： </td>
	   	<td><textarea name="comment" rows="2" cols="30" readonly="readonly">${job.comment}</textarea></td>
	</tr>
	<tr>	
	   	<td align="center">资产种类： </td>
    	<td><font color="blue"><b>&nbsp;${event.property_kind}</b></font></td>
    	<td align="center">设备名称： </td>
    	<td><font color="blue"><b>&nbsp;${event.property_name}</b></font></td>
    </tr>
   	<tr>
   		<td align="center">规格： </td>
   		<td><font color="blue"><b>&nbsp;${event.property_standard}</b></font></td>	
   		<td align="center">型号： </td>
   		<td><font color="blue"><b>&nbsp;${event.property_type}</b></font></td>	
  	</tr>
		<tr>
  		<td align="center">资产密级： </td>
    	<td><font color="blue"><b>&nbsp;${event.property_seclvcode_name}</b></font></td> 
	    <td align="center">安装地点： </td>
   		<td><font color="blue"><b>&nbsp;${event.setup_place}</b></font></td>	  		
  	</tr>

  	<tr>
  		<td align="center">原值： </td>
	    <td><font color="blue"><b>&nbsp;${event.original_value}</b></font></td>  
   		<td align="center">资产管理部门： </td>
	    <td><font color="blue"><b>&nbsp;${event.manage_dept_name}</b></font></td>     	
  	</tr>
  	<tr>
  		<td align="center">出厂编号： </td>
	    <td><font color="blue"><b>&nbsp;${event.factory_no}</b></font></td>  
  		<td align="center">出厂日期： </td>
	    <td><font color="blue"><b>&nbsp;${event.factory_date_str}</b></font></td>  	   
  	</tr>
    <tr>	 
    	<td align="center">国别厂家： </td>
    	<td><font color="blue"><b>&nbsp;${event.supplier}</b></font></td> 	  	
	    <td align="center">数量： </td>
	    <td><font color="blue"><b>&nbsp;${event.amount}</b></font></td>	  		   	
  	</tr>
  	    <tr>	 	
    	<td align="center">启用日期： </td>
	    <td><font color="blue"><b>&nbsp;${event.use_date_str}</b></font></td>  
	    <td align="center">申请时间： </td>
    	<td><font color="blue"><b>&nbsp;${event.apply_time_str}</b></font></td>   		   	
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
  	<tr valign="middle" height="80"> 
    	<td align="center">流程信息： </td>
    	<td class="table_box_border_empty" colspan="5">
			<table class="table_box_border_empty"><tr>
				<td>
					<table>
						<tr><td align="center">
							<img alt="流程开始" border="0" src="${ctx}/_image/ico/process/prc_start.jpg" />
						</td></tr>
						<tr><td align="center">
							流程开始
						</td></tr>
					</table>
				</td>
				<td>
					<table>
						<tr><td align="center">
							<img border="0" src="${ctx}/_image/ico/process/to.gif"/>
						</td></tr>
					</table>
				</td>
				<td>
					<table>
						<tr><td align="center">
							<img alt="用户提交申请" border="0" src="${ctx}/_image/ico/process/prc_step.jpg" />
						</td></tr>
						<tr><td align="center">
							用户提交申请
						</td></tr>
					</table>
				</td>
				<td>
					<table>
						<tr><td align="center">
							<img border="0" src="${ctx}/_image/ico/process/to.gif"/>
						</td></tr>
					</table>
				</td>
				<td>
					<table>
						<tr><td align="center">
							<img border="0" src="${ctx}/_image/ico/process/prc_end.jpg" id="prc_end"/>
						</td></tr>
						<tr><td align="center">
							流程结束
						</td></tr>
					</table>
				</td>
			</tr></table>
		</td>
	</tr>
	<form action="${ctx}/asset/approvestoragejob.action" method="post" >
	<input type="hidden" name="next_approver" id="next_approver"/>
	<input type="hidden" name="job_code" value="${job.job_code}"/>
	<input type="hidden" name="listSize" id="listSize" value="${listSize}"/>
	<input type="hidden" name="opinion_all" id="opinion_all" value="${opinion_all}"/>
	<input type="hidden" name="history" id="history" value="${history}"/>
	<input type="hidden" id="opinion" name="opinion" />
	<input type="hidden" id="event_code" name="event_code" value="${event_code}"/>
	<c:if test="${history != 'Y'}">
	 <tr>
	 <tr>
				<td align="center">资产编码及凭证号</td>
					<td colspan="5" id="step1">
						<table width="99%" border="0" cellspacing="0" cellpadding="0" align="left">
						<tr>
		             		<td align="left"> 
		             		     资产编号<input type="text" id="property_no" name="property_no" readonly="readonly" value="${property_no}"/>
			                </td>
	                    </tr>	
			      		<tr>
				     		<td align="left">
				             	识别码&nbsp;<input type="text" id="identity_code" name="identity_code"  readonly="readonly"value="${identity_code}">				      		</td>
			       		</tr>
						<tr><td><textarea id="opinion1" rows="2" cols="95" readonly="readonly"></textarea></td></tr>
							<tr><td id="hidden1"></td></tr>
						</table>
					</td>
			</tr>
			<tr>
				<td align="center">财务意见</td>
					<td colspan="5" id="step2">
						<table width="99%" border="0" cellspacing="0" cellpadding="0" align="left">
							<tr>
							<tr>
				     		<td align="left">
				             	凭证号&nbsp;<input type="text" id="voucher_no" name="voucher_no"  readonly="readonly" value="${voucher_no}">	
				             </td>
			       		</tr>
                            </tr>
                            <tr><td><textarea id="opinion2" rows="2" cols="95" readonly="readonly"></textarea></td></tr>
							<tr><td id="hidden2"></td></tr>
						</table>
					</td>
			</tr>
  		<td align="center" id="selApprover1">选择审批人：</td>
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
	     	<input class="button_2003" type="submit" value="提交" onclick="return chk();" id="submit_btn" disabled="disabled">&nbsp;
	     	</c:if>
			<input class="button_2003" type="button" value="返回" onClick="goback()">&nbsp;
	    </td>
  	</tr>
  	</form>
</table>
</br></br>

</body>
</html>
