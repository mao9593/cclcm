<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>岗位显示排序</title>
	<link rel="stylesheet" href="${ctx}/_css/displaytag.css" type="text/css" media="screen"/>
	<link rel="stylesheet" href="${ctx}/_css/css200603.css" type="text/css" media="screen"/>
	<script>

		function UpDown(obj, UpOrDown) {
			var sel = document.getElementById(obj);
			var nIndex = sel.selectedIndex;
			var nLen = sel.length;
			if (nLen <= 0 || nIndex == -1) return;
			var sValue = sel.options[nIndex].value;
			var sHTML = sel.options[nIndex].innerHTML;
			if (UpOrDown == "Up") {
				if (nIndex > 0) {
					sel.options[nIndex].value = sel.options[nIndex - 1].value;
					sel.options[nIndex].innerHTML = sel.options[nIndex - 1].innerHTML;
					sel.options[nIndex - 1].value = sValue;
					sel.options[nIndex - 1].innerHTML = sHTML;
					sel.selectedIndex = nIndex - 1;
				}
			}
			if (UpOrDown == "Down") {
				if (nIndex < nLen - 1) {
					sel.options[nIndex].value = sel.options[nIndex + 1].value;
					sel.options[nIndex].innerHTML = sel.options[nIndex + 1].innerHTML;
					sel.options[nIndex + 1].value = sValue;
					sel.options[nIndex + 1].innerHTML = sHTML;
					sel.selectedIndex = nIndex + 1;
				}
			}
			document.getElementById("selDiv").scrollTop=sel.selectedIndex*sel.scrollHeight/sel.size-120;
		}
	function MostUpDown(obj, UpOrDown){
			var sel = document.getElementById(obj);
			var nIndex = sel.selectedIndex;
			var nLen = sel.length;
			if (nLen <= 0 || nIndex == -1) return;
			var sValue = sel.options[nIndex].value;
			var sHTML = sel.options[nIndex].innerHTML;
		if (UpOrDown == "Up") {
			for(i=0;i<nIndex;i++){
				sel.options[nIndex-i].value=sel.options[nIndex-1-i].value
				sel.options[nIndex-i].innerHTML=sel.options[nIndex-1-i].innerHTML;
			}
			sel.options[0].value=sValue;
			sel.options[0].innerHTML=sHTML;
			sel.selectedIndex=0;
		}
			if (UpOrDown == "Down") {
				for(i=nIndex+1;i<nLen;i++){
					sel.options[i-1].value=sel.options[i].value
					sel.options[i-1].innerHTML=sel.options[i].innerHTML;
				}
			sel.options[nLen-1].value=sValue;
			sel.options[nLen-1].innerHTML=sHTML;
			sel.selectedIndex=nLen-1;

		}
		document.getElementById("selDiv").scrollTop=sel.selectedIndex*sel.scrollHeight/sel.size-120;

	 }
		function generatePostLevel() {
			var sel = document.getElementById('postSel');
			var content = "";
			for (i = 0; i < sel.length; i++) {
				var optionValue = sel.options[i].value;
				content = content + "<input type=hidden name='post_id' value='" + optionValue + "'/>" + "<input type=hidden name='post_level' value='" + (sel.length - i) + "'/>";

			}
			return content;
		}
	</script>

</head>

<body>
<table border="0" cellspacing="0" cellpadding="0" class="table_only_border" width="100%" height="100%">
	<tr>
		<td colspan="2" class="title_box">
			岗位显示排序
		</td>
	</tr>
	<tr>
		<td valign="top">
<table class="displaytable-outter" cellspacing=0 cellpadding=0 border=0 height="100%">
	<tr>
		<td rowspan="4" align="right">
			<div  id="selDiv" style="width:300;height:100%; overflow:auto;border:0 solid #000000;">
			<select name="postSel" class="select_2003" style="width:100%;" onclick="document.getElementById('confirm').focus();document.getElementById('selDiv').scrollTop=document.getElementById('selDiv').scrollTop;">
				<c:forEach var="item" items="${postList}">
					<option value="${item.post_id}">${item.post_name}</option>
				</c:forEach>
			</select>
			</div>
	<script>
		document.all.postSel.size=document.all.postSel.options.length;
	</script>
		</td>
		<td width="8"></td>
		<td align="left">
			<input type="button" class="button_2003" style="font-size:10pt;width:40;height:30" value="最上" onclick="MostUpDown('postSel','Up');">
		</td>
	</tr>
	<tr>
		<td width="8"></td>
		<td align="left">
			<input type="button" class="button_2003" style="font-size:10pt;width:40;height:30" value="向上" onclick="UpDown('postSel','Up');">
		</td>
	</tr>
		<tr>
		<td width="8"></td>
		<td align="left">
			<input type="button" class="button_2003" style="font-size:10pt;width:40;height:30" value="向下" onclick="UpDown('postSel','Down');">
		</td>
	</tr>
		<tr>
		<td width="8"></td>
		<td align="left">
			<input type="button" class="button_2003" style="font-size:10pt;width:40;height:30" value="最下" onclick="MostUpDown('postSel','Down');">
		</td>
	</tr>
	<tr>
		<td align="center" colspan="3">
			<input type="button" class="button_2003" name="confirm" onclick="window.returnValue=generatePostLevel(); window.close();" value="确定">
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<input type="button" class="button_2003" onclick="window.close();" value="取消">
		</td>
	</tr>
</table>
		</td>
	</tr>
</table>
</body>
</html>
