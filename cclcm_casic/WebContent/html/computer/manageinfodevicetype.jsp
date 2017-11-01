<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="${ctx}/_css/displaytag.css" media="screen"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/_css/css200603.css"  media="screen"/>
    <script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
    <script language="javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>
</head>
<script language="javascript">
	$(document).ready(function(){
		onHover();
	});
	function deleteUserpost(post_id){
		    var url="${ctx}/computer/manageinfodevicetype.action?type=D&id="+escape(post_id);
		    if(confirm("确定停用该类型？")){
				go(url);
		    }
		}
		function recoverUserpost(post_id){
		   var url="${ctx}/computer/manageinfodevicetype.action?type=R&id="+escape(post_id);
		    if(confirm("确定启用该类型？")){
				go(url);
		    }
		}
			
</script>
<body oncontextmenu="self.event.returnValue=false">
<table width="80%" border="0" cellspacing="1" cellpadding="1" height="100%" align="center">
  <tr> 
    <td height="450" align="center" valign="top">       
        <table width="95%" border="0" cellspacing="0" cellpadding="0" align="center" class="table_only_border">
          <tr class="layouttr">
            <td colspan="4" class="title_box" >信息设备分类及编号规则一览表</td>
          </tr>
          <tr>
			  <td colspan="4" align="right">
