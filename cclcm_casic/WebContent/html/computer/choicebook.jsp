<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="${ctx}/_css/displaytag.css" media="screen"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/_css/css200603.css"  media="screen"/>
    <link href="${ctx}/_script/calendar2/calendar-blue.css" type="text/css" rel="stylesheet"/>
    <script language="javascript" src="${ctx}/_script/calendar2/calendar.js"></script>
    <script language="javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>
    <script language="javascript" src="${ctx}/_script/casic304_common.js"></script>   
    <script language="javascript" src="${ctx}/_script/casic304_ajax.js"></script>
<script>
	function onOK(book_barcode){
		var result = new Object();
		result.book_barcode=book_barcode;
		result.type = "Y";
		window.returnValue=result;	
		window.close();  
	}	
	function onCancel(){
		window.close();
	}
	
	function choiceid(){
		//var selv = $("#selv_book").val();
		//return true;
		//var url_n = "${ctx}/computer/choicebook.action?selv_book="+escape(selv);
		//location.replace(url_n);
		document.getElementById("QueryCondForm").submit();
	}
	function clearFindForm(){
		$("#QueryCondForm :text").val("");	
	}
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
	var user_id=$("#allOption").val();
	var user_name=$("#allOption option[value='"+user_id+"']").text();
	user_name=user_name.substring(0,user_name.indexOf("/"));
	if(user_id != ""){
		$("#duty_user_id").val(user_id);
		$("#duty_user_name").val(user_name);
		document.getElementById("allOptions").innerHTML="";
	}
	}
</script>	
</head>
<body oncontextmenu="self.event.returnValue=false">
<base target="_self"/>
<form action="${ctx}/computer/choicebook.action"  id="QueryCondForm"  name="QueryCondForm" method="POST">
<input type="hidden" name="event_code" value="${event_code}"/>
<input type="hidden" name="job_code" value="${job_code}"/>
<table width="95%" border="0" cellspacing="0" cellpadding="0" align="center" class="table_only_border">
	<tr>
		<td class="title_box" colspan="8">待分配笔记本列表</td>
	</tr>	
<%-- 	<tr>
		<td align="center">密级 </td>
		<td>
 			<c:set var="seclv1" value="${seclv_code}" scope="request"/>
 			<select name="selv_book" id="selv_book">
				<option value="">--所有--</option>
				<s:iterator value="#request.seclvList" var="seclv">
					<option value="${seclv.seclv_code}" <s:if test="#request.seclv1==#seclv.seclv_code">selected="selected"</s:if>>${seclv.seclv_name}</option>
				</s:iterator>
			</select>
 		</td>
 		<td colspan="2">
 			<input name="button" type="submit" class="button_2003" value="查询" onclick="choiceid();">&nbsp;&nbsp;
 		</td>
	</tr> --%>
	<tr>
		<td align="right">	
		 	<table border=0 class="table_box" cellspacing="0" cellpadding="0" width="100%">	 		 		
				<tr>
				    <td align="center" width="10%">责任人</td>
			 		<td width="15%">
			 			<input type="text" id="duty_user_name" name="duty_user_name" onkeyup="selectRecvUser(this.value);" value="${duty_user_name}"/>
			    		<input type="hidden" id="duty_user_id" name="duty_user_id" value="${duty_user_id}"/><br>
			    		<div id="allOptions" class="containDiv" style="position:absolute;border:0px solid black;padding:0px">
			 		</td>
					<td align="center" width="10%">保密编号</td>
			 		<td align="center" width="15%">
			 			<input type="text" id="conf_code" name="conf_code" value="${conf_code}"/>
			 		</td>
			 		<td align = "center" width="10%">原保密编号</td>
			 		<td align="center" width="15%">
			 		   	<input type="text" id="oldconf_code" name="oldconf_code" value="${oldconf_code}"/>
			 		</td>	
			 		<td align="center">
						<input name="button" type="submit" class="button_2003" value="查询" onclick="return;">&nbsp;&nbsp;
						<input name="button" type="button" class="button_2003" value="清空" onclick="clearFindForm();">
					</td>
				</tr>
		    </table>	
		</td>
	</tr> 		
	<tr>
		<td colspan="8">
			<table class="displaytable-outter" cellspacing=0 cellpadding=0 border=0 width="100%">
	 			<tr>
	   				<td>
					<display:table requestURI="${ctx}/computer/choicebook.action" id="item" class="displaytable" name="bookList" 
					pagesize="15" sort="list" excludedParams="*" form="QueryCondForm">
						<display:column title="序号">
							<c:out value="${item_rowNum}"/>
						</display:column>
						<display:column property="duty_entp" title="单位"/>
						<display:column property="book_barcode" title="设备条码" maxLength="20"/>
						<display:column property="oldconf_code" title="原保密编号"/>
						<display:column property="internet_type" title="网络类型"/>
						<display:column property="book_code" title="统一编码"/>
						<display:column property="conf_code" title="保密编码"/>
						<display:column property="duty_user_name" title="责任人" maxLength="15"/>
						<display:column property="duty_dept_name" title="责任部门" maxLength="15"/>
						<display:column property="seclv_name" title="密级"/>
						<display:column property="book_status_name" title="设备状态"/>
 						<display:column title="操作" style="width:50px" >
							<%-- <input type="button" class="button_2003" value="分配" onclick="go('${ctx}/computer/choicebook.action?type=Y&event_code=${event_code}&barcode=${item.book_barcode}');";/>&nbsp; --%>
							<input type="button" class="button_2003" value="分配" onclick="onOK('${item.book_barcode}');";/>&nbsp;
						</display:column> 
					</display:table>
					</td>
				</tr>
			</table>
         </td>
	</tr>
	<tr>
		<td colspan="8" align="center">
			<input type="button" class="button_2003" value="关闭" onclick="onCancel();" />
		</td>
	</tr>
</table>
</form>
</body>
</html>
