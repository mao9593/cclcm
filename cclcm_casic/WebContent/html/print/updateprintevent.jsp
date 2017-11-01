<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<link rel="stylesheet" type="text/css" href="${ctx}/_css/displaytag.css" media="screen"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/_css/css200603.css"  media="screen"/>
    <script language="javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>
	<script language="JavaScript" src="${ctx}/_script/function.js"></script>
	<script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
	<script language="javascript">
	//<!--
		$(document).ready(function(){
			init();
		});
		
		//mod by wx 20150505 子图添加、删除再添加js报错问题修复
		//var num = 0;
		//var num1 = 0;
		var curNumofChildPic = 0;//当前下标
		var totalNumofChildPic = 0;//增加的总数
		//above
		var addArr = new Array();
		function init(){
			
			var print_type = "${event.print_type}";
			$("#print_type").val(print_type);
			if(print_type == 2){
				//$("#color").attr("disabled","disabled");
				//$("#print_double").attr("disabled","disabled");
				$("#add_file").attr("style","display:block inline");
			}else if(print_type == 3){
				$("#files").attr("style","display:none");
				$("#repalce_page").attr("style","display:block inline");
				$("#choose").attr("style","display:block inline");
				if($("#PID_barcode").val()==null && $("#PID_barcode").val()==""){
					$("#PID_barcode").attr("style","display:none");
				}
			}
		}
		function chkComplete(){
			
			if($("#print_type").val() == 3){
				if($("#PID_barcode").val().trim() == ""){
					alert("需要替换的文件主台账不能为空");
					$("#PID_barcode").focus();
					return false;
				}
				if($("#PID_pagenum").val().trim() != null && $("#PID_pagenum").val().trim() != ""){
					var testpagenum =/^([1-9][0-9,]+)|([1-9])$/; 
			  	    if(!testpagenum.test($("#PID_pagenum").val())){
		     			alert("输入错误，请重新输入");
                		$("#PID_pagenum").focus();
	            		return false;
	           	    }else{
	            		var PID_pagenum = $("#PID_pagenum").val().trim();
	            		var arr = new Array();
	            		arr = PID_pagenum.split(",");
	            		arr.sort(function(a,b){return Number(a) - Number(b)});
	            		if(arr.length > Number($("#page_count").val())){
	            			alert("已超过需要打印文件的最大页码");
	            			$("#PID_pagenum").focus();
	            			return false;
	            		}
	            		if(Number(arr[arr.length-1]) > Number($("#pageCount").val())){
	            			alert("需要替换的页码不能超过被替换文件的总页数");
	            			$("#PID_pagenum").focus();
	            			return false;
	            		}
	            	}
				}else{
					alert("需要替换的页码不能为空");
            		$("#PID_pagenum").focus();
	        		return false;
				}
			}else if($("#print_type").val() == 2){
				
				if($("#file_title").val().trim() == ""){
					alert("文件名不能为空");
					$("#file_title").focus();
					return false;
				}
				//mod by wx 20150505 子图添加、删除再添加js报错问题修复
				//if(num-num1<=0){
				//	alert("请添加子图名称");
				//	return false;
				//}
				if(totalNumofChildPic == 0){
					alert("请添加子图名称");
					return false;
				}
				
				
				//for(var i=1;i<=num;i++){
				for(var i = 0; i < curNumofChildPic; i++){
					//if(addArr[i] != "adddel" || addArr[i]==undefined){
					if(addArr[i] != "adddel"){
					//above
						if($("#addtr"+ i +"").val().trim() == ""){
							alert("子图名称不能为空");
							$("#addtr"+ i +"").focus();
							return false;
						}
					}
				}
			}else{
				if($("#file_title").val().trim() == ""){
					alert("文件名不能为空");
					$("#file_title").focus();
					return false;
				}
			}
			$("#update").val("Y");
			$("#UpdatePrintEventForm").submit();
			return true;
		}
		function changePaperType(val){
			//mod by wx 20150505 子图添加、删除再添加js报错问题修复
			curNumofChildPic = 0;//当前下标
			totalNumofChildPic = 0;//增加的总数
			//above
			$("#PID_barcode").val('');
			$("#PID_pagenum").val('');
			if("2" == val){
				//$("#color").attr("disabled","disabled");
				//$("#print_double").attr("disabled","disabled");
				//$("#print_count").attr("disabled","disabled");
				$("#repalce_page").attr("style","display:none");
				$("#files").attr("style","display:block inline");
				$("#add_file").attr("style","display:block inline");
				//$("#file_name").html('<font color="red">*</font>子图名称： ');
				$("#pageCount").html('');
			}else if("3" == val){
				$("#files").attr("style","display:none");
				$("#repalce_page").attr("style","display:block inline");
				$("#choose").attr("style","display:block inline");
				$("#PID_barcode").attr("style","display:none");
			}else{
				$("#app").children().remove();
				//$("#color").removeAttr("disabled");
				//$("#print_double").removeAttr("disabled");
				//$("#print_count").removeAttr("disabled");
				$("#repalce_page").attr("style","display:none");
				$("#files").attr("style","display:block inline");
				$("#add_file").attr("style","display:none");
				$("#file_name").html('<font color="red">*</font>文件名： ');
				$("#pageCount").html('');
			}
		}
		function add_files(){
			//mod by wx 20150505 子图添加、删除再添加js报错问题修复
			//$("#app").append('<tr name="addtr"><td align="center"><font color="red">*</font>子图名称： </td><td colspan="3"><input type="text" id="addtr'+ ++num +'" name="file_title" size="80"/>&nbsp;<input class="button_2003" type="button" value="删除子图名称" name="addtr'+ num +'"  onclick="removeFiles(this)"/></td></tr>');
			//addArr[num-1]="addtr"+num+",";
			$("#app").append('<tr name="addtr"><td align="center"><font color="red">*</font>子图名称： </td><td colspan="3"><input type="text" id="addtr'+ curNumofChildPic +'" name="file_title" size="80"/>&nbsp;<input class="button_2003" type="button" value="删除子图名称" name="rmfile'+ curNumofChildPic +'"  onclick="removeFiles(this)"/></td></tr>');
			addArr[curNumofChildPic]="addtr"+curNumofChildPic+","
			curNumofChildPic++;
			totalNumofChildPic++;
			//above
		}
		function removeFiles(obj){
			$(obj).closest("tr").remove();
			var objName = obj.name;
			//mod by wx 20150505 子图添加、删除再添加js报错问题修复
			//var numStr = objName.charAt(objName.indexOf("addtr") + 6);
			//var numStr = objName.charAt(objName.indexOf("rmfile") + 6);
			var numStr = objName.substring(objName.indexOf("rmfile") + 6);
			//above
			addArr[numStr]="adddel";
			//mod by wx 20150505 子图添加、删除再添加js报错问题修复
			//num1++;
			totalNumofChildPic--;
			//above
		}
		function choose_file(user_iidd){
			var url = "${ctx}/print/replacepageprint.action?user_iidd="+user_iidd+"&updateFlag=Y";
			var rValue = window.showModalDialog(url,'', 'dialogHeight:680px;dialogWidth:1200px;center:yes;status:no;scroll:no;help:no;unadorned:no;resizable:no');
			if(rValue != null && rValue != undefined){
				$("#pageCount").html("<font color='red'>&nbsp;&nbsp;被替换文件总共有"+rValue.page_count+"页</font>");
				$("#pageCount").val(rValue.page_count);
				$("#choose").attr("style","display:none");
				$("#PID_barcode").attr("style","display:block inline");
				$("#PID_barcode").val(rValue.paper_barcode);
			}
		}
	//-->
	</script>
