<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
<title>修改定密组织机构</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="${ctx}/_css/css200603.css" type="text/css" media="screen" />
	<script language="javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>
	<script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
	<script language="javascript" src="${ctx}/_script/casic304_ajax.js"></script>
	<script  language="JavaScript" >
	$(document).ready(function(){
		onHover();
	});
	
	function chk()
	{
	     if(!confirm("确定修改联系人？"))
	     {
	       return false;
	     }
		if($("#name").val().trim() == ""){
			alert("请填写联系人姓名");
			$("#name").focus();
			return false;
		}
		if($("#post").val().trim() == ""){
			alert("请填写职务");
			$("#post").focus();
			return false;
		}
/* 		if($("#mobile").val().trim() == ""){
			alert("请填写手机号码");
			$("#mobile").focus();
			return false;
		} */
		if($("#name").val().length > 255){
			alert("姓名不能超过255个字符");
			$("#name").focus();
			return false;
		}
		if($("#post").val().length > 1024){
			alert("职务不能超过1024个字符");
			$("#post").focus();
			return false;
		}
/* 		if($("#telephone").val().length > 16){
			alert("座机不能超过16个字符");
			$("#telephone").focus();
			return false;
		} */
/* 		if($("#mobile").val().length > 11){
			alert("请检查手机号码！");
			$("#mobile").focus();
			return false;
		} */
		if($("#address").val().length > 1024){
			alert("公办室不能超过255个字符");
			$("#address").focus();
			return false;
		}
		if($("#reark").val().length > 1024){
			alert("备注不能超过1024个字符");
			$("#reark").focus();
			return false;
		}
		if(!isInteger($("#rank").val())){
			alert("排序值只能是整数！")
			$("#rank").focus();
			return false;
		}
	    return true;
	}
	 function selectRecvUser(word){
		var url = "${ctx}/basic/getfuzzyuser.action?source=CR";
		if(word != ""){
			callServer1(url,"fuzzy="+word);
		}else{
			document.getElementById("name_id").innerHTML="";
		}
	}
	function updateResult(){
		if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
				if(xmlHttp.responseText.toString().length > 154){
					document.getElementById("name_id").innerHTML=xmlHttp.responseText;
				}else{
					document.getElementById("name_id").innerHTML="";
				}
			}else{
				document.getElementById("name_id").innerHTML="";
			}
	}
   function add_TrueCR(){
		var user_id=$("#allOptionCR").val();
		var user_name=$("#allOptionCR option[value='"+user_id+"']").text();
		user_name=user_name.substring(0,user_name.indexOf("/"));
		if(user_id != ""){
	    	$("#user_iidd").val(user_id);
			$("#name").val(user_name);
			document.getElementById("name_id").innerHTML="";
		}
	}
	</script>
</head>
<body oncontextmenu="self.event.returnValue=false">
<center>
<form method="post" action="${ctx}/securitydept/updatefixsecertuser.action">
  	<table class="table_box" cellspacing=0 cellpadding=0 border=1 width="95%">
	 <tr>
		 <td colspan="8" class="title_box">修改定密组织机构</td>
		 <input type="hidden" id="change" name="change" value="update"/>
		 <input type="hidden" id="id" name="id"  value="${contact.id}"/>
    </tr>
    <tr>
    	<td align="center" width="8%" ><font color="red">*</font>姓名</td>
		<td>
			<input  width="17%" type="text" id="name" name="name"   value="${contact.name}" onkeyup="selectRecvUser(this.value);"/>
			<input type="hidden" id="user_iidd" name="user_iidd"/><br>
    		<div id="name_id" class="containDiv" style="position:absolute;border:0px solid black;padding:0px">
    		</div>
		</td>
		<td align="center" width="8%" ><font color="red">*</font>职务</td>
		<td>
			<input type="text" width="17%" name="post" id="post" value="${contact.post}"/>
		</td>
		<td align="center" width="8%">所属机构</td>
		<td>
			<select name="type" id="type" >
    			<option value="5" <c:if test="${contact.type == '5'}">selected</c:if>>定密委员会</option>
    			<option value="6" <c:if test="${contact.type == '6'}">selected</c:if>>定密工作小组</option>
    			<option value="7" <c:if test="${contact.type == '7'}">selected</c:if>>法定代表人</option>
    			<option value="8" <c:if test="${contact.type == '8'}">selected</c:if>>定密责任人</option>
    		</select>
		</td>
	</tr>
    <tr>
<%-- 		<td align="center">手机</td>
	    <td>
			<input type="text" id="mobile" name= "mobile" value="${contact.mobile}" />
		</td> --%>
		
<%-- 		<td align="center" width="8%"></font>办公地点</td>
		<td>
			<input type="text" width="17%" name="address" id="address" value="${contact.address}"/>
		</td> --%>
		<td align="center">排序值</td>
		<td colspan="5">
			<input type="text" name="rank" id="rank"  value="${contact.rank}"/>
		</td>
    </tr>
<%--     <tr>
    	
		<td align="center">座机</td>
		<td>
			<input type="text" name="telephone" id="telephone" value="${contact.telephone}"/>
		</td>
		<td align="center">email</td>
	    <td>
			<input type="text" id="email" name= "email" value="${contact.email}" />
		</td>

    </tr> --%>
    <tr>
    	
    	<td align="center">职责</td>
		<td colspan="7"><textarea name="work" rows="4" cols="80" id="work">${contact.work}</textarea></td>
		
    </tr>
     <tr>
    	
		<td align="center">备注</td>
		<td colspan="7"><textarea name="reark" rows="4" cols="80" id="reark" >${contact.reark}</textarea></td>
		
    </tr>
    <tr>
        <td colspan="8" align="center" class="bottom_box">
            <input type="submit" value="修改" class="button_2003" onclick="return chk();">&nbsp;
            <input type="button" value="返回" class="button_2003" onclick="javascript:history.go(-1);">
        </td>
    </tr>
  	</table>
</form>
</center>
</body>
</html>