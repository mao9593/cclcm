<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<title>查看控制台版本列表</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="${ctx}/_css/displaytag.css" media="screen"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/_css/css200603.css"  media="screen"/>
    <script language="javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>
    <script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
    <script language="javascript" src="${ctx}/_script/casic304_ajax.js"></script>
    <script>
    <!--
        function clientversion()
        {
            var url="${ctx}/basic/updateconsoleversion.action";      
			var rValue = window.showModalDialog(url,window,"dialogHeight:200px;dialogWidth:400px;center:yes;resizable:no;status:no;scroll:no;help:no");
			if(rValue != undefined && rValue != null)
    		{
    			return rValue;  
    		}else{
    			return false;
    		}
        }
        function setversion_single(console_code)
        { 
          	var version = clientversion();
          	alert(version);
          	if(version != false){
	          	$("#hidSet_version").val(version);
	    		$("#hidConsole_code").val(console_code);
	    		$("#hidScope").val("single");
	    		var url="${ctx}/basic/updateconsoleversion.action";
	    		callServerPostForm(url,document.forms[0]);
          	}
        }
        function setversion_all()
        { 
          	var version = clientversion();
          	if(version != false){
	          	$("#hidSet_version").val(version);
	    		$("#hidScope").val("all");
	    		var url="${ctx}/basic/updateconsoleversion.action";
	    		callServerPostForm(url,document.forms[0]);
          	}
        }
        function getAjaxResult(){
	    	if(xmlHttp.readyState == 4 && xmlHttp.status == 200){
				alert("设定客户端版本["+$("#hidSet_version").val()+"]成功!");
				SetConsoleVersionForm.submit();
			}
		}
    //-->    
    </script>
</head>

<body oncontextmenu="self.event.returnValue=false">

<table width="95%" border="0" cellspacing="0" cellpadding="0" align="center" class="table_only_border">
	
	<tr>
		<td class="title_box">控制台版本列表</td>
	</tr>
<form id="SetConsoleVersionForm" method="post" action="${ctx}/basic/manageconsoleversion.action" >
	    <input type="hidden" value="" id="hidConsole_code" name="hidConsole_code"/>
	    <input type="hidden" value="" id="hidSet_version" name="hidSet_version"/>
	    <input type="hidden" value="" id="hidScope" name="hidScope"/>
	    <input type="hidden" name="is_set" value="true"/>
	<tr>
		<td>
			
			<table class="displaytable-outter" cellspacing=0 cellpadding=0 border=0 width="100%">
	 			<tr>
	   				<td>
					<display:table requestURI="${ctx}/basic/manageconsole.action" id="item" class="displaytable" name="consoleList">
						<display:column title="序号">
							<c:out value="${item_rowNum}"/>
						</display:column>
						<display:column property="console_code" title="控制台代号"/>
						<display:column property="console_name" title="控制台名称"/>
						<display:column property="hardware_type" title="硬件类型"/>
						<display:column property="console_type_name" title="控制台类型"/>
						<display:column property="seclv_name" title="密级"/>
						<display:column property="console_location" title="控制台位置"/>
						<display:column property="curr_version" title="当前版本"/>
						<display:column property="set_version" title="设定版本"/>
						<display:column title="操作" style="width:150px">
							<c:if test="${!item.is_delete}">
								<input type="button" class="button_2003" value="修改" onclick="go('${ctx}/basic/updateconsole.action?console_code=${item.console_code}');"/>&nbsp;
								<input type="button" class="button_2003" value="设定版本" onclick="setversion_single('${item.console_code}');"/>
							</c:if>
							<c:if test="${item.is_delete}">
								${item.deleteStatus}
							</c:if>
						</display:column>
					</display:table>
					</td>
				</tr>
			</table>
         </td>
	</tr>
	<tr>
		<td align="left">
			<input name="button" type="button" class="button_2003" onClick="setversion_all();" value="全局设定版本">&nbsp;&nbsp;
		</td>
	</tr>
</table>
</form>
</body>
</html>
