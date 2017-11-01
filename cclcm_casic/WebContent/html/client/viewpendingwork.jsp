<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<title>查看未读待办任务列表</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="${ctx}/_css/displaytag.css" media="screen"/>
   <link rel="stylesheet" type="text/css" href="${ctx}/_css/css200603.css"  media="screen"/>
   <script language="javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>
    <script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
    <script>
    function setMessageIsRead(){
		if(confirm("确定要删除所有未读消息？")){
			go("${ctx}/client/viewpendingwork.action?setIsRead=1");
		}
	}
    </script>
</head>

<body oncontextmenu="self.event.returnValue=false">
<table width="95%" border="0" cellspacing="0" cellpadding="0" align="center">
	<tr>
		<td align="right">
			<input type="button" class="button_2003" value="删除所有未读消息" onclick="setMessageIsRead()"/>
		</td>
	</tr>
</table>
</br>
<table width="95%" border="0" cellspacing="0" cellpadding="0" align="center" class="table_only_border">
	<tr>
		<td class="title_box">您未读的审批结果列表</td>
	</tr>
	<tr>
		<td>
			<table class="displaytable-outter" cellspacing=0 cellpadding=0 border=0 width="100%">
	 			<tr>
	   				<td>
					<display:table id="item" class="displaytable" name="approvedList">
						<display:column property="item_name" title="申请类型"/>
						<display:column property="pass_intro" title="审批结果"/>	
						<display:column property="pass_num" title="数量"/>	
						<display:column property="rej_intro" title="审批结果"/>	
						<display:column property="rej_num" title="数量"/>	
						<display:column title="进入查看">
							<input type="button" class="button_2003" value="进入" onclick="go('${ctx}/${item.item_url}');"/>
						</display:column>
					</display:table>
					</td>
				</tr>
			</table>
         </td>
	</tr>
</table>
</br></br>
<table width="95%" border="0" cellspacing="0" cellpadding="0" align="center" class="table_only_border" <c:if test="${needApprove == 'N'}">style="display: none"</c:if>>
	<tr>
		<td class="title_box">待您审批的任务列表</td>
	</tr>
	<tr>
		<td>
			<table class="displaytable-outter" cellspacing=0 cellpadding=0 border=0 width="100%">
	 			<tr>
	   				<td>
					<display:table id="item" class="displaytable" name="waitApproveList">
						<display:column property="item_name" title="申请类型"/>
						<display:column property="wait_intro" title="审批结果"/>	
						<display:column property="wait_num" title="数量"/>	
						<display:column title="进入查看">
							<input type="button" class="button_2003" value="进入" onclick="go('${ctx}/${item.item_url}');"/>
						</display:column>
					</display:table>
					</td>
				</tr>
			</table>
         </td>
	</tr>
</table>
</body>
</html>
