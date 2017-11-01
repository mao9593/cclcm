<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
<title>添加条码</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="${ctx}/_css/css200603.css" type="text/css" media="screen"/>
	<link href="${ctx}/_script/calendar2/calendar-blue.css" type="text/css" rel="stylesheet"/>
	<script language="javascript" src="${ctx}/_script/calendar2/calendar.js"></script>
	<script language="javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>
	<script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
	<script  language="JavaScript" >
	<!--
	$(document).ready(function(){
		onHover();
		init();
	});
	function init(){
		var is_first = $("#is_first").val();
		if(is_first == "Y"){
			changeValue("Y");
			$("#is_default").val("Y");
		}else{
			changeValue("N");
			$("#is_default").val("N");
		}
	}
	function chk()
	{
		if($("#barcode_name").val().trim() == ""){
			alert("请填条码名称");
			$("#barcode_name").focus();
			return false;
		}
		if($("#position").val().trim() == ""){
			alert("请选择条码位置");
			$("#position").focus();
			return false;
		}
		if($("#bypage").val().trim() == ""){
			alert("请选择条码分配方式");
			$("#bypage").focus();
			return false;
		}
		if($("#form").val().trim() == ""){
			alert("请选择条码形式");
			$("#form").focus();
			return false;
		}
		if($("#perpage").val().trim() == ""){
			alert("请选择条码输出模式");
			$("#perpage").focus();
			return false;
		}
		if($("#pageno").val().trim() == ""){
			alert("请选择页码输出模式");
			$("#pageno").focus();
			return false;
		}
		if($("#cord_x").val().trim() == ""){
			alert("请填写X坐标");
			$("#cord_x").focus();
			return false;
		}
		if($("#cord_y").val().trim() == ""){
			alert("请填写Y坐标");
			$("#cord_y").focus();
			return false;
		}
		if(!checkCode_addword($("#barcode_name").val())){
			alert("条码名称只能由数字、字母、汉字或下划线组成，长度小于30位");
			$("#barcode_name").focus();
			return false;
		}
		var p_num = /^[0-9]{0,5}$/;
		if(!p_num.test($("#cord_x").val())){
			alert("X坐标只能是 0-99999 的数字");
			$("#cord_x").focus();
			return false;
		}
		if(!p_num.test($("#cord_y").val())){
			alert("Y坐标只能是 0-99999 的数字");
			$("#cord_y").focus();
			return false;
		}
		/*
		if(!p_num.test($("#size_x").val())){
			alert("横向尺寸只能是 0-99999 的数字");
			$("#size_x").focus();
			return false;
		}
		if(!p_num.test($("#size_y").val())){
			alert("纵向尺寸只能是 0-99999 的数字");
			$("#size_y").focus();
			return false;
		}*/
		if($("#is_default").val()=="N"){
			/*if($(":checkbox[name=define_textcontent][checked]").size() == 0){
				alert("内容必须至少勾选一个");
				return false;
			}*/
			if($(":checkbox[name=define_seclv][checked]").size() == 0){
				alert("密级必须至少勾选一个");
				return false;
			}
			if($(":checkbox[name=define_usage][checked]").size() == 0){
				alert("用途必须至少勾选一个");
				return false;
			}
			/*
			if($(":checkbox[name=define_project][checked]").size() == 0){
				alert("项目必须至少勾选一个");
				return false;
			}*/
			if($(":checkbox[name=define_console][checked]").size() == 0){
				alert("控制台必须至少勾选一个");
				return false;
			}
		}
	    return true;
	}
	function changeValue(value){
		if(value=="N"){
			$(":checkbox[name=define_seclv]").each(function(){
				$(this).removeAttr("disabled");
			});	
			$(":checkbox[name=define_usage]").each(function(){
				$(this).removeAttr("disabled");
			});	
			$(":checkbox[name=define_project]").each(function(){
				$(this).removeAttr("disabled");
			});	
			$(":checkbox[name=define_console]").each(function(){
				$(this).removeAttr("disabled");
			});	
		}
		if(value=="Y"){
			$(":checkbox[name=define_seclv]").each(function(){
				$(this).attr("disabled","disabled");
			});	
			$(":checkbox[name=define_usage]").each(function(){
				$(this).attr("disabled","disabled");
			});	
			$(":checkbox[name=define_project]").each(function(){
				$(this).attr("disabled","disabled");
			});	
			$(":checkbox[name=define_console]").each(function(){
				$(this).attr("disabled","disabled");
			});
		}
	    return true;
	}
	function changeDefault(obj)
	{
		if(obj.value=="N"){
			changeValue("N");
		}
		if(obj.value=="Y"){
			changeValue("Y");
		}
	    return true;
	}
	//-->
	</script>
