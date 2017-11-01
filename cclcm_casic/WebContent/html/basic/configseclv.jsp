<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
<title>配置密级信息</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="${ctx}/_css/css200603.css" type="text/css" media="screen"/>
	<script language="javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>
	<script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
	<script  language="JavaScript" >
	<!--
	$(document).ready(function(){
		onHover();
		if('${done}' == 'Y'){
			alert("配置成功");
		}
		if('${seclv.archive_time}' == '0'){
			$("#chk_archive").attr("checked",true);
			chkArchiveTime($("#chk_archive")[0]);
		}
		if(!${isBurnEnable}){
			$("#tr_burn").hide();
		}
	});
	function chk()
	{
		/* if($("#leak_time").val().trim() == '' || !isInteger($("#leak_time").val())){
			alert("最长解密期限输入不合理，请输入合理数字");
			$("#leak_time").focus();
			return false;
		} */
		if(!$("#chk_archive")[0].checked){
			if($("#archive_time").val().trim() == '' || !isInteger($("#archive_time").val())){
				alert("回收时间期限输入不合理，请输入合理数字");
				$("#archive_time").focus();
				return false;
			}
		}else{
			$("#archive_time").val("0");
		} 
		if($("#allow_reprint").val() == ''){
			alert("请选择补打模式");
			$("#allow_reprint").focus();
			return false;
		}
	    return true;
	}
	function chkArchiveTime(tag){
		if(tag.checked){
			$("#archive_time").attr("readonly",true);
		}else{
			$("#archive_time").attr("readonly",false);
		}
	}
	//-->
	</script>
</head>
<body oncontextmenu="self.event.returnValue=false">
<center>
<form method="post" action="${ctx}/basic/configseclv.action">
	<input type="hidden" value="${seclv.seclv_code}" name="seclv_code"/>
	<input type="hidden" value="Y" name="config"/>
   	<table class="table_box" cellspacing=0 cellpadding=0 border=1 width="80%">
	 <tr>
		 <td colspan="2" class="title_box">
            	配置密级信息
        </td>
    </tr>
    <tr>
    	<td align="center">密级名称：</td>
		<td><font color="blue"><b>${seclv.seclv_name}</b></font></td>
    </tr>
    <%-- <tr>
    	<td align="center"><font color="red">*</font>最长解密期限：</td>
		<td>
			<input type="text" name="leak_time" id="leak_time" value="${seclv.leak_time}"/>&nbsp;年
		</td>
    </tr> --%>
    <tr>
    	<td align="center"><font color="red">*</font>回收时间期限：</td>
		<td>
			<input type="text" name="archive_time" id="archive_time" value="${seclv.archive_time}"/>&nbsp;天&nbsp;&nbsp;
			<input title="0为不需要回收" type="checkbox" name="c_archive_time" value="0" onclick="chkArchiveTime(this);" id="chk_archive"/>不需回收
			<!-- <font color="red">(若勾选'不需回收'，系统不会对该密级载体向用户和管理员提醒留存超时)</font> -->
		</td>
    </tr>
    <tr>
    	<td align="center"><font color="red">*</font>补打模式：</td>
		<td>
			<select id="allow_reprint" name="allow_reprint">
				<option value="">--请选择--</option>
				<option value="0" <c:if test="${seclv.allow_reprint == '0'}">selected</c:if>>不允许补打</option>
				<option value="1" <c:if test="${seclv.allow_reprint == '1'}">selected</c:if>>允许补打</option>
			<%-- 	<option value="2" <c:if test="${seclv.allow_reprint == '2'}">selected</c:if>>需管理员参与</option> --%>
			</select>
		</td>
    </tr>
    <tr>
    	<td align="center"><font color="red">*</font>审计打印文件权限：</td>
		<td>
			<select id="is_paper_audit" name="is_paper_audit">
				<option value="N" <c:if test="${seclv.is_paper_audit == 'N'}">selected</c:if>>不允许审计</option>
				<option value="Y" <c:if test="${seclv.is_paper_audit == 'Y'}">selected</c:if>>允许审计</option>
			</select>
		</td>
    </tr>
    <tr id="tr_burn">
    	<td align="center"><font color="red">*</font>审计刻录文件权限：</td>
		<td>
			<select id="is_cd_audit" name="is_cd_audit">
				<option value="N" <c:if test="${seclv.is_cd_audit == 'N'}">selected</c:if>>不允许审计</option>
				<option value="Y" <c:if test="${seclv.is_cd_audit == 'Y'}">selected</c:if>>允许审计</option>
			</select>
		</td>
    </tr>
    <tr>
        <td colspan="2" align="center" class="bottom_box">
            <input type="button" value="保存" class="button_2003" onclick="if(chk()) forms[0].submit();">&nbsp;
            <input type="button" value="返回" class="button_2003" onclick="go('${ctx}/user/viewseclevel.action');">
        </td>
    </tr>
  	</table>
</form>
</center>
</body>
</html>