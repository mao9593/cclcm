<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.*"%>
<%@ page import="hdsec.web.project.user.session.*"%>
<%@ include file="/common/taglibs.jsp" %>

<%
SessionACL sAcl = (SessionACL)session.getAttribute("SessionACL");   //从session中取得SessionACL对象
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<meta http-equiv="Pragma" content="No-cache">
<meta http-equiv="Cache-Control" content="no-cache, must-revalidate">
<meta http-equiv="Expires" content="-1">

	<title>当前角色：<%=sAcl.getRoleName()%></title>
	<script type="text/javascript" src="${ctx}/_script/tree/dtree.js"></script>
	<script type="text/javascript" src="${ctx}/_script/xmlhttp.js"></script>
    <link rel="stylesheet" type="text/css" href="${ctx}/_css/css200603.css"  media="screen"/>
	<script language="JavaScript">
	<!--
	var MODIFY_STATUS_INIT = 0;     //修改状态之初始状态未做任何修改
	var MODIFY_STATUS_MODIFIED = 1; //修改状态之被修改过
	var MODIFY_STATUS_APPLIED = 2;  //修改状态之应用过即已将状态存入session
	var modified = MODIFY_STATUS_INIT;

	//以下两个集合为不相交集合
	//存储新增的操作ID字典集合对象
	var addDict = new ActiveXObject("Scripting.Dictionary");    //新增操作中不记录资源相关的操作，其在配置资源时已经处理
	//存储去除的操作ID字典集合对象
	var delDict = new ActiveXObject("Scripting.Dictionary");

	//用于初始化字典
	function init(){
		//暂无操作
	}

	//取消一个操作的授权，要同时操作两个集合
	function delOper(id){
		if (!delDict.Exists(id)){ //去除集合中不存在，则加入
			delDict.add(id, id);
		}
		if (addDict.Exists(id)){ //新增集合中存在，则删除
			addDict.remove(id);
		}
	}

	//授权一个操作，要同时操作两个集合
	function addOper(id){
		if (delDict.Exists(id)){ //去除集合中存在，则删除
			delDict.remove(id);
		}
		if (!addDict.Exists(id)){ //新增集合中不存在，则加入
			addDict.add(id, id);
		}
	}

	//取得新授权操作的ID列表，格式为：xxx,xxx,xxx,...
	function getAddOperCodeList(){
		return (new VBArray(addDict.Keys())).toArray().toString();
	}

	//取得已取消授权的操作的ID列表，格式为：xxx,xxx,xxx,...
	function getDelOperCodeList(){
		return (new VBArray(delDict.Keys())).toArray().toString();
	}

	/**
	 * 说明：改变当前操作的授权状态
	 * 本方法要依据check和enRes两个参数状态的不同组合进行不同的操作。
	 * 由于本方法可在复选框和节点URL两处触发，固有在代码中用以下语句对授权状态进行同步：
	 * document.getElementById(objID).checked = true/false;
	 *
	 * @param objID 触发事件的复选取框的ID，结构为：cb_xxx，其中xxx为当前复选框所在节点对应的操作ID
	 * @param check 授权状态，true为授权，false为取消授权
	 * @param enRes 指示当前操作是否是资源相关的
	 */
	function changeCheck(objID, check){
		//alert(objID + ", " + check);
		var id = objID.split("_")[1];           //得到当前操作的ID
		if (check){                             //当授权状态为授权时
			addOper(id);                   //将当前操作标记为授权,只记录资源无关的新增操作
		}
		else{                                   //当授权状态为取消授权时
			delOper(id);                        //将当前操作标记为取消授权,无论是否资源相关都要从delDict中删除
		}
		modified = MODIFY_STATUS_MODIFIED;      //将修改状态置为MODIFY_STATUS_MODIFIED
		document.getElementById("applyButton").disabled = false;    //使应用按钮可用
	}

	//当该页提交时，使用所有按钮无效，防止重复提交和产生无意义操作
	function disableButtons(){
		document.getElementById("okButton").disabled = true;
		document.getElementById("refreshButton").disabled = true;
		document.getElementById("applyButton").disabled = true;
		document.getElementById("cancelButton").disabled = true;
	}

	//使用所有按钮有效
	function enableButtons(){
		document.getElementById("okButton").disabled = false;
		document.getElementById("refreshButton").disabled = false;
		document.getElementById("applyButton").disabled = false;
		document.getElementById("cancelButton").disabled = false;
	}

	//处理应用按钮单击事件
	function onApply(){
		document.getElementById("applyButton").disabled = true;         //使应用按钮无效，防止重复提交
		//用XMLHTTP将当前配置数据存入session
		var r = new Boolean(send("${ctx}/user/configroleapply.action?addOperCodes=" + getAddOperCodeList() +"&delOperCodes=" + getDelOperCodeList())).valueOf();
		if (!r){
			document.getElementById("applyButton").disabled = false;    //若提交失败，则恢复应用按钮有效。
		}
		else{
			modified = MODIFY_STATUS_APPLIED;   //提交成功则将当前修改状态置为已应用
		}
		return r;   //返回提交结果，以供onOK()方法调用时参考
	}

	//处理确定按钮单击事件
	function onOK(){
		//alert("修改状态：" +modified);
		if (modified >= MODIFY_STATUS_MODIFIED){  //有修改过
			var applyButtonStatus = document.getElementById("applyButton").disabled;
			disableButtons();
			var rr = modified == MODIFY_STATUS_APPLIED ? true : onApply();  //如果应用过以后没有修改就不再执行应用函数
			if (rr){
				var r = new Boolean(send("${ctx}/user/configroleupdate.action")).valueOf();
				//alert("更新是否成功：" + r);
				if (!r){
					enableButtons();
					document.getElementById("applyButton").disabled = applyButtonStatus;
					alert("修改失败，请重新尝试，或与系统管理员联系！");
					return false;
				}else{
					alert("更新成功");	
				}
			}
		}
		window.close();     //关闭本对话框
	}

	//处理刷新按钮单击事件
	function onRefresh(){
		if (modified > MODIFY_STATUS_INIT && !confirm("当前配置已修改，刷新操作将丢失之前的所有配置数据，是否继续？"))
			return;

		window.location = '${ctx}/user/prepconfigrole.action?role_id=${role_id}&role_name=${role_name}';
	}

	//处理取消按钮单击事件
	function onCancel(){
		if (modified > MODIFY_STATUS_INIT && !confirm("当前配置已修改，取消操作将丢失之前的所有配置数据，是否继续？"))
			return;

		window.close();
	}
	//-->
	</script>
	<style type="text/css">
	<!--
		body {
			margin-left: 5px;
			margin-top: 5px;
		}
		td {
			FONT-SIZE: 9pt;
		}
	-->
	</style>
