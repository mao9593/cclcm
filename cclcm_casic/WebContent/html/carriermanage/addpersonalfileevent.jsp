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
 	<script language="javascript" src="${ctx}/_script/casic304_common_bm.js"></script>
 	<script language="javascript" src="${ctx}/_script/calendar2/calendar.js"></script>
 	<script language="javascript" src="${ctx}/_script/My97DatePicker/WdatePicker.js"></script>
 	<link href="${ctx}/_script/calendar2/calendar-blue.css" type="text/css" rel="stylesheet"/>
<script>
$(document).ready(function(){
	onHover();
	setSeclv("seclv_code");
	selectNextApprover();
});

	function selectNextApprover(){
		del_all_True('next_approver_sel');
		var url = "${ctx}/basic/getnextapprover.action";
		callServerPostForm1(url,document.forms[0]);
	}
	function getAjaxResult1(){
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
	function chk()
	{
         if($("#file_num").val() == 0){
			alert("请填写涉密人员个人涉密资料情况");
			$("#file_num").focus();
			return false;
		} 
		 if($("#row_num").val() == 0){
			alert("请填写涉密人员个人涉密资料情况");
			$("#row_num").focus();
			return false;
		} 
		
		 if($("#row_num").val() != 0){
			if($("#file_name").val() == 0){
			alert("请填写个人涉密资料名称");
			$("#file_name").focus();
			return false;
		} 
		
		if($("#file_seclv_code").val() == 0){
			alert("请选择个人涉密资料密级");
			$("#file_seclv_code").focus();
			return false;
		} 
		
		if($("#file_type").val() == 0){
			alert("请选择资料类型");
			$("#file_type").focus();
			return false;
		} 
		if($("#file_type").val() == '电子资料'){
		if($("#file_category").val() == 0){
			alert("请选择电子资料的格式");
			$("#file_category").focus();
			return false;
		} 
		}
		 if($("#file_type").val() == '其他'){
		if($("#other_type").val() == 0){
			alert("请选择资料的格式");
			$("#other_type").focus();
			return false;
		} 
		} 
		if($("#file_quantity").val() == 0){
			alert("请填写资料数量");
			$("#file_quantity").focus();
			return false;
		} 
		
			
		/* 	alert("请填写涉密人员个人涉密资料情况");
			$("#row_num").focus();
			return false; */
		} 
		
		/* if($("#duty_dept_id").val().trim() == ""){
			alert("请输入责任部门");
			$("#duty_dept_name").focus();
			return false;
		}  */
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
		

function addRow_personal_ledger(){
	var table = document.all("personal_ledger");
	
	var row_num = table.rows.length;
	new_tr = table.insertRow();
	new_tr.align="center";
	new_tb0 = new_tr.insertCell();
	new_tb1 = new_tr.insertCell();
	new_tb2 = new_tr.insertCell();
	new_tb3 = new_tr.insertCell();
	new_tb4 = new_tr.insertCell();
	new_tb5 = new_tr.insertCell();
	new_tb6 = new_tr.insertCell();

	var nums = row_num -1;
	new_tb0.innerHTML='<input type="checkbox" value="'+row_num+'" name="file_id"/>'+nums; 
	new_tb1.innerHTML='<input type="text" id="file_name" name="file_name" size="25"/>';
 	new_tb2.innerHTML='<select id="file_seclv_code" name="file_seclv_code"> \
				<option value="">--请选择--</option> \
				<s:iterator value="#request.seclvList" var="seclv"> \
					<option value="${seclv.seclv_code}">${seclv.seclv_name}</option> \
				</s:iterator> \
			</select>';   
 	new_tb3.innerHTML='<select name="file_type" id="file_type" >\
			<option value="" >--请选择--</option>\
			<option value="电子资料">电子资料</option>\
			<option value="纸质">纸质</option>\
			<option value="软件">软件</option>\
			<option value="其他">其他</option>\
		</select>';  
	new_tb4.innerHTML='<select name="file_category" id="file_category" > \
			<option value="" >--请选择--</option> \
			<option value="word" >word</option>\
			<option value="excel">excel</option>\
			<option value="ppt">ppt</option>\
		</select>';    
	new_tb5.innerHTML='<input type="text" id="other_type" name="other_type" size="15"/>'; 
	new_tb6.innerHTML='<input type="text" id="file_quantity" name="file_quantity" size="5"/>';
 
	$("#file_num").val(nums);//添加的行数
}

function delRow_personal_ledger(){//删除一行
	var table = document.all("personal_ledger");
	var sum_num = table.rows.length;
	if(sum_num < 3)
	{
		alert("已没有可删除的行！");
		return false;
	}
	if($(":checkbox:checked").size() == 0){
		alert("请先勾选需要删除的行");
			return false;
	}else if($(":checkbox:checked").size()>1){
		alert("每次只能删除一行！");
	}else{
		$(":checkbox:checked[value!=''][name='file_id']").each(function (){
			var row_num = this.value;
			row_num = row_num+1;
			table.deleteRow(this.value);
			sum_num = sum_num -1;
			for(i=2;i<sum_num;i++)
			{
				var j = i-1;
				table.rows[i].cells[0].innerHTML = '<input type="checkbox" value="'+i+'" name="file_id"/>'+j;
			}
		});		
	}
	var nums = $("#file_num").val();
	$("#file_num").val(nums-1);
}
</script>
</head>
<body oncontextmenu="self.event.returnValue=false">
	<form action="${ctx}/carriermanage/addpersonalfileevent.action" method="POST">
		<input type="hidden" id="event_code" name="event_code" value="${event_code}"/>
		<input type="hidden" id="dept_id" name="dept_id" value="${curUser.dept_id}"/>
		<input type="hidden" id="jobType" name="jobType" value="${jobType}"/>
		<input type="hidden" id="next_approver" name="next_approver" />
		<input type="hidden" id="type" name="type" value="${type}"/>
		<input type="hidden" name="seclv_code" id="seclv_code"/>
		<input type="hidden" name="file_num" id="file_num" value="${file_num}"/>

<table width="85%" border="1" cellspacing="0" cellpadding="0" align="center" class="table_box">	
	<tr>
		<td colspan="4" class="title_box">涉密人员个人涉密资料台帐</td>
	</tr>
	<tr> 
    	<td width="10%" align="center">申请人 </td>
    	<td width="23%"><font color="blue"><b>${curUser.user_name}</b></font></td>
    	<td width="10%" align="center">用户部门 </td>
    	<td width="23%"><font color="blue"><b>${curUser.dept_name}</b></font></td>
	</tr>
	<tr> 
  <!--   	<td align="center"><font color="red">*</font>责任人</td>
		<td >
			<input type="text" id="duty_user_name" name="duty_user_name" onkeyup="selectRecvUser(this.value);"/>
    		<input type="hidden" id="duty_user_id" name="duty_user_id"/><br>
    		<div id="allOptions" class="containDiv" style="position:absolute;border:0px solid black;padding:0px">
		</td>
    	<td width="10%" align="center">责任部门 </td>
    	<td>
			<input type="text" id="duty_dept_name" name="duty_dept_name" readonly="readonly" style="cursor:hand" onclick="openDeptSelect('duty_dept_name','duty_dept_id','radio')"/>
    		<input type="hidden" id="duty_dept_id" name="duty_dept_id"/><br>
		</td> -->
		<td align="center"><font color="red">*</font>季度</td>
		  <td>
			<input type="text" id="file_year" name="file_year" onclick="WdatePicker({dateFmt:'yyyy'})" class="Wdate" size="5"/>年
			<select name="file_quarter" id="file_quarter">
				<option value="">--季度--</option>
				<option value="一季度">一季度</option>
				<option value="二季度">二季度</option>
				<option value="三季度">三季度</option>
				<option value="四季度">四季度</option>
    		</select>&nbsp;	
	    </td> 
		<td align="center"><font color="red">*</font>责任人单位</td>
		<td>
		   <select name="duty_entp_id" id="duty_entp_id">
				<option value="">--请选择--</option>
				<option value="总部">总部</option>
				<option value="24所">24所</option>
				<option value="26所">26所</option>
				<option value="44所">44所</option>
    		</select>&nbsp;	
		</td>
	</tr>
	
<!-- 	<tr>
	   <td colspan="4">涉密台帐信息&nbsp;</td>
	</tr> -->
	<tr>
		<td align="center"><font color="red">*</font>涉密台帐信息</td>
		<td colspan="3">
			<table id="personal_ledger" name="personal_ledger" width="100%" border="1" cellspacing="0" cellpadding="0" align="center" class="table_box_bottom_border">
				<tr>
					<input type="button" class="button_2003" value="添加一行" onclick="addRow_personal_ledger();"/>
					<input type="button" class="button_2003" value="删除一行" onclick="delRow_personal_ledger();"/>
				</tr>
				<tr>
					<td align="center" width="5%">序号</td>
					<td align="center" width="25%">涉密资料名称</td>
					<td align="center" width="10%">资料密级</td>
					<td align="center" width="10%">资料类型</td>
					<td align="center" width="10%">电子资料格式</td>
					<td align="center" width="15%">备注(其他类型请填写)</td>
					<td align="center" width="5%">合计</td>
				</tr>		
			</table>	
		</td>
	</tr>	
  	<tr id="tr_approver" style="display: none">
  		<td align="center" id="selApprover1">选择审批人</td>
  		<td id="selApprover2" colspan="3">
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
  	<tr>
    <td colspan="4" align="center"> 
    <input type="submit" value="提交" class="button_2003" onclick="return chk();">&nbsp;
    <input type="button" value="返回" class="button_2003" onclick="history.go(-1);">
    </td>
  </tr>
</table>
</form>
</body>
</html>
