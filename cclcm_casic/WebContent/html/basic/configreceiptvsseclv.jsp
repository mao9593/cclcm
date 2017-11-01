<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
<title>交接单输出配置</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="${ctx}/_css/css200603.css" type="text/css" media="screen"/>
	<link href="${ctx}/_script/calendar2/calendar-blue.css" type="text/css" rel="stylesheet"/>
	<script language="javascript" src="${ctx}/_script/calendar2/calendar.js"></script>
	<script language="javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>
	<script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
	<script  language="JavaScript" >
	<!--
	$(document).ready(function(){
		onHover();
		init();
		$(":checkbox[name='define_value']").each(function(){
		var define_value="${seclv.seclv_code}";
		if(define_value.indexOf("|"+this.value+"|") != -1){
			this.checked=true;
		}
		});
	});
	function init(){
		var allowLevel = "${define_value}";
		$(":checkbox[name='define_value']").each(function(){
			if(allowLevel.indexOf(this.value) > -1){
				this.checked = true;
			}
		});
	}
	function chk()
	{
		var sec = $("#seclv_med").val();
		var check = true;
		$(":checkbox[name=define_value]:checked").nextAll().each(function(){
			if(this.value == sec){
				this.focus();
				check = false;
			}
		});
		if($(":checkbox[name=define_value]:checked").size() == 0){
			alert("请选择可设定密级");
			return false;
		}
	    return true;
	}
	//-->
	</script>
</head>
<body oncontextmenu="self.event.returnValue=false">
<center>
<form method="post" action="${ctx}/basic/configreceiptvsseclv.action">
    <input type="hidden" name="update" id="update" value="Y"/>
   	<table class="table_box" cellspacing=0 cellpadding=0 border=1 width="90%">
	 <tr>
		 <td colspan="4" class="title_box">
            	交接单输出配置
        </td>
    </tr>
    <tr height="50">
	    <td align="center"><font color="red">*</font>允许输出交接单的密级：</td>
		<td colspan="3">
			<s:iterator value="#request.seclvList" var="seclv">
				&nbsp;&nbsp;<input type="checkbox" value="${seclv.seclv_code}" name="define_value"/>${seclv.seclv_name}
			</s:iterator>
		</td>
	</tr>
    <tr>
        <td colspan="4" align="center" class="bottom_box">
            <input type="submit" value="修改" class="button_2003" onclick="return chk();">&nbsp;&nbsp;
            <input type="button" value="返回" class="button_2003" onclick="javascript:history.go(-1);">
        </td>
    </tr>
  	</table>
</form>
</center>
</body>
</html>