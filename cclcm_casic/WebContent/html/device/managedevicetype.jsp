<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<title>磁介质类型管理</title>
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
	function delType(id){	
   		var url="${ctx}/device/deldevicetype.action?id="+escape(id);
    	if(confirm("确定删除该类型？")){
    		go(url);
	   		/* xmlHttp.open("GET", url, true);
			xmlHttp.onreadystatechange = updatePage;
			xmlHttp.send(null); */
    	}   
	}
	function updatePage() {
		if (xmlHttp.readyState == 4) {
			  var response = xmlHttp.responseText;
			  QueryCondForm.submit();
		}
	}
//-->
</script>
</head>

<body oncontextmenu="self.event.returnValue=false">
<form method="POST" action="${ctx}/device/managedevicetype.action" id="QueryCondForm" name="QueryCondForm">
<table width="100%" border="0" cellspacing="1" cellpadding="1" height="100%">
  <tr> 
    <td height="450" align="center" valign="top">       
        <table width="95%" border="0" cellspacing="0" cellpadding="0" align="center" class="table_only_border">
          <tr class="layouttr">
            <td class="title_box">磁介质类型管理</td>
          </tr>
          <tr>
			  <td align="right">
				<input name="button" type="button" class="button_2003" onClick="go('${ctx}/device/adddevicetype.action');" value="增加新类型">&nbsp;&nbsp;
			  </td>
          </tr>
          <tr>
            <td valign="top">
				<table class="displaytable-outter" cellspacing=0 cellpadding=0 border=0 width="100%">
				    <tr>
					    <td>
							<display:table uid="type" class="displaytable" name="typeList" pagesize="15" excludedParams="*" form="QueryCondForm">
								<display:column title="序号">
									<c:out value="${type_rowNum}"/>
								</display:column>
								<display:column property="typename" title="类型名称" />
								<display:column property="content" title="类型描述" />
								<display:column title="操作" style="width:100px">
									<input type="button" class="button_2003" value="修改" onclick="go('${ctx}/device/updatedevicetype.action?id=${type.id}');"/>&nbsp;
									<input type="button" class="button_2003" value="删除" onclick="delType('${type.id}')"/>
								</display:column>
							</display:table>
						</td>
					</tr>
				</table>
            </td>
          </tr>
		  <tr>
		      <td align="center" class="bottom_box">
		          <input type="button" value="返回" class="button_2003" onclick="location.href='${ctx}/device/managedevice.action'">
		      </td>
		  </tr>
        </table>
	</td>
  </tr>
</table>
</form>
</body>
</html>
