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
		var rValue=window.showModalDialog(url,'', 'dialogHeight:700px;dialogWidth:1300px;center:yes;status:no;scroll:no;help:no;unadorned:no;resizable:no');
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
<table width="95%" border="1" cellspacing="0" cellpadding="0" align="center" class="table_box">
<form action="${ctx}/computer/approveborrowbookjob.action" method="post" >
	<input type="hidden" name="next_approver" id="next_approver"/>
	<input type="hidden" name="job_code" value="${job.job_code}"/>
	<input type="hidden" id="listSize" name="listSize" value="${listSize}"/> 
	<input type="hidden" id="opinion_all" name="opinion_all" value="${opinion_all}"/> 
	<input type="hidden" name="event_code" id="event_code" value="${event.event_code}"/> 
	<input type="hidden" id="opinion" name="opinion"/> 
	<input type="hidden" name="book_barcode" id="book_barcode" value="${device.book_barcode}"/>
	<tr>
	    <td colspan="6" align="center" class="title_box">借用笔记本电脑审批表</td>
	</tr>
	<tr> 
    	<td width="10%" align="center">申请用户 </td>
    	<td width="10%"><font color="blue"><b>${job.user_name}</b></font></td>
    	<td width="10%" align="center">用户部门 </td>
    	<td width="10%"><font color="blue"><b>${job.dept_name}</b></font></td>
		<td width="10%" align="center">申请状态 </td>
    	<td width="10%"><font color="red"><b>${job.job_status_name}</b></font></td>    	
    </tr>
	<tr>
    	<td align="center">业务类型</td>
    	<td><font color="blue"><b>${job.jobType.jobTypeName}</b></font></td>
    	<td align="center">拟借用笔记本密级</td>
		<td><font color="blue"><b>&nbsp;${event.book_selv_name}</b></font></td>
		<td align="center">联系电话</td>
    	<td><font color="blue"><b>&nbsp;${event.contact_num}</b></font></td>
	</tr>
	<tr>
	   	<td align="center">随身携带存储介质<br>/设备名称</td>
	   	<td><font color="blue"><b>&nbsp;${name_str[0]}</b></font></td>
		<td align="center">介质/设备保密编号</td>
		<td colspan="3"><font color="blue"><b>&nbsp;${name_str[1]}</b></font></td>
	</tr>
	<tr>
		<td align="center">出差地点</td>
		<td><font color="blue"><b>&nbsp;${event.place}</b></font></td>
	   	<td align="center">借用时间</td>
	   	<td><font color="blue"><b>&nbsp;${event.start_time_str}</b></font></td>
	   	<td align="center">归还时间</td>
	   	<td><font color="blue"><b>&nbsp;${event.end_time_str}</b></font></td>
	</tr>	
 	<tr>
		<td align="center" colspan="6">
			<table width="95%" border="0" cellspacing="0" cellpadding="0"  align="center">
				<tr><td colspan="4" align="center" style="background-color: #C1E5FD">借用笔记本电脑期间特殊使用申请</td></tr>
			<tr>
			   	<td width="10%" align="center"><font color="red">*</font>&nbsp;序号</td>
			   	<td width="20%" align="center"><font color="red">*</font>&nbsp;详细</td>
			   	<td width="15%" align="center"><font color="red">*</font>&nbsp;是/否</td>
			   	<td width="45%" align="center"><font color="red">*</font>&nbsp;具体情况</td>
			</tr>	
			<tr>
				<td align="center">&nbsp;1</td>
				<td align="center">是否连接各种网络</td>
				<td align="center"><font color="blue"><b>&nbsp;${judge_str[0]}</b></font></td>
				<td align="center"><font color="blue"><b>&nbsp;${other_str[0]}</b></font></td>
			</tr>
			<tr>
				<td align="center">&nbsp;2</td>
				<td align="center">是否连接各种设备</td>
				<td align="center"><font color="blue"><b>&nbsp;${judge_str[1]}</b></font></td>
				<td align="center"><font color="blue"><b>&nbsp;${other_str[1]}</b></font></td>
			</tr>
			<tr>
				<td align="center">&nbsp;3</td>
				<td align="center">是否使用外来介质</td>
				<td align="center"><font color="blue"><b>&nbsp;${judge_str[2]}</b></font></td>
				<td align="center"><font color="blue"><b>&nbsp;${other_str[2]}</b></font></td>
			</tr>
			<tr>
				<td align="center">&nbsp;4</td>
				<td align="center">是否安装各种软件</td>
				<td align="center"><font color="blue"><b>&nbsp;${judge_str[3]}</b></font></td>
				<td align="center"><font color="blue"><b>&nbsp;${other_str[3]}</b></font></td>
			</tr>
			<tr>
				<td align="center">&nbsp;5</td>
				<td align="center">是否涉密数据输出</td>
				<td align="center"><font color="blue"><b>&nbsp;${judge_str[4]}</b></font></td>
				<td align="center"><font color="blue"><b>&nbsp;${other_str[4]}</b></font></td>
			</tr>
			<tr>
				<td align="center">&nbsp;6</td>
				<td align="center">是否存储涉密信息</td>
				<td align="center"><font color="blue"><b>&nbsp;${judge_str[5]}</b></font></td>
				<td align="center"><font color="blue"><b>&nbsp;${other_str[5]}</b></font></td>
			</tr>
			<tr>
				<td align="center">&nbsp;7</td>
				<td align="center">是否有特殊需求<br>（如刻录、打印、三合一导入功能）</td>
				<td align="center"><font color="blue"><b>&nbsp;${judge_str[6]}</b></font></td>
				<td align="center"><font color="blue"><b>&nbsp;${other_str[6]}</b></font></td>
			</tr>
			<tr>
				<td align="center">&nbsp;8</td>
				<td align="center">其他临时</td>
				<td align="center" colspan="2"><font color="blue"><b>&nbsp;${other_str[7]}</b></font></td>
			</tr>
		</table>
    </tr>
    <tr> 
    	<td colspan="2" align="center"> 申请人签名</td>
    	<td><font color="blue"><b>&nbsp;${event.signname}</td>
		<td align="center"> 时&nbsp;&nbsp;间</td>
		<td><font color="blue"><b>&nbsp;${event.sign_time_str}</td>
    </tr>
    <tr>
    	<td align="center">备注</td>
    	<td colspan="5"><font color="blue"><b>&nbsp;${event.summ}</b></font></td>
    </tr>
    <c:if test="${history eq 'Y'}">
		<tr>
	  		<td align="center">下一步骤审批人</td>
	  		<td colspan="5"><font color="blue"><b>&nbsp;${job.next_approver_name}</b></font></td>
	  	</tr>
  	</c:if>
	<tr>
		<td align="center">部门领导意见</td>
		<td colspan="5" id="step1">
			<table width="99%" border="0" cellspacing="0" cellpadding="0" align="left">
				<tr><td><textarea id="opinion1" rows="4" cols="100" readonly="readonly"></textarea></td></tr>
				<tr><td id="hidden1"></td></tr>
			</table>
		</td>
	</tr>
