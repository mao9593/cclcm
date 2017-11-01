<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<HTML><HEAD>
<meta http-equiv="Content-Type" content="text/html"/>
<title>安全用户添加</title>
<link href="${ctx}/_css/css200603.css" rel="stylesheet" type="text/css"/>
<link rel="stylesheet" href="${ctx}/_css/displaytag.css" type="text/css" media="screen"/>
<link href="${ctx}/_script/calendar2/calendar-blue.css" type="text/css" rel="stylesheet"/>
<script language="javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>
<script language="javascript" src="${ctx}/_script/calendar2/calendar.js"></script>
<script language="JavaScript" src="${ctx}/_script/function.js"></script>
<script language="JavaScript" src="${ctx}/_script/common.js"></script>
<script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
<script>
	<!--
function checkForm()
{
	if(document.all.user_iidd.value==""){
		alert('请填写登录名称');
		document.all.user_iidd.focus();
		return false;
	}
	if(!specialCharFilter(document.all.user_iidd.value)){
		alert('登录名不能包含特殊字符');
		document.all.user_iidd.focus();
		return false;
	}
	if(document.all.user_name.value==""){
		alert('请填写用户名');
		document.all.user_name.focus();
		return false;
	}
	if(!specialCharFilter(document.all.user_name.value)){
		alert('用户名称不能包含空格和特殊字符');
		document.all.user_name.focus();
		return false;
	}
	var user_ppww =/[\u4e00-\u9fa5]+/;
    if(user_ppww.test(document.all.user_ppww.value)){
    	alert("密码不能含有汉字");
    	document.all.user_ppww.focus();
		return false;
    }
	if(document.all.user_ppww1.value!=document.all.user_ppww.value){
		alert('两次输入的密码不一致,请确认');
		document.all.user_ppww.focus();
		return false;
	}
		/*if (document.forms[0].birthday.value == ""){
            var now=new Date();
            var year=now.getYear()-30;
            document.forms[0].birthday.value = year+"-01-01 00:00:00.000";
		}*/
	if(document.all.security_code.value == ''){
		alert('请选择涉密人员类别');
		document.all.security_code.focus();
		return false;
	}
    if(document.all.dept_name.value==""){
        alert('归属部门为空');
        document.all.dept_name.focus();
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
	}else {
		alert("请输入排序值！");
		 $("#rank").focus();
		return false;
	}
	
    //处理角色信息
    document.all.role_id_list.value=getSelectedCodes();
    if(!checkUserIidd(document.all.user_iidd.value)){
   		alert("登录名称只能由数字、字母或下划线组成，长度小于30位");
		document.all.user_iidd.focus();
		return false;
    }
    return true;
}
function checkUserIidd(value){
	var code_pattern=/^[0-9a-zA-Z_-]{1,30}$/;
	if(!code_pattern.test(value)){
		return false;
	}
	return true;
}
function showError(){

}
function preCalendar(){
	Calendar.setup({inputField: "base_joinpartytime", button: "base_joinpartytime_trigger_b", singleClick: true, showsTime: true, ifFormat: "%Y-%m-%d", cache: true, weekNumbers:true, showOthers: true, step: 1});
	Calendar.setup({inputField: "base_birthday", button: "base_birthday_trigger_b", singleClick: true, showsTime: true, ifFormat: "%Y-%m-%d", cache: true, weekNumbers:true, showOthers: true, step: 1});
	Calendar.setup({inputField: "job_insectime", button: "job_insectime_trigger_b", singleClick: true, showsTime: true, ifFormat: "%Y-%m-%d", cache: true, weekNumbers:true, showOthers: true, step: 1});
	Calendar.setup({inputField: "job_inposttime", button: "job_inposttime_trigger_b", singleClick: true, showsTime: true, ifFormat: "%Y-%m-%d", cache: true, weekNumbers:true, showOthers: true, step: 1});
	Calendar.setup({inputField: "job_offposttime", button: "job_offposttime_trigger_b", singleClick: true, showsTime: true, ifFormat: "%Y-%m-%d", cache: true, weekNumbers:true, showOthers: true, step: 1});
}
function checkSame(){
	if(document.all.user_ppww1.value==''){
		document.all.samePwd.innerText="";
	}else if(document.all.user_ppww1.value!=document.all.user_ppww.value){
 		document.all.samePwd.style.color="FF0000";
 		document.all.samePwd.innerText=" 不一致" ;
	}else if(document.all.user_ppww1.value==document.all.user_ppww.value){
 		document.all.samePwd.style.color="#33CC00";
 		document.all.samePwd.innerText=" 一致" ;
	}
}
function selectRealUser() {
	var url = "${ctx}/user/getrealuser.action";
	var rValue = window.showModalDialog(url, '', 'dialogHeight:600px;dialogWidth:900px;center:yes;status:no;scroll:auto;help:no;unadorned:no;resizable:no');
	if (rValue != undefined) {
		document.all.real_user_id.value = rValue.id;
		document.getElementById("showRealUser").innerHTML="<font color='blue'><b>已选定员工["+rValue.name+"]</b></font>";
		document.all.base_username.disabled =true;
	}
}
//-->
</script>
<script language="javascript">
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
function callServer() {
  var u_name = document.getElementById("user_iidd").value;
  if ((u_name == null) || (u_name == "")){
      //result.innerHTML="<font color='red'>请输入登录名称!</font>";
      alert("请输入登录名称!");
      return;
  }
  var url = "${ctx}/user/checkuserid.action?user_iidd=" + escape(u_name)+"&random="+Math.random();
  xmlHttp.open("GET", url, true);
  xmlHttp.onreadystatechange = updatePage;
  xmlHttp.send(null);
}

