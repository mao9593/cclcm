<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <link rel="stylesheet" type="text/css" href="${ctx}/_css/displaytag.css" media="screen"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/_css/css200603.css"  media="screen"/>
    <script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
    <script language="javascript" src="${ctx}/_script/casic304_ajax.js"></script>
    <script type="text/javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>
<script>

$(document).ready(function(){
    onHover();
    addSelectAllCheckbox();
    $("#tr_send input").attr("disabled",true);
    $("#tr_send").hide();
    $("#submit_btn").attr("disabled",true);
    $("#for_display").show();
    $("#copy_number").hide();
    $("#merge").hide();
     //合并复印使用说明
    $("#mergecopy").next().hover(function (){
    	$("#sp1").css({"color":"red","font-size":"9pt","font-weight":"bold"}).html("选择合并复印，可将打印和复印流程合并一起，只需要申请一次流程即可完成打印和复印行为。打印完成后到复印管理员处进行该文件的复印！");
    },function (){
    	$("#sp1").html("");
    });
    //特殊打印使用说明
    $("#specialprint").next().hover(function (){
    	$("#sp2").css({"color":"red","font-size":"9pt","font-weight":"bold"}).html("打印输出的涉密文件总份数超过10份时，请选择特殊打印流程；非特殊打印流程，一个审批单只支持不超过10份涉密文件的打印申请！");
    },function (){
    	$("#sp2").html("");
    });
});

