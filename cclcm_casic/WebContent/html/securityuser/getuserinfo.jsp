<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<c:if test="${func != 'CHANGE'}">
<table width="100%" cellspacing="0" cellpadding="0" align="center" class="table_box">
	<tr>
 		<td width="10%" align="center">姓名</td>
		<td width="16%"><font color="blue"><b>&nbsp;${bmUser.base_username}</b></font></td>
		<td width="10%" align="center">性别</td>
    	<td width="16%"><font color="blue"><b>&nbsp;${bmUser.base_sex_name}</b></font></td>	    	
		<td width="10%" align="center">政治面貌</td>
		<td width="16%"><font color="blue"><b>&nbsp;${bmUser.base_politics_name}</b></font></td>
		<td width="10%" align="center">学历</td>
		<td width="18%"><font color="blue"><b>&nbsp;${bmUser.edu_education_name}</b></font></td>
	</tr>
	<tr>
		<td align="center">涉密等级</td>
		<td><font color="blue"><b>&nbsp;${secUser.security_name}</b></font></td>
	    <td align="center">联系电话</td>
	    <td><font color="blue"><b>&nbsp;${bmUser.com_telephone}</b></font></td>
	    <td align="center">职务</td>
	    <td><font color="blue"><b>&nbsp;${bmUser.job_techpost}</b></font></td>
		<td align="center">参加工作时间</td>
		<td><font color="blue"><b>&nbsp;${bmUser.job_insectime}</b></font></td>
	</tr>	
</table>
</c:if>
<c:if test="${func == 'CHANGE'}">
<table width="90%" cellspacing="0" cellpadding="0" align="center" class="table_box">
	<tr>
		<td align="center"><font color="red">*</font>变更内容</td>
		<td align="center">变更前</td>
		<td align="center">变更后</td>
	</tr>
	<tr>
		<td align="center"><input type="checkbox">部门</td>
		<td align="center">&nbsp;${secUser.dept_name}</td>
		<td align="center"><input type="text" id="dept_name" value="${burner.dept_name}" readonly="readonly" style="cursor:hand" onclick="openDeptSelect('dept_name','dept_id_after','radio')" />
			<input type="hidden" name="dept_id_after" id="dept_id_after" value="${burner.dept_id}"/>
		</td>
	</tr>
	<tr>
		<td align="center"><input type="checkbox">岗位</td>
		<td align="center">&nbsp;${secUser.post_name}</td>
		<td align="center">
		<select name=post_id_after id="post_id_after">
				<option value="">--请选择--</option>
					<s:iterator value="#request.postList" var="post_after">
						<option value="${post_after.post_id}">${post_after.post_name}</option>
					</s:iterator>
		</select>
		</td> 	
	</tr>
	<tr>
		<td align="center"><input type="checkbox">涉密等级</td>
		<td align="center">&nbsp;${secUser.security_name}</td>
		<td align="center"><select name="seclv_code_after" id="seclv_code_after">
				<option value="">--请选择--</option>
					<s:iterator value="#request.securityList" var="security_after">
						<option value="${security_after.security_code}">${security_after.security_name}</option>
					</s:iterator>
			</select>
		</td>
	</tr>
</table>
</c:if>
