<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改岗位--${secUserPost.post_name}</title>
<link rel="stylesheet" href="${ctx}/_css/css200603.css" type="text/css" media="screen" />
<script language="JavaScript" src="${ctx}/_script/function.js"></script>
<script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
<script  language="JavaScript" >
<!--
function chk()
{
    if(document.all.post_name.value=='')
    {
        alert('请填写岗位名称');
        document.all.spec_name.focus();
        return false;
    }
	if(!specialCharFilter(document.all.post_name.value)){
		alert('岗位名称包含特殊字符');
		document.all.post_name.focus();
        return false;
	}
    return true;
}
	//-->
</script>
</head>
<body oncontextmenu="self.event.returnValue=false" onload="onHover();">
<center>
<form method="post" action="${ctx}/user/updatepost.action">
	<input type="hidden" name="post_id" value="${secUserPost.post_id}"/>
	<input type="hidden" name="update" value="Y"/>
  <table class="table_box" cellspacing=0 cellpadding=0 border=1 width="70%">
	 <tr>
		 <td colspan="2" class="title_box">
            	修改岗位--${secUserPost.post_name}
        </td>
    </tr>
    <tr>
      <td width="30%" align="center"><font style="color:red">岗位名称：</font></td>
      <td><input type="text" name="post_name" size="38" value="${secUserPost.post_name}"/>
      </td>
    </tr>
    <tr>
      <td align="center">岗位简介：</td>
      <td>
            <textarea rows="3" cols="38" name="post_desc">${secUserPost.post_desc}</textarea>
      </td>
    </tr>
    <tr>
        <td colspan="2" align="center" class="bottom_box">
            <input type="button" value="修改" class="button_2003" onclick="if(chk()) forms[0].submit();">&nbsp;
            <input type="button" value="返回" class="button_2003" onclick="location.href='${ctx}/user/managepost.action'">
        </td>
    </tr>
  </table>
  </form>
</center>
</body>
</html>
