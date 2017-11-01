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

function viewEventDetail(event_code){
	go("${ctx}/securityuser/viewuseclvchangedetail.action?event_code="+escape(event_code));
}

function chk(){
	subOpinion();//提交时将审批意见复制给opinion
	//判断审批时是否全部勾选对应项
	
	if("${event.change_type}" == 'ADD'){

		var this_step = Number($("#listSize").val()) +1;
		if(this_step == 1){
			var a_value = $("input:radio[name='a_chk']:checked").val();
			if(a_value == null){
				alert("请选择情况是否属实！");
				$("#a_chk").focus();
				return false;
			}
			if($("#post_station").val().trim() == ""){
				alert("请填写拟安排的工作岗位！");
				$("#post_station").focus();
				return false;
			}
			var c_value = $("input:radio[name='c_chk']:checked").val();
			if(c_value == null){
				alert("请选择涉密等级！");
				$("#c_chk").focus();
				return false;
			}
		}else if(this_step == 2){
			var p_value = $("input:radio[name='p_chk']:checked").val();
			if(p_value == null){
				alert("请选择个人经历审查情况！");
				$("#p_chk").focus();
				return false;
			}
			var s_value = $("input:radio[name='s_chk']:checked").val();
			if(s_value == null){
				alert("请选择政治表现审查情况！");
				$("#s_chk").focus();
				return false;
			}
			var b_value = $("input:radio[name='b_chk']:checked").val();
			if(b_value == null){
				alert("请选择品行表现审查情况！");
				$("#b_chk").focus();
				return false;
			}
			var f_value = $("input:radio[name='f_chk']:checked").val();
			if(f_value == null){
				alert("请选择与境外人员关系审查情况！");
				$("#f_chk").focus();
				return false;
			}
			var o_value = $("input:radio[name='o_chk']:checked").val();
			if(o_value == null){
				alert("请选择调查及谈话等了解的情况！");
				$("#o_chk").focus();
				return false;
			}
/* 			var ez_value = $("input:radio[name='ez_chk']:checked").val();
			var ey_value = $("input:radio[name='ey_chk']:checked").val();
			if(ez_value == null && ey_value == null){
				alert("请选择最终审查结果！");
				$("#ez_chk").focus();
				return false;
			}
			if(ez_value != null && ey_value != null){
				alert("最终审查结果只能选择一种，请修改！");
				$("#ez_chk").focus();
				return false;
			} */
		}
	}
	//end
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
	<input type="hidden" id="listSize" name="listSize" value="${listSize}"/> 
    <input type="hidden" id="opinion_all" name="opinion_all" value="${opinion_all}"/> 
</form>
<iframe height="0" width="0" frameborder="0" id="hidden_frame" name="hidden_frame" onload="getFrameReturn();"></iframe>
<form action="${ctx}/securityuser/approveuseclvchangejob.action" method="post" >
	<input type="hidden" id="listSize" name="listSize" value="${listSize}"/> 
	<input type="hidden" id="opinion_all" name="opinion_all" value="${opinion_all}"/> 
	<input type="hidden" name="next_approver" id="next_approver"/>
	<input type="hidden" name="job_code" value="${job.job_code}"/>
	<input type="hidden" id="opinion" name="opinion"/> 
	<input type="hidden" id="types" name="types" value="${types}"/>
<table width="95%" border="1" cellspacing="0" cellpadding="0" align="center" class="table_box">
	<tr>
	<c:if test="${event.change_type == 'CHANGE'}">
		<td colspan="4" align="center" class="title_box">涉密人员变更审批表</td>
	</c:if>
	<c:if test="${event.change_type == 'ADD'}">
		<td colspan="4" align="center" class="title_box">新增涉密人员审批表</td>
	</c:if>   
	</tr>
	<tr> 
    	<td width="10%" align="center">发起人 </td>
    	<td width="23%"><font color="blue"><b>${job.user_name}</b></font></td>
    	<td width="10%" align="center">发起人部门 </td>
    	<td width="23%"><font color="blue"><b>${job.dept_name}</b></font></td> 	
	</tr>
	<tr>
		<td align="center">业务类型</td>
	    <td><font color="blue"><b>&nbsp;${event.change_type_name}</b></font></td>
		<td align="center">申请状态 </td>
    	<td><font color="red"><b>${job.job_status_name}</b></font></td> 
	</tr>		
	<c:if test="${event.change_type == 'CHANGE'}">
		<tr>
			<td align="center">变更人 </td>
	    	<td><font color="blue"><b>&nbsp;${event.change_user_name}</b></font></td>
	    	<td align="center">变更人部门 </td>
	    	<td><font color="blue"><b>&nbsp;${event.change_dept_name}</b></font></td>
		<tr>
	    	<td align="center">变更前部门 </td>
	    	<td><font color="blue"><b>&nbsp;${event.change_dept_name}</b></font></td>
	    	<td align="center">变更后部门 </td>
	    	<td><font color="blue"><b>&nbsp;${event.dept_after_name}</b></font></td>
		</tr>
		<tr>
	    	<td align="center">变更前岗位 </td>
	    	<td><font color="blue"><b>&nbsp;${event.post_before_name}</b></font></td>
	    	<td align="center">变更后岗位 </td>
	    	<td><font color="blue"><b>&nbsp;${event.post_after_name}</b></font></td>
		</tr>
		<tr>
	    	<td align="center">变更前涉密等级 </td>
	    	<td><font color="blue"><b>&nbsp;${event.seclv_before_name}</b></font></td>
	    	<td align="center">变更后涉密等级 </td>
	    	<td><font color="blue"><b>&nbsp;${event.seclv_after_name}</b></font></td>
		</tr>
	</c:if>
	<c:if test="${event.change_type == 'ADD'}">
		<tr>
			<td align="center">申请人 </td>
	    	<td><font color="blue"><b>&nbsp;${event.change_user_name}</b></font></td>
	    	<td align="center">申请人部门 </td>
	    	<td><font color="blue"><b>&nbsp;${event.change_dept_name}</b></font></td>
	    </tr>
		<tr>
	    	<td align="center">申请前涉密等级 </td>
	    	<td><font color="blue"><b>&nbsp;${event.seclv_before_name}</b></font></td>
	    	<td align="center">申请后涉密等级 </td>
	    	<td><font color="blue"><b>&nbsp;${event.seclv_after_name}</b></font></td>
		</tr>
		<tr>
	    	<td align="center">变更前岗位 </td>
	    	<td><font color="blue"><b>&nbsp;${event.post_before_name}</b></font></td>
	    	<td align="center">变更后岗位 </td>
	    	<td><font color="blue"><b>&nbsp;${event.post_after_name}</b></font></td>
		</tr>
	</c:if>
	<tr>  	
  		<td align="center">申请时间</td>
		<td><font color="blue"><b>&nbsp;${event.apply_time_str}</b></font></td>
		<td align="center">联系电话</td>
	    <td><font color="blue"><b>&nbsp;${event.contact_num}</b></font></td>
	</tr>
	<tr>  	
  		<td align="center">申请理由</td>
		<td colspan="3"><font color="blue"><b>&nbsp;${event.summ}</b></font></td>
	</tr>
	<c:if test="${event.change_type == 'ADD'}">
		<tr>
		<td colspan="4" align="center">
		<table width="100%" cellspacing="0" cellpadding="0" align="center" class="table_box">
			<tr><td colspan="8"><font color="gray"><b>&nbsp;&nbsp;&nbsp;申请人详细资料</b></font></td></tr>
		<tr>
			<td align="center">姓名</td>
			<td><font color="blue"><b>&nbsp;${userinfo.base_username}</b></font></td>
			<td align="center">性别</td>
		    <td><font color="blue"><b>&nbsp;${userinfo.base_sex_name}</b></font></td>
			<td align="center">国籍</td>
			<td><font color="blue"><b>&nbsp;${userinfo.base_country}</b></font></td>
			<td align="center">民族</td>
			<td><font color="blue"><b>&nbsp;${userinfo.base_nation}</b></font></td>
		</tr>
		<tr>
			<td align="center">籍贯</td>
			<td><font color="blue"><b>&nbsp;${userinfo.base_nativeplace}</b></font></td>
			<td align="center">出生日期</td>
			<td><font color="blue"><b>&nbsp;${userinfo.base_birthday}</b></font></td>
			<td align="center">政治面貌</td>
			<td><font color="blue"><b>&nbsp;${userinfo.base_politics_name}</b></font></td>
		   	<td align="center">&nbsp;婚姻状况 </td>
		   	<td><font color="blue"><b>&nbsp;${userinfo.marital_status_str}</b></font></td>	
		</tr>
		<tr>
			<td align="center">学历</td>
			<td><font color="blue"><b>&nbsp;${userinfo.edu_education_name}</b></font></td>
		   	<td align="center">身份证号</td>
		   	<td><font color="blue"><b>&nbsp;${userinfo.idcard}</b></font></td>
			<td align="center">电子邮箱</td>
			<td><font color="blue"><b>&nbsp;${userinfo.com_email}</b></font></td>
		    <td align="center">联系电话</td>
		    <td><font color="blue"><b>&nbsp;${userinfo.com_telephone}</b></font></td>	
		</tr>
		<tr>
			<td align="center">户籍所在地</td>
			<td colspan="3"><font color="blue"><b>&nbsp;${userinfo.com_residency}</b></font></td>
			<td align="center">现住址</td>
			<td colspan="3"><font color="blue"><b>&nbsp;${userinfo.com_address}</b></font></td>
		</tr>
		<tr>
		    <td align="center">职务</td>
		    <td><font color="blue"><b>&nbsp;${userinfo.job_techpost}</b></font></td>
			<td align="center">职称</td>
			<td><font color="blue"><b>&nbsp;${userinfo.job_techtitle}</b></font></td>
			<td align="center">参加工作时间</td>
			<td colspan="3"><font color="blue"><b>&nbsp;${userinfo.job_insectime}</b></font></td>
		</tr>
		<tr>
			<td align="center">涉密等级</td>
			<td><font color="blue"><b>&nbsp;${secUser.security_name}</b></font></td>
			<td align="center">岗位类别</td>
			<td colspan="5"><font color="blue"><b>&nbsp;${userinfo.bmpost_name}</b></font></td>
			<%-- <td align="center">&nbsp;涉密类别</td>
			<td colspan="3"><font color="blue"><b>&nbsp;${userinfo.sec_category}</b></font></td> --%>
		</tr>
		<tr>
		    <td align="center">因私出国（境）情况</td>
		    <td><font color="blue"><b>&nbsp;${userinfo.is_abroad_name}</b></font></td>
			<td align="center">他国绿卡或永久居留证</td>
			<td><font color="blue"><b>&nbsp;${userinfo.resident_card}</b></font></td>
			<td align="center">本人海外经历</td>
			<td><font color="blue"><b>&nbsp;${userinfo.oversea_name}</b></font></td>
			<td align="center">配偶子女海外经历</td>
			<td><font color="blue"><b>&nbsp;${userinfo.famliy_oversea_name}</b></font></td>	
		</tr>
	<c:if test="${userinfo.is_abroad == '1'}">
		<tr>
		    <td align="center">最近两次出国情况</td>
		    <td colspan="6">
				<table width="100%" border="0" cellspacing="0" cellpadding="0" align="left"  class="table_box_bottom_border">
		    		<tr>
						<td align="center" width="20%"><font color="blue"><b>&nbsp;出国时间段</b></font></td>
						<td align="center" width="10%"><font color="blue"><b>&nbsp;出国地点</b></font></td>
						<td align="center" width="15%"><font color="blue"><b>&nbsp;出国事由</b></font></td>
						<td align="center" width="15%"><font color="blue"><b>&nbsp;出境海关</b></font></td>
						<td align="center" width="15%"><font color="blue"><b>&nbsp;入境海关</b></font></td>
						<td align="center" width="15%"><font color="blue"><b>&nbsp;陪同人员</b></font></td>
					</tr>
					<s:iterator value="#request.abroadinfo" var="itemm">
		  				<tr>
		  					<td align="center" width="20%"><font color="blue"><b>&nbsp;${itemm.abroad_time}至${itemm.back_time}</b></font></td>
		  					<td align="center" width="10%"><font color="blue"><b>&nbsp;${itemm.abroad_place}</b></font></td>
		  					<td align="center" width="15%"><font color="blue"><b>&nbsp;${itemm.abroad_reason}</b></font></td>
		  					<td align="center" width="15%"><font color="blue"><b>&nbsp;${itemm.out_customs}</b></font></td>
		  					<td align="center" width="15%"><font color="blue"><b>&nbsp;${itemm.in_customs}</b></font></td>
		  					<td align="center" width="15%"><font color="blue"><b>&nbsp;${itemm.abroad_content}</b></font></td>
		  				</tr>
		  			</s:iterator>
				</table>
			</td>
			<td>
				<table width="100%" border="0" cellspacing="0" cellpadding="0" align="left"  class="table_box_bottom_border">
		    		<tr>
						<td align="center" width="20%"><font color="blue"><b>&nbsp;在外情况汇报</b></font></td>
					</tr>
					<s:iterator value="#request.burnFileList" var="burnFile">
		  				<tr>
		  					<td align="center"><u><label style="color: blue;cursor: pointer;" onclick="downloadFile('${burnFile.file_path}','${burnFile.file_name}');">${burnFile.file_name}</label></u></td>	  		
		  				</tr>
					</s:iterator>
				</table>		
			</td>
		</tr>
	</c:if>
		<tr>
			<td align="center">个人简历</td>
			<td colspan="7">
				<font color="blue"><b>&nbsp;
		    		<table border rules=all width="90%" border="0" cellspacing="0" cellpadding="0" align="left"  class="table_box_bottom_border">
		    		<tr>
						<td align="center" width="15%"><font color="blue"><b>&nbsp;开始时间</b></font></td>
						<td align="center" width="15%"><font color="blue"><b>&nbsp;结束时间</b></font></td>
						<td align="center" width="55%"><font color="blue"><b>&nbsp;工作/学习经历</b></font></td>
					</tr>		
			  			<s:iterator value="#request.experienceinfo" var="itemmm">
			  				<tr>
			  					<td align="center"><font color="blue"><b>&nbsp;${itemmm.experience_time}</b></font></td>
			  					<td align="center"><font color="blue"><b>&nbsp;${itemmm.end_time}</b></font></td>
			  					<td align="center"><font color="blue"><b>&nbsp;${itemmm.experience_content}</b></font></td>
			  				</tr>
						</s:iterator>
		  			</table>
				</b></font>
			</td>
		</tr>
		<tr>
			<td align="center">家庭成员信息</td>
			<td colspan="7">
				<font color="blue"><b>&nbsp;
		    		<table border rules=all width="90%" border="0" cellspacing="0" cellpadding="0" align="left"  class="table_box_bottom_border">
		    		<tr>
						<td align="center" width="10%"><font color="blue"><b>&nbsp;与本人关系</b></font></td>
						<td align="center" width="10%"><font color="blue"><b>&nbsp;姓名</b></font></td>
						<td align="center" width="10%"><font color="blue"><b>&nbsp;国籍</b></font></td>
						<td align="center" width="10%"><font color="blue"><b>&nbsp;出生年月</b></font></td>
						<td align="center" width="10%"><font color="blue"><b>&nbsp;政治面貌</b></font></td>
						<td align="center" width="15%"><font color="blue"><b>&nbsp;工作地点</b></font></td>
						<td align="center" width="15%"><font color="blue"><b>&nbsp;现居住地</b></font></td>		
						<td align="center" width="20%"><font color="blue"><b>&nbsp;有无绿卡或永久居住证</b></font></td>			
					</tr>		
			  			<s:iterator value="#request.familyinfo" var="ite">
			  				<tr>
			  					<td align="center"><font color="blue"><b>&nbsp;${ite.family_relationship}</b></font></td>
			  					<td align="center"><font color="blue"><b>&nbsp;${ite.family_name}</b></font></td>
			  					<td align="center"><font color="blue"><b>&nbsp;${ite.family_nationality}</b></font></td>
			  					<td align="center"><font color="blue"><b>&nbsp;${ite.family_borndate}</b></font></td>
			  					<td align="center"><font color="blue"><b>&nbsp;${ite.family_politicalstatus}</b></font></td>
			  					<td align="center"><font color="blue"><b>&nbsp;${ite.family_workplace}</b></font></td>
			  					<td align="center"><font color="blue"><b>&nbsp;${ite.family_lifeplace}</b></font></td>
			  					<td align="center"><font color="blue"><b>&nbsp;${ite.family_greencard}</b></font></td>
			  				</tr>
						</s:iterator>
		  			</table>
				</b></font>
			</td>
		</tr>
		</table>
	</td>
	</tr>
</c:if>
	<!-- <tr>
    	<td align="center">操作人 </td>
    	<td><font color="blue"><b>&nbsp;${event.oper_user_name}</b></font></td>
    	<td align="center">操作人部门 </td>
    	<td><font color="blue"><b>&nbsp;${event.oper_dept_name}</b></font></td>
	</tr> -->
	<c:if test="${history eq 'Y'}">
		<tr>
	  		<td align="center">下一步骤审批人</td>
	  		<td colspan="5"><font color="blue"><b>&nbsp;${job.next_approver_name}</b></font></td>
	  	</tr>
  	</c:if>
  	<c:if test="${event.change_type == 'ADD'}">
	  	<tr>
			<td align="center">部门初审意见</td>
			<td colspan="3" id="step1">
				<table width="95%" border="0" cellspacing="0" cellpadding="0" align="left">
					<c:if test="${history != 'Y'}">
						<tr>
							<td align="left">情况是否属实
								<input type="radio" id="a_chk" name="a_chk" value="yes" />是
								<input type="radio" name="a_chk" value="no" />否
							</td>
						</tr>
						<tr>
							<td align="left">拟安排的工作岗位
								<input type="text" id="post_station" name="post_station" />
							</td>
						</tr>
						<tr>
							<td align="left">涉密等级
								<input type="radio" id="c_chk" name="c_chk" value="yes" />秘密
								<input type="radio" name="c_chk" value="no" />机密
							</td>
						</tr>
					</c:if>
					<tr><td colspan="3"><textarea id="opinion1" rows="4" cols="100" readonly="readonly"></textarea></td></tr>
					<tr><td id="hidden1"></td></tr>
				</table>
			</td>
		</tr>
		<tr>
			<td align="center">人力资源部审查意见</td>
			<td colspan="3" id="step2">
				<table width="99%" border="0" cellspacing="0" cellpadding="0" align="left">
					<c:if test="${history != 'Y'}">
					<tr>
						<td align="left">个人经历审查情况
						<input type="radio" id="p_chk" name="p_chk" value="yes" />符合
						<input type="radio" name="p_chk" value="no" />不符合
						</td>
					</tr>
					<tr>
						<td align="left">政治表现审查情况
						<input type="radio" id="s_chk" name="s_chk" value="yes" />符合
						<input type="radio" name="s_chk" value="no"/>不符合
						</td>
					</tr>
					<tr>
						<td align="left">品行表现审查情况
						<input type="radio" id="b_chk" name="b_chk" value="yes"/>符合
						<input type="radio" name="b_chk" value="no"/>不符合
						</td>
					</tr>
					<tr>
						<td align="left">与境外人员关系审查情况
						<input type="radio" id="f_chk" name="f_chk" value="yes" />符合
						<input type="radio" name="f_chk" value="no"/>不符合
						</td>
					</tr>
					<tr>
						<td align="left">调查及谈话等了解的情况
						<input type="radio" id="o_chk" name="o_chk" value="yes" />符合
						<input type="radio" name="o_chk" value="no"/>不符合
						</td>
					</tr>
					<tr>
						<td align="left">请填写审查最终结果：是否符合重要/一般涉密人员要求</br>
<%-- 						<c:if test="${event.seclv_after_name == '重要涉密人员'}">
						<input type="radio" id="ez_chk" name="ez_chk" value="yes" />符合重要涉密人员要求
						<input type="radio" name="ez_chk" value="no"/>不符合重要涉密人员要求</br>
						</c:if>
						<c:if test="${event.seclv_after_name != '重要涉密人员'}">
						<input type="radio" id="ey_chk" name="ey_chk" value="yes" />符合一般涉密人员要求
						<input type="radio" name="ey_chk" value="no"/>不符合一般涉密人员要求</br>
						</c:if> --%>
						<!-- <textarea id="opinion2" rows="4" cols="100" readonly="readonly"></textarea> -->
						</td>
					</tr>
					</c:if>
					<tr><td colspan="3"><textarea id="opinion2" rows="4" cols="100" readonly="readonly"></textarea></td></tr>
					<tr><td id="hidden2"></td></tr>
				</table>
			</td>
		</tr>
		<tr>
			<td align="center">保密处审核意见</td>
			<td colspan="3" id="step3">
				<table width="99%" border="0" cellspacing="0" cellpadding="0" align="left">
					<tr><td><textarea id="opinion3" rows="4" cols="100" readonly="readonly"></textarea></td></tr>
					<tr><td id="hidden3"></td></tr>
			</table>
			</td>
		</tr>
		<tr>
			<td align="center">保密委员会审批意见</td>
			<td colspan="3" id="step4">
				<table width="99%" border="0" cellspacing="0" cellpadding="0" align="left">
					<tr><td><textarea id="opinion4" rows="4" cols="100" readonly="readonly"></textarea></td></tr>
					<tr><td id="hidden4"></td></tr>
				</table>
		</tr>
		<tr>
			<td align="center">保密处办理</td>
			<td colspan="3" id="step5">
			<table width="99%" border="0" cellspacing="0" cellpadding="0" align="left">
					<tr><td><textarea id="opinion5" rows="4" cols="100" readonly="readonly">通知人力资源部变更保密补贴，人资部办理人员：       （填写人员名称)
