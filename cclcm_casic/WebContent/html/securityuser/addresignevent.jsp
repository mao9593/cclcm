<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<link rel="stylesheet" type="text/css" href="${ctx}/_css/displaytag.css" media="screen"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/_css/css200603.css"  media="screen"/>
	<script language="JavaScript" src="${ctx}/_script/function.js"></script>
	<script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
 	<script language="javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>	
 	<script language="javascript" src="${ctx}/_script/casic304_ajax.js"></script>
 	<script language="JavaScript" src="${ctx}/_script/common.js"></script>
 	<script language="javascript" src="${ctx}/_script/casic304_common_bm.js"></script>
<script>
$(document).ready(function(){
	onHoverBm();
	disableEnterSubmit();
	setSeclv("seclv_code");
	selectNextApprover();
	document.getElementById("allOptions").innerHTML="";
});
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
	function add_TrueCR(){
		var user_id=$("#allOptionCR").val();
		var user_name=$("#allOptionCR option[value='"+user_id+"']").text();
		user_name=user_name.substring(0,user_name.indexOf("/"));
		if(user_id != ""){
	    	$("#resign_user_iidd").val(user_id);
			$("#resign_user_name").val(user_name);
			document.getElementById("allOptions").innerHTML="";
		}
	}

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
	function chk()
	{	
		if($("#resign_user_name").val().trim() == ""){
			alert("请输入离职人");
			$("#resign_user_name").focus();
			return false;
		}
		if($("#signname").val().trim() == ""){
			alert("请勾起本人承诺");
			$("#signnames").focus();
			return false;
		}
		 
		if($("#next_approver_all option").size() > 0 && $("#next_approver_sel option").size() == 0){
			alert("请选择审批人员");
			$("#next_approver_all").focus();
			return false;
		}
     
     //增加离职原因处必填
		var value = $("input:radio[name='reason']:checked").val();
		if(value == null){
				alert("请选择离职原因！");
				$("#reason").focus();
				return false;
			}
        if(value == "2"){ 
		     if($("#new_firm").val().trim() == ""){
			   alert("请填写人员去向");
			   $("#new_firm").focus();
			   return false;
		    }
	   }
	   else if(value == "3"){
	    if($("#dept_id_after").val().trim() == ""){
			   alert("请填写人员新部门");
			   $("#dept_id_after").focus();
			   return false;
		    }
		 else if ($("#post_id_after").val().trim() == ""){
			   alert("请填写人员新岗位");
			   $("#post_id_after").focus();
			   return false;
		    }  
	   }
	   if(value == "4"){ 
		     if($("#resign_others").val().trim() == ""){
			   alert("请填写人员离职（脱密）原因");
			   $("#resign_others").focus();
			   return false;
		    }
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
	
	function chkresigninfo(){
	var url = "${ctx}/securityuser/getresignuserinfo.action";
	$("#useid").val($("#resign_user_iidd").val());
	callServerPostForm1(url,document.forms[0]);
}
   function getAjaxResult1(){
	if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
		if(xmlHttp.responseText.indexOf("<table") != -1){
			$("#info").show();
			document.getElementById("resignuserinfo").innerHTML=xmlHttp.responseText;
			document.getElementById("info1").style.display="None";
			document.getElementById("info2").style.display="None";
			document.getElementById("info3").style.display="None";
			$("#submit_btn").attr("disabled",false);
		}else{
			$("#info").hide();
			alert(xmlHttp.responseText);
			$("#submit_btn").attr("disabled",true);
		}
	}
}

function singcheck(check){
    if(check.checked)
    {
       var time = new Date();
       time = time.getFullYear()+"-"+(time.getMonth()+1)+"-"+time.getDate()+" "+time.getHours()+":"+time.getMinutes()+":"
       +time.getSeconds();
       $("#signname").val($("#resign_user_name").val());
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
<table width="95%" border="1" cellspacing="0" cellpadding="0" align="center" class="table_box" >	
	<form action="${ctx}/securityuser/addresignevent.action" method="POST">
		<input type="hidden" id="dept_id" name="dept_id" value="${curUser.dept_id}"/>
		<input type="hidden" id="jobType" name="jobType" value="${jobType}"/>
		<input type="hidden" id="next_approver" name="next_approver" />
		<input type="hidden" id="event_code" name="event_code" value="${event_code}"/>
		<input type="hidden" name="seclv_code" id="seclv_code"/>
		<input type="hidden" name="useid" id="useid"/>
		<input type="hidden" name="username" id="username" value="${curUser.user_name}"/>
	<tr>
		<td colspan="6" class="title_box">涉密人员脱密（离岗）审批表</td>
	</tr>		
	<tr> 
    	<td width="12%" align="center">&nbsp;发起人 </td>
    	<td width="21%" align="center"><font color="blue"><b>&nbsp;${curUser.user_name}</b></font></td>	
       	<td width="12%" align="center">&nbsp;用户部门 </td>
    	<td width="21%" align="center"><font color="blue"><b>&nbsp;${curUser.dept_name}</b></font></td>
    	<td  width="12%" align="center"><font color="red">*</font>申请人</td>
    	<td  width="21%" align="center">
    	    <input type="text" id="resign_user_name" name="resign_user_name" onkeyup="selectRecvUser(this.value);"/>
    		<input type="button" value="查询" class="button_2003" onclick="return chkresigninfo();">
    		<input type="hidden" id="resign_user_iidd" name="resign_user_iidd"/><br>
    		<div id="allOptions" class="containDiv" style="position:absolute;border:0px solid black;padding:0px">
    		</div>
	 	</td>	
	</tr>
	<tr id="info" style="display: none">
		<td id=resignuserinfo colspan="6">	
		</td>
	</tr>
 	<tr id="info1"  style="display: block"> 
	 	<td width="12%" align="center">&nbsp;&nbsp;性别</td>
    	<td width="21%" align="center"><font color="blue"><b>&nbsp;</b></font></td> 
    	<td width="12%" align="center">&nbsp;&nbsp;出生年月</td>
    	<td width="21%" align="center"><font color="blue"><b>&nbsp;</b></font></td>
    	<td width="12%" align="center">&nbsp;&nbsp;学历</td>
    	<td width="21%" align="center"><font color="blue"><b>&nbsp;</b></font></td> 
	</tr>
	<tr id="info2"  style="display: block"> 
    	<td width="12%" align="center">&nbsp;&nbsp;政治面貌</td>
    	<td width="21%" align="center"><font color="blue"><b>&nbsp;</b></font></td>
    	<td width="12%" align="center">&nbsp;&nbsp;人员涉密等级</td>
    	<td width="21%" align="center"><font color="blue"><b>&nbsp;</b></font></td>
    	<td width="12%" align="center">&nbsp;职务/职称 </td>
    	<td width="21%" align="center"><font color="blue"><b>&nbsp;</b></font></td>
	</tr>
	<tr id="info3"  style="display: block"> 
        <td width="12%" align="center">&nbsp;现从事岗位 </td>
    	<td width="21%" align="center"><font color="blue"><b>&nbsp;</b></font></td>
    	<td width="12%" align="center">&nbsp;&nbsp;联系电话</td>
    	<td width="21%" align="center"><font color="blue"><b>&nbsp;</b></font></td>
    	<td width="12%" align="center">&nbsp;&nbsp;涉密资料总数</td>
    	<td width="21%" align="center"><font color="blue"><b>&nbsp;</b></font></td>
	</tr>	
    <tr>
			<td align="center"><font color="red">*</font>脱密（离职）原因&nbsp;</td>
			<td colspan="5" id="step2">
				<table width="100%" cellspacing="0" cellpadding="0" align="center" class="table_box">
					<tr>
						<td align="left">
						<input type="radio" id="reason" name="reason" value="1" />&nbsp;退休
						</td>
					</tr>
					<tr>
						<td align="left">
						<input type="radio" id="reason" name="reason" value="2" />&nbsp;调离单位，去向
						<input type="text" id="new_firm" name="resign_others" value="${resign_others}"/>
						</td>
					</tr>
		          <tr>
		             <td align="left">
						<input type="radio" id="reason" name="reason" value="3" />&nbsp;岗位变化，新部门

					 <input type="text" id="dept_name" value="${burner.dept_name}" readonly="readonly" style="cursor:hand" onclick="openDeptSelect('dept_name','dept_id_after','radio')" />
			          <input type="hidden" name="dept_id_after" id="dept_id_after" value="${burner.dept_id}"/>
			                           新岗位
					  <select name="post_id_after" id="post_id_after">
						<option value="">--请选择--</option>
							<s:iterator value="#request.postList" var="post_after">
								<option value="${post_after.post_id}">${post_after.post_name}</option>
							</s:iterator>
					</select> 
		      </td>
	       </tr>	
			<tr>
				  <td align="left">
				  <input type="radio" id="reason" name="reason" value="4" />&nbsp;其他
				  <input type="text" id="resign_others" name="resign_others" value="${resign_others}" />
				  </td>
			</tr>
					<tr><td id="hidden2"></td></tr>
				</table>
			</td>
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
		     <td ><font color="red">*</font>&nbsp;
		     本人遵守承诺<input type="checkbox" name="signnames" id="signnames" onclick="singcheck(this)">
		     </td>
		      <td> 申请人</td><td><input type="text" name="signname" id="signname" readonly="readonly"></td>
		       <td> 时&nbsp;&nbsp;间</td><td><input type="text" name="sign_time" id="sign_time" readonly="readonly"></td>
		     </tr>
		    </table>
		</td>	 
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
    	<td colspan="6" align="center"> 
      		<input type="button" class="button_2003" value="提交" onclick="if(chk()) forms[0].submit();" id="submit_btn" disabled="true"/>
    	</td>
 	</tr>
 	</form> 
</table>
</body>
</html>