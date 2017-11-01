<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
<title>标记失败</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<link rel="stylesheet" type="text/css" href="${ctx}/_css/displaytag.css" media="screen"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/_css/css200603.css"  media="screen"/>
	<script language="JavaScript" src="${ctx}/_script/function.js"></script>
	<script language="javascript" src="${ctx}/_script/casic304_common.js"></script>	
	<script language="javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>	
	<script language="javascript" src="${ctx}/_script/casic304_ajax.js"></script>
	<script>
	function chk_paper(paper_id){
		if(paper_id != null && paper_id != ""){
			if($("#page_count").val().trim() != null && $("#page_count").val().trim() != ""){
				//if(!isInteger($("#page_count").val().trim())){
				//	alert("实际出纸页数只能填写1到10000之间的整数");
				//	$("#page_count").focus();
				//	return false;
				//}
				var page_count =/^[0-9]{0,4}$/; 
			    if(!page_count.test($("#page_count").val())){
		     	alert("实际出纸页数只能填写0到10000之间的整数");
                $("#page_count").focus();
	            return false;
	          }
		   }
		}
		
		if($("#fail_remark_paper").val().trim() == ""){
			alert("请填写备注");
			$("#fail_remark_paper").focus();
			return false;
		}		
	    return true;
	}
		
	function chk_cd(cd_id){
		if(cd_id != null && cd_id != ""){
			if($("#file_num").val().trim() != null && $("#file_num").val().trim() != ""){
				var file_num =/^[0-9]{0,4}$/; 
			    if(!file_num.test($("#file_num").val())){
		     	alert("实际文件数只能填写0到10000之间的整数");
                $("#file_num").focus();
	            return false;
	          }
		   }
		}
		
		if($("#fail_remark_cd").val().trim() == ""){
			alert("请填写备注");
			$("#fail_remark_cd").focus();
			return false;
		}		
	    return true;
	}	

	function onOK_paper(paper_id){
	
		if(chk_paper(paper_id)){
			var ret = new Object();
			if(paper_id != null && paper_id != ""){
				ret.page_count = $("#page_count").val().trim();
			}	
			ret.fail_remark = $("#fail_remark_paper").val().trim();
			if($(":checkbox:checked[value!='']").size()>0)
			{
				ret.isDestroyPaper = "Y";
			}else{
				ret.isDestroyPaper = "N";
			}
			window.returnValue=ret;
			window.close();  
		}
	}	
	
	function onOK_cd(cd_id){
	
		if(chk_cd(cd_id)){
			var ret = new Object();
			if(cd_id != null && cd_id != ""){
				ret.file_num = $("#file_num").val().trim();
			}			
			ret.fail_remark = $("#fail_remark_cd").val().trim();	
			ret.file_list = $("#file_list").val().trim();
			if($(":checkbox:checked[value!='']").size()>0)
			{
				ret.isRetrieveCD = "Y";
			}else{
				ret.isRetrieveCD = "N";
			}

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
<c:choose>
	<c:when test="${type == 'PAPER'}">
		<table width="95%" border="1" cellspacing="0" cellpadding="0" align="center" class="table_box" style="margin-top:30px">
			<tr>
				<td class="title_box" colspan="2">&nbsp;</td>
			</tr>
			<c:if test="${!empty paper_id and paper_id != ''}">
				<tr> 
					<td align="right">实际出纸页数：</td>
			    	<td align="center"><input type="text" id="page_count" name="page_count" value="${page_count}" size="32"/>&nbsp;</td> 	
				</tr>
			</c:if>
		
			<tr> 
				<td align="right"><font color="red">*</font>备注：</td>
		    	<td align="center"><textarea name="fail_remark_paper" rows="3" cols="30" id="fail_remark_paper">${fail_remark_tips}</textarea></td>   	
			</tr>
			<tr> 
				<td>&nbsp;</td>
				<td align="center">
				<input type="checkbox" name="isDestroyPaper" id="isDestroyPaper" checked/>已现场销毁
				</td>
			</tr>
			<tr> 
				<td colspan="2" align="center">
					<input type="button" class="button_2003" value="提交" onclick="onOK_paper('${paper_id}');" />&nbsp;
					<input type="button" class="button_2003" value="取消" onclick="onCancel();" />
				</td>
			</tr>
		</table>
	</c:when>
	<c:otherwise>
		<table width="95%" border="1" cellspacing="0" cellpadding="0" align="center" class="table_box" style="margin-top:30px">
			<tr>
				<td class="title_box" colspan="2">&nbsp;</td>
			</tr>
			<c:if test="${!empty cd_id and cd_id != ''}">
				<tr> 
					<td align="right">实际刻录文件数：</td>
			    	<td align="center"><input type="text" id="file_num" name="file_num" value="${file_num}" size="32"/>&nbsp;</td> 	
				</tr>
			</c:if>
			<tr> 
				<td align="right">刻录成功的文件名称：</td>
		    	<td align="center"><textarea name="file_list" rows="3" cols="30" id="file_list">${file_list}</textarea></td>   	
			</tr>
			<tr> 
				<td align="right"><font color="red">*</font>备注：</td>
		    	<td align="center"><textarea name="fail_remark_cd" rows="3" cols="30" id="fail_remark_cd">${fail_remark_tips}</textarea></td>   	
			</tr>
			<tr> 
				<td>&nbsp;</td>
				<td align="center">
				<input type="checkbox" name="isRetrieveCD" id="isRetrieveCD" checked/>已现场回收
				</td>
			</tr>
			<tr> 
				<td colspan="2" align="center">
					<input type="button" class="button_2003" value="提交" onclick="onOK_cd('${cd_id}');" />&nbsp;
					<input type="button" class="button_2003" value="取消" onclick="onCancel();" />
				</td>
			</tr>
		</table>
	</c:otherwise>
</c:choose>
</center>
</body>
</html>
