<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
<title>修改智能回收柜</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="${ctx}/_css/css200603.css" type="text/css" media="screen"/>
	<script language="javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>
	<script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
	<script  language="JavaScript" >
	<!--
	$(document).ready(function(){
		onHover();
	});
	function chk()
	{
		if($("#box_name").val().trim() == ""){
			alert("请填写智能回收柜名称");
			$("#box_name").focus();
			return false;
		}
		if($("#seclv_code").val().trim() == ""){
			alert("请选择密级");
			$("#seclv_code").focus();
			return false;
		}
		if(!checkCode_addword($("#box_name").val())){
			alert("智能回收柜名称只能由数字、字母、汉字或下划线组成，长度小于30位");
			$("#box_name").focus();
			return false;
		}
		if(!checkCode_addword($("#box_location").val())&& !$("#box_location").val().trim() == ""){
			alert("地理位置只能由数字、字母、汉字或下划线组成，长度小于30位");
			$("#box_location").focus();
			return false;
		}
	    return true;
	}
	//-->
	</script>
</head>
<body oncontextmenu="self.event.returnValue=false">
<center>
<form method="post" action="${ctx}/basic/updaterecyclebox.action">
	<input type="hidden" value="${recyclebox.box_code}" name="box_code"/>
	<input type="hidden" value="Y" name="update"/>
   	<table class="table_box" cellspacing=0 cellpadding=0 border=1 width="60%">
	 <tr>
		 <td colspan="2" class="title_box">
            	修改智能回收柜信息
        </td>
    </tr>
    <tr>
    	<td align="center"><font color="red">*</font>智能回收柜名称：</td>
		<td>
			<input type="text" name="box_name" id="box_name" value="${recyclebox.box_name}"/>
		</td>
    </tr>
    <tr>
    	<td align="center">地理位置：</td>
		<td>
			<input type="text" name="box_location" id="box_location" value="${recyclebox.box_location}"/>
		</td>
    </tr>
    <tr>
    	<td align="center"><font color="red">*</font>密级：</td>
	    <td>
	    	<c:set var="seclv1" value="${recyclebox.seclv_code}" scope="request"/>
			<select name="seclv_code" id="seclv_code">
				<option value="">--请选择--</option>
				<s:iterator value="#request.seclvList" var="seclv">
					<option value="${seclv.seclv_code}" <s:if test="#request.seclv1==#seclv.seclv_code">selected="selected"</s:if>>${seclv.seclv_name}</option>
				</s:iterator>
			</select>
		</td>
    </tr>
    <tr>
    	<td align="center">智能回收柜类型：</td>
		<td>
			<select id="box_type" name="box_type">
				<option value="0" <c:if test="${recyclebox.box_type == '0'}">selected='selected'</c:if>>纸质</option>
				<option value="1" <c:if test="${recyclebox.box_type == '1'}">selected='selected'</c:if>>光盘</option>
			</select>
		</td>
    </tr>
    <tr>
    	<td align="center"><font color="red">*</font>智能回收柜状态：</td>
		<td>
			<select id="box_status" name="box_status">
				<option value="0" <c:if test="${recyclebox.box_status == '0'}">selected='selected'</c:if>>空</option>
				<option value="1" <c:if test="${recyclebox.box_status == '1'}">selected='selected'</c:if>>已存</option>
				<option value="2" <c:if test="${recyclebox.box_status == '2'}">selected='selected'</c:if>>损坏</option>
				<option value="3" <c:if test="${recyclebox.box_status == '3'}">selected='selected'</c:if>>打开</option>
			</select>
		</td>
    </tr>
    <tr>
    	<td align="center">当前介质数量：</td>
		<td>
			${recyclebox.current_num}
		</td>
    </tr>
    <tr>
        <td colspan="2" align="center" class="bottom_box">
            <input type="submit" value="修改" class="button_2003" onclick="return chk();">&nbsp;
            <input type="button" value="返回" class="button_2003" onclick="javascript:history.go(-1);">
        </td>
    </tr>
  	</table>
</form>
</center>
</body>
</html>