<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<link rel="stylesheet" type="text/css" href="${ctx}/_css/displaytag.css" media="screen"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/_css/css200603.css"  media="screen"/>
	<script language="JavaScript" src="${ctx}/_script/function.js"></script>
	<script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
 	<script language="javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>	
 	<script language="javascript" src="${ctx}/_script/casic304_ajax.js"></script>
 	<script language="JavaScript" src="${ctx}/_script/common.js"></script>
<script>
$(document).ready(function(){
	onHover();
	addSelectAllCheckbox();
});
	function clearFindForm(){
		$("#QueryCondForm :text").val("");
		$("#QueryCondForm select").val("");
	}
	
 var event_ids = "";
	var submitable = true;
	var event_delids = "";
	function addSpaceCDJob(){
		if($(":checkbox:checked[value!=''][name='event_id']").size()>0){
			$(":checkbox:checked[value!=''][name='event_id']").each(function (){
				event_ids += this.value+":";
				//seclv_code = this.value;
			});
			if(submitable){
				$("#event_ids").val(event_ids);
				//$("#seclv_code").val(seclv_code);
				$("#hid_form").submit();
			}else{
				return false;
			}
		}else{
			alert("请先勾选需要审批的作业");
			return false;
		}
	}


var pattern = /^[0-9]{0,5}$/;
function isInteger(value){
	if(!pattern.test(value)){
		return false;
	}
	if(parseInt(value)>1000 || parseInt(value)==0){
		return false;
	}
	return true;
}


	function deleteSpaceCDJob(){
	if($(":checkbox:checked[value!=''][name='event_id']").size()>0){

			$(":checkbox:checked[value!=''][name='event_id']").each(function (){
				event_delids += this.value+":";
			});
			if(submitable){
				$("#event_delids").val(event_delids);
				$("#deleteform").submit();
			}else{
				return false;
			}
		}else{
			alert("请先勾选需要审批的作业");
			return false;
		}
	}
function chk(){
	if($("#enter_num").val()==""){
		alert("申领的空白盘数量不能为空");
		return false;
	}
		if (Number($("#enter_num").val())>15){
		
		alert("单次申领数量不能超过15张");
		return false;
	}
	
	//判断空白盘数量是否合理
	var enter_num_wrong = null;
	var enter_num_zero = null;
	$("input[name='enter_num']").each(function(){
		if(!isInteger(this.value)&&parseInt(this.value)!=0){
			enter_num_wrong = this;
			return false;
		}
		if(parseInt(this.value)==0){
			enter_num_zero = this;
			return false;
		}
	});
	if(enter_num_wrong != null){
		alert("空白盘数量只能填写1~1000之间的数字");
		$(enter_num_wrong).focus();
		return false;
	}
	if(enter_num_zero != null){
		alert("空白盘数量不能为0");
		$(enter_num_zero).focus();
		return false;
	}
	
	
	if($("#scope_dept_id").val()==""){
		alert("请选择部门");
		return false;
	}
    return true;
}

   
</script>
</head>
<body oncontextmenu="self.event.returnValue=true">
<table width="95%" border="1" cellspacing="0" cellpadding="0" align="center" class="table_box">	
	<form action="${ctx}/disc/addapplyspacecdjob.action" method="get">
	<input type="hidden" name="event_code" value="${event_code}" id="event_code"/>
	<tr>
	    <td colspan="6" class="title_box">空白盘领用申请</td>
	</tr>
	<tr> 
    	<td width="20%" align="center">申请用户： </td>
    	<td width="30%"><font color="blue"><b>${curUser.user_name}</b></font></td>
    	<td width="20%" align="center">用户部门： </td>
    	<td width="30%"><font color="blue"><b>${curUser.dept_name}</b></font></td>
	</tr>
	<tr>
		<td width="20%" align="center">&nbsp;密级：</td>
	    <td width="30%">
			<select id="seclv_code" name="seclv_code">
				<option value="">--请选择--</option>
				<s:iterator value="#request.seclvList" var="seclv">
					<option value="${seclv.seclv_code}">${seclv.seclv_name}</option>
				</s:iterator>
			</select>
		</td> 
    	<td  width="20%" align="center"><font color="red">*</font>&nbsp;空白盘数量： </td>
    	<td  width="30%"><input type="text" name="enter_num" id="enter_num"/></td>    	  	
  	</tr>
  	<tr>
		<td width="20%" align="center">&nbsp;光盘类型：</td>
	    <td width="30%">
			<select id="cd_type" name="cd_type">
					<option value="">--请选择--</option>
					<option value="CD-R">CD-R</option>
					<option value="DVD-R">DVD-R</option>
			</select>
		</td> 
		<td width="20%" align="center">&nbsp;空白盘类型：</td>
	    <td width="30%">
			<select id="spacecd_type" name="spacecd_type">
					<option value="">--请选择--</option>
					<option value="0">空白盘</option>
					<option value="1">中转盘</option>
			</select>
		</td> 
    	<!-- <td align="center"><font color="red">*</font>&nbsp;归属部门：</td> -->
		<!-- <td> -->
		<!-- 	<select id="scope_dept_id" name="scope_dept_id"> -->
		<%-- <td width="30%"><font color="blue"><b>${curUser.dept_name}</b></font></td> --%>
			
				<%-- <option value="">--请选择--</option>
				<s:iterator value="#request.deptAdminList" var="groupscope">
					<option value="${groupscope.dept_id}">${groupscope.dept_name}</option>
				</s:iterator>
			</select> --%>
	<!-- 	</td> -->   
	</tr>
	<!-- <tr>	  	
  	  <td align="center">&nbsp;备注：</td>
		<td colspan="3"><textarea name="summ" rows="3" cols="40" id="summ"></textarea></td>
		</tr> -->
	</form>
