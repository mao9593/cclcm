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
	setSeclv("seclv_code");
	selectNextApprover();
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

	function selectNextApprover(){
		del_all_True('next_approver_sel');
		var url = "${ctx}/basic/getnextapprover.action";
		callServerPostForm(url,document.forms[0]);
	}
	function getAjaxResult(){
		if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
			if(xmlHttp.responseText.indexOf("<SELECT") != -1){
				$("#tr_approver").show();
				document.getElementById("allApprovers").innerHTML=xmlHttp.responseText;
				$("#submit_btn").attr("disabled",false);
			}else{
				$("#tr_approver").hide();
				alert(xmlHttp.responseText);
				$("#submit_btn").attr("disabled",true);
			}
			if($("#next_approver_all").size() > 0 && $("#next_approver_all").children().size() == 0){
				$("#tr_approver").hide();
			}
		}
	}
	function setSelectKind(type){
		var url = "${ctx}/computer/getinfotype.action";
		if(type != ""){
			callServer4(url,"info="+type);
			if(type == 5){
				$("#sec_pro").show();
				$("#jobType").val($("#jobType_DEVICE").val());
			}else{
				$("#sec_pro").hide();
			}
			if(type == 6){
				$("#memory").show();
				$("#memory1").show();
				$("#jobType").val($("#jobType_DEVICE").val());
			}else{
				$("#memory").hide();
				$("#memory1").hide();
				$("#jobType").val($("#jobType_OTHER").val());
			}
		}else{
			document.getElementById("varinfo").innerHTML="";
		}
	}
	function updateResult2(){
		if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
				if(xmlHttp.responseText.toString().length > 154){
					document.getElementById("varinfo").innerHTML=xmlHttp.responseText;
				}else{
					document.getElementById("varinfo").innerHTML="";
				}
			}else{
				document.getElementById("varinfo").innerHTML="";
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
		if($("#device_type").val().trim() == ""){
			alert("请选择设备类型");
			$("#device_type").focus();
			return false;
		}
		if($("#next_approver_all option").size() > 0 && $("#next_approver_sel option").size() == 0){
			alert("请选择审批人员");
			$("#next_approver_all").focus();
			return false;
		}		
			//审批人信息
		var next_approver = "";
		$("#next_approver_sel option").each(function(){
			next_approver += this.value+",";
		});
		var len = next_approver.length;
		if (next_approver.lastIndexOf(",") == len - 1){
			next_approver = next_approver.substring(0, len - 1);
		}
		$("#next_approver").val(next_approver);
	    return true;
	}
