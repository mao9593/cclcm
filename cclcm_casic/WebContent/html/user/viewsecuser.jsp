<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<title>通讯录</title>
	<link rel="stylesheet" href="${ctx}/_css/displaytag.css" type="text/css" media="screen" />
    <link rel="stylesheet" href="${ctx}/_css/css200603.css" type="text/css" media="screen" />
    <script language="javascript" src="${ctx}/_script/grobal_val.js"></script>
    <script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
	<script language="javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>
	<script language="javascript">
		$(document).ready(function(){
			onHover();
			addSelectAllCheckbox();
			$("#security_code").val("${security_code}");
		});
		function clearFindForm(){
			queryform.user_iidd.value = "";
			queryform.user_name.value = "";
			queryform.pass_num.value = "";
			queryform.base_sex[0].selected = true;
			queryform.security_code[0].selected = true;
			queryform.dept_name.value = "";
			queryform.dept_id.value = "";
			queryform.status.value = "";
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
		function setStatus(user_iidd,status){
  			var url = "${ctx}/user/setuserstatus.action?user_iidd=" +escape(user_iidd)+"&status="+status;
  			xmlHttp.open("GET", url, true);
  			xmlHttp.onreadystatechange = updatePage;
  			xmlHttp.send(null);
		}
		function multiunlock(){
		var user_iidd = "";
		if($(":checkbox:checked[value!='']").size()>0){
			$(":checkbox:checked[value!='']").each(function (){
				user_iidd += this.value+"#";
			});
			setStatus(user_iidd,0)
		}else{
			alert("请先勾选需批量激活的用户");
			return false;
		}
		}
		function updatePage() {
		  if (xmlHttp.readyState == 4) {
		    var response = xmlHttp.responseText;
		    //result.innerHTML=response;
		    alert(response);
		    queryform.submit();
		  }
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
		<td colspan="2"  class="title_box">用户查询</td>
	</tr>
	<tr height="20"><td>
<table border=0 class="table_box" cellspacing="0" cellpadding="0" width="100%">
<form method="post" action="${ctx}/user/viewsecuser.action" name="queryform">
	<tr>
		<td>登录名：</td>
		<td><input type="text" name="user_iidd" size="15" value="${user_iidd}"/></td>
		<td>用户名：</td>
		<td><input type="text" name="user_name" size="15" value="${user_name}"/></td>
		<td>归属部门：</td>
		<td>
			<input type="text" id="dept_name" name="dept_name" value="${dept_name}" readonly style="cursor:hand" size="15" styleClass="input_2k3" onclick="openDeptSelect('dept_name','dept_id','radio')" />
			<input type="hidden" name="dept_id" id="dept_id" value="${dept_id}"/>
		</td>
		<td>用户卡号：</td>
		<td><input type="text" name="pass_num" size="15"/ value="${pass_num}"></td>
	</tr>
	<tr>
        <td>锁定状态：</td>
        <td>
            <select name="status">
                <option value="">--所有--</option>
				<option value="0" <c:if test="${status == '0'}">selected="selected"</c:if>>正常</option>
				<option value="1" <c:if test="${status == '1'}">selected="selected"</c:if>>锁定</option>
            </select>
        </td>
		<td>涉密人员类别：</td>
		<td>
			<select name="security_code" id="security_code">
				<option value="">--所有--</option>
				<s:iterator value="#request.securityList" var="security">
					<option value="${security.security_code}">${security.security_name}</option>
				</s:iterator>
			</select>
		</td>
		<td>性别：</td>
		<td><select name="base_sex">
				<option value="">--所有--</option>
				<option value="M" <c:if test="${base_sex == 'M'}">selected="selected"</c:if>>男</option>
				<option value="F" <c:if test="${base_sex == 'F'}">selected="selected"</c:if>>女</option>
			</select></td>
      	<td colspan="2" align="center">
			<input type="submit" class="button_2003" value="查询" />&nbsp;
			<input type="button" value="清空" class="button_2003" onclick="clearFindForm()" />
		</td>
	</tr>
</table>
		</td>
	</tr>
	<tr>
		<td valign="top">
<table class="displaytable-outter" cellspacing=0 cellpadding=0 border=0 width="100%">
    <tr>
      <td>
           <display:table requestURI="${ctx}/user/viewsecuser.action" uid="user" name="secUserList" class="displaytable" defaultsort="0" 
           pagesize="15" excludedParams="*" form="queryform">
                 <display:column title="选择">
					<input type="checkbox" value="${user.user_iidd}" name="user_iidd"/>
				</display:column>
                 <display:column title="登录名" sortable="true" >
					<div style="cursor:hand;color:blue" onMouseOver="this.style.color='red';" onMouseOut="this.style.color='blue';" onClick="window.open('${ctx}/user/viewuserdetail.action?user_iidd=${user.user_iidd}', '_blank', 'left=400,top=200,height=500,width=530,location=no,menubar=no,resizable=no,status=no,titlebar=no,toolbar=no,scrollbars=yes');" >
					<c:if test="${user.status == 1}">
						<img src="${ctx}/_image/iconx32/stop1.gif" />
					</c:if>
						<c:out value="${user.user_iidd}"/>
					</div>
				 </display:column>
                 <display:column title="用户名" property="user_name" sortable="true" />
                 <display:column title="性别" sortable="true" >
                 	<c:if test="${user.base_sex == 'M'}">男</c:if>
                 	<c:if test="${user.base_sex == 'F'}">女</c:if>
	             </display:column>
	             <display:column title="用户卡号" property="pass_num" sortable="true" />
	           　　		 <display:column title="涉密人员类别" property="security_name"/>
                 <display:column title="部门" property="dept_name" sortable="true" />
           <!--  <display:column title="固定电话" property="com_telephone" sortable="true" />
                 <display:column title="电子邮件" property="com_email" sortable="true" />    -->
                 <display:column title="锁定状态" property="status_name" sortable="true" />
                 <display:column title="重置密码" style="width:150px">
                 	<input type="button" class="button_2003" value="重置密码" onClick="showDialogModifyPW('${user.user_iidd}');"/>&nbsp;&nbsp;
                 	<c:choose>
                		<c:when test="${user.status == 0}">
							<input type="button" value="禁用" class="button_2003" onclick="setStatus('<c:out value='${user.user_iidd}'/>',1)"/>&nbsp;
                		</c:when>
                		<c:when test="${user.status == 1}">
							<input type="button" value="激活" style="color:red" class="button_2003" onclick="setStatus('<c:out value='${user.user_iidd}'/>',0)"/>&nbsp;
                		</c:when>
                		<c:otherwise>
                		</c:otherwise>
	                	</c:choose>
                 </display:column>
          </display:table>
      </td>
    </tr>
  </table>
		</td>
	</tr>
	<tr><td><input type="button" value="批量激活" onclick="multiunlock();" class="button_2003"/></td></tr>
</form>
</table>
</body>
</html>