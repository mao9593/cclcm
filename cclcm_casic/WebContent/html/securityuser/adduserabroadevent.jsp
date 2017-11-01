<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<link rel="stylesheet" type="text/css" href="${ctx}/_css/displaytag.css" media="screen"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/_css/css200603.css"  media="screen"/>
    <link href="${ctx}/_script/calendar2/calendar-blue.css" type="text/css" rel="stylesheet"/>
	<script language="JavaScript" src="${ctx}/_script/function.js"></script>
	<script language="javascript" src="${ctx}/_script/calendar2/calendar.js"></script>
	<script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
 	<script language="javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>	
 	<script language="javascript" src="${ctx}/_script/casic304_ajax.js"></script>
 	<script language="JavaScript" src="${ctx}/_script/common.js"></script>
 	<script language="javascript" src="${ctx}/_script/casic304_common_bm.js"></script>
 	<script language="javascript" src="${ctx}/_script/My97DatePicker/WdatePicker.js"></script>
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

	function selectRecvUser(word){
		var url = "${ctx}/basic/getfuzzyuser.action?source=CR";
		if(word != ""){
			callServer1(url,"fuzzy="+word);
		}else{
			document.getElementById("allOptions").innerHTML="";
		}
	}
	function updateResult(){
		if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
				if(xmlHttp.responseText.toString().length > 154){
					document.getElementById("allOptions").innerHTML=xmlHttp.responseText;
				}else{
					document.getElementById("allOptions").innerHTML="";
				}
			}else{
				document.getElementById("allOptions").innerHTML="";
			}
	}

	function chk()
	{	
		if($("#termini").val().trim() == ""){
			alert("请填写目的地或途径国家");
			$("#termini").focus();
			return false;
		}
		if($("#go_time").val() == ""){
			alert("请填写外出时间");
			$("#go_time").focus();
			return false;
		}
		if($("#back_time").val() == ""){
			alert("请填写返回时间");
			$("#back_time").focus();
			return false;
		}
		var value = $("input:radio[id='leave']:checked").val();
		if(value == null){
			alert("请选择请假情况");
			$("#leave").focus();
			return false;
		}else if(value == "已请假"){
			if($(":checkbox[id='leave1'][checked]").size() == 0){
				alert("请选择请假类型");
				return false;
			}		
	    }

	  	var value = $("input:radio[name='reason']:checked").val();
		if(value == null){
				alert("请选择事由");
				$("#reason").focus();
				return false;
		}
		if(value == "探亲"){
			if($(":checkbox[id='reason_famliy'][checked]").size() == 0){
				alert("请选择探亲类型");
				return false;
			}		
	    }
		if($("#signname").val().trim() =="")
		{
		   alert("请填写个人承诺");
		   $("#signnames").focus();
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
	function singcheck(check){
    if(check.checked)
    {
       var time = new Date();
       time = time.getFullYear()+"-"+(time.getMonth()+1)+"-"+time.getDate()+" "+time.getHours()+":"+time.getMinutes()+":"
       +time.getSeconds();
       $("#signname").val($("#username").val());
       $("#sign_time").val(time);
    }
    else
    {
       $("#signname").val("");
       $("#sign_time").val("");
       
    }
   }
</script>
</head>
<body oncontextmenu="self.event.returnValue=false">
<table width="95%" border="1" cellspacing="0" cellpadding="0" align="center" class="table_box">	
	<form action="${ctx}/securityuser/adduserabroadevent.action" method="POST">
		<input type="hidden" id="event_code" name="event_code" value="${event_code}"/>
		<input type="hidden" id="dept_id" name="dept_id" value="${curUser.dept_id}"/>
		<input type="hidden" id="jobType" name="jobType" value="${jobType}"/>
		<input type="hidden" id="next_approver" name="next_approver" />
		<input type="hidden" name="seclv_code" id="seclv_code"/>
		<input type="hidden" name="username" id="username" value="${curUser.user_name}"/>
	<tr>
	    <td colspan="6" class="title_box">涉密人员因私出国/出境审批表</td>
	</tr>
	<tr> 
    	<td width="10%" align="center">申请用户 </td>
    		<td width="23%"><font color="blue"><b>${curUser.user_name}</b></font></td>
    	<td width="10%" align="center">用户部门 </td>
    		<td width="23%"><font color="blue"><b>${curUser.dept_name}</b></font></td>
    	<td width="10%"align="center">身份证号 </td>
    	<td width="23%"><font color="blue"><b>&nbsp;${bmUser.idcard}</b></font></td>	
	</tr>
	<tr>
		<td align="center">出生日期 </td>
    	<td><font color="blue"><b>&nbsp;${bmUser.base_birthday}</b></font></td>	
  		<td align="center">人员涉密等级 </td>
    	<td><font color="blue"><b>&nbsp;${curUser.security_name}</b></font></td>
    	<td align="center">文化程度 </td>
    	<td><font color="blue"><b>&nbsp;${bmUser.edu_education_name}</b></font></td>	  	
	</tr>	
	<tr>
  		<td align="center">职务 </td>
    	<td><font color="blue"><b>&nbsp;${bmUser.job_techpost}</b></font></td>	
    	<td align="center">职称</td>
    	<td><font color="blue"><b>&nbsp;${bmUser.job_techtitle}</b></font></td>	
    	<td align="center">政治面貌 </td>
    	<td><font color="blue"><b>&nbsp;${bmUser.base_politics_name}</b></font></td>	  	
	</tr>
	<tr>
		<td align="center">&nbsp;婚姻状况 </td>
	    	<td><font color="blue"><b>&nbsp;
    			<c:if test="${bmUser.marital_status == '0'}">未婚</c:if>
    			<c:if test="${bmUser.marital_status == '1'}">已婚</c:if>
    			<c:if test="${bmUser.marital_status == '2'}">离异</c:if>
    		</b></font></td>	
		<td align="center">手机号码 </td>
    	<td><font color="blue"><b>&nbsp;${bmUser.com_telephone}</b></font></td>	
  		<td align="center">家庭住址 </td>
    	<td><font color="blue"><b>&nbsp;${bmUser.com_address}</b></font></td>	 	
	</tr>		
	
	<c:choose>
		<c:when test="${empty userpassport}">
		<tr>
			<td align="center">护照信息</td>
			<td colspan="5"><font color="blue"><b>无护照信息，或不在档</b></font></td>
		</tr>
		</c:when>
	<c:otherwise>
		<tr>
			<td align="center">护照信息</td>
			<td colspan="5">
				<font color="blue"><b>&nbsp;
		    		<table border rules=all width="100%" border="0" cellspacing="0" cellpadding="0" align="left"  class="table_box_bottom_border">
		    		<tr>
		    			<td align="center" width="10%"><font color="blue"><b>证件编号</b></font></td>
						<td align="center" width="20%"><font color="blue"><b>护照类型</b></font></td>
						<td align="center" width="10%"><font color="blue"><b>护照状态</b></font></td>
						<td align="center" width="13%"><font color="blue"><b>签发时间</b></font></td>
						<td align="center" width="13%"><font color="blue"><b>过期时间</b></font></td>
						<td align="center" width="20%"><font color="blue"><b>备注</b></font></td>
					</tr>		
		  			<s:iterator value="#request.userpassport" var="itemmm">
		  				<tr>
		  					<td align="center"><font color="blue"><b>&nbsp;${itemmm.passport_num}</b></font></td>
		  					<td align="center"><font color="blue"><b>&nbsp;${itemmm.passport_type_name}</b></font></td>
			  				<td align="center"><font color="red"><b>&nbsp;${itemmm.passport_status_name}</b></font></td>
			  				<td align="center"><font color="blue"><b>&nbsp;${itemmm.issuing_date}</b></font></td>	
			  				<td align="center"><font color="blue"><b>&nbsp;${itemmm.ending_date}</b></font></td>
			  				<td align="center"><font color="blue"><b>&nbsp;${itemmm.summ}</b></font></td>
		  				</tr>
					</s:iterator>
		  			</table>
				</b></font>
			</td>
		</tr>	
	</c:otherwise>
	</c:choose>

	<tr>
	 	<td align="center"><font color="red">*</font>&nbsp;计划外出时间</td>
		<td>
   			<input type="text" id="go_time" name="go_time" onclick="WdatePicker()" class="Wdate" size="15"/>
	 	</td>   		
	   	<td align="center"><font color="red">*</font>&nbsp;计划返回时间</td>
	   	<td>
   			<input type="text" id="back_time" name="back_time" onclick="WdatePicker()" class="Wdate" size="15"/>
   		</td>	  	 		
   		<td align="center">他国绿卡或永久居留证</td>
		<td><font color="blue"><b>&nbsp;${bmUser.resident_card}</b></font></td>
   	</tr>		
	<tr>    	
	   	<td align="center"><font color="red">*</font>&nbsp;目的地或途径国家</td>
		<td colspan="5"><textarea name="termini" rows="3" cols="80" id="termini"></textarea></td>    
	</tr>
	<tr>
		<td align="center"><font color="red">*</font>&nbsp;历史出境情况</td>
	    <td colspan="5"><textarea readonly="readonly" name="journey" rows="3" cols="80" id="journey">属本年度第 ${his_abroad} 次出国/境（共出国/境   ${history_all}  次）</textarea></td>
	</tr>
	<tr>
		<td align="center"><font color="red">*</font>&nbsp;请假情况</td>
		<td colspan="5">
			<table width="60%" cellspacing="0" cellpadding="0" align="left" class="table_box">
				<tr>				
					<td align="left">
						<input type="radio" id="leave" name="leave" value="不请假" />不请假
					</td>
				</tr>
				<tr>
					<td align="left">
						<input type="radio" id="leave" name="leave" value="已请假" />已请假
						【<input type="checkbox" id="leave1" name="leave" value="年休假" />年休假
						<input type="checkbox" id="leave1" name="leave" value="探亲假" />探亲假
						<input type="checkbox" id="leave1" name="leave" value="婚假" />婚假
						<input type="checkbox" id="leave1" name="leave" value="其他假" />其他假
						<input type="text" name="leave"/>
						】
					</td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td align="center"><font color="red">*</font>&nbsp;申请事由</td>
		<td colspan="5">
			<table width="60%" cellspacing="0" cellpadding="0" align="left" class="table_box">
				<tr>
					<td align="left">
						<input type="radio" id="reason" name="reason" value="探亲" />探亲
						【<input type="checkbox" id="reason_famliy" name="reason" value="父母" />父母
						<input type="checkbox" id="reason_famliy" name="reason" value="配偶" />配偶
						<input type="checkbox" id="reason_famliy" name="reason" value="子女" />子女
						<input type="checkbox" id="reason_famliy" name="reason" value="其他" />其他】
					</td>
				</tr>
				<tr>				
					<td align="left">
						<input type="radio" id="reason" name="reason" value="只办理因私护照（通行证），本次不出国（境）" />只办理因私护照（通行证），本次不出国（境）
					</td>
				</tr>
				<tr>
					<td align="left">
						<input type="radio" id="reason" name="reason" value="访友" />访友
					</td>
				</tr>
				<tr>
					<td align="left">
						<input type="radio" id="reason" name="reason" value="旅游" />旅游
					</td>
				</tr>
				<tr>
					<td align="left">
						<input type="radio" id="reason" name="reason" value="其他" />其他
						<input type="text" name="reason" id="reason"/>
					</td>
				</tr>
			</table>			
		</td>
		
		<!-- <td colspan="5"><textarea name="reason" rows="3" cols="80" id="reason"></textarea></td> -->		  
	</tr>
	<tr>  
  		<td align="center"><font color="red">*</font>&nbsp;个人承诺</td>
  		<td colspan="5">
  		   <table width="95%" border="0" cellspacing="0" cellpadding="0" align="left">
  		     <tr>
		     	<td colspan="5"><textarea name="summ" rows="5" cols="80" id="summ" readonly>     本人对国家保密法规及声光电公司保密规定已知晓，承诺严格保守知悉的国家秘密和商业秘密，如违反保密相关条款，本人愿意依照《中华人民共和国保守国家保密法》、《国防科技工业涉密人员管理办法》等保密法规及公司保密规定接受相关处罚，并承担相应的责任，赔偿给公司及三所带来的损失。</textarea></td>
		     </tr>	
		     <tr>
		     <td ><font color="red">*</font>&nbsp;
		     本人遵守承诺<input type="checkbox" name="signnames" id="signnames" onclick="singcheck(this)">
		     </td>
		      <td> 申请人</td><td><input type="text" name="signname" id="signname" readonly="readonly"></td>
		       <td> 时&nbsp;&nbsp;间</td><td><input type="text" name="sign_time" id="sign_time" readonly="readonly"></td>
		     </tr>
		    </table>
		</td>	  	
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
	</form>
	 	
  	<tr>
    <td colspan="6" align="center"> 
      <input type="button" class="button_2003" value="提交" onclick="if(chk()) forms[0].submit();" id="submit_btn"/>
    </td>
  </tr>
</table>
</body>
</html>
