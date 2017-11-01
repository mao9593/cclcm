<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
<title>添加打印机</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="${ctx}/_css/css200603.css" type="text/css" media="screen" />
	<script language="javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>
	<script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
	<script language="javascript" src="${ctx}/_script/casic304_ajax.js"></script>
	<script  language="JavaScript" >
	<!--
	$(document).ready(function(){
		onResetHover();
	});
	function checkPrinterNum(console_code){
		//alert(console_code);
		if(console_code != null){
			var url = "${ctx}/basic/checkprinternum.action?console_code="+escape(console_code);
			callServer(url);
		}else {
			alert("控制台代号不存在，请重新选择");
			$("#console_code").focus();
			return false;
		}
		
	}
	function updateResult(){
		if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
			//alert(xmlHttp.responseText);
			if(xmlHttp.responseText >= 5){
				alert("配置给该控制台的打印机个数已超过5个，请重新选择控制台");
				$("#console_code").val("");
				$("#console_code").focus();
				return false;
			}
		}	
	}
	function chk()
	{
		if($("#printer_name").val().trim() == ""){
			alert("请填写打印机名称");
			$("#printer_name").focus();
			return false;
		}
		if($("#printer_path").val().trim() == ""){
			alert("请选择打印机路径");
			$("#printer_path").focus();
			return false;
		}
		if($("#dept_id").val().trim() == ""){
			alert("请选择所属部门");
			return false;
		}
		if($("#console_code").val().trim() == ""){
			alert("请选择控制台");
			$("#console_code").focus();
			return false;
		}
		if($("#seclv_code").val().trim() == ""){
			alert("请选择密级");
			$("#seclv_code").focus();
			return false;
		}
		if(!checkName_addword($("#printer_name").val())){
			alert("打印机名称只能由数字、字母、汉字或下划线组成，长度小于60位");
			$("#printer_name").focus();
			return false;
		}		
	    return true;
	}
	var code_pattern_addword=/^[0-9a-zA-Z_\u4e00-\u9fa5]{1,60}$/;
	function checkName_addword(value){
		if(!code_pattern_addword.test(value)){
			return false;
		}
		return true;
	}
/**
 * 添加输入框等变色效果
 */
function onResetHover(){
	var list=document.getElementsByTagName('input');
	for(i=0;i<list.length;i++){
		input = list[i];
		if(input.getAttribute('readonly')){
			// input.onfocus=new Function('alert("标签只读，不可直接修改");');
		}else if(input.getAttribute('type')=='text'){
			input.onkeypress=new Function('if(this.value.length>=60)return false;')
			input.onfocus = new Function('this.style.backgroundColor="#ede4cd";');
			input.onblur = new Function('this.style.backgroundColor="";if(this.value.length>60){alert("最大合法输入长度为60个字符，超长部分将被截断。");this.value=this.value.substring(0,60);}');
		}else if(input.getAttribute('type')=='password'){
			input.onfocus = new Function('this.style.backgroundColor="#ede4cd";');
			input.onblur = new Function('this.style.backgroundColor="";');
		}
	}
	// 进入页面，输入焦点自动停留在第一个输入框
	for(i=0;i<list.length;i++){
		input = list[i];
		if(input.getAttribute('type')=='text' || input.getAttribute('type')=='password'){
			if(!input.getAttribute('readonly') && !input.getAttribute('disabled')){
				input.focus();
				break;
			}
		}
	}
	list = document.getElementsByTagName('textarea');
	for(i=0;i<list.length;i++){
		input = list[i];
		if(input.getAttribute('readonly')){
			// input.onfocus=new Function('alert("标签只读，不可直接修改");');
		}else{
			input.onkeypress=new Function('if(this.value.length>=250)return false;')
			input.onfocus = new Function('this.style.backgroundColor="#ede4cd";');
			input.onblur = new Function('this.style.backgroundColor="";if(this.value.length>250){alert("最大合法输入长度为250个字符，超长部分将被截断。");this.value=this.value.substring(0,250);}');
		}
	}
}
	//-->
	</script>
