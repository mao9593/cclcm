<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<link rel="stylesheet" type="text/css" href="${ctx}/_css/displaytag.css" media="screen"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/_css/css200603.css"  media="screen"/>
    <script language="javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>
	<script language="JavaScript" src="${ctx}/_script/function.js"></script>
	<script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
	<script language="javascript" src="${ctx}/_script/casic304_ajax.js"></script>
	<script language="javascript" src="${ctx}/_script/casic304_common_bm.js"></script>
<script>
<!--
$(document).ready(function(){
	if('${event.job_status}' != ""){
		prepareBizApproval("${process.step_dept}","${process.step_role}","${process.step_dept_name}","${process.step_role_name}","${ctx}");
	}
	viewOpinion("");//判断审批到哪一步
});

//-->
</script>
</head>
<body oncontextmenu="self.event.returnValue=false">
<table width="95%" border="1" cellspacing="0" cellpadding="0" align="center" class="table_box">
<input type="hidden" id="listSize" name="listSize" value="${listSize}"/> 
<input type="hidden" id="opinion_all" name="opinion_all" value="${opinion_all}"/> 
<input type="hidden" id="type" name="type" value="${type}"/>
	<tr>
	    <td colspan="6" class="title_box">查看用户因私出国作业详情</td>
	</tr>
	<tr>
    	<td width="10%" align="center">姓名 </td>
    	<td width="23%"><font color="blue"><b>&nbsp;${event.user_name}</b></font></td>
    	<td width="10%" align="center">身份证号 </td>
    	<td width="23%"><font color="blue"><b>&nbsp;${applyinfo.idcard}</b></font></td> 
    	<td width="10%" align="center">当前状态 </td>
    	<td width="23%"><font color="red"><b>&nbsp;${event.job_status_name}&nbsp;/&nbsp;${event.abroad_status_name}</b></font></td>      	  		
	</tr>
	<tr>
	    <td width="10%" align="center">出生日期 </td>
    	<td width="23%"><font color="blue"><b>&nbsp;${applyinfo.base_birthday}</b></font></td>
    	<td width="10%" align="center">文化程度 </td>
    	<td width="23%"><font color="blue"><b>&nbsp;${applyinfo.edu_education}</b></font></td>
    	<td width="10%" align="center">职务 </td>
    	<td width="23%"><font color="blue"><b>&nbsp;${bmUser.job_techpost}</b></font></td>
    </tr>	
    <tr>	
    	<td align="center">政治面貌 </td>
    	<td><font color="blue"><b>&nbsp;${applyinfo.base_politics}</b></font></td>	
    	<td align="center">&nbsp;婚姻状况 </td>
	    	<td><font color="blue"><b>&nbsp;
    			<c:if test="${applyinfo.marital_status == '0'}">未婚</c:if>
    			<c:if test="${applyinfo.marital_status == '1'}">已婚</c:if>
    			<c:if test="${applyinfo.marital_status == '2'}">离异</c:if>
    	</b></font></td>	
    	<td align="center">手机号码 </td>
    	<td><font color="blue"><b>&nbsp;${applyinfo.com_telephone}</b></font></td>	
     </tr>		
     <tr>	
        <td align="center">家庭住址 </td>
    	<td  colspan="5"><font color="blue"><b>&nbsp;${applyinfo.com_address}</b></font></td>
      </tr>
      <tr>
  		<td align="center">涉密人员类别 </td>
    	<td><font color="blue"><b>&nbsp;${applyuser.security_name}</b></font></td>
    	<td width="10%" align="center">用户单位 </td>
    	<td width="23%"><font color="blue"><b>${curUser.dept_name}</b></font></td>	
    	<td>&nbsp;</td>
    	<td>&nbsp;</td>
	</tr>	
	<tr>
    	<td align="center">申请时间 </td>
    	<td><font color="blue"><b>&nbsp;${event.apply_time_str}</b></font></td>	
	   	<td align="center">计划外出时间</td>
	   	<td><font color="blue"><b>&nbsp;${event.go_time_str}</b></font></td>	
	   	<td align="center">计划返回时间</td>
	   	<td><font color="blue"><b>&nbsp;${event.back_time_str}</b></font></td>	  	
	</tr>
	<tr>
		<td align="center">目的地或途径国家</td>
	   	<td colspan="5"><font color="blue"><b>&nbsp;${event.termini}</b></font></td>			 	
	</tr>	
	<tr>				
	   	<td align="center">历史出境情况</td>
	   	<td colspan="5"><font color="blue"><b>&nbsp;${event.journey}</b></font></td>    	
  	</tr>			
	<tr>
		<td align="center">护照信息</td>
		<td><font color="blue"><b>&nbsp;${applyinfo.passport_info}</b></font></td>
		<td align="center">他国绿卡或永久居留证</td>
		<td><font color="blue"><b>&nbsp;${applyinfo.resident_card}</b></font></td>
		<td align="center">&nbsp;涉密类别</td>
		<td><font color="blue"><b>&nbsp;${applyinfo.sec_category}</b></font></td>
	</tr> 		
	<tr>
	   	<td align="center">事由</td>
	   	<td colspan="5"><font color="blue"><b>&nbsp;${event.reason}</b></font></td>
	</tr>
	<tr>
  		<td align="center">&nbsp;个人承诺</td>
  		<td colspan="5"><font color="blue"><b>&nbsp;${event.summ}</b></font></td>	
	</tr>
  	<tr>
			<td align="center">申请部门领导意见</td>
			<td colspan="3" id="step1">
				<table width="99%" border="0" cellspacing="0" cellpadding="0" align="left">
					<tr><td>
						<textarea id="opinion1" rows="4" cols="100" readonly="readonly" ></textarea>
					</td></tr>
					<tr><td id="hidden1"></td></tr>
			</table>
	  </tr>
	  <tr>
			<td align="center">人力资源部意见</td>
			<td colspan="3" id="step2">
				<table width="99%" border="0" cellspacing="0" cellpadding="0" align="left">
					<tr><td><textarea id="opinion2" rows="4" cols="100" readonly="readonly"></textarea></td></tr>
					<tr><td id="hidden2"></td></tr>
			</table>
			</td>
	   </tr>
	   <tr>
			<td align="center">保密处意见</td>
			<td colspan="3" id="step3">
				<table width="99%" border="0" cellspacing="0" cellpadding="0" align="left">
					<tr><td><textarea id="opinion3" rows="4" cols="100" readonly="readonly"></textarea></td></tr>
					<tr><td id="hidden3"></td></tr>
				</table>
		</tr>
		<tr>
			<td align="center">保密委意见</td>
			<td colspan="3" id="step4">
				<table width="99%" border="0" cellspacing="0" cellpadding="0" align="left">
					<tr><td><textarea id="opinion4" rows="4" cols="100" readonly="readonly"></textarea></td></tr>
					<tr><td id="hidden4"></td></tr>
				</table>
		</tr>
		<tr>
			<td align="center">办公室盖章</td>
			<td colspan="3" id="step5">
				<table width="99%" border="0" cellspacing="0" cellpadding="0" align="left">
					<tr><td><textarea id="opinion5" rows="4" cols="100" readonly="readonly"></textarea></td></tr>
					<tr><td id="hidden5"></td></tr>
				</table>
		</tr>
		<tr>
			<td align="center">人力资源部保密教育提醒</td>
			<td colspan="3" id="step6">
				<table width="99%" border="0" cellspacing="0" cellpadding="0" align="left">
					<tr><td><textarea id="opinion6" rows="4" cols="100" readonly="readonly">提醒人</textarea></td></tr>
					<tr><td id="hidden6"></td></tr>
				</table>
		</tr>
		<tr>
			<td align="center">申请人确认</td>
			<td colspan="3" id="step7">
				<table width="99%" border="0" cellspacing="0" cellpadding="0" align="left">
					<tr><td><textarea id="opinion7" rows="4" cols="100" readonly="readonly"></textarea></td></tr>
					<tr><td id="hidden7"></td></tr>
				</table>
		</tr>
		<tr>
			<td align="center">人力资源部审查意见</td>
			<td colspan="3" id="step8">
				<table width="99%" border="0" cellspacing="0" cellpadding="0" align="left">
					<tr>
						<td align="left">护照是否已交回
						<input type="radio" name="p_chk" value="yes" />交回
						<input type="radio" name="p_chk" value="no" />未交回
						</td>
			        </tr>
			        <tr><td id="hidden8"></td></tr>
				</table>
			</td>
		  </tr>
		  <tr>
			<td align="center">保密处确认出境汇报情况</td>
			<td colspan="3" id="step9">
				<table width="99%" border="0" cellspacing="0" cellpadding="0" align="left">
					<tr><td><textarea id="opinion9" rows="4" cols="100" readonly="readonly"></textarea></td></tr>
					<tr><td id="hidden9"></td></tr>
				</table>		
		  </tr>	  
		  <tr>
			<td align="center">备注</td>
			<td colspan="3" id="step10">
				<table width="99%" border="0" cellspacing="0" cellpadding="0" align="left">
					<tr><td><textarea id="opinion10" rows="4" cols="100" readonly="readonly"></textarea></td></tr>
					<tr><td id="hidden10"></td></tr>
				</table>		
		  </tr>	      
  	<tr>
    <td colspan=6 align=center> 
		<input class="button_2003" type="button" value="返回" onClick="javascript:history.go(-1);">&nbsp;
    </td>
  </tr>
</table>
</body>
</html>
