<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<title>交接确认配置</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="${ctx}/_css/displaytag.css" media="screen"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/_css/css200603.css"  media="screen"/>
    <script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
    <script language="javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>
    <script language="javascript" src="${ctx}/_script/casic304_ajax.js"></script>   
</head>
<script>
	$(document).ready(function(){
		if(!${isTransferEnable}){
			$("#tr_transfer").hide();
		}
		if(!${isBorrowEnable}){
			$("#tr_read_br").hide();
			$("#tr_read_rt").hide();
		}
		if(!${isDeviceEnable}){
			$("#tr_device_br").hide();
			$("#tr_device_rt").hide();
		}
		if(!${isCopyEnable}){
			$("#tr_copy").hide();
		}
		if(!${isLeadinEnable}){
			$("#tr_enter").hide();
		}
		/* if(!${isStorageEnable}){
			$("#tr_storage_br").hide();
			$("#tr_storage_rt").hide();
		} */
	});
    function openConfirm(module){
		var url = "${ctx}/basic/configconfirm.action?update=Y&module="+escape(module)+"&type=open";
		//alert(url);
		callServer(url);
	}
	function closeConfirm(module){
		var url = "${ctx}/basic/configconfirm.action?update=Y&module="+escape(module)+"&type=close";
		//alert(url);
		callServer(url);
	}
	function updateResult(){
		if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
			alert("修改成功");
			$("#TemplateQueryCondForm").submit();
		}
	}