var secretflag = "";
var specialflag = "N";
function chkSubmit()
{   
    if($("#proxyprint_user_name").val().trim() == '${curUser.user_name}'){
            alert("委托人不能填写本人");
            $("#proxyprint_user_name").focus();
            return false;
        }
    
    if (secretflag == "Y") {
		if($("#file_scope").val() == ""){
	        alert("请填写文件知悉范围");
	        $("#file_scope").focus();
	        return false;
	    }
	    if($("#file_scope").val().length>1000){
	    	alert("知悉范围最多1000字，请重新填写");
	    	$("#file_scope").focus();
	        return false;
	    }
		if($("#seclv_accord").val() == ""){
	        alert("请填写定密依据");
	        $("#seclv_accord").focus();
	        return false;
	    }
	    if($("#seclv_accord").val().length>1000){
	    	alert("定密依据最多1000字，请重新填写");
	    	$("#seclv_accord").focus();
	        return false;
	    }
		if($("#secret_time").val() == ""){
	        alert("请填写保密期限");
	        $("#secret_time").focus();
	        return false;
	    }
	    if($("#secret_time").val().length>1000){
	    	alert("保密期限最多1000字，请重新填写");
	    	$("#secret_time").focus();
	        return false;
	    }
    }
    
    if($("#cycle_type").val() == ""){
        alert("请选择状态");
        $("#cycle_type").focus();
        return false;
    }
        //是否选择合并复印
    if($("#cycle_type").val() == "REMAIN"){
        if($("#is_merge").val().trim() == ""){
            alert("请选择是否为合并复印");
            $("#is_merge").focus();
            return false;
        }
    }
    //是否填写了接收人
    if($("#cycle_type").val() == "SEND"){
        if($("#output_dept_name").val().trim() == ""){
            alert("请填写接收单位");
            $("#output_dept_name").focus();
            return false;
        }
        if($("#output_dept_name").val().length>100){
            alert("接收单位名字长度不能超过100个字符");
            $("#output_dept_name").focus();
            return false;
        }
        if($("#output_user_name").val().trim() == ""){
            alert("请填写接收人");
            $("#output_user_name").focus();
            return false;
        }
    }
    //是否选择了用途
    
    if($("#usage_code").val() == ""){
        alert("请选择用途");
        $("#usage_code").focus();
        return false;
    }
    if (specialflag == "Y") {
    	if($("#comment").val() == ""){
			alert("请填写具体说明：描述该任务的数量等信息");
			$("#comment").focus();
			return false;
		}
    }
    //是否选择了审批人员
    if($("#next_approver_all option").size() > 0 && $("#next_approver_sel option").size() == 0){
        alert("请选择审批人员");
        $("#next_approver_all").focus();
        return false;
    }
    //判断文件密级选择是否合理
    var file_sec_wrong = null;
    var list_seclv_code = "";
    $("select[name='file_seclv_code']").each(function(){
        list_seclv_code += this.selectedIndex + ",";
        if(this.selectedIndex == 0 || this.selectedIndex < document.getElementById("seclv_code").selectedIndex){
            file_sec_wrong = this;
            return false;
        }
    });
    if(file_sec_wrong != null){
        alert("请指定打印文件的密级，且不能高于作业审批密级");
        //$("select[name='file_seclv_code'][value='']").first().focus();
        $(file_sec_wrong).focus();
        return false;
    }
    
    //文件名称是否为空
    var file_title_blank = null;
     $("input[name='file_title']").each(function(){
        if(this.value.trim() == ""){
            file_title_blank = this;
            return false;
        }
    }); 
    if(file_title_blank != null){
        alert("文件名称不能为空");
        $(file_title_blank).focus();
        return false;
    }
    //判断文件打印份数输入是否合理
    var file_count_wrong = null;
    var file_count_zero = null;
    var list_print_count = "";
    var listcount = 0;
    $("input[name='print_count']").each(function(){
        listcount += 1;
        list_print_count += this.value + ",";
        if(!isInteger(this.value)){
            file_count_wrong = this;
            return false;
        }
        if(parseInt(this.value)==0){
            file_count_zero = this;
            return false;
        }
    });
    
    
    // 统计涉密文件的总打印份数
    var allsecuritycount = 0;
    if (specialflag == "N") {
        $("#is_special_print").val(specialflag);
        
        for(var i=0; i<listcount; i++){
           var tempPrintCountArr = list_print_count.split(",");
           var tempSeclvCodeArr = list_seclv_code.split(",");
           if(tempSeclvCodeArr[i] < 5){
                allsecuritycount = allsecuritycount + parseInt(tempPrintCountArr[i]);
           }
        } 
        
        if(allsecuritycount > 9){
            alert("涉密文件总打印份数不能超过10份！请调整打印份数！");
            return false;
        }
    }
    
    if(file_count_wrong != null){
        alert("打印份数只能填写合法数字");
        $(file_count_wrong).focus();
        return false;
    }
    if(file_count_zero != null){
        alert("打印份数不能为0");
        $(file_count_zero).focus();
        return false;
    }
    //审批人信息
    var next_approver = "";
    $("#next_approver_sel option").each(function(){
        next_approver += this.value+",";
    });
    var len = next_approver.length;
    if (next_approver.lastIndexOf(",") == len - 1){
        next_approver = next_approver.substring(0, len - 1);
    }
    $("#next_approver").val(next_approver);
    //文件密级信息
    var file_seclvs = "";
    $("select[name='file_seclv_code']").each(function(){
        file_seclvs += this.value+",";
    });
    
    var len = file_seclvs.length;
    if (file_seclvs.lastIndexOf(",") == len - 1){
        file_seclvs = file_seclvs.substring(0, len - 1);
    }
    $("#file_seclvs").val(file_seclvs);
    
    //文件名称信息
    var file_titles = "";
    $("input[name='file_title']").each(function(){
        file_titles += this.id+":"+this.value+",";
    });
    var len = file_titles.length;
    if (file_titles.lastIndexOf(",") == len - 1){
        file_titles = file_titles.substring(0, len - 1);
    }
    $("#file_titles").val(file_titles);
    //文件份数信息
    var file_printcounts = "";
    $("input[name='print_count']").each(function(){
        file_printcounts += this.id+":"+this.value+",";
    });
    var len = file_printcounts.length;
    if (file_printcounts.lastIndexOf(",") == len - 1){
        file_printcounts = file_printcounts.substring(0, len - 1);
    }
    $("#file_printcounts").val(file_printcounts);
    //文件色彩信息
    var file_colors = "";
    $("select[name='file_color']").each(function(){
        file_colors += this.value+",";
    });
    var len = file_colors.length;
    if (file_colors.lastIndexOf(",") == len - 1){
        file_colors = file_colors.substring(0, len - 1);
    }
    $("#file_colors").val(file_colors);
    //文件单双面打印信息
    var file_printdoubles = "";
    $("select[name='file_print_double']").each(function(){
        file_printdoubles += this.value+",";
    });
    var len = file_printdoubles.length;
    if (file_printdoubles.lastIndexOf(",") == len - 1){
        file_printdoubles = file_printdoubles.substring(0, len - 1);
    }
    $("#file_printdoubles").val(file_printdoubles);
    
    var event_ids = "";
    var check_result = true;
    if($(":checkbox:checked[value!=''][name='event_id']").size() == 0){
        alert("请先勾选需要审批的作业");
        return false;
    }else{
        $(":checkbox:checked[value!=''][name='event_id']").each(function (){
            event_ids += this.value+":";
        });
        $("#event_ids").val(event_ids);
    }
        
    $("#submit_btn").attr("disabled",true); 
    //判断复印份数输入是否合理
    if($("#is_merge").val().trim() == "yes"){
    var numval = /^[0-9]+$/;
    if(!numval.test( $("#copy_num").val())){
        alert("复印份数应为整数");
        $("#copy_num").focus();
        return false;
    }else if ($("#copy_num").val()=="0"){
      alert("复印份数不能为0");
        $("#copy_num").focus();
        return false;
    }
    }
    
    //ajax提交
    var url = "${ctx}/print/addprintprocessjob.action";
    var keywords = $("#keywords").val();
    if($("#keywords").val() != "" && "${isKeywordEnable}" == "true" && $("#seclv_code").val()>3){
        if(confirm('您的作业可能存在高密低出，确定要提交该作业？')){
            var urlEnsure = "${ctx}/print/ensuresubevent.action?event_ids="+escape(event_ids)+"&file_seclvs="+file_seclvs;
            var rValue=window.showModalDialog(urlEnsure,'', 'dialogHeight:600px;dialogWidth:700px;center:yes;status:no;scroll:no;help:no;unadorned:no;resizable:no');
            if(rValue != null && rValue!= undefined){
                if(1 == rValue.confirmsubmit){
                     callServerPostForm1(url,document.forms[0]);
                  }     
                }   
            }
    }else{
        var urlEnsure = "${ctx}/print/ensuresubevent.action?event_ids="+escape(event_ids)+"&file_seclvs="+file_seclvs+"&file_titles="+encodeURI(encodeURI(file_titles));
        var rValue=window.showModalDialog(urlEnsure,'', 'dialogHeight:600px;dialogWidth:700px;center:yes;status:no;scroll:no;help:no;unadorned:no;resizable:no');   
        if(rValue != null && rValue!= undefined){
            if(1 == rValue.confirmsubmit){
               callServerPostForm1(url,document.forms[0]);
            }
        }
    }
    
    return true;
}
function getAjaxResult1(){
    if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
        if(xmlHttp.responseText == "ok"){
            alert("任务添加完成");
            window.location="${ctx}/print/manageprintjob.action";
        }else if(xmlHttp.responseText == "nnd"){
            window.location="${ctx}/print/printbyself.action";
            $("#submit_btn").attr("disabled",false);
        }else{
           window.location="${ctx}/print/manageprintjob.action";
        }
        
        
    }
}
function selectNextApprover(){
    del_all_True('next_approver_sel');
    var url = "${ctx}/basic/getnextapprover.action";
    
    if (specialflag == "Y") {
        // 若为特殊打印，则走特殊打印流程
        $("#jobType").val("SPECIAL_PRINT_ZWYJG");
    }else if (specialflag == "N"){
        $("#jobType").val("PRINT_"+$("#cycle_type").val());
    }
    callServerPostForm(url,document.forms[0]);
}
function selectSeclv(seclv){
    if(seclv == ""){
        alert("请选择作业审批密级,以确认审批流程");
        $("#seclv_code").focus();
        return false;
    }else {
        if($("#keywords").val() != "" && "${isKeywordEnable}" == "true"){
            alert("文件中包含关键字,请注意密级的选择");
        }
        $("select[name='file_seclv_code']").each(function(){
            index = $(this).val().indexOf(":");
            $(this).val($(this).val().substring(0,index+1)+seclv);
        });
        if($("#cycle_type").val() != "" && $("#usage_code").val() != ""){
            selectNextApprover();
        }
    }
}

