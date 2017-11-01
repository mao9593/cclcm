<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<title>查看关键字列表</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="${ctx}/_css/displaytag.css" media="screen"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/_css/css200603.css"  media="screen"/>
    <script language="javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>
    <script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
    <script language="javascript" src="${ctx}/_script/casic304_ajax.js"></script>  
	<script>
	$(document).ready(function(){
		onHover();
	});
	function clearFindForm(){
		$("#Form :text").val("");
	}	
	function deleteKeyword(keyword_code){
	    var url="${ctx}/basic/delkeyword.action?keyword_code="+escape(keyword_code);
	    if(confirm("确定删除该关键字？")){
	    	window.location.href =url;
	    }
	}
	function changeKeyword(type){
		var url = "${ctx}/basic/managekeyword.action?update=Y&type="+escape(type);
		callServer(url);
	}
	function updateResult(){
		if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
			alert("配置成功");
			$("#Form").submit();
		}
	}
	function addKeyword(){
	    if(parseInt(${keyword_count})>=5){
	    	alert("关键字不可超过五个");
			return false;
	    }else{
			go("${ctx}/basic/addkeyword.action");
	    }
	}
	</script>
</head>

<body oncontextmenu="self.event.returnValue=false">
<form  id="Form" method="POST" action="${ctx}/basic/managekeyword.action">
<table width="95%" border="0" cellspacing="0" cellpadding="0" align="center" class="table_only_border">
	<tr>
		<td class="title_box">关键字列表</td>
	</tr>
	<tr>
		<td align="right">
			<table  table border=0 class="table_box" cellspacing="0" cellpadding="0" width="100%">
				<tr>
					<td align="left">&nbsp;&nbsp;&nbsp;&nbsp;当前关键字检测启用状态：
						<c:choose>
							<c:when test="${keyword_start==0}">
								<font color="red">已关闭</font>&nbsp;&nbsp;&nbsp;&nbsp;
								<input name="button" type="button" class="button_2003" onClick="changeKeyword('open');" value="启用检测关键字 ">
							</c:when>
							<c:otherwise>
								<font color="red">已开启</font>&nbsp;&nbsp;&nbsp;&nbsp;
								<input name="button" type="button" class="button_2003" onClick="changeKeyword('close');" value="关闭检测关键字 ">
							</c:otherwise>
						</c:choose>
					</td>
					<td align="right" width="300">
						关键字：
						<input type="text" id="keyword_content" name="keyword_content" value="${keyword_content}" size="15"/>&nbsp;&nbsp;&nbsp;&nbsp;
						<input name="button" type="submit" class="button_2003" value="查询">&nbsp;
						<input name="button" type="button" class="button_2003" value="清空" onclick="clearFindForm();">&nbsp;&nbsp;
					</td>
					<td align=center width="150">
						<input name="button" type="button" class="button_2003" onClick="addKeyword();" value="增加新关键字 ">
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
					<display:table requestURI="${ctx}/basic/managekeyword.action" id="item" class="displaytable" name="keywordList">
						<display:column title="序号">
							<c:out value="${item_rowNum}"/>
						</display:column>
						<display:column property="keyword_content" title="关键字"  maxLength="15"/>
						<display:column property="keyword_detail" title="关键字说明"  maxLength="30"/>	
						<display:column property="update_time_str" title="更新时间"  sortable="true"/>							
						<display:column title="操作" style="width:150px">
							<input type="button" class="button_2003" value="修改" onclick="go('${ctx}/basic/updatekeyword.action?keyword_code=${item.keyword_code}');"/>&nbsp;
							<input type="button" class="button_2003" value="删除" onclick="deleteKeyword('${item.keyword_code}')";/>
						</display:column>
					</display:table>
					</td>
				</tr>
			</table>
         </td>
	</tr>
</table>
</form>
</body>
</html>
