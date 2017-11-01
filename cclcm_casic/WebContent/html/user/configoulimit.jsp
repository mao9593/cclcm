<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<HTML><HEAD>
    <meta http-equiv="Content-Type" content="text/html; charset=gb2312"/>
    <title>设置在线用户图临界值</title>
    <link href="${ctx}/_css/css200603.css" rel="stylesheet" type="text/css"/>
    <script language="JavaScript" src="${ctx}/_script/ToolKit.js"></script>
    <script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
    <script language="JavaScript">
        <!--
        function checkForm()
        {
            if (!checkIsInteger(document.all.normalValue.value) || !checkIsInteger(document.all.warningValue.value) || !checkIsInteger(document.all.criticalValue.value))
            {
                alert('所有上限值必须是正整数值！');
                if (!checkIsInteger(document.all.normalValue.value)){
                    document.all.normalValue.focus();
                }
                if (!checkIsInteger(document.all.warningValue.value)){
                    document.all.warningValue.focus();
                }
                if (!checkIsInteger(document.all.criticalValue.value)){
                    document.all.criticalValue.focus();
                }
                return false;
            }

            if (document.all.normalValue.value <= 0 || document.all.warningValue.value <= 0 || document.all.criticalValue.value <= 0)
            {
                alert('所有上限值必须大于0！');
                if (document.all.normalValue.value <= 0){
                    document.all.normalValue.focus();
                }
                if (document.all.warningValue.value <= 0){
                    document.all.warningValue.focus();
                }
                if (document.all.criticalValue.value <= 0){
                    document.all.criticalValue.focus();
                }
                return false;
            }

            if (document.all.warningValue.value * 1.0 < document.all.normalValue.value * 1.0) {
                alert('高负荷上限值不能小于正常上限值！');
                document.all.warningValue.focus();
                return false;
            }

            if (document.all.criticalValue.value * 1.0 < document.all.warningValue.value * 1.0) {
                alert('危险上限值不能小于高负荷上限值！');
                document.all.criticalValue.focus();
                return false;
            }

            return true;
        }
        function modified(){
        	document.all.normalValue.focus();
        	if('${update}' == 'Y'){
	            window.opener.location.reload();
	            this.close();
        	}
        }
        //-->
    </script>
</HEAD>

<body onLoad="modified();onHover();">
<!--width="100%" height="100%"-->
<div align="center">
    <form method="post" action="${ctx}/user/configoulimit.action">
    	<input type="hidden" name="update" value="Y"/>
        <table border="0" width="300" cellspacing="1" cellpadding="1" class="table_box">
            <TR>
                <td colspan=2 class="title_box">设置在线用户图临界值</td>
            </tr>
            <tr>
                <td style="color:red">正常上限值：</td>
                <td><input type="text" name="normalValue" value="${normalValue}"/></td>
            </tr>
            <tr>
                <td style="color:red">高负荷上限值：</td>
                <td><input type="text" name="warningValue" value="${warningValue}"/></td>
            </tr>
            <tr>
                <td style="color:red">危险上限值：</td>
                <td><input type="text" name="criticalValue" value="${criticalValue}"/></td>
            </tr>
            <tr>
                <td colspan="2" class="bottom_box">
                    <input type="button" value="保存" class="button_2003" onClick="if(checkForm()) forms[0].submit();">
                </td>
            </tr>
        </table>
    </form>
</div>
</body>
</HTML>