<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="${ctx}/_css/css200603.css" type="text/css" media="screen" />
<script language="JavaScript" src="${ctx}/_script/function.js"></script>
<script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
<script language="javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>	
<script  language="JavaScript" >
<!--
$(document).ready(function(){
	onHover();
	$("#producttype").hide();
});
function chk(){
	if($("#device_id").val().trim() == ""){
			alert("请选择设备类型");
			$("#device_id").focus();
			return false;
	}	
	if($("#device_id").val().trim() == 5){
		if($("#product_type").val().trim() == ""){
			alert("请选择安全产品类型");
			$("#product_type").focus();
			return false;
		}
	}	
    if(document.all.typename.value==''){
        alert("请填写类型名称");
        document.all.typename.focus();
        return false;
    }
	if(!specialCharFilter(document.all.typename.value)){
		alert("类型名称包含特殊字符");
		document.all.typename.focus();
        return false;
	}
    return true;
}
function changeDeviceType(type){
	if(type == 5){
		$("#producttype").show();
	}else{
		$("#producttype").hide();
	}
}
	//-->
</script>
</head>
<body oncontextmenu="self.event.returnValue=false">
<center>
<form method="post" action="${ctx}/computer/addinfotype.action">
<input type="hidden" name="add" value="Y"/>
  <table class="table_box" cellspacing=0 cellpadding=0 border=1 width="50%">
	 <tr>
		 <td colspan="2" class="title_box">添加信息设备子类型</td>
    </tr>
    <tr>
   		<td width="40%" align="center"><font color="red">*</font>设备类型</td>
		<td>
			<select name="device_id" id="device_id" onchange="changeDeviceType(this.value)">
				<option value="">--请选择--</option>
				<option value="1">计算机</option>
				<option value="2">网络设备</option>
				<option value="3">外部设备</option>
				<option value="4">办公自动化设备</option>
				<option value="5">安全产品</option>
				<option value="6">介质</option>
			</select>
		</td>  		
    </tr>
	<tr id="producttype" style="display: none">
			<td align="center"><font style="color:red">*</font>安全产品类型</td>
			<td>
				<select name="product_type" id="product_type">
					<option value="">--请选择--</option>
					<option value="1">软件</option>
					<option value="2">硬件</option>
				</select>
			</td>
	</tr>
    <tr>
		<td align="center"><font style="color:red">*</font>类型名称</td>
		<td><input type="text" name="typename" size="38" maxlength="15"/></td>
    </tr>
    <tr>
		<td align="center">类型描述</td>
		<td><input type="text" name="summ" size="38" maxlength="15"/></td>
    </tr>
    <tr>
        <td colspan="2" align="center" class="bottom_box">
            <input type="button" value="添加" class="button_2003" onclick="if(chk()) forms[0].submit();">&nbsp;
            <input type="reset" value="重置" class="button_2003">&nbsp;
            <input type="button" value="返回" class="button_2003" onclick="location.href='${ctx}/computer/manageinfodevicetype.action'">
        </td>
    </tr>
  </table>
  </form>
</center>
</body>
</html>
