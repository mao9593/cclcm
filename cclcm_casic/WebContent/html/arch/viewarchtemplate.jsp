<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<link rel="stylesheet" type="text/css" href="${ctx}/_css/displaytag.css" media="screen"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/_css/css200603.css"  media="screen"/>
	<script language="JavaScript" src="${ctx}/_script/function.js"></script>
	<script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
 	<script language="javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>	
 	<script language="javascript" src="${ctx}/_script/casic304_ajax.js"></script>
 	<script language="JavaScript" src="${ctx}/_script/common.js"></script>
<script>
$(document).ready(function(){
	onHover();
});
	function add(){
		var url ="${ctx}/html/arch/addfield.jsp";
		var result =window.showModalDialog(url,'', 'dialogHeight:300px;dialogWidth:600px;center:yes;status:no;help:no;scroll:no;unadorned:no;resizable:no');
		var dict = "";
		var dict_name ="";
		if(result.dirc!=null){
			var dircs = (result.dirc).split("|");
			dict = dircs[0];
			dict_name = dircs[1];
		}
		$("#last_tr").after(addRow(result.name,result.type,dict,dict_name));
	}
	function addRow(name,type,dirc,dict_name){
		var type_name;
		switch (type) {
		case "s":
			 type_name="文字";
			break;
		case "i":
			 type_name="数值";
			break;
		case "d":
			 type_name="日期";
			break;
		case "e":
			 type_name="下划框";
			break;
		default:
			break;
		}
		var $tr_i = $("<tr>");
		var $input_hidden = $("<input>",{
		 type:"hidden",
		 name:"hiddens",
		 value:name+"^"+type+"^"+dirc
		});
		
		var $td_name = $("<td>",{
			text:name,
			align:"center"
		});
		var $td_type = $("<td>",{
			text:type_name,
			align:"center"
		});
		var $td_dirc = $("<td>",{
			text:dict_name,
			align:"center"
		});
		var $td_but = $("<td>",{
			align:"center"
		});
		var $del_font = $("<font>",{
			color:"blue"
		});
		var $del_u = $("<u>",{
		text:"删除"
		});
		var $del_but = $("<a>",{
		style:"cursor:pointer",
		click:function(){
			if(confirm("确定要取消该借用申请？")){
				$tr_i.attr("id","pendingDelete");
				$("#pendingDelete").remove();
			}
		}
	});
	$del_font.append($del_u);
	$del_but.append($del_font);
	$td_but.append($del_but);
	$tr_i.append($td_name);
	$tr_i.append($td_type);
	$tr_i.append($td_dirc);
	$tr_i.append($td_but);
	$tr_i.append($input_hidden);
	return $tr_i;
}
</script>
<script type="text/javascript">
	function chk(property_name,name,type,dirc){
	
		var old = new Object();
		old.name=name;
		old.template_id=${template_id};
		old.property_name=property_name;
		old.type=type;
		old.dirc=dirc;
		var url = "${ctx}/html/arch/updateoneproperty.jsp";
		window.showModalDialog(url,old, 'dialogHeight:200px;dialogWidth:400px;center:yes;status:no;help:no;scroll:no;unadorned:no;resizable:no');
		location.reload();
		}
