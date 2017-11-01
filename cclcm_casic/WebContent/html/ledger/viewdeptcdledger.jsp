<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<title>部门光盘台账列表</title>
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
		addSelectAllCheckbox();
		preCalendar();
		optionSelect();
		if("${secError}" == "Y"){
			alert("勾选的载体密级不一致");
		}
	});
	function selectRecvUserCR(word){
	    var url = "${ctx}/basic/getfuzzyuser.action?source=CR";
	    if(word != ""){
		   callServer3(url,"fuzzy="+word);
	    }else{
		   document.getElementById("allOptionsCR").innerHTML="";
	         }
	} 
	function updateResult1(){
		if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
				if(xmlHttp.responseText.toString().length > 154){
					document.getElementById("allOptionsCR").innerHTML=xmlHttp.responseText;
				}else{
					document.getElementById("allOptionsCR").innerHTML="";
				}
			}else{
				document.getElementById("allOptionsCR").innerHTML="";
			}
	}
	function add_TrueCR(){
		var user_id=$("#allOptionCR").val();
		var user_name=$("#allOptionCR option[value='"+user_id+"']").text();
		user_name=user_name.substring(0,user_name.indexOf("/"));
		if(user_id != ""){
	    	$("#user_id").val(user_id);
			$("#user_name").val(user_name);
			document.getElementById("allOptionsCR").innerHTML="";
		}
	}
	function clearFindForm(){
		$("#LedgerQueryCondForm :text").val("");
		$("#LedgerQueryCondForm select").val("");
		$("#LedgerQueryCondForm :hidden").val("");
		$(":checkbox[name='seclv_code1']").attr("checked",false);	
	}	
	function optionSelect(){
		//$("#seclv_code").val("${seclv_code}");
		var codes='${seclv_code1}';
			if($(":checkbox[name='seclv_code1']").size()>0){
			  $(":checkbox[name='seclv_code1']").each(function(){
			    var code=codes.split(",");
			    for(var i=0;i<code.length;i++){
			      if(code[i].trim()==this.value){
			       $(":checkbox[name='seclv_code1'][value='"+this.value+"']").attr("checked",true);
			      }
			    }
			  
			  })
			}
		$("#cd_state").val("${cd_state}");
		$("#scope_dept_id").val("${scope_dept_id}");
	}
	function exportLedger(formId,url,url1){
		document.getElementById(formId).action = url;
		document.getElementById(formId).submit();
		document.getElementById(formId).action = url1;
	}
	function chkSubmit(){
		//提交之前把查询语句里的密级多选checkbox清空。解决载体提交的checkbox出现404错误。
		$.each($("input[name=seclv_code]"),function(i){
			$(this).removeAttr("checked");
		});
		
		var seclv_code = -1;
		var submitable = true;
		var cd_barcodes = "";
		if($(":checkbox:checked[value!='']").size()>0){
 			$(":checkbox:checked[value!='']").each(function (){
				cd_barcodes += this.value+",";
			    $("#cd_barcodes").val(cd_barcodes);
			});
			if(submitable){
					if($(":radio:checked").size() == 0){
					alert("请勾选闭环方式");
					$("radio:first").focus();
					return false;
				}else{
					$("#LedgerQueryCondForm").attr("action","${ctx}/basic/handlecdjob.action");
					$("#LedgerQueryCondForm").submit();
				}
				return true;
			}else{
				return false;
			}
		}else{
			alert("请先勾选光盘任务");
			return false;
		}
		}
		
	function SubmitAll()
	{
		
		if($("#seclv_code").val()== null || $("#seclv_code").val() == "")
		{
			alert("请先按照密级检索，只能提交相同密级的记录");
			return false;
		}
		
		if($("#cd_state").val()== null || $("#cd_state").val() == "" || $("#cd_state").val() != "0")
		{
			alert("请选择留用状态，只能提交留用状态的文件"); 
			return false;
		}
		
		if($(":radio:checked").size() == 0){
				alert("请勾选闭环方式");
				$("radio:first").focus();
				return false;
		}else{
			
			if($(":radio:checked").val()!="FILE_CD" && $(":radio:checked").val()!="DESTROY_CD")
			{
				alert("只有‘归档’和‘销毁’能够进行‘全部提交’！");
				return false;
			}else{
				if(confirm("您确认提交检索出的所有文件？"))
				{
					$("#hidjobType").val($(":radio:checked").val());
					if($("#cd_barcode").val() != "" && $("#cd_barcode").val() != null)
    				{
    					$("#hidcd_barcode").val($("#cd_barcode").val());
    				}
    				
    				if($("#conf_code").val() != "" && $("#conf_code").val() != null)
    				{
    					$("#hidconf_code").val($("#conf_code").val());
    				}
    				
    				if($("#file_list").val() != "" && $("#file_list").val() != null)
    				{
    					$("#hidfile_list").val($("#file_list").val());
    				}
    			
    				if($("#user_name").val() != "" && $("#user_name").val() != null)
    				{
    					$("#hiduser_name").val($("#user_name").val());
    				}
    				
    				if($("#scope_dept_id").val() != "" && $("#scope_dept_id").val() != null)
    				{
    					$("#hidscope_dept_id").val($("#scope_dept_id").val());
    				}
    				
    				if($("#cd_state").val() != "" && $("#cd_state").val() != null)
    				{
    					$("#hidcd_state").val($("#cd_state").val());
    				}
    				
    				if($("#seclv_code").val() != "" && $("#seclv_code").val() != null)
    				{
    					$("#hidseclv_code").val($("#seclv_code").val());
    				}
    				
    				if($("#dept_name").val() != "" && $("#dept_name").val() != null)
    				{
    					$("#hiddept_name").val($("#dept_name").val());
    				}
      				
    				if($("#startTime").val() != "" && $("#startTime").val() != null)
    				{
    					$("#hidstartTime").val($("#startTime").val());
    				}
    				
    				if($("#endTime").val() != "" && $("#endTime").val() != null)
    				{
    					$("#hidendTime").val($("#endTime").val());
    				}
    				
    				
    				
    				if($("#hid_dept_ids").val() != "" && $("#hid_dept_ids").val() != null)
    				{
    					$("#hiddept_ids").val($("#hid_dept_ids").val());
    				}
    				
					$("#hidSubmitAllForm").attr("action","${ctx}/basic/handlesubmitall.action");
					$("#hidSubmitAllForm").submit();
				}
			}
		}
		
	}
	</script>
