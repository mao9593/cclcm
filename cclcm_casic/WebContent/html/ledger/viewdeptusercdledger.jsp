<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<title>部门用户光盘台账列表</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="${ctx}/_css/css200603.css" type="text/css" media="screen" />
		<link rel="stylesheet" type="text/css" href="${ctx}/_css/displaytag.css" media="screen"/>
	<link href="${ctx}/_script/calendar2/calendar-blue.css" type="text/css" rel="stylesheet"/>
	<script language="javascript" src="${ctx}/_script/calendar2/calendar.js"></script>
	<script language="javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>
	<script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
	<script language="javascript" src="${ctx}/_script/casic304_ajax.js"></script>
	<script>
	$(document).ready(function(){
		onHoverInfinite();
		preCalendar();
		optionSelect();
	});
	function selectRecvUser(word){
	    var url = "${ctx}/basic/getfuzzyuser.action";
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
	function add_True(){
		var duty_user_iidd=$("#allOption").val();
		var duty_user_name=$("#allOption option[value='"+duty_user_iidd+"']").text();
		duty_user_name=duty_user_name.substring(0,duty_user_name.indexOf("/"));
		if(duty_user_iidd != ""){
			$("#duty_user_iidd").val(duty_user_iidd);
			$("#duty_user_name").val(duty_user_name);
			document.getElementById("allOptions").innerHTML="";
		}
	}
	function clearFindForm(){
		$("#LedgerQueryCondForm :text").val("");
		$("#LedgerQueryCondForm select").val("");
		$("#LedgerQueryCondForm :hidden").val("");
		$(":checkbox[name='seclv_code']").attr("checked",false);
	}	
	function optionSelect(){
		//$("#seclv_code").val("${seclv_code}");
		$("#cd_state").val("${cd_state}");
		$("#duty_dept_id").val("${duty_dept_id}");
		$("#create_type").val("${create_type}");
		var codes='${seclv_code}';
		if($(":checkbox[name='seclv_code']").size()>0){
		  $(":checkbox[name='seclv_code']").each(function(){
		    var code=codes.split(",");
		    for(var i=0;i<code.length;i++){
		      if(code[i].trim()==this.value){
		       $(":checkbox[name='seclv_code'][value='"+this.value+"']").attr("checked",true);
		      }
		    }
		  
		  })
		}
	}
	function exportLedger(formId,url,url1){
		document.getElementById(formId).action = url;
		document.getElementById(formId).submit();
		document.getElementById(formId).action = url1;
	}
	</script>
</head>
<body oncontextmenu="self.event.returnValue=false">
<table width="95%" border="0" cellspacing="0" cellpadding="0" align="center" class="table_only_border">
	<tr>
		<td class="title_box">部门用户光盘台账列表</td>
	</tr>
	<form id="LedgerQueryCondForm" method="post" action="${ctx}/ledger/viewdeptusercdledger.action">
	<input type="hidden" name="dept_ids" id="dept_ids" value="${dept_ids}" />
	<tr>
		<td align="right">
			<table  table border=0 class="table_box" cellspacing="0" cellpadding="0" width="100%">
				<tr>
					<td width="6%" align="center">责任人：</td>
					<td width="17%">
						<input type="text" id="duty_user_name" name="duty_user_name" value="${duty_user_name}" onkeyup="selectRecvUser(this.value);"/><br>
    		            <div id="allOptions" class="containDiv" style="position:absolute;border:0px solid black;padding:0px">
    	        	    </div>
					</td>
					<td width="6%" align="center">光盘条码：</td>
					<td width="25%">
						<input type="text" id="cd_barcode" name="cd_barcode" value="${cd_barcode}"/>
					</td>
					<td width="6%" align="center">保密编号：</td>
					<td width="17%">
						<input type="text" id="conf_code" name="conf_code" value="${conf_code}"/>
					</td>
					<td width="6%" align="center">文件列表：</td>
					<td width="17%">
						<input type="text" id="file_list" name="file_list" value="${file_list}"/>
					</td>
				</tr>
				<tr>
					<td align="center">责任部门：</td>
					<td>
						<select id="duty_dept_id" name="duty_dept_id">
							<option value="">--不限--</option>
							<s:iterator value="secAdminDeptList" var="dept">
								<option value="${dept.dept_id}">${dept.dept_name}</option>	
							</s:iterator>
						</select>
					</td>
					<td align="center">密级：</td>
					<td>
						<%-- <select name="seclv_code" id="seclv_code">
							<option value="">--所有--</option>
							<s:iterator value="#request.seclvList" var="seclv">
								<option value="${seclv.seclv_code}">${seclv.seclv_name}</option>
							</s:iterator>
						</select> --%>
						<s:iterator value="#request.seclvList" var="seclv">
							<input type="checkbox" value="${seclv.seclv_code}" name="seclv_code" id="seclv_code"/>${seclv.seclv_name}
						</s:iterator>	
					</td>
					<td align="center">状态：</td>
					<td>
						<select name="cd_state" id="cd_state">
							<option value="">--所有--</option>
							<s:iterator value="#request.stateList" var="state">
							<option value="${state.key}">${state.name}</option>
							</s:iterator>				
						</select>
					</td>									
					<td align="center">制作方式：</td>
					<td>
						<select name="create_type" id="create_type">
							<option value="">--所有--</option>
    						<option value="BURN">刻录</option>
    						<option value="LEADIN">录入</option>					
						</select>
					</td>
				</tr>
				<tr>
					<td align="center">产生时间：</td>
					<td>
						<input type="text" name="startTime" readonly="readonly" style="cursor:hand;" value="${startTime}"/>
        				<img src="${ctx}/_image/time2.jpg" id="startTime_trigger">
					</td>
					<td align="center">至：</td>
					<td>
						<input type="text" name="endTime" readonly="readonly" style="cursor:hand;" value="${endTime}"/>
        				<img src="${ctx}/_image/time2.jpg" id="endTime_trigger">
					</td>
					<td align="center">到期状态：</td>
					<td>
			    		<select name="expire_status" id="expire_status">
			    			<option value="">--所有--</option>
			    			<option value="0" <c:if test="${expire_status ==0}">selected</c:if>>未到期</option>
			    			<option value="2" <c:if test="${expire_status ==2}">selected</c:if>>即将到期</option>
			    			<option value="1" <c:if test="${expire_status ==1}">selected</c:if>>已到期</option>
			    		</select>
					</td>
					<td align="center" colspan="2"> 
						<input name="button" type="submit" class="button_2003" value="查询" onclick="return checkTime();">&nbsp;&nbsp;
						<input name="button" type="button" class="button_2003" value="清空" onclick="clearFindForm();">
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
	   					<display:table requestURI="${ctx}/ledger/viewdeptusercdledger.action" uid="item" class="displaytable" name="cdLedgerList"
	   							pagesize="${pageSize}" sort="page" partialList="true" size="${totalSize}" form="LedgerQueryCondForm" excludedParams="*">		   							   					
							<display:column title="序号">
								<c:out value="${item_rowNum}"/>
							</display:column>
							<display:column property="duty_user_name" title="责任人"/>
							<display:column property="duty_dept_name" title="责任部门" maxLength="20"/>
							<display:column property="cd_barcode" title="光盘条码" maxLength="25"/>
							<display:column property="conf_code" title="保密编号"/>
							<display:column property="file_list" title="文件列表" maxLength="30"/>
							<display:column property="seclv_name" title="介质密级"/>
							<display:column property="create_type_name" title="制作方式"/>
							<display:column property="cd_state_name" title="状态"/>
							<display:column title="到期状态">							
			   					<c:choose>
									<c:when test="${item.expire_status == 1}">
										<font color="red">${item.expire_status_name}</font>
									</c:when>
									<c:when test="${item.expire_status == 2}">
										<font color="orange">${item.expire_status_name}</font>
									</c:when>
									<c:otherwise>
										${item.expire_status_name}
									</c:otherwise>
								</c:choose>
							</display:column>
							<display:column property="burn_time" sortable="true" title="产生时间"/>
							<display:column title="操作" style="width:50px">
								<!-- <input type="button" class="button_2003" value="查看" onclick="go('${ctx}/ledger/viewledgerinfo.action?cd_id=${item.cd_id}');"/>&nbsp;-->
							 	<input type="button" class="button_2003" value="详细" onclick="go('${ctx}/ledger/viewcycledetail.action?type=DISC&barcode=${item.cd_barcode}&ledger_type=dept');"/>														 
							 </display:column>
						</display:table>
					</td>
				</tr>
			</table>
         </td>
	</tr>
	<tr><td><input type="button" class="button_2003" value="导出EXCEL" onclick="exportLedger('LedgerQueryCondForm','${ctx}/ledger/exportdeptusercdledger.action','${ctx}/ledger/viewdeptusercdledger.action');"/></td></tr>
</table>	
</form>
</body>
</html>
