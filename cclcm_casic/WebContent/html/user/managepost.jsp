<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>配置岗位</title>
<link rel="stylesheet" href="${ctx}/_css/displaytag.css" type="text/css" media="screen" />
<link rel="stylesheet" href="${ctx}/_css/css200603.css" type="text/css" media="screen" />
<script>
	function showLevelChangeDialog(){
		var url="${ctx}/user/configpostorder.action";
		var rValue = window.showModalDialog(url,'', 'dialogHeight:420px;dialogWidth:400px;center:yes;status:no;help:no;scroll:no;unadorned:no;resizable:no');
	    if(rValue!=""&&rValue!=undefined){
          levelChangeForm.innerHTML=rValue;
		  levelChangeForm.submit();
	    }
	}
    function showDialogr(url){
		return window.showModalDialog(url,'', 'dialogHeight:400px;dialogWidth:800px;center:yes;status:no;help:no;scroll:no;unadorned:no;resizable:no');
	}
    function showDialogrdept(url){
		return window.showModalDialog(url,'', 'dialogHeight:400px;dialogWidth:400px;center:yes;status:yes;scroll:yes;help:no;scroll:no;unadorned:no;resizable:no');
	}
</script>
</head>
<body oncontextmenu="self.event.returnValue=false">
<table border="0" cellspacing="0" cellpadding="0" class="table_only_border" width="95%" >
	<tr>
		<td class="title_box">
			配置岗位列表
		</td>
	</tr>
	<tr>
		<td class="nav_box" align="right">
			<!-- 
            &nbsp;<input type="button" value="岗位级别修改" class="button_2003" onclick="window.location = '${ctx}/user/configpostclass.action'"/>            
            &nbsp;<input type="button" value="岗位显示顺序" class="button_2003" onclick="showLevelChangeDialog();"/>
			 -->
            &nbsp;<input type="button" value="新增岗位" class="button_2003" onclick="location.href='${ctx}/user/addpost.action'">
		</td>
	</tr>
	<tr>
		<td valign="top">
  <table class="displaytable-outter" cellspacing=0 cellpadding=0 border=0>
    <tr>
      <td>
	   <display:table requestURI="${ctx}/user/managepost.action" uid="post"  name="userPostList" class="displaytable"  defaultsort="0" pagesize="15">
           <display:column title="序号" sortable="true">
             <c:out value="${post_rowNum}"/>
           </display:column>
		   <display:column title="岗位名称"  property="post_name" sortable="true"/>
           <display:column title="岗位简介"  property="post_desc" sortable="true"/>
           <display:column title="操作" style="width:200px">
           <div align="left" style="padding-left:10px">
	           <input type="button" class="button_2003" value="用户" onClick="showDialogr('${ctx}/user/viewuserbypost.action?post_id=${post.post_id}');"/>&nbsp;
	           <input type="button" class="button_2003" value="部门" onClick="showDialogrdept('${ctx}/user/viewdeptbypost.action?post_id=${post.post_id}');"/>&nbsp;
	           <c:if test="${!post.is_delete}">
					<input type="button" value="修改" class="button_2003" onclick="location.href='${ctx}/user/updatepost.action?post_id=${post.post_id}'"/>&nbsp;
	           		<input type="button" value="删除" class="button_2003" <c:if test="${post.user_count > 0}">disabled</c:if>
			       		onclick="if (confirm('确定要删除此信息吗?')) location.href='${ctx}/user/delpost.action?post_id=${post.post_id}'">
			   </c:if>
			   <c:if test="${post.is_delete}">
			   		<input type="button" value="修改" class="button_2003" disabled="disabled"/>&nbsp;
	           		<input type="button" value="封存" class="button_2003" disabled="disabled">
				</c:if>
           </div>
          </display:column>
        </display:table>
      </td>
    </tr>
  </table>
		</td>
	</tr>
</table>
<form action="${ctx}/user/configpostorder.action" method="post" name="levelChangeForm">

</form>
</body>
</html>
