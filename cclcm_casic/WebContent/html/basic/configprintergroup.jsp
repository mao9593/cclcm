<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
<title>配置打印机</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="${ctx}/_css/css200603.css" type="text/css" media="screen" />
	<script language="javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>
	<script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
	<script  language="JavaScript" >
	$(document).ready(function(){
		onHover();
	});
	function chk()
	{		
	    return true;
	}
	</script>
</head>
<body oncontextmenu="self.event.returnValue=false">
<center>
<form method="post" action="${ctx}/basic/configprintergroup.action">
    <input type="hidden" value="${printer.printer_code}" name="printer_code"/>
    <input type="hidden" value="Y" name="update"/>
  	<table class="table_box" cellspacing=0 cellpadding=0 border=1 width="60%">
  	    <tr>
		    <td colspan="2" class="title_box">
            	配置打印机
            </td>
        </tr>
        <tr>
            <td align="center">当前打印机：</td>
            <td width=60%>
                <font color="blue"><b>${printer.printer_name}</b></font>
		    </td>
        </tr>
        <tr>
            <td align="center">为打印机分配部门：</td>
            <td width=60%>
		        <textarea width="100%" rows="10" id="dept_name" name="dept_name" readonly="readonly" onclick="openDeptSelect('dept_name','dept_id','checkbox')">${dept_names}</textarea>
		        <input type="hidden" name="dept_id" id="dept_id" value="${printer.dept_id}"/>
		    </td>
        </tr>
        <tr>
            <td align="center">为打印机分配用户（独立模式）：</td>
            <td  width=60%>
                <input type="text" name="user_name" id="user_name"/>
                <input type="hidden" name="user_iidd" id="user_iidd"/>
		    </td>
        </tr>
        <tr>
            <td colspan="2" align="center" class="bottom_box">
                <input type="submit" value="保存" class="button_2003" onclick="return chk();">&nbsp;
                <input type="button" value="关闭" class="button_2003" onclick="go('${ctx}/basic/manageprinter.action?printer_code=${item.printer_code}');">
            </td>
        </tr>
  	</table>
</form>
</center>
</body>
</html>