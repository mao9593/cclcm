<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<title>查看密级</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="${ctx}/_css/displaytag.css" media="screen"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/_css/css200603.css"  media="screen"/>
    <script language="javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>
    <script language="javascript" src="${ctx}/_script/casic304_ajax.js"></script>
    <script>
<!--
	function go(url){
		window.location.href=url;
	}
	function onclick1(user_count,seclv_code){	
   		var url="${ctx}/user/delseclv.action?seclv_code="+escape(seclv_code);
    	if(confirm("确定停用该密级？")){
	   		xmlHttp.open("GET", url, true);
			xmlHttp.onreadystatechange = updatePage;
			xmlHttp.send(null);
    	}   
	}
	function onclick2(seclv_code){
		if(parseInt(${seclvCount}) >= 8){
    		alert("在用密级不能超过8个");
			return false;
    	}else{
	   	 	var url="${ctx}/user/recoverseclv.action?seclv_code="+escape(seclv_code);
	   	 	if(confirm("确定启用该密级？")){
		    	xmlHttp.open("GET", url, true);
				xmlHttp.onreadystatechange = updatePage;
				xmlHttp.send(null);
	    	}
	    }
	}
	function updatePage() {
		if (xmlHttp.readyState == 4) {
			  var response = xmlHttp.responseText;
			  QueryCondForm.submit();
		}
	}
	function clearFindForm(){
		$("#is_sealed").val("");
	}
//-->
</script>
</head>

<body oncontextmenu="self.event.returnValue=false">
<form method="POST" action="${ctx}/user/viewseclevel.action" id="QueryCondForm" name="QueryCondForm">
<table width="100%" border="0" cellspacing="1" cellpadding="1" height="100%">
  <tr> 
    <td height="450" align="center" valign="top">       
        <table width="95%" border="0" cellspacing="0" cellpadding="0" align="center" class="table_only_border">
          <tr class="layouttr">
            <td class="title_box">查看密级</td>
          </tr>
          <tr>
			  <td class="nav_box" align="right">
			  		状态：&nbsp;
			  		<select name="is_sealed" id="is_sealed">
	                <option value="">--全部--</option>
					<option value="N" <c:if test="${is_sealed == 'N'}">selected="selected"</c:if>>已启用</option>
					<option value="Y" <c:if test="${is_sealed == 'Y'}">selected="selected"</c:if>>已停用</option>
	            </select>&nbsp;&nbsp;
	            <input type="submit" class="button_2003" value="查询" />
	        	<input type="button" value="清空" class="button_2003" onClick="clearFindForm()" />&nbsp;&nbsp;&nbsp;&nbsp;
				<input name="button" type="button" class="button_2003" onClick="go('${ctx}/user/addseclv.action');" value="增加密级">&nbsp;
			  </td>
          </tr>
          <tr>
            <td valign="top">
			<table class="displaytable-outter" cellspacing=0 cellpadding=0 border=0 width="100%">
		    <tr>
		    <td>
			<display:table uid="seclv" class="displaytable" name="seclvList" excludedParams="*" form="QueryCondForm">
				<display:column title="序号">
					<c:out value="${seclv_rowNum}"/>
				</display:column>
				<display:column property="seclv_name" title="密级名称" />
				<display:column property="othername" title="密级编码" />
				<display:column property="seclv_rank" title="密级排序" />
				<display:column title="操作" style="width:150px">
					<c:if test="${!seclv.is_delete}">
						<input type="button" class="button_2003" value="配置" onclick="go('${ctx}/basic/configseclv.action?seclv_code=${seclv.seclv_code}');"/>&nbsp;
						<input type="button" class="button_2003" value="修改" onclick="go('${ctx}/user/updateseclv.action?seclv_code=<c:out value="${seclv.seclv_code}"/>');"/>&nbsp;
						<input type="button" class="button_2003" value="停用" onclick="onclick1('${seclv.user_count}','${seclv.seclv_code}')"/>
					</c:if>
					<c:if test="${seclv.is_delete}">
						<input type="button" class="button_2003" value="配置" disabled="disabled"/>&nbsp;
						<input type="button" class="button_2003" value="修改" disabled="disabled"/>&nbsp;
						<input type="button" class="button_2003" value="启用" onclick="onclick2('${seclv.seclv_code}')"/>
					</c:if>
				</display:column>
			</display:table>
			</td>
			</tr>
			</table>
            </td>
          </tr>
          <tr>
          	<td>
          	<div>
          		<font color="red" >提示：密级排序数字越小，密级越高。</font>
          	</div>
          	</td>
          </tr>
        </table>
	</td>
  </tr>
</table>
</form>
</body>
</html>
