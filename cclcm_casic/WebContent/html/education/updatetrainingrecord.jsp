<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<link rel="stylesheet" type="text/css" href="${ctx}/_css/displaytag.css" media="screen"/>
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
		disableEnterSubmit();
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
			//alert("user_id:"+user_id+"---user_iidds:"+user_iidds+"---choose_name:"+choose_name);
			$("#edu_user").val(user_id);
			$("#choose_users").val(choose_name);
			$("#edu_user_iidds").val(user_iidds);
			document.getElementById("allOptions").innerHTML="";
		}
	}
	
	function chk()
	{	
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
		if($("#location").val().trim() == ""){
			alert("请填写培训机构");
			$("#location").focus();
			return false;
		}
		if($("#location").val().trim() == ""){
			alert("请填写培训证书编号");
			$("#location").focus();
			return false;
		} 
	/* 	if(!pattern.test($("#class_hour").val())){
	    	alert("学时必须是数字");
	    	$("#class_hour").focus();
	    	return false;
	    }  */
	    if(isNaN($("#class_hour").val())){
	    	alert("学时必须是数字");
	    	$("#class_hour").focus();
	    	return false;
	    } 
		return true;		
			
	}
</script>  
</head>
<body >
<form action="${ctx}/education/updatetrainingrecord.action" method="post">
<table width="95%" border="1" cellspacing="0" cellpadding="0" align="center" class="table_box">	
	<input type="hidden" id="course_id" name="course_id" value="${record.course_id}"/>	
	<input type="hidden" id="training_type" name="training_type" value="${record.training_type}"/>
	<input type="hidden" id="id" name="id" value="${id}"/>	
	<input type="hidden" value="Y" name="update"/>
	<tr>
	    <td colspan="6" class="title_box"><c:if test="${record.training_type == '1'}">修改集中培训记录</c:if><c:if test="${record.training_type == '2'}">修改外派培训记录</c:if></td>
	</tr>
	<tr> 
    	<td width="13%" align="center"><font color="red">*</font>培训名称 </td>
    	<td width="20%">
    		<input type="text" id="course_name" name="course_name" value="${record.course_name}"/><br>
		</td>
    	<td width="13%" align="center">记录类型 </td>
    	<td width="20%"><font color="blue"><b>&nbsp;${record.training_type_name}</b></font></td>	
    	<td width="13%" align="center"><font color="red">*</font>学时</td>
    	<td width="20%">
    		<input type="text" id="class_hour" name="class_hour" value="${record.class_hour}"/><br>
		</td>
   	</tr>
   	<tr>
		<td width="13%" align="center"><font color="red">*</font>开始时间 </td>
    	<td>
    		<input width="20%" type="text" id="start_time" name="start_time" value="${record.start_time_str}" onclick="WdatePicker()" class="Wdate" size="20"/>    	
	    </td>
		</td>
    	<td width="13%" align="center"><font color="red">*</font>结束时间 </td>
    	<td>
    		<input width="20%" type="text" id="end_time" name="end_time"  value="${record.end_time_str}" onclick="WdatePicker()" class="Wdate" size="20"/>    	
	    </td>
	    <td width="13%" align="center" ><font color="red">*</font>培训地点</td>
    	<td width="20%"><input type="text" id="location" name="location" value="${record.location}" /></td>
   	</tr>  
    <tr>
        <td width="13%" align="center">添加人员</td>
        <td  width="20%">
 		    <input type="text" id="edu_user" name="edu_user" onkeyup="selectRecvUser(this.value);" size="20"/>
 		    <br>
 		    <div id="allOptions" class="containDiv" style="position:absolute;border:0px solid black;padding:0px">
 		    </div>
        </td>
    	<td width="13%" align="center"> 已选择人员
    	</td>
    	<td width="20%">
    		<input type="text" readonly="true" name="choose_users" id="choose_users" value="${record.edu_user_names}" size="40">
    		<input type="hidden"  name="edu_user_iidds" id="edu_user_iidds" value="${record.edu_user_iidds}">
    	</td>
    	<td colspan='2' >&nbsp;</td>
    </tr>
    <tr>
    <c:if test="${record.training_type == '2'}">
        <tr>
		    <td width="13%" align="center"><font color="red">*</font>培训机构</td>
    	    <td><input width="20%" type="text" id="training_org" name="training_org" value="${record.training_org}"/></td>
    	    <td width="13%" align="center"><font color="red">*</font>培训证书编号</td>
    	    <td><input width="20%" type="text" id="certificate_no" name="certificate_no" value="${record.certificate_no}"/></td>
    	    <td width="13%" align="center">证书有效期</td>
            <td>
                <input width="20%" type="text" id="certificate_exp" name="certificate_exp" value="${record.certificate_exp_str}" onclick="WdatePicker()" class="Wdate" size="20"/>    	
	        </td>
   	    </tr>  
    </c:if> 
	<tr>
 		<td align="center">备注</td>
	   	<td colspan="5"><textarea name="summ" rows="4" cols="80" id="summ">${record.summ}</textarea></td>
	</tr>
  	<tr>
    <td colspan="6" align="center"> 
       <input type="submit" value="保存" class="button_2003" onclick="return chk();">&nbsp;
      <input type="button" value="返回" class="button_2003" onclick="history.go(-1);">
    </td>
  </tr>
</table>
</form>
</body>
</html>