<%-- 			  	状态&nbsp;&nbsp;
		  		<select name="is_sealed" id="is_sealed">
	                <option value="">--所有--</option>
					<option value="N" <c:if test="${is_sealed == 'N'}">selected="selected"</c:if>>已启用</option>
					<option value="Y" <c:if test="${is_sealed == 'Y'}">selected="selected"</c:if>>已停用</option>
	            </select>&nbsp;&nbsp;
	            <input name="button" type="submit" class="button_2003" value="查询">
				<input name="button" type="button" class="button_2003" value="清空" onclick="clearFindForm();">&nbsp;&nbsp;&nbsp;&nbsp;
 --%>				<input name="button" type="button" class="button_2003" onClick="go('${ctx}/computer/addinfotype.action');" value="增加新类型">&nbsp;&nbsp;
			  </td>
          </tr>
   	<tr>
		<td colspan="4">
			<table class="displaytable-outter" cellspacing=0 cellpadding=0 border=0 width="100%">
			    <tr>
				    <td>
						<display:table uid="type" class="displaytable" name="infotype1" excludedParams="*" form="QueryCondForm">
							<display:column title="设备类型" style="width:120px">计算机</display:column>
							<display:column property="info_id" title="编号" style="width:80px"/>
							<display:column property="info_type" title="类型名称" style="width:210px"/>
							<display:column title="类型描述" style="width:210px">&nbsp;${type.summ}</display:column>
							<display:column title="操作" style="width:150px">
								&nbsp;<input type="button" class="button_2003" value="修改" onclick="go('${ctx}/computer/updateinfotype.action?id=${type.info_id}');"/>&nbsp;
								<c:choose>
									<c:when test="${type.statue == 0}">
										<input type="button" class="button_2003" value="停用" onclick="deleteUserpost('${type.info_id}')";/>
										<input type="button" class="button_2003" value="启用" disabled="disabled"/>
									</c:when>
									<c:otherwise>
										<input type="button" class="button_2003" value="停用" disabled="disabled"/>
										<input type="button" class="button_2003" value="启用" onclick="recoverUserpost('${type.info_id}')";/>
									</c:otherwise>
								</c:choose>
							</display:column>
						</display:table>
					</td>
				</tr>
			</table>
		</td>
   	</tr>          
   	<tr>
		<td colspan="4">
			<table class="displaytable-outter" cellspacing=0 cellpadding=0 border=0 width="100%">
			    <tr>
				    <td>
						<display:table uid="type" class="displaytable" name="infotype2" excludedParams="*" form="QueryCondForm">
							<display:column title="设备类型" style="width:120px">网络设备</display:column>
							<display:column property="info_id" title="编号" style="width:80px"/>
							<display:column property="info_type" title="类型名称" style="width:210px"/>
							<display:column title="类型描述" style="width:210px">&nbsp;${type.summ}</display:column>
							<display:column title="操作" style="width:150px">
								&nbsp;<input type="button" class="button_2003" value="修改" onclick="go('${ctx}/computer/updateinfotype.action?id=${type.info_id}');"/>&nbsp;
								<c:choose>
									<c:when test="${type.statue == 0}">
										<input type="button" class="button_2003" value="停用" onclick="deleteUserpost('${type.info_id}')";/>
										<input type="button" class="button_2003" value="启用" disabled="disabled"/>
									</c:when>
									<c:otherwise>
										<input type="button" class="button_2003" value="停用" disabled="disabled"/>
										<input type="button" class="button_2003" value="启用" onclick="recoverUserpost('${type.info_id}')";/>
									</c:otherwise>
								</c:choose>
							</display:column>
						</display:table>
					</td>
				</tr>
			</table>
		</td>
   	</tr>
   	<tr>
		<td colspan="4">
			<table class="displaytable-outter" cellspacing=0 cellpadding=0 border=0 width="100%">
			    <tr>
				    <td>
						<display:table uid="type" class="displaytable" name="infotype3" excludedParams="*" form="QueryCondForm">
							<display:column title="设备类型" style="width:120px">外部设备</display:column>
							<display:column property="info_id" title="编号" style="width:80px"/>
							<display:column property="info_type" title="类型名称" style="width:210px"/>
							<display:column title="类型描述" style="width:210px">&nbsp;${type.summ}</display:column>
							<display:column title="操作" style="width:150px">
								&nbsp;<input type="button" class="button_2003" value="修改" onclick="go('${ctx}/computer/updateinfotype.action?id=${type.info_id}');"/>&nbsp;
								<c:choose>
									<c:when test="${type.statue == 0}">
										<input type="button" class="button_2003" value="停用" onclick="deleteUserpost('${type.info_id}')";/>
										<input type="button" class="button_2003" value="启用" disabled="disabled"/>
									</c:when>
									<c:otherwise>
										<input type="button" class="button_2003" value="停用" disabled="disabled"/>
										<input type="button" class="button_2003" value="启用" onclick="recoverUserpost('${type.info_id}')";/>
									</c:otherwise>
								</c:choose>
							</display:column>
						</display:table>
					</td>
				</tr>
			</table>
		</td>
   	</tr>
   	<tr>
		<td colspan="4">
			<table class="displaytable-outter" cellspacing=0 cellpadding=0 border=0 width="100%">
			    <tr>
				    <td>
						<display:table uid="type" class="displaytable" name="infotype4" excludedParams="*" form="QueryCondForm">
							<display:column title="设备类型" style="width:120px">办公自动化设备</display:column>
							<display:column property="info_id" title="编号" style="width:80px"/>
							<display:column property="info_type" title="类型名称"  style="width:210px"/>
							<display:column title="类型描述" style="width:210px">&nbsp;${type.summ}</display:column>
							<display:column title="操作" style="width:150px">
								&nbsp;<input type="button" class="button_2003" value="修改" onclick="go('${ctx}/computer/updateinfotype.action?id=${type.info_id}');"/>&nbsp;
								<c:choose>
									<c:when test="${type.statue == 0}">
										<input type="button" class="button_2003" value="停用" onclick="deleteUserpost('${type.info_id}')";/>
										<input type="button" class="button_2003" value="启用" disabled="disabled"/>
									</c:when>
									<c:otherwise>
										<input type="button" class="button_2003" value="停用" disabled="disabled"/>
										<input type="button" class="button_2003" value="启用" onclick="recoverUserpost('${type.info_id}')";/>
									</c:otherwise>
								</c:choose>
							</display:column>
						</display:table>
					</td>
				</tr>
			</table>
		</td>
   	</tr>
   	<tr>
		<td colspan="4">
			<table class="displaytable-outter" cellspacing=0 cellpadding=0 border=0 width="100%">
			    <tr>
				    <td>
						<display:table uid="type" class="displaytable" name="infotype5" excludedParams="*" form="QueryCondForm">
							<display:column title="设备类型" style="width:120px">安全产品</display:column>
							<display:column property="info_id" title="编号" style="width:80px"/>
							<display:column property="product_type_name" title="产品类型"  style="width:80px"/>
							<display:column property="info_type" title="类型名称"  style="width:200px"/>
							<display:column title="类型描述" style="width:200px">&nbsp;${type.summ}</display:column>
							<display:column title="操作" style="width:150px">
								&nbsp;<input type="button" class="button_2003" value="修改" onclick="go('${ctx}/computer/updateinfotype.action?id=${type.info_id}');"/>&nbsp;
								<c:choose>
									<c:when test="${type.statue == 0}">
										<input type="button" class="button_2003" value="停用" onclick="deleteUserpost('${type.info_id}')";/>
										<input type="button" class="button_2003" value="启用" disabled="disabled"/>
									</c:when>
									<c:otherwise>
										<input type="button" class="button_2003" value="停用" disabled="disabled"/>
										<input type="button" class="button_2003" value="启用" onclick="recoverUserpost('${type.info_id}')";/>
									</c:otherwise>
								</c:choose>
							</display:column>
						</display:table>
					</td>
				</tr>
			</table>
		</td>
   	</tr>
   	<tr>
		<td colspan="4">
			<table class="displaytable-outter" cellspacing=0 cellpadding=0 border=0 width="100%">
			    <tr>
				    <td>
						<display:table uid="type" class="displaytable" name="infotype6" excludedParams="*" form="QueryCondForm">
							<display:column title="设备类型" style="width:120px">介质</display:column>
							<display:column property="info_id" title="编号" style="width:80px"/>
							<display:column property="info_type" title="类型名称"  style="width:210px"/>
							<display:column title="类型描述" style="width:210px">&nbsp;${type.summ}</display:column>
							<display:column title="操作" style="width:150px">
								&nbsp;<input type="button" class="button_2003" value="修改" onclick="go('${ctx}/computer/updateinfotype.action?id=${type.info_id}');"/>&nbsp;
								<c:choose>
									<c:when test="${type.statue == 0}">
										<input type="button" class="button_2003" value="停用" onclick="deleteUserpost('${type.info_id}')";/>
										<input type="button" class="button_2003" value="启用" disabled="disabled"/>
									</c:when>
									<c:otherwise>
										<input type="button" class="button_2003" value="停用" disabled="disabled"/>
										<input type="button" class="button_2003" value="启用" onclick="recoverUserpost('${type.info_id}')";/>
									</c:otherwise>
								</c:choose>
							</display:column>
						</display:table>
					</td>
				</tr>
			</table>
		</td>
   	</tr>
   	<tr>
   		<td colspan="4">
  			<table class="table_box" cellspacing=0 cellpadding=0 border=0 width="100%">
				 <tr>
				 	<td colspan="3" class="title_box">声光电保密编号编码规则</td>
		    	</tr>
			    <tr>
				    <td align="center" width="33%">信息设备类<br>编号规则：（年份+类型+子类型+流水号）</td>
					<td align="left" width="25%">1、计算机<br>2、网络设备<br>3、外部设备<br>4、办公自动化设备<br>5、安全产品</td>
					<td align="left" width="42%">例：15110001<br>2015年-计算机-台式机-0001<br><br>子类型扩展1到9到A到Z<br>15210001到152A0011到152Z9999</td>
				</tr>
				<tr>
				    <td align="center" >介质类<br>编号规则：（单位+类型+年份+流水号）</td>
					<td align="left">A——声光电公司<br>B——24所<br>C——26所<br>D——44所</td>
					<td align="left">
						U——优盘<br>G——光盘<br>Y——移动硬盘<br>Q——其他，指内存卡、录音笔等<br>
						例：AQ150001：声光电公司的其他类型的存储介质，15年发放，流水号为0001
					</td>
				</tr>
			</table>
   		</td>
   	</tr>
</table>
</body>
</html>
