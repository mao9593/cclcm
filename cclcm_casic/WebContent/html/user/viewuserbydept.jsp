<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<title>用户管理</title>
	<link rel="stylesheet" href="${ctx}/_css/displaytag.css" type="text/css" media="screen" />
    <link rel="stylesheet" href="${ctx}/_css/css200603.css" type="text/css" media="screen" />
    <script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
    <script language="javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>
	<script language="javascript">
		$(document).ready(function(){
			onHover();
			optionSelect();
			if("${admin_version}" == "0" || ("${admin_version}" == "1" && "${display_flag}" == "SEC")){addSelectAllCheckbox();}
			//alert("${display_flag}"+"--"+"${admin_version}");
		});
		function setSelectAllStatus(tag){
			if(tag.checked){
				$(":checkbox[name!='isAllDept']").attr("checked",true);
			}else{
				$(":checkbox[name!='isAllDept']").attr("checked",false);
			}
		}
		function optionSelect(){
			$("#security_code").val("${security_code}");
		}
		function clearFindForm(){
			SecUserQueryCondForm.user_iidd.value = "";
			SecUserQueryCondForm.user_name.value = "";
			SecUserQueryCondForm.status.value = "";
			SecUserQueryCondForm.del_status.value = "";
			SecUserQueryCondForm.role_id.value = "";
			SecUserQueryCondForm.security_code.value = "";
			SecUserQueryCondForm.pass_num.value = "";
		}
		var xmlHttp = false;
		try {
		  xmlHttp = new ActiveXObject("Msxml2.XMLHTTP");
		} catch (e) {
		  try {
		    xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
		  } catch (e2) {
		    xmlHttp = false;
		  }
		}
		if (!xmlHttp && typeof XMLHttpRequest != 'undefined') {
		  xmlHttp = new XMLHttpRequest();
		}
		function setStatus(user_iidds,status){
  			var url = "${ctx}/user/setuserstatus.action?user_iidds=" +escape(user_iidds)+"&status="+status;
  			xmlHttp.open("GET", url, true);
  			xmlHttp.onreadystatechange = updatePage;
  			xmlHttp.send(null);
		}
		function delSecUser(user_iidd){
  			var url = "${ctx}/user/delsecuser.action?user_iidd="+user_iidd;
  			xmlHttp.open("GET", url, true);
  			xmlHttp.onreadystatechange = updatePage;
  			xmlHttp.send(null);
		}
		function recoverSecUser(user_iidd){
  			var url = "${ctx}/user/recoversecuser.action?user_iidd="+user_iidd;
  			xmlHttp.open("GET", url, true);
  			xmlHttp.onreadystatechange = updatePage;
  			xmlHttp.send(null);
		}
		function multiunlock(){
			var user_iidds = "";
			if($(":checkbox:checked[value!='']").size()>0){
				$(":checkbox:checked[value!='']").each(function (){
					user_iidds += this.value+"#";
				});
				setStatus(user_iidds,0)
			}else{
				alert("请先勾选需批量激活的用户");
				return false;
			}
		}
		function updatePage() {
		  if (xmlHttp.readyState < 4) {
			document.getElementById("result").innerHTML="操作中...";
		  }
		  if (xmlHttp.readyState == 4) {
		    var response = xmlHttp.responseText;
		    alert(response);
		    SecUserQueryCondForm.submit();
		  }
		}
		function updateUserClick(user_iidd){
			SecUserQueryCondForm.action="${ctx}/user/updatesecuser.action";
			SecUserQueryCondForm.userId.value = user_iidd;
			SecUserQueryCondForm.submit();
		}
		function configDeptAdmin(user_iidd){
			var url = "${ctx}/user/configdeptadmin.action?user_iidd="+user_iidd;
			window.open(url, "_blank", "width=700,height=260,left="+((window.screen.width/2)-350)+",top="+((window.screen.height/2)-150)+",location=no,menubar=no,resizable=no,status=no,titlebar=no,toolbar=no,scrollbars=no");
		}
		/**
 		* 弹出清空密码结果的对话框
 		*/
		function showDialogModifyPW(userID){
			if(confirm("确定重置该用户的密码？")){
				mp.location.href = "${ctx}/user/resetuserpwd.action?user_iidd=" + userID;
			}
		}
	</script>