</script>
<body oncontextmenu="self.event.returnValue=false" onload="onHover();" style="padding-top: 80px">
<center>
<form id="TemplateQueryCondForm" method="POST" action="${ctx}/basic/configconfirm.action">
<table class="table_box" cellspacing=0 cellpadding=0 border=1 width="50%">
	<tr>
		<td colspan="3" class="title_box">&nbsp;&nbsp;交接确认配置</td>
	</tr>	
	<%-- <tr>
		<td width="40%" align="center">回收模块：</td>
		<td align="center">
			<c:choose>
				<c:when test="${retrieve_start == 1}">
					<input type="button" class="button_2003" value="已经开启" disabled="disabled"/>&nbsp;&nbsp;&nbsp;&nbsp;																		
					<input type="button" class="button_2003" value="关闭交接" onclick="closeConfirm('RETRIEVE');"/>
				</c:when>
				<c:otherwise>
					<input type="button" class="button_2003" value="开启交接" onclick="openConfirm('RETRIEVE');"/>&nbsp;&nbsp;&nbsp;&nbsp;																		
					<input type="button" class="button_2003" value="已经关闭" disabled="disabled"/>
				</c:otherwise>
			</c:choose>
		</td>
	</tr> --%>
	<tr>
		<td align="center">归档模块：</td>
		<td align="center">
			<c:choose>
				<c:when test="${file_start == 1}">
					<input type="button" class="button_2003" value="已经开启" disabled="disabled"/>&nbsp;&nbsp;&nbsp;&nbsp;																		
					<input type="button" class="button_2003" value="关闭交接" onclick="closeConfirm('FILE');"/>
				</c:when>
				<c:otherwise>
					<input type="button" class="button_2003" value="开启交接" onclick="openConfirm('FILE');"/>&nbsp;&nbsp;&nbsp;&nbsp;																		
					<input type="button" class="button_2003" value="已经关闭" disabled="disabled"/>
				</c:otherwise>
			</c:choose>																	
		</td>
	</tr>
	<tr id="tr_copy">
		<td align="center">复印模块：</td>
		<td align="center">
			<c:choose>
				<c:when test="${copy_start == 1}">
					<input type="button" class="button_2003" value="已经开启" disabled="disabled"/>&nbsp;&nbsp;&nbsp;&nbsp;																		
					<input type="button" class="button_2003" value="关闭交接" onclick="closeConfirm('COPY');"/>
				</c:when>
				<c:otherwise>
					<input type="button" class="button_2003" value="开启交接" onclick="openConfirm('COPY');"/>&nbsp;&nbsp;&nbsp;&nbsp;																		
					<input type="button" class="button_2003" value="已经关闭" disabled="disabled"/>
				</c:otherwise>
			</c:choose>
		</td>						
	</tr>
	<tr id="tr_enter">
		<td align="center">录入模块：</td>
		<td align="center">
			<c:choose>
				<c:when test="${enter_start == 1}">
					<input type="button" class="button_2003" value="已经开启" disabled="disabled"/>&nbsp;&nbsp;&nbsp;&nbsp;																		
					<input type="button" class="button_2003" value="关闭交接" onclick="closeConfirm('ENTER');"/>
				</c:when>
				<c:otherwise>
					<input type="button" class="button_2003" value="开启交接" onclick="openConfirm('ENTER');"/>&nbsp;&nbsp;&nbsp;&nbsp;																		
					<input type="button" class="button_2003" value="已经关闭" disabled="disabled"/>
				</c:otherwise>
			</c:choose>
		</td>						
	</tr>	
	<tr id="tr_device_br">
		<td align="center">磁介质借用模块：</td>
		<td align="center">
			<c:choose>
				<c:when test="${device_br_start == 1}">
					<input type="button" class="button_2003" value="已经开启" disabled="disabled"/>&nbsp;&nbsp;&nbsp;&nbsp;																		
					<input type="button" class="button_2003" value="关闭交接" onclick="closeConfirm('DEVICE_BR');"/>
				</c:when>
				<c:otherwise>
					<input type="button" class="button_2003" value="开启交接" onclick="openConfirm('DEVICE_BR');"/>&nbsp;&nbsp;&nbsp;&nbsp;																		
					<input type="button" class="button_2003" value="已经关闭" disabled="disabled"/>
				</c:otherwise>
			</c:choose>
		</td>
	</tr>	
	<tr id="tr_device_rt">
		<td align="center">磁介质归还模块：</td>
		<td align="center">
			<c:choose>
				<c:when test="${device_rt_start == 1}">
					<input type="button" class="button_2003" value="已经开启" disabled="disabled"/>&nbsp;&nbsp;&nbsp;&nbsp;																		
					<input type="button" class="button_2003" value="关闭交接" onclick="closeConfirm('DEVICE_RT');"/>
				</c:when>
				<c:otherwise>
					<input type="button" class="button_2003" value="开启交接" onclick="openConfirm('DEVICE_RT');"/>&nbsp;&nbsp;&nbsp;&nbsp;																		
					<input type="button" class="button_2003" value="已经关闭" disabled="disabled"/>
				</c:otherwise>
			</c:choose>
		</td>
	</tr>	 
	<%-- <tr id="tr_storage_br">
		<td align="center">存储介质分配模块：</td>
		<td align="center">
			<c:choose>
				<c:when test="${storage_br_start == 1}">
					<input type="button" class="button_2003" value="已经开启" disabled="disabled"/>&nbsp;&nbsp;&nbsp;&nbsp;																		
					<input type="button" class="button_2003" value="关闭交接" onclick="closeConfirm('STORAGE_BR');"/>
				</c:when>
				<c:otherwise>
					<input type="button" class="button_2003" value="开启交接" onclick="openConfirm('STORAGE_BR');"/>&nbsp;&nbsp;&nbsp;&nbsp;																		
					<input type="button" class="button_2003" value="已经关闭" disabled="disabled"/>
				</c:otherwise>
			</c:choose>
		</td>
	</tr>	
	<tr id="tr_storage_rt">
		<td align="center">存储介质归还模块：</td>
		<td align="center">
			<c:choose>
				<c:when test="${storage_rt_start == 1}">
					<input type="button" class="button_2003" value="已经开启" disabled="disabled"/>&nbsp;&nbsp;&nbsp;&nbsp;																		
					<input type="button" class="button_2003" value="关闭交接" onclick="closeConfirm('STORAGE_RT');"/>
				</c:when>
				<c:otherwise>
					<input type="button" class="button_2003" value="开启交接" onclick="openConfirm('STORAGE_RT');"/>&nbsp;&nbsp;&nbsp;&nbsp;																		
					<input type="button" class="button_2003" value="已经关闭" disabled="disabled"/>
				</c:otherwise>
			</c:choose>
		</td>
	</tr> --%>  
	<tr id="tr_read_br">
		<td align="center">部门载体借用模块：</td>
		<td align="center">
			<c:choose>
				<c:when test="${read_br_start == 1}">
					<input type="button" class="button_2003" value="已经开启" disabled="disabled"/>&nbsp;&nbsp;&nbsp;&nbsp;																		
					<input type="button" class="button_2003" value="关闭交接" onclick="closeConfirm('READ_BR');"/>
				</c:when>
				<c:otherwise>
					<input type="button" class="button_2003" value="开启交接" onclick="openConfirm('READ_BR');"/>&nbsp;&nbsp;&nbsp;&nbsp;																		
					<input type="button" class="button_2003" value="已经关闭" disabled="disabled"/>
				</c:otherwise>
			</c:choose>
		</td>
	</tr>
	<tr id="tr_read_rt">
		<td align="center">部门载体归还模块：</td>
		<td align="center">
			<c:choose>
				<c:when test="${read_rt_start == 1}">
					<input type="button" class="button_2003" value="已经开启" disabled="disabled"/>&nbsp;&nbsp;&nbsp;&nbsp;																		
					<input type="button" class="button_2003" value="关闭交接" onclick="closeConfirm('READ_RT');"/>
				</c:when>
				<c:otherwise>
					<input type="button" class="button_2003" value="开启交接" onclick="openConfirm('READ_RT');"/>&nbsp;&nbsp;&nbsp;&nbsp;																		
					<input type="button" class="button_2003" value="已经关闭" disabled="disabled"/>
				</c:otherwise>
			</c:choose>
		</td>
	</tr>
	<tr id="tr_transfer">
		<td align="center" rowspan="2">流转模块：</td>
		<td align="center">
			<c:choose>
				<c:when test="${transfer_start == 1}">
					<input type="button" class="button_2003" value="已经开启" disabled="disabled"/>&nbsp;&nbsp;&nbsp;&nbsp;																		
					<input type="button" class="button_2003" value="关闭交接" onclick="closeConfirm('TRANSFER');"/>
				</c:when>
				<c:otherwise>
					<input type="button" class="button_2003" value="开启交接" onclick="openConfirm('TRANSFER');"/>&nbsp;&nbsp;&nbsp;&nbsp;																		
					<input type="button" class="button_2003" value="已经关闭" disabled="disabled"/>
				</c:otherwise>
			</c:choose>
		</td>						
	</tr>
	<tr>
		<td>
			<font color="red" size="2">
				若开启流转交接，则需要删除普通用户的”载体接收确认“权限；</br>若关闭流转交接，则需要赋予普通用户的”载体接收确认“权限
			</font>
		</td>						
	</tr>  	
	<tr>
        <td colspan="3" align="center" class="bottom_box">
            <input type="button" value="返回" class="button_2003" onclick="history.go(-1);" style="display:none"/>
        </td>
    </tr>
</table>
</form>
</center>
</body>
</html>