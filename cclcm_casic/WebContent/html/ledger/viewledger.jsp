<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<title>光盘总台账列表</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="${ctx}/_css/css200603.css" type="text/css" media="screen" />
		<link rel="stylesheet" type="text/css" href="${ctx}/_css/displaytag.css" media="screen"/>
	<link href="${ctx}/_script/calendar2/calendar-blue.css" type="text/css" rel="stylesheet"/>
	<script language="javascript" src="${ctx}/_script/calendar2/calendar.js"></script>
	<script language="javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>
	<script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
	<script language="javascript" src="${ctx}/_script/casic304_ajax.js"></script>
	<script>
	$(document).ready(function(){
			onHoverInfinite();
			preCalendarDay();
			optionSelect();
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
	function selectRecvUserCR(word){
	    var url = "${ctx}/basic/getfuzzyuser.action?source=CR";
	    if(word != ""){
		   callServer3(url,"fuzzy="+word);
	    }else{
		   document.getElementById("allOptionsCR").innerHTML="";
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
	function updateResult1(){
		if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
				if(xmlHttp.responseText.toString().length > 154){
					document.getElementById("allOptionsCR").innerHTML=xmlHttp.responseText;
				}else{
					document.getElementById("allOptionsCR").innerHTML="";
				}
			}else{
				document.getElementById("allOptionsCR").innerHTML="";
			}
	}
	function add_True(){
		var duty_user_iidd=$("#allOption").val();
		var duty_user_name=$("#allOption option[value='"+duty_user_iidd+"']").text();
		duty_user_name=duty_user_name.substring(0,duty_user_name.indexOf("/"));
		if(duty_user_iidd != ""){
			$("#duty_user_iidd").val(duty_user_iidd);
			$("#duty_user_name").val(duty_user_name);
			document.getElementById("allOptions").innerHTML="";
		}
	}
	function add_TrueCR(){
		var user_id=$("#allOptionCR").val();
		var user_name=$("#allOptionCR option[value='"+user_id+"']").text();
		user_name=user_name.substring(0,user_name.indexOf("/"));
		if(user_id != ""){
	    	$("#user_id").val(user_id);
			$("#user_name").val(user_name);
			document.getElementById("allOptionsCR").innerHTML="";
		}
	}
	function preCalendarDay(){
		Calendar.setup({inputField: "start_time", button: "start_time_trigger", singleClick: true, showsTime: true, ifFormat: "%Y-%m-%d %H:%M:%S", cache: true, weekNumbers:true, showOthers: true, step: 1});
		Calendar.setup({inputField: "end_time", button: "end_time_trigger", singleClick: true, showsTime: true, ifFormat: "%Y-%m-%d %H:%M:%S", cache: true, weekNumbers:true, showOthers: true, step: 1});
	}	
	function clearFindForm(){
		$("#LedgerQueryCondForm :text").val("");
		//mode by wx 20150505 修复子部门单选框无法复位问题 修复连续点击“清空”按钮出现重置数据不正常问题
		//$("#LedgerQueryCondForm select").val("");
		//$("#LedgerQueryCondForm :hidden").val("");
		$("#create_type").val("");
		//$("#seclv_code").val("");
		$(":checkbox[name='seclv_code']").attr("checked",false);
		$("#cd_state_id").val("");
		$("#expire_status").val("");
		$(":checkbox[name='chooseChildDept']").attr("checked",false);
		$("#duty_dept_id").val("");
		$("#duty_dept_name").val("");
		$("#dept_iidd").val("");
		//above	
	}	
	function optionSelect(){
		//$("#seclv_code").val("${seclv_code}");
		var codes='${seclv_code}';
			if($(":checkbox[name='seclv_code']").size()>0){
			  $(":checkbox[name='seclv_code']").each(function(){
			    var code=codes.split(",");
			    for(var i=0;i<code.length;i++){
			      if(code[i].trim()==this.value){
			       $(":checkbox[name='seclv_code'][value='"+this.value+"']").attr("checked",true);
			      }
			    }
			  
			  })
		}
		$("#cd_state_id").val("${cd_state}");
		$("#data_type").val("${data_type}");
	}
	function exportLedger(formId,url,url1){
			document.getElementById(formId).action = url;
			document.getElementById(formId).submit();
			document.getElementById(formId).action = url1;
	}
	function checkDateTime(){
	if($("input[name='start_time']").val() != "" && $("input[name='end_time']").val() != ""){
		var startTimeInput = $("input[name='start_time']").val();
		var endTimeInput = $("input[name='end_time']").val();
		var startYear = startTimeInput.substring(0,4);
		var endYear = endTimeInput.substring(0,4);
		var startTime = startTimeInput.substr(5,5)+"-"+startYear+startTimeInput.substr(10);
		var endTime = endTimeInput.substr(5,5)+"-"+endYear+endTimeInput.substr(10);
		var startLong = Date.parse(startTime);
		var endLong = Date.parse(endTime);
		if(startLong != NaN && endLong != NaN && startLong > endLong){
			alert("起止时间查询条件设置不合理，请修改");
			//$("#researchFlag").val("N");
			return false;
		}
	}
	var dept_id=$("#dept_id").val();
	if(dept_id==""||dept_id==null){
	$("#researchFlag").val("Y");
	}
	return true;
}
	</script>
</head>

<body oncontextmenu="self.event.returnValue=false">
<form id="LedgerQueryCondForm" method="GET" action="${ctx}/ledger/viewledger.action">
    <input type="hidden" id="dept_id" name="dept_id" value="${dept_id}"/>
	<input type="hidden" id="researchFlag" name="researchFlag" value="${researchFlag}"/>
	<table width="95%" border="0" cellspacing="0" cellpadding="0" align="center" class="table_only_border">
	<tr>
		<td class="title_box">光盘总台账列表</td>
	</tr>
	<tr>
		<td align="right">
			<table  table border=0 class="table_box" cellspacing="0" cellpadding="0" width="100%">
				<tr>
					<td align="center">责任人：</td>
					<td>	
					<!--   
					<input type="text" id="duty_user_name" name="duty_user_name" value="${duty_user_name}"  size="15"/>	
				    -->  
    		       <input type="text" id="duty_user_name" name="duty_user_name" value="${duty_user_name}" onkeyup="selectRecvUser(this.value);"/><br>
    		       <div id="allOptions" class="containDiv" style="position:absolute;border:0px solid black;padding:0px">
    	        	</div>
					</td>
					<td align="center">责任人部门：</td>
					<td>		
						<input type="text"  id="duty_dept_name" name="duty_dept_name" readonly="readonly" style="cursor:hand;" onclick="openDeptSelect('duty_dept_name','duty_dept_id','radio')" value="${duty_dept_name}"/>
		      			<input type="hidden" name="duty_dept_id" id="duty_dept_id" value="${duty_dept_id}"/>	      			
		      			子部门	      			
		      			<c:choose>
		      				<c:when test="${chooseChildDept=='yes' }">
		      					<input type="checkbox" name="chooseChildDept" value="yes" checked="checked"/>
		      				</c:when>
		      				<c:otherwise>
		      					<input type="checkbox" name="chooseChildDept" value="yes"/>
		      				</c:otherwise>
		      			</c:choose>	      			
					</td>
					<td align="center">光盘条码：</td>
					<td>
						<input type="text" id="cd_barcode" name="cd_barcode" value="${cd_barcode}"/>
					</td>
				</tr>
				<tr>
					<td align="center">保密编号：</td>
					<td>
						<input type="text" id="conf_code" name="conf_code" value="${conf_code}"/>
					<td align="center">制作人：</td>
					<td>
					    <!--  
						<input type="text" id="user_name" name="user_name" value="${user_name}"/>&nbsp;
						-->
						<input type="text" id="user_name" name="user_name" value="${user_name}" onkeyup="selectRecvUserCR(this.value);"/><br>
    		            <div id="allOptionsCR" class="containDiv" style="position:absolute;border:0px solid black;padding:0px"></div>
					</td>
					<td align="center">制作部门：</td>
					<td>
						<input type="text" id="dept_name" name="dept_name" readonly="readonly" style="cursor:hand;" onclick="openDeptSelect('dept_name','dept_iidd','radio')" value="${dept_name}"/>
		      			<input type="hidden" name="dept_iidd" id="dept_iidd" value="${dept_iidd}"/>
					</td>
				</tr>
				<tr>
					<td align="center">制作方式：</td>
					<td>
						<select name="create_type" id="create_type">
							<option value="">--所有--</option>
							<option value="BURN" <c:if test="${create_type eq 'BURN'}">selected</c:if>>刻录</option>
							<option value="LEADIN" <c:if test="${create_type eq 'LEADIN'}">selected</c:if>>录入</option>
						</select>
					</td>
					<td align="center">开始时间：</td>
					<td>
						<input type="text" name="start_time" readonly="readonly" style="cursor:hand;" value="${start_time}"/>
        				<img src="${ctx}/_image/time2.jpg" id="start_time_trigger">
					</td>
					<td align="center">结束时间：</td>
					<td>
						<input type="text" name="end_time" readonly="readonly" style="cursor:hand;" value="${end_time}"/>
        				<img src="${ctx}/_image/time2.jpg" id="end_time_trigger">
					</td>
				</tr>
				<tr>
				    <td align="center">密级：</td>
					<td>
						<%-- <select name="seclv_code" id="seclv_code">
						<option value="">--所有--</option>
						<s:iterator value="#request.seclvList" var="seclv">
							<option value="${seclv.seclv_code}">${seclv.seclv_name}</option>
						</s:iterator>
						</select> --%>
						<s:iterator value="#request.seclvList" var="seclv">
							<input type="checkbox" value="${seclv.seclv_code}" name="seclv_code" id="seclv_code"/>${seclv.seclv_name}
						</s:iterator>
					</td>
					<td align="center">到期状态：</td>
					<td>
			    		<select name="expire_status" id="expire_status">
			    			<option value="">--所有--</option>
			    			<option value="0" <c:if test="${expire_status ==0}">selected</c:if>>未到期</option>
			    			<option value="2" <c:if test="${expire_status ==2}">selected</c:if>>即将到期</option>
			    			<option value="1" <c:if test="${expire_status ==1}">selected</c:if>>已到期</option>
			    		</select>
					</td>
					<td align="center">状态：</td>
					<td>
						<select name="cd_state" id="cd_state_id">
							<option value="">--所有--</option>
							<s:iterator value="#request.stateList" var="state">
								<option value="${state.key}">${state.name}</option>
							</s:iterator>
						</select>
						&nbsp;&nbsp;&nbsp;
						<input name="button" type="submit" class="button_2003" value="查询" onclick="return checkDateTime();">&nbsp;
						<input name="button" type="button" class="button_2003" value="清空" onclick="clearFindForm();">
					</td> 
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td>
			<table class="displaytable-outter" cellspacing=0 cellpadding=0 border=0 width="100%">
	 			<tr>
	   				<td>
	   					<display:table requestURI="${ctx}/ledger/viewledger.action" id="item" class="displaytable" name="cdLedgerList"
	   					pagesize="${pageSize}" sort="page" partialList="true" size="${totalSize}" excludedParams="*" form="LedgerQueryCondForm">
						<display:column title="序号">
							<c:out value="${item_rowNum}"/>
						</display:column>
						<display:column property="duty_user_name" title="责任人"/>
						<display:column property="duty_dept_name" title="责任人部门"/>
						<display:column property="user_name" title="制作人"/>
						<display:column property="dept_name" title="制作人部门"/>
						<display:column property="file_list" title="文件列表" maxLength="30"/>
						<display:column property="cd_barcode" title="光盘条码"/>
						<display:column property="conf_code" title="保密编号"/>
						<display:column property="seclv_name" title="光盘密级"/>
						<display:column property="create_type_name" title="制作方式"/>
						<display:column property="cd_state_name" title="状态"/>
						<display:column title="到期状态">							
			   					<c:choose>
									<c:when test="${item.expire_status == 1}">
										<font color="red">${item.expire_status_name}</font>
									</c:when>
									<c:when test="${item.expire_status == 2}">
										<font color="orange">${item.expire_status_name}</font>
									</c:when>
									<c:otherwise>
										${item.expire_status_name}
									</c:otherwise>
								</c:choose>
							</display:column>
						<display:column property="burn_time" sortable="true" title="制作时间"/>
						<display:column title="操作" style="width:60px">
							<input type="button" class="button_2003" value="详细" onclick="go('${ctx}/ledger/viewcycledetail.action?type=DISC&barcode=${item.cd_barcode}&ledger_type=total');"/>
						</display:column>
					</display:table>
					</td>
				</tr>
			</table>
         </td>
	</tr>
	<tr><td><input type="button" class="button_2003" value="导出EXCEL" onclick="exportLedger('LedgerQueryCondForm','${ctx}/ledger/exportcdledger.action','${ctx}/ledger/viewledger.action');"/></td></tr>
	<c:choose>
		<c:when test="${researchFlag eq 'WARING'}">
			<tr> 
		    	<td align="center"><font style="color:red;font-size:15">请选择需要的查询条件，点击“查询”按钮查询光盘台账！</font></td>	    	
			</tr>
		</c:when>
	</c:choose>
</table>
</form>
<table width="95%" border="0" cellspacing="0" cellpadding="0" align="center" class="table_only_border">
	<tr>
		<td class="title_box">导入单机刻录台账</td>
	</tr>
	<tr style="padding-top: 20px;">
		<td align="center">
		<form id="TemplateQueryCondForm" method="POST" action="${ctx}/ledger/viewledger.action" enctype="multipart/form-data">
			<input type="hidden" name="imFlag" value="Y"/>
 			导入台账文件：<input type="file" name="upload" class="button_2003"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
 				<input type="submit" class="button_2003" value="提交">&nbsp;&nbsp;
 				<br><br>
 				<font color="red">注意：导入前请确保文件中的用户名、部门名称、密级名称准确且在网络版系统组织机构中存在！</font>
 		</form>
		</td>
	</tr>
</table>
</body>
</html>
