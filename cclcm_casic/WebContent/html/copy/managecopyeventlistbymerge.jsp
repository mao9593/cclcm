<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
    <title>复印申请管理</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" type="text/css" href="${ctx}/_css/displaytag.css" media="screen"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/_css/css200603.css"  media="screen"/>
    <link href="${ctx}/_script/calendar2/calendar-blue.css" type="text/css" rel="stylesheet"/>
    <script language="javascript" src="${ctx}/_script/calendar2/calendar.js"></script>
    <script language="javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>
    <script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
    <script language="javascript" src="${ctx}/_script/casic304_ajax.js"></script>
<script>
    $(document).ready(function(){
        onHover();
        preCalendar();
    });
    function selectRecvUser(word){
        var url = "${ctx}/basic/getfuzzyuser.action";
        if(word != ""){
           callServer1(url,"fuzzy="+word);
        }else{
           document.getElementById("allOptions").innerHTML="";
             }
    } 
    function updateResult(){
        if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
                if(xmlHttp.responseText.toString().length > 154){
                    document.getElementById("allOptions").innerHTML=xmlHttp.responseText;
                }else{
                    document.getElementById("allOptions").innerHTML="";
                }
            }else{
                document.getElementById("allOptions").innerHTML="";
            }
    }
    function add_True(){
        var user_iidd=$("#allOption").val();
        var user_name=$("#allOption option[value='"+user_iidd+"']").text();
        user_name=user_name.substring(0,user_name.indexOf("/"));
        if(user_iidd != ""){
            $("#user_iidd").val(user_iidd);
            $("#user_name").val(user_name);
            document.getElementById("allOptions").innerHTML="";
        }
    }
    function clearFindForm(){
        $("#QueryCondForm :text").val("");
        $("#QueryCondForm select").val("");
    }
    function chkCopy(event_code, tag,is_barcode){
        $("#event_code").val(event_code);
        $("#is_barcode").val(is_barcode);
        if(is_barcode=='Y')
        {
            var url = "${ctx}/copy/configbarcode.action";
            var rValue = window.showModalDialog(url,'', 'dialogHeight:240px;dialogWidth:400px;center:yes;status:no;scroll:no;help:no;unadorned:no;resizable:no');
            if(rValue != null && rValue!= undefined && rValue.onOK == "Y"){
                $("#n_dum").val(rValue.n_dum);  
                //$("#is_encrypt").val(rValue.is_encrypt);
                callServerPostForm("${ctx}/copy/updatecopystatus.action",document.forms[0]);    
                $(tag).attr("disabled",true);
            }   
        }else{
            callServerPostForm("${ctx}/copy/updatecopystatus.action",document.forms[0]);    
            $(tag).attr("disabled",true);
        }
    }
    function getAjaxResult(){
        if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {         
            if(xmlHttp.responseText != null && "" != xmlHttp.responseText){
                var paper_barcodes = xmlHttp.responseText.split(";");
                for (var i=0;i<paper_barcodes.length-1;i++){
                    var barcodetype = paper_barcodes[i].split("#")[0];
                    var fileno = paper_barcodes[i].split("#")[1];
                    var barcode = paper_barcodes[i].split("#")[2];  
                    var others = paper_barcodes[i].split("#")[3];   
                    var obj=new ActiveXObject("SprintCom.DataProcess.1");
                    var is_barcode = $("#is_barcode").val();
                    if(is_barcode=='Y'){
                        obj.PrintBarcodeInfo(barcodetype, fileno, barcode, $("#n_dum").val(), 1,others);    
                    } 
                }   
                alert("复印完成");
                $("#QueryCondForm").submit();   
            }else{
                alert("不打条码，复印已完成");
                $("#QueryCondForm").submit();   
            }
        }
    } 
</script>
</head>
<body oncontextmenu="self.event.returnValue=false">
<form method="POST"  id="hiddenform">
    <input type="hidden" name="event_code" id="event_code"/>
    <input type="hidden" id="n_dum"/>
    <input type="hidden" id="is_barcode"/>
    <input type="hidden" id="is_encrypt"/>