<tr>
	<td colspan="6" align="center"> 
	      <input type="button" class="button_2003" value="添加" onclick="if(chk()) forms[0].submit();" id="submit_btn"/>&nbsp;
	  	    </td>
	</tr>
</table>
<form method="post" action="${ctx}/disc/delmultispacecdevent.action?" id="deleteform">
	<input type="hidden" name="event_delids" id="event_delids"/>	
</form>
<form method="post" action="${ctx}/disc/addspacecdprocessjob.action" id="hid_form">
	<input type="hidden" name="jobType" value="${jobType}"/>
	<input type="hidden" name="event_ids" id="event_ids"/>	
	<input type="hidden" name="seclv_code" id="seclv_code"/> 
	<input type="hidden" name="user_iidd" value="${curUser.user_iidd}"/>
	<input type="hidden" name="dept_id" value="${curUser.dept_id}"/>
	<input type="hidden" name="actionContext" value="disc/submitspacecdevent.action"/>
</form>
<table width="95%" border="0" cellspacing="0" cellpadding="0" align="center" class="table_only_border">
	<tr>
		<td class="title_box">已添加的空白盘领用申请列表</td>
	</tr>
	<tr>
		<td>
			<table class="displaytable-outter" cellspacing=0 cellpadding=0 border=0 width="100%">
	 			<tr>
	   				<td>
					<display:table uid="item" class="displaytable" name="eventList" sort="list">
						<display:column title="选择">
						<!-- 	<input type="checkbox" name="event_code" id="${item.event_code}" value="${item.seclv_code}"/>  -->
							<input type="checkbox" value="${item.id}" name="event_id"/>
						</display:column>
						<display:column title="序号">
							<c:out value="${item_rowNum}"/>
						</display:column>	
						<display:column title="密级" property="seclv_name"/>
						<display:column title="光盘类型" property="cd_type" maxLength="15"/>
						<display:column title="光空白盘类型" property="spacecd_type_name" maxLength="15"/>
						<display:column title="归属部门" property="scope_dept_name" maxLength="15"/>	
						<display:column title="申领数量" property="enter_num" />						
						<display:column title="申请时间" property="apply_time_str"/>
						<display:column title="操作" style="width:150px">
						<div>
							<input type="button" class="button_2003" value="查看" onclick="go('${ctx}/disc/viewborrowspacecd.action?event_code=${item.event_code}&op=view');"/>&nbsp;							
							<input type="button" class="button_2003" value="删除" onclick="if(confirm('确定要删除该申请？'))go('${ctx}/disc/delspacecdevent.action?event_code=${item.event_code}');"/>
						</div>
						</display:column>
					</display:table>
					</td>
				</tr>
			</table>
         </td>
	</tr>
	<tr>
	   <td>
	       <input type="button" value="批量提交" onclick="addSpaceCDJob();" class="button_2003"/>&nbsp;
	       <input type="button" value="批量删除" onclick="deleteSpaceCDJob();" class="button_2003"/>
	  </td>
	</tr>
</table>
</body>
</html>