<c:if test="${job.jobType.jobTypeCode == 'BORROW_BOOKOUT'}">
	<tr>
		<td align="center">部/所领导审批</td>
		<td colspan="5" id="step2">
			<table width="99%" border="0" cellspacing="0" cellpadding="0" align="left">
				<tr><td colspan="6"><textarea id="opinion2" rows="4" cols="100" readonly="readonly"></textarea></td></tr>
				<tr><td colspan="6" id="hidden2"></td></tr>
			</table>
		
		</td>
	</tr>
	<tr>
		<td align="center">保密处办理登记</td>	
		<td colspan="5">
			<table width="99%" border="0" cellspacing="0" cellpadding="0" align="left">
				<c:if test="${!empty device}">
					<tr>
						<td colspan="6" style="background-color: #C1E5FD" align="center">查看已分配笔记本明细</td>
					</tr>
					<tr>
						<td width="10%" align="center">保管人</td>
						<td width="15%"><font color="blue"><b>&nbsp;${device.duty_user_name}</b></font></td>
						<td width="10%" align="center">保管部门</td>
						<td width="13%"><font color="blue"><b>&nbsp;${device.duty_dept_name}</b></font></td>
						<td width="10%" align="center">单位</td>
				    	<td width="13%"><font color="blue"><b>&nbsp;${device.duty_entp}</b></font></td>
				    </tr>  
				    <tr>
						<td align="center">设备状态</td>
						<td><font color="blue"><b>&nbsp;${device.book_status_name}</b></font></td>
						<td align="center">硬盘序列号</td>
						<td><font color="blue"><b>&nbsp;${device.hdisk_no}</b></font></td>
						<td align="center">原保密编号</td>
						<td><font color="blue"><b>&nbsp;${device.oldconf_code}</b></font></td>
					</tr>     
				    <tr>
				    	<td align="center">设备密级</td>
				    	<td><font color="blue"><b>&nbsp;${device.seclv_name}</b></font></td>
				    	<td align="center">保密编号</td>
				    	<td><font color="blue"><b>&nbsp;${device.conf_code}</b></font></td>
				    	<td align="center">名称型号</td>
				    	<td><font color="blue"><b>&nbsp;${device.book_model}</b></font></td>
					</tr>
					<tr>
						<td align="center">笔记本条码</td>
				    	<td><font color="blue"><b>&nbsp;${device.book_barcode}</b></font></td>
				    	<td align="center">外出情况</td>
				    	<td><font color="blue"><b>&nbsp;${device.outsideinfo}</b></font></td>
				    	<td align="center">系统时间</td>
				    	<td><font color="blue"><b>&nbsp;${device.book_os}</b></font></td>
					</tr>
					<tr>
				    	<td align="center">MAC地址</td>
				    	<td><font color="blue"><b>&nbsp;${device.book_mac}</b></font></td>
				    	<td align="center">存储涉密信息情况</td>
						<td><font color="blue"><b>&nbsp;${device.storage_secinfo}</b></font></td>
				    	<td align="center">联网情况</td>
				    	<td><font color="blue"><b>&nbsp;${device.internet_type}</b></font></td>
					</tr>    
				 	<tr>
						<td align="center">存放地点</td>
						<td><font color="blue"><b>&nbsp;${device.storage_location}</b></font></td>
						<td align="center">备注</td>
						<td colspan="3"><font color="blue"><b>&nbsp;${device.detail}</b></font></td>
					</tr>
				</c:if>	
				<tr><td colspan="6" id="step3">
					<table width="99%" border="0" cellspacing="0" cellpadding="0" align="left">
						<c:if test="${history != 'Y'}">
							<tr>
								<td>&nbsp;&nbsp;
									<input type="button" id="ChoiceButton" class="button_2003" value="分配可借的笔记本"  onclick="OpenUrl('${event.event_code}','${job.job_code}')";/>&nbsp;
									<%-- <input type="button" id="ChoiceButton" class="button_2003" value="分配可借的笔记本" onclick="go('${ctx}/computer/choicebook.action?job_code=${job.job_code}&event_code=${event.event_code}')";/>&nbsp; --%>
								</td>
							</tr>
			<!-- 				<tr>
								<td>计算机密级 &nbsp;&nbsp;
								<select name="selv" >
									<option value="">--请选择--</option>
									<option value="机密">机密</option>
									<option value="秘密">秘密</option>
									<option value="内部">内部</option>
									<option value="非密">非密</option>
								</select>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								计算机保密编号 &nbsp;&nbsp;
								<input type="text" name="serial"/></td>
							</tr> -->
							<tr>
								<td>借用其他设备/介质 
									  <input type="checkbox" name="other" value="打印机"/>打印机
								      <input type="checkbox"  name="other" value="刻录机"/>刻录机
								      <input type="checkbox" name="other" value="U盘"/>U盘
								      <input type="checkbox" name="other" value="光盘"/>光盘
								  <br>
								  编号<br>
								  <textarea name="other_code" rows="4" cols="80"></textarea>	
								</td>
							</tr>
						</c:if>								
						<tr><td><textarea id="opinion3" rows="4" cols="100" readonly="readonly"></textarea></td></tr>
						<tr><td id="hidden3"></td></tr>
					</table>
				</td></tr>			
			</table>
		</td>
	</tr>
	<tr>
		<td align="center">开通端口检查</td>
		<td colspan="5" id="step4">
			<table width="99%" border="0" cellspacing="0" cellpadding="0" align="left">
				<tr><td><textarea id="opinion4" rows="4" cols="100" readonly="readonly"></textarea></td></tr>
				<tr><td id="hidden4"></td></tr>
			</table>
		</td>
	</tr>
	<tr>
		<td align="center">借用人确认</td>
		<td colspan="5" id="step5">
			<table width="99%" border="0" cellspacing="0" cellpadding="0" align="left">
				<tr><td><textarea id="opinion5" rows="4" cols="100" readonly="readonly"></textarea></td></tr>
				<tr><td id="hidden5"></td></tr>
			</table>
		</td>
	</tr>
	<tr>
		<td align="center">开通端口检查</td>
		<td colspan="5" id="step6">
			<table width="99%" border="0" cellspacing="0" cellpadding="0" align="left">
				<tr><td><textarea id="opinion6" rows="4" cols="100" readonly="readonly"></textarea></td></tr>
				<tr><td id="hidden6"></td></tr>
			</table>
		</td>
	</tr>
	<tr>
		<td align="center">保密处检查</td>
		<td colspan="5" id="step7">
			<table width="99%" border="0" cellspacing="0" cellpadding="0" align="left">
			<c:if test="${history != 'Y'}">
				<tr>
					<td>1、是否连接各种网络
						<input type="radio" name="check1" value="是"/>是
					    <input type="radio"  name="check1" value="否"/>否
					   核实情况<input type="text" name="check_info"/>
					</td>
				</tr>
				<tr>
					<td>2、是否连接各种设备
						<input type="radio" name="check2" value="是"/>是
					    <input type="radio"  name="check2" value="否"/>否
					   核实情况<input type="text" name="check_info"/>
					</td>
				</tr>
				<tr>
					<td>3、是否使用外来介质
						<input type="radio" name="check3" value="是"/>是
					    <input type="radio"  name="check3" value="否"/>否
					   核实情况<input type="text" name="check_info"/>
					</td>
				</tr>
				<tr>
					<td>4、是否安装各种软件
						<input type="radio" name="check4" value="是"/>是
					    <input type="radio"  name="check4" value="否"/>否
					   核实情况<input type="text" name="check_info"/>
					</td>
				</tr>
				<tr>
					<td>5、是否涉密数据输出
						<input type="radio" name="check5" value="是"/>是
					    <input type="radio"  name="check5" value="否"/>否
					   核实情况<input type="text" name="check_info"/>
					</td>
				</tr>
				<tr>
					<td>6、是否存储涉密信息
						<input type="radio" name="check6" value="是"/>是
					    <input type="radio"  name="check6" value="否"/>否
					   核实情况<input type="text" name="check_info"/>
					</td>
				</tr>
				<tr>
					<td>7、是否有特殊需求（如刻录、打印、三合一导入功能）
						<input type="radio" name="check7" value="是"/>是
					    <input type="radio"  name="check7" value="否"/>否
					   核实情况<input type="text" name="check_info"/>
					</td>
				</tr>
				<tr>
					<td>8、其他临时
					   核实情况<input type="text" name="check_info" size="50"/>
					</td>
				</tr>
				</c:if>
				<tr><td><textarea id="opinion7" rows="4" cols="100" readonly="readonly"></textarea></td></tr>
				<tr><td id="hidden7"></td></tr>
			</table>
		</td>
	</tr>
