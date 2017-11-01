<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
<title>查看载体生命周期详细信息</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="${ctx}/_css/css200603.css" type="text/css" media="screen"/>
	<link href="${ctx}/_script/calendar2/calendar-blue.css" type="text/css" rel="stylesheet"/>
	<script language="javascript" src="${ctx}/_script/calendar2/calendar.js"></script>
	<script language="javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>
	<script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
	<script>
	$(document).ready(function(){
		//if('${event.job_status}' != ""){
			prepareBizApproval("${process.step_dept}","${process.step_role}","${process.step_dept_name}","${process.step_role_name}","${ctx}");
		//}
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
	function downloadFile(file_path,file_name,nasFlag){
	//alert(nasFlag+"---"+file_path);
		if(nasFlag == "Y"){
			var address = "\\\\"+nas_url+"\\fileswap";  
			var nas =  document.getElementById('nas');
		    nas.DeleteNASConnect(address);
			var connected=nas.AddNASConnect(address,nas_username, nas_password);
			if(connected == 0){
				var tfile = address+"\\"+'${burn_event.event_code}' + "\\" + file_name;
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
				alert("连接NAS失败,错误代码" + connected);
			}
		}else{
		document.getElementById("file_path").value=file_path;
		document.getElementById("file_name").value=file_name;
		document.getElementById("uploadForm").submit();
		}	
	}
	function getFrameReturn(){
	}
	</script>
<%-- <OBJECT ID="nas"
	CLASSID="CLSID:29570FE6-F45D-4627-8338-CD7CD4BD8B88"
	CODEBASE="${ctx}/html/burn/BurnByNAS.cab#version=1,0,0,3">
</OBJECT> --%>
</head>
<body oncontextmenu="self.event.returnValue=false">
<form action="${ctx}/burn/downloadfile.action" target="hidden_frame" id="uploadForm" method="post">
	<input type="hidden" name="file_path" id="file_path"/>
	<input type="hidden" name="file_name" id="file_name"/>
</form>
<iframe height="0" width="0" frameborder="0" id="hidden_frame" name="hidden_frame" onload="getFrameReturn();"></iframe>
<center>
<!-- 
<c:if test="${not empty recordList}">
<table width="95%" border="1" cellspacing="0" cellpadding="0" align="center" class="table_box">
	<tr>
	    <td colspan="6" class="title_box">载体流程信息</td>
	</tr>
	<tr>
  		<td align="center">流程记录：</td>
  		<td colspan="5">
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
	<tr valign="middle" height="80"> 
    	<td align="center">流程信息： </td>
    	<td class="table_box_border_empty" colspan="5">
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
</table>
</c:if>
<br/>
 -->
<table width="95%" border="1" cellspacing="0" cellpadding="0" align="center" class="table_box">
	<tr>
	    <td colspan="6" class="title_box">载体属性信息</td>
	</tr>
	<tr>  	
    	<td width="13%" align="center">光盘条码： </td>
    	<td width="20%"><font color="blue"><b>&nbsp;${cd.cd_barcode}</b></font></td>
    	<td width="13%" align="center">光盘类型： </td>
    	<td width="20%"><font color="blue"><b>&nbsp;${cd.cd_type}</b></font></td>
    	<td width="13%" align="center">当前状态： </td>
    	<td width="20%"><font color="red"><b>&nbsp;${cd.cd_state_name}</b></font></td>
	</tr>
	<tr>
		<td align="center">卷标名称：</td>
	    <td><font color="blue"><b>&nbsp;${cd.cd_volume}</b></font></td>
	    <td align="center">文件列表：</td>
    	<td><font color="blue"><b>&nbsp;${cd.file_list}</b></font></td>
    	<td align="center">文件数量： </td>
    	<td><font color="blue"><b>&nbsp;${cd.file_num}</b></font></td>		
	</tr>
  	<tr> 
    	<td align="center">光盘密级： </td>
    	<td><font color="blue"><b>&nbsp;${cd.seclv_name}</b></font></td>
		<td align="center">责任人： </td>
    	<td><font color="blue"><b>&nbsp;${cd.duty_user_name}</b></font></td>
    	<td align="center">责任人部门： </td>
    	<td><font color="blue"><b>&nbsp;${cd.duty_dept_name}</b></font></td>
	</tr>
	<tr>
		<td align="center">制作人： </td>
    	<td><font color="blue"><b>&nbsp;${cd.user_name}</b></font></td>
    	<td align="center">制作人部门： </td>
    	<td><font color="blue"><b>&nbsp;${cd.dept_name}</b></font></td> 
    	<td align="center">数据类型： </td>
    	<td><font color="blue"><b>&nbsp;${cd.data_type_name}</b></font></td>
  	</tr>
  	<tr> 
		<td align="center">刻录时间： </td>
    	<td><font color="blue"><b>&nbsp;${cd.burn_time}</b></font></td>
    	<td align="center">刻录类型： </td>
    	<td><font color="blue"><b>&nbsp;${cd.burn_type_name}</b></font></td>
    	<td align="center">刻录结果： </td>
    	<td><font color="blue"><b>&nbsp;${cd.burn_result_name}</b></font></td>	    
	</tr>
	<tr> 
	    <td align="center">刻录机器：</td>
	    <td><font color="blue"><b>&nbsp;${cd.burn_machine}</b></font></td>
	    <td align="center">刻录IP： </td>
    	<td><font color="blue"><b>&nbsp;${cd.burn_ipaddress}</b></font></td>	    
    	<td align="center">是否补刻： </td>
    	<td><font color="blue"><b>&nbsp;${cd.is_reburn_name}</b></font></td>	    
	</tr>
	<tr> 
    	<td align="center">载体归属： </td>
    	<td><font color="blue"><b>&nbsp;${cd.scope_name}</b></font></td>
		<td align="center">归属部门： </td>
    	<td><font color="blue"><b>&nbsp;${cd.scope_dept_name}</b></font></td>
		<td align="center">保密编号： </td>
    	<td><font color="blue"><b>&nbsp;${cd.conf_code}</b></font></td>
    </tr>
	<tr>	
		<td align="center">外发接收人： </td>
    	<td><font color="blue"><b>&nbsp;${cd.output_user_name}</b></font></td>
    	<td align="center">外发接收部门： </td>
    	<td><font color="blue"><b>&nbsp;${cd.output_dept_name}</b></font></td>
    	<td align="center">备注： </td>
    	<td><font color="blue"><b>&nbsp;${cd.fail_remark}</b></font></td>
  	</tr>
  	<c:choose>
		<c:when test="${cd.job_code.contains('SEND')}">
		    <tr>  
		        <td align="center">交接单委托打印人： </td>
		        <td colspan="5"><font color="blue"><b>&nbsp;${supervise_user_name}</b></font></td>
		    </tr>
		</c:when>
	</c:choose>
	<c:choose>
		<c:when test="${cd.create_type.contains('LEADIN')}">
		    <tr>  
		        <td align="center">原光盘编号： </td>
		        <td ><font color="blue"><b>&nbsp;${cd.src_code}</b></font></td>
		        <td align="center">机要号(录入)： </td>
		        <td colspan="3"><font color="blue"><b>&nbsp;${cd.confidential_num}</b></font></td>
		    </tr>
		</c:when>
	</c:choose>
	<tr> 
		<td align="center">到期时间： </td>
    	<td><font color="blue"><b>&nbsp;${cd.expire_time}</b></font></td>
    	<td align="center">到期状态： </td>
    	<td><font color="blue"><b>&nbsp;${cd.expire_status_name}</b></font></td>
    	<td align="center">闭环操作人： </td>
    	<td><font color="blue"><b>&nbsp;${cd.retrieve_user_name}</b></font></td>
    </tr>
     <tr>
  		<td align="center">监销人：</td>
  		<td colspan="5"><font color="blue"><b>&nbsp;${supervise_user_name}</b></font></td>
  	</tr>
	<tr>	
    	<td align="center">闭环时间： </td>
    	<td><font color="blue"><b>&nbsp;${cd.retrieve_time}</b></font></td>
    	<td align="center">销毁人： </td>
    	<td><font color="blue"><b>&nbsp;${cd.destroy_user_name}</b></font></td>
    	<td align="center">销毁时间： </td>
    	<td><font color="blue"><b>&nbsp;${cd.destroy_time}</b></font></td>
	</tr>
	<c:choose>
		<c:when test="${cd.job_code.contains('SEND') or cd.job_code.contains('CARRYOUT_CD')}">
		    <tr>	
				<td align="center">外发(带)方式： </td>
    			<td><font color="blue"><b>&nbsp;${cd.send_way_str}</b></font></td>
    			<td align="center">外发(带)机要号： </td>
    			<td><font color="blue"><b>&nbsp;${cd.output_confidential_num}</b></font></td>
    			<td align="center">携带人： </td>
    			<td><font color="blue"><b>&nbsp;${cd.carryout_user_names}</b></font></td>
			</tr>
		</c:when>
	</c:choose>
	
	<c:if test="${cd.create_type == 'BURN'}">
		<tr>
  		<td align="center">刻录文件列表：</td>
  		<td colspan="5">
  			<table width="60%" border="0" cellspacing="0" cellpadding="0" align="left" >
				<tr><td align="center">文件名</td><td align="center">密级</td></tr>		
	  			<s:iterator value="#request.burnFileList" var="burnFile">
	  				<c:set var="temp_param" value='${fn:replace(burnFile.file_name,"\'","\\\\\'") }' />
	  				<c:choose>
					<c:when test="${ledger_type == 'personal'}">
						<tr>
	  						<td align="center"><a href="#" onclick="downloadFile('${burnFile.file_path}','${temp_param}','${nasFlag}');">${burnFile.file_name}</a></td>
	  						<td align="center">${burnFile.seclv_name}</td>
	  					</tr>
					</c:when>
					<c:when test="${ledger_type == 'total' or ledger_type == 'dept' or ledger_type == 'file'}">				
						<tr>
  							<td align="center"><c:if test="${is_cd_audit}"><a href="#" onclick="downloadFile('${burnFile.file_path}','${temp_param}','${nasFlag}');"></c:if>${burnFile.file_name}</a></td>
  							<td align="center">${burnFile.seclv_name}</td>
  						</tr>	
					</c:when>
					</c:choose>
				</s:iterator>
  			</table>
  		</td>
  		</tr>
	</c:if>
</table>
<c:if test="${not empty itemList}">
<br/>
<table width="95%" border="1" cellspacing="0" cellpadding="0" align="center" class="table_box">
	<tr>
	    <td colspan="6" class="title_box">载体全生命周期信息</td>
	</tr>
	<tr>
    	<td width="20%" align="center">载体条码 </td>
    	<td width="15%" align="center">操作人</td>
    	<td width="15%" align="center">部门</td>
    	<td width="20%" align="center">操作时间 </td>
    	<td width="15%" align="center">操作 </td>
    	<td width="15%" align="center">审批流程</td>
	</tr>
	<s:iterator value="#request.itemList" var="item">
		<tr>
	    	<td align="center">${item.barcode}</td>
	    	<td align="center">${item.user_name}</td>
	    	<td align="center">${item.dept_name}</td>
	    	<td align="center">${item.oper_time_str}</td>
	    	<td align="center">${item.oper.name}</td>
	    	<c:choose>
		    	 <c:when test="${item.job_code == 'default'}">
		    	 	<td align="center">暂无审批信息</td>
		    	 </c:when>
		    	 <c:otherwise>
		    	 	<td align="center"><input class="button_2003" type="button" value="查看审批信息" onClick="go('${ctx}/ledger/viewapprovalprocessdetail.action?job_code=${item.job_code}');"/></td>
		    	 </c:otherwise>
	    	</c:choose>
		</tr>	
	</s:iterator>
</table>
</c:if>
<c:if test="${not empty rejectRecordList}">
<br/>
<table width="95%" border="1" cellspacing="0" cellpadding="0" align="center" class="table_box">
	<tr>
	    <td colspan="5" class="title_box">载体拒收记录</td>
	</tr>
	<tr>
    	<td width="20%" align="center">载体条码 </td>
    	<td width="20%" align="center">拒收用户姓名</td>
    	<td width="20%" align="center">拒收用户部门</td>
    	<td width="15%" align="center">拒收时间 </td>
    	<td width="25%" align="center">备注 </td>
	</tr>
	<s:iterator value="#request.rejectRecordList" var="item">
		<tr>
	    	<td align="center">${item.entity_barcode}</td>
	    	<td align="center">${item.reject_user_name}</td>
	    	<td align="center">${item.reject_dept_name}</td>
	    	<td align="center">${item.reject_time_str}</td>
	    	<td align="center">${item.comment}</td>
		</tr>	
	</s:iterator>
</table>
</c:if>
<br/>
<c:if test="${modify_status==1}">
<table width="95%" border="1" cellspacing="0" cellpadding="0" align="center" class="table_box">
	<tr>
	    <td colspan="5" class="title_box">密级变更记录</td>
	</tr>
	<tr>
    	<td width="20%" align="center">载体条码 </td>
    	<td width="20%" align="center">类型</td>
    	<td width="20%" align="center">原密级</td>
    	<td width="15%" align="center">目标密级</td>
    	<td width="25%" align="center">备注 </td>
    	<td width="15%" align="center">审批流程</td>
	</tr>
	<s:iterator value="#request.eventModify" var="item">
	<c:if test="${item.modify_status==1}">
		<tr>
	    	<td align="center">${item.barcode}</td>
	    	<td align="center">${item.entity_type_name}</td>
	    	<td align="center">${item.pre_seclv_name}</td>
	    	<td align="center">${item.trg_seclv_name}</td>
	    	<td align="center">${item.summ}</td>
	    	<c:choose>
		    	 <c:when test="${item.job_code == 'default'}">
		    	 	<td align="center">暂无审批信息</td>
		    	 </c:when>
		    	 <c:otherwise>
		    	 	<td align="center"><input class="button_2003" type="button" value="查看审批信息" onClick="go('${ctx}/ledger/viewapprovalprocessdetail.action?job_code=${item.job_code}');"/></td>
		    	 </c:otherwise>
	    	</c:choose>
	    	
		</tr>	
	</c:if>
</s:iterator>
</table>
</c:if>
<br/>
<table>
  	<tr>
	    <td colspan="5" align="center"> 
			<input class="button_2003" type="button" value="返回" onClick="javascript:history.go(-1);">&nbsp;
	    </td>
  	</tr>
</table>
</center>
</body>
</html>