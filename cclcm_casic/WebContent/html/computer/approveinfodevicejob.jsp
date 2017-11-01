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
		
		$("#page_print").click(function(){
		$(".printContent").jqprint();
	})		
	});

	function chk(){
		subOpinion();//提交时将审批意见复制给opinion
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

</script>
</head>
<div class="printContent">
<body oncontextmenu="self.event.returnValue=false">
<table width="95%" border="1" cellspacing="0" cellpadding="0" align="center" class="table_box">
<form action="${ctx}/computer/approveinfodevicejob.action" method="post" >
	<input type="hidden" name="next_approver" id="next_approver"/>
	<input type="hidden" name="job_code" value="${job.job_code}"/>
	<input type="hidden" id="listSize" name="listSize" value="${listSize}"/> 
	<input type="hidden" id="opinion_all" name="opinion_all" value="${opinion_all}"/> 
	<input type="hidden" name="event_code" id="event_code" value="${event.event_code}"/> 
	<input type="hidden" id="opinion" name="opinion"/> 
	<tr>
	    <td colspan="6" align="center" class="title_box">新增信息设备审批表</td>
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
    	<td align="center">设备类型</td>
    	<td><font color="blue"><b>&nbsp;${event.device_type_name}</b></font></td>
    	<td align="center">子类型</td>
		<td><font color="blue"><b>&nbsp;${event.info_type}</b></font></td>  
	</tr>  
	<tr>
		<td align="center">责任人</td>
    	<td><font color="blue"><b>&nbsp;${event.duty_user_name}</b></font></td>
    	<td align="center">责任部门</td>
		<td><font color="blue"><b>&nbsp;${event.duty_dept_name}</b></font></td>
		<td align="center">单位</td>
		<td><font color="blue"><b>&nbsp;${temp.company}</b></font></td>
	</tr>
 	<tr>
		<td align="center">品牌类型</td>
		<td><font color="blue"><b>&nbsp;${temp.brand_type}</b></font></td> 
		<td align="center">型号/序列号</td>
		<td><font color="blue"><b>&nbsp;${temp.device_version}</b></font></td> 
    	<td align="center">保密编号</td>
    	<td><font color="blue"><b>&nbsp;${temp.conf_code}</b></font></td> 
    </tr> 
    <tr>     
		<td align="center">资产编号</td>
		<td><font color="blue"><b>&nbsp;${temp.device_series}</b></font></td>
    	<td align="center">设备密级</td>
    	<td><font color="blue"><b>&nbsp;${temp.seclv_name}</b></font></td> 
		<td align="center">设备用途</td>
		<td><font color="blue"><b>&nbsp;${temp.device_usage}</b></font></td> 
    </tr>  
 	 <tr>
 	 	<td align="center">原保密编号</td>
    	<td><font color="blue"><b>&nbsp;${temp.oldconf_code}</b></font></td>
    	<td align="center">采购时间</td>
    	<td><font color="blue"><b>&nbsp;${temp.purchase_time_str}</b></font></td> 
		<td align="center">启用时间</td>
		<td><font color="blue"><b>&nbsp;${temp.use_time_str}</b></font></td>
		<%-- <td align="center">序列号</td>
		<td><font color="blue"><b>&nbsp;${temp.serial_num}</b></font></td> --%> 
    </tr> 
    <tr>
    	<c:if test="${event.device_type != '5' && event.device_type != '6'}">		
			<td align="center">安装地点</td>
			<td colspan="5"><font color="blue"><b>&nbsp;${temp.location}</b></font></td>
		</c:if>
		<c:if test="${event.device_type == '5'}">
			<td align="center">安装地点</td>
			<td><font color="blue"><b>&nbsp;${temp.location}</b></font></td> 
			<td align="center">检测证书名称</td>
			<td><font color="blue"><b>&nbsp;${temp.cert_name}</b></font></td> 
			<td align="center">证书编号</td>
			<td><font color="blue"><b>&nbsp;${temp.cert_num}</b></font></td>
		</c:if>
		<c:if test="${event.device_type == '6'}"> 
			<td align="center">安装地点</td>
			<td><font color="blue"><b>&nbsp;${temp.location}</b></font></td>
			<td align="center">内存/容量</td>
			<td colspan="3"><font color="blue"><b>&nbsp;${temp.memory}</b></font></td> 
    	</c:if> 
    </tr>
    <tr>
    	<td align="center">设备状态</td>
    	<td><font color="blue"><b>&nbsp;${temp.device_statues_name}</b></font></td>
    	<td align="center">备注</td>
    	<td colspan="3"><font color="blue"><b>&nbsp;${event.summ}</b></font></td>
    </tr>
    <c:if test="${history eq 'Y'}">
		<tr>
	  		<td align="center">下一步骤审批人</td>
	  		<td colspan="5"><font color="blue"><b>&nbsp;${job.next_approver_name}</b></font></td>
	  	</tr>
  	</c:if>
	<tr>
		<td align="center">部门审批</td>
		<td colspan="5" id="step1">
			<table width="99%" border="0" cellspacing="0" cellpadding="0" align="left">
				<tr><td><textarea id="opinion1" rows="4" cols="100" readonly="readonly"></textarea></td></tr>
				<tr><td id="hidden1"></td></tr>
			</table>
		</td>
	</tr>
	<tr>
		<td align="center">保密处发放及信息录入</td>
		<td colspan="5" id="step2">
			<table width="99%" border="0" cellspacing="0" cellpadding="0" align="left">
			<c:if test="${history != 'Y'}">
			 	<tr>
					<td align="center">品牌类型</td>
					<td><input type="text" name="brand_type" id="brand_type" value="${temp.brand_type}"/></td>
					<td align="center">型号/序列号</td>
					<td><input type="text" name="device_version" id="device_version" value="${temp.device_version}"/></td>
					<td align="center">资产编号</td>
					<td><input type="text" name="device_series" id="device_series" value="${temp.device_series}"/></td>
			    </tr> 
			    <tr>     
			    	<td align="center">设备密级</td>
			    	<td>
						<select name="device_seclv" id="device_seclv">
							<option value="">--请选择--</option>
							<s:iterator value="#request.seclvList" var="seclv">
								<option value="${seclv.seclv_code}" <c:if test="${temp.device_seclv == seclv.seclv_code}">selected</c:if> >${seclv.seclv_name}</option>
							</s:iterator>
						</select>
					<td align="center">设备用途</td>
					<td><input type="text" name="device_usage" id="device_usage" value="${temp.device_usage}"/></td>
					<td align="center">原保密编号</td>
					<td><input type="text" name="oldconf_code" id="oldconf_code" value="${temp.oldconf_code}"/></td>
			    </tr>  
			 	 <tr>
			    	<td align="center">采购时间</td>
			    	<td><input type="text" id="purchase_time" name="purchase_time" onclick="WdatePicker()" class="Wdate" value="${temp.purchase_time_str}"/></td>
					<td align="center">启用时间</td>
					<td><input type="text" id="use_time" name="use_time" onclick="WdatePicker()" class="Wdate" value="${temp.use_time_str}"/></td>
					<td align="center">安装地点</td>
					<td><input type="text" name="location" id="location" value="${temp.location}"/></td>
			    </tr>
			    <tr>			
			    	<td align="center">单位</td>
					<td>
						<select name="company" id="company">
							<option value="">--请选择--</option>
							<option value="总部" <c:if test="${temp.company == '总部'}">selected</c:if>>总部</option>
							<option value="24所" <c:if test="${temp.company == '24所'}">selected</c:if>>24所</option>
							<option value="26所" <c:if test="${temp.company == '26所'}">selected</c:if>>26所</option>
							<option value="44所" <c:if test="${temp.company == '44所'}">selected</c:if>>44所</option>
						</select>
					</td>   	
				    <c:if test="${event.device_type == '5'}">
						<td align="center">检测证书名称</td>
						<td><input type="text" name="cert_name" id="cert_name" value="${temp.cert_name}"/></td>
						<td align="center">证书编号</td>
						<td><input type="text" name="cert_num" id="cert_num" value="${temp.cert_num}"/></td>
				    </c:if>
				    <c:if test="${event.device_type == '6'}"> 
						<td align="center">内存/容量</td>
						<td><input type="text" name="memory" id="memory" value="${temp.memory}"/></td>
				    </c:if>
			    </tr>    	
			</c:if>
				<tr><td colspan="6"><textarea id="opinion2" rows="4" cols="100" readonly="readonly"></textarea></td></tr>
				<tr><td colspan="6" id="hidden2"></td></tr>
			</table>
		
		</td>
	</tr>
	<c:if test="${event.device_type == '5' || event.device_type == '6'}">
		<tr>
			<td align="center">用户接受确认</td>
			<td colspan="5" id="step3">
				<table width="99%" border="0" cellspacing="0" cellpadding="0" align="left">
					<tr><td><textarea id="opinion3" rows="4" cols="100" readonly="readonly"></textarea></td></tr>
					<tr><td id="hidden3"></td></tr>
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
