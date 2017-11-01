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
		$(document).ready(function(){
			init();
		});
		function init(){
			$("#cycle_type").val("${event.cycle_type}");
			var print_type = "${event.print_type}";
			$("#print_type").val(print_type);
			if(print_type == 2){
				$("#color").attr("disabled","disabled");
				$("#print_double").attr("disabled","disabled");
				$("#print_collate").attr("disabled","disabled");
				$("#add_file").attr("style","display:block inline");
			}
		}
		function chkStore(){
			$("#update").val("S");
			$("#UpdatePrintDraftForm").submit();
		}
		function chkComplete(){
			var checkFile=true;
			$("input[name=file_title]").each(function(){
				if($(this).val().trim() == ""){
					$(this).focus();
					checkFile = false;
				}
			});
			if(!checkFile){
				alert("文件名不能为空");
				return false;
			}
			$("#update").val("Y");
			$("#UpdatePrintDraftForm").submit();
			return true;
		}
		function changePaperType(val){
			if("2" == val){
				$("#color").attr("disabled","disabled");
				$("#print_double").attr("disabled","disabled");
				$("#print_collate").attr("disabled","disabled");
				$("#print_count").attr("disabled","disabled");
				$("#add_file").attr("style","display:block inline");
			}else{
				$("#app").children().remove();
				$("#color").removeAttr("disabled");
				$("#print_double").removeAttr("disabled");
				$("#print_collate").removeAttr("disabled");
				$("#print_count").removeAttr("disabled");
				$("#add_file").attr("style","display:none");
			}
		}
		function add_files(){
			$("#app").append('<tr name="addtr"><td align="center"><font color="red">*</font>&nbsp;文件名： </td><td colspan="3"><input type="text" name="file_title" value="${event.file_title}"/> &nbsp;<input class="button_2003" type="button" value="删除文件" id="add_file" onclick="removeFiles(this)"/></td></tr>');
		}
		function removeFiles(obj){
			$(obj).closest("tr").remove();
		}
	//-->
	</script>
</head>
<body oncontextmenu="self.event.returnValue=false">
<table width="95%" border="1" cellspacing="0" cellpadding="0" align="center" class="table_box" >
	<form action="${ctx}/print/updateprintdraft.action" method="post" id="UpdatePrintDraftForm">
		<input type="hidden" name="update" id="update"/>
		<input type="hidden" name="id" value="${event.id}"/>
	<tr>
	    <td colspan="4" class="title_box">修改打印草稿</td>
	</tr>
	<tr>
    	<td align="center" width="15%">申请用户： </td>
    	<td><font color="blue" width="35%"><b>&nbsp;${event.user_name}</b></font></td>
    	<td align="center" width="15%">用户部门： </td>
    	<td><font color="blue" width="35%"><b>&nbsp;${event.dept_name}</b></font></td>
	</tr>
	<tr>
		<td align="center">申请时间： </td>
    	<td><font color="blue"><b>&nbsp;${event.apply_time_str}</b></font></td>
    	
    	<td align="center"><font color="red">*</font>&nbsp;打印类型： </td>
		<td colspan="3">
			<select id="print_type" name="print_type" onchange="changePaperType(this[selectedIndex].value)">
	    		<option value="1">普通打印</option>
	    		<option value="2">拼图打印</option>
			</select>
		</td>
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
    	<td align="center"><font color="red">*</font>&nbsp;去向： </td>
    	<td>
    		<select id="cycle_type" name="cycle_type">
				<option value="">--请选择--</option>
    			<option value="REMAIN">留用</option>
    			<option value="FILE">归档</option>
    			<option value="SEND">外发</option>
			</select>
    	</td>
	</tr>
	<tr> 
		<td align="center"><font color="red">*</font>&nbsp;文件名： </td>
    	<td>
    		<input type="text" name="file_title" id="file_title" value="${event.file_title}"/> &nbsp;
    		<button class="button_2003" style="display:none" id="add_file" onclick="add_files()"/> 添加文件</button>
    	</td> 
		<td align="center">文件大小：</td>
	    <td><font color="blue"><b>&nbsp;${event.file_size}B</b></font></td>
  	</tr>
	<tr id="app"></tr>
	<tr>
		<td align="center">份数：</td>
	    <td><input type="text" name="print_count" id="print_count" value="${event.print_count}"/></td>
		<td align="center">纸张类型：</td>
	    <td>
	    	<font color="blue"><b>&nbsp;${event.page_size}</b></font>
	    	<input type="hidden" name="page_size" value="${event.page_size}"/>
		</td>
	</tr>
	<tr>
		<td align="center">色彩类型：</td>
	    <td>
	    	<select id="color" name="color">
	    		<option value="">--请选择--</option>
	    		<option value="1" <c:if test="${event.color == '1'}">selected</c:if>>黑白</option>
	    		<option value="2" <c:if test="${event.color == '2'}">selected</c:if>>彩色</option>
	    	</select>
	    </td>	
		<td align="center">单/双面：</td>
		<td>
			<select id="print_double" name="print_double">
	    		<option value="">--请选择--</option>
	    		<option value="1" <c:if test="${event.print_double == '1'}">selected</c:if>>单面</option>
	    		<option value="2" <c:if test="${event.print_double == '2'}">selected</c:if>>双面左右翻</option>
	    		<option value="3" <c:if test="${event.print_double == '3'}">selected</c:if>>双面上下翻</option>
	    	</select>
		</td>		
	</tr>
	<tr>
		<!-- 
		<td align="center">逐份/页：</td>
	    <td>
	    	<select id="print_collate" name="print_collate">
	    		<option value="">--请选择--</option>
	    		<option value="0" <c:if test="${event.print_collate == '0'}">selected</c:if>>逐页</option>
	    		<option value="1" <c:if test="${event.print_collate == '1'}">selected</c:if>>逐份</option>
	    	</select>
	    </td>	
		 -->
  		<td align="center">备注：</td>
		<td colspan="3"><textarea rows="5" cols="60" name="summ">${event.summ}</textarea></td>
	</tr>
  	<tr>
    <td colspan="4" align="center">
       	<input class="button_2003" type="button" value="完成" onClick="return chkComplete();">&nbsp;
       	<!-- 
    	<input class="button_2003" type="button" value="保存" onClick="chkStore();">&nbsp;
       	 -->
		<input class="button_2003" type="button" value="返回" onClick="javascript:history.go(-1);">&nbsp;
    </td>
	</tr>
	</form>
</table>
</body>
</html>