</head>
<body oncontextmenu="self.event.returnValue=false">
<form id="hidSubmitAllForm" method="POST" action="${ctx}/basic/handlesubmitall.action">
	<input type="hidden" value="" id="hidcd_barcode" name="hidcd_barcode"/>
	<input type="hidden" value="" id="hidconf_code" name="hidconf_code"/>
	<input type="hidden" value="" id="hidfile_list" name="hidfile_list"/>
	<input type="hidden" value="" id="hiduser_name" name="hiduser_name"/>
	<input type="hidden" value="" id="hidscope_dept_id" name="hidscope_dept_id"/>
	<input type="hidden" value="" id="hidcd_state" name="hidcd_state"/>
	<input type="hidden" value="" id="hidseclv_code" name="hidseclv_code"/>
	<input type="hidden" value="" id="hiddept_name" name="hiddept_name"/>
	<input type="hidden" value="" id="hidstartTime" name="hidstartTime"/>
	<input type="hidden" value="" id="hidendTime" name="hidendTime"/>

	<input type="hidden" value="" id="hidjobType" name="hidjobType"/>
	<input type="hidden" value="" id="hiddept_ids" name="hiddept_ids"/>
	<input type="hidden" name="hidhandle_type" value="cd"/>
	<input type="hidden" name="isDept" value="Y"/>
