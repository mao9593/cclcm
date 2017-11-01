<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<html><head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
	<title>安全用户修改</title>
	<link href="${ctx}/_css/css200603.css" rel="stylesheet" type="text/css"/>
	<link href="${ctx}/_css/displaytag.css" rel="stylesheet" type="text/css" media="screen"/>
	<link href="${ctx}/_script/calendar2/calendar-blue.css" type="text/css" rel="stylesheet"/>
	<script language="javascript" src="${ctx}/_script/IDCard.js"></script>
    <script language="javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>
	<script language="javascript" src="${ctx}/_script/calendar2/calendar.js"></script>
	<script language="JavaScript" src="${ctx}/_script/function.js"></script>
	<script language="JavaScript" src="${ctx}/_script/common.js"></script>
	<script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
	<script language="JScript">
		<!--
	function checkIDCard(idNum){
		if (idNum==""&&!(idNum.length==15||idNum.length==18)){
			alert("身份号长度不正确,请正确输入！");
			return false;
		}
		return true;
	}
	function preCalendar(){
		Calendar.setup({inputField: "base_joinpartytime", button: "base_joinpartytime_trigger_b", singleClick: true, showsTime: true, ifFormat: "%Y-%m-%d", cache: true, weekNumbers:true, showOthers: true, step: 1});
		Calendar.setup({inputField: "base_birthday", button: "base_birthday_trigger_b", singleClick: true, showsTime: true, ifFormat: "%Y-%m-%d", cache: true, weekNumbers:true, showOthers: true, step: 1});
		Calendar.setup({inputField: "job_insectime", button: "job_insectime_trigger_b", singleClick: true, showsTime: true, ifFormat: "%Y-%m-%d", cache: true, weekNumbers:true, showOthers: true, step: 1});
		Calendar.setup({inputField: "job_inposttime", button: "job_inposttime_trigger_b", singleClick: true, showsTime: true, ifFormat: "%Y-%m-%d", cache: true, weekNumbers:true, showOthers: true, step: 1});
		Calendar.setup({inputField: "job_offposttime", button: "job_offposttime_trigger_b", singleClick: true, showsTime: true, ifFormat: "%Y-%m-%d", cache: true, weekNumbers:true, showOthers: true, step: 1});
	}
	function checkForm()
	{
		if(document.all.user_name.value=="")
		{
				alert("请填写用户名称");
				document.all.user_name.focus();
				return false;
		}
		if(!specialCharFilter(document.all.user_name.value))
		{
			alert("用户名称不能包含特殊字符");
			document.all.user_name.focus();
			return false;
		}
		var numval = /^[0-9]*$/;
	    if($("#job_workyears").val()!= ""){
			if(!numval.test($("#job_workyears").val())){
			    alert("涉密年限应为整数");
			    $("#job_workyears").focus(); 
			    return false;
			}
		}
	    if($("#rank").val()!= ""){
			if(!numval.test($("#rank").val())){
			    alert("排序值应为整数！");
			    $("#rank").focus(); 
			    return false;
			}
			if($("#rank").val().length > 4) {
	    	alert("排序值应在4位之内！");
	    	$("#rank").focus();
	    	return false;
	    }
		}else{
			alert("请输入排序值！");
			$("#rank").focus(); 
			return false;
		}
		
		return true;
	}

	/**
	 * 弹出清空密码结果的对话框
	 */
	function showDialogModifyPW(userID){
		mp.location.href = "${ctx}/user/resetuserpwd.action?user_iidd=" + userID;
	}
	function selectRealUser(tag) {
		var url = "${ctx}/user/getrealuser.action";
		var rValue = window.showModalDialog(url, '', 'dialogHeight:600px;dialogWidth:900px;center:yes;status:no;scroll:auto;help:no;unadorned:no;resizable:no');
		if (rValue != undefined) {
			document.all.real_user_id.value = rValue.id;
			document.getElementById("showRealUser").innerHTML="<font color='blue'><b>已选定员工"+rValue.name+"["+rValue.id+"]</b></font>";
			//document.all.base_username.disabled =true;
			$(tag).parentsUntil("tr").parent().nextAll("tr[id!='tr_btn']").hide();
		}
	}