</head>
<body oncontextmenu="self.event.returnValue=false">
<table width="95%" border="1" cellspacing="0" cellpadding="0" align="center" class="table_box">
	<form action="${ctx}/print/updateprintevent.action" method="post" id="UpdatePrintEventForm">
		<input type="hidden" name="update" id="update"/>
		<input type="hidden" name="id" value="${event.id}"/>
		<input type="hidden" name="PIDBarcode" value="${event.PID_barcode}"/>
		<input type="hidden" name="PIDPagenum" value="${event.PID_pagenum}"/>
		<input type="hidden" name="page_count" id="page_count" value="${event.page_count}"/>
	<tr>
	    <td colspan="4" class="title_box">修改打印参数</td>
	</tr>
	<tr>
    	<td align="center" width="15%">申请用户： </td>
    	<td width="35%"><font color="blue"><b>&nbsp;${event.user_name}</b></font></td>
    	<td align="center" width="15%">用户部门： </td>
    	<td width="35%"><font color="blue"><b>&nbsp;${event.dept_name}</b></font></td>
	</tr>
	<tr>
		<td align="center">申请时间： </td>
    	<td><font color="blue"><b>&nbsp;${event.apply_time_str}</b></font></td>
		<td align="center"><font color="red">*</font>&nbsp;打印类型： </td>
		<td>
			<select id="print_type" name="print_type" onchange="changePaperType(this[selectedIndex].value)">
	    		<option value="1">普通打印</option>
	    		<option value="2">拼图打印</option>
	    		<option value="3">替换页打印</option>
	    		<option value="4">针式打印</option>
			</select>
		</td>
	</tr>
	<tr id="files">
		<td align="center" id="file_name"><font color="red">*</font>文件名： </td>
    	<td colspan="3" title="${event.file_title}"><input type="text" name="file_title" id="file_title" value="${event.file_title}" size="80"/>&nbsp;
    		<button class="button_2003" id="add_file" style="display:none" onclick="add_files()"/>&nbsp;添加子图名称</button>
    	</td>	
  	</tr>
  	<tr id="repalce_page" style="display:none">
  		<td align="center"><font color="red">*&nbsp;</font>需要替换的文件条码：</td>
  		<td>
  			<input type="text" name="PID_barcode" id="PID_barcode" value="${event.PID_barcode}" size="30" readonly="readonly"/>
  			<span id="choose"><button class="button_2003" onclick="choose_file('${event.user_iidd}');">&nbsp;&nbsp;选择</button></span>
  		</td>
  		<td align="center"><font color="red">*&nbsp;</font>需要替换的页码：</td>
  		<td>
  			<input type="text" name="PID_pagenum" id="PID_pagenum" value="${event.PID_pagenum}" size="25"/>
  			<font color="red">注：页数必须以英文符号“,”相隔</font>
  			<div id="pageCount" value="${event.page_count}"></div>
  		</td>
  	</tr>
  	<tr id="app"></tr>
  	<tr>
    <td colspan="4" align="center">
    	<input class="button_2003" type="button" value="保存" onClick="chkComplete();">&nbsp;
		<input class="button_2003" type="button" value="返回" onClick="javascript:history.go(-1);">&nbsp;
    </td>
	</tr>
	</form>
</table>
</body>
</html>
