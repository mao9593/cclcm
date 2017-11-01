<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<title>项目配置</title>
	<link rel="StyleSheet" href="${ctx}/_css/css200603.css" type="text/css" />
	<script type="text/javascript" src="${ctx}/_script/tree/dtree.js"></script>
	<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
</head>

<body style="margin-left: 3px;margin-top: 3px;" oncontextmenu="self.event.returnValue=false">
<table width="100%" border="0" cellpadding="0" cellspacing="0"  >
    <tr>
        <td  height=1  class=title>配置项目</td>
    </tr>
	<tr>
        <td  height=1 class="hr"></td>
    </tr>
</table>
	<br/>
<div class="dtree">

	<script type="text/javascript">
		d = new dTree('d', '${ctx}/_image/');
	    d.add(0,-1,'配置项目');
		d.add(1,0,'配置密级','${ctx}/user/viewseclevel.action','','workframe');
		d.add(2,0,'配置机构类型','${ctx}/user/managedepttype.action','','workframe');
		d.add(3,0,'配置地区','${ctx}/user/manageterr.action','','workframe');
		d.add(4,0,'配置员工信息','${ctx}/user/viewrealuser.action','','workframe');
		d.add(5,0,'查看机构等级','${ctx}/user/viewdeptlevel.action','','workframe');
		document.write(d);
	</script>
</div>
</body>
</html>