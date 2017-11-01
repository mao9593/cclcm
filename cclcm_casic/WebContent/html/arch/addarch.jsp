<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<HTML><HEAD>
<meta http-equiv="Content-Type" content="text/html"/>
<title>档案添加</title>
<link href="${ctx}/_css/css200603.css" rel="stylesheet" type="text/css"/>
<link rel="stylesheet" type="text/css" href="${ctx}/_css/css200603.css"  media="screen"/>
<link href="${ctx}/_script/calendar2/calendar-blue.css" type="text/css" rel="stylesheet"/>
<script language="javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>
<script language="javascript" src="${ctx}/_script/calendar2/calendar.js"></script>
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
		$("#seclv_code").val("${arch.seclv_code}");
		$("#file_carr").val("${arch.file_carr}");
		$("#keep_limit").val("${arch.keep_limit}");
		//forms[0].submit()
	}
	function numberChk(name){
		var str = $("#"+name).val();
	    var reg = /^[0-9]*$/
   		if(!reg.test(str)){
   			alert("必须填写数字");
   			 $("#"+name).val("");
   			return false;
   		}
	}
	function preCalendar(){
		Calendar.setup({inputField: "file_date", button: "file_date_trigger", singleClick: true, showsTime: true, ifFormat: "%Y-%m-%d %H:%M:%S", cache: true, weekNumbers:true, showOthers: true, step: 1});
	}
	function newTime(idName){
		Calendar.setup({inputField: idName, button: idName+"_trigger", singleClick: true, showsTime: true, ifFormat: "%Y-%m-%d %H:%M:%S", cache: true, weekNumbers:true, showOthers: true, step: 1});
	}
	function chkSubmit(){
		var url ="${ctx}/arch/chkinputarchbarcode.action";
		var arch_barcode = $("#arch_barcode").val();
		if(arch_barcode!=""){
			callServer1(url,"arch_barcode="+arch_barcode);
		}else{
		$("#saveForm").submit();
		}
	}
	function updateResult(){
		if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
				if(xmlHttp.responseText=="true"){
					alert("你添加的档案编码已经存在，请仔细检查！");
					return false;
				}else{
					$("#saveForm").submit();
				}
			}
	}
	//-->
</script> 
</HEAD>
<form method="post" action="savearch.action" id="saveForm" name="saveForm">
<input type="hidden" name="template_id" id="template_id" value="${template_id }" />
<input type="hidden" name="id" id="id" value="${id }" />
<body  oncontextmenu="self.event.returnValue=false">
<table border="0" cellspacing="0" cellpadding="0" class="table_box" width="95%" align="center">
<tr>
	<td colspan="8" class="title_box">
        <div style="float:left">新档案添加</div>
    </td>
</tr>
<tr>
    <td colspan="8" id="result"></td>
</tr>
 <tr><td colspan="8" style="background-color: #C1E5FD" align="center">档案信息</td></tr>
  <tr>
	<td>档案条码</td>
	<td><input type="text" name="arch_barcode" id="arch_barcode" ></td>
	<td colspan="4"><font color="red">填写的档案条码为载体唯一编码请认真确认，保存之后不能修改。(不填写系统自动生成条码)</font></td>
</tr>
<tr>
	<td>全宗号</td>
	<td><input type="text" name="dos_num" id="dos_num" value="${arch.dos_num}"></td>
	<td>档号</td>
	<td>
		<input type="text" name="arch_num" id="arch_num" value="${arch.arch_num}">
	</td>
	<td>分类号</td>
	<td><input type="text" name="type_code" id="type_code" value="${arch.type_code}"></td>
	
</tr>
<tr>
	<td>文件标题</td>
	<td><input type="text" name="file_title" id="file_title" value="${arch.file_title}" ></td>
	<td>文件编号</td>
	<td><input type="text" name="file_num" id="file_num"  value="${arch.file_num}"></td>
	<td>页数</td>
	<td><input type="text" name="page_num" id="page_num"  value="${arch.page_num}"></td>
</tr>
<tr>
	<td>份数</td>
	<td><input type="text" name="count" id="count" value="${arch.count}" /></td>
	<td>总页数</td>
	<td><input type="text" name="total_page" id="total_page" value="${arch.total_page}"/>
    </td>
	<td>密级</td>
	<td >	
	<select name="seclv_code" id="seclv_code">
		<c:forEach var="seclv" items="${seclv_list}" >
			<option value="${seclv}">${seclv}</option>
		</c:forEach>
	</select>
	</td>
</tr>
<tr>
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
    <td>归档时间</td>
    <td>
     <input type="text" name="file_date" readonly="readonly" style="cursor:hand;"/>
     <img src="${ctx}/_image/time2.jpg" id="file_date_trigger">&nbsp;</td>
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
		<input type="text" name="${item.property_name}" id="${item.property_name}" value="${arch.getTValue(status.count,status1.count)}" onblur="numberChk('${item.property_name }')">
		</c:if>
		<c:if test="${item.type=='s'}">
		<input type="text" name="${item.property_name}" id="${item.property_name}" value="${arch.getTValue(status.count,status1.count)}">
		</c:if>
		<c:if test="${item.type=='d'}">
		<input type="text" name="${item.property_name}" id="${item.property_name}" value="${arch.getTValue(status.count,status1.count)}">
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
	<td align="center" colspan="6"><input type="button" value="添加档案" class="button_2003" onclick="chkSubmit();"/> </td>
</tr>
</table>
<br/>
<br/>
</body>
</form>
</HTML>