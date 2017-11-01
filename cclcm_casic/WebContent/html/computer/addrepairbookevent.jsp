<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
<title>笔记本维修</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="${ctx}/_css/css200603.css" type="text/css" media="screen" />
	<script language="javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>
	<script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
	<script language="javascript" src="${ctx}/_script/casic304_common_bm.js"></script>
	<script language="javascript" src="${ctx}/_script/casic304_ajax.js"></script>
	<script  language="JavaScript" >
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
		if($("#user_phone").val().trim() == ""){
			alert("请填申请人电话");
			$("#user_phone").focus();
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
<center>
<form method="post" action="${ctx}/computer/addrepairbookevent.action">
	<input type="hidden" name="event_code" value="${event_code}" id="event_code"/>
	<input type="hidden" name="book_barcode" value="${book_barcode}" id="book_barcode"/>
	<input type="hidden" name="dept_id" id="dept_id" value="${curUser.dept_id}"/>
	<input type="hidden" name="jobType" id="jobType" value="${jobType}"/>
	<input type="hidden" name="seclv_code" id="seclv_code"/>
	<input type="hidden" name="next_approver" id="next_approver"/>
  	<table class="table_box" cellspacing=0 cellpadding=0 border=1 width="90%">
	  	<tr>
		    <td colspan="6" class="title_box">笔记本维修申请</td>
		</tr>
		<tr height="40"> 
	    	<td width="10%" align="center">申请用户 </td>
	    	<td width="23%"><font color="blue"><b>${curUser.user_name}</b></font></td>
	    	<td width="10%" align="center">用户部门 </td>
	    	<td width="23%"><font color="blue"><b>${curUser.dept_name}</b></font></td>
	    	<td align="center"><font color="red">*</font>申请人电话</td>
			<td align="center"><input type="text" name="user_phone" id="user_phone"/></td>
	     </tr>
	     <tr>
	     	<td align="center">名称型号</td>
			<td ><font color="blue"><b>&nbsp;${book.book_model}</td>
			<td align="center">统一编号 </td>
    		<td ><font color="blue"><b>&nbsp;${book.book_code}</b></font></td>
    		<td align="center">保密编号</td>
			<td ><font color="blue"><b>&nbsp;${book.conf_code}</td>
	     </tr>
	     <tr>
			<td align="center">使用单位</td>
			<td ><font color="blue"><b>&nbsp;${book.duty_entp}</td>
			<td align="center">原保密编号 </td>
    		<td ><font color="blue"><b>&nbsp;${book.oldconf_code}</b></font></td>
			<td align="center">设备密级 </td>
    		<td ><font color="blue"><b>&nbsp;${book.seclv_name}</b></font></td>
		</tr>
		<tr>
			<td align="center">硬盘序列号 </td>
    		<td ><font color="blue"><b>&nbsp;${book.hdisk_no}</b></font></td>
			<td align="center">安装地点</td>
    		<td ><font color="blue"><b>&nbsp;${book.storage_location}</b></font></td>
    		<td align="center">笔记本状态</td>
    		<td><font color="blue"><b>&nbsp;${book.book_status_name}</b></font></td>
		</tr>
		<tr>
			<td align="center">备注</td>
			<td colspan="5"><textarea id="summ" name="summ" rows="3" cols="50"></textarea></td>
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
	  	<tr>
	        <td colspan="6" align="center" class="bottom_box">
	            <input type="submit" value="添加申请" class="button_2003" onclick="return chk();">&nbsp;
	            <input type="reset" value="重置" class="button_2003">
	            <input type="button" value="返回" class="button_2003" onclick="history.go(-1);">
	        </td>
	    </tr>
  	</table>
</form>
</center>
</body>
</html>