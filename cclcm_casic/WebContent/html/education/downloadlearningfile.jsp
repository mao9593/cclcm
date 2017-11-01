<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<title>保密学习文档下载</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="${ctx}/_css/displaytag.css" media="screen"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/_css/css200603.css"  media="screen"/>
</head>
	<script language="javascript" src="${ctx}/_script/My97DatePicker/WdatePicker.js"></script>
	<script language="javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>
	<script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
	<script type="text/javascript" src="${ctx}/uploadify/jquery.uploadify.min.js"></script>
	<script language="javascript" src="${ctx}/_script/casic304_ajax.js"></script>
<script language="JavaScript" >
	var c =0;
	var t= 0;
	
	function goOnlineLearning(name, id){
		$("#file_id").val(id);
		$("#file_name").val(name); 
		var url =  "${ctx}/files/learning/"+name;
	    //window.open('OnlineFileFrame.html?file='+url);
	    	
	    $("#timer_show").show();
	    $("#file_content").show();
	    
	    var content=document.getElementById('file_content');
	    $("#file_content").html("<iframe align='center' width='98%' height='90%' src="+url+"></iframe>");
		alert("请点击开始学习，在线学习开始计时");
	    $("#Material_list").hide();	
	    //saveCookie();
	}

	function startCount(){
		var textmin = document.getElementById('timer_text');
		hour = parseInt(c /3000);
		min  = parseInt(c /60);
		if(min > 50){
			min = min % 50;
		}
		lastsecs = c % 60;
		
		textmin.value = hour+":"+min+":"+lastsecs;
		c= c+1;
		t = setTimeout("startCount()",1000);
	
		$("#timer_start").attr("disabled",true);
		$("#timer_stop").attr("disabled",false);
	}

	function stopCount(){
		clearTimeout(t);
		
		var tmp1=$("#timer_text").val();
		var tmp2=$("#file_name").val();
		var tmp3=$("#file_id").val();
		var url = "${ctx}/education/updateclasshour.action?timer_text="+tmp1+"&file_name="+tmp2+"&file_id="+tmp3;
		go(url);
	}
 
</script>
<body oncontextmenu="self.event.returnValue=false">
<form method="post" action="${ctx}/education/downloadlearningfile.action" id="submitaction">
	<input type="hidden" name="user_iidd" id="user_iidd" value="${curUser.user_iidd}"/>
	<input type="hidden" name="file_id" id="file_id" value=""/>
<div id="timer_show" style="display:none">
<table  class="table_box" width="95%" border="1" cellspacing="0" cellpadding="0" align="center" class="table_only_border">
	<tr>
		<td colspan=2 class="title_box">在线学习</td></td>
	</tr>
	<tr>
		<td width="60%" align=center>
		&nbsp;共 &nbsp;&nbsp;<font color=blue><b><input type="text" id="timer_text" name="timer_text"></b></font> &nbsp;&nbsp;学时 （1学时 = 学习时间/50分钟）
		</td>
		<td align=left>
		<input type="button" id="timer_start" class="button_2003" value="开始学习" onclick="startCount();"/>
		<input type="button" id="timer_stop" class="button_2003" value="结束学习" onclick="stopCount();" disabled/>
		</td>	
	</tr>
	</table>
</div>

<div id="file_content" style="display:none">
<!-- <iframe align="center" width="98%" height="90%" src="">
</iframe> -->
</div>

<div id="Material_list">
<table  class="table_box" width="98%" border="1" cellspacing="0" cellpadding="0" align="center" class="table_only_border">
	<tr>
		<td colspan=2 class="title_box">学习资料列表</td>
		</td>
	</tr>
	<tr><td colspan=2>

			<table  class="table_box" width="99%" border="1" cellspacing="0" cellpadding="0" align="center" class="table_only_border">
				<tr>
					<td>文件资料分类</td>
					<td>
						<select id="file_type" name="file_type" >
							<option value="">--请选择--</option>
							<option value="政策法规" <c:if test="${file_type eq '政策法规'}">selected</c:if>>法律法规</option>
							<option value="管理制度" <c:if test="${file_type eq '管理制度'}">selected</c:if>>管理制度</option>
							<option value="学习资料" <c:if test="${file_type eq '学习资料'}">selected</c:if>>学习资料</option>
							<option value="图文资料" <c:if test="${file_type eq '图文资料'}">selected</c:if>>图文资料</option>
							<option value="警钟长鸣" <c:if test="${file_type eq '警钟长鸣'}">selected</c:if>>警钟长鸣</option>	
						</select>
					</td>
				<td>文件名称</td>
				<td colspan=2><input size="60" type="text" name="file_name" id="file_name" value="${file_name}"/></td>
				<td >&nbsp;&nbsp;
					<input name="button" type="submit" class="button_2003" value="查询" onclick="return">&nbsp;&nbsp;
					<!-- <input type="button" onclick="" value="查询" class="button_2003" id="btn_query"/>&nbsp;&nbsp;</td> -->
				</tr>
					<!-- <td>文件年份</td>
					<td>
					<input type="text" id="file_year" name="file_year" onclick="WdatePicker({dateFmt:'yyyy'})" class="Wdate" size="10" value=""/>
					</td> -->
					<%-- <td align="center"><font color="red">*</font>&nbsp;密级：</td>
			    			<td>
							<select id="seclv_code" name="seclv_code" onchange="selectSeclv(this.value);">
								<option value="">--请选择--</option>
									<s:iterator value="#request.seclvList" var="seclv">
									<option value="${seclv.seclv_name}">${seclv.seclv_name}</option>
								</s:iterator>
							</select>
							</td> --%>
			</table>
		</td></tr>
	<tr>
		<td width="30%" align=center>已学学时统计</td>
		<td align=left>&nbsp;共 &nbsp;&nbsp;<font color=blue><b>${online_time}</b></font> &nbsp;&nbsp;学时 （1学时 = 学习时间/50分钟）</td>
	</tr>
	<tr>
		<td colspan=2>
			 <display:table  requestURI="${ctx}/education/downloadlearningfile.action" id="item" class="displaytable" name="uploadEvent">
				<display:column title="序号">
					<c:out value="${item_rowNum}"/>
				</display:column>
				<display:column title="文档编号" property="file_id"/>&nbsp;
				<display:column title="文档名称" property="file_name"/>&nbsp;
				<display:column title="学时要求" property="file_edu_hour"/>&nbsp;
				<%-- <display:column title="已学学时" property="file_edu_hour"/>&nbsp; --%>
				<display:column title="密级" property="file_sec_lv"/>&nbsp;
				<display:column title="材料类型" property="file_type"/>&nbsp;
				<display:column title="上传人" property="upload_user"/>&nbsp;
				<%--<display:column title="摘要" property="comment"/>&nbsp; --%>
				<display:column title="操作" style="width:120px">
					 <%-- <input type="button" class="button_2003" value="在线学习" onclick="goOnlineLearning('${ctx}/files/learning/${item.file_name}');"/> --%> 
					<input type="button" class="button_2003" value="在线学习" onclick="goOnlineLearning('${item.file_name}', '${item.file_id}');"/>
					<input type="button" class="button_2003" value="下载" onclick="go('${ctx}/files/learning/${item.file_name}');"/>
				</display:column>
			</display:table>   
    	 </td>
    </tr>
 </table>   
</td>	
</div>	
</form>
</body>
</html>
