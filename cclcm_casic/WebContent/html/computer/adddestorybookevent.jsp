<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
<title>笔记本报废</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="${ctx}/_css/css200603.css" type="text/css" media="screen" />
	<link href="${ctx}/_script/calendar2/calendar-blue.css" type="text/css" rel="stylesheet"/>
    <script language="javascript" src="${ctx}/_script/calendar2/calendar.js"></script>
	<script language="javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>
	<script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
	<script language="javascript" src="${ctx}/_script/casic304_common_bm.js"></script>
	<script language="javascript" src="${ctx}/_script/casic304_ajax.js"></script>
	<script type="text/javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>
 	<script type="text/javascript" src="${ctx}/uploadify/jquery.uploadify.min.js"></script>
	<script  language="JavaScript" >
	$(document).ready(function(){
		onHover();
		setSeclv("seclv_code");
		selectNextApprover();
	});

	function selectSeclv(seclv){
		if(seclv == ""){
			alert("请选择作业密级,以确认审批流程");
			$("#seclv_med").focus();
			return false;
		}else {
			selectNextApprover();
		}
	}
	
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
<form method="post" action="${ctx}/computer/adddestorybookevent.action">
	<input type="hidden" name="event_code" value="${event_code}" id="event_code"/>
	<input type="hidden" name="book_barcode" value="${book_barcode}" id="book_barcode"/>
	<input type="hidden" name="dept_id" id="dept_id" value="${curUser.dept_id}"/>
	<input type="hidden" name="jobType" id="jobType" value="${jobType}"/>
	<input type="hidden" name="seclv_code" id="seclv_code"/>
	<input type="hidden" name="next_approver" id="next_approver"/>
  	<table class="table_box" cellspacing=0 cellpadding=0 border=1 width="90%">
	  	<tr>
		    <td colspan="6" class="title_box">笔记本保密报废申请</td>
		</tr>
		<tr> 
	    	<td width="15%" align="center">申请用户 </td>
	    	<td width="18%"><font color="blue"><b>${curUser.user_name}</b></font></td>
	    	<td width="15%" align="center">用户部门 </td>
	    	<td width="18%"><font color="blue"><b>${curUser.dept_name}</b></font></td>
	    	<td width="15%" align="center"><font color="red">*</font>申请人电话</td>
			<td width="18%">
				<input type="text" name="user_phone" id="user_phone"/>
			</td>
	     </tr>
	    <tr>
		  	<td colspan="6" align="left">&nbsp;&nbsp;&nbsp;<font color="blue"><b>待报废笔记本基本信息</td>
		</tr>
		<tr>
			<td align="center">保管人</td>
			<td><font color="blue"><b>&nbsp;${book.duty_user_name}</b></font></td>
			<td align="center">使用部门</td>
			<td><font color="blue"><b>&nbsp;${book.duty_dept_name}</b></font></td>
			<td align="center">单位</td>
	    	<td><font color="blue"><b>&nbsp;${book.duty_entp}</b></font></td>
	    </tr>  
	    <tr>
			<td align="center">设备状态</td>
			<td><font color="blue"><b>&nbsp;${book.book_status_name}</b></font></td>
			<td align="center">硬盘序列号</td>
			<td><font color="blue"><b>&nbsp;${book.hdisk_no}</b></font></td>
			<td align="center">统一编号</td>
			<td><font color="blue"><b>&nbsp;${book.book_code}</b></font></td>
			
		</tr>     
	    <tr>
	    	<td align="center">设备密级</td>
	    	<td><font color="blue"><b>&nbsp;${book.seclv_name}</b></font></td>
	    	<td align="center">保密编号</td>
	    	<td><font color="blue"><b>&nbsp;${book.conf_code}</b></font></td>
	    	<td align="center">名称型号</td>
	    	<td><font color="blue"><b>&nbsp;${book.book_model}</b></font></td>
		</tr>
		<tr>
	    	<td align="center">外出情况</td>
	    	<td><font color="blue"><b>&nbsp;${book.outsideinfo}</b></font></td>
	    	<td align="center">操作系统时间</td>
	    	<td><font color="blue"><b>&nbsp;${book.book_os}</b></font></td>
	    	<td align="center">原保密编号</td>
	    	<td><font color="blue"><b>&nbsp;${book.oldconf_code}</b></font></td>
		</tr>
		<tr>
	    	<td align="center">MAC地址</td>
	    	<td><font color="blue"><b>&nbsp;${book.book_mac}</b></font></td>
	    	<td align="center">存储涉密信息情况</td>
			<td><font color="blue"><b>&nbsp;${book.storage_secinfo}</b></font></td>
	    	<td align="center">联网情况</td>
	    	<td><font color="blue"><b>&nbsp;${book.internet_type}</b></font></td>
		</tr>    
	 	<tr>
			<td align="center">存放地点</td>
			<td><font color="blue"><b>&nbsp;${book.storage_location}</b></font></td>
			<td align="center">备注</td>
			<td colspan="3"><font color="blue"><b>&nbsp;${book.detail}</b></font></td>
		</tr>
		<tr>
			<td align="center">备注</td>
			<td colspan="5"><input type="text" name="summ" id="summ" size="55"/></td>
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