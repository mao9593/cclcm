<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
  <head>
   <title>空白盘中转</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="${ctx}/_css/css200603.css" type="text/css" media="screen" />
		<link rel="stylesheet" type="text/css" href="${ctx}/_css/displaytag.css" media="screen"/>
	<link href="${ctx}/_script/calendar2/calendar-blue.css" type="text/css" rel="stylesheet"/>
	<script language="javascript" src="${ctx}/_script/calendar2/calendar.js"></script>
	<script language="javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>
	<script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
	<script language="javascript" src="${ctx}/_script/casic304_ajax.js"></script>
	<script>
	function onOK(id,seclv_code){
		if(chk(seclv_code)){
			if(seclv_code==""){
				seclv_code=$("#seclv_code").val();
			}
			var ret = new Object();
			ret.seclv_code = seclv_code;
			ret.file_list = $("#file_list").val();
			ret.comment = $("#comment").val();
			window.returnValue=ret;
			window.close();  
		}
	}
	function chk(seclv_code){
		if(seclv_code==""){
			if($("#seclv_code").val()==""){
				alert("密级不能为空");
				return false;
			}
			return true;
		}
		if($("#file_list").val().length>400){
			alert("文件列表长度过长不能填写");
			return false;
		}
		if($("#comment").val().length>400){
			alert("中转说明长度过长不能填写");
			return false;
		}
		return true;
	}	
	function onCancel(){
		window.close();
	}
	</script>
  </head>
  <body oncontextmenu="self.event.returnValue=false">
<center>
<table width="95%" border="1" cellspacing="0" cellpadding="0" align="center" class="table_box" style="margin-top:30px">
	<tr>
		<td class="title_box" colspan="2">&nbsp;</td>
	</tr>
	<c:choose>
		<c:when test="${spaceCD.seclv_code !=null }">
			<tr>
				<td align="right">密级：</td>
				<td>&nbsp;&nbsp;&nbsp;&nbsp;<font color="red">${spaceCD.seclv_name }</font></td>
			</tr>
		</c:when>
		<c:otherwise>
			<tr>
				<td align="right"><font color="red">*</font>密级：</td>
				<td>
					<select name="seclv_code" id="seclv_code">
							<option value="">--所有--</option>
							<s:iterator value="#request.seclvList" var="seclv">
								<option value="${seclv.seclv_code}">${seclv.seclv_name}</option>
							</s:iterator>
					</select>
				</td>
			</tr>
		</c:otherwise>
	</c:choose>
	<tr> 
		<td align="right">文件列表：</td>
	    <td>
			<textarea name="file_list" rows="5" cols="45" id="file_list"></textarea>
			<font color="red">注：文件列表请以“|”相隔</font>
		</td> 	
	</tr>
	<tr> 
		<td align="right">中转说明：</td>
    	<td><textarea name="comment" rows="4" cols="45" id="comment"></textarea></td>   	
	</tr>
	<tr> 
		<td colspan="2" align="center">
			<input type="button" class="button_2003" value="提交" onclick="return onOK('${spaceCD.id}','${spaceCD.seclv_code}');" />&nbsp;
			<input type="button" class="button_2003" value="取消" onclick="onCancel();" />
		</td>
	</tr>
</table>
</center>
  </body>
</html>
