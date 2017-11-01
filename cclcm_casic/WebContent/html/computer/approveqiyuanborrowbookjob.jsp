<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<link rel="stylesheet" type="text/css" href="${ctx}/_css/displaytag.css" media="screen"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/_css/css200603.css"  media="screen"/>
     <link rel="stylesheet" type="text/css" href="${ctx}/_css/print.css"  media="print"/>
    <script language="javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>
	<script language="JavaScript" src="${ctx}/_script/function.js"></script>
	<script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
	<script language="javascript" src="${ctx}/_script/casic304_common_bm.js"></script>
	<script language="javascript" src="${ctx}/_script/My97DatePicker/WdatePicker.js"></script>
	<script language="javascript" src="${ctx}/_script/jquery.jqprint-0.3.js"></script>
<script>
	$(document).ready(function(){
		prepareBizApproval("${process.step_dept}","${process.step_role}","${process.step_dept_name}","${process.step_role_name}","${ctx}");
		if($("#next_approver_all").children().size() == 0){
			$("#selApprover1").hide();
			$("#selApprover2").hide();
		}
		$("#submit_btn").attr("disabled",false);

		if("${history}" == "Y"){
			viewOpinion("");//判断审批到哪一步
		}else{
			viewOpinion("read");//判断审批到哪一步
		}
		var this_step1 = Number($("#listSize").val()) +1;
		if((this_step1 == 3 && "${job.jobType.jobTypeCode}" == 'BORROW_BOOKOUT') || (this_step1 == 2 && "${job.jobType.jobTypeCode}" == 'BORROW_BOOK')){
			$("#ChoiceButton").attr("disabled",false);	
		}else		
			$("#ChoiceButton").attr("disabled",true);	 
		
		$("#page_print").click(function(){
		$(".printContent").jqprint();
	})
	});

	function chk(){
		subOpinion();//提交时将审批意见复制给opinion
		var this_step = Number($("#listSize").val()) +1;
 		if((this_step == 3 && "${job.jobType.jobTypeCode}" == 'BORROW_BOOKOUT') || (this_step == 2 && "${job.jobType.jobTypeCode}" == 'BORROW_BOOK')){
			if($("#approved")[0].checked){
				if($("#book_barcode").val().trim() == ""){
					alert("请分配对应的笔记本");
					return false;
				}
			}	
		}
		if((this_step == 7 && "${job.jobType.jobTypeCode}" == 'BORROW_BOOKOUT') || (this_step == 6 && "${job.jobType.jobTypeCode}" == 'BORROW_BOOK')){
			if($("input:radio[name='check1']:checked").val() == null || $("input:radio[name='check2']:checked").val() == null ||$("input:radio[name='check3']:checked").val() == null
			    || $("input:radio[name='check4']:checked").val() == null || $("input:radio[name='check5']:checked").val() == null ||$("input:radio[name='check6']:checked").val() == null
			     || $("input:radio[name='check7']:checked").val() == null){
				alert("请填写检查结果");
				return false;
			}
		} 
		if($("#opinion").val().trim() == ""){
			alert("审批意见不能为空");
			$("#opinion").focus();
			return false;
		}
		if($("#next_approver_all option").size() > 0 && $("#next_approver_sel option").size() == 0 && $("#approved")[0].checked){
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
	function OpenUrl(event_code, job_code){
		var url1 = "${ctx}/computer/choicebook.action";
		var url = "${ctx}/computer/choicebook.action?event_code="+escape(event_code);
		var rValue=window.showModalDialog(url,'', 'dialogHeight:600px;dialogWidth:1300px;center:yes;status:no;scroll:no;help:no;unadorned:no;resizable:no');
		if(rValue != null){
			$("#book_barcode").val(rValue.book_barcode);
			$("#type").val(rValue.type);
			if($("#book_barcode").val()){
				$("#ChoiceButton").attr("disabled",true);
			}
			var url_n = "${ctx}/computer/approveborrowbookjob.action?job_code="+escape(job_code)+"&book_barcode="+escape(rValue.book_barcode);
			location.replace(url_n);
		}
	}
	
</script>
</head>
<div class="printContent">
<body oncontextmenu="self.event.returnValue=false">
<table width="90%" border="1" cellspacing="0" cellpadding="0" align="center" class="table_box">
<form action="${ctx}/computer/approveqiyuanborrowbookjob.action" method="post" >
	<input type="hidden" name="next_approver" id="next_approver"/>
	<input type="hidden" name="job_code" value="${job.job_code}"/>
	<input type="hidden" id="listSize" name="listSize" value="${listSize}"/> 
	<input type="hidden" id="opinion_all" name="opinion_all" value="${opinion_all}"/> 
	<input type="hidden" name="event_code" id="event_code" value="${event.event_code}"/> 
	<input type="hidden" id="opinion" name="opinion"/> 
	<input type="hidden" name="book_barcode" id="book_barcode" value="${device.book_barcode}"/>
	<tr>
	    <td colspan="4" align="center" class="title_box">便携式计算机借用审批表</td>
	</tr>
	<tr> 
    	<td width="10%" align="center">姓名</td>
    	<td width="23%"><font color="blue"><b>${job.user_name}</b></font></td>
    	<td width="10%" align="center">单位(部门) </td>
    	<td width="23%"><font color="blue"><b>${job.dept_name}</b></font></td>
    </tr>
    <tr> 
		<td width="10%" align="center">涉密类别 </td>
    	<td width="10%"><font color="red"><b>${curUser.security_name}</b></font></td>
    	<td width="10%" align="center">使用地点</td>
    	<td width="23%"><font color="blue"><b>${event.place}</b></font></td>    	
    </tr>
	<tr>
		<td align="center">联系电话</td>
    	<td><font color="blue"><b>&nbsp;${event.contact_num}</b></font></td>
    	<td align="center">手机</td>
    	<td><font color="blue"><b>&nbsp;${event.mobilephone_num}</b></font></td>
	</tr>
	<tr>
		<td align="center">计算机编号</td>
		<td><font color="blue"><b>&nbsp;${event.devieinfo}</b></font></td>
		<td align="center">计算机密级</td>
		<td><font color="blue"><b>&nbsp;${event.book_selv_name}</b></font></td>
	</tr>
	<tr>
	   	<td align="center">借用时间</td>
	   	<td><font color="blue"><b>&nbsp;${event.start_time_str}</b></font></td>
	   	<td align="center">归还时间</td>
	   	<td><font color="blue"><b>&nbsp;${event.end_time_str}</b></font></td>
	</tr>
	<tr>
	    <td align="center" colspan="4">
	        <tr>
	   	        <td align="center">借用事由</td>
	   	        <td colspan="5"><font color="blue"><b>&nbsp;${event.summ}</b></font></td>
		    </tr>
		</td>		
	</tr>
	<tr>
	   	<td align="center">借用期间是否<br>需要使用本机刻录文件</td>
		<td><font color="blue"><b>&nbsp;${isBurnAtBook}</b></font></td>		
	   	<td align="center">光盘编号</td>
	   	<td><font color="blue"><b>&nbsp;${event.disc_num}</b></font></td>
	</tr> 	
 	<tr>
		<td align="center" colspan="4">
			<table width="95%" border="0" cellspacing="0" cellpadding="0"  align="center">
			<tr>
			   	<td width="10%" align="center">&nbsp;序号</td>
			   	<td width="60%" align="center">&nbsp;刻录文件名称</td>
			   	<td width="15%" align="center">&nbsp;密级</td>
			</tr>	
			<tr>
				<td align="center">&nbsp;1</td>
				<td align="center"><font color="blue"><b>&nbsp;${discfile_str[0]}</b></font></td>
				<td align="center"><font color="blue"><b>&nbsp;${discfile_str[1]}</b></font></td>
			</tr>
			<tr>
				<td align="center">&nbsp;2</td>
				<td align="center"><font color="blue"><b>&nbsp;${discfile_str[2]}</b></font></td>
				<td align="center"><font color="blue"><b>&nbsp;${discfile_str[3]}</b></font></td>
			</tr>
			<tr>
				<td align="center">&nbsp;3</td>
				<td align="center"><font color="blue"><b>&nbsp;${discfile_str[4]}</b></font></td>
				<td align="center"><font color="blue"><b>&nbsp;${discfile_str[5]}</b></font></td>
			</tr>
			<tr>
				<td align="center">&nbsp;4</td>
				<td align="center"><font color="blue"><b>&nbsp;${discfile_str[6]}</b></font></td>
				<td align="center"><font color="blue"><b>&nbsp;${discfile_str[7]}</b></font></td>
			</tr>
		    </table>
    </tr>
    <tr>
 		<td align="center">保密防护措施</td>
 		<td colspan="5"><font color="blue"><b>${security_methord_str[0]}<br>${security_methord_str[1]}<br>${security_methord_str[2]}${security_methord_str[3]}</b></font></td>
	</tr>
	<tr>
		<td align="center">处(室)<br>领导意见</td>
		<td colspan="5" id="step1">
			<table width="99%" border="0" cellspacing="0" cellpadding="0" align="left">
				<tr><td><textarea id="opinion1" rows="4" cols="100" readonly="readonly"></textarea></td></tr>
				<tr><td id="hidden1"></td></tr>
			</table>
		</td>
	</tr>
<c:if test="${job.jobType.jobTypeCode == 'QIYUAN_BORROW_BOOK'}">	
	<tr>
		<td align="center">单位(部门)<br>领导意见</td>	
		<td colspan="5" id="step2">
			<table width="99%" border="0" cellspacing="0" cellpadding="0" align="left">							
				<tr><td><textarea id="opinion2" rows="4" cols="100" readonly="readonly"></textarea></td></tr>
				<tr><td id="hidden2"></td></tr>
			</table>				
		</td>
	</tr>
</c:if>
	<c:if test="${history != 'Y'}">
	<tr>
  		<td align="center" id="selApprover1">选择审批人</td>
  		<td id="selApprover2" colspan="6">
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
						<INPUT class="button_2003" onclick="add_MoreTrue('next_approver_all','next_approver_sel');" type="button" value="增加-->" /><br/>
						<br/>
						<INPUT class="button_2003" onclick="del_MoreTrue('next_approver_sel');" type="button" value="删除<--"><br/>
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
  	</c:if>
  	<tr class="Noprint">
	    <td colspan="6" align="center">
	    	<c:if test="${history != 'Y'}">
	     	<input class="button_2003" type="submit" value="提交" onclick="return chk();" id="submit_btn" disabled="disabled">&nbsp;
	     	</c:if>
			<input class="button_2003" type="button" value="返回" onClick="javascript:history.go(-1);">&nbsp;
			<c:if test="${history == 'Y'}">
			<input type="button" class="button_2003" value="打印" id="page_print" />
			</c:if>
	    </td>
  	</tr>
  	</form>
</table>
</br></br>
</body>
</div>
</html>
