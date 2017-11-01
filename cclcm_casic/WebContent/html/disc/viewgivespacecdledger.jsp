<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<title>已分发空白盘台账列表</title>
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
		LedgerQueryCondForm.seclv_code.value = "${seclv_code}";
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
	    	$("#duty_user_iidd").val(user_id);
			$("#duty_user_name").val(user_name);
			document.getElementById("allOptionsCR").innerHTML="";
		}
	}
	function clearFindForm(){
		LedgerQueryCondForm.barcode.value = "";
		$("#duty_user_name").val("");
		$("#duty_user_iidd").val("");
		LedgerQueryCondForm.seclv_code.value = "";
		LedgerQueryCondForm.startTime.value = "";
		LedgerQueryCondForm.endTime.value = "";	
	}
	function exportLedger(formId,url,url1){
		document.getElementById(formId).action = url;
		document.getElementById(formId).submit();
		document.getElementById(formId).action = url1;
	}
	function spacecd_return(id){
		var url = "${ctx}/disc/spacecdreturn.action?id="+id;
		if(confirm("确认退还吗？")){
			callServer(url);
		}
	}
	
	function updateResult(){
		if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
			alert("退还成功");
			$("#LedgerQueryCondForm").submit();
		}
	}
	
	
	</script>
</head>
<body oncontextmenu="self.event.returnValue=false">
  	<form id="retrieveForm" method="POST" action="${ctx}/disc/spacecdchangeuse.action">
  		<input type="hidden" name="update" id="update" value="Y"/>
	 	<input type="hidden" name="file_list" id="file_list"/>
	 	<input type="hidden" name="seclv_code" id="seclv_code"/>
	 	<input type="hidden" name="comment" id="comment"/>
	 	<input type="hidden" name="id" id="id"/>
	</form>
<table width="95%" border="0" cellspacing="0" cellpadding="0" align="center" class="table_only_border">
	<tr>
		<td class="title_box">已分发空白盘台账列表</td>
	</tr>
	<form id="LedgerQueryCondForm" method="GET" action="${ctx}/disc/viewgivespacecdledger.action">
	<tr>
		<td align="right">
			<table  table border=0 class="table_box" cellspacing="0" cellpadding="0" width="100%">
				<tr>
					<td align="center">光盘条码：</td>
					<td>
						<input type="text" id="barcode" name="barcode" value="${barcode}"/>
					</td>
					<td align="center">责任人：</td>
					<td>
						<input type="text" id="duty_user_name" name="duty_user_name" value="${duty_user_name}" onkeyup="selectRecvUserCR(this.value);"/><br>
						<input type="hidden" id="duty_user_iidd" name="duty_user_iidd" value="${duty_user_iidd}"/><br>
    		            <div id="allOptionsCR" class="containDiv" style="position:absolute;border:0px solid black;padding:0px">
    		            </div>
					</td>
					<td align="center">密级：</td>
					<td>
						<select name="seclv_code" id="seclv_code">
							<option value="">--所有--</option>
							<s:iterator value="#request.seclvList" var="seclv">
								<option value="${seclv.seclv_code}">${seclv.seclv_name}</option>
							</s:iterator>
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
						<input name="button" type="submit" class="button_2003" value="查询" onclick="return checkTime();">&nbsp;&nbsp;
						<input name="button" type="button" class="button_2003" value="清空" onclick="clearFindForm();">
						<input type="button" class="button_2003" style="display: none;" disabled="disabled" value="导出EXCEL" onclick="exportLedger('LedgerQueryCondForm','${ctx}/disc/exportdeptcdledger.action','${ctx}/disc/viewgivespacecdledger.action');"/>
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
	   					<display:table requestURI="${ctx}/disc/viewgivespacecdledger.action" uid="item" class="displaytable" name="items"
	   							pagesize="15" sort="page" form="LedgerQueryCondForm" excludedParams="*">
							<display:column title="序号">
								<c:out value="${item_rowNum}"/>
							</display:column>
							<display:column property="user_name" title="制作人"/>
							<display:column property="dept_name" title="部门"/>
							<display:column property="duty_user_name" title="责任人"/>
							<display:column property="duty_dept_name" title="责任人部门"/>
							<display:column property="barcode" title="光盘条码"/>
							<display:column property="seclv_name" title="密级"/>
							<display:column property="spacecd_state_name" title="状态"/>
							<display:column property="leadin_timename" title="录入时间"/>
							<display:column title="操作" style="width:100px">
								<input type="button" class="button_2003" value="详细" onclick="go('${ctx}/disc/viewspacecdledgerinfo.action?op=hasprc&id=${item.id}');"/>&nbsp;&nbsp;
								<input type="button" class="button_2003" value="退还" onclick="spacecd_return('${item.id}')"/>							
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
