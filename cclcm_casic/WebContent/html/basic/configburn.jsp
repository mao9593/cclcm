<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<title>刻录参数设置</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="${ctx}/_css/displaytag.css" media="screen"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/_css/css200603.css"  media="screen"/>
    <script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
    <script language="javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>
</head>
<script>
var uploadburnfilenum = 0;
var uploadburnfileziptype;
$(document).ready(function(){
		uploadburnfilenum = ${upload_burnfile_num};		
		uploadburnfileziptype = "${upload_burnfile_ziptype}";
						
		$(":checkbox[name='upload_burnfile_ziptype']").each(function(){										
			if(uploadburnfileziptype.indexOf(this.value) != -1){
				this.checked=true;
			}
		}); 
});

if('${done}' == 'Y'){
	alert("修改成功");
}

function chk()
{
	var upload_burnfile_num = $("#upload_burnfile_num").val().trim();
    
    var numval = /^[0-9]+$/;
    
    if($("input[name='upload_burnfile_num_start'][checked]").size()==1){
    	if(!numval.test(upload_burnfile_num)){
	        alert("上传文件数量应为整数");
	        document.all.upload_burnfile_num.focus();
	        return false;
   		}
	    if(upload_burnfile_num > 100){
		     alert("上传文件数量不应超过100个");
		     document.all.upload_burnfile_num.focus();
		    return false;
		}
		if(upload_burnfile_num <= 0){
		     alert("上传文件数量至少为1个");
		     document.all.upload_burnfile_num.focus();
		    return false;
		}
		$("#upload_burnfile_num").val(upload_burnfile_num);
    }else{
    	$("#upload_burnfile_num").val(uploadburnfilenum);
    }

   return true;
}


</script>
<body oncontextmenu="self.event.returnValue=false" onload="onHover();" style="padding-top: 80px">
<center>
<form id="TemplateQueryCondForm" method="post" action="${ctx}/basic/configburn.action">
<table class="table_box" cellspacing=0 cellpadding=0 border=1 width="60%">
	<tr>
		<td colspan="3" class="title_box">刻录参数设置</td>
	</tr>	
	<tr>
		<td width="5%" align="center"><input type="checkbox" name="upload_burnfile_num_start"    value="1" title="勾选表示启用该项参数" ${upload_burnfile_num_start}/></td>
		<td width="25%" align="center">刻录上传文件限制数量：</td>
		<td><input type="text" name="upload_burnfile_num" id="upload_burnfile_num"  value="${upload_burnfile_num}"/>&nbsp;个&nbsp;&nbsp;<font color="red">(请填写1-100之间的整数)</font></td>
	</tr>
	<tr>
		<td width="5%" align="center"><input type="checkbox" name="upload_burnfile_ziptype_start"    value="1" title="勾选表示禁止上传该压缩类型文件" ${upload_burnfile_ziptype_start}/></td>
		<td width="25%" align="center">刻录上传文件限制类型：</td>
		<td>			
			<input type="checkbox" value="zip" name="upload_burnfile_ziptype" id="zip"/>.zip &nbsp;&nbsp;
			<input type="checkbox" value="rar" name="upload_burnfile_ziptype" id="rar"/>.rar &nbsp;&nbsp;
			<input type="checkbox" value="tar" name="upload_burnfile_ziptype" id="tar"/>.tar &nbsp;&nbsp;
			<input type="checkbox" value="7-zip" name="upload_burnfile_ziptype" id="7-zip"/>.7-zip &nbsp;&nbsp;
			<input type="checkbox" value="gzip" name="upload_burnfile_ziptype" id="gzip"/>.gzip &nbsp;&nbsp;
			<input type="checkbox" value="z" name="upload_burnfile_ziptype" id="z"/>.z	&nbsp;&nbsp;
			<input type="checkbox" value="cab" name="upload_burnfile_ziptype" id="cab"/>.cab &nbsp;&nbsp;
			<input type="checkbox" value="iso" name="upload_burnfile_ziptype" id="iso"/>.iso &nbsp;&nbsp;
			<input type="checkbox" value="jar" name="upload_burnfile_ziptype" id="jar"/>.jar	&nbsp;&nbsp;
			
			<input type="checkbox" value="arj" name="upload_burnfile_ziptype" id="arj"/>.arj &nbsp;&nbsp;
			<input type="checkbox" value="lzh" name="upload_burnfile_ziptype" id="lzh"/>.lzh &nbsp;&nbsp;
			<input type="checkbox" value="ace" name="upload_burnfile_ziptype" id="ace"/>.ace	&nbsp;
			<input type="checkbox" value="bz2" name="upload_burnfile_ziptype" id="bz2"/>.bz2 &nbsp;&nbsp;
			<input type="checkbox" value="uue" name="upload_burnfile_ziptype" id="uue"/>.uue &nbsp;&nbsp;				
		</td>
    </tr>
	<tr>
        <td colspan="3" align="center" class="bottom_box">
        	<input type="hidden" name="update" value="Y"/>
            <input type="button" value="修改" class="button_2003" onclick="if(chk()) forms[0].submit();">&nbsp;
            <input type="button" value="返回" class="button_2003" onclick="history.go(-1);"/>
        </td>
    </tr>
</table>
</form>
</center>
</body>
</html>