</c:if>
<c:if test="${job.jobType.jobTypeCode == 'BORROW_BOOK'}">	
	<tr>
		<td align="center">保密处办理登记</td>	
		<td colspan="5">
			<table width="99%" border="0" cellspacing="0" cellpadding="0" align="left">
				<c:if test="${!empty device}">
					<tr>
						<td colspan="6" style="background-color: #C1E5FD" align="center">查看已分配笔记本明细</td>
					</tr>
					<tr>
						<td width="10%" align="center">保管人</td>
						<td width="15%"><font color="blue"><b>&nbsp;${device.duty_user_name}</b></font></td>
						<td width="10%" align="center">保管部门</td>
						<td width="13%"><font color="blue"><b>&nbsp;${device.duty_dept_name}</b></font></td>
						<td width="10%" align="center">单位</td>
				    	<td width="13%"><font color="blue"><b>&nbsp;${device.duty_entp}</b></font></td>
				    </tr>  
				    <tr>
						<td align="center">设备状态</td>
						<td><font color="blue"><b>&nbsp;${device.book_status_name}</b></font></td>
						<td align="center">硬盘序列号</td>
						<td><font color="blue"><b>&nbsp;${device.hdisk_no}</b></font></td>
						<td align="center">原保密编号</td>
						<td><font color="blue"><b>&nbsp;${device.oldconf_code}</b></font></td>
					</tr>     
				    <tr>
				    	<td align="center">设备密级</td>
				    	<td><font color="blue"><b>&nbsp;${device.seclv_name}</b></font></td>
				    	<td align="center">保密编号</td>
				    	<td><font color="blue"><b>&nbsp;${device.conf_code}</b></font></td>
				    	<td align="center">名称型号</td>
				    	<td><font color="blue"><b>&nbsp;${device.book_model}</b></font></td>
					</tr>
					<tr>
						<td align="center">笔记本条码</td>
				    	<td><font color="blue"><b>&nbsp;${device.book_barcode}</b></font></td>
				    	<td align="center">外出情况</td>
				    	<td><font color="blue"><b>&nbsp;${device.outsideinfo}</b></font></td>
				    	<td align="center">系统时间</td>
				    	<td><font color="blue"><b>&nbsp;${device.book_os}</b></font></td>
					</tr>
					<tr>
				    	<td align="center">MAC地址</td>
				    	<td><font color="blue"><b>&nbsp;${device.book_mac}</b></font></td>
				    	<td align="center">存储涉密信息情况</td>
						<td><font color="blue"><b>&nbsp;${device.storage_secinfo}</b></font></td>
				    	<td align="center">联网情况</td>
				    	<td><font color="blue"><b>&nbsp;${device.internet_type}</b></font></td>
					</tr>    
				 	<tr>
						<td align="center">存放地点</td>
						<td><font color="blue"><b>&nbsp;${device.storage_location}</b></font></td>
						<td align="center">备注</td>
						<td colspan="3"><font color="blue"><b>&nbsp;${device.detail}</b></font></td>
					</tr>
				</c:if>	
				<tr><td colspan="6" id="step2">
					<table width="99%" border="0" cellspacing="0" cellpadding="0" align="left">
						<c:if test="${history != 'Y'}">
							<tr>
								<td>&nbsp;&nbsp;
									<input type="button" id="ChoiceButton" class="button_2003" value="分配可借的笔记本"  onclick="OpenUrl('${event.event_code}','${job.job_code}')";/>&nbsp;
								</td>
							</tr>
							<tr>
								<td>借用其他设备/介质 
									  <input type="checkbox" name="other" value="打印机"/>打印机
								      <input type="checkbox"  name="other" value="刻录机"/>刻录机
								      <input type="checkbox" name="other" value="U盘"/>U盘
								      <input type="checkbox" name="other" value="光盘"/>光盘
								  <br>
								  编号<br>
								  <textarea name="other_code" rows="4" cols="80"></textarea>	
								</td>
							</tr>
						</c:if>								
						<tr><td><textarea id="opinion2" rows="4" cols="100" readonly="readonly"></textarea></td></tr>
						<tr><td id="hidden2"></td></tr>
					</table>
				</td></tr>			
			</table>
		</td>
	</tr>
	<tr>
		<td align="center">开通端口检查</td>
		<td colspan="5" id="step3">
			<table width="99%" border="0" cellspacing="0" cellpadding="0" align="left">
				<tr><td><textarea id="opinion3" rows="4" cols="100" readonly="readonly"></textarea></td></tr>
				<tr><td id="hidden3"></td></tr>
			</table>
		</td>
	</tr>
	<tr>
		<td align="center">借用人确认</td>
		<td colspan="5" id="step4">
			<table width="99%" border="0" cellspacing="0" cellpadding="0" align="left">
				<tr><td><textarea id="opinion4" rows="4" cols="100" readonly="readonly"></textarea></td></tr>
				<tr><td id="hidden4"></td></tr>
			</table>
		</td>
	</tr>
	<tr>
		<td align="center">开通端口检查</td>
		<td colspan="5" id="step5">
			<table width="99%" border="0" cellspacing="0" cellpadding="0" align="left">
				<tr><td><textarea id="opinion5" rows="4" cols="100" readonly="readonly"></textarea></td></tr>
				<tr><td id="hidden5"></td></tr>
			</table>
		</td>
	</tr>
	<tr>
		<td align="center">保密处检查</td>
		<td colspan="5" id="step6">
			<table width="99%" border="0" cellspacing="0" cellpadding="0" align="left">
			<c:if test="${history != 'Y'}">
				<tr>
					<td>1、是否连接各种网络
						<input type="radio" name="check1" value="是"/>是
					    <input type="radio"  name="check1" value="否"/>否
					   核实情况<input type="text" name="check_info"/>
					</td>
				</tr>
				<tr>
					<td>2、是否连接各种设备
						<input type="radio" name="check2" value="是"/>是
					    <input type="radio"  name="check2" value="否"/>否
					   核实情况<input type="text" name="check_info"/>
					</td>
				</tr>
				<tr>
					<td>3、是否使用外来介质
						<input type="radio" name="check3" value="是"/>是
					    <input type="radio"  name="check3" value="否"/>否
					   核实情况<input type="text" name="check_info"/>
					</td>
				</tr>
				<tr>
					<td>4、是否安装各种软件
						<input type="radio" name="check4" value="是"/>是
					    <input type="radio"  name="check4" value="否"/>否
					   核实情况<input type="text" name="check_info"/>
					</td>
				</tr>
				<tr>
					<td>5、是否涉密数据输出
						<input type="radio" name="check5" value="是"/>是
					    <input type="radio"  name="check5" value="否"/>否
					   核实情况<input type="text" name="check_info"/>
					</td>
				</tr>
				<tr>
					<td>6、是否存储涉密信息
						<input type="radio" name="check6" value="是"/>是
					    <input type="radio"  name="check6" value="否"/>否
					   核实情况<input type="text" name="check_info"/>
					</td>
				</tr>
				<tr>
					<td>7、是否有特殊需求（如刻录、打印、三合一导入功能）
						<input type="radio" name="check7" value="是"/>是
					    <input type="radio"  name="check7" value="否"/>否
					   核实情况<input type="text" name="check_info"/>
					</td>
				</tr>
				<tr>
					<td>8、其他临时
					   核实情况<input type="text" name="check_info" size="50"/>
					</td>
				</tr>
				</c:if>
				<tr><td><textarea id="opinion6" rows="4" cols="100" readonly="readonly"></textarea></td></tr>
				<tr><td id="hidden6"></td></tr>
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
