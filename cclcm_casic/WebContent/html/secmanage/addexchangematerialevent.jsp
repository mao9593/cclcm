<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<link rel="stylesheet" type="text/css" href="${ctx}/_css/displaytag.css" media="screen"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/_css/css200603.css"  media="screen"/>
    <link type="text/css" rel="stylesheet" href="${ctx}/uploadify/uploadify.css"/>
	<script language="JavaScript" src="${ctx}/_script/function.js"></script>
	<script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
 	<script language="javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>	
 	<script language="javascript" src="${ctx}/_script/casic304_ajax.js"></script>
 	<script language="JavaScript" src="${ctx}/_script/common.js"></script>	
	<script type="text/javascript" src="${ctx}/uploadify/jquery.uploadify.min.js"></script>
	<script language="javascript" src="${ctx}/_script/casic304_common_bm.js"></script>
<script>
$(document).ready(function(){
	onHover();
	disableEnterSubmit();
	setSeclv("seclv_code");
	selectNextApprover();
});
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
	function chk()
	{
		if($("#contact_num").val() == ""){
			alert("请填写联系方式");
			$("#contact_num").focus();
			return false;
		}	
		if($("#exc_type").val() == ""){
			alert("请填写交付材料类型");
			$("#exc_type").focus();
			return false;
		}	
		if($("#exc_object").val() == ""){
			alert("请填写交付对象");
			$("#exc_object").focus();
			return false;
		}
		if($("#exc_location").val() == ""){
			alert("请填写交付地点 ");
			$("#exc_location").focus();
			return false;
		}
		
		if($("#file_num").val() == ""){
			alert("请填写材料清单");
			$("#file_num").focus();
			return false;			
		}
		if($("#file_name").val() == ""){
			alert("请填写材料名称");
			$("#file_name").focus();
			return false;
		}		 		
 		if($("#file_selv").val() == ""){
			alert("请选择材料密级");
			$("#file_selv").focus();
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
	
function addRow_family(){//添加一行
	var table = document.all("myTable");	
	var row_num = table.rows.length;
	new_tr = table.insertRow();
	new_tr.align="center";
	new_tb0 = new_tr.insertCell();
	new_tb1 = new_tr.insertCell();
	new_tb2 = new_tr.insertCell();
	var nums = row_num -1;
	new_tb0.innerHTML='<input type="checkbox" value="'+row_num+'" name="list_id" size="10"/>'+nums; 
	new_tb1.innerHTML='<input type="text" name="file_name" id="file_name" size="70"/>';
  	new_tb2.innerHTML='<select name="file_selv" id="file_selv" >\
			<option value="" >--请选择--</option>\
			<option value="机密">机密</option>\
			<option value="秘密">秘密</option>\
			<option value="内部">内部</option>\
			<option value="非密">非密</option>\
		</select>';  
		
	$("#file_num").val(nums);//添加的行数
}

function delRow_family(){//删除一行
	var table = document.all("myTable");
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
		$(":checkbox:checked[value!=''][name='list_id']").each(function (){
			var row_num = this.value;
			table.deleteRow(this.value);
			sum_num = sum_num -1;
			for(i=2;i<sum_num;i++)
			{
				var j = i-1;
				table.rows[i].cells[0].innerHTML = '<input type="checkbox" value="'+i+'" name="list_id"/>'+j;
			}
		});		
	}
}
</script>
</head>
<body oncontextmenu="self.event.returnValue=false">
<table width="80%" border="1" cellspacing="0" cellpadding="0" align="center" class="table_box">	
	<form action="${ctx}/secmanage/addexchangematerialevent.action" method="POST">
		<input type="hidden" id="event_code" name="event_code" value="${event_code}"/>
		<input type="hidden" id="dept_id" name="dept_id" value="${curUser.dept_id}"/>
		<input type="hidden" id="jobType" name="jobType" value="${jobType}"/>
		<input type="hidden" id="next_approver" name="next_approver" />
		<input type="hidden" name="seclv_code" id="seclv_code"/>
		<input type="hidden" name="file_num" id="file_num" value=""/>
	<tr>
		<td colspan="4" class="title_box">对外提供资料保密审查表</td>   
	</tr>
	<tr> 
    	<td width="12%" align="center">申请部门 </td>
    	<td width="20%"><font color="blue"><b>${curUser.dept_name}</b></font></td>
    	<td width="15%" align="center">申请人 </td>
    	<td width="20%"><font color="blue"><b>${curUser.user_name}</b></font></td>
    	
	</tr>
	<tr> 			
		<td align="center"><font color="red">*</font>联系电话 </td>
		<td><input type="text" id="contact_num" name="contact_num"/></td>
		<td align="center"><font color="red">*</font>交付材料类型 </td>
		<td>
			<select name="exc_type" id="exc_type">
				<option value="">--请选择--</option>
				<option value="1">纸介质</option>
				<option value="2">电子载体</option>
				<option value="3">其他</option>
			</select>
		</td>
   	</tr> 
   	<tr>
   		<td align="center"><font color="red">*</font>交付对象 </td>
		<td><input type="text" id="exc_object" name="exc_object"/></td>
		<td align="center"><font color="red">*</font>交付地点 </td>
		<td><input type="text" id="exc_location" name="exc_location"/></td>
   	</tr>
	<tr>
		<td align="center"><font color="red">*</font>&nbsp;交付资料清单</td>
		<td colspan="4">
			
		<table  width="80%" border="1" cellspacing="0" cellpadding="0" class="table_box_bottom_border" id="myTable" id="myTable">
			<tr>
				<input type="button" class="button_2003" value="添加一行" onclick="addRow_family();"/>
				<input type="button" class="button_2003" value="删除一行" onclick="delRow_family();"/>
			</tr>
			<tr>
				<td width="6%" align="center">序号</td>
				<td width="50%" align="center">资料名称</td>
				<td width="10%" align="center">密级</td>
			</tr>				
		</table>
		</td>
	</tr>
	<!-- <tr>
			<td align="center">部门审查意见</td>
			<td colspan="3" id="step1">
				<table width="99%" border="0" cellspacing="0" cellpadding="0" align="left">
					<tr><td>
						<textarea id="opinion1" rows="4" cols="100" readonly="readonly" ></textarea>
					</td></tr>
					<tr><td id="hidden1"></td></tr>
			</table>
	  </tr>
	  <tr>
			<td align="center">保密审批意见</td>
			<td colspan="3" id="step2">
				<table width="99%" border="0" cellspacing="0" cellpadding="0" align="left">
					<tr><td>
						<textarea id="opinion2" rows="4" cols="100" readonly="readonly" ></textarea>
					</td></tr>
					<tr><td id="hidden2"></td></tr>
			</table>
	  </tr>
	  <tr>
			<td align="center">材料交接情况记录</td>
			<td colspan="3" id="step3">
				<table width="99%" border="0" cellspacing="0" cellpadding="0" align="left">
					<tr><td>
						<textarea id="opinion3" rows="4" cols="100" readonly="readonly" ></textarea>
					</td></tr>
					<tr><td id="hidden3"></td></tr>
			</table>
	  </tr> -->
	
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
	</form>
	 	
  	<tr>
    <td colspan="4" align="center"> 
      <input type="button" class="button_2003" value="提交" onclick="if(chk()) forms[0].submit();" id="submit_btn"/>
    </td>
  </tr>
</table>
</body>
</html>
