<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html><head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>用户其他信息修改</title>
<link href="${ctx}/_css/css200603.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/_css/displaytag.css"  rel="stylesheet" type="text/css" media="screen" />
<script type="text/javascript" src="${ctx}/_script/xmlhttp.js"></script>
<script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
<script language="javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>
<script language="javascript" src="${ctx}/_script/casic304_ajax.js"></script>
<script language="JavaScript" src="${ctx}/_script/function.js"></script>
<script language="JScript">
<!--
function init(){
	if('${prompt}'=='Y'){
		//if(confirm("无匹配岗位，若继续修改则该用户岗位将置空，以后要重新配置该用户岗位。是否继续修改？")){
			//如果强制修改部门，则置用户岗位为空
			window.location = "${ctx}/user/updateuserdept.action?dept_id=${dept_id}&old_dept_id=${old_dept_id}&user_iidd=${user_iidd}&clearPost=Y";
		//}
	}
}
function submitChangeMainDept(old_dept_id){
	var cID = document.getElementById("user_dept_id").value;//新的部门编号
	window.location = "${ctx}/user/updateuserdept.action?dept_id=" + cID +"&old_dept_id=" + old_dept_id+"&user_iidd=${user_iidd}";
}

function submitD(){
	var old_dept_id = document.getElementById("user_dept_id").value;
	openDeptSelect("user_dept_name","user_dept_id","radio");//用模态对话框选择部门
	//如果选择了新部门
	if (document.getElementById("user_dept_id").value != '' && old_dept_id != document.getElementById("user_dept_id").value){
		submitChangeMainDept(old_dept_id);
	}
}

/**
 * 弹出选择角色的对话框
 */
function showDialogSelectRole(subsys_code){
	var url = "${ctx}/user/manageuserrole.action?user_iidd=${user_iidd}&subsys_code="+subsys_code;
	var rValue = window.showModalDialog(url,'', 'dialogHeight:400px;dialogWidth:700px;center:yes;status:no;help:no;scroll:no;unadorned:no;resizable:no');
	//alert(rValue);
	if (rValue != null && rValue != undefined){
		window.location = "${ctx}/user/updateuserrole.action?user_iidd=${user_iidd}&subsys_code="+subsys_code+"&newCodes=" + rValue;
	}
}
function selectAdminRole(subsys_code) {
	var url = "${ctx}/user/getadminrole.action?user_iidd=${user_iidd}&subsys_code="+subsys_code;
	window.showModalDialog(url, '', 'dialogHeight:300px;dialogWidth:600px;center:yes;status:no;scroll:auto;help:no;unadorned:no;resizable:no');
	window.location = window.location;
}
function selectSpecialRole(subsys_code) {
	var url = "${ctx}/user/getspecialrole.action?user_iidd=${user_iidd}&subsys_code="+subsys_code;
	window.showModalDialog(url, '', 'dialogHeight:300px;dialogWidth:600px;center:yes;status:no;scroll:auto;help:no;unadorned:no;resizable:no');
	window.location = window.location;
}
function configScope(role_id,role_spec_key){
	if(role_spec_key == "TERR"){
		var url = "${ctx}/user/configscopeterr.action?user_iidd=${user_iidd}&role_id="+role_id;
		window.showModalDialog(url, '', 'dialogHeight:400px;dialogWidth:600px;center:yes;status:no;scroll:auto;help:no;unadorned:no;resizable:no');
	}else if(role_spec_key == "DEPT"){
		//先展示已经配置的部门列表（把dept_id写入"dept_id"标签，openDeptSelect方法会自动读取其中的参数进行展示）
		var url = "${ctx}/user/getscopedeptid.action?user_iidd=${user_iidd}&role_id="+role_id;
		var deptIds = send(url);
		//使用正则判断返回是否正确,只有数字和逗号，说明action没有返回error页面。
		var pattern = /^[0-9,]*$/;
		if(pattern.test(deptIds)){
			document.getElementById("dept_id").value =deptIds; 
		}else{
			alert("查询已配置部门时发生错误，请重试!");
			return false;
		}
		//选择部门
		var rValue = openDeptSelect("dept_name","dept_id","checkbox");
		//alert(rValue);
		if(rValue != undefined && rValue == "Y"){
			//更新操作
			var list = document.getElementById("dept_id").value;
			var url = "${ctx}/user/configscopedept.action?user_iidd=${user_iidd}&role_id="+role_id+"&deptIdList="+list;
			var result = send(url);
			if (result == "true"){
				alert("更新成功");	
			}else{
				alert("配置失败，请重新尝试，或与管理员联系！");
			}
		}
	}
}
function configScope1(role_id,dept_ids,dept_names){
	$("#role_id").val(role_id);
	$("#dept_ids").val(dept_ids);
	$("#dept_names").val(dept_names);
	var rValue = openDeptSelect("dept_names","dept_ids","checkbox");
	if(rValue != undefined && rValue == "Y"){
		 if($("#dept_ids").val()!=""){
			var url="${ctx}/basic/configdeptbyrole.action";
			callServerPostForm(url,document.forms[0]);
		} 
	}
}
function getAjaxResult(){
	if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
		alert(xmlHttp.responseText);
		$("#ConfigDeptForm").submit();
	}
}
//-->
</script>
</head>
<body onLoad="init();" oncontextmenu="self.event.returnValue=true">
<form method="post" action="${ctx}/basic/configdeptbyrole.action" >
	<input type="hidden" name="user_iidd" value="${user_iidd}" />
	<input type="hidden" name="role_id" id="role_id" />
	<input type="hidden" name="dept_ids" id="dept_ids" />
	<input type="hidden" name="dept_names" id="dept_names" />
