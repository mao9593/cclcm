<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<html>
<head>
<meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>
<title>在线用户列表</title>
<link rel="stylesheet" href="${ctx}/_css/displaytag.css" type="text/css" media="screen"/>
<link rel="stylesheet" href="${ctx}/_css/css200603.css" type="text/css" media="screen"/>
<script type="text/javascript" language="JavaScript">
        <!--
function winOpenCenterFree(strURL, strName) {
   theWindow = window.open(strURL, strName, 'width='+window.screen.width*4/5+',height='+window.screen.height*4/5+',left='+window.screen.width/10+',top='+window.screen.height/10+',status=no,fullscreen=no,toolbar=no,menubar=no,location=no,scrollbars=yes,resizable=yes');
   if (theWindow.opener == null) theWindow.opener = window;
   if (window.focus) theWindow.focus();
}

function winOpenCenterHalf(strURL, strName) {
   theWindow = window.open(strURL, strName, 'width=' + window.screen.width/3 + ',height=' + +window.screen.height/3 + ',left='+window.screen.width/3+',top='+window.screen.height/4+',status=no,fullscreen=no,toolbar=no,menubar=no,location=no,scrollbars=no,resizable=yes')
   if (theWindow.opener == null) theWindow.opener = window;
   if (window.focus) theWindow.focus();
}
        //-->
    </script>
</head>

<body>
<table cellspacing=0 cellpadding=0 border=0 width="100%">
    <tr>
        <td align="center">
            <a href="javascript:window.location='${ctx}/user/onlineuserstatus.action';">[刷新]</a>&nbsp;&nbsp;&nbsp;&nbsp;
            <a href="javascript:winOpenCenterHalf('${ctx}/user/configoulimit.action', '设置在线用户图临界值')">[设置]</a>&nbsp;&nbsp;&nbsp;&nbsp;
            		单击图片打开在线用户列表<BR/>
            <img src="${ctx}/servlet/DisplayChart?filename=${online_user_chart_filename}" usemap="#${ctx}/servlet/DisplayChart?filename=${online_user_chart_filename}" width=700 height=450 border=0 style="cursor:hand"
                 onclick="winOpenCenterFree('${ctx}/user/viewonlineuser.action', '在线用户列表')"/>
        </td>
    </tr>
</table>
</body>
</html>