</form>
<table width="95%" border="0" cellspacing="0" cellpadding="0" align="center" class="table_only_border">
	<input type="hidden" value="${hid_dept_ids}" id="hid_dept_ids" name="hid_dept_ids"/>
	<tr>
		<td class="title_box">部门光盘台账列表</td>
	</tr>
	<form id="LedgerQueryCondForm" method="post" action="${ctx}/ledger/viewdeptcdledger.action">
	<input type="hidden" name="isDept" value="Y"/>
	<input type="hidden" name="dept_ids" id="dept_ids" value="${dept_ids}" />
	<input type="hidden" id="cd_barcodes" name="cd_barcodes" value=""/>&nbsp;
	<tr>
		<td align="right">
			<table  table border=0 class="table_box" cellspacing="0" cellpadding="0" width="100%">
				<tr>
					<td align="center" width="8%">光盘条码：</td>
					<td width="15%">
						<input type="text" id="cd_barcode" name="cd_barcode" value="${cd_barcode}"/>
					</td>
					<td align="center" width="8%">保密编号：</td>
					<td width="15%">
						<input type="text" id="conf_code" name="conf_code" value="${conf_code}"/>
					</td>
					<td align="center" width="8%">文件列表：</td>
					<td width="20%">
						<input type="text" id="file_list" name="file_list" value="${file_list}"/>
					</td>
				</tr>
				<tr>
					<td align="center">归属部门：</td>
					<td>
						<select id="scope_dept_id" name="scope_dept_id">
							<option value="">--不限--</option>
							<s:iterator value="secAdminDeptList" var="dept">
								<option value="${dept.dept_id}">${dept.dept_name}</option>	
							</s:iterator>
						</select>
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
					<td align="center">密级：</td>
					<td>
						<%-- <select name="seclv_code" id="seclv_code">
							<option value="">--所有--</option>
							<s:iterator value="#request.seclvList" var="seclv">
								<option value="${seclv.seclv_code}">${seclv.seclv_name}</option>
							</s:iterator>
						</select> --%>
						<s:iterator value="#request.seclvList" var="seclv">
							<input type="checkbox" value="${seclv.seclv_code}" name="seclv_code1" id="seclv_code1"/>${seclv.seclv_name}
						</s:iterator>
					</td>
				</tr>
				<tr>
					<td align="center">产生时间：</td>
					<td>
						<input type="text" id="startTime" name="startTime" readonly="readonly" style="cursor:hand;" value="${startTime}"/>
        				<img src="${ctx}/_image/time2.jpg" id="startTime_trigger">
					</td>
					<td align="center">至：</td>
					<td>
						<input type="text" id="endTime" name="endTime" readonly="readonly" style="cursor:hand;" value="${endTime}"/>
        				<img src="${ctx}/_image/time2.jpg" id="endTime_trigger">
					</td>
					<td align="center" colspan="2" rowspan="2"> 
						<input name="button" type="submit" class="button_2003" value="查询" onclick="return checkTime();">&nbsp;&nbsp;
						<input name="button" type="button" class="button_2003" value="清空" onclick="clearFindForm();">
						<input type="button" class="button_2003" value="导出EXCEL" onclick="exportLedger('LedgerQueryCondForm','${ctx}/ledger/exportdeptcdledger.action','${ctx}/ledger/viewdeptcdledger.action');"/>
					</td>					
				</tr>
				<tr>
					<td align="center">制作人：</td>
					<td>
				    	<input type="text" id="user_name" name="user_name" value="${user_name}" onkeyup="selectRecvUserCR(this.value);"/>
						<input type="hidden" id="user_id" name="user_id" value="${user_id}"/><br>
						<div id="allOptionsCR" class="containDiv" style="position:absolute;border:0px solid black;padding:0px">
					</td>
					<td align="center">制作部门：</td>
					<td>
						<input type="text" id="dept_name" name="dept_name" value="${dept_name}" readonly="readonly" style="cursor:hand" onclick="openDeptSelect('dept_name','dept_id','radio')"/>
						<input type="hidden" id="dept_id" name="dept_id" value="${dept_id}"/><br>
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
	   					<display:table requestURI="${ctx}/ledger/viewdeptcdledger.action" uid="item" class="displaytable" name="cdLedgerList"
	   							pagesize="${pageSize}" sort="page" partialList="true" size="${totalSize}" form="LedgerQueryCondForm" excludedParams="*">		   							   					
							<display:column title="选择">							
			   					<c:choose>
									<c:when test="${item.cd_state == 0}">
										<input type="checkbox" name="_chk" value="${item.cd_barcode}" id="${item.seclv_code}"/>
									</c:when>
									<c:otherwise>
										${item.cd_state_name}
									</c:otherwise>
								</c:choose>
							</display:column>
							<display:column title="序号">
								<c:out value="${item_rowNum}"/>
							</display:column>
							<display:column property="user_name" title="制作人"/>
							<display:column property="dept_name" title="制作部门"/>
							<display:column property="cd_barcode" title="光盘条码" maxLength="25"/>
							<display:column property="conf_code" title="保密编号"/>
							<display:column property="file_list" title="文件列表" maxLength="30"/>
							<display:column property="seclv_name" title="介质密级"/>
							<display:column property="scope_dept_name" title="归属部门" maxLength="20"/>
							<display:column property="confidential_num" title="机要号"/>
							<display:column property="burn_time" sortable="true" title="产生时间"/>
							<display:column property="cd_state_name" title="状态"/>
							<display:column title="操作" style="width:50px">
								<!--  <input type="button" class="button_2003" value="查看" onclick="go('${ctx}/ledger/viewledgerinfo.action?cd_id=${item.cd_id}');"/>&nbsp;-->
								<input type="button" class="button_2003" value="详细" onclick="go('${ctx}/ledger/viewcycledetail.action?type=DISC&barcode=${item.cd_barcode}&ledger_type=dept');"/>							
							</display:column>
						</display:table>
					</td>
				</tr>
			</table>
         </td>
	</tr>
</table>
<table width="95%" align="center" border="0" cellspacing="0" cellpadding="0" align="center" class="table_only_border">
	<tr height="40">
		<td align="center">
			<label style="width: 80">
				<input type="radio" name="jobType" value="SEND_CD"/>外发
			</label>
			<label style="width: 80">
				<input type="radio" name="jobType" value="FILE_CD"/>归档
			</label>
			<label style="width: 80">  
				<input type="radio" name="jobType" value="DESTROY_CD_BYSELF"/>销毁
			</label>
			<label style="width: 80">
				<input type="radio" name="jobType" value="DELAY_CD"/>延期留用
			</label>
			<input type="button" value="批量提交" class="button_2003" style="margin-left: 30px" onclick="return chkSubmit();"/>
			<!-- <input type="button" value="全部提交" class="button_2003" style="margin-left: 30px" onclick="return SubmitAll();"/>
 -->		</td>
	</tr>
</table>
</form>
</body>
</html>