function selectFileSeclv(seclv){
    var tempFirstVal = $("select[name='file_seclv_code']")[0].value;
    var highSeclvCode = tempFirstVal.substring(tempFirstVal.indexOf(":") + 1);
    $("select[name='file_seclv_code']").each(function(){
        var str1 =this.value;
        var str2 = str1.substring(str1.indexOf(":") + 1);
        if((str2 != null && str2 != "")&& str2<=highSeclvCode)
        {
           highSeclvCode = str2;
        }
    });
    $("#seclv_code").val(highSeclvCode);
    if($("#cycle_type").val() != "" && $("#usage_code").val() != ""){
           selectNextApprover();
        }
}

function selectCycle(cycle){
    if(cycle == ""){
        alert("请选择打印状态,以确认审批流程");
        $("#cycle_type").focus();
        return false;
    }else if($("#seclv_code").val() != "" && $("#usage_code").val() != ""){
        selectNextApprover();
    }
    if(cycle == "SEND"){
        /*if($("select[name='file_seclv_code']").size() > 5){
            alert("由于外发回执单篇幅有限，一次打印外发的文件数不能超过5个，请返回申请页面重新勾选");
            $("#submit_btn").attr("disabled",true);
            return false;
        }*/
        $("#tr_send input").attr("disabled",false);
        $("#tr_send").show();
        $("#tr_merge input").attr("disabled",true);
        $("#tr_merge").hide();
    }else{
        $("#submit_btn").attr("disabled",false);
        $("#tr_merge input").attr("disabled",false);
        $("#tr_merge").show();
        $("#tr_send input").attr("disabled",true);
        $("#tr_send").hide();
    }
    if(cycle == "REMAIN"){
        $("#period").show();
    }else{
        $("#period").hide();
    }
}
 function selectMerge(merge){
    if(merge == ""){
        alert("请选择是否合并复印,以确认审批流程");
        $("#is_merge").focus();
        return false;
    }
    if($("#is_merge").val() == "yes") {
    $("#copy_num input").attr("disabled",false);
    $("#copy_number").show();
    $("#merge").show();
    $("#for_display").hide();
    }else{
    $("#copy_number").hide();
    $("#merge").hide();
    $("#for_display").show();
    
} 
}

