<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="${ctx}/_css/css200603.css" type="text/css" media="screen" />
	<link href="${ctx}/_script/calendar2/calendar-blue.css" type="text/css" rel="stylesheet"/>
	<script language="javascript" src="${ctx}/_script/calendar2/calendar.js"></script> 
	<script language="javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>
	<script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
	<script language="javascript" src="${ctx}/_script/casic304_ajax.js"></script>
	<script language="javascript" src="${ctx}/_script/My97DatePicker/WdatePicker.js"></script>
	<script  language="JavaScript" >
	<!--
	$(document).ready(function(){
		onHover();
	});
	
	function selectRecvUser(word){
		var url = "${ctx}/basic/getfuzzyuser.action";
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
	function add_True(){
		var user_id=$("#allOption").val();
		var user_name=$("#allOption option[value='"+user_id+"']").text();
		if(user_id != ""){
			$("#duty_user_iidd").val(user_id);
			$("#duty_user_name").val(user_name);
			document.getElementById("allOptions").innerHTML="";
		}
	}
	function chk()
	{
		
	    return true;
	}
	//-->
	</script>
</head>
<body oncontextmenu="self.event.returnValue=false">
<center>
<form method="post" action="${ctx}/computer/updatebook.action">
    <input type="hidden" name="type" id="type" value="UPDATE"/>
    <input type="hidden" name="duty_user_id" id="duty_user_id" value="${device.duty_user_id}"/>
    <input type="hidden" name="duty_dept_id" id="duty_dept_id" value="${device.duty_dept_id}"/>
    <input type="hidden" name="device_barcode" id="device_barcode" value="${device.book_barcode}"/>
	
  	<table class="table_box" cellspacing=0 cellpadding=0 border=1 width="80%">
	 <tr>
		 <td colspan="6" class="title_box">修改笔记本台账</td>
    </tr>
    <tr>
<%--     	<td align="center"><font color="red">*</font>保管人</td>
		<td>
			<input type="text" id="duty_user_name" name="duty_user_name" value="${device.duty_user_name}" onkeyup="selectRecvUser(this.value);"/>
    		<input type="hidden" id="duty_user_id" name="duty_user_id"  value="${device.duty_user_id}"/><br>
    		<div id="allOptions" class="containDiv" style="position:absolute;border:0px solid black;padding:0px">
		</td>
		<td align="center"><font color="red">*</font>使用部门</td>
		<td>
			<input type="text" id="duty_dept_name" name="duty_dept_name" value="${device.duty_dept_name}" readonly="readonly" style="cursor:hand" onclick="openDeptSelect('duty_dept_name','duty_dept_id','radio')"/>
    		<input type="hidden" id="duty_dept_id" name="duty_dept_id" value="${device.duty_dept_id}"/><br>
		</td> --%>
		<td align="center"><font color="red">*</font>保管人</td>
		<td><font color="blue"><b>&nbsp;${device.duty_user_name}</b></font></td>
		<td align="center"><font color="red">*</font>使用部门</td>
		<td><font color="blue"><b>&nbsp;${device.duty_dept_name}</b></font></td>
		<td align="center">单位</td>
		<td>
			<select name="duty_entp" id="duty_entp">
				<option value="">--请选择--</option>
				<option value="总部" <c:if test="${device.duty_entp == '总部'}">selected</c:if>>总部</option>
				<option value="24所" <c:if test="${device.duty_entp == '24所'}">selected</c:if>>24所</option>
				<option value="26所" <c:if test="${device.duty_entp == '26所'}">selected</c:if>>26所</option>
				<option value="44所" <c:if test="${device.duty_entp == '44所'}">selected</c:if>>44所</option>
			</select>			
		</td> 
    </tr>     
    <tr>
		<td align="center"><font color="red">*</font>硬盘序列号</td>
		<td>
			<input type="text" name="hdisk_no" id="hdisk_no" value="${device.hdisk_no}"/>
		</td>
    	<td align="center"><font color="red">*</font>统一编号</td>
		<td>
			<input type="text" name="book_code" id="book_code" value="${device.book_code}"/>
		</td>
		<td align="center"><font color="red">*</font>原保密编号</td>
		<td>
			<input type="text" name="oldconf_code" id="oldconf_code" value="${device.oldconf_code}"/>
		</td>					
    </tr> 	
    <tr>
		<td align="center"><font color="red">*</font>密级</td>
		<td>
			<c:set var="seclv1" value="${device.seclv_code}" scope="request"/>
			<select name="seclv_code" id="seclv_code">
				<option value="">--请选择--</option>
				<s:iterator value="#request.seclvList" var="seclv">
					<option value="${seclv.seclv_code}" <s:if test="#request.seclv1==#seclv.seclv_code">selected="selected"</s:if>>${seclv.seclv_name}</option>
				</s:iterator>
			</select>
		</td>
		<td align="center">联网情况</td>
		<td>
			<select name="internet_type" id="internet_type">
				<option value="">--请选择--</option>
				<option value="涉密网" <c:if test="${device.internet_type == '涉密网'}">selected</c:if>>涉密网</option>
				<option value="科研网" <c:if test="${device.internet_type == '科研网'}">selected</c:if>>科研网</option>
				<option value="市政网" <c:if test="${device.internet_type == '市政网'}">selected</c:if>>市政网</option>
				<option value="单机" <c:if test="${device.internet_type == '单机'}">selected</c:if>>单机</option>
				<option value="互联网" <c:if test="${device.internet_type == '互联网'}">selected</c:if>>互联网</option>
				<option value="非密专网" <c:if test="${device.internet_type == '非密专网'}">selected</c:if>>非密专网</option>
				<option value="其他" <c:if test="${device.internet_type == '其他'}">selected</c:if>>其他</option>
			</select>
		</td>		
    	<td align="center"><font color="red">*</font>笔记本状态</td>
		<td>
			<select name="book_status" id="book_status">
				<option value="">--请选择--</option>
				<option value="1" <c:if test="${device.book_status == '1'}">selected</c:if>>在用</option>
				<option value="6" <c:if test="${device.book_status == '6'}">selected</c:if>>已借出</option>
				<option value="2" <c:if test="${device.book_status == '2'}">selected</c:if>>停用</option>
				<option value="3" <c:if test="${device.book_status == '3'}">selected</c:if>>维修</option>
				<option value="4" <c:if test="${device.book_status == '4'}">selected</c:if>>报废</option>
				<option value="5" <c:if test="${device.book_status == '5'}">selected</c:if>>销毁</option>
			</select>
		</td>    
    </tr> 		
   <tr>
		<td align="center">名称型号</td>
		<td>
			<input type="text" id="book_model" name="book_model" value="${device.book_model}"/>
		</td>
		<%-- <td align="center">使用情况</td>
		<td>
			<input type="text" name="useinfo" id="useinfo" value="${device.useinfo}"/>
		</td> --%>
		<td align="center">外出情况</td>
		<td colspan="3">
			<input type="text" name="outsideinfo" id="outsideinfo" value="${device.outsideinfo}"/>
		</td>
   </tr>
    <tr>
		<td align="center">系统时间</td>
		<td>
			<input type="text" id="book_os" name="book_os" onclick="WdatePicker()" class="Wdate" value="${device.book_os}"/>
		</td>
		<td align="center">MAC地址</td>
		<td>
			<input type="text" name="book_mac" id="book_mac" value="${device.book_mac}"/>
		</td>
		<td align="center">存放地点</td>
		<td>
			<input type="text" name="storage_location" id="storage_location" value="${device.storage_location}"/>
		</td>		
    </tr> 
     <tr>
     	<td align="center">输出情况</td>
		<td>
			<input type="text" name="output_point" id="output_point" value="${device.output_point}"/>
		</td>
		<td align="center">存储涉密信息情况</td>
		<td>
			<input type="text" name="storage_secinfo" id="storage_secinfo" value="${device.storage_secinfo}"/>
		</td>
		<td align="center">备注</td>
		<td colspan="3">
			<input type="text" name="detail" id="detail" value="${device.detail}"/>
		</td>
    </tr>     
    <tr>
        <td colspan="6" align="center" class="bottom_box">
        	<input type="button" class="button_2003" value="提交" onclick="forms[0].submit()"/>
            <input type="button" value="返回" class="button_2003" onclick="history.go(-1);">
        </td>
    </tr>
  	</table>
</form>
</center>
</body>
</html>