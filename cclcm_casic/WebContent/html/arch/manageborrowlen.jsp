<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<title>设置档案借用时长</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="${ctx}/_css/displaytag.css" media="screen"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/_css/css200603.css"  media="screen"/>
    <script language="javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>
    <script language="javascript" src="${ctx}/_script/casic304_ajax.js"></script>
    <script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
    <script>
<!--
	function modLength(tag){
		var len = $(tag).text();
		var seclv_code = $(tag).attr("id");
		var $form =$("<form>",{id:'setBorrowLenForm',action:"${ctx}/arch/setborrowlen.action"});
		var $inputl = $("<input>",{id:'bow_len',name:'length',type:'text',value:len,blur:setBorrowLen,keypress:isPressEnter});
		var $inputs = $("<input>",{type:'hidden',name:'seclv_code',value:seclv_code});
		$form.append($inputl);
		$form.append($inputs);
		$(tag).before($form);
		$(tag).remove();
		$inputl.focus();	
	}
	function isPressEnter(){
		if(event.keyCode==13){
			//让当前输入框失去焦点即可
			$(".title_box").focus();
			return false;
		}
	}
	function setBorrowLen(){
		var bow_len = $("#bow_len").val().trim();
		if(checkBorrowLen(bow_len)){
			$("#setBorrowLenForm").submit();
		}
	}
	//判断输入是否合法，合法输入为数字，且天数小于1000
	function checkBorrowLen(bow_len){
		var code_pattern=/^[0-9]+$/;
		if(!code_pattern.test(bow_len)){
			alert("借用期限只能输入数字");
			$("#bow_len").val("");
			$("#bow_len").focus();
			return false;
		}
		if(parseInt(bow_len)>1000){
			alert("借用期限不能大于1000天");
			$("#bow_len").val("");
			$("#bow_len").focus();
			return false;
		}
		return true;
	}
//-->
</script>
</head>

<body oncontextmenu="self.event.returnValue=false">
<table width="100%" border="0" cellspacing="1" cellpadding="1" height="100%">
  <tr> 
    <td height="450" align="center" valign="top">       
        <table width="95%" border="0" cellspacing="0" cellpadding="0" align="center" class="table_only_border">
          <tr class="layouttr">
            <td class="title_box">设置档案借用时长</td>
          </tr>
          <tr>
		    <td>
			<display:table uid="len" class="displaytable" name="lenList">
				<display:column title="序号" style="width:80px">
					<c:out value="${len_rowNum}"/>
				</display:column>
				<display:column property="seclv_name" title="密级名称" style="width:400px"/>
				<display:column title="借用期限(天)">
					<label id="${len.seclv_code}" ondblclick="modLength(this);">${len.length}</label>
				</display:column>
			</display:table>
			</td>
          </tr>
          <tr>
          	<td>
          	<div>
          		<font color="red" >提示：双击借用期限单元格内的数字可直接进行修改</font>
          	</div>
          	</td>
          </tr>
        </table>
	</td>
  </tr>
</table>
</body>
</html>
