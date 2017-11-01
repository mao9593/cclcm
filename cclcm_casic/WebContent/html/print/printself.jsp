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
	var SprintCom =new ActiveXObject("SprintCom.NetworkProcess.1");
	var SprintComData = new ActiveXObject("SprintCom.DataProcess.1"); 
	
	 function initPreview(){ 
 		SprintComData.CreateHDConsole(3,"${user_iidd}")
//		alert(path1);
/*      	var obj_pic=document.getElementById("EmfPreview1");
     	var ua = navigator.userAgent.split(";");
		if(ua[2].indexOf("Windows NT 6.1")!=-1){
			try{
				var objShell = new ActiveXObject("wscript.shell");
				objShell.Run("C:\\Imagine32\\imagine.exe "+path1);
				objShell = null;
			}catch(e){
				//alert("找不到文件或组件，请确定路径或文件名是否正确，且所需库文件均可用");
			}
		}else{
     		obj_pic.ShowFile(path1, 2);
    		obj_pic.ActualSize();
    	} */
    	document.getElementById("draftForm").submit();
		return true;					
	 }	
	 function cleanImgCache(){
	 	 
		//alert(unzipfilepath);
	 }	
/*
	 function ZoomOut(){
	    	 	var obj_pic=document.getElementById("preDiv");
	    	 	obj_pic.Zoom(1);
					return true;
	 }	   	
	  function ZoomIn(){
	    	 	var obj_pic=document.getElementById("preDiv");
	    	 	obj_pic.Zoom(-1);
					return true;
	 }	   	
	  function ActualSize(){
	    	 	var obj_pic=document.getElementById("preDiv");
	    	 	obj_pic.ActualSize();
					return true;
	 }	   	
	  function BestFit(){
	    	 	var obj_pic=document.getElementById("preDiv");
	    	 	obj_pic.BestFit();
					return true;
	 } 
*/
	 var img;
	 var initWidth;
	 var initHeight;
	 function ZoomOut(){
		img.height = img.height * 1.1;
		img.width = img.width * 1.1;
	 }
	 function ZoomIn(){
	    img.height = img.height * 0.9;
		img.width = img.width * 0.9;
	 }	   	   	
	 function autoResizeImage(){
	    img.height = initWidth;
		img.width = initHeight;
	 } 
 	 function previewImg(pf,src){
		var div = document.getElementById("preDiv");
		if( i != 1 || !init){
			div.removeChild(div.lastChild);
			init = false;
		}
	 	switch(pf){
	 	case "jpg":
		case "emf":
	 	case "jpeg":
	 	case "bmp":
	 	case "gif":
	 	case "png":
		 	img = document.createElement("img");
		 	break;
	 	case "swf":
	 		img = document.createElement("embed");
	 		img.type = "application/x-shockwave-flash";
	 		img.width=400;
	 		img.height=300;
	 		break;
	 	}
	 	img.src = src;
		initWidth = img.width;
		initHeight = img.height;
	 	div.appendChild(img);
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
<body onload="initPreview();" onunload="cleanImgCache();" >		
</body>
<form target="mainFrame" action="${ctx}/print/manageprintjob.action" id="draftForm">
</form>
</html>