function updatePage() {
  /* if (xmlHttp.readyState < 4) {
	result.innerHTML="检测中...";
  } */
  if (xmlHttp.readyState == 4) {
    var response = xmlHttp.responseText;
    //result.innerHTML=response;
	alert(response.substring(response.indexOf("00\">",0)+4, response.indexOf("</div>",0)));
  }
}
function addaction()
{
	if($("#use_man_dept_id").val().length == 0){
		alert("归属部门不能为空");
		return false;
	}
    go("${ctx}/user/addsecuser.action?dept_id="+$("#use_man_dept_id").val());
}
function getSelectedCodes(){
	var codes = "";
	for(j=0; j<document.all.selectOption.options.length; j++){
	    codes += document.all.selectOption.options[j].value + ",";
	}
	var len = codes.length;
	if (codes.lastIndexOf(",") == len - 1){
    	codes = codes.substring(0, len - 1);
	}
	return codes;
}   

function checkPassNum() {
  var pass_num = $("#pass_num").val();
  if ((pass_num == null) || (pass_num == "")){
      alert("请输入用户卡号!");
      return;
  }
  var url = "${ctx}/user/checkpassnum.action?pass_num=" + escape(pass_num)+"&random="+Math.random();
  xmlHttp.open("GET", url, true);
  xmlHttp.onreadystatechange = updatePage1;
  xmlHttp.send(null);
}
function updatePage1() {
  if (xmlHttp.readyState == 4) {
    var response = xmlHttp.responseText;
	alert(response.substring(response.indexOf("00\">",0)+4, response.indexOf("</div>",0)));
  }
} 
</script>
</HEAD>
<form method="post" action="${ctx}/user/addsecuser.action?oper=insert">
<input type="hidden" name="dept_id" value="${dept_id}"/>
<input type="hidden" name="real_user_id" value=""/>
<input type="hidden" name="role_id_list" id="role_id_list" />
<body onload="onHover();document.all.user_iidd.focus();preCalendar();" oncontextmenu="self.event.returnValue=false">
<table border="0" cellspacing="0" cellpadding="0" class="table_box" width="95%" align="center">
<tr>
	<td colspan="8" class="title_box">
        <div style="float:left">新安全用户添加</div><div style="float:right"><input type="button" name="Reset2" value="展开/关闭说明" onClick="showHide(document.all.explain)" class="button_2003"></div>
    </td>
