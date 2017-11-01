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
<form method="post" action="${ctx}/computer/updateinfodevice.action">
	<input type="hidden" id="dept_id" name="dept_id" value="${curUser.dept_id}"/>
  	<table class="table_box" cellspacing=0 cellpadding=0 border=1 width="80%">
	 <tr>
		 <td colspan="4" class="title_box">修改信息设备台账</td>
    </tr>
    <input type="hidden" name="user_iidd" id="user_iidd" value="${curUser.user_iidd}"/>
    <input type="hidden" name="type" id="type" value="UPDATE"/>
    <input type="hidden" name="device_barcode" id="device_barcode" value="${device.device_barcode}"/>
    <tr>
    	<td width="15%" align="center" >录入员</td>
		<td width="20%"><font color="blue"><b>&nbsp;${curUser.user_name}</b></font></td>
		<td width="15%" align="center">录入员部门</td>
		<td width="20%"><font color="blue"><b>&nbsp;${curUser.dept_name}</b></font></td>
	</tr>  
 	<tr>
		<td align="center">责任人</td>
		<td><font color="blue"><b>&nbsp;${device.duty_user_name}</b></font></td>
		<td align="center">责任人部门</td>
		<td><font color="blue"><b>&nbsp;${device.duty_dept_name}</b></font></td>
    </tr>
    <tr>
    	<td align="center">设备类型</td>
		<td><font color="blue"><b>&nbsp;${device.device_type_name}</b></font></td>
		<td align="center">子类型</td>
		<td><font color="blue"><b>&nbsp;${device.info_type}</b></font></td>
	</tr>        
 	<tr>
    	<td align="center">保密编号</td>
		<td><font color="blue"><b>&nbsp;${device.conf_code}</b></font></td>    	
		<td align="center">资产编号</td>
		<td><input type="text" name="device_series" id="device_series" value="${device.device_series}"></td>
	</tr>  
	<tr>
		<td align="center">原保密编号</td>
		<td><input type="text" name="oldconf_code" id="oldconf_code" value="${device.oldconf_code}"></td>
		<td align="center"><font color="red">*</font>单位</td>
		<td>
			<select name="company" id="company">
				<option value="">--请选择--</option>
				<option value="总部" <c:if test="${device.company == '总部'}">selected</c:if>>总部</option>
				<option value="24所" <c:if test="${device.company == '24所'}">selected</c:if>>24所</option>
				<option value="26所" <c:if test="${device.company == '26所'}">selected</c:if>>26所</option>
				<option value="44所" <c:if test="${device.company == '44所'}">selected</c:if>>44所</option>
			</select>
		</td>    
	</tr>	
 	<tr>
		<td align="center">品牌类型</td>
		<td><input type="text" name="brand_type" id="brand_type" value="${device.brand_type}"></td>
		<td align="center">型号/序列号</td>
		<td><input type="text" name="device_version" id="device_version" value="${device.device_version}"></td>
    </tr> 
 	<tr>
    	<td align="center">设备密级</td>
    	<td>
    		<c:set var="seclv1" value="${device.device_seclv}" scope="request"/>
    		<select id="device_seclv" name="device_seclv" >
    			<option value="">--请选择--</option>
				<s:iterator value="#request.seclvList" var="seclv">
					<option value="${seclv.seclv_code}" <s:if test="#request.seclv1==#seclv.seclv_code">selected="selected"</s:if>>${seclv.seclv_name}</option>
				</s:iterator>
			</select>
		</td>
		<td align="center">设备用途</td>
		<td><input type="text" name="device_usage" id="device_usage" value="${device.device_usage}"></td>
    </tr> 
    <tr>
    	<td align="center">设备状态</td>
 		<td>
 			<select name="device_statues" value="${device_statues}">
				<option value="">--全部--</option>
				<option value="1"<c:if test="${device.device_statues == 1}">selected</c:if>>在用</option>
				<option value="2"<c:if test="${device.device_statues == 2}">selected</c:if>>停用</option>
				<option value="3"<c:if test="${device.device_statues == 3}">selected</c:if>>维修</option>
				<option value="4"<c:if test="${device.device_statues == 4}">selected</c:if>>报废</option>
				<option value="5"<c:if test="${device.device_statues == 5}">selected</c:if>>已回收</option>
				<option value="6"<c:if test="${device.device_statues == 6}">selected</c:if>>已销毁</option>			
			</select>
 		</td>
 		<td align="center">安装地点</td>
		<td><input type="text" name="location" id="location" value="${device.location}"></td>
    </tr> 
 	 <tr>
    	<td align="center">采购时间</td>
    	<td><input type="text" name="purchase_time" id="purchase_time" onclick="WdatePicker()" class="Wdate" size="15" value="${device.purchase_time_str}"/></td>
    	</td>
		<td align="center">启用时间</td>
		<td><input type="text" name="use_time" id="use_time" onclick="WdatePicker()" class="Wdate" size="15" value="${device.use_time_str}"/></td>
	</tr>  
     <tr>   	
		<td align="center">设备备注</td>
		<td colspan="3"><input type="text" name="detail" id="detail" value="${device.detail}"></td>
<%-- 		<td align="center">序列号</td>
		<td><input type="text" name="serial_num" id="serial_num" value="${device.serial_num}"></td> --%>
	</tr>
<c:if test="${device.device_type == '5'}">  
 	<tr>
		<td align="center">检测证书名称</td>
		<td><input type="text" name="cert_name" id="cert_name" value="${device.cert_name}"></td>
		<td align="center">证书编号</td>
		<td><input type="text" name="cert_num" id="cert_num" value="${device.cert_num}"></td>
    </tr> 
</c:if>
<c:if test="${device.device_type == '6'}">
    <tr>
		<td align="center">内存/容量</td>
		<td colspan="3"><input type="text" name="memory" id="memory" value="${device.memory}"></td>
    </tr>      
</c:if>    
    <tr>
        <td colspan="4" align="center" class="bottom_box">
        	<input type="button" class="button_2003" value="提交" onclick="forms[0].submit()"/>
            <input type="button" value="返回" class="button_2003" onclick="history.go(-1);">
        </td>
    </tr>
  	</table>
</form>
</center>
</body>
</html>