</head>
<body oncontextmenu="self.event.returnValue=false">
<center>
<form method="post" action="${ctx}/basic/addprinter.action">
  	<table class="table_box" cellspacing=0 cellpadding=0 border=1 width="60%">
	 <tr>
		 <td colspan="2" class="title_box">
            	添加打印机
        </td>
    </tr>
    <tr>
    	<td align="center" width="150" nowrap="nowrap"><font color="red">*</font>打印机名称：</td>
		<td>
			<input type="text" name="printer_name" id="printer_name"/>
		</td>
    </tr>
    <tr>
    	<td align="center"><font color="red">*</font>打印机路径：</td>
		<td>
			<input type="text" name="printer_path" id="printer_path"/>
		</td>
	<!-- 暂时隐藏打印机所属部门属性，默认都为根机构 
    </tr>
        <tr>
    	<td align="center"><font color="red">*</font>所属部门：</td>
		<td>
			<input type="text" id="dept_name" readonly="readonly" style="cursor:hand" onclick="openDeptSelect('dept_name','dept_id','radio')" />
			<input type="hidden" name="dept_id" id="dept_id"/>
		</td>
    </tr>
	 -->
	 <input type="hidden" name="dept_id" id="dept_id" value="01"/>
    <tr>
    	<td align="center"><font color="red">*</font>控制台：</td>
	    <td>
			<select name="console_code" id="console_code" onChange="checkPrinterNum(this.value)">
				<option value="">--请选择--</option>
				<s:iterator value="#request.consoleList" var="console">
					<option value="${console.console_code}">${console.console_name}</option>
				</s:iterator>
			</select>
		</td>
    </tr>
    <tr>
    	<td align="center"><font color="red">*</font>密级：</td>
	    <td>
			<select name="seclv_code" id="seclv_code">
				<option value="">--请选择--</option>
				<s:iterator value="#request.seclvList" var="seclv">
					<option value="${seclv.seclv_code}">${seclv.seclv_name}</option>
				</s:iterator>
			</select>
		</td>
    </tr>
    <tr>
    	<td align="center">打印机类型：</td>
		<td>
			<select id="printer_type" name="printer_type">
				<option value="普通打印机">普通打印机</option>
				<option value="网络打印机">网络打印机</option>
			</select>
		</td>
    </tr>
    <tr>
    	<td align="center">颜色：</td>
		<td>
			<select id="printer_color" name="printer_color">
				<option value="黑白">黑白</option>
				<option value="彩打">彩打</option>
			</select>
		</td>
    </tr>
    <tr>
    	<td align="center">是否支持双面：</td>
		<td>
			<select id="is_double" name="is_double">
			    <option value="是">是</option>
				<option value="否">否</option>			
			</select>
		</td>
    </tr>
    <tr>
    	<td align="center">品牌：</td>
		<td>
			<input type="text" name="printer_brand" id="printer_brand"/>
		</td>
    </tr>
    <tr>
    	<td align="center">型号：</td>
		<td>
			<input type="text" name="printer_model" id="printer_model"/>
		</td>
    </tr>
    <tr>
    	<td align="center">地理位置：</td>
		<td>
			<input type="text" name="printer_location" id="printer_location"/>
		</td>
    </tr>
    <tr>
    	<td align="center">网络打印机IP：</td>
		<td>
			<input type="text" name="printer_ipaddr" id="printer_ipaddr"/>
		</td>
    </tr>
    <tr>
        <td colspan="2" align="center" class="bottom_box">
            <input type="submit" value="添加" class="button_2003" onclick="return chk();">&nbsp;
            <input type="reset" value="重置" class="button_2003">
            <input type="button" value="关闭" class="button_2003" onclick="go('${ctx}/basic/manageprinter.action?printer_code=${item.printer_code}');">&nbsp;
        </td>
    </tr>
  	</table>
</form>
</center>
</body>
</html>