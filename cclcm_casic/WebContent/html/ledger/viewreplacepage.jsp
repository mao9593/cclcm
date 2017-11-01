<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
  <head>
   <title>替换页监销申请台账列表</title>
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
		preCalendar();
		optionSelect();
	});
	function optionSelect(){
		$("#seclv_med").val(${seclv_code});
	}
	function clearFindForm(){
		$("#LedgerQueryCondForm :text").val("");
		$("#LedgerQueryCondForm select").val("");
	}	
	function destroy_paper(paper_barcode, paper_id) {
		$("#_chk").val(paper_barcode);
		$("#paper_id").val(paper_id);
		var url = "${ctx}/ledger/replacepageretrieve.action?paper_id="+paper_id;
		var rValue = window.showModalDialog(url,'', 'dialogHeight:280px;dialogWidth:480px;center:yes;status:no;scroll:no;help:no;unadorned:no;resizable:no');
		if(rValue!=null &&　rValue!=undefined){
			$("#retrieve_pagenum").val(rValue.retrieve_pagenum);
			$("#fail_remark").val(rValue.fail_remark);
			//$("#retrieveForm").submit();
		}
		$("#LedgerQueryCondForm").attr("action","${ctx}/basic/handlepaperjob.action");
		$("#LedgerQueryCondForm").submit();
	}
	
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
		var duty_user_iidd=$("#allOption").val();
		var duty_user_name=$("#allOption option[value='"+duty_user_iidd+"']").text();
		duty_user_name=duty_user_name.substring(0,duty_user_name.indexOf("/"));
		if(duty_user_iidd != ""){
			$("#duty_user_iidd").val(duty_user_iidd);
			$("#duty_user_name").val(duty_user_name);
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
	</script>
  </head>
  <body oncontextmenu="self.event.returnValue=false">
  	
<%-- <form id="retrieveForm" method="POST" action="${ctx}/ledger/replacepageretrieve.action">
	 	<input type="hidden" name="retrieved_ok" value="Y"/>
	 	<input type="hidden" name="retrieve_pagenum" id="retrieve_pagenum"/>
	 	<input type="hidden" name="fail_remark" id="fail_remark"/>
	 	<input type="hidden" name="paper_id" id="paper_id"/>
	</form> --%>
  	<form id="LedgerQueryCondForm" method="GET" action="${ctx}/ledger/viewreplacepage.action">
  		<input type="hidden" name="_chk" id="_chk"/>
  		<input type="hidden" name="jobType" value="DESTROY_PAPER_BYSELF"/>
  		<input type="hidden" name="replacePageFlag" value="Y"/>
  		<input type="hidden" name="retrieve_pagenum" id="retrieve_pagenum"/>
	 	<input type="hidden" name="fail_remark" id="fail_remark"/>
	 	<input type="hidden" name="paper_id" id="paper_id"/>
	<table width="95%" border="0" cellspacing="0" cellpadding="0" align="center" class="table_only_border">
	<tr>
		<td class="title_box">替换页监销申请台账列表</td>
	</tr>
	<tr>
		<td align="right">
			<table  table border=0 class="table_box" cellspacing="0" cellpadding="0" width="100%">
				<tr>
					<td align="center">文件条码：
					</td>
					<td>
						<input type="text" id="paper_barcode" name="paper_barcode" value="${paper_barcode}" size="15"/>&nbsp;
					</td>
					<%-- <td align="center">责任人：
					</td>
					<td>
						<input type="text" id="duty_user_name" name="duty_user_name" value="${duty_user_name}" onkeyup="selectRecvUser(this.value);"/><br>
    	        	    <div id="allOptions" class="containDiv" style="position:absolute;border:0px solid black;padding:0px">
    	        	    </div>
					</td> --%>
					<td align="center">制作人：
					</td>
					<td>
						<input type="text" id="user_name" name="user_name" value="${user_name}" onkeyup="selectRecvUserCR(this.value);"/><br>
    		            <div id="allOptionsCR" class="containDiv" style="position:absolute;border:0px solid black;padding:0px">
    		            </div>
					</td>
						<td align="center">制作部门：
					</td>
					<td>
						<input type="text" id="dept_name" name="dept_name" value="${dept_name}"/>&nbsp;
					</td>
				</tr>
				<tr>
				   <td align="center">密级：
					</td>
					<td>
						<select name="seclv_code" id="seclv_med">
						<option value="">--所有--</option>
						<s:iterator value="#request.seclvList" var="seclv">
							<option value="${seclv.seclv_code}">${seclv.seclv_name}</option>
						</s:iterator>
						</select>
					</td>
					<td align="center">产生时间：</td>
					<td>
						<input type="text" name="startTime" readonly="readonly" style="cursor:hand;" value="${startTime}"/>
        				<img src="${ctx}/_image/time2.jpg" id="startTime_trigger">
					</td>
					<td align="center">至：</td>
					<td>
						<input type="text" name="endTime" readonly="readonly" style="cursor:hand;" value="${endTime}"/>
        				<img src="${ctx}/_image/time2.jpg" id="endTime_trigger">
					</td>
					<td align="center" colspan="2"> 
						<input name="button" type="submit" class="button_2003" value="查询" onclick="return checkTime();">
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
	   				<display:table requestURI="${ctx}/ledger/viewdestroyreplacepage.action" id="item" class="displaytable" name="paperLedgerList"
	   					pagesize="${pageSize}" sort="page" partialList="true" size="${totalSize}" excludedParams="*" form="LedgerQueryCondForm">
						<display:column title="序号">
							<c:out value="${item_rowNum}"/>
						</display:column>
						<display:column property="duty_user_name" title="责任人"/>
						<display:column property="duty_dept_name" title="责任人部门"/>
						<display:column property="file_title" title="文件名称"/>
						<display:column property="paper_barcode" title="文件条码"/>
						<display:column property="seclv_name" title="文件密级"/>
						<display:column property="page_count" title="页数"/>
						<display:column property="user_name" title="制作人"/>
						<display:column property="dept_name" title="制作人部门"/>
						<display:column property="print_time" sortable="true" title="制作时间"/>
						<display:column title="替换页监销情况">
							<c:choose>
								<c:when test="${item.retrieve_replace=='ALL_DESTROYED'}">
									<c:out value="已全部监销"/>
								</c:when>
								<c:otherwise>
									<c:choose>
										<c:when test="${item.retrieve_replace=='SUB_RETRIEVED-SUB_DESTROYED' or item.retrieve_replace=='ALL_RETRIEVED-SUB_DESTROYED' or (not empty item.destroy_pagenum)}">
											<c:out value="已部分监销"/>
										</c:when>
										<c:otherwise>
											<c:out value="尚未监销"/>
										</c:otherwise>
									</c:choose>
								</c:otherwise>
							</c:choose>
						</display:column>
						<display:column title="操作" style="width:150px">
							<input type="button" class="button_2003" value="详细" onclick="go('${ctx}/ledger/viewcycledetail.action?type=PAPER&printType=replacePage&barcode=${item.paper_barcode}');"/>						
							<c:choose>
								<c:when test="${item.retrieve_replace=='ALL_DESTROYED' || item.retrieve_replace=='ALL_RETRIEVED' || item.retrieve_replace=='ALL_RETRIEVED-SUB_DESTROYED'}">
									<input type="button" class="button_2003" value="申请监销" disabled=true/>
								</c:when>
								<c:otherwise>
									<input type="button" class="button_2003" value="申请监销" onclick="destroy_paper('${item.paper_barcode}', ${item.paper_id})"/>
								</c:otherwise>
							</c:choose>
						</display:column>
					</display:table>
					</td>
				</tr>
			</table>
	     </td>
	</tr>
	</table>
</form>
  </body>
</html>
