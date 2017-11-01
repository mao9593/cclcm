<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<title>机构类型管理</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="${ctx}/_css/displaytag.css" media="screen"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/_css/css200603.css"  media="screen"/>
<script>
<!--
function go(url){
	window.location.href=url;
}
function onclick1(dept_count,dept_type_code){
    var url="${ctx}/user/deldepttype.action?dept_type_code="+dept_type_code;
    if(dept_count>0){
        alert("该类型下存在关联机构，无法删除，请先修改相关机构。");
    }else{
        if(confirm("该类型下无关联机构，确定删除吗？")){
	       window.location.href =url;
        }
    }
}
function showDialogr(url){
		return window.showModalDialog(url,'', 'dialogHeight:400px;dialogWidth:800px;center:yes;status:no;help:no;scroll:no;unadorned:no;resizable:no');
}
//-->
</script>
</head>

<body oncontextmenu="self.event.returnValue=false">
<table width="100%" border="0" cellspacing="1" cellpadding="1" height="100%">
  <tr> 
    <td height="450" align="center" valign="top">       
        <table width="95%" border="0" cellspacing="0" cellpadding="0" align="center" class="table_only_border">
          <tr class="layouttr">
            <td class="title_box">机构类型管理</td>
          </tr>
		  <tr>
		  <td class="nav_box" align="right">
			<input name="button" type="button" class="button_2003" onClick="go('${ctx}/user/adddepttype.action');" value="增加类型">
		  </td>
          </tr>
          <tr>
            <td valign="top">
  			<table class="displaytable-outter" cellspacing=0 cellpadding=0 border=0>
    		<tr>
      		<td>
			<display:table requestURI="${ctx}/user/managedepttype.action" uid="deptType" class="displaytable" name="deptTypeList">
				<display:column property="dept_type_code" title="类型编号" />
				<display:column property="dept_type_name" title="类型名称" />
				<display:column property="dept_type_desc" title="类型描述" />
				<display:column title="操作" style="width:150px">
					<input type="button" class="button_2003" value="部门" onclick="showDialogr('${ctx}/user/viewdeptbytype.action?dept_type_code=<c:out value="${deptType.dept_type_code}"/>')"/>&nbsp;
					<c:if test="${!deptType.is_delete}">
						<input type="button" class="button_2003" value="修改" onclick="go('${ctx}/user/updatedepttype.action?dept_type_code=<c:out value="${deptType.dept_type_code}"/>');"/>&nbsp;
						<input type="button" class="button_2003" value="删除" onclick="onclick1( <c:out value="${deptType.dept_count}"/>,<c:out value="${deptType.dept_type_code}"/>)"/>
					</c:if>
					<c:if test="${deptType.is_delete}">
						<input type="button" class="button_2003" value="修改" disabled="disabled"/>&nbsp;
						<input type="button" class="button_2003" value="封存" disabled="disabled"/>
					</c:if>
				</display:column>
			</display:table>
			</td>
			</tr>
			</table>
            </td>
          </tr>
        </table>
	</td>
  </tr>
</table>
</body>
</html>
