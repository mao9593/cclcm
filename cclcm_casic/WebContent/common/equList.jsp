<%@ include file="/common/taglibs.jsp" %>
<%@ page language="java" pageEncoding="GBK" contentType="text/html;charset=GBK" %>
<%@ page import="com.metarnet.security.model.User,
                 eoms.Constants" %>

<html>
<head>

<meta http-equiv="Pragma" content="No-cache">
<meta http-equiv="Cache-Control" content="no-cache, must-revalidate">
<meta http-equiv="Expires" content="-1">
<%
    //List allDeptList = DeptListUtil.getAllDeptList();
    //String objName = request.getParameter("objName");
    String objId = request.getParameter("objId");
    String inputType = request.getParameter("inputType");
    String mainCode= request.getParameter("mainCode");
    String isuse= request.getParameter("isuse");
    String reloadStr = request.getParameter("reload");
    //if (objName == null) objName = "";
  
%>
<title>
  �豸ѡ��
</title>
<layout:skin includeScript="true"/>

<script type="text/javascript" src="<%=request.getContextPath()%>/_script/TabPanel.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/_script/xtree/xtree.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/_script/xtree/xmlextras.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/_script/xtree/xloadtree.js"></script>
<SCRIPT type="text/javascript" src="<%=request.getContextPath()%>/_script/grobal_val.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/_script/pngfix.js"></script>
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/_script/xtree/xtree.css"/>
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/_css/treeControl.css"/>
<SCRIPT language="JavaScript">
var xmlHttp = false;
var tabPanel = new TabPanel("activeTab", "otherTab"); //��ʼ�� tabPanel
//�رղ���ѡ�񴰿��Ҳ��������
function closeDeptSelectNoSave()
{
    window.close();
}

//�رղ���ѡ�񴰿ڲ��ش�����
function closeDeptSelectWithSave() {
	getSelectSingle();
    //backNameObj.value = selectedName;
    backNameIdObj.value = selectedId;
    window.close();
}
//ˢ��
function reload() {

    receiveXML("true");
}

function showPopTip() {
    var tip = document.getElementById("tipPic");
    tip.alt = "�Ѿ�ѡ���ˣ�" + selectedId;
}

function replaceAll(str,substr){
   if(str!=""){
    while(str.indexOf(substr)!=-1){
     str=str.replace(substr,"");
	}
   }
   return str;
}
//ѡ������ ѡ�����������ñ��������xtree.js �е���
function changeSelect(objName_select, objId_select, ischecked) {

    //var tempid2 = objId_select.split(";");                   //�豸��Ϣ��;����
    //objId_select = tempid2[tempid2.length-1];                  //����ʲ����
    
    if (sInputType == "checkbox") {
        //alert(objName_select+ "      " +objId_select);
        if (ischecked == false) {
            if (("," + selectedId + ",").indexOf("," + objId_select + ",") != -1) {
                //var selectedNameArray = selectedName.split(",");
                var selectedIdArray = selectedId.split(",");
                flag = true;
                for (i = 0; i < selectedIdArray.length && flag; i++) {
                    if (selectedIdArray[i] == objId_select) {
                        //selectedNameArray[i] = "";
                        selectedIdArray[i] = "";
                        flag = false;
                    }
                }
               // selectedName = "";
                selectedId = "";
                for (i = 0; i < selectedIdArray.length; i++) {
                    if (selectedIdArray[i] != "") {
                        //selectedName = selectedName + "," + selectedNameArray[i];
                        selectedId = selectedId + "," + selectedIdArray[i];
                    }
                }
                if (selectedId != "") {
                    //selectedName = selectedName.substring(1, selectedName.length);
                    selectedId = selectedId.substring(1, selectedId.length);
                }
            }
        } else {
            if (("," + selectedId + ",").indexOf("," + objId_select + ",") == -1) {
                if (selectedId == "") {
                    //selectedName = objName_select;
                    selectedId = objId_select;
                } else {
                    //selectedName = selectedName + "," + objName_select;
                    selectedId = selectedId + "," + objId_select;
                }
            }
        }
    } else {
        //selectedName = objName_select;
		//alert(replaceAll(selectedId,"common_"));
        selectedId = replaceAll(objId_select,"common_");
    }
}
function getSelectSingle(){
  var selIdTemp="";
  //var selNameTemp="";
  //var selName=selectedName.split(",");
  var selId=selectedId.split(",");
  for(i=0;i<selId.length;i++){
   if(selId[i].indexOf("common_")==-1){
     
    // var tempid = selId[i].split(";");                   //�豸��Ϣ��;����
    //  selIdTemp=selIdTemp+","+tempid[tempid.length-1];    //ȡ���һ�� �ʲ����
     selIdTemp=selIdTemp+","+selId[i];    //��ȡ������Ϣ
     //selNameTemp=selNameTemp+","+selName[i];
   }else{
     if((","+selectedId+",").indexOf(","+selId[i].replace("common_","")+",")==-1){
          selIdTemp=selIdTemp+","+selId[i].replace("common_","");
          //selNameTemp=selNameTemp+","+selName[i];  
	 }

   }
  }
  if (selIdTemp != ""){
  // selectedName=selNameTemp.substring(1,selNameTemp.length);
   selectedId=selIdTemp.substring(1,selIdTemp.length);
  }else{
   //selectedName="";
   selectedId="";
  }

}