</head>
<body oncontextmenu="self.event.returnValue=false">
<iframe id="mp" width="0" height="0" ></iframe>
<table border="0" cellspacing="0" cellpadding="0" class="table_only_border" width="95%" height="100%" align="center">
	<tr>
		<td colspan="2" class="title_box">
			<c:if test="${allDept=='Y'}">
				全范围
			</c:if>
			<c:if test="${allDept=='N'}">
				${dept_name}
			</c:if>
			用户管理
		</td>
	</tr>
	<form method="post" action="${ctx}/user/viewuserbydept.action" name="SecUserQueryCondForm" id="SecUserQueryCondForm">
	<tr height="20"><td>
	<table border=0 class="table_box" cellspacing="0" cellpadding="0" width="100%">
	<input type="hidden" name="dept_id" value="${dept_id}"/>
	<input type="hidden" name="userId" value=""/>
	<tr>
		<td width="33%"><label style="width:80px">登&nbsp;&nbsp;录&nbsp;&nbsp;名：</label>
			<input name="user_iidd"  style="width:55%" value="${user_iidd}"/>
		</td>
		<td width="33%" colspan="1"><label style="width:80px">用&nbsp;&nbsp;户&nbsp;&nbsp;名：</label>
			<input name="user_name"  size="10" style="width:60%" value="${user_name}"/>
		</td>
		<td width="33%"><label>涉密人员类别：</label>
	        <select name="security_code" id="security_code" style="width:55%">
		        <option value="">--所有--</option>
	            <s:iterator value="#request.securityList" var="security">
					<option value="${security.security_code}">${security.security_name}</option>
				</s:iterator>
	        </select>
		</td>
	</tr>
	<tr>
		<td width="33%"><label style="width:80px">角&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;色：</label>
	        <c:set var="roleId1" value="${role_id}" scope="request"/>
	        <select name="role_id" style="width:55%">
		        <option value="">--所有--</option>
	            <s:iterator value="#request.secRoleList" var="role">
					<c:set var="roleId2" value="${role.role_id}" scope="request"/>
					<option value="${role.role_id}" <s:if test="#request.roleId2==#request.roleId1">selected="selected"</s:if> >${role.role_name}</option>
				</s:iterator>
	        </select>
		</td>
		<td width="33%"><label style="width:80px">锁定状态：</label>
            <select name="status">
                <option value="">--所有--</option>
				<option value="0" <c:if test="${status == '0'}">selected="selected"</c:if>>正常</option>
				<option value="1" <c:if test="${status == '1'}">selected="selected"</c:if>>锁定</option>
            </select>
        </td>
		<td width="33%"> <label style="width:80px">删&nbsp;&nbsp;&nbsp;除&nbsp;&nbsp;&nbsp;状&nbsp;&nbsp;态：</label>
            <select name="del_status">
                <option value="">--所有--</option>
				<option value="N" <c:if test="${del_status == 'N'}">selected="selected"</c:if>>未删除</option>
				<option value="Y" <c:if test="${del_status == 'Y'}">selected="selected"</c:if>>已删除</option>
            </select>
        </td>
	</tr>
    <tr>
    	<td><label style="width:80px">用户卡号：</label>
			<input name="pass_num"  style="width:55%" value="${pass_num}"/>
		</td>
		<!-- 系统管理员 -->
		<c:if test="${admin_version == 0 or (admin_version == 1 and display_flag == 'SYS')}">
	        <td width="20%"><label style="width:80px">添加用户：</label>
	        	<c:if test="${dept_id == ''}">
					<input type="button" value="先在左侧选部门" class="button_2003" disabled="true" />&nbsp;&nbsp;
				</c:if>
				<c:if test="${dept_id != ''}">
					<input type="button" value="新增用户" class="button_2003" onclick="this.disabled=true;location.href='${ctx}/user/addsecuser.action?dept_id=${dept_id}'"/>
				</c:if>
			</td>
		</c:if>
        <td>
        	<input type="checkbox" name="isAllDept" <c:if test="${allDept=='Y'}">checked</c:if> />全范围查询&nbsp;&nbsp;
			<input type="submit" class="button_2003" value="查询" />&nbsp;
	        <input type="button" value="清空" class="button_2003" onClick="clearFindForm()" />
        </td>
    </tr>
</table>
		</td>
	</tr>
	<tr>
		<td valign="top">
