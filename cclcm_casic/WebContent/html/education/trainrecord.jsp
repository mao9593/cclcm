<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <!-- <base href="<%=basePath%>"> -->
    
    <title>培训记录</title>
   <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="${ctx}/_css/displaytag.css" media="screen"/>
	<link rel="stylesheet" href="${ctx}/_css/css200603.css" type="text/css" media="screen"/>
	<link type="text/css" rel="stylesheet" href="${ctx}/uploadify/uploadify.css"/>
	<script language="javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>
	<script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
	<script type="text/javascript" src="${ctx}/uploadify/jquery.uploadify.min.js"></script>
	<script language="javascript" src="${ctx}/_script/casic304_ajax.js"></script>
	<script language="javascript" src="${ctx}/_script/My97DatePicker/WdatePicker.js"></script>  
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script>
$(document).ready(function(){
	onHover();
});
function clearFindForm(){
		$("#QueryCondForm :text").val("");
		$("#QueryCondForm select").val("");
	}	
</script>
  </head>
  
  <body oncontextmenu="self.event.returnValue=false" onload="onHover();">
    <table width="95%" border="0" cellspacing="0" cellpadding="0" align="center" class="table_only_border">
	<tr>
		<td class="title_box">培训记录</td>
	</tr>
	<tr>
		<td align="right">
		<form id="TraintrcordForm" method="POST" action="${ctx}/education/trainrecord.action">
			<table border=0 class="table_box" cellspacing="0" cellpadding="0" width="100%">
					<tr>
						<td align="center" width="8%">培训类型:</td>
					 	<td width="17%">
					 		<select name="train_type" id="event_type">
								<option value="">--请选择--</option>
								<option value="1">集中培训</option>
								<option value="2">外派培训</option>						
							</select>
					 	</td>		 		
					 	<td align="center" width="8%">培训名称</td>
					<td width=17%>
						<input type="text" name="train_name" value="${train_name}"/>
					</td>
					<td align="center" width="8%">人员</td>
		<td width="17%">
			<input type="text" id="attend_username" name="attend_username" onkeyup="selectRecvUser(this.value);"/>
    		<input type="hidden" id="attend_userid" name="attend_userid"/><br>
    		<div id="allOptions" class="containDiv" style="position:absolute;border:0px solid black;padding:0px">
		</td>												 
					 	<td align="center" width="8%">培训地点</td>
					<td width="17%">
						<input type="text" name="location" value="${location}"/>
					</td>
					<tr>
					
					<td width="8%" align="center">培训时间</td>
					 	<td width="17%" >
						 <input type="text" name="startTime" id="startTime" onclick="WdatePicker()" class="Wdate" size="10"/> 
	        				
				    	</td> 
				    	<td width="8%" align="center">至</td>
					 	<td width="17%">
					       <input type="text" name="endTime" id="endTime" onclick="WdatePicker()" class="Wdate" size="10"/>
	        			
	        			</td> 
				    	
						<td width="50%" align="center" colspan=4>
					 		<input name="button" type="submit" class="button_2003" value="查询" onclick="return checkTime();">&nbsp;&nbsp;
							<input name="button" type="button" class="button_2003" value="清空" onclick="clearFindForm();">&nbsp;&nbsp;&nbsp;
							<input name="button" type="button" class="button_2003" value="增加集中培训记录" onClick="go('${ctx}/education/addcentrainrecord.action');" value="增加集中培训">&nbsp;&nbsp;
							<input name="button" type="button" class="button_2003" value="增加外派培训记录" onClick="go('${ctx}/education/addouttrainrecord.action');" value="增加外派培训">
					 	</td>
		
					</tr>
				</table>
 		</form>
		</td>
	</tr>
	<tr>
		<td>
			<table class="displaytable-outter" cellspacing=0 cellpadding=0 border=0 width="100%">
	 			<tr>
	   				<td>
					<display:table requestURI="${ctx}/education/trainrecord.action" uid="distrainrecord" class="displaytable" name="distrainrecord" pagesize="15" sort="list">
						<display:column title="序号" sortable="true">
							<c:out value="${distrainrecord.course_id}"/>
						</display:column>
						<display:column title="培训名称" style="whitespace: nowrap;">
							<c:out value="${distrainrecord.course_name}" />
						</display:column>
						<display:column property="location" title="培训地点" style="whitespace: nowrap;"/>
						<display:column property="class_hour" title="学时" style="whitespace: nowrap;"/>
						<display:column property="train_time" title="时间" style="whitespace: nowrap;"/>
						<display:column property="train_type" title="类型" style="whitespace: nowrap;"/>
						<display:column title="操作" style="width:200px">
							
										<input type="button" class="button_2003" value="修改" onclick="go('${ctx}/education/updatetrainrecord.action?course_id=<c:out value="${distrainrecord.course_id}"/>');"/>&nbsp;									
										<input type="button" class="button_2003" value="删除" onclick="onclick1( <c:out value="${distrainrecord.course_id}"/>,<c:out value="${distrainrecord.course_id}"/>)"/>									
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
