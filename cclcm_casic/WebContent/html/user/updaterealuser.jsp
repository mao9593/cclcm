<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title>员工信息修改</title>
<link href="${ctx}/_css/css200603.css" rel="stylesheet" type="text/css"/>
<link href="${ctx}/_css/displaytag.css" rel="stylesheet" type="text/css" media="screen"/>
<link href="${ctx}/_component/calendar2/calendar-blue.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}/_component/calendar2/calendar.js"></script>
<script language="JavaScript" src="${ctx}/_script/function.js"></script>
<script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
<script language="JScript">
<!--
function checkForm()
{
	return true;
}
function preCalendar(){
	Calendar.setup({inputField: "base_joinpartytime", button: "base_joinpartytime_trigger_b", singleClick: true, showsTime: true, ifFormat: "%Y-%m-%d", cache: true, weekNumbers:true, showOthers: true, step: 1});
	Calendar.setup({inputField: "base_birthday", button: "base_birthday_trigger_b", singleClick: true, showsTime: true, ifFormat: "%Y-%m-%d", cache: true, weekNumbers:true, showOthers: true, step: 1});
	Calendar.setup({inputField: "job_insectime", button: "job_insectime_trigger_b", singleClick: true, showsTime: true, ifFormat: "%Y-%m-%d", cache: true, weekNumbers:true, showOthers: true, step: 1});
	Calendar.setup({inputField: "job_inposttime", button: "job_inposttime_trigger_b", singleClick: true, showsTime: true, ifFormat: "%Y-%m-%d", cache: true, weekNumbers:true, showOthers: true, step: 1});
	Calendar.setup({inputField: "job_offposttime", button: "job_offposttime_trigger_b", singleClick: true, showsTime: true, ifFormat: "%Y-%m-%d", cache: true, weekNumbers:true, showOthers: true, step: 1});
}
//-->
</script>
</head>
<body oncontextmenu="self.event.returnValue=false" onload="onHover();preCalendar();">
<table border="0" cellspacing="0" cellpadding="1" width="95%" class="table_box" align="center">
<form method="post" action="${ctx}/user/updaterealuser.action">
	<input type="hidden" name="real_user_id" value="${realUser.real_user_id}"/>
	<input type="hidden" name="update" value="Y"/>
<tr>
	<td colspan="8" class="title_box">
		员工信息修改 </td>
</tr>
<tr><td colspan="8" style="background-color: #C1E5FD" align="center">员工基本信息</td></tr>
<tr>
	<td>员工ID</td>
	<td>${realUser.real_user_id}</td>
	<td>员工姓名</td>
	<td><input type="text" name="base_username" size="15" value="${realUser.base_username}"></td>
	<td>国别</td>
	<td><input type="text" name="base_country" size="15" value="${realUser.base_country}"></td>
	<td>民族</td>
	<td><input type="text" name="base_nation" size="15" value="${realUser.base_nation}"></td>
</tr>
<tr>
	<td>籍贯</td>
	<td><input type="text" name="base_nativeplace" size="15" value="${realUser.base_nativeplace}"></td>
	<td>出生地</td>
	<td><input type="text" name="base_birthplace" size="15" value="${realUser.base_birthplace}"></td>
	<td>政治面貌</td>
	<td><input type="text" name="base_politics" size="15" value="${realUser.base_politics}"/></td>
	<td>入党时间</td>
	<td><input type="text" name="base_joinpartytime" readonly="readonly" style="cursor:hand;" size="10"  value="${realUser.base_joinpartytime_str}"/>
        <input type="image" class="button_2003" src="${ctx}/_image/time2.jpg" id="base_joinpartytime_trigger_b">
    </td>
</tr>
<tr>
	<td>性别</td>
	<td><select name="base_sex">
			<option value="M" <c:if test="${realUser.base_sex == 'M'}">selected="selected"</c:if>>男</option>
			<option value="F" <c:if test="${realUser.base_sex == 'F'}">selected="selected"</c:if>>女</option>
		</select>
	</td>
	<td>出生日期</td>
	<td><input type="text" name="base_birthday" readonly="readonly" style="cursor:hand;" size="10" value="${realUser.base_birthday_str}"/>
        <input type="image" class="button_2003" src="${ctx}/_image/time2.jpg" id="base_birthday_trigger_b"></td>
	<td colspan="4">&nbsp;</td>
</tr>
<tr><td colspan="8" style="background-color: #C1E5FD" align="center">员工学历信息</td></tr>
<tr>
	<td>学历</td>
	<td><input type="text" name="edu_education" size="15" value="${realUser.edu_education}"></td>
	<td>学位</td>
	<td><input type="text" name="edu_degree" size="15" value="${realUser.edu_degree}"></td>
	<td>毕业院校</td>
	<td><input type="text" name="edu_school" size="15" value="${realUser.edu_school}"></td>
    <td>专业</td>
	<td><input type="text" name="edu_major" size="15" value="${realUser.edu_major}"></td>
