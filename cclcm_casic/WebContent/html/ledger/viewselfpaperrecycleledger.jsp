<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<title>用户纸质台账列表</title>
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
	function clearFindForm(){
		$("#LedgerQueryCondForm :text").val("");
		$("#LedgerQueryCondForm select").val("");
	}	
	function optionSelect(){
		$("#seclv_code").val("${seclv_code}");
		$("#paper_state").val("${paper_state}");
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
			$("#LedgerQueryCondForm").attr("action","${ctx}/basic/handlepaperjob.action");
			$("#LedgerQueryCondForm").submit();
		}
		return true;
	} */
	function chkSubmit(){
		var seclv_code = -1;
		var submitable = true;
		var paper_barcodes = "";
		if($(":checkbox:checked[value!='']").size()>0){
			$(":checkbox:checked[value!='']").each(function (){
			    paper_barcodes += this.value+",";
			    $("#paper_barcodes").val(paper_barcodes);
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
					if($(":radio:checked").val()!="CARRYOUT_PAPER"){
						$("#LedgerQueryCondForm").attr("action","${ctx}/basic/handlepaperjob.action");
						$("#LedgerQueryCondForm").submit();
					}else{
						
						
						$.post(
							"${ctx}/ledger/checkorcarryout.action?barcodes="+paper_barcodes,
							function(data){
								if(data=="Y"){
									alert("你选择的文件有未带回的,请重新选择！");
								}else{
									$("#LedgerQueryCondForm").attr("action","${ctx}/ledger/handlepapercarryoutjob.action");
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
			alert("请先勾选文件任务");
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
		
		if($("#paper_state").val()== null || $("#paper_state").val() == "" || $("#paper_state").val() != "0")
		{
			alert("请选择留用状态，只能提交留用状态的文件"); 
			return false;
		}
		
		if($(":radio:checked").size() == 0){
				alert("请勾选闭环方式");
				$("radio:first").focus();
				return false;
		}else{
			
			if($(":radio:checked").val()!="FILE_PAPER" && $(":radio:checked").val()!="DESTROY_PAPER")
			{
				alert("只有‘归档’和‘销毁’能够进行‘全部提交’！");
				return false;
			}else{
				if(confirm("您确认提交检索出的所有文件？"))
				{
					$("#hidjobType").val($(":radio:checked").val());
					if($("#paper_barcode").val() != "" && $("#paper_barcode").val() != null)
    				{
    					$("#hidpaper_barcode").val($("#paper_barcode").val());
    				}
    				
    				if($("#file_title").val() != "" && $("#file_title").val() != null)
    				{
    					$("#hidfile_title").val($("#file_title").val());
    				}
    			
    				if($("#keyword_content").val() != "" && $("#keyword_content").val() != null)
    				{
    					$("#hidkeyword_content").val($("#keyword_content").val());
    				}
    				
    				if($("#seclv_code").val() != "" && $("#seclv_code").val() != null)
    				{
    					$("#hidseclv_code").val($("#seclv_code").val());
    				}
    				
    				if($("#paper_state").val() != "" && $("#paper_state").val() != null)
    				{
    					$("#hidpaper_state").val($("#paper_state").val());
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
				//	var url = "{ctx}/basic/handlesubmitall.action";
				//	callServerPostForm(url,document.forms[0]);
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
<body oncontextmenu="self.event.returnValue=true">
<form id="hidSubmitAllForm" method="POST" action="${ctx}/basic/handlesubmitall.action">
	<input type="hidden" value="" id="hidfile_title" name="hidfile_title"/>
	<input type="hidden" value="" id="hidpaper_barcode" name="hidpaper_barcode"/>
	<input type="hidden" value="" id="hidkeyword_content" name="hidkeyword_content"/>
	<input type="hidden" value="" id="hidpaper_state" name="hidpaper_state"/>
	<input type="hidden" value="" id="hidseclv_code" name="hidseclv_code"/>
	<input type="hidden" value="" id="hidstartTime" name="hidstartTime"/>
	<input type="hidden" value="" id="hidendTime" name="hidendTime"/>
	<input type="hidden" value="" id="hidjobType" name="hidjobType"/>
	<input type="hidden" value="" id="hidexpire_status" name="hidexpire_status"/>
	<input type="hidden" name="hidhandle_type" value="paper"/>
	
</form>
<table width="95%" border="0" cellspacing="0" cellpadding="0" align="center" class="table_only_border">
	<input type="hidden" value="${paperBarcodeList}" id="paperBarcodeList"/>
	<tr>
		<td class="title_box">用户需回收纸质台账列表</td>
	</tr>
	<form id="LedgerQueryCondForm" method="GET" action="${ctx}/ledger/viewselfpaperrecycleledger.action">
	<input type="hidden" id="paper_barcodes" name="paper_barcodes" value=""/>&nbsp;
	<tr>
		<td align="right">
			<table  table border=0 class="table_box" cellspacing="0" cellpadding="0" width="100%">
				<tr>
					<td width="8%" align="center">文件名：</td>
					<td width="20%">
						<input type="text" id="file_title" name="file_title" value="${file_title}"/>&nbsp;
					</td>
					<td width="8%" align="center">文件条码：</td>
					<td width="17%">
						<input type="text" id="paper_barcode" name="paper_barcode" value="${paper_barcode}"/>&nbsp;
					</td>
					<td width="8%" align="center">关键字：</td>
					<td width="17%">
						<input type="text" id="keyword_content" name="keyword_content" value="${keyword_content}"/>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="center">密级：</td>
					<td>
						<select name="seclv_code" id="seclv_code">
							<option value="">--所有--</option>
							<s:iterator value="#request.seclvList" var="seclv">
								<option value="${seclv.seclv_code}">${seclv.seclv_name}</option>
							</s:iterator>
						</select>
					</td>			
					<td align="center">状态：</td>
					<td >
						<select name="paper_state" id="paper_state">
							<option value="">--所有--</option>
							<s:iterator value="#request.stateList" var="state">
							<option value="${state.key}">${state.name}</option>
							</s:iterator>			
						</select>
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
					<td align="center" colspan="2"> 
						&nbsp;<input name="button" type="submit" class="button_2003" value="查询" onclick="return checkTime();">
						&nbsp;<input name="button" type="button" class="button_2003" value="清空" onclick="clearFindForm();">&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="button" class="button_2003" value="导出EXCEL" onclick="exportLedger('LedgerQueryCondForm','${ctx}/ledger/exportpersonalpaperrecycleledger.action','${ctx}/ledger/viewselfpaperledger.action');"/>
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
	   					<display:table requestURI="${ctx}/ledger/viewselfpaperrecycleledger.action" uid="item" class="displaytable" name="paperLedgerList"
	   							pagesize="${pageSize}" sort="page" partialList="true" size="${totalSize}" form="LedgerQueryCondForm" excludedParams="*">		   							   					
		   					<display:column title="选择">							
			   					<span id="checkbox">
			   					<c:choose>
									<c:when test="${item.paper_state == 0}">
										<input type="checkbox" name="_chk" value="${item.paper_barcode}" id="${item.seclv_code}"/>
									</c:when>
									<c:otherwise>
										${item.paper_state_name}
									</c:otherwise>
								</c:choose>
								</span>
							</display:column>
							<display:column title="序号">
								<c:out value="${item_rowNum}"/>
							</display:column>
							<display:column title="文件名">
								<c:choose>
									<c:when test="${item.create_type_name=='被替换页' }">
										<font color="orange">${item.file_title}</font>
									</c:when>
									<c:otherwise>${item.file_title}</c:otherwise>
								</c:choose>
							</display:column>
							<display:column title="文件条码">
								<c:choose>
									<c:when test="${item.create_type_name=='被替换页' }">
										<font color="orange">${item.paper_barcode}</font>
									</c:when>
									<c:otherwise>${item.paper_barcode}</c:otherwise>
								</c:choose>
							</display:column>
							<display:column property="seclv_name" title="文件密级"/>
							<display:column title="制作方式">
								<c:choose>
									<c:when test="${item.create_type=='REPLACEPRINT' || item.create_type=='PRINT'}">打印</c:when>
									<c:otherwise>${item.create_type_name}</c:otherwise>
								</c:choose>
							</display:column>
							<display:column property="print_result_name" title="打印结果"/>
							<display:column property="paper_state_name" title="状态"/>
							<display:column title="关键字" >
								<font color="red">${item.keyword_content}&nbsp;</font>
							</display:column>
							<display:column property="page_count" title="页数"/>	
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
							<display:column property="print_time" sortable="true" title="制作时间"/>
							<display:column title="操作" style="width:50px">
								<c:choose>
									<c:when test="${item.create_type_name=='被替换页' }">
										<input type="button" class="button_2003" value="详细" onclick="go('${ctx}/ledger/viewcycledetail.action?type=PAPER&printType=replacePage&barcode=${item.paper_barcode}&ledger_type=personal');"/>
									</c:when>
									<c:otherwise>
										<input type="button" class="button_2003" value="详细" onclick="go('${ctx}/ledger/viewcycledetail.action?type=PAPER&barcode=${item.paper_barcode}&ledger_type=personal');"/>
									</c:otherwise>
								</c:choose>
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
					<input type="radio" name="jobType" value="CARRYOUT_PAPER"/>外带
			</label>
			<label style="width: 80">
				<input type="radio" name="jobType" value="SEND_PAPER"/>外发
			</label>
			<label style="width: 80">
				<input type="radio" name="jobType" value="FILE_PAPER"/>归档
			</label>
			<label style="width: 80">
				<input type="radio" name="jobType" value="DESTROY_PAPER"/>销毁
			</label>
			<label style="width: 80">
				<input type="radio" name="jobType" value="DELAY_PAPER"/>延期留用
			</label>
			<input type="button" value="批量提交" class="button_2003" style="margin-left: 30px" onclick="return chkSubmit();"/>
			<!-- <input type="button" value="全部提交" class="button_2003" style="margin-left: 30px" onclick="return SubmitAll();"/>
 -->		</td>
	</tr>
</table>
</form>
</body>
</html>
