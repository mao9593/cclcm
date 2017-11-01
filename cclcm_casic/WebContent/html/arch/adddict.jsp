<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新增字典</title>
<link rel="stylesheet" href="${ctx}/_css/css200603.css" type="text/css" media="screen" />
<script language="JavaScript" src="${ctx}/_script/function.js"></script>
<script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
<script type="text/javascript" src="${ctx}/uploadify/jquery.min.js"></script>
<script  language="JavaScript" >
<!--
function chk()
{
    if($("#dict_name").val().trim() == "")	
    {
        alert("请填写字典名称");
        $("#dict_name").focus();
        return false;
    }
    return true;
}
function addNewValue(){
	var newValue=$("#input_add input[type='text']").val().trim();
	if(newValue == ""){
		alert("请在输入框中填写具体取值后，再点击‘添加’按钮");
        $("#input_add input[type='text']").focus();
        return false;
	}else{
		addNewValueDom(newValue);
		$("#input_add input[type='text']").val("");
	}
}
function addNewValueDom(v){
	var $tr = $("<tr>");
	var $td = $("<td>");
	var $vl = $("<input>",{type:"text",name:"dict_value",readonly:"readonly",value:v});
	var $db = $("<input>",{type:"button",'class':"button_2003",value:"删除",click:function(){
		if(confirm("确定要删除该字典取值吗？")){
			$tr.remove();
		}
	}});
	$td.append($vl);
	$td.append($db);
	$tr.append($td);
	$("#input_add").before($tr);
	
}
	//-->
</script>
</head>
<body onload="onHover();" oncontextmenu="self.event.returnValue=false">
<center>
<form method="post" action="${ctx}/arch/adddict.action">
<input type="hidden" name="add" value="Y"/>
  <table class="table_box" cellspacing=0 cellpadding=0 border=1 width="50%">
	 <tr>
		 <td colspan="2" class="title_box">
            	新增字典
        </td>
    </tr>
    <tr>
      <td width="40%" align="center"><font style="color:red">*</font>字典名称：</td>
      <td><input type="text" id="dict_name" name="dict_name" maxlength="15"/></td>
    </tr>
    <tr>
      <td align="center">候选取值：</td>
      <td>
      	<table  cellspacing=0 cellpadding=0 border=0>
      		<tr id="input_add"><td><input type="text" maxlength="15"/><input type="button" class="button_2003" value="添加" onclick="addNewValue();"/></td></tr>
      	</table>
      </td>
    </tr>
    <tr>
        <td colspan="2" align="center" class="bottom_box">
            <input type="button" value="提交" class="button_2003" onclick="if(chk()) forms[0].submit();">&nbsp;
            <input type="button" value="返回" class="button_2003" onclick="location.href='${ctx}/arch/managedict.action'">
        </td>
    </tr>
  </table>
  </form>
</center>
</body>
</html>
