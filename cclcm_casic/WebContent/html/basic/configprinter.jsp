<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
<title>配置打印机</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="${ctx}/_css/css200603.css" type="text/css" media="screen" />
	<link rel="stylesheet" type="text/css" href="${ctx}/_css/displaytag.css" media="screen"/>
	<script language="javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>
	<script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
	<script language="javascript" src="${ctx}/_script/casic304_ajax.js"></script>
	<script  language="JavaScript" >
	$(document).ready(function(){
		onHover();
		document.getElementById("allOptions").innerHTML="";
	});
	function chk()
	{		
		
	    return true;
	}
	function selectRecvUser(word){
		$("#accept_user_iidd").val(" ");
		var url = "${ctx}/basic/getfuzzyuser.action";
		if(word != ""){
			callServer1(url,"fuzzy="+word);
		}else{
			document.getElementById("allOptions").innerHTML="";
		}
	}
	
	function updateResult(){
		if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
				if(xmlHttp.responseText.toString().length > 154){
					document.getElementById("allOptions").innerHTML=xmlHttp.responseText;
				}else{
					document.getElementById("allOptions").innerHTML="";
				}
			}else{
				document.getElementById("allOptions").innerHTML="";
			}
	}
	function add_True(){
		var user_id=$("#allOption").val();
		var user_name=$("#allOption option[value='"+user_id+"']").text();
		if(user_id != ""){
			var choose_name = $("#choose_users").val() + user_name.split("/")[0]+ ",";
			var user_iidds =  $("#user_iidds").val() +user_id+",";
			$("#accept_user_name").val(user_name);
			$("#choose_users").val(choose_name);
			$("#user_iidds").val(user_iidds);
			document.getElementById("allOptions").innerHTML="";
		}
	}
	</script>
</head>
<body oncontextmenu="self.event.returnValue=false">
<center>
<form method="post" action="${ctx}/basic/configprinter.action">
    <input type="hidden" value="${printer.printer_code}" name="printer_code"/>
    <input type="hidden" value="Y" name="update"/>
  	<table class="table_box" cellspacing=0 cellpadding=0 border=1 width="60%">
  	    <tr>
		    <td colspan="2" class="title_box">
            	配置打印机
            </td>
        </tr>
        <tr>
            <td align="center">当前打印机：</td>
            <td width="60%">
                <font color="blue"><b>${printer.printer_name}</b></font>
		    </td>
        </tr>
        <tr>
            <td align="center">为打印机分配部门：</td>
            <td width="60%">
		        <textarea width="100%" rows="10" cols="40" id="dept_name" name="dept_name" readonly="readonly" onclick="openDeptSelect('dept_name','dept_id','checkbox')">${dept_names}</textarea>
		        <input type="hidden" name="dept_id" id="dept_id" value="${dept_ids}"/>
		    </td>
        </tr>
        <tr>
            <td align="center">为打印机分配用户（独立模式）：</td>
            <td  width="60%">
	    		<input type="text" id="accept_user_name" name="accept_user_name" onkeyup="selectRecvUser(this.value);" size="45"/>
	    		 <br>
	    		 <div id="allOptions" class="containDiv" style="position:absolute;border:0px solid black;padding:0px">
	    		 </div>
		    </td>
        </tr>
        <tr>
        	<td align="center"> 已配置的用户：
        	<br>
        	<font color="red">（用户数量不能超过15）</font>
        	</td>
        	<td>
	        	<display:table id="item" class="displaytable" name="users">
					<display:column property="user_name" title="用户"/>
					<display:column title="操作">
					<input type="button" class="button_2003" value="删除" 
						onclick="go('${ctx}/basic/delprinteruser.action?user_iidd=${item.user_iidd}&printer_code=${item.printer_code}');"/>&nbsp;
					</display:column>
				</display:table>
        	<!--  &nbsp;<div id="choosedUsers">
	        	<s:iterator value="#request.users" var="user">
	        		<font color="blue">${user.user_name} &nbsp;</font>
	        	</s:iterator>
        	</div>-->
        	</td>
        </tr>
        <tr>
        	<td align="center"> 待配置的用户：
        	</td>
        	<td width="60%">
        		<input type="text" readonly="true" name="choose_users" id="choose_users">
        		<input type="hidden"  name="user_iidds" id="user_iidds" >
        	</td>
        </tr>
        <tr>
            <td colspan="2" align="center" class="bottom_box">
                <input type="submit" value="保存" class="button_2003" onclick="return chk();">&nbsp;
                <input type="button" value="返回" class="button_2003" onclick="go('${ctx}/basic/manageprinter.action?printer_code=${item.printer_code}');">
            </td>
        </tr>
  	</table>
</form>
</center>
</body>
</html>