</tr>
<tr>
    <td colspan="8">
    	<FIELDSET  id="explain" style="PADDING-RIGHT: 5px; PADDING-LEFT: 5px; PADDING-BOTTOM: 5px; PADDING-TOP: 5px">
          	<LEGEND>说明</LEGEND>
          		&nbsp;&nbsp;&nbsp;&nbsp;1.登录名请不要输入中文字符;
          	<br/>
               &nbsp;&nbsp;&nbsp;&nbsp;2.检查登录名按钮用于检测当前输入的登录名是否已存在;
              <br/>
               &nbsp;&nbsp;&nbsp;&nbsp;3.为提高安全性,建议设定用户密码大于6位，并且使用字母、数字、特殊字符相结合的密码。
              <br/>
              &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;若密码为空，系统将为用户设定默认密码(12345678a@)。
              <br/>
              &nbsp;&nbsp;&nbsp;&nbsp;4.显示用户时，以用户排序值递增的顺序进行排序，排序值相同时根据用户名称排序，且用户排序值应在4位以内。
              
  		</FIELDSET>
      <script>showHide(document.all.explain)</script>
    </td>
</tr>
<tr>
    <td colspan="8" id="result"></td>
</tr>
<tr>
	<td width="10%" align="center"><font color=red>*</font>用户名称</td>
	<td width="15%"><input type="text" name="user_name" size="15"></td>
    <td width="10%" align="center"><font color=red>*</font>归属部门</td>
	<td width="15%" align="left">
        <input type="hidden" name="use_man_dept_id" id="use_man_dept_id" value="${dept_id}"/>
        <input type="text" name="dept_name" id="dept_name" value="${dept_name}" readonly="readonly" size="15">
        <input type="button" class="button_2003" value="选择" onClick="openDeptSelect('dept_name','use_man_dept_id','radio');addaction()"/>
    </td>
    <td width="10%" align="center"><font color=red>*</font>登录名称</td>
	<td width="15%">
		<input type="text" name="user_iidd" onkeyup="result.innerHTML='';" size="15">
		<input type="button" class="button_2003" name="checkUserName" value="查重" onclick="callServer();"/>
	</td>
	<td width="10%" align="center">用户卡号</td>
	<td width="15%">
		<input type="text" name="pass_num" id="pass_num" size="15">
		<input type="button" class="button_2003" name="checkNum" value="查重" onclick="checkPassNum();"/>
	</td>
	<!-- 
	<td width="10%">岗位</td>
	<td width="15%"><select name="post_id">
			<option value="">--请选择--</option>
			<s:iterator value="#request.postList" var="post">
				<option value="${post.post_id}">${post.post_name}</option>
			</s:iterator>
		</select>
	</td>
	 -->
</tr>
<tr>
	<td align="center">密码</td>
	<td><input type="password" name="user_ppww" onfocus="this.style.backgroundColor='#ede4cd'" onkeyup="pwStrength(this.value,'strength');checkSame();" onblur="this.style.backgroundColor='';pwStrength(this.value,'strength');" size="17">
		<div id="strength"></div></td>
	<td align="center">确认密码</td>
	<td><input type="password" name="user_ppww1" onfocus="this.style.backgroundColor='#ede4cd'" onkeyup="checkSame();" onblur="this.style.backgroundColor='';" size="17"><font id="samePwd" color="#FF0000"></font></td>
	<td align="center"><font color=red>*</font>涉密人员类别</td>
	<td><select name="security_code" id="security_code">
			<option value="">--请选择--</option>
			<s:iterator value="#request.securityList" var="security">
				<option value="${security.security_code}">${security.security_name}</option>
			</s:iterator>
		</select>
	</td>
	<td align="center">身份证号</td>
	<td><input type="text" name="idCard" size="15"></td>
</tr>
<tr>
	<td align="center">打印权限</td>
	<td colspan="3">
		<input type="checkbox" name="print_method" id="print_method" value="1">&nbsp;独立模式
		<input type="checkbox" name="print_permission" id="print_permission" value="1">&nbsp;本地打印
	</td>
	<td align="center"><font color=red>*</font>排序值</td>
	<td colspan="3">
	<input type="text" name="rank" id="rank" size="15" value="100">
	</td>
