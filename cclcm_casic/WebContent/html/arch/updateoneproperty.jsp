<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
<base target="_self">
<title>添加字段</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<link rel="stylesheet" type="text/css" href="${ctx}/_css/displaytag.css" media="screen"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/_css/css200603.css"  media="screen"/>
	<script language="JavaScript" src="${ctx}/_script/function.js"></script>
	<script language="javascript" src="${ctx}/_script/casic304_common.js"></script>	
	<script language="javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>	
	<script language="javascript" src="${ctx}/_script/casic304_ajax.js"></script>
	<script>
	$(document).ready(function(){
			onHover();
			var obj = window.dialogArguments;
			$("#name").val(obj.name);
			$("#type").val(obj.type);
			chk();
			$("#template_id").val(obj.template_id);
			$("#property_name").val(obj.property_name);
			});
	function sbt(){
		var url = "${ctx}/arch/updateoneproperty.action";
		callServerPostForm(url,document.getElementById("firstForm"));
		  
	}	
	function onCancel(){
		window.close();
	}
 	function chk(){
		if($("#type").val()=="e"){
			var url="${ctx}/arch/getdictionary.action";
			callServer1(url);
			$("#select").show();
		}else{
			$("#select").hide();
		}
	}
function getAjaxResult(){
	if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
			alert("修改完成");
			window.close();
	}
}
	function updateResult(){
		if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {					
			if(xmlHttp.responseText != null && "" != xmlHttp.responseText){
			    var archDictInfo= xmlHttp.responseText.split("\^");
			    	for (var i=0;i<archDictInfo.length-1;i++){
			    			var archDict = archDictInfo[i].split("|");
			    		$("#dirc").append("<option value="+archDict[0]+">"+archDict[1]+"</option>");
			    	}
			}
		}
	}  
	</script>
</head>
<body oncontextmenu="self.event.returnValue=false">
<form  action="${ctx}/arch/updateoneproperty.action" method="get" id="firstForm">
<input type="hidden" name="template_id" id="template_id">
<input type="hidden" name="property_name" id="property_name">
<center>
<table width="95%" border="1" cellspacing="0" cellpadding="0" align="center" class="table_box" style="margin-top:30px">
	<tr>
		<td class="title_box" colspan="2">字段设置</td>
	</tr>
	<tr >
		<td align="center">字段名称</td>
		<td align=><input type="text" name="name" id="name" >	
		</td>
	</tr>
	<tr >
		<td align="center">数据类型</td>
		<td align="left">
		<select name="type" id="type"  onchange="chk()" >
        		<option value="s">文　字</option>
        		<option value="i">数　值</option>
        		<option value="d">日　期</option>
        		<option value="e">下划框</option>
    	</select>
		</td>
	</tr>
	<tr id="select" style="display:none" >
		<td align="center">选项字典</td>
		<td >
		<select name="dirc" id="dirc" >
		</select>
		</td>
	</tr>
	
	<tr> 
		<td colspan="2" align="center">
			<input type="submit" class="button_2003" value="修改" onclick="sbt()"/>&nbsp;
			<input type="button" class="button_2003" value="取消" onclick="onCancel();" />
		</td>
	</tr>
</table>
</center>
</form>
</body>
</html>
