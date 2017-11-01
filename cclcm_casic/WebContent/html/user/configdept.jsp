<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${ctx}/_css/css200603.css" rel="stylesheet" type="text/css"/>
<link rel="stylesheet" href="${ctx}/_css/displaytag.css" type="text/css" media="screen"/>
<script language="JavaScript" src="${ctx}/_script/function.js"></script>
<script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
<script language="javascript">
	<!--
    /*function ctrDeptType(v) {
        if (v == '2' || v == '3') { 
            document.getElementById("deptTypeCode").style.display = 'block';
        } else {
            document.getElementById("deptTypeCode").style.display = 'none';
        }
    }*/

    function delDept(){
    	if('${hasUser}' == 'Y'){
    		alert("该部门或子部门下已配置用户,无法删除该部门，请先在用户管理中删除用户或修改用户配置");
    		return false;
    	}else{
    		if(confirm("确定要删除该部门及其所有的子部门吗?")){
    			parent.location.href='${ctx}/user/delsecdept.action?dept_id=${dept_id}';
    			return true;
	    	}
    	}
    }
    //-->
</script>
</head>
<body oncontextmenu="self.event.returnValue=false">
<script language="JavaScript">
    <!--
    function init() {
        ctrDeptType('${secDept.dept_level_code}');
    }
    function configOpenScope(dept_id){
		var url = "${ctx}/borrow/configopenscope.action?dept_id="+dept_id;
		window.open(url, "_blank", "width=700,height=260,left="+((window.screen.width/2)-350)+",top="+((window.screen.height/2)-150)+",location=no,menubar=no,resizable=no,status=no,titlebar=no,toolbar=no,scrollbars=no");
	}
	function configDeptPrinter(dept_id){
		var url = "${ctx}/print/configdeptprinter.action?dept_id="+dept_id;
		window.open(url, "_blank", "width=700,height=250,left="+((window.screen.width/2)-350)+",top="+((window.screen.height/2)-150)+",location=no,menubar=no,resizable=no,status=no,titlebar=no,toolbar=no");
	}
    //-->
</script>

<table width="95%" border="0" align="center" cellpadding="0" cellspacing="0" class="table_box">
    <tr>
        <td colspan="4" class="title_box">组织机构查看</td>
    </tr>
    <tr>
      <td nowrap="nowrap" width="10%">机构编号:</td>
        <td colspan="3">${secDept.dept_cs}</td>
     </tr>
   <tr>
   		<td nowrap="nowrap" width="10%">机构排序值:</td>
   		<td colspan="3">${secDept.dept_rank}</td>
   </tr>
    <tr>
      <td nowrap="nowrap" width="10%">机构名称:</td>
        <td colspan="3">${secDept.dept_name}</td>
    </tr>
     <tr>
    	<td nowrap="nowrap" width="10%">机构代号:</td>
    	<td colspan="3">&nbsp;${secDept.ext_code}</td>
    </tr>
    <!--暂去掉部门所在地、机构级别、机构类型等信息，因为需求中没有 
    <tr>
		<td nowrap="nowrap">相关子系统:</td>
	  	<td colspan="3">
	  		<div id="dept_subsys_name">${dept_subsys_name}<br>
	  		</div>
	    </td>
    </tr>
    <tr>
        <td colspan="4">
            <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="table_box">
                <td width="140">所在地: 
                <c:if test="${not empty secTerr}">${secTerr.terr_name}</c:if>
                </td>
                <td width="140">机构级别: ${secDeptLevel.dept_level_name}
                </td>
                <td>
                    <div id="deptTypeCode" style='<c:if test="${secDeptLevel.dept_level_code == '1'}">display:none</c:if>'>
                     	机构类型: ${secDeptType.dept_type_name}
                    </div>
                </td>
            </table>
        </td>
    </tr>
     -->
    <tr>
        <td nowrap="nowrap" height="50">备注:</td>
        <td colspan="3">&nbsp;${secDept.dept_desc}</td>
    </tr>
    <tr>
        <td colspan="4" class="bottom_box">
        	<!-- 
            <input type="button" value="岗位编制修改" class="button_2003" 
            		onclick="location.href='${ctx}/user/managedeptpost.action?dept_id=${dept_id}'"/>&nbsp;
        	 -->
            <input type="button" class="button_2003" value="添加子部门" onclick="go('${ctx}/user/addsecdept.action?dept_id=${dept_id}')"/>&nbsp;
            <input type="button" class="button_2003" value="修改" onclick="go('${ctx}/user/updatesecdept.action?dept_id=${dept_id}')"/>&nbsp;
            <input type="button" class="button_2003" value="删除" onClick="return delDept();"/>&nbsp;
            <input type="button" class="button_2003" value="配置默认打印机"  onclick="configDeptPrinter('${dept_id}')"/>&nbsp;
            <input type="button" class="button_2003" value="配置载体借用范围" title="默认为仅对本部门内员工开放借用" onclick="configOpenScope('${dept_id}')"/>&nbsp;
        </td>
    </tr>
</table>
</body>
</html>