<%@ include file="/common/taglibs.jsp" %>
<%@ page language="java" pageEncoding="GBK" contentType="text/html;charset=GBK" %>


<html>
<head>
<!-- <meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
	 modified by renmingfei 2013-07-08  -->
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

<layout:skin includeScript="true"/>

<script type="text/javascript" src="<%=request.getContextPath()%>/_script/TabPanel.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/_script/pngfix.js"></script>
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/_script/xtree/xtree.css"/>
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/_css/treeControl.css"/>

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/common/yui/build/fonts/fonts.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/common/yui/build/treeview/assets/skins/sam/treeview.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/common/yui/build/yahoo/yahoo.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/common/yui/build/event/event.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/common/yui/build/connection/connection.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/common/yui/build/treeview/treeview.js"></script>
<!--Script and CSS includes for YUI datatable dependencies on this page-->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/common/yui/build/datatable/assets/skins/sam/datatable.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/common/yui/build/yahoo-dom-event/yahoo-dom-event.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/common/yui/build/element/element-beta.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/common/yui/build/dom/dom.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/common/yui/build/utilities/utilities.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/common/yui/build/datasource/datasource-beta.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/common/yui/build/datatable/datatable-beta.js"></script>


<SCRIPT language="JavaScript">
var tabPanel = new TabPanel("activeTab", "otherTab");
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
        background-color: #CFCFCF;
        cursor: hand;
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

<body class="yui-skin-sam" style="margin : 0px;"  >
<table class="treeControl" width="100"  >
    <tr>
        <td colspan="2" height=20>
            <table border="0" cellpadding="0" cellspacing="0">
                <tr>
                    <td><div style="width: 60px;" id="treeTab" onclick="tabPanel.activePage(this);" >资源树</div></td>
                    <td><input type="text" style="width: 200px;"  id="searchTxt" onkeydown="fkeydown();"></td>
                    <td><div style="width: 40px;" id="searchTab" onclick="tabPanel.activePage(this);abc();">搜索</div></td>
                </tr>
            </table>
        </td>
    </tr>
    <tr>
        <td colspan="2" style="border:1px solid black" valign="top">
            <div style="width:350px;height:435px;overflow :scroll;" id="treeDiv" ondblclick="tree_ok();"><br/>
            </div>
            <div style="width:350px;height:435px;overflow :scroll;" id="searchDiv" ><br/>
                <div id="dt-pag-nav">
                    <span id="prevLink">&lt;</span> 显示第
                    <span id="startIndex">0</span> - <span id="endIndex">0</span>
                    <span id="ofTotal"></span> <span id="nextLink">&gt;</span>
                </div>
                <div id="searchResultDiv" ondblclick="tree_ok();"></div>
            </div>
          </td>
    </tr>


    <tr>
        <td>
            <table>
                <tr>
					<td class="treeControl"></td><td class="treeControl"></td><td class="treeControl"></td><td class="treeControl"></td>
					<td class="treeControl"></td><td class="treeControl"></td><td class="treeControl"></td><td class="treeControl"></td>
                    <td class="treeControl">
                        <div class="treeControl" onclick="tree_ok()">
                            <IMG alt="选择" vspace="0" hspace="3"
                                 src="<%=request.getContextPath()%>/_image/_depticon/ok_32.png">
                        </div>
                    </td>
                    <td class="treeControl">
                        <div class="treeControl" onclick="closeDeptSelectNoSave()">
						<IMG alt="关闭" vspace="0" hspace="0"
                             src="<%=request.getContextPath()%>/_image/_depticon/close_alart_32.png">
                        </div>
                    </td>
                </tr>
            </table>
        </td>
    </tr>
</table>

<%
    String strTreeId = request.getParameter("treeid");
    String strTreeName = (String) request.getAttribute("treename");
    String strTargetId = request.getParameter("targetid");
    String strRootTypeCode= request.getParameter("roottypecode");
    String strRootCode= request.getParameter("rootcode");
    String strRootName= request.getParameter("rootname");
%>
<script type="text/javascript">
    tabPanel.addPage(document.getElementById("treeTab"), document.getElementById("treeDiv"));
    tabPanel.addPage(document.getElementById("searchTab"), document.getElementById("searchDiv"));
    tabPanel.activePage(document.getElementById("treeTab"));

</script>
<script type="text/javascript">

    var objValue;
    var objCode;
    var objTypeCode;
	var nodeSelected=false;
    var dataTableSelected=false;

