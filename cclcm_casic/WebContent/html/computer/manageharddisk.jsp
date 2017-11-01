<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<title>查看硬盘台帐</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="${ctx}/_css/displaytag.css" media="screen"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/_css/css200603.css"  media="screen"/>
    <link href="${ctx}/_script/calendar2/calendar-blue.css" type="text/css" rel="stylesheet"/>
    <script language="javascript" src="${ctx}/_script/calendar2/calendar.js"></script>
    <script language="javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>
    <script language="javascript" src="${ctx}/_script/casic304_common.js"></script>   
    <script language="javascript" src="${ctx}/_script/casic304_ajax.js"></script>
    <script language="javascript" src="${ctx}/_script/My97DatePicker/WdatePicker.js"></script>
	<script>
	//人员匹配
	function selectRecvUser(word){
			var url = "${ctx}/basic/getfuzzyuser.action?source=CR";
			if(word != ""){
				callServer1(url,"fuzzy="+word);
			}else{
				document.getElementById("allOptions").innerHTML="";
			}
	}
	function updateResult(){
			if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
					if(xmlHttp.responseText.toString().length > 154){
						document.getElementById("allOptions").innerHTML=xmlHttp.responseText;
					}else{
						document.getElementById("allOptions").innerHTML="";
					}
				}else{
					document.getElementById("allOptions").innerHTML="";
				}
	}
	
	
	function add_TrueCR(){
			var user_id=$("#allOptionCR").val();
			var user_name=$("#allOptionCR option[value='"+user_id+"']").text();
			if(user_id != ""){
				$("#duty_user_id").val(user_id);
				$("#duty_user_name").val(user_name);
				document.getElementById("allOptions").innerHTML="";
			}
	}
	function exportHardDisk(formId,url,url1){
			document.getElementById(formId).action = url;
			document.getElementById(formId).submit();
			document.getElementById(formId).action = url1;
	}

	function clearFindForm(){
		$("#QueryCondForm :text").val("");
		$("#QueryCondForm select").val("");	
	//	$("#QueryCondForm :hidden").val("");		
	}		
	function DeleteEntity(hdisk_no){
		if(confirm("确定删除该硬盘台账？")){
			go("${ctx}/computer/deleteharddisk.action?hdisk_no="+escape(hdisk_no));
		}
	}		
	</script>
</head>
<body oncontextmenu="self.event.returnValue=false">
<form id="QueryCondForm" name="QueryCondForm" method="POST" action="${ctx}/computer/manageharddisk.action" >
<input type="hidden" name="type" value="${type}" id="type"/>
<input type="hidden" name="computer_barcode" id="computer_barcode"/> 
<input type="hidden" name="dept_ids" value="${dept_ids}" id="dept_ids"/>
<table width="95%" border="0" cellspacing="0" cellpadding="0" align="center" class="table_only_border">
	<tr>
		<td class="title_box">硬盘列表</td>
	</tr>	
	<tr>
		<td align="right">
	 		<table border=0 class="table_box" cellspacing="0" cellpadding="0" width="100%">
				<tr>				 				 		
					<td width ="10%" align="center">责任人</td>
						<td align="center">
					    	<input type="text" id="duty_user_name" name="duty_user_name" value="${duty_user_name}" onkeyup="selectRecvUser(this.value);"/>
							<input type="hidden" id="duty_user_id" name="duty_user_id" value="${duty_user_id}"/><br>
							<div id="allOptions" class="containDiv" style="position:absolute;border:0px solid black;padding:0px">
						</td> 
					<td align="center" width="10%">所属部门</td>
					<td align="center">
						<input type="text" id="duty_dept_name" name="duty_dept_name" value="${duty_dept_name}" readonly="readonly" style="cursor:hand" onclick="openDeptSelect('duty_dept_name','duty_dept_id','radio')"/>
						<input type="hidden" id="duty_dept_id" name="duty_dept_id" value="${duty_dept_id}"/><br>
						</td>
					<td align="center" width="10%">所属单位</td>
		            <td>
		   <select name="duty_entp_id" id="duty_entp_id">
				<option value="">--请选择--</option>
				<option value="1">总部</option>
				<option value="2">24所</option>
				<option value="3">26所</option>
				<option value="4">44所</option>
    		</select>&nbsp;	
		</td>		
					<td align="center" width="10%">计算机保密编号</td>
			 		<td align="center">
			 			<input type="text" id="conf_code" name="conf_code" value="${conf_code}"/>
			 		</td>
					
															 		
				</tr>
				<tr>
			 		<td align = "center">硬盘序列号</td>
			 		<td align="center">
			 		   	<input type="text" id="hdisk_no" name="hdisk_no" value="${hdisk_no}"/>
			 		</td>				 		
			      <td align="center">回收人</td>
						<td align="center">
							<input type="text" id="retrieve_user_name" name="retrieve_user_name" value="${retrieve_user_name}"/>&nbsp;
						</td> 
						<td width="8%" align="center">回收时间</td>
					 	<td width="17%" >起
						 <input type="text" name="start_time" id="start_time" onclick="WdatePicker()" class="Wdate" size="10"/> 			
				    </td> 
                   <td width="8%" align="center">止</td>
				    	<td width="17%" >
						 <input type="text" name="end_time" id="end_time" onclick="WdatePicker()" class="Wdate" size="10"/> 			
				    	</td> 
				    	<td></td>
				    </tr>
				    <tr>			
			 		 <td align="left" colspan="8">
						<input name="button" type="submit" class="button_2003" value="查询" onclick="return;">&nbsp;&nbsp;
						<input name="button" type="button" class="button_2003" value="清空" onclick="clearFindForm();">&nbsp;&nbsp;
						<input type="button" class="button_2003" value="导出EXCEL" onclick="exportHardDisk('QueryCondForm','${ctx}/computer/exportharddisk.action','${ctx}/computer/manageharddisk.action');"/>
			 		</td>					 						
				</tr>
			</table>	
	  </td>
	</tr>
	<tr>
		<td>
			<table class="displaytable-outter" cellspacing=0 cellpadding=0 border=0 width="100%">
	 			
	 			<tr>
	   				<td>
					<display:table requestURI="${ctx}/computer/manageharddisk.action" id="item" class="displaytable" name="hdiskList" 
					pagesize="15" sort="list" excludedParams="*" form="QueryCondForm">
						<display:column title="序号">
							<c:out value="${item_rowNum}"/>
						</display:column>
						<display:column property="duty_user_name" title="责任人" maxLength="15"/>
						<display:column property="duty_dept_name" title="所属部门" maxLength="15"/>
						<display:column property="duty_entp_name" title="所属单位" maxLength="15"/>
						<display:column property="conf_code" title="计算机保密编号"  />
						<display:column property="hdisk_no" title="硬盘序列号" />
						<display:column property="retrieve_time_str" title="回收日期"/>
						<display:column property="retrieve_user_name" title="回收人"/>
						<display:column title="操作" maxLength="150">					
							    <input type="button" class="button_2003" value="详细" onclick="go('${ctx}/computer/viewharddiskdetail.action?hdisk_no=${item.hdisk_no}');"/>&nbsp;
								<input type="button" class="button_2003" value="修改" onclick="go('${ctx}/computer/updateharddisk.action?hdisk_no=${item.hdisk_no}');"/>
								<input type="button" class="button_2003" value="删除" onclick="DeleteEntity('${item.hdisk_no}');"/>					
						</display:column>
					</display:table>
					</td>
				</tr>
			</table>
         </td>
	</tr>
</table>

</form>
</body>
</html>
