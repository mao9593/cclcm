<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<title>已回收文件列表</title>
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
			preCalendarDay();
			optionSelect();
			if("${secError}" == "Y"){
				alert("勾选送销的文件密级不一致");
			}
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
	function preCalendarDay(){
		Calendar.setup({inputField: "start_time", button: "start_time_trigger", singleClick: true, showsTime: true, ifFormat: "%Y-%m-%d %H:%M:%S", cache: true, weekNumbers:true, showOthers: true, step: 1});
		Calendar.setup({inputField: "end_time", button: "end_time_trigger", singleClick: true, showsTime: true, ifFormat: "%Y-%m-%d %H:%M:%S", cache: true, weekNumbers:true, showOthers: true, step: 1});
	}	
	function clearFindForm(){
		$("#LedgerQueryCondForm :text").val("");
		$("#LedgerQueryCondForm select").val("");
		$("#LedgerQueryCondForm :hidden").val("");
	}	
	function optionSelect(){
			$("#seclv_med").val(${seclv_code});
			$("#retrieve_box").val('${retrieve_box_code}');
	}
	function chkSubmit(){
		var checked = $("input[name='_chk']:checked").size();
		var hidded = $("input[name='_chk'][type='hidden']").size();
		if(checked+hidded >0){
				$("#LedgerQueryCondForm").attr("action","${ctx}/basic/handlepapersenddestroy.action");
				$("#LedgerQueryCondForm").submit();
		}else{
			alert("请先勾选需要送销的文件");
			return false;
		}
	}	
	function exportLedger(formId,url,url1){
			document.getElementById(formId).action = url;
			document.getElementById(formId).submit();
			document.getElementById(formId).action = url1;
	}
	
	</script>
</head>

<body>
<form id="LedgerQueryCondForm" method="GET" action="${ctx}/ledger/viewrecycledpaper.action">
	<input type="hidden" name="dept_ids" id="dept_ids" value="${dept_ids}" />
	<table width="95%" border="0" cellspacing="0" cellpadding="0" align="center" class="table_only_border">
	<tr>
		<td class="title_box">已回收文件列表</td>
	</tr>
	<tr>
		<td align="right">
			<table  table border=0 class="table_box" cellspacing="0" cellpadding="0" width="100%">
				<tr>
		 			<td align="center" >责任人：</td>
		 			<td>
		 				<input type="text" id="duty_user_name" name="duty_user_name" value="${duty_user_name}" onkeyup="selectRecvUser(this.value);"/><br>
    		            <div id="allOptions" class="containDiv" style="position:absolute;border:0px solid black;padding:0px">
    	        	    </div>
		 			</td>
		 			<td align="center" >文件条码：</td>
		 			<td>
		 				<input type="text" id="paper_barcode" name="paper_barcode" value="${paper_barcode}"/>
		 			</td>
					<td align="center" >密级：</td>
					<td>
						<select name="seclv_code" id="seclv_med">
								<option value="">--所有--</option>
								<s:iterator value="#request.seclvList" var="seclv">
									<option value="${seclv.seclv_code}">${seclv.seclv_name}</option>
								</s:iterator>
						</select>
					</td>
					<td align="center" rowspan="2">
						<input name="button" type="submit" class="button_2003" value="查询" onclick="return checkDateTime();">&nbsp;
						<input name="button" type="button" class="button_2003" value="清空" onclick="clearFindForm();">&nbsp;&nbsp;
						<!-- 
						 <input type="button" class="button_2003" value="导出EXCEL" onclick="exportLedger('LedgerQueryCondForm','${ctx}/ledger/exportrecycledcd.action','${ctx}/ledger/viewretrievedcd.action');"/>
						 -->
					</td>
				</tr>
				<tr>
					<td align="center" >起止时间：</td>
					<td>
						<input type="text" name="start_time" readonly="readonly" style="cursor:hand;" value="${start_time}"/>
		        		<img src="${ctx}/_image/time2.jpg" id="start_time_trigger">
		        	</td>
		        	<td align="center" >至：</td>
					<td>
						<input type="text" name="end_time" readonly="readonly" style="cursor:hand;" value="${end_time}"/>
		        		<img src="${ctx}/_image/time2.jpg" id="end_time_trigger">
		        	</td>
		        	<td align="center">回收柜：
					</td>
					<td>
						<select name="retrieve_box_code" id="retrieve_box">
						<option value="">--所有--</option>
						<s:iterator value="#request.recycleBoxList" var="box">
							<option value="${box.box_code}">${box.box_name}</option>
						</s:iterator>
						</select>
					</td>
				</tr>
			</table>
		</td>
		<tr>
    </tr>
	</tr>
	<tr>
		<td>
			<table class="displaytable-outter" cellspacing=0 cellpadding=0 border=0 width="100%">
	 			<tr>
	   				<td>
	   					<display:table requestURI="${ctx}/ledger/viewrecycledpaper.action" id="item" class="displaytable" name="paperLedgerList"
	   					pagesize="${pageSize}" sort="page" partialList="true" size="${totalSize}" excludedParams="*" form="LedgerQueryCondForm" decorator="decorator">
	   					<display:column property="checkbox" title="选择"></display:column>	
						<display:column title="序号">
							<c:out value="${item_rowNum}"/>
						</display:column>
						<display:column property="duty_user_name" title="责任人"/>
						<display:column property="duty_dept_name" title="责任人部门"/>
						<display:column property="file_title" title="文件名" maxLength="40"/>
						<display:column property="paper_barcode" title="文件条码"/>
						<display:column property="page_count" title="页数"/>
						<display:column property="seclv_name" title="介质密级"/>
						<display:column property="create_type_name" title="制作方式"/>
						<display:column property="user_name" title="制作人"/>
						<display:column property="dept_name" title="制作人部门"/>
						<display:column title="回收方式">
							<c:choose>
								<c:when test="${item.retrieve_type == 0}">${item.retrieve_box_name}</c:when>
								<c:otherwise>人工回收</c:otherwise>
							</c:choose>
						</display:column>
						<display:column property="retrieve_time" sortable="true" title="回收时间"/>
						<display:column title="操作" style="width:60px">
							<input type="button" class="button_2003" value="详细" onclick="go('${ctx}/ledger/viewcycledetail.action?type=PAPER&barcode=${item.paper_barcode}');"/>						
						</display:column>
					</display:table>
					</td>
				</tr>
			</table>
				<tr>
					<td>
						<input type="hidden" name="jobType" value="SENDES_PAPER"/>
						<input type="button" class="button_2003" value="批量送销" onclick="return chkSubmit();"/>&nbsp;
						请在同一批次勾选<font color="red"><b>相同密级</b></font>的文件进行送销，为了保证程序运行稳定，同一批次送销的文件数最好不要超过<font color="red"><b>40</b></font>个
					</td>
				</tr>
	         </td>
		</tr>
	</table>
</form>
</body>
</html>
