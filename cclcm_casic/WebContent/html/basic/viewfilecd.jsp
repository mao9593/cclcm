<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<title>查看已归档光盘列表</title>
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
			preCalendarDay();
			optionSelect();
			if('${retrieveType}'.localeCompare("批量扫描回收") == 0){
				$("#changeRetrieve").attr("disabled","true");
				$("#retrieveType").val("批量扫描回收");
			}else if('${retrieveType}'.localeCompare("") == 0){
				$("#retrieveType").val("");
			}
		});
	function selectRecvUser(word){
	    var url = "${ctx}/basic/getfuzzyuser.action";
	    if(word != ""){
		    callServer4(url,"fuzzy="+word);
	     }else{
		    document.getElementById("allOptions").innerHTML="";
	          }
	} 
	function updateResult2(){
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
	}
	function fileToUse(cd_id){		
		if(confirm("确定将此光盘还原成留用？")){		
			var url = "${ctx}/basic/filetouse.action?item_id="+cd_id+"&type=CD";	
			callServer(url);
		}	
	}
	function updateResult(){
		if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
			alert("此光盘已还原为留用状态");
			$("#LedgerQueryCondForm").submit();
		}
	}
	function exportLedger(formId,url,url1){
			document.getElementById(formId).action = url;
			document.getElementById(formId).submit();
			document.getElementById(formId).action = url1;
	}
	function changeRetrieveJob(){
		$("#changeRetrieve").attr("disabled","true");
		$("#retrieveType").val("批量扫描回收");
	}
	
	function chkSubmit(){
		var submitable = true;
		var paper_barcodes = "";
		if($(":checkbox:checked[value!='']").size()>0){
			$(":checkbox:checked[value!='']").each(function (){
			    paper_barcodes += this.value+",";
			    $("#paper_barcodes").val(paper_barcodes);
			});
			if(submitable){
				$("#LedgerQueryCondForm").attr("action","${ctx}/ledger/handletempjob.action");
				$("#jobType").val("FILECD_DESTROY");
				$("#LedgerQueryCondForm").submit();
				return true;
			}else{
				return false;
			}
		}else{
			alert("请先勾选文件任务");
			return false;
		}
	}	
	
	</script>
</head>

