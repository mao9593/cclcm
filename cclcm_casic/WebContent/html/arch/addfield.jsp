<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
<title>添加字段</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<link rel="stylesheet" type="text/css" href="${ctx}/_css/displaytag.css" media="screen"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/_css/css200603.css"  media="screen"/>
	<script language="JavaScript" src="${ctx}/_script/function.js"></script>
	<script language="javascript" src="${ctx}/_script/casic304_common.js"></script>	
	<script language="javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>	
	<script language="javascript" src="${ctx}/_script/casic304_ajax.js"></script>
	<script>
	function onOK(){
		var result = new Object();
		result.name=$("#name").val();
		result.type =$("#type").val();
		result.dirc =$("#dirc").val();
		window.returnValue=result;	
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
	function onCancel(){
		window.close();
	}
		function updateResult(){
		if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {					
			if(xmlHttp.responseText != null && "" != xmlHttp.responseText){
			    var archDictInfo= xmlHttp.responseText.split("\^");
			    	for (var i=0;i<archDictInfo.length-1;i++){
			    			var archDict = archDictInfo[i].split("|");
			    		$("#dirc").append("<option value="+archDict[0]+"|"+archDict[1]+">"+archDict[1]+"</option>");
			    	}
			}
		}
	} 
	</script>
</head>
<body oncontextmenu="self.event.returnValue=false">
<center>
<table width="95%" border="1" cellspacing="0" cellpadding="0" align="center" class="table_box" style="margin-top:30px">
	<tr>
		<td class="title_box" colspan="2">字段设置</td>
	</tr>
	<tr >
		<td align="center">字段名称</td>
		<td ><input type="text" name="name" id="name">	
		</td>
	</tr>
	<tr >
		<td align="center">数据类型</td>
		<td align=>
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
			<input type="button" class="button_2003" value="提交" onclick="onOK();" />&nbsp;
			<input type="button" class="button_2003" value="取消" onclick="onCancel();" />
		</td>
	</tr>
</table>
</center>
</body>
</html>
