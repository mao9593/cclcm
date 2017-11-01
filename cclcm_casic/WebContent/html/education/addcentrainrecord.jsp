<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<title>添加集中培训记录</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <link rel="stylesheet" type="text/css" href="${ctx}/_css/css200603.css"  media="screen"/>
	<script language="JavaScript" src="${ctx}/_script/function.js"></script>
	<script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
	<script language="javascript" src="${ctx}/_script/casic304_common_bm.js"></script>
 	<script language="javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>	
 	<script language="javascript" src="${ctx}/_script/casic304_ajax.js"></script>
 	<script language="JavaScript" src="${ctx}/_script/common.js"></script>
 	<script language="javascript" src="${ctx}/_script/My97DatePicker/WdatePicker.js"></script>  
<script>
	$(document).ready(function(){
		onHover();
		document.getElementById("allOptions").innerHTML="";
	});

	function selectRecvUser(word){
		//$("#edu_user_iidd").val(" ");
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
			var user_iidds =  $("#edu_user_iidds").val() +user_id+",";
			$("#edu_user").val(user_id);
			$("#choose_users").val(choose_name);
			$("#edu_user_iidds").val(user_iidds);
			document.getElementById("allOptions").innerHTML="";
		}
	}

	function chk()
	{	
		if($("#course_id").val().trim() == ""){
			alert("请填写培训编号");
			$("#course_id").focus();
			return false;
		} 
		if($("#course_name").val().trim() == ""){
			alert("请填写培训名称");
			$("#course_name").focus();
			return false;
		}
		if($("#class_hour").val().trim() == ""){
			alert("请填写学时");
			$("#class_hour").focus();
			return false;
		}
		 if($("#start_time").val().trim() == ""){
			alert("请填写培训开始时间");
			$("#start_time").focus();
			return false;
		} 
			if($("#end_time").val().trim() == ""){
			alert("请填写培训结束时间");
			$("#end_time").focus();
			return false;
		} 
		if($("#location").val().trim() == ""){
			alert("请填写培训地点");
			$("#location").focus();
			return false;
		}	
		if($("#choose_users").val().trim() == ""){
			alert("请填写参训人员");
			$("#edu_user").focus();
			return false;
		}			
		 if(isNaN($("#class_hour").val())){
	    	alert("学时必须是数字");
	    	$("#class_hour").focus();
	    	return false;
	    } 
		  return true;	
	}
</script>  
</head>
<body  oncontextmenu="self.event.returnValue=true">
<center>
<form action="${ctx}/education/addcentrainrecord.action" method="post">	
	<input type="hidden" name="dept_id" id="dept_id" value="${curUser.dept_id}"/>
	<input type="hidden" name="training_type" id="training_type" value="1"/>
	<table width="95%" border="1" cellspacing="0" cellpadding="0" align="center" class="table_box">	
	<tr>
	    <td colspan="6" class="title_box">添加集中培训记录</td>
	</tr>
	<tr> 
    	<td width="13%" align="center"><font color="red">*</font>培训名称 </td>
    	<td width="20%"><input type="text" id="course_name" name="course_name"/></td>
		<td width="13%" align="center"><font color="red">*</font>培训编号 </td>
    	<td width="20%"><input type="text" id="course_id" name="course_id"/></td>
    	<td width="13%" align="center">培训类型 </td>
    	<td width="20%" ><font color="blue"><b>集中培训</b></font></td>	
   	</tr>
   	<tr>
		<td width="13%" align="center"><font color="red">*</font>开始时间 </td>
    	<td>
    		<input width="20%" type="text" id="start_time" name="start_time" onclick="WdatePicker()" class="Wdate" size="20"/>    	
	    </td>
    	<td width="13%" align="center"><font color="red">*</font>结束时间 </td>
    	<td>
    		<input width="20%" type="text" id="end_time" name="end_time" onclick="WdatePicker()" class="Wdate" size="20"/>    	
	    </td>
	    <td width="13%" align="center"><font color="red">*</font>学时</td>
    	<td width="21%"><input type="text" id="class_hour" name="class_hour"/></td>
   	</tr>  
     <tr>
       <td width="13%" align="center" ><font color="red">*</font>培训地点</td>
    	<td width="21%" ><input type="text" id="location" name="location"  /></td>		
            <td width="13%" align="center">添加人员</td>
            <td  width="20%">
	    		<input type="text" id="edu_user" name="edu_user" onkeyup="selectRecvUser(this.value);" size="20"/>
	    		 <br>
	    		 <div id="allOptions" class="containDiv" style="position:absolute;border:0px solid black;padding:0px">
	    		 </div>
		    </td>
        	<td width="13%" align="center"> <font color="red">*</font>已选择人员
        	</td>
        	<td width="20%">
        		<input type="text" readonly="true" name="choose_users" id="choose_users" size="40">
        		<input type="hidden"  name="edu_user_iidds" id="edu_user_iidds" >
        	</td>
        	<td width="21%" ></td>
        </tr>
	<tr>
 		<td align="center">备注</td>
	   	<td colspan="5"><textarea name="summ" rows="4" cols="80" id="summ"></textarea></td>
	</tr>	
  	<tr>
    <td colspan="6" align="center" class="bottom_box"> 
       <input type="submit" value="添加" class="button_2003" onclick="return chk();">&nbsp;
      <input type="button" value="返回" class="button_2003" onclick="history.go(-1);">
    </td>
  </tr>
</table>
</form>
</center>
</body>
</html>
