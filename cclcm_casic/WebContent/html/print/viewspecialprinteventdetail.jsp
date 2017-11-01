<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<link rel="stylesheet" type="text/css" href="${ctx}/_css/displaytag.css" media="screen"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/_css/css200603.css"  media="screen"/>
    <script language="javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>
	<script language="JavaScript" src="${ctx}/_script/function.js"></script>
	<script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
	<script language="javascript" src="${ctx}/_script/casic304_ajax.js"></script>
<script>
$(document).ready(function(){
	if('${event.job_status}' != ""){
		prepareBizApproval("${process.step_dept}","${process.step_role}","${process.step_dept_name}","${process.step_role_name}","${ctx}");
	}
});
	function downloadFile(file_path,file_name){
		document.getElementById("file_path").value=file_path;
		document.getElementById("file_name").value=file_name;
		document.getElementById("uploadForm").submit();
	}
	function getFrameReturn(){
	
	}
</script>
</head>
<body oncontextmenu="self.event.returnValue=false">
<form action="${ctx}/burn/downloadfile.action" target="hidden_frame" id="uploadForm" method="post">
	<input type="hidden" name="file_path" id="file_path"/>
	<input type="hidden" name="file_name" id="file_name"/>
</form>
<iframe height="0" width="0" frameborder="0" id="hidden_frame" name="hidden_frame" onload="getFrameReturn();"></iframe>
<table width="95%" border="1" cellspacing="0" cellpadding="0" align="center" class="table_box">
	<tr>
	    <td colspan="4" class="title_box">查看特殊打印作业详情</td>
	</tr>
	<tr>
    	<td width="15%" align="center">申请用户： </td>
    	<td width="35%"><font color="blue"><b>&nbsp;${event.user_name}</b></font></td>
    	<td width="15%" align="center">当前状态： </td>
    	<td width="35%"><font color="red"><b>&nbsp;${event.job_status_name}&nbsp;/&nbsp;${event.paper_status_str}</b></font></td>
	</tr>
	<tr>
    	<td align="center">用户部门： </td>
    	<td><font color="blue"><b>&nbsp;${event.dept_name}</b></font></td>
    	<td align="center">申请时间： </td>
    	<td><font color="blue"><b>&nbsp;${event.apply_time_str}</b></font></td>
	</tr>
	<tr>
		<td align="center">文件名称： </td>
    	<td><font color="blue"><b>&nbsp;${event.paper_name}</b></font></td>
		<td align="center">文件密级：</td>
	    <td><font color="blue"><b>&nbsp;${event.file_sname}</b></font></td>
	</tr>
	<tr>
		<td align="center">份数：</td>
	    <td><font color="blue"><b>&nbsp;${event.paper_num}份</b></font></td>
	    <td align="center">页数：</td>
	    <td><font color="blue"><b>&nbsp;${event.page_num}页</b></font></td>
	</tr>
	<tr>
		<td align="center">制作类型： </td>
    	<td><font color="blue"><b>&nbsp;${event.make_type_str}</b></font></td>
		<td align="center">单双面：</td>
	    <td><font color="blue"><b>&nbsp;${event.print_double_str}</b></font></td>
	</tr>
	<tr>
		<td align="center">纸张类型：</td>
	    <td><font color="blue"><b>&nbsp;${event.paper_kind}</b></font></td>
	    <td align="center">色彩类型：</td>
	    <td><font color="blue"><b>&nbsp;${event.paper_color_str}</b></font></td>
	</tr>
	<tr>
		<td align="center">横纵向： </td>
    	<td><font color="blue"><b>&nbsp;${event.print_direct_str}</b></font></td>
		<td align="center">备注： </td>
    	<td><font color="blue"><b>&nbsp;${event.summ}</b></font></td>
	</tr>
  	<tr>
  		<td align="center">文件：</td>
  		<td colspan="3">
  			<table width="60%" border="0" cellspacing="0" cellpadding="0" align="left" >
				<tr><td align="center">文件名</td></tr>		
	  			<s:iterator value="#request.burnFileList" var="burnFile">
	  				<tr>
	  					<td align="center"><u><label style="color: blue;cursor: pointer;" onclick="downloadFile('${burnFile.file_path}','${burnFile.file_name}');">${burnFile.file_name}</label></u></td>
	  				</tr>
				</s:iterator>
  			</table>
  		</td>
  	</tr>	
  	<tr>
    <td colspan=4 align=center> 		
		<input class="button_2003" type="button" value="返回" onClick="javascript:history.go(-1);">	
    </td>
  </tr>
</table>
</body>
</html>
