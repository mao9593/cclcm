<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
  <head>
   <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="${ctx}/_css/displaytag.css" media="screen"/>
	<link rel="stylesheet" href="${ctx}/_css/css200603.css" type="text/css" media="screen"/>
    <link href="${ctx}/_script/calendar2/calendar-blue.css" type="text/css" rel="stylesheet"/>
	<script language="javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>
	<script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
	<script type="text/javascript" src="${ctx}/uploadify/jquery.uploadify.min.js"></script>
	<script language="javascript" src="${ctx}/_script/casic304_ajax.js"></script>
	<script language="javascript" src="${ctx}/_script/My97DatePicker/WdatePicker.js"></script>  
<script>
	$(document).ready(function(){
		onHover();
		disableEnterSubmit();
	});

	
	function clearFindForm(){
		$("#QueryCondForm :text").val("");
		$("#QueryCondForm select").val("");
//		$("#QueryCondForm hidden").val("");
	}
	
	function selectRecvUser(word){
		var url = "${ctx}/basic/getfuzzyuser.action?source=CR";
		if(word != ""){
			callServer1(url,"fuzzy="+word);
		}else{
			document.getElementById("allOptions").innerHTML="";
		}
	}
	function updateResult(){
		if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
				if(xmlHttp.responseText.toString().length > 154){
					document.getElementById("allOptions").innerHTML=xmlHttp.responseText;
				}else{
					document.getElementById("allOptions").innerHTML="";
				}
			}else{
				document.getElementById("allOptions").innerHTML="";
			}
	}
	function add_TrueCR(){
		var user_id=$("#allOptionCR").val();
		var user_name=$("#allOptionCR option[value='"+user_id+"']").text();
		user_name=user_name.substring(0,user_name.indexOf("/"));
		if(user_id != ""){
	    	$("#duty_user_id").val(user_id);
			$("#duty_user_name").val(user_name);
			document.getElementById("allOptions").innerHTML="";
		}
	}
	
	</script>
