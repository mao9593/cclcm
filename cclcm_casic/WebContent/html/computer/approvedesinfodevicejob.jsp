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
		var this_step = Number($("#listSize").val()) +1;
		
 		if(this_step == 3){
			if($("input:radio[name='check']:checked").val() == null){
				alert("请选择是否拆除存储部件");
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
	    <td colspan="6" align="center" class="title_box">信息设备报废审批表</td>
	</tr>
	<tr>
		<td colspan="6"><font color="red" size="2"><b>&nbsp;&nbsp;&nbsp;&nbsp;本流程只适用于保密报废，资产报废流程请咨询资产部门</b></font></td>
	</tr>
	<tr>
    	<td width="10%" align="center">申请用户 </td>
    	<td width="15%"><font color="blue"><b>&nbsp;${event.user_name}</b></font></td>
    	<td width="10%" align="center">申请用户部门 </td>
    	<td width="15%"><font color="blue"><b>&nbsp;${event.dept_name}</b></font></td>
    	<td width="10%" align="center">申请时间 </td>
    	<td width="15%"><font color="blue"><b>&nbsp;${event.apply_time_str}</b></font></td>
    </tr>
	<tr>	
    	<td align="center">设备类型</td>
		<td><font color="blue"><b>&nbsp;${device.device_type_name}</b></font></td>
		<td align="center">子类型</td>
		<td><font color="blue"><b>&nbsp;${device.info_type}</b></font></td>
    	<td align="center">设备状态</td>
    	<td><font color="blue"><b>&nbsp;${device.device_statues_name}</b></font></td>
	</tr>	
	<tr>
		<td align="center">责任人</td>
		<td><font color="blue"><b>&nbsp;${device.duty_user_name}</b></font></td>
		<td align="center">责任人部门</td>
		<td><font color="blue"><b>&nbsp;${device.duty_dept_name}</b></font></td>
		<td align="center">单位</td>
		<td><font color="blue"><b>&nbsp;${device.company}</b></font></td>
	</tr>  
	<tr>
		<td align="center">保密编号</td>
		<td><font color="blue"><b>&nbsp;${device.conf_code}</b></font></td>
		<td align="center">原保密编号</td>
		<td><font color="blue"><b>&nbsp;${device.oldconf_code}</b></font></td>
		<td align="center">设备密级</td>
		<td><font color="blue"><b>&nbsp;${device.seclv_name}</b></font></td>
	</tr>
 	<tr>		
		<td align="center">资产编号</td>
		<td><font color="blue"><b>&nbsp;${device.device_series}</b></font></td>
		<td align="center">品牌类型</td>
		<td><font color="blue"><b>&nbsp;${device.brand_type}</b></font></td>
		<td align="center">型号/序列号</td>
		<td><font color="blue"><b>&nbsp;${device.device_version}</b></font></td>
    </tr> 
 	<tr>
		<td align="center">设备用途</td>
		<td><font color="blue"><b>&nbsp;${device.device_usage}</b></font></td>
		<td align="center">联系电话</td>
    	<td><font color="blue"><b>&nbsp;${event.contact_num}</b></font></td> 
    	<td align="center">备注</td>
    	<td><font color="blue"><b>&nbsp;${event.summ}</b></font></td>
    </tr>
    <c:if test="${history eq 'Y'}">
		<tr>
	  		<td align="center">下一步骤审批人</td>
	  		<td colspan="5"><font color="blue"><b>&nbsp;${job.next_approver_name}</b></font></td>
	  	</tr>
  	</c:if>
	<tr>
		<td align="center">部门领导审批</td>
		<td colspan="5" id="step1">
			<table width="99%" border="0" cellspacing="0" cellpadding="0" align="left">
				<tr><td><textarea id="opinion1" rows="4" cols="100" readonly="readonly"></textarea></td></tr>
				<tr><td id="hidden1"></td></tr>
			</table>
		</td>
	</tr>
	<tr>
		<td align="center">任务分配</td>
			<td colspan="5" id="step2">
				<table width="99%" border="0" cellspacing="0" cellpadding="0" align="left">
					<tr><td><textarea id="opinion2" rows="4" cols="100" readonly="readonly"></textarea></td></tr>
					<tr><td id="hidden2"></td></tr>
				</table>
			</td>
	</tr>
	<tr>
		<td align="center">信息中心处理</td>
		<td colspan="5" id="step3">
			<table width="99%" border="0" cellspacing="0" cellpadding="0" align="left">
				<c:if test="${history != 'Y'}">
					<tr><font color="red">*</font>是否拆除存储部件
						<input type="radio" name="check" value="yes"/>是
						<input type="radio"  name="check" value="no"/>否
					</tr>
				</c:if>
				<tr><td><textarea id="opinion3" rows="4" cols="100" readonly="readonly"></textarea></td></tr>
				<tr><td id="hidden3"></td></tr>
			</table>
		</td>
	</tr>
	<tr>
		<td align="center">保密处回收确认</td>
		<td colspan="5" id="step4">
			<table width="99%" border="0" cellspacing="0" cellpadding="0" align="left">
				<tr><td><textarea id="opinion4" rows="4" cols="100" readonly="readonly"></textarea></td></tr>
				<tr><td id="hidden4"></td></tr>
			</table>
		</td>
	</tr>
	<c:if test="${history != 'Y'}">
	<tr>
  		<td align="center" id="selApprover1">选择审批人</td>
  		<td colspan="5" id="selApprover2">
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
