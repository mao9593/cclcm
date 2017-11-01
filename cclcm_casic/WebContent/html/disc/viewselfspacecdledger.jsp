<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<title>个人空白盘台账列表</title>
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
	    	$("#user_id").val(user_id);
			$("#user_name").val(user_name);
			document.getElementById("allOptionsCR").innerHTML="";
		}
	}
	function clearFindForm(){
		LedgerQueryCondForm.barcode.value = "";
		$("#user_name").val("");
		LedgerQueryCondForm.seclv_code.value = "";
		LedgerQueryCondForm.startTime.value = "";
		LedgerQueryCondForm.endTime.value = "";	
	}
	function exportLedger(formId,url,url1){
		document.getElementById(formId).action = url;
		document.getElementById(formId).submit();
		document.getElementById(formId).action = url1;
	}
	function spacecd_change(id){
		$("#id").val(id);
		var url = "${ctx}/disc/spacecdchangeselfentitycd.action?id="+id;
		var rValue = window.showModalDialog(url,'', 'dialogHeight:480px;dialogWidth:680px;center:yes;status:no;scroll:no;help:no;unadorned:no;resizable:no');
		if(rValue!=null &&　rValue!=undefined){
			$("#file_list").val(rValue.file_list);
			$("#seclv_code").val(rValue.seclv_code);
			$("#comment").val(rValue.comment);
			$("#retrieveForm").submit();
		}
	}
	
	</script>
</head>
<body oncontextmenu="self.event.returnValue=false">
  	<form id="retrieveForm" method="POST" action="${ctx}/disc/spacecdchangeselfentitycd.action">
  		<input type="hidden" name="update" id="update" value="Y"/>
	 	<input type="hidden" name="file_list" id="file_list"/>
	 	<input type="hidden" name="seclv_code" id="seclv_code"/>
	 	<input type="hidden" name="comment" id="comment"/>
	 	<input type="hidden" name="id" id="id"/>
	</form>
<table width="95%" border="0" cellspacing="0" cellpadding="0" align="center" class="table_only_border">
	<tr>
		<td class="title_box">个人空白盘台账列表</td>
	</tr>
	<form id="LedgerQueryCondForm" method="GET" action="${ctx}/disc/viewselfspacecdledger.action">
	<tr>
		<td align="right">
			<table  table border=0 class="table_box" cellspacing="0" cellpadding="0" width="100%">
				<tr>
					<td align="center">光盘条码：</td>
					<td>
						<input type="text" id="barcode" name="barcode" value="${barcode}"/>
					</td>
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
					<%-- <td align="center">制作人：</td>
					<td>
						<input type="text" id="user_name" name="user_name" value="${user_name}" onkeyup="selectRecvUserCR(this.value);"/><br>
    		            <div id="allOptionsCR" class="containDiv" style="position:absolute;border:0px solid black;padding:0px">
    		            </div>
					</td> --%>
					
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
					<td align="center">光盘类型：</td>
					<td>
						<select name="cd_type" id="cd_type">
							<option value="">--请选择--</option>
							<option value="CD-R" <c:if test="${cd_type eq 'CD-R'}">selected</c:if>>CD-R</option>
					        <option value="DVD-R" <c:if test="${cd_type eq 'DVD-R'}">selected</c:if>>DVD-R</option>
						</select>
					</td>
					<td align="center">空白盘类型：</td>
					<td>
						<select name="spacecd_type" id="spacecd_type">
							<option value="">--请选择--</option>
							<option value="0" <c:if test="${spacecd_type eq '0'}">selected</c:if>>空白盘</option>
					        <option value="1" <c:if test="${spacecd_type eq '1'}">selected</c:if>>中转盘</option>
						</select>
					</td>		
					</tr>
					<tr>
					<td align="center" colspan="2"> 
						<input name="button" type="submit" class="button_2003" value="查询" onclick="return checkTime();">&nbsp;&nbsp;
						<input name="button" type="button" class="button_2003" value="清空" onclick="clearFindForm();">
						<input type="button" class="button_2003" style="display: none;" disabled="disabled" value="导出EXCEL" onclick="exportLedger('LedgerQueryCondForm','${ctx}/ledger/exportdeptcdledger.action','${ctx}/ledger/viewselfspacecdledger.action');"/>
					</td>	
					<td colspan = "4">&nbsp;</td>				
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td>
			<table class="displaytable-outter" cellspacing=0 cellpadding=0 border=0 width="100%">
	 			<tr>
	   				<td>
	   					<display:table requestURI="${ctx}/disc/viewselfspacecdledger.action" uid="item" class="displaytable" name="items"
	   							pagesize="15" sort="page" form="LedgerQueryCondForm" excludedParams="*">
							<display:column title="序号">
								<c:out value="${item_rowNum}"/>
							</display:column>
							<display:column property="barcode" title="光盘条码"/>
							<display:column property="seclv_name" title="密级"/>
							<display:column property="spacecd_type_name" title="空白盘类型"/>
							<display:column property="cd_type" title="光盘类型"/>
							<%-- <display:column property="spacecd_state_name" title="状态"/> --%>
							<display:column property="leadin_timename" title="录入时间"/>
							<display:column title="操作" style="width:100px">
								<input type="button" class="button_2003" value="详细" onclick="go('${ctx}/disc/viewspacecdledgerinfo.action?op=hasprc&id=${item.id}');"/>&nbsp;&nbsp;
								<%-- <input type="button" class="button_2003" value="一键转换" onclick="spacecd_change('${item.id}')"/>							 --%>
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
