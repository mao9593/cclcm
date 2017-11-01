<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<HTML><HEAD>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="Pragma" content="No-cache">
<meta http-equiv="Cache-Control" content="no-cache, must-revalidate">
<meta http-equiv="Expires" content="-1">
<title>配置相关子系统</title>
<link href="${ctx}/_css/css200603.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="${ctx}/_css/displaytag.css" type="text/css" media="screen" />

<script language="JScript">
<!--
	function add_True(){
		if (document.all.allOption.selectedIndex > -1){
			selected_spr_text = document.all.allOption.options[document.all.allOption.selectedIndex].text;

			selected_spr_value = document.all.allOption.options[document.all.allOption.selectedIndex].value;
			var sel_sprlen = document.all.selectOption.options.length - 1;

			var exist_flag = 1;
			var j = 0;
			for(j = 0; j <= sel_sprlen; j++){
				if(document.all.selectOption.options[j].value == selected_spr_value){
					exist_flag = 0;
					break;
				}
			}

			if(exist_flag){
				var temp = new Option(selected_spr_text);
				temp.value = selected_spr_value;
				document.all.selectOption.options[++sel_sprlen] = temp;
			}
			else{
				alert("''" + selected_spr_text + "''  " + "该子系统已存在于右边列表中！请重新选择");
			}
		}
	}

	function add_MoreTrue(){
		if (document.all.allOption.selectedIndex > -1){
			var j = 0, k = 0;

			for(k = 0; k < document.all.allOption.options.length; k++){
				if(document.all.allOption.options[k].selected == true){
					var exist_flag = 1;
					selected_spr_text = document.all.allOption.options[k].text;
					selected_spr_value = document.all.allOption.options[k].value;
					var sel_sprlen = document.all.selectOption.options.length - 1;
					for(j = 0; j <= sel_sprlen; j++){
						if(document.all.selectOption.options[j].value == selected_spr_value){
                            exist_flag = 0;
                            break;
						}
					}

					if(exist_flag){
						var temp = new Option(selected_spr_text);
						temp.value = selected_spr_value;
						document.all.selectOption.options[++sel_sprlen] = temp;
					}
					else{
						alert("''" + selected_spr_text + "''  " + "该子系统已存在于右边列表中！请重新选择");
					}
				}
			}
		}
	}


	function add_all_True(){
		var sel_sprlen = document.all.selectOption.options.length - 1;
		var j = 0;
		for(j = sel_sprlen; j >= 0; j--){
            document.all.selectOption.options[j] = null;
		}
		sel_sprlen = document.all.allOption.options.length - 1;
		for(j = 0; j <= sel_sprlen; j++){
  	        var temp = new Option(document.all.allOption.options[j].text);
            temp.value = document.all.allOption.options[j].value;
            document.all.selectOption.options[j] = temp;
		}
	}

	function del_True(){
		var sel_sprindex = document.all.selectOption.selectedIndex;
		if(sel_sprindex != -1){
			document.all.selectOption.options[sel_sprindex] =null;
		}
	}

	function del_MoreTrue(){
		var k = 0;
		var temp = document.all.selectOption.options.length - 1;
		for(k = temp; k >= 0; k--){
			if(document.all.selectOption.options[k].selected == true){
                document.all.selectOption.options[k] = null;
			}
		}
	}

	function del_all_True(){
		var sel_sprlen = document.all.selectOption.options.length - 1;
		var j = 0;
		for(j = sel_sprlen; j >= 0; j--){
			document.all.selectOption.options[j] = null;
		}
	}

	function getSelectedCodes(){
        var codes = "";
        var subsysname="";
        for(j=0; j<document.all.selectOption.options.length; j++){
            codes += document.all.selectOption.options[j].value + ",";
            subsysname += document.all.selectOption.options[j].innerText + ",";
        }
        //alert(codes);
        var len = codes.length;
        //alert(len);
        if (len>0&&(codes.lastIndexOf(",") == len - 1)){
            codes = codes.substring(0, len - 1);
		}

		var len2 = subsysname.length;
        //alert(len);
        if (len2>0&&(subsysname.lastIndexOf(",") == len2 - 1)){
            subsysname = subsysname.substring(0, len2 - 1);
		}
        //alert(codes);
        return codes+"#"+subsysname;
    }
//-->
</script>
</head>
<body>
<table border="0" cellspacing="1" cellpadding="1" width="100%" height="100%" class="table_box">
  <tr>
    <td colspan="3" class="title_box">相关子系统选择</td>
  </tr>
	<tr>
		<td>
            <SELECT ondblclick="add_True();" style="WIDTH: 180px" multiple="true" size="16" name="allOption">
            	<c:forEach var="item" items="${allSubsysList}">
            		<option value="${item.subsys_code}">${item.subsys_name}</option>
            	</c:forEach>
            </SELECT>
		</td>
		<td aling="center" valign="middle">
            <INPUT class="button_2003" onclick="add_MoreTrue();" type="button" value="增加->" name="btnAddItem" /><br/>
            <INPUT class="button_2003" onclick="del_MoreTrue();" type="button" value="删除<-" name="btnDelItem"><br/>
            <INPUT class="button_2003" onclick="add_all_True();" type="button" value="全加>>" name="btnAddAll" /><br/>
            <INPUT class="button_2003" onclick="del_all_True();" type="button" value="全删<<" name="btnDelAll" />
		</td>
		<td>
            <SELECT name="selectOption" size="16" multiple="true" style="WIDTH: 180px" ondblclick="del_True();">
				<c:forEach var="item" items="${cfgedSubsysList}">
            		<option value="${item.subsys_code}">${item.subsys_name}</option>
            	</c:forEach>
            </SELECT>
		</td>
	</tr>
  <tr>
    <td colspan="3"  align="center">
	    <input type="button" value="确定" class="button_2003" onClick="window.returnValue=getSelectedCodes();window.close();" />&nbsp;
        <input type="button" value="取消" class="button_2003" onClick="window.close();">
    </td>
  </tr>
</table>
</BODY>
</HTML>