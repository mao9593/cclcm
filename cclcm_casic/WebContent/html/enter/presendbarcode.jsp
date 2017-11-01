<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
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
function preBarcode(tag){
	var url = "${ctx}/enter/configbarcode.action";
	var rValue = window.showModalDialog(url,'', 'dialogHeight:240px;dialogWidth:400px;center:yes;status:no;scroll:no;help:no;unadorned:no;resizable:no');
	if(rValue != null && rValue!= undefined && rValue.onOK == "Y"){
		$("#n_dum").val(rValue.n_dum);
		$("#barcode_type").val($("#type").val());
		$("#barcode_num").val($("#num").val());
		callServerPostForm("${ctx}/enter/presendbarcode.action",document.forms[0]);
		$(tag).attr("disabled",true);
	}
}
function getAjaxResult(){
		if ( xmlHttp.readyState == 4 && xmlHttp.status == 200) {	
			if(xmlHttp.responseText != null && "" != xmlHttp.responseText){
				var paper_barcodes = xmlHttp.responseText.split(":");
				for (var i=0;i<paper_barcodes.length-1;i++){
					var barcodetype = paper_barcodes[i].split("#")[0];			
					var fileno = paper_barcodes[i].split("#")[1];	
					var barcode = paper_barcodes[i].split("#")[2];
					var others = paper_barcodes[i].split("#")[3];	//条码其他信息，如：部门、用户名、密级等
 					var obj=new ActiveXObject("SprintCom.DataProcess.1");
 					obj.PrintBarcodeInfo(barcodetype, fileno, barcode, $("#n_dum").val(), 1,others); 	
				}				
				alert("条码打印完成");	
				$("#QueryCondForm").submit();
			}
		}
	}
</script>
</head>
<body oncontextmenu="self.event.returnValue=false">
<form method="POST"  id="hiddenform">
	<input type="hidden" name="barcode_type" id="barcode_type"/>
	<input type="hidden" id="barcode_num" name="barcode_num"/>
	<input type="hidden" id="n_dum"/>
</form>
<table width="95%" border="1" cellspacing="0" cellpadding="0" align="center" class="table_box">	
<form id="QueryCondForm" method="GET" action="${ctx}/enter/presendbarcode.action">
	<tr>
	    <td colspan="6" class="title_box">预发条码</td>
	</tr>
	<tr> 
    	<td align="center">&nbsp;预发条码个数： </td>
    	<td><input type="text" name="num" id="num"/></td>  
		 
    	<td align="center">&nbsp;条码类型： </td>
    	<td colspan="1">
    		<select name="type" id="type">
    			<option value="file">纸质条码</option>
    			<option value="cd">光盘条码</option> 
    		</select>
    	</td>
    	<td colspan="2"><input type="button" class="button_2003" value="打印条码" onclick="preBarcode(this);"/></td> 	        	  	
  	</tr>
</form>
</table>
</body>
</html>