<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<title>配置特殊角色(${subsys_name}-${role_name},用户：${user_name})</title>
	<link rel="stylesheet" href="${ctx}/_css/displaytag.css" type="text/css" media="screen" />
    <link rel="stylesheet" href="${ctx}/_css/css200603.css" type="text/css" media="screen" />
    <script type="text/javascript" src="${ctx}/_script/xmlhttp.js"></script>
	<script language="javascript">
	<!--
		function selectTerr(){
			var terr = "";
			var list = document.getElementsByName("terrChk");
			for(i=0;i<list.length;i++){
				if(list[i].checked){
					terr=terr+list[i].value+",";
				}
			}
			var url = "${ctx}/user/configscopeterr.action?config=Y&domain=${domain}&terrCodes="+terr;
			var result = send(url);
			if (result == "true"){
				alert("更新成功");	
			}else{
				alert("配置失败，请重新尝试，或与管理员联系！");
			}
			window.close();
		}
	//-->
	</script>
</head>
<body oncontextmenu="self.event.returnValue=false">
<table border="0" cellspacing="0" cellpadding="0" class="table_only_border" width="100%" align="center">
	<tr>
		<td colspan="2"  class="title_box">地区列表</td>
	</tr>
	<tr>
		<td>
		<table class="displaytable-outter" cellspacing=0 cellpadding=0 border=0 width="100%">
		    <tr>
		      <td>
				<display:table requestURI="${ctx}/user/configscopeterr.action" uid="terr" name="secTerrList" class="displaytable" defaultsort="0">
					<display:column  title="选择">
						<input type="checkbox" value="${terr.terr_code}" name="terrChk"
						<c:forEach var="item" items="${cfgedTerrCodeList}">
							<c:if test="${item eq terr.terr_code}">
								checked="checked"
							</c:if>
						</c:forEach>
						/>
					</display:column>
					<display:column title="地区代号" property="terr_code" />
					<display:column title="地区名称" property="terr_name" />
					<display:column title="地区描述" property="terr_desc" />
		        </display:table>
		      </td>
		    </tr>
		</table>
		</td>
	</tr>
</table>
<div  style="padding-left: 35px;padding-top: 5px;"><input type="button" class="button_2003" value="确定" onclick="selectTerr();"/></div>
</body>
</html>