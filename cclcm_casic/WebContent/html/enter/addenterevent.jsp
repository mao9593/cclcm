<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<link rel="stylesheet" type="text/css" href="${ctx}/_css/displaytag.css" media="screen"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/_css/css200603.css"  media="screen"/>
	<script language="JavaScript" src="${ctx}/_script/function.js"></script>
	<script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
 	<script language="javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>	
 	<script language="javascript" src="${ctx}/_script/casic304_ajax.js"></script>
 	<script language="JavaScript" src="${ctx}/_script/common.js"></script>
<script>
$(document).ready(function(){
	onHover();
	init();
});
var is_cd = false;
function init(){
	if($("#file_type").val()==2){
		$(".tr_page_num").hide();
		$("#page_num").val("");
		$(".tr_blank").show();
		is_cd = true;
	}
}

function chk()
{	
	if($("#seclv_code").val() == ""){
		alert("请选择密级");
		$("#seclv_code").focus();
		return false;
	}
	if($("#zipfile").val().trim() == ""){
		alert("请填写文件名称");
		$("#zipfile").focus();
		return false;
	}	
	if($("#page_num").val().trim() == ""  && !is_cd){
		alert("请填写文件页数");
		$("#page_num").focus();
		return false;
	}
	if(!isInteger($("#page_num").val()) && !is_cd){
	    alert("文件页数只能填写1到10000之间的整数");
	    $("#page_num").focus();
	    return false;
	}
    return true;
}
</script>
<script language="javascript">
function checkBarcode() {
	 var barcode = document.getElementById("medium_serial").value;
	 var file_type = document.getElementById("file_type").value;
	 
	 if ((barcode == null) || (barcode == "")){
       result.innerHTML="<font color='red'>请输入预发条码!</font>";
       return;
  	}
	 var url = "${ctx}/enter/checkbarcode.action?barcode=" + escape(barcode)+"&file_type="+ escape(file_type)+"&random="+Math.random();
	 callServer(url);
}

function updateResult() {
  if (xmlHttp.readyState < 4) {
	result.innerHTML="检测中...";
  }
  if (xmlHttp.readyState == 4) {
    var response = xmlHttp.responseText;
    result.innerHTML=response;
  }
}
function selectType(file_type){
	if(file_type == 2){//光盘
		$(".tr_page_num").hide();
		$("#page_num").val("");
		$(".tr_blank").show();
		is_cd = true;
	}else {
		$(".tr_page_num").show();
		$(".tr_blank").hide();
		is_cd = false;
	}
}   
</script>
</head>
<body oncontextmenu="self.event.returnValue=false">
<table width="95%" border="1" cellspacing="0" cellpadding="0" align="center" class="table_box">	
	<form action="${ctx}/enter/addenterevent.action" method="POST">
		<input type="hidden" name="event_code" value="${event_code}" id="event_code"/>
	<tr>
	    <td colspan="6" class="title_box">添加录入申请</td>
	</tr>
	<tr> 
    	<td width="10%" align="center">申请用户： </td>
    	<td width="23%"><font color="blue"><b>${curUser.user_name}</b></font></td>
    	<td width="10%" align="center">用户部门： </td>
    	<td width="23%"><font color="blue"><b>${curUser.dept_name}</b></font></td>
    	<td width="10%" align="center">流水号： </td>
    	<td width="23%"><font color="blue"><b>${event_code}</b></font></td>
	</tr>
	<tr> 
    	<td align="center"><font color="red">*</font>&nbsp;密级：</td>
	    <td>
			<select id="seclv_code" name="seclv_code">
				<option value="">--请选择--</option>
				<s:iterator value="#request.seclvList" var="seclv">
					<option value="${seclv.seclv_code}">${seclv.seclv_name}</option>
				</s:iterator>
			</select>
		</td>		
    	<td align="center"><font color="red">*</font>&nbsp;类型： </td>
    	<td>
    		<select name="file_type" id="file_type" onchange="selectType(this.value);">
    			<option value="1">文件</option>
    			<option value="2">光盘</option>   			
    		</select>
    	</td>  	    	   	
		<td align="center">&nbsp;预发条码： </td>
    	<td>
	    	<input type="text" name="medium_serial" id="medium_serial"/>
	    	<input type="button" class="button_2003" value="查重" onclick="checkBarcode();"/>
    	</td> 
    </tr>    
    <tr> 
    	<td align="center">&nbsp;来源： </td>
    	<td><input type="text" name="source" id="source"/></td>      			    	  	
    	<td class="tr_page_num" align="center"><font color="red">*</font>&nbsp;文件页数： </td>
    	<td class="tr_page_num"><input type="text" name="page_num" id="page_num"/></td> 
    	<td class="tr_blank" style="display: none">&nbsp;</td>
    	<td class="tr_blank" style="display: none">&nbsp;</td>	
    	<td>&nbsp;</td>
		<td id="result">&nbsp;</td> 	 	  	   	
  	</tr>
  	<tr style="display: <c:if test="${deptadmin==false}">none</c:if>">   		   	
  		<td align="center">&nbsp;载体归属：</td>
		<td colspan="5">
    		<label style="width: 80">
					<input type="radio" name="scope" value="PERSON" checked="true"/>个人文件
			</label>
    		<s:iterator value="#request.adminGroupList" var="groupscope">
    			<label style="width: 100">
					<input type="radio" name="scope" value="${groupscope.dept_id}"/>${groupscope.dept_name}
				</label>
			</s:iterator>
		</td>
  	</tr>
  	<tr>   		   	
  		<td align="center"><font color="red">*</font>&nbsp;文件名称：</td>
		<td colspan="5"><textarea name="zipfile" rows="3" cols="40" id="zipfile"></textarea></td> 
  	</tr> 	
  	<tr>   		   	
  		<td align="center">&nbsp;备注：</td>
		<td colspan="5"><textarea name="summ" rows="3" cols="40" id="summ"></textarea></td>
  	</tr>
	</form>
	 	
  	<tr>
    <td colspan="6" align="center"> 
      <input type="button" class="button_2003" value="提交" onclick="if(chk()) forms[0].submit();" id="submit_btn"/>
      &nbsp;
      <input class="button_2003" type="reset" value="返回"  onClick="javascript:history.go(-1);">&nbsp;
    </td>
  </tr>
</table>
</body>
</html>
