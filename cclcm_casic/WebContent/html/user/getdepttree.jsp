<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="hdsec.web.project.user.model.DeptTree"%>
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
        <td height=1 class=title>部门结构</td>
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
tree.add("__x",-1,'组织机构');
<%          DeptTree dept;
      List<DeptTree> deptTreeList = (List<DeptTree>)request.getAttribute("deptTreeList");
      for (int j = 0; j< deptTreeList.size(); j++){
       dept = (DeptTree)deptTreeList.get(j);
       String parentId = dept.getDept_parent_id().equals("")? "__x" : dept.getDept_parent_id();
%>
tree.add("<%=dept.getDept_id()%>",
             "<%=parentId%>",
             "<%=dept.getDept_name()%>",
             "javascript:window.location=\'${ctx}/${context}?dept_id=<%=dept.getDept_id()%>\'",
             "<%=dept.getDept_desc()%>",
             "workframe");
<%}%>
document.write(tree);
         //-->
         </script>
      </div>

</form>
</body>
</html>