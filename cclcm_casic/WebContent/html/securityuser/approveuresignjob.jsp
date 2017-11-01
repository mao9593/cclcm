<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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
function viewEventDetail(event_code){
	go("${ctx}/securityuser/viewuresigndetail.action?event_code="+escape(event_code));
}
function chk(){
    subOpinion();//提交时将审批意见复制给opinion
    
    var this_step = Number($("#listSize").val()) +1;
	if(this_step == 3){
	   if($("#start_time").val() == ""){
			alert("请填写脱密期开始时间");
			$("#start_time").focus();
			return false;
		}
		else if($("#end_time").val() == ""){
			alert("请填写脱密期结束时间");
			$("#end_time").focus();
			return false;
		}
	}
	if(this_step == 5){
	   if($("#accept_name1").val() == ""){
			alert("请填写接收人");
			$("#accept_name1").focus();
			return false;
		}else if($("#accept_name2").val() == ""){
			alert("请填写接收人");
			$("#accept_name2").focus();
			return false;
		}else if($("#accept_name3").val() == ""){
			alert("请填写接收人");
			$("#accept_name3").focus();
			return false;
		}else if($("#accept_name4").val() == ""){
			alert("请填写接收人");
			$("#accept_name4").focus();
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
function changeApproved(value){
	if(value == "true"){
		$("#opinion").val("同意");
	}else if(value == "false"){
		$("#opinion").val("不同意");
	}
}
</script>
</head>
<div class="printContent">
<body oncontextmenu="self.event.returnValue=false">
<form action="${ctx}/securityuser/approveuresignjob.action" method="post" >
<input type="hidden" name="next_approver" id="next_approver"/>
<input type="hidden" name="job_code" value="${job.job_code}"/>
<input type="hidden" id="listSize" name="listSize" value="${listSize}"/> 
<input type="hidden" id="opinion_all" name="opinion_all" value="${opinion_all}"/> 
<input type="hidden" name="event_code" id="event_code" value="${event.event_code}"/> 
<input type="hidden" id="opinion" name="opinion"/> 
<input type="hidden" id="types" name="types" value="${types}"/>
<table width="95%" border="1" cellspacing="0" cellpadding="0" align="center" class="table_box">
	<tr>
	    <td colspan="6" align="center" class="title_box">涉密人员脱密(离岗)审批表</td>
	</tr>
	<tr>
    	<td width="12%" align="center">发起人&nbsp;/&nbsp;部门 </td>
    	<td width="21%" align="center"><font color="blue"><b>&nbsp;${event.user_name}&nbsp;/&nbsp;${event.dept_name}</b></font></td>
    	<td width="12%" align="center">申请时间 </td>
    	<td width="21%" align="center"><font color="blue"><b>&nbsp;${event.apply_time_str}</b></font></td>
    	<td width="12%" align="center">审批状态 </td>
    	<td width="21%" align="center"><font color="red"><b>&nbsp;${event.job_status_name}&nbsp;</b></font></td>
	</tr>
	<tr>
		<td width="12%" align="center">申请人&nbsp;/&nbsp;部门 </td>
    	<td width="21%" align="center"><font color="blue"><b>&nbsp;${event.resign_user_name}&nbsp;/&nbsp;${event.resign_dept_name}</b></font></td>
		<td width="12%" align="center">&nbsp;&nbsp;性别</td>
    	<td width="21%" align="center"><font color="blue"><b>&nbsp;${bmUser.base_sex_name}</b></font></td> 
    	<td width="12%" align="center">&nbsp;&nbsp;出生年月</td>
    	<td width="21%" align="center"><font color="blue"><b>&nbsp;${bmUser.base_birthday}</b></font></td>
	</tr>
	<tr>
    	<td width="12%" align="center">&nbsp;职务/职称 </td>
    	<td width="21%" align="center"><font color="blue"><b>&nbsp;${bmUser.job_techpost}/${bmUser.job_techtitle}</b></font></td>
    	<td width="12%" align="center">&nbsp;&nbsp;学历</td>
    	<td width="21%" align="center"><font color="blue"><b>&nbsp;${bmUser.edu_education_name}</b></font></td>
    	<td width="12%" align="center">&nbsp;&nbsp;政治面貌</td>
    	<td width="21%" align="center"><font color="blue"><b>&nbsp;${bmUser.base_politics_name}</b></font></td>
    </tr>
    <tr>	
    	<td align="center">&nbsp;岗位类别</td>
    	<td align="center"><font color="blue"><b>&nbsp;${event.post_name_before}</b></font></td>
    	<td align="center">&nbsp;&nbsp;人员涉密等级</td>
		<td align="center"><font color="blue"><b>&nbsp;${resignUser.security_name}</b></font></td>
    	<td align="center">&nbsp;&nbsp;联系电话</td>
		<td align="center"><font color="blue"><b>&nbsp;${bmUser.com_telephone}</b></font></td>
	</tr>
	<tr>
		<td align="center">名下计算机数量</td>
    	<td align="center"><font color="blue"><b>&nbsp;${computer_num}</b></font></td>
    	<td align="center">名下设备数量</td>
    	<td align="center"><font color="blue"><b>&nbsp;${device_num}</b></font></td>
    	<td align="center">名下介质数量</td>
    	<td align="center"><font color="blue"><b>&nbsp;${media_num}</b></font></td>
	</tr>
    <tr>
		<td align="center">脱密（离岗）原因</td>
    	<td align="center"><font color="blue"><b>&nbsp;${event.reason_name}</b></font></td>
	    <c:if test="${event.reason == '1'}"> 
			<td align="center">去向</td>
			<td align="center"><font color="blue"><b>&nbsp;${event.reason_name}</b></font></td>   
		</c:if>
	    <c:if test="${event.reason == '2'}"> 
			<td align="center">去向</td>
			<td align="center"><font color="blue"><b>&nbsp;${event.resign_others}</b></font></td>   
		</c:if>
		<c:if test="${event.reason == '3'}">
			<td align="center">去向</td>
			<td align="center"><font color="blue"><b>&nbsp;新部门:${event.dept_name_after},&nbsp;新岗位:${event.post_name_after}</b></font></td>   
		</c:if>
		<c:if test="${event.reason == '4'}"> 
			<td align="center">去向</td>
			<td align="center"><font color="blue"><b>&nbsp;${event.resign_others}</b></font></td>   
		</c:if>
		<td align="center">&nbsp;涉密资料总数</td>
    	<td align="center"><font color="blue"><b>&nbsp;${all_total}</b></font></td> 
    </tr>
	<tr>
 		<td align="center">&nbsp;<font color="red">*</font>本人承诺</td>
  		<td colspan="5">
  		   <table width="99%" border="0" cellspacing="0" cellpadding="0" align="left">
  		     <tr>
		     	<td colspan="5"><textarea name="summ" rows="14" cols="100" id="summ" readonly>本人了解有关保密法规制度，知悉应当承当的保密义务和法律责任，按规定接受脱密期管理。

本人承诺如下：
1、认真遵守国家保密法律法规和规章制度，履行保密义务；
2、不以任何方式泄露所接触和知悉的国家涉密和公司商业机密；
3、已全部清退个人持有的各类国家和公司涉密纸质和电子载体及内部资料；
4、未经公司审查批准，不发表涉及未公开工作内容的文章、著述；
5、不利用在所工作期间所知悉的国家秘密和公司秘密与公司竞争，或为这种竞争服务；
6、离岗后三年内，不到境外驻华机构、组织或者外商独资企业工作，不为境外组织或者人员提供劳务、咨询或者其他服务；
7、在脱密期内，自愿服从有关部门的保密监管；
8、脱密期满仍有保守所知悉的国家秘密和公司秘密的义务；
9、违反上述承诺，自愿承担经济和法律责任。</textarea></td>
		     </tr>	
	  		   <tr>
	  		   <td align="center"> 申请人签名</td><td><font color="blue"><b>&nbsp;${event.signname}</td>
			       <td align="center"> 时&nbsp;&nbsp;间</td><td><font color="blue"><b>&nbsp;${event.sign_time}</td>
	  		   </tr>
  		   </table>	
  		 </td>
	</tr>
    <c:if test="${history eq 'Y'}">
		<tr>
	  		<td align="center">下一步骤审批人</td>
	  		<td colspan="5"><font color="blue"><b>&nbsp;${job.next_approver_name}</b></font></td>
	  	</tr>
  	</c:if>
  	<tr>
		<td align="center">部门审核意见</td>
			<td colspan="5" id="step1">
				<table width="99%" border="0" cellspacing="0" cellpadding="0" align="left">
					<tr><td><textarea id="opinion1" rows="4" cols="100" readonly="readonly"></textarea></td></tr>
					<tr><td id="hidden1"></td></tr>
				</table>
			</td>
	</tr>
	<tr>
		<td align="center">人力资源部审批</td>
			<td colspan="5" id="step2">
				<table width="99%" border="0" cellspacing="0" cellpadding="0" align="left">
					<tr><td><textarea id="opinion2" rows="4" cols="100" readonly="readonly"></textarea></td></tr>
					<tr><td id="hidden2"></td></tr>
				</table>
			</td>
	</tr>
	<tr>
		<td align="center">保密处审批</td>
		<td colspan="5" id="step3">
		  <table width="99%" border="0" cellspacing="0" cellpadding="0" align="left">
		  <c:if test="${history != 'Y'}">
		  <tr>
	           <td width="25%" align="center"><font color="red">*</font>&nbsp;脱密期：从</td>
	           <td width="21%" >
	               <input type="text" id="start_time" name="start_time" onclick="WdatePicker()" class="Wdate" size="15" value="${start_time}" readonly="readonly"/>
	           </td>
			   <td width="20%"  align="center"> &nbsp;至</td>
			   <td>
			     <input type="text" id="end_time" name="end_time" onclick="WdatePicker()" class="Wdate" size="15" value="${end_time}" readonly="readonly"/>
			   </td>
  		 </tr>
  		 </c:if>
		 <tr>
		    <td colspan="5"><textarea id="opinion3" rows="4" cols="100" readonly="readonly"></textarea></td></tr>		
		      <tr><td id="hidden3"></td></tr>
		</table>
	   </td>
	</tr>
	<tr>
		<td align="center">保密委审批</td>
			<td colspan="5" id="step4">
				<table width="99%" border="0" cellspacing="0" cellpadding="0" align="left">
					<tr><td><textarea id="opinion4" rows="4" cols="100" readonly="readonly"></textarea></td></tr>
					<tr><td id="hidden4"></td></tr>
				</table>
			</td>
	</tr>
	<tr>
		<td align="center">办理交接手续</td>
			<td colspan="5" id="step5">
				<table width="99%" border="0" cellspacing="0" cellpadding="0" align="left">
				<c:if test="${history != 'Y'}">
					<tr>
			           <td colspan="2" width="15%" align="center">申请人办理交接事项 </td>
		            </tr>
				 	<tr>
			           <td width="15%" align="center">1、 涉密计算机（留存部门） </td>
			           <td width="15%" ><font color="red">*</font>接收人：
			               <input type="text" id="accept_name1" name="accept_name1" value="" />
			           </td>
		            </tr>
		           <tr>
			           <td width="15%" align="center">2、 上内网使用的“UKey”（交信息化管理部门有关人员） </td>
			           <td width="15%" ><font color="red">*</font>接收人：
			               <input type="text" id="accept_name2" name="accept_name2" value="" />
			           </td>
		           </tr>
		           <tr>
			           <td width="15%" align="center">3、涉密载体（资料） </td>
			           <td width="15%" ><font color="red">*</font>接收人：
			               <input type="text" id="accept_name3" name="accept_name3" value="" />
			           </td>
		           </tr>
		           <tr>
			           <td width="15%" align="center">4、其他涉密资料 </td>
			           <td width="15%" ><font color="red">*</font>接收人：
			               <input type="text" id="accept_name4" name="accept_name4" value="" />
			           </td>
		  		   </tr>
		  		 </c:if>
					<tr><td colspan="5"><textarea id="opinion5" rows="4" cols="100" readonly="readonly"></textarea></td></tr>
					<tr><td id="hidden5"></td></tr>
				</table>
			</td>
	</tr>
  	<tr>
		<td align="center">应用系统权限变更</td>
			<td colspan="5" id="step6">
				<table width="99%" border="0" cellspacing="0" cellpadding="0" align="left">
					<tr><td><textarea id="opinion6" rows="4" cols="100" readonly="readonly"></textarea></td></tr>
					<tr><td id="hidden6"></td></tr>
				</table>
			</td>
	</tr>
	<tr>
		<td align="center">保密处备案</td>
			<td colspan="5" id="step7">
				<table width="99%" border="0" cellspacing="0" cellpadding="0" align="left">
					<tr><td><textarea id="opinion7" rows="4" cols="100" readonly="readonly"></textarea></td></tr>
					<tr><td id="hidden7"></td></tr>
				</table>
			</td>
	</tr>
	<c:if test="${history != 'Y'}">
	<tr>
  		<td align="center" id="selApprover1">选择审批人</td>
  		<td id="selApprover2" colspan="4">
  			<table width="300" border="0" cellspacing="0" cellpadding="0" align="left" class="table_box_border_empty">
				<tr>
					<td id="allApprovers">
						<SELECT ondblclick="add_True('next_approver_all','next_approver_sel');" style="WIDTH: 100px" multiple="true" size="8" id="next_approver_all">
							<c:forEach var="item" items="${userList}" varStatus="status">
								<option value="${item.user_iidd}">${item.user_name}</option>
							</c:forEach>
						</SELECT>
					</td>
					<td align="center" valign="middle">
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
</body>
</div>
</html>
