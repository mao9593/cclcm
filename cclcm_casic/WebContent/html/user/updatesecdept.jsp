<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${ctx}/_css/css200603.css" rel="stylesheet" type="text/css"/>
<link rel="stylesheet" href="${ctx}/_css/displaytag.css" type="text/css" media="screen"/>
<script language="JavaScript" src="${ctx}/_script/function.js"></script>
<script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
<script language="javascript">
function checkForm() {
	if (document.all.dept_name.value == 0) {
		alert("请填写机构名称！");
		document.all.dept_name.focus();
		return false;
	}
	/*if(document.getElementsByName("dept_level_code")[0].selectedIndex!=0&&document.getElementsByName("dept_type_code")[0].selectedIndex==0){
		alert("请选择机构类型!");
		document.all.dept_type_code.focus();
		return false;
	}*/
	if(!specialCharFilter(document.all.dept_name.value)){
		alert("机构名称包含特殊字符！");
		document.all.dept_name.focus();
		return false;
	}
    var numval = /^[0-9]*$/;
    var temp_rank = document.getElementById("temp_rank").value;
    if(temp_rank != ""){
		if(!numval.test(temp_rank)){
	    	alert("排序值应为整数！");
	    	document.getElementById("temp_rank").focus();
	    	return false;
	    }
	    if(temp_rank.length> 2) {
	    	alert("请输入2位之内的排序值！");
	    	document.getElementById("temp_rank").focus();
	    	return false;
	    }
	}else {
		alert("请输入排序值！");
		document.getElementById("temp_rank").focus();
		return false;
	}
	//机构代码如果非空，须填写两位的数字、大写字母任意组合
	var check_extcode = /^[0-9A-Z]*$/;
          var ext_code = document.getElementById("ext_code").value.trim();
          if(ext_code != "" && ext_code != null){
         		/* if(ext_code.length != 2) {
   			alert("请输入2位机构代号！");
   			document.getElementById("ext_code").focus();
   			return false;
   		} */
		if(!check_extcode.test(ext_code)){
   			alert("机构代号应为2位的数字或大写字母组合！");
   			document.getElementById("ext_code").focus();
   			return false;
 			}		
	}else{
		document.getElementById("ext_code").value ="";
	}
	document.getElementById("dept_rank").value="${dept_parent_cs}"+document.getElementById("temp_rank").value;
	return true;
}
function ctrDeptType(v) {
	if (v == 2 || v == 3) { 
		document.getElementById("deptTypeCode").style.display = 'block';
	} else {
		document.getElementById("deptTypeCode").style.display = 'none';
	}
}
function setDeptSubsys_name() {
	//document.getElementById("dept_subsys_name").innerText = '${dept_subsys_name}';
}
function showDialogSelect() {
	var url = "${ctx}/user/managedeptsubsys.action?dept_id=${dept_id}";
	var rValue = window.showModalDialog(url, '', 'dialogHeight:400px;dialogWidth:460px;center:yes;status:no;help:no;scroll:no;unadorned:no;resizable:no');
	if (rValue != undefined) {
		var returnArray = new Array();
		returnArray = rValue.split("\#");
		var subsyscode = returnArray[0];
		var subsysname = returnArray[1];
		document.getElementById("dept_subsys_name").innerText = subsysname;
		document.getElementById("dept_subsys").value = subsyscode;
		document.getElementById("updateSubsys").value = "Y";
	}
}
</script>
</head>
<body onload="onHover();" oncontextmenu="self.event.returnValue=false">
<form action="${ctx}/user/updatesecdept.action" target="mainFrame" method="post">
<input type="hidden" name="dept_rank" id="dept_rank"/>
<table width="95%" border="0" align="center" cellpadding="0" cellspacing="0" class="table_box">
<tr>
	<td colspan="4" class="title_box">组织机构修改</td>
</tr>
<tr>
	<td nowrap="nowrap">上级机构:</td>
	<td colspan="3">
		${upDeptName}
	</td>
</tr>
<tr>
	<td nowrap="nowrap">机构编号:</td>
	<td colspan="3">
		${secDept.dept_cs}
	</td>
