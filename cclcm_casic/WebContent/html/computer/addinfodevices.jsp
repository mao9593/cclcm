<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<link rel="stylesheet" type="text/css" href="${ctx}/_css/displaytag.css" media="screen"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/_css/css200603.css"  media="screen"/>
	<script language="JavaScript" src="${ctx}/_script/function.js"></script>
	<script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
	<script language="javascript" src="${ctx}/_script/casic304_common_bm.js"></script>
 	<script language="javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>	
 	<script language="javascript" src="${ctx}/_script/casic304_ajax.js"></script>
 	<script language="JavaScript" src="${ctx}/_script/common.js"></script>
 	<script language="javascript" src="${ctx}/_script/My97DatePicker/WdatePicker.js"></script>
<script>
$(document).ready(function(){
	onHover();
	disableEnterSubmit();
	document.getElementById("allOptions").innerHTML="";
});
	function selectRecvUser(word){
		var url = "${ctx}/basic/getfuzzyuser.action?source=CR";
		if(word != ""){
			callServer1(url,"fuzzy="+word);
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
	
	function chk()
	{	
		if($("#duty_user_name").val().trim() == ""){
			alert("请输入责任人");
			$("#duty_user_name").focus();
			return false;
		}
		if($("#contact_num").val().trim() == ""){
			alert("请输入联系电话");
			$("#contact_num").focus();
			return false;
		}
		if($("#duty_dept_name").val().trim() == ""){
			alert("请选择责任部门");
			$("#duty_dept_name").focus();
			return false;
		}
		if($("#info_id").val().trim() == ""){
			alert("请选择类型");
			$("#info_id").focus();
			return false;
		}
	    return true;
	}
</script>
</head>
<!-- <body oncontextmenu="self.event.returnValue=false"> -->
<table width="95%" border="1" cellspacing="0" cellpadding="0" align="center" class="table_box">	
	<form action="${ctx}/computer/addinfosdevice.action" method="post">
		<input type="hidden" id="event_code" name="event_code" value="${event_code}"/>
		<input type="hidden" id="dept_id" name="dept_id" value="${curUser.dept_id}"/>
		<input type="hidden" id="jobType" name="jobType" value="${jobType_OTHER}"/>
		<input type="hidden" id="jobType_DEVICE" name="jobType_DEVICE" value="${jobType_DEVICE}"/>
		<input type="hidden" id="jobType_OTHER" name="jobType_OTHER" value="${jobType_OTHER}"/>
		<input type="hidden" name="seclv_code" id="seclv_code" />
		<input type="hidden" name="info" id="info"/>
		<input type="hidden" name="device_type" id="device_type" value="2"/>
	<tr>
	    <td colspan="6" class="title_box">添加网络信息设备</td>
	</tr>
	<tr> 
    	<td width="10%" align="center">申请用户 </td>
    	<td width="23%"><font color="blue"><b>${curUser.user_name}</b></font></td>
    	<td width="10%" align="center">用户部门 </td>
    	<td width="23%"><font color="blue"><b>${curUser.dept_name}</b></font></td>	
    	<td width="10%" align="center"><font color="red">*</font>联系电话</td>
    	<td width="23%">
    		<input type="text" id="contact_num" name="contact_num"/><br>
		</td>
   	</tr>
   	<tr>
		<td align="center"><font color="red">*</font>责任人</td>
    	<td>
    		<input type="text" id="duty_user_name" name="duty_user_name" onkeyup="selectRecvUser(this.value);"/>
    		<input type="hidden" id="duty_user_id" name="duty_user_id"/><br>
    		<div id="allOptions" class="containDiv" style="position:absolute;border:0px solid black;padding:0px">
    		</div>
	    </td>
	    <td align="center"><font color="red">*</font>责任部门</td>
    	<td colspan="3">
			<input type="text" id="duty_dept_name" name="duty_dept_name" readonly="readonly" style="cursor:hand" onclick="openDeptSelect('duty_dept_name','duty_dept_id','radio')"/>
    		<input type="hidden" id="duty_dept_id" name="duty_dept_id"/><br>
		</td>
   	</tr>  
	<tr>
   		<td align="center"><font color="red">*</font>设备类型</td>
		<td><font color="blue"><b>网络设备</td>    
    	<td align="center"><font color="red">*</font>类型</td>
	    <td>
	   		<div id="varinfo">
 				<select name="info_id" id="info_id">
				<option value="">--请选择--</option>
    			<s:iterator value="#request.infoType2" var="type">
					<option value="${type.info_id}">${type.info_type}</option>
				</s:iterator>
    		</select> 
			</div>
		</td>   			
		<td align="center">设备编号</td>
		<td><input type="text" name="device_series" id="device_series" ></td>
	</tr>  
 	<tr>
		<td align="center">品牌类型</td>
		<td><input type="text" name="brand_type" id="brand_type" ></td>
		<td align="center">型号/序列号</td>
		<td><input type="text" name="device_version" id="device_version" ></td>
    	<td align="center">设备密级</td>
    	<td>
    		<select id="device_seclv" name="device_seclv" >
    			<option value="">--请选择--</option>
				<s:iterator value="#request.seclvList" var="seclv">
					<option value="${seclv.seclv_code}" >${seclv.seclv_name}</option>
				</s:iterator>
			</select>
		</td>
    </tr> 
 	<tr>		
		<td align="center">设备用途</td>
		<td><input type="text" name="device_usage" id="device_usage" ></td>
    	<td align="center">采购时间</td>
        <td><input type="text" name="purchase_time" id="purchase_time" onclick="WdatePicker()" class="Wdate" size="15"/></td>
    	</td>
		<td align="center">启用时间</td>
		<td><input type="text" name="use_time" id="use_time" onclick="WdatePicker()" class="Wdate" size="10"/></td>
	</tr>  
     <tr id="sec_pro" style="display: none">   	
		<td align="center">安装地点</td>
		<td><input type="text" name="location" id="location" ></td>
		<td align="center">检测证书名称</td>
		<td><input type="text" name="cert_name" id="cert_name" ></td>
		<td align="center">证书编号</td>
		<td><input type="text" name="cert_num" id="cert_num" ></td>
    </tr>  
 	 <tr id="memory" style="display: none">		
 	 	<!-- <td align="center">序列号</td>
		<td><input type="text" name="serial_num" id="serial_num" ></td> -->
		<td align="center">内存/容量</td>
		<td colspan="5"><input type="text" name="memory" id="memory" ></td>
    </tr>     
	<tr>
 		<td align="center">备注</td>
	   	<td colspan="5"><textarea name="summ" rows="4" cols="80" id="summ"></textarea></td>
	</tr>
	</form>
	 	
  	<tr>
    <td colspan="6" align="center"> 
      <input type="button" class="button_2003" value="提交" onclick="if(chk()) forms[0].submit();" id="submit_btn"/>
    </td>
  </tr>
</table>
</body>
</html>
