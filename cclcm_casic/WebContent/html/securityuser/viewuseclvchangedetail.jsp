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
 	<script language="javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>	
 	<script language="javascript" src="${ctx}/_script/casic304_ajax.js"></script>
 	<script language="JavaScript" src="${ctx}/_script/common.js"></script>
 	<script language="javascript" src="${ctx}/_script/casic304_common_bm.js"></script>
<script>
<!--
$(document).ready(function(){
	if('${event.job_status}' != ""){
		prepareBizApproval("${process.step_dept}","${process.step_role}","${process.step_dept_name}","${process.step_role_name}","${ctx}");
	}
	var type = "readonly";
	viewOpinion(type);//判断审批到哪一步
});
	
//-->
</script>
</head>
<body oncontextmenu="self.event.returnValue=false">
<table width="95%" border="1" cellspacing="0" cellpadding="0" align="center" class="table_box">
<input type="hidden" id="listSize" name="listSize" value="${listSize}"/> 
<input type="hidden" id="opinion_all" name="opinion_all" value="${opinion_all}"/> 
<input type="hidden" id="type" name="type" value="${type}"/>
<form>
	<tr>
	<c:if test="${type == 'NEW'}">
		<td colspan="4" class="title_box">新增涉密人员审批表</td>
	</c:if>
	<c:if test="${type != 'NEW'}">
		<td colspan="4" class="title_box">涉密人员变更审批表</td>
	</c:if>   
	</tr>
	<tr> 
    	<td width="10%" align="center">申请用户 </td>
    	<td width="23%"><font color="blue"><b>${job.user_name}</b></font></td>
    	<td width="10%" align="center">用户部门 </td>
    	<td width="23%"><font color="blue"><b>${job.dept_name}</b></font></td>
    	
	</tr>
	<tr>
		<td align="center">申请状态</td>
    	<td><font color="blue"><b>${job.job_status_name}</b></font></td> 
    	<td align="center">联系电话</td>
	    <td><font color="blue"><b>&nbsp;${event.contact_num}</b></font></td>
	</tr>
  	<tr>
    	<td align="center">变更人 </td>
    	<td><font color="blue"><b>&nbsp;${event.change_user_name}</b></font></td>
    	<td align="center">变更人部门 </td>
    	<td><font color="blue"><b>&nbsp;${event.change_dept_name}</b></font></td>
	</tr>
	<c:if test="${type != 'NEW'}">
		<tr>
	    	<td align="center">变更前部门</td>
	    	<td><font color="blue"><b>&nbsp;${event.change_dept_name}</b></font></td>
	    	<td align="center">变更后部门</td>
	    	<td><font color="blue"><b>&nbsp;${event.dept_after_name}</b></font></td>
		</tr>
		<tr>
	    	<td align="center">变更前岗位</td>
	    	<td><font color="blue"><b>&nbsp;${event.post_before_name}</b></font></td>
	    	<td align="center">变更后岗位 </td>
	    	<td><font color="blue"><b>&nbsp;${event.post_name_after}</b></font></td>
		</tr>
	</c:if>  
	<tr>
    	<td align="center">变更前涉密等级 </td>
    	<td><font color="blue"><b>&nbsp;${event.seclv_before_name}</b></font></td>
    	<td align="center">变更后涉密等级</td>
    	<td><font color="blue"><b>&nbsp;${event.seclv_after_name}</b></font></td>
	</tr>
	<tr>
    	<td align="center">变更状态</td>
    	<td><font color="blue"><b>&nbsp;${event.change_status_name}</b></font></td>
    	<td align="center">变更时间</td>
    	<td><font color="blue"><b>&nbsp;${event.change_time}</b></font></td>
	</tr>
	<tr> 
    	 <td align="center">业务类型</td>
	    <td><font color="blue"><b>&nbsp;${event.change_type_name}</b></font></td>
  		<td align="center">变更理由</td>
		<td><font color="blue"><b>&nbsp;${event.summ}</b></font></td>
	</tr>
	<!--
	<tr>
    	<td align="center">操作人： </td>
    	<td><font color="blue"><b>&nbsp;${event.oper_user_name}</b></font></td>
    	<td align="center">操作人部门： </td>
    	<td><font color="blue"><b>&nbsp;${event.oper_dept_name}</b></font></td>
	</tr>
	 -->
 	<c:if test="${type == 'NEW'}">
	  	<tr>
			<td align="center">部门初审意见</td>
			<td colspan="3" id="step1">
				<table width="95%" border="0" cellspacing="0" cellpadding="0" align="left">
					<tr>
						<td align="left">情况是否属实
							<input type="radio" name="a_chk" value="yes" />是
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
							<input type="radio" name="c_chk" value="yes" />秘密
							<input type="radio" name="c_chk" value="no" />机密
						</td>
					</tr>
					<tr><td id="hidden1"></td></tr>
			</table>
		</tr>
		<tr>
			<td align="center">人力资源部审查意见</td>
			<td colspan="3" id="step2">
				<table width="99%" border="0" cellspacing="0" cellpadding="0" align="left">
					<tr>
						<td align="left">个人经历审查情况
						<input type="radio" name="p_chk" value="yes" />符合
						<input type="radio" name="p_chk" value="no" />不符合
						</td>
					</tr>
					<tr>
						<td align="left">政治表现审查情况
						<input type="radio" name="s_chk" value="yes"/>符合
						<input type="radio" name="s_chk" value="no"/>不符合
						</td>
					</tr>
					<tr>
						<td align="left">品行表现审查情况
						<input type="radio" name="b_chk" value="yes"/>符合
						<input type="radio" name="b_chk" value="no"/>不符合
						</td>
					</tr>
					<tr>
						<td align="left">与境外人员关系审查情况
						<input type="radio" name="f_chk" value="yes"/>符合
						<input type="radio" name="f_chk" value="no"/>不符合
						</td>
					</tr>
					<tr>
						<td align="left">调查及谈话等了解的情况
						<input type="radio" name="o_chk" value="yes"/>符合
						<input type="radio" name="o_chk" value="no"/>不符合
						</td>
					</tr>
					<tr>
				
						<td align="left">审查最终结果：是否符合重要/一般涉密人员要求</br>
						<input type="radio" id="ez_chk" name="ez_chk" value="yes" />符合重要涉密人员要求
						<input type="radio" name="ez_chk" value="no"/>不符合重要涉密人员要求</br>
						
						<input type="radio" id="ey_chk" name="ey_chk" value="yes" />符合一般涉密人员要求
						<input type="radio" name="ey_chk" value="no"/>不符合一般涉密人员要求</br>
						<textarea id="opinion2" rows="4" cols="100" readonly="readonly"></textarea>
						</td>
					
					</tr>
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
					<tr><td><textarea id="opinion5" rows="4" cols="100" readonly="readonly">通知人力资源部变更保密补贴，人资部办理人员：          （填写人员名称）
考试成绩：  </textarea></td></tr>
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
	<c:if test="${type != 'NEW'}">
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
			<td align="center">航盾系统权限变更</td>
			<td colspan="3" id="step5">
				<table width="99%" border="0" cellspacing="0" cellpadding="0" align="left">
					<tr><td><textarea id="opinion5"rows="4" cols="100" readonly="readonly"></textarea></td></tr>
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
  	
	 	<tr>
    <td colspan="4" align=center> 
		<input class="button_2003" type="button" value="返回" onClick="javascript:history.go(-1);">&nbsp;
    </td>
  </tr>
 </form>
</table>
</body>
</html>
