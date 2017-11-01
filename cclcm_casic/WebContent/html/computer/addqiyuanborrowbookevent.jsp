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
 	<script language="javascript" src="${ctx}/_script/My97DatePicker/WdatePicker.js"></script>
<script>
$(document).ready(function(){
	onHover();
	disableEnterSubmit();
	setSeclv("seclv_code");
	selectNextApprover();
});
	function selectRecvUser(word){
		var url = "${ctx}/basic/getfuzzyuser.action?source=CR";
		if(word != ""){
			callServer1(url,"fuzzy="+word);
		}else{
			document.getElementById("allOptions").innerHTML="";
		}
	}
	function add_TrueCR(){
		var user_id=$("#allOptionCR").val();
		var user_name=$("#allOptionCR option[value='"+user_id+"']").text();
		user_name=user_name.substring(0,user_name.indexOf("/"));
		if(user_id != ""){
	    	$("#entrust_user_iidd").val(user_id);
			$("#entrust_user_name").val(user_name);
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
		if($("#book_selv").val() == ""){
			alert("请填写计算机密级");
			$("#book_selv").focus();
			return false;
		}
		if($("#contact_num").val() == ""){
			alert("请填写联系电话");
			$("#contact_num").focus();
			return false;
		}
		if($("#place").val() == ""){
			alert("请选择出差地点");
			$("#place").focus();
			return false;
		}
		if($("#start_time").val() == ""){
			alert("请填写借用时间");
			$("#start_time").focus();
			return false;
		}
		if($("#end_time").val() == ""){
			alert("请填写归还时间");
			$("#end_time").focus();
			return false;
		}
		if($("#next_approver_all option").size() > 0 && $("#next_approver_sel option").size() == 0){
			alert("请选择审批人员");
			$("#next_approver_all").focus();
			return false;
		}	
		//七院笔记本借用-判断刻录文件有内容时对应密级是否已勾选
		if($("#disc_filename").val() != ""){
		    if($("#discfile1_seclv_code").val() == ""){
			    alert("请勾选刻录文件的密级");
			    $("#discfile1_seclv_code").focus();
			    return false;
			}
		}
		if($("#disc_filename_2").val() != ""){
		    if($("#discfile2_seclv_code").val() == ""){
			    alert("请勾选刻录文件的密级");
			    $("#discfile2_seclv_code").focus();
			    return false;
			}
		}	
		if($("#disc_filename_3").val() != ""){
		    if($("#discfile3_seclv_code").val() == ""){
			    alert("请勾选刻录文件的密级");
			    $("#discfile3_seclv_code").focus();
			    return false;
			}
		}	
		if($("#disc_filename_4").val() != ""){
		    if($("#discfile4_seclv_code").val() == ""){
			    alert("请勾选刻录文件的密级");
			    $("#discfile4_seclv_code").focus();
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
  function selectJudge8(pram){
       if($("#judge8").val() == "是"){
           $("#disc_num").val("请填写光盘编号");
       }else{
           $("#disc_num").val("");
       }     
   }
</script>
</head>
<body oncontextmenu="self.event.returnValue=false">
<table width="95%" border="1" cellspacing="0" cellpadding="0" align="center" class="table_box">	
	<form action="${ctx}/computer/addqiyuanborrowbookevent.action" method="POST">
		<input type="hidden" id="event_code" name="event_code" value="${event_code}"/>
		<input type="hidden" id="dept_id" name="dept_id" value="${curUser.dept_id}"/>
		<input type="hidden" id="jobType" name="jobType" value="${jobType}"/>
		<input type="hidden" id="next_approver" name="next_approver" />
		<input type="hidden" name="seclv_code" id="seclv_code"/>
		<input type="hidden" name="username" id="username" value="${curUser.user_name}"/>
	<tr>
	    <td colspan="4" class="title_box">借用笔记本电脑审批表</td>
	</tr>
	<tr>
		<td colspan="4"><font color="red"><b>
		为确保国家秘密的安全，携带笔记本电脑外出的员工必须遵守保密承诺；<br>
1、	外出期间，严格遵守《笔记本电脑保密规定》及相关制度，做到不用涉密笔记本电脑上互联网，不借予他人使用。<br>
2、	未经保密处允许，所携带的笔记本电脑内不存储涉密内容，配套携带的外接式存储介质内只存储与本次出差相关的资料。<br>
3、	随时随地采取有效措施妥善保管笔记本电脑和存储介质，确保不失控。<br>
4、	不带笔记本电脑和存储介质出入公共场所。<br>
5、	回公司首个工作日，自觉将笔记本电脑送保密处接受保密检查。<br>
6、	如违反以上承诺，愿意接受公司管理制度的处罚。
		</font></td>
	</tr>
	<tr> 
    	<td width="10%" align="center">申请用户 </td>
    	<td width="23%"><font color="blue"><b>${curUser.user_name}</b></font></td>
    	<td width="10%" align="center">单位(部门) </td>
    	<td width="23%"><font color="blue"><b>${curUser.dept_name}</b></font></td>
    </tr> 
    <tr>
    	<td width="10%" align="center">涉密类别 </td>
    	<td width="23%"><font color="blue"><b>${curUser.security_name}</b></font></td>
    	<td align="center"><font color="red">*</font>使用地点</td>
		<td><input type="text" name="place"/></td>
	</tr> 
	<tr>
	   	<td align="center"><font color="red">*</font>联系电话</td>
		<td><input type="text" name="contact_num" id="contact_num"/></td>		
	   	<td align="center">手机</td>
		<td><input type="text" name="mobilephone_num"/></td>
	</tr>
	<tr> 
	    <td align="center">计算机编号</td>
		<td><input type="text" name="devieinfo"/></td>
    	<td width="10%" align="center"><font color="red">*</font>计算机密级</td>
    	<td width="23%">
    		<select name="book_selv" id="book_selv">
				<option value="">--请选择--</option>
				<s:iterator value="#request.seclvList" var="seclv">
					<option value="${seclv.seclv_code}" >${seclv.seclv_name}</option>
				</s:iterator>
			</select>
    	</td>
	</tr> 
	<tr>
	   	<td align="center"><font color="red">*</font>借用时间</td>
		<td><input type="text" name="start_time" id="start_time" onclick="WdatePicker()" class="Wdate" size="15"/></td>		
	   	<td align="center"><font color="red">*</font>归还时间</td>
		<td><input type="text" name="end_time" id="end_time" onclick="WdatePicker()" class="Wdate" size="15"/></td>
	</tr>
	<tr>
	    <td align="center" colspan="4">
	        <tr>
	   	        <td align="center"><font color="red">*</font>借用事由</td>
	   	        <td colspan="3" ><textarea name="summ" rows="4" cols="80" id="summ"></textarea></td>
		    </tr>
		</td>		
	</tr>
	<tr>
	   	<td align="center"><font color="red">*</font>借用期间是否<br>需要使用本机刻录文件</td>
		<td align="left">
			<select name="judge" id="judge8" onchange="selectJudge8(this.value);">
			<option value="">--请选择--</option>
			<option value="是" >是</option>
			<option value="否" >否</option>
			</select>
		</td>		
	   	<td align="center">光盘编号</td>
		<td><input type="text" name="disc_num" id="disc_num" size="30"/></td>
	</tr>   	   	
	<tr>
		<td align="center" colspan="4">
			<table width="95%" border="0" cellspacing="0" cellpadding="0"  align="center">
			<tr>
			   	<td align="center">序号</td>
			   	<td align="center">刻录文件名称</td>
			   	<td align="center">密级</td>
			</tr>	
			<tr>
				<td align="center">&nbsp;1</td>
				<td align="center"><input type="text" name="disc_filename" id="disc_filename" size="70"/></td>
				<td align="center">
					<select name="discfile1_seclv_code" id="discfile1_seclv_code">
				    <option value="">--请选择--</option>
				    <s:iterator value="#request.seclvList" var="seclv">
					<option value="${seclv.seclv_code}" >${seclv.seclv_name}</option>
				    </s:iterator>
			        </select>
				</td>
			</tr>
			<tr>
				<td align="center">&nbsp;2</td>
				<td align="center"><input type="text" name="disc_filename_2" id="disc_filename_2" size="70"/></td>
				<td align="center">
					<select name="discfile2_seclv_code" id="discfile2_seclv_code">
				    <option value="">--请选择--</option>
				    <s:iterator value="#request.seclvList" var="seclv">
					<option value="${seclv.seclv_code}" >${seclv.seclv_name}</option>
				    </s:iterator>
			        </select>
				</td>
			</tr>
			<tr>
				<td align="center">&nbsp;3</td>
				<td align="center"><input type="text" name="disc_filename_3" id="disc_filename_3" size="70"/></td>
				<td align="center">
					<select name="discfile3_seclv_code" id="discfile3_seclv_code">
				    <option value="">--请选择--</option>
				    <s:iterator value="#request.seclvList" var="seclv">
					<option value="${seclv.seclv_code}" >${seclv.seclv_name}</option>
				    </s:iterator>
			        </select>
				</td>
			</tr>
			<tr>
				<td align="center">&nbsp;4</td>
				<td align="center"><input type="text" name="disc_filename_4" id="disc_filename_4" size="70"/></td>
				<td align="center">
					<select name="discfile4_seclv_code" id="discfile4_seclv_code">
				    <option value="">--请选择--</option>
				    <s:iterator value="#request.seclvList" var="seclv">
					<option value="${seclv.seclv_code}" >${seclv.seclv_name}</option>
				    </s:iterator>
			        </select>
				</td>
			</tr>
		</table>
	</tr>	
	<tr>
 		<td align="center"><font color="red">*</font>保密防护措施</td>
	   	<td align="left" colspan="3">
			<input type="checkbox" id="security_methord" name="security_methord" value="便携式计算机严禁上互联网" />便携式计算机严禁上互联网<br>
		    <input type="checkbox" id="security_methord" name="security_methord" value="便携式计算机硬盘禁止存储任何信息" />便携式计算机硬盘禁止存储任何信息<br>
			<input type="checkbox" id="security_methord" name="security_methord" value="其他措施:" />其他措施
			<input type="text" name="security_methord" size="50"/>
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