var xmlHttp = false;
try {
  xmlHttp = new ActiveXObject("Msxml2.XMLHTTP");
} catch (e) {
  try {
    xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
  } catch (e2) {
    xmlHttp = false;
  }
}
if (!xmlHttp && typeof XMLHttpRequest != 'undefined') {
  xmlHttp = new XMLHttpRequest();
}
function checkPassNum(user_iidd) {
  var pass_num = $("#pass_num").val();
  if ((pass_num == null) || (pass_num == "")){
      alert("请输入用户卡号!");
      return;
  }
  var url = "${ctx}/user/checkpassnum.action?pass_num=" + escape(pass_num)+"&user_iidd="+user_iidd;
  xmlHttp.open("GET", url, true);
  xmlHttp.onreadystatechange = updatePage;
  xmlHttp.send(null);
}
function updatePage() {
  if (xmlHttp.readyState == 4) {
    var response = xmlHttp.responseText;
	alert(response.substring(response.indexOf("00\">",0)+4, response.indexOf("</div>",0)));
  }
} 
//-->
	</script>
</head>

<body oncontextmenu="self.event.returnValue=false" onload="onHover();preCalendar();">
<iframe id="mp" width="0" height="0" ></iframe>
<table border="0" cellspacing="0" cellpadding="1" width="95%" class="table_box" align="center">
<form method="post" action="${ctx}/user/updatesecuser.action">
	<input type="hidden" name="userId" value="${secUser.user_iidd}"/>
	<input type="hidden" name="dept_id" value="${dept_id}"/>
	<input type="hidden" name="real_user_id" value=""/>
<tr>
	<td colspan="8" class="title_box">
		用户基本信息修改 </td>
</tr>
<tr>
	<td width="10%" align="center">登录名称</td>
	<td width="15%">
		<c:if test="${secUser.status == 1}">
			<img src="${ctx}/_image/iconx32/stop1.gif"/>
		</c:if>
		<c:out value="${secUser.user_iidd}"></c:out>
	</td>
	<td width="10%" align="center"><font color=red>*</font>用户名称</td>
	<td width="15%"><input type="text" name="user_name" size="15" value="${secUser.user_name}"></td>
	<td width="10%" align="center">涉密人员类别</td>
	<td width="15%">
		<select name="security_code">
			<s:iterator value="#request.securityList" var="security">
				<c:set value="${security.security_code}" var="security1" scope="request"/>
				<option value="${security.security_code}" <s:if test="#request.secUser.security_code == #request.security1">selected</s:if>>${security.security_name}</option>
			</s:iterator>
		</select>
	</td>
	<td width="10%" align="center">身份证号</td>
	<td width="15%"><input type="text" name="idCard" size="15" value="${secUser.idCard}"></td>
	<!-- 
	<td width="10%" align="center">用户密码</td>
	<td width="15%"><input type="button" class="button_2003" value="重置为初始密码" onClick="showDialogModifyPW('${secUser.user_iidd}');"/></td>
	 -->
</tr>
	<!-- 
	<td align="center">身份证号</td>
	<td><input type="text" name="idCard" size="15" value="${secUser.idCard}"></td>
	 -->
<tr>
	<td align="center">用户卡号</td>
	<td>
		<input type="text" name="pass_num" id="pass_num" size="15" value="${secUser.pass_num}">
		<input type="button" class="button_2003"  value="查重" onclick="checkPassNum('${secUser.user_iidd}');"/>
	</td>
	<td align="center">打印权限</td>
	<td colspan="3">
		<input type="checkbox" <c:if test="${secUser.print_method == 1}">checked</c:if> name="print_method" id="print_method" value="1">&nbsp;独立模式
		<input type="checkbox" <c:if test="${secUser.print_permission == 1}">checked</c:if> name="print_permission" id="print_permission" value="1">&nbsp;本地打印
	</td>
	<td align="center"><font color=red>*</font>排序值</td>
	<td><input type="text" name="rank" id="rank" size="15" value="${secUser.rank}"></td>
</tr>
<tr>
	<td colspan="8" align="left">
	<input type="button" class="button_2003" value="从已有人员中选择" onclick="selectRealUser(this);"/>
	<div id="showRealUser"></div>
	</td>