function cleanAll() {
    //selectedName = "";
    selectedId = "";
    closeDeptSelectWithSave();
}


function receiveXML(reload) {;

    // alert(selectedId);
    equ_tree = new WebFXLoadTree("�豸ѡ��", "", METAR_WEB_ROOT + "/EquListSrv.do?isuse=<%=isuse%>&mainCode=<%=mainCode%>&selectedEquId=" + selectedId + "&reload=" + reload);
   
    document.getElementById("treediv").innerHTML = equ_tree.toString();
	
}


</SCRIPT>

<style type="text/css">
    <!--
    .activeTab {
        color: #000000;
        font-weight: bold;
        border: 2px ridge #C0C0C0;
        padding-left:
        2px;
        padding-right: 2px;
        padding-top: 1px;
        padding-bottom: 1px;
        background-color: #33CCFF;
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

<body style="margin : 0px;">
<table class="treeControl" width="100">
    <tr>
        <td colspan="2" height=20>
            <table border="0" cellpadding="0" cellspacing="0">
                <tr>
                    <td><div style="width: 80px;" id="treeTab" onclick="tabPanel.activePage(this);">�豸��ͼ</div></td>
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
                    
                    <td class="treeControl"><div class="treeControl" onclick="equ_tree.expandAll()"><IMG alt="ȫ��չ��"
                                                                                                           vspace="0"
                                                                                                           hspace="7"
                                                                                                           src="<%=request.getContextPath()%>/_image/_depticon/treeExpand_32.png">
                    </div></td>
                   
                    <td class="treeControl">
                        <div class="treeControl" onclick="equ_tree.collapseAll()"><IMG alt="ȫ����£" vspace="0"
                                                                                         hspace="5"
                                                                                         src="<%=request.getContextPath()%>/_image/_depticon/treeClose_32.png">
                        </div>
                    </td>
                    <td class="treeControl">
                        <div class="treeControl" onclick="reload()"><IMG alt="ˢ��" vspace="0" hspace="3"
                                                                         src="<%=request.getContextPath()%>/_image/_depticon/refurbish_32.png">
                        </div>
                    </td>
                    <td class="treeControl">
                        <div class="treeControl" onclick="cleanAll()"><IMG alt="���" vspace="0" hspace="3"
                                                                           src="<%=request.getContextPath()%>/_image/_depticon/clean_32.png">
                        </div>
                    </td>
                    <td class="treeControl">
                        <div class="treeControl" onmouseover="showPopTip()"><IMG id="tipPic" alt="��ʾ��Ϣ" vspace="0"
                                                                                 hspace="3"
                                                                                 src="<%=request.getContextPath()%>/_image/_depticon/info_32.png">
                        </div>
                    </td>
                    <td class="treeControl">
                        <div class="treeControl" onclick="closeDeptSelectWithSave()">

                            <IMG alt="�ر��ҷ���ֵ" vspace="0" hspace="3"
                                 src="<%=request.getContextPath()%>/_image/_depticon/ok_32.png">
                        </div>
                    </td>
                    <td class="treeControl">
                        <div class="treeControl" onclick="closeDeptSelectNoSave()"><IMG alt="�ر��Ҳ�����ֵ" vspace="0"
                                                                                        hspace="0"
                                                                                        src="<%=request.getContextPath()%>/_image/_depticon/close_alart_32.png">
                        </div>
                    </td>
                </tr>
            </table>
        </td>
    </tr>
</table>

<script language="javaScript">
    //����һЩ��ʼ��
	
    var sData = window.dialogArguments;
    var backNameIdObj = sData.document.getElementById("<%=objId%>");
    
    var selectedId = backNameIdObj.value;
    var sInputType = "<%=inputType%>";
   

    //��ʼxtree����
    var equ_tree;

    //����ѡ������
    //ѡ����checkbox����radio
    webFXTreeConfig.inputType = "<%=inputType%>";

    // XP Look
    webFXTreeConfig.rootIcon = "<%=request.getContextPath()%>/_script/xtree/images/xp/folder.png";
    webFXTreeConfig.openRootIcon = "<%=request.getContextPath()%>/_script/xtree/images/xp/openfolder.png";
    webFXTreeConfig.folderIcon = "<%=request.getContextPath()%>/_script/xtree/images/xp/folder.png";
    webFXTreeConfig.openFolderIcon = "<%=request.getContextPath()%>/_script/xtree/images/xp/openfolder.png";
    webFXTreeConfig.fileIcon = "<%=request.getContextPath()%>/_script/xtree/images/xp/file.png";
    webFXTreeConfig.lMinusIcon = "<%=request.getContextPath()%>/_script/xtree/images/xp/Lminus.png";
    webFXTreeConfig.lPlusIcon = "<%=request.getContextPath()%>/_script/xtree/images/xp/Lplus.png";
    webFXTreeConfig.tMinusIcon = "<%=request.getContextPath()%>/_script/xtree/images/xp/Tminus.png";
    webFXTreeConfig.tPlusIcon = "<%=request.getContextPath()%>/_script/xtree/images/xp/Tplus.png";
    webFXTreeConfig.iIcon = "<%=request.getContextPath()%>/_script/xtree/images/xp/I.png";
    webFXTreeConfig.lIcon = "<%=request.getContextPath()%>/_script/xtree/images/xp/L.png";
    webFXTreeConfig.tIcon = "<%=request.getContextPath()%>/_script/xtree/images/xp/T.png";
    webFXTreeConfig.tMinusIcon = "<%=request.getContextPath()%>/_script/xtree/images/Tminus.png";
    webFXTreeConfig.tPlusIcon = "<%=request.getContextPath()%>/_script/xtree/images/Tplus.png";
    webFXTreeConfig.blankIcon = "<%=request.getContextPath()%>/_script/xtree/images/blank.png";
	webFXTreeConfig.METAR_WEB_ROOT=METAR_WEB_ROOT;

    receiveXML(<%=reloadStr%>);
    tabPanel.addPage(document.getElementById("treeTab"), document.getElementById("treediv"));
    tabPanel.activePage(document.getElementById("treeTab"));
	
</script>


<script language="JavaScript">
    //alert(document.getElementById("00-icon"));
   
    treediv.scrollTop=treediv.scrollTop;
    //alert(treediv.scrollTop);


</script>

</body>
</html>