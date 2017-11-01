<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<table width="100%" cellspacing="0" cellpadding="0" align="center" class="table_box">
	<tr>
		<td align="center"><font color="red">*</font>变更内容</td>
		<td align="center">变更前</td>
		<td align="center">变更后</td>
	</tr>
	<tr>
		<td align="center"><input type="checkbox">部门</td>
		<td align="center">&nbsp;${event.change_dept_name}</td>
		<td align="center"><input type="text" id="dept_name" value="${burner.dept_name}" readonly="readonly" style="cursor:hand" onclick="openDeptSelect('dept_name','dept_id_after','radio')" />
			<input type="hidden" name="dept_id_after" id="dept_id_after" value="${burner.dept_id}"/>
		</td>
	</tr>
	<tr>
		<td align="center"><input type="checkbox">岗位</td>
		<td align="center">&nbsp;${event.post_before_name}</td>
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
		<td align="center">&nbsp;${event.seclv_before_name}</td>
		<td align="center"><select name="seclv_code_after" id="seclv_code_after">
				<option value="">--请选择--</option>
					<s:iterator value="#request.securityList" var="security_after">
						<option value="${security_after.security_code}">${security_after.security_name}</option>
					</s:iterator>
			</select>
		</td>
	</tr>
</table>