<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<title>打印参数设置</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="${ctx}/_css/displaytag.css" media="screen"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/_css/css200603.css"  media="screen"/>
    <script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
    <script language="javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>
</head>
<script>
var printValidDays = 0;
var repprintValidDays = 0;
var repprintTimes = 0;
$(document).ready(function(){
		printValidDays = ${print_valid_days};
		repprintValidDays = ${repprint_valid_days};
		repprintTimes = ${repprint_times};
	});
if('${done}' == 'Y'){
	alert("修改成功");
} 
function chk()
{
	var print_valid_days = $("#print_valid_days").val().trim();
	var repprint_valid_days = $("#repprint_valid_days").val().trim();
	var repprint_times = $("#repprint_times").val().trim();
    
    var numval = /^[0-9]+$/;
    
    if($("input[name='print_valid_days_start'][checked]").size()==1){
    	if(!numval.test(print_valid_days)){
	        alert("打印任务有效时间应为整数");
	        document.all.print_valid_days.focus();
	        return false;
   		}
	    if(print_valid_days > 3560){
		     alert("打印任务有效时间不超过10年");
		     document.all.print_valid_days.focus();
		    return false;
		}
		$("#print_valid_days").val(print_valid_days);
    }else{
    	$("#print_valid_days").val(printValidDays);
    }
    
    
    if($("input[name='repprint_valid_days_start'][checked]").size()==1){
	    if(!numval.test(repprint_valid_days))
	    {
	        alert("补打有效时间应为整数");
	        document.all.repprint_valid_days.focus();
	        return false;
	    }
	    if(repprint_valid_days > 3560){
		     alert("补打有效效时间不超过10年");
		     document.all.repprint_valid_days.focus();
		    return false;
		}
		$("#repprint_valid_days").val(repprint_valid_days);
	}else{
    	$("#repprint_valid_days").val(repprintValidDays);
    }
    
    
	if($("input[name='repprint_times_start'][checked]").size()==1){
	    if(!numval.test(repprint_times))
	    {
	        alert("补打限制次数应为整数");
	        document.all.repprint_times.focus();
	        return false;
	    }
	    if(repprint_times > 1000){
		     alert("补打限制次不超过1000次");
		     document.all.repprint_times.focus();
		    return false;
		}
		 $("#repprint_times").val(repprint_times);
    }else{
    	$("#repprint_times").val(repprintTimes);
    }
    
     var fileCheck = true;
     $(".item_startuse").each(function (){		
     	var obj = $(this).closest("tr").children(".item_value_day").children();
     	if(this.checked){
			 var day = $(obj).val().trim();
			 if(!numval.test(day)){
		        fileCheck = false;
			}				
	     }else{
	     	$(obj).val(this.id);
	     } 
	     
     });
     if(!fileCheck){
     	alert("文档保存时间应为整数");
     	return false;
     }
   return true;
}

function setPrintfileDaysArray()
{
	var printfile_days_array = "";
	var startuse_array = "";
	if($(".item_value").size()>0){
		$(".item_value").each(function (){
			
				printfile_days_array += (this.id+":"+this.value+",");
		});
		
			$("#printfile_days_array").val(printfile_days_array);						
		}
	if($(".item_startuse").size()>0){
		$(".item_startuse").each(function (){
			
				if(this.checked==true)
				startuse_array += "1,";
				else
				startuse_array += "0,";
		});
		$("#startuse_array").val(startuse_array);						
	}
	return true;
}


</script>
<body oncontextmenu="self.event.returnValue=false" onload="onHover();" style="padding-top: 80px">
<center>
<form id="TemplateQueryCondForm" method="GET" action="${ctx}/basic/configprint.action">
<table class="table_box" cellspacing=0 cellpadding=0 border=1 width="60%">
	<tr>
		<td colspan="3" class="title_box">打印参数设置</td>
	</tr>	
	<tr>
		<td width="5%" align="center"><input type="checkbox" name="print_valid_days_start"    value="1" title="勾选表示启用该项参数" ${print_valid_days_start}/></td>
		<td width="25%" align="center">打印任务有效时间：</td>
		<td><input type="text" name="print_valid_days" id="print_valid_days"  value="${print_valid_days}"/>&nbsp;天</td>
	</tr>
	<tr>
		<td colspan="3" ><b>&nbsp;&nbsp;补打参数设置:</b></td>
	</tr>
	<tr>
		<td align="center"><input type="checkbox" name="repprint_valid_days_start"   value="1" title="勾选表示启用该项参数" ${repprint_valid_days_start}/></td>
		<td align="center">补打有效时间：</td>
		<td><input type="text" name="repprint_valid_days" id="repprint_valid_days" value="${repprint_valid_days}"/>&nbsp;天</td>
	</tr>
	<tr>
		<td align="center"><input type="checkbox" name="repprint_times_start"  value="1" title="勾选表示启用该项参数" ${repprint_times_start}/></td>
		<td align="center">补打限制次数：</td>
		<td><input type="text" name="repprint_times" id="repprint_times" value="${repprint_times}"/>&nbsp;次</td>
	</tr>	     		       				
	<tr>
		<td colspan="3" ><b>&nbsp;&nbsp;打印文档保存时间设置:</b></td>
	</tr>	
	<s:iterator value="#request.sysConfigItemList" var="item">
		<tr>				
			<td align="center"><input type="checkbox" class="item_startuse"  title="勾选表示启用该项参数"  <s:if test="#item.startuse == 1">checked</s:if> id="${item.item_value}"/></td>
			<td align="center">${item.item_name}：</td>
			<td class="item_value_day"><input type="text" class="item_value"  id="${item.item_key}"  value="${item.item_value}" />&nbsp;天</td>			
		</tr>
	 </s:iterator>
  		<input type="hidden" name="printfile_days_array" id="printfile_days_array"/>   
      	<input type="hidden" name="startuse_array" id="startuse_array"/>  
	<tr>
        <td colspan="3" align="center" class="bottom_box">
        	<input type="hidden" name="update" value="Y"/>
            <input type="button" value="修改" class="button_2003" onclick="if(chk()&&setPrintfileDaysArray()) forms[0].submit();">&nbsp;
            <input type="button" value="返回" class="button_2003" onclick="history.go(-1);"/>
        </td>
    </tr>
</table>
</form>
</center>
</body>
</html>