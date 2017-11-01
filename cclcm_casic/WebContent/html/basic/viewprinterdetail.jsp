<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
<title>打印机详细信息</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="${ctx}/_css/css200603.css" type="text/css" media="screen"/>
	<script language="javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>
	<script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
</head>
<body oncontextmenu="self.event.returnValue=false">
<center>
<form method="post" action="${ctx}/basic/updateprinter.action">
	<input type="hidden" value="${printer.printer_code}" name="printer_code"/>
	<input type="hidden" value="Y" name="update"/>
   	<table class="table_box" cellspacing=0 cellpadding=0 border=1 width="60%">
	 <tr>
		 <td colspan="2" class="title_box">
            	打印机详细信息
        </td>
    </tr>
    <tr>
    	<td align="center"><font color="red">*</font>打印机名称：</td>
		<td>
			
		<%-- 	<input type="text" name="printer_name" id="printer_name" readonly="readonly" value="${printer.printer_name}"/> --%>
		<textarea rows="3" cols="60" name="printer_name" id="printer_name" readonly="readonly" >${printer.printer_name}</textarea>
		</td>
    </tr>
    <tr>
    	<td align="center"><font color="red">*</font>打印机路径：</td>
		<td>
			<input type="text" name="printer_path" id="printer_path" readonly="readonly" value="${printer.printer_path}"/>
		</td>
	<!-- 
    </tr>
        <tr>
    	<td align="center"><font color="red">*</font>所属部门：</td>
		<td>
			<input type="text" id="dept_name" value="${printer.dept_name}"  readonly="readonly" />
			<input type="hidden" name="dept_id" id="dept_id" value="${printer.dept_id}"/>
		</td>
    </tr>
	 -->
    <tr>
    	<td align="center"><font color="red">*</font>控制台：</td>
	    <td>
	    	<c:set var="console1" value="${printer.console_code}" scope="request"/>
			<select name="console_code" id="console_code" disabled="none">
				<option value="">--请选择--</option>
				<s:iterator value="#request.consoleList" var="console">
					<option value="${console.console_code}" <s:if test="#request.console1==#console.console_code">selected="selected"</s:if>>${console.console_name}</option>
				</s:iterator>
			</select>
		</td>
    </tr>
    <tr>
    	<td align="center"><font color="red">*</font>密级：</td>
	    <td>
	    	<c:set var="seclv1" value="${printer.seclv_code}" scope="request"/>
			<select name="seclv_code" id="seclv_code" disabled="none">
				<option value="">--请选择--</option>
				<s:iterator value="#request.seclvList" var="seclv">
					<option value="${seclv.seclv_code}" <s:if test="#request.seclv1==#seclv.seclv_code">selected="selected"</s:if>>${seclv.seclv_name}</option>
				</s:iterator>
			</select>
		</td>
    </tr>
    <tr>
    	<td align="center">打印机类型：</td>
		<td>
			<select id="printer_type" disabled="none" name="printer_type">
				<option value="普通打印机">普通打印机</option>
				<option value="网络打印机">网络打印机</option>
			</select>
		</td>
    </tr>
    <tr>
    	<td align="center">颜色：</td>
		<td>
			<select id="printer_color" disabled="none" name="printer_color">
				<option value="黑白">黑白</option>
				<option value="彩打">彩打</option>
			</select>
		</td>
    </tr>
    <tr>
    	<td align="center">是否支持双面：</td>
		<td>
			<select id="is_double" disabled="none" name="is_double">
			    <option value="是">是</option>
				<option value="否">否</option>			
			</select>
		</td>
    </tr>
    <tr>
    	<td align="center">品牌：</td>
		<td>
			<input type="text" name="printer_brand" id="printer_brand" readonly="readonly" value="${printer.printer_brand}"/>
		</td>
    </tr>
    <tr>
    	<td align="center">型号：</td>
		<td>
			<input type="text" name="printer_model" id="printer_model" readonly="readonly" value="${printer.printer_model}"/>
		</td>
    </tr>
    <tr>
    	<td align="center">地理位置：</td>
		<td>
			<input type="text" name="printer_location" id="printer_location" readonly="readonly" value="${printer.printer_location}"/>
		</td>
    </tr>
    <tr>
    	<td align="center">网络打印机IP：</td>
		<td>
			<input type="text" name="printer_ipaddr" id="printer_ipaddr" readonly="readonly" value="${printer.printer_ipaddr}"/>
		</td>
    </tr>
    <tr>
    	<td align="center">上次连接时间：</td>
		<td>
			<input type="text" name="last_connect_time" id="last_connect_time" readonly="readonly" value="${printer.last_connect_time_str}"/>
		</td>
    </tr>
    <tr>
    	<td align="center">创建时间：</td>
		<td>
			<input type="text" name="create_time" id="create_time" readonly="readonly" value="${printer.create_time_str}"/>
		</td>
    </tr>
    <tr>
    	<td align="center">删除时间：</td>
		<td>
			<input type="text" name="delete_time" id="delete_time" readonly="readonly" value="${printer.delete_time_str}"/>
		</td>
    </tr>
    <tr>
        <td colspan="2" align="center" class="bottom_box">
            <input type="button" value="关闭" class="button_2003" onclick="go('${ctx}/basic/manageprinter.action?printer_code=${item.printer_code}');">&nbsp;
        </td>
    </tr>
  	</table>
</form>
</center>
</body>
</html>