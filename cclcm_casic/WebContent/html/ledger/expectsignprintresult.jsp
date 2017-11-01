<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
<title>标记失败</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<link rel="stylesheet" type="text/css" href="${ctx}/_css/displaytag.css" media="screen"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/_css/css200603.css"  media="screen"/>
	<script language="JavaScript" src="${ctx}/_script/function.js"></script>
	<script language="javascript" src="${ctx}/_script/casic304_common.js"></script>	
	<script language="javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>	
	<script language="javascript" src="${ctx}/_script/casic304_ajax.js"></script>
	<script>
	function chk(paper_id){
			if($("#real_page_count").val().trim() == ""){
		     	alert("实际出纸页数不能为空!");
	            return false;
	          }
		if($("#fail_remark").val().trim() == ""){
			alert("请填写备注");
			$("#fail_remark").focus();
			return false;
		}		
	    return true;
	}	
	function onOK(paper_id){
		if(chk(paper_id)){
			var ret = new Object();
			if(paper_id != null && paper_id != ""){
				ret.real_page_count = $("#real_page_count").val().trim();
			}		
			ret.fail_remark = $("#fail_remark").val().trim();
			window.returnValue=ret;
			window.close();  
		}
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
	<c:if test="${!empty paper_id and paper_id != ''}">
		<tr> 
			<td align="right"><font color="red">*</font>实际出纸页数：</td>
	    	<td align="center"><input type="text" id="real_page_count" name="real_page_count" size="32"/>&nbsp;</td> 	
		</tr>
	</c:if>
	<tr> 
		<td align="right"><font color="red">*</font>备注：</td>
    	<td align="center"><textarea name="fail_remark" rows="3" cols="30" id="fail_remark"></textarea></td>   	
	</tr>
	<tr> 
		<td colspan="2" align="center">
			<input type="button" class="button_2003" value="提交" onclick="onOK('${paper_id}');" />&nbsp;
			<input type="button" class="button_2003" value="取消" onclick="onCancel();" />
		</td>
	</tr>
</table>
</center>
</body>
</html>
