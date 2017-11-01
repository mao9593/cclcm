<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<title>查看客户端版本列表</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="${ctx}/_css/displaytag.css" media="screen"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/_css/css200603.css"  media="screen"/>
    <script language="javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>
    <script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
    <script language="javascript" src="${ctx}/_script/casic304_ajax.js"></script>
    <script>
    <!--
   		function clearFindForm(){
			SelectForm.user_name.value = "";
			SelectForm.dept_name.value = "";
			SelectForm.dept_id.value = "";
		}
        function clientversion()
        {
            var url="${ctx}/client/updatecvs.action";      
			var rValue = window.showModalDialog(url,window,"dialogHeight:200px;dialogWidth:400px;center:yes;resizable:no;status:no;scroll:no;help:no");
			if(rValue != undefined && rValue != null)
    		{
    			return rValue;  
    		}else{
    			return false;
    		}
        }
        function setversion_single(cvs_code)
        { 
          	var version = clientversion();
          	//alert(version);
          	if(version != false){
	          	$("#hidSet_version").val(version);
	    		$("#hidCvs_code").val(cvs_code);
	    		$("#hidScope").val("single");
	    		var url="${ctx}/client/updatecvs.action";
	    		callServerPostForm(url,document.forms[1]);
          	}
        }
        function setversion_all()
        { 
          	var version = clientversion();
          	if(version != false){
	          	$("#hidSet_version").val(version);
	    		$("#hidScope").val("all");
	    		var url="${ctx}/client/updatecvs.action";
	    		callServerPostForm(url,document.forms[1]);
          	}
        }
        function setversion_dept()
        {
        	if($("#dept_id").val() != "" && $("#dept_id").val() != null)
    		{
    			var version = clientversion();
          	  	if(version != false){
	          		$("#hidSet_version").val(version);
	    			$("#hidScope").val("dept");
	    			$("#hidDept_id").val($("#dept_id").val());
	    			$("#hidDept_name").val($("#dept_name").val());
	    			var url="${ctx}/client/updatecvs.action";
	    			callServerPostForm(url,document.forms[1]);
          		}
          		
    		}else{
    			alert("请选择归属部门!");
    		}
          	
        }
        function openConfirm(module){
		    var url = "${ctx}/client/managecvs.action?update=Y&module="+escape(module)+"&type=open";
		    //alert(url);
		    callServer(url);
	    }
	    function closeConfirm(module){
		    var url = "${ctx}/client/managecvs.action?update=Y&module="+escape(module)+"&type=close";
		    //alert(url);
		    callServer(url);
    	}
    	function updateResult(){
		    if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
			    alert("修改成功");
			    SelectForm.submit();
		    }
	    }
        function getAjaxResult(){
	    	if(xmlHttp.readyState == 4 && xmlHttp.status == 200){
				alert("设定客户端版本["+$("#hidSet_version").val()+"]成功!");
				SetClientVersionForm.submit();
	    		SelectForm.submit();
			}
		}
		function exportLedger(formId,url,url1){
			document.getElementById(formId).action = url;
			document.getElementById(formId).submit();
			document.getElementById(formId).action = url1;
	}
    //-->    
    </script>
</head>

<body oncontextmenu="self.event.returnValue=false">

<table width="95%" border="0" cellspacing="0" cellpadding="0" align="center" class="table_only_border">
	
	<tr>
		<td class="title_box">客户端版本列表</td>
	</tr>
	<tr><td>
<form id="SelectForm" method="GET" action="${ctx}/client/managecvs.action" name="SelectForm">
<table border=0 class="table_box" cellspacing="0" cellpadding="0" width="100%">
	<tr>
        <td align="center">用户名：</td>
		<td>
			<input type="text" name="user_name" size="15" value="${user_name}"/>
		</td>
		<td align="center">归属部门：</td>
		<td>
			<input type="text" id="dept_name" name="dept_name" value="${dept_name}" readonly style="cursor:hand" size="15" styleClass="input_2k3" onclick="openDeptSelect('dept_name','dept_id','radio')" />
			<input type="hidden" name="dept_id" id="dept_id" value="${dept_id}"/>
		</td>
		<td align="center" width="30%">
			<input name="button" type="submit" class="button_2003" value="查询">&nbsp;&nbsp;
			<input name="button" type="button" class="button_2003" value="清空" onclick="clearFindForm();">&nbsp;&nbsp;&nbsp;&nbsp;	
			<input type="button" class="button_2003" value="导出EXCEL" onclick="exportLedger('SelectForm','${ctx}/client/exportcvs.action','${ctx}/client/managecvs.action');"/>
		</td>
		<td align="center" width="30%">
			<c:choose>
				<c:when test="${message_tips_start == '1'}">
					<font color="red">客户端消息提醒已开启!</font>&nbsp;&nbsp;&nbsp;&nbsp;																		
					<input type="button" class="button_2003" value="关闭消息提醒" onclick="closeConfirm('CLIENT_MSG');"/>
				</c:when>
				<c:otherwise>																		
					<font color="red">客户端消息提醒已关闭!</font>&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="button" class="button_2003" value="开启消息提醒" onclick="openConfirm('CLIENT_MSG');"/>
				</c:otherwise>
			</c:choose>	
		</td>
	</tr>
</table>
	</td>
	</tr>
	<tr>
		<td>
			<table class="displaytable-outter" cellspacing=0 cellpadding=0 border=0 width="100%">
	 			<tr>
	   				<td>
					<display:table requestURI="${ctx}/client/managecvs.action" id="item" class="displaytable" name="cVSList"  
					pagesize="15" sort="list" excludedParams="*" form="SelectForm">
						<display:column title="序号">
							<c:out value="${item_rowNum}"/>
						</display:column>
						<display:column property="user_name" title="用户名"/>
						<display:column property="dept_name" title="部门"  maxLength="15"/>
						<display:column property="computer_name" title="计算机名"  maxLength="15"/>
						<display:column property="ip_address" title="IP地址"/>
						<display:column property="curr_version" title="当前版本"  maxLength="15"/>
						<display:column property="set_version" title="设定版本"  maxLength="15"/>
						<display:column property="update_time_str"   sortable="true" title="上次更新时间"/>
						<display:column property="last_time_str"   sortable="true" title="上次在线时间"/>
						<display:column title="操作" style="width:150px">
							<c:if test="${!item.is_delete}">
								<input type="button" class="button_2003" value="详细" onclick="go('${ctx}/client/viewcvsdetail.action?cvs_code=${item.cvs_code}');"/>&nbsp;
							    <input type="button" class="button_2003" value="设定版本" onclick="setversion_single('${item.cvs_code}');"/>
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
		    <input name="button" type="button" class="button_2003" onClick="setversion_dept();" value="部门批量设定">
		</td>
	</tr>
</form>
</table>
<form id="SetClientVersionForm" method="post" action="${ctx}/client/updatecvs.action" >
	    <input type="hidden" value="" id="hidCvs_code" name="hidCvs_code"/>
	    <input type="hidden" value="" id="hidSet_version" name="hidSet_version"/>
	    <input type="hidden" value="" id="hidScope" name="hidScope"/>
	    <input type="hidden" value="" id="hidDept_id" name="hidDept_id"/>
	    <input type="hidden" value="" id="hidDept_name" name="hidDept_name"/>
	    <input type="hidden" name="is_set" value="true"/>
</form>
</body>
</html>
