<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
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
	if("${history}" == "Y"){
		viewOpinion("");//判断审批到哪一步
	}else{
		viewOpinion("read");//判断审批到哪一步
	}
	
	$("#page_print").click(function(){
		$(".printContent").jqprint();
	})	
});

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
	<form action="${ctx}/secactivity/approvesecoutexchangejob.action" method="post" >
	<input type="hidden" name="next_approver" id="next_approver"/>
	<input type="hidden" name="job_code" value="${job.job_code}"/>
	<input type="hidden" id="listSize" name="listSize" value="${listSize}"/> 
	<input type="hidden" id="opinion_all" name="opinion_all" value="${opinion_all}"/>
	<input type="hidden" id="opinion" name="opinion"/>  	
<table width="95%" border="1" cellspacing="0" cellpadding="0" align="center" class="table_box">
	<tr>
	    <td colspan="6" class="title_box">与境外交流保密工作审批表</td>
	</tr>
	<tr> 
    	<td width="10%" align="center">申请用户 </td>
    	<td width="23%"><font color="blue"><b>${job.user_name}</b></font></td>
    	<td width="10%" align="center">用户部门 </td>
    	<td width="23%"><font color="blue"><b>${job.dept_name}</b></font></td>
    	<td width="10%" align="center">业务类型</td>
    	<td width="23%"><font color="blue"><b>${job.jobType.jobTypeName}</b></font></td>
	</tr>
	<tr>
		<td align="center">申请状态 </td>
    	<td><font color="blue"><b>${job.job_status_name}</b></font></td> 
		<td align="center">来访单位</td>
	    <td colspan="3"><font color="blue"><b>&nbsp;${event.company_info}</b></font></td>  	
	</tr>
	<tr>	
		<td align="center">抵达时间</td>
	    <td><font color="blue"><b>&nbsp;${event.go_time_str}</b></font></td>  	
    	<td align="center">离所时间</td>
	    <td colspan="3"><font color="blue"><b>&nbsp;${event.back_time_str}</b></font></td>
	</tr>  
	<tr>
		<td align="center">安全保密防范内容</td>
		<td colspan="5">
			<table width="95%" border="0" cellspacing="0" cellpadding="0" align="left">
			<tr>
			   	<td width="15%" align="center"><font color="red">*</font>&nbsp;接待人员</td>
				<td width="40%"><font color="blue"><b>&nbsp;${event.receptionist}</b></font></td> 
			 	<td width="15%">&nbsp;</td>
			 	<td width="15%">&nbsp;</td>
			</tr>	
			<tr>
				<td align="center"><font color="red">*</font>&nbsp;业务交流范围</td>
				<td><font color="blue"><b>&nbsp;${event.exchange_info}</b></font></td>
				<td width="15%">&nbsp;</td>
			 	<td width="15%">&nbsp;</td>
			</tr>	
			<tr>
				<td align="center">接待地点</td>
				<td><font color="blue"><b>&nbsp;${event.reception}</b></font></td>
				<td align="center">是否涉密军工<br>管理研发场所</td>
				<td align="center">&nbsp;
					<c:if test="${event.reception_sec eq '1'}">是</c:if>
					<c:if test="${event.reception_sec eq '0'}">否</c:if>
				</td>
			</tr>
			<tr>
				<td align="center">拟参观地点</td>
				<td><font color="blue"><b>&nbsp;${event.visite_place}</b></font></td> 
				<td align="center">是否涉密军工<br>管理研发场所</td>
				<td align="center">&nbsp;
					<c:if test="${event.visite_sec eq '1'}">是</c:if>
					<c:if test="${event.visite_sec eq '0'}">否</c:if>
				</td>				
			</tr>
			<tr>
				<td align="center">提供资料</td>
				<td><font color="blue"><b>&nbsp;${event.material}</b></font></td>
				<td align="center">是否包含涉密内容</td>
				<td align="center">&nbsp;
					<c:if test="${event.material_sec eq '1'}">是</c:if>
					<c:if test="${event.material_sec eq '0'}">否</c:if>
				</td>					
			</tr>	
		</table>
	</tr>		
	<tr>
		<td align="center">外宾基本信息</td>
		<td colspan="5" align="center">
			<table border rules=all width="90%" cellspacing=5 border=5 bodercolor=#ffaa00 border="0" cellspacing="0" cellpadding="0" align="left">
				<td width="15%" align="center">姓 名</td>
				<td width="10%" align="center">性 别</td>
				<td width="15%" align="center">国 籍</td>
				<td width="20%" align="center">职 务</td>
				<td width="20%" align="center">证件号码</td>
				<s:iterator value="#request.visitorList" var="visitor">
	  				<tr>
	  					<td align="center">${visitor.visitor_name}</td>
	  					<td align="center">${visitor.visitor_sex}</td>
	  					<td align="center">${visitor.nationality}</td>
	  					<td align="center">${visitor.post_info}</td>
	  					<td align="center">${visitor.certificate_code}</td>
	  				</tr>
				</s:iterator>
			</table>
		</td>
	</tr>	
  	<tr>
  		<td align="center">涉外交流附件</td>
  		<td colspan="5">
  			<table width="60%" border="0" cellspacing="0" cellpadding="0" align="left" >
				<tr><td align="center">文件名</td></tr>		
	  			<s:iterator value="#request.burnFileList" var="burnFile">
	  				<tr>
	  					<td align="center"><u><label style="color: blue;cursor: pointer;" onclick="downloadFile('${burnFile.file_path}','${burnFile.file_name}');">${burnFile.file_name}</label></u></td>	  		
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
		<td align="center">申请部门领导意见</td>
		<td colspan="7" id="step1">
			<table width="95%" border="0" cellspacing="0" cellpadding="0" align="left">
				<tr><td>
					<textarea id="opinion1" rows="4" cols="100" readonly="readonly"></textarea>
				</td></tr>
				<tr><td id="hidden1"></td></tr>
		</table>
	</tr>
	<tr>
		<td align="center">科研场所管理部门意见</td>
		<td colspan="7" id="step2">
			<table width="95%" border="0" cellspacing="0" cellpadding="0" align="left">
				<tr><td>
					<textarea id="opinion2" rows="4" cols="100" readonly="readonly"></textarea>
				</td></tr>
				<tr><td id="hidden2"></td></tr>
		</table>
	</tr>
	<tr>
		<td align="center">科技产业与质量部国际合作处审查意见</td>
		<td colspan="7" id="step3">
			<table width="95%" border="0" cellspacing="0" cellpadding="0" align="left">
				<tr><td>
					<textarea id="opinion3" rows="4" cols="100" readonly="readonly"></textarea>
				</td></tr>
				<tr><td id="hidden3"></td></tr>
		</table>
	</tr>
	<tr>
		<td align="center">科技产业与质量部审查意见</td>
		<td colspan="7" id="step4">
			<table width="95%" border="0" cellspacing="0" cellpadding="0" align="left">
				<tr><td>
					<textarea id="opinion4" rows="4" cols="100" readonly="readonly"></textarea>
				</td></tr>
				<tr><td id="hidden4"></td></tr>
		</table>
	</tr>
	<tr>
		<td align="center">保密处审核意见</td>
		<td colspan="7" id="step5">
			<table width="95%" border="0" cellspacing="0" cellpadding="0" align="left">
				<tr><td>
					<textarea id="opinion5" rows="4" cols="100" readonly="readonly"></textarea>
				</td></tr>
				<tr><td id="hidden5"></td></tr>
		</table>
	</tr>
	<tr>
		<td align="center">保密委审批意见</td>
		<td colspan="7" id="step6">
			<table width="95%" border="0" cellspacing="0" cellpadding="0" align="left">
				<tr><td>
					<textarea id="opinion6" rows="4" cols="100" readonly="readonly"></textarea>
				</td></tr>
				<tr><td id="hidden6"></td></tr>
		</table>
	</tr>
	<tr>
		<td align="center">接待过程中保密防范情况总结</td>
		<td colspan="7" id="step7">
			<table width="95%" border="0" cellspacing="0" cellpadding="0" align="left">
				<tr><td>
					<textarea id="opinion7" rows="4" cols="100" readonly="readonly"></textarea>
				</td></tr>
				<tr><td id="hidden7"></td></tr>
		</table>
	</tr>
	<tr>
		<td align="center">保密处确认</td>
		<td colspan="7" id="step8">
			<table width="95%" border="0" cellspacing="0" cellpadding="0" align="left">
				<tr><td>
					<textarea id="opinion8" rows="4" cols="100" readonly="readonly"></textarea>
				</td></tr>
				<tr><td id="hidden8"></td></tr>
		</table>
	</tr>
	<tr>
		<td align="center">涉密交流主管部门确认</td>
		<td colspan="7" id="step9">
			<table width="95%" border="0" cellspacing="0" cellpadding="0" align="left">
				<tr><td>
					<textarea id="opinion9" rows="4" cols="100" readonly="readonly"></textarea>
				</td></tr>
				<tr><td id="hidden9"></td></tr>
		</table>
	</tr>

	<c:if test="${history != 'Y'}">
	<tr>
  		<td align="center" id="selApprover1">选择审批人</td>
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
