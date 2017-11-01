<%@ include file="/common/taglibs.jsp" %>
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<html>
<head>
<meta http-equiv="Pragma" content="No-cache">
<meta http-equiv="Cache-Control" content="no-cache, must-revalidate">
<meta http-equiv="Expires" content="-1">
<title>部门选择
</title>
<script language="javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/_script/TabPanel.js"></script>
<script type="text/javascript" src="${ctx}/_script/xtree/xtree.js"></script>
<script type="text/javascript" src="${ctx}/_script/xtree/xmlextras.js"></script>
<script type="text/javascript" src="${ctx}/_script/xtree/xloadtree.js"></script>
<SCRIPT type="text/javascript" src="${ctx}/_script/grobal_val.js"></script>
<script type="text/javascript" src="${ctx}/_script/pngfix.js"></script>
<link type="text/css" rel="stylesheet" href="${ctx}/_script/xtree/xtree.css"/>
<link type="text/css" rel="stylesheet" href="${ctx}/_css/treeControl.css"/>
<SCRIPT language="JavaScript">
var xmlHttp = false;
var tabPanel = new TabPanel("activeTab", "otherTab");
//关闭部门选择窗口且不保存更改
function closeDeptSelectNoSave()
{
    window.close();
}

//关闭部门选择窗口并回传更改
function closeDeptSelectWithSave() {
	getSelectSingle();
    backNameObj.value = selectedName;
    backNameIdObj.value = selectedId;
    window.returnValue = "Y";
    window.close();
}
//刷新
function reload() {
    receiveXML("true");
}

function showPopTip() {
    var tip = document.getElementById("tipPic");
    tip.alt = "已经选择了：" + selectedName;
}

function replaceAll(str,substr){
   if(str!=""){
    while(str.indexOf(substr)!=-1){
     str=str.replace(substr,"");
	}
   }
   return str;
}

function changeSelect(objName_select, objId_select, ischecked) {
    if (sInputType == "checkbox") {
        if (ischecked == false) {
            if (("," + selectedId + ",").indexOf("," + objId_select + ",") != -1) {
                var selectedNameArray = selectedName.split(",");
                var selectedIdArray = selectedId.split(",");
                flag = true;
                for (i = 0; i < selectedIdArray.length && flag; i++) {
                    if (selectedIdArray[i] == objId_select) {
                        selectedNameArray[i] = "";
                        selectedIdArray[i] = "";
                        flag = false;
                    }
                }
                selectedName = "";
                selectedId = "";
                for (i = 0; i < selectedIdArray.length; i++) {
                    if (selectedIdArray[i] != "") {
                        selectedName = selectedName + "," + selectedNameArray[i];
                        selectedId = selectedId + "," + selectedIdArray[i];
                    }
                }
                if (selectedName != "") {
                    selectedName = selectedName.substring(1, selectedName.length);
                    selectedId = selectedId.substring(1, selectedId.length);
                }
            }
        } else {
            if (("," + selectedId + ",").indexOf("," + objId_select + ",") == -1) {
                if (selectedName == "") {
                    selectedName = objName_select;
                    selectedId = objId_select;
                } else {
                    selectedName = selectedName + "," + objName_select;
                    selectedId = selectedId + "," + objId_select;
                }
            }
        }
    } else {
        selectedName = objName_select;
        selectedId = replaceAll(objId_select,"common_");
    }
}
function getSelectSingle(){
  var selIdTemp="";
  var selNameTemp="";
  var selName=selectedName.split(",");
  var selId=selectedId.split(",");
  for(i=0;i<selId.length;i++){
   if(selId[i].indexOf("common_")==-1){
     selIdTemp=selIdTemp+","+selId[i];
     selNameTemp=selNameTemp+","+selName[i];
   }else{
     if((","+selectedId+",").indexOf(","+selId[i].replace("common_","")+",")==-1){
          selIdTemp=selIdTemp+","+selId[i].replace("common_","");
          selNameTemp=selNameTemp+","+selName[i];  
	 }

   }
  }
  if (selIdTemp != ""){
   selectedName=selNameTemp.substring(1,selNameTemp.length);
   selectedId=selIdTemp.substring(1,selIdTemp.length);
  }else{
   selectedName="";
   selectedId="";
  }

}

function cleanAll() {
	$(":checkbox").attr("checked",false);
	$(":radio").attr("checked",false);
	selectedName = "";
   	selectedId = "";
	if($(":checkbox").size() > 1){
		$("#cbSelectAll").attr("checked",false);
	}
    //closeDeptSelectWithSave();
}

