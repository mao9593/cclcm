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
<!--
$(document).ready(function(){
	if('${event.job_status}' != ""){
		prepareBizApproval("${process.step_dept}","${process.step_role}","${process.step_dept_name}","${process.step_role_name}","${ctx}");
	}
	init();
});

var nas_url;
var nas;
var nas_username;
var nas_password;
function init(){
	nas_url = "${nas_url}";
	nas_username = "${nas_username}";
	nas_password = "${nas_password}";
}

function downloadFile(file_path,file_name){
//	alert("begin nas download!");
	var address = "\\\\"+nas_url+"\\fileswap";  
	var nas =  document.getElementById('nas');
    //nas.DeleteNASConnect(address);
    // mod by rmf 20150811 连接NAS前先访问NAS的logout方法，将当前的NAS连接断开，以防止NAS窗口打开时无法上传文件。
    window.open("http://"+nas_url+"/sp/user.do?o=tologout", "_blank", "width=10,height=10,left="+((window.screen.width/2)-5)+",top="+((window.screen.height/2)-5)+",location=no,menubar=no,resizable=no,status=no,titlebar=no,toolbar=no,scrollbars=no");
	var connected=nas.AddNASConnect(address,nas_username, nas_password);
	if(connected == 0){
		var tfile = address+"\\"+'${event.event_code}' + "\\" + file_name;
//	    var fileok=nas.MakeSureDirectoryPathExists(tfile2);
		var test = "c:\\" + file_name;
		//alert(tfile + "  " + test + "  " + address);
		var upok=nas.DecodeFileToTmp(tfile,test,address);
		nas.DeleteNASConnect(address);
	}else if(connected == 5){
		alert("连接NAS失败，请确认关闭安全NAS系统的WEB管理页面后重试");
	}else if(connected == 1219){
		alert("连接NAS失败，请确认关闭安全NAS系统的文件操作窗口后重试");
	}else{
		alert("连接NAS失败,错误代码:" + connected);
	}
}

//显示每条作业的邦辰关键字检查情况
function ShowRisklist(filename,user_iidd,event_code){
	    if("Microsoft Internet Explorer"==navigator.appName){
		var url ="${ctx}/burn/showrisklistburn.action?filename="+encodeURI(encodeURI(filename))+"&event_code="+event_code+"&user_iidd="+user_iidd;
		window.showModalDialog(url,window,'dialogHeight:600px;dialogWidth:700px;center:yes;status:no;scroll:no;help:no;unadorned:no;resizable:no');
		return false;
		}
}
function getFrameReturn(){
}
//-->
</script>
<OBJECT ID="nas"
	CLASSID="CLSID:29570FE6-F45D-4627-8338-CD7CD4BD8B88"
	CODEBASE="${ctx}/html/burn/BurnByNAS.cab#version=1,0,0,3">