</head>
<body oncontextmenu="self.event.returnValue=false">
<form id="QueryCondForm"  name="QueryCondForm" method="POST" action="${ctx}/carriermanage/managedeptsecfile.action">
<table width="95%" border="0" cellspacing="0" cellpadding="0" align="center" class="table_only_border">
	<tr>
		<td class="title_box">部门涉密资料汇总台账</td>
	</tr>
	<tr>
		<td align="right">
			<table border=0 class="table_box" cellspacing="0" cellpadding="0" width="100%">
			<tr>
				<td align="center" width="13%">年份</td>
				<td width=15%>
					<input type="text" id="file_year" name="file_year" onclick="WdatePicker({dateFmt:'yyyy'})" class="Wdate" size="5"/>
				</td>
				<td align="center" width="13%">季度</td>
				<td width=15%>
					<select name="file_quarter" id="file_quarter">
						<option value="">--季度--</option>
						<option value="一季度"<c:if test="${file_quarter == '一季度'}">selected</c:if>>一季度</option>
						<option value="二季度"<c:if test="${file_quarter == '二季度'}">selected</c:if>>二季度</option>
						<option value="三季度"<c:if test="${file_quarter == '三季度'}">selected</c:if>>三季度</option>
						<option value="四季度"<c:if test="${file_quarter == '四季度'}">selected</c:if>>四季度</option>
	    			</select>&nbsp;
				</td>
				<td align="center" width="13%">单位</td>
				<td width=15%>
					<select name="duty_entp_id" id="duty_entp_id">
						<option value="">--请选择--</option>
						<option value="总部"<c:if test="${duty_entp_id == '总部'}">selected</c:if>>总部</option>
						<option value="24所"<c:if test="${duty_entp_id == '24所'}">selected</c:if>>24所</option>
						<option value="26所"<c:if test="${duty_entp_id == '26所'}">selected</c:if>>26所</option>
						<option value="44所"<c:if test="${duty_entp_id == '44所'}">selected</c:if>>44所</option>
		    		</select>&nbsp;	
				</td>
				<td align="center" width="13%">责任人</td>
				<td width=15%>
					<input type="text" id="duty_user_name" name="duty_user_name" value="${duty_user_name}" onkeyup="selectRecvUser(this.value);"/>
			   		<input type="hidden" id="duty_user_id" name="duty_user_id" value="${duty_user_id}"/><br>
			   		<div id="allOptions" class="containDiv" style="position:absolute;border:0px solid black;padding:0px">
			   		</div>		
			   	</td>		
			</tr>
			<tr>
				<td align="center">部门</td>
				<td>
					<input type="text" id="duty_dept_name" name="duty_dept_name" value="${duty_dept_name}" readonly="readonly" style="cursor:hand" onclick="openDeptSelect('duty_dept_name','duty_dept_id','radio')"/>
	    			<input type="hidden" id="duty_dept_id" name="duty_dept_id" value="${duty_dept_id}"/><br>
				</td>
				<td align="center">密级</td>
				<td>
					<select id="file_seclv_code" name="file_seclv_code" >
		    			<option value="">--请选择--</option>
						<s:iterator value="#request.seclvList" var="seclv">
							<option value="${seclv.seclv_code}" >${seclv.seclv_name}</option>
						</s:iterator>
					</select>
				</td>
				<td align="center">资料类型</td>
				<td>
					<select name="file_type" id="file_type" >
						<option value="" >--请选择--</option>
						<option value="电子资料"<c:if test="${file_type == '电子资料'}">selected</c:if>>电子资料</option>
						<option value="纸质"<c:if test="${file_type == '纸质'}">selected</c:if>>纸质</option>
						<option value="软件"<c:if test="${file_type == '软件'}">selected</c:if>>软件</option>
						<option value="其他"<c:if test="${file_type == '其他'}">selected</c:if>>其他</option>
					</select>
				</td>
				<td colspan="2" align="center">
					<input name="button" type="submit" class="button_2003" value="查询" onclick="return">&nbsp;
					<input name="button" type="button" class="button_2003" value="清空" onclick="clearFindForm();">
				</td>
			</tr>	
			</table>
		</td>
	</tr>	
	<tr>
		<td colspan="8">
			<table class="displaytable-outter" cellspacing=0 cellpadding=0 border=0 width="100%">
	 			<tr>
	   				<td>
					<display:table requestURI="${ctx}/carriermanage/managedeptsecfile.action" id="item" class="displaytable" name="fileList" pagesize="15" 
					sort="list" excludedParams="*" form="QueryCondForm">
						<display:column title="序号">
							<c:out value="${item_rowNum}"/>
						</display:column>
						<display:column property="file_year" title="年份" maxLength="15"/>
						<display:column property="file_quarter" title="季度" maxLength="15"/>
						<display:column property="file_name" title="文件名称" maxLength="15"/>
						<display:column property="duty_entp_id"  title="单位"  maxLength="15"/>
						<display:column property="duty_user_name" title="责任人"  maxLength="15"/>
						<display:column property="duty_dept_name" title="部门"  maxLength="15"/>
						<display:column property="file_seclv_name" title="密级" maxLength="15"/>
						<display:column property="file_type" title="资料类型" maxLength="15"/>
						<display:column property="file_quantity" title="文件数量" maxLength="15"/>
						<%-- <display:column title="操作" style="width:200px">
							<input type="button" class="button_2003" value="详细" onclick="go('${ctx}/education/viewrecorddetail.action?course_id=${item.course_id}');"/>&nbsp;
						</display:column> --%>
					</display:table>
					</td>
				</tr>
				<tr>
					<td align="right"><font size="2">总数合计：${total_file}页&nbsp;&nbsp;&nbsp;&nbsp;</font></td>
				</tr>
			</table>
         </td>
	</tr>
</table>
</form>
 </body>
</html>