</head>
<body oncontextmenu="self.event.returnValue=false">
<center>
<form method="post" action="${ctx}/basic/addbarcode.action">
	<input type="hidden" name="is_save" value="true"/>
   	<table class="table_box" cellspacing=0 cellpadding=0 border=1 width="90%">
	 <tr>
		 <td colspan="4" class="title_box">
            	添加条码规则
        </td>
    </tr>
    <tr>
    	<td align="center"><font color="red">*</font>是否默认：</td>
    	<input type="hidden" name="is_first" id="is_first" value="${is_first}"/>
    	<input type="hidden" name="is_default" id="is_default" />
			<c:if test="${is_first == 'Y'}">
				<td colspan="3">是</td>
			</c:if>
			<c:if test="${is_first == 'N'}">
				<td colspan="3">否</td>
			</c:if>	
		
    </tr>
    <tr>
    	<td align="center"><font color="red">*</font>条码名称：</td>
		<td>
			<input type="text" name="barcode_name" maxlength="15" id="barcode_name" />
		</td>
   		<td align="center"><font color="red">*</font>条码位置：</td>
		<td>
			<select name="position" id="position">
				<option value="">--请选择--</option>
				<option value="0">右上</option>
				<option value="1">左上</option>
				<option value="2">右下</option>
				<option value="3">左下</option>
				<option value="4">上居中</option>
				<option value="5">下居中</option>
			</select>
		</td>
    </tr>
     <tr>
    	<td align="center"><font color="red">*</font>条码分配方式：</td>
		<td>
			<select name="bypage" id="bypage">
				<option value="">--请选择--</option>
				<option value="0">按页分配条码</option>
				<option value="1">按份分配条码</option>
			</select>
		</td>
    	<td align="center"><font color="red">*</font>条码形式：</td> 
		<td>
			<select name="form" id="form">
				<option value="">--请选择--</option>
				<option value="0">无</option>
				<option value="1">一维码</option>
				<!-- <option value="2">二维码(QR)</option>  -->
				<option value="3">二维码(PDF417)</option>
			</select>
		</td>
    </tr>
    <tr>
		<td align="center"><font color="red">*</font>条码输出模式：</td>
		<td>
			<select name="perpage" id="perpage">
				<option value="">--请选择--</option>
				<option value="1">首页</option>
				<option value="2">尾页</option>
				<option value="3">所有页</option>
			</select>
		</td>
    	<td align="center"><font color="red">*</font>页码输出模式：</td> 
		<td>
			<select name="pageno" id="pageno">
				<option value="">--请选择--</option>
				<option value="0">不输出</option>
				<option value="1">首页</option>
				<option value="2">尾页</option>
				<option value="3">所有页</option>
			</select>
		</td>
    </tr>
    <tr>
    	<td align="center"><font color="red">*</font>X坐标：</td>
		<td>
			<input type="input"  name="cord_x" id="cord_x" value="0"/>&nbsp;mm(毫米)
		</td>
    	<td align="center"><font color="red">*</font>Y坐标：</td>
		<td>
			<input type="input"  name="cord_y" id="cord_y" value="0"/>&nbsp;mm(毫米)
		</td>
    </tr>
    <!-- 
    <tr>
    	<td align="center">横向尺寸：</td>
		<td>
			<input type="input"  name="size_x" id="size_x"/>&nbsp;mm(毫米)
		</td>
    	<td align="center">纵向尺寸：</td>
		<td>
			<input type="input"  name="size_y" id="size_y"/>&nbsp;mm(毫米)
		</td>
    </tr>
     -->
    <tr height="50">
    	<td align="center">显示内容：</td>        
		<td colspan="3">
			&nbsp;&nbsp;<input type="checkbox" value="tm" name="define_textcontent" checked="checked"/>条码
			&nbsp;&nbsp;<input type="checkbox" value="bm" name="define_textcontent"/>部门
			&nbsp;&nbsp;<input type="checkbox" value="yh" name="define_textcontent"/>用户
			&nbsp;&nbsp;<input type="checkbox" value="mj" name="define_textcontent"/>密级
			&nbsp;&nbsp;<input type="checkbox" value="lx" name="define_textcontent"/>类型
			&nbsp;&nbsp;<input type="checkbox" value="rq" name="define_textcontent"/>日期
			&nbsp;&nbsp;<input type="checkbox" value="ys" name="define_textcontent"/>页数
			&nbsp;&nbsp;<input type="checkbox" value="fs" name="define_textcontent"/>份数 
		</td>
	</tr>
	<tr height="50">
	    <td align="center"><font color="red">*</font>可设定密级：</td>
		<td colspan="3">&nbsp;&nbsp;
			<s:iterator value="#request.seclvs" var="seclv">
				<input type="checkbox" value="${seclv.seclv_code}" name="define_seclv" disabled="true"/>${seclv.seclv_name}&nbsp;&nbsp;
			</s:iterator>
		</td>
	</tr>
    <tr height="50">
	    <td align="center"><font color="red">*</font>可设用途：</td>
		<td colspan="3">&nbsp;&nbsp;
			<s:iterator value="#request.usages" var="usage">
				<input type="checkbox" value="${usage.usage_code}" name="define_usage" disabled="true"/>${usage.usage_name}&nbsp;&nbsp;
			</s:iterator>
		</td>
	</tr>
    <tr height="50">
	    <td align="center"><font color="red">*</font>可设定控制台：</td>
		<td colspan="3">&nbsp;&nbsp;
			<s:iterator value="#request.consoles" var="console">
				<input type="checkbox" value="${console.console_code}" name="define_console" disabled="true"/>${console.console_name}&nbsp;&nbsp;
			</s:iterator>
		</td>
	</tr>
	<%-- <tr height="50">
	    <td align="center">可设定项目：</td>
		<td colspan="3">&nbsp;&nbsp;
			<s:iterator value="#request.projects" var="project">
				<input type="checkbox" value="${project.project_code}" name="define_project" disabled="true"/>${project.project_name}&nbsp;&nbsp;
			</s:iterator>
		</td>
	</tr> --%>
    <tr>
        <td colspan="4" align="center" class="bottom_box">
            <input type="submit" value="添加" class="button_2003" onclick="return chk();">&nbsp;
            <input type="reset" value="重置" class="button_2003">
            <input type="button" value="返回" class="button_2003" onclick="javascript:history.go(-1);">
        </td>
    </tr>
  	</table>
</form>
</center>
</body>
</html>