<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<title>提交审批</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="${ctx}/_css/displaytag.css" media="screen"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/_css/css200603.css"  media="screen"/>
    <link href="${ctx}/_script/calendar2/calendar-blue.css" type="text/css" rel="stylesheet"/>
    <script language="javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>
    <script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
    <script language="javascript" src="${ctx}/_script/calendar2/calendar.js"></script>
    <script>
    $(document).ready(function(){
		onHover();
		$("#color").val("${color}");
		$("#print_type").val("${print_type}");
		addSelectAllCheckbox();
		preCalendar();
	});
	function clearFindForm(){
		$("#QueryCondForm :text").val("");
		$("#QueryCondForm select").val("");
	}
	function addPrintJob(){
		var checked = $("input[name='_chk']:checked").size();
		var hidded = $("input[name='_chk'][type='hidden']").size();
		if(checked+hidded >0){
			$("#QueryCondForm").attr("action","${ctx}/print/addprintprocessjob.action");
			$("#QueryCondForm").submit();
		}else{
			alert("请先勾选需要审批的作业");
			return false;
		}
	}
	//显示每条作业的邦辰关键字检查情况
	function ShowRisklist(st_filename,event_code){
	    if("Microsoft Internet Explorer"==navigator.appName){
		var url ="${ctx}/print/showrisklist.action?st_filename="+st_filename+"&event_code="+event_code;
		window.showModalDialog(url,window,'dialogHeight:600px;dialogWidth:700px;center:yes;status:no;scroll:no;help:no;unadorned:no;resizable:no');
		//var rValue=window.showModalDialog(urlEnsure,'', 'dialogHeight:600px;dialogWidth:700px;center:yes;status:no;scroll:no;help:no;unadorned:no;resizable:no');
		return false;
		}
	}
	function showPrintFile(filestorename,pagecount){    
	    if("Microsoft Internet Explorer"==navigator.appName){
		var FileStoreNameLen = filestorename.length;
		var unzipdirname = filestorename.substr(0,FileStoreNameLen-4);
		var url ="${ctx}/print/showprintfile.action?unzipdirname="+unzipdirname+"&pagecount="+pagecount;
		window.showModalDialog(url,window,"dialogHeight:"+(screen.availHeight-40)+";dialogWidth:"+(screen.availWidth-5)+";center:yes;resizable:no;status:no;scroll:no;help:no");
		return false;
		}
		else{
			var FileStoreNameLen = filestorename.length;
			var unzipdirname = filestorename.substr(0,FileStoreNameLen-4);	
			var url ="${ctx}/print/showprintfilelinux.action?unzipdirname="+unzipdirname;
			window.showModalDialog(url,window);
			//onunload = live();
			return false;
		}
		
	}
	function delMultiEvent(){
		var checked = $("input[name='_chk']:checked").size();
		var hidded = $("input[name='_chk'][type='hidden']").size();
		if(checked+hidded >0){
			$("#QueryCondForm").attr("action","${ctx}/print/delmultiprintevent.action");
			$("#QueryCondForm").submit();
		}else{
			alert("请先勾选需要删除的作业");
			return false;
		}
	}
    </script>
</head>
<body oncontextmenu="self.event.returnValue=false">
<form id="QueryCondForm" method="post" action="${ctx}/print/submitprintevent.action">
	<input type="hidden" name="jobType" value="${jobType}"/>
	<input type="hidden" name="actionContext" value="print/submitprintevent.action"/>