function selectUsage(usage){
    if(usage == ""){
        alert("请选择用途,以确认审批流程");
        $("#usage_code").focus();
        return false;
    }else if($("#seclv_code").val() != "" && $("#cycle_type").val() != ""){
        selectNextApprover();
    }
}
function getAjaxResult(){
     if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
        if(xmlHttp.responseText.indexOf("<SELECT") != -1){
            $("#tr_approver").show();
            document.getElementById("allApprovers").innerHTML=xmlHttp.responseText;
            $("#submit_btn").attr("disabled",false);
        }else{
            $("#tr_approver").hide();
            alert(xmlHttp.responseText);
            $("#submit_btn").attr("disabled",true);
        }
        if($("#next_approver_all").size() > 0 && $("#next_approver_all").children().size() == 0){
            $("#tr_approver").hide();
        }
    } 
}
function showPrintFile(filestorename,pagecount){    
        if("Microsoft Internet Explorer"==navigator.appName){
        var FileStoreNameLen = filestorename.length;
        var unzipdirname = filestorename.substr(0,FileStoreNameLen-4);
        var url ="${ctx}/print/showprintfile.action?unzipdirname="+unzipdirname+"&pagecount="+pagecount;
        window.showModalDialog(url,window,"dialogHeight:"+(screen.availHeight-40)+";dialogWidth:"+(screen.availWidth-5)+";center:yes;resizable:no;status:no;scroll:no;help:no");
        return false;
        }
        else{
            var FileStoreNameLen = filestorename.length;
            var unzipdirname = filestorename.substr(0,FileStoreNameLen-4);  
            var url ="${ctx}/print/showprintfilelinux.action?unzipdirname="+unzipdirname;
            window.showModalDialog(url,window);
            //onunload = live();
            return false;
        }
        
    }