</script>
</head>
<body >
<form action="${ctx}/arch/updateoneproperty.action" method="get" id="update">
<input type="hidden" id="property_name" name="property_name"/>
<input type="hidden" id="name" name="name"/>
<input type="hidden" id="type" name="type"/>
<input type="hidden" id="dirc" name="dirc"/>
</form>
<form action="${ctx}/arch/addpropertykeys.action" method="get">
<input type="hidden" value="${archTypeId}" id="archTypeId" name="archTypeId"/>
<input type="hidden" name="template_id" id="template_id" value="${template_id}">
<table id="t_arch" width="95%" border="1" cellspacing="0" cellpadding="0" align="center" class="table_box">	

	<tr>
		<td align="center">名称</td>
		<td align="center">数据类型</td>
		<td align="center">选项字典</td>
		<td align="center">操作</td>
	</tr>
	<tr>
		<td align="center"><input type="hidden" id="BARCODE" value="载体条码 |s">载体条码</td>
		<td align="center">文字</td>
		<td align="center"></td>
		<td align="right"><input type="button" value="内置" class="button_2003" disabled="false"></td>
	</tr>
	<tr>
		<td align="center"><input type="hidden" id="DOS_NUM" value="全宗 号|s">全宗 号</td>
		<td align="center">文字</td>
		<td align="center"></td>
		<td align="right"><input type="button" value="内置" class="button_2003" disabled="false"></td>
	</tr>
	<tr>
		<td align="center"><input type="hidden" id="ARCH_NUM" value="档号|s">档号</td>
		<td align="center">文字</td>
		<td align="center"></td>
		<td align="right"><input type="button" value="内置" class="button_2003" disabled="false"></td>
	</tr>
	<tr>
		<td align="center"><input type="hidden" id="TYPE_CODE" value="分类号|s">分类号</td>
		<td align="center">文字</td>
		<td align="center"></td>
		<td align="right"><input type="button" value="内置" class="button_2003" disabled="false"></td>
	</tr>
	<tr>
		<td align="center"><input type="hidden" id="FILE_TITLE" value="文件标题|s">文件标题</td>
		<td align="center">文字</td>
		<td align="center"></td>
		<td align="right"><input type="button" value="内置" class="button_2003" disabled="false"></td>
	</tr>
	<tr>
		<td align="center"><input type="hidden" id="FILE_NUM" value="文件编号|s">文件编号</td>
		<td align="center">文字</td>
		<td align="center"></td>
		<td align="right"><input type="button" value="内置" class="button_2003" disabled="false"></td>
	</tr>
	<tr>
		<td align="center"><input type="hidden" id="PAGE_NUM" value="页数|i">页数</td>
		<td align="center">数值</td>
		<td align="center"></td>
		<td align="right"><input type="button" value="内置" class="button_2003" disabled="false"></td>
	</tr>
	<tr>
		<td align="center"><input type="hidden" id="COUNT" value="份数|i">份数</td>
		<td align="center">数值</td>
		<td align="center"></td>
		<td align="right"><input type="button" value="内置" class="button_2003" disabled="false"></td>
	</tr>
	<tr>
		<td align="center"><input type="hidden" id="TOTAL_PAGE" value="总页数|i">总页数</td>
		<td align="center">数值</td>
		<td align="center"></td>
		<td align="right"><input type="button" value="内置" class="button_2003" disabled="false"></td>
	</tr>
	<tr>
		<td align="center"><input type="hidden" id="SECLV_CODE" value="密级|e">密级</td>
		<td align="center">下划框</td>
		<td align="center">密级</td>
		<td align="right"><input type="button" value="内置" class="button_2003" disabled="false"></td>
	</tr>
	<tr>
		<td align="center"><input type="hidden" id="FILE_DATE" value="归档时间|d" >归档时间</td>
		<td align="center">日期</td>
		<td align="center"></td>
		<td align="right"><input type="button" value="内置" class="button_2003" disabled="false"></td>
	</tr>
	<tr>
		<td align="center"><input type="hidden" id="FILE_CARR" value="文件载体|e" >文件载体</td>
		<td align="center">下划框</td>
		<td align="center">文件载体</td>
		<td align="right"><input type="button" value="内置" class="button_2003" disabled="false"></td>
	</tr>
	<tr>
		<td align="center"><input type="hidden" id="KEEP_LIMIT" value="保管期限|e">保管期限</td>
		<td align="center">下划框</td>
		<td align="center">保管期限</td>
		<td align="right"><input type="button" value="内置" class="button_2003" disabled="false"></td>
	</tr>
	<tr >
		<td align="center"><input type="hidden" id="SUMM" value="备注|s">备注</td>
		<td align="center">文字</td>
		<td align="center"></td>
		<td align="right"><input type="button" value="内置" class="button_2003" disabled="false"></td>
	</tr>
	<c:forEach var="item" items="${list}" step="1">
	<tr>
		<td align="center">${item.name }</td>
		<td align="center">
		${item.type_name }
		</td>
		<td align="center">
		${item.dirc_name }
		</td>
		<td align="right"><input type="button" value="修改" class="button_2003" onclick="chk('${item.property_name}','${item.name}','${item.type}','${item.dirc}')"></td>
	</tr>
	</c:forEach>
	<tr id="last_tr"></tr>
	<tr>
		<td align="right" colspan="4"><input class="button_2003" type="button"  onclick="add();" value="添加新字段"/></td>
	</tr>
</table>
<center>

<table width="95%" >
	<tr >
		<td align="right"><input class="button_2003" type="submit" name="stb" value="保存">&nbsp;	
		<td align="left"><input class="button_2003"  type="reset" name="st" value="返回" onclick="history.go(-1)">	

		</td>
	</tr>

</table>
</center>
</form>
</body>
</html>