<table width="95%" border="0" cellspacing="0" cellpadding="0" align="center" class="table_only_border">
	<tr>
		<td class="title_box">待提交打印作业</td>
	</tr>
	<tr>
		<td align="right">
				<table border=0 class="table_box" cellspacing="0" cellpadding="0" width="100%">
					<tr>
						<td width="30%" align="center">文件名：
				 			<input type="text" name="file_title" style="cursor:hand;" value="${file_title}"/>
				 		</td>
			    		<td width="30%" align="center">提交时间：
				        	<input type="text" name="startTime" readonly="readonly" style="cursor:hand;" value="${startTime_str}"/>
        					<img src="${ctx}/_image/time2.jpg" id="startTime_trigger">
			    		</td> 
			    		<td width="30%" align="center">至：
				        	<input type="text" name="endTime" readonly="readonly" style="cursor:hand;" value="${endTime_str}"/>
        					<img src="${ctx}/_image/time2.jpg" id="endTime_trigger">
			    		</td> 
				        <td width="10%" align="center">
							<input name="button" type="submit" class="button_2003" value="查询" onclick="return checkTime();">&nbsp;&nbsp;
							<input name="button" type="button" class="button_2003" value="清空" onclick="clearFindForm();">
						</td>
					</tr>
				</table>
		</td>
	</tr>

	<tr>
		<td valign="top">
			<table class="displaytable-outter" cellspacing=0 cellpadding=0 border=0 width="100%">
	 			<tr>
	   				<td>
					<display:table requestURI="${ctx}/print/submitprintevent.action" id="item" class="displaytable" name="eventList"
	   					pagesize="${pageSize}" sort="page" partialList="true" size="${totalSize}" excludedParams="*" form="QueryCondForm" decorator="decorator">
		   				<display:column property="checkbox" title="选择"></display:column>
		   				<%-- <display:column>
		   					<c:choose>
								<c:when test="${item.policy eq 'YES' or item.policy eq 'NO'}">
									<input type="checkbox" name="_chk"/>
								</c:when>
								<c:otherwise>
									<input type="checkbox" name="_chk" disabled="disabled"/>
								</c:otherwise>
							</c:choose>
		   				</display:column> --%>
						<display:column title="序号">
							<c:out value="${item_rowNum}"/>
						</display:column>
						<display:column title="文件名称" property="file_title" maxLength="45"/>
						<display:column title="页数" property="page_count"/>
						<display:column title="份数" property="print_count"/>
						<display:column title="色彩" property="color_name"/>
						<display:column title="打印类型" property="print_type_name"/>
						<display:column title="关键字检查结果" >
						    <c:if test="${item.policy eq 'YES' }">
	  					        <u><label style="color: red;cursor: pointer;" onclick="ShowRisklist('${item.st_filename}','${item.event_code}');">含敏感信息，请点击查看&nbsp;</label></u>
	  				        </c:if>
	  				        <c:if test="${item.policy eq 'NO' }">
	  					        	<font color="green">未检测到敏感信息&nbsp;</font>
	  				        </c:if>
	  				        <c:if test="${item.policy eq 'Checking' or item.policy eq ''}">
	  					        	<font color="red">敏感信息检测中</font>&nbsp;
	  				        </c:if>
	  				        <c:if test="${item.policy eq 'RequestFailed'}">
	  					       		<font color="red">提交检测失败</font>&nbsp;
	  				        </c:if>
	  				        <c:if test="${item.policy eq 'Failed' }">
	  					         	<font color="red">检测异常</font>&nbsp;
	  				        </c:if> 				
						</display:column>
						<display:column title="提交时间" property="apply_time_str" sortable="true"/>	
						<display:column title="操作" style="width:200px">	
							<input type="button" class="button_2003" value="预览" onclick="showPrintFile('${item.st_filename}','${item.page_count}');"/>&nbsp;			
							<input type="button" class="button_2003" value="查看" onclick="go('${ctx}/print/viewprinteventdetail.action?id=${item.id}');"/>&nbsp;
							<input type="button" class="button_2003" value="编辑" onclick="go('${ctx}/print/updateprintevent.action?id=${item.id}');"/>&nbsp;
							<input type="button" class="button_2003" value="删除" onclick="if(confirm('确定要删除该作业？'))go('${ctx}/print/delprintevent.action?key=byId&id=${item.id}');"/>
						</display:column>
					</display:table>
					</td>
				</tr>
			</table>
         </td>
	</tr>
	<tr>
		<td>
			<input type="button" value="申请打印" onclick="addPrintJob();" class="button_2003"/>&nbsp;&nbsp;
			<input type="button" value="批量删除" onclick="delMultiEvent();" class="button_2003"/>
		</td>
	</tr>
</table>
</form>
</body>
</html>