</tr>
<tr><td colspan="8" style="background-color: #C1E5FD" align="center">配置用户角色</td></tr>
<tr>
	<td colspan="2">
           <SELECT ondblclick="add_True('allOption','selectOption');" multiple="true" size="10" name="allOption" style="width: 100%" id="allOption">
           	<c:forEach var="item" items="${allRoleList}" varStatus="status">
				<option value="${item.role_id}">${item.role_name}</option>
           	</c:forEach>
           </SELECT>
	</td>
	<td aling="center" valign="middle">
           <INPUT class="button_2003" onclick="add_True('allOption','selectOption');" type="button" value="增加->" name="btnAddItem" /><br/>
           <br/>
           <INPUT class="button_2003" onclick="del_True('selectOption');" type="button" value="删除<-" name="btnDelItem"><br/>
	</td>
	<td colspan="2">
           <SELECT name="selectOption" size="10" multiple="true" ondblclick="del_True('selectOption');" style="width: 100%" id="selectOption">
           </SELECT>
	</td>
	<td colspan="3">&nbsp;</td>
</tr>
<tr>
	<td colspan="8" align="center">
		<input type="hidden" name="todo"/>
		<input type="button" value="保存并新增" class="button_2003" onClick="if(checkForm()){ forms[0].todo.value='add'; forms[0].submit();}">
		&nbsp;&nbsp;
		<input type="button" value="保存并返回" class="button_2003" onClick="if(checkForm()) forms[0].submit();">
		&nbsp;&nbsp;
		<input type="Reset" class="button_2003" value="重置"/>
		&nbsp;&nbsp;
		<input type="button" value="返回" class="button_2003" onClick="location.href='${ctx}/user/viewuserbydept.action?dept_id=${dept_id}'">
	</td>
</tr>
<tr>
	<td colspan="8" align="left">
	<input type="button" class="button_2003" value="从已有人员中选择" onclick="selectRealUser();"/>
	<div id="showRealUser"></div>
	</td>
</tr>
<tr><td colspan="8" style="background-color: #C1E5FD" align="center">员工基本信息</td></tr>
<tr>
	<td>员工姓名</td>
	<td><input type="text" name="base_username" size="15"></td>
	<td>性别</td>
	<td>
		<input type="radio" name="base_sex" value="M" checked="checked"/>男
		<input type="radio" name="base_sex" value="F"/>女 
	</td>
	<td>国别</td>
	<td><input type="text" name="base_country" size="15"></td>
	<td>民族</td>
	<td><input type="text" name="base_nation" size="15"></td>
	
</tr>
<tr>
	<td>籍贯</td>
	<td><input type="text" name="base_nativeplace" size="15"></td>
	<td>出生地</td>
	<td><input type="text" name="base_birthplace" size="15"></td>
	<td>政治面貌</td>
	<td><input type="text" name="base_politics" size="15"/></td>
	<td>入党时间</td>
	<td><input type="text" name="base_joinpartytime" readonly="readonly" style="cursor:hand;" size="10"/>
        <input type="image" class="button_2003" src="${ctx}/_image/time2.jpg" id="base_joinpartytime_trigger_b">
    </td>
</tr>
<tr>
	<td>出生日期</td>
	<td><input type="text" name="base_birthday" readonly="readonly" style="cursor:hand;" size="10"/>
        <input type="image" class="button_2003" src="${ctx}/_image/time2.jpg" id="base_birthday_trigger_b">
    </td>
	<td colspan="6">&nbsp;</td>
</tr>
<tr><td colspan="8" style="background-color: #C1E5FD" align="center">员工学历信息</td></tr>
<tr>
	<td>学历</td>
	<td><input type="text" name="edu_education" size="15"></td>
	<td>学位</td>
	<td><input type="text" name="edu_degree" size="15"></td>
	<td>毕业院校</td>
	<td><input type="text" name="edu_school" size="15"></td>
    <td>专业</td>
	<td><input type="text" name="edu_major" size="15"></td>