function reloadCommonTree(){
}
function receiveXML(reload) {
	var contactList =new Array;
	var preurl = METAR_WEB_ROOT + "/user/getalldeptlist.action";
//	var preurl = METAR_WEB_ROOT + "/user/getalldeptlist.action?selected=" + selectedId + "&reload=" + reload;
	$("#selected").val(selectedId);
	$("#reload").val(reload);
	var form = document.getElementById("hiddenDelFileForm");
    dept_tree = new WebFXLoadTree("组织机构", "", preurl,"","","","",form);
    document.getElementById("treediv").innerHTML = dept_tree.toString();
}

//全选or全不选
function funCheckAll(tag){
	if(tag.checked){
		//alert(document.getElementById("treediv").innerHTML);
		$(":checkbox").attr("checked",true);
		var name = "";
		var id = "";
		$(":checkbox[id!='cbSelectAll']").each(function(){
			name = $(this).next().text();
			id = this.id;
			if(id != '01' && id.indexOf("_") != -1){
				id = id.substring(id.indexOf("_")+1);
			}
			changeSelect(name,id,true);
		});
	}else{
		$(":checkbox").attr("checked",false);
		var name = "";
		var id = "";
		$(":checkbox[id!='cbSelectAll']").each(function(){
			name = $(this).next().text();
			id = this.id;
			if(id != '01' && id.indexOf("_") != -1){
				id = id.substring(id.indexOf("_")+1);
			}
			changeSelect(name,id,false);
		});
	}
}
</SCRIPT>

<style type="text/css">
    <!--
    .activeTab {
        color: #000000;
        font-weight: bold;
        border: 1px ridge #C0C0C0;
        padding-left:2px;
        padding-right: 2px;
        padding-top: 1px;
        padding-bottom: 1px;
        background-color: #C1E5FD;
        text-align: center
    }

    .otherTab {
        color: #777777;
        border: 1px solid #CCCCCC;
        padding-left: 2px;
        padding-right:
        2px;
        padding-top: 1px;
        padding-bottom: 1px;
        background-color:
        #FFFFFF;
        cursor: hand;
        text-align: center;
        font-style: italic
    }

    body {
        margin-left: 5px;
        margin-top: 5px;
    }

    td {
        FONT-SIZE: 9pt;
    }

    -->
</style>
</head>

<body style="margin : 0px;" oncontextmenu="self.event.returnValue=false">
<form action="${ctx}/user/getalldeptlist.action" method="POST" id="hiddenDelFileForm">
	<input type="hidden" name="selected" id="selected"/>
	<input type="hidden" name="reload" id="reload"/>
</form>
<table class="treeControl" width="100">
    <tr>
        <td colspan="2" height=20>
            <table border="0" cellpadding="0" cellspacing="0">
                <tr>
                    <td><div style="width: 80px;" id="treeTab" onclick="tabPanel.activePage(this);">部门树图</div></td>
                    <td>&nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox" id='cbSelectAll' onclick="funCheckAll(this);" title="全选/全不选"></td>  
                </tr>
            </table>
        </td>
    </tr>
    <tr>
        <td colspan="2" style="border:1px solid black" valign="top">
            <div style="width:300px;height:350px;overflow :scroll;" id="treediv"><br/>
            </div>
            　 </td>
    </tr>
    <tr>
        <td>
            <table>
                <tr>
                    <td class="treeControl"><div class="treeControl" onclick="dept_tree.expandAll();//common_tree.expandAll()">
   	                	<IMG alt="全部展开" vspace="0" hspace="7" src="${ctx}/_image/_depticon/treeExpand_32.png">
       	            </div></td>
                    <td class="treeControl">
                        <div class="treeControl" onclick="dept_tree.collapseAll();//common_tree.collapseAll()">
                        	<IMG alt="全部合拢" vspace="0" hspace="5" src="${ctx}/_image/_depticon/treeClose_32.png">
                        </div>
                    </td>
                    <td class="treeControl">
                        <div class="treeControl" onclick="reload()">
                        	<IMG alt="刷新" vspace="0" hspace="3" src="${ctx}/_image/_depticon/refurbish_32.png">
                        </div>
                    </td>
                    <td class="treeControl">
                        <div class="treeControl" onclick="cleanAll()">
                        	<IMG alt="清空" vspace="0" hspace="3" src="${ctx}/_image/_depticon/clean_32.png">
                        </div>
                    </td>
                    <td class="treeControl">
                        <div class="treeControl" onmouseover="showPopTip()">
                        	<IMG id="tipPic" alt="提示信息" vspace="0" hspace="3" src="${ctx}/_image/_depticon/info_32.png">
                        </div>
                    </td>
                    <td class="treeControl">
                        <div class="treeControl" onclick="closeDeptSelectWithSave()">
                            <IMG alt="关闭且返回值" vspace="0" hspace="3" src="${ctx}/_image/_depticon/ok_32.png">
                        </div>
                    </td>
                    <td class="treeControl">
                        <div class="treeControl" onclick="closeDeptSelectNoSave()">
                        	<IMG alt="关闭且不更改值" vspace="0" hspace="0" src="${ctx}/_image/_depticon/close_alart_32.png">
                        </div>
                    </td>
                </tr>
            </table>
        </td>
    </tr>
