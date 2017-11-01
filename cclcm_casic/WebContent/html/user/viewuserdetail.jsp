<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title>用户详细信息</title>
<link href="${ctx}/_css/css200603.css" rel="stylesheet" type="text/css"/>
<link rel="stylesheet" href="${ctx}/_css/displaytag.css" type="text/css" media="screen"/>
<style type="text/css">
    .onshow
    {
		height:27;
        FONT-WEIGHT: bold;FONT-SIZE: 8pt; color:YELLOW;
	    BORDER-RIGHT: #89c3eb 1px solid;
	    BORDER-TOP: #89c3eb 1px solid;
	    BORDER-LEFT: #89c3eb 1px solid;
	    BORDER-BOTTOM: #89c3eb 1px solid;
	    BACKGROUND-COLOR:#007bbb;
	    PADDING:5,5,0,5;
    }
    .offshow
    {
		height:25;
        FONT-WEIGHT: bold;FONT-SIZE: 8pt; color:#FFFFFF;
	    BORDER-RIGHT: #89c3eb 1px solid;
	    BORDER-TOP: #89c3eb 1px solid;
	    FILTER: progid:DXImageTransform.Microsoft.Gradient(gradientType=0,startColorStr='#007bbb',endColorStr='#89c3eb');
	    BORDER-LEFT: #89c3eb 1px solid;
	    BORDER-BOTTOM: #89c3eb 1px solid;
	    PADDING:5,5,0,5;
    }
</style>
<script type="text/javascript">
function switchTab(ProTag, ProBox) {
	for (i = 1; i < 5; i++) {
	    if ("tab" + i == ProTag) {
	        document.getElementById(ProTag).className = "onshow";
	    } else {
	        document.getElementById("tab" + i).className = "offshow";
	    }
	    if ("con" + i == ProBox) {
	        document.getElementById(ProBox).style.display = "";
	    } else {
	        document.getElementById("con" + i).style.display = "none";
	    }
	}
}
</script>
</head>
<body oncontextmenu="self.event.returnValue=false">
<table border="0" cellspacing="0" cellpadding="0" width="60%">
<div id="tabContainer">
	<span class="onshow" onclick="switchTab('tab1','con1');" id="tab1" style="cursor: pointer;">基本信息</span>
	<span class="offshow" onclick="switchTab('tab2','con2');" id="tab2" style="cursor: pointer;">学历信息</span>
	<span class="offshow" onclick="switchTab('tab3','con3');" id="tab3" style="cursor: pointer;">通信信息</span>
	<span class="offshow" onclick="switchTab('tab4','con4');" id="tab4" style="cursor: pointer;">岗位信息</span>
</div>
</table>
<div id="con1">
<table border="0" cellspacing="0" cellpadding="1" width="100%" class="table_box" style="text-align: center;">
	<tr>
		<td width="20%" align="center">登录名</td>
		<td width="30%">
			<c:if test="${secUser.status == 1}">
				<img src="${ctx}/_image/iconx32/stop1.gif"/>
			</c:if>
			&nbsp;${user_iidd}
		</td>
		<td width="20%" align="center">用户名</td>
		<td width="30%">&nbsp;${secUser.user_name}</td>
	</tr>
	<tr>
		<td align="center">员工ID</td>
		<td>&nbsp;${secUser.real_user_id}</td>
		<td align="center">员工姓名</td>
		<td>&nbsp;${secUser.base_username}</td>
	</tr>
	<tr>
		<td align="center">国别</td>
		<td>&nbsp;${secUser.base_country}</td>
		<td align="center">民族</td>
		<td>&nbsp;${secUser.base_nation}</td>
	</tr>
	<tr>
		<td align="center">籍贯</td>
		<td>&nbsp;${secUser.base_nativeplace}</td>
		<td align="center">出生地</td>
		<td>&nbsp;${secUser.base_birthplace}</td>
	</tr>
	<tr>
		<td align="center">政治面貌</td>
		<td>&nbsp;${secUser.base_politics}</td>
		<td align="center">入党时间</td>
		<td>&nbsp;${secUser.base_joinpartytime_str}</td>
	</tr>
	<tr>
		<td align="center">性别</td>
		<td>&nbsp;<c:if test="${secUser.base_sex == 'M'}">男</c:if>
			<c:if test="${secUser.base_sex == 'F'}">女</c:if></td>
		<td align="center">出生日期</td>
		<td>&nbsp;${secUser.base_birthday_str}</td>
	</tr>