</tr>
<tr>
	<td>掌握语言</td>
	<td><input type="text" name="edu_language" size="15"/></td>
	<td>熟悉程度</td>
	<td><input type="text" name="edu_familiarity" size="15"/></td>
	<td colspan="4">&nbsp;</td>
</tr>
<tr><td colspan="8" style="background-color: #C1E5FD" align="center">员工通信信息</td></tr>
<tr>
	<td>户籍所在地</td>
	<td><input type="text" name="com_residency" size="15"></td>
	<td>户籍派出所</td>
	<td><input type="text" name="com_police" size="15"></td>
	<td>现住址</td>
	<td><input type="text" name="com_address" size="15"></td>
    <td>联系电话</td>
	<td><input type="text" name="com_telephone" size="15"></td>
</tr>
<tr>
	<td>手机号码</td>
	<td><input type="text" name="com_mobile" size="15"/></td>
	<td>电子邮箱</td>
	<td><input type="text" name="com_email" size="15"/></td>
	<td colspan="4">&nbsp;</td>
</tr>
<tr><td colspan="8" style="background-color: #C1E5FD" align="center">员工岗位信息</td></tr>
<tr>
	<td>涉密级别</td>
	<td><input type="text" name="job_category" size="15"></td>
	<td>岗位密级</td>
	<td><input type="text" name="job_seclevel" size="15"></td>
	<td>行政职务</td>
	<td><input type="text" name="job_adminpost" size="15"></td>
    <td>技术职务</td>
	<td><input type="text" name="job_techpost" size="15"></td>
</tr>
<tr>
	<td>技术职称</td>
	<td><input type="text" name="job_techtitle" size="15"></td>
	<td>人员类别</td>
	<td><input type="text" name="job_humansort" size="15"></td>
	<td>进入涉密岗位时间</td>
	<td><input type="text" name="job_insectime" readonly="readonly" style="cursor:hand;" size="10"/>
        <input type="image" class="button_2003" src="${ctx}/_image/time2.jpg" id="job_insectime_trigger_b">
    <td>涉密年限</td>
	<td><input type="text" name="job_workyears" id="job_workyears" size="15"></td>
</tr>
<tr>
	<td>员工编号</td>
	<td><input type="text" name="job_passnum" size="15"></td>
	<td>出入级别</td>
	<td><input type="text" name="job_passlevel" size="15"></td>
	<td>入岗时间</td>
	<td><input type="text" name="job_inposttime" readonly="readonly" style="cursor:hand;" size="10"/>
        <input type="image" class="button_2003" src="${ctx}/_image/time2.jpg" id="job_inposttime_trigger_b">
    <td>离岗时间</td>
	<td><input type="text" name="job_offposttime" readonly="readonly" style="cursor:hand;" size="10"/>
        <input type="image" class="button_2003" src="${ctx}/_image/time2.jpg" id="job_offposttime_trigger_b">
</tr>
<tr>
	<td>聘用形式</td>
	<td colspan="7"><select name="job_employtype">
			<option value="正式">正式</option>
			<option value="外聘">外聘</option>
			<option value="外协">外协</option>
			<option value="通勤">通勤</option>
		</select>
	</td>
</tr>
<tr>
	<td colspan="8" align="center">
		<!-- <input type="hidden" name="todo"/> -->
		<input type="button" value="保存并新增" class="button_2003" onClick="if(checkForm()){ forms[0].todo.value='add'; forms[0].submit();}">
		&nbsp;&nbsp;
		<input type="button" value="保存并返回" class="button_2003" onClick="if(checkForm()) forms[0].submit();">
		&nbsp;&nbsp;
		<input type="Reset" class="button_2003" value="重置"/>
		&nbsp;&nbsp;
		<input type="button" value="返回" class="button_2003" onClick="location.href='${ctx}/user/viewuserbydept.action?dept_id=${dept_id}'">
	</td>
</tr>
</table>
<br/>
<br/>
</body>
</form>
</HTML>