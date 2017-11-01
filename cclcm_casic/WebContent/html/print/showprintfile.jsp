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
	var zipfileName = "${unzipdirname}"+".zip";	
	var filePath = "${remoteAddr}"+"${ctx}/files/print/"+zipfileName; 
	var totalPage = "${pagecount}";
    var i=1;
	var init=true;
    var FormatNum="00000001";
   	var localPath="";
   	var localZipfile =""; 
   	var curDate = new Date();
   	var month = curDate.getMonth()+1;
   	if(month < 10){
   		month = "0" + month;
   	}
   	var day = curDate.getDate();
   	if(day < 10){
   		day = "0" + day;
   	}
   	var datePath = ""+curDate.getYear()+(month)+(day);
	//alert(datePath);
  	//alert(filePath);
    function trim(str){ //删除左右两端的空格
        return str.replace(/(^\s*)|(\s*$)/g, "");
    }        
    function NoSubmit(ev){
	    if(ev.keyCode==13){
	        i=document.FormMain.currentPage.value;
		    if(parseInt(i)>parseInt(totalPage) || i<1){
		     	alert("页码超出范围");
		    }
            else{
			    document.FormMain.currentPage.value=i;
			    getPageNumber();
//			    var obj_pic=document.getElementById("EmfPreview1");
			    var path1 = localPath+"unzipPreview\\" + datePath + "\\" + "${unzipdirname}"+"\\"+FormatNum+".emf";
				previewImg("emf",path1);
//		     	obj_pic.ShowFile(path1, 2);
//		    	obj_pic.ActualSize();
            }
               return false;
	    }
	}   
	function downloadEMF(){
		localZipfile = SprintCom.DownLoadFile(filePath,"","");
		if(localZipfile == "")
		{
			alert("该文件不存在或已删除，请重新提交打印任务！")
			window.close();
			return 1;
		}
		
		SprintComData.UnzipEmf(localZipfile);
		localZipfile = localZipfile.replace(/\\\\/g,"\\");
		localPath = localZipfile.substring(0,localZipfile.lastIndexOf("\\")+1);	
  }	   	    
	   /**
	   * @functionname  ahead()
	   * @description  向前
	   * @param   none
	   * @return  none
	   */	   
	 function getPageNumber(){
   		var strTemp="";
   		for(m=1;m <=8-i.toString().length;m++){ 
    		strTemp+= "0"; 
    	} 
   		FormatNum = trim(strTemp)+i.toString();
   	}	   	   
	 function Prevpage(){ 
	    if(i<=1){
	        alert("已经到了第一页");
	    }else{
	     i--;
	     document.FormMain.currentPage.value=i;
	 		 getPageNumber();
//	 		 	var obj_pic=document.getElementById("EmfPreview1");
	     	var path1 = localPath+"unzipPreview\\" + datePath + "\\" + "${unzipdirname}"+"\\"+FormatNum+".emf";
	     	previewImg("emf",path1);
	    /*  	obj_pic.ShowFile(path1, 2);
	    	obj_pic.ActualSize(); */
	 		 
	    }
	 } 
	   /**
	   * @functionname  next()
	   * @description  下一页
	   * @param   none
	   * @return  none
	   */ 
	 function Nextpage(){ 
	    if(i>=(totalPage)){
	     	alert("已经到了最后一页");
	    }else{
		     i++;
		     document.FormMain.currentPage.value=i;
		     getPageNumber();
//		    var obj_pic=document.getElementById("EmfPreview1");
		    var path1 = localPath+"unzipPreview\\" + datePath + "\\" + "${unzipdirname}"+"\\"+FormatNum+".emf";
		    previewImg("emf",path1);
	     	/* obj_pic.ShowFile(path1, 2);
	    	obj_pic.ActualSize(); */
	    }
	 }         	    
	 function initPreview(){ 
 		downloadEMF();				
		document.FormMain.currentPage.value=i;
//		var obj = document.FormMain.EmfPreview1;
//		obj.width = document.body.clientWidth;
//  		obj.height = document.body.clientHeight-40;		   
    	getPageNumber();
    	var path1 = localPath+"unzipPreview\\" + datePath + "\\" + "${unzipdirname}"+"\\"+FormatNum+".emf";	
    	previewImg("emf",path1);
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
		return true;					
	 }	
	 function cleanImgCache(){
	 	 var unzipfilepath=localPath+"unzipPreview\\"+datePath+"\\"+zipfileName.substring(0,zipfileName.length-4);
	 	zipfilepath = localPath+zipfileName;
		SprintComData.deleteFiles(zipfilepath);  
		//alert(zipfilepath);
	 	SprintComData.deleteDirectory(unzipfilepath);	
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
	<center>
		<form method="post" name="FormMain2" enctype="application/x-www-form-urlencoded" >
		<input type="hidden" name="next_approver" id="next_approver"/>
	    <input type="hidden" name="job_code" id="job_code"/>
		<div style="width:100%;height:800px;overflow:auto" id="preDiv" align="center">
		</div>
		</form>
		<!-- div style="height:1px;width:100%"></div>
        <object width="1000px" height="800px" classid="CLSID:50F16B26-467E-11D1-8271-00C04FC3183B" name="EmfPreview1" id="EmfPreview1"></object><br/-->	
		<form method="post" name="FormMain" enctype="application/x-www-form-urlencoded" >
 		<input class="button_2003"  type="button" id= "btnPrevpage" name="" value="上一页" onClick="Prevpage();"/>&nbsp;
 		<input class="button_2003"  type="button" id= "btnNextpage" name="" value="下一页" onClick="Nextpage();"/>&nbsp;
 		<input class="button_2003"  type="button" id= "btnZoomOut" name="" value="放大" onClick="ZoomOut();"/>&nbsp;
 		<input class="button_2003"  type="button" id= "btnZoomIn" name="" value="缩小" onClick="ZoomIn();"/>&nbsp;
 		<!--input class="button_2003"  type="button" id= "btnBestFit" name="" value="还原" onClick="autoResizeImage();"/>&nbsp;&nbsp;&nbsp;&nbsp;-->
	 	第<input type="input" id="currentPage" size=1 onkeypress="javascript:return NoSubmit(event);">页/共&nbsp;<b>${pagecount}</b>&nbsp;页
		</form>  
 	</center> 			
</body>
</html>