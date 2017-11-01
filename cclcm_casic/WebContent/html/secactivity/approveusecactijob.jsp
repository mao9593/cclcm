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
	var this_step = Number($("#listSize").val()) +1;
	if(this_step == 4){
		var a_value = $("input:radio[name='secfile']:checked").val();
		if(a_value == null){
			alert("请选择有无涉密文件资料！");
			return false;
		}
		var a_value = $("input:radio[name='secother']:checked").val();
		if(a_value == null){
			alert("请选择有无涉密载体使用！");
			return false;
		}
	}	
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
function downloadFile(file_path,file_name){
	document.getElementById("file_path").value=file_path;
	document.getElementById("file_name").value=file_name;
	document.getElementById("uploadForm").submit();
}
function getFrameReturn(){
}
</script>
</head>
<div class="printContent">
<body oncontextmenu="self.event.returnValue=false">
<form action="${ctx}/burn/downloadfile.action" target="hidden_frame" id="uploadForm" method="post">
	<input type="hidden" name="file_path" id="file_path"/>
	<input type="hidden" name="file_name" id="file_name"/>
</form>
<iframe height="0" width="0" frameborder="0" id="hidden_frame" name="hidden_frame" onload="getFrameReturn();"></iframe>
<form action="${ctx}/secactivity/approveusecactijob.action" method="post" >
	<input type="hidden" name="next_approver" id="next_approver"/>
	<input type="hidden" name="job_code" value="${job.job_code}"/>
	<input type="hidden" id="listSize" name="listSize" value="${listSize}"/> 
	<input type="hidden" id="opinion_all" name="opinion_all" value="${opinion_all}"/>
	<input type="hidden" id="opinion" name="opinion"/>  
<table width="95%" border="1" cellspacing="0" cellpadding="0" align="center" class="table_box">
	<tr>
	    <td colspan="6" class="title_box">重大涉密活动（会议）保密方案审批表</td>
	</tr>
	<tr> 
    	<td width="15%" align="center">申请用户 </td>
    	<td width="20%"><font color="blue"><b>${job.user_name}</b></font></td>
    	<td width="13%" align="center">用户部门 </td>
    	<td width="20%"><font color="blue"><b>${job.dept_name}</b></font></td>
    	<td width="13%" align="center">申请类型 </td>
    	<td width="20%"><font color="blue"><b>${event.apply_type_name}</b></font></td>
	</tr>
	<tr>
		<td width="10%" align="center">业务类型</td>
    	<td width="23%"><font color="red"><b>${job.jobType.jobTypeName}</b></font></td>
		<td align="center">申请状态 </td>
    	<td><font color="red"><b>${job.job_status_name}</b></font></td> 
    	<td align="center">申请时间 </td>
    	<td><font color="red"><b>&nbsp;${event.apply_time_str}</b></font></td>
    </tr>
    <tr>
    	<td align="center">活动名称</td>
	    <td colspan="5"><font color="blue"><b>&nbsp;${event.name}</b></font></td>
	</tr>
	<tr>  	
		<td align="center">会务承担单位/部门</td>
	    <td><font color="blue"><b>&nbsp;${event.act_dept_name}</b></font></td>
	    <td align="center">活动/会议 密级</td>
	    <td><font color="blue"><b>&nbsp;${event.act_selv_name}</b></font></td>
    	<td align="center">活动地点</td>
	    <td><font color="blue"><b>&nbsp;${event.place}</b></font></td>  
	</tr>
	<tr>
    	<td align="center">主办单位</td>
	    <td><font color="blue"><b>&nbsp;${event.sponsor}</b></font></td>  
    	<td align="center">承办单位</td>
	    <td><font color="blue"><b>&nbsp;${event.organizer}</b></font></td>  	      	
    	<td align="center">活动/会议 负责人</td>
	    <td><font color="blue"><b>&nbsp;${event.secret_director_name}</b></font></td>  	
	</tr>
  	<tr>
    	<td align="center">开始时间</td>
	    <td><font color="blue"><b>&nbsp;${event.start_time_str}</b></font></td>  	
    	<td align="center">结束时间</td>
	    <td><font color="blue"><b>&nbsp;${event.end_time_str}</b></font></td>  	
	    <td align="center">联系方式</td>
	    <td><font color="blue"><b>&nbsp;${event.contact}</b></font></td>
	</tr>
	<tr>
		<td align="center">参会人员</td>
		<td colspan="5"><font color="blue"><b>&nbsp;${event.act_user}</b></font></td>
	</tr>	
	<tr>
  		<td align="center">备注</td>
		<td colspan="5"><font color="blue"><b>&nbsp;${event.summ}</b></font></td>
	</tr>
	
  	<tr>
  		<td align="center">保密措施详细情况</td>
  		<td colspan="5">
			<table width="90%" border="0" cellspacing="0" cellpadding="0" align="center" class="table_box_bottom_border">
				<tr>
					<td align="center" width="60%">保密措施</td>
					<td align="center" width="40%">具体负责人</td>
				</tr>
				<tr>
					<td align="center">对场所进行安全检查</td>
					<td align="center"><font color="blue"><b>&nbsp;${leader[0]}</b></font></td>
				</tr>
				<tr>
					<td align="center">会场使用手机信号屏蔽器，并要求与会人员进入会场关闭手机</td>
					<td align="center"><font color="blue"><b>&nbsp;${leader[1]}</b></font></td>
				</tr>
				<tr>
					<td align="center">检查是否使用无线话筒及其他无线设备</td>
					<td align="center"><font color="blue"><b>&nbsp;${leader[2]}</b></font></td>
				</tr>
				<tr>
					<td align="center">检查参会人员参会证件</td>
					<td align="center"><font color="blue"><b>&nbsp;${leader[3]}</b></font></td>
				</tr>
				<tr>
					<td align="center">通知参会人员使用参会证件</td>
					<td align="center"><font color="blue"><b>&nbsp;${leader[4]}</b></font></td>
				</tr>
				<tr>
					<td align="center">通知参会人员使用保密记录本</td>
					<td align="center"><font color="blue"><b>&nbsp;${leader[5]}</b></font></td>
				</tr>
				<tr>
					<td align="center">会议文件、资料专人管理，统一编号、登记、发放、回收</td>
					<td align="center"><font color="blue"><b>&nbsp;${leader[6]}</b></font></td>
				</tr>
				<tr>
					<td align="center">会议照像、录音、录像人员</td>
					<td align="center"><font color="blue"><b>&nbsp;${leader[7]}</b></font></td>
				</tr>
				<tr>
					<td align="center">会议休息、散场期间，负责照看、清理会场人员</td>
					<td align="center"><font color="blue"><b>&nbsp;${leader[8]}</b></font></td>
				</tr>
				<tr>
					<td align="center">会议设立专门保卫人员</td>
					<td align="center"><font color="blue"><b>&nbsp;${leader[9]}</b></font></td>
				</tr>
				<tr>
					<td align="center">会议通讯录发放回收</td>
					<td align="center"><font color="blue"><b>&nbsp;${leader[10]}</b></font></td>
				</tr>
				<tr>
					<td align="center">其他</td>
					<td align="center"><font color="blue"><b>&nbsp;${leader[11]}</b></font></td>
				</tr>
			</table>
		</td>
  	</tr>	
  	<tr>
  		<td align="center">涉密活动附件</td>
  		<td colspan="5">
  			<table width="80%" border="0" cellspacing="0" cellpadding="0" align="left" >
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
		<td align="center">会议负责人意见</td>
		<td colspan="5" id="step1">
			<table width="95%" border="0" cellspacing="0" cellpadding="0" align="left">
				<tr><td>
					<textarea id="opinion1" rows="4" cols="100" readonly="readonly"></textarea>
				</td></tr>
				<tr><td id="hidden1"></td></tr>
		</table>
	</tr>
	<tr>
		<td align="center">保密处意见</td>
		<td colspan="5" id="step2">
			<table width="95%" border="0" cellspacing="0" cellpadding="0" align="left">
				<tr><td>
					<textarea id="opinion2" rows="4" cols="100" readonly="readonly"></textarea>
				</td></tr>
				<tr><td id="hidden2"></td></tr>
		</table>
	</tr>
	<tr>
		<td align="center">保密委意见</td>
		<td colspan="5" id="step3">
			<table width="95%" border="0" cellspacing="0" cellpadding="0" align="left">
				<tr><td>
					<textarea id="opinion3" rows="4" cols="100" readonly="readonly"></textarea>
				</td></tr>
				<tr><td id="hidden3"></td></tr>
		</table>
	</tr>
	<tr>
		<td align="center">会议负责人总结</td>
		<td colspan="5" id="step4">
			<table width="95%" border="0" cellspacing="0" cellpadding="0" align="left">
			<c:if test="${history != 'Y'}">
				<tr><td>
					涉密文件资料:
					<input type="radio" id="secfile" name="secfile" value="yes" />有
					<input type="radio" name="secfile" value="no"/>无
				</td></tr>
				<tr><td>
					若有请详细填写:<br>
					<textarea name="fileinfo" id="fileinfo" rows="4" cols="100">