</table>
</div>
<div id="con2" style="display: none">
<table border="0" cellspacing="0" cellpadding="1" width="100%" class="table_box" style="text-align: center;">
	<tr>
		<td width="20%" align="center">学历</td>
		<td width="30%">&nbsp;${secUser.edu_education}</td>
		<td width="20%" align="center">学位</td>
		<td width="30%">&nbsp;${secUser.edu_degree}</td>
	</tr>
	<tr>
		<td align="center">毕业院校</td>
		<td>&nbsp;${secUser.edu_school}</td>
    	<td align="center">专业</td>
		<td>&nbsp;${secUser.edu_major}</td>
	</tr>
	<tr>
		<td align="center">掌握语言</td>
		<td>&nbsp;${secUser.edu_language}</td>
		<td align="center">熟悉程度</td>
		<td>&nbsp;${secUser.edu_familiarity}</td>
	</tr>
</table>
</div>
<div id="con3" style="display: none;">
<table border="0" cellspacing="0" cellpadding="1" width="100%" class="table_box" style="text-align: center;">
	<tr>
		<td width="20%" align="center">户籍所在地</td>
		<td width="30%">&nbsp;${secUser.com_residency}</td>
		<td width="20%" align="center">户籍派出所</td>
		<td width="30%">&nbsp;${secUser.com_police}</td>
	</tr>
	<tr>
		<td align="center">现住址</td>
		<td>&nbsp;${secUser.com_address}</td>
	    <td align="center">联系电话</td>
		<td>&nbsp;${secUser.com_telephone}</td>
	</tr>
	<tr>
		<td align="center">手机号码</td>
		<td>&nbsp;${secUser.com_mobile}</td>
		<td align="center">电子邮箱</td>
		<td>&nbsp;${secUser.com_email}</td>
	</tr>
</table>
</div>
<div id="con4" style="display: none;">
<table border="0" cellspacing="0" cellpadding="1" width="100%" class="table_box" style="text-align: center;">
	<tr>
		<td width="20%" align="center">涉密级别</td>
		<td width="30%">&nbsp;${secUser.job_category}</td>
		<td width="20%" align="center">岗位密级</td>
		<td width="30%">&nbsp;${secUser.job_seclevel}</td>
	</tr>
	<tr>
		<td align="center">行政职务</td>
		<td>&nbsp;${secUser.job_adminpost}</td>
	    <td align="center">技术职务</td>
		<td>&nbsp;${secUser.job_techpost}</td>
	</tr>
	<tr>
		<td align="center">技术职称</td>
		<td>&nbsp;${secUser.job_techtitle}</td>
		<td align="center">人员类别</td>
		<td>&nbsp;${secUser.job_humansort}</td>
	</tr>
	<tr>
		<td align="center">进入涉密岗位时间</td>
		<td>&nbsp;${secUser.job_insectime_str}</td>
		<td align="center">涉密年限</td>
		<td>&nbsp;${secUser.job_workyears_str}</td>
	</tr>
	<tr>
		<td align="center">员工编号</td>
		<td>&nbsp;${secUser.job_passnum}</td>
		<td align="center">出入级别</td>
		<td>&nbsp;${secUser.job_passlevel}</td>
	</tr>
	<tr>
		<td align="center">入岗时间</td>
		<td>&nbsp;${secUser.job_inposttime_str}</td>
	    <td align="center">离岗时间</td>
		<td>&nbsp;${secUser.job_offposttime_str}</td>
	</tr>
	<tr>
		<td align="center">聘用形式</td>
		<td colspan="3">&nbsp;${secUser.job_employtype}</td>
	</tr>
</table>
</div>
<br/>
<br/>
<table border="0" cellspacing="0" cellpadding="1" width="100%" class="table_only_border">
	<tr>
		<td colspan=2 class="title_box">部门信息</td>
	</tr>
	<tr><td>&nbsp;</td></tr>
	<tr>
		<td>
			<span title="${secUser.dept_id}">用户归属部门：<span style="color:red;font-weight:bold">${secUser.dept_name}</span></span>
			<br/>
		</td>
	</tr>
</table>
<br/>
<center><input type="button" class="button_2003" value="关闭" onclick="window.close();"/></center>
</body>
</html>