<table class="displaytable-outter" cellspacing=0 cellpadding=0 border=0 width="150%">
    <tr>
      <td>
           <display:table requestURI="${ctx}/user/viewuserbydept.action" id="user" name="secUserList" class="displaytable" pagesize="${pageSize}" sort="page" 
					partialList="true" size="${totalSize}" excludedParams="*" form="SecUserQueryCondForm">
				<!-- 安全管理员 -->
				<c:if test="${admin_version == 0 or (admin_version == 1 and display_flag == 'SEC')}">
					<display:column title="选择">
						<input type="checkbox" value="${user.user_iidd}" name="user_iidds"/>
					</display:column>
				</c:if>
                <display:column title="登录名称" sortable="true"  >
	                <c:if test="${user.status == 1}">
						<img src="${ctx}/_image/iconx32/stop1.gif" />
					</c:if>
						<c:out value="${user.user_iidd}"/>
				</display:column>
                <display:column title="用户名称" property="user_name" />
                <display:column title="部门" property="dept_name" />
	            <display:column title="角色" property="roleNameStr" maxLength="10"/>
	            <display:column title="涉密人员类别" property="security_name"/>
	            <display:column title="用户卡号" property="pass_num" sortable="true" maxLength="10"/>
                <display:column title="操作" sortable="true" style="width:310px">
				<!-- 普通未被删除的用户 -->
				<!-- 安全管理员 -->
				<c:if test="${admin_version == 0 or (admin_version == 1 and display_flag == 'SEC')}">
					<c:choose>
						<c:when test="${user.adminIsNull=='admin'}">
						<input type="button" class="button_2003" value="重置密码" disabled="disabled"/>&nbsp;
						</c:when>
						<c:otherwise>
						<input type="button" class="button_2003" value="重置密码" onClick="showDialogModifyPW('${user.user_iidd}');"/>&nbsp;
						</c:otherwise>
					</c:choose>
					<c:choose>
                		<c:when test="${user.status == 0}">
							<input type="button" value="禁用" class="button_2003" onclick="setStatus('<c:out value='${user.user_iidd}'/>',1)"/>
                		</c:when>
                		<c:when test="${user.status == 1}">
							<input type="button" value="激活" style="color:red" class="button_2003" onclick="setStatus('<c:out value='${user.user_iidd}'/>',0)"/>
                		</c:when>
                		<c:otherwise>
                		</c:otherwise>
	                </c:choose>&nbsp;
					<c:if test="${user.deletable == 'Y' and user.del_status == 'N'}">
						<c:choose>
							<c:when test="${user.canConfigDept == 'Y'}">
								<input type="button" value="配置部门" class="button_2003" onclick="go('${ctx}/basic/configdeptforuser.action?user_iidd=${user.user_iidd}');"/>
							</c:when>
							<c:otherwise>
								<input type="button" value="配置部门" class="button_2003" disabled="disabled"/>
	                		</c:otherwise>
						</c:choose>
						&nbsp;<input type="button" value="修改" class="button_2003" onclick="updateUserClick('${user.user_iidd}');">&nbsp;
					</c:if>
					<!-- 已删除用户 -->
					<c:if test="${user.deletable == 'Y' and user.del_status == 'Y'}">
						<input type="button" value="配置部门" class="button_2003" disabled="disabled"/>
						&nbsp;<input type="button" value="修改" class="button_2003" disabled="disabled"/>&nbsp;
					</c:if>
					<!-- 内置角色 -->
					<c:if test="${user.deletable == 'N'}">
						<input type="button" value="配置部门" class="button_2003" disabled="disabled"/>
						&nbsp;<input type="button" value="修改" class="button_2003" onclick="updateUserClick('${user.user_iidd}');">&nbsp;
					</c:if>
					</c:if>
					<!-- 系统管理员 -->
					<c:if test="${admin_version == 0 or (admin_version == 1 and display_flag == 'SYS')}">
						<c:if test="${user.deletable == 'Y' and user.del_status == 'N'}">
							<input type="button" value="删除" class="button_2003" onclick="if (confirm('确定要删除此用户吗?')) delSecUser('<c:out value='${user.user_iidd}'/>')"/>
						</c:if>
						<!-- 已删除用户 -->
						<c:if test="${user.deletable == 'Y' and user.del_status == 'Y'}">
							<input type="button" value="恢复" class="button_2003" onclick="if (confirm('确定要恢复此用户吗?')) recoverSecUser('<c:out value='${user.user_iidd}'/>')"/>
						</c:if>
						<!-- 内置角色 -->
						<c:if test="${user.deletable == 'N'}">
							<input type="button" value="内置" class="button_2003" disabled="disabled"/>
						</c:if>
					</c:if>
					<div id="result"></div>
                 </display:column>
          </display:table>
      </td>
    </tr>
  </table>
		</td>
	</tr>
	<!-- 安全管理员 -->
	<c:if test="${admin_version == 0 or (admin_version == 1 and display_flag == 'SEC')}">
		<tr><td><input type="button" value="批量激活" onclick="multiunlock();" class="button_2003"/></td></tr>
	</c:if>
	</form>
</table>
</body>
</html>