</form>
<form id="QueryCondForm" method="POST" action="${ctx}/copy/managecopyeventlistbymerge.action">
<table width="95%" border="0" cellspacing="0" cellpadding="0" align="center" class="table_only_border">     
    <tr>
        <td class="title_box">复印申请列表</td>
    </tr>
    <tr>
        <td align="right">
            <table  table border=0 class="table_box" cellspacing="0" cellpadding="0" width="100%">
                <tr>
                   <td align="center">密级 ：</td>
                   <td>
                        <c:set var="seclv1" value="${seclv_code}" scope="request"/>
                        <select name="seclv_code">
                            <option value="">--全部--</option>
                            <s:iterator value="#request.seclvList" var="seclv">
                                <option value="${seclv.seclv_code}" <s:if test="#request.seclv1==#seclv.seclv_code">selected="selected"</s:if>>${seclv.seclv_name}</option>
                            </s:iterator>
                        </select> 
                    </td>
                    <td align="center">用户名：</td>
                    <td>
                      <input type="text" id="user_name" name="user_name" value="${user_name}" onkeyup="selectRecvUser(this.value);"/><br>
                      <div id="allOptions" class="containDiv" style="position:absolute;border:0px solid black;padding:0px"></div>
                    </td>   
                     <td align="center">部门：</td>
                    <td>
                      <input type="text" id="dept_name" name="dept_name" value="${dept_name}"/>&nbsp;
                    </td>   
                    <td align="center" rowspan="2">
                        <input name="button" type="submit" class="button_2003" value="查询" onclick="return checkTime();">
                        <input name="button" type="button" class="button_2003" value="清空" onclick="clearFindForm();">
                    </td>
                </tr>
                <tr>
                <td align="center">复印状态：</td>
                    <td>
                        <select name="copy_status">
                            <option value="">--全部--</option>
                            <option value="0" <c:if test="${copy_status eq '0'}">selected</c:if>>未复印</option>
                            <option value="1" <c:if test="${copy_status eq '1'}">selected</c:if>>已复印</option>
                        </select> 
                    </td>   
                    <td align="center">添加时间：</td>
                    <td>
                        <input type="text" name="startTime" readonly="readonly" style="cursor:hand;" value="${startTime_str}"/>
                        <img src="${ctx}/_image/time2.jpg" id="startTime_trigger">
                    </td>
                    <td align="center">至:</td>
                    <td>
                        <input type="text" name="endTime" readonly="readonly" style="cursor:hand;" value="${endTime_str}"/>
                        <img src="${ctx}/_image/time2.jpg" id="endTime_trigger">
                    </td>
                    
                </tr>
                
            </table>
        </td>
    </tr>
    <tr>
        <td>
            <table class="displaytable-outter" cellspacing=0 cellpadding=0 border=0 width="100%">
                <tr>
                    <td>
                    <display:table requestURI="${ctx}/copy/managecopyeventlistbymerge.action" uid="item" class="displaytable" name="eventList" pagesize="15" sort="list">
                        <display:column title="序号">
                            <c:out value="${item_rowNum}"/>
                        </display:column>
                        <display:column title="复印类型" property="cycle_type_name"/>
                        <display:column title="文件名称" property="file_name" maxLength="15"/>
                        <display:column title="原文件编号" property="originalid" maxLength="15"/>                
                        <display:column title="密级" property="seclv_name"/>
                        <display:column title="申请人"  property="user_name"/> 
                        <display:column title="部门"  property="dept_name"/>                  
                        <display:column title="页数" property="page_num"/>
                        <display:column title="份数" property="copy_num"/>
                        <display:column title="用途" property="usage_name"/>              
                        <display:column title="复印状态" property="copy_status_name"/>
                        <display:column title="申请时间" property="apply_time_str"   sortable="true"/>
                        <display:column title="操作" style="width:280px">
                        <div>
                            <input type="button" class="button_2003" value="查看" onclick="go('${ctx}/copy/viewcopyeventdetail.action?event_code=${item.event_code}');"/>
                            <c:choose>
                                <c:when test="${item.copy_status eq '0'}">
                                    &nbsp;<input type="button" class="button_2003" value="打条码复印" onclick="chkCopy('${item.event_code}',this,'Y');"/>
                                    &nbsp;<input type="button" class="button_2003" value="不打条码复印" onclick="chkCopy('${item.event_code}',this,'N');"/>
                                </c:when>
                                <c:otherwise>
                                    &nbsp;<input type="button" class="button_2003" value="打条码复印" disabled="disabled"/>
                                    &nbsp;<input type="button" class="button_2003" value="不打条码复印" disabled="disabled"/>
                                </c:otherwise>
                            </c:choose>
                        </div>
                        </display:column>
                    </display:table>
                    </td>
                </tr>
            </table>
         </td>
    </tr>
</table>
</form>
</body>
</html>
