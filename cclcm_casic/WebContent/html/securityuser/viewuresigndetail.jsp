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

$(document).ready(function(){
	if('${event.job_status}' != ""){
		prepareBizApproval("${process.step_dept}","${process.step_role}","${process.step_dept_name}","${process.step_role_name}","${ctx}");
	}
	viewOpinion();//判断审批到哪一步
});
</script>
</head>
<body oncontextmenu="self.event.returnValue=ture">
<table width="95%" border="1" cellspacing="0" cellpadding="0" align="center" class="table_box">
<input type="hidden" id="listSize" name="listSize" value="${listSize}"/> 
<input type="hidden" id="opinion_all" name="opinion_all" value="${opinion_all}"/> 

	<tr>
	    <td colspan="6" class="title_box">查看涉密人员脱密（离岗）作业详情</td>
	</tr>
	<tr>
    	<td width="12%" align="center">发起人&nbsp;/&nbsp;部门 </td>
    	<td width="21%" align="center"><font color="blue"><b>&nbsp;${event.user_name}&nbsp;/&nbsp;${event.dept_name}</b></font></td>
    	<td width="12%" align="center">申请时间 </td>
    	<td width="21%" align="center"><b>&nbsp;${event.apply_time_str}</b></font></td>
    	<td width="12%" align="center">审批状态 </td>
    	<td width="21%" align="center"><font color="red"><b>&nbsp;${event.job_status_name}&nbsp;/&nbsp;${event.resign_status_name}</b></font></td>
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
    	<td width="21%" align="center"><font color="blue"><b>&nbsp;${bmUser.edu_education}</b></font></td>
    	<td width="12%" align="center">&nbsp;&nbsp;政治面貌</td>
    	<td width="21%" align="center"><font color="blue"><b>&nbsp;${bmUser.base_politics}</b></font></td>
    </tr>
    <tr>	
    	<td width="12%" align="center">&nbsp;现从事岗位 </td>
    	<td width="21%" align="center"><font color="blue"><b>&nbsp;${resignUser.post_name}</b></font></td> 
    	<td width="12%" align="center">&nbsp;&nbsp;人员涉密等级</td>
    	<td width="21%" align="center"><font color="blue"><b>&nbsp;${resignUser.security_name}</b></font></td>
    	<td width="12%" align="center">&nbsp;&nbsp;联系电话</td>
    	<td width="21%" align="center"><font color="blue"><b>&nbsp;${bmUser.com_telephone}</b></font></td>
    </tr>	
    <tr>	
    	<td width="12%" align="center">脱密（离岗）原因</td>
		<td colspan="5" align="center"><font color="red"><b>&nbsp;${event.reason_name}</b></font></td>
    </tr>
	<tr>
 		<td align="center">&nbsp;<font color="red">*</font>本人承诺</td>
  	<td colspan="5">
  		   <table width="95%" border="0" cellspacing="0" cellpadding="0" align="left">
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
					<tr><td><textarea id="opinion3" rows="4" cols="100" readonly="readonly"></textarea></td></tr>
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
					<tr><td><textarea id="opinion5" rows="4" cols="100" readonly="readonly"></textarea></td></tr>
					<tr><td id="hidden5"></td></tr>
				</table>
			</td>
	</tr>
  	
    <td colspan=5 align=center> 
		<input class="button_2003" type="button" value="返回" onClick="javascript:history.go(-1);">&nbsp;
    </td>
  </tr>
</table>
</body>
</html>
