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
	<script language="javascript">
	<!--
		function chkComplete(){
			var numval = /^[0-9]+$/;
	
			if($("#seclv_code").val() == ""){
				alert("请选择密级");
				$("#seclv_code").focus();
				return false;
			}
			if($("#file_name").val() == ""){
				alert("请填写文件名称");
				$("#file_name").focus();
				return false;
			}	
			if($("#copy_num").val().trim() == ""){
				alert("请填写复印份数");
				$("#copy_num").focus();
				return false;
			}
			if(!numval.test( $("#copy_num").val())){
			    alert("复印份数应为整数");
			    $("#copy_num").focus();
			    return false;
			}
			if($("#page_num").val().trim() == ""){
				alert("请填写每份页数");
				$("#page_num").focus();
				return false;
			}
			if(!numval.test( $("#page_num").val())){
			    alert("每份页数应为整数");
			    $("#page_num").focus();
			    return false;
			}	
			if($("#scale").val()!= ""){
				if(!numval.test( $("#scale").val())){
			    alert("缩放应为整数");
			    $("#scale").focus();
			    return false;
			}}
			
			$("#update").val("Y");
			$("#UpdateCopyEventForm").submit();
			return true;
		}
	//-->
	</script>
</head>
<body oncontextmenu="self.event.returnValue=false">
<table width="95%" border="1" cellspacing="0" cellpadding="0" align="center" class="table_box">
	<form action="${ctx}/copy/updatecopyevent.action" method="post" id="UpdateCopyEventForm">
		<input type="hidden" name="update" id="update"/>
		<input type="hidden" name="event_code" value="${event.event_code}" id="event_code"/>
	<tr>
	    <td colspan="6" class="title_box">修改复印申请</td>
	</tr>
	<tr> 
    	<td width="10%" align="center">申请用户： </td>
    	<td width="23%"><font color="blue"><b>${event.user_name}</b></font></td>
    	<td width="10%" align="center">用户部门： </td>
    	<td width="23%"><font color="blue"><b>${event.dept_name}</b></font></td>
    	<td width="10%" align="center">申请时间： </td>
    	<td width="23%"><font color="blue"><b>${event.apply_time_str}</b></font></td>
	</tr>
	<tr> 
    	<td align="center"><font color="red">*</font>&nbsp;作业密级：</td>
	    <td>
			<select id="seclv_code" name="seclv_code">
				<option value="">--请选择--</option>
				<s:iterator value="#request.seclvList" var="seclv">
					<option value="${seclv.seclv_code}" <s:if test="#seclv.seclv_code==#request.event.seclv_code">selected</s:if>>${seclv.seclv_name}</option>
				</s:iterator>
			</select>
		</td> 		    	
    	<td align="center"><font color="red">*</font>&nbsp;文件名称： </td>
    	<td><input type="text" name="file_name" id="file_name" value="${event.file_name}"/></td>  
    	<td align="center">&nbsp;源文件编号： </td>
    	<td><input type="text" name="originalid" id="originalid" value="${event.originalid}"/></td>    	   	
    </tr>    
    <tr>
    	<td align="center"><font color="red">*</font>&nbsp;复印份数： </td>
    	<td><input type="text" name="copy_num" id="copy_num"  value="${event.copy_num}"/></td>
    	<td align="center"><font color="red">*</font>&nbsp;每份页数： </td>
    	<td><input type="text" name="page_num" id="page_num"  value="${event.page_num}"/></td>	
    	<td align="center">&nbsp;缩放： </td>
    	<td><input type="text" name="scale" id="scale"  value="${event.source}"/></td>
    </tr>
    <tr>   
    	<td align="center">&nbsp;纸张类型： </td>
    	 <td>
	    	<select id="page_type" name="page_type">
	    		<option value="">--请选择--</option>
	    		<option value="A4" <c:if test="${event.page_type == 'A4'}">selected</c:if>>A4</option>
	    		<option value="A5" <c:if test="${event.page_type == 'A5'}">selected</c:if>>A5</option>
	    		<option value="B4" <c:if test="${event.page_type == 'B4'}">selected</c:if>>B4</option>
	    		<option value="B5" <c:if test="${event.page_type == 'B5'}">selected</c:if>>B5</option>
	    		<option value="其他">其他</option>
	    	</select>
		</td>  	   	
    	<td align="center">&nbsp;单双面： </td>
    	<td><select name="is_double" id="is_double">
    			<option value="1" <c:if test="${event.is_double == '1'}">selected</c:if>>单面</option>
    			<option value="2" <c:if test="${event.is_double == '2'}">selected</c:if>>双面左右翻</option>   
    			<option value="3" <c:if test="${event.is_double == '3'}">selected</c:if>>双面上下翻</option> 	 			
    		</select>
    	</td>    	   	    	
    	<td align="center">&nbsp;颜色： </td>
    	<td><select name="color" id="color">
    			<option value="">--请选择--</option>
    			<option value="1" <c:if test="${event.color == '1'}">selected</c:if>>黑白</option>
    			<option value="2" <c:if test="${event.color == '2'}">selected</c:if>>彩色</option>   			
    		</select>
    	</td>    	
    </tr>
    <tr>   	
	   	<td align="center">&nbsp;来源： </td>
    	<td><input type="text" name="source" id="source" value="${event.source}"/></td> 	  	
  		<td align="center">&nbsp;备注：</td>
		<td colspan="3"><textarea rows="3" cols="60" name="summ">${event.summ}</textarea></td>
  	</tr>

 	<tr>
    <td colspan="6" align="center">
    	<input class="button_2003" type="button" value="保存" onClick="chkComplete();">&nbsp;
		<input class="button_2003" type="button" value="返回" onClick="javascript:history.go(-1);">&nbsp;
    </td>
	</tr>
	</form>
</table>
</body>
</html>
