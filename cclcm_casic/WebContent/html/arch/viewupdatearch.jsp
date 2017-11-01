<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<HTML><HEAD>
<meta http-equiv="Content-Type" content="text/html"/>
<title>档案修改</title>
<link href="${ctx}/_css/css200603.css" rel="stylesheet" type="text/css"/>
<link rel="stylesheet" type="text/css" href="${ctx}/_css/css200603.css"  media="screen"/>
<link href="${ctx}/_script/calendar2/calendar-blue.css" type="text/css" rel="stylesheet"/>
<script language="javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>
<script language="javascript" src="${ctx}/_script/calendar2/calendar.js"></script>
<script language="JavaScript" src="${ctx}/_script/function.js"></script>
<script language="JavaScript" src="${ctx}/_script/common.js"></script>
<script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
<script language="javascript" src="${ctx}/_script/casic304_ajax.js"></script>

<script>
<!--
	$(document).ready(function(){
		onHover();
		preCalendar();
		initData();
	});
	function initData(){
		$("#seclv_code").val("${archValue.seclv_code}");
		$("#file_carr").val("${archValue.file_carr}");
		$("#keep_limit").val("${archValue.keep_limit}");
		$("#file_date").val("${archValue.file_date}");
	}
	function preCalendar(){
		Calendar.setup({inputField: "file_date", button: "file_date_trigger", singleClick: true, showsTime: true, ifFormat: "%Y-%m-%d %H:%M:%S", cache: true, weekNumbers:true, showOthers: true, step: 1});
	}
	function newTime(idName){
		Calendar.setup({inputField: idName, button: idName+"_trigger", singleClick: true, showsTime: true, ifFormat: "%Y-%m-%d %H:%M:%S", cache: true, weekNumbers:true, showOthers: true, step: 1});
	}
	//-->
</script> 
<script type="text/javascript">
	function chkSubmit(){
	//ajax提交
	var url = "${ctx}/arch/updatearch.action";
	callServerPostForm1(url,document.getElementById("updateForm"));
	$("#submit_btn").attr("disabled",true);
	return true;
}
function getAjaxResult1(){
	var dos_id = $("#dos_id").val();
	if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
		if(xmlHttp.responseText == "ok"){
			alert("档案修改成功！");
			window.location="${ctx}/arch/viewdosarch.action?id="+dos_id;
		}else{
			alert(xmlHttp.responseText);
			$("#submit_btn").attr("disabled",false);
		}
	}
}
	</script>
</HEAD>
<form method="post" action="updatearch.action" id="updateForm">
<input type="hidden" name="id" id="id" value="${archValue.id }" />
<input type="hidden" name="dos_id" id="dos_id" value="${archValue.dos_id }" />
<body  oncontextmenu="self.event.returnValue=false">
<table border="0" cellspacing="0" cellpadding="0" class="table_box" width="95%" align="center">
<tr>
	<td colspan="8" class="title_box">
        <div style="float:left">档案修改</div>
    </td>
</tr>
<tr>
    <td colspan="8" id="result"></td>
</tr>
 <tr><td colspan="8" style="background-color: #C1E5FD" align="center">档案信息</td></tr>
 <tr>
	<td>档案条码</td>
	<td>${archValue.barcode}</td>
	<td colspan="4"><font color="red">档案条码为载体唯一标识不能修改。</font></td>
</tr> 
<tr>
	<td>全宗号</td>
	<td><input type="text" name="dos_num" id="dos_num" value="${archValue.dos_num}"></td>
	<td>档号</td>
	<td>
		<input type="text" name="arch_num" id="arch_num" value="${archValue.arch_num}">
	</td>
	<td>归档时间</td>
    <td>
     <input type="text" name="file_date" id="file_date" readonly="readonly" />
     <img src="${ctx}/_image/time2.jpg" id="file_date_trigger">&nbsp;</td>
</tr>
<tr>
	<td>分类号</td>
	<td><input type="text" name="type_code" id="type_code" value="${archValue.type_code}"></td>
	<td>文件标题</td>
	<td><input type="text" name="file_title" id="file_title" value="${archValue.file_title}" ></td>
	<td>文件编号</td>
	<td><input type="text" name="file_num" id="file_num"  value="${archValue.file_num}"></td>
</tr>
<tr>
	<td>页数</td>
	<td><input type="text" name="page_num" id="page_num"  value="${archValue.page_num}"></td>
	<td>份数</td>
	<td><input type="text" name="count" id="count" value="${archValue.count}" /></td>
	<td>总页数</td>
	<td><input type="text" name="total_page" id="total_page" value="${archValue.total_page}"/>
    </td>
</tr>
<tr>
	<td>密级</td>
	<td >	
	<select name="seclv_code" id="seclv_code">
		<c:forEach var="seclv" items="${seclv_list}" >
			<option value="${seclv}">${seclv}</option>
		</c:forEach>
	</select>
	</td>
	<td>文件载体</td>
	<td >	
	<select name="file_carr" id="file_carr">
		<c:forEach var="file" items="${file_list}" >
			<option value="${file}">${file }</option>
		</c:forEach>
	</select>
	</td>
	<td>保管期限</td>
	<td >	
	<select name="keep_limit" id="keep_limit" >
		<c:forEach var="limit" items="${limit_list}" >
			<option value="${limit}">${limit }</option>
		</c:forEach>
	</select>
	</td>

</tr>
<c:forEach var="it" items="${list}" step="3"  varStatus="status">
<tr>
<c:forEach var="item" items="${list}"  begin="${3*(status.count-1)}" end="${3*(status.count-1)+2}"  step="1" varStatus="status1">
		<td >${item.name}</td>
		<td >
		<c:if test="${item.type=='e'}">
			<select name="${item.property_name}">
			<c:forEach var="im" items="${item.dict_list}" >
				<option value="${im}">${im}</option>
				</c:forEach>
			</select>
		</c:if>
		<c:if test="${item.type=='i'}">
		<input type="text" name="${item.property_name}" id="${item.property_name}" value="${archValue.getTValue(status.count,status1.count)}" onblur="numberChk('${item.property_name }')">
		</c:if>
		<c:if test="${item.type=='s'}">
		<input type="text" name="${item.property_name}" id="${item.property_name}" value="${archValue.getTValue(status.count,status1.count)}">
		</c:if>
		<c:if test="${item.type=='d'}">
		<input type="text" name="${item.property_name}" id="${item.property_name}" readonly="readonly" value="${archValue.getTValue(status.count,status1.count)}"/>
		 <img src="${ctx}/_image/time2.jpg" id="${item.property_name}_trigger" onload="newTime('${item.property_name}')">&nbsp;</td>
		</c:if>
		</td>
	</c:forEach>
</tr>
</c:forEach>
<tr align="center">
  <td>备注</td>
	<td   colspan="5"><textarea name="summ" rows="3" cols="90" id="summ">${arch.summ}</textarea> </td>
</tr>
<tr align="center">
	<td colspan="6" align="center"> 
		<input type="button" value="保存档案" class="button_2003" onclick="chkSubmit();" id="submit_btn"/> &nbsp;
	    <input class="button_2003" type="button" value="返回" onclick="javascript:history.go(-1)">&nbsp;
	</td>
</tr>

</table>
<br/>
<br/>
</body>
</form>
</HTML>