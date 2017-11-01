<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
  <head>
   <title>替换页监销确认列表</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="${ctx}/_css/css200603.css" type="text/css" media="screen" />
		<link rel="stylesheet" type="text/css" href="${ctx}/_css/displaytag.css" media="screen"/>
	<link href="${ctx}/_script/calendar2/calendar-blue.css" type="text/css" rel="stylesheet"/>
	<script language="javascript" src="${ctx}/_script/calendar2/calendar.js"></script>
	<script language="javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>
	<script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
	<script language="javascript" src="${ctx}/_script/casic304_ajax.js"></script>
	<script>
	function chk(paper_id,pagenum){
		if(paper_id != null && paper_id != ""){
			if($("#destroy_pagenum").val().trim() != null && $("#destroy_pagenum").val().trim() != ""){
				var destroy_pagenum =/^([1-9][0-9,]+)|([1-9])$/; 
			    if(!destroy_pagenum.test($("#destroy_pagenum").val())){
		     		alert("输入错误，请重新输入");
                	$("#destroy_pagenum").focus();
	            	return false;
	            }else{
	            	var destroy_pagenum = $("#destroy_pagenum").val().trim();
	            	var arr = new Array();
	            	arr = destroy_pagenum.split(",");
	            	var flag = false;
	            	var count = 0;
	            	for(var i=0;i<arr.length;i++){
	            		var num=arr[i];
	            		if(pagenum.indexOf(num) < 0){
	            			break;
	            		}else{
	            			flag = true;
	            			count++;
	            		}
	            	}
	            	if(!flag || count<arr.length){
	            		alert("实际出纸页数只能填写第"+pagenum+"页里面的页码");
	            		$("#destroy_pagenum").focus();
	            		return false;
	            	}
	            }
			}else{
				alert("请输入需要回收的页码");
            	$("#destroy_pagenum").focus();
	        	return false;
			}
			return true;
		}	
	}
	function onOK(paper_id,pagenum){
		if(chk(paper_id,pagenum)){
			var ret = new Object();
			if(paper_id != null && paper_id != ""){
				ret.destroy_pagenum = $("#destroy_pagenum").val().trim();
			}		
			ret.fail_remark = $("#fail_remark").val().trim();
			window.returnValue=ret;
			window.close();  
		}
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
	<tr>
		<td align="right">需销毁页码：</td>
		<td><font color="red">第${pagenum}页</font></td>
	</tr>
	<tr> 
		<td align="right"><font color="red">*</font>实际销毁页码：</td>
	    <td align="left">
			<input type="text" id="destroy_pagenum" name="destroy_pagenum" value="${pagenum}" readonly="true" size="15"/>
			<font color="red">注：页码由系统读取,无须手动填写</font>
		</td> 	
	</tr>
	<tr> 
		<td align="right">备注：</td>
    	<td align="left"><textarea name="fail_remark" rows="3" cols="45" id="fail_remark">${fail_remark_tips}</textarea></td>   	
	</tr>
	<tr> 
		<td colspan="2" align="center">
			<input type="button" class="button_2003" value="提交" onclick="return onOK('${paper_id}','${pagenum }');" />&nbsp;
			<input type="button" class="button_2003" value="取消" onclick="onCancel();" />
		</td>
	</tr>
</table>
</center>
  </body>
</html>
