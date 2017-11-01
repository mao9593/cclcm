<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<title>委托刻录光盘台账列表</title>
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
		//addSelectAllCheckbox();
		preCalendar();
		optionSelect();
		if("${secError}" == "Y"){
			alert("勾选的载体密级不一致");
		}
	});
	function clearFindForm(){
		$("#LedgerQueryCondForm :text").val("");
		$("#LedgerQueryCondForm select").val("");
	}	
	function optionSelect(){
		$("#seclv_code").val("${seclv_code}");
		$("#cd_state").val("${cd_state}");
	}
	/* function chkSubmit(){
		var checked = $("input[name='_chk']:checked").size();
		var hidded = $("input[name='_chk'][type='hidden']").size();
		if(checked+hidded == 0){
			alert("请先勾选需要闭环的载体");
			return false;
		}else if($(":radio:checked").size() == 0){
			alert("请勾选闭环方式");
			$("radio:first").focus();
			return false;
		}else{
			$("#LedgerQueryCondForm").attr("action","${ctx}/basic/handlecdjob.action");
			$("#LedgerQueryCondForm").submit();
		}
		return true;
	} */	
	function chkSubmit(){
		var seclv_code = -1;
		var submitable = true;
		var cd_barcodes = "";
		if($(":checkbox:checked[value!='']").size()>0){
			$(":checkbox:checked[value!='']").each(function (){
				cd_barcodes += this.value+",";
			    $("#cd_barcodes").val(cd_barcodes);
				if(seclv_code == -1 || seclv_code == this.id){					
					seclv_code = this.id;
				}else{
					alert("勾选的申请密级不一致，请确认");
					submitable =  false;
					return false;
				}
			});
			if(submitable){
					if($(":radio:checked").size() == 0){
					alert("请勾选闭环方式");
					$("radio:first").focus();
					return false;
				}else{
						if($(":radio:checked").val()!="CARRYOUT_CD"){
						$("#LedgerQueryCondForm").attr("action","${ctx}/basic/handlecdjob.action");
						$("#LedgerQueryCondForm").submit();
					}else{
						$.post(
							"${ctx}/ledger/checkorcarryout.action?barcodes="+cd_barcodes,
							function(data){
								if(data=="Y"){
									alert("你选择的光盘有未带回的,请重新选择！");
								}else{
										$("#LedgerQueryCondForm").attr("action","${ctx}/ledger/handlecdcarryoutjob.action");
										$("#LedgerQueryCondForm").submit();
								}
							}
						)
						
					}
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
    			
    				if($("#seclv_code").val() != "" && $("#seclv_code").val() != null)
    				{
    					$("#hidseclv_code").val($("#seclv_code").val());
    				}
    				
    				if($("#cd_state").val() != "" && $("#cd_state").val() != null)
    				{
    					$("#hidcd_state").val($("#cd_state").val());
    				}
    				
    				if($("#expire_status").val() != "" && $("#expire_status").val() != null)
    				{
    					$("#hidexpire_status").val($("#expire_status").val());
    				}
    				
    				if($("#startTime").val() != "" && $("#startTime").val() != null)
    				{
    					$("#hidstartTime").val($("#startTime").val());
    				}
    				
    				if($("#endTime").val() != "" && $("#endTime").val() != null)
    				{
    					$("#hidendTime").val($("#endTime").val());
    				}
    				
					$("#hidSubmitAllForm").attr("action","${ctx}/basic/handlesubmitall.action");
					$("#hidSubmitAllForm").submit();
				}
			}
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
<form id="hidSubmitAllForm" method="POST" action="${ctx}/basic/handlesubmitall.action">
	<input type="hidden" value="" id="hidcd_barcode" name="hidcd_barcode"/>
	<input type="hidden" value="" id="hidconf_code" name="hidconf_code"/>
	<input type="hidden" value="" id="hidseclv_code" name="hidseclv_code"/>
	<input type="hidden" value="" id="hidexpire_status" name="hidexpire_status"/>
	<input type="hidden" value="" id="hidstartTime" name="hidstartTime"/>
	<input type="hidden" value="" id="hidendTime" name="hidendTime"/>
	<input type="hidden" value="" id="hidcd_state" name="hidcd_state"/>
	<input type="hidden" value="" id="hidjobType" name="hidjobType"/>
	<input type="hidden" name="hidhandle_type" value="cd"/>
</form>

<table width="95%" border="0" cellspacing="0" cellpadding="0" align="center" class="table_only_border">
	<tr>
		<td class="title_box">委托刻录光盘台账列表</td>
	</tr>
	<form id="LedgerQueryCondForm" method="GET" action="${ctx}/ledger/viewproxycdledger.action">
	<input type="hidden" id="cd_barcodes" name="cd_barcodes" value=""/>&nbsp;
	<tr>
		<td align="right">
			<table border=0 class="table_box" cellspacing="0" cellpadding="0" width="100%">
				<tr>
					<td align="center" width="8%">光盘条码：</td>
			 		<td width="20%">
			 			<input type="text" id="cd_barcode" name="cd_barcode" value="${cd_barcode}"/>
			 		</td>
					<td align="center" width="8%">密级 ：</td>
			 		<td width="18%">
			 			<select name="seclv_code" id="seclv_code">
							<option value="">--所有--</option>
							<s:iterator value="#request.seclvList" var="seclv">
								<option value="${seclv.seclv_code}">${seclv.seclv_name}</option>
							</s:iterator>
						</select>
			 		</td>
			 		<td align="center">制作时间：</td>
			 		<td >
			        	<input type="text" name="startTime" readonly="readonly" style="cursor:hand;" value="${startTime}"/>
							<img src="${ctx}/_image/time2.jpg" id="startTime_trigger">&nbsp;
		    		</td> 
		    		<td align="center">至：</td>
			 		<td>
			        	<input type="text" name="endTime" readonly="readonly" style="cursor:hand;" value="${endTime}"/>
   						<img src="${ctx}/_image/time2.jpg" id="endTime_trigger">&nbsp;
		    		</td> 
			 		 <td align="center" colspan="2">
						<input name="button" type="submit" class="button_2003" value="查询" onclick="return checkTime();">&nbsp;
						<input name="button" type="button" class="button_2003" value="清空" onclick="clearFindForm();">&nbsp;&nbsp;
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
	   					<display:table requestURI="${ctx}/ledger/viewproxycdledger.action" uid="item" class="displaytable" name="cdLedgerList"
	   							pagesize="${pageSize}" sort="page" partialList="true" size="${totalSize}"  form="LedgerQueryCondForm" excludedParams="*">
							<display:column title="序号">
								<c:out value="${item_rowNum}"/>
							</display:column>		
							<display:column property="file_list" title="文件列表" maxLength="40"/>
							<display:column property="cd_barcode" title="光盘条码"/>
							<display:column property="conf_code" title="保密编号"/>
							<display:column property="seclv_name" title="密级"/>
							<display:column property="create_type_name" title="制作方式"/>
							<display:column property="burn_result_name" title="刻录结果"/>
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
							<display:column property="burn_time" sortable="true" title="制作时间"/>
							<display:column title="操作" style="width:50px">
								<%-- <input type="button" class="button_2003" value="查看" onclick="go('${ctx}/ledger/viewledgerinfo.action?cd_id=${item.cd_id}');"/>&nbsp;	 --%>							
								<input type="button" class="button_2003" value="详细" onclick="go('${ctx}/ledger/viewcycledetail.action?type=DISC&barcode=${item.cd_barcode}&ledger_type=personal');"/>								
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
