<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
<title>修改岗位</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="${ctx}/_css/css200603.css" type="text/css" media="screen" />
	<script language="javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>
	<script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
	<script  language="JavaScript" >
	<!--
	$(document).ready(function(){
		onHover();
		init();	
	});
	function chk()
	{
		if($("#post_name").val().trim() == ""){
			alert("请填写岗位名称");
			$("#post_name").focus();
			return false;
		}
		if($("#dept_ids").val().trim() == ""){
			alert("请双击选择适用部门");
			$("#dept_ids").focus();
			return false;
		}
		if($("#post_desc").val().length > 1024){
			alert("岗位说明不能超过1024个字符");
			$("#post_desc").focus();
			return false;
		}
	    return true;
	}
	function init(){
		var moduleCodeIndex = "${userpost.post_id}";
		if(moduleCodeIndex!=null){
			$(":checkbox[name='post_id']").each(function(){
				if(moduleCodeIndex.indexOf(this.value) > -1){
					this.checked = true;
				}
			});
		}
	}
	</script>
</head>
<body oncontextmenu="self.event.returnValue=false">
<center>
<form method="post" action="${ctx}/securityuser/updateuserpost.action">
	<input type="hidden" value="${userpost.post_id}" name="post_id"/>
	<input type="hidden" value="Y" name="update"/>
   	<table class="table_box" cellspacing=0 cellpadding=0 border=1 width="60%">
	 <tr>
		 <td colspan="4" class="title_box">
            	修改岗位
        </td>
    </tr>
    <tr>
    	<td align="center"><font color="red">*</font>岗位特征值</td>
		<td><font color="blue"><b>${userpost.post_id}</b></font></td>
    	<td align="center"><font color="red">*</font>岗位名称</td>
		<td>
			<input type="text" name="post_name" id="post_name" value="${userpost.post_name}"/>
		</td>
    </tr>
      <tr>
    	<td align="center"><font color="red">*</font>岗位排序</td>
		<td>
			<input type="text" name="post_level" id="post_level" value="${userpost.post_level}"/>
		</td>
		<td align="center"><font color="red">*</font>岗位等级</td>
		<td>
			<input type="text" name="post_class" id="post_class" value="${userpost.post_class}"/>
		</td>
    </tr>
    <tr height="35">
  		<td align="center"><font color="red">*</font>&nbsp;适用部门</td>
  		<td>
			<textarea rows="5" cols="20" name="dept_name" id="dept_name" readonly="readonly" style="cursor:hand" onclick="openDeptSelect('dept_name','dept_ids','checkbox')">${dept_names}</textarea>
			<input type="hidden" name="dept_ids" id="dept_ids" value="${dept_ids}"/>
		</td>
    	<td align="center">岗位描述</td>
		<td>
			<textarea rows="5" cols="20" name="post_desc" id="post_desc">${userpost.post_desc}</textarea>
		</td>
    </tr>
    <tr>
        <td colspan="4" align="center" class="bottom_box">
            <input type="submit" value="修改" class="button_2003" onclick="return chk();">&nbsp;
            <input type="button" value="返回" class="button_2003" onclick="javascript:history.go(-1);">
        </td>
    </tr>
  	</table>
</form>
</center>
</body>
</html>