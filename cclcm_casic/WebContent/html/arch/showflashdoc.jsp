<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<title>续借档案列表</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="${ctx}/_css/css200603.css" type="text/css" media="screen" />
		<link rel="stylesheet" type="text/css" href="${ctx}/_css/displaytag.css" media="screen"/>
	<link href="${ctx}/_script/calendar2/calendar-blue.css" type="text/css" rel="stylesheet"/>
	<script language="javascript" src="${ctx}/_script/calendar2/calendar.js"></script>
	<script language="javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>
	<script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
	<script language="javascript" src="${ctx}/_script/casic304_ajax.js"></script>
	<script>
	$(document).ready(function(){
		onHover();
		optionSelect();
	});
	function optionSelect(){
		$("#template_id").val("${template_id}");
		$("#job_status").val("${job_status}");
	}
	function clearFindForm(){
		$("#ArchQueryCondForm :text").val("");
		$("#template_id").val("");
		$("#job_status").val("");
	}	
	function wopen(id){
		var url = '${ctx}/arch/viewarchdetail.action?id='+id;
		window.open(url,'','height='+(screen.availHeight-40)+', width='+(screen.availWidth-5)+', top=0, left=0, toolbar=no, menubar=no, scrollbars=yes, resizable=yes,location=no, status=no');
	}
	function returnarch(id,barcode){
		if(confirm("确定要继续借出该文档？")){
			var url = "${ctx}/arch/setarchrenew.action?id="+id+"&barcode="+barcode;
			callServer(url);
		}
	}
	function updateResult(){
		if (xmlHttp.readyState == 4) {
		   var response = xmlHttp.responseText;
		   alert(response);
		   $("#ArchQueryCondForm").submit();
		}
	}
	</script>
	
<script language="JavaScript">

    function GetDoc(movieName) {
        var isIE = navigator.appName.indexOf("Microsoft") != -1;
        return (isIE) ? window[movieName] : document[movieName];
    }

    function Search() {
        GetDoc('Print2FlashDoc').SearchText(document.all.searchbox.value);
    }
    
    function onPageChanged() {
        setTimeout("UpdatePageNumber()",1)
    }
    
    function UpdatePageNumber() {
        document.all.pagenum.value=GetDoc('Print2FlashDoc').getCurrentPage()+" of "+GetDoc('Print2FlashDoc').getNumberOfPages()
    }    

</script>
	
<script language="JavaScript1.1" type="text/javascript">
 <!-- 
 var requiredMajorVersion = 8;
 var requiredMinorVersion = 0;
 var requiredRevision = 0;

 var isIE  = (navigator.appVersion.indexOf("MSIE") != -1) ? true : false;
 var isWin = (navigator.appVersion.toLowerCase().indexOf("win") != -1) ? true : false;
 
 function JSGetSwfVer(i){
             if (navigator.plugins != null && navigator.plugins.length > 0) {
                         if (navigator.plugins["Shockwave Flash 2.0"] || navigator.plugins["Shockwave Flash"]) {
                                    var swVer2 = navigator.plugins["Shockwave Flash 2.0"] ? " 2.0" : "";
                         var flashDescription = navigator.plugins["Shockwave Flash" + swVer2].description;
                                    descArray = flashDescription.split(" ");
                                    tempArrayMajor = descArray[2].split(".");
                                    versionMajor = tempArrayMajor[0];
                                    versionMinor = tempArrayMajor[1];
                                    if ( descArray[3] != "" ) {
                                                tempArrayMinor = descArray[3].split("r");
                                    } else {
                                                tempArrayMinor = descArray[4].split("r");
                                    }
                         versionRevision = tempArrayMinor[1] > 0 ? tempArrayMinor[1] : 0;
             flashVer = versionMajor + "." + versionMinor + "." + versionRevision;
             } else {
                                    flashVer = -1;
                         }
             }
             else if (navigator.userAgent.toLowerCase().indexOf("webtv/2.6") != -1) flashVer = 4;
             else if (navigator.userAgent.toLowerCase().indexOf("webtv/2.5") != -1) flashVer = 3;
             else if (navigator.userAgent.toLowerCase().indexOf("webtv") != -1) flashVer = 2;
             else {                       
                         flashVer = -1;
             }
             return flashVer;
 } 
 
 function DetectFlashVer(reqMajorVer, reqMinorVer, reqRevision) 
 {
             reqVer = parseFloat(reqMajorVer + "." + reqRevision);
             for (i=25;i>0;i--) {       
                         versionStr = JSGetSwfVer(i);              
                         if (versionStr == -1 ) { 
                                    return false;
                         } else if (versionStr != 0) {
                                    versionArray      = versionStr.split(".");
                                    
                                    versionMajor      = versionArray[0];
                                    versionMinor      = versionArray[1];
                                    versionRevision   = versionArray[2];
                                    
                                    versionString     = versionMajor + "." + versionRevision;   
                                    versionNum        = parseFloat(versionString);
                                    if ( (versionMajor > reqMajorVer) && (versionNum >= reqVer) ) {
                                                return true;
                                    } else {
                                                return ((versionNum >= reqVer && versionMinor >= reqMinorVer) ? true : false );    
                                    }
                         }
             }          
             return (reqVer ? false : 0.0);
 }
 // -->
