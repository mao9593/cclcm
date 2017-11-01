<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<title>查看设置的流程列表</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="${ctx}/_css/displaytag.css" media="screen"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/_css/css200603.css"  media="screen"/>
    <script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
    <script language="javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>
<script>
<!--
	function updateProcess(process_id){
	    var url="${ctx}/activiti/updateprocess.action?process_id="+escape(process_id);
    	window.location.href =url;
	}
	function deleteProcess(process_id){
	    var url="${ctx}/activiti/delprocess.action?process_id="+escape(process_id);
	    if(confirm("确定删除该流程？")){
	    	window.location.href =url;
	    }
	}
	function clearFindForm(){
		$("#ProcessQueryCondForm :text").val("");
		$("#seclv_code").val("");
		$("#jobType_code").val("");
		$("#dept_id").val("");
	}
//-->
</script>
</head>

<body oncontextmenu="self.event.returnValue=false" onload="onHover();">
<table width="95%" border="0" cellspacing="0" cellpadding="0" align="center" class="table_only_border">
	<tr>
		<td class="title_box">流程列表</td>
	</tr>
	<tr style="padding-bottom: 20px">
		<form id="ProcessQueryCondForm" method="POST" action="${ctx}/activiti/manageprocess.action">
		<td align="right">
			<table  table border=0 class="table_box" cellspacing="0" cellpadding="0" width="100%">
				<tr>
					<td align="center">流程名称：
						<input type="text" name="process_name" id="process_name" value="${process_name}" size="15"/>&nbsp;
					</td>
					<td align="center">部门名称：
						<input type="hidden" name="dept_id" id="dept_id" value="${dept_id}"/>
						<input type="text" name="dept_name" value="${dept_name}" size="15" readonly="readonly" onclick="openDeptSelect('dept_name','dept_id','radio')"/>&nbsp;
					</td>
					<td align="center">密级：
						<c:set var="seclv1" value="${seclv_code}" scope="request"/>
						<select name="seclv_code" id="seclv_code">
							<option value="">--所有--</option>
							<s:iterator value="#request.seclvList" var="seclv">
								<c:set var="seclv2" value="${seclv.seclv_code}" scope="request"/>
									<option value="${seclv.seclv_code}" <s:if test="#request.seclv1==#request.seclv2">selected="selected"</s:if>>${seclv.seclv_name}</option>
							</s:iterator>
						</select>&nbsp;
					</td>
					<td align="center">操作：
						<c:set var="tag1" value="${jobType_code}" scope="request"/>
						<select name="jobType_code" id="jobType_code">
							<option value="">--所有--</option>
							<s:iterator value="#request.typeList" var="type">
								<c:set var="tag2" value="${type.jobTypeCode}" scope="request"/>
									<option value="${type.jobTypeCode}" <s:if test="#request.tag1==#request.tag2">selected="selected"</s:if>>${type.jobTypeName}</option>
							</s:iterator>
						</select>&nbsp;
					</td>
					<td align="center"> 
						<input name="button" type="submit" class="button_2003" value="查询">&nbsp;&nbsp;
						<input name="button" type="button" class="button_2003" value="清空" onclick="clearFindForm();">&nbsp;&nbsp;
						<input name="button" type="button" class="button_2003" onClick="go('${ctx}/activiti/addprocess.action');" value="增加新流程">
					</td>
				</tr>
			</table>
		</td>
		</form>
	</tr>
	<tr>
		<td>
			<table class="displaytable-outter" cellspacing=0 cellpadding=0 border=0 width="100%">
	 			<tr>
	   				<td>
					<display:table requestURI="${ctx}/activiti/manageprocess.action" id="item" class="displaytable" name="prcList" pagesize="15">
						<display:column title="序号">
							<c:out value="${item_rowNum}"/>
						</display:column>
						<display:column property="process_name" title="流程名称"/>
						<display:column property="dept_name" title="部门" maxLength="20"/>
						<display:column property="seclv_name" title="密级"/>
						<display:column property="jobType_name" title="操作" maxLength="20"/>
						<display:column property="usage_name" title="用途" maxLength="20"/>
						<display:column property="total_steps" title="审批级数"/>
						<%-- <display:column property="steps_dest" title="步骤说明" maxLength="40"/> --%>
						<display:column title="操作">
							<input type="button" class="button_2003" value="修改" onclick="updateProcess('${item.process_id}')";/>&nbsp;
							<input type="button" class="button_2003" value="删除" onclick="deleteProcess('${item.process_id}')";/>
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
