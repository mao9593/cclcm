<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<title>查看机构等级</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="${ctx}/_css/displaytag.css" media="screen"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/_css/css200603.css"  media="screen"/>
    <script>
    <!--
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
            <td class="title_box">查看机构等级</td>
          </tr>
          <tr>
            <td valign="top">
  			<table class="displaytable-outter" cellspacing=0 cellpadding=0 border=0>
    		<tr>
      		<td>
			<display:table uid="deptLevel" class="displaytable" name="deptLevelList">
				<display:column property="dept_level_code" title="等级编号"/>
				<display:column property="dept_level_name" title="等级名称"/>
				<display:column property="dept_level_desc" title="等级描述"/>
				<display:column title="查看">
					<input type="button" class="button_2003" value="部门" onclick="showDialogr('${ctx}/user/viewdeptbylevel.action?dept_level_code=<c:out value="${deptLevel.dept_level_code}"/>')";/>
				</display:column>
			</display:table>
			</td>
			</tr>
			</table>
            </td>
          </tr>
          <tr>
          	<td>
          	<div>
          		<font color="red" >机构等级为系统内置的数据，不提供增、删、改，只可查看。</font>
          	</div>
          	</td>
          </tr>
        </table>
	</td>
  </tr>
</table>
</body>
</html>
