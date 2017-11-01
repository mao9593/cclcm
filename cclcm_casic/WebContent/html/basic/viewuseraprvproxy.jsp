<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<title>查看代理记录</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="${ctx}/_css/displaytag.css" media="screen"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/_css/css200603.css"  media="screen"/>
    <script language="javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>
    <script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
    <script>
	<!--
	$(document).ready(function(){
		onHover();
		init();
	});
	function init(){
		var size=${proxy_size};
		if (size>=5){
			$(":input[name='button_1']").attr("disabled","disabled");	
		}
	}
	function confirmDel(){
		if(confirm("确定要取消当前代理人？")){
			return true;
		}
	}
	//-->
	</script>
</head>
<body oncontextmenu="self.event.returnValue=false">
<table width="95%" border="0" cellspacing="0" cellpadding="0" align="center" class="table_only_border">
	<tr>
		<td class="title_box">代理审批人列表</td>
	</tr>
		<form id="QueryCondForm" method="POST" action="${ctx}/basic/addaprvproxy.action">
			<td class="nav_box" align="right">
				<input name="button_1" type="submit" class="button_2003" value="添加" >
			</td>
	 	</form>
	<tr>
		<form name="form1"> 
		<td>
			<table class="displaytable-outter" cellspacing=0 cellpadding=0 border=0 width="100%">
	 			<tr>
	   				<td>
					<display:table requestURI="${ctx}/basic/viewuseraprxproxy.action" id="item" class="displaytable" name="proxys" pagesize="15" sort="list">
						<display:column title="序号">
							<c:out value="${item_rowNum}"/>
						</display:column>
						<display:column title="申请人姓名" property="user_name"/>
						<display:column title="代理人姓名" property="proxy_user_name"/>
						<display:column title="代理类型" property="proxy_name"/>
						<display:column title="有效时间" property="useful_life_time"/>
						<display:column title="操作" style="width:50px">
							<input type="button" class="button_2003" value="取消代理" onclick="if (confirmDel()) go('${ctx}/basic/deleteuseraprvproxy.action?proxy_user_iidd=${item.proxy_user_iidd}');"/>
						</display:column>
					</display:table>
					</td>
				</tr>
			</table>
         </td>
        </form> 
	</tr>
</table>
</body>
</html>
