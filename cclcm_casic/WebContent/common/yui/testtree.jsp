<%@ page language="java" pageEncoding="GBK" contentType="text/html;charset=GBK" %>
<html>
<head>
<script language="javascript" src="<%=request.getContextPath()%>/_script/selectDiv.js"></script>
<!-- <meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
	 modified by renmingfei 2013-07-08  -->
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Default TreeView</title>


</head>

<body class=" yui-skin-sam">

<h1>���� TreeView</h1>


<tr><td>
<input type="text" id="txtggg">
<input type="button" name="edit" class="button_2003" onClick="openTree('-38','txtggg',event);"  value="��ʾ��">
<input type="button" name="edit2" class="button_2003" onClick="showReturnValue('txtggg');"  value="��ʾ����ֵ">
</td></tr>
<tr><td>
<input type="text" id="txtEquip">
<input type="button" name="edit" class="button_2003" onClick="openTree('30','txtEquip',event);"  value="ѡ���豸">
<input type="text" id="txtport">
<input type="button" name="edit" class="button_2003" onClick="openTree('28','txtport',event,txtEquip.value,txtEquip.code,'transEquip');"  value="ѡ��˿�">
</td></tr>


<script type="text/javascript">
	function showReturnValue(){
        var targetid="txtggg";
        var obj;
        obj=document.getElementById(targetid);
        alert("obj.value:"+obj.value+";obj.code:"+obj.code+";obj.typecode:"+obj.typecode)
    }
    function tree_ok(aa){
//            alert(aa);
    }
</script>
</body>
</html>