</script>
	
</head>

<body oncontextmenu="self.event.returnValue=false">
<form id="ArchShowFlashForm" method="GET" action="${ctx}/arch/showflashdoc.action">
	<input size="10" type="hidden" id="file_address" name="file_title" value="C:\Users\Dell\Desktop\"/>
	<table width="95%" border="0" cellspacing="0" cellpadding="0" align="center" class="table_only_border">
	<tr>
		<td class="title_box">在线预览</td>
	</tr>
	<tr>
		<td align="right">
			<table  table border=0 class="table_box" cellspacing="0" cellpadding="0" width="100%">
			<tr>
			  <td>
	
<!-- Start of document code -->


<!-- Start of document placement code -->
<CENTER>
<script language="JavaScript" type="text/javascript">
 <!-- 
 var width=710
 var height=500
 var align="center"
 var name="Print2FlashDoc"
 var url="../../files/flash/sample.swf"
 if(isIE && isWin) {  
     var oeTags = '<object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000"'
     + 'width="'+width+'" height="'+height+'" align="'+align+'" id="'+name+'"'
     + 'codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version='+requiredMajorVersion+','+requiredMinorVersion+','+requiredRevision+',0">'
     + '<param name="movie" value="'+url+'" /><param name="quality" value="best" />'
     + '<param name="allowScriptAccess" value="sameDomain" />'
     + '<param name="allowFullScreen" value="true" />'
     + '<\/object>';
     document.write(oeTags);   
   } else if(DetectFlashVer(requiredMajorVersion, requiredMinorVersion, requiredRevision)) {
     var oeTags = '<embed src="'+url+'" quality="best" '
     + 'width="'+width+'" height="'+height+'" align="'+align+'" name="'+name+'"'
     + 'play="true"'
     + 'loop="false"'
     + 'quality="best"'
     + 'allowScriptAccess="sameDomain"'
     + 'allowFullScreen="true"'
     + 'type="application/x-shockwave-flash"'
     + 'pluginspage="http://www.macromedia.com/go/getflashplayer">'
     + '<\/embed>'
     document.write(oeTags);   
   } else {
     var alternateContent = 'This content requires the Adobe Flash Player. '
             + '<a href="http://www.macromedia.com/go/getflash/">Get Flash</a>';
     document.write(alternateContent);  
   }
  -->
 </script>
 <noscript>
             This content requires the Adobe Flash Player.
             <a href="http://www.macromedia.com/go/getflash/">Get Flash</a></noscript>
</CENTER>
<!-- End of document placement code -->

<!-- End of document code -->
	
    </td>
			</tr>	
			</table>
		</td>
	</tr>
	<tr>
		<td>
			<table class="displaytable-outter" cellspacing=0 cellpadding=0 border=0 width="100%">
	 			
			</table>
         </td>
	</tr>
</table>
</form>
</body>
</html>