YAHOO.example.treeExample = function (){

    var tree, currentIconMode;
    var treeid,treename,rootTypeCode,rootCode,rootName;

    function buildTree() {
        //create a new tree:
        tree = new YAHOO.widget.TreeView("treeDiv");

        //turn dynamic loading on for entire tree:
        tree.setDynamicLoad(loadNodeData, currentIconMode);

        //get root node for tree:
        var root = tree.getRoot();

        treeid="<%=strTreeId%>";
        treename="<%=strTreeName%>";
        rootTypeCode="<%=strRootTypeCode%>";
        rootCode="<%=strRootCode%>";
        rootName="<%=strRootName%>";
        if(rootName!="undefined")
        {
            treename=rootName;
        }

        var tempNode = new YAHOO.widget.TextNode(treename, root, false); 
        if (rootTypeCode!="undefined")
        {
            tempNode.nodeTypeCode= rootTypeCode;
            tempNode.nodeCode=rootCode;
        }
        tree.subscribe("labelClick", function(node) {
            if(node.iHaveChild=="-1"){
                objValue=node.label;
                objCode=node.nodeCode;
                objTypeCode=node.nodeTypeCode;
				nodeSelected=true;
            }
			else
			{
				nodeSelected=false;
			}
            
        });

        tree.draw();
        tempNode.expand();

    }

    function loadNodeData(node, fnLoadComplete)  {
        if(!node.iHaveChild)
        {
            fnLoadComplete();
            return;
        }
        var sUrl = "<%=request.getContextPath()%>/AjaxTreeNodeExpandAction.do?treeid="+treeid+"&nodetypecode="+node.nodeTypeCode+"&nodecode="+node.nodeCode;

        var callback = {
            success: function(oResponse) {
                var oResults = eval("(" + oResponse.responseText + ")");
                var tempNode;
                var i=0;
                while (oResults[i]){
                    tempNode= new YAHOO.widget.TextNode(oResults[i], node, true);
                    i++;
                }
                oResponse.argument.fnLoadComplete();
            },
            failure: function(oResponse) {
                oResponse.argument.fnLoadComplete();
            },

            argument: {
                "node": node,
                "fnLoadComplete": fnLoadComplete
            },
            timeout: 7000
        };
        YAHOO.util.Connect.asyncRequest('GET', sUrl, callback);
    }

    return {
        init: function() {
            currentIconMode = 1;
            buildTree();
        }
    }

}() ;

//once the DOM has loaded, we can go ahead and set up our tree:
YAHOO.util.Event.onDOMReady(YAHOO.example.treeExample.init, YAHOO.example.treeExample);
</script>

     <!--BEGIN search result table =============================== -->