考试成绩  </textarea></td></tr>
					<tr><td id="hidden5"></td></tr>
			</table>
			</td>
		</tr>
		<tr>
			<td align="center">航盾系统权限变更</td>
			<td colspan="3" id="step6">
				<table width="99%" border="0" cellspacing="0" cellpadding="0" align="left">
					<tr><td><textarea id="opinion6"rows="4" cols="100" readonly="readonly"></textarea></td></tr>
					<tr><td id="hidden6"></td></tr>
				</table>
			</td>
		</tr>
		<tr>
			<td align="center">应用系统权限变更</td>
			<td colspan="3" id="step7">
				<table width="99%" border="0" cellspacing="0" cellpadding="0" align="left">
					<tr><td><textarea id="opinion7" rows="4" cols="100" readonly="readonly"></textarea></td></tr>
					<tr><td id="hidden7"></td></tr>
				</table>
			</td>
		</tr>
	</c:if>
	<c:if test="${event.change_type == 'CHANGE'}">
		<tr>
			<td align="center">部门初审意见</td>
			<td colspan="3" id="step1">
				<table width="99%" border="0" cellspacing="0" cellpadding="0" align="left">
					<tr><td><textarea id="opinion1" rows="4" cols="100" readonly="readonly"></textarea></td></tr>
					<tr><td id="hidden1"></td></tr>
			</table>
			</td>
		</tr>
		<tr>
			<td align="center">人力资源部审查意见</td>
			<td colspan="3" id="step2">
				<table width="99%" border="0" cellspacing="0" cellpadding="0" align="left">
					<tr><td><textarea id="opinion2" rows="4" cols="100" readonly="readonly"></textarea></td></tr>
					<tr><td id="hidden2"></td></tr>
				</table>
			</td>
		</tr>
		<tr>
			<td align="center">保密处审核意见</td>
			<td colspan="3" id="step3">
				<table width="99%" border="0" cellspacing="0" cellpadding="0" align="left">
					<tr><td><textarea id="opinion3" rows="4" cols="100" readonly="readonly"></textarea></td></tr>
					<tr><td id="hidden3"></td></tr>
			</table>
			</td>
		</tr>
		<tr>
			<td align="center">保密委员会审批意见</td>
			<td colspan="3" id="step4">
				<table width="99%" border="0" cellspacing="0" cellpadding="0" align="left">
					<tr><td><textarea id="opinion4" rows="4" cols="100" readonly="readonly"></textarea></td></tr>
					<tr><td id="hidden4"></td></tr>
				</table>
		</tr>
		<tr>
			<td align="center">保密处办理</td>
			<td colspan="3" id="step5">
				<table width="99%" border="0" cellspacing="0" cellpadding="0" align="left">
					<tr><td><textarea id="opinion5"rows="4" cols="100" readonly="readonly">通知人力资源部变更保密补贴，人资部办理人员：       （填写人员名称)
考试成绩  </textarea></td></tr>
					<tr><td id="hidden5"></td></tr>
				</table>
			</td>
		</tr>
		<tr>
			<td align="center">应用系统权限变更</td>
			<td colspan="3" id="step6">
				<table width="99%" border="0" cellspacing="0" cellpadding="0" align="left">
					<tr><td><textarea id="opinion6" rows="4" cols="100" readonly="readonly"></textarea></td></tr>
					<tr><td id="hidden6"></td></tr>
				</table>
			</td>
		</tr>
	</c:if>
  	
	
	<c:if test="${history != 'Y'}">
	<tr>
  		<td align="center" id="selApprover1">选择下级审批人</td>
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
  	</c:if>
  	<tr class="Noprint">
	    <td colspan="4" align="center">
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
