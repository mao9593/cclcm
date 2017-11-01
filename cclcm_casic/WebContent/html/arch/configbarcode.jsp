<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
<title>设置条码格式</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<link rel="stylesheet" type="text/css" href="${ctx}/_css/displaytag.css" media="screen"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/_css/css200603.css"  media="screen"/>
	<script language="JavaScript" src="${ctx}/_script/function.js"></script>
	<script language="javascript" src="${ctx}/_script/casic304_common.js"></script>	
	<script language="javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>	
	<script language="javascript" src="${ctx}/_script/casic304_ajax.js"></script>
	<script>
	function onOK(){
		var result = new Object();
		result.n_dum=$("input[name='n_dum'][checked]").val();
		//result.is_encrypt=$("input[name='is_encrypt'][checked]").val();
		result.onOK = "Y";
		window.returnValue=result;	
		window.close();  
	}	
	function onCancel(){
		window.close();
	}
	</script>
</head>
<body oncontextmenu="self.event.returnValue=false">
<center>
<table width="95%" border="1" cellspacing="0" cellpadding="0" align="center" class="table_box" style="margin-top:30px">
	<tr>
		<td class="title_box" colspan="2">&nbsp;</td>
	</tr>
	<tr >
		<td align="center">横纵向设置：</td>
		<td align="center">
			<label style="width: 80">
				<input type="radio" name="n_dum"  value="1" checked="true"/>纵向
			</label>
			<label style="width: 80">
				<input type="radio" name="n_dum"  value="2"/>横向
			</label>		
		</td>
	</tr>
	<!-- <tr >
		<td align="center">是否加密：</td>
		<td align="center">
			<label style="width: 80">
				<input type="radio" name="is_encrypt"  value="0" checked="true"/>不加密
			</label>
			<label style="width: 80">
				<input type="radio" name="is_encrypt"  value="1"/>加密
			</label>		
		</td>
	</tr> -->
	<tr> 
		<td colspan="2" align="center">
			<input type="button" class="button_2003" value="提交" onclick="onOK();" />&nbsp;
			<input type="button" class="button_2003" value="取消" onclick="onCancel();" />
		</td>
	</tr>
</table>
</center>
</body>
</html>