</tr>
<tr>
	<td nowrap="nowrap">机构排序值:</td>
	<td colspan="3">${dept_parent_cs}
	<input type="text" name="temp_rank" id="temp_rank" value="${temp_rank}" />
	</td>
</tr>
<tr>
	<td nowrap="nowrap">机构名称:</td>
	<td colspan="3"><input type="text" name="dept_name" value="${secDept.dept_name}"/></td>
</tr>
<tr>
    <td nowrap="nowrap">机构代号:</td>
    <td colspan="3"><input type="text" name="ext_code" value="${secDept.ext_code}"/></td>
</tr>
<!-- 

<tr>
   	<td nowrap="nowrap">机构信息:</td>
    <td colspan="3">
    	<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="table_box">
    		<tr>
    			<td width="25%" align="center"><div>所在地:&nbsp;&nbsp;
                   	<select name="secTerr_code">
						<c:set var="selectedSecTerr" value="${secDept.terr_code}" scope="request"/>
	              		<c:forEach var="item" items="${secTerrList}" varStatus="status">
	              			<c:set var="showSecTerr" value="${item.terr_code}" scope="request"/>
	              			<option value="${item.terr_code}" <s:if test="#request.selectedSecTerr==#request.showSecTerr">selected="selected"</s:if>>${item.terr_name}</option>
	              		</c:forEach>
               		</select>
                    </div>
                </td>
                <td width="25%" align="center"><div>机构级别:&nbsp;&nbsp;
                	<select name="dept_level_code" onchange="ctrDeptType(this.options[this.selectedIndex].value);">
						<c:set var="selectedDeptLevel" value="${secDept.dept_level_code}" scope="request"/>
	              		<c:forEach var="item" items="${deptLevelList}" varStatus="status">
	              			<c:set var="showDeptLevel" value="${item.dept_level_code}" scope="request"/>
	              			<option value="${item.dept_level_code}" <s:if test="#request.selectedDeptLevel==#request.showDeptLevel">selected="selected"</s:if>>${item.dept_level_name}</option>
	              		</c:forEach>
               		</select>
                   	</div>
                </td>
                <td width="25%" align="center"><div id="deptTypeCode">机构类型:&nbsp;&nbsp;
                	<select name="dept_type_code">
                		<c:set var="selectedDeptType" value="${secDept.dept_type_code}" scope="request"/>
						<option value="">--请选择--</option>                		
                   		<c:forEach var="item" items="${deptTypeList}" varStatus="status">
                   			<c:set var="showDeptType" value="${item.dept_type_code}" scope="request"/>
                   			<option value="${item.dept_type_code}" <s:if test="#request.selectedDeptType==#request.showDeptType">selected="selected"</s:if>>${item.dept_type_name}</option>
                   		</c:forEach>
                   	</select>
                    </div>
                </td>
           </tr>
       </table>
   </td>
</tr>
<tr>
    <td nowrap="nowrap">相关子系统:</td>
    <td colspan="3">
    	<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="table_box">
    		<tr>
    			<td>&nbsp;<div id="dept_subsys_name">${dept_subsys_name}</div>
    				&nbsp;<input type="hidden" name="dept_subsys" id="dept_subsys"/>
    				&nbsp;<input type="hidden" name="updateSubsys" id="updateSubsys"/></td>
				<td width="50"><input type="button" value="配置" class="button_2003" onclick="showDialogSelect()"/></td>
            </tr>
        </table>
    </td>
</tr>
 -->
<tr>
	<td>备注:</td>
	<td><textarea name="dept_desc" cols="60" rows="10">${secDept.dept_desc}</textarea></td>
</tr>
<tr>
	<td colspan="4" class="bottom_box" align="center">
		<input type="hidden" name="dept_id" value="${dept_id}"/>
		<input type="hidden" name="update" value="Y">
		<input type="button" class="button_2003" name="editButton" value="修改" onClick="if(checkForm()) forms[0].submit();"/>
		<input type="Reset" class="button_2003" value="重置" onClick="setDeptSubsys_name();"/>
		<input type="button" class="button_2003" value="返回" onClick="history.go(-1);"/>
	</td>
</tr>
</table>
</form>
</body>
</html>