<script type="text/javascript">
    var searchCount=0;
    function abc() {
        if(searchCount==0)
        {
            initSearchTable();
            searchCount++;
        }else{
            this.getPage(1);
        };
    }
    function getParams(startIndex){
        var obj;
        obj=document.getElementById('searchTxt');
        var params = "rootcode=<%=strRootCode%>&startidx=" + startIndex +"&treeid=<%=strTreeId%>&key="+obj.value;
        return params;
    }
    function initSearchTable(){
        var sUrl = "<%=request.getContextPath()%>/AjaxSearchAction.do?";
        var myColumnDefs = [
        {key:"text",label:"搜索结果：",sortable:true,width:"28em"}
                ];
        var params=getParams(1);
        var myConfigs = {
            sortedBy:{key:"text",dir:"asc"},
            initialRequest:params,
            rowSingleSelect:true
        }
        this.myDataSource = new YAHOO.util.DataSource(sUrl);
        this.myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON;
        this.myDataSource.connXhrMode = "queueRequests";
        this.myDataSource.responseSchema = {
            resultsList: "ResultSet",
            fields: ["code","label","type","text"]
        };

        this.myDataTable = new YAHOO.widget.DataTable("searchResultDiv", myColumnDefs,
                this.myDataSource, myConfigs);
        this.myDataTable.subscribe("rowClickEvent",this.myDataTable.onEventSelectRow);
        this.myDataTable.subscribe("rowClickEvent",dataTableSelectRow);
        function dataTableSelectRow(oArgs) {
            var oRecord = this.getRecord(oArgs.target);
            objValue=oRecord.getData("label");
            objCode=oRecord.getData("code");
            objTypeCode=oRecord.getData("type");
            dataTableSelected=true;
        }
        var totalRecords;
        // Custom code to parse the raw server data for Paginator values and page links
        this.myDataSource.doBeforeCallback = function(oRequest, oRawResponse, oParsedResponse) {
            // Get Paginator values
            var oResults = eval("(" + oRawResponse + ")");

            var recordsReturned = oResults.recordsReturned; // How many records this page
            var startIndex = oResults.startIndex; // Start record index this page
            var endIndex = startIndex + recordsReturned -1; // End record index this page
            totalRecords = oResults.totalRecords; // Total records all pages
            if(endIndex<=0) endIndex=0;
            // Update the DataTable Paginator with new values
            var newPag = {
                recordsReturned: recordsReturned,
                startRecordIndex: startIndex,
                endIndex: endIndex,
                totalRecords: totalRecords
            }
            myDataTable.updatePaginator(newPag);

            // Update the links UI
            YAHOO.util.Dom.get("prevLink").innerHTML = (startIndex === 1) ? "&lt;" :
                                                       "<a href=\"#previous\" alt=\"后退\">&lt;</a>" ;
            YAHOO.util.Dom.get("nextLink").innerHTML =
            (endIndex >= totalRecords) ? "&gt;" :
            "<a href=\"#next\" alt=\"前进\">&gt;</a>";
            YAHOO.util.Dom.get("startIndex").innerHTML = startIndex;
            YAHOO.util.Dom.get("endIndex").innerHTML = endIndex;
            YAHOO.util.Dom.get("ofTotal").innerHTML = " 条,共搜索到 " + totalRecords+" 条 ";

            // Let the DataSource parse the rest of the response
            return oParsedResponse;
        };
        // Hook up custom pagination
        this.getPage = function(nStartRecordIndex, nResults) {
            // If a new value is not passed in
            // use the old value
            //                if(!YAHOO.lang.isValue(nResults)) {
            //                    nResults = this.myDataTable.get("paginator").totalRecords;
            //                }
            // Invalid value
            if(!YAHOO.lang.isValue(nStartRecordIndex)) {
                return;
            }
            //                alert("hi:"+newRequest);
            var newRequest=getParams(nStartRecordIndex);
            this.myDataSource.sendRequest(newRequest, this.myDataTable.onDataReturnInitializeTable, this.myDataTable);
        };
        this.getPreviousPage = function(e) {
            YAHOO.util.Event.stopEvent(e);
            // Already at first page
            if(this.myDataTable.get("paginator").startRecordIndex <= 1) {
                return;
            }
            //                var newStartRecordIndex = this.myDataTable.get("paginator").startRecordIndex - this.myDataTable.get("paginator").rowsThisPage;
            var newStartRecordIndex = this.myDataTable.get("paginator").startRecordIndex - 15;
            this.getPage(newStartRecordIndex);
        };
        this.getNextPage = function(e) {
            YAHOO.util.Event.stopEvent(e);
            // Already at last page
            if(this.myDataTable.get("paginator").startRecordIndex +
               this.myDataTable.get("paginator").rowsThisPage >=
               totalRecords) {
                return;
            }
            var newStartRecordIndex = (this.myDataTable.get("paginator").startRecordIndex + this.myDataTable.get("paginator").rowsThisPage);
            this.getPage(newStartRecordIndex);
        };
        YAHOO.util.Event.addListener(YAHOO.util.Dom.get("prevLink"), "click", this.getPreviousPage, this, true);
        YAHOO.util.Event.addListener(YAHOO.util.Dom.get("nextLink"), "click", this.getNextPage, this, true);
    };
</script>
<!--END  search result table =============================== -->
<script type="text/javascript">
function fkeydown()
{
	if(event.keyCode==13){
		tabPanel.activePage(document.getElementById("searchTab"));
        abc();
    }
}

//document.searchTxt.onkeydown=fkeydown;


function tree_ok()
{
    if(tabPanel.getActivePageID()=="treeTab")
    {
        if (nodeSelected==false)
        {
            //		alert("请选择节点！");
            return;
        }


    }
    else{

        if( dataTableSelected==false)
        {
            return;
        }
    }
    var obj;
        obj=eval("window.opener.document.getElementById('<%=strTargetId%>')");
        if (obj )
        {
            obj.value=objValue;
            obj.code=objCode;
            obj.typecode=objTypeCode;
        }

        if (objCode)
        {
            var callbackFun;
            callbackFun = eval("window.opener.tree_ok");
            if (callbackFun)
            {
                callbackFun('<%=strTargetId%>',objTypeCode,objCode,objValue);
            }
        }
        window.close();
}
            
function closeDeptSelectNoSave()
{
    window.close();
}

</script>
</body>
</html>