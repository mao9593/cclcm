<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
<title>台账同步</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="${ctx}/_css/css200603.css" type="text/css" media="screen" />
	<link rel="stylesheet" type="text/css" href="${ctx}/_css/displaytag.css" media="screen"/>
	<script language="javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>
	<script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
	<script  language="JavaScript" >
	<!--
	function chk()
	{
		if($("#retrieve").val() == ""){
			alert("请选择台账闭环条件");
			$("#retrieve").focus();
			return false;
		}
		if($("#seclv").val() == ""){
			alert("请选择台账密级");
			$("#seclv").focus();
			return false;
		}
		$("#buttonFlag").val("1");
	    return true;
	}
	function chk_n(k)
	{
		$("#buttonFlag").val(k);
		//alert($("#buttonFlag").val());
	    return true;
	}
	//-->
	</script>
</head>
<body oncontextmenu="self.event.returnValue=false">
<center>
<form method="post" action="${ctx}/ledger/syncdata.action">
<input type="hidden" name="buttonFlag" id="buttonFlag"/> 
  	<table class="table_box" cellspacing=0 cellpadding=0 border=1 width="80%">
  	<tr>
		<td colspan="4">
			<font color="red">做任何操作之前，切记备份新、旧系统数据库！！！同步操作建立在旧系统用户登录账号与新版系统用户id一致的基础上，请提前确认！</font>
		</td>
    </tr>
	 <tr>
	 	<td colspan="4" class="title_box">第一步：准备工作</td>
	 </tr>
    <tr>
        <td colspan="4" align="center" class="bottom_box">
            <input type="submit" value="1 增加同步标志" class="button_2003" onclick="return chk_n('11');">&nbsp;&nbsp;
            <input type="submit" value="2 检测用户是否重名" class="button_2003" onclick="return chk_n('12');">&nbsp;&nbsp;
            <input type="submit" value="3 检测新旧系统密级一致性" class="button_2003" onclick="return chk_n('13');">&nbsp;&nbsp;
            <input type="submit" value="4 条码查重" class="button_2003" onclick="return chk_n('14');">
        </td>
    </tr>
    <tr>
    	<td align="center"><font color="red">注意：</font></td>
		<td colspan="3">
			<font color="red">台账同步前请先做好准备工作，尽量确保新旧系统数据的一致性，避免同步时出现问题！<br>
			准备过程如果出现问题，需要现场与用户单位相关人员沟通，商定后进行修改！<br>
			如果检测出的问题数据不进行修改，则在同步后可能出现台账不准的问题。一定要先修改问题数据，再进行台账同步！！！</font>
		</td>
    </tr>
    <tr>
    	<td align="center">准备工作详情：</td>
		<td  colspan="3">
			<textarea rows="8" cols="100" name="readydetail" id="readydetail" readonly="readonly">${readyDetail}</textarea>
		</td>
    </tr>
  	</table>
  	<table class="table_box" cellspacing=0 cellpadding=0 border=1 width="80%">
	 <tr>
	 	<td colspan="4" class="title_box">第二步：台账同步</td>
	 </tr>
	 <tr>
	 	<td align="center">
        	<font color="red">*</font>是否闭环：
        </td>
        <td>
			<select name="retrieve" id="retrieve">
				<option value="">--请选择--</option>
				<option value="nohandle" <c:if test="${retrieve eq 'nohandle'}">selected</c:if>>未闭环处理</option>
				<option value="all" <c:if test="${retrieve eq 'all'}">selected</c:if>>全部</option>
			</select>
		</td>	
	 	<td align="center">
        	<font color="red">*</font>密级：
        </td>
        <td>
			<select name="seclv" id="seclv">
				<option value="">--请选择--</option>
				<option value="1" <c:if test="${seclv eq '1'}">selected</c:if>>机密</option>
				<option value="2" <c:if test="${seclv eq '2'}">selected</c:if>>秘密</option>
				<option value="3" <c:if test="${seclv eq '3'}">selected</c:if>>商密</option>
				<option value="4" <c:if test="${seclv eq '4'}">selected</c:if>>内部</option>
				<option value="5" <c:if test="${seclv eq '5'}">selected</c:if>>非密</option>
			</select>
		</td>	
    </tr>
    <tr>
        <td colspan="4" align="center" class="bottom_box">
            <input type="submit" value="（1）旧台账导入临时表" class="button_2003" onclick="return chk();">&nbsp;
        </td>
    </tr>
    <tr>
        <td colspan="4" align="center" class="bottom_box">
            <input type="submit" value="（2）更新临时表部门、密级等信息" class="button_2003" onclick="return chk_n('2');">&nbsp;
        </td>
    </tr>
    <tr>
        <td colspan="4" align="center" class="bottom_box">
            <input type="submit" value="（3）把临时表台账导入载体台账表" class="button_2003" onclick="return chk_n('3');">&nbsp;
        </td>
    </tr>
    <tr>
        <td colspan="4" align="center" class="bottom_box">
            <input type="submit" value="（4）检测本次同步操作旧系统中未同步成功的台账" class="button_2003" onclick="return chk_n('4');">&nbsp;
        </td>
    </tr>
    <tr>
    	<td align="center"><font color="red">特别说明：</font></td>
		<td colspan="4">
			<font color="red">请严格按照序号依次点击按钮，因台账同步过程受服务器性能、台账数量等因素影响，在点击每一个按钮操作时请耐心等待几分钟，<br>
			待后台数据操作完成且提示成功后，再进行下一步操作！！！<br>
			台账同步后台操作复杂，可能存在少量台账信息不精准、未同步成功等情况，请同步完成后测试核对！！！</font>
		</td>
    </tr>
    <tr>
    	<td align="center">详细信息：</td>
		<td  colspan="3">
			<textarea rows="10" cols="100" name="detail" id="detail" readOnly>${detail}</textarea>
		</td>
    </tr>
  	</table>
</form>
</center>
</body>
</html>