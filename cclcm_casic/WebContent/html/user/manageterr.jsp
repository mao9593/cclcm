<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<title>部门地区管理</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="${ctx}/_css/displaytag.css" media="screen"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/_css/css200603.css"  media="screen"/>
<script>
<!--
function go(url){
	window.location.href=url;
}
function onclick1(dept_count,terr_code){
    var url="${ctx}/user/delterritory.action?terr_code="+terr_code;
    if(dept_count>0){
        alert("该地区内的组织机构不为空，地区无法删除，请先去除相关关联。");
    }else{
        if(confirm("该地区内还没有组织机构，确定删除该地区吗？")){
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
            <td class="title_box">部门地区管理</td>
          </tr>
		  <tr>
		  <td class="nav_box" align="right">
			<input name="button" type="button" class="button_2003" onClick="go('${ctx}/user/addterritory.action');" value="增加地区">
		  </td>
          </tr>
          <tr>
            <td valign="top">
  			<table class="displaytable-outter" cellspacing=0 cellpadding=0 border=0>
    		<tr>
      		<td>
			<display:table requestURI="${ctx}/user/manageterr.action" uid="terr" class="displaytable" name="terrList">
				<display:column property="terr_code" title="地区编号" />
				<display:column property="terr_name" title="地区名称" />
				<display:column property="terr_desc" title="地区描述" />
				<display:column title="操作">
					&nbsp;<input type="button" class="button_2003" value="部门" onclick="showDialogr('${ctx}/user/viewdeptbyterr.action?terr_code=<c:out value="${terr.terr_code}"/>')";/>
					&nbsp;<input type="button" class="button_2003" value="修改" onclick="go('${ctx}/user/updateterritory.action?terr_code=<c:out value="${terr.terr_code}"/>');"/>
					&nbsp;<input type="button" class="button_2003" value="删除" onclick="onclick1('${terr.dept_count}','${terr.terr_code}')";/>
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
