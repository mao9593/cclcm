<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="hdsec.web.project.arch.model.ArchTypeName"%>
<%@ page import="hdsec.web.project.arch.model.Item"%>
<%@ page import="hdsec.web.project.arch.model.Dossier"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
<meta http-equiv='Content-Type' content='text/html; charset=UTF-8'/>
<link rel="stylesheet" href="${ctx}/_css/css200603.css" type="text/css" />
<link rel="stylesheet" href="${ctx}/_css/displaytag.css" type="text/css" media="screen" />
<script type="text/javascript" src="${ctx}/_script/tree/dtree.js"></script>
</head>
<body style="margin-left: 3px;margin-top: 3px;" oncontextmenu="self.event.returnValue=false">
<form method="post">
<table width="100%" border="0" cellpadding="0" cellspacing="0">
    <tr height="20">
        <td height=1 class=title>案卷结构</td>
    </tr>
	<tr height="10">
        <td height=1 class="hr"></td>
    </tr>
</table>
<div id="Layer1" style="white-space: nowrap;">
<br>
<p><a href="javascript: tree.openAll();">打开全部</a> | <a href="javascript: tree.closeAll();">关闭全部</a></p>
<!-- 准备树 -->
<script type="text/javascript" language="JScript">
<!--
var tree = new dTree('tree', '${ctx}/_image/');
tree.add("__x",-1,'41所');
<%    
	List<ArchTypeName> typeList = (List<ArchTypeName>)request.getAttribute("typeList");
    for (ArchTypeName type:typeList){
%>
	tree.add("t<%=type.getID()%>",
             "__x",
             "<%=type.getType_name()%>",
             "javascript:window.location='${ctx}/arch/viewdosarch.action?type_id=<%=type.getID()%>'",
             "档案类别:<%=type.getType_name()%>",
             "workframe");
<%
	List<Item> itemList = type.getItemList();
	for(Item it:itemList){
%>      
	tree.add("i<%=it.getId()%>",
             "t<%=it.getType_id()%>",
             "<%=it.getItem_code()%>",
             "javascript:window.location='${ctx}/arch/viewdosarch.action?item_id=<%=it.getId()%>'",
             "子项目:<%=it.getItem_code()%>,<%=it.getSumm()%>",
             "workframe");
<%
	List<Dossier> dosList = it.getDosList();
	for(Dossier dos:dosList){
%>
	tree.add("d<%=dos.getId()%>",
             "i<%=dos.getItem_id()%>",
             "<%=dos.getDos_code()%>",
             "javascript:window.location='${ctx}/arch/viewdosarch.action?id=<%=dos.getId()%>'",
             "案卷:<%=dos.getDos_code()%>,<%=dos.getDos_subject()%>",
             "workframe");	
<%}}}%>
document.write(tree);
         //-->
         </script>
      </div>

</form>
</body>
</html>