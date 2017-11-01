<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>打印文件预览</title>
	<link rel="stylesheet" type="text/css" href="${ctx}/_css/displaytag.css" media="screen"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/_css/css200603.css"  media="screen"/>
    <script language="javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>
	<script language="JavaScript" src="${ctx}/_script/function.js"></script>
	<script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
	<script language="javascript" src="${ctx}/_script/casic304_ajax.js"></script>
	
    <script type="text/javascript"> 
    var unfileName = "${unzipdirname}";
    //var fileName = "";
	var zipfileName = "${unzipdirname}";	
	var filePath = "${remoteAddr}"+"${ctx}/files/des/"+zipfileName;
	var rs;
	 function initPreview(){ 
		 window.location.href=filePath;
		 //rs = window.showModalDialog(filePath,window);
		//alert(rs);
		 onunload = live();
		 
	 }	
	 
	 function live(){
		 //异步删除明文
		 deleteFile(unfileName);
		 //window.histroy.go(-1);
		 //回退上一个到jsp
	 }
	function deleteFile(zipfileName){
			var url = "${ctx}/print/deletefile.action";
			if(zipfileName != ""){
				callServer1(url,"zipfileName="+zipfileName);
			}
		}

</script>   
<style>
		   *{
		   		margin:0px;
		   		padding:0px;
		   	}
		   body {
		   		height:100%;
		   		background-color:#EEF2FB;
		   }
	
		   #d1{
		     float:left;
		     border:2px solid #6C6C6C;
		     width:10%;
		     height:100%;
		   }
	
		   #d2{
		     border:2px solid #6C6C6C;
		     width:auto;
		     height:100%;
		   }
	
		   #d3{
		     float:left;
		     border:2px solid #6C6C6C;  
		     width:10%;
		     height:100%;
		     overflow:scroll;
		   }
</style>   
</head>
<body onload="initPreview();">		
</body>
</html>