</script>
</head>
<body oncontextmenu="self.event.returnValue=false">
<table width="95%" border="1" cellspacing="0" cellpadding="0" align="center" class="table_box">	
	<form action="${ctx}/computer/addinfodeviceevent.action" method="post">
		<input type="hidden" id="event_code" name="event_code" value="${event_code}"/>
		<input type="hidden" id="dept_id" name="dept_id" value="${curUser.dept_id}"/>
		<input type="hidden" id="jobType" name="jobType" value="${jobType_OTHER}"/>
		<input type="hidden" id="jobType_DEVICE" name="jobType_DEVICE" value="${jobType_DEVICE}"/>
		<input type="hidden" id="jobType_OTHER" name="jobType_OTHER" value="${jobType_OTHER}"/>
		<input type="hidden" id="next_approver" name="next_approver" />
		<input type="hidden" name="seclv_code" id="seclv_code" />
		<input type="hidden" name="info" id="info"/>
	<tr>
	    <td colspan="6" class="title_box">新增信息设备审批表</td>
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
    	<td>
			<input type="text" id="duty_dept_name" name="duty_dept_name" readonly="readonly" style="cursor:hand" onclick="openDeptSelect('duty_dept_name','duty_dept_id','radio')"/>
    		<input type="hidden" id="duty_dept_id" name="duty_dept_id"/><br>
		</td>
		<td align="center"><font color="red">*</font>单位</td>
		<td>
			<select name="company" id="company">
				<option value="">--请选择--</option>
				<option value="总部">总部</option>
				<option value="24所">24所</option>
				<option value="26所">26所</option>
				<option value="44所">44所</option>
			</select>
		</td>		
   	</tr>  
	<tr>
   		<td align="center"><font color="red">*</font>设备类型</td>
		<td>
			<select name="device_type" id="device_type" onchange="setSelectKind(this.value)">
				<option value="">--请选择--</option>
				<!-- <option value="2">网络设备</option> -->
				<option value="3">外部设备</option>
				<option value="4">办公自动化设备</option>
				<option value="5">安全产品</option>
				<option value="6">介质</option>
			</select>
		</td>    
    	<td align="center"><font color="red">*</font>类型</td>
	    <td>
	   		<div id="varinfo">
 				<select>
					<option value="">--请先选择设备类型--</option>
				</select>
			</div>
		</td>   			
		<td align="center">资产编号</td>
		<td><input type="text" name="device_series" id="device_series" ></td>
	</tr>  
	<tr>
		<td align="center">原保密编号</td>
		<td><input type="text" name="oldconf_code" id="oldconf_code" ></td>
		<td align="center">设备用途</td>
		<td><input type="text" name="device_usage" id="device_usage" ></td>
		<td align="center">安装地点</td>
		<td><input type="text" name="location" id="location" ></td>    
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
    	<td align="center">采购时间</td>
        <td><input type="text" name="purchase_time" id="purchase_time" onclick="WdatePicker()" class="Wdate" size="15"/></td>
    	</td>
		<td align="center">启用时间</td>
		<td><input type="text" name="use_time" id="use_time" onclick="WdatePicker()" class="Wdate" size="10"/></td>
		<td align="center">设备状态</td>
 		<td>
 			<select name="device_statues" value="${device_statues}">
				<option value="">--全部--</option>
				<option value="1">在用</option>
				<option value="2">停用</option>
				<option value="3">维修</option>
				<option value="4">报废</option>
				<option value="5">已回收</option>
				<option value="6">已销毁</option>			
			</select>
 		</td>
 		</tr>
 		<tr>
			<td id="memory" style="display: none" align="center">内存/容量</td>
			<td id="memory1" colspan="5" style="display: none"><input type="text" name="memory" id="memory" ></td>
		</tr>
	</tr>  
     <tr id="sec_pro" style="display: none">   	
		<td align="center">检测证书名称</td>
		<td><input type="text" name="cert_name" id="cert_name" ></td>
		<td align="center">证书编号</td>
		<td colspan="3"><input type="text" name="cert_num" id="cert_num" ></td>
    </tr>  		
	<!-- <td align="center">序列号</td>
	<td><input type="text" name="serial_num" id="serial_num" ></td> -->
	<tr>
 		<td align="center">申请备注</td>
	   	<td colspan="5"><textarea name="summ" rows="4" cols="80" id="summ"></textarea></td>
	</tr>
  	<tr id="tr_approver" style="display: none">
  		<td align="center" id="selApprover1">选择审批人</td>
  		<td id="selApprover2" colspan="5">
  			<table width="300" border="0" cellspacing="0" cellpadding="0" align="left" class="table_box_border_empty">
				<tr>
					<td id="allApprovers">
						<SELECT ondblclick="add_True('next_approver_all','next_approver_sel');" style="WIDTH: 100px" multiple="true" size="8" id="next_approver_all">
							<c:forEach var="item" items="${userList}" varStatus="status">
								<option value="${item.user_iidd}">${item.user_name}</option>
							</c:forEach>
						</SELECT>
					</td>
					<td aling="center" valign="middle">
						<INPUT class="button_2003" onclick="add_MoreTrue('next_approver_all','next_approver_sel');" type="button" value="增加-->" name="btnAddItem" /><br/>
						<br/>
						<INPUT class="button_2003" onclick="del_MoreTrue('next_approver_sel');" type="button" value="删除<--" name="btnDelItem"><br/>
						<br/>
						<INPUT class="button_2003" onclick="add_all_True('next_approver_all','next_approver_sel');" type="button" value="全部增加" name="btnAddItem" /><br/>
						<br/>
						<INPUT class="button_2003" onclick="del_all_True('next_approver_sel');" type="button" value="全部删除" name="btnDelItem"><br/>
					</td>
					<td>
						<SELECT size="8" multiple="true" style="WIDTH: 100px" ondblclick="del_True('next_approver_sel');" id="next_approver_sel">
						</SELECT>
					</td>
				</tr>
			</table>
		</td>
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
