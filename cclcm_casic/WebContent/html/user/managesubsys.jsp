<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<title>子系统管理</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="${ctx}/_css/displaytag.css" media="screen"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/_css/css200603.css"  media="screen"/>
<script>
<!--
function go(url){
	window.location.href=url;
}
function onclick1(role_count,oper_count,dept_count,subsys_code){
    var url="${ctx}/user/delsubsys.action?subsys_code="+subsys_code;
    if(role_count>0){
        alert("该子系统下已经配置安全角色，子系统无法删除，请先去除相关关联。");
    }else if(oper_count>1){
    	alert("该子系统下已经配置功能操作，子系统无法删除，请先去除相关关联。");
    }else if(dept_count>0){
    	alert("该子系统关联的机构不为空，子系统无法删除，请先去除相关关联。");
    }else{
        if(confirm("该子系统没有关联的角色、操作或机构，确定删除该子系统吗？")){
	       window.location.href =url;
        }
    }
}
function change(flag){
	QueryCondForm.admin_version.value = flag;
	QueryCondForm.submit();
}
//-->
</script>
</head>

<body oncontextmenu="self.event.returnValue=false">
<table width="100%" border="0" cellspacing="1" cellpadding="1" height="100%">
  <tr> 
    <td height="450" align="center" valign="top">      
    <form id="QueryCondForm" method="POST" action="${ctx}/user/managesubsys.action">
    <input type="hidden" id="admin_version" name="admin_version" value="0"/> 
        <table width="95%" border="0" cellspacing="0" cellpadding="0" align="center" class="table_only_border">
          <tr class="layouttr">
            <td class="title_box">子系统管理</td>
          </tr>
		  <tr>
		  <td class="nav_box" align="right">
			<input name="button" type="button" class="button_2003" onClick="go('${ctx}/user/addsubsys.action');" value="增加子系统">
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<c:choose>
				<c:when test="${admin_version eq '0'}">
					<input name="button" type="button" class="button_2003" onClick="change('1');" value="三员用户管理权限切换到科工集团模式">
				</c:when>
				<c:otherwise>
					<input name="button" type="button" class="button_2003" onClick="change('0');" value="三员用户管理权限切换到保密测评模式">
              	</c:otherwise>
			</c:choose>
			
		  </td>
          </tr>
          <tr>
            <td valign="top">
  			<table class="displaytable-outter" cellspacing=0 cellpadding=0 border=0>
    		<tr>
      		<td>
			<display:table requestURI="${ctx}/user/managesubsys.action" uid="subsys" class="displaytable" name="subsysList">
				<display:column property="subsys_code" title="子系统代号" />
				<display:column property="subsys_name" title="子系统名称" />
				<display:column property="subsys_desc" title="子系统描述" />
				<display:column property="oper_code_prefix" title="操作编码前缀" />
				<display:column title="操作" style="width:100px">
					<c:if test="${!subsys.is_delete}">
						<input type="button" class="button_2003" value="修改" <c:if test="${subsys.subsys_code == 'admin'}">disabled</c:if> onclick="go('${ctx}/user/updatesubsys.action?subsys_code=<c:out value="${subsys.subsys_code}"/>');"/>&nbsp;
						<input type="button" class="button_2003" value="删除" <c:if test="${subsys.subsys_code == 'admin'}">disabled</c:if> onclick="onclick1('${subsys.role_count}','${subsys.oper_count}','${subsys.dept_count}','${subsys.subsys_code}')"/>
					</c:if>
					<c:if test="${subsys.is_delete}">
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
        </form>
	</td>
  </tr>
</table>
</body>
</html>