</HEAD>

<body onload="init();" oncontextmenu="self.event.returnValue=false">

<script type="text/javascript">
<!--
	var operTree = new dTree('operTree', '${ctx}/_image/');  //定义一个树对象，用于显示操作
	operTree.clearCookie();                 //清除cookie中的记忆
	operTree.add('__x', -1, '操作树图');      //加入根节点
	<%Iterator ite = sAcl.getOperations().values().iterator();   //从session中取得所有操作
	SessionOper oper;
	while (ite.hasNext()){
		oper = (SessionOper)ite.next();
		//树中的节点编码
		String operCode = oper.getOperCode();
		//树中的父节点编码
		String operParentCode = operCode.substring(0, operCode.length() - 2);
		operParentCode = operParentCode.equals("") ? "__x" : operParentCode;
		//树中的操作名
		String operName = oper.getOperName();
		String nameInNode = !oper.isEnDirectory()
							? "<input type=\"checkbox\" id=\"cb_" + oper.getOperCode() + "\" onclick=\"changeCheck(this.id, this.checked);\"" + (oper.isAuthorited() ? " checked=\"checked\"" : "") + " />"
							: "";
		nameInNode += operName;
		String urlInNode="";
	%>
		operTree.add('<%=operCode%>', '<%=operParentCode%>', '<%=nameInNode%>', '<%=urlInNode%>', '<%=oper.getOperDesc()%>', '_self');
	<%}%>
//-->
</script>

<table width="100%" height="100%" border="0" cellspacing="1" cellpadding="0" class="table_only_border">
<TR id="treeTD">
	<td valign=top>
		<div id="treeDIV" style="height:100%;width:100%;overFlow:auto;border: 1px solid black"><br/>
			<script type="text/javascript">
			<!--
			//prompt("", operTree);
			document.write(operTree);   //输出操作树
			//-->
			</script>
		</div>
	</td>
</tr>
<TR height="30">
	<td align="center">
		<table border=0 width=100% style="border:0px">
			<tr valign="middle">
				<td align="left">
					<!--向服务器重新请求当前页面，所有配置将丢失-->
					<input type="button" id="refreshButton" class="button_2003" value="刷新" onclick="onRefresh();" />&nbsp;
					<input type="button" class="button_2003" onClick="operTree.openAll();" value="打开全部"/>&nbsp;
					<input type="button" class="button_2003" onClick="operTree.closeAll();" value="关闭全部"/>
				</td>
				<td align="right">
					<input type="button" id="okButton" class="button_2003" value="确定" onclick="onOK();" />
					&nbsp;
					<input type="button" style="display:none;" id="applyButton" class="button_2003" value="应用" disabled="true" onclick="onApply();" />
					<!--&nbsp;-->
					<input type="button" id="cancelButton" class="button_2003" value="取消" onclick="onCancel()" />
				</td>
			</tr>
		</table>
	</td>
</tr>
</table>
</body>
</HTML>
