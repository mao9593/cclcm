<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<link rel="stylesheet" type="text/css" href="${ctx}/_css/displaytag.css" media="screen"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/_css/css200603.css"  media="screen"/>
    <script language="javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>
	<script language="JavaScript" src="${ctx}/_script/function.js"></script>
	<script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
	<script language="javascript" src="${ctx}/_script/casic304_ajax.js"></script>
<script language="javascript">
	$(document).ready(function(){
		onHover();
	});
	function chkComplete(){
		if($("#seclv_code").val() == ""){
			alert("请选择密级");
			$("#seclv_code").focus();
			return false;
		}
		if($("#period").val() == ""){
			alert("请选择使用期限");
			$("#period").focus();
			return false;
	    }
		if($("#zipfile").val().trim() == ""){
			alert("请填写文件列表");
			$("#zipfile").focus();
			return false;
		}	
		if($("#src_code").val().trim() == ""){
			alert("请填写原光盘编号");
			$("#src_code").focus();
			return false;
		}
		$("#UpdateEnterEventForm").submit();
		return true;
	}
	
	function checkBarcode(event_code) {
		 var barcode = document.getElementById("medium_serial").value;
		 var file_type = 2;
		 if ((barcode == null) || (barcode == "")){
	       alert("请输入预发条码!");
      	   $("#medium_serial").focus();
	       return;
	  	}
		 var url = "${ctx}/enter/checkbarcode.action?barcode=" + escape(barcode)+"&file_type="+ escape(file_type)+"&update='true'"+"&event_code="+escape(event_code);
		 callServer(url);
	}
	
	function updateResult() {
	  if (xmlHttp.readyState == 4) {
	    var response = xmlHttp.responseText;
		alert(response.substring(response.indexOf("00\">",0)+4, response.indexOf("</div>",0)));
		$("#medium_serial").focus();
	  }
	}
</script>
</head>
<body oncontextmenu="self.event.returnValue=false">
<table width="95%" border="1" cellspacing="0" cellpadding="0" align="center" class="table_box">
	<form action="${ctx}/enter/updateenterevent.action" method="post" id="UpdateEnterEventForm">
		<input type="hidden" name="event_code" value="${event.event_code}" id="event_code"/>
		<input type="hidden" name="update" value="Y"/>
		<input type="hidden" name="scope" value="PERSON"/>
		<input type="hidden" name="file_type" value="2"/>
	<tr>
	    <td colspan="6" class="title_box">修改个人光盘录入申请</td>
	</tr>
	<tr> 
    	<td width="10%" align="center">申请用户： </td>
    	<td width="23%"><font color="blue"><b>${curUser.user_name}</b></font></td>
    	<td width="10%" align="center">用户部门： </td>
    	<td width="23%"><font color="blue"><b>${curUser.dept_name}</b></font></td>
    	<td width="10%" align="center"><font color="red">*</font>&nbsp;密级：</td>
	    <td width="23%">
			<select id="seclv_code" name="seclv_code">
				<option value="">--请选择--</option>
				<s:iterator value="#request.seclvList" var="seclv">
					<option value="${seclv.seclv_code}" <s:if test="#seclv.seclv_code==#request.event.seclv_code">selected</s:if>>${seclv.seclv_name}</option>
				</s:iterator>
			</select>
		</td>	
	</tr>
	<tr> 
    	<td align="center">&nbsp;来源： </td>
    	<td><input type="text" name="source" id="source" value="${event.source}"/></td>      			    	  	
    	<td align="center"><font color="red">*</font>&nbsp;使用期限：</td>
		<td>
			<select name="period" id="period">
				<option value="">--请选择--</option>
    			<option value="L" <c:if test="${event.period == 'L'}">selected='selected'</c:if>>长期留用</option>
    			<option value="S" <c:if test="${event.period == 'S'}">selected='selected'</c:if>>短期留用</option>   			
    		</select>
    	</td>
    	<td align="center"><font color="red">*</font>&nbsp;原光盘编号：</td> 
    	<td><input type="text" name="src_code" id="src_code" value="${event.src_code}"/></td>   
    </tr>
    <tr>
        <td align="center">机要号：</td>
    	<td>
    		<input type="text" id="confidential_num" name="confidential_num" value="${event.confidential_num}"/>
    	</td>
    	<td>&nbsp;</td><td>&nbsp;</td>
    	<td>&nbsp;</td><td>&nbsp;</td>
    </tr>
  	<tr>   	   	
  		<td align="center"><font color="red">*</font>&nbsp;文件列表： </td>
		<td colspan="5"><textarea rows="3" cols="60" name="zipfile" id="zipfile">${event.zipfile}</textarea></td>
  	</tr>
  	<tr>   		   	
  		<td align="center">&nbsp;备注：</td>
		<td colspan="5"><textarea rows="3" cols="60" name="summ">${event.summ}</textarea></td>
  	</tr>

 	<tr>
    <td colspan="6" align="center">
    	<input class="button_2003" type="button" value="保存" onClick="chkComplete();">&nbsp;
		<input class="button_2003" type="button" value="返回" onClick="go('${ctx}/enter/addpersonalcdenterevent.action')">&nbsp;
    </td>
	</tr>
	</form>
</table>
</body>
</html>
