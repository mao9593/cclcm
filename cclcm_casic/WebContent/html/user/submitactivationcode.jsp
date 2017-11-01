<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
<title>输入激活码</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="${ctx}/_css/css200603.css" type="text/css" media="screen"/>
	<link href="${ctx}/_script/calendar2/calendar-blue.css" type="text/css" rel="stylesheet"/>
	<script language="javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>
	<script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
	<script language="javascript" src="${ctx}/_script/casic304_ajax.js"></script>
	<script  language="JavaScript" >
	<!--
	$(document).ready(function(){
		onHover();
	});
	function chk(){
		if($("#activation_code").val().trim() == ""){
			$("#activation_code").focus();
			alert("激活码为空");
			return false;
		}
		$("#activationForm").submit();
		return true;
	}

	</script>
</head>
<body oncontextmenu="self.event.returnValue=false">
<center>
<form method="post" action="${ctx}/submitactivationcode.action" id="activationForm">
	<input type="hidden" name="_submit" value="Y"/>
   	<table class="table_box" cellspacing=0 cellpadding=0 border=1 width="90%" style="margin:5% 0 0 1%">
	 <tr>
		 <td colspan="4" class="title_box">
            	输入激活码
        </td>
    </tr>
    <c:if test="${not empty result}">
	    <tr>
			 <td colspan="3" align="center">
	            	<font color="red">${result}</font>	        
	         </td>
	    </tr>
    </c:if>
    <tr>
  		<td colspan="3">
  			<table width="90%" border="1" cellspacing="0" cellpadding="0" align="left">
  				<tr>
  					<td align="center">注册码</td>
  					<td align="center" width="65%"><input size="85%" type="text" id="register_code" name="register_code" value="${register_code}" readonly="readonly"/>  </td>
  				</tr>
  				<tr>
  					<td align="center"><font color="red">*</font>激活码</td>
  					<td align="center" width="65%"><input size="85%" type="text" id="activation_code" name="activation_code"/>  </td>
  				</tr>
		 	</table>
  		</td>
  	</tr>
  	 <tr>
			 <td colspan="3" align="center">
	            	<font color="red">温馨提示：注册码只当天有效！</font>	        
	         </td>
	    </tr>
  	<tr>
	    <td colspan="3" align="center"> 
	      <input type="button" class="button_2003" value="提交" onclick="return chk()" id="submit_btn"/>
	      &nbsp;
	      <input class="button_2003" type="reset" value="返回"  onClick="javascript:history.go(-1);">&nbsp;
	    </td>
	</tr>
  	</table>
</form>
</center>
</body>
</html>