function delMultiEvent(){
        var del_event_ids = "";
        var check_result=true;
        if($(":checkbox:checked[value!=''][name='event_id']").size() == 0){
            alert("请先勾选需要删除的作业");
            return false;
        }else{
            $(":checkbox:checked[value!=''][name='event_id']").each(function (){
                del_event_ids += this.value+":";
            });
            if(check_result){
                if(confirm('确定要删除以上作业？')){
                    $("#del_event_ids").val(del_event_ids);
                    $("#del_form").submit();
                }
            }
        }
}

function selectRecvUser(word){
    $("#proxyprint_user_iidd").val(" ");
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
function add_True(param1,param2){
    if(param1 == "allOption"){
        var duty_user_iidd=$("#allOption").val();
        var duty_user_name=$("#allOption option[value='"+duty_user_iidd+"']").text();
        duty_user_name=duty_user_name.substring(0,duty_user_name.indexOf("/"));
        if(duty_user_iidd != ""){
            $("#proxyprint_user_iidd").val(duty_user_iidd);
            $("#proxyprint_user_name").val(duty_user_name);
            document.getElementById("allOptions").innerHTML="";
            }
        }else{
            add_Select_True(param1,param2);
        }
}
function add_Select_True(allOption,selectOption){
    var all = document.getElementById(allOption);
    var select = document.getElementById(selectOption);
    if (all.selectedIndex > -1){
        selected_spr_text = all.options[all.selectedIndex].text;
        selected_spr_value = all.options[all.selectedIndex].value;
        var sel_sprlen = select.options.length - 1;
        var exist_flag = 1;
        var j = 0;
        for(j = 0; j <= sel_sprlen; j++){
            if(select.options[j].value == selected_spr_value){
                exist_flag = 0;
                break;
            }
        }
        if(exist_flag){
            var temp = new Option(selected_spr_text);
            temp.value = selected_spr_value;
            select.options[++sel_sprlen] = temp;
        }
        else{
            alert("'" + selected_spr_text + "'" + "该选项已存在于右边列表中！请重新选择");
        }
    }
}

function setIsSecret(is_secret){
    secretflag = is_secret;
    if (secretflag == "N") {
        document.getElementById("secrettr1").style.display="none";
        $("textarea[class^='fixList']").val("");
        $("input[class^='clearVal']").prop("checked",false);
    } else if (secretflag == "Y") {
        document.getElementById("secrettr1").style.display="block";
    }
}

function setIsSpecial(is_special){
    if(is_special == ""){
        alert("请选择是否特殊打印");
        return false;
    }else{
    	specialflag = is_special;
   		$("#is_special_print").val(specialflag);
   		if($("#seclv_code").val() != "" && $("#usage_code").val() != ""){
            selectNextApprover();
        }
    }
}

function selectFileScope(){
	var test = "";
	if($(":radio:checked[name='file_scope_id']").size()>0){
		$(":radio:checked[name='file_scope_id']").each(function(){
			$("#file_scope").attr("disabled",false);
			test = this.value;
			$("#file_scope").val(test);
		});
	}
}

function selectSeclvAccord(){
	var test = "";
	if($(":radio:checked[name='seclv_accord_id']").size()>0){
		$(":radio:checked[name='seclv_accord_id']").each(function(){
			$("#seclv_accord").attr("disabled",false);
			test = this.value;
			$("#seclv_accord").val(test);
		});
	}
}

function selectSecretTime(){
	var test = "";
	if($(":radio:checked[name='secret_time_id']").size()>0){
		$(":radio:checked[name='secret_time_id']").each(function(){
			$("#secret_time").attr("disabled",false);
			test = this.value;
			$("#secret_time").val(test);
		});
	}
}
</script>
</head>
<body oncontextmenu="self.event.returnValue=false">
<table width="95%" border="1" cellspacing="0" cellpadding="0" align="center" class="table_box">
<form method="post" action="${ctx}/print/addprintprocessjob.action" id="hid_form" >
    <input type="hidden" name="submit" value="Y"/>
    <input type="hidden" name="dept_id" value="${curUser.dept_id}"/>
    <input type="hidden" name="jobType" id="jobType"/>
    <input type="hidden" name="event_ids" id="event_ids" value="${event_ids}"/>
    <input type="hidden" name="next_approver" id="next_approver"/>
    <input type="hidden" name="file_seclvs" id="file_seclvs"/>
    <input type="hidden" name="file_printcounts" id="file_printcounts"/>
    <input type="hidden" name="file_colors" id="file_colors"/>
    <input type="hidden" name="file_printdoubles" id="file_printdoubles"/>
    <input type="hidden" name="file_titles" id="file_titles"/>
    <input type="hidden" name="keywords" id="keywords" value="${keywords}"/>
    <input type="hidden" name="is_special_print" id="is_special_print"/>
    <input type="hidden" name="_chk" id="_chk" value="${_chk}"/>
    <tr>
        <td colspan="6" class="title_box">生成审批单</td>
    </tr>
    <tr> 
        <td width="15%" align="center">申请用户： </td>
        <td width="35%"><font color="blue"><b>${curUser.user_name}</b></font></td>
        <td width="15%" align="center">用户部门： </td>
        <td width="35%"><font color="blue"><b>${curUser.dept_name}</b></font></td>
    </tr>
    <tr>
        <td align="center"><font color="red">*</font>&nbsp;作业审批密级：</td>
        <td>
            <select name="seclv_code" id="seclv_code" disabled="disabled" onchange="selectSeclv(this.value);">
                <option value="">--请选择--</option>
                <s:iterator value="#request.seclvList" var="item">
                    <option value="${item.seclv_code}">${item.seclv_name}</option>
                </s:iterator>
            </select>
        </td>
        <td align="center">委托打印人： </td>
        <td>
            <input type="text" id="proxyprint_user_name" name="proxyprint_user_name" onkeyup="selectRecvUser(this.value);" size="20"/>
            <input type="hidden" id="proxyprint_user_iidd" name="proxyprint_user_iidd"  value="${proxyprint_user_iidd}" size="10"/>
            <br>
            <div id="allOptions" class="containDiv" style="position:absolute;border:0px solid black;padding:0px">
            </div>
        </td>
    </tr>
    <tr>
    <td align="center" ><font color="red">*</font>&nbsp;状态： </td>
            <td>
            <select name="cycle_type" id="cycle_type" onchange="selectCycle(this.value);">
                <option value="REMAIN">留用</option>
                <option value="FILE">归档</option>
                <option value="SEND">外发</option>
            </select>&nbsp;&nbsp;&nbsp;
            <select name="period" id="period">
                        <option value="S">短期</option>
                        <option value="L">长期</option>
            </select>
            </td>
            <td align="center"><font color="red">*</font>&nbsp;用途： </td>
            <td><select name="usage_code" id="usage_code" onchange="selectUsage(this.value);">
                <option value="">--请选择--</option>
                <s:iterator value="#request.usageList" var="usage">
                    <option value="${usage.usage_code}">${usage.usage_name}</option>
                </s:iterator>
            </select>
            </td>
    </tr>
    <tr id="tr_merge"> 
        <td align="center" id="mergecopy"><font color="red">*</font>是否合并复印： </td>
        <td><select name="is_merge" id="is_merge" onchange="selectMerge(this.value);">
                <option value="no" selected>否</option>
                <option value="yes">是</option> 
            </select>&nbsp;&nbsp; 
            <span id="sp1"></span>  
        </td>
        <td id="merge" align="center">&nbsp;<font color="red">*</font>复印份数： </td>
        <td id="copy_number"><input type="text" name="copy_num" id="copy_num" title="复印份数"/>&nbsp;</td>
        <td id="for_display" colspan="2">&nbsp;</td>
        
    </tr>
    <tr id="tr_send"> 
        <td align="center"><font color="red">*</font>接收单位： </td>
        <td><textarea name="output_dept_name" id="output_dept_name" title="打印状态为外发时，才需要填写接收单位和接收人"> </textarea></td> 
        <td align="center"><font color="red">*</font>接收人： </td>
        <td><input type="text" name="output_user_name" id="output_user_name" title="打印状态为外发时，才需要填写接收单位和接收人"/></td>
    </tr>
    <tr>
       <td align="center" id="specialprint"><font color="red">*</font>是否为特殊打印</td>
       <td>
       <input type="radio" value="Y" name="is_special" onclick="setIsSpecial(this.value)"/><font color="blue">是&nbsp;&nbsp;</font>
       <input type="radio" value="N" name="is_special" onclick="setIsSpecial(this.value)" checked/><font color="blue">否&nbsp;&nbsp;</font>
       <span id="sp2"></span>
       </td>
       <td align="center">具体说明:</td>
       <td><textarea name="comment" id="comment" rows="3" cols="40"></textarea></td>
    </tr>
    <tr>
       <td align="center"><font color="red">*</font>是否需要定密</td>
        <td>
        <input type="radio" value="Y" name="is_secret" onclick="setIsSecret(this.value)"/><font color="blue">是&nbsp;&nbsp;</font>
        <input type="radio" value="N" name="is_secret" onclick="setIsSecret(this.value)" checked/><font color="blue">否&nbsp;&nbsp;</font>
        </td>
       <td>&nbsp;</td>
       <td>&nbsp;</td>
    </tr>
    <tr id="secrettr1" style=display:none> 
    	<td colspan="4">
    		<table width="95%" border="1" cellspacing="0" cellpadding="0" align="center" class="table_box">
    			<tr>
    				<td width="10%" align="center">&nbsp;知悉范围库:</td>
			        <td width="30%">&nbsp;
			        	<s:iterator value="#request.fixList2" var="fix">
							<input type="radio" class="clearVal1" value="${fix.content}" name="file_scope_id" id="file_scope_id" onclick="selectFileScope()"/>${fix.content}
						</s:iterator>
			        </td>
			        <td width="10%" align="center"><font color="red">*</font>知悉范围(可编辑)：</td>
					<td width="30%"><textarea rows="3" cols="40" name="file_scope" id="file_scope" class="fixList2"></textarea>
					</td>
				</tr>
				<tr>
			        <td align="center">&nbsp;定密依据库:</td>
			        <td>&nbsp;
			        	<s:iterator value="#request.fixList1" var="fix">
							<input type="radio" class="clearVal2"  value="${fix.content}" name="seclv_accord_id" id="seclv_accord_id" onclick="selectSeclvAccord()"/>${fix.content}
						</s:iterator>
			        </td>
			        <td align="center"><font color="red">*</font>定密依据(可编辑)：</td>
					<td><textarea rows="3" cols="40" name="seclv_accord" id="seclv_accord" class="fixList1"></textarea>
					</td>
    			</tr>
    			<tr>   
			        <td align="center">&nbsp;保密期限库:</td>
			        <td>
			        	<s:iterator value="#request.fixList3" var="fix">
							<input type="radio" class="clearVal3"  value="${fix.content}" name="secret_time_id" id="secret_time_id" onclick="selectSecretTime()"/>${fix.content}
						</s:iterator>
			        </td> 
			        <td align="center"><font color="red">*</font>保密期限(可编辑)：</td>
					<td><textarea rows="3" cols="40" name="secret_time" id="secret_time" class="fixList3"></textarea>
					</td>
			    </tr>
    		</table>
    	</td>
    </tr>
</form>
    <tr id="tr_approver" style="display: none">
        <td align="center" id="selApprover1">选择审批人：</td>
        <td id="selApprover2" colspan="3">
            <table width="300" border="0" cellspacing="0" cellpadding="0" align="left" class="table_box_border_empty">
                <tr>
                    <td id="allApprovers">
                        <SELECT ondblclick="add_True('next_approver_all','next_approver_sel');" style="WIDTH: 100px" multiple="true" size="8" id="next_approver_all">
                            <c:forEach var="item" items="${userList}" varStatus="status">
                                <option value="${item.user_iidd}">${item.user_name}</option>
                            </c:forEach>
                        </SELECT>
                    </td>
                    <td aling="center" valign="middle">
                        <INPUT class="button_2003" onclick="add_MoreTrue('next_approver_all','next_approver_sel');" type="button" value="增加-->" name="btnAddItem" /><br/>
                        <br/>
                        <INPUT class="button_2003" onclick="del_MoreTrue('next_approver_sel');" type="button" value="删除<--" name="btnDelItem"><br/>
                    </td>
                    <td>
                        <SELECT size="8" multiple="true" style="WIDTH: 100px" ondblclick="del_True('next_approver_sel');" id="next_approver_sel">
                        </SELECT>
                    </td>
                </tr>
            </table>
        </td>
    </tr>
    <tr>
        <td colspan="4">
            <display:table uid="item" class="displaytable" name="eventList">
                <display:column title="选择">
                        <input type="checkbox" value="${item.id}" name="event_id" checked="checked" />
                </display:column>
                <display:column title="序号">
                    <c:out value="${item_rowNum}"/>
                </display:column>
                <display:column title="文件密级">
                    <select name="file_seclv_code" id="file_seclv_code" onchange="selectFileSeclv(this.value);">
                        <option value="${item.id}:">--请选择--</option>
                        <s:iterator value="#request.seclvList" var="seclv">
                            <option value="${item.id}:${seclv.seclv_code}">${seclv.seclv_name}</option>
                        </s:iterator>
                    </select>
                </display:column>
                <display:column title="文件名称" style="width:50px">
                    <input type="text" name="file_title" value="${item.file_title}" size="50" id="${item.id}"/>
                </display:column>
                <%-- <display:column title="提交时间" property="apply_time_str"/> --%>          
                <display:column title="打印类型" property="print_type_name"/>
                <display:column title="页数" property="page_count"/>
                <display:column title="份数">
                    <input type="text" name="print_count" value="${item.print_count}" size="3" id="${item.id}"/>
                </display:column>
                <display:column title="色彩">
                    <select name="file_color" disabled="disabled">
                        <option value="${item.id}:1" <c:if test="${item.color == '1'}">selected</c:if>>黑白</option>
                        <option value="${item.id}:2" <c:if test="${item.color == '2'}">selected='selected'</c:if>>彩色</option>
                    </select>
                </display:column>
                <display:column title="单/双面及翻页">
                    <select name="file_print_double">
                        <option value="${item.id}:2" <c:if test="${item.print_double == '2'}">selected</c:if>>双面左右翻</option>
                        <option value="${item.id}:1" >单面</option>
                        <option value="${item.id}:3">双面上下翻</option>
                    </select>
                </display:column>               
                <display:column title="纸张类型" property="page_size"/>
                <display:column title="关键字" >
                    <font color="red">${item.keyword_content}&nbsp;</font>
                </display:column>
                <display:column title="操作" style="width:120px"> 
                        <input type="button" class="button_2003" value="预览" onclick="showPrintFile('${item.st_filename}','${item.page_count}');"/>&nbsp;
                        <input type="button" class="button_2003" value="删除" onclick="if(confirm('确定要删除该作业？'))go('${ctx}/print/delprintevent.action?key=byId&id=${item.id}');"/>
                </display:column>
            </display:table>
        </td>
    </tr>
    <tr>
        <td colspan="6" align="center"> 
          <input type="button" class="button_2003" value="提交" onclick="chkSubmit();" id="submit_btn"/>&nbsp;
          <input class="button_2003" type="button" value="返回" onclick="javascript:history.go(-1)">&nbsp;
        </td>
    </tr>
</table>
</body>
</html>