</OBJECT>
</head>
<body oncontextmenu="self.event.returnValue=false">
<iframe height="0" width="0" frameborder="0" id="hidden_frame" name="hidden_frame" onload="getFrameReturn();"></iframe>
<table width="95%" border="1" cellspacing="0" cellpadding="0" align="center" class="table_box">
	<tr>
	    <td colspan="4" class="title_box">查看刻录作业详情</td>
	</tr>
	<tr>
    	<td width="15%" align="center">申请用户： </td>
    	<td width="35%"><font color="blue"><b>&nbsp;${event.user_name}</b></font></td>
    	<td width="15%" align="center">当前状态： </td>
    	<td width="35%"><font color="red"><b>&nbsp;${event.job_status_name}&nbsp;/&nbsp;${event.burn_status_name}</b></font></td>
	</tr>
	<tr>
    	<td align="center">用户部门： </td>
    	<td><font color="blue"><b>&nbsp;${event.dept_name}</b></font></td>
    	<td align="center">申请时间： </td>
    	<td><font color="blue"><b>&nbsp;${event.apply_time_str}</b></font></td>
	</tr>
	<tr>
    	<td align="center">作业密级：</td>
	    <td><font color="blue"><b>&nbsp;${event.seclv_name}</b></font></td>
	    <td align="center">状态：</td>
		<td><font color="blue"><b>&nbsp;${event.cycle_type_name}&nbsp;${event.period_name}</b></font></td>
	</tr>
	<c:if test="${event.cycle_type eq 'SEND'}">
		<tr>
			<td align="center">接收人： </td>
	    	<td><font color="blue"><b>&nbsp;${job.output_user_name}</b></font></td>
	    	<td align="center">接收单位： </td>
	    	<td><font color="blue"><b>&nbsp;${job.output_dept_name}</b></font></td>
	  	</tr>
	</c:if>
	<tr>
		<td align="center">用途： </td>
    	<td><font color="blue"><b>&nbsp;${event.usage_name}</b></font></td>
    	<td align="center">代理刻录： </td>
    	<td><font color="blue"><b>&nbsp;${event.is_proxy_name}</b></font></td>
  	</tr>
  	<tr>
		<td align="center">刻录份数： </td>
    	<td><font color="blue"><b>&nbsp;${event.cd_num}</b></font></td> 
    	<td align="center">保密编号： </td>
    	<td><font color="blue"><b>&nbsp;${event.conf_code}</b></font></td>
  	</tr>
	<tr> 
    	<%-- <td align="center">项目：</td>
	    <td><font color="blue"><b>&nbsp;${event.project_name}</b></font></td> --%>
  		<td align="center">具体说明：</td>
		<td colspan="3"><textarea rows="3" cols="40" readonly="readonly">&nbsp;${event.summ}</textarea></td>
	</tr>
  	<tr>
  		<td align="center">刻录文件：</td>
  		<td colspan="3">
  			<table width="60%" border="0" cellspacing="0" cellpadding="0" align="left" >
				<tr><td align="center">文件名</td><td align="center">密级</td><!-- <td align="center">&nbsp;关键字检查结果</td> --></tr>		
	  			<s:iterator value="#request.burnFileList" var="burnFile">
	  				<tr>
	  					<c:choose>
	  						<c:when test="${is_cd_audit}">
	  							<td align="center"><a href="#" onclick="downloadFile('${burnFile.file_path}','${burnFile.file_name}');">${burnFile.file_name}</a></td>
	  						</c:when>
	  						<c:otherwise>
	  							<td align="center">${burnFile.file_name}</td>
	  						</c:otherwise>
	  					</c:choose>
	  					<td align="center">${burnFile.seclv_name}</td>
	  					<%-- <td align="center"><u><label style="color: red;cursor: pointer;" onclick="ShowRisklist('${burnFile.file_name}','${event.user_iidd}','${event.event_code}');">查看结果&nbsp;</label></u></td> --%>
	  				</tr>
				</s:iterator>
  			</table>
  		</td>
  	</tr>
  	<tr height="50" style="display: <c:if test="${empty event.job_status or op=='noprc'}">none</c:if>">
  		<td align="center">审批人：</td>
  		<td colspan="3"><font color="blue"><b>&nbsp;${job.next_approver_name}</b></font></td>
  	</tr>
  	<tr style="display: <c:if test="${empty event.job_status or op=='noprc'}">none</c:if>">
  		<td align="center">流程记录：</td>
  		<td colspan="3">
  			<table width="90%" border="0" cellspacing="0" cellpadding="0" align="left" >
				<tr>
					<td align="center" width="100">操作</td>
					<td align="center" width="100">操作人</td>
					<td align="center" width="100">部门</td>
					<td align="center" width="150">时间</td>
					<td align="center">意见</td>
				</tr>		
	  			<s:iterator value="#request.recordList" var="item">
	  				<tr>
	  					<td align="center">&nbsp;${item.operation}</td>
	  					<td align="center">&nbsp;${item.user_name}</td>
	  					<td align="center">&nbsp;${item.dept_name}</td>
	  					<td align="center">&nbsp;${item.op_time_str}</td>
	  					<td align="center">&nbsp;${item.opinion}</td>
	  				</tr>
				</s:iterator>
  			</table>
  		</td>
  	</tr>
  	<tr valign="middle" height="80" style="display: <c:if test="${empty event.job_status or op=='noprc'}">none</c:if>"> 
    	<td align="center">流程信息： </td>
    	<td class="table_box_border_empty" colspan="3">
			<table class="table_box_border_empty"><tr>
				<td>
					<table>
						<tr><td align="center">
							<img alt="流程开始" border="0" src="${ctx}/_image/ico/process/prc_start.jpg" />
						</td></tr>
						<tr><td align="center">
							流程开始
						</td></tr>
					</table>
				</td>
				<td>
					<table>
						<tr><td align="center">
							<img border="0" src="${ctx}/_image/ico/process/to.gif"/>
						</td></tr>
					</table>
				</td>
				<td>
					<table>
						<tr><td align="center">
							<img alt="用户提交申请" border="0" src="${ctx}/_image/ico/process/prc_step.jpg" />
						</td></tr>
						<tr><td align="center">
							用户提交申请
						</td></tr>
					</table>
				</td>
				<td>
					<table>
						<tr><td align="center">
							<img border="0" src="${ctx}/_image/ico/process/to.gif"/>
						</td></tr>
					</table>
				</td>
				<td>
					<table>
						<tr><td align="center">
							<img border="0" src="${ctx}/_image/ico/process/prc_end.jpg" id="prc_end"/>
						</td></tr>
						<tr><td align="center">
							流程结束
						</td></tr>
					</table>
				</td>
			</tr></table>
		</td>
	</tr>
  	<tr>
    <td colspan=4 align=center> 
		<input class="button_2003" type="button" value="返回" onClick="javascript:history.go(-1);">&nbsp;
    </td>
  </tr>
</table>
</body>
</html>