</tr>
<tr ><td colspan="8" style="background-color: #C1E5FD" align="center">员工基本信息</td></tr>
<tr>
	<td align="center">员工姓名</td>
	<td><input type="text" name="base_username" size="15" value="${secUser.base_username}"></td>
	<td align="center">性别</td>
	<td><select name="base_sex">
			<option value="M" <c:if test="${secUser.base_sex == 'M'}">selected="selected"</c:if>>男</option>
			<option value="F" <c:if test="${secUser.base_sex == 'F'}">selected="selected"</c:if>>女</option>
		</select></td>
	<td align="center">国别</td>
	<td><input type="text" name="base_country" size="15" value="${secUser.base_country}"></td>
	<td align="center">民族</td>
	<td><input type="text" name="base_nation" size="15" value="${secUser.base_nation}"></td>
	
</tr>
<tr>
	<td align="center">籍贯</td>
	<td><input type="text" name="base_nativeplace" size="15" value="${secUser.base_nativeplace}"></td>
	<td align="center">出生地</td>
	<td><input type="text" name="base_birthplace" size="15" value="${secUser.base_birthplace}"></td>
	<td align="center">政治面貌</td>
	<td><input type="text" name="base_politics" size="15" value="${secUser.base_politics}"/></td>
	<td align="center">入党时间</td>
	<td><input type="text" name="base_joinpartytime" readonly="readonly" style="cursor:hand;" size="10"  value="${secUser.base_joinpartytime_str}"/>
        <input type="image" class="button_2003" src="${ctx}/_image/time2.jpg" id="base_joinpartytime_trigger_b">
    </td>
</tr>
<tr>
	<td align="center">出生日期</td>
	<td><input type="text" name="base_birthday" readonly="readonly" style="cursor:hand;" size="10" value="${secUser.base_birthday_str}"/>
        <input type="image" class="button_2003" src="${ctx}/_image/time2.jpg" id="base_birthday_trigger_b">
    </td>
	<td colspan="6">&nbsp;</td>
</tr>
<tr><td colspan="8" style="background-color: #C1E5FD" align="center">员工学历信息</td></tr>
<tr>
	<td align="center">学历</td>
	<td><input type="text" name="edu_education" size="15" value="${secUser.edu_education}"></td>
	<td align="center">学位</td>
	<td><input type="text" name="edu_degree" size="15" value="${secUser.edu_degree}"></td>
	<td align="center">毕业院校</td>
	<td><input type="text" name="edu_school" size="15" value="${secUser.edu_school}"></td>
    <td align="center">专业</td>
	<td><input type="text" name="edu_major" size="15" value="${secUser.edu_major}"></td>
</tr>
<tr>
	<td align="center">掌握语言</td>
	<td><input type="text" name="edu_language" size="15" value="${secUser.edu_language}"/></td>
	<td align="center">熟悉程度</td>
	<td><input type="text" name="edu_familiarity" size="15" value="${secUser.edu_familiarity}"/></td>
	<td colspan="4">&nbsp;</td>
</tr>
<tr><td colspan="8" style="background-color: #C1E5FD" align="center">员工通信信息</td></tr>
<tr>
	<td align="center">户籍所在地</td>
	<td><input type="text" name="com_residency" size="15" value="${secUser.com_residency}"></td>
	<td align="center">户籍派出所</td>
	<td><input type="text" name="com_police" size="15" value="${secUser.com_police}"></td>
	<td align="center">现住址</td>
	<td><input type="text" name="com_address" size="15" value="${secUser.com_address}"></td>
    <td align="center">联系电话</td>
	<td><input type="text" name="com_telephone" size="15" value="${secUser.com_telephone}"></td>
</tr>
<tr>
	<td align="center">手机号码</td>
	<td><input type="text" name="com_mobile" size="15" value="${secUser.com_mobile}"/></td>
	<td align="center">电子邮箱</td>
	<td><input type="text" name="com_email" size="15" value="${secUser.com_email}"/></td>
	<td colspan="4">&nbsp;</td>
</tr>
<tr><td colspan="8" style="background-color: #C1E5FD" align="center">员工岗位信息</td></tr>
<tr>
	<td align="center">涉密级别</td>
	<td><input type="text" name="job_category" size="15" value="${secUser.job_category}"></td>
	<td align="center">岗位密级</td>
	<td><input type="text" name="job_seclevel" size="15" value="${secUser.job_seclevel}"></td>
	<td align="center">行政职务</td>
	<td><input type="text" name="job_adminpost" size="15" value="${secUser.job_adminpost}"></td>
    <td align="center">技术职务</td>
	<td><input type="text" name="job_techpost" size="15" value="${secUser.job_techpost}"></td>
