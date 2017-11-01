<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<title>查看纸张统计(按大小)</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="${ctx}/_css/css200603.css" type="text/css" media="screen" />
	<link rel="stylesheet" type="text/css" href="${ctx}/_css/displaytag.css" media="screen"/>
	<link href="${ctx}/_script/calendar2/calendar-blue.css" type="text/css" rel="stylesheet"/>
	<script language="javascript" src="${ctx}/_script/calendar2/calendar.js"></script>
	<script language="javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>
	<script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
	<script language="javascript" src="${ctx}/_script/casic304_ajax.js"></script>
	<script language="javascript" src="${ctx}/_script/highcharts/highcharts.js"></script>
	<script language="javascript" src="${ctx}/_script/highcharts/modules/exporting.js"></script>
	<script>
	$(document).ready(function(){
		onHover();
		preCalendarDay();
		if($("#dept_name").val() != ""){
			creatHighcharts();
		}
	});
	function preCalendarDay(){
		Calendar.setup({inputField: "start_time", button: "start_time_trigger", singleClick: true, showsTime: true, ifFormat: "%Y-%m-%d %H:%M:%S", cache: true, weekNumbers:true, showOthers: true, step: 1});
		Calendar.setup({inputField: "end_time", button: "end_time_trigger", singleClick: true, showsTime: true, ifFormat: "%Y-%m-%d %H:%M:%S", cache: true, weekNumbers:true, showOthers: true, step: 1});
	}	
	function clearFindForm(){
		$("#LedgerQueryCondForm :text").val("");
	}	
	function chk(){	
		if($("#dept_name").val() == ""){
			alert("请选择所查询的部门");
			$("#dept_name").focus();
			return false;
		}
		if(!checkDateTime()){
			return false;
		}
	    return true;
	}
	function exportLedger(formId,url,url1){
		document.getElementById(formId).action = url;
		document.getElementById(formId).submit();
		document.getElementById(formId).action = url1;
	}
	function creatHighcharts() {
	    var dept_names = $("#dept_names").val().split("#");
	    var page_counts = $("#page_counts").val().split("#");
	    var dept_page_counts = $("#dept_page_counts").val().split("#");
	    var size_names = $("#size_names").val().split("#");
    
        var colors = Highcharts.getOptions().colors;
        var name = 'Browser brands';
        // Build the data arrays
        var deptData = [];
        var sizeData = [];
        for (var i = 0; i < dept_names.length; i++) {
    
            deptData.push({
                name: dept_names[i],
                y: parseInt(dept_page_counts[i]),
                color: colors[i+3]
            });
            
    		var size_counts = size_names.length-1;
            for (var j = 0; j < size_counts; j++) {
                var brightness = 0.2 - (j / size_counts) / 5 ;
                sizeData.push({
                    name: size_names[j],
                    y: parseInt(page_counts[size_counts*i+j]),
                    color: Highcharts.Color(colors[i+3]).brighten(brightness).get()
                });
            }
        }
    
        // Create the chart
        $('#container').highcharts({
            chart: {
                type: 'pie'
            },
            title: {
                text: ''
            },
            yAxis: {
                title: {
                    text: '页数'
                }
            },
            plotOptions: {
                pie: {
                    shadow: false,
                    center: ['50%', '50%']
                }
            },
            tooltip: {
        	    valueSuffix: '页'
            },
            series: [{
                name: '合计',
                data: deptData,
                size: '60%',
                dataLabels: {
                    formatter: function() {
                        return this.y > 2 ? this.point.name : null;
                    },
                    color: 'white',
                    distance: -30
                }
            }, {
                name: '合计',
                data: sizeData,
                size: '80%',
                innerSize: '60%',
                dataLabels: {
                    formatter: function() {
                        return this.y > 1 ? '<b>'+ this.point.name +':</b> '+ Math.round(this.y*10000/parseInt(${page_sum}))/100 + '%' : null;
                    }
                }
            }]
        });
    }
	</script>
</head>

<body oncontextmenu="self.event.returnValue=false">
<form id="LedgerQueryCondForm" method="POST" action="${ctx}/basic/viewpaperstaticbysize.action">
	<input type="hidden" name="dept_names" id="dept_names" value="${dept_names}"/>
	<input type="hidden" name="page_counts" id="page_counts" value="${page_counts}"/>
	<input type="hidden" name="dept_page_counts" id="dept_page_counts" value="${dept_page_counts}"/>
	<input type="hidden" name="size_names" id="size_names" value="${size_names}"/>
	<table width="95%" border="0" cellspacing="0" cellpadding="0" align="center" class="table_only_border">
	<tr>
		<td class="title_box">纸张统计</td>
	</tr>
	<tr>
		<td>
			<table  table border=0 class="table_box" cellspacing="0" cellpadding="0" width="100%">
				<tr>
					<td align="center">
						部门：	
						<input type="text" width="100%"  id="dept_name" name="dept_name" readonly="readonly" style="cursor:hand;" onclick="openDeptSelect('dept_name','dept_id','radio')" value="${dept_name}" />&nbsp;&nbsp;
		      			<input type="hidden" name="dept_id" id="dept_id" value="${dept_id}"/>
		      		</td>	
					<td align="center">
						开始时间：
						<input type="text" name="start_time" readonly="readonly" style="cursor:hand;" value="${start_time}"/>
	        			<img src="${ctx}/_image/time2.jpg" id="start_time_trigger">
	        		</td>
	        		<td align="center">
						结束时间：
						<input type="text" name="end_time" readonly="readonly" style="cursor:hand;" value="${end_time}"/>
	        			<img src="${ctx}/_image/time2.jpg" id="end_time_trigger">
					</td>
					<td align="center">
						<input name="button" type="button" class="button_2003" value="查询" onclick="if(chk()) forms[0].submit();" >
						<input name="button" type="button" class="button_2003" value="清空" onclick="clearFindForm();">
						<input type="hidden" name="query" value="Y"/>
						<input type="button" class="button_2003" value="导出EXCEL" onclick="if(chk()) exportLedger('LedgerQueryCondForm','${ctx}/ledger/exportpaperstaticbysize.action','${ctx}/basic/viewpaperstaticbysize.action');"/>
					</td>
				</tr>
			</table>
		</td>
    </tr>
	</tr>
	<tr>
		<td>
			<table class="displaytable-outter" cellspacing=0 cellpadding=0 border=0 width="100%">
	 			<tr>
	   				<td>
						<display:table requestURI="${ctx}/basic/viewpaperstaticbysize.action" id="item" class="displaytable" name="paperStaticList" pagesize="15" sort="list" >	   					
							<display:column title="序号">
								<c:out value="${item_rowNum}"/>
							</display:column>													
							<display:column property="dept_name" title="部门名称"/>						
							<display:column property="page_size" title="纸张类型"/>
							<display:column property="page_count" title="页数"/>							
						</display:table>
					</td>
				</tr>
				<tr>
					<td align="right">总数合计：${page_sum}页&nbsp;&nbsp;&nbsp;&nbsp;</td>
				</tr>
			</table>
         </td>
	</tr>
	</table>
</form>
<div id="container" style="min-width: 400px; height: 400px; margin: 0 auto"></div>
</body>
</html>