</tr>
<tr>
	<td>掌握语言</td>
	<td><input type="text" name="edu_language" size="15" value="${realUser.edu_language}"/></td>
	<td>熟悉程度</td>
	<td><input type="text" name="edu_familiarity" size="15" value="${realUser.edu_familiarity}"/></td>
	<td colspan="4">&nbsp;</td>
</tr>
<tr><td colspan="8" style="background-color: #C1E5FD" align="center">员工通信信息</td></tr>
<tr>
	<td>户籍所在地</td>
	<td><input type="text" name="com_residency" size="15" value="${realUser.com_residency}"></td>
	<td>户籍派出所</td>
	<td><input type="text" name="com_police" size="15" value="${realUser.com_police}"></td>
	<td>现住址</td>
	<td><input type="text" name="com_address" size="15" value="${realUser.com_address}"></td>
    <td>联系电话</td>
	<td><input type="text" name="com_telephone" size="15" value="${realUser.com_telephone}"></td>
</tr>
<tr>
	<td>手机号码</td>
	<td><input type="text" name="com_mobile" size="15" value="${realUser.com_mobile}"/></td>
	<td>电子邮箱</td>
	<td><input type="text" name="com_email" size="15" value="${realUser.com_email}"/></td>
	<td colspan="4">&nbsp;</td>
</tr>
<tr><td colspan="8" style="background-color: #C1E5FD" align="center">员工岗位信息</td></tr>
<tr>
	<td>涉密级别</td>
	<td><input type="text" name="job_category" size="15" value="${realUser.job_category}"></td>
	<td>岗位密级</td>
	<td><input type="text" name="job_seclevel" size="15" value="${realUser.job_seclevel}"></td>
	<td>行政职务</td>
	<td><input type="text" name="job_adminpost" size="15" value="${realUser.job_adminpost}"></td>
    <td>技术职务</td>
	<td><input type="text" name="job_techpost" size="15" value="${realUser.job_techpost}"></td>
</tr>
<tr>
	<td>技术职称</td>
	<td><input type="text" name="job_techtitle" size="15" value="${realUser.job_techtitle}"></td>
	<td>人员类别</td>
	<td><input type="text" name="job_humansort" size="15" value="${realUser.job_humansort}"></td>
	<td>进入涉密岗位时间</td>
	<td><input type="text" name="job_insectime" readonly="readonly" style="cursor:hand;" size="10" value="${realUser.job_insectime_str}"/>
        <input type="image" class="button_2003" src="${ctx}/_image/time2.jpg" id="job_insectime_trigger_b"></td>
    <td>涉密年限</td>
	<td><input type="text" name="job_workyears" size="15" value="${realUser.job_workyears_str}"></td>
</tr>
<tr>
	<td>员工编号</td>
	<td><input type="text" name="job_passnum" size="15" value="${realUser.job_passnum}"></td>
	<td>出入级别</td>
	<td><input type="text" name="job_passlevel" size="15" value="${realUser.job_passlevel}"></td>
	<td>入岗时间</td>
	<td><input type="text" name="job_inposttime" readonly="readonly" style="cursor:hand;" size="10" value="${realUser.job_inposttime_str}"/>
        <input type="image" class="button_2003" src="${ctx}/_image/time2.jpg" id="job_inposttime_trigger_b"></td>
    <td>离岗时间</td>
	<td><input type="text" name="job_offposttime" readonly="readonly" style="cursor:hand;" size="10" value="${realUser.job_offposttime_str}"/>
        <input type="image" class="button_2003" src="${ctx}/_image/time2.jpg" id="job_offposttime_trigger_b"></td>
</tr>
<tr>
	<td>聘用形式</td>
	<td colspan="7"><select name="job_employtype">
			<option value="正式" <c:if test="${realUser.job_employtype eq '正式'}">selected="selected"</c:if>>正式</option>
			<option value="外聘" <c:if test="${realUser.job_employtype eq '外聘'}">selected="selected"</c:if>>外聘</option>
			<option value="外协" <c:if test="${realUser.job_employtype eq '外协'}">selected="selected"</c:if>>外协</option>
			<option value="通勤" <c:if test="${realUser.job_employtype eq '通勤'}">selected="selected"</c:if>>通勤</option>
		</select>
	</td>
</tr>
<tr>
	<td colspan="8" class="bottom_box" align="center">
		<input type="button" value="保存" class="button_2003" onClick="if(checkForm()) forms[0].submit();">&nbsp;
		<input type="button" value="返回" class="button_2003" onClick="javascirpt:history.go(-1);">
	</td>
</tr>
</table>
</form>
</body>
</html>