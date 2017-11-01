<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
<title>将存储介质分配给用户</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="${ctx}/_css/css200603.css" type="text/css" media="screen" />
	<script language="javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>
	<script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
	<script language="javascript" src="${ctx}/_script/casic304_ajax.js"></script>
	<script  language="JavaScript" >
	<!--
	$(document).ready(function(){
		onHover();
		disableEnterSubmit();
		document.getElementById("allOptions").innerHTML="";
	});
	function selectRecvUser(word){
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
			$("#use_user_iidd").val(user_id);
			$("#use_user_name").val(user_name);
			document.getElementById("allOptions").innerHTML="";
		}
	}
	function chkSubmit()
	{
		if($("#use_user_iidd").val().trim() == ""){
			alert("请选择用户");
			$("#use_user_name").focus();
			return false;
		}
		window.returnValue=$("#use_user_iidd").val().trim();
		window.close();
	    return true;
	}
	function clearInput(){
		$("#use_user_name").val("");
		$("#use_user_iidd").val("");
	}
	//-->
	</script>
</head>
<body oncontextmenu="self.event.returnValue=false">
<center>
  	<table class="table_box" cellspacing=0 cellpadding=0 border=1 width="95%" style="margin-top: 20px">
	 <tr>
		 <td colspan="2" class="title_box">
            	将存储介质分配给用户
        </td>
    </tr>
    <tr>
    	<td align="center"><font color="red">*</font>分配用户：</td>
    	<td>
    		<input type="text" id="use_user_name" name="use_user_name" onkeyup="selectRecvUser(this.value);" size="45"/>
    		<input type="hidden" id="use_user_iidd" name="use_user_iidd"/><br>
    		<div id="allOptions" class="containDiv" style="position:absolute;border:0px solid black;padding:0px">
    		</div>
	    </td>
    </tr>
    <tr>
        <td colspan="2" align="center" class="bottom_box">
            <input type="submit" value="确定" class="button_2003" onclick="return chkSubmit();">&nbsp;
            <input type="button" value="重置" class="button_2003" onclick="clearInput();">&nbsp;
            <input type="button" value="关闭" class="button_2003" onclick="window.close();">
        </td>
    </tr>
  	</table>
</center>
</body>
</html>