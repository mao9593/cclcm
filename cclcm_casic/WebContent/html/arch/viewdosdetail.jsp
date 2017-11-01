<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>查看案卷信息</title>
<link rel="stylesheet" href="${ctx}/_css/css200603.css" type="text/css" media="screen" />
<script language="JavaScript" src="${ctx}/_script/function.js"></script>
<script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
<script type="text/javascript" src="${ctx}/uploadify/jquery.min.js"></script>
<script  language="JavaScript" >
<!--
	function deleteDos(id){
		if(confirm("您确定要删除该案卷?")){
			$("#dosId").val(id);
			$("#delForm").submit();
		}
	}
//-->
</script>
</head>
<body onload="onHover();" oncontextmenu="self.event.returnValue=false">
<form id="delForm" method="get" target="_parent" action="${ctx}/arch/deletedos.action">
  	<input type="hidden" name="id" id="dosId"/>
  </form>
<center>
  <table class="table_box" cellspacing=0 cellpadding=0 border=1 width="50%">
	 <tr>
		 <td colspan="2" class="title_box">
            	查看案卷信息
        </td>
    </tr>
    <tr>
      <td width="40%" align="center"><font style="color:red">*</font>案卷号：</td>
      <td>${dos.dos_code}</td>
    </tr>
    <tr>
      <td align="center">子项代号：</td>
      <td>${dos.sub_prog_code}</td>
    </tr>
    <tr>
      <td align="center">项目号：</td>
      <td>${dos.prog_code}</td>
    </tr>
    <tr>
      <td align="center">分类号：</td>
      <td>${dos.type_code}</td>
    </tr>
    <tr>
      <td align="center">案卷题名：</td>
      <td>${dos.dos_subject}</td>
    </tr>
    <tr>
      <td align="center">目录号：</td>
      <td>${dos.dir_code}</td>
    </tr>
    <tr>
      <td align="center">编制单位：</td>
      <td>${dos.unit}</td>
    </tr>
    <tr>
      <td align="center">密级：</td>
      <td>${dos.seclv_code}</td>
    </tr>
    <tr>
      <td align="center">文件件数：</td>
      <td>${dos.arch_num}</td>
    </tr>
    <tr>
      <td align="center">总页数：</td>
      <td>${dos.total_page}</td>
    </tr>
    <tr>
      <td align="center">档案分类：</td>
      <td>${dos.arch_type}</td>
    </tr>
    <tr>
      <td align="center">立卷人：</td>
      <td>${dos.create_user}</td>
    </tr>
    <tr>
      <td align="center">全宗号：</td>
      <td>${dos.dos_num}</td>
    </tr>
    <tr>
      <td align="center">备注：</td>
      <td>${dos.summ}</td>
    </tr>
    <tr>
      <td align="center">责任者：</td>
      <td>${dos.dutyman}</td>
    </tr>
    <tr>
        <td colspan="2" align="center" class="bottom_box">
        	<input type="button" value="修改" class="button_2003" onclick="go('${ctx}/arch/updatedos.action?id='+${dos.id});">
        	<input type="button" value="删除" class="button_2003" onclick="deleteDos('${dos.id}')">
            <input type="button" value="返回" class="button_2003" onclick="history.go(-1);">
        </td>
    </tr>
  </table>
</center>
</body>
</html>