</table>

<script language="javaScript">
    //进行一些初始化
    var sData = window.dialogArguments;
    var backNameObj = sData.document.getElementById("${objName}");
    var backNameIdObj = sData.document.getElementById("${objId}");
    var selectedName = backNameObj.value;
    var selectedId = backNameIdObj.value;
    var sInputType = "${inputType}";

    //开始xtree呈现
    //var dept_tree;

    //设置选择类型
    //选择是checkbox还是radio
    webFXTreeConfig.inputType = "${inputType}";

    // XP Look
    /*webFXTreeConfig.rootIcon = "${ctx}/_script/xtree/images/xp/folder.png";
    webFXTreeConfig.openRootIcon = "${ctx}/_script/xtree/images/xp/openfolder.png";
    webFXTreeConfig.folderIcon = "${ctx}/_script/xtree/images/xp/folder.png";
    webFXTreeConfig.openFolderIcon = "${ctx}/_script/xtree/images/xp/openfolder.png";
    webFXTreeConfig.fileIcon = "${ctx}/_script/xtree/images/xp/file.png";
    webFXTreeConfig.lMinusIcon = "${ctx}/_script/xtree/images/xp/Lminus.png";
    webFXTreeConfig.lPlusIcon = "${ctx}/_script/xtree/images/xp/Lplus.png";
    webFXTreeConfig.tMinusIcon = "${ctx}/_script/xtree/images/xp/Tminus.png";
    webFXTreeConfig.tPlusIcon = "${ctx}/_script/xtree/images/xp/Tplus.png";
    webFXTreeConfig.iIcon = "${ctx}/_script/xtree/images/xp/I.png";
    webFXTreeConfig.lIcon = "${ctx}/_script/xtree/images/xp/L.png";
    webFXTreeConfig.tIcon = "${ctx}/_script/xtree/images/xp/T.png";
    webFXTreeConfig.tMinusIcon = "${ctx}/_script/xtree/images/Tminus.png";
    webFXTreeConfig.tPlusIcon = "${ctx}/_script/xtree/images/Tplus.png";
    webFXTreeConfig.blankIcon = "${ctx}/_script/xtree/images/blank.png";
    webFXTreeConfig.selectType="true";
	webFXTreeConfig.METAR_WEB_ROOT=METAR_WEB_ROOT;*/
	// isms look
	webFXTreeConfig.rootIcon = "${ctx}/_script/xtree/images/casic/base.gif";
    webFXTreeConfig.openRootIcon = "${ctx}/_script/xtree/images/casic/openedfolder.gif";
    webFXTreeConfig.folderIcon = "${ctx}/_script/xtree/images/casic/closedfolder.gif";
    webFXTreeConfig.openFolderIcon = "${ctx}/_script/xtree/images/casic/openedfolder.gif";
    webFXTreeConfig.fileIcon = "${ctx}/_script/xtree/images/casic/menufile.gif";
    webFXTreeConfig.lMinusIcon = "${ctx}/_script/xtree/images/casic/minusbottom.gif";
    webFXTreeConfig.lPlusIcon = "${ctx}/_script/xtree/images/casic/plusbottom.gif";
    webFXTreeConfig.tMinusIcon = "${ctx}/_script/xtree/images/casic/minus.gif";
    webFXTreeConfig.tPlusIcon = "${ctx}/_script/xtree/images/casic/plus.gif";
    webFXTreeConfig.iIcon = "${ctx}/_script/xtree/images/casic/bar3.gif";
    webFXTreeConfig.lIcon = "${ctx}/_script/xtree/images/casic/bar2.gif";
    webFXTreeConfig.tIcon = "${ctx}/_script/xtree/images/casic/bar1.gif";
    webFXTreeConfig.tMinusIcon = "${ctx}/_script/xtree/images/casic/minus.gif";
    webFXTreeConfig.tPlusIcon = "${ctx}/_script/xtree/images/casic/plus.gif";
    webFXTreeConfig.blankIcon = "${ctx}/_script/xtree/images/casic/spacer.gif";
    webFXTreeConfig.selectType="true";
	webFXTreeConfig.METAR_WEB_ROOT=METAR_WEB_ROOT;
    receiveXML("true");
    tabPanel.addPage(document.getElementById("treeTab"), document.getElementById("treediv"));
    tabPanel.activePage(document.getElementById("treeTab"));
    if($(":checkbox").size() <= 1){
    	$("#cbSelectAll").hide();	
    }
    //清除上次选择的，系统管理员定密时重置，否则传值为undefined
    cleanAll();
</script>
<script language="JavaScript">
    var dept = document.getElementById("${dept_id}");
    if(dept){
    	treediv.scrollTop=dept.offsetTop;
    }
</script>
</body>
</html>