资料名称：         领用时间：       份数及编号：        清退时间：            
 清退情况：
					</textarea>
				</td></tr>
				<tr><td>
					涉密载体使用:
					<input type="radio" id="secother" name="secother" value="yes" />有
					<input type="radio" name="secother" value="no"/>无
				</td></tr>
				<tr><td>
					若有请详细填写：<br>
					<textarea name="otherinfo" id="otherinfo" rows="4" cols="100">
领用时间：            载体类别及编号：           清退时间：            
清退情况：						
					</textarea>
				</td></tr>
			</c:if>
				<tr><td>
					<textarea id="opinion4" rows="7" cols="100" readonly="readonly">
会议过程保密管理情况：
    总结视会议具体情况包括会议全过程详细的保密执行情况，包括：会场的保密管理情况；人员进出的情况；有无涉密产品及保管情况；会场干扰器的使用情况；会议过程中的保密执行、监督情况；以及其他保密管理职责履行内容 
    				</textarea>
				</td></tr>
				<tr><td id="hidden4"></td></tr>
		</table>
	</tr>
<!-- 	<tr>
		<td align="center">机要室确认</td>
		<td colspan="5" id="step5">
			<table width="95%" border="0" cellspacing="0" cellpadding="0" align="left">
				<tr><td>
					<textarea id="opinion5" rows="4" cols="100" readonly="readonly"></textarea>
				</td></tr>
				<tr><td id="hidden5"></td></tr>
		</table>
	</tr> -->
	<tr>
		<td align="center">保密处监督确认</td>
		<td colspan="5" id="step5">
			<table width="95%" border="0" cellspacing="0" cellpadding="0" align="left">
				<tr><td>
					<textarea id="opinion5" rows="4" cols="100" readonly="readonly"></textarea>
				</td></tr>
				<tr><td id="hidden5"></td></tr>
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
