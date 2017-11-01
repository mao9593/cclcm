<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<title>已拒绝外发列表</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="${ctx}/_css/css200603.css" type="text/css" media="screen" />
	<link rel="stylesheet" type="text/css" href="${ctx}/_css/displaytag.css" media="screen"/>
	<link href="${ctx}/_script/calendar2/calendar-blue.css" type="text/css" rel="stylesheet"/>
	<script language="javascript" src="${ctx}/_script/calendar2/calendar.js"></script>
	<script language="javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>
	<script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
	<script language="javascript" src="${ctx}/_script/casic304_ajax.js"></script>
</head>
	<script>
	$(document).ready(function(){
			onHoverInfinite();
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
			LedgerQueryCondForm.paper_barcode.value = "";
			LedgerQueryCondForm.duty_user_name.value = "";
			LedgerQueryCondForm.file_title.value = "";
	}	
	</script>
<body oncontextmenu="self.event.returnValue=false">
<form id="LedgerQueryCondForm" method="GET" action="${ctx}/ledger/viewrejectsendpaper.action">
    <input type="hidden" name="dept_ids" id="dept_ids" value="${dept_ids}" />
	<table width="95%" border="0" cellspacing="0" cellpadding="0" align="center" class="table_only_border">
		<tr>
			<td class="title_box">已拒绝外发纸质列表</td>
		</tr>
		<tr>
			<td>
				<table  table border=0 class="table_box" cellspacing="0" cellpadding="0" width="100%">
					<tr>
						<td align="center">文件条码：</td>
						<td>					
							<input type="text" id="paper_barcode" name="paper_barcode" value="${paper_barcode}"/>
						</td>
						<td align="center">责任人：</td>
						<td>					
							<input type="text" id="duty_user_name" name="duty_user_name" value="${duty_user_name}" onkeyup="selectRecvUser(this.value);"/><br>
	    		            <div id="allOptions" class="containDiv" style="position:absolute;border:0px solid black;padding:0px">
	    	        	    </div>
						</td>
						<td align="center">文件名：</td>
						<td>
							<input type="text" id="file_title" name="file_title" value="${file_title}"/>
						</td>
						<td align="center" colspan="2">
							<input name="button" type="submit" class="button_2003" value="查询" onclick="return checkDateTime();">
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
						<display:table requestURI="${ctx}/ledger/viewrejectsendpaper.action" id="item" class="displaytable" name="rejectRecordList"
		   					pagesize="${pageSize}" sort="page" partialList="true" size="${totalSize}" excludedParams="*" >
								<display:column title="序号">
									<c:out value="${item_rowNum}"/>
								</display:column>
								<display:column property="duty_user_name" title="责任人"/>							
								<display:column property="duty_dept_name" title="责任人部门"/>
								<display:column property="file_title" title="文件名" maxLength="40"/>
								<display:column property="paper_barcode" title="文件条码"/>
								<display:column property="seclv_name" title="文件密级"/>
								<display:column property="page_count" title="页数"/>
								<display:column property="user_name" title="制作人"/>
								<display:column property="dept_name" title="制作人部门"/>	
								<display:column property="print_time" sortable="true" title="制作时间"/>
								<display:column title="操作" style="width:50px">
								<input type="button" class="button_2003" value="详细" onclick="go('${ctx}/ledger/viewcycledetail.action?type=PAPER&barcode=${item.paper_barcode}');"/>											
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