</form>
<form method="post" action="${ctx}/user/updatesecuserother.action" id="ConfigDeptForm">
	<input type="hidden" name="user_iidd" value="${user_iidd}" />
<table border="0" cellspacing="0" cellpadding="0" width="95%" align="center" class="table_only_border">
	<tr>
		<td class="title_box">用户部门修改</td>
	</tr>
	<tr height="20">
		<td>
			<table width="100%" border="0">
              <tr>
                <td width="50%">
					<span title="${userDept.dept_id}">
						用户归属部门：
						<span  style="color:red;font-weight:bold">${userDept.dept_name}</span>
					</span>
				</td>
                <td>
					<input type="button" class="button_2003" onclick="javascript:submitD();" value="修改"/>
					<input type="hidden" id="user_dept_name" name="user_dept_name" value="${userDept.dept_name}"/>
                 	<input type="hidden" id="user_dept_id" name="user_dept_id" value="${userDept.dept_id}"/>
				 </td>
              </tr>
            </table>
		</td>
	</tr>  
</table>
<br/>
<table border="0" cellspacing="0" cellpadding="0" width="95%" align="center" class="table_only_border">
	<tr>
		<td colspan="2" class="title_box">用户角色信息</td>
	</tr>
	<tr>
		<td colspan="2" valign="top">
			<input type="hidden" id="dept_name"/>
			<input type="hidden" id="dept_id"/>
			<c:forEach var="subsys" items="${secSubsyslist}">
			<br/>
			<fieldset style="PADDING-RIGHT: 5px; PADDING-LEFT: 5px; PADDING-BOTTOM: 5px; PADDING-TOP: 5px">
				<legend><span title="${subsys.subsys_desc}" style="color:red;font-weight:bold"></span></legend>
				<table border="0" cellspacing="0" cellpadding="0" width="100%">
					<tr>
						<td align="right" class="nav_box">
							<input type="button" class="button_2003" onclick="javascript:showDialogSelectRole('${subsys.subsys_code}');" value="配置角色"/>&nbsp;
							<!-- 
							<input type="button" class="button_2003" onclick="javascript:selectAdminRole('${subsys.subsys_code}');" value="配置三员角色"/>&nbsp;
							<input type="button" class="button_2003" onclick="javascript:selectSpecialRole('${subsys.subsys_code}');" value="配置特殊角色"/>&nbsp;
							 -->
						</td>
					</tr>
					<c:forEach var="item" items="${secRoleMap}">
					<c:if test="${item.subsys_code == subsys.subsys_code}">
					<c:set var="secRoleList" value="${item.secRoleList}" scope="request"/>
					<tr>
						<td valign="top" colspan="2">
						<table class="displaytable-outter" cellspacing=0 cellpadding=0 border=0 width="100%">
		    				<tr>
		      				<td>
							<display:table uid="secRole" class="displaytable" name="secRoleList" defaultsort="1" sort="list">
								<display:column title="角色名称" style="whitespace: nowrap;">
									<div title="<c:out value="${secRole.role_id}" />">
										<c:out value="${secRole.role_name}"/>
									</div>
								</display:column>
								<display:column property="roleTypeName"  title="角色类型" style="whitespace: nowrap;" nulls="false"/>
								<display:column title="管理部门" >
									${secRole.deptAdminConfig.dept_names}
								</display:column>
								<display:column  title="操作" style="whitespace: nowrap;">
									<c:if test="${secRole.role_type ==4}">
										<input type="button" class="button_2003" value="配置${secRole.role_spec_key == 'DEPT'?'部门':'地区'}" onclick="configScope('${secRole.role_id}','${secRole.role_spec_key}');"/>
									</c:if>
									<c:if test="${secRole.role_spec_key == null or secRole.role_spec_key == '' or secRole.role_spec_key == 'BURNADMIN'}">
										<input type="button" class="button_2003" value="配置部门" onclick="configScope1('${secRole.role_id}','${secRole.deptAdminConfig.dept_ids}','${secRole.deptAdminConfig.dept_names}');"/>
									</c:if>
								</display:column>
							</display:table>
							</td>
							</tr>
							</table>
						</td>
					</tr>
					</c:if>
					</c:forEach>
				</table>
			</fieldset>
			</c:forEach>
		</td>
	</tr>
</table>
</form>
</body>
</html>