</tr>
<tr>
	<td align="center">技术职称</td>
	<td><input type="text" name="job_techtitle" size="15" value="${secUser.job_techtitle}"></td>
	<td align="center">人员类别</td>
	<td><input type="text" name="job_humansort" size="15" value="${secUser.job_humansort}"></td>
	<td align="center">进入涉密岗位时间</td>
	<td><input type="text" name="job_insectime" readonly="readonly" style="cursor:hand;" size="10" value="${secUser.job_insectime_str}"/>
        <input type="image" class="button_2003" src="${ctx}/_image/time2.jpg" id="job_insectime_trigger_b">
    <td align="center">涉密年限</td>
	<td><input type="text" name="job_workyears" id="job_workyears" size="15" value="${secUser.job_workyears_str}"></td>
</tr>
<tr>
	<td align="center">员工编号</td>
	<td><input type="text" name="job_passnum" size="15" value="${secUser.job_passnum}"></td>
	<td align="center">出入级别</td>
	<td><input type="text" name="job_passlevel" size="15" value="${secUser.job_passlevel}"></td>
	<td align="center">入岗时间</td>
	<td><input type="text" name="job_inposttime" readonly="readonly" style="cursor:hand;" size="10" value="${secUser.job_inposttime_str}"/>
        <input type="image" class="button_2003" src="${ctx}/_image/time2.jpg" id="job_inposttime_trigger_b">
    <td align="center">离岗时间</td>
	<td><input type="text" name="job_offposttime" readonly="readonly" style="cursor:hand;" size="10" value="${secUser.job_offposttime_str}"/>
        <input type="image" class="button_2003" src="${ctx}/_image/time2.jpg" id="job_offposttime_trigger_b">
</tr>
<tr>
	<td align="center">聘用形式</td>
	<td colspan="7"><select name="job_employtype">
			<option value="正式" <c:if test="${secUser.job_employtype eq '正式'}">selected="selected"</c:if>>正式</option>
			<option value="外聘" <c:if test="${secUser.job_employtype eq '外聘'}">selected="selected"</c:if>>外聘</option>
			<option value="外协" <c:if test="${secUser.job_employtype eq '外协'}">selected="selected"</c:if>>外协</option>
			<option value="通勤" <c:if test="${secUser.job_employtype eq '通勤'}">selected="selected"</c:if>>通勤</option>
		</select>
	</td>
</tr>
<tr id="tr_btn">
	<td colspan="8" class="bottom_box">
		<input type="hidden" name="todo" value="update"/>
		<input type="hidden" name="direction" value=""/>
		<input type="hidden" name="beforeCount" value="${beforeCount}"/>
		<input type="hidden" name="behindCount" value="${behindCount}"/>
		<input type="hidden" name="position" value="${position}"/>
		<br/>
<%-- 		<input type="button" value="保存并修改上一用户(${beforeCount})" class="button_2003"
		<c:if test="${position == 'first'}"> disabled="true"</c:if>
		<c:if test="${beforeCount <= 0 }"> disabled="true"</c:if> onClick="if(checkForm()){ this.disabled = 'true'; forms[0].todo.value='continue'; forms[0].direction.value='prev'; forms[0].target='workframe'; forms[0].submit();}">&nbsp;
		<input type="button" value="保存并修改下一用户(${behindCount})" class="button_2003"
		<c:if test="${position=='last'}"> disabled="true"</c:if>
		<c:if test="${behindCount<=0}"> disabled="true"</c:if> onClick="if(checkForm()){ this.disabled = 'true'; forms[0].todo.value='continue'; forms[0].direction.value='next'; forms[0].target='workframe'; forms[0].submit();}">&nbsp;
		<br/><br/> --%>
		<input type="button" value="保存并返回" class="button_2003" onClick="if(checkForm()){ forms[0].todo.value='return'; forms[0].target='workframe'; forms[0].submit();}">&nbsp;
		<input type="button" value="保存" class="button_2003" onClick="if(checkForm()) {forms[0].target='workframe';forms[0].submit();}">&nbsp;
		<input type="button" value="返回" class="button_2003" onClick="window.parent.location='${ctx}/user/viewuserbydept.action?dept_id=${dept_id}'">
	</td>
</tr>
</table>
</form>
</body>
</html>