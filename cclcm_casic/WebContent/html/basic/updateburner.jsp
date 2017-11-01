<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
<title>修改刻录机</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="${ctx}/_css/css200603.css" type="text/css" media="screen"/>
	<script language="javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>
	<script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
	<script  language="JavaScript" >
	<!--
	$(document).ready(function(){
		onHover();
	});
	function chk()
	{
		if($("#burner_name").val().trim() == ""){
			alert("请填写刻录机名称");
			$("#burner_name").focus();
			return false;
		}
		if($("#burner_type").val().trim() == ""){
			alert("请选择刻录机类型");
			$("#burner_type").focus();
			return false;
		}
		if($("#burner_usefor").val().trim() == ""){
			alert("请选择刻录机用途");
			$("#burner_usefor").focus();
			return false;
		}
		if($("#dept_id").val().trim() == ""){
			alert("请选择所属部门");
			return false;
		}
		if($("#seclv_code").val().trim() == ""){
			alert("请选择密级");
			$("#seclv_code").focus();
			return false;
		}
		if($("#console_code").val().trim() == ""){
			alert("请选择控制台");
			$("#console_code").focus();
			return false;
		}
		if(!checkCode_addword($("#burner_name").val())){
			alert("刻录机名称只能由数字、字母、汉字或下划线组成，长度小于30位");
			$("#burner_name").focus();
			return false;
		}
	    return true;
	}
	//-->
	</script>
</head>
<body oncontextmenu="self.event.returnValue=false">
<center>
<form method="post" action="${ctx}/basic/updateburner.action">
	<input type="hidden" value="${burner.burner_code}" name="burner_code"/>
	<input type="hidden" value="Y" name="update"/>
   	<table class="table_box" cellspacing=0 cellpadding=0 border=1 width="60%">
	 <tr>
		 <td colspan="2" class="title_box">
            	修改刻录机信息
        </td>
    </tr>
    <tr>
    	<td align="center"><font color="red">*</font>刻录机名称：</td>
		<td>
			<input type="text" name="burner_name" id="burner_name" value="${burner.burner_name}"/>
		</td>
    </tr>
    <tr>
    	<td align="center"><font color="red">*</font>刻录机类型：</td>
		<td>
			<select id="burner_type" name="burner_type">
				<option value="">--请选择--</option>
				<option value="DVD" <c:if test="${burner.burner_type == 'DVD'}">selected='selected'</c:if>>DVD</option>
				<option value="CD" <c:if test="${burner.burner_type == 'CD'}">selected='selected'</c:if>>CD</option>
				<option value="BLUERAY" <c:if test="${burner.burner_type == 'BLUERAY'}">selected='selected'</c:if>>BLUERAY</option>
			</select>
		</td>
    </tr>
    <tr>
    	<td align="center"><font color="red">*</font>刻录机用途：</td>
		<td>
			<select id="burner_usefor" name="burner_usefor">
				<option value="">--请选择--</option>
				<option value="0" <c:if test="${burner.burner_usefor == '0'}">selected='selected'</c:if>>输入</option>
				<option value="1" <c:if test="${burner.burner_usefor == '1'}">selected='selected'</c:if>>输出</option>
			</select>
		</td>
    </tr>
    <tr>
    	<td align="center">刻录机路径：</td>
		<td>
			<input type="text" name="burner_path" id="burner_path" value="${burner.burner_path}"/>
		</td>
    </tr>
    <tr>
    	<td align="center">品牌：</td>
		<td>
			<input type="text" name="burner_brand" id="burner_brand" value="${burner.burner_brand}"/>
		</td>
    </tr>
    <tr>
    	<td align="center">型号：</td>
		<td>
			<input type="text" name="burner_model" id="burner_model" value="${burner.burner_model}"/>
		</td>
    </tr>
    <tr>
    	<td align="center">地理位置：</td>
		<td>
			<input type="text" name="burner_location" id="burner_location" value="${burner.burner_location}"/>
		</td>
    </tr>
    <tr>
    	<td align="center"><font color="red">*</font>所属部门：</td>
		<td>
			<input type="text" id="dept_name" value="${burner.dept_name}" readonly="readonly" style="cursor:hand" onclick="openDeptSelect('dept_name','dept_id','radio')" />
			<input type="hidden" name="dept_id" id="dept_id" value="${burner.dept_id}"/>
		</td>
    </tr>
    <tr>
    	<td align="center"><font color="red">*</font>密级：</td>
	    <td>
	    	<c:set var="seclv1" value="${burner.seclv_code}" scope="request"/>
			<select name="seclv_code" id="seclv_code">
				<option value="">--请选择--</option>
				<s:iterator value="#request.seclvList" var="seclv">
					<option value="${seclv.seclv_code}" <s:if test="#request.seclv1==#seclv.seclv_code">selected="selected"</s:if>>${seclv.seclv_name}</option>
				</s:iterator>
			</select>
		</td>
    </tr>
    <tr>
    	<td align="center"><font color="red">*</font>控制台：</td>
	    <td>
	    	<c:set var="console1" value="${burner.console_code}" scope="request"/>
			<select name="console_code" id="console_code">
				<option value="">--请选择--</option>
				<s:iterator value="#request.consoleList" var="console">
					<option value="${console.console_code}" <s:if test="#request.console1==#console.console_code">selected="selected"</s:if>>${console.console_name}</option>
				</s:iterator>
			</select>
		</td>
    </tr>
    <tr>
    	<td align="center">一体机：</td>
	    <td>
	    	<c:set var="mfp1" value="${burner.mfp_code}" scope="request"/>
			<select name="mfp_code" id="mfp_code">
				<option value="">--请选择--</option>
				<s:iterator value="#request.mfpList" var="mfp">
					<option value="${mfp.mfp_code}" <s:if test="#request.mfp1==#mfp.mfp_code">selected="selected"</s:if>>${mfp.mfp_name}</option>
				</s:iterator>
			</select>
		</td>
    </tr>
    <tr>
        <td colspan="2" align="center" class="bottom_box">
            <input type="submit" value="修改" class="button_2003" onclick="return chk();">&nbsp;
            <input type="button" value="返回" class="button_2003" onclick="javascript:history.go(-1);">
        </td>
    </tr>
  	</table>
</form>
</center>
</body>
</html>