<body oncontextmenu="self.event.returnValue=false">
<form id="LedgerQueryCondForm" method="GET" action="${ctx}/basic/viewfilecd.action" name="LedgerQueryCondForm">
	<input type="hidden" name="dept_ids" id="dept_ids" value="${dept_ids}" />
	<input type="hidden" id="paper_barcodes" name="paper_barcodes"/>
	<input type="hidden" id="jobType" name="jobType" />
	<input type="hidden" id="entity_type" name="entity_type" value="cd"/>
	<table width="95%" border="0" cellspacing="0" cellpadding="0" align="center" class="table_only_border">
	<tr>
		<td class="title_box">已归档光盘列表</td>
	</tr>
	<tr>
		<td>
			<table  table border=0 class="table_box" cellspacing="0" cellpadding="0" width="100%">
				<tr>
					<td  align="center">责任人：</td>
					<td >
						<input type="text" id="duty_user_name" name="duty_user_name" value="${duty_user_name}" onkeyup="selectRecvUser(this.value);"/><br>
    		            <div id="allOptions" class="containDiv" style="position:absolute;border:0px solid black;padding:0px">
    	        	    </div>
					</td>
					<td  align="center">光盘编号：</td>
					<td >
						<input type="text" id="cd_barcode" name="cd_barcode" value="${cd_barcode}"/>
					</td>
					<td align="center">保密编号：</td>
					<td>
						<input type="text" id="conf_code" name="conf_code" value="${conf_code}"/>
					</td>
					<td align="center">制作人：</td>
					<td>
						<input type="text" id="user_name" name="user_name" value="${user_name}" onkeyup="selectRecvUserCR(this.value);"/><br>
    		            <div id="allOptionsCR" class="containDiv" style="position:absolute;border:0px solid black;padding:0px">
    		            </div>
					</td>
					<td width="10%" align="center" rowspan="2">
						<input name="button" type="submit" class="button_2003" value="查询" onclick="return checkDateTime();">&nbsp;
						<input name="button" type="button" class="button_2003" value="清空" onclick="clearFindForm();">
					</td>			
				</tr>
				<tr>
				    <td align="center">制作部门：</td>
					<td>
						<input type="text" id="dept_name" name="dept_name" value="${dept_name}" size="15"/>&nbsp;
					</td>				
					<td align="center">密级：</td>
					<td>
						<select name="seclv_code" id="seclv_med">
							<option value="">--所有--</option>
							<s:iterator value="#request.seclvList" var="seclv">
								<option value="${seclv.seclv_code}">${seclv.seclv_name}</option>
							</s:iterator>
						</select>
					</td>
					<td align="center">开始时间：</td>
					<td>
						<input type="text" name="start_time" readonly="readonly" style="cursor:hand;" value="${start_time}"/>
	        			<img src="${ctx}/_image/time2.jpg" id="start_time_trigger">
					</td>
					<td align="center">结束时间：</td>
					<td>
						<input type="text" name="end_time" readonly="readonly" style="cursor:hand;" value="${end_time}"/>
	        			<img src="${ctx}/_image/time2.jpg" id="end_time_trigger">
					</td>
				</tr>
				<tr>
					<td colspan="9">
					    <input type="button" class="button_2003" value="导出EXCEL" onclick="exportLedger('LedgerQueryCondForm','${ctx}/basic/exportfilecd.action','${ctx}/basic/viewfilecd.action');"/>
					    <input name="button" type="button" class="button_2003" id="changeRetrieve" value="批量扫描回收" onclick="changeRetrieveJob();">
						<input type="hidden" name="retrieveType" id="retrieveType"/>
				    </td>
				</tr>
			</table>
		</td>
    </tr>
	</tr>
	<tr>
		<td>
			<table class="displaytable-outter" cellspacing=0 cellpadding=0 border=0 width="100%">
	 			<tr>
	   				<td>
						<display:table requestURI="${ctx}/basic/viewfilecd.action" id="item" class="displaytable" name="cdLedgerList"
	   					pagesize="${pageSize}" sort="page" partialList="true" size="${totalSize}" excludedParams="*" form="LedgerQueryCondForm">	
	   						<display:column title="选择">							
								<input type="checkbox" name="_chk" value="${item.cd_barcode}" id="${item.seclv_code}"/>
							</display:column>
							<display:column title="序号">
								<c:out value="${item_rowNum}"/>
							</display:column>
							<display:column property="duty_user_name" title="责任人"/>
							<display:column property="duty_dept_name" title="部门"/>
							<display:column property="file_list" title="文件列表" maxLength="40"/>
							<display:column property="cd_barcode" title="光盘编号"/>
							<display:column property="conf_code" title="保密编号"/>
							<display:column property="seclv_name" title="光盘密级"/>
							<display:column property="user_name" title="制作人"/>
							<display:column property="dept_name" title="制作人部门"/>
							<display:column property="retrieve_time" sortable="true" title="归档时间"/>
							<display:column title="操作" style="width:100px">
								<input type="button" class="button_2003" value="详细" onclick="go('${ctx}/ledger/viewcycledetail.action?type=DISC&barcode=${item.cd_barcode}&ledger_type=file');"/>&nbsp;								
								<c:choose>
									<c:when test="${item.cd_state == 3}">
										<input type="button" class="button_2003" value="还原"  onclick="fileToUse('${item.cd_id}');"/>												
									</c:when>
									<c:otherwise>
										<input type="button" class="button_2003" value="还原"  disabled="disabled"/>	
									</c:otherwise>							
								</c:choose>
							</display:column>
					</display:table>
				</td>
			</table>
         </td>
	</tr>
	<tr>
		<td>
         	<input type="button" class="button_2003" value="批量销毁" onclick="chkSubmit();"/>&nbsp;
         </td>
	</tr>
	</table>